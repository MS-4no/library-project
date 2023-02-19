<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報登録画面</title>
<link rel="stylesheet" type="text/css" href="regist.css">
</head>
<body>

<jsp:include page="/adminMenu.jsp" /><br>
<%int a = (int)request.getAttribute("key");
if ( a == 1 ) { %>
<h2>商品情報更新</h2>

<h4>商品情報を入力してください</h4>
<form action="/library/ItemInfoServlet?action=decide" method="post">
     <table border="1">
     <tr><td>商品番号</td><td>${code}</td></tr>
     <tr><td>商品名</td><td><input type="text" name="name" value="${name }"></td></tr>
     <tr><td>著者</td><td><input type="text" name="author" value="${author }"></td></tr>
     <tr><td>出版社</td><td><input type="text" name="publisher" value="${publisher }"></td></tr>
     <tr><td>画像ファイル名</td><td><input type="text" name="image" value="${image }"></td></tr>
    </table> 
    <input type="hidden" name="code" value="${code }">
    <div>
	<input type="submit" value="更新">
</div>
</form>
<%return;}%>
<h2>新規商品登録</h2>

<h4>商品情報を入力してください</h4>



<form action="/library/ItemInfoServlet?action=regist" method="post">
     <table border="1">
        <tr>
        <td>商品名</td><td><input type="text" name="name"></td>
        </tr>
        <tr>
        <td>著者</td><td><input type="text" name="author"></td>
        </tr>
        <tr>
        <td>出版社</td><td><input type="text" name="publisher"></td>
        </tr>
        <tr>
        <td>画像ファイル名</td><td><input type="text" name="image"></td>
        </tr>
    </table> 
<div>
	<input type="submit" value="登録">
</div>

</form>

</body>
</html>