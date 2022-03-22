
<%@page import="dto.Match"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% List<Match> list = (List)request.getAttribute("list");%>


<%@ include file="../../layout/header.jsp"%>
<div class="page-header">

	<h3 class="page-title">모집 게시판</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">


<div class="text-center" style="margin: 0 auto; width: 1200px">
				
<div style="float: left; margin: 10px 0;">


<form method="get" action="/match/list">
	<table>
	<tr>
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


<div style="float: right; margin: 10px 0;">
<button class="btn btn-sm btn-primary" style="height: 38px;" onclick="location.href='/match/write'">작성하기</button>
</div>

<div>
			<table class="table text-secondary">
			<tr>
			<th>NO</th>
			<th>제목</th>
			<th>경기일</th>
			<th>경기 지역</th>
			<th>인원수</th>
			<th>모집 여부</th>
			</tr>



			<% for (int i = 0; i < list.size(); i++) { %>
			<tr>
			<td><%=list.get(i).getMatchRnum()%></td>
			<td><%=list.get(i).getInviteTitle()%></td>
			<td><%=list.get(i).getPlayDate()%></td>
			<td><%=list.get(i).getPlayLocal()%></td>
			<td><%=list.get(i).getPlayPerson()%>명</td>
			<td><%if(list.get(i).getJoinNo() != 0 ) {%>
				모집완료<%}else{ %>모집중<%} %>
							
                     		</td>
						</tr>
						<%} %>
					</table>
</div>
<br>
<%@ include file="../../layout/matchPaging.jsp"%>
			
					
					</div>

				</div>
			</div>
		</div>
	</div>

	

	<%@ include file="../../layout/footer.jsp"%>