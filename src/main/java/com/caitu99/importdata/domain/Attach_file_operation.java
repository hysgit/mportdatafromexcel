package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public class Attach_file_operation {
    private Long id;            //ID                   bigint not null,
    private String path;        //PATH                 varchar(256) not null comment '路径',
    private Integer status;     //STATUS               int not null comment '状态：0.文件未删除,1.文件已删除',
    private Date date_del_time;     //DATE_DEL_TIME        datetime not null comment '数据删除时间',
    private Date file_del_time;     //FILE_DEL_TIME        datetime comment '文件删除时间',

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate_del_time() {
        return date_del_time;
    }

    public void setDate_del_time(Date date_del_time) {
        this.date_del_time = date_del_time;
    }

    public Date getFile_del_time() {
        return file_del_time;
    }

    public void setFile_del_time(Date file_del_time) {
        this.file_del_time = file_del_time;
    }
}
