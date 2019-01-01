package shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import shop.model.CartBean;
import shop.model.ProductVO;

public class CartListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 1.�α��� ���� üũ
		
		// 2.�α��� �ߴٸ� ��ٱ��� ��������
		HttpSession ses = req.getSession();
		CartBean cart = (CartBean)ses.getAttribute("cartBeanAdmin");
		
		if(cart==null) {
			cart = new CartBean();
		}
		
		// 3.��ٱ��� ��ǰ��� ��������
		List<ProductVO> cartList = cart.getCartList();
		
		// 4.��ٱ��� �� �ݾ�, ����Ʈ ��������
		Map<String,Integer> cartMap = cart.getCartTotal();
		
		// 5. ���ǿ� ����
		ses.setAttribute("cartBeanAdmin", cart);
		ses.setAttribute("cartList", cartList);
		ses.setAttribute("cartMap", cartMap);
		
		this.setViewPage("shop/cartList.jsp");
		this.setRedirect(false);
		
		/* ��ٱ��Ͽ� ��ǳ�� �߰��� �� ������ �̵��� redirect�� �̵��ؾ��Ѵ�.
		 * forward �̵��� �ϸ� �������� ����url(cartAdd.do)�� ��� �����ϱ� ������
		 * ���ΰ�ħ�� �� ��� ��ٱ��� ��ǰ ������ �����ϴ� ������ �߻��Ѵ�.
		 * ���� cartList.do�� redirect�̵��� ����.
		 * redirect�̵��� �������� url�� ���ο� url(cartList.do)�� ����Ǿ� 
		 * �׷� ������ ���� �� �ִ�. 
		 * 
		 * forward�� ���ΰ�ħ�� �ϸ� ������ �ѹ� �� ���� 
		 * redirect�� ���ο� �������� �ٲ�����Ƿ� ���� ����x */
	}

}
