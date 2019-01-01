package shop.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {
	
	private DataSource ds;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ProductDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/oracle/myshop");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int productInsert(ProductVO prod) throws SQLException{
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO PRODUCT"
					+ " VALUES(PNUM_SEQ.NEXTVAL,?,?,?,?,?,?,"
					+ " ?,?,?,?,?,?,?,SYSDATE)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1,prod.getUpCode());
			ps.setInt(2, prod.getDownCode());
			ps.setString(3, prod.getPname());
			ps.setString(4, prod.getPimage1());
			ps.setString(5, prod.getPimage2());
			ps.setString(6, prod.getPimage3());
			ps.setInt(7, prod.getPrice());
			ps.setInt(8, prod.getSaleprice());
			ps.setInt(9, prod.getPqty());
			ps.setInt(10, prod.getPoint());
			ps.setString(11, prod.getPspec());
			ps.setString(12, prod.getPcontents());
			ps.setString(13, prod.getPcompany());
			
			int n = ps.executeUpdate();
			
			return n;
		} finally{
			close();
		}
	}
	
	/** pspec-NEW,HIT,BEST 별로 상품 목록 가져오는 메소드 */
	public List<ProductVO> selectByPspec(String pspec) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT pnum,pname,upCode,downCode,pcompany,"
					+ " nvl(pimage1,'noimage.JPG') pimage1,"
					+ " nvl(pimage2,'noimage.JPG') pimage2,"
					+ " nvl(pimage3,'noimage.JPG') pimage3,"
					+ " pqty,price,saleprice,pspec,pcontents,point,pindate"
					+ " FROM PRODUCT WHERE PSPEC=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, pspec.toUpperCase());
			rs = ps.executeQuery();
			
			return makeList(rs);
		} finally {
			close();
		}
	}
	
	/** 상품번호(PK)로 상품정보 가져오는 메소드 */
	public ProductVO selectProductByPnum(String pnum) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT pnum,pname,upCode,downCode,pcompany,"
					+ " nvl(pimage1,'noimage.JPG') pimage1,"
					+ " nvl(pimage2,'noimage.JPG') pimage2,"
					+ " nvl(pimage3,'noimage.JPG') pimage3,"
					+ " pqty,price,saleprice,pspec,pcontents,point,pindate"
					+ " FROM PRODUCT WHERE PNUM=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pnum);
			rs = ps.executeQuery();
			
			List<ProductVO> arr = makeList(rs);
			if(arr!=null&&arr.size()==1) {
				return arr.get(0);
			}
			
			return null;
		} finally {
			close();
		}
	}
	
	private List<ProductVO> makeList(ResultSet rs) throws SQLException {
		List<ProductVO> arr = new ArrayList<>();
		while(rs.next()) {
			int pnum = rs.getInt("pnum");
			String pname = rs.getString("pname");
			int upCode = rs.getInt("upCode");
			int downCode = rs.getInt("downCode");
			
			String pcompany = rs.getString("pcompany");
			String pimage1 = rs.getString("pimage1");
			String pimage2 = rs.getString("pimage2");
			String pimage3 = rs.getString("pimage3");
			
			int pqty = rs.getInt("pqty");
			int price = rs.getInt("price");
			int saleprice = rs.getInt("saleprice");
			String pspec = rs.getString("pspec");
			
			String pcontents = rs.getString("pcontents");
			int point = rs.getInt("point");
			java.sql.Date pindate = rs.getDate("pindate");
			
			ProductVO item = new ProductVO(pnum, pname, upCode, downCode, pcompany, pimage1, pimage2, pimage3,
					pqty, price, saleprice, pspec, pcontents, point, pindate);
			arr.add(item);
		}
		return arr;
	}

	/** DB관련 자원을 반납하는 메소드 */
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}catch(SQLException e) {
			System.out.println("e: "+e);
		}
	}
}