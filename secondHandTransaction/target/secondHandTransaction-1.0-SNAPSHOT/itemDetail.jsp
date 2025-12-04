<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eathelyn
  Date: 2025/12/3
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - 查看我发布的物品详情</title>
</head>
<body>
<h1>我发布的物品详情</h1>
<a href="secondhand.jsp">返回二手信息首页</a><br>
<c:if test="${empty searchItems}">
    <p style="color: red">你还没有发布任何物品！</p>
</c:if>
<table border="1">
    <tr>
        <th>物品名称</th>
        <th>物品描述</th>
        <th>物品价格</th>
        <th>删除</th>
        <th>修改</th>
    </tr>
    <c:forEach items="${searchItems}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.price}</td>
            <td>
                <a href="deleteItem?id=${item.id}">删除</a>
            </td>
            <td>
                <a href="updateItem?id=${item.id}">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
<c:if test="${not empty success}">
    <p style="color: green">${success}</p>
</c:if>
</body>
</html>
