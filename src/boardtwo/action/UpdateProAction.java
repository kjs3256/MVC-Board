package boardtwo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import boardtwo.model.*;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		//파일 업로드를 하기 위해서 cos.jar 추가 및 객체 생성
		MultipartRequest multi = null;
		
		//업로드 될 파일의 최대 사이즈(100메가)
		int sizeLimit = 10 * 1024 * 1024;
		
		//파일이 업로드될 실제 톰캣 폴더의 경로 (WebContent 기준)
		String savePath = request.getServletContext().getRealPath("/upload");
		
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
					new DefaultFileRenamePolicy());
		}catch(Exception e) {
			e.printStackTrace();
		}
		int num = Integer.parseInt(multi.getParameter("num"));
		String pageNum = multi.getParameter("pageNum");
		
		BoardDao dbPro = BoardDao.getInstance();
		
		BoardDto article = new BoardDto();
		BoardDto original = dbPro.getArticle(num);
		
		String filename = "";
		
		if(multi.getFilesystemName("filename") != null) {
			filename = multi.getFilesystemName("filename");
			article.setFilename(filename);
		}else {
			filename = original.getFilename();
			article.setFilename(filename);
		}
		
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setNickname(multi.getParameter("nickname"));
		article.setSubject(multi.getParameter("subject"));
		article.setContent(multi.getParameter("content"));

		int check = dbPro.updateArticle(article);
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		return "/WEB-INF/boardtwo/updatePro.jsp";
	}

}
