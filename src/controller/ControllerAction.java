package controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import action.CommandAction;

public class ControllerAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	//명령어와 명령어 처리 클래스를 쌍으로 저장해두는 MAP
	private Map<String,Object> commandMap =
			new HashMap<String,Object>();
	/*
	 * 명령어와 처리 클래스가 매핑되어 있는
	 * properties파일(CommandPro.properties)을 읽어
	 * Map객체인 commandMap에 저장한다.
	 */
	
	//web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어온다.
	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		
		//명령어와 커리 클래스의 매핑 정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path,props));
			//Command.properties파일의 정보를 Properties 객체에 저장
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			try {
				if(f != null) f.close();
			}catch(IOException e) {}
		}
		
		//Iterator 객체 사용
		Iterator<Object> keyIt = pr.keySet().iterator();
		while(keyIt.hasNext()) {
			String command = (String)keyIt.next();
			String className = pr.getProperty(command);
			try {
				//가져온 문자열을 클래스로 만듬
				Class<?> commandClass = Class.forName(className);
				
				//만들어진 해당 클래스의 객체 생성
				Object commandInstance = commandClass.newInstance();
				
				//생성된 객체를 commandMap에 저장
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	//GET 방식 서비스 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}
	//POST 방식 서비스 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}
	
	//사용자의 요청에 따라 분석하여 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String view = null;
		CommandAction com = null; //명령어 인터페이스
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
