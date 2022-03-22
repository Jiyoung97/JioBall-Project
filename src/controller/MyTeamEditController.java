package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TeamInfo;
import service.face.MyTeamService;
import service.impl.MyTeamServiceImpl;

@WebServlet("/myteam/edit")
public class MyTeamEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스객체
	private MyTeamService myTeamService = new MyTeamServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myteam/edit [GET]");
	
		//내 팀 정보 저장할 객체 생성
		TeamInfo myTeam = new TeamInfo();
		
		// 세션의 팀번호로 내 팀 프로필 가져오기
		myTeam.setTeamNo((Integer)req.getSession().getAttribute("teamNo"));
		myTeamService.getMyTeamInfo( myTeam.getTeamNo()  );

		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("myTeam", myTeam);
				
		// /myteam/edit 로 뷰 지정 후 포워드
		req.getRequestDispatcher("/WEB-INF/views/myteam/myteam_edit.jsp").forward(req, resp);
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myteam/edit [POST]");
	
		//내 팀 정보 저장할 객체 생성
		TeamInfo myTeam = new TeamInfo();
		
		// 세션의 팀번호로 내 팀 프로필 가져오기
		myTeam.setTeamNo((Integer)req.getSession().getAttribute("teamNo"));
		
		myTeamService.editMyTeamInfo(req, myTeam.getTeamNo());
		
		// /myteam/myteam_editSuccess 로 뷰 지정 후 포워드
		req.getRequestDispatcher("/WEB-INF/views/myteam/myteam_editSuccess.jsp").forward(req, resp);
		
	}
	
}
