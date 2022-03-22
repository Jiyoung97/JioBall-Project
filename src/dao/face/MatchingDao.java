package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Matching;

public interface MatchingDao {

	/**
	 * 팀번호로 최근 매칭내역 3건 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param teamNo - 조회할 팀 번호
	 * @return Matching - 조회된 최근 매칭내역 3건 객체
	 */
	public List<Matching> selectRecentMatching(Connection conn, int teamNo);

	/**
	 * 팀번호로 모집팀이름 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param teamNo - 조회할 팀 번호
	 * @return 
	 */
	public Matching selectInviteTeamNameByTeamNo(Connection conn, int teamNo);

	/**
	 * 팀번호로 참가팀이름 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param teamNo - 조회할 팀 번호
	 * @return 
	 */
	public Matching selectJoinTeamNameByTeamNo(Connection conn, int teamNo);

	/**
	 * 팀번호로 전체 매칭내역 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param teamNo - 조회할 팀 번호
	 * @return Matching - 조회된 전체 매칭내역 객체
	 */
	public List<Matching> selectAllMatching(Connection conn, int teamNo);

	/**
	 * 모집번호로 매칭내역 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param inviteNo - 조회할 모집번호
	 * @return Matching - 조회된 매칭내역 정보
	 */ 
	public Matching selectMatching(Connection conn, int inviteNo);



}
