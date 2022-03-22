package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.ManagerLoginDao;
import dto.ManagerInfo;

public class ManagerLoginDaoImpl implements ManagerLoginDao{
	
	private PreparedStatement ps = null; 
	private ResultSet rs = null; 
	
	@Override
	public int selectManager(Connection conn, ManagerInfo managerParam) {
		
		String sql = "";
		sql += "SELECT * FROM managerinfo";
		sql += " WHERE (manager_id = ?) AND (manager_pw = ?)";
		
		int managerNo = 0;
		
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, managerParam.getManagerId());
			ps.setString(2, managerParam.getManagerPw());
			rs = ps.executeQuery();

			if(rs.next()) {
				
				managerNo = rs.getInt("manager_no");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return managerNo;

	}

}
