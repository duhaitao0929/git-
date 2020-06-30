package com.lening.mapper;

import com.lening.entity.BumenBean;
import com.lening.entity.JueseBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BumenMapper {

    //查询部门列表信息
    List<BumenBean> getbumenList();

    // <!-- 查询部门列表单个数据 id -->
    BumenBean getbumenByDeptid(@Param("deptid") Integer deptid);
    // <!-- 查询角色列表  -->
    List<JueseBean> getjueseList();
    //查询角色列表里面的部门id
    List<Integer> RidsByDeptid(@Param("deptid") Integer deptid);


    // <!-- 先把角色表里面的部门id 变空  -->
    void deleteJueseByDeptid(@Param("deptid")Integer deptid);

    //<!-- 二级联动 在根据角色id 修改部门id  -->
    void insertBumenJuese(@Param("deptid") Integer deptid, @Param("rid") Integer rid);


}
