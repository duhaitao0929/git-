package com.lening.controller;

import com.alibaba.fastjson.JSON;
import com.lening.entity.*;
import com.lening.service.UserService;
import com.lening.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    private String isql;

    private int count;

    private static final String PAGE_SIZE = "5";

    /**
     * 请假流程
     * @param request
     * @param model
     * @return
     */
    //跳转  转发到学生请假页面
    @RequestMapping("/toStuQj")
    public String toStuQj(HttpServletRequest request,Model model){
        UserBean ub = (UserBean) request.getSession().getAttribute("ub");
        model.addAttribute("stu",ub);
        return "stu_qj";
    }

    //发起请假请求
    @RequestMapping("/saveStuQj2")
    public String saveStuQj2(ProcessBean pb){
        //请求发起之后，开始走我们的业务流程，分为三个流程属于业务型的代码，在service层处理
        userService.saveStuQj2(pb);
        return "redirect:getStuJtList.do";
    }

    //我的请假记录
    @RequestMapping("/getStuJtList")
    public String getStuJtList(HttpServletRequest request,Model model){
        //先查学生的情况再去查询学生的请假情况
        UserBean ub = (UserBean) request.getSession().getAttribute("ub");
       //vo里面只能查询出一部分字段，最好不要联查，出来之后在判断查询
        List<QjVo> list=userService.getStuQjListBySid(ub.getId());
        model.addAttribute("list",list);
        return "stuqj_list";
    }

    //我的审核
    @RequestMapping("/getQjShList")
    public String getQjShList(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        /**
         * 先拿着我的id去流程明细表中查询一下，有没有需要我审核的流程，有的话，再去流程表中把流程查出来，
         * 和学生是相反的，学生是先查流程，再查明细，老师是先查明细，再查流程
         * select pid from t_pmx a where a.pstatus =1 and userid=37 其实我就需要pid，查询流程id，因为我的页面展示的时候，
         * 我需要知道这个是谁请假的，请了多次时间，什么时候开始的，那个班级的
         */
        List<QjVo> list = userService.getQjshListByUserid(ub.getId());
        model.addAttribute("list", list);
        return "qjsh_list";
    }

    //保存我的审核
    @RequestMapping("/saveWdsh")
    public String saveWdsh(HttpServletRequest request,Integer pid,Integer shstatus){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        userService.saveWdsh(pid,shstatus,ub.getId());
        return "redirect:getQjShList.do";
    }



















    /**
     * 查询员工列表
     * <p>
     * current :当前页码
     */
    @RequestMapping("/getUserList")
    public String getUserList(String current, Model model) {
        List<UserBean> list = userService.findAll();
        Page page = new Page(current, list.size(), PAGE_SIZE);
        count = list.size();
        isql = "SELECT * FROM t_user LEFT JOIN t_dept ON t_user.deptid = t_dept.deptid LEFT JOIN t_role ON t_user.rid = t_role.rid limit ";
        String sql = isql + page.getStartRecord() + "," + page.getPageSize();
        List<UserBean> userByPage = userService.findUserByPage(sql);
        model.addAttribute("list", list);
        model.addAttribute("current", page.getCurrentPage());
        return "user_list";
    }

    /**
     * 模糊查询
     *
     * @param model
     * @return isql+page.getStartRecord()+","+page.getPageSize()
     * sql语句   +  分页开始记录     +   分页每页多少记录
     */
    @RequestMapping("/getlikeUsername")
    public String getlikeUsername(String current, String likeName, Model model) {
        List<UserBean> userBeans = userService.findLikeName(likeName);
        Page page = new Page(current, userBeans.size(), PAGE_SIZE);
        count = userBeans.size();
        isql = "SELECT * FROM t_user LEFT JOIN t_dept ON t_user.deptid = t_dept.deptid LEFT JOIN t_role ON t_user.rid = t_role.rid WHERE t_user.username like '%" + likeName + "%' limit ";
        String sql = isql + page.getStartRecord() + "," + page.getPageSize();
        List<UserBean> list = userService.findUserByPage(sql);
        model.addAttribute("list", list);
        model.addAttribute("current", page.getCurrentPage());
        return "user_list";
    }

    /**
     * 根据日期范围查询
     *
     * @param current
     * @param sbirthday
     * @param ebirthday
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping("/getUserfanwei")
    public String getUserfanwei(String current, String sbirthday, String ebirthday, Model model) throws ParseException {
        List<UserBean> userBeanList = userService.findByBirthday(sbirthday, ebirthday);
        Page page = new Page(current, userBeanList.size(), PAGE_SIZE);
        count = userBeanList.size();
        isql = "SELECT * FROM t_user LEFT JOIN t_dept ON t_user.deptid = t_dept.deptid LEFT JOIN t_role ON t_user.rid = t_role.rid WHERE t_user.birthday >= '" + sbirthday + "' AND t_user.birthday <= '" + ebirthday + "' limit ";
        String sql = isql + page.getStartRecord() + "," + page.getPageSize();
        List<UserBean> list = userService.findUserByPage(sql);
        model.addAttribute("list", list);
        model.addAttribute("current", page.getCurrentPage());
        return "user_list";
    }

    /**
     * 查询上一页
     *
     * @param current
     * @param model
     * @return
     */
    @RequestMapping("/getUserSahngye")
    public String getUserSahngye(String current, Model model) {
        Integer i = Integer.parseInt(current) - 1;
        Page page = new Page(i.toString(), count, PAGE_SIZE);
        String sql = isql + page.getStartRecord() + "," + page.getPageSize();
        List<UserBean> list = userService.findUserByPage(sql);
        model.addAttribute("list", list);
        model.addAttribute("current", page.getCurrentPage());
        return "user_list";
    }

    /**
     * 查询下一页
     *
     * @param current
     * @param model
     * @return
     */
    @RequestMapping("/getUserXiaye")
    public String getUserXiaye(String current, Model model) {
        Integer i = Integer.parseInt(current) + 1;
        Page page = new Page(i.toString(), count, PAGE_SIZE);
        String sql = isql + page.getStartRecord() + "," + page.getPageSize();
        List<UserBean> list = userService.findUserByPage(sql);
        model.addAttribute("list", list);
        model.addAttribute("current", page.getCurrentPage());
        return "user_list";
    }

    /**
     * 查询User表当前页码
     *
     * @param current
     * @param model
     * @return
     */
    @RequestMapping("/getUserByCurrent")
    public String getUserByCurrent(String current, Model model) {
        Page pageUtil = new Page(current, count, PAGE_SIZE);
        String sql = isql + pageUtil.getStartRecord() + " , " + pageUtil.getPageSize();
        List<UserBean> list = userService.findUserByPage(sql);
        model.addAttribute("current", pageUtil.getCurrentPage());
        model.addAttribute("list", list);
        return "user_list";
    }


    /**
     * 登录模块
     * 登录成功了去main.jsp,登录失败了会index.jsp
     * 登录成功了，需要把用户的信息存进session里面
     *
     * @param userBean
     * @return
     */
    @RequestMapping("/getLogin")
    public String getLogin(UserBean userBean, HttpServletRequest request) {
        /**
         * 登录的返回，要是想明确告诉用户是用户名为空，密码错误等详细信息，那么就不能这样用，
         * 一般登录是告诉登录失败
         */
        UserBean ub = userService.getLogin(userBean);
        if (ub != null) {
            request.getSession().setAttribute("ub",ub);
           // session.setAttribute("ub", ub);
            return "main";
        }
        return "../../index";
    }

    /**
     * 查看权限
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getUserPower")
    public String getUserPower(Integer id, Model model) {
        List<PowerBean> userPower = userService.getUserPower(id);
        String s = JSON.toJSONString(userPower);
        model.addAttribute("json", s);
        model.addAttribute("id", id);
        return "user_power";
    }

    @RequestMapping("/getPowerJson")
    public String getPowerJson(Model model,HttpServletRequest request) {
        /**
         * 获取sesion的时候，可以传递boolean类型的参数，不写默认是true，
         * 要是有就返回原来的，要是没有重新创建
         * 要是设置成false，有的话，返回，没有的话，返回null
         */
       // UserBean ub = (UserBean) session.getAttribute("ub");
        UserBean ub= (UserBean)request.getSession(true).getAttribute("ub");
        if (ub != null) {
            /**
             * 这个list里面目前是有按钮的，给菜单不能有按钮，但是我们给urls必须有按钮，要不查询两次，查询两次没什么可说的，直接查
             * 我们就只想查询一次
             */
            List<PowerBean> list = userService.getPowerListById(ub.getId());
            /**
             * 可以直接去数据库把用户拥有的url查出来，也可以直接遍历这list，把url拿出来
             */
            HashSet<String> urls = new HashSet<>();
            //没有把list的大小固定，每次会自动去获取list的大小
            for (int i = 0; i < list.size(); i++) {
                PowerBean pb = list.get(i);
                if (pb.getUrl()!=null){
                    urls.add(pb.getUrl().trim());
                }
                //把url放进urls里面之后，再次判断，要是这个按钮
                //把他从list里面删除（要把是按钮的删除）
                if ("是".equals(pb.getIsbutton())){
                    //list删除，可以把pb给它，也可以把角标给它
                    //它的大小会自动减一
                    list.remove(pb);
                    //删除完之后，需要把list的大小减一，要不然集合下标越界，并且要把指针再回减一个
                    //比如现在角标是3，把3删掉后，4就会自动变成3，但是循环里面已经到4了，所以就会把原来的4没越过去了
                    i--;//不会减角标，只能一部门
                }
            }
            request.getSession().setAttribute("urls",urls);
           // session.setAttribute("urls",urls);
            //转化json之前，把按钮的删除
            String json = JSON.toJSONString(list);
            model.addAttribute("json", json);
        }
        return "left";
    }

    /**
     * 去给员工分配部门和角色
     * 传过来的参数只有员工id
     * 需要的参数
     * 1、员工的全部信息，需要查询出员工已有的部门和角色（其实只需要部门和角色的id就OK啦，用来回显）
     * 2、全部部门列表
     * 3、角色列表（用户现在所在的部门的所有角色列表）
     * <p>
     * <p>
     * 也就是二级联动 分配部门跟角色
     */
    @RequestMapping("/toUserDeptRole")
    public String toUserDeptRole(Integer id, Model model) {
        //查询员工信息
        UserBean userBean = userService.getUserById(id);
        //查询员工所在部门
        List<BumenBean> dlist = userService.getbumenList();
        //查询员工所在部门的全部角色，有可能这个员工没有部门也没有角色
        List<JueseBean> jlist = userService.getRoleListByDeptid(userBean);
        model.addAttribute("userBean", userBean);
        model.addAttribute("dlist", dlist);
        model.addAttribute("jlist", jlist);
        return "user_bumen_juese";
    }

    /**
     * 二级联动之后回显
     *
     * @param
     * @return
     */
    @RequestMapping("/getbujuese")
    @ResponseBody
    public List<JueseBean> getbujuese(Integer deptid) {
        List<JueseBean> selist = userService.getJueseListByDeptid(deptid);
        return selist;
    }

    @RequestMapping("/updateUserBumenJuese")
    public String updateUserBumenJuese(Integer id, Integer deptid, Integer rid) {
        userService.updateUserBumenJuese(id, deptid, rid);
        return "redirect:getUserList.do";
    }

    /**
     * user_list   修改
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getUserById")
    public String getUserById(Integer id, Model model) {
        UserBean byId = userService.getUserById(id);
        model.addAttribute("ub", byId);
        return "user_update";
    }

    /**
     * 修改
     *
     * @param userBean
     * @param mybirthday
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(UserBean userBean, String mybirthday) {
        try {
            //日期类型处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(mybirthday);
            userBean.setBirthday(birthday);
            userService.updateUser(userBean);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/todelid")
    public String delUserid(Integer id) {
        userService.deleteUserid(id);
        return "redirect:getUserList.do";
    }

    /**
     * 跳转到add页面
     *
     * @param
     * @return
     */
    @RequestMapping("/totiaoadd")
    public String addUser() {
        return "user_add";
    }

    /**
     * 添加
     *
     * @param userBean
     * @param mybirthday
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(UserBean userBean, String mybirthday) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(mybirthday);
            userBean.setBirthday(birthday);
            System.out.println("safas" + userBean);
            userService.addUser(userBean);

            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 上传头像
     *
     * @param img
     * @param req
     * @param id
     * @return
     */
    @RequestMapping("/updatePortrait")
    public String updatePortrait(@RequestParam MultipartFile img, HttpServletRequest req, Integer id) {
        String imgPath = req.getServletContext().getRealPath("image");
        File saveFile = new File(imgPath, img.getOriginalFilename());
        try {
            img.transferTo(saveFile);
            userService.updatePortrait(id, img.getOriginalFilename());
        } catch (Exception e) {
        }

        return "redirect:getUserList.do?current=1";
    }


}
