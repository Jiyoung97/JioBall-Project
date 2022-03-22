<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%	String result = (String) request.getAttribute("result"); %>

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
<% if(result!=null) { %>
		<h3>회원님의 아이디는</h3><br>
		<h3 class="text-primary"><%= result %></h3><br><h3>입니다.</h3>
<% } else {%>
		<br><h3>회원님의 아이디를</h3><br>
		<h3 class="text-danger">찾을 수 없습니다.</h3>
<% } %>
	</div>
</div>
</body>
</html>