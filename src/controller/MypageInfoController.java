package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserInfo;
import service.face.MyPageService;
import service.impl.MyPageServiceImp;

@WebServlet("/mypage/userinfo")
public class MypageInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/mypage/inform [GET]");

		HttpSession session = req.getSession();

		if(session.getAttribute("teamNo") == null) {

			System.out.println("TeamInfo [teamNo=" + session.getAttribute("teamNo") + "]");
			System.out.println("Connection Unavailable [Redirect]");
			resp.sendRedirect("/login/login");

		} else {

			System.out.println("Show Information [SUCCESS]");

			int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
			System.out.println("UserInfo [userno=" + userNo + "]");

			MyPageService myPageService = new MyPageServiceImp();

			UserInfo userInfo = myPageService.showOriginInfo(userNo);

			System.out.println("userInfo : " + userInfo);
			
			req.setAttribute("userInfo", userInfo);

			req.getRequestDispatcher("/WEB-INF/views/mypage/userInfo.jsp").forward(req, resp);

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/mypage/userinfo [GET]");
		
		req.setCharacterEncoding("UTF-8");
		
		MyPageService myPageService = new MyPageServiceImp();
		
		UserInfo userParam = myPageService.getUserParam(req);
		
		HttpSession session = req.getSession();
		
		userParam.setUserNo(Integer.parseInt(session.getAttribute("userNo").toString()));
		
		System.out.println(userParam);
		
		boolean result = myPageService.alterUserInfo(userParam);
		
		if(result) {
			
			System.out.println("Alter User [UPDATE]");
			req.getRequestDispatcher("/WEB-INF/views/mypage/updateResult.jsp").forward(req, resp);
		
		} else {
			
			System.out.println("Alter User [FAIL]");
			resp.sendRedirect("/WEB-INF/layout/error500.html");
		
		}

	}


}

