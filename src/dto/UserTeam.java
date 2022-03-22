package dto;

public class UserTeam {
	private int userNo;
	private String userId;
	private String userName;
	private String userTelecom;
	private String userPhone;
	private int teamNo;
	private String teamName;
	
	@Override
	public String toString() {
		return "UserTeam [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", userTelecom="
				+ userTelecom + ", userPhone=" + userPhone + ", teamNo=" + teamNo + ", teamName=" + teamName + "]";
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
