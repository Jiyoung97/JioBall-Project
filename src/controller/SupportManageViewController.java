package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Support;
import dto.SupportFile;
import service.face.SupportService;
import service.impl.SupportServiceImpl;


@WebServlet("/manager/support/view")
public class SupportManageViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SupportService supportService = new SupportServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerNo") == null) {
			
			System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/manager/login");
			
		} else {
			
			Support support = supportService.getSupportNo(req);
			
			Support supportView = supportService.getSupportView(support);
			
			req.setAttribute("supportView", supportView);
			
			SupportFile supportFile = supportService.getSupportFileView(support);
			
			req.setAttribute("supportFile", supportFile);
			req.getRequestDispatcher("/WEB-INF/views/manager/supportManageView.jsp").forward(req, resp);
		}
	}

}
