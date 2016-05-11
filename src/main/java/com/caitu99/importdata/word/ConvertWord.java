package com.caitu99.importdata.word;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by hy on 16-5-11.
 */
public class ConvertWord {
    public static void main(String[] args) throws Exception{
        InputStream inputStream = new FileInputStream("/home/hy/work/xiaozhi/中等生提分/分式的乘除（A）——修改版.docx");
        String path = "/home/hy/work/xiaozhi/中等生提分/分式的乘除（A）——修改版.docx";

        XWPFDocument docx = new XWPFDocument(inputStream);

        List<XWPFParagraph> paragraphs = docx.getParagraphs();
        List<XWPFPictureData> allPictures = docx.getAllPictures();
        for (XWPFParagraph paragraph : paragraphs) {

            CTP ctp = paragraph.getCTP();
            String style = paragraph.getStyle();

            System.out.println();
        }
        String path2 = System.getProperty("user.dir")+"/now/";
        File file = new File(path2);
        if(!file.exists())
        {
            file.mkdirs();
        }
        deleteDir(file);
        System.out.println(path2);
        for (XWPFPictureData xwpfPictureData : allPictures) {

            String picsf = getPichouz(xwpfPictureData.getPictureType());
            byte[] data = xwpfPictureData.getData();

            OutputStream out = new FileOutputStream(path2+ UUID.randomUUID().toString()+picsf);
            out.write(data);
            out.close();
        }

        inputStream.close();
        System.out.println();
    }

    public static String getPichouz(int type)
    {
        switch (type)
        {
            case Document.PICTURE_TYPE_PNG:return ".png";
            case Document.PICTURE_TYPE_JPEG:
                return ".jpeg";
            case Document.PICTURE_TYPE_BMP:
                return ".bmp";
            case Document.PICTURE_TYPE_WMF:
                return ".wmf";
            default:
                System.out.println(type);
                throw new RuntimeException("error type:"+type);

        }
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();//递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        else
        {
            return dir.delete();
        }
        return false;
    }
}
