package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MatchingDao;
import dto.Matching;

public class MatchingDaoImpl implements MatchingDao{

	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null;	//결과 집합 객체
	
	@Override
	public List<Matching> selectRecentMatching(Connection conn, int teamNo) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum, V.* FROM (";
		sql += "  SELECT * FROM V_MATCHING";
		sql += "  WHERE 모집팀번호 = ? OR 참가팀번호 = ?";
		sql += "  ORDER BY 경기일자 DESC";
		sql += " )V";
		sql += ")";
		sql += "WHERE rownum BETWEEN 1 AND 3";
		
		//--- 조회 결과를 저장할 객체 ---
		List<Matching> recentMatching = new ArrayList<>();
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, teamNo);
			ps.setInt(2, teamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			rs = ps.executeQuery();
			
			//--- 조회 결과 처리 ---
			while( rs.next() ) {
				Matching matching = new Matching(); //결과값 저장 객체
				
				matching.setInviteNo( rs.getInt("모집번호") );
				matching.setInviteDate( rs.getString("모집일자") );
				matching.setPlayDate( rs.getDate("경기일자") );
				matching.setInviteTeamNo( rs.getInt("모집팀번호") );
				matching.setJoinTeamNo( rs.getInt("참가팀번호") );
				matching.setGroundNo( rs.getInt("구장번호") );
				matching.setGroundName( rs.getString("구장이름") );
				matching.setJoinNo( rs.getInt("참가번호") );
				matching.setJoinDate( rs.getString("참가일자") );
				matching.setPlayResultInvite( rs.getInt("모집팀경기결과") );
				matching.setPlayResultJoin( rs.getInt("참가팀경기결과") );
				matching.setMatchingProgressNo( rs.getInt("매칭진행상황") );
 
				//리스트객체에 조회한 행 객체 저장
				recentMatching.add(matching);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps );
		}
		
		//--- 최종 조회 결과 반환 ---
		return recentMatching;
	}

	@Override
	public Matching selectInviteTeamNameByTeamNo(Connection conn, int teamNo) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT TEAM_NAME FROM TEAMINFO";
		sql += "  WHERE TEAM_NO = ?";
		
		//--- 조회 결과를 저장할 객체 ---
		Matching matching = new Matching();
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, teamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			rs = ps.executeQuery();
			
			//--- 조회 결과 처리 ---
			while( rs.next() ) {
				
				matching.setInviteTeamName( rs.getString("team_name") );

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps );
		}
		
		//--- 최종 조회 결과 반환 ---
		return matching;
		
	}
	
	@Override
	public Matching selectJoinTeamNameByTeamNo(Connection conn, int teamNo) {
		
		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT TEAM_NAME FROM TEAMINFO";
		sql += "  WHERE TEAM_NO = ?";
		
		//--- 조회 결과를 저장할 객체 ---
		Matching matching = new Matching();
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, teamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			rs = ps.executeQuery();
			
			//--- 조회 결과 처리 ---
			while( rs.next() ) {
				
				matching.setJoinTeamName( rs.getString("team_name") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps );
		}
		
		//--- 최종 조회 결과 반환 ---
		return matching;
		
	}

	@Override
	public List<Matching> selectAllMatching(Connection conn, int teamNo) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT * FROM V_MATCHING";
		sql += " WHERE 모집팀번호 = ? OR 참가팀번호 = ?";
		sql += " ORDER BY 경기일자 DESC";
		
		//--- 조회 결과를 저장할 객체 ---
		List<Matching> allMatching = new ArrayList<>();
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, teamNo);
			ps.setInt(2, teamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			rs = ps.executeQuery();
			
			//--- 조회 결과 처리 ---
			while( rs.next() ) {
				Matching matching = new Matching(); //결과값 저장 객체
				
				matching.setInviteNo( rs.getInt("모집번호") );
				matching.setInviteDate( rs.getString("모집일자") );
				matching.setPlayDate( rs.getDate("경기일자") );
				matching.setInviteTeamNo( rs.getInt("모집팀번호") );
				matching.setJoinTeamNo( rs.getInt("참가팀번호") );
				matching.setGroundNo( rs.getInt("구장번호") );
				matching.setGroundName( rs.getString("구장이름") );
				matching.setJoinNo( rs.getInt("참가번호") );
				matching.setJoinDate( rs.getString("참가일자") );
				matching.setPlayResultInvite( rs.getInt("모집팀경기결과") );
				matching.setPlayResultJoin( rs.getInt("참가팀경기결과") );
				matching.setMatchingProgressNo( rs.getInt("매칭진행상황") );
 
				//리스트객체에 조회한 행 객체 저장
				allMatching.add(matching);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps );
		}
		
		//--- 최종 조회 결과 반환 ---
		return allMatching;
	}

	@Override
	public Matching selectMatching(Connection conn, int inviteNo) {
		
		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT * FROM V_MATCHING";
		sql += " WHERE 모집번호 = ?";
		
		//--- 조회 결과를 저장할 객체 ---
		Matching matching = new Matching();
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, inviteNo);
			
			//--- SQL 수행 및 결과 저장 ---
			rs = ps.executeQuery();
			
			//--- 조회 결과 처리 ---
			while( rs.next() ) {
				
				matching.setInviteNo( rs.getInt("모집번호") );
				matching.setInviteDate( rs.getString("모집일자") );
				matching.setPlayDate( rs.getDate("경기일자") );
				matching.setInviteTeamNo( rs.getInt("모집팀번호") );
				matching.setJoinTeamNo( rs.getInt("참가팀번호") );
				matching.setGroundNo( rs.getInt("구장번호") );
				matching.setGroundName( rs.getString("구장이름") );
				matching.setJoinNo( rs.getInt("참가번호") );
				matching.setJoinDate( rs.getString("참가일자") );
				matching.setPlayResultInvite( rs.getInt("모집팀경기결과") );
				matching.setPlayResultJoin( rs.getInt("참가팀경기결과") );
				matching.setMatchingProgressNo( rs.getInt("매칭진행상황") );
 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps );
		}
		
		//--- 최종 조회 결과 반환 ---
		return matching;
	}



}
