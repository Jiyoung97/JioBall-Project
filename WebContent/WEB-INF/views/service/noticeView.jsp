<%@page import="dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% Notice content = (Notice)request.getAttribute("content");%>

<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h2 class="page-title">공지사항</h2>
</div>

<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">
			
			
<div style="width: 800px; margin: 0 auto;">



<!-- 공지사항 제목 -->
<div style=" border-top: 2px solid white; border-bottom: 1px solid; height: 50px; width: 800px; display: table-cell; vertical-align: middle;">

	<div style="float: left;"><b style="font-size:1.3em"><%= content.getNoticeTitle() %></b></div>

	<div style="float: right;"><%=(content.getNoticeDate()).substring(0,11)%></div>
	
</div>
<br>

<div style="height: 600px; border-top: 2px solid white; border-bottom: 1px solid; background-color: #343a40;">
<p><%= content.getNoticeContent() %></p>
</div>


<br>
<div align="center">
<button style="width: 80px; height: 50px" class="btn btn-sm btn-primary" onclick="location.href='/notice/list'">목록</button>
</div>

</div>
			
			
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>