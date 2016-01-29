package com.caitu99.importdata;

import com.caitu99.importdata.domain.*;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中信商城特卖商品数据导入
 */

public class MainImport8 {
    private static final Logger logger = LoggerFactory.getLogger(MainImport8.class);
    // private static ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:spring.xml");
    // private static T_brandService t_brandService = applicationContext.getBean(T_brandService.class);
    private static Map<String, T_brand> map_brand = new HashMap<>();       //品牌 以品牌code为key
    private static Long map_brand_cnt = 24000L;

    private static Map<String, T_area_store> map_area_store = new HashMap<>();   //适用的地区数据，以品牌code、省、城市、店名、地址、电话连接起来为key
    private static Long map_area_store_cnt = 24000L;

    private static Map<String, T_item> map_item = new HashMap<>();      //兑换商品表 以商品代码为key
    private static Long map_item_cnt = 24000L;

    private static Map<String, T_sku> map_sku = new HashMap<>();        //商品明细表以item_id和SALE_PRICE联合为ky
    private static Long map_sku_cnt = 24000L;

    private static Map<String, T_stock> map_stock = new HashMap<>();   //商品库存表 以item_id 和SKU_ID CODE为key
    private static Long map_stock_cnt = 24000L;

    private static Map<String, T_type> map_type = new HashMap<>();      //类型 类型名为key
    private static Long map_type_cnt = 24000L;

    private static Map<String, T_type_item_relation> map_type_item_relation = new HashMap<>(); //item_id和type_id连接为key
    private static Long map_type_item_relation_cnt = 24000L;

    private static Long good_prop_id_cnt = 22000L;

    private static Map<String, Attach_file> map_file = new HashMap<>();
    private static Long map_file_cnt = 24000L;

    public static void main(String[] args) throws Exception {
        InputStream inputStream = MainImport8.class.getClassLoader().getResourceAsStream("log4j.properties");
        PropertyConfigurator.configure(inputStream);
        logger.debug("闪修侠数据导入");
        logger.info("开始处理数据.....");
        importDataToSQLFile();

    }

