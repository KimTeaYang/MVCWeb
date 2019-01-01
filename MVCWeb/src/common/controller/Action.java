package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// interface : 추상메소드와 상수(public static final인 변수)
public interface Action {
	
	//interface의 메소드에는 자동으로 public abstract가 붙는다.
	void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception;
	
}