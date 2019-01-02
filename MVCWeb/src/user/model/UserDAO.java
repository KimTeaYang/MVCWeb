package user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.DAOBase;

//DAO(Data Access Object) - 데이터 처리 계층. Persistence계층
//CRUD의 기능 - biz logic을 구현한다.
public class UserDAO extends DAOBase {
	//DataSource ds를 상속받아 가지고 있음
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public UserDAO() {
		super();
	}
	
	/** 회원가입 처리 */
	public int createUser(UserVO user) throws SQLException {
		try {
			con = ds.getConnection();
			
			String sql = "INSERT INTO MEMBER"
					+ " VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,SYSDATE,0,0)";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserid());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getHp1());
			ps.setString(6, user.getHp2());
			ps.setString(7, user.getHp3());
			ps.setString(8, user.getZipcode());
			ps.setString(9, user.getAddr1());
			ps.setString(10, user.getAddr2());
			
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	
	/** 아이디 중복체크 여부를 반환하는 메소드 */
	public boolean isDuplicatedId(String userid) 
	throws SQLException{
		try {
			con = ds.getConnection();
			
			String sql = "SELECT IDX FROM MEMBER WHERE USERID=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			boolean isRs = rs.next();
			
			return isRs;
		}finally {
			close();
		}
	}
	
	/** 전체 회원 목록 반환하는 메소드 */
	public ArrayList<UserVO> listUser() throws SQLException{
		try {
			con = ds.getConnection();
			
			String sql = "SELECT * "
					+ " FROM MEMBER ORDER BY IDX DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			return makeList(rs);
		}finally {
			close();
		}
	}
	
	/** 회원번호(idx=>PK)로 회원정보를 가져오는 메소드 */
	public UserVO selectUserByIdx(int idx) throws SQLException{
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM MEMBER WHERE IDX="+idx;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<UserVO> arr = makeList(rs);
			if(arr!=null && arr.size()==1) {
				UserVO userVO = arr.get(0);
				return userVO;
			}
			return null;
		} finally {
			close();
		}
	}
	
	/** 회원정보를 검색 조건에 따라 검색하는 메소드
	 *  findType : 0[모든 회원정보], 1[이름], 2[아이디],
	 *  		   3[연락처], 4[회원상태]
	 *  */
	public ArrayList<UserVO> findUser(String findType,
			String keyword,String mstate) throws SQLException{
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM MEMBER WHERE 1=1";
			String val = "";
			switch(findType) {
				case "1":
					sql+=" AND NAME LIKE ?";
					val="%"+keyword+"%";
					break;
				case "2":
					sql+=" AND USERID LIKE ?";
					val="%"+keyword+"%";
					break;
				case "3":
					sql+=" AND HP1||HP2||HP3 LIKE ?";
					val="%"+keyword+"%";
					break;
				case "4":
					sql+=" AND MSTATE=?";
					val=mstate;
					break;
			}
			
			ps = con.prepareStatement(sql);
			ps.setString(1,val);
			
			rs = ps.executeQuery();
			
			return makeList(rs);
			
		}finally {
			close();
		}
	}
	
	private ArrayList<UserVO> makeList(ResultSet rs)
	throws SQLException{
		ArrayList<UserVO> arr = new ArrayList<>();
		while(rs.next()) {
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
			UserVO userVO = new UserVO(idx,name,userid,pwd,
					email,hp1,hp2,hp3,zipcode,
					addr1,addr2,indate,mileage,mstate);
			arr.add(userVO);
		}
		
		if(arr.size()==0) {
			return null;
		}
		return arr;
	}
	
	/** 회원 탈퇴 또는 정지, 재활동 처리를 하는 메소드 */
	
	public int managerUser(int idx, int state) throws SQLException{
		try {
			con = ds.getConnection();
			
			String sql="UPDATE MEMBER SET MSTATE=? WHERE IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, idx);
			
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	
	/** 회원정보를 수정하는 메소드 */
	
	public int updateUser(UserVO userVO) throws SQLException{
		try {
			
			con = ds.getConnection();
			
			String sql="UPDATE MEMBER SET NAME=?,USERID=?,PWD=?,"
					+ "EMAIL=?,HP1=?,HP2=?,HP3=?,ZIPCODE=?,ADDR1=?,ADDR2=?,"
					+ "MSTATE=? WHERE IDX=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, userVO.getName());
			ps.setString(2, userVO.getUserid());
			ps.setString(3, userVO.getPwd());
			ps.setString(4, userVO.getEmail());
			ps.setString(5, userVO.getHp1());
			ps.setString(6, userVO.getHp2());
			ps.setString(7, userVO.getHp3());
			ps.setString(8, userVO.getZipcode());
			ps.setString(9, userVO.getAddr1());
			ps.setString(10, userVO.getAddr2());
			ps.setInt(11, userVO.getMstate());
			ps.setInt(12, userVO.getIdx());
			
			int n = ps.executeUpdate();
			return n;
			
		} finally {
			close();
		}
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