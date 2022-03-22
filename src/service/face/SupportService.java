package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Support;
import dto.SupportFile;
import util.Paging;

public interface SupportService {
	
	public List<Support> getSupportlist(Paging paging, String search, String type);
	
	public Support getSupportNo(HttpServletRequest req);
	
	public Support getSupportView(Support supportNo);
	
	public SupportFile getSupportFileView(Support support);
	
	public Paging getSupportPaging(HttpServletRequest req, String search, String type);
	
	public void supportWrite(HttpServletRequest req, HttpServletResponse resp);

	public void answerWrite(HttpServletRequest req);

}
