package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserInfo;
import service.face.LoginService;
import service.impl.LoginServiceImpl;


@WebServlet("/login/kakaologin")
public class LoginKaKaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/login/kakaologin [POST]");
		
		String userKakaoId = "[kakao]" + req.getParameter("userkakaoid");
		
		System.out.println("UserInfo [userid=" + userKakaoId + "]");
		
		UserInfo userParam = new UserInfo();
		
		userParam.setUserId(userKakaoId);

		LoginService loginService = new LoginServiceImpl();
		
		int teamNo = loginService.kakaoLogin(userParam);
		System.out.println("UserInfo [userName=" + userParam.getUserName() + "]");
		System.out.println("TeamInfo [teamNo=" + teamNo + "]");
		
		if(teamNo != 0) {
			
			System.out.println("Allow Access [LOGIN]");
			
			HttpSession session = req.getSession();
			session.setAttribute("userNo", userParam.getUserNo());
			session.setAttribute("userId", userParam.getUserId());
			session.setAttribute("userName", userParam.getUserName());
			session.setAttribute("teamNo", teamNo);
			
			req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
			
		} else {
			
			System.out.println("No Access [REDIRECT]");
			
			Random createPw = new Random();
			
			int kakaoPwKey = createPw.nextInt(90000000) + 10000000;
			String userKakaoPw = "[kakao]" + kakaoPwKey;
			
			req.setAttribute("userid", userKakaoId);
			req.setAttribute("userpw", userKakaoPw);
			req.getRequestDispatcher("/WEB-INF/views/login/kakaoJoinForm.jsp").forward(req, resp);
			
		}
		
	}

}
