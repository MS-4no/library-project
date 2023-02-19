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
<h3>下記の内容で登録しますか？</h3>
<h3>ユーザー情報</h3>


<form action="/library/CustomerRegistServlet?action=order" method="post">
    <table border="1">
        <tr>
        <td>お名前</td><td>${customer.name}</td>
        </tr>
        <tr>
        <td>電話番号</td><td>${customer.tel}</td>
        </tr>
        <tr>
        <td>住所</td><td>${customer.address}</td>
        </tr>
        <tr>
        <td>e-mail</td><td>${customer.email}</td>
        </tr>
         <tr>
        <td>パスワード</td><td>${customer.pass}</td>
        </tr>
    </table><br>
    <input type="submit" value="この内容で確定">
</form>



</body>
</html>