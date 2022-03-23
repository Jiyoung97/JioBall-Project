package dto;

import java.util.Date;

public class Matching { //V_MATCHING (매칭모집+매칭참가 테이블)
	private int inviteNo;			//모집번호
	private String inviteDate;		//모집일자
	private Date playDate;			//경기일자
	private int inviteTeamNo;		//모집팀번호
	private String inviteTeamName;	//모집팀이름
	private int joinTeamNo;			//참가팀번호
	private String joinTeamName;	//참가팀이름
	private int groundNo;			//구장번호
	private String groundName;		//구장이름
	private int joinNo;				//참가번호
	private String joinDate;		//참가일자
	private int playResultInvite;	//참가팀경기결과
	private int playResultJoin;		//모집팀경기결과
	private int matchingProgressNo;	//매칭진행상황
	
	@Override
	public String toString() {
		return "Matching [inviteNo=" + inviteNo + ", inviteDate=" + inviteDate + ", playDate=" + playDate
				+ ", inviteTeamNo=" + inviteTeamNo + ", inviteTeamName=" + inviteTeamName + ", joinTeamNo=" + joinTeamNo
				+ ", joinTeamName=" + joinTeamName + ", groundNo=" + groundNo + ", groundName=" + groundName
				+ ", joinNo=" + joinNo + ", joinDate=" + joinDate + ", playResultInvite=" + playResultInvite
				+ ", playResultJoin=" + playResultJoin + ", matchingProgressNo=" + matchingProgressNo + "]";
	}
	
	public int getInviteNo() {
		return inviteNo;
	}
	public void setInviteNo(int inviteNo) {
		this.inviteNo = inviteNo;
	}
	public String getInviteDate() {
		return inviteDate;
	}
	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
	}
	public Date getPlayDate() {
		return playDate;
	}
	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}
	public int getInviteTeamNo() {
		return inviteTeamNo;
	}
	public void setInviteTeamNo(int inviteTeamNo) {
		this.inviteTeamNo = inviteTeamNo;
	}
	public String getInviteTeamName() {
		return inviteTeamName;
	}
	public void setInviteTeamName(String inviteTeamName) {
		this.inviteTeamName = inviteTeamName;
	}
	public int getJoinTeamNo() {
		return joinTeamNo;
	}
	public void setJoinTeamNo(int joinTeamNo) {
		this.joinTeamNo = joinTeamNo;
	}
	public String getJoinTeamName() {
		return joinTeamName;
	}
	public void setJoinTeamName(String joinTeamName) {
		this.joinTeamName = joinTeamName;
	}
	public int getGroundNo() {
		return groundNo;
	}
	public void setGroundNo(int groundNo) {
		this.groundNo = groundNo;
	}
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public int getJoinNo() {
		return joinNo;
	}
	public void setJoinNo(int joinNo) {
		this.joinNo = joinNo;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public int getPlayResultInvite() {
		return playResultInvite;
	}
	public void setPlayResultInvite(int playResultInvite) {
		this.playResultInvite = playResultInvite;
	}
	public int getPlayResultJoin() {
		return playResultJoin;
	}
	public void setPlayResultJoin(int playResultJoin) {
		this.playResultJoin = playResultJoin;
	}
	public int getMatchingProgressNo() {
		return matchingProgressNo;
	}
	public void setMatchingProgressNo(int matchingProgressNo) {
		this.matchingProgressNo = matchingProgressNo;
	}
	

}
