package review.model;

import java.io.Serializable;
import java.sql.Date;

public class ReviewVO implements Serializable {
	
	private int ridx; //리뷰글번호
	private String title; //제목
	private String content; //상품평 내용
	private int score; //평가점수
	private String filename; //첨부파일
	private Date indate; // 등록일
	private String midx_fk; //작성자 회원번호
	private String pnum_fk; // 상품번호
	
	private String userid; //작성자 id
	private String name; //작성자 이름
	
	public ReviewVO() {
		super();
	}

	public ReviewVO(int ridx, String title, String content, int score, String filename, Date indate, String midx_fk,
			String pnum_fk) {
		super();
		this.ridx = ridx;
		this.title = title;
		this.content = content;
		this.score = score;
		this.filename = filename;
		this.indate = indate;
		this.midx_fk = midx_fk;
		this.pnum_fk = pnum_fk;
	}

	public int getRidx() {
		return ridx;
	}

	public void setRidx(int ridx) {
		this.ridx = ridx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getMidx_fk() {
		return midx_fk;
	}

	public void setMidx_fk(String midx_fk) {
		this.midx_fk = midx_fk;
	}

	public String getPnum_fk() {
		return pnum_fk;
	}

	public void setPnum_fk(String pnum_fk) {
		this.pnum_fk = pnum_fk;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
