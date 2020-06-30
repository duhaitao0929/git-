package com.lening.entity;

import java.util.Date;


public class UserBean {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Date birthday;
    private String portrait;
    private Integer gid;
    private Integer rid;
    /**
     * 在这里，建议大家直接对部门进行初始化，
     * 区别：不初始化，要是用户没有部门的时候，deptBean对象为空  null，
     * 在前台页面我们展示的时候，u.deptBean.dname,在部分前端语言里面，会报错
     * 不初始化，u.deptBean = null
     */
    //部门表
    private BumenBean bumenBean = new BumenBean();

    //角色表
    private  JueseBean jueseBean = new JueseBean();
    private GradeBean gradeBean=new GradeBean();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public GradeBean getGradeBean() {
        return gradeBean;
    }

    public void setGradeBean(GradeBean gradeBean) {
        this.gradeBean = gradeBean;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    public JueseBean getJueseBean() {
        return jueseBean;
    }

    public void setJueseBean(JueseBean jueseBean) {
        this.jueseBean = jueseBean;
    }

    public BumenBean getBumenBean() {
        return bumenBean;
    }

    public void setBumenBean(BumenBean bumenBean) {
        this.bumenBean = bumenBean;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", portrait='" + portrait + '\'' +
                ", gid=" + gid +
                ", rid=" + rid +
                ", bumenBean=" + bumenBean +
                ", jueseBean=" + jueseBean +
                ", gradeBean=" + gradeBean +
                '}';
    }
}
