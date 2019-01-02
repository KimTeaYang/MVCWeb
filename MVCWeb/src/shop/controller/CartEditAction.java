package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import shop.model.CartBean;
import user.model.UserVO;

public class CartEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String pnumStr = req.getParameter("pnum").trim();
		String oqtyStr = req.getParameter("oqty").trim();
		
		if(pnumStr==null||pnumStr.isEmpty()||oqtyStr==null||oqtyStr.isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		int pnum = Integer.parseInt(pnumStr);
		int oqty = Integer.parseInt(oqtyStr);
		
		HttpSession ses = req.getSession();
		
		UserVO loginUser = (UserVO)ses.getAttribute("loginUser");
		String userid = loginUser.getUserid();
		
		CartBean cart = (CartBean)ses.getAttribute("cartBean"+userid);
		if(cart!=null) {
			try {
				cart.setEdit(pnum,oqty); // 수량이 음수일 경우 예외발생
			} catch(Exception e) {
				req.setAttribute("msg", e.getMessage());
				req.setAttribute("loc", "javascript:history.back()");
				this.setViewPage("memo/message.jsp");
				this.setRedirect(false);
				return;
			}
		}
		
		this.setViewPage("cartList.do");
		this.setRedirect(true);
		
	}

}
