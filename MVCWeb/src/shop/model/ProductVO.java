package shop.model;

import java.io.Serializable;
import java.sql.Date;

public class ProductVO implements Serializable{
	
	private int pnum;
	private String pname;
	private int upCode;
	private int downCode;
	
	private String pcompany;
	private String pimage1;
	private String pimage2;
	private String pimage3;
	
	private int pqty;
	private int price;
	private int saleprice;
	private String pspec;
	private String pcontents;
	private int point;
	private java.sql.Date pindate;
	
	private int totalPrice; // 판매가*수량 
	private int totalPoint; // 포인트*수량

	public ProductVO(){
		
	}

	public ProductVO(int pnum, String pname, int upCode, int downCode, String pcompany, String pimage1, String pimage2,
			String pimage3, int pqty, int price, int saleprice, String pspec, String pcontents, int point, Date pindate) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.upCode = upCode;
		this.downCode = downCode;
		this.pcompany = pcompany;
		this.pimage1 = pimage1;
		this.pimage2 = pimage2;
		this.pimage3 = pimage3;
		this.pqty = pqty;
		this.price = price;
		this.saleprice = saleprice;
		this.pspec = pspec;
		this.pcontents = pcontents;
		this.point = point;
		this.pindate = pindate;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public String getPcompany() {
		return pcompany;
	}

	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}

	public String getPimage1() {
		return pimage1;
	}

	public void setPimage1(String pimage1) {
		this.pimage1 = pimage1;
	}

	public String getPimage2() {
		return pimage2;
	}

	public void setPimage2(String pimage2) {
		this.pimage2 = pimage2;
	}

	public String getPimage3() {
		return pimage3;
	}

	public void setPimage3(String pimage3) {
		this.pimage3 = pimage3;
	}

	public int getPqty() {
		return pqty;
	}

	public void setPqty(int pqty) {
		this.pqty = pqty;
		
		/* 수량이 정해지면 totalPrice와 totalPoint를 연산하여 결정한다. */
		this.totalPrice = saleprice * this.pqty;
		this.totalPoint = point * this.pqty;
	}
	
	/** 할인률을 반환해주는 메소드 */
	public int getPercent() {
		// (정가 - 판매가)*100/정가
		int percent = (price-saleprice)*100/price;
		return percent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}

	public String getPspec() {
		return pspec;
	}

	public void setPspec(String pspec) {
		this.pspec = pspec;
	}

	public String getPcontents() {
		return pcontents;
	}

	public void setPcontents(String pcontents) {
		this.pcontents = pcontents;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public java.sql.Date getPindate() {
		return pindate;
	}

	public void setPindate(java.sql.Date pindate) {
		this.pindate = pindate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public int getTotalPoint() {
		return totalPoint;
	}
    
}////////////////////////////////////////////////////////










