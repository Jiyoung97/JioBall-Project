package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.UserInfo;

public interface MyPageService {
	
	public UserInfo getUserParam(HttpServletRequest req);
	
	public UserInfo showOriginInfo(int userNo);
	
	public boolean alterUserInfo(UserInfo userParam);

	public boolean selectUserPw(HttpServletRequest req);
	
	public boolean withdrawalUser(HttpServletRequest req);
	
}
