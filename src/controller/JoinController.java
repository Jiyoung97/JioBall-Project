package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TeamInfo;
import dto.UserInfo;
import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/login/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/join [GET]");
		
		
		req.getRequestDispatcher("/WEB-INF/views/login/joinForm.jsp").forward(req, resp);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/join [POST]");
		
		req.setCharacterEncoding("UTF-8");
		
		LoginService loginService = new LoginServiceImpl();
		
		UserInfo userParam = loginService.getUserParam(req);
		String phone = req.getParameter("userphone1");
		userParam.setUserPhone(phone + userParam.getUserPhone()); 
		System.out.println(userParam);
		
		TeamInfo teamParam = loginService.getTeamParam(req);
		System.out.println(teamParam);
		
		boolean result = loginService.join(userParam,teamParam);
		
		if(result) {
			
			System.out.println("Create User [JOIN]");
			req.getRequestDispatcher("/WEB-INF/views/login/joinResult.jsp").forward(req, resp);
		
		} else {
			
			System.out.println("Create User [FAIL]");
			req.getRequestDispatcher("/WEB-INF/layout/error500.html").forward(req, resp);
		
		}
		
	}

}
