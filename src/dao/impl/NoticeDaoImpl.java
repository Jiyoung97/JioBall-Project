package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dto.Notice;
import util.Paging;

public class NoticeDaoImpl implements NoticeDao{
	
	private PreparedStatement ps = null; //sql 수행객체
	private ResultSet rs = null; // 조회결과 객체
	
	@Override
	public List<Notice> selectAll(Connection connection, Paging paging, String search) {
		
		String sql = "";
		sql += "SELECT * FROM(";
		sql += "	SELECT rownum rnum, N.* FROM(";
		sql += " 		SELECT";
		sql += " 			notice_no, notice_title, notice_date";
		sql += "		FROM notice";
		sql += "		WHERE notice_title LIKE '%' || ? || '%'";
		sql += "		ORDER BY notice_no DESC";
		sql += "	)N";
		sql += ")NOTICE";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Notice> list = new ArrayList<>();
		
		
		try {
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, search);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();

			while(rs.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeRownum(rs.getInt("rnum"));
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
//				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeDate(rs.getString("notice_date"));
				
				list.add(notice);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			
		}
		
		return list;
	}
	
	
	
	
	@Override
	public Notice selectView(Connection connection, Notice noticeNo) {
		
		String sql = "";
		sql += "SELECT notice_no, notice_title, notice_content, notice_date FROM notice";
		sql += " WHERE notice_no = ?" ;
		Notice content = new Notice();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, noticeNo.getNoticeNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
			content.setNoticeTitle(rs.getString("notice_title"));
			content.setNoticeContent(rs.getString("notice_content"));
			content.setNoticeDate(rs.getString("notice_date"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return content;
	}
	
	
	@Override
	public int selectCntAll(Connection connection) {
		//SQL작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";
				
		//총 게시글 수
		int count = 0 ;
				
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
					
			while(rs.next()) {
				count = rs.getInt("cnt");
			}
					
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return count;
	}
	
	@Override
	public int selectCntAll(Connection connection, String search) {
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";
		sql += " WHERE notice_title LIKE '%' || ? || '%'";
				
		//총 게시글 수
		int count = 0 ;
				
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, search);
			rs = ps.executeQuery();
					
			while(rs.next()) {
				count = rs.getInt("cnt");
			}
					
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return count;
		
	}
	
	@Override
	public int deleteNotice(Connection conn, Notice noticeno) {
		
		String sql="";
		sql += "DELETE from notice WHERE notice_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeno.getNoticeNo());
			
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}
	
	@Override
	public int insertNotice(Connection conn, Notice notice) {
		
		String sql = "";
		sql += "INSERT INTO notice(notice_no, notice_title, notice_content, notice_date)";
		sql += " VALUES(notice_seq.nextval, ?, ?, sysdate)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getNoticeTitle());
			ps.setString(2, notice.getNoticeContent());
			
			res = ps.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
