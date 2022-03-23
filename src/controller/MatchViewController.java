package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.GroundInfo;
import dto.Match;
import service.face.MatchService;
import service.impl.MatchServiceImpl;

@WebServlet("/match/view")
public class MatchViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MatchService matchService = new MatchServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Match matchNo = matchService.getMatchNo(req);
		GroundInfo groundNo = matchService.getGroundNo(req);
		
		Match match = matchService.getMatchView(matchNo);
		GroundInfo ground = matchService.getGroundInfo(groundNo);
		
		req.setAttribute("match", match);
		req.setAttribute("ground", ground);
		
		req.getRequestDispatcher("/WEB-INF/views/match/matchView.jsp").forward(req, resp);
		
		
	}

}
