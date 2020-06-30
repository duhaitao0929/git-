package com.lening.service;

import com.lening.entity.BumenBean;
import com.lening.entity.JueseBean;

import java.util.List;

public interface BumenService {
    //查询部门列表信息
    List<BumenBean> getbumenList();

    // <!-- 查询部门列表单个数据 id -->
    BumenBean getbumenByDeptid(Integer deptid);


    // <!-- 查询角色列表  -->
    List<JueseBean> getjueseList();

    // <!-- 再查询角色表里面的部门id  -->
    List<Integer> RidsByDeptid(Integer deptid);

    //<!-- 二级联动 在根据角色id 修改部门id  -->
    void saveDeptRole(Integer deptid, Integer[] rids);
}
