package com.lening.entity;

public class BumenBean {
    private  Integer deptid;
    private  String dname;

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "BumenBean{" +
                "deptid=" + deptid +
                ", dname='" + dname + '\'' +
                '}';
    }
}
