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
 * ���ʹ� web.xml�� ����Ǵ���, �ƴϸ� @WebFilter������̼��� �̿��ϴ��� 2�� �ϳ���.
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
		//Login ���θ� üũ�ϴ� ����
		//Login�� ���� �ʾҴٸ� ��û�� ó������ �ʴ´�.
		//Login�� �ߴٸ� ���� ���ͷ� ��û�� �ѱ���.
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			//Login ���ߴٸ�
			req.setAttribute("msg", "�α����ؾ� �̿��� �� �־��");
			req.setAttribute("loc", "javascript:history.back()");
			
			String viewPage = "/memo/message.jsp";
			RequestDispatcher disp = req.getRequestDispatcher(viewPage);
			disp.forward(req, response);
			
			return;
		}else {
			//Login �ߴٸ�
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
