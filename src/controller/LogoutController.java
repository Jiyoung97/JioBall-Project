package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/login/logout [GET]");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("teamno") != null) {

			System.out.println("UserInfo [teamNo=" + session.getAttribute("teamno") + "]");
			session.invalidate();
			System.out.println("Disconnect [LOGOUT]");
			
		}
				
		resp.sendRedirect("/login/login");
		
	}

}
