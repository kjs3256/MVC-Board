package boardtwo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MainAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String loginID = null;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
		}
		request.setAttribute("loginID", loginID);
		return "/WEB-INF/boardtwo/main.jsp";
	}

}
