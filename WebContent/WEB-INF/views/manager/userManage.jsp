<%@page import="dto.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%	List<String> teamNameList = (List) request.getAttribute("teamnamelist"); %>
    <%	List<UserInfo> userList = (List) request.getAttribute("userlist"); %>
    <%	List<Integer> inviteCnt = (List) request.getAttribute("invitecnt"); %>
    <%	List<Integer> chargeCnt = (List) request.getAttribute("chargecnt"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>게시글 목록</h1>
<hr>

<table>
<tr>
	<th>팀이름</th>
	<th>아이디</th>
	<th>이름</th>
	<th>성별</th>
	<th>생년월일</th>
	<th>연락처</th>
	<th>게시글</th>
	<th>결제</th>
</tr>

<%	for(int i=0; i<teamNameList.size(); i++) { %>
<tr>
	<td><%= teamNameList.get(i).toString() %></td>
	<td><%= userList.get(i).getUserId() %></td>
	<td><%= userList.get(i).getUserName() %></td>
	<td><%= userList.get(i).getUserGender() %></td>
	<td><%= userList.get(i).getUserBirth() %></td>
	<td><%= userList.get(i).getUserPhone() %></td>
	<td><%= inviteCnt.get(i) %></td>
	<td><%= chargeCnt.get(i) %></td>
	
</tr>
<%	} %>

</table>



<%@ include file="../../layout/paging.jsp" %>

</body>
</html>
