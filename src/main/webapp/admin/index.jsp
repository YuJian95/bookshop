<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/3/6
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理员主页</title>
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
    <div id="header">

        <%--logo显示--%>
        <div id="logo">
            <img src="../img/logo.gif" class="img-rounded" alt="logo.png">
        </div>
        <%--导航栏--%>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">网上书城</a>
                </div>

                <div>
                    <form class="navbar-form navbar-left" method="post" action="">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索图书">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.jsp"><i class="fas fa-home"></i>首页</a></li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fas fa-users-cog"></i>我的设置</a>

                            <ul class="dropdown-menu">
                                <li><a href="/bs/user/logout.jsp"><i class="fas fa-sign-out-alt"></i>注销</a></li>
                                <li><a href="/bs/user/eidt.jsp"><i class="fas fa-key"></i>修改密码</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>

<%--中间的内容--%>
<div class="container">
    <div class="row">
        <%--分类菜单--%>
        <div class="col-sm-2">
            <ul class="nav nav-pills nav-stacked text-center">
                <li><a href="/bs/BsUserAction?method=manage"><i class="fas fa-users"></i>用户管理</a></li>
                <li><a href="/bs/BsOrderAction?method=manage"><i class="fas fa-list-ul"></i>订单管理</a></li>
                <li><a href="/bs/BsBookAction?method=manage"><i class="fas fa-book"></i>图书管理</a></li>
                <li><a href="/bs/BsCategoryAction?method=manage"><i class="fas fa-stream"></i>分类管理</a></li>
            </ul>
        </div>

        <%--右边的数据展示--%>
        <div class="col-sm-10" id="right">
            <table class="table table-hover text-center">
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>操作</th>
                </tr>
            </table>
        </div>
    </div>
</div>

<%--底部的内容--%>
<div class="jumbotron text-center" style="margin-bottom: 0">
    <div class="footer_copyright">
        <span>Copyright (C) 网上书城 2019, All Rights Reserved</span>
    </div>
</div>
</body>
</html>
