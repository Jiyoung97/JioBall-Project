package dto;

public class MatchJoin {
	
	private int joinNo;
	private int joinTeamNo;
	private String joinTeamName;
	private String joinName;
	
	public int getJoinNo() {
		return joinNo;
	}
	public void setJoinNo(int joinNo) {
		this.joinNo = joinNo;
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
	public String getJoinName() {
		return joinName;
	}
	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}
	@Override
	public String toString() {
		return "MatchJoin [joinNo=" + joinNo + ", joinTeamNo=" + joinTeamNo + ", joinTeamName=" + joinTeamName
				+ ", joinName=" + joinName + "]";
	}
	
	

}
