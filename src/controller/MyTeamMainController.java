package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Matching;
import dto.TeamInfo;
import service.face.MatchingService;
import service.face.MyTeamService;
import service.impl.MatchingServiceImpl;
import service.impl.MyTeamServiceImpl;

@WebServlet("/myteam/main")
public class MyTeamMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스객체
	private MyTeamService myTeamService = new MyTeamServiceImpl();
	private MatchingService matchingService = new MatchingServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myteam/main [GET]");
	
		//내 팀 정보 저장할 객체 생성
		TeamInfo myTeam = new TeamInfo();
		System.out.println( "[MYTEAM MAIN] 세션 teamNo : " + (Integer)req.getSession().getAttribute("teamNo"));
		// 세션의 팀번호로 내 팀 프로필 가져오기
		myTeam.setTeamNo((Integer)req.getSession().getAttribute("teamNo"));
		myTeam = myTeamService.getMyTeamInfo( myTeam.getTeamNo() );
		
		System.out.println("myteamNo : " + myTeam.getTeamNo());
		System.out.println("myteam : " + myTeam);
		 
		
		// 세션의 팀번호로 내 팀 최근 매칭내역 3건 가져오기
		List<Matching> recentMatchingList = matchingService.getRecentMatching( myTeam.getTeamNo() );
		
		System.out.println("최근매칭내역 : " + recentMatchingList);

		//모집팀이름, 참가팀이름 가져오기
		for(int i=0; i<recentMatchingList.size(); i++) {
			if(recentMatchingList.get(i).getInviteTeamNo() == myTeam.getTeamNo()) {
				//내 팀번호가 매칭의 모집팀 번호일 경우
				recentMatchingList.get(i).setInviteTeamName( (matchingService.getInviteTeamName(myTeam.getTeamNo())).getInviteTeamName() );
				recentMatchingList.get(i).setJoinTeamName( (matchingService.getJoinTeamName(recentMatchingList.get(i).getJoinTeamNo())).getJoinTeamName());
			} else if(recentMatchingList.get(i).getJoinTeamNo() == myTeam.getTeamNo()){
				//내 팀번호가 매칭의 참가팀 번호일 경우
				recentMatchingList.get(i).setJoinTeamName( (matchingService.getJoinTeamName(myTeam.getTeamNo())).getJoinTeamName() );
				recentMatchingList.get(i).setInviteTeamName( (matchingService.getInviteTeamName(recentMatchingList.get(i).getInviteTeamNo())).getInviteTeamName() );
			}
		}
			
		//---------------------------------------------------------------
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("myTeam", myTeam);
		req.setAttribute("recentMatchingList", recentMatchingList);
		
		// /myteam/main로 뷰 지정 후 포워드
		req.getRequestDispatcher("/WEB-INF/views/myteam/myteam_main.jsp").forward(req, resp);
				
	}
}
