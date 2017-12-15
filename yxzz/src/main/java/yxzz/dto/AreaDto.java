package yxzz.dto;

public class AreaDto {

	private int areaId;

	private String areaName;

	private String areaLng;

	private String areaLat;

	private String areaCityId;

	private String areaSubwayNum;

	private String areaCode;

	private String areaPropertyNum;

	private String condition1;

	private String condition2;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaLng() {
		return areaLng;
	}

	public void setAreaLng(String areaLng) {
		this.areaLng = areaLng;
	}

	public String getAreaLat() {
		return areaLat;
	}

	public void setAreaLat(String areaLat) {
		this.areaLat = areaLat;
	}

	public String getAreaCityId() {
		return areaCityId;
	}

	public void setAreaCityId(String areaCityId) {
		this.areaCityId = areaCityId;
	}

	public String getAreaSubwayNum() {
		return areaSubwayNum;
	}

	public void setAreaSubwayNum(String areaSubwayNum) {
		this.areaSubwayNum = areaSubwayNum;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaPropertyNum() {
		return areaPropertyNum;
	}

	public void setAreaPropertyNum(String areaPropertyNum) {
		this.areaPropertyNum = areaPropertyNum;
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

	@Override
	public String toString() {
		return "AreaDto [areaId=" + areaId + ", areaName=" + areaName + ", areaLng=" + areaLng + ", areaLat=" + areaLat
				+ ", areaCityId=" + areaCityId + ", areaSubwayNum=" + areaSubwayNum + ", areaCode=" + areaCode
				+ ", areaPropertyNum=" + areaPropertyNum + ", condition1=" + condition1 + ", condition2=" + condition2
				+ ", getAreaId()=" + getAreaId() + ", getAreaName()=" + getAreaName() + ", getAreaLng()=" + getAreaLng()
				+ ", getAreaLat()=" + getAreaLat() + ", getAreaCityId()=" + getAreaCityId() + ", getAreaSubwayNum()="
				+ getAreaSubwayNum() + ", getAreaCode()=" + getAreaCode() + ", getAreaPropertyNum()="
				+ getAreaPropertyNum() + ", getCondition1()=" + getCondition1() + ", getCondition2()=" + getCondition2()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
