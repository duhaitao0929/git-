package com.lening.controller;

import com.lening.entity.InfoBean;
import com.lening.entity.QueryVo;
import com.lening.entity.UserBean;
import com.lening.service.UserService;
import com.lening.utils.JieXiXml;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestDataController {

    @Resource
    private UserService userService;

    /**
     * post的对象参数 （接受数据）
     */
    @RequestMapping("/testPost3")
    public String testPost3(@RequestBody InfoBean infoBean){
        return infoBean.toString();
    }


    /**
     * 对应的是无参的post请求
     */
    @RequestMapping(value = "/testPost1")
    public String testPost1(){
        System.out.println(123);
        return "123";
    }

    /**
     * 给分厂提供查询
     * @param str1
     * @param str2
     * @return
     */
    @RequestMapping("/getDataInterface")
    public String getDataInterface(String str1,String str2){
        //这两个参数是对方传递过来的，模拟真实，在开发总部的时候，不要去考虑分部，按照文档来
        //str1="<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
        //str2="<CONTENT><CARDNO>xy0002</CARDNO></CONTENT>";

        QueryVo vo=userService.jieXiStr1(str1);
        if (vo==null){
            //解析失败，几乎不可能，第一次项目对接有可能，后续就不能了
            return "<result><MEG><CODE>0</CODE></MEG></result>";
        }else {
            //参数1 解析成功
            //去登录
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);
            //判断是否为空，如果为空则登录失败，不为空则登录成功
            if (ru==null){
                return "<result><MEG><CODE>1</CODE></MEG></result>";
            }else {
                //登录成功，解析参数2
                String cardno =userService.jieXiStr2(str2);
                if (cardno==null){
                    return "<result><MEG><CODE>0</CODE></MEG></result>";
                }else {
                    vo.setCardno(cardno);
                    //查询返回，返回要是空，就没有查到，返回要不是空，把返回的接口，拼成需要的xml返回回去
                    //总厂这边开始建立两个数据库，一个香烟库，一个酒水的库，对应的实体类等

                    //vo 里面有code和cardno 去查询
                    String rs=userService.getInfo(vo);
                    if (rs==null){
                        //表示没有查询到
                        return "<result><MEG><CODE>2</CODE></MEG></result>";
                    }else {
                        System.out.println("<result><MEG><CODE>3</CODE></MEG>"+rs+"</result>");
                        return "<result><MEG><CODE>3</CODE></MEG>"+rs+"</result>";
                    }
                }
            }
        }
    }

    //第二个接口，接受分厂发来的数据
    @RequestMapping("/reciDataInterface")
    public String reciDataInterface(String str1,String str2){
        //str1 = "<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
         //str2 = "<CONTENT><CARDNO>xy00001</CARDNO><MADEDATE>2018-06-02 11:37:17</MADEDATE><ADDRESS>北京</ADDRESS><PRICE>35</PRICE><NAME>黄鹤楼</NAME></CONTENT>";

        /**
         * 接收分厂发过来的数据后，先解析第一个参数
         */
        QueryVo vo = JieXiXml.jieXiStr1(str1);
        //判断vo 是否为空， 如果为空参数一解析失败
        if (vo == null){
            return "<MEG><CODE>0</CODE><CONTENT>参数一解析失败</CONTENT></MEG>";
        }else {
            //解析成功，判断登录， 鉴权（判断它有没有资格登录）
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);

            //判断是否登录成功和失败
            if (ru == null){
                return "<MEG><CODE>0</CODE><CONTENT>用户名或者密码错误</CONTENT></MEG>";
            }else {
                //登录成功了，根据参数一解析出来的code去解析参数二
                return userService.saveData(vo,str2);
            }
        }
    }


}
