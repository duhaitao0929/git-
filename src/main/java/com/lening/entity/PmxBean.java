package com.lening.entity;

import java.util.Date;


public class PmxBean {
    private Integer id;
    private Integer pid;
    private Integer userid;
    private Integer status;
    private String pyijian;
    private Integer pstatus;
    private Integer pshunxu;
    private Date shdate = new Date();

    public Date getShdate() {
        return shdate;
    }

    public void setShdate(Date shdate) {
        this.shdate = shdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPyijian() {
        return pyijian;
    }

    public void setPyijian(String pyijian) {
        this.pyijian = pyijian;
    }

    public Integer getPstatus() {
        return pstatus;
    }

    public void setPstatus(Integer pstatus) {
        this.pstatus = pstatus;
    }

    public Integer getPshunxu() {
        return pshunxu;
    }

    public void setPshunxu(Integer pshunxu) {
        this.pshunxu = pshunxu;
    }
}
