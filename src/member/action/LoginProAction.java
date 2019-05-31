package member.action;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.model.*;

public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		MemberDto mem = new MemberDto();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		MemberDao dbPro = MemberDao.getInstance(); //인스턴스 생성
		String id = mem.getId();
		String pass = mem.getPass();
		int result = dbPro.login(id, pass);
		boolean ret = true;
		if(result == 1){
			request.getSession().setAttribute("loginID", mem.getId());
			//아이디 저장을 체크했을 경우
			if(request.getParameter("saveID") != null){
				Cookie cookie = new Cookie("saveID", (String)request.getSession().getAttribute("loginID"));
				cookie.setPath("/");
				cookie.setMaxAge(60*30);
				response.addCookie(cookie);
			}
			//체크하지 않을 경우
			else{
				Cookie cookie = new Cookie("saveID","");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}else if(result == 0) {
			ret = false;
		}else {
			ret = true;
		}
		request.setAttribute("ret", new Boolean(ret));
		request.setAttribute("result", new Integer(result));
		
		return "/WEB-INF/boardtwo/loginPro.jsp";
	}

}
