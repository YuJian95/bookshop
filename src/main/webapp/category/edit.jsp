<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/7
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改分类页面</title>
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
<br/>
<form role="form" method="post" action="/bs/BsCategoryAction?method=edit">

    <table align="center">
        <input type="hidden" name="catId" value="${category.catId}"/>
        <%--@declare id="catname"--%>
        <tr>
            <td><label for="catName">分类名称：</label></td>
            <td><input type="text" class="form-control col-lg-2" name="catName" value="${category.catName}"/></td>
            <button type="submit" class="btn btn-default" value="修改"></button>

            <button type="button" class="btn btn-default" value="取消" onclick="window.history.back()"></button>

    </table>
</form>
</body>
</html>
