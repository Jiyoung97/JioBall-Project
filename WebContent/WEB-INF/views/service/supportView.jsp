
<%@page import="dto.SupportFile"%>
<%@page import="dto.Support"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Support view = (Support)request.getAttribute("supportView");%>
<% SupportFile file = (SupportFile)request.getAttribute("supportFile");%>

<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h2 class="page-title">문의내역</h2>
</div>

<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">
			
			
<div style="width: 800px; margin: 0 auto;">




<div style=" border-top: 2px solid white; border-bottom: 1px solid; height: 50px; width: 800px; display: table-cell; vertical-align: middle;">

	<div style="float: left;"><p>
	<small><%= view.getSupportType() %></small>
	&nbsp;&nbsp;<b style="font-size:1.3em"><%= view.getSupportTitle() %></b></p></div>

	<div style="float: right;"><%=(view.getSupportDate()).substring(0,11)%></div>
	
</div>

<br>

<div style="height: 400px; border-top: 2px solid white; border-bottom: 1px solid; background-color: #343a40;">
<p><%= view.getSupportContent() %></p>
</div>
<br>
<div>
	<p><span class="mdi mdi-download"></span>&nbsp;&nbsp;
	<%if(file.getSupportFileOriginName()!=null){ %>
	<%= file.getSupportFileOriginName() %><%}else{ %>파일없음<%} %></p>
</div>

<div style=" border-top: 2px solid white; border-bottom: 1px solid; height: 50px; width: 800px; display: table-cell; vertical-align: middle;">

	<div style="float: left;"><b style="font-size:1.3em">답변</b></div>

	<div style="float: right;"><%if(view.getSupportAnswerDate() !=null){ %>
	<%= (view.getSupportAnswerDate()).substring(0,11) %>
	<%} else{ %><%} %></div>
	
</div>
<br>

<div style="height: 200px; border-top: 2px solid white; border-bottom: 1px solid; background-color: #343a40; overflow: auto;">
<p><%if(view.getSupportAnswer()!=null){ %>
	<%= view.getSupportAnswer() %>
	<%} else { %>답변 없음<%} %></p>
</div>




<br>
<div align="center">
<button style="width: 80px; height: 50px" class="btn btn-sm btn-primary" onclick="location.href='/support/list'">목록</button>
</div>

</div>
			
			
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>