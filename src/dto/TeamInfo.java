package dto;

public class TeamInfo {
	private int teamNo;
	private int userNo;
	private String teamName;
	private int teamGender;
	private String teamIntroduce;
	private String teamUniform;
	private double teamManner;
	private int teamMatches;
	private int teamVictories;
	private int teamDefeats;
	private int playTypeNo;
	private int playLocalNo;
	
	@Override
	public String toString() {
		return "TeamInfo [teamNo=" + teamNo + ", userNo=" + userNo + ", teamName=" + teamName + ", teamGender="
				+ teamGender + ", teamIntroduce=" + teamIntroduce + ", teamUniform=" + teamUniform + ", teamManner="
				+ teamManner + ", teamMatches=" + teamMatches + ", teamVictories=" + teamVictories + ", teamDefeats="
				+ teamDefeats + ", playTypeNo=" + playTypeNo + ", playLocalNo=" + playLocalNo + "]";
	}
	
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamGender() {
		return teamGender;
	}
	public void setTeamGender(int teamGender) {
		this.teamGender = teamGender;
	}
	public String getTeamIntroduce() {
		return teamIntroduce;
	}
	public void setTeamIntroduce(String teamIntroduce) {
		this.teamIntroduce = teamIntroduce;
	}
	public String getTeamUniform() {
		return teamUniform;
	}
	public void setTeamUniform(String teamUniform) {
		this.teamUniform = teamUniform;
	}
	public double getTeamManner() {
		return teamManner;
	}
	public void setTeamManner(double teamManner) {
		this.teamManner = teamManner;
	}
	public int getTeamMatches() {
		return teamMatches;
	}
	public void setTeamMatches(int teamMatches) {
		this.teamMatches = teamMatches;
	}
	public int getTeamVictories() {
		return teamVictories;
	}
	public void setTeamVictories(int teamVictories) {
		this.teamVictories = teamVictories;
	}
	public int getTeamDefeats() {
		return teamDefeats;
	}
	public void setTeamDefeats(int teamDefeats) {
		this.teamDefeats = teamDefeats;
	}
	public int getPlayTypeNo() {
		return playTypeNo;
	}
	public void setPlayTypeNo(int playTypeNo) {
		this.playTypeNo = playTypeNo;
	}
	public int getPlayLocalNo() {
		return playLocalNo;
	}
	public void setPlayLocalNo(int playLocalNo) {
		this.playLocalNo = playLocalNo;
	}
	
}
