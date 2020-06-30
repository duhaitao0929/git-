package com.lening.service.impl;

import com.lening.entity.BumenBean;
import com.lening.entity.JueseBean;
import com.lening.mapper.BumenMapper;
import com.lening.service.BumenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BumenServiceimpl implements BumenService {

    @Resource
    private BumenMapper bumenMapper;

    //查询部门列表信息
    @Override
    public List<BumenBean> getbumenList() {
        return bumenMapper.getbumenList();
    }

    //查询部门列表单个信息
    @Override
    public BumenBean getbumenByDeptid(Integer deptid) {
        return bumenMapper.getbumenByDeptid(deptid);
    }

    //查询角色列表
    @Override
    public List<JueseBean> getjueseList() {
        /**
         * 是去查询角色列表的，可以把角色的mapper注入进来，直接查询，
         * 自己查询也行，但是角色的里面肯定会用这个
         */
        return bumenMapper.getjueseList();
    }
    //再查询角色列表里面的部门id
    @Override
    public List<Integer> RidsByDeptid(Integer deptid) {
        return bumenMapper.RidsByDeptid(deptid);
    }

    @Override
    public void saveDeptRole(Integer deptid, Integer[] rids) {
        /**
         * 把这个部门原来的rid全部清空，其实就是去Role里面把deptid=传过来的这个deptid的删掉
         */
        if (deptid != null) {
            bumenMapper.deleteJueseByDeptid(deptid);
            if (rids != null && rids.length >= 1) {
                for (Integer rid : rids) {
                    bumenMapper.insertBumenJuese(deptid, rid);
                }
            }
        }
    }
}
