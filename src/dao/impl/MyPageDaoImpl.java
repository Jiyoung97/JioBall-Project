package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MyPageDao;
import dto.UserInfo;

public class MyPageDaoImpl implements MyPageDao{
	
	private PreparedStatement ps = null; 
	private ResultSet rs = null; 
	
	@Override
	public UserInfo selectUserInfo(Connection conn, int userNo) {
		
		String sql = "";
		sql += "SELECT user_id, user_pw, user_name, user_gender, user_birth, user_telecom, user_phone FROM userinfo";
		sql += " WHERE user_no = ?";
		
		UserInfo userInfo = new UserInfo(); 
		
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			rs = ps.executeQuery();

			if(rs.next()) {
				
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setUserPw(rs.getString("user_pw"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setUserGender(rs.getInt("user_gender"));
				userInfo.setUserBirth(rs.getString("user_birth"));
				userInfo.setUserTelecom(rs.getString("user_telecom"));
				userInfo.setUserPhone(rs.getString("user_phone"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userInfo;
	}

	@Override
	public int updateUserInfo(Connection conn, UserInfo userParam) {

		String sql = "";
		sql += "UPDATE userinfo SET user_name = ?, user_telecom = ?, user_phone = ?";
		sql += " WHERE user_no = ?";
		
		int result = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, userParam.getUserName());
			ps.setString(2, userParam.getUserTelecom());
			ps.setString(3, userParam.getUserPhone());
			ps.setInt(4, userParam.getUserNo());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;
		
	}

	@Override
	public int deleteUserInfo(Connection conn, int userNo) {
		
		String sql = "";
		sql += "DELETE userinfo WHERE user_no = ?";
		
		int result = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;

	}
	
	@Override
	public int deleteTeamInfo(Connection conn, int userNo) {
		
		String sql = "";
		sql += "DELETE teaminfo WHERE user_no = ?";
		
		int result = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
		
	}

}
