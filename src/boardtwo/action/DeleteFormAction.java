package boardtwo.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import boardtwo.model.*;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String loginID = null;
		boolean flag = true;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
			flag = false;
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.getArticle(num);
		
		String filename = article.getFilename();
		String uploadFileName = request.getServletContext().getRealPath("/upload")+"/"+filename;
		
		File uploadfile = new File(uploadFileName);
		if(uploadfile.exists() && uploadfile.isFile()) {
			uploadfile.delete(); //파일 삭제
		}
		int i = dbPro.deleteArticle(num);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("i", new Integer(i));
		request.setAttribute("flag", new Boolean(flag));
		request.setAttribute("loginID", loginID);
		
		return "/WEB-INF/boardtwo/deleteForm.jsp";
	}

}
