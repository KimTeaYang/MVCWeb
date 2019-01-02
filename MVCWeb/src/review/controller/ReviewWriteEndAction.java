package review.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import review.model.ReviewDAOMyBatis;
import review.model.ReviewVO;

public class ReviewWriteEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//���ε� ó�� - cos.jar
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/review_images");
		
		File dir = new File(upDir);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(req,upDir,10*1024*1024,"UTF-8",df);
		
		//����ڰ� �Է��� �� �޾Ƽ� VO�� ����ֱ�
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String score1 = mr.getParameter("score1");
		String filename = mr.getFilesystemName("mfilename");
		String pnum_fk = mr.getParameter("pnum_fk");
		String midx_fk = mr.getParameter("midx_fk");
		int score = Integer.parseInt(score1);
		
		//DAO�� reviwWrite(vo)ȣ��
		ReviewVO rvo = new ReviewVO(
				0,title,content,score,filename,null,midx_fk,pnum_fk); 
		ReviewDAOMyBatis dao = new ReviewDAOMyBatis();
		
		int n = dao.reviewWrite(rvo);
		
		String msg=(n>0)?"���侲�� ����":"���侲�� ����";
		String loc="../prodDetail.do?pnum="+pnum_fk;
		String view=CommonUtil.addMsgLoc(req, msg, loc);
		
		this.setViewPage(view);
		this.setRedirect(false);
	}

}
