package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Review;

public interface ReviewService {

	/**
	 * 리뷰 작성하기
	 * 
	 * @param review - inviteNo 가진 객체
	 * @param req - 요청 정보 객체
	 */
	public void writeReview(Review review, HttpServletRequest req);

}
