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
팀 번호: <%=session.getAttribute("teamNo") %><br>
이름 : <%=session.getAttribute("userName") %><br>
아이디 : <%=session.getAttribute("userId") %><br>
비밀번호 : <%=session.getAttribute("userPw") %><br>

</div>

</body>
</html>