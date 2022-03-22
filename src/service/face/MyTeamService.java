package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.TeamInfo;

public interface MyTeamService {

	/**
	 * 세션의 팀번호로 팀정보 조회
	 * 
	 * @param teamNo - 로그인한 회원의 팀번호
	 * @return TeamInfo - 조회된 회원의 팀정보
	 */
	public TeamInfo getMyTeamInfo(int teamNo);

	/**
	 * 내 팀 정보 수정
	 * 
	 * @param req - 요청 정보 객체
	 * @param teamNo - 로그인한 회원의 팀번호
	 */
	public void editMyTeamInfo(HttpServletRequest req, int teamNo);
}
