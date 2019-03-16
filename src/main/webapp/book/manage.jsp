<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/2/28
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<%--<!Doctype html>--%>
<html>
<head>
    <title>图书管理</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<bs:UserCheckTag right="2"/>
<div class="container">
    <form class="form-horizontal" role="form" method="POST" action="/bs/BsBookAction?method=manage&catId=${catId}">
        <label class="col-sm-1 control-label">书名：</label>
        <div class="col-lg-3"><input type="text" class="form-control" name="bookName" placeholder="书名"></div>
        <label class="col-sm-1 control-label">作者：</label>
        <div class="col-lg-3"><input type="text" class="form-control" name="bookAuthor" placeholder="作者"></div>
        <div class="col-sm-1">
            <button type="submit" class="btn btn-default">查询</button>
        </div>
    </form>
    <div class="row">
        <!--创建悬停表格-->
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-center">书名</th>
                <th class="text-center">作者</th>
                <th class="text-center">单价</th>
                <th class="text-center">数量</th>
                <th class="text-center">首页展示</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${pageList.list}" var="n">
                <tr>
                    <td class="text-center">
                        <a href="/bs/BsBookAction?method=show&bookId=${n.bookId}">
                                ${n.bookName}
                        </a>
                    </td>
                    <td class="text-center">${n.bookAuthor}</td>
                    <td class="text-center">${n.bookPrice}</td>
                    <td class="text-center">${n.bookNum}</td>
                    <td class="text-center">
                            <%--<a href="/bs/BsBookAction?method=change&bookId=${n.bookId}">${n.isCarousel}</a>--%>
                    </td>
                    <td class="text-center">
                        <a href="/bs/BsBookAction?method=willEdit&bookId=${n.bookId}">修改</a>
                        <a href="/bs/BsBookAction?method=delete&bookId=${n.bookId}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center">
        ${pageList.pageBar}&nbsp;<a href="/bs/book/add.jsp">添加图书</a>
    </div>
</div>
</body>
</html>
