<%@page import="dto.Support"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Support> list = (List)request.getAttribute("supportList");%>
<%@ include file="../../layout/headerManager.jsp" %>

<div class="page-header">

	<h3 class="page-title">문의 게시판</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body">
			
			<div class="text-center" style=" margin: 0 auto; width: 1200px">
			<div style="float: left; margin: 10px 0;">
<form method="get" action="/manager/support">
<table>
	<tr>
	<td>
	<select class="form-control text-secondary" name="type">
		<option>전체</option>
		<option value="qna">질문</option>
		<option value="team">팀</option>
		<option value="ground">구장</option>
		<option value="support">문의</option>
	</select>
	</td>
	<td>
	<input type="text" placeholder="검색어 입력" name="search" class="form-control text-secondary" style="width: 200px" >
	</td>
	<td>
	<button type="submit" class="btn btn-sm btn-primary" style="height: 38px;">검색</button>
	</td>
	</tr>
</table>
</form>
</div>



<!-- 게시판 div -->
<div>
<table class="table text-secondary">
<tr class="active">
	<th width="5%">NO</th>
	<th width="10%">문의 유형</th>
	<th width="10%">작성자</th>
	<th>제목</th>
	<th width="10%">작성 일자</th>
	<th width="10%"></th>
</tr>
<% for(int i=0; i<list.size(); i++){ %>
<tr>
	<td><%= list.get(i).getSupportRownum() %></td>
	<td><%= list.get(i).getSupportType() %></td>
	<td><%= list.get(i).getSupportWriter() %></td>
	<td><a class="text-secondary" href="/manager/support/view?supportno=<%= list.get(i).getSupportNo()%>"><%= list.get(i).getSupportTitle()%></a></td>
	<td><%= (list.get(i).getSupportDate()).substring(0,11)%></td>
	<td><% if(list.get(i).getSupportState()==0) {%><a class="text-secondary" href="/manager/support/view?supportno=<%= list.get(i).getSupportNo()%>">답변하기</a>
	<%} else{ %><%} %></td>
</tr>
<%} %>
</table>
<br>
</div>
<%@ include file="../../layout/supportManagePaging.jsp" %>

</div>
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footerManager.jsp" %>