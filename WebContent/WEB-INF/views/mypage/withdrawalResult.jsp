<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String) request.getAttribute("msg"); %>
<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h3 class="page-title">회원 탈퇴</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body"><%= msg %></div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>