package com.lening.entity;

public class JueseBean {
    private Integer rid;
    private String rname;

    private BumenBean bumenBean=new BumenBean();

    public BumenBean getBumenBean() {
        return bumenBean;
    }

    public void setBumenBean(BumenBean bumenBean) {
        this.bumenBean = bumenBean;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "JueseBean{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                '}';
    }
}