    public static void importDataToSQLFile() throws Exception {

        int wareCnt = 0;
        File file1 = new File("/home/hy/workspace/wrksp1/importdata/shanxiuxia/UBB");
        if (file1.exists()) {
            deleteFile(file1);
            logger.debug("删除UBB成功");
        }
        File file2 = new File("/home/hy/workspace/wrksp1/importdata/shanxiuxia/GOODS");
        if (file2.exists()) {
            deleteFile(file2);
            logger.debug("删除GOODS成功");
        }
//        File file3 = new File("D:\\zhongxinStore.sql");
//        if (file3.exists()) {
//            deleteFile(file3);
//            logger.error("删除zhongxinStore.sql成功");
//        }

        OutputStream outputStream = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/Store.sql");
        OutputStream out_item = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/item.sql");
        OutputStream out_brand = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/brand.sql");
        OutputStream out_attach_file = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/attach_file.sql");
        OutputStream out_type = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/type.sql");
        OutputStream out_sku = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/sku.sql");
        OutputStream out_type_item_relation = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/type_item_relation.sql");
        OutputStream out_good_prop = new FileOutputStream("/home/hy/work/sql/sqlsh/shanxiuxia/good_prop.sql");

        //加载xlsx文件
        InputStream inputStream = new FileInputStream("/home/hy/work/数据/年底采购_闪修侠/年底采购.xlsx");
        Workbook wb = new XSSFWorkbook(inputStream);

        //没有兑换券
        LinkedList<Coupon> stocks = getAllCode(wb);     //获取到所有的兑换券
        Sheet sheet0 = wb.getSheetAt(0);
        int x = sheet0.getLastRowNum();
        String sql = null;
        for (int i = 1; i <= x; i++) {
            wareCnt += 1;
            Row row = sheet0.getRow(i);     //读取一行商品信息
            if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK) {
                logger.debug("商品页遇到空行，结束！");
                break;
            }

            Excel_item excel_item = getRowData(row);    //解析一行的数据
            logger.debug("开始解析第{}行.......", i);
            //item
            String pruductCode = excel_item.getPruduct_code();      //商品编码为key
            T_item t_item = map_item.get(pruductCode);
            if (t_item == null) {
                //品牌
                String brandCode = excel_item.getBrand_code();
                T_brand brand = map_brand.get(brandCode);
                if (brand == null) {
                    logger.debug("有新品牌: {}", brandCode);
                    //处理品牌
                    brand = new T_brand();
                    brand.setBrand_id(++map_brand_cnt);     //品牌表id
                    brand.setBrand_no(brandCode);       //品牌编码
                    brand.setName(excel_item.getBrand_name());  //品牌名称
                    map_brand.put(brandCode, brand);
                    sql = genertorSqlForT_brand(brand);
                    out_brand.write(sql.getBytes());

                    //在这里面处理品牌范围
//                    Sheet brandSheet = wb.getSheet(brandCode);
//                    if (brandSheet != null) {
//                        int sheetCnt = brandSheet.getLastRowNum();
//                        logger.debug("开始处理品牌：{}的品牌范围",brandCode);
//                        for (int ii = 1; ii <= sheetCnt; ii++) {
//                            Row row1 = brandSheet.getRow(ii);
//                            if (row1.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)
//                            {
//                                logger.debug("品牌: {} 的范围为空",brandCode);
//                                break;
//                            }
//                            T_area_store t_area_store = new T_area_store();
//
//                            t_area_store.setArea_store_id(++map_area_store_cnt);
//                            t_area_store.setBrand_id(brand.getBrand_id());
//
//                            t_area_store.setProvince(row1.getCell(0).getStringCellValue());
//                            try {
//                                t_area_store.setCity(row1.getCell(1).getStringCellValue());
//                            } catch (Exception e) {
//                                logger.error("处理品牌范围数据时发生了异常：{} 品牌代码：brandCode: {}, ii: {}", e.getMessage(),brandCode,ii);
//                                throw e;
//                            }
//
//                            t_area_store.setShop_name(row1.getCell(2).getStringCellValue());
//                            t_area_store.setShop_address(row1.getCell(3).getStringCellValue());
//
//                            int row1celltype = row1.getCell(4).getCellType();
//                            if (row1celltype == Cell.CELL_TYPE_STRING) {
//                                t_area_store.setShop_phone(row1.getCell(4).getStringCellValue());
//                            } else if (row1celltype == Cell.CELL_TYPE_NUMERIC) {
//                                t_area_store.setShop_phone(String.valueOf((long) row1.getCell(4).getNumericCellValue()));
//                            } else {
//                                logger.error("处理品牌数据时有不正确的数据：品牌编码: {}, ii: {}",brandCode,ii);
//                                logger.error("单元格类型：{}",row1celltype);
//                            }
//                            String key = t_area_store.getBrand_id() + "" +
//                                    t_area_store.getProvince() +
//                                    t_area_store.getCity() +
//                                    t_area_store.getShop_name() +
//                                    t_area_store.getShop_address() +
//                                    (t_area_store.getShop_phone() + "");
//                            T_area_store t_area_store1 = map_area_store.get(key);
//                            if (t_area_store1 == null) {
//                                map_area_store.put(key, t_area_store);
//                                sql = genertorSqlForT_area_store(t_area_store);
//                                outputStream.write(sql.getBytes());
//                            }
//
//                        }
//                    }
                }

                //类别
                String type = excel_item.getPruduct_type();
                T_type tType = map_type.get(type);
                if (tType == null) {
                    //产生一个新的类别
                    tType = new T_type();
                    tType.setName(type);
                    if (type.equals("生活服务")) {
                        tType.setId(10004L);
                    } else if (type.equals("美食")) {
                        tType.setId(10001L);
                    } else if (type.equals("娱乐")) {
                        tType.setId(10003L);
                    } else {
                        tType.setId(++map_type_cnt);
                        sql = genertorSqlForT_type(tType);
                        out_type.write(sql.getBytes());
                    }
                    map_type.put(tType.getName(), tType);

                }

                //构建商品
                t_item = new T_item();
                t_item.setItem_no(pruductCode);     //商品编码
                t_item.setBrand_id(brand.getBrand_id());    //品牌id
                t_item.setTitle(excel_item.getPruduct_name());  //商品名
                t_item.setSort(excel_item.getId() + 14000); //排序      14000 闪修侠
                t_item.setContent(excel_item.getContent());
                t_item.setItem_id(++map_item_cnt);
                t_item.setMarket_price(excel_item.getMarket_price());//市场价
                t_item.setSale_price(excel_item.getSale_price());   //单价
                t_item.setSale_volume(excel_item.getMonth_sale());  //月销量
                t_item.setCost_price(excel_item.getCost_price());
                t_item.setStatus(1);                    //上架
                t_item.setVersion("0.00");
                t_item.setLimit_num(1);
                t_item.setWap_url("/pages/goods-detail.html?itemId=" + map_item_cnt);


                String picurl = null;
                //处理商品图片
                if (excel_item.getPic() != null) {
                    picurl = anlsImgGoods(brand.getBrand_no(), t_item.getItem_no(), excel_item.getPic(), out_attach_file, "t_item", t_item.getItem_id());
                }
                t_item.setPic_url(picurl);

                //处理内容中的图片
                String content = t_item.getContent();
                if (content != null) {
                    String url = "http://static.caitu99.com/list_page/store_list.html?brandid=";
                    String context = anlsContent(content, brand.getBrand_no(), t_item.getItem_no(), t_item.getItem_id(), outputStream);
                    context = context.replaceAll("\\*\\*\\*", "<a href=\"" + url + brand.getBrand_id() + "\">点击查看门店列表<a>");

                    t_item.setContent(context);
                }

                t_item.setSource(1);        //1：自己商城商品
                t_item.setSales_type("1001");       //财分
                map_item.put(t_item.getItem_no(), t_item);
                sql = genertorSqlForT_item(t_item);
                out_item.write(sql.getBytes());

                //处理商品明细
                String key = t_item.getItem_id() + "_" + t_item.getSale_price();
                T_sku t_sku = map_sku.get(key);
                if (t_sku == null) {
                    t_sku = new T_sku();
                    t_sku.setSku_id(++map_sku_cnt);
                    t_sku.setItem_id(t_item.getItem_id());
//                    t_sku.setProp_code(good_prop.getCode()+":"+good_prop2.getCode()+":"
//                            +good_prop3.getCode()+":"+good_prop4.getCode()+":"
//                            +good_prop5.getCode()+":"+good_prop6.getCode()
//                    );
//                    t_sku.setProp_name(null);
                    t_sku.setSale_price(t_item.getSale_price());
                    t_sku.setCost_price(t_item.getCost_price());
                    t_sku.setVersion("0.00");
                    map_sku.put(key, t_sku);
                    sql = genertorSqlForT_sku(t_sku);
                    out_sku.write(sql.getBytes());

                }

                //处理商品兑换码
                Iterator<Coupon> iter = stocks.iterator();
                while (iter.hasNext()) {
                    Coupon coupon = iter.next();
                    if (coupon.getPruduct_code().equals(t_item.getItem_no())) {
                        String key2 = coupon.getPruduct_code() + "" + coupon.getCode();
                        T_stock t_stock = map_stock.get(key2);
                        if (t_stock == null) {
                            t_stock = new T_stock();
                            t_stock.setItem_id(t_item.getItem_id());
                            t_stock.setSku_id(t_sku.getSku_id());
                            t_stock.setCode(coupon.getCode());
                            t_stock.setPassword(coupon.getPassword());
                            t_stock.setStatus(1);
                            t_stock.setVersion("0.00");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            t_stock.setEffective_time(sdf.parse(coupon.getEffective_time()));
                            t_stock.setStock_id(++map_stock_cnt);
                            map_stock.put(key2, t_stock);
                            sql = genertorSqlForT_stock(t_stock);
                            outputStream.write(sql.getBytes());
                        }
                        iter.remove();
                    }
                }

                //这个要最后处理
                //处理商品和类型的关系
                String itemId_typeId = t_item.getItem_id() + "_" + tType.getId();
                T_type_item_relation relation = map_type_item_relation.get(itemId_typeId);
                if (relation == null) {
                    relation = new T_type_item_relation();
                    relation.setITEM_ID(t_item.getItem_id());
                    relation.setTYPE_ID(tType.getId());
                    relation.setID(++map_type_item_relation_cnt);
                    map_type_item_relation.put(itemId_typeId, relation);
                    sql = genertorSqlForT_type_item_realation(relation);
                    out_type_item_relation.write(sql.getBytes());
                }
            }
        }
        logger.debug("解析完毕!");

