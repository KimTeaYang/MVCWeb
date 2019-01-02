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
 * Servlet Filter implementation class AdminCheckFilter
 */
@WebFilter("/admin/*")
public class AdminCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminCheckFilter() {
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
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		if(loginUser!=null) {
			if(loginUser.getUserid().equals("admin")) {
				chain.doFilter(request, response);
				return;
			}else {
				req.setAttribute("msg", "관리자만 이용할 수 있어요");
				req.setAttribute("loc", "javascript:history.back()");
				
				String viewPage = "/memo/message.jsp";
				RequestDispatcher disp = req.getRequestDispatcher(viewPage);
				disp.forward(req, response);
				return;
			}
		}
		
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
