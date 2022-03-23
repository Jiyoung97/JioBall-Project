package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Support;
import service.face.SupportService;
import service.impl.SupportServiceImpl;
import util.Paging;


@WebServlet("/support/list")
public class SupportListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SupportService supportService = new SupportServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/support/list");
	
			//검색어 받아오기
			String search = req.getParameter("search");
			
			//유형받아오기
			String type = req.getParameter("type");
			
			Paging paging = supportService.getSupportPaging(req, search, type);
			
			List<Support> list = supportService.getSupportlist(paging, search, type);
			
			req.setAttribute("supportList", list);
			req.setAttribute("supportPaging", paging);
			
			req.getRequestDispatcher("/WEB-INF/views/service/supportList.jsp").forward(req, resp);

	}
	
	
}
