package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Match;
import service.face.MatchService;
import service.impl.MatchServiceImpl;
import util.Paging;

@WebServlet("/match/list")
public class MatchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MatchService matchService = new MatchServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//검색어 파라미터 받아오기
		String search = req.getParameter("search");
//		System.out.println(search);
		
		//페이징 객체 생성
		Paging paging = matchService.getPaging(req, search);
		
		//게시글 목록
		List<Match> matchList = matchService.getMatchList(paging,search);
		
		//전달
		req.setAttribute("paging", paging);
		req.setAttribute("list", matchList);
		req.getRequestDispatcher("/WEB-INF/views/match/matchList.jsp").forward(req, resp);
		
	}
	
	

}
