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
		// ��ǰ��ȣ�� ���� �޾ƿ���
		String pnumStr = req.getParameter("pnum").trim();
		String oqtyStr = req.getParameter("oqty").trim();
		
		if(pnumStr==null||oqtyStr==null||pnumStr.isEmpty()||oqtyStr.isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		//��ٱ��Ͽ� �߰��� ��ǰ������ DB���� ��������.
		ProductDAO dao = new ProductDAO();
		ProductVO item = dao.selectProductByPnum(pnumStr);
		
		CartBean cart = null;
		//session�� ����� ��ٱ��Ϻ��� �ִ��� ��������. ������ ���� ����
		
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