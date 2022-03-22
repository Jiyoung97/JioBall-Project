package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MatchingDao;
import dao.face.ReviewDao;
import dao.impl.MatchingDaoImpl;
import dao.impl.ReviewDaoImpl;
import dto.Matching;
import dto.Review;
import service.face.ReviewService;

public class ReviewServiceImpl implements ReviewService {

	//DAO객체
	private ReviewDao reviewDao = new ReviewDaoImpl();
	private MatchingDao matchingDao = new MatchingDaoImpl();

	@Override
	public void writeReview(Review review, HttpServletRequest req) {

		// 리뷰 정보 저장할 객체 생성
		Review updateReview = new Review();
		   
		updateReview.setInviteNo(review.getInviteNo());
		updateReview.setJoinNo(review.getJoinNo());
		updateReview.setReviewResult(Integer.parseInt(req.getParameter("reviewResult")));
		updateReview.setReviewManner(Double.parseDouble(req.getParameter("reviewManner")));
		
		Connection conn = JDBCTemplate.getConnection();

		//리뷰 삽입
		int rRes = reviewDao.insertReview(conn, updateReview);
		
		if( rRes > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		 
		//모집팀번호, 참가팀번호 조회
		Matching matching  = new Matching();
		matching.setInviteNo(updateReview.getInviteNo());
		matching = matchingDao.selectMatching(conn, matching.getInviteNo());
		
		//모집게시글에 경기결과 삽입 결과 객체
		int iRes = 0;
		
		//내 팀 번호가 모집팀 번호일 경우
		if( (Integer)req.getSession().getAttribute("teamNo") == matching.getInviteTeamNo() ) {
			//모집게시글에 모집팀경기결과 컬럼에 경기결과 저장
			iRes = reviewDao.updateInviteInv(conn, updateReview);

			//참가팀의 팀정보테이블에 리뷰의 팀매너점수 추가 후 다시계산
			iRes += reviewDao.updateTeamMannerJo(conn, updateReview, matching.getJoinTeamNo());
			
		//내 팀 번호가 참가팀 번호일 경우
		} else if( (Integer)req.getSession().getAttribute("teamNo") == matching.getJoinTeamNo()  ) {
			//모집게시글에 참가팀경기결과 컬럼에 경기결과 저장
			iRes = reviewDao.updateInviteJo(conn, updateReview);
			
			//참가팀의 팀정보테이블에 리뷰의 팀매너점수 추가 후 다시계산
			iRes += reviewDao.updateTeamMannerInv(conn, updateReview, matching.getInviteTeamNo());
			
		}
		
		System.out.println("[리뷰서비스] iRes : " + iRes);
		
		if( iRes >= 2 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}


}
