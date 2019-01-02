package user.model;

import java.io.Serializable;

public class PostVO implements Serializable{
	private String new_post_code;
	private String sido_kor;
	private String sigungu_kor;
	private String doro_kor;
	private String law_dong_name;
	private String admin_dong_name;
	private int jibeon_bonbeon;
	private int jibeon_bubeon;
	
	public PostVO() {
		super();
	}

	public PostVO(String new_post_code, String sido_kor, String sigungu_kor, String doro_kor, String law_dong_name,
			String admin_dong_name, int jibeon_bonbeon, int jibeon_bubeon) {
		super();
		this.new_post_code = new_post_code;
		this.sido_kor = sido_kor;
		this.sigungu_kor = sigungu_kor;
		this.doro_kor = doro_kor;
		this.law_dong_name = law_dong_name;
		this.admin_dong_name = admin_dong_name;
		this.jibeon_bonbeon = jibeon_bonbeon;
		this.jibeon_bubeon = jibeon_bubeon;
	}

	public String getNew_post_code() {
		return new_post_code;
	}

	public void setNew_post_code(String new_post_code) {
		this.new_post_code = new_post_code;
	}

	public String getSido_kor() {
		return sido_kor;
	}

	public void setSido_kor(String sido_kor) {
		this.sido_kor = sido_kor;
	}

	public String getSigungu_kor() {
		return sigungu_kor;
	}

	public void setSigungu_kor(String sigungu_kor) {
		this.sigungu_kor = sigungu_kor;
	}

	public String getDoro_kor() {
		return doro_kor;
	}

	public void setDoro_kor(String doro_kor) {
		this.doro_kor = doro_kor;
	}

	public String getLaw_dong_name() {
		return law_dong_name;
	}

	public void setLaw_dong_name(String law_dong_name) {
		this.law_dong_name = law_dong_name;
	}

	public String getAdmin_dong_name() {
		return admin_dong_name;
	}

	public void setAdmin_dong_name(String admin_dong_name) {
		this.admin_dong_name = admin_dong_name;
	}

	public int getJibeon_bonbeon() {
		return jibeon_bonbeon;
	}

	public void setJibeon_bonbeon(int jibeon_bonbeon) {
		this.jibeon_bonbeon = jibeon_bonbeon;
	}

	public int getJibeon_bubeon() {
		return jibeon_bubeon;
	}

	public void setJibeon_bubeon(int jibeon_bubeon) {
		this.jibeon_bubeon = jibeon_bubeon;
	}
	
	public String getAllInfo() {
		return new_post_code+" "+sido_kor+" "+sigungu_kor+" "+doro_kor+" "+jibeon_bonbeon+"-"+jibeon_bubeon;
	}
	
	public String getInfo() {
		return sido_kor+" "+sigungu_kor+" "+doro_kor+" "+jibeon_bonbeon+"-"+jibeon_bubeon;
	}
	
}
