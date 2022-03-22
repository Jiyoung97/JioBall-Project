package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/manager/logout")
public class ManagerLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/manager/logout [GET]");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerno") != null) {

		System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerno") + "]");
		session.invalidate();
		System.out.println("Disconnect [LOGOUT]");
		
		}
				
		resp.sendRedirect("/manager/login");
		
	}

}
