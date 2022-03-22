<%@page import="dto.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Notice> list = (List)request.getAttribute("list");%>
<script type="text/javascript">

function button_event(e){
	if(confirm("정말 삭제하시겠습니까?") == true){
		location.href="/manage/notice/delete?no="+ e;
	}else{
		return;
	}
}

</script>
<%@ include file="../../layout/headerManager.jsp" %>

<div class="page-header">

	<h3 class="page-title">공지사항</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body" >
			
			<div class="text-center" style=" margin: 0 auto; width: 1200px">
			
<div style="float: left; margin: 10px 0;">
<form method="get" action="/manage/notice">
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

<div style="float: right; margin: 10px 0;">
<button class="btn btn-sm btn-primary" style="height: 38px;" onclick="location.href='/manage/notice/write'">작성하기</button>
</div>



<!-- 게시판 div -->
<div>
<table class="table text-center text-secondary">
<tr>
	<th width="5%">NO</th>
	<th>제목</th>
	<th width="15%">작성 일자</th>
	<th width="5%"></th>
</tr>
<% for(int i=0; i<list.size(); i++){ %>
<tr>
	<td><%= list.get(i).getNoticeRownum()%></td>
	<td><a class="text-secondary" href="/manage/notice/view?no=<%= list.get(i).getNoticeNo()%>"><%= list.get(i).getNoticeTitle()%></a></td>
	<td><%= (list.get(i).getNoticeDate()).substring(0,11)%></td>
	<td><button class ="text-secondary" style=" border: 0; outline: 0; background-color: #191c24;" onclick="button_event(<%= list.get(i).getNoticeNo()%>)">삭제</button></td>
</tr>
<%} %>
</table>

</div><!-- 게시판 div 끝 -->

<br>
<%@ include file="../../layout/noticeManagePaging.jsp" %>
		
			
			</div>
			
			
			
			
			
			
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footerManager.jsp" %>