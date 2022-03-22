package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.TeamInfo;
import dto.UserInfo;
import util.Paging;

public interface UserManageDao {
	
	public int selectCntUser(Connection conn);
	
	public int selectCntUSer(Connection conn, String teamName);
		
	public List<UserInfo> selectUserList(Connection conn, Paging paging);
	
	public List<TeamInfo> selectTeamList(Connection conn, Paging paging);

	public int selectUserNo(Connection conn, int teamNo);

	public int deleteUser(Connection conn, int userNo);

	public int deleteTeam(Connection conn, int teamNo);
	
	public List<UserInfo> selectSearchUser(Connection conn, Paging paging, String teamName);
	
	public List<TeamInfo> selectSearchTeam(Connection conn, Paging paging, String teamName);
	
}
