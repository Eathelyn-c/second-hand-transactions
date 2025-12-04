<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eathelyn
  Date: 2025/12/3
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - 二手信息</title>
</head>
<body>
<h1>二手物品交易平台</h1>
<p>欢迎，${sessionScope.username}</p>
<a href="logout">退出登录</a><br>
<a href="item.jsp">发布物品</a><br>
<a href="itemDetail">查看我发布的物品</a><br>
<form action="searchItem" method="post">
    <input type="text" name="keyWord" placeholder="请输入关键词" required>
    <input type="submit" value="搜索">
</form>
<c:if test="${empty items && param.keyWord!= null}">
    <p style="color: red">未找到相关商品！</p>
</c:if>
<table border="1">
    <tr>
        <th>物品名称</th>
        <th>描述</th>
        <th>价格</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
