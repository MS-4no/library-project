<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報更新画面</title>
<link rel="stylesheet" type="text/css" href="regist.css">
</head>
<body>

<jsp:include page="/menu.jsp" /><br>
<h2>ユーザ情報更新</h2>


<h4>ユーザー情報を入力してください</h4>

<form action="/library/CustomerUpdateServlet?action=confirm" method="post">
    <table border="1">
        <tr>
        <td>お名前</td><td><input type="text" name="name" value= "${customerInfo.name}"></td>
        </tr>
        <tr>
        <td>電話番号</td><td><input type="text" name="tel" value= "${customerInfo.tel}"></td>
        </tr>
         <tr>
        <td>住所</td><td><input type="text" name="address" value= "${customerInfo.address}"></td>
        </tr>
        <tr>
        <td>e-mail</td><td><input type="text" name="email" value= "${customerInfo.email}"></td>
        </tr>
        <tr>
         <td>パスワード</td><td><input type="text" name="pass" value= "${customerInfo.pass}"></td>
        </tr>
    </table>
<div>
	<input type="submit" value="更新">
</div>
</form>



</body>
</html>