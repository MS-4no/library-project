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
<a href="/library/AdminRServlet?action=reserved">予約済</a>|

<a href="/library/AdminRServlet?action=checkedout">貸出中</a>|

<!--  <a href="/library/AdminRServlet?action=all">過去予約履歴一覧</a>|-->
<hr>
<form action="/library/AdminRServlet?action=adminCustomerIDSearch
" method="post">
ユーザーID：<input type="text" name="customer_code">
<input type="submit" value="検索">
</form>
<form action="/library/AdminRServlet?action=codeSearch" method="post">
予約No.：<input type="text" name="code">
<input type="submit" value="検索">
</form>
<form action="/library/AdminRServlet?action=titleSearch" method="post">
タイトル：<input type="text" name="title">
<input type="submit" value="検索">
</form>
<form action="/library/AdminRServlet?action=authorSearch" method="post">
著者：<input type="text" name="author">
<input type="submit" value="検索">
</form>
<form action="/library/AdminRServlet?action=publisherSearch" method="post">
出版社：<input type="text" name="publisher">
<input type="submit" value="検索">
</form>
<table border="1">
<tr align="center"><td>予約No.</td><td>ユーザーID</td><td>タイトル</td><td>著者</td><td>出版社</td><td>予約日</td><td>貸出予定日</td><td>貸出日</td><td>返却日</td><td>ステータス</td><td colspan="3">ステータス変更</td></tr>
<c:forEach items="${histories}" var="history">
<tr><td>${history.code }</td><td>${history.customer }</td><td>${history.name }</td><td>${history.author }</td><td>${history.publisher }</td><td>${history.orderedDate }</td><td>${history.reserveDate }</td><td>${history.checkoutDate}</td><td>${history.returnDate }</td><td>${history.status }</td>
<td>
<form action="/library/AdminRServlet?action=reserve" method="post">
    <input type="hidden" name="ordered_code" value="${history.code}">
    <input type="hidden" name="item_code" value="${history.itemCode}">
    <input type="hidden" name="name" value="${history.name}">
    <input type="hidden" name="customer" value="${history.customer}">
    <input type="submit" value="予約取り消し">
</form>
</td>
<td>
<form action="/library/AdminRServlet?action=checkout" method="post">
    <input type="hidden" name="ordered_code" value="${history.code}">
    <input type="hidden" name="item_code" value="${history.itemCode}">
    <input type="hidden" name="name" value="${history.name}">
    <input type="hidden" name="customer" value="${history.customer}">
    <input type="submit" value="貸出中">
</form>
</td>
<td>
<form action="/library/AdminRServlet?action=returns" method="post">
    <input type="hidden" name="ordered_code" value="${history.code}">
    <input type="hidden" name="item_code" value="${history.itemCode}">
    <input type="hidden" name="name" value="${history.name}">
    <input type="hidden" name="customer" value="${history.customer}">
    <input type="submit" value="返却">
</form>
</td>
</tr>
</c:forEach>

</table>
</body>
</html>