package user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.DAOBase;

public class PostDAO extends DAOBase {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public PostDAO() {
		super();
	}
	
	/** 전체 회원 목록 반환하는 메소드 **/
	public ArrayList<PostVO> select(String doro_kor) throws SQLException{
		try {
			con = ds.getConnection();
			
			String sql = "SELECT NEW_POST_CODE, SIDO_KOR, SIGUNGU_KOR, DORO_KOR, LAW_DONG_NAME, ADMIN_DONG_NAME, JIBEON_BONBEON, JIBEON_BUBEON"
					+ " FROM ZIPCODE WHERE DORO_KOR LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+doro_kor+"%");
			rs = ps.executeQuery();
			
			return makeList(rs);
		}finally {
			close();
		}
	}

	private ArrayList<PostVO> makeList(ResultSet rs)
	throws SQLException{
		ArrayList<PostVO> arr = new ArrayList<>();
		while(rs.next()) {
			String new_post_code = rs.getString(1);
			String sido_kor = rs.getString(2);
			String sigungu_kor = rs.getString(3);
			String doro_kor = rs.getString(4);
			String law_dong_name = rs.getString(5);
			String admin_dong_name = rs.getString(6);
			int jibeon_bonbeon = rs.getInt(7);
			int jibeon_bubeon = rs.getInt(8);
			PostVO postVO = new PostVO(new_post_code,sido_kor,
					sigungu_kor,doro_kor,law_dong_name,
					admin_dong_name,jibeon_bonbeon,jibeon_bubeon);
			arr.add(postVO);
		}
		
		if(arr.size()==0) {
			return null;
		}
		return arr;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			
		}
	}
}
