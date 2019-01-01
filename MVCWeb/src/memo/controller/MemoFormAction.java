package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class MemoFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		// 뷰페이지 지정
		this.setViewPage("memo/memo.jsp");
		// 이동방식 지정
		this.setRedirect(false);
	}

}