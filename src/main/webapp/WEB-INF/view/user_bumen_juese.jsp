<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Title</title>
</head>
<body>
<form action="updateUserBumenJuese.do" method="post">
    <input name="id" type="hidden" value="${userBean.id}"/>

    <h1>请给----${userBean.username}-----分配部门和角色</h1>

    分配部门：<select name="deptid" onchange="getrolelist(this)">
    <option value="-1">--请选择--</option>
    <c:forEach var="d" items="${dlist}">
        <option value="${d.deptid}" ${userBean.bumenBean.deptid==d.deptid?'selected':''}>${d.dname}</option>
    </c:forEach>
</select> -----

    分配角色: <select name="rid">
    <option value="-1">--请选择角色--</option>
    <c:forEach var="r" items="${jlist}">
        <option value="${r.rid}" ${userBean.jueseBean.rid==r.rid?'selected':''}>${r.rname}</option>
    </c:forEach>
</select><br>
    <input type="submit" value="保存">

</form>
</body>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script>
    function getrolelist(obj) {
        /**
         *ajax去获取列表在查询，异步操作
         * 选一个js的类库，组件 jquery
         */
        $.post("getbujuese.do", {deptid: obj.value}, function (data) {
            var rselect = $("[name=rid]");
            rselect.html("<option value='-1'>--请选择角色--</option>");
           // var jlist = data;
            for (var i = 0; i < data.length; i++) {
                //要拼接一个option，在之前要先把原来的删掉
                rselect.append("<option value=" + data[i].rid + ">"+data[i].rname+"</option>");
            }
        })
    }
</script>
</html>
