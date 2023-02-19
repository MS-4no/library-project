<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>history</title>
</head>
<body>

<jsp:include page="/menu.jsp" />


<table border="1">
<tr align="center"><td>予約No.</td><td>ユーザーID</td><td>タイトル</td><td>著者</td><td>出版社</td><td>予約日</td><td>貸出予定日</td><td>貸出日</td><td>返却日</td>
<c:forEach items="${histories}" var="history">
<tr><td>${history.code }</td><td>${history.customer }</td><td>${history.name }</td><td>${history.author }</td><td>${history.publisher }</td><td>${history.orderedDate }</td><td>${history.reserveDate }</td><td>${history.checkoutDate}</td><td>${history.returnDate }</td>
</tr>
</c:forEach>
</table>
<br><br>

<table border="1">
	<tr><td>レビュー番号</td><td>商品名</td><td>ユーザーID</td><td>コメント</td><td>レビュー日</td><td>５段階評価</td></tr>
	
	<c:forEach items="${review}" var="r">

		<tr><td>${r.code}</td><td>${r.item_name}</td><td>${r.custome}</td><td>${r.content}</td><td>${r.review_date}</td><td>${r.review_score}</td>
		<td>
<form action="/library/ReviewByAdminServlet?action=deleteByCustomer" method="post">
    <input type="hidden" name="review_code" value="${r.code}">
    <input type="submit" value="削除">
</form>
</td>
		
		</tr>
				
				
	</c:forEach>
	
	</table>
</body>
</html>