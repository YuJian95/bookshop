<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/2/28
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<html>
<head>
    <title>修改图书</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="/bs/BsBookAction?method=add">
    <table class="table table-hover">
        <input type="hidden" name="bookId" value="${book.bookId}"/>
        <tr>
            <td>类别</td>
            <td><bs:BsCategoryTag/></td>
        </tr>
        <tr>
            <td>名称</td>
            <td><input type="text" name="bookName" value="${book.bookName}"></td>
        </tr>
        <tr>
            <td>出版社</td>
            <td><input type="text" name="bookPublisher" value="${book.bookPublisher}"></td>
        </tr>
        <tr>
            <td>ISBN</td>
            <td><input type="text" name="bookIsbn" value="${book.bookIsbn}"></td>
        </tr>
        <%--<tr>--%>
        <%--<td>图片</td>--%>
        <%--<td><input type="file" name="upFile" value="${book.}"></td>--%>
        <%--</tr>--%>
        <tr>
            <td>单价</td>
            <td><input type="text" name="bookPrice" value="${book.bookPrice}"></td>
        </tr>
        <tr>
            <td>作家</td>
            <td><input type="text" name="bookAuthor" value="${book.bookAuthor}"></td>
        </tr>
        <tr>
            <td>简介</td>
            <td><textarea rows="8" cols="61" name="bookDesc">${book.booDesc}</textarea></td>
        </tr>
        <tr>
            <td>数量</td>
            <td><input type="text" name="bookNum" value="${book.bookNum}"></td>
        </tr>
        <p>
            <input type="submit" value="修改"/>
            <input type="button" value="取消" onclick="window.history.back()"/>
        </p>
    </table>
</form>
</body>
</html>
