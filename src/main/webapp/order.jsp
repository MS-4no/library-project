<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約完了</title>
</head>
<body>

<jsp:include page="/menu.jsp" /><br>
<h3>予約を確定しました</h3>
あなたの注文番号は${orderNumber}です。<br>
<a href="/library/top.jsp">トップページに戻る</a>

</body>
</html>