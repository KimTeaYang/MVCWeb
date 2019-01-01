package shop.model;

import java.io.Serializable;

public class CategoryVO implements Serializable {
	
	private int upCode;
	private int downCode;
	private String upCgName;
	private String downCgName;
	
	public CategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryVO(int upCode, int downCode, String upCgName, String downCgName) {
		super();
		this.upCode = upCode;
		this.downCode = downCode;
		this.upCgName = upCgName;
		this.downCgName = downCgName;
	}

	public int getUpCode() {
		return upCode;
	}

	public void setUpCode(int upCode) {
		this.upCode = upCode;
	}

	public int getDownCode() {
		return downCode;
	}

	public void setDownCode(int downCode) {
		this.downCode = downCode;
	}

	public String getUpCgName() {
		return upCgName;
	}

	public void setUpCgName(String upCgName) {
		this.upCgName = upCgName;
	}

	public String getDownCgName() {
		return downCgName;
	}

	public void setDownCgName(String downCgName) {
		this.downCgName = downCgName;
	}
	
}
