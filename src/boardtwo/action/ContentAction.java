package boardtwo.action;


import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import boardtwo.model.*;
import comment.model.CommentDao;
import comment.model.CommentDto;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String loginID = null;
		if(request.getSession().getAttribute("loginID") != null){
			loginID = (String)request.getSession().getAttribute("loginID");
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		BoardDao dbPro = BoardDao.getInstance();
		
		//해당 글 번호에 대한 레코드
		BoardDto article = dbPro.getArticle(num);
		
		//댓글 관련 로직 처리
		CommentDao comm_dbPro = CommentDao.getInstance();
		List<CommentDto> commList = null;
		
		int count = 0;
		int comment_num = 0;
		boolean flag = false;
		count = comm_dbPro.getCommentCount(num);
		if(count > 0){
			commList = comm_dbPro.getComments(num);
		
			for(int i=0; i<commList.size(); i++) {
				if((loginID != null && loginID.equals(commList.get(i).getId()))) {
					comment_num = commList.get(i).getComment_num();
					flag = true;
				}
			}
		}
		String savePath = request.getContextPath()+"/upload";

		boolean image = false;
		if(article.getFilename() != null) {
			image = article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("jpg")
				|| article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("gif")
				|| article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("jpeg")
				|| article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("png")
				|| article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("bit");
		}
		boolean video = false;
		if(article.getFilename() != null) {
			video = article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("mp4")
				|| article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("wmv")
				|| article.getFilename().substring(article.getFilename().lastIndexOf('.')+1).equals("avi");
		}
		request.setAttribute("loginID", loginID);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("sdf", sdf);
		request.setAttribute("article", article);
		request.setAttribute("commList", commList);
		request.setAttribute("count", new Integer(count));
		request.setAttribute("comment_num", new Integer(comment_num));
		request.setAttribute("flag", new Boolean(flag));
		request.setAttribute("path", savePath);
		request.setAttribute("image", new Boolean(image));
		request.setAttribute("video", new Boolean(video));
		return "/WEB-INF/boardtwo/content.jsp";
	}

}
