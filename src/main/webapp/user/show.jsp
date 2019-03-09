<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/5
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>显示用户</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<table border="0" width="362" align="center" style="border-collapse: collapse">
    <tr>
        <td width="100">用户名：</td>
        <td align="left">${user.userName}</td>
    </tr>
    <tr>
        <td>真实名：</td>
        <td align="left">${user.userRealName}</td>
    </tr>
    <tr>
        <td width="100">邮箱：</td>
        <td align="left">${user.userEmail}</td>
    </tr>
    <tr>
        <td width="100">电话：</td>
        <td align="left">${user.userPhone}</td>
    </tr>
    <tr>
        <td width="100">地址：</td>
        <td align="left">${user.userAddr}</td>
    </tr>
    <tr>
        <td width="100">注册时间：</td>
        <td align="left">${user.userDatetime}</td>
    </tr>
</table>
<center><input type="button" value="返回" name="B2" onclick="window.history.back()"></center>
</body>
</html>
