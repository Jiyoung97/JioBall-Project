<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Paging paging = (Paging)request.getAttribute("paging"); %>

<div class="template-demo">
<div class ="btn-group" role="group" aria-label="Basic example">

<ul class="pagination">
	<% if( paging.getCurPage() !=1){ %>
	<li ><a class="text-secondary" href="<%=request.getContextPath()%>/match/list?curPage=<%=paging.getCurPage()-1%>">&lt;</a></li>
	<%} %>
	
	
	<% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++){ %>
		<%if (paging.getCurPage()==i){ %>
		
		<li class="active"><a class="text-secondary" href="<%=request.getContextPath()%>/match/list?curPage=<%=i%>"><%=i %></a></li>
		<%} else{%>
		<li ><a class="text-secondary" href="<%=request.getContextPath()%>/match/list?curPage=<%=i%>"><%=i %></a></li>
		<%} %>
	<%} %>
	
	<% if( paging.getCurPage() < paging.getTotalPage()){ %>
	<li ><a class="text-secondary" href="<%=request.getContextPath()%>/match/list?curPage=<%=paging.getCurPage()+1%>">&gt;</a></li>
	<%} %>


</ul>
</div>
</div>