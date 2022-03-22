package dao.face;

import java.sql.Connection;

import dto.TeamInfo;

public interface MyTeamDao {

	/**
	 * 팀번호가 일치하는 팀 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param teamNo - 조회할 팀 번호
	 * @return TeamInfo - 조회된 팀 정보 객체
	 */
	public TeamInfo selectTeamByTeamNo(Connection conn, int teamNo);

	/**
	 * 팀정보 수정하기
	 * 
	 * @param conn - DB연결 객체
	 * @param teamNo - 조회할 팀 번호
	 * @return 수행 결과 (1-성공, 0-실패)
	 */
	public int updateTeamInfo(Connection conn, TeamInfo newMyTeamInfo);
}
