package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/login/join/checkid")
public class JoinCheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/login/join/checkid [GET]");
		
		resp.setContentType("application/json;charset=utf-8");
				
		LoginService loginService = new LoginServiceImpl();
		
		String userId = req.getParameter("userid").toLowerCase();
		System.out.println("UserInfo [userId=" + userId + "]");
		
		int result = loginService.checkId(userId);
		
		if(result==0) {
			System.out.println("Double Check [SUCCESS]");
		} else {
			System.out.println("Double Check [FAIL]");
		}
		
		PrintWriter out = resp.getWriter();
			
		out.print(result);
		
	}

}
