package comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import comment.model.CommentDao;

public class Comm_DelAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		boolean flag = true;
		if(request.getSession().getAttribute("loginID") != null){
			flag = false;
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		
		CommentDao dbPro = CommentDao.getInstance();
		int i = dbPro.deleteComment(comment_num);
		
		request.setAttribute("flag", new Boolean(flag));
		request.setAttribute("i", new Integer(i));
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		return "/WEB-INF/boardtwo/commDel.jsp";
	}

}
