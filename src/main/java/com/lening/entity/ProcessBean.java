package com.lening.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ProcessBean {
    private  Integer id;
    private Integer sid;
    private Double qjtime;
    /**
     * 我们发情假条的时间
     */
    private  Date qjdate = new Date();

    public Date getQjdate() {
        return qjdate;
    }

    public void setQjdate(Date qjdate) {
        this.qjdate = qjdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private Date stime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private Date etime;
    private String qjcause;
    private Integer qjstatus;

    public Integer getQjstatus() {
        return qjstatus;
    }

    public void setQjstatus(Integer qjstatus) {
        this.qjstatus = qjstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Double getQjtime() {
        return qjtime;
    }

    public void setQjtime(Double qjtime) {
        this.qjtime = qjtime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getQjcause() {
        return qjcause;
    }

    public void setQjcause(String qjcause) {
        this.qjcause = qjcause;
    }
}
