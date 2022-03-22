	package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ManagerInfo;
import service.face.ManagerLoginService;
import service.impl.ManagerLoginServiceImpl;

@WebServlet("/manager/login")
public class ManagerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/manager/login [GET]");

		req.getRequestDispatcher("/WEB-INF/views/manager/login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/manager/login [POST]");
		
		ManagerLoginService managerLoginService = new ManagerLoginServiceImpl()	;
		
		ManagerInfo managerParam = managerLoginService.getManagerParam(req);
		
		System.out.println(managerParam);
		
		int managerNo = managerLoginService.login(managerParam);
		
		System.out.println("ManagerInfo [managerNo=" + managerNo + "]");
		
		if(managerNo !=0) {
			
			System.out.println("Allow Access [LOGIN]");
			
			HttpSession session = req.getSession();
			session.setAttribute("managerno", managerNo);
			
			req.getRequestDispatcher("/WEB-INF/views/manager/home.jsp").forward(req, resp);
			
		} else {
			
			System.out.println("No Access [FAIL");
			
			req.getRequestDispatcher("/WEB-INF/views/manager/loginFail.jsp").forward(req, resp);

		}

	}

}
