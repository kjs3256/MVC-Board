package comment.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import comment.model.*;
import member.model.*;

public class Comm_InAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			request.setCharacterEncoding("UTF-8");
			
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			String loginID = null;
			boolean flag = true;
			if(request.getSession().getAttribute("loginID") != null){
				loginID = (String)request.getSession().getAttribute("loginID");
				flag = false;
			}
			CommentDto comm = new CommentDto();
			comm.setComment_content(request.getParameter("comment_content"));
			
			MemberDao memDao = MemberDao.getInstance();
			MemberDto member = memDao.getUser(loginID);
			if(loginID != null){
				CommentDao dbPro = CommentDao.getInstance();
				comm.setComment_nickname(member.getNickname());
				comm.setComment_regdate(new Timestamp(System.currentTimeMillis()));
				comm.setComment_ip(request.getRemoteAddr());
				comm.setId(loginID);
				comm.setNum(num);
				dbPro.insertComment(comm);
			}
			request.setAttribute("flag", new Boolean(flag));
			request.setAttribute("num", new Integer(num));
			request.setAttribute("pageNum", new Integer(pageNum));
		return "/WEB-INF/boardtwo/commPro.jsp";
	}

}
