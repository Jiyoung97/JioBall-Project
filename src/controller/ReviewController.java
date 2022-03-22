package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Matching;
import dto.Review;
import service.face.MatchingService;
import service.face.ReviewService;
import service.impl.MatchingServiceImpl;
import service.impl.ReviewServiceImpl;

@WebServlet("/myteam/matching/review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스객체
	private ReviewService reviewService = new ReviewServiceImpl();
	private MatchingService matchingService = new MatchingServiceImpl();

	Review review = new Review();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myteam/matching/review [GET]");
		
		//리뷰작성할 매칭정보 객체 생성, 모집번호 객체에 저장
		Matching matching = new Matching();
		
		matching.setInviteNo((matchingService.getInviteNo(req)).getInviteNo());
		review.setInviteNo(Integer.parseInt(req.getParameter("inviteNo")));
		review.setJoinNo(Integer.parseInt(req.getParameter("joinNo")));
		
		//모집번호로 매칭내역 조회
		matching = matchingService.getMatching(matching.getInviteNo());
		
		System.out.println("리뷰 쓸 매칭 : " + matching);
		 
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("matching", matching);
		
		// /myteam/matching/review 으로 뷰 지정 후 포워드
		req.getRequestDispatcher("/WEB-INF/views/myteam/myteam_reviewWrite.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		System.out.println("[리뷰컨트롤러] inviteNo:" + review.getInviteNo());
		System.out.println("[리뷰컨트롤러] joinNo:" + review.getJoinNo());
		//작성글 삽입
		reviewService.writeReview(review, req);
		
		// /myteam/myteam_reviewSuccess 로 뷰 지정 후 포워드
		req.getRequestDispatcher("/WEB-INF/views/myteam/myteam_reviewSuccess.jsp").forward(req, resp);
				
	}
	
}
