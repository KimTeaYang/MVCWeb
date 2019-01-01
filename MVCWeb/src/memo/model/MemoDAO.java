package memo.model;

import java.sql.*;
import java.util.*;
import common.db.*;

/* DAO (Data Access Object)
 *  - biz logic�� ���� Ŭ����
 *  - Persistence ����(���Ӽ� ����)
 *    DB�� �����Ͽ� insert, delete, update, select ���� �����Ѵ�.
 * */
public class MemoDAO extends DAOBase{
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	/** ���� �޸� ����ϴ� �޼ҵ�
	 *   - INSERT���� �����Ѵ�.
	 * */
	public int insertMemo(MemoVO memo) {
		try {
			con = ds.getConnection(); 
			
			String sql = "INSERT INTO MEMO"
					+ " VALUES(MEMO_SEQ.NEXTVAL,?,?,SYSDATE)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			close();
		}
	}
	
	/** ��� �� ��� �������� (R)
	 *   - SELECT���� ����
	 * */
	
	public ArrayList<MemoVO> listMemo(){
		try {
			con = ds.getConnection(); 
			
			String sql = "SELECT * FROM MEMO"
					+ " ORDER BY IDX DESC";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<MemoVO> arr = makeList(rs);
			
			return arr;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	
	/** ���������� 5���� ������ �޼ҵ� */
	public ArrayList<MemoVO> listMemo(int start, int end) {
		try {
			con = ds.getConnection(); 
			
			String sql = "select * from ( " + 
					"select a.*,rownum rn from ( " + 
					"select * from memo order by idx desc) a" + 
					") " + 
					"where rn between ? and ?"; 
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();
			
			ArrayList<MemoVO> arr = makeList(rs);
			
			return arr;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	
	public ArrayList<MemoVO> makeList(ResultSet rs)
		throws SQLException {
			ArrayList<MemoVO> arr = new ArrayList<>();
			while(rs.next()) {
				int idx = rs.getInt(1);
				String name = rs.getString(2);
				String msg = rs.getString(3);
				java.sql.Date wdate = rs.getDate(4);
				
				MemoVO record = new MemoVO(idx,name,msg,wdate);
				arr.add(record);
			}
			return arr;
	}
	
	public MemoVO selectMemo(int idx) {
		try {
			con = ds.getConnection(); 
			
			String sql = "SELECT * FROM MEMO"
					+ " WHERE IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			
			ArrayList<MemoVO> arr = makeList(rs);
			if(arr!=null && arr.size()==1) {
				MemoVO mvo = arr.get(0);
				return mvo;
			}
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
	}
	
	public int deleteMemo(int idx) {
		try {
			con = ds.getConnection(); 
			
			String sql = "DELETE FROM MEMO"
					+ " WHERE IDX=?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, idx);
			
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			close();
		}
	}
	
	/** �޸���� ����ó���ϴ� �޼ҵ�
	 *   - UPDATE���� ����
	 * */
	
	public int updateMemo(MemoVO memo) {
		try {
			con = ds.getConnection(); 
			
			String sql = "UPDATE MEMO"
					+ " SET NAME=?, MSG=?"
					+ " WHERE IDX=?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			ps.setInt(3, memo.getIdx());
			
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			close();
		}
	}
	
	/** �ۼ��� �Ǵ� �޸𳻿����� �˻��ϴ� �޼ҵ�
	 *   - SELECT���� ���� 
	 *   - LIKE�� ���*/
	public ArrayList<MemoVO> findMemo(
			String colName, String keyword) {
		try {
			con = ds.getConnection(); 
			
			String sql = "SELECT * FROM MEMO"
					+ " WHERE "+colName+" LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery();
			
			ArrayList<MemoVO> arr = makeList(rs);
			
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	
	/** �� �Խñ� �� �������� */
	public int getTotalCount() throws SQLException {
		try {
			con = ds.getConnection();
			
			String sql = "SELECT COUNT(IDX) CNT FROM MEMO";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			int cnt = rs.getInt("CNT");
			
			return cnt;
		}finally {
			close();
		}
	}
	
	
	/** DB���� �ڿ��� �ݳ��ϴ� �޼ҵ� */
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