        outputStream.close();
        inputStream.close();
        out_item.close();
        out_brand.close();
        out_attach_file.close();
        out_type.close();
        out_sku.close();
        out_type_item_relation.close();
        out_good_prop.close();
    }

    private static void deleteFile(File file1) {
        if (file1.exists()) {
            if (file1.isDirectory()) {
                String[] files = file1.list();
                for (String filestr : files) {
                    File file = new File(file1 + "/" + filestr);
                    if (file.isDirectory()) {
                        String[] str = file.list();
                        if (str.length == 0) {
                            boolean b = file.delete();
                            if (!b) {
                                logger.error(file.toPath() + "未删除成功!");
                            }
                        } else {
                            deleteFile(file);
                        }
                    } else {
                        boolean b = file.delete();
                        if (!b) {
                            logger.error(file.toPath() + "未删除成功!");
                        }
                    }
                }
                file1.delete();
            } else {
                boolean b = file1.delete();
                if (!b) {
                    logger.error(file1.toPath() + "未删除成功!");
                }
            }
        }

    }

    private static String genertorSqlForT_good_prop(Good_prop good_prop) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_good_prop`(`id`, `code`,`item_id`,`name`,`value`,`group_list`,`sort`,`use_type`,`create_time`,`update_time`) values(");
        sb.append(good_prop.getId() + ",");
        sb.append("'" + good_prop.getCode() + "',");
        sb.append(good_prop.getItem_id() + ",");
        sb.append("'" + good_prop.getPropName() + "',");
        sb.append("'" + good_prop.getValue() + "',");
        sb.append(good_prop.getGroup() + ",");     //group
        sb.append(good_prop.getSort() + ",");     //sort
        sb.append("0,");
        sb.append("now(),now());\r\n");
        return sb.toString();
    }


    protected static LinkedList<Coupon> getAllCode(Workbook wb) {
        Sheet sheetCode = wb.getSheet("replenishments");
        LinkedList<Coupon> stocks = new LinkedList<>();
        int x = sheetCode.getLastRowNum();

        for (int i = 1; i <= x; i++) {
            Row row = sheetCode.getRow(i);
            if (row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK) {

                break;
            }
            Coupon coupon = new Coupon();

            int type = row.getCell(0).getCellType();
            if (type == Cell.CELL_TYPE_NUMERIC) {
                coupon.setPruduct_code(String.valueOf((long) row.getCell(0).getNumericCellValue()).trim());
            } else if (type == Cell.CELL_TYPE_STRING) {
                coupon.setPruduct_code(row.getCell(0).getStringCellValue().trim());
            } else {
                System.out.println("提取兑换码时出错");
            }

            type = row.getCell(1).getCellType();
            if (type == Cell.CELL_TYPE_NUMERIC) {
                coupon.setCode(String.valueOf((long) row.getCell(1).getNumericCellValue()).trim());
            } else if (type == Cell.CELL_TYPE_STRING) {
                coupon.setCode(row.getCell(1).getStringCellValue().trim());
            } else {
                System.out.println("提取兑换码时出错");
            }

            Cell cell = row.getCell(2);
            if (cell != null) {
                type = cell.getCellType();
                if (type == Cell.CELL_TYPE_NUMERIC) {
                    String str = String.valueOf((long) row.getCell(2).getNumericCellValue());
                    if (str.trim().equals(""))
                        coupon.setPassword(null);
                    else
                        coupon.setPassword(str.trim());
                } else if (type == Cell.CELL_TYPE_STRING) {
                    String str = row.getCell(2).getStringCellValue();
                    if (str.trim().equals(""))
                        coupon.setPassword(null);
                    else
                        coupon.setPassword(str.trim());
                } else if (type == Cell.CELL_TYPE_BLANK) {
                    coupon.setPassword(null);
                } else {
                    System.out.println("提取兑换码时出错");
                }
            } else {
                coupon.setPassword(null);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = row.getCell(3).getDateCellValue();
            coupon.setEffective_time(sdf.format(date));
            stocks.add(coupon);


        }
        return stocks;
    }


    private static Excel_item getRowData(Row row) {
        try {
            Excel_item item = new Excel_item();
            //读取一行数据

            item.setId((long) row.getCell(0).getNumericCellValue());    //id A
            item.setBrand_name(row.getCell(1).getStringCellValue());    //品牌名称 B
            item.setPruduct_name(row.getCell(2).getStringCellValue());  //商品名称  C

            int celltype = row.getCell(3).getCellType();                //品牌编码 D
            if (celltype == Cell.CELL_TYPE_STRING) {
                item.setBrand_code(row.getCell(3).getStringCellValue());
            } else if (celltype == Cell.CELL_TYPE_NUMERIC) {
                item.setBrand_code(String.valueOf((long) row.getCell(3).getNumericCellValue()));
            } else {
                System.out.println("Error");
                throw new RuntimeException("类型转换错误");
            }
            celltype = row.getCell(4).getCellType();    //商品编码 E
            if (celltype == Cell.CELL_TYPE_STRING) {
                item.setPruduct_code(row.getCell(4).getStringCellValue());
                //logger.error("商品编码不是纯数字");
                //System.exit(0);
            } else if (celltype == Cell.CELL_TYPE_NUMERIC) {
                long l = (long) row.getCell(4).getNumericCellValue();
                item.setPruduct_code(String.valueOf(l));
                //item.setBrand_code(String.valueOf(l / 100));
            } else {
                System.out.println("Error");
                throw new RuntimeException("类型转换错误");
            }
            item.setCost_price((long) row.getCell(5).getNumericCellValue());                //成本价 F

            item.setSale_price((long) row.getCell(6).getNumericCellValue());                //单价 G
            //item.setBase_value((long) row.getCell(6).getNumericCellValue());                //base_value

            item.setMarket_price((long) row.getCell(7).getNumericCellValue());        //市场价 H



            item.setPic(row.getCell(8).getStringCellValue());                           //图片类型 K

            Cell cell = row.getCell(9);
            if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
                item.setMonth_sale(null);        //月销量
            else
                item.setMonth_sale((long) cell.getNumericCellValue());        //月销量  L

            item.setPruduct_type(row.getCell(10).getStringCellValue());                 //商品类型 类别   M

            Cell cell2 = row.getCell(11);
            if (cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING)
                item.setRange(cell2.getStringCellValue());         //是否有范围  N
            else
                item.setRange(null);

            //购买限制
//        Cell cellLimitedNum = row.getCell(14);
//        if (cellLimitedNum != null && cellLimitedNum.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//            item.setLimitedNum((int) new Double(cellLimitedNum.getNumericCellValue()).longValue());
//        } else {
//            logger.error("限制数量错误,{}", row);
//            throw new RuntimeException("限制数量有误");
//        }


            Cell cell3 = row.getCell(12);                                       //详细信息 R
            if (cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING)
                item.setContent(cell3.getStringCellValue());
            else {
                logger.error("商品详情出错");
                throw new RuntimeException("商品详情出错");
            }

            return item;
        } catch (Exception e) {
            logger.error("转换一行商品数据发生错误",e);
            System.exit(0);
            return null;
        }
    }

    private String getStringValue(int num, Row row) {
        Cell cell3 = row.getCell(num);                                       //详细信息
        if (cell3 == null) {
            logger.error("cell为空");
            System.exit(0);
        }

        if (cell3.getCellType() == Cell.CELL_TYPE_STRING) {
            return row.getCell(num).getStringCellValue();
        } else {
            logger.error("出错");
            System.exit(0);
            return "";
        }
    }

    public static String filter(String message) {

        if (message == null)
            return (null);

        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#39;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());

    }

    private static String genertorSqlForT_brand(T_brand t_brand) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_brand`(`BRAND_ID`, `BRAND_NO`,`NAME`,");
        if (t_brand.getMemo() != null) {
            sb.append("`MEMO`,");
        }
        sb.append("`CREATE_TIME`, `UPDATE_TIME`)");

        sb.append(" values(");
        sb.append(t_brand.getBrand_id() + ",");
        sb.append("'" + t_brand.getBrand_no() + "',");
        sb.append("'" + t_brand.getName() + "',");
        if (t_brand.getMemo() != null) {
            sb.append("'" + t_brand.getMemo() + "',");
        }
        sb.append("now(),now());\r\n");

        return sb.toString();
    }

    private static String genertorSqlForT_area_store(T_area_store t_area_store) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_area_store`(`AREA_STORE_ID`, `BRAND_ID`, `PROVINCE`, `CITY`, `SHOP_NAME`, `SHOP_ADDRESS`, `SHOP_PHONE`, `CREATE_TIME`, `UPDATE_TIME`) values(");
        sb.append(t_area_store.getArea_store_id() + ",");
        sb.append(t_area_store.getBrand_id() + ",");
        sb.append("'" + t_area_store.getProvince() + "',");
        sb.append("'" + t_area_store.getCity() + "',");
        sb.append("'" + t_area_store.getShop_name() + "',");
        sb.append("'" + t_area_store.getShop_address() + "',");
        sb.append("'" + t_area_store.getShop_phone() + "',");
        sb.append("now(),now()");
        sb.append(");\r\n");


        return sb.toString();
    }

    private static String genertorSqlForT_type(T_type t_type) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_type`(`ID`, `NAME`, `use_type`,`CREATE_TIME`, `UPDATE_TIME`) values(");
        sb.append(t_type.getId() + ", ");
        sb.append("'" + t_type.getName() + "', ");
        sb.append("now(),now()");
        sb.append(");\r\n");

        return sb.toString();
    }

    private static String genertorSqlForT_item(T_item t_item) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_item`(`ITEM_ID`, `TITLE`, `ITEM_NO`, `BRAND_ID`, `SALE_PRICE`, " +
                "`MARKET_PRICE`, `SALE_VOLUME`, `CONTENT`, `VERSION`, `STATUS`," +
                "`LIST_TIME`, `WAP_URL`, `SORT`, `PIC_URL`, `CREATE_TIME`, `UPDATE_TIME`," +
                "`source`,`sales_type`,`limit_num`) values(");
        sb.append(t_item.getItem_id() + ", ");
        sb.append("'" + t_item.getTitle() + "', ");
        sb.append("'" + t_item.getItem_no() + "',");
        sb.append(t_item.getBrand_id() + ",");
        sb.append(t_item.getSale_price() + ",");
        sb.append(t_item.getMarket_price() + ",");
        sb.append(t_item.getSale_volume() + ",");
        sb.append("'" + t_item.getContent() + "',");    //        sb.append("'" + filter(t_item.getContent()) + "',");
        sb.append("'" + t_item.getVersion() + "',");
        sb.append(t_item.getStatus() + ",");
        sb.append("now(),");
        sb.append("'" + t_item.getWap_url() + "',");
        sb.append(t_item.getSort() + ",");
        if (t_item.getPic_url() == null) {
            sb.append("null,");
        } else {
            sb.append("'" + t_item.getPic_url().replaceAll("\\\\", "/") + "',");
        }
        sb.append("now(),now(),");
        sb.append(t_item.getSource() + ",");
        sb.append("1001,");     //销售方式，1001：自带积分
        sb.append(t_item.getLimit_num());
        sb.append(");\r\n");

        return sb.toString();

    }

    private static String genertorSqlForAttach_file(Attach_file attach_file) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_attach_file`(`ID`, `PATH`, `TABLE_NAME`,`TABLE_ID`, `CODE`, `SORT`, `STATUS`, `CREATE_TIME`, `UPDATE_TIME`) values(");
        sb.append(attach_file.getId() + ",");
        sb.append("'" + attach_file.getPath().replaceAll("\\\\", "/") + "',");
        sb.append("'" + attach_file.getTable_name() + "',");
        sb.append(attach_file.getRow_id() + ",");
        sb.append("'" + attach_file.getCode() + "',");
        sb.append(attach_file.getSort() + ",");
        sb.append(attach_file.getStatus() + ",");
        sb.append("now(),now()");
        sb.append(");\r\n");

        return sb.toString();
    }

