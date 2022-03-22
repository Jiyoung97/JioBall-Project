package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.UserManageService;
import service.impl.UserManageServcieImpl;

@WebServlet("/manager/userdelete")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerNo") == null) {
			
			System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/manager/login");
			
		} else {
			
			System.out.println("/manager/userdelete [GET]");
			
			int teamNo = (Integer.parseInt(req.getParameter("teamno")));
			
			System.out.println("TeamInfo [teamno=" + teamNo + "]");
			
			UserManageService userManageService = new UserManageServcieImpl();
			
			boolean result = userManageService.delete(teamNo);
			
			if( result ) {
				
				System.out.println("Delete User [SUCCESS]");
				req.getRequestDispatcher("/manager/userlist").forward(req, resp);;
				
			} else {
				
				System.out.println("Delete User [FAIL]");
				resp.sendRedirect("/WEB-INF/layout/error500.html");
				
			}
			
		}
		
	}

}
