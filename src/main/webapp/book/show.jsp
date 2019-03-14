<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/2/28
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>查看图书</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-2 text-right">
            <img src="/bs/bookimg/${book.bookPicture}" width="150" height="230">
        </div>
        <div class="col-xs-2">
            <ul class="list-unstyled">
                <li><h3>${book.bookName}</h3></li>
                <li>作者:${book.bookAuthor}</li>
                <li>出版社:${book.bookPublisher}</li>
                <li>ISBN:${book.bookIsbn}</li>
                <li>价格:${book.bookPrice}</li>
                <li>数量:${book.bookNum}</li>
                <li>加入购物车</input><input type="button" value="返回" onclick="window.history.back()"/></li>
            </ul>
        </div>
    </div>
    <div class="col-xs-10">
        <p>${book.bookDesc}</p>
    </div>
</div>
</body>
</html>
