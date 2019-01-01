package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.ProductDAO;
import shop.model.ProductVO;

public class IndexAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		
		ProductDAO dao = new ProductDAO();
		List<ProductVO> newList = dao.selectByPspec("NEW");
		 
		List<ProductVO> hitList = dao.selectByPspec("HIT");
		
		req.setAttribute("newList", newList);
		req.setAttribute("hitList", hitList);
		
		//ViewPage 지정
		this.setViewPage("index.jsp");
		//이동방식 지정
		this.setRedirect(false);
	}
	
}