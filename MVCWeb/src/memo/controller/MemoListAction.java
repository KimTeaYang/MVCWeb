package memo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import memo.model.MemoDAO;
import memo.model.MemoVO;

public class MemoListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//0. ���� ������ �������� �ޱ�(cpage)
		String cpStr = req.getParameter("cpage");
		if(cpStr==null || cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		
		int cpage = Integer.parseInt(cpStr);
		if(cpage<=0) {
			cpage=1;
		}
		
		MemoDAO memoDAO = new MemoDAO();
		//1. ����¡ ó���� ���� �� �Խñ� ���� ��������
		int totalCount = memoDAO.getTotalCount();
		
		//1-2 �������� �� ������ ��� ������ ������.
		int pageSize = 10;
		
		int pageCount = 0;
		pageCount = ((totalCount-1)/pageSize)+1;
		
		if(cpage>pageCount) {
			cpage=pageCount; //������ ������ �����ֵ���
		}
		
		// 1-3 DB���� 5�� ������ ������� ���� ���� ���� �� ����
		int end = cpage*pageSize;
		int start = end-(pageSize-1);
		
		ArrayList<MemoVO> arr = memoDAO.listMemo(start,end);
		
		req.setAttribute("cpage", cpage);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("memoArr", arr);
		
		this.setViewPage("memo/memoList.jsp");
		this.setRedirect(false);
	}

}
