package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MatchingDao;
import dao.impl.MatchingDaoImpl;
import dto.Matching;
import dto.UserTeam;
import service.face.MatchingService;

public class MatchingServiceImpl implements MatchingService {

	//DAO객체
	private MatchingDao matchingDao = new MatchingDaoImpl();

	@Override
	public List<Matching> getRecentMatching(int teamNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return matchingDao.selectRecentMatching(conn, teamNo);	
	}

	@Override
	public Matching getInviteTeamName(int teamNo) {

		Connection conn = JDBCTemplate.getConnection();
		
		return matchingDao.selectInviteTeamNameByTeamNo(conn, teamNo);
	}

	@Override
	public Matching getJoinTeamName(int teamNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return matchingDao.selectJoinTeamNameByTeamNo(conn, teamNo);
	}

	@Override
	public List<Matching> getAllMatching(int teamNo) {

		Connection conn = JDBCTemplate.getConnection();
		
		return matchingDao.selectAllMatching(conn, teamNo);
	}

	@Override
	public Matching getInviteNo(HttpServletRequest req) {
		
		//전달파라미터 inviteNo를 저장할 DTO객체 생성
		Matching inviteNo = new Matching();
		
		String param = req.getParameter("inviteNo");
		if( param != null && !"".equals( param ) ) {
			inviteNo.setInviteNo( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] MatchingService getInviteNo() - InviteNo값이 null이거나 비어있음");
		}
		
		System.out.println("[MatchingService] inviteNo : " + inviteNo);

		return inviteNo;
	}


	@Override
	public UserTeam getUserInfo(UserTeam userTeam) {

		Connection conn = JDBCTemplate.getConnection();
		
		return matchingDao.selectUserTeam(conn, userTeam);
	}


	@Override
	public Matching getMatching(int inviteNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//매칭정보 저장할 객체 생성 및 매칭정보 저장
		Matching matching = matchingDao.selectMatching(conn, inviteNo);
		//모집팀이름 저장
		matching.setInviteTeamName( (matchingDao.selectInviteTeamNameByTeamNo(conn, matching.getInviteTeamNo())).getInviteTeamName() );
		//참가팀이름 저장
		matching.setJoinTeamName( (matchingDao.selectJoinTeamNameByTeamNo(conn, matching.getJoinTeamNo())).getJoinTeamName() );
		
		System.out.println("[MatchingService] matching : " + matching);
		return matching;
	}


}
