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
		
		String pnum = req.getParameter("pnum");
		
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		
		String cpStr = req.getParameter("cpage");
		
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		
		int cpage = Integer.parseInt(cpStr.trim());
		
		if(cpage<0) {
			cpage=1;
		}
		
		ProductDAO dao = new ProductDAO();
		ProductVO pvo = dao.selectProductByPnum(pnum);
		
		req.setAttribute("pvo", pvo);
		
		// 리뷰목록 받아오기 => req에 리뷰목록 저장하기
		ReviewDAOMyBatis rdao = new ReviewDAOMyBatis();
		int count=rdao.getReviewCount(pnum);
		
		int pageSize = 3;
		int pageCount = 0;
		pageCount = (count-1)/pageSize+1;
		
		if(cpage>pageCount) { // cpage가 페이지수보다 크다면
			cpage=pageCount; // 마지막 페이지를 보여준다.
		}
		
		int end = cpage*pageSize;
		int start = end-(pageSize-1);
		
		List<ReviewVO> rvList = rdao.getReviewList(pnum,start,end);
		
		req.setAttribute("cpage", cpage);
		req.setAttribute("rvList", rvList);
		req.setAttribute("rvCount", count);
		req.setAttribute("rvPageCount", pageCount);
		
		// 로그인 한 후에 돌아갈 페이지를 세션에 저장하자.
		HttpSession ses=req.getSession();
		
		ses.setAttribute("returnPage", "prodDetail.do?pnum="+pnum);
		
		this.setViewPage("/shop/prodDetail.jsp");
		this.setRedirect(false);
	}

}
