package member.action;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class LoginFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		Cookie tmp = null;
		boolean check = false;
		String cookieValue = "";
		if(request.getCookies() != null){
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(int i=0; i<cookies.length; i++){
					cookieMap.put(cookies[i].getName(), cookies[i]);
				}
				tmp = cookieMap.get("saveID");
				if(tmp != null){
					check = true;
					cookieValue = tmp.getValue();
				}
			}
		}
		request.setAttribute("check", new Boolean(check));
		request.setAttribute("cookieID", cookieValue);
		return "/WEB-INF/boardtwo/loginForm.jsp";
	}

}
