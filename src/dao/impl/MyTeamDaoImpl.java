package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MyTeamDao;
import dto.TeamInfo;

public class MyTeamDaoImpl implements MyTeamDao{

	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null;	//결과 집합 객체
	
	@Override
	public TeamInfo selectTeamByTeamNo(Connection conn, int teamNo) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT * FROM teaminfo";
		sql += " WHERE team_no = ?";
		
		
		//--- 조회 결과를 저장할 객체 ---
		TeamInfo teamInfo = new TeamInfo();
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, teamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			rs = ps.executeQuery();
			
			//--- 조회 결과 처리 ---
			while( rs.next() ) {
				
				teamInfo.setTeamNo( rs.getInt("team_no") );
				teamInfo.setTeamName( rs.getString("team_name") );
				teamInfo.setTeamGender( rs.getInt("team_gender") );	//DB NUMBER로 수정
				teamInfo.setTeamIntroduce( rs.getString("team_introduce") );
				teamInfo.setTeamUniform( rs.getString("team_uniform") );
				teamInfo.setTeamManner( rs.getDouble("team_manner") );
				teamInfo.setTeamMatches( rs.getInt("team_matches") );
				teamInfo.setTeamVictories( rs.getInt("team_victories") );
				teamInfo.setTeamDefeats( rs.getInt("team_defeats") );
				teamInfo.setPlayTypeNo( rs.getInt("playtype_no") );
				teamInfo.setPlayLocalNo( rs.getInt("playlocal_no") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps );
		}
		
		//--- 최종 조회 결과 반환 ---
		return teamInfo;
	}

	@Override
	public int updateTeamInfo(Connection conn, TeamInfo newMyTeamInfo) {

		String sql = "";
		sql += "UPDATE teaminfo";
		sql += " SET team_name = ?,";
		sql += " 	team_introduce = ?,";
		sql += " 	playtype_no = ?,";
		sql += " 	playlocal_no = ?,";
		sql += " 	team_gender = ?,";
		sql += " 	team_uniform = ?";
		sql += " WHERE team_no = ?";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, newMyTeamInfo.getTeamName());
			ps.setString(2, newMyTeamInfo.getTeamIntroduce());
			ps.setInt(3, newMyTeamInfo.getPlayTypeNo());
			ps.setInt(4, newMyTeamInfo.getPlayLocalNo());
			ps.setInt(5, newMyTeamInfo.getTeamGender());
			ps.setString(6, newMyTeamInfo.getTeamUniform());
			ps.setInt(7, newMyTeamInfo.getTeamNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	
	}

}
