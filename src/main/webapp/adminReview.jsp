<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminReview</title>
</head>
<body>

<jsp:include page="/adminMenu.jsp" />

<h1>レビュー閲覧</h1>

	<div class="bg_pattern Cross"></div>
	<div class="section">
	</div>
<%int a = (int)request.getAttribute("key");
if(a==1){ %> 
<form action="/library/ReviewByAdminServlet?action=IDSearch" method="post">
ユーザーID：<input type="text" name="id">
<input type="submit" value="検索">
</form>
<form action="/library/ReviewByAdminServlet?action=commentSearch" method="post">
コメント：<input type="text" name="comment">
<input type="submit" value="検索">
</form>
	<table border="1">
	<tr><td>レビュー番号</td><td>商品名</td><td>ユーザーID</td><td>コメント</td><td>レビュー日</td><td>５段階評価</td></tr>
	
	<c:forEach items="${items}" var="item">

		<tr><td>${item.code}</td><td>${item.item_name}</td><td>${item.custome}</td><td>${item.content}</td><td>${item.review_date}</td><td>${item.review_score}</td>
		<td>
<form action="/library/ReviewByAdminServlet?action=deleteByAll" method="post">
    <input type="hidden" name="review_code" value="${item.code}">
    <input type="hidden" name="item_code" value="${item.item_code}">
    <input type="submit" value="削除">
</form>
</td>
		
		</tr>
				
				
	</c:forEach>
	
	</table>
<%} else if(a==2){ %>
<table border="1">
<tr><td colspan="3">${name }</td><td>評価平均</td><td>${avg}</td></tr>
	<tr><td>レビュー番号</td><td>ユーザーID</td><td>コメント</td><td>レビュー日</td><td>５段階評価</td></tr>
	
	<c:forEach items="${items}" var="item">

		<tr><td>${item.code}</td><td>${item.custome}</td><td>${item.content}</td><td>${item.review_date}</td><td>${item.review_score}</td>
		<td>
<form action="/library/ReviewByAdminServlet?action=delete" method="post">
    <input type="hidden" name="review_code" value="${item.code}">
    <input type="hidden" name="item_code" value="${item.item_code}">
    <input type="submit" value="削除">
</form>
</td>
		
		</tr>
				
				
	</c:forEach>
	
	</table>
<%} %>
<!--
<c:forEach items="${review}" var="review">
    <form action="/library/ShoeReviewSevlet?action=review" method="post">
        <input type="hidden" name="item_code" value="${review.code}">
        レビュー番号：<b>${review.code}</b><br>
        商品番号：<b>${review.item_code}</b><br>
        レビュー内容：<b>${review.content}円</b><br>
    </form>
</c:forEach>
-->

</body>
</html>