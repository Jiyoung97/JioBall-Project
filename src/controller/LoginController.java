package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserInfo;
import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/login/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/login [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/login/join [POST]");
		
		LoginService loginService = new LoginServiceImpl();
		
		UserInfo userParam = loginService.getUserParam(req);
		System.out.println(userParam);
		
		int teamNo = loginService.login(userParam);
		System.out.println("UserInfo [userName=" + userParam.getUserName() + "]");
		System.out.println("TeamInfo [teamNo=" + teamNo + "]");
		
		if(teamNo != 0) {
			
			System.out.println("Allow Access [LOGIN]");
			
			HttpSession session = req.getSession();
			session.setAttribute("userNo", userParam.getUserNo());
			session.setAttribute("userId", userParam.getUserId());
			session.setAttribute("userPw", userParam.getUserPw());
			session.setAttribute("userName", userParam.getUserName());
			session.setAttribute("teamNo", teamNo);
			
			req.getRequestDispatcher("/WEB-INF/views/login/home.jsp").forward(req, resp);
			
		} else {
			
			System.out.println("No Access [FAIL]");
			
			req.getRequestDispatcher("/WEB-INF/views/login/loginFail.jsp").forward(req, resp);
		}
		
	}

}
