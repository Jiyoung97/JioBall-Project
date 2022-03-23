package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.GroundInfo;
import dto.Match;
import util.Paging;

public interface MatchService {

	public Paging getPaging(HttpServletRequest req, String search);

	public List<Match> getMatchList(Paging paging, String search);

	public void matchWrite(HttpServletRequest req);

	public List<GroundInfo> getGroundNameList();
	
	public Match getMatchView(Match matchNo);
	
	public GroundInfo getGroundInfo(GroundInfo groundNo);
	
	public Match getMatchNo(HttpServletRequest req);
	
	public GroundInfo getGroundNo(HttpServletRequest req);
	
	public void matchJoin(Match matchNo);
	
	public Match getJoinInsertInfo(HttpServletRequest req);
	

}
