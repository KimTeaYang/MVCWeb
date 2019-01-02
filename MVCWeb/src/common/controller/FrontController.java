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

	/* servlet ����� ù ��û�� ���� �� �� �ѹ� ȣ��Ǵ� method 
	 * init-param�� ����Ǿ� �ִ� config�� �ش��ϴ� 
	 * value��(Command.properties�� ������)�� ������. 
	 * web.xml�� ����Ǿ� �ִ��� �ֱٿ��� ������̼����� ��ġ�� */
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("config");
		//props => ���ϰ��
		
		Properties pr = new Properties();
		// key = value �����Ͽ� �����ϴ� �ڷᱸ��.
		// Command.properties���Ͽ� ���ε� ������ Properties�� �ű���.
		
		try {
			FileInputStream fis = new FileInputStream(props);
			pr.load(fis);
			
			if(fis!=null) fis.close();
			
			//pr���� key���鸸 ��������.
			Set<Object> set = pr.keySet();
			
			for(Object key:set) {
				String cmd = key.toString(); //key�� ex) /index.do
				String className = pr.getProperty(cmd);
				
				if(className!=null) {
					className=className.trim();
					Class cmdClass = Class.forName(className);
					Object cmdInstance = cmdClass.newInstance();
					//�ش� Ŭ������ ��üȭ���� �޸𸮿� �÷����´�.
					
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
		//System.out.println("FrontController�����...");
		//1. Ŭ�� ��û�� �м��ؼ� Sub Controller(XXXAction)���� 
		//��û�� ó���ϵ��� �����Ѵ�.
		String uri = req.getRequestURI(); // ==> /MVCWeb/index.do
		String myctx = req.getContextPath(); // ==> /MVCWeb
		int len = myctx.length(); // ==> 6
		String cmd = uri.substring(len); // ==> /index.do
		Object instance = cmdMap.get(cmd); // ==> IndexAction ��ü
		
		if(instance==null) {
			//System.out.println("Action NullPointException");
			//return;
			throw new ServletException(cmd+": �׼��� null");
		}
		
		AbstractAction action = (AbstractAction)instance;
		// ������Ʈ�ѷ��� execute() ȣ���ϱ�
		try {
			action.execute(req, res);
			
			//�̵��� �������� ���
			String viewPage = action.getViewPage();
			if(viewPage==null) {
				System.out.println("viewPage NullPointException");
				viewPage = "test.html";
				// default page�� test.html�� ����
			}
			
			//�̵���Ŀ� ���� ���������� �̵�
			if(action.isRedirect()) {
				//redirect�� �̵��ϴ� ���(true)
				res.sendRedirect(viewPage);
			}else {
				//forward�� �̵��ϴ� ���(false)
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
