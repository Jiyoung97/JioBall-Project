package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.MyPageService;
import service.impl.MyPageServiceImp;

@WebServlet("/mypage/withdrawal")
public class MypageWithdrawalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/mypage/withdrawal [GET]");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("teamNo") == null) {

			System.out.println("TeamInfo [teamNo=" + session.getAttribute("teamNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/login/login");

		} else {
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/withdrawal.jsp").forward(req, resp);
		
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("/mypage/withdrawal [POST]");
		
		MyPageService myPageService = new MyPageServiceImp();
		
		boolean result = myPageService.selectUserPw(req);
		
		String msg = null;
		
		if(!result) {
			
			msg = "비밀번호가 맞지 않습니다!";
			System.out.println("Delete User [FAIL]");
			
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/WEB-INF/views/mypage/withdrawalResult.jsp").forward(req, resp);

		} else {

			result = myPageService.withdrawalUser(req);

			msg = "회원 탈퇴가 완료 되었습니다!";
			System.out.println("Delete User [SUCCESS]");

			HttpSession session = req.getSession();

			session.invalidate();

			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/WEB-INF/views/mypage/withdrawalResult.jsp").forward(req, resp);

		}

	}

}
