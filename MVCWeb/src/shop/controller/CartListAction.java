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
		
		// 1.로그인 여부 체크
		
		// 2.로그인 했다면 장바구니 꺼내오기
		HttpSession ses = req.getSession();
		CartBean cart = (CartBean)ses.getAttribute("cartBeanAdmin");
		
		if(cart==null) {
			cart = new CartBean();
		}
		
		// 3.장바구니 상품목록 가져오기
		List<ProductVO> cartList = cart.getCartList();
		
		// 4.장바구니 총 금액, 포인트 가져오기
		Map<String,Integer> cartMap = cart.getCartTotal();
		
		// 5. 세션에 저장
		ses.setAttribute("cartBeanAdmin", cart);
		ses.setAttribute("cartList", cartList);
		ses.setAttribute("cartMap", cartMap);
		
		this.setViewPage("shop/cartList.jsp");
		this.setRedirect(false);
		
		/* 장바구니에 삼풍을 추가한 뒤 페이지 이동은 redirect로 이동해야한다.
		 * forward 이동을 하면 브라우저는 이전url(cartAdd.do)를 계속 유지하기 때문에
		 * 새로고침을 할 경우 장바구니 상품 수량이 증가하는 현상이 발생한다.
		 * 따라서 cartList.do로 redirect이동을 하자.
		 * redirect이동은 브라우저의 url이 새로운 url(cartList.do)로 변경되어 
		 * 그런 현상을 막을 수 있다. 
		 * 
		 * forward는 새로고침을 하면 로직이 한번 더 수행 
		 * redirect는 새로운 페이지로 바꿔버리므로 로직 수행x */
	}

}
