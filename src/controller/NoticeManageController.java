package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Notice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;
import util.Paging;


@WebServlet("/manager/notice")
public class NoticeManageController extends HttpServlet {
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
			
			//검색어 파라미터 받아오기
			String search = req.getParameter("searchText");
//		System.out.println(search);
			
			//페이징 객체 생성
			Paging paging = noticeService.getPaging(req, search);
			
			//게시글 목록
			List<Notice> noticelist = noticeService.list(paging,search);
			
			//전달
			req.setAttribute("paging", paging);
			req.setAttribute("list", noticelist);
			req.getRequestDispatcher("/WEB-INF/views/manager/noticeManageList.jsp").forward(req, resp);

		}
		
	}

}
