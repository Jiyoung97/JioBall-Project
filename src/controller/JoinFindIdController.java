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

@WebServlet("/login/findid")
public class JoinFindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/findid [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/login/findId.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/findid [POST]");
		
		req.setCharacterEncoding("UTF-8");
		
		LoginService loginService = new LoginServiceImpl();
		
		UserInfo userParam = loginService.getUserParam(req);
		System.out.println(userParam);
		
		String result = loginService.findUserId(userParam);
		System.out.println("UserInfo [userId=" + result + "]");
		
		if(result != null) {
			
			System.out.println("User Exist [SUCCESS]");
			
		} else {
			
			System.out.println("User No Exist[FAIL]");
		}
		
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("/WEB-INF/views/login/findIdResult.jsp").forward(req, resp);
		
	}

}
