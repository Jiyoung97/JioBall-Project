package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.GroundInfo;
import service.face.MatchService;
import service.impl.MatchServiceImpl;

@WebServlet("/match/write")
public class MatchWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MatchService matchService = new MatchServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		if(session.getAttribute("teamNo") == null) {

			System.out.println("TeamInfo [teamNo=" + session.getAttribute("teamNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/login/login");

		} else {
			
			List<GroundInfo> list = matchService.getGroundNameList();
			
			req.setAttribute("list", list);
			
			req.getRequestDispatcher("/WEB-INF/views/match/matchWrite.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		matchService.matchWrite(req);
		
		resp.sendRedirect("/main");
	}

}
