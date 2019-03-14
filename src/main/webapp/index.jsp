<%-- 主页 --%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

<div id="header" class="container">

    <%--logo显示--%>
    <div id="logo">
        <img src="img/logo.gif" class="img-rounded" alt="logo.gif">
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
                    <li><a href="index.jsp"><i class="fas fa-home"></i>首页</a></li>
                    <li><a href="#"><i class="fas fa-cart-plus"></i>购物车</a></li>
                    <li><a href="#"><i class="fas fa-book"></i>我的订单</a></li>
                    <bs:BsUserStateTag/>
                </ul>
            </div>
        </div>
    </nav>

</div>

<%--中间的内容--%>
<div id="content" class="container">
    <div class="row">
        <%--分类菜单--%>
        <div id="left" class="col-sm-2 text-center font-menu-button">
            <bs:BsCategoryTag/>
        </div>

        <%--右边的内容展示--%>
        <div id="content" class="col-sm-10">

        </div>
    </div>
</div>

<div id="footer" class="jumbotron text-center" style="margin-bottom: 0">
    <bs:BsCoryrightTag/>
</div>
</body>
</html>
