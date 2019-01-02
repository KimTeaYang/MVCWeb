package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import review.model.ReviewDAOMyBatis;
import review.model.ReviewVO;
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
		
		// ������ �޾ƿ��� => req�� ������ �����ϱ�
		ReviewDAOMyBatis rdao = new ReviewDAOMyBatis();
		List<ReviewVO> rvList = rdao.getReviewList(pnum);
		
		req.setAttribute("rvList", rvList);
		
		// �α��� �� �Ŀ� ���ư� �������� ���ǿ� ��������.
		HttpSession ses=req.getSession();
		
		ses.setAttribute("returnPage", "prodDetail.do?pnum="+pnum);
		
		this.setViewPage("/shop/prodDetail.jsp");
		this.setRedirect(false);
	}

}
