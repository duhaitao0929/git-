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
    <title>用户展示页面</title>
    <script src="../../js/jquery-1.8.2.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<form action="getlikeUsername.do?current=1" method="post">
    <input class="one" name="likeName" type="text" placeholder="根据姓名模糊查询">
    <input type="submit" value="模糊查询">
</form>
<form action="getUserfanwei.do?current=1" method="post">
    <input class="two" name="sbirthday" type="date">--<input class="two" name="ebirthday" type="date">
    <input type="submit" value="范围查询">
</form>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>编码</th>
        <th>姓名</th>
        <th>密码</th>
        <th>年龄</th>
        <th>生日</th>
        <th>部门</th>
        <th>角色</th>
        <th>操作</th>
    </tr>
    <c:forEach var="l" items="${list}">
        <tr>
            <td>
                    ${l.id}
            </td>
            <td>
                    ${l.username}
            </td>
            <td>
                    ${l.password}
            </td>
            <td>
                    ${l.age}
            </td>
            <td>
                <fmt:formatDate value="${l.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td>
                    ${l.bumenBean.dname}
            </td>
            <td>
                    ${l.jueseBean.rname}
            </td>
            <td>
                <img alt="${l.portrait}" style="display: inline-block;width: 100px;height: 50px" src="http://localhost:8080/image/${l.portrait}">
            </td>
            <td>
                <a href="toUserDeptRole.do?id=${l.id}"><button>给员工分配部门和角色</button></a>
            </td>
            <td>
                <a href="todelid.do?id=${l.id}"><button>删除信息</button></a>
            </td>
            <td>
                <a href="getUserById.do?id=${l.id}">
                    <button>修改信息</button>
                </a>
            </td>
            <td>
                <a onclick="userPower(${l.id})"> <button>查看权限 </button></a>
            </td>
            <td>
                <form action="updatePortrait.do?id=${l.id}" method="post" enctype="multipart/form-data">
                    头像：<input type="file" name="img"><br><br>
                    <input type="submit" value="上传">
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8">
            <form action="getUserByCurrent.do" method="post">
                <a href="getUserSahngye.do?current=${current}">上一页</a>
                <input class="three" name="current" value="${current}" type="text">
                <a href="getUserXiaye.do?current=${current}">下一页</a>
                <input type="submit" value="跳转">
            </form>
        </td>
    </tr>

</table>
<a href="totiaoadd.do"><button>添加</button></a>
<script src="../../js/jquery-1.8.2.js"></script>
<script>
    var userPower=function (id) {
        window.open("getUserPower.do?id="+id,"","height=500px,width=500px");
    }
</script>


</body>
</html>
