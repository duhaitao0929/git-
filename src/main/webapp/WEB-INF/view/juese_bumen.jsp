<%--
  Created by IntelliJ IDEA.
  User: 东方月初
  Date: 2020/4/10
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<div>
    <h1>正在给--${jueseBean.rname}--挑选部门</h1>
    <form action="updateRoleDept.do" method="post">
        <input type="hidden" name="rid" value="${jueseBean.rid}" >
        <c:forEach items="${dlist}" var="d">
            <input type="radio" name="deptid" value="${d.deptid}" ${d.deptid == jueseBean.bumenBean.deptid?'checked':''} >${d.dname}<br>
        </c:forEach>
        <input type="submit" value="确认提交">
    </form>
</div>
</body>
</html>
