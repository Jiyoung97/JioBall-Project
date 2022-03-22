package dto;

public class ManagerInfo {
	
	private int managerNo;
	private String managerId;
	private String managerPw;
	
	@Override
	public String toString() {
		return "ManagerInfo [managerNo=" + managerNo + ", managerId=" + managerId + ", managerPw=" + managerPw + "]";
	}

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}
	
}
