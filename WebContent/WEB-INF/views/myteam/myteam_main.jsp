<%@page import="java.util.Calendar"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="dto.Matching"%>
<%@page import="java.util.List"%>
<%@page import="dto.TeamInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%	TeamInfo myTeam = (TeamInfo) request.getAttribute("myTeam"); %>
<%  List<Matching> recentMatchingList = (List) request.getAttribute("recentMatchingList");%>

<%@ include file="../../layout/header.jsp" %>

<title>JIOBALL - MYTEAM</title>

<script type="text/javascript">

//팀 정보 수정 버튼
function openPopTeamEdit() {
	
	var width = 450;
	var height = 600;
	var top = (window.screen.height/4);
	var left = (window.screen.width/2) - (width/2);
	
	var popup = window.open('/myteam/edit',"MYTEAM_Edit","width="+width+",height="+height+",top="+top+",left="+left);

}

$(document).ready(function() {
	
	//매칭내역 [더보기] 버튼
	$("#btn_matchingMore").click(function() {
		location.href="/myteam/matching";
	});
	
});


</script>

<style type="text/css">

#team_name {
	font-size: 25px;
	font-weight: bold;
}

/* 버튼 위치, 색상 조정 */
#btn_teamEdit, #btn_matchingMore {
	float: right;
}

</style>

<div class="page-header">
	<h3 class="page-title">MY TEAM</h3>
</div>


