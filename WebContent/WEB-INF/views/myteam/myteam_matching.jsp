<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.Matching"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  List<Matching> matchingList = (List) request.getAttribute("matchingList");%>

<%@ include file="../../layout/header.jsp" %>

<script type="text/javascript">

// [리뷰작성] 버튼
function openPopReview(inviteNo, joinNo) {
	
	var width = 450;
	var height = 350;
	var top = (window.screen.height/4);
	var left = (window.screen.width/2) - (width/2);
	
	var popup = window.open('/myteam/matching/review?inviteNo='+inviteNo+'&joinNo='+joinNo,"REVIEW","width="+width+",height="+height+",top="+top+",left="+left);
	
}
</script>


<div class="page-header">

	<h3 class="page-title">전체 매칭 내역</h3>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body">
				<div class="text-center" style=" margin: 0 auto; width: 1200px">
							
				<table class="table table-condensed text-secondary">
				<tr>
					<th>경기날짜</th>
					<th>구장명</th>
					<th>모집팀</th>
					<th>참가팀</th>
					<th>진행상황</th>
					<th>경기결과</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<%	for(int i=0; i<matchingList.size(); i++) { %>
					<% //현재시간에서 시분초밀리초 제거
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					Date now = cal.getTime(); //현재날짜
					%>
					<% Date playDate = matchingList.get(i).getPlayDate(); %>
					<%	if( !(now.after(playDate) && matchingList.get(i).getMatchingProgressNo() == 1)) {%>
				<tr>
					<td><%=matchingList.get(i).getPlayDate() %></td>
					<td><%=matchingList.get(i).getGroundName() %></td>
					<td><%=matchingList.get(i).getInviteTeamName() %></td>
					<td><%=matchingList.get(i).getJoinTeamName() %></td>
					<td><%	if( matchingList.get(i).getMatchingProgressNo() == 1) { %>
							모집중
							<%	} else if( matchingList.get(i).getMatchingProgressNo() == 2) { %>
							모집마감
							<%	} else if( matchingList.get(i).getMatchingProgressNo() == 3) { %>
							경기완료
							<%	} %>
					</td>
					<td>
						<%	if( (matchingList.get(i).getPlayResultInvite() == 0 || matchingList.get(i).getPlayResultJoin() == 0)  && (now.before(playDate) || now.equals(playDate) ) ) { %>
						경기 대기 중
						<%	} else if( (matchingList.get(i).getPlayResultInvite() == 0 || matchingList.get(i).getPlayResultJoin() == 0) && now.after(playDate) ) { %>
						리뷰 대기 중
						<%	} else if( matchingList.get(i).getPlayResultInvite() == 1 && matchingList.get(i).getPlayResultJoin() == 1) { %>
						<%=matchingList.get(i).getInviteTeamName() %> 팀 승
						<%	} else if( matchingList.get(i).getPlayResultInvite() == 2 && matchingList.get(i).getPlayResultJoin() == 2) { %>
						<%=matchingList.get(i).getJoinTeamName() %> 팀 승
						<%	} %> 
					</td>
					<td>
						<%	if( matchingList.get(i).getMatchingProgressNo() == 3 && (matchingList.get(i).getPlayResultInvite() == 0 || matchingList.get(i).getPlayResultJoin() == 0) ) { %>
							<button type="button" class="btn btn-sm btn-primary" id="btn_writeReview" onclick="openPopReview(<%=matchingList.get(i).getInviteNo() %>, <%=matchingList.get(i).getJoinNo() %>)">리뷰작성</button>
						<%	} %>
					</td>
				</tr>
					<%	} %>
				<%	} %>
				</table>
			
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>