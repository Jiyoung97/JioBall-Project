package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/user/logout [GET]");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("userno") != null) {

			System.out.println("UserInfo [userNo=" + session.getAttribute("userno")+ "]");
			session.invalidate();
			System.out.println("Disconnect [LOGOUT]");
			
		}
				
		resp.sendRedirect("/login/login");
		
	}

}
