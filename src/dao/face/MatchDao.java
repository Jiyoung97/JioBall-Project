package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.GroundInfo;
import dto.Match;
import util.Paging;

public interface MatchDao {

	public List<Match> selectAll(Connection connection, Paging paging, String search);

	public int selectCntAll(Connection connection, String search);

	public int selectCntAll(Connection connection);

	public int insertMatch(Connection conn, Match match);

	public List<GroundInfo> selectGroundList(Connection connection);

	public GroundInfo selectGroundInfo(Connection conn, GroundInfo groundNo);

	public Match selectMatchView(Connection connection, Match matchNo);

	public Match selectTeamName(Connection connection, Match match);

	public int updateMatch(Connection conn, Match matchNo);

	public int insertJoin(Connection conn, Match matchNo);

}
