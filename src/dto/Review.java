package dto;

import java.util.Date;

public class Review {
	private int reviewNo;
	private Date reviewDate;
	private int reviewResult;
	private double reviewManner;
	private int inviteNo;
	private int joinNo;
	
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewDate=" + reviewDate + ", reviewResult=" + reviewResult
				+ ", reviewManner=" + reviewManner + ", inviteNo=" + inviteNo + ", joinNo=" + joinNo + "]";
	}
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(int reviewResult) {
		this.reviewResult = reviewResult;
	}
	public double getReviewManner() {
		return reviewManner;
	}
	public void setReviewManner(double reviewManner) {
		this.reviewManner = reviewManner;
	}
	public int getInviteNo() {
		return inviteNo;
	}
	public void setInviteNo(int inviteNo) {
		this.inviteNo = inviteNo;
	}
	public int getJoinNo() {
		return joinNo;
	}
	public void setJoinNo(int joinNo) {
		this.joinNo = joinNo;
	}
	
}
