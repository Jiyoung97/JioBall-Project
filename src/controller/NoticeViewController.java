package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Notice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;

@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/content [get]");
		Notice noticeno = noticeService.getNoticeNo(req);
//		System.out.println(noticeno);
		
		Notice content = noticeService.view(noticeno);
		req.setAttribute("content", content);
		
		
		req.getRequestDispatcher("/WEB-INF/views/service/noticeView.jsp").forward(req, resp);
	}

}
