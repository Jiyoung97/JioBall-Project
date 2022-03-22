<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	Paging paging = (Paging) request.getAttribute("paging"); %>

<div>

<ul class="pagination" style="justify-content: center;">
	
	<%-- 첫 페이지로 이동 --%>
	<%	if( paging.getCurPage() != 1 ) { %>
	<li class="page-link"><a class="text-secondary" href="<%=request.getContextPath() %>/manager/userlist">&larr;</a></li>
	<%	} %>


	
	<%-- 이전 페이징 리스트로 이동 --%>
	<%	if( paging.getStartPage() == 1 ) { %>
	<%	} else { %>
		<li class="page-link"><a class="text-secondary" href="/manager/userlist?curpage=<%=paging.getStartPage() - paging.getPageCount() %>">&laquo;</a></li>
	<%	} %>



	<%-- 이전 페이지로 이동 --%>
	<%	if( paging.getCurPage() > 1 ) { %>
	<li class="page-link">
		<a class="text-secondary" href="<%=request.getContextPath() %>/manager/userlist?curpage=<%=paging.getCurPage()-1 %>">
			&lt;
		</a>
	</li>
	<%	} %>



	<%-- 페이징 번호 리스트 --%> 
	<% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<% if( paging.getCurPage() == i ) { %>
			<li class="page-link">
				<a class="text-secondary" href="<%=request.getContextPath() %>/manager/userlist?curpage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } else { %>
			<li class="page-link">
				<a class="text-secondary" href="<%=request.getContextPath() %>/manager/userlist?curpage=<%=i %>">
					<%=i %>
				</a>
			</li>
		<% } %>
	<% } %>
	
	
	
	<%-- 다음 페이지로 이동 --%>
	<%	if( paging.getCurPage() < paging.getTotalPage() ) { %>
	<li class="page-link">
		<a class="text-secondary" href="<%=request.getContextPath() %>/manager/userlist?curpage=<%=paging.getCurPage()+1 %>">
			&gt;
		</a>
	</li>
	<%	} %>
	
	
	
	<%-- 다음 페이징 리스트로 이동 --%>
	<%	if( paging.getEndPage() == paging.getTotalPage() ) { %>
	<%	} else { %>
		<li class="page-link"><a class="text-secondary" href="manager/userlist?curpage=<%=paging.getStartPage() + paging.getPageCount() %>">&raquo;</a></li>
	<%	} %>
	
	
	
	<%-- 마지막 페이지로 이동 --%>
	<%	if( paging.getCurPage() != paging.getTotalPage() ) { %>
	<li class="page-link">
	<a id="lastpage" class="text-secondary" href="<%=request.getContextPath() %>/manager/userlist?curpage=<%=paging.getTotalPage() %>">
	 &rarr;
	</a>
	</li>
	<%	} %>
	
</ul>

</div>