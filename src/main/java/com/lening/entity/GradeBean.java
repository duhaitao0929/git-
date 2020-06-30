package com.lening.entity;


public class GradeBean {

    private Integer gid;
    private String gname;
    private Integer tid;
    private Integer fid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "GradeBean{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", tid=" + tid +
                ", fid=" + fid +
                '}';
    }
}
