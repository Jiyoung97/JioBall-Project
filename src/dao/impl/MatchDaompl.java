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
		sql += " 			invite_no, play_date,invite_title, matchingprogress_type, playlocal_no, play_person,ground_no";
		sql += "		FROM invite";
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
				match.setMatchingProgressType(rs.getInt("matchingprogress_type"));
				match.setPlayPerson(rs.getInt("play_person"));
				match.setGroundNo(rs.getInt("ground_no"));
				if(1==rs.getInt("playlocal_no")) {
					match.setPlayLocal("김해");
				}else if(2==rs.getInt("playlocal_no")) {
					match.setPlayLocal("부산");
				}else if(3==rs.getInt("playlocal_no")) {
					match.setPlayLocal("양산");
				}else if(4==rs.getInt("playlocal_no")) {
					match.setPlayLocal("진주");
				}else if(5==rs.getInt("playlocal_no")) {
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
		sql += "SELECT count(*) cnt FROM invite";
				
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
		sql += "SELECT count(*) cnt FROM invite";
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
		sql += "INSERT INTO invite(invite_no,invite_date, invite_title, invite_comment, play_date, ";
		sql += " play_person, playlocal_no, team_no, ground_no, playtype_no, matchingprogress_type)";
		sql += " VALUES(invite_seq.nextval,sysdate,?, ?, ?, ?, ?, ?, ?, ?, 1)";
		 
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
			ps.setInt(8, match.getPlayType());
			
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
	
	@Override
	public GroundInfo selectGroundInfo(Connection conn, GroundInfo groundNo) {
		String sql = "";
		sql += "SELECT *";
		sql += "  FROM GROUNDINFO";
		sql += " WHERE GROUND_NO = ?";
		
		GroundInfo groundInfo = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groundNo.getGroundNo());
			
			rs = ps.executeQuery();

			while(rs.next()) {
				groundInfo = new GroundInfo();
				
				groundInfo.setGroundNo(rs.getInt("GROUND_NO"));
				groundInfo.setGroundName(rs.getString("GROUND_NAME"));
				groundInfo.setGroundLocation(rs.getString("GROUND_LOCATION"));
				groundInfo.setGroundSize(rs.getString("GROUND_SIZE"));
				groundInfo.setGroundOpenTime(rs.getTimestamp("GROUND_OPENTIME"));
				groundInfo.setGroundCloseTime(rs.getTimestamp("GROUND_CLOSETIME"));
				groundInfo.setGroundShower(rs.getInt("GROUND_SHOWER"));
				groundInfo.setGroundParking(rs.getInt("GROUND_PARKING"));
				groundInfo.setGroundShoes(rs.getInt("GROUND_SHOES"));
				groundInfo.setGroundBib(rs.getInt("GROUND_BIB"));
				groundInfo.setGroundNotice(rs.getString("GROUND_NOTICE"));
				groundInfo.setGroundFee(rs.getInt("GROUND_FEE"));
				groundInfo.setPlayTypeNo(rs.getInt("PLAYTYPE_NO"));
				groundInfo.setPlayLocalNo(rs.getInt("PLAYLOCAL_NO"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return groundInfo;
	}
	
	@Override
	public Match selectMatchView(Connection connection, Match matchNo) {
		
		String sql = "";
		sql += "SELECT *";
		sql += "  FROM invite";
		sql += " WHERE invite_no = ?";
		
		Match match = new Match();
		
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, matchNo.getInviteNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				match.setInviteNo(rs.getInt("invite_no"));
				match.setPlayDate(rs.getString("play_date"));
				match.setInviteTitle(rs.getString("invite_title"));
				match.setInviteComment(rs.getString("invite_comment"));
				if(rs.getInt("playlocal_no")==1) {
				match.setPlayLocal("김해");
				}else if(rs.getInt("playlocal_no")==2) {
					match.setPlayLocal("부산");
				}else if(rs.getInt("playlocal_no")==3) {
					match.setPlayLocal("양산");
				}else if(rs.getInt("playlocal_no")==4) {
					match.setPlayLocal("진주");
				}else if(rs.getInt("playlocal_no")==5) {
					match.setPlayLocal("창원");
				}
				match.setMatchingProgressType(rs.getInt("matchingprogress_type"));
				match.setPlayPerson(rs.getInt("play_person"));
				match.setPlayType(rs.getInt("playtype_no"));
				match.setTeamNo(rs.getInt("team_no"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		
		return match;
	}
	
	@Override
	public Match selectTeamName(Connection connection, Match match) {
		
		String sql = "";
		sql += "SELECT team_name FROM teaminfo";
		sql += " WHERE team_no = ?";
		Match teamName = new Match();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, match.getTeamNo());
			
			rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				teamName.setTeamName(rs.getString("team_name")); 
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return teamName;
	}
	
	
	@Override
	public int insertJoin(Connection conn, Match matchNo) {
		
		String sql = "";
		sql += "INSERT INTO join(join_no,join_date, invite_no, team_no)";
		sql += " VALUES (join_seq.nextval, sysdate, ?, ?)";
		
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, matchNo.getInviteNo());
			ps.setInt(2, matchNo.getTeamNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	
	@Override
	public int updateMatch(Connection conn, Match matchNo) {
		
		String sql = "";
		sql += "UPDATE invite SET matchingprogress_type = 2";
		sql += " WHERE invite_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, matchNo.getInviteNo());
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		
		
		return res;
	}
	
	
	
}
