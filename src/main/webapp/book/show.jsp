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
<table>
    <tr>
        <td rowspan="3" width="169" align="center">
            <img border="0" src="/bs/bookimg/${book.bookPicture}" width="100" height="140" alt="">
        </td>
        <td height="30"><b>书名:${book.bookName}</b></td>
    </tr>
    <tr>
        <td height="169" valign="top">${book.bookDesc}</td>
    </tr>
    <tr>
        <td width="169" align="center">
            <input type="button" value="返回" onclick="window.history.back()"/>
        </td>
    </tr>
</table>

</body>
</html>
