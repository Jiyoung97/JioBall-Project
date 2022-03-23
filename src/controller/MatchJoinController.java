package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Match;
import service.face.MatchService;
import service.impl.MatchServiceImpl;


@WebServlet("/match/join")
public class MatchJoinController extends HttpServlet {
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
			
			Match match =  matchService.getJoinInsertInfo(req);
			
			matchService.matchJoin(match);
			
			resp.sendRedirect("/main");
		}
    }

}
