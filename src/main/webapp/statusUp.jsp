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

<h3>予約番号：${ocode}  ユーザーID：${customer}  商品名：${name} (商品番号：${icode})の予約を${message}しました</h3>


</body>
</html>