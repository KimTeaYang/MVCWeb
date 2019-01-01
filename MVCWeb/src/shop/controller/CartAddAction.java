package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import shop.model.CartBean;
import shop.model.ProductDAO;
import shop.model.ProductVO;

public class CartAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 상품번호와 수량 받아오기
		String pnumStr = req.getParameter("pnum").trim();
		String oqtyStr = req.getParameter("oqty").trim();
		
		if(pnumStr==null||oqtyStr==null||pnumStr.isEmpty()||oqtyStr.isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		//장바구니에 추가할 상품정보를 DB에서 가져오자.
		ProductDAO dao = new ProductDAO();
		ProductVO item = dao.selectProductByPnum(pnumStr);
		
		CartBean cart = null;
		//session에 저장된 장바구니빈이 있는지 꺼내보자. 없으면 새로 생성
		
		HttpSession ses = req.getSession();
		
		cart=(CartBean)ses.getAttribute("cartBeanAdmin"); // "cartBean"+ID
		
		if(cart==null) {
			cart = new CartBean();
		}
		
		int pnum = Integer.parseInt(pnumStr);
		int oqty = Integer.parseInt(oqtyStr);
		
		cart.addProduct(pnum, oqty, item);
		ses.setAttribute("cartBeanAdmin", cart);
		
		this.setViewPage("cartList.do");
		this.setRedirect(true);
	}
}