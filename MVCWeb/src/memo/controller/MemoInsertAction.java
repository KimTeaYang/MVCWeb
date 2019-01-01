package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.controller.AbstractAction;
import memo.model.*;

public class MemoInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		String name = req.getParameter("name");
		String msg = req.getParameter("msg");
		
		if(name==null||msg==null||name.trim().isEmpty()) {
			this.setViewPage("memo.do");
			this.setRedirect(true);
			return;
		}
		
		MemoVO memoVO = new MemoVO();
		memoVO.setName(name);
		memoVO.setMsg(msg);
		MemoDAO memoDAO = new MemoDAO();
		
		int n = memoDAO.insertMemo(memoVO);
		
		String str = (n>0)?"등록 성공":"등록 실패";
		String loc = (n>0)?"memo-list.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		this.setViewPage("memo/message.jsp");
		this.setRedirect(false);
		
	}

}
