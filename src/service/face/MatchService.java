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
	

}
