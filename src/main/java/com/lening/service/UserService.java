package com.lening.service;

import com.lening.entity.*;
import com.lening.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface UserService {
    /**
     * 学生发起请求  开始在service层走流程
     *
     */
    void saveStuQj(Integer id, Double qjtime, Date stime, Date etime, String qjcause);
    void saveStuQj2(ProcessBean pb);

    //查询我的请假记录
    List<QjVo> getStuQjListBySid(Integer id);

    //我的审核
    List<QjVo> getQjshListByUserid(Integer id);

    //保存我的审核
    void saveWdsh(Integer pid, Integer shstatus, Integer id);


    List<UserBean> findAll();

    List<PowerBean> getPowerList();

    //查询单条员工信息（1.修改前先查询数据库有没有这条数据 2.查完之后执行修改方法）
    UserBean getUserById(Integer id);

    //查询部门
    List<BumenBean> getbumenList();

    //查询角色 条件部门id
    List<JueseBean> getRoleListByDeptid(UserBean userBean);

    List<JueseBean> getJueseListByDeptid(Integer deptid);

    void updateUserBumenJuese(Integer id, Integer deptid, Integer rid);

    /**
     * 删除
     *
     * @param id
     */
    void deleteUserid(Integer id);

    /**
     * 修改
     *
     * @param userBean
     */
    void updateUser(UserBean userBean);


    /**
     * 添加
     *
     * @param userBean
     */
    void addUser(UserBean userBean);

    /**
     * 模糊查询
     *
     * @param likeName
     * @return
     */
    List<UserBean> findLikeName(String likeName);

    /**
     * 范围查询
     *
     * @param
     * @return
     */
    List<UserBean> findByBirthday(String sbirthday, String ebirthday);

    /**
     * 分页
     *
     * @param sql
     * @return
     */
    List<UserBean> findUserByPage(String sql);

    void updatePortrait(Integer id, String portrait);

    /**
     * 登录
     *
     * @param userBean
     * @return
     */
    UserBean getLogin(UserBean userBean);

    List<PowerBean> getPowerListById(@Param("id") Integer id);

    /**
     * 查看权限
     *
     * @param id
     * @return
     */
    List<PowerBean> getUserPower(Integer id);

    //接口对接   解析 第一个参数 str1
    QueryVo jieXiStr1(String str1);

    //接口对接  解析 第二个参数 str2
    String jieXiStr2(String str2);

    //查询数据
    String getInfo(QueryVo vo);

    //<!--第二个接口，接受分厂发来的数据 酒水 香烟-->
    String saveData(QueryVo vo, String str2);



}
