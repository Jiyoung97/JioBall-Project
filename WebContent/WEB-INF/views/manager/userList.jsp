<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.TeamInfo"%>
<%@page import="dto.UserInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% List<TeamInfo> teamList = (List) request.getAttribute("teamlist"); %>
    <% List<UserInfo> userList = (List) request.getAttribute("userlist"); %>

<%@ include file="../../layout/headerManager.jsp" %>

<div class="page-header">

	<h3 class="page-title">유저 목록</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body">
				<form action="/manager/usersearch" method="get" class="forms-sample form-userlist-top">
					<input type="text" class="form-control form-input input-userlist-search form-input-search" name="teamname" placeholder="팀 이름을 입력해주세요">
					<button class="btn btn-sm btn-primary btn-userlist-search btn-ground-search">검색</button>
				</form>
				</div>
				<table class="table text-secondary">
					<thead>
					<tr>
						<th>팀번호</th>
						<th>팀이름</th>
						<th>아이디</th>
						<th>이름</th>
						<th>성별</th>
						<th>생년월일</th>
						<th>연락처</th>
						<th>매칭</th>
						<th>삭제</th>
					</tr>
					</thead>
					<% for (int i = 0; i < teamList.size(); i++) {%>
					<tr>
						<td><%=teamList.get(i).getTeamNo() %></td>
						<td><%=teamList.get(i).getTeamName()%></td>
						<td><%=userList.get(i).getUserId()%></td>
						<td><%=userList.get(i).getUserName()%></td>
						<td><% if(userList.get(i).getUserGender()==1) {%>남<%} else {%>여<%} %></td>
						<td><%=userList.get(i).getUserBirth().substring(0, 10)%></td>
						<td><%=userList.get(i).getUserPhone()%></td>
						<td><a href="?=<%=teamList.get(i).getTeamNo()%>">내역</a></td>
						<td><a href="/manager/userdelete?teamno=<%=teamList.get(i).getTeamNo()%>">삭제</a></td>
					</tr>
					<%	} %>
				</table>
				<br>
				</div>
			</div>
		</div>
		<%@ include file="../../layout/paging.jsp" %>
	</div>

<%@ include file="../../layout/footerManager.jsp" %>