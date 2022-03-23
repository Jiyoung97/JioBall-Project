<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h3 class="text-secondary">MY PAGE</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body" style="text-align:center;">
			<% if(session.getAttribute("userId").toString().substring(0, 7).equals("[kakao]")) { %>
			<h2><%= session.getAttribute("userId").toString().substring(7) %></h2>
			<% } else {%>
			<h2><%= session.getAttribute("userId") %></h2>
			<% } %>
			</div>
		</div>
		<hr>
		<div class="card">
			<div class="card-body" style="text-align:center;">
			<br><br>
			<button type="button" class="btn btn-secondary btn-rounded btn-fw" onclick="location.href='/mypage/userinfo'">개인 정보 변경</button><br><br><br>
			<button type="button" class="btn btn-secondary btn-rounded btn-fw" onclick="location.href='/mypage/withdrawal'">회원 탈퇴</button><br><br><br>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>