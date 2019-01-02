package review.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int getReviewCount(String pnum) {
		try {
			ses=fac.openSession();
			int count=ses.selectOne(NS+".reviewCount",pnum);
			return count;
		}finally {
			close();
		}
	}
	
	/** 특정 상품에 대한 상품평 리뷰목록 가져오기(페이징 처리) */
	public List<ReviewVO> getReviewList(String pnum,int start,int end) {
		
		try {
			ses = fac.openSession(true);
			
			Map<String,Object> val = new HashMap<>();
			val.put("pnum", pnum);
			val.put("start", start);
			val.put("end", end);
			
			List<ReviewVO> rvList = ses.selectList(NS+".reviewList", val);
			
			return rvList;
		} finally {
			close();
		}
		
	}
	
	/** 리뷰글에 업로드한 파일명 가져오기 */
	public String getReviewUploadFile(String ridx) {
		try {
			ses = fac.openSession();
			
			String filename = ses.selectOne(NS+".getUploadFile", ridx);
			
			return filename;
			
		} finally {
			close();
		}
	}
	//reviewDelete
	public int deleteReview(String ridx) {
		try {
			ses = fac.openSession(true);
			
			int n = ses.delete(NS+".reviewDelete", ridx);
			
			return n;
			
		} finally {
			close();
		}
	}
	
	private void close() {
		if(ses!=null) ses.close();
	}

}