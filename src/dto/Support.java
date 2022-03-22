package dto;

public class Support {
	
	private int supportNo;
	private int supportRownum;
	private String supportType;
	private String supportTitle;
	private String supportContent;
	private String supportDate;
	private int supportState;
	private String supportAnswer;
	private String supportAnswerDate;
	private String supportWriter;
	
	
	public int getSupportNo() {
		return supportNo;
	}
	public void setSupportNo(int supportNo) {
		this.supportNo = supportNo;
	}
	public int getSupportRownum() {
		return supportRownum;
	}
	public void setSupportRownum(int supportRownum) {
		this.supportRownum = supportRownum;
	}
	public String getSupportType() {
		return supportType;
	}
	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}
	public String getSupportTitle() {
		return supportTitle;
	}
	public void setSupportTitle(String supportTitle) {
		this.supportTitle = supportTitle;
	}
	public String getSupportContent() {
		return supportContent;
	}
	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}
	public String getSupportDate() {
		return supportDate;
	}
	public void setSupportDate(String supportDate) {
		this.supportDate = supportDate;
	}
	public int getSupportState() {
		return supportState;
	}
	public void setSupportState(int supportState) {
		this.supportState = supportState;
	}
	public String getSupportAnswer() {
		return supportAnswer;
	}
	public void setSupportAnswer(String supportAnswer) {
		this.supportAnswer = supportAnswer;
	}
	public String getSupportAnswerDate() {
		return supportAnswerDate;
	}
	public void setSupportAnswerDate(String supportAnswerDate) {
		this.supportAnswerDate = supportAnswerDate;
	}
	public String getSupportWriter() {
		return supportWriter;
	}
	public void setSupportWriter(String supportWriter) {
		this.supportWriter = supportWriter;
	}
	@Override
	public String toString() {
		return "Support [supportNo=" + supportNo + ", supportRownum=" + supportRownum + ", supportType=" + supportType
				+ ", supportTitle=" + supportTitle + ", supportContent=" + supportContent + ", supportDate="
				+ supportDate + ", supportState=" + supportState + ", supportAnswer=" + supportAnswer
				+ ", supportAnswerDate=" + supportAnswerDate + ", supportWriter=" + supportWriter + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
