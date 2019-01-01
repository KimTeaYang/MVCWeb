package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.model.ProductDAO;
import shop.model.ProductVO;

public class ProductDetailAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String pnum = req.getParameter("pnum").trim();
		
		if(pnum==null||pnum.isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		ProductDAO dao = new ProductDAO();
		ProductVO pvo = dao.selectProductByPnum(pnum);
		
		req.setAttribute("pvo", pvo);
		
		this.setViewPage("/shop/prodDetail.jsp");
		this.setRedirect(false);
	}

}