<div class="row">
	<div class="col-lg-12 grid-margin">
		<div class="card">
			<div class="card-body">
	
			<div class="text-center" style=" margin: 0 auto; width: 1200px">
				
					<table class="table text-secondary">
					
					<tr>
						<td>
							<!-- 팀 이름 -->
							<div class="team_profile" id="team_name">
							<%=myTeam.getTeamName() %>
							</div>
						</td>
						
						<td>	
							<!-- 매너 평점 -->
							<div class="team_profile" id="team_mannerscore_title">매너점수</div>
						</td>
						
						<td>	
							<!-- 매너 평점 점수 -->
							<div class="team_profile" id="team_mannerscore"><%=myTeam.getTeamManner() %>점</div>
						</td>
						
						<td colspan="2">
							<!-- 매너 평점 별 -->
							<%	if( myTeam.getTeamManner() == 0 && myTeam.getTeamManner() < 1 ){ %>
							<div class="team_profile" id="team_mannerstar">☆☆☆☆☆</div>
							<%	} else if( myTeam.getTeamManner() >= 1 && myTeam.getTeamManner() < 2){ %>
							<div class="team_profile" id="team_mannerstar">★☆☆☆☆</div>
							<%	} else if( myTeam.getTeamManner() >= 2 && myTeam.getTeamManner() < 3 ){ %>
							<div class="team_profile" id="team_mannerstar">★★☆☆☆</div>
							<%	} else if( myTeam.getTeamManner() >= 3 && myTeam.getTeamManner() < 4 ){ %>
							<div class="team_profile" id="team_mannerstar">★★★☆☆</div>
							<%	} else if( myTeam.getTeamManner() >= 4 && myTeam.getTeamManner() < 5 ){ %>
							<div class="team_profile" id="team_mannerstar">★★★★☆</div>
							<%	} else if( myTeam.getTeamManner() == 5 ){ %>
							<div class="team_profile" id="team_mannerstar">★★★★★</div>
							<%	} %>
						</td>
					</tr>
						
					<tr>
						<td>
							<!-- 팀 소개 -->
							<div class="team_profile" id="team_introduce"><%=myTeam.getTeamIntroduce() %></div>
						</td>
						
						<td>
							<!-- 팀 선호 종목 -->
							<div class="team_profile" id="team_preferType">
								<i class="mdi mdi-soccer text-warning" ></i>
							<%	if( myTeam.getPlayTypeNo() == 1) { %>
							축구
							<%	} else if( myTeam.getPlayTypeNo() == 2) { %>
							풋볼
							<%	} %>
							</div>
						</td>
						
						<td>
							<!-- 팀 선호 지역 -->
							<div class="team_profile" id="team_preferLocal">
								<i class="mdi mdi-map-marker  text-warning" ></i>
							<%	if( myTeam.getPlayLocalNo() == 1) { %>
							김해
							<%	} else if( myTeam.getPlayLocalNo() == 2) { %>
							부산
							<%	} else if( myTeam.getPlayLocalNo() == 3) { %>
							진주
							<%	} else if( myTeam.getPlayLocalNo() == 4) { %>
							양산
							<%	} else if( myTeam.getPlayLocalNo() == 5) { %>
							창원
							<%	} %>
							</div>
						</td>
						
						<td>
							<!-- 팀 성별 -->
							<div class="team_profile" id="team_gender">
								<i class="mdi mdi-human-male-female text-warning"></i>
							<%	if( myTeam.getTeamGender() == 1) { %>
							남성
							<%	} else if( myTeam.getTeamGender() == 2) { %>
							여성
							<%	} else if( myTeam.getTeamGender() == 3) { %>
							혼성
							<%	} %>
							</div>
						</td>
						
						<td>
							<!-- 팀 유니폼 색상 -->
							<div class="team_profile" id="team_uniformColor">
								<i class="mdi mdi-hanger text-warning"></i>
								<%=myTeam.getTeamUniform() %>
							</div>
						</td>
					</tr>
					
					<tr>			
							<!-- 전적 및 승률 -->
						<td>
							<div class="team_profile" id="team_record">전적 및 승률</div>
						</td>
						<td>
							총 경기 수 : <%=myTeam.getTeamMatches() %>번
						</td>
						<td>
							이긴 경기 : <%=myTeam.getTeamVictories() %>번
						</td>
						<td>
							진 경기 : <%=myTeam.getTeamDefeats() %>번
						</td>
						<td>
							승률 : 
							<%	if(myTeam.getTeamMatches() == 0) {%>
								0 %
							<%	} else { %>
								<%=(double)myTeam.getTeamVictories()/(double)myTeam.getTeamMatches()*100 %>%
							<%	} %>
						</td>
					</tr>
				</table>	
				
				<br>
				
				<button type="button" class="btn btn-sm btn-primary" id="btn_teamEdit" onclick="openPopTeamEdit()">수정하기</button>

				<!---------------------------------------------------------------  -->
				<br><br><br><br><br>
				
				<!-- 매칭 내역 -->
				<table class="table table-condensed text-secondary">
				<caption style="caption-side: top;" class="text-secondary">최근 매칭 내역</caption>
				<tr>
					<th>경기날짜</th>
					<th>구장명</th>
					<th>모집팀</th>
					<th>참가팀</th>
					<th>진행상황</th>
					<th>경기결과</th>
				</tr>
				<%	for(int i=0; i<recentMatchingList.size(); i++) { %>
					<% //현재시간에서 시분초밀리초 제거
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					Date now = cal.getTime(); //현재날짜
					%>
					<% Date playDate = recentMatchingList.get(i).getPlayDate(); %>
					<%	if( !(now.after(playDate) && recentMatchingList.get(i).getMatchingProgressNo() == 1)) {%>
					<tr>
						<td><%=recentMatchingList.get(i).getPlayDate() %></td>
						<td><%=recentMatchingList.get(i).getGroundName() %></td>
						<td><%=recentMatchingList.get(i).getInviteTeamName() %></td>
						<td><%=recentMatchingList.get(i).getJoinTeamName() %></td>
						<td><%	if( recentMatchingList.get(i).getMatchingProgressNo() == 1) { %>
							모집중
							<%	} else if( recentMatchingList.get(i).getMatchingProgressNo() == 2) { %>
							모집마감
							<%	} else if( recentMatchingList.get(i).getMatchingProgressNo() == 3) { %>
							경기완료
							<%	} %>
						</td>
						<td>
							<%	if( (recentMatchingList.get(i).getPlayResultInvite() == 0 || recentMatchingList.get(i).getPlayResultJoin() == 0)  && (now.before(playDate) || now.equals(playDate) ) ) { %>
							경기 대기 중
							<%	} else if( (recentMatchingList.get(i).getPlayResultInvite() == 0 || recentMatchingList.get(i).getPlayResultJoin() == 0) && now.after(playDate) ) { %>
							리뷰 대기 중
							<%	} else if( recentMatchingList.get(i).getPlayResultInvite() == 1 && recentMatchingList.get(i).getPlayResultJoin() == 1) { %>
							<%=recentMatchingList.get(i).getInviteTeamName() %> 팀 승
							<%	} else if( recentMatchingList.get(i).getPlayResultInvite() == 2 && recentMatchingList.get(i).getPlayResultJoin() == 2) { %>
							<%=recentMatchingList.get(i).getJoinTeamName() %> 팀 승
							<%	} %>
						</td>
					</tr>
					<%	} %>
				<%	} %>
				</table>
				<button type="button" class="btn btn-sm btn-primary" id="btn_matchingMore"
					onclick="location.href='/myteam/matching'">더보기</button>

				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../../layout/footer.jsp" %>







