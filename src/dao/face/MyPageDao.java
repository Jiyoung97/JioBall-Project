package dao.face;

import java.sql.Connection;

import dto.UserInfo;

public interface MyPageDao {
	
	public UserInfo selectUserInfo(Connection conn, int userNo);
	
	public int updateUserInfo(Connection conn, UserInfo userParam);
	
	public int deleteUserInfo(Connection conn, int userNo);
	
	public int deleteTeamInfo(Connection conn, int userNo);

}
