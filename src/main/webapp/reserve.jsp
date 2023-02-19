<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reserve</title>
</head>
<body>

<jsp:include page="/adminMenu.jsp" /><br>

<form action="/library/ReserveByAdminServlet?action=decide" method="post">
ユーザーID：<input type="text" name="id"><br>
貸出予定日
<input type="text" name="year" size="5">年
<input type="text" name="month" size="5">月
<input type="text" name="day" size="5">日

<input type="submit" value="決定">
<input type="hidden" name="item_code" value="${item_code }">
</form>



</body>
</html>