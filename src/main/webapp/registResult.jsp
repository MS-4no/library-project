<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin page</title>
</head>
<body>

<jsp:include page="/adminMenu.jsp" />

<h3>商品番号：${code} 商品名：${name}  著者：${author}  出版社：${publisher}  画像ファイル：${image }を${message }しました</h3>


</body>
</html>