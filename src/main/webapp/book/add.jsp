<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/2/28
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>

<html>
<head>
    <title>添加图书</title>
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
    <form method="post" enctype="multipart/form-data" action="/bs/BsBookAction?method=add">
        <table class="table table-hover">
            <tr>
                <td>类别</td>
                <td><bs:BsCategoryTag/></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input type="text" name="bookName"></td>
            </tr>
            <tr>
                <td>出版社</td>
                <td><input type="text" name="bookPublisher"></td>
            </tr>
            <tr>
                <td>ISBN</td>
                <td><input type="text" name="bookIsbn"></td>
            </tr>
            <tr>
                <td>图片</td>
                <td><input type="file" name="upFile"></td>
            </tr>
            <tr>
                <td>单价</td>
                <td><input type="text" name="bookPrice"></td>
            </tr>
            <tr>
                <td>作家</td>
                <td><input type="text" name="bookAuthor"></td>
            </tr>
            <tr>
                <td>简介</td>
                <td><input type="text" name="bookDesc"></td>
            </tr>
            <tr>
                <td>数量</td>
                <td><input type="text" name="bookNum"></td>
            </tr>
            <p>
                <input type="submit" value="添加"/>
                <input type="button" value="取消" onclick="window.history.back()"/>
            </p>
        </table>
    </form>
</div>
</body>
</html>
