<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Paging paging = (Paging)request.getAttribute("paging"); %>

<div>
<ul class="pagination" style="justify-content: center;">
   <% if( paging.getCurPage() !=1){ %>
   <li class="page-link"><a class="text-secondary" href="<%=request.getContextPath() %>/ground/groundlist">&lt;</a></li>
   <%} %>
   
   
   <% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++){ %>
      <%if (paging.getCurPage()==i){ %>
      
      <li class="page-link"><a class="text-secondary" href="<%=request.getContextPath() %>/ground/groundlist?curPage=<%=i %>"><%=i %></a>
      <%} else{%>
      <li class="page-link"><a class="text-secondary" href="<%=request.getContextPath() %>/ground/groundlist?curPage=<%=i %>"><%=i %></a>
      <%} %>
   <%} %>
   
   <% if( paging.getCurPage() < paging.getTotalPage()){ %>
   <li class="page-link"><a class="text-secondary" href="<%=request.getContextPath() %>/ground/groundlist?curPage=<%=paging.getCurPage()+1 %>">&gt;</a></li>
   <%} %>


</ul>
</div>