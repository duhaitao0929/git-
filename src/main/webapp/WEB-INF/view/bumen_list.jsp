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

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>编码</th>
        <th>部门</th>
        <th>操作</th>
    </tr>
    <c:forEach var="bu" items="${list}">
        <tr>
            <td>
                    ${bu.deptid}
            </td>
            <td>
                    ${bu.dname}
            </td>
            <td>
                <a href="toBumenJuese.do?deptid=${bu.deptid}"><button>给部门分配角色</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
