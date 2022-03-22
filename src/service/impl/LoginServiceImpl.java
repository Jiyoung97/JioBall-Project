package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dao.impl.LoginDaoImpl;
import dto.TeamInfo;
import dto.UserInfo;
import service.face.LoginService;

public class LoginServiceImpl implements LoginService {

	@Override
	public UserInfo getUserParam(HttpServletRequest req) {
		
		UserInfo userParam = new UserInfo();
		
		if(req.getParameter("userid")!=null) {
			userParam.setUserId(req.getParameter("userid").toLowerCase());
		} else {
			userParam.setUserId(req.getParameter("userid"));
		}
		userParam.setUserPw(req.getParameter("userpw"));
		userParam.setUserName(req.getParameter("username"));
		
		String userGender = req.getParameter("usergender");
				
		if( userGender == null) {
			
			userGender = "0";
			userParam.setUserGender(Integer.parseInt(userGender));
			
		} else {
			
			userParam.setUserGender(Integer.parseInt(userGender));
		}
			
		userParam.setUserBirth(req.getParameter("userbirth"));
		userParam.setUserTelecom(req.getParameter("usertelecom"));
		userParam.setUserPhone(req.getParameter("userphone"));
		
		return userParam;
		
	}

	@Override
	public TeamInfo getTeamParam(HttpServletRequest req) {
		
		TeamInfo teamParam = new TeamInfo();
		
		teamParam.setTeamName(req.getParameter("teamname"));
		teamParam.setTeamGender(Integer.parseInt(req.getParameter("teamgender")));
		teamParam.setTeamIntroduce(req.getParameter("teamintroduce"));
		teamParam.setPlayLocalNo(Integer.parseInt(req.getParameter("playlocalno")));
		
		return teamParam;
	}
	
	@Override
	public int login(UserInfo userParam) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao loginDao = new LoginDaoImpl();
		
		return loginDao.selectUser(conn, userParam);
		
	}
	
	@Override
	public int kakaoLogin(UserInfo userParam) {
		
		return 0;
	}

	@Override
	public String findUserId(UserInfo userParam) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao loginDao = new LoginDaoImpl();
		
		return loginDao.selectUserId(conn, userParam);
		
	}
	
	@Override
	public UserInfo findUserPw(UserInfo userParam) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao loginDao = new LoginDaoImpl();
		
		loginDao.selectUserPw(conn, userParam);
		
		return userParam;
		
	}
	
	@Override
	public void sendMail(UserInfo userParam) {
		
		String mailId = "jioball3c";
		String mailPw = "rewm yfgn mlxr ixgy";
		
		Properties prop = System.getProperties();
		
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailId, mailPw);
			}
		});

		try {
			
			MimeMessage msg = new MimeMessage(session);

			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress("jioball3c@gmail.com", "JioBall"));

			InternetAddress to = new InternetAddress(userParam.getUserId());
			
			msg.setRecipient(Message.RecipientType.TO, to);
			
			msg.setSubject("JioBall find Your PassWord","UTF-8");
			
			msg.setText("회원님의 비밀번호는 [ " + userParam.getUserPw() + " ] 입니다");
			
			Transport.send(msg);

			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public int checkId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao loginDao = new LoginDaoImpl();
		
		String result = loginDao.selectAlreadyId(conn, userId);
		
		if (result != null) {
			
			if(result.equals(userId)) {
				
				return 1;
			}
			
		}
		
		return 0;
	}

	@Override
	public int checkName(String teamName) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao loginDao = new LoginDaoImpl();
		
		String result = loginDao.selectAlreadyName(conn, teamName);
		
		if (result != null) {
			
			if(result.equals(teamName)) {
				
				return 1;
			}
			
		}
		
		return 0;
	}

	@Override
	public boolean join(UserInfo userParam, TeamInfo teamParam) {

		Connection conn =  JDBCTemplate.getConnection();

		LoginDao loginDao = new LoginDaoImpl();

		int userNo = loginDao.selectUserNo(conn);
		System.out.println("UserInfo [userNo=" + userNo + "]");

		userParam.setUserNo(userNo);
		teamParam.setUserNo(userNo);

		int teamNo = loginDao.selectTeamNo(conn);
		System.out.println("TeamInfo [teamNo=" + teamNo + "]");

		teamParam.setTeamNo(teamNo);

		int result = loginDao.insert(conn, userParam);

		result += loginDao.insert(conn, teamParam);

		if( result == 2 ) { 
			JDBCTemplate.commit(conn);
			return true;
		} else { 
			JDBCTemplate.rollback(conn);
			return false;
		}

	}

}
