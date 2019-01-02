package user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.db.DAOBase;

public class LoginDAO extends DAOBase {
	
	/*private ConnectionPoolBean pool;*/ //ConnectionPool property
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
/*
	public ConnectionPoolBean getPool() {
		return pool;
	}
	public void setPool(ConnectionPoolBean pool) {
		this.pool = pool;
	}
*/
	
	public LoginDAO() {
		super();
	}
	
	/** userid(unique constraint)로 회원정보를 가져오는 메소드 */
	public UserVO findUserByUserid(String userid) 
	throws SQLException, NotUserException {
		try {
			//con = DBUtil.getCon();
			con = ds.getConnection();
			String sql = "SELECT * FROM memberView WHERE USERID=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			ArrayList<UserVO> arr = makeList(rs);
			
			if(arr!=null && arr.size()==1) {
				UserVO userVO = arr.get(0);
				return userVO;
			}
 
			//해당 아이디가 없는 경우 예외를 발생시킨다
			throw new NotUserException("존재하지 않는 회원입니다.");
		}finally{
			close();
		}
	}
	
	/** 로그인 여부를 체크하는 메소드 */
	public UserVO loginCheck(String userid, String pwd) 
	throws SQLException, NotUserException {
		
		//1. userid로 회원정보 가져오는 메소드 호출
		UserVO loginUser = this.findUserByUserid(userid);
		
		//2. 아이디가 존재한다면
		if(loginUser!=null) {
			// 비밀번호 일치여부를 체크하자
			String dbPwd = loginUser.getPwd();
			if(!dbPwd.equals(pwd)) {
				//비밀번호가 일치하지 않으면 예외 발생
				throw new NotUserException("비밀번호가 틀려요");
			}
			
			// 정지회원 및 탈퇴회원 로그인 차단
			/*int mstate = loginUser.getMstate();
			if(mstate==-1) {
				throw new NotUserException("정지 회원입니다.");
			}else if(mstate==-2) {
				throw new NotUserException("탈퇴 회원입니다.");
			}*/
		}
		//비밀번호가 일치한다면 해당 회원정보 반환
		return loginUser;
	}

	private ArrayList<UserVO> makeList(ResultSet rs) 
	throws SQLException {
		ArrayList<UserVO> arr = new ArrayList<>();
		while (rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String userid = rs.getString("userid");
			String pwd = rs.getString("pwd");
			String email = rs.getString("email");
			String hp1 = rs.getString("hp1");
			String hp2 = rs.getString("hp2");
			String hp3 = rs.getString("hp3");
			String zipcode = rs.getString("zipcode");
			String addr1 = rs.getString("addr1");
			String addr2 = rs.getString("addr2");
			java.sql.Date indate = rs.getDate("indate");
			int mileage = rs.getInt("mileage");
			int mstate = rs.getInt("mstate");
			UserVO userVO = new UserVO(idx, name, userid, pwd, email, hp1, hp2, hp3, zipcode, addr1, addr2, indate,
					mileage, mstate);
			arr.add(userVO);
		}

		if (arr.size() == 0) {
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
			if (con != null) con.close();
			//if(con!=null) pool.returnCon(con);
		} catch (SQLException e) {
			
		}
	}
}