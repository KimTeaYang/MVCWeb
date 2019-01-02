package review.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import review.model.ReviewDAOMyBatis;

public class ReviewDelAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String ridx = req.getParameter("ridx");
		String pnum = req.getParameter("pnum_fk");
		System.out.println(ridx+"/"+pnum);
		if(ridx==null||pnum==null) {
			this.setViewPage("prodDetail.do");
			this.setRedirect(false);
			return;
		}
		
		ReviewDAOMyBatis dao = new ReviewDAOMyBatis();
		String filename = dao.getReviewUploadFile(ridx);
		
		//load file 제거하기
		if(filename!=null) {
			ServletContext app = req.getServletContext();
			String upDir = app.getRealPath("/review_images");
			File f = new File(upDir,filename);
			
			if(f.exists()) {
				boolean b = f.delete();
			}
		}
		
		int n = dao.deleteReview(ridx);
		
		String msg=(n>0)?"삭제 완료":"삭제 실패";
		String loc="../prodDetail.do?pnum="+pnum;
		
		String viewPage=CommonUtil.addMsgLoc(req, msg, loc);
		this.setViewPage(viewPage);
		this.setRedirect(false);
	}

}
