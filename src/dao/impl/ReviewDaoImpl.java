package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.Review;

public class ReviewDaoImpl implements ReviewDao {
	
	private PreparedStatement ps = null; //SQL 수행 객체
	
	@Override
	public int insertReview(Connection conn, Review updateReview) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "INSERT INTO REVIEW(REVIEW_NO, REVIEW_RESULT, REVIEW_MANNER, INVITE_NO, JOIN_NO)";
		sql += " VALUES (REVIEW_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, updateReview.getReviewResult());
			ps.setDouble(2, updateReview.getReviewManner());
			ps.setInt(3, updateReview.getInviteNo());
			ps.setInt(4, updateReview.getJoinNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int updateInviteInv(Connection conn, Review updateReview) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "UPDATE invite";
		sql += " SET play_result_invite = ?";
		sql += " WHERE invite_no = ?";
		
		
		//--- SQL 수행 결과 변수 ---
		int res = 0;
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, updateReview.getReviewResult());
			ps.setInt(2, updateReview.getInviteNo());
			
			//--- SQL 수행 및 결과 저장 ---
			res = ps.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( ps );
		}
	
		//--- 수행 결과 반환 ---
		return res;
	}
      
	@Override
	public int updateInviteJo(Connection conn, Review updateReview) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "UPDATE invite";
		sql += " SET play_result_join = ?";
		sql += " WHERE invite_no = ?";
		
		
		//--- SQL 수행 결과 변수 ---
		int res = 0;
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, updateReview.getReviewResult());
			ps.setInt(2, updateReview.getInviteNo());
			
			//--- SQL 수행 및 결과 저장 ---
			res = ps.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( ps );
		}
	
		//--- 수행 결과 반환 ---
		return res;
	}
	
	@Override
	public int updateTeamMannerInv(Connection conn, Review updateReview, int inviteTeamNo) {

		//--- SQL 작성 ---
		String sql = "";
		sql += "UPDATE teaminfo";
		sql += " SET team_manner = (team_manner + ?) / 2";
		sql += " WHERE team_no = ?";
		
		
		//--- SQL 수행 결과 변수 ---
		int res = 0;
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, updateReview.getReviewManner());
			ps.setInt(2, inviteTeamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			res = ps.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( ps );
		}
	
		//--- 수행 결과 반환 ---
		return res;
	}
	
	@Override
	public int updateTeamMannerJo(Connection conn, Review updateReview, int joinTeamNo) {
		
		//--- SQL 작성 ---
		String sql = "";
		sql += "UPDATE teaminfo";
		sql += " SET team_manner = (team_manner + ?) / 2";
		sql += " WHERE team_no = ?";
		
		
		//--- SQL 수행 결과 변수 ---
		int res = 0;
		
		try {
			//--- SQL 수행 객체 생성 ---
			ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, updateReview.getReviewManner());
			ps.setInt(2, joinTeamNo);
			
			//--- SQL 수행 및 결과 저장 ---
			res = ps.executeUpdate();	
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//--- 자원 해제 ---
			JDBCTemplate.close( ps );
		}
		
		//--- 수행 결과 반환 ---
		return res;
	}
}
