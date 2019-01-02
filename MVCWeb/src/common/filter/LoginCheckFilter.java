package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import user.model.UserVO;

/**
 * Servlet Filter implementation class LoginCheckFilter
 * 필터는 web.xml에 기술되던지, 아니면 @WebFilter어노테이션을 이용하던지 2중 하나만.
 */

@WebFilter({"/user/*","/admin/*"})
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Login 여부를 체크하는 필터
		//Login을 하지 않았다면 요청을 처리하지 않는다.
		//Login을 했다면 다음 필터로 요청을 넘기자.
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			//Login 안했다면
			req.setAttribute("msg", "로그인해야 이용할 수 있어요");
			req.setAttribute("loc", "javascript:history.back()");
			
			String viewPage = "/memo/message.jsp";
			RequestDispatcher disp = req.getRequestDispatcher(viewPage);
			disp.forward(req, response);
			
			return;
		}else {
			//Login 했다면
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
