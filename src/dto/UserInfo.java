package dto;

public class UserInfo {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private int userGender;
	private String userBirth;
	private String userTelecom;
	private String userPhone;
	private String userSignup;
	
	@Override
	public String toString() {
		return "UserInfo [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userGender=" + userGender + ", userBirth=" + userBirth + ", userTelecom=" + userTelecom
				+ ", userPhone=" + userPhone + ", userSignup=" + userSignup + "]";
	}
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserGender() {
		return userGender;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserTelecom() {
		return userTelecom;
	}
	public void setUserTelecom(String userTelecom) {
		this.userTelecom = userTelecom;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserSignup() {
		return userSignup;
	}
	public void setUserSignup(String userSignup) {
		this.userSignup = userSignup;
	}
	
}
