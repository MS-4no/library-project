<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/menu.jsp" /><br>
<hr>
	<h2>ログイン画面</h2>

	<form action= "/library/LoginServlet" method="post">
	ユーザID：<input type= "text" name="code" ><br>
	パスワード：<input type= "password" name="pw"><br>
	<input type= "hidden" name="action" value="login" >
	<input type= "submit" value="ログイン" >
	</form>

</body>
</html>