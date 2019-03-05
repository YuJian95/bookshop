<%-- 主页 --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

</head>

<body>

<div class="container">
    <div id="header">

        <%--logo显示--%>
        <div id="logo">
            <img src="img/background.png" class="img-rounded" alt="logo.png" height="50px">
        </div>
        <%--导航栏--%>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">网上书城</a>
                </div>
                <%--添加搜索栏--%>
                <div>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索图书">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a></li>

                        <li><a href="user/register.jsp"><span class="glyphicon glyphicon-user"></span>注册</a></li>
                        <li><a href="user/login.jsp"><span class="glyphicon glyphicon-log-in"></span>登录</a></li>

                        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-user"></span>我的书城<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">我的订单</a></li>
                                <li><a href="#">我的收藏</a></li>
                                <%--这里显示登陆状态--%>
                                <li><a href="#">登陆状态</a></li>
                            </ul>
                        </li>

                        <li><a href="#"></a></li>

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
                <li><a href="#">分类1</a></li>
                <li><a href="#">分类2</a></li>
                <li><a href="#">分类3</a></li>
            </ul>
        </div>

        <%--右边的内容展示--%>
        <div class="col-sm-10">
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
