package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Support;
import dto.SupportFile;
import util.Paging;

public interface SupportDao {
	
	public List<Support> selectSupportAll(Connection connection, Paging paging, String search, String type);
	
	public Support selectSupportView(Connection connection, Support supportNo);
	
	public int selectSupportCntAll(Connection connection, String type);

	public int selectSupportCntAll(Connection connection,String search, String type);
	
	public int insertSupportWrite(Connection connection, Support support);
	
	public int insertSupportFile(Connection connection, SupportFile supportFile);

	public int selectSupportNo(Connection conn);

	public SupportFile selectSupportFileView(Connection connection,Support support);

	public int updateAnswer(Connection conn, Support support);
	
	
	
}
