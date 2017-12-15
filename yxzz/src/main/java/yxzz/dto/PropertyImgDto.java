package yxzz.dto;

public class PropertyImgDto {

	private int propertyImgId;

	private String propertyImgName;

	private int propertyImgPropertyId;

	private String propertyImgRemark;

	public int getPropertyImgId() {
		return propertyImgId;
	}

	public void setPropertyImgId(int propertyImgId) {
		this.propertyImgId = propertyImgId;
	}

	@Override
	public String toString() {
		return "PropertyImgDto [propertyImgId=" + propertyImgId + ", propertyImgName=" + propertyImgName
				+ ", propertyImgPropertyId=" + propertyImgPropertyId + ", propertyImgRemark=" + propertyImgRemark
				+ ", condition1=" + condition1 + ", condition2=" + condition2 + "]";
	}

	public String getPropertyImgName() {
		return propertyImgName;
	}

	public void setPropertyImgName(String propertyImgName) {
		this.propertyImgName = propertyImgName;
	}

	public int getPropertyImgPropertyId() {
		return propertyImgPropertyId;
	}

	public void setPropertyImgPropertyId(int propertyImgPropertyId) {
		this.propertyImgPropertyId = propertyImgPropertyId;
	}

	public String getPropertyImgRemark() {
		return propertyImgRemark;
	}

	public void setPropertyImgRemark(String propertyImgRemark) {
		this.propertyImgRemark = propertyImgRemark;
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

	private String condition1;
	private String condition2;
}
