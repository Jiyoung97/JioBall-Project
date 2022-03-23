package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MatchDao;
import dao.impl.MatchDaompl;
import dto.GroundInfo;
import dto.Match;
import service.face.MatchService;
import util.Paging;

public class MatchServiceImpl implements MatchService{
	
	MatchDao matchDao = new MatchDaompl();

	@Override
	public List<Match> getMatchList(Paging paging, String search) {
		
		List<Match> list = matchDao.selectAll(JDBCTemplate.getConnection(),paging,search);
		
		return list;
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req, String search) {
		
		//전달파라미터 curPage 추출하기
				String param = req.getParameter("curPage");
						
				int curPage = 0;
				if( param != null && !"".equals(param)) {
					curPage = Integer.parseInt(param);
				}else {
				}
						
				//총 게시글 수 조회하기
				int totalCount = 0;
				if(search != null && !"".equals(search)) {
					totalCount = matchDao.selectCntAll(JDBCTemplate.getConnection(), search);
//					System.out.println("검색어 o : "+totalCount);
				}else {
					totalCount = matchDao.selectCntAll(JDBCTemplate.getConnection());
//					System.out.println("검색어 x : "+totalCount);
				}	
				
				Paging paging = new Paging(totalCount, curPage);
						
				return paging;
	}
	
	@Override
	public void matchWrite(HttpServletRequest req) {
		
		Match match = new Match();
		Connection conn = JDBCTemplate.getConnection();
		
		match.setInviteTitle(req.getParameter("title"));
		match.setInviteComment(req.getParameter("comment"));
		match.setPlayDate(req.getParameter("playDate"));
		match.setPlayPerson(Integer.parseInt(req.getParameter("person")));
		match.setPlayLocal(req.getParameter("local"));
		match.setTeamNo((Integer)req.getSession().getAttribute("teamNo"));
		match.setGroundNo(Integer.parseInt(req.getParameter("groundNo")));
		match.setPlayType(Integer.parseInt(req.getParameter("playType")));
		
		int res = matchDao.insertMatch(conn,match);
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
	}
	
	public List<GroundInfo> getGroundNameList(){
		
		List<GroundInfo> list = matchDao.selectGroundList(JDBCTemplate.getConnection());
		
		return list;
	}
	
	@Override
	public GroundInfo getGroundInfo(GroundInfo groundNo) {
		
		GroundInfo groundInfo = matchDao.selectGroundInfo(JDBCTemplate.getConnection(), groundNo);
		return groundInfo;
	}
	
	@Override
	public Match getMatchView(Match matchNo) {
		Match match = matchDao.selectMatchView(JDBCTemplate.getConnection(),matchNo);
		Match teamName =  matchDao.selectTeamName(JDBCTemplate.getConnection(),match);
		match.setTeamName(teamName.getTeamName());
		return match;
	}
	
	@Override
	public GroundInfo getGroundNo(HttpServletRequest req) {
		GroundInfo groundNo = new GroundInfo();
		groundNo.setGroundNo(Integer.parseInt(req.getParameter("groundNo")));		
		return groundNo;
	}
	
	@Override
	public Match getMatchNo(HttpServletRequest req) {
		
		Match matchNo = new Match();
		
		matchNo.setInviteNo(Integer.parseInt(req.getParameter("matchNo")));
		
		return matchNo;
	}
	
	@Override
	public void matchJoin(Match matchNo) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println(matchNo);
		int res = matchDao.updateMatch(conn,matchNo);
		
		if(res>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		int joinres = matchDao.insertJoin(conn,matchNo);
		
		if(joinres>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public Match getJoinInsertInfo(HttpServletRequest req) {
		Match matchNo = new Match();
		
		matchNo.setInviteNo(Integer.parseInt(req.getParameter("matchNo")));
		matchNo.setTeamNo((Integer)req.getSession().getAttribute("teamNo"));
		
		return matchNo;
	
	}
	
}