//    private static String genertorSqlForAttach_file_operation(Attach_file_operation attach_file_operation)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("insert into `caitu99`.`t_attach_file_operation`(`ID`, `PATH`, `STATUS`) values(");
//        sb.append(attach_file_operation.getId()+",");
//        sb.append("'"+attach_file_operation.getPath().replaceAll("\\\\", "\\\\\\\\")+"',");
//        sb.append(attach_file_operation.getStatus());
//        sb.append(");\r\n");
//
//        return sb.toString();
//    }

    private static String genertorSqlForT_sku(T_sku t_sku) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_sku`(`SKU_ID`, `ITEM_ID`, `SALE_PRICE`,`cost_price`, `VERSION`, `CREATE_TIME`, `UPDATE_TIME`) values(");
        sb.append(t_sku.getSku_id() + ",");
        sb.append(t_sku.getItem_id() + ",");
        //sb.append("'"+t_sku.getProp_code() + "',");          //新增
        // sb.append("'"+t_sku.getProp_name() + "',");              //新增
        sb.append(t_sku.getSale_price() + ",");
        sb.append(t_sku.getCost_price() + ",");
        sb.append("'" + t_sku.getVersion() + "',");
        sb.append("now(),now()");
        sb.append(");\r\n");

        return sb.toString();
    }

    public static String genertorSqlForT_stock(T_stock t_stock) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_stock`(`STOCK_ID`, `ITEM_ID`, `SKU_ID`, `CODE`, `PASSWORD`, `STATUS`, `VERSION`, `EFFECTIVE_TIME`, " +
                "`CREATE_TIME`) values(");
        sb.append(t_stock.getStock_id() + ",");
        sb.append(t_stock.getItem_id() + ",");
        sb.append(t_stock.getSku_id() + ",");
        sb.append("'" + t_stock.getCode() + "',");
        if (t_stock.getPassword() == null) {
            sb.append("null,");
        } else {
            sb.append("'" + t_stock.getPassword() + "',");
        }
        sb.append(t_stock.getStatus() + ",");
        sb.append("'" + t_stock.getVersion() + "',");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.format(t_stock.getEffective_time())
