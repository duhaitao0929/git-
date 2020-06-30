package com.lening.service.impl;

import com.lening.entity.JueseBean;
import com.lening.entity.PowerBean;
import com.lening.mapper.JueseMapper;
import com.lening.service.JueseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class JueseServiceimpl implements JueseService {

    @Resource
    private JueseMapper jueseMapper;
    @Override
    public List<JueseBean> getJueseList() {
        List<JueseBean> jueseList = jueseMapper.getJueseList();
        return jueseList;
    }

    @Override
    public JueseBean getRoleByRid(Integer rid) {
        return jueseMapper.getRoleByRid(rid);
    }

    @Override
    public void updateDeptRole(Integer deptid, Integer[] rids) {
        jueseMapper.updateDeptidNull(deptid);
        for (Integer rid:rids) {
            jueseMapper.updateDeptidRole(deptid,rid);
        }
    }

    @Override
    public void updateRoleDept(Integer rid, Integer deptid) {
        jueseMapper.updateDeptidRole(deptid,rid);
    }

    @Override
    public List<PowerBean> getjuesePower(Integer rid) {
        //1.把权限全部查出来
        //2.把该角色的原来权限查出来
        //3.回显
        List<PowerBean> list=jueseMapper.getPowerList();
        List<Integer> ids=jueseMapper.getIdsByRid(rid);
        /**
         * 两个集合需要遍历的时候，要是全部遍历先后无所谓，
         * 要是里面有条件的遍历，先遍历小的，在遍历大的，效率高
         * 举例：list有100条数据，ids有10条数据
         * 先遍历  list   遍历的次数  100 -1000之间
         * 先遍历 ids    遍历次数     10-1000之间
         */
        if (ids!=null&&ids.size()>=1){
            if (list!=null&&list.size()>=1){
                for (Integer id : ids) {
                    for (PowerBean powerBean : list) {
                        if (id.equals(powerBean.getId())){
                            powerBean.setChecked(true);
                            /**
                             * 在这里加break是为了提高效率，要是不加，大小集合谁先遍历无所谓，都全部比遍历
                             * 复选框回显，当时页面的jstl里面没有break，所以出现了笛卡尔迪
                             */
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void saveJuesePower(Integer rid, String ids) {
        //先删除后新增
        jueseMapper.deleteJuesePowerByrid(rid);
        if (ids!=null&&ids.length()>=1){
            String[] sids = ids.split(",");
            for (String sid : sids) {
                jueseMapper.saveJuesePower(rid,sid);
            }
        }
    }
}
