package com.lening.controller;

import com.lening.entity.BumenBean;
import com.lening.entity.JueseBean;
import com.lening.service.BumenService;
import com.lening.service.JueseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BumenController {

    @Resource
    private BumenService bumenService;
    @Resource
    private JueseService jueseService;

    //查询部门列表信息
    @RequestMapping("/getbumenList")
    public String getbumenList(Model model) {
        List<BumenBean> list = bumenService.getbumenList();
        model.addAttribute("list", list);
        return "bumen_list";
    }

    //给部门分配角色
    @RequestMapping("/toBumenJuese")
    public String toBumenJuese(Integer deptid, Model model) {
        //1.查询部门表里面的 部门id
        BumenBean db = bumenService.getbumenByDeptid(deptid);
        //2.查询角色表所有信息
        List<JueseBean> list = bumenService.getjueseList();
        //当前部门有角色的rid 需要在前台页面的复选框中回显使用
        //3.角色表里面查询部门id
        List<Integer> rids = bumenService.RidsByDeptid(deptid);
        model.addAttribute("db", db);
        model.addAttribute("list", list);
        model.addAttribute("rlist", rids);
        return "bumen_juese";
    }

    //保存给部门分配角色
    @RequestMapping("/saveDeptRole")
    public String saveDeptRole(Integer deptid, Integer[] rids) {
        bumenService.saveDeptRole(deptid, rids);

        return "redirect:getbumenList.do";
    }


    //给角色选择部门
    @RequestMapping("/getDeptByRole")
    public String getDeptByRole(Integer rid, Model model) {
        JueseBean jueseBean = jueseService.getRoleByRid(rid);
        List<BumenBean> dlist = bumenService.getbumenList();
        model.addAttribute("dlist", dlist);
        model.addAttribute("jueseBean", jueseBean);
        return "juese_bumen";
    }

    //保存角色选择部门
    @RequestMapping("/updateRoleDept")
    public String updateRoleDept(Integer deptid, Integer rid) {
        jueseService.updateRoleDept(rid, deptid);
        return "redirect:getJueseList.do";
    }
}
