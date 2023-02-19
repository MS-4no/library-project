<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customer search</title>
</head>
<body>

<jsp:include page="/adminMenu.jsp" />
<form action="/library/AdminCServlet?action=IDSearch" method="post">
ID：<input type="text" name="id">
<input type="submit" value="検索">
</form>
<form action="/library/AdminCServlet?action=nameSearch" method="post">
名前：<input type="text" name="name">
<input type="submit" value="検索">
</form>
<form action="/library/AdminCServlet?action=telSearch" method="post">
電話番号：<input type="text" name="tel">
<input type="submit" value="検索">
</form>
<table border="1">
<tr><td>No</td><td>名前</td><td>住所</td><td>電話番号</td><td>e-mail</td></tr>
<c:choose>
<c:when test="${test eq 1}">
<tr><td>${customers.code }</td><td>${customers.name }</td><td>${customers.address }</td><td>${customers.tel }</td><td>${customers.email }</td></tr>
<td>
<form action="/library/CustomerUpdateByAdminServlet?action=update" method="post">
    <input type="hidden" name="code" value="${customers.code}">
    <input type="hidden" name="name" value="${customers.name}">
    <input type="hidden" name="address" value="${customers.address}">
    <input type="hidden" name="tel" value="${customers.tel}">
    <input type="hidden" name="email" value="${customers.email}">
    <input type="submit" value="更新">
</form>
</td>
<td>
<form action="/library/CustomerUpdateByAdminServlet?action=delete" method="post">
    <input type="hidden" name="code" value="${customers.code}">
    <input type="submit" value="削除">
</form>
</td>
</c:when>
<c:otherwise>
<c:forEach items="${customers}" var="customer">
<tr><td>${customer.code }</td><td>${customer.name }</td><td>${customer.address }</td><td>${customer.tel }</td><td>${customer.email }</td>
<td>
<form action="/library/CustomerUpdateByAdminServlet?action=update" method="post">
    <input type="hidden" name="code" value="${customer.code}">
    <input type="hidden" name="name" value="${customer.name}">
    <input type="hidden" name="address" value="${customer.address}">
    <input type="hidden" name="tel" value="${customer.tel}">
    <input type="hidden" name="email" value="${customer.email}">
    <input type="submit" value="更新">
</form>
</td>
<td>
<form action="/library/CustomerUpdateByAdminServlet?action=delete" method="post">
    <input type="hidden" name="code" value="${customer.code}">
    <input type="submit" value="削除">
</form>
</td>
</tr>
</c:forEach>
</c:otherwise>
</c:choose>

</table>
</body>
</html>