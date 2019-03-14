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
<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<html>
<head>
    <title>查看购物车</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<bs:UserCheckTag right="1"/>
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
