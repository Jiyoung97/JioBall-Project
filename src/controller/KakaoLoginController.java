package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/kakaologin")
public class KakaoLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/login/kakaologin [GET]");
		
		String userkakaoid = "[kakao]" + req.getParameter("userkakaoid");
		
		System.out.println("UserInfo [userid=" + userkakaoid + "]");
		
	}

}
