package review.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ReviewDAOMyBatis {
	
	private SqlSession ses;
	private SqlSessionFactory fac;
	private final String NS = "review.model.ReviewMapper";
	
	public ReviewDAOMyBatis() {
		String resource = "config/config.xml";
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			
			fac = new SqlSessionFactoryBuilder().build(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int reviewWrite(ReviewVO rvo) {
		
		try {
			ses = fac.openSession(true);
			
			int n = ses.insert(NS+".reviewWrite", rvo);
			
			return n;
		} finally {
			close();
		}
		
	}
	
	/** Ư�� ��ǰ�� ���� ��ǰ�� ������ �������� */
	public List<ReviewVO> getReviewList(String pnum) {
		
		try {
			ses = fac.openSession(true);
			
			List<ReviewVO> rvList = ses.selectList(NS+".reviewList", pnum);
			
			return rvList;
		} finally {
			close();
		}
		
	}
	
	private void close() {
		if(ses!=null) ses.close();
	}

}