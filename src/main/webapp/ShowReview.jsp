<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--   
<a href="/Test/ShowReviewServlet2">レコードの閲覧</a>
-->

<!-- 
<form action="/Test/ShowReviewServlet2" method="post">
<input type="submit" value="商品番号１のレビューを見る">
</form>
-->

<form action="/library/ShowReviewServlet" method="post">
<!--  
追加：商品番号<input type="text" name="item_code" size="2">
-->
5段階評価：
<select name="review_score">
        <option value="1">1
        <option value="2">2
        <option value="3">3
        <option value="4">4
        <option value="5">5
</select><br>
<!--  
<input type="text" name="content" >を<input type="submit" value="追加">
-->
<textarea name="content" rows="4" cols="40" required>レビューを記入します。</textarea><br>
<input type="submit" value="送信">
<input type="hidden" name="action" value="add">
<input type="hidden" name="review_code" value="${review_code}">
</form>

<!-- 
<form method="post" action="sample.cgi">
<textarea name="kansou" rows="4" cols="40">感想を記入します。</textarea><br>
<input type="submit" value="送信">
</form>
-->

<!--  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShowReview</title>
</head>
<body>

<a href="/Test/ShowReviewServlet">レビュー閲覧</a>

</body>
</html>
-->