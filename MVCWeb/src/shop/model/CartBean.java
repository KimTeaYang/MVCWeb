package shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 장바구니(ArrayList)에 상품을 추가, 삭제, 편집(수량)하는 로직을 갖는 서비스 Beans*/
public class CartBean implements java.io.Serializable {
	
	private List<ProductVO> cartList
		= new ArrayList<>();
		
	public List<ProductVO> getCartList() {
		return cartList;
	}

	/**장바구니에 상품을 추가하는 메소드*/
	public void addProduct(int pnum, int oqty, ProductVO item) {
		/*1. 새로 추가하려는 상품이 이미 장바구니에 있는지 검색한다
		 * 1) 이미 있는 상품이라면 => 수량만 증가 */
		
		for(ProductVO pd:cartList) {
			int pnum2 = pd.getPnum();
			
			if(pnum==pnum2) {
				int qty = pd.getPqty()+oqty;
				pd.setPqty(qty);
				return;
			}
		}
		
		/*2. 새롭게 추가하는 상품이라면 장바구니에 저장*/
		if(item!=null) {
			item.setPqty(oqty);
			cartList.add(item);
		}
	}
	
	/** 장바구니 상품의 총 금액과 포인트를 반환하는 메소드 */
	public Map<String,Integer> getCartTotal(){
		Map<String,Integer> map = new HashMap<>();
		int totalPrice=0, totalPoint=0;
		for(ProductVO pd:cartList) {
			totalPrice +=pd.getTotalPrice();
			totalPoint +=pd.getTotalPoint();
		}
		map.put("cartTotalPrice", totalPrice);
		map.put("cartTotalPoint", totalPoint);

		return map;
	}
	
	/** 장바구니에서 특정 상품을 삭제하는 메소드 */
	public void removeProduct(int pnum) {
		for(ProductVO pd:cartList) {
			if(pd.getPnum()==pnum) {
				cartList.remove(pd);
				break;
			}
		}
	}
	
	/** 장바구니의 특정 상품의 수량을 수정하는 메소드 */
	public void setEdit(int pnum, int oqty) throws Exception {
		for(ProductVO pd:cartList) {
			if(pd.getPnum()==pnum) {
				if(oqty>0) {
					pd.setPqty(oqty);
				}else if(oqty==0) {
					cartList.remove(pd);
				}else {
					throw new Exception("수량은 음수여선 안돼요");
				}
				break;
			}
		}
		
	}
	
}