<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/5
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加分类</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<bs:UserCheckTag right="2"/>
<body>

<form role="form" method="post" action="/bs/BsCategoryAction?method=add">
    <table align="center">
        <%--@declare id="catname"--%>
        <tr>
            <td><label for="catName">分类名称：</label></td>
            <td><input type="text" class="form-control col-lg-2" name="catName" placeholder="请输入分类名称"/></td>
            <td>
                <button type="submit" class="btn btn-default">添加</button>
            </td>
            <td>
                <button type="button" class="btn btn-default" onclick="window.history.back()">取消</button>
            </td>
    </table>
</form>
</body>
</html>
