package memo.model;

import java.sql.Date;

/*
 * M : MemoVO (Value Object)=> Domain계층
		db테이블의 레코드를 Object화해서 옮겨놓은 클래스.
		사용자가 입력한 값을 담거나, db에서 가져온 값을 담을 때 사용한다.
	   MemoDAO (Data Access Object)=> Persistence계층
		데이터베이스에 접근하여 비즈니스 로직(CRUD)을 수행하는 클래스.
   V : MemoGui (View계층)=> Presentation 계층
   C : Controller
------------------------------------------------
   UI Delegation -- Model
*/

public class MemoVO implements java.io.Serializable {
	private int idx;
	private String name;
	private String msg;
	private java.sql.Date wdate;
	
	public MemoVO() {
		
	}

	public MemoVO(int idx, String name, String msg, Date wdate) {
		super();
		this.idx = idx;
		this.name = name;
		this.msg = msg;
		this.wdate = wdate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}

	@Override
	public String toString() {
		return "MemoVO [idx=" + idx + ", name=" + name + ", msg=" + msg + ", wdate=" + wdate + "]";
	}
	
}