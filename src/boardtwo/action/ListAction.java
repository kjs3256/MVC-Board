package boardtwo.action;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

import action.CommandAction;
import boardtwo.model.*;

public class ListAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5; //한 페이지 당 글의 갯수
		int currentPage = Integer.parseInt(pageNum);
		//페이지의 시작 글 번호
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글 번호
		int count = 0;
		int number = 0;
		List<BoardDto> articleList = null;
		BoardDao dbPro = BoardDao.getInstance(); //DB연결
		count = dbPro.getArticleCount(); //전체 글 갯수
		if(count>0) {//현재 페이지의 글 목록
			articleList = dbPro.getArticles(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize; //글 목록에 표시할 글 번호
		String loginID = null;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		//해당 뷰에서 사용할 속성
		request.setAttribute("loginID", loginID);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("sdf", sdf);
		
		return "/WEB-INF/boardtwo/list.jsp"; //해당하는 뷰 경로 반환
	}
	
}
