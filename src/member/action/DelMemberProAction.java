package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.model.*;

public class DelMemberProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MemberDao dao = MemberDao.getInstance();
		
		String id = (String)request.getSession().getAttribute("loginID");
		String pass = request.getParameter("pass");
		int check = dao.userDelete(id, pass);
		if(check == 1){
			request.getSession().invalidate();
		}
		request.setAttribute("check", new Integer(check));
		return "/WEB-INF/boardtwo/delMemberPro.jsp";
	}
}
