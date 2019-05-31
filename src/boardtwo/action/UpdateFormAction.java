package boardtwo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import boardtwo.model.*;

public class UpdateFormAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String loginID = null;
		boolean flag1 = true;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
			flag1 = false;
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		boolean flag2 = false;
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.updateGetArticle(num);
		
		if((!loginID.equals(article.getId())) && (loginID != null)){
			flag2 = true;
		}
		request.setAttribute("loginID", loginID);
		request.setAttribute("flag1", new Boolean(flag1));
		request.setAttribute("flag2", new Boolean(flag2));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		return "/WEB-INF/boardtwo/updateForm.jsp";
	}

}
