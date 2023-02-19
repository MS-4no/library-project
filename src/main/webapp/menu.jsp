<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.CustomerBean"%>
<link rel="stylesheet" type="text/css" href="top.css">

<p>
	<a href="/library/top.jsp">TOP</a>|
	<a href="/library/ShowItemServlet">検索</a>
	<% CustomerBean c = (CustomerBean)session.getAttribute("customerInfo");
	if(c == null){%>
	<a href="/library/login.jsp">ログイン</a>
	<%}else{ %>
	<a href="/library/LoginServlet?action=logout">ログアウト</a>
	<% }%>
	<a href="/library/regist.jsp">新規登録</a>
	<a href="/library/CustomerUpdateServlet?action=input_customer">更新</a>
	<a href="/library/AdminRServlet?action=customerIDSearch">過去履歴</a>
	<a href="/library/CartServlet?action=show">カートを見る</a>
</p>