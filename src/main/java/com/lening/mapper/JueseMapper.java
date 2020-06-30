package com.lening.mapper;

import com.lening.entity.JueseBean;
import com.lening.entity.PowerBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JueseMapper {
    //查询角色列表
    List<JueseBean> getJueseList();

    JueseBean getRoleByRid(Integer rid);

    void updateDeptidNull(Integer deptid);

    void updateDeptidRole(@Param("deptid") Integer deptid,@Param("rid") Integer rid);

    //查询权限所有
    List<PowerBean> getPowerList();
    //查询权限和角色的中间表里面 角色rid
    List<Integer> getIdsByRid(@Param("rid")Integer rid);

    void deleteJuesePowerByrid(@Param("rid")Integer rid);

    void saveJuesePower(@Param("rid")Integer rid,@Param("sid") String sid);
}
