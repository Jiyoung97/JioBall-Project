package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Support;
import service.face.SupportService;
import service.impl.SupportServiceImpl;
import util.Paging;

@WebServlet("/manager/support")
public class SupportManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SupportService supportService = new SupportServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerNo") == null) {
			
			System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/manager/login");
			
		} else {
			
			//검색어 받아오기
			String search = req.getParameter("search");
			
			//유형받아오기
			String type = req.getParameter("type");
			
			Paging paging = supportService.getSupportPaging(req, search, type);
			
			List<Support> list = supportService.getSupportlist(paging, search, type);
			
			req.setAttribute("supportList", list);
			req.setAttribute("supportPaging", paging);
			
			req.getRequestDispatcher("/WEB-INF/views/manager/supportManageList.jsp").forward(req, resp);
		}
	}

}
