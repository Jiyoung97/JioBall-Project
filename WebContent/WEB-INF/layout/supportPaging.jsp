<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Paging paging = (Paging)request.getAttribute("supportPaging"); %>

<div>
<ul class="pagination" style="justify-content: center;">
	<% if( paging.getCurPage() !=1){ %>
	<li class="page-link"><a class="text-secondary" href="<%=request.getContextPath()%>/support/list?curPage=<%=paging.getCurPage()-1%>">&lt;</a></li>
	<%} %>
	
	
	<% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++){ %>
		<%if (paging.getCurPage()==i){ %>
		
		<li class="page-link"><a class="text-secondary" href="<%=request.getContextPath()%>/support/list?curPage=<%=i%>"><%=i %></a></li>
		<%} else{%>
		<li class="page-link"><a class="text-secondary" href="<%=request.getContextPath()%>/support/list?curPage=<%=i%>"><%=i %></a></li>
		<%} %>
	<%} %>
	
	<% if( paging.getCurPage() < paging.getTotalPage()){ %>
	<li class="page-link" ><a class="text-secondary" href="<%=request.getContextPath()%>/support/list?curPage=<%=paging.getCurPage()+1%>">&gt;</a></li>
	<%} %>


</ul>
</div>