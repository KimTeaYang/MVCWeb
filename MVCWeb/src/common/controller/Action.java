package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// interface : �߻�޼ҵ�� ���(public static final�� ����)
public interface Action {
	
	//interface�� �޼ҵ忡�� �ڵ����� public abstract�� �ٴ´�.
	void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception;
	
}