package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.TeamInfo;
import dto.UserInfo;

public interface LoginService {
	
	public UserInfo getUserParam(HttpServletRequest req);
	
	public TeamInfo getTeamParam(HttpServletRequest req);
	
	public int login(UserInfo userParam);
	
	public int kakaoLogin(UserInfo userParam);
	
	public String findUserId(UserInfo userParam);

	public UserInfo findUserPw(UserInfo userParam);

	public void sendMail(UserInfo userParam);
	
	public int checkId(String userId);
	
	public int checkName(String teamName);

	public boolean join(UserInfo userParam, TeamInfo teamParam);
}
