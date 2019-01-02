package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();
		
		session.invalidate();
		
		String myctx = req.getContextPath();
		
		this.setViewPage(myctx+"/index.do");
		this.setRedirect(true);

	}

}
