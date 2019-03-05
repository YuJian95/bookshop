<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%-- 主页 --%>
<!DOCTYPE html>

<html>
<head>
    <title>主页</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

    <style>
        body {
            padding-top: 50px;
            padding-left: 50px;
        }
    </style>

</head>

<body>
<div class="container">
    <div id="header">
        <div id="logo">

        </div>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">网上书城</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span>首页</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                                    class="glyphicon glyphicon-user"></span>我的书城
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">我的订单</a></li>
                                <li><a href="#">我的收藏</a></li>
                                <%--这里显示登陆状态--%>
                                <li><a href="#">登陆状态</a></li>
                            </ul>
                        </li>
                        <li class="active"><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a>
                        </li>
                        <li><a href="#"></a></li>

                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div id="menu">
        <%--<bs:BsCategoryMenu/>--%>
    </div>
    <div id="category">
        <%--<bs:BsCategory/>--%>
    </div>

    <div id="content">
        <div id="left">
            <%--显示分类--%>
        </div>
        <div id="right">
            <%--显示出售的书籍信息--%>
        </div>
    </div>

    <div id="footer">
        <%--显示版权信息--%>
    </div>
</div>
</body>
</html>
