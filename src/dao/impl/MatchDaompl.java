package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MatchDao;
import dto.GroundInfo;
import dto.Match;
import util.Paging;

public class MatchDaompl implements MatchDao {

	private PreparedStatement ps = null; //sql 수행객체
	private ResultSet rs = null; // 조회결과 객체
	
	@Override
	public List<Match> selectAll(Connection connection, Paging paging, String search) {
		
		String sql = "";
		sql += "SELECT * FROM(";
		sql += "	SELECT rownum rnum, N.* FROM(";
		sql += " 		SELECT";
		sql += " 			invite_no, invite_title, play_date, join_no, play_local, play_person";
		sql += "		FROM matchinvite";
		sql += "		WHERE invite_title LIKE '%' || ? || '%'";
		sql += "		ORDER BY invite_no DESC";
		sql += "	)N";
		sql += ")MATCH";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Match> list = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, search);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Match match = new Match();
				
				match.setMatchRnum(rs.getInt("rnum"));
				match.setInviteNo(rs.getInt("invite_no"));
				match.setInviteTitle(rs.getString("invite_title"));
				match.setPlayDate(rs.getString("play_date"));
				match.setJoinNo(rs.getInt("join_no"));
				match.setPlayPerson(rs.getInt("play_person"));
				if(1==rs.getInt("play_local")) {
					match.setPlayLocal("김해");
				}else if(2==rs.getInt("play_local")) {
					match.setPlayLocal("부산");
				}else if(3==rs.getInt("play_local")) {
					match.setPlayLocal("양산");
				}else if(4==rs.getInt("play_local")) {
					match.setPlayLocal("진주");
				}else if(5==rs.getInt("play_local")) {
					match.setPlayLocal("창원");
				}
				
				list.add(match);
				
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
	public int selectCntAll(Connection connection) {
		
		String sql = "";
		sql += "SELECT count(*) cnt FROM matchinvite";
				
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
		sql += "SELECT count(*) cnt FROM matchinvite";
		sql += " WHERE invite_title LIKE '%' || ? || '%'";
				
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
	public int insertMatch(Connection conn, Match match) {
		
		String sql = "";
		sql += "INSERT INTO matchinvite(invite_no, invite_title, invite_comment, play_date, ";
		sql += " play_person, play_local, team_no, ground_no)";
		sql += " VALUES(matchinvite_seq.nextval,?, ?, ?, ?, ?, ?, ?)";
		 
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, match.getInviteTitle());
			ps.setString(2, match.getInviteComment());
			ps.setString(3, match.getPlayDate());
			ps.setInt(4, match.getPlayPerson());
			if(match.getPlayLocal().equals("gimhae")) {
				ps.setInt(5, 1);
			}else if(match.getPlayLocal().equals("busan")) {
				ps.setInt(5, 2);
			}else if(match.getPlayLocal().equals("yangsan")) {
				ps.setInt(5, 3);
			}else if(match.getPlayLocal().equals("jinju")) {
				ps.setInt(5, 4);
			}else if(match.getPlayLocal().equals("changwon")) {
				ps.setInt(5, 5);
			}
			ps.setInt(6, match.getTeamNo());
			ps.setInt(7, match.getGroundNo());
			
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}
	
	@Override
	public List<GroundInfo> selectGroundList(Connection connection) {
		
		String sql = "";
		sql += "SELECT ground_no, ground_name FROM groundinfo";
		sql += " ORDER BY ground_no DESC";
		
		List<GroundInfo> list = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				GroundInfo groundInfo = new GroundInfo();
				
				groundInfo.setGroundNo(rs.getInt("ground_no"));
				groundInfo.setGroundName(rs.getString("ground_name"));
				
				list.add(groundInfo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		
		return list;
	}
}
