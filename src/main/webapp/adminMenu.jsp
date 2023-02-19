<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.CustomerBean"%>
<a href="/library/reservationConfirm.jsp">予約確認検索</a>|

<a href="/library/userSearch.jsp">ユーザー検索</a>|

<a href="/library/searchByAdmin.jsp">本検索</a>|

<a href="/library/ReviewByAdminServlet?action=showAll">レビュー一覧</a>|

<a href="/library/ItemInfoServlet?action=register">商品登録</a>|

<% CustomerBean c = (CustomerBean)session.getAttribute("customerInfo");
	if(c == null){%>
	<a href="/library/login.jsp">ログイン</a>
	<%}else{ %>
	<a href="/library/LoginServlet?action=logout">ログアウト</a>
	<% }%>

<hr>