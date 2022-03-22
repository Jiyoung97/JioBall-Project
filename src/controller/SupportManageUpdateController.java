package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.SupportService;
import service.impl.SupportServiceImpl;

@WebServlet("/manager/support/update")
public class SupportManageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SupportService supportService = new SupportServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerNo") == null) {
			
			System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/manager/login");
			
		} else {
			
			req.setCharacterEncoding("UTF-8");
			supportService.answerWrite(req);
			
			resp.sendRedirect("/manager/support");
		}
	}

}
