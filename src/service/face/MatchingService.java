package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Matching;
import dto.UserTeam;

public interface MatchingService {

	/**
	 * 세션의 팀번호로 최근매칭내역 3건 조회
	 * 
	 * @param teamNo - 로그인한 회원의 팀번호 
	 * @return Matching - 조회된 회원의 최근매칭내역
	 */
	public List<Matching> getRecentMatching(int teamNo);

	/**
	 * 팀번호로 모집팀의 이름 조회
	 * 
	 * @param teamNo - 조회활 팀 번호
	 * @return Matching - 팀 이름이 저장된 객체
	 */
	public Matching getInviteTeamName(int teamNo);

	/**
	 * 팀번호로 참가팀의 이름 조회
	 * 
	 * @param teamNo - 조회활 팀 번호
	 * @return Matching - 팀 이름이 저장된 객체
	 */
	public Matching getJoinTeamName(int teamNo);

	/**
	 * 세션의 팀번호로 전체매칭내역 조회
	 * 
	 * @param teamNo - 로그인한 회원의 팀번호 
	 * @return Matching - 조회된 회원의 전체매칭내역
	 */
	public List<Matching> getAllMatching(int teamNo);

	/**
	 * 전달파라미터에서 모집번호를 Matching DTO로 저장하여 반환
	 * 
	 * @param req - 요청 정보 객체
	 * @return 전달된 데이터를 Matching객체에 담아서 반환
	 */
	public Matching getInviteNo(HttpServletRequest req);
	

	/**
	 * 회원 이름, 통신사, 휴대폰번호 조회
	 * 
	 * @param otherTeam - 조회할 팀번호
	 * @return UserInfo - 조회된 회원정보
	 */
	public UserTeam getUserInfo(UserTeam userTeam);

	/**
	 * 모집번호로 매칭내역 정보 조회
	 * 
	 * @param inviteNo - 조회할 모집번호
	 * @return
	 */
	public Matching getMatching(int inviteNo);


}
