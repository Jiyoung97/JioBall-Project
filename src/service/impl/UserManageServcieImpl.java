package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.UserManageDao;
import dao.impl.UserManageDaoImpl;
import dto.TeamInfo;
import dto.UserInfo;
import service.face.UserManageService;
import util.Paging;

public class UserManageServcieImpl implements UserManageService{
	
	private UserManageDao userManageDao = new UserManageDaoImpl();
	private Connection conn = JDBCTemplate.getConnection();

	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curpage");
		
		int curPage = 0;
		
		if( param != null && !"".equals( param ) ) {
			
			curPage = Integer.parseInt(param);
			
		} else {
			
			
		}

		int totalCount = userManageDao.selectCntUser(conn);
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;

	}
	
	@Override
	public Paging getSearchPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curpage");
		
		int curPage = 0;
		
		if( param != null && !"".equals( param ) ) {
			
			curPage = Integer.parseInt(param);
			
		} else {
			
			
		}

		int totalCount = userManageDao.selectCntUser(conn);
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;

	}

	@Override
	public List<UserInfo> getUserList(Paging paging) {

		return userManageDao.selectUserList(conn, paging);
		
	}

	@Override
	public List<TeamInfo> getTeamList(Paging paging) {

		return userManageDao.selectTeamList(conn, paging);
		
	}

	@Override
	public boolean delete(int teamNo) {

		int userNo = userManageDao.selectUserNo(conn, teamNo);
		
		int result =  userManageDao.deleteTeam(conn, teamNo);
		result += userManageDao.deleteUser(conn, userNo);
		
		if( result == 2 ) { 
			JDBCTemplate.commit(conn);
			return true;
		} else { 
			JDBCTemplate.rollback(conn);
			return false;
		}
		
	}

	@Override
	public List<UserInfo> searchUser(Paging paging,String teamName) {
		
		return userManageDao.selectSearchUser(conn, paging, teamName);
	}

	@Override
	public List<TeamInfo> searchTeam(Paging paging, String teamName) {

		return userManageDao.selectSearchTeam(conn, paging, teamName);
	}

}
