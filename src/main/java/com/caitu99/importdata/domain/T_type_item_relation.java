package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/23 0023.
 */
public class T_type_item_relation {

    private  Long ID;       //                   bigint,
    private Long ITEM_ID;       //              bigint,
    private Long TYPE_ID;       //              bigint,
    private Date CREATE_TIME;//          datetime,
    private Date UPDATE_TIME;  //        datetime    );

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(Long ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public Long getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(Long TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Date UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }
}
