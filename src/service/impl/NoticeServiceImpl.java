package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dao.impl.NoticeDaoImpl;
import dto.Notice;
import service.face.NoticeService;
import util.Paging;

public class NoticeServiceImpl implements NoticeService{

	private NoticeDao noticeDao = new NoticeDaoImpl();
	
	@Override
	public List<Notice> list(Paging paging, String search) {
		List<Notice> list = noticeDao.selectAll(JDBCTemplate.getConnection(),paging,search);
		
		return list;
	}
	
	@Override
	public Notice getNoticeNo(HttpServletRequest req) {
		Notice notice = new Notice();
		notice.setNoticeNo(Integer.parseInt(req.getParameter("no")));
		
		return notice;
	}
	

	
	@Override
	public Notice view(Notice noticeNo) {
		Notice content = noticeDao.selectView(JDBCTemplate.getConnection(), noticeNo);
		return content;
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req, String search) {
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
//		System.out.println("getPaging search = "+search);
				
		int curPage = 0;
		if( param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}else {
//			System.out.println("[WARN] BoardService getPagin() - curPage값이 null이거나 비어있음");
		}
				
		//총 게시글 수 조회하기
		int totalCount = 0;
		if(search != null && !"".equals(search)) {
			totalCount = noticeDao.selectCntAll(JDBCTemplate.getConnection(), search);
//			System.out.println("검색어 o : "+totalCount);
		}else {
			totalCount = noticeDao.selectCntAll(JDBCTemplate.getConnection());
//			System.out.println("검색어 x : "+totalCount);
		}	
		
		Paging paging = new Paging(totalCount, curPage);
				
		return paging;
	}
	
	
	
	@Override
	public void noticeDelete(Notice noticeno) {
		Connection conn = JDBCTemplate.getConnection();
		int res = noticeDao.deleteNotice(conn,noticeno);
		
		if( res> 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public void writeNotice(HttpServletRequest req) {
		Notice notice = new Notice();
		Connection conn = JDBCTemplate.getConnection();
		
		notice.setNoticeTitle(req.getParameter("title"));
		notice.setNoticeContent(req.getParameter("content"));
		
		if(notice.getNoticeTitle()==null || "".equals(notice.getNoticeTitle())) {
			notice.setNoticeTitle("(제목없음)");
		}else if(notice.getNoticeTitle() == null || "".equals(notice.getNoticeTitle())) {
			notice.setNoticeTitle("(내용없음)");
		}
		
		int res = noticeDao.insertNotice(conn,notice);
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
}
