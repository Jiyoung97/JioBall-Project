package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Support;
import dto.SupportFile;
import service.face.SupportService;
import service.impl.SupportServiceImpl;

@WebServlet("/support/view")
public class SupportViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SupportService supportService = new SupportServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			Support support = supportService.getSupportNo(req);
			
			Support supportView = supportService.getSupportView(support);
			
			req.setAttribute("supportView", supportView);
			
			SupportFile supportFile = supportService.getSupportFileView(support);
			
			req.setAttribute("supportFile", supportFile);
			req.getRequestDispatcher("/WEB-INF/views/service/supportView.jsp").forward(req, resp);
		
	}

}
