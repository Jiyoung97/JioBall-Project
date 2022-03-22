package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.TeamInfo;
import dto.UserInfo;
import service.face.UserManageService;
import service.impl.UserManageServcieImpl;
import util.Paging;

@WebServlet("/manager/usersearch")
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("/manager/usersearch [GET]");
	
		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerNo") == null) {
			
			System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/manager/login");
			
		} else {
			
			String teamName = req.getParameter("teamname");
			System.out.println("TeamInfo [teamName=" + teamName + "]");
			
			UserManageService userManageService = new UserManageServcieImpl();
			
			Paging paging = userManageService.getSearchPaging(req);
			System.out.println(paging);
			
			System.out.println("Search User [SUCCESS]");
			
			List<UserInfo> userList = userManageService.searchUser(paging, teamName);  
			
			List<TeamInfo> teamList = userManageService.searchTeam(paging, teamName);
			
			req.setAttribute("paging", paging);
			req.setAttribute("userlist", userList);
			req.setAttribute("teamlist", teamList);
			
			System.out.println("Show UserList [SUCCESS]");
			
			req.getRequestDispatcher("/WEB-INF/views/manager/userList.jsp").forward(req, resp);
			
		}
	}


}
