<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/5
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>修改个人信息</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form" method="post"
              action="/bs/BsUserAction?method=edit">
            <h3 class="form-title">修改个人信息</h3>
            <div class="col-sm-9 col-md-9">
                <input type="hidden" name="userId" value="${sessionScope.user.userId}">
                <div class="form-group">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <input class="form-control required" type="text" placeholder="用户名" name="userName"
                           value="${sessionScope.user.userName}"
                           autofocus="autofocus"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock" aria-hidden="true"></i>
                    <input class="form-control required" type="password" placeholder="密码,最大只能有8位" id="password"
                           name="user.password"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-check" aria-hidden="true"></i>
                    <input class="form-control required" type="password" placeholder="确认密码"
                           name="userPwd1"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-user-secret" aria-hidden="true"></i>
                    <input class="form-control required" type="text" placeholder="真实名" name="userRealName"
                           value="${sessionScope.user.userRealName}"/>
                </div>

                <div class="form-group">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                    <input class="form-control required" type="text" placeholder="邮箱" name="userEmail"
                           value="${sessionScope.user.userEmail}"/>
                </div>

                <div class="form-group">
                    <i class="fa fa-phone" aria-hidden="true"></i>
                    <input class="form-control required" type="text" placeholder="电话" name="userPhone"
                           value="${sessionScope.user.userPhone}"/>
                </div>

                <div class="form-group">
                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                    <input class="form-control required" type="text" placeholder="地址" name="userAddr"
                           value="${sessionScope.user.userAddr}"/>
                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-success pull-right" value="修改"/>
                    <input type="button" onclick="window.history.back()"
                           class="btn btn-info pull-left" id="back_btn" value="返回"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
