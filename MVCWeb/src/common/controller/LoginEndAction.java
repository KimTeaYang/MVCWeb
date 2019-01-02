package common.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.LoginDAOMybatis;
import user.model.NotUserException;
import user.model.UserVO;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String saveId = req.getParameter("saveId");
		
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		LoginDAOMybatis dao = new LoginDAOMybatis();
		int userCount = dao.getUserCount();
		//UserVO user = dao.findUserByUserid(userid);
		HttpSession session = req.getSession();
		try {
			UserVO user = dao.loginCheck(userid, pwd);
			if(user!=null) {
				//�α��� ������ �޾Ҵٸ�
				session.setAttribute("loginUser", user);
				
				Cookie ck= new Cookie("saveId", user.getUserid());
				if(saveId!=null) {
					//���̵� ���忡 üũ�ߴٸ�
					ck.setMaxAge(7*24*60*60); //7�ϰ� ��ȿ
				}else {
					//���̵� ���忡 üũ��������
					ck.setMaxAge(0); // ��Ű����
				}
				ck.setPath("/");
				res.addCookie(ck); // Ŭ���̾�Ʈ�� ��Ű �о�ֱ�
			}
			
			String returnPage = (String)session.getAttribute("returnPage");
			
			if(returnPage==null) {
				this.setViewPage(req.getContextPath()+"/index.do");
			}else {
				this.setViewPage(req.getContextPath()+"/"+returnPage);
			}
			
			this.setRedirect(true);
		}catch(NotUserException e) {
			//�α��� ������ ������ ���
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("memo/message.jsp");
			this.setRedirect(false);
		}
		
//		req.setAttribute("user", user);
//		req.setAttribute("userCount", userCount);
//		this.setViewPage("mybatisResult.jsp");
//		this.setRedirect(false);
	}

}