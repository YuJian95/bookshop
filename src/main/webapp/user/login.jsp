<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/5
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>用户登录</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/checkinput.js"></script>
</head>
<body>

<div class="container">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form" method="post"
              action="/bs/BsUserAction?method=login">
            <%--这里的/bs是项目的url--%>
            <h3 class="form-title">用户登录</h3>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <input class="form-control required" type="text" placeholder="用户名" name="userName"
                           autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock" aria-hidden="true"></i>
                    <input class="form-control required" type="password" placeholder="密码" name="password"
                           maxlength="8"/>
                </div>

                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/> 请记住我
                    </label>
                    <hr/>
                    <a href="#" id="register_btn" class="">忘记密码了</a>
                </div>

                <div class="form-group">
                    <input type="button" class="btn btn-success pull-left" href="/user/add.jsp" value="注册"/>
                    <input type="submit" class="btn btn-success pull-right" value="登录"/>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
