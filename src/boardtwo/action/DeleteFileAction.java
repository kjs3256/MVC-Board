package boardtwo.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import boardtwo.model.BoardDao;

public class DeleteFileAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

		boolean flag = true;
		if(request.getSession().getAttribute("loginID") != null){
			flag = false;
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String filename = request.getParameter("filename");
		@SuppressWarnings("deprecation")
		String uploadFileName = request.getRealPath("/upload")+"/"+filename;
		
		File uploadfile = new File(uploadFileName);
		if(uploadfile.exists() && uploadfile.isFile()) {
			uploadfile.delete(); //파일 삭제
		}
		BoardDao dbPro = BoardDao.getInstance();
		int i = dbPro.deleteFile(num);
		
		request.setAttribute("i", new Integer(i));
		request.setAttribute("flag", new Boolean(flag));
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		return "/WEB-INF/boardtwo/deleteFile.jsp";
	}

}
