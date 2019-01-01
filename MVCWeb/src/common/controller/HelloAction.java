package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		System.out.println("HelloAction�� execute()ȣ���...");
		
		req.setAttribute("msg", "HelloAction���� ������");
		
		this.setViewPage("/hello.jsp");
		this.setRedirect(false);
	}
	
}