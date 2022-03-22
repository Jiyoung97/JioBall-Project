package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.TeamInfo;
import dto.UserInfo;
import util.Paging;

public interface ManagerUserService {
	
	public Paging getPaging(HttpServletRequest req);
	
	public Paging getSearchPaging(HttpServletRequest req);

	public List<UserInfo> getUserList(Paging paging);
	
	public List<TeamInfo> getTeamList(Paging paging);
	
	public boolean delete(int teamNo);
	
	public List<UserInfo> searchUser(Paging paging, String teamName);
	
	public List<TeamInfo> searchTeam(Paging paging, String teamName);
	
}
