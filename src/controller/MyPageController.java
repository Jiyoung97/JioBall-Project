package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/main")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/mypage/main [GET]");
		
		HttpSession session = req.getSession();

		if(session.getAttribute("teamNo") == null) {

			System.out.println("TeamInfo [teamNo=" + session.getAttribute("teamNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/login/login");

		} else {
			
			System.out.println("Show Mypage [SUCCESS]");
			req.getRequestDispatcher("/WEB-INF/views/mypage/main.jsp").forward(req, resp);
			
		}

	}
	
}
		
		
