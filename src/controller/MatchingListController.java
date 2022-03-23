package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Matching;
import service.face.MatchingService;
import service.impl.MatchingServiceImpl;


@WebServlet("/myteam/matching")
public class MatchingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스객체
	private MatchingService matchingService = new MatchingServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myteam/matching [GET]");
		
		HttpSession session = req.getSession();

		if(session.getAttribute("teamNo") == null) {

			System.out.println("TeamInfo [teamNo=" + session.getAttribute("teamNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/login/login");

		} else {
			
			//전체 매칭내역 목록 조회 - MatchingService이용
			List<Matching> matchingList = matchingService.getAllMatching( (Integer)req.getSession().getAttribute("teamNo") );
			
			//모집팀이름, 참가팀이름 가져오기
			for(int i=0; i<matchingList.size(); i++) {
				if(matchingList.get(i).getInviteTeamNo() == (Integer)req.getSession().getAttribute("teamNo") ) {
					//내 팀번호가 매칭의 모집팀 번호일 경우
					matchingList.get(i).setInviteTeamName( (matchingService.getInviteTeamName((Integer)req.getSession().getAttribute("teamNo"))).getInviteTeamName() );
					matchingList.get(i).setJoinTeamName( (matchingService.getJoinTeamName(matchingList.get(i).getJoinTeamNo())).getJoinTeamName());
				} else if(matchingList.get(i).getJoinTeamNo() == (Integer)req.getSession().getAttribute("teamNo")){
					//내 팀번호가 매칭의 참가팀 번호일 경우
					matchingList.get(i).setJoinTeamName( (matchingService.getJoinTeamName((Integer)req.getSession().getAttribute("teamNo"))).getJoinTeamName() );
					matchingList.get(i).setInviteTeamName( (matchingService.getInviteTeamName(matchingList.get(i).getInviteTeamNo())).getInviteTeamName() );
				}
			}
			
			//조회결과 MODEL값 전달 - req.setAttribute
			req.setAttribute("matchingList", matchingList);
			
			// /myteam/matching 으로 뷰 지정 후 포워드
			req.getRequestDispatcher("/WEB-INF/views/myteam/myteam_matching.jsp").forward(req, resp);
			
			
		}

	}
}
