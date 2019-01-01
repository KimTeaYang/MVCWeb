package shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* ��ٱ���(ArrayList)�� ��ǰ�� �߰�, ����, ����(����)�ϴ� ������ ���� ���� Beans*/
public class CartBean implements java.io.Serializable {
	
	private List<ProductVO> cartList
		= new ArrayList<>();
		
	public List<ProductVO> getCartList() {
		return cartList;
	}

	/**��ٱ��Ͽ� ��ǰ�� �߰��ϴ� �޼ҵ�*/
	public void addProduct(int pnum, int oqty, ProductVO item) {
		/*1. ���� �߰��Ϸ��� ��ǰ�� �̹� ��ٱ��Ͽ� �ִ��� �˻��Ѵ�
		 * 1) �̹� �ִ� ��ǰ�̶�� => ������ ���� */
		
		for(ProductVO pd:cartList) {
			int pnum2 = pd.getPnum();
			
			if(pnum==pnum2) {
				int qty = pd.getPqty()+oqty;
				pd.setPqty(qty);
				return;
			}
		}
		
		/*2. ���Ӱ� �߰��ϴ� ��ǰ�̶�� ��ٱ��Ͽ� ����*/
		if(item!=null) {
			item.setPqty(oqty);
			cartList.add(item);
		}
	}
	
	/** ��ٱ��� ��ǰ�� �� �ݾװ� ����Ʈ�� ��ȯ�ϴ� �޼ҵ� */
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
	
	/** ��ٱ��Ͽ��� Ư�� ��ǰ�� �����ϴ� �޼ҵ� */
	public void removeProduct(int pnum) {
		for(ProductVO pd:cartList) {
			if(pd.getPnum()==pnum) {
				cartList.remove(pd);
				break;
			}
		}
	}
	
	/** ��ٱ����� Ư�� ��ǰ�� ������ �����ϴ� �޼ҵ� */
	public void setEdit(int pnum, int oqty) throws Exception {
		for(ProductVO pd:cartList) {
			if(pd.getPnum()==pnum) {
				if(oqty>0) {
					pd.setPqty(oqty);
				}else if(oqty==0) {
					cartList.remove(pd);
				}else {
					throw new Exception("������ �������� �ȵſ�");
				}
				break;
			}
		}
		
	}
	
}