<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reserve confirm</title>
</head>
<body>

<jsp:include page="/adminMenu.jsp" />


<form action="/library/SearchByAdminServlet?action=title" method="post">
タイトル：<input type="text" name="title">
<input type="submit" value="検索">
</form>
<form action="/library/SearchByAdminServlet?action=author" method="post">
著者：<input type="text" name="author">
<input type="submit" value="検索">
</form>
<form action="/library/SearchByAdminServlet?action=publisher" method="post">
出版社：<input type="text" name="publisher">
<input type="submit" value="検索">
</form>
<table border="1">
<tr align="center"><td>No.</td><td>タイトル</td><td>著者</td><td>出版社</td><td>ステータス</td></tr>
<c:forEach items="${items}" var="item">
<tr><td>${item.code }</td><td>${item.name }</td><td>${item.author }</td><td>${item.publisher }</td><td>${item.status }</td>
<td>
<form action="/library/ReserveByAdminServlet?action=reserve" method="post">
    <input type="hidden" name="item_code" value="${item.code}">
    <input type="hidden" name="status" value="${item.status }">
    <input type="submit" value="予約">
</form>
</td>
<td>
<form action="/library/ReviewByAdminServlet?action=show" method="post">
    <input type="hidden" name="item_code" value="${item.code}">
    <input type="submit" value="レビュー表示">
</form>
</td>
<td>
<form action="/library/ItemInfoServlet?action=update" method="post">
    <input type="hidden" name="item_code" value="${item.code}">
    <input type="submit" value="商品情報更新">
</form>
</td>
<td>
<form action="/library/ItemInfoServlet?action=delete" method="post">
    <input type="hidden" name="item_code" value="${item.code}">
    <input type="hidden" name="name" value="${item.name}">
    <input type="submit" value="商品削除">
</form>
</td>
</tr>
</c:forEach>

</table>
</body>
</html>