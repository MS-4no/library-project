<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報登録画面</title>
</head>
<body>

<jsp:include page="/menu.jsp" /><br>
<h3>ご登録ありがとうございました。</h3>
あなたのユ―ザーIDは
<h3><font color="red">${customerNumber}</font></h3>
になります。

</body>
</html>