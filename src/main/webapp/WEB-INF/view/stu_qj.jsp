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
    <script src="../../js/calendarTime.js"></script>
</head>
<body>
        <form action="saveStuQj2.do" method="post">


        <table>
            <tr>
                <td colspan="100">
                    ${stu.username}同学正在请假
                    <input type="hidden" name="sid" value="${stu.id}"/>
                </td>
            </tr>
            <tr>
                <td>请假时间</td>
                <td><input name="qjtime" type="text"></td>
            </tr>
            <tr>
                <td>选择</td>
                <td>
                    开始时间：<input type="text" name="stime" onclick="setDayH(this)"/>
                    结束时间：<input type="text" name="etime" onclick="setDayH(this)"/>
                </td>
                <tr>
            <tr>
                <td>请假原因</td>
                <td><input name="qjcause" type="text"></td>
            </tr>
            <td colspan="100">
                <input type="submit" value="提交"/>
            </td><tr>
            </tr>
        </table>
        </form>
</body>
</html>
