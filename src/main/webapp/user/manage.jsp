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
<%@taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<bs:UserCheckTag right="2"/>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>用户名</th>
            <th>真实名</th>
            <th>邮件地址</th>
            <th>注册时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
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
    <div class="text-center">${pageList.pageBar}&nbsp;<a href="/bs/user/add.jsp">添加</a></div>
</div>
</body>
</html>