//        sb.append("DATE_FORMAT(" + sdf.format(t_stock.getEffective_time()) + ",%Y-%m-%d)" + ",");
        sb.append("'" + sdf.format(t_stock.getEffective_time()) + "',");


        sb.append("now()");
        sb.append(");\r\n");

        return sb.toString();
    }

    private static String genertorSqlForT_type_item_realation(T_type_item_relation t_type_item_relation) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `caitu99`.`t_type_item_relation`(`ID`, `ITEM_ID`, `TYPE_ID`, `CREATE_TIME`, `UPDATE_TIME`) values(");
        sb.append(t_type_item_relation.getID() + ", ");
        sb.append(t_type_item_relation.getITEM_ID() + ",");
        sb.append(t_type_item_relation.getTYPE_ID() + ", ");
        sb.append("now(), now()");
        sb.append(");\r\n");

        return sb.toString();
    }

    private static String anlsImgGoods(String brandCode, String productCode, String imgtype, OutputStream out, String tabname, Long productid) throws IOException {
        String path = "/home/hy/work/数据/年底采购_闪修侠/img/";
        String imgstore = "/home/hy/workspace/wrksp1/importdata/shanxiuxia";
        String returl = null;
        Integer cnt = 1;
//        while(true) {
        File file = new File(path + brandCode + "/" + productCode + "/main/" + "1." + imgtype.toLowerCase());  //真实路径
        if (file.exists()) {
            String url = "/GOODS/" + brandCode + "/" + productCode + "/" + System.currentTimeMillis();

            try {
                FileInputStream input = new FileInputStream(file);        //可替换为任何路径何和文件名
                File newFile = new File(imgstore + url);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }

                FileOutputStream output = new FileOutputStream( imgstore+ url + "/1." + imgtype.toLowerCase());//可替换为任何路径何和文件名
                int in = input.read();
                while (in != -1) {
                    output.write(in);
                    in = input.read();
                }
                input.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

            //if (cnt == 1) {
            returl = url + "/1." + imgtype.toLowerCase();
            //  }
            Attach_file attach_file = new Attach_file();
            attach_file.setId(++map_file_cnt);
            attach_file.setTable_name("t_item");
            attach_file.setRow_id(productid);
            attach_file.setPath(returl);
            attach_file.setStatus(1);
            attach_file.setCode("GOOD");
            attach_file.setSort(cnt);
            map_file.put(url, attach_file);
            String sql2 = genertorSqlForAttach_file(attach_file);
            out.write(sql2.getBytes());
        } else {
            logger.error("获取商品图片失败 图片不存在：路径：{}", file.getPath());
            System.exit(0);
        }


//        }


        return returl;
    }

    private static String anlsContent(String content, String brandCode, String productCode, Long productid, OutputStream out) {
        int cnt = 1;
        Pattern pattern = Pattern.compile("(?<=<img src=\").*?(?=\")");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String group = matcher.group(0);
            String path = "/home/hy/work/数据/年底采购_闪修侠";
            String imgstore = "/home/hy/workspace/wrksp1/importdata/shanxiuxia";
            File file = new File(path + group);
            if (file.exists()) {
                try {
                    FileInputStream input = new FileInputStream(file);        //可替换为任何路径和文件名

                    String url = "/UBB/" + brandCode + "/" + productCode + "/" + "deatails/" + System.currentTimeMillis();
                    File newFile = new File(imgstore+ url);
                    if (!newFile.exists()) {
                        newFile.mkdirs();
                    }
                    String filename = group.substring(group.lastIndexOf("/"));//获取到文件名
                    FileOutputStream output = new FileOutputStream(imgstore + url + filename);//可替换为任何路径何和文件名
                    int in = input.read();
                    while (in != -1) {
                        output.write(in);
                        in = input.read();
                    }
                    content = content.replaceAll(group, url + filename);

                    Attach_file attach_file = new Attach_file();
                    attach_file.setId(++map_file_cnt);
                    attach_file.setTable_name("t_item");
                    attach_file.setRow_id(productid);
                    attach_file.setPath(url + filename);
                    attach_file.setStatus(1);
                    attach_file.setCode("UBB");
                    attach_file.setSort(cnt++);
                    map_file.put(url, attach_file);
                    String sql2 = genertorSqlForAttach_file(attach_file);
                    //   out.write(sql2.getBytes());
                    input.close();

                } catch (IOException e) {

                    logger.error("存储文件出错: {}", e.getMessage());
                    System.exit(0);
                }

            } else {
                logger.error("文件不存在：文件路径：{}, 品牌id:{}, 商品id：{}", file.getPath(), brandCode, productCode);
                System.exit(0);
            }


            logger.debug(group);
        }
        return content;
    }
}