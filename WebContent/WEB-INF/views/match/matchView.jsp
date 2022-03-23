
<%@page import="dto.GroundInfo"%>
<%@page import="dto.Match"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% Match match = (Match)request.getAttribute("match");%>
    <% GroundInfo ground = (GroundInfo)request.getAttribute("ground");%>

<%@ include file="../../layout/header.jsp" %>

<div class="page-header">

	<h2 class="page-title">모집</h2>
</div>

<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card" style="width: 1600px;">
			<div class="card-body">
			
			
				<div style=" ">
				
				<div style=" width: 1200px; margin: 0 auto; ">
				<table class="table text-secondary text-center">
				
				<tr>
				<td>제목</td><td colspan="3"><%=match.getInviteTitle() %></td>
				</tr>
				<tr>
				<td>팀이름</td><td><%=match.getTeamName() %></td>
				<td>경기일</td><td><%=match.getPlayDate().substring(0,11) %></td>
				</tr>

				<tr>
				<td>지역</td>
				<td><%=match.getPlayLocal() %></td>
				<td>모집상태</td>
				<td><% if(match.getMatchingProgressType() == 1) { %>모집중
					<% } else if(match.getMatchingProgressType() == 2) { %>모집완료
					<%	} %>
				</td>
				</tr>


				<tr>
 				<td>종목</td>
 				<td><%if(match.getPlayType()==1) {%>풋살<%}else{ %>축구<%} %></td> 
				<td>인원수</td>
				<td><%	if(match.getPlayPerson() == 1) { %>5:5
					<% 	} else if(match.getPlayPerson() == 2 ) { %>6:6
					<% 	} else if(match.getPlayPerson() == 3 ) { %>11:11
					<%	} %>
				</td>
				</tr>

				

				<tr><td colspan="4">내용</td></tr>
				<tr><td colspan="4"><%=match.getInviteComment()%></td></tr>

				</table>
				</div>
				<br>
				
				<div class="text-center">
				<%if(match.getMatchingProgressType() == 1){ %>
				<button class="btn btn-sm btn-primary" onclick="location.href='/match/join?matchNo=<%=match.getInviteNo()%>'">참가하기</button>
				<%} %>
				</div>
			
				




			
			
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>