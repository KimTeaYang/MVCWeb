package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import shop.model.CartBean;

public class CartDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String pnumStr = req.getParameter("pnum").trim();
		
		if(pnumStr==null||pnumStr.isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		int pnum = Integer.parseInt(pnumStr);
		
		HttpSession ses = req.getSession();
		
		CartBean cart = (CartBean)ses.getAttribute("cartBeanAdmin");
		if(cart!=null) {
			cart.removeProduct(pnum);
		}
		
		this.setViewPage("cartList.do");
		this.setRedirect(true);
		
		
	}

}
