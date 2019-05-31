package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.model.MemberDao;

public class IdCheck implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MemberDao dao = MemberDao.getInstance();
		String id = request.getParameter("id");
		boolean check = dao.idCheck(id);
		request.setAttribute("check", new Boolean(check));
		request.setAttribute("id", id);
		return "/WEB-INF/boardtwo/idCheck.jsp";
	}

}
