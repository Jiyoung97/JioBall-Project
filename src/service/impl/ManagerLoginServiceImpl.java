package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ManagerLoginDao;
import dao.impl.ManagerLoginDaoImpl;
import dto.ManagerInfo;
import service.face.ManagerLoginService;

public class ManagerLoginServiceImpl implements ManagerLoginService{

	@Override
	public ManagerInfo getManagerParam(HttpServletRequest req) {
		
		ManagerInfo managerParam = new ManagerInfo();
		
		managerParam.setManagerId(req.getParameter("managerid").toLowerCase());
		managerParam.setManagerPw(req.getParameter("managerpw"));

		return managerParam;
		
	}

	@Override
	public int login(ManagerInfo managerParam) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ManagerLoginDao managerLoginDao = new ManagerLoginDaoImpl();
		
		int managerNo = managerLoginDao.selectManager(conn, managerParam);
		
		return managerNo;
		
	}

}
