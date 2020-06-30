<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <table border="1" rules="all">
            <tr>
                <td colspan="100">
                    下面是我的请假明细
                </td>
            </tr>
            <tr>
                <%--要是我们前台需要这些展示的数据，那么就需要去后台想办法给我查询这个数据--%>
                <th>编号</th>
                <th>请假时长</th>
                <th>请假时间</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>请假原因</th>
                <th>角色</th>
                <th>操作者</th>
                <th>审批状态</th>
                <th><a href="toStuQj.do">新增请假</a></th>
            </tr>
            <c:forEach var="pro" items="${list}">
                <tr align="center">
                    <td>
                        ${pro.id}
                    </td>
                    <td>
                        ${pro.qjtime}
                    </td>
                    <td></td>
                    <td>
                        <fmt:formatDate value="${pro.stime}" pattern="yyyy-MM-dd HH"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${pro.etime}" pattern="yyyy-MM-dd HH"/>
                    </td>
                    <td>
                        ${pro.qjcause}
                    </td>
                    <td>
                        ${pro.rname}
                    </td>
                    <td>
                        ${pro.uname}
                    </td>
                    <td>
                            ${pro.statusStr}
                    </td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
