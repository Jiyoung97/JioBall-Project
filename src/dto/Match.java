package dto;

public class Match {
	
	private int inviteNo;
	private String inviteDate;
	private String playDate;
	private String inviteTitle;
	private String inviteComment;
	private String playLocal;
	private int matchingProgressType;
	private int playPerson;
	private int playType;
	private int teamNo;
	private int groundNo;
	private int matchRnum;
	private String teamName;
	
	
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	public String getPlayDate() {
		return playDate;
	}
	public void setPlayDate(String playDate) {
		this.playDate = playDate;
	}
	public String getInviteTitle() {
		return inviteTitle;
	}
	public void setInviteTitle(String inviteTitle) {
		this.inviteTitle = inviteTitle;
	}
	public String getInviteComment() {
		return inviteComment;
	}
	public void setInviteComment(String inviteComment) {
		this.inviteComment = inviteComment;
	}
	public String getPlayLocal() {
		return playLocal;
	}
	public void setPlayLocal(String playLocal) {
		this.playLocal = playLocal;
	}
	public int getMatchingProgressType() {
		return matchingProgressType;
	}
	public void setMatchingProgressType(int matchingProgressType) {
		this.matchingProgressType = matchingProgressType;
	}
	public int getPlayPerson() {
		return playPerson;
	}
	public void setPlayPerson(int playPerson) {
		this.playPerson = playPerson;
	}
	public int getPlayType() {
		return playType;
	}
	public void setPlayType(int playType) {
		this.playType = playType;
	}
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public int getGroundNo() {
		return groundNo;
	}
	public void setGroundNo(int groundNo) {
		this.groundNo = groundNo;
	}
	public int getMatchRnum() {
		return matchRnum;
	}
	public void setMatchRnum(int matchRnum) {
		this.matchRnum = matchRnum;
	}
	@Override
	public String toString() {
		return "Match [inviteNo=" + inviteNo + ", inviteDate=" + inviteDate + ", playDate=" + playDate
				+ ", inviteTitle=" + inviteTitle + ", inviteComment=" + inviteComment + ", playLocal=" + playLocal
				+ ", matchingProgressType=" + matchingProgressType + ", playPerson=" + playPerson + ", playType="
				+ playType + ", teamNo=" + teamNo + ", groundNo=" + groundNo + ", matchRnum=" + matchRnum
				+ ", teamName=" + teamName + "]";
	}
	
	
	

}
