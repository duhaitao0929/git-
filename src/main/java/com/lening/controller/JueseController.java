package com.lening.controller;

import com.alibaba.fastjson.JSON;
import com.lening.entity.BumenBean;
import com.lening.entity.JueseBean;
import com.lening.entity.PowerBean;
import com.lening.service.BumenService;
import com.lening.service.JueseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class JueseController {

    @Resource
    private JueseService jueseService;


    /**
     * 查询角色列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/getJueseList")
    public String getJueseList(Model model) {
        List<JueseBean> list = jueseService.getJueseList();
        model.addAttribute("list", list);
        return "juese_list";
    }

    //给角色分配权限
    @RequestMapping("/tojueseQuanxian")
    public String tojueseQuanxian(Integer rid, Model model) {
        //查询角色和权限的中间表  条件角色的id
        List<PowerBean> list = jueseService.getjuesePower(rid);
        String json = JSON.toJSONString(list);
        model.addAttribute("json", json);
        model.addAttribute("rid", rid);
        return "juese_quanxian";
    }

    //保存给角色分配权限
    @RequestMapping("/saveJuesePower")
    public String saveJuesePower(Integer rid, String ids) {
        jueseService.saveJuesePower(rid, ids);
        return "redirect:getJueseList.do";
    }
}
