package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dto.TeamInfo;
import dto.UserInfo;

public class LoginDaoImpl implements LoginDao {

	private PreparedStatement ps = null; 
	private ResultSet rs = null; 

	@Override
	public int selectUser(Connection conn, UserInfo userParam) {

		String sql = "";
		sql += "SELECT u.user_no, user_name, team_no From userinfo u, teaminfo t";
		sql += " WHERE (u.user_no = t.user_no) AND (user_id = ?) AND (user_pw = ?)";
		
		int teamNo = 0;
		
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, userParam.getUserId());
			ps.setString(2, userParam.getUserPw());
			rs = ps.executeQuery();

			if(rs.next()) {
				
				userParam.setUserNo(Integer.parseInt(rs.getString("user_no")));
				userParam.setUserName(rs.getString("user_name"));
				teamNo = rs.getInt("team_no");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return teamNo;


	}

	@Override
	public String selectUserId(Connection conn, UserInfo userParam) {

		String sql = "";
		sql += "SELECT user_id FROM userinfo";
		sql += " WHERE (user_name = ?) and (user_telecom = ?) and (user_phone = ?)";

		String userId = null;

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, userParam.getUserName());
			ps.setString(2, userParam.getUserTelecom());
			ps.setString(3, userParam.getUserPhone());
			rs = ps.executeQuery();

			if(rs.next()) {
				userId = rs.getString("user_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return userId;

	}

	@Override
	public UserInfo selectUserPw(Connection conn, UserInfo userParam) {

		String sql = "";
		sql += "SELECT user_pw FROM userinfo";
		sql += " WHERE (user_id = ?) and (user_telecom = ?) and (user_phone = ?)";

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, userParam.getUserId());
			ps.setString(2, userParam.getUserTelecom());
			ps.setString(3, userParam.getUserPhone());
			rs = ps.executeQuery();

			if(rs.next()) {
				userParam.setUserPw(rs.getString("user_pw"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return userParam;

	}
	
	@Override
	public String selectAlreadyId(Connection conn, String userId) {
		
		String sql ="";
		sql += "SELECT user_id FROM userinfo";
		sql += " WHERE user_id = ?";
		
		String result = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getString("user_id");
				
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
		
		
		return result;
	}
	
	@Override
	public String selectAlreadyName(Connection conn, String teamName) {
		
		String sql ="";
		sql += "SELECT team_name FROM teaminfo";
		sql += " WHERE team_name = ?";
		
		String result = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, teamName);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getString("team_name");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return result;
	}

	@Override
	public int selectUserNo(Connection conn) {

		String sql = "";
		sql += "SELECT userinfo_seq.nextval AS next FROM dual";

		int userNo = 0; 

		try {

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()) {
				userNo = rs.getInt("next");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return userNo;
	}

	@Override
	public int selectTeamNo(Connection conn) {

		String sql = "";
		sql += "SELECT teaminfo_seq.nextval AS next FROM dual";

		int teamNo = 0; 

		try {

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			teamNo = rs.getInt("next");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return teamNo;
	}

	@Override
	public int insert(Connection conn, UserInfo userParam) {

		String sql = "";
		sql += "INSERT INTO userinfo ( user_no, user_id, user_pw, user_name, user_gender, user_birth, user_telecom, user_phone)";
		sql += " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

		int result = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, userParam.getUserNo());
			ps.setString(2, userParam.getUserId());
			ps.setString(3, userParam.getUserPw());
			ps.setString(4, userParam.getUserName());
			ps.setInt(5, userParam.getUserGender());
			ps.setString(6, userParam.getUserBirth());
			ps.setString(7, userParam.getUserTelecom());
			ps.setString(8, userParam.getUserPhone());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;

	}

	public int insert(Connection conn, TeamInfo teamParam) {

		String sql = "";
		sql += "INSERT INTO teaminfo ( user_no, team_no, team_name, team_gender, team_introduce, playlocal_no )";
		sql += " VALUES ( ?, ?, ?, ?, ?, ? )";

		int result = 0;

		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, teamParam.getUserNo());
			ps.setInt(2, teamParam.getTeamNo());
			ps.setString(3, teamParam.getTeamName());
			ps.setInt(4, teamParam.getTeamGender());
			ps.setString(5, teamParam.getTeamIntroduce());
			ps.setInt(6, teamParam.getPlayLocalNo());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;

	}

}



