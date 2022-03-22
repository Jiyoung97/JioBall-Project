package dao.face;

import java.sql.Connection;

import dto.TeamInfo;
import dto.UserInfo;

public interface LoginDao {
	
	public int selectUser(Connection conn, UserInfo userParam);

	public int selectKakaoUser(Connection conn, UserInfo userParam);
	
	public String selectUserId(Connection conn, UserInfo userParam);
	
	public UserInfo selectUserPw(Connection conn, UserInfo userParam);
	
	public String selectAlreadyId(Connection conn, String userId);
	
	public String selectAlreadyName(Connection conn, String teamName);
	
	public int selectUserNo(Connection conn);
	
	public int selectTeamNo(Connection conn);
	
	public int insert(Connection conn, UserInfo userParam);
	
	public int insert(Connection conn, TeamInfo teamParam);

	
	
}
