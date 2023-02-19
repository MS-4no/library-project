<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<link rel="stylesheet" type="text/css" href="Review.css">
</head>
<body>
<jsp:include page="/menu.jsp" /><br>
<h3>レビュー投稿</h3>
<jsp:include page="/ShowReview.jsp" />

<h1>レビュー閲覧</h1>

	<div class="bg_pattern Cross"></div>
	<div class="section">
	</div>
 
	<table border="1">
	<tr><td colspan="2">${name }</td><td>評価平均</td><td>${avg}</td></tr>
	<tr><td>レビュー番号</td><td>コメント</td><td>レビュー日</td><td>５段階評価</td></tr>
	 
	<c:forEach items="${items}" var="item">

		<tr><td>${item.code}</td><td>${item.content}</td><td>${item.review_date}</td><td>${item.review_score}</td></tr>
				
	</c:forEach>
	
	</table>

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