package com.lening.service;

import com.lening.entity.JueseBean;
import com.lening.entity.PowerBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JueseService {

    //查询角色列表
    List<JueseBean> getJueseList();


    JueseBean getRoleByRid(Integer rid);

    void updateDeptRole(Integer deptid, Integer[] rids);

    void updateRoleDept(Integer rid, Integer deptid);

    List<PowerBean> getjuesePower(Integer rid);

    void saveJuesePower(Integer rid, String ids);
}

