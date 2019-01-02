package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "config", value = "C:\\git\\MVCWeb\\MVCWeb\\WebContent\\WEB-INF\\Command.properties")
		})

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String,Object> cmdMap = new HashMap<>();

	/* servlet 실행시 첫 요청이 있을 때 딱 한번 호출되는 method 
	 * init-param에 기술되어 있는 config에 해당하는 
	 * value값(Command.properties의 절대경로)을 얻어오자. 
	 * web.xml에 기술되어 있던지 최근에는 어노테이션으로 대치됨 */
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("config");
		//props => 파일경로
		
		Properties pr = new Properties();
		// key = value 매핑하여 저장하는 자료구조.
		// Command.properties파일에 매핑된 정보를 Properties로 옮기자.
		
		try {
			FileInputStream fis = new FileInputStream(props);
			pr.load(fis);
			
			if(fis!=null) fis.close();
			
			//pr에서 key값들만 추출하자.
			Set<Object> set = pr.keySet();
			
			for(Object key:set) {
				String cmd = key.toString(); //key값 ex) /index.do
				String className = pr.getProperty(cmd);
				
				if(className!=null) {
					className=className.trim();
					Class cmdClass = Class.forName(className);
					Object cmdInstance = cmdClass.newInstance();
					//해당 클래스를 객체화시켜 메모리에 올려놓는다.
					
					cmdMap.put(cmd, cmdInstance);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request,response);
	}

	private void requestProcess(HttpServletRequest req,
			HttpServletResponse res) 
					throws IOException, ServletException {
		//System.out.println("FrontController실행됨...");
		//1. 클의 요청을 분석해서 Sub Controller(XXXAction)에게 
		//요청을 처리하도록 위임한다.
		String uri = req.getRequestURI(); // ==> /MVCWeb/index.do
		String myctx = req.getContextPath(); // ==> /MVCWeb
		int len = myctx.length(); // ==> 6
		String cmd = uri.substring(len); // ==> /index.do
		Object instance = cmdMap.get(cmd); // ==> IndexAction 객체
		
		if(instance==null) {
			//System.out.println("Action NullPointException");
			//return;
			throw new ServletException(cmd+": 액션이 null");
		}
		
		AbstractAction action = (AbstractAction)instance;
		// 서브컨트롤러의 execute() 호출하기
		try {
			action.execute(req, res);
			
			//이동할 뷰페이지 얻기
			String viewPage = action.getViewPage();
			if(viewPage==null) {
				System.out.println("viewPage NullPointException");
				viewPage = "test.html";
				// default page를 test.html로 지정
			}
			
			//이동방식에 따라서 뷰페이지로 이동
			if(action.isRedirect()) {
				//redirect로 이동하는 경우(true)
				res.sendRedirect(viewPage);
			}else {
				//forward로 이동하는 경우(false)
				RequestDispatcher disp
					= req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}
