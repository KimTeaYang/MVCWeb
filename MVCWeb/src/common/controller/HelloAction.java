package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		System.out.println("HelloAction의 execute()호출됨...");
		
		req.setAttribute("msg", "HelloAction에서 저장함");
		
		this.setViewPage("/hello.jsp");
		this.setRedirect(false);
	}
	
}