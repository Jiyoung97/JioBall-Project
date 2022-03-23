package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.MyPageDao;
import dao.impl.MyPageDaoImpl;
import dto.UserInfo;
import service.face.MyPageService;

public class MyPageServiceImp implements MyPageService{
	
	@Override
	public UserInfo getUserParam(HttpServletRequest req) {
		
		UserInfo userParam = new UserInfo();
		
		if(req.getParameter("userid")!=null) {
			userParam.setUserId(req.getParameter("userid").toLowerCase());
		} else {
			userParam.setUserId(req.getParameter("userid"));
		}
		userParam.setUserPw(req.getParameter("userpw"));
		userParam.setUserName(req.getParameter("username"));
		
		String userGender = req.getParameter("usergender");
				
		if( userGender == null) {
			
			userGender = "0";
			userParam.setUserGender(Integer.parseInt(userGender));
			
		} else {
			
			userParam.setUserGender(Integer.parseInt(userGender));
		}
			
		userParam.setUserBirth(req.getParameter("userbirth"));
		userParam.setUserTelecom(req.getParameter("usertelecom"));
		String userPhone = req.getParameter("userphone");
		String userPhone1 = req.getParameter("userphone1");
		
		userParam.setUserPhone(userPhone1 + userPhone);
		
		return userParam;
		
	}
	
	public UserInfo showOriginInfo(int userNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MyPageDao myPageDao = new MyPageDaoImpl();
		
		return myPageDao.selectUserInfo(conn, userNo);
		
	}

	@Override
	public boolean alterUserInfo(UserInfo userParam) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MyPageDao myPageDao = new MyPageDaoImpl();
		
		int result = myPageDao.updateUserInfo(conn, userParam);
		
		if( result == 1 ) { 
			JDBCTemplate.commit(conn);
			return true;
		} else { 
			JDBCTemplate.rollback(conn);
			return false;
		}
		
	}

	@Override
	public boolean selectUserPw(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		System.out.println("UserInfo [userno=" + session.getAttribute("userNo").toString() + "]");
		
		int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
		
		String userPw = req.getParameter("userPw");
		
		System.out.println("UserInfo [userpw=" + userPw + "]");
		
		Connection conn = JDBCTemplate.getConnection();
		
		MyPageDao myPageDao = new MyPageDaoImpl();
		
		UserInfo userParam = myPageDao.selectUserInfo(conn, userNo);
		
		String result = userParam.getUserPw();
		
		if(userPw.equals(result)) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public boolean withdrawalUser(HttpServletRequest req) {
		
		HttpSession session = req.getSession();

		int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
		
		Connection conn = JDBCTemplate.getConnection();
		
		MyPageDao myPageDao = new MyPageDaoImpl();
		
		int result = myPageDao.deleteTeamInfo(conn, userNo);
		
		result += myPageDao.deleteUserInfo(conn, userNo);
		
		if( result == 2 ) { 
			JDBCTemplate.commit(conn);
			return true;
		} else { 
			JDBCTemplate.rollback(conn);
			return false;
		}
		
	}

}
