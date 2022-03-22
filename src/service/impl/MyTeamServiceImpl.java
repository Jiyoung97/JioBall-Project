package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MyTeamDao;
import dao.impl.MyTeamDaoImpl;
import dto.TeamInfo;
import service.face.MyTeamService;

public class MyTeamServiceImpl implements MyTeamService {

	//DAO객체
	private MyTeamDao myTeamDao = new MyTeamDaoImpl();

	@Override
	public TeamInfo getMyTeamInfo(int teamNo) {

		Connection conn = JDBCTemplate.getConnection();

		TeamInfo myTeam = myTeamDao.selectTeamByTeamNo(conn, teamNo);
		System.out.println("TeamInfo: " +  myTeam);
		return 	myTeam;	
	}

	@Override
	public void editMyTeamInfo(HttpServletRequest req, int teamNo) {

		try {
			//전달 파라미터에 대한 한글 인코딩 설정 (UTF-8)
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//새로운 팀 정보 DTO객체
		TeamInfo newMyTeamInfo = new TeamInfo();
		
		//팀 번호 저장
		newMyTeamInfo.setTeamNo(teamNo);
		
		//새로운 팀 정보 DTO객체에 저장
		newMyTeamInfo.setTeamName(req.getParameter("teamName"));
		newMyTeamInfo.setTeamIntroduce(req.getParameter("teamIntroduce"));
		newMyTeamInfo.setPlayTypeNo(Integer.parseInt(req.getParameter("playTypeNo")));
		newMyTeamInfo.setPlayLocalNo(Integer.parseInt(req.getParameter("playLocalNo")));
		newMyTeamInfo.setTeamGender(Integer.parseInt(req.getParameter("teamGender")));
		newMyTeamInfo.setTeamUniform(req.getParameter("teamUniform"));
			
		Connection conn = JDBCTemplate.getConnection();
		
		if( myTeamDao.updateTeamInfo(conn, newMyTeamInfo) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

}
