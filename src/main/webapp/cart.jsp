<%@ page language="java" contentType="text/html; charset=UTF-8"
	   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>

<jsp:include page="/menu.jsp" /><br>
<h3>現在のカートの中身</h3>

<c:if test="${empty cart.items}">
現在、カートは空です。
</c:if>

<c:if test="${not empty cart.items}">
<table border="1">
<tr><td>商品番号</td><td>商品名</td><td>削除</td></tr>

<c:forEach items="${cart.items}" var="item">
<tr align="center">
    <td >${item.code}</td>
    <td >${item.name}</td>
    <td >${item.author}円</td>
    <td >${item.publisher}</td>
<td>
<form action="/library/CartServlet?action=delete" method="post">
    <input type="hidden" name="item_code" value="${item.code}">
    <input type="submit" value="削除">
</form>
</td>
</tr>
</c:forEach>
</table>
<form action="/library/CartServlet?action=deleteAll" method="post">
	<input type="submit" value="全て削除">
</form>
<form action="/library/OrderServlet?action=" method="post">
貸出予定日
<input type="text" name="year" size="5">年
<input type="text" name="month" size="5">月
<input type="text" name="day" size="5">日
    <input type="submit" value="注文する">
</form>
</c:if>

</body>
</html>