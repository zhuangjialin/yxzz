package yxzz.dto;

public class SubwaylineDto {
	
	
	private int subwaylineId;
	
	private String subwaylineName;
	
	private String subwaylineLng;
	
	@Override
	public String toString() {
		return "SubwaylineDto [subwaylineId=" + subwaylineId + ", subwaylineName=" + subwaylineName + ", subwaylineLng="
				+ subwaylineLng + ", subwaylineLat=" + subwaylineLat + ", subwaylineSubwayNum=" + subwaylineSubwayNum
				+ ", subwaylinePropertyNum=" + subwaylinePropertyNum + ", condition1=" + condition1 + ", condition2="
				+ condition2 + "]";
	}

	public int getSubwaylineId() {
		return subwaylineId;
	}

	public void setSubwaylineId(int subwaylineId) {
		this.subwaylineId = subwaylineId;
	}

	public String getSubwaylineName() {
		return subwaylineName;
	}

	public void setSubwaylineName(String subwaylineName) {
		this.subwaylineName = subwaylineName;
	}

	public String getSubwaylineLng() {
		return subwaylineLng;
	}

	public void setSubwaylineLng(String subwaylineLng) {
		this.subwaylineLng = subwaylineLng;
	}

	public String getSubwaylineLat() {
		return subwaylineLat;
	}

	public void setSubwaylineLat(String subwaylineLat) {
		this.subwaylineLat = subwaylineLat;
	}

	public int getSubwaylineSubwayNum() {
		return subwaylineSubwayNum;
	}

	public void setSubwaylineSubwayNum(int subwaylineSubwayNum) {
		this.subwaylineSubwayNum = subwaylineSubwayNum;
	}

	public int getSubwaylinePropertyNum() {
		return subwaylinePropertyNum;
	}

	public void setSubwaylinePropertyNum(int subwaylinePropertyNum) {
		this.subwaylinePropertyNum = subwaylinePropertyNum;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	private String subwaylineLat;

	private int subwaylineSubwayNum;
	
	private int subwaylinePropertyNum;
	
	private String condition1;
	
	private String condition2;

	
	
	
	
	
	
	
	

}
