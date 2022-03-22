package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.ManagerInfo;

public interface ManagerLoginService {
	
	public ManagerInfo getManagerParam(HttpServletRequest req);
	
	public int login(ManagerInfo managerParam);

}
