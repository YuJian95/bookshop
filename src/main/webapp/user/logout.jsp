<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/7
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销用户</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
</head>
<body>
<%
    session.invalidate();
    response.sendRedirect("/bs/index.jsp");
%>
</body>
</html>