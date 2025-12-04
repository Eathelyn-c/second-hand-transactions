<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eathelyn
  Date: 2025/12/3
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - 二手物品发布</title>
</head>
<body>
<h1>二手物品发布</h1>
<a href="secondhand.jsp">返回二手信息首页</a>
<form action="item" method="post">
    <label for="name">商品名称：</label><input type="text" name="name"><br>
    <label for="description">商品描述：</label><textarea name="description" rows="4" cols="50"></textarea><br>
    <label for="price">价格：</label><input type="number" name="price" step="0.01"><br>
    <input type="submit" value="发布">
</form>
<c:if test="${not empty success}">
    <p style="color: green">${success}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>