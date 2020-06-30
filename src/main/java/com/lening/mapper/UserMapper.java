package com.lening.mapper;

import com.lening.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface UserMapper {
    List<UserBean> findAll();

    List<PowerBean> getPowerList();


    /**
     * 一个可以不用写注解，两个及两个以上必须写
     */
    UserBean getUserById(@Param("id")Integer id);

    List<BumenBean> getbumenList();

    List<JueseBean> getRoleListByDeptid(Integer deptid);


    void updateUserBumenJuese(@Param("id") Integer id, @Param("deptid") Integer deptid,@Param("rid") Integer rid);

    /**
     * 删除
     * @param id
     */
    void deleteUserid(@Param("id")Integer id);

    /**
     * 修改
     * @param userBean
     */
    void updateUser(UserBean userBean);


    /**
     * 添加
     * @param userBean
     */
    void addUser(UserBean userBean);

    /**
     * 模糊查询
     * @param likeName
     * @return
     */
    List<UserBean> findLikeName(@Param("likeName")String likeName);

    /**
     * 范围查询
     * @param map
     * @return
     */
    List<UserBean> findByBirthday(Map<String,String > map);

    /**
     * 分页
     * @param sql
     * @return
     */
    List<UserBean> findUserByPage(@Param("sql") String sql);

    void updatePortrait(@Param("id") Integer id,@Param("portrait") String portrait);

    /**
     * 登录
     * @param username
     * @return
     */
    List<UserBean> getLogin(@Param("username") String username);
    List<PowerBean> getPowerListById(@Param("id") Integer id);

    List<PowerBean> getUserPower(@Param("id")Integer id);
    //香烟
    RsBean getRsSmoke(@Param("cardno")String cardno);
    //酒水
    RsBean getRsWine(@Param("cardno")String cardno);

    void saveSmoke(SmokeBean smokeBean);

    void saveWine(WineBean wineBean);

    /**
     * 学生请假流程
     * @param pb
     */

    //当学生发起请假的请求时，保存 请假条
    void insertPorcess(ProcessBean pb);
    //查询学生的班级
    GradeBean getGradeByGid(@Param("gid") Integer gid);
    //保存请假流程明细
    void insertProcessPmx(PmxBean pmxBean);

    //我的请假记录
    //查询vo 根据sid查询只能查询出一部分
    List<QjVo> getStuQjListBySid(@Param("sid") Integer sid);

    Integer getUserIdByPid(@Param("pid") Integer pid);

    Integer getUserIdByPidMaxShunxu(@Param("pid") Integer pid);

    Integer getUserIdByPidNopass(@Param("pid") Integer pid);

    QjVo getUnameAndRnameById(@Param("id") Integer id);

    //我的审核
    List<Integer> getPidsByUserid(@Param("rid")Integer rid);
    QjVo getProcessById(@Param("id") Integer id);

    QjVo getStuInfoBySid(@Param("id") Integer id);


    //保存我的审核
    void updateProcessStatus(@Param("pid") Integer pid,@Param("shstatus") Integer shstatus);

    void updatePmxStatus(@Param("pid") Integer pid, @Param("rid") Integer rid,@Param("shstatus")Integer shstatus);
   //void updatePmxStatus(Integer pid, Integer rid, Integer shstatus);

    Integer getMaxPshunxu(@Param("pid") Integer pid);

    //Integer getQjMxInfo(@Param("pid") Integer pid, @Param("userid") Integer userid);
    Integer getQjMxInfo(@Param("pid") Integer pid,@Param("rid")  Integer rid);

    void updatePmxShunxu(@Param("pid") Integer pid, @Param("pshunxu") Integer pshunxu);



}
