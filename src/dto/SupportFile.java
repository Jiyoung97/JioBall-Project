package dto;

public class SupportFile {
	
	private int supportNo;
	private int supportFileNo;
	private String supportFileOriginName;
	private String supportFileStoredName;
	private String supportFileType;
	private String supportFilePath;
	private int supportFileSize;
	
	
	public int getSupportNo() {
		return supportNo;
	}
	public void setSupportNo(int supportNo) {
		this.supportNo = supportNo;
	}
	public int getSupportFileNo() {
		return supportFileNo;
	}
	public void setSupportFileNo(int supportFileNo) {
		this.supportFileNo = supportFileNo;
	}
	public String getSupportFileOriginName() {
		return supportFileOriginName;
	}
	public void setSupportFileOriginName(String supportFileOriginName) {
		this.supportFileOriginName = supportFileOriginName;
	}
	public String getSupportFileStoredName() {
		return supportFileStoredName;
	}
	public void setSupportFileStoredName(String supportFileStoredName) {
		this.supportFileStoredName = supportFileStoredName;
	}
	public String getSupportFileType() {
		return supportFileType;
	}
	public void setSupportFileType(String supportFileType) {
		this.supportFileType = supportFileType;
	}
	public String getSupportFilePath() {
		return supportFilePath;
	}
	public void setSupportFilePath(String supportFilePath) {
		this.supportFilePath = supportFilePath;
	}
	public int getSupportFileSize() {
		return supportFileSize;
	}
	public void setSupportFileSize(int supportFileSize) {
		this.supportFileSize = supportFileSize;
	}
	@Override
	public String toString() {
		return "SupportFile [supportNo=" + supportNo + ", supportFileNo=" + supportFileNo + ", supportFileOriginName="
				+ supportFileOriginName + ", supportFileStoredName=" + supportFileStoredName + ", supportFileType="
				+ supportFileType + ", supportFilePath=" + supportFilePath + ", supportFileSize=" + supportFileSize
				+ "]";
	}
	
	
	
	
	
	

}
