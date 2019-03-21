<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 2019/2/28
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" uri="http://yujian95.github.io/jstl/tag" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单管理</title>
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
<table class="table table-hover text-center">
    <tr>
        <th>订单号</th>
        <th>订货时间</th>
        <th>发货状态</th>
        <th>展开细目</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${pageList.list}" var="order">
        <tr>
            <td>${order.ordId}</td>
            <td>${order.ordDatetime}</td>
            <td>
                <c:choose>
                    <c:when test="${order.ordState == 0}">
                        <input type="button" value="发货"
                               onclick="window.location.href= '/bs/BsOrderAction?method=manageEditState&ordId=${order.ordId}';"/>
                    </c:when>
                    <c:when test="${order.ordState == 1}">
                        发货途中
                    </c:when>
                    <c:otherwise>
                        已收货
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <label>
                    <input type="radio" name="r${order.ordId}"
                           onclick="document.getElementById('det${order.ordId}').style.display = '';"/>
                    展开
                </label>
                <label>
                    <input type="radio" name="r${order.ordId}"
                           onclick="document.getElementById('det${order.ordId}').style.display = 'none';" checked/>
                    收起
                </label>
            </td>
            <td class="text-center">
                <a href="/bs/BsOrderAction?method=delete&ordId=${order.ordId}">删除</a>
            </td>
        </tr>
        <tr id="det${order.ordId}" style="display: none;">
            <td colspan="5" style="text-align: left">
                订单${order.ordId}的细目如下：
                <table class="table table-hover">
                    <tr>
                        <th>书名</th>
                        <th>单价</th>
                        <th>数量</th>
                    </tr>
                    <c:forEach items="${order.bsDetailses}" var="det">
                        <tr>
                            <td class="text-left">
                                <a href="/bs/BsBookAction?method=show&bookId=${det.book.bookId}">${det.book.bookName}</a>
                            </td>
                            <td align="center">${det.book.bookPrice}</td>
                            <td align="center">${det.detNum}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="text-center">
    ${pageList.pageBar}
</div>

</body>
</html>
