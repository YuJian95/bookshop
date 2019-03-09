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
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<br/>
<form method="post" action="/bs/BsCategoryAction?method=edit">
    <input type="hidden" name="catId" value="${category.catId}"/>
    <table border="0" width="562" align="center" style="border-collapse: collapse;font-size: 12px">
        <tr>
            <td>分类名称</td>
            <td align="left">
                <input type="text" name="catName" size="47" value="${category.catName}"/>
            </td>
        </tr>
    </table>
    <p align="center">
        <input type="submit" value="提交"/>
        <input type="button" value="取消" onclick="window.history.back()"/>
    </p>
</form>
</body>
</html>
