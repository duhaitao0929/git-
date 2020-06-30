package com.lening.service.impl;

import com.lening.entity.*;
import com.lening.mapper.UserMapper;
import com.lening.service.UserService;
import com.lening.utils.JieXiXml;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void saveStuQj(Integer id, Double qjtime, Date stime, Date etime, String qjcause) {
        /**
         * 时间的判断 ，可以使用 两个日期进行做差，当然也可以使用  qjtime（需要在页面固定单位）
         * qjtime：固定他的但是是天
         */
        /**
         * 在进入请假流程之前，需要查询出学生的班级和班级的讲师以及辅导员，也可以把主任和院长查出来
         */


        /**
         *判断学生请假的长度，只是用来判断审核流程的站点的，学生请假进库和这个时间没有关系不管请多长时间，都需要进库
         */

        /**
         * 1、先把请假流程保存进去再说,需要把流程的id拿回来，因为我们保存流程明细的时候，需要流程的id。
         * 保存返回的id，只有是在实体的时候返回过，直接用方法返回，可以吗？可以试一下
         * 没有能把插入的id作为返回值返回回来的办法，那么就把数控封装成实体类
         */

        ProcessBean pb = new ProcessBean();
        pb.setSid(id);
        pb.setQjcause(qjcause);
        pb.setEtime(etime);
        pb.setStime(stime);
        pb.setQjtime(qjtime);
        /**
         * 流程状态，0表示 正在审核中，1表示审核通过，2表示审核未通过
         * 这个是整合流程的状态，整合请假流程现在的状态，供请假者来查询
         * 在明细表中的，记录的是每一个审批的状态
         */
        pb.setQjstatus(0);
        userMapper.insertPorcess(pb);
        System.out.println("pb=========" + pb.getId());

        /**
         * 流程保存成功，接下来，根据时间保存流程明细，需要根据学生的sid去把学生班级信息查询出来，因为我们要查询学生班级的讲师和辅导员id
         * 查询到学生班级情况后，就有了讲师id和辅导员id，这样就能生成明细了，主任id和院长id这个是固定的，可以在班级里面加入，
         * 我们没有更大的单位了，还有教研室，还有学院（主任和id是固定的）
         */
        /**
         * 流程明细全部进去了，谁先来处理。都是0,但是因为讲师先审批，所以新增的时候，其他的都是0，讲师是1，表示正在审核，2表示已审核
         * 要是讲师或者辅导员，审核过了，不管是审核未通过还是审核通过，只要操作了，就把顺序码改成2，表示这个流程已经操作完了
         * 每个人登录上来，我们只需要查看id是自己的，然后顺序码是1的，那些流程明细，表示该我审核了。2表示我已经处理过了。0的表示还没有到我这里来。就OK啦
         */

        if (qjtime <= 1) {
            /**
             * 小于等于1的全部在这里了，给明细里面爆出一个讲师和辅导员的明细就OK啦
             */

        } else if (qjtime <= 3) {
            /**
             * 在这个里面没有必要在写  >1 && <=3，给明细表中，主任加一个明细
             */
        } else {
            /**
             * 给院长一个明细
             */
        }
    }

    /**
     * 学生发起请求  开始在service层走流程
     *
     * @param pb
     */
    @Override
    public void saveStuQj2(ProcessBean pb) {
        //Qjstatus 流程状态：0表示正在审核中，1表示审核通过，2表示审核未通过
        //流程状态是供请假者用来查询   在明细表中记录了每一批审批的状态
        pb.setQjstatus(0);
        //保存假条 porcess表中
        userMapper.insertPorcess(pb);
        //请假条保存好之后，把假条流程id拿回来。  接下来保存流程明细（明细表示：都需要谁来审核）
        //在明细表中添加数据的时候  需要查询学生的班级信息，最起码需要查询出讲师和班主任
        UserBean ub = userMapper.getUserById(pb.getSid());
        if (ub != null && ub.getGid() != null) {
            //查询班级
            GradeBean gb = userMapper.getGradeByGid(ub.getGid());
            //班级里面就有讲师和辅导员，如果这个学生没有班级  直接提交给主任审核
            PmxBean pmxBean = new PmxBean();
            //流程id
            pmxBean.setPid(pb.getId());
            //status这个表示流程处理的意见状态：0表示 正在审核中，1表示审核通过，2表示审核未通过
            pmxBean.setStatus(0);
            //pstatus:这个是流程处理的状态码： 0没有没到我这里，  1表示该我处理了，2表示我已经处理过了
            pmxBean.setPstatus(1);
            //需要判断流程有没有结束：需要判断的状态：处理顺序   1234 默认给1
            pmxBean.setPshunxu(1);
            //处理人的id   讲师
            pmxBean.setUserid(gb.getTid());
            //保存讲师的处理流程明细
            userMapper.insertProcessPmx(pmxBean);

            //处理辅导员的业务流程
            pmxBean.setPshunxu(2);
            pmxBean.setPstatus(0);
            pmxBean.setUserid(gb.getFid());//辅导员的id
            //保存辅导员的处理流程明细
            userMapper.insertProcessPmx(pmxBean);

            //当请假时间大于1天的时候  判断
            if (pb.getQjtime() > 1) {
                pmxBean.setPshunxu(3);
                //目前我们给班级没有设置主任和院长，所以这两个写死，可以在班级里面增加主任和院长
                pmxBean.setUserid(21);//主任
                userMapper.insertProcessPmx(pmxBean);
            }

            //当请假时间大于3天的时候
            if (pb.getQjtime() > 3) {
                //院长的明细  也就是顺序
                pmxBean.setPshunxu(4);
                //院长
                pmxBean.setUserid(20);
                userMapper.insertProcessPmx(pmxBean);
            }
        }
    }


    //查询我的请假记录
    @Override
    public List<QjVo> getStuQjListBySid(Integer sid) {
        List<QjVo> list = userMapper.getStuQjListBySid(sid);// select * from t_process where sid = 1
        //在循环里面开始判断
        for (QjVo vo : list) {
            Integer qjstatus = vo.getQjstatus();//0
            Integer userid = 0;
            if (qjstatus == 0) {
                //正在审核
                vo.setStatusStr("正在审核中");
                //需要查询目前谁正在审核，使用流程id查询，去明细表中查询pstatus等于1的，就是正在审核
                //能查出来，userid select userid from t_pmx where pstatus=1 and pid=#{36}
                // 22  select userid from t_pmx where pstatus=1 and pid=#{33}
                userid =  userMapper.getUserIdByPid(vo.getId());
            } else if (qjstatus == 1) {
                //审核通过
                vo.setStatusStr("审核通过");
                //审核通过了，肯定是最后一个人审核的，也就是审核码最大的那个人
                userid = userMapper.getUserIdByPidMaxShunxu(vo.getId());
            } else {
                //审核没通过
                vo.setStatusStr("审核未通过");
                //审核未通过  找审核码是2的
                userid = userMapper.getUserIdByPidNopass(vo.getId());
            }
            QjVo vvo = userMapper.getUnameAndRnameById(userid);//userid 23
            vo.setUname(vvo.getUname());
            vo.setRname(vvo.getRname());
        }
        return list;
    }

    //我的审核
    @Override
    public List<QjVo> getQjshListByUserid(Integer id) {
        /**
         * 去查询有没有需要我审核的流程id的集合
         */
        UserBean bean = userMapper.getUserById(id);
        List<Integer> pids = userMapper.getPidsByUserid(bean.getRid());

        List<QjVo> list = null;
        if (pids != null && pids.size() >= 1) {
            list = new ArrayList<QjVo>();
            for (Integer pid : pids) {
                //先按照流程id去查询流程表里面有的信息
                QjVo vo = userMapper.getProcessById(pid);
                /**
                 * 查询出来的vo中，只有学生的id，没有学生名字和班级的名字
                 * 又要去查询这个学生的另外两个字段
                 */
                QjVo voo = userMapper.getStuInfoBySid(vo.getSid());
                vo.setUname(voo.getUname());
                vo.setGname(voo.getGname());
                list.add(vo);
            }
        }
        return list;
    }
    @Override
    public void saveWdsh(Integer pid, Integer shstatus,Integer userid) {
        /**
         * 保存我的审核
         */
        UserBean user = userMapper.getUserById(userid);//8
        if (shstatus==1){
            /**
             * 审核通过了，
             *  需要判断我的审核是不是最后一个，要是最后一个，就要把流程表中的状态改成  改成 审核成功，然后把流程明细里面状态改掉
             *  要是不是最后一个，把自己的流程明细里面的状态改掉，改成已处理，然后把自己审核的状态该进去，然后把流程交给下一个人
             *  怎么交给，把pstatus改成1，怎么样判断下一个人   pshunxu +1,
             *  怎么样判断自己是不是最后流程审核的最后一人，把流程审核最后一人的pshun  max（pshunxu），查出来，和自己pshunxu比较
             */

            /**
             * 接下来，我们需要使用 流程id和userid去明细表中查询，   查询目前我审核的这个流程的详细信息
             * 在查询，目前审核的这个这个整合流程的最大  顺序，是不是最后一个
             * vo里面没有顺序，那就不要用vo了，字节用一个数字表示方便
             * user.getRid()  22
             */

            Integer pshunxu = userMapper.getQjMxInfo(pid,user.getRid());
            //4
            Integer maxpshunxu = userMapper.getMaxPshunxu(pid);
            /**
             * 他俩要是相等，就是最后一步了，要是不相等，表示不是最后一步审核
             */
            if(pshunxu==maxpshunxu){
                userMapper.updateProcessStatus(pid,shstatus);
            }else{
                /**
                 * 不通过的话，把流程明细改成一下，然后把任务交给下一个
                 */
                /**
                 * 流程交给下一步，因为有自己的流程顺序码，+1，就是下一个人
                 */
                userMapper.updatePmxShunxu(pid,pshunxu+1);// update t_pmx set status = 1 where pid=#{pid} and pshunxu = #{pshunxu}
            }
            userMapper.updatePmxStatus(pid,user.getRid(),shstatus);//  update t_pmx set status=#{shstatus},pstatus=2 where pid=#{pid} and userid=#{rid}
        }else{

            /**
             * 审核不通过，直接把流程该更流程明细和流程就OK啦，直接结束流程
             * 直接改成两张表就ok
             * 要是想写方法复用，那么就不要在xml中把状态写死，传递给xml
             */
            userMapper.updateProcessStatus(pid,shstatus);
            userMapper.updatePmxStatus(pid,user.getRid(),shstatus);
        }
    }

    @Override
    public List<UserBean> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<PowerBean> getPowerList() {
        return userMapper.getPowerList();
    }

    //查询员工信息
    @Override
    public UserBean getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    //查询员工所在的部门
    @Override
    public List<BumenBean> getbumenList() {
        return userMapper.getbumenList();
    }

    @Override
    public List<JueseBean> getRoleListByDeptid(UserBean userBean) {

        if (userBean != null) {
            if (userBean.getBumenBean() != null && userBean.getBumenBean().getDeptid() != null) {
                List jList = userMapper.getRoleListByDeptid(userBean.getBumenBean().getDeptid());
                return jList;
            }
        }
        return null;
    }

    @Override
    public List<JueseBean> getJueseListByDeptid(Integer deptid) {
        return userMapper.getRoleListByDeptid(deptid);
    }


    @Override
    public void updateUserBumenJuese(Integer id, Integer deptid, Integer rid) {
        userMapper.updateUserBumenJuese(id, deptid, rid);
    }

    @Override
    public void deleteUserid(Integer id) {
        userMapper.deleteUserid(id);
    }

    @Override
    public void updateUser(UserBean userBean) {
        userMapper.updateUser(userBean);
    }

    @Override
    public void addUser(UserBean userBean) {
        userMapper.addUser(userBean);
    }

    @Override
    public List<UserBean> findLikeName(String likeName) {
        return userMapper.findLikeName(likeName);
    }

    @Override
    public List<UserBean> findByBirthday(String sbirthday, String ebirthday) {
        Map<String, String> map = new HashMap<>();
        map.put("sbirthday", sbirthday);
        map.put("ebirthday", ebirthday);
        return userMapper.findByBirthday(map);
    }

    @Override
    public List<UserBean> findUserByPage(String sql) {
        return userMapper.findUserByPage(sql);
    }

    @Override
    public void updatePortrait(Integer id, String portrait) {
        userMapper.updatePortrait(id, portrait);
    }

    @Override
    public UserBean getLogin(UserBean userBean) {
        if (userBean != null) {
            if (userBean.getUsername() != null && !"".equals(userBean.getUsername())) {
                /**
                 * 虽然你在实际生产不可以出现，一个用户名查询出来两条数据的可能性
                 * 但是测试有可能，所以，我们写的全面点，返回为list，判断list大小，是1就OK啦，在判断密码，否则登录失败
                 */
                List<UserBean> list = userMapper.getLogin(userBean.getUsername());
                if (list != null && list.size() == 1) {
                    UserBean ub = list.get(0);
                    if (ub.getPassword().equals(userBean.getPassword())) {
                        return ub;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<PowerBean> getPowerListById(Integer id) {
        return userMapper.getPowerListById(id);
    }

    @Override
    public List<PowerBean> getUserPower(Integer id) {
        return userMapper.getUserPower(id);
    }


    //接口对接   解析参数1
    @Override
    public QueryVo jieXiStr1(String str1) {

        return JieXiXml.jieXiStr1(str1);
    }

    @Override
    public String jieXiStr2(String str2) {
        return JieXiXml.jieXiStr2(str2);
    }

    @Override
    public String getInfo(QueryVo vo) {
        /**
         * 我们发现香烟和酒水只有一个字段不一样。
         * 我们可以把这两个试题合并，只适合我们这项目，实际中很少
         */
        RsBean rs = null;
        if ("01".equals(vo.getCode())) {
            //如果code是01去查询香烟，到香烟的数据库里面查询
            rs = userMapper.getRsSmoke(vo.getCardno());
        } else if ("02".equals(vo.getCode())) {
            //如果code是02去查询酒水，到酒水的数据库里面查询
            rs = userMapper.getRsWine(vo.getCardno());
        }
        String str = JieXiXml.pinRsStr(vo.getCode(), rs);
        if (str != null) {
            return str;
        }
        return null;
    }


    //<!--第二个接口，接受分厂发来的数据 酒水-->
    @Override
    public String saveData(QueryVo vo, String str2) {
        //01是香烟
        if ("01".equals(vo.getCode())) {
            //这个str2目前没有解析的方法，解析里面的str2是查询方法的解析
            SmokeBean smokeBean = JieXiXml.jieXiStr2Smoke(str2);
            //解析出来进行判断保存
            if (smokeBean == null) {
                return "<MEG><CODE>0</CODE><CONTENT>参数2解析失败</CONTENT></MEG>";
            } else {
                try {
                    userMapper.saveSmoke(smokeBean);
                    return "<MEG><CODE>1</CODE><CONTENT>保存成功了</CONTENT></MEG>";
                } catch (Exception e) {
                    return "<MEG><CODE>0</CODE><CONTENT>数据保存失败</CONTENT></MEG>";
                }
            }
        } else if ("02".equals(vo.getCode())) {
            //02 是酒水
            WineBean wineBean = JieXiXml.jieXiStr2Wine(str2);
            System.out.println("==========" + wineBean);
            if (wineBean == null) {
                return "<MEG><CODE>0</CODE><CONTENT>参数2解析失败</CONTENT></MEG>";
            } else {
                try {
                    userMapper.saveWine(wineBean);
                    return "<MEG><CODE>1</CODE><CONTENT>保存成功了</CONTENT></MEG>";
                } catch (Exception e) {
                    return "<MEG><CODE>0</CODE><CONTENT>数据保存失败</CONTENT></MEG>";
                }
            }
        }
        return null;
    }


}

