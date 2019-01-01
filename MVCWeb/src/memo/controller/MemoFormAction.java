package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class MemoFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		// �������� ����
		this.setViewPage("memo/memo.jsp");
		// �̵���� ����
		this.setRedirect(false);
	}

}