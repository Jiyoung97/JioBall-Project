<%@page import="dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% List<Notice> list = (List)request.getAttribute("list");%>
    

<%@ include file="../../layout/header.jsp" %>
<div class="page-header">

	<h2 class="page-title">공지사항</h2>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">
			
			<div class="text-center" style="margin: 0 auto; width: 1200px">




<!-- 검색기능 -->
<div align="right" style="margin: 10px 0;">
<form method="get" action="/notice/list">
	<table>
	<tr>
	<td>
	<input type="text" placeholder="검색어 입력" name="searchText" class="form-control text-secondary" style="width: 200px" >
	</td>
	<td>
	<button type="submit" class="btn btn-sm btn-primary" style="height: 38px;">검색</button>
	</td>
	</tr>
</table>
</form>
</div>

<!-- 게시판 -->
<div id="info">
<table class="table text-secondary" >
<tr>
	<th width="10%;">NO</th>
	<th width="70%">제목</th>
	<th width="15%;">작성 일자</th>
</tr>
<% for(int i=0; i<list.size(); i++){ %>
<tr>
	<td><%= list.get(i).getNoticeRownum()%></td>
	<td><a class="text-secondary" href="/notice/view?no=<%= list.get(i).getNoticeNo()%>"><%= list.get(i).getNoticeTitle()%></a></td>
	<td><%= (list.get(i).getNoticeDate()).substring(0,11)%></td>
</tr>
<%} %>
</table>

</div>
<br>

<%@ include file="../../layout/noticePaging.jsp" %>
          	
          	
</div>
			
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>