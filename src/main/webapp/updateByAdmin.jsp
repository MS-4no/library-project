<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報更新画面</title>
</head>
<body>

<jsp:include page="/adminMenu.jsp" /><br>
<h3></h3>



<h3>ユーザー情報を入力してください</h3>

<form action="/library/CustomerUpdateByAdminServlet?action=update_input" method="post">
    <table border="1">
        <tr>
        <td>No.</td><td><input type="text" name="code" value= "${customer.code}"></td>
        </tr>
        <tr>
        <td>名前</td><td><input type="text" name="name" value= "${customer.name}"></td>
        </tr>
        <tr>
        <td>住所</td><td><input type="text" name="address" value= "${customer.address}"></td>
        </tr>
        <tr>
        <td>電話番号</td><td><input type="text" name="tel" value= "${customer.tel}"></td>
        </tr>
        <tr>
        <td>e-mail</td><td><input type="text" name="email" value= "${customer.email}"></td>
        </tr>
        <tr>
        </tr>
    </table>
<input type="submit" value="更新">
</form>



</body>
</html>