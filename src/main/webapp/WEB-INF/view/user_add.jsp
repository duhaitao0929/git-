<%--
  Created by IntelliJ IDEA.
  User: 杜海涛
  Date: 2020/4/10
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>添加信息</h2>

    <table>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="username" placeholder="请输入姓名"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password" placeholder="请输入密码"></td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td><input type="text" name="age" placeholder="请输入年龄"></td>
        </tr>
        <tr>
            <td>生日：</td>
            <td><input type="date" name="birthday"></td>
        </tr>
        <tr>
            <td><button id="addUser">提交</button></td>
        </tr>
    </table>
<script src="../../js/jquery-1.8.2.js"></script>
<script>
    $(function () {

        $("#addUser").click(function () {
            var username = $("[name=username]").val();
            var password = $("[name=password]").val();
            var age = $("[name=age]").val();
            //var agePatt = /\d{1,3}/;
            var mybirthday = $("[name=birthday]").val();
            if(username == ""){
                alert("请填写用户名");
            }else if(password == ""){
                alert("请填写密码");
            }else if(age == ""){
                alert("请填写正确年龄");
            }else if(mybirthday == ""){
                alert("请输入生日");
            }else{
                $.ajax({
                    type: "post",
                    url: "addUser.do",
                    data: {
                        username:username,
                        password:password,
                        age:age,
                        mybirthday,mybirthday
                    },
                    dataType: "text",
                    success: function (data) {
                        alert(data);
                        if(data == "success"){
                            window.location.href="getUserList.do?current=1";
                        }else{
                            alert("新增失败");
                        }
                    }
                });
            }


        })
    })
</script>
</body>
</html>
