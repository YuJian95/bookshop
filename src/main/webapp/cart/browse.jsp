<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/10
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看购物车</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<table>
    <tr>
        <th>书名</th>
        <th>单价</th>
        <th>数量</th>
        <th>总价</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.book.bookName}</td>
            <td>${item.book.bookPrice}</td>
            <td><input type="text" id="num_${item.book.bookId}" value="${item.num}" disabled></td>
            <td>${item.total}</td>
            <td>
                <a id="btn1_${item.book.bookId}" href="/bs/BsCartAction?method=delete&bookId=${item.book.bookId}">删除</a>
                <a id="btn2_${item.book.bookId}" href="JavaScript:void(0)"
                   onclick="document.getElementById('num_${item.book.bookId}').disable=false;
                           document.getElementById('btn1_${item.book.bookId}').disable=false;
                           document.getElementById('btn2_${item.book.bookId}').disable=false;
                           document.getElementById('btn3_${item.book.bookId}').disable=false;
                           ">修改</a>
                <a id="btn3_${item.book.bookId}" disabled href="JavaScript:void(0)"
                   onclick="window.location.href='/bs/BsCartAction?method=edit&bookId=${item.book.bookId}&num='
                           +document.getElementById('num_${item.book.bookId}').value;
                           ">保存</a>
            </td>
        </tr>
    </c:forEach>
    <div class="center-block">
        总计:${total}&nbsp;&nbsp;
        <a href="/bs/BsBookAction?method=browse">继续购物</a>
        <a href="/bs/BsCartAction?method=browse">请空购物车</a>
        <a href="/bs/BsOrderAction?method=browse">结账</a>

    </div>
</table>
</body>
</html>
