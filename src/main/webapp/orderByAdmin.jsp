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

お客様(ID：${customerID })の注文番号は
<h3><font color="red">${orderNumber}</font></h3>
になります。

</body>
</html>