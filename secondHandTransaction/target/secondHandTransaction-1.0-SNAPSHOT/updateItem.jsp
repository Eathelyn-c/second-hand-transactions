<%--
  Created by IntelliJ IDEA.
  User: eathelyn
  Date: 2025/12/3
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - 修改物品信息</title>
</head>
<body>
<h1>修改物品信息</h1>
<a href="itemDetail">返回我的物品</a>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
<form action="updateItem" method="post">
    <input type="hidden" name="id" value="${item.id}">
    <label for="name">物品名称：</label>
    <input type="text" id="name" name="name" value="${item.name}" required><br>
    <label for="description">物品描述：</label>
    <textarea id="description" name="description" rows="4" cols="50" required>${item.description}</textarea><br>
    <label for="price">物品价格：</label>
    <input type="number" id="price" name="price" value="${item.price}" step="0.01" min="0" required><br>
    <input type="submit" value="保存修改">
    <a href="itemDetail">取消</a>
</form>
</body>
</html>
