package shop.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.controller.AbstractAction;
import shop.model.ProductDAO;
import shop.model.ProductVO;

public class ProductInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//0. 업로드할 디렉토리의 절대 경로 얻기(product_images)
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/product_images");
		File dir = new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//1. 파일 업로드 처리 (MultipartRequest객체를 생성)
		DefaultFileRenamePolicy df
				= new DefaultFileRenamePolicy();
			
		MultipartRequest mr = new MultipartRequest(
					req,upDir,10*1024*1024,"UTF-8",df);
		
		String pname = mr.getParameter("pname");
		String upCodeStr = mr.getParameter("upCode");
		String downCodeStr = mr.getParameter("downCode");
		
		// 업로드 파일명
		String pimage1 = mr.getFilesystemName("pimage1");
		String pimage2 = mr.getFilesystemName("pimage2");
		String pimage3 = mr.getFilesystemName("pimage3");
		
		String priceStr = mr.getParameter("price");
		String spriceStr = mr.getParameter("saleprice");
		String pqtyStr = mr.getParameter("pqty");
		String pointStr = mr.getParameter("point");
		
		String pspec = mr.getParameter("pspec");
		String pcontents = mr.getParameter("pcontents");
		String pcompany = mr.getParameter("pcompany");
		
		if(pname==null||upCodeStr==null||downCodeStr==null||
				upCodeStr.trim().isEmpty()||
				downCodeStr.trim().isEmpty()) {
			this.setViewPage("prodInput.do");
			this.setRedirect(true);
			return;
		}
		
		int upCode = Integer.parseInt(upCodeStr.trim());
		int downCode = Integer.parseInt(downCodeStr.trim());
		int price =Integer.parseInt(priceStr.trim());
		int saleprice = Integer.parseInt(spriceStr.trim());
		int pqty = Integer.parseInt(pqtyStr.trim());
		int point = Integer.parseInt(pointStr.trim());
		
		ProductVO product = new ProductVO(
				0, pname, upCode, downCode, pcompany, pimage1, 
				pimage2, pimage3, pqty, price, saleprice, pspec, 
				pcontents, point, null);
		
		ProductDAO dao = new ProductDAO();
		int n = dao.productInsert(product);
		
		String str = (n>0)?"상품등록 성공":"상품등록 실패";
		String loc = (n>0)?"../index.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		this.setViewPage("../memo/message.jsp");
		this.setRedirect(false);
	}
}