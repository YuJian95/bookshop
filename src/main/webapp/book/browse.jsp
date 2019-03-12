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
<!DOCTYPE html>
<html>
<head>
    <title>图书浏览</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" method="POST" action="/bs/BsBookAction?method=browse&catId=${catId}">
        <div class="form-group">
            <label class="col-sm-1 control-label">书名：</label>
            <div class="col-lg-3"><input type="text" class="form-control" name="bookName" placeholder="书名"></div>
            <label class="col-sm-1 control-label">作者：</label>
            <div class="col-lg-3"><input type="text" class="form-control" name="bookAuthor" placeholder="作者"></div>
            <div class="col-sm-1">
                <button type="submit" class="btn btn-default">查询</button>
            </div>
        </div>
    </form>

    <table class="table table-hover">
        <tr>
            <th class="text-center">书名</th>
            <th class="text-center">作者</th>
            <th class="text-center">单价</th>
            <th class="text-center">数量</th>
            <th class="text-center">操作</th>
        </tr>

        <tbody>
        <c:forEach items="${pageList.list}" var="book">
            <tr>
                <td class="text-center">
                    <a href="/bs/BsBookAction!show.action&bookId=${book.bookId}">
                            ${book.bookName}
                    </a>
                </td>
                <td class="text-center">${book.bookAuthor}</td>
                <td class="text-center">${book.bookPrice}</td>
                <td class="text-center">${book.bookNum}</td>
                <td class="text-center">
                    <a href="/bs/BsCartAction?method=add&bookId=${book.bookId}">加入购物车</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
