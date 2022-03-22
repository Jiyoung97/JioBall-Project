<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%	boolean result = (boolean) request.getAttribute("result"); %>

<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>JIOBall FindResult</title>
<link rel="stylesheet" href="/assets/css/style.css">
</head>
<body>
<div class="card">
	<div class="card-body" style="text-align:center; margin:50px">
<% if(result) { %>
		<h3>회원님의</h3><br>
		<h3 class="text-primary">아이디로 메일을</h3><br><h3>보냈습니다.</h3>
<% } else { %>
		<br><h3>회원님의 비밀번호를</h3><br>
		<h3 class="text-danger">찾을 수 없습니다.</h3>
<% } %>
	</div>
</div>
</body>
</html>