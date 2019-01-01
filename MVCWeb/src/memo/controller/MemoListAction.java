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
		
		//0. 현재 보여줄 페이지값 받기(cpage)
		String cpStr = req.getParameter("cpage");
		if(cpStr==null || cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		
		int cpage = Integer.parseInt(cpStr);
		if(cpage<=0) {
			cpage=1;
		}
		
		MemoDAO memoDAO = new MemoDAO();
		//1. 페이징 처리를 위해 총 게시글 수를 가져오자
		int totalCount = memoDAO.getTotalCount();
		
		//1-2 한페이지 당 보여줄 목록 갯수를 정하자.
		int pageSize = 10;
		
		int pageCount = 0;
		pageCount = ((totalCount-1)/pageSize)+1;
		
		if(cpage>pageCount) {
			cpage=pageCount; //마지막 페이지 보여주도록
		}
		
		// 1-3 DB에서 5개 단위로 끊어오기 위한 변수 선언 및 연산
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
