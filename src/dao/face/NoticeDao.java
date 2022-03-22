package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Notice;
import util.Paging;

public interface NoticeDao {
	
	/**
	 * 공지사항 목록 전체 조회하기
	 * @param connection -DB연결 객체
	 * @return 테이블 전체 조회 결과 List
	 */
	public List<Notice> selectAll(Connection connection, Paging paging, String search);
	
	/**
	 * 공지사항 상세보기
	 * @param connection -DB연결 객체
	 * @param no - 공지사항 번호
	 * @return 공지사항 상세정보
	 */
	public Notice selectView(Connection connection, Notice noticeNo);
	
	
	/**
	 * 전체게시글 수
	 * @param connection
	 * @return
	 */
	public int selectCntAll(Connection connection);

	/**
	 * 검색된 게시글 수
	 * @param connection
	 * @param search - 검색어
	 * @return
	 */
	public int selectCntAll(Connection connection, String search);

	public int deleteNotice(Connection conn, Notice noticeno);

	public int insertNotice(Connection conn, Notice notice);

}
