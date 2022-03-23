
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


<form method="get" action="/main">
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
			<%	if( !(list.get(i).getMatchingProgressType() == 3)) {%>
			<tr>
			<td><%=list.get(i).getMatchRnum()%></td>
			<td><a href="/match/view?matchNo=<%=list.get(i).getInviteNo()%>&groundNo=<%=list.get(i).getGroundNo()%>">
			<%=list.get(i).getInviteTitle()%></a></td>
			<td><%=list.get(i).getPlayDate().substring(0,11)%></td>
			<td><%=list.get(i).getPlayLocal()%></td>
			<td><%	if( list.get(i).getPlayPerson() == 1){ %>
				5:5
				<%	} else if( list.get(i).getPlayPerson() == 2) { %>
				6:6
				<%	} else if( list.get(i).getPlayPerson() == 3) { %>
				11:11
				<%	} %>
			</td>
			<td><% if(list.get(i).getMatchingProgressType() == 1) { %>
				모집중
				<%	} else if( list.get(i).getMatchingProgressType() ==2) { %>
				모집완료
				<%	} %>
			</td>
			</tr>
			<%} %>
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