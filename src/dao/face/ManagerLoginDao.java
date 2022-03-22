package dao.face;

import java.sql.Connection;

import dto.ManagerInfo;

public interface ManagerLoginDao {
	
	public int selectManager(Connection conn, ManagerInfo managerParam);

}
