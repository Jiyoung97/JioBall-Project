package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserInfo;
import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/login/findpw")
public class FindPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/findpw [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/login/findPw.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/findpw [POST]");
		
		req.setCharacterEncoding("UTF-8");
		
		LoginService loginService = new LoginServiceImpl();
		
		UserInfo userParam = loginService.getUserParam(req);
		System.out.println(userParam);
		
		loginService.findUserPw(userParam);
		System.out.println("UserInfo [userPw=" + userParam.getUserPw() + "]");
		
		Boolean result = false;
		
		if(userParam.getUserPw() != null) {
			
			System.out.println("User Exist [SUCCESS]");
			loginService.sendMail(userParam);
			System.out.println("Send Mail [SUCCESS]");
			result = true;
			
		} else {
			
			System.out.println("User No Exist [FAIL]");
			System.out.println("Send Mail [FAIL]");
			result = false;
		}
		
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("/WEB-INF/views/login/findPwResult.jsp").forward(req, resp);
		
	}

}
