package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Notice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;


@WebServlet("/manager/notice/delete")
public class NoticeManageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("managerNo") == null) {
			
			System.out.println("ManagerInfo [managerNo=" + session.getAttribute("managerNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/manager/login");
			
		} else {

			Notice noticeno = noticeService.getNoticeNo(req);
			
			noticeService.noticeDelete(noticeno);
			
			resp.sendRedirect("/manager/notice");
			
		}
		
	}

}
