<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SerchPage</title>
<link rel="stylesheet" type="text/css" href="search.css">
</head>

<body>
  
<jsp:include page="/menu.jsp" />

	【検索ページ】
	<br>
	<form action="/library/ShowItemServlet" method="get">
		<select name="category_choise">
			<option value="1">書籍名</option>
			<option value="2">著者名</option>
			<option value="3">出版社名</option>
		</select> <input type="text" name="user_query" value=""> 
		<input type="submit" name="serch_submit" value="検索"> 
		<input type="hidden" name="action" value="search">
	</form>
	<hr>
${message}
	
	<c:forEach items="${items}" var="item">
	<img src="img/${item.img}" width="100" height="150"><br>
	
${item.name} ${item.author} ${item.publisher} ${item.status}
<form action="/library/CartServlet?action=add" method="post">
<input type="hidden" name="item_code" value="${item.code }">
<input type="hidden" name="status" value="${item.status }">
 <input type="submit" name="order_submit" value="予約カートに入れる"> 
</form>
<!--  
<form action="/Test/ShowReviewServlet" method="get">
 <input type="submit" name="review_submit" value="レビューを投稿する"> 
 </form>
-->
 
 <form action="/library/ShowReviewServlet" method="post">
  <input type="submit" name="review_submit" value="レビューを投稿"> 
 <input type="hidden" name="review_code" value="${item.code}">
</form>
<br>
</c:forEach>

<c:if test="${empty items}">
検索ワードを入力してください。
</c:if>

</body>
</html>