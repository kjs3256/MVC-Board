package boardtwo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;

import java.sql.Timestamp;

import boardtwo.model.*;

public class WriteProAction implements CommandAction{
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
		
		//파일 이름 저장
		String filename = multi.getFilesystemName("filename");
		
		String loginID = null;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
		}
		
		BoardDto article = new BoardDto(); //데이터를 처리할 Bean
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setSubject(multi.getParameter("subject"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(multi.getParameter("ref")));
		article.setStep(Integer.parseInt(multi.getParameter("step")));
		article.setDepth(Integer.parseInt(multi.getParameter("depth")));
		article.setContent(multi.getParameter("content"));
		article.setIp(request.getRemoteAddr());
		article.setId(loginID);
		article.setFilename(filename);
		
		
		BoardDao dbPro = BoardDao.getInstance(); //DB연결
		dbPro.insertArticle(loginID, article);
		
		return "/WEB-INF/boardtwo/writePro.jsp"; //해당 뷰 경로 반환
	}
}
