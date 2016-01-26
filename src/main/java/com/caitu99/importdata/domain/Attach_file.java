package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public class Attach_file {
    private Long id;        //                   bigint not null auto_increment,
    private String path;        //                 varchar(256) not null comment '路径',
    private String table_name;          //TABLE_NAME           varchar(64) not null comment '关联表名',
    private Long row_id;          //TABLE_ID             bigint not null comment '关联表编号',
    private String code;
    private Integer sort;
    private Integer status;     //STATUS               int not null comment '状态-1：删除，1.在用',

    private Date create_time;       //CREATE_TIME          datetime not null comment '创建时间',
    private Date update_time;           //UPDATE_TIME          datetime not null comment '修改时间',


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public Long getRow_id() {
        return row_id;
    }

    public void setRow_id(Long row_id) {
        this.row_id = row_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
