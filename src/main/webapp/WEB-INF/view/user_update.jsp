<%--
  Created by IntelliJ IDEA.
  User: 杜海涛
  Date: 2020/4/10
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        div, table {
            margin: 0 auto;
            text-align: center;
        }
    </style>
    <script src="../../js/jquery-1.8.2.js"></script>
</head>
<body>
<div>
    <h1>现在正在给--${ub.username}--修改个人信息</h1>
    <input name="id" type="hidden" value="${ub.id}">
    <table>
        <tr>
            <td>用户名</td>
            <td><input name="username" value="${ub.username}" type="text"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input name="password" value="${ub.password}" type="text"/></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input name="age" value="${ub.age}" type="text"/></td>
        </tr>
        <tr>
            <td>出生日期</td>
            <td><input name="birthday" value="<fmt:formatDate value='${ub.birthday}' pattern="yyyy-MM-dd" />" type="date"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="sub_mit">提交</button>
            </td>
        </tr>
    </table>
</div>
<script>
    $(function () {
        $("#sub_mit").click(function () {
            var id = $("[name=id]").val();
            var username = $("[name=username]").val();
            var password = $("[name=password]").val();
            var age = $("[name=age]").val();
            var mybirthday = $("[name=birthday]").val();
            if(username == ""){
                alert("请填写用户名");
            }else if(password == ""){
                alert("请填写密码");
            }else if(age == ""){
                alert("请填写年龄");
            }else if(mybirthday == ""){
                alert("请输入生日");
            }else{
                $.ajax({
                    type: "post",
                    url: "updateUser.do",
                    data: {
                        id: id,
                        username:username,
                        password:password,
                        age:age,
                        mybirthday,mybirthday
                    },
                    dataType: "text",
                    success: function (data) {
                        if(data == "success"){
                            window.location.href="getUserList.do?current=1";
                        }else{
                            alert("修改失败");
                        }
                    }
                });
            }


        })
    })
</script>
</body>
</html>
