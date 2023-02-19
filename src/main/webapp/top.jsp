<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>library 「カフナ」!</title>
<link rel="stylesheet" type="text/css" href="top.css">
</head>
<body>
 
<IMG class ="logo" src = "大きい.png" alt="カフナのロゴ"><br>

<jsp:include page="/menu.jsp" />

<form action="/library/ShowItemServlet" method="get">
		<select name="category_choise">
			<option value="1">書籍名</option>
			<option value="2">著者名</option>
			<option value="3">出版社名</option>
		</select> <input type="text" name="user_query" value=""> <input
			type="submit" name="serch_submit" value="検索"> <input
			type="hidden" name="action" value="search">
</form>

<section class="section1">
<h3>ようこそ！図書館システム「カフナ」へ</h3>
近年、図書館にいく機会も減っていくだろう
本システムでは、図書館にいく機会を増やすために作られた図書館特化システムです。<br>
</section>

カフナ由来
　「図書館の大魔術師」で出てくるアフツァック中央図書の職員（司書）から取ってきた名前です。<br>
</body>
</html>