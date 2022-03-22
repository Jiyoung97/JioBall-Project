package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.GroundInfo;
import dto.Match;
import util.Paging;

public interface MatchDao {

	List<Match> selectAll(Connection connection, Paging paging, String search);

	int selectCntAll(Connection connection, String search);

	int selectCntAll(Connection connection);

	int insertMatch(Connection conn, Match match);

	List<GroundInfo> selectGroundList(Connection connection);

}
