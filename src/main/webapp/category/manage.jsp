<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/5
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>分类管理</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<br/>
<div class="container">
    <table border="1" align="center" width="98%" cellpadding="4" style="border-collapse: collapse">
        <tr>
            <td align="left" width="60">分类</td>
            <td align="center" width="160">分类名称</td>
            <td align="center" width="80">操作</td>
        </tr>
        <c:forEach items="${pageList.list}" var="n">
            <tr>
                <td align="left">${n.catId}</td>
                <td align="center">${n.catName}</td>
                <td align="center">
                    <a href="/bs/BsCategoryAction?method=willEdit&catId=${n.catId}">修改</a>
                    <a href="/bs/BsCategoryAction?method=delete&catId=${n.catId}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <center>${pageList.pageBar}&nbsp;<a href="/bs/category/add.jsp">添加</a></center>
</div>
</body>
</html>
