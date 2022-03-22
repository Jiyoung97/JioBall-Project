package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Notice;
import util.Paging;

public interface NoticeService {
	
	/**
	 * 공지사항 목록을 가져온다
	 * @return 공지사항 목록
	 */
	public List<Notice> list(Paging paging, String search);

	
	/**
	 * 공지사항 상세보기
	 * @param no - notice_no
	 * @return 공지사항 상세정보
	 */
	public Notice view(Notice noticeNo); 
	
	/**
	 * 페이징 기능
	 * @param req
	 * @param search - 검색어
	 * @return
	 */
	public Paging getPaging(HttpServletRequest req, String search);
	
	public Notice getNoticeNo(HttpServletRequest req);


	public void noticeDelete(Notice noticeno);


	public void writeNotice(HttpServletRequest req);
		
	

}
