package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ManagerUserDao;
import dto.TeamInfo;
import dto.UserInfo;
import util.Paging;

public class ManagerUserDaoImpl implements ManagerUserDao{
	private PreparedStatement ps = null; 
	private ResultSet rs = null;
	
	@Override
	public int selectCntUser(Connection conn) {
		
		String sql = "";
		sql += "SELECT count(*) AS cnt FROM userinfo";
		
		int userCnt = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				userCnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userCnt;
	}
	
	@Override
	public int selectCntUSer(Connection conn, String teamName) {
		
		String sql = "";
		sql += "SELECT count(*) AS cnt FROM teaminfo";
		sql += "WHERE team_name = ?";
		
		int userCnt = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, teamName);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				userCnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return userCnt;
	}
	
	@Override
	public List<UserInfo> selectUserList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, u.* FROM (";
		sql += "		SELECT user_id, user_name, user_gender, user_birth, user_phone FROM userinfo u, teaminfo t";
		sql += "		WHERE u.user_no = t.user_no";	
		sql += "		ORDER BY team_no DESC";
		sql += " 		) u";
		sql += "	)";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<UserInfo> userList = new ArrayList<UserInfo>();
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				UserInfo u = new UserInfo();
				u.setUserId(rs.getString("user_id"));
				u.setUserName(rs.getString("user_name"));
				u.setUserGender(rs.getInt("user_gender"));
				u.setUserBirth(rs.getString("user_birth"));
				u.setUserPhone(rs.getString("user_phone"));
				userList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return userList;

	}

	@Override
	public List<TeamInfo> selectTeamList(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, t.* FROM (";
		sql += "		SELECT team_name, team_no FROM teaminfo";
		sql += " 		ORDER BY team_no DESC";
		sql += " 		) t";
		sql += " 	)";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<TeamInfo> teamList = new ArrayList<>();

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();

			while( rs.next() ) {
				TeamInfo t = new TeamInfo();
				t.setTeamNo(rs.getInt("team_no"));
				t.setTeamName(rs.getString("team_name"));
				teamList.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return teamList;
	}

	@Override
	public int selectUserNo(Connection conn, int teamNo) {
		
		String sql ="";
		sql += "SELECT user_no FROM teaminfo";
		sql += " WHERE team_no = ?";
		
		int result = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, teamNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getInt("user_no");
				
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
	public int deleteUser(Connection conn, int userNo) {
		
		String sql = "";
		sql += "DELETE userinfo";
		sql += " WHERE user_no = ?";

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
	public int deleteTeam(Connection conn, int teamNo) {
		String sql = "";
		sql += "DELETE teaminfo";
		sql += " WHERE team_no = ?";

		int result = 0;

		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, teamNo);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;
		
	}

	@Override
	public List<UserInfo> selectSearchUser(Connection conn, Paging paging, String teamName) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, u.* FROM (";
		sql += "		SELECT user_id, user_name, user_gender, user_birth, user_phone FROM userinfo u, teaminfo t";
		sql += "		WHERE (u.user_no = t.user_no) and (t.team_name LIKE ?)";	
		sql += "		ORDER BY team_no DESC";
		sql += " 		) u";
		sql += "	)";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<UserInfo> userList = new ArrayList<UserInfo>();
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%'+teamName+'%');
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				UserInfo u = new UserInfo();
				u.setUserId(rs.getString("user_id"));
				u.setUserName(rs.getString("user_name"));
				u.setUserGender(rs.getInt("user_gender"));
				u.setUserBirth(rs.getString("user_birth"));
				u.setUserPhone(rs.getString("user_phone"));
				userList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return userList;

	}

	@Override
	public List<TeamInfo> selectSearchTeam(Connection conn, Paging paging, String teamName) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, t.* FROM (";
		sql += "		SELECT team_name, team_no FROM teaminfo";
		sql += "		WHERE team_name LIKE ?";
		sql += " 		ORDER BY team_no DESC";
		sql += " 		) t";
		sql += " 	)";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<TeamInfo> teamList = new ArrayList<>();

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + teamName + '%');
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();

			while( rs.next() ) {
				TeamInfo t = new TeamInfo();
				t.setTeamNo(rs.getInt("team_no"));
				t.setTeamName(rs.getString("team_name"));
				teamList.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return teamList;
	}

}
