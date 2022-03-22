package dao.face;

import java.sql.Connection;

import dto.Review;

public interface ReviewDao {

	/**
	 * 경기결과, 매너점수 삽입
	 * 
	 * @param conn - DB연결 객체
	 * @param updateReview - 저장할 리뷰 객체
	 * @return 수행결과 (1-성공, 0-실패)
	 */
	int insertReview(Connection conn, Review updateReview);

	/**
	 * 모집게시글 모집팀경기결과 업데이트
	 * 
	 * @param conn - DB연결 객체
	 * @param updateReview - 경기결과
	 * @return 수행결과 (1-성공, 0-실패)
	 */
	int updateInviteInv(Connection conn, Review updateReview);

	/**
	 * 모집게시글 참가팀경기결과 업데이트
	 * 
	 * @param conn - DB연결 객체
	 * @param updateReview - 경기결과
	 * @return 수행결과 (1-성공, 0-실패)
	 */
	int updateInviteJo(Connection conn, Review updateReview);

	/**
	 * 모집팀정보의 팀 평균매너점수 다시 계산
	 * 
	 * @param conn - DB연결 객체
	 * @param updateReview - 받은 경기 매너점수
	 * @param inviteTeamNo - 모집팀번호
	 * @return 수행결과 (1-성공, 0-실패)
	 */
	int updateTeamMannerInv(Connection conn, Review updateReview, int inviteTeamNo);

	/**
	 * 참가팀정보의 팀 평균매너점수 다시 계산
	 * 
	 * @param conn - DB연결 객체
	 * @param updateReview - 받은 경기 매너점수
	 * @param joinTeamNo - 참가팀번호
	 * @return 수행결과 (1-성공, 0-실패)
	 */
	int updateTeamMannerJo(Connection conn, Review updateReview, int joinTeamNo);
}
