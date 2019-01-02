package user.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class LoginDAOMybatis {

	private final String NS = "user.model.LoginMapper";
	private SqlSession ses;
	private SqlSessionFactory fac;
	
	public LoginDAOMybatis() {
		String resource = "config/config.xml"; // package/��������(config.xml)
		InputStream is = null;
		
		try {
			is=Resources.getResourceAsStream(resource);
			fac =  new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getUserCount() {
		try {
			ses = fac.openSession();
			int count = ses.selectOne(NS+".userCount"); // namespace + id
			
			return count;
		}finally {
			if(ses!=null) ses.close();
		}
	}
	
	public UserVO findUserByUserid(String userid) throws NotUserException {
		try {
			ses = fac.openSession();
			UserVO user = ses.selectOne(NS+".findUserByUserid",userid);
			
			if(user==null) {
				throw new NotUserException("�������� �ʴ� ȸ���Դϴ�");
			}
			
			return user;
		}finally {
			if(ses!=null) ses.close();
		}
	}
	
	public UserVO loginCheck(String userid, String pwd) throws NotUserException {
		UserVO user = findUserByUserid(userid);
		if(user!=null) {
			//���̵� �´ٸ�
			if(user.getPwd().equals(pwd)) {
				//����� ��ġ�Ѵٸ�
				return user;
			}
			
			throw new NotUserException("��й�ȣ�� ��ġ���� �ʾƿ�");
		}
		return null;
	}
	
}