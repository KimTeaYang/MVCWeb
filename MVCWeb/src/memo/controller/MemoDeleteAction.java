package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import memo.model.MemoDAO;
import memo.model.MemoVO;

public class MemoDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String idxStr = req.getParameter("idx");
		
		if(idxStr==null||idxStr.trim().isEmpty()) {
			this.setViewPage("memo-list.do");
			this.setRedirect(true);
			return;
		}
		
		int idx = Integer.parseInt(idxStr.trim());
		
		MemoDAO memoDAO = new MemoDAO();
		int n = memoDAO.deleteMemo(idx);
		
		String str = (n>0)?"삭제 성공":"삭제 실패";
		String loc = (n>0)?"memo-list.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		this.setViewPage("memo/message.jsp");
		this.setRedirect(false);
	}

}
