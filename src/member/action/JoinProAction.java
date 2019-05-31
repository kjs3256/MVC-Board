package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.model.*;

public class JoinProAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String loginID = null;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
		}
		
		MemberDao dbPro = MemberDao.getInstance();
		MemberDto member = new MemberDto();
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pass"));
		member.setEmail(request.getParameter("email"));
		member.setNickname(request.getParameter("nickname"));
		member.setGender(request.getParameter("gender"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setLoc(request.getParameter("loc"));
		
		boolean flag = dbPro.userInsert(member);
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("loginID", loginID);
		request.setAttribute("flag", new Boolean(flag));
				
		return "/WEB-INF/boardtwo/joinPro.jsp";
	}

}