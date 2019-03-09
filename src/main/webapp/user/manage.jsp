<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/5
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <table border="1" width="98%" align="center" cellpadding="4" style="border-collapse: collapse">
        <tr>
            <th align="left" width="120">用户名</th>
            <th align="center" width="120">真实名</th>
            <th align="center" width="260">邮件地址</th>
            <th align="center" width="160">注册时间</th>
            <th align="center" width="80">操作</th>
        </tr>

        <c:forEach items="${pageList.list}" var="n">
            <tr>
                <td align="left">
                    <a href="/bs/BsUserAction?method=show&userId=${n.userId}" target="_blank">${n.userName}</a>
                </td>
                <td align="center">${n.userRealName}</td>
                <td align="center">${n.userEmail}</td>
                <td align="center">${n.userDatetime}</td>
                <td align="center"><a href="/bs/BsUserAction?method=delete&userId=${n.userId}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <center>${pageList.pageBar}&nbsp;<a href="/bs/user/add.jsp">添加</a></center>
</div>
</body>
</html>
