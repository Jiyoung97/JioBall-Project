package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.SupportDao;
import dto.Support;
import dto.SupportFile;
import util.Paging;

public class SupportDaoImpl implements SupportDao{
	
	private PreparedStatement ps = null; //sql 수행객체
	private ResultSet rs = null; // 조회결과 객체
	
	@Override
	public List<Support> selectSupportAll(Connection connection, Paging paging, String search, String type) {
		
		String sql = "";
		sql += "SELECT * FROM(";
		sql += "	SELECT rownum rnum, N.* FROM(";
		sql += " 		SELECT";
		sql += " 			support_no, support_type ,support_title, support_date, support_state, support_writer";
		sql += "		FROM support";
		sql += "		WHERE support_title LIKE '%' || ? || '%'";
		if("team".equals(type)) {
			sql += " AND support_type = 1";
		}else if ("ground".equals(type) ) {
			sql += " AND support_type = 2";
		}else if ("support".equals(type)) {
			sql += " AND support_type = 3";
		}else if ("qna".equals(type)) {
			sql += " AND support_type = 0";
		}
		sql += "		ORDER BY support_no DESC";
		sql += "	)N";
		sql += ")SUPPORT";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Support> list = new ArrayList<>();
		
		
		try {
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, search);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();

			while(rs.next()) {
				Support support = new Support();
				
				support.setSupportRownum(rs.getInt("rnum"));
				support.setSupportNo(rs.getInt("support_no"));
				if(rs.getInt("support_type")== 0) {
					support.setSupportType("자주 묻는 질문");
				}else if(rs.getInt("support_type")==1) {
					support.setSupportType("팀");
				}else if(rs.getInt("support_type")==2) {
					support.setSupportType("구장");
				}else if(rs.getInt("support_type")==3) {
					support.setSupportType("문의");
				}
				
				support.setSupportTitle(rs.getString("support_title"));
				support.setSupportDate(rs.getString("support_date"));
				support.setSupportState(rs.getInt("support_state"));
				support.setSupportWriter(rs.getString("support_writer"));
				
				
				list.add(support);
				
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
	public int selectSupportCntAll(Connection connection,String type) {
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM support";
		
		if("team".equals(type)) {
			sql += " WHERE support_type = 1";
		}else if ("ground".equals(type) ) {
			sql += " WHERE support_type = 2";
		}else if ("support".equals(type)) {
			sql +=" WHERE support_type = 3";
		}else if ("qna".equals(type) ) {
			sql += " WHERE support_type = 0";
		}else {
			sql += " WHERE 1=1";
		}
				
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
	public int selectSupportCntAll(Connection connection, String search, String type) {
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM support";
		sql += " WHERE support_title LIKE '%' || ? || '%'";
		if("team".equals(type)) {
			sql += " AND support_type = 1";
		}else if ("ground".equals(type)) {
			sql += " AND support_type = 2";
		}else if ("support".equals(type)) {
			sql +=" AND support_type = 3";
		}else if("qna".equals(type)){
			sql +=" AND support_type = 0";
		}
				
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
	public Support selectSupportView(Connection connection, Support supportNo) {
		
		String sql = "";
		
		sql += "SELECT support_type, support_title, support_content, support_date,";
		sql	+= " support_answerdate, support_answer, support_writer";
		sql += " FROM support";
		sql += " WHERE support_no = ?";
		
		Support support = new Support();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, supportNo.getSupportNo());
			
			rs = ps.executeQuery();
			support.setSupportNo(supportNo.getSupportNo());
			while(rs.next()) {
				if(rs.getInt("support_type")== 0) {
					support.setSupportType("자주 묻는 질문");
				}else if(rs.getInt("support_type")==1) {
					support.setSupportType("팀");
				}else if(rs.getInt("support_type")==2) {
					support.setSupportType("구장");
				}else if(rs.getInt("support_type")==3) {
					support.setSupportType("문의");
				}
				support.setSupportTitle(rs.getString("support_title"));
				support.setSupportContent(rs.getString("support_content"));
				support.setSupportDate(rs.getString("support_date"));
				if(rs.getString("support_answerdate")!=null) {
				support.setSupportAnswerDate(rs.getString("support_answerdate"));
				support.setSupportAnswer(rs.getString("support_answer"));
				}
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return support;
	}
	
	@Override
	public int insertSupportWrite(Connection connection, Support support) {
		
		String sql = "";
		sql += "INSERT INTO support(support_no, support_title, support_content, support_type, support_date, support_state,";
		sql += " support_writer)";
		sql += " VALUES(?, ?, ?, ?, sysdate,0,?)";
		
		int res = 0;
		
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, support.getSupportNo());
			ps.setString(2, support.getSupportTitle());
			ps.setString(3, support.getSupportContent());
			if(support.getSupportType().equals("team")) {
				ps.setInt(4, 1);
			}else if(support.getSupportType().equals("ground")) {
				ps.setInt(4, 2);
			}else if(support.getSupportType().equals("support")) {
				ps.setInt(4, 3);
			}
			ps.setString(5, support.getSupportWriter());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int insertSupportFile(Connection connection, SupportFile supportFile) {
		
		String sql = "";
		sql += "INSERT INTO supportfile(support_file_no,support_no, support_file_originname, support_file_storedname";
		sql	+= ", support_file_path, support_file_size)";
		sql += " VALUES(supportfile_seq.nextval,?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, supportFile.getSupportNo());
			ps.setString(2, supportFile.getSupportFileOriginName());
			ps.setString(3, supportFile.getSupportFileStoredName());
			ps.setString(4, supportFile.getSupportFilePath());
			ps.setInt(5, supportFile.getSupportFileSize());
			
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int selectSupportNo(Connection conn) {
		
		String sql = "";
		sql += "SELECT support_seq.nextval FROM dual";
		
		int nextNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				nextNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextNo;
	}
	
	@Override
	public SupportFile selectSupportFileView(Connection connection,Support support) {
		
		String sql = "";
		sql += "SELECT support_file_no, support_no, support_file_originname, support_file_storedname, support_file_size";
		sql += " FROM supportfile";
		sql += " WHERE support_no = ?";
		
		SupportFile supportFile = new SupportFile();
		
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, support.getSupportNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				
				supportFile.setSupportFileNo(rs.getInt("support_file_no"));
				supportFile.setSupportNo(rs.getInt("support_no"));
				supportFile.setSupportFileOriginName(rs.getString("support_file_originname"));
				supportFile.setSupportFileStoredName(rs.getString("support_file_storedname"));
				supportFile.setSupportFileSize(rs.getInt("support_file_size"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return supportFile;
	}
	
	@Override
	public int updateAnswer(Connection conn, Support support) {
		
		String sql = "";
		sql += "UPDATE support SET support_answer = ?, support_answerdate = sysdate,";
		sql += " support_state = 1 WHERE support_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, support.getSupportAnswer());
			ps.setInt(2, support.getSupportNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}

}
