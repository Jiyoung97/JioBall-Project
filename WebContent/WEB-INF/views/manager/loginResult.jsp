<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%	String msg = (String) request.getAttribute("msg"); %>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
	width:350px;
	margin:100px auto;
	border:1px solid black;
	}
</style>

</head>
<body>

<div>
<h3><%= msg %></h3>
<hr>
<h4>SESSION</h4>
관리자 번호: <%=session.getAttribute("managerno") %><br>
</div>

</body>
</html>