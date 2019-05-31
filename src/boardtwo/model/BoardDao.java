package boardtwo.model;

import java.sql.*;
import java.util.*;

import util.ConnUtil;

public class BoardDao {
	private static BoardDao instance = null;
	private BoardDao() {}
	public static BoardDao getInstance() {
		if(instance == null) {
			synchronized(BoardDao.class) {
				instance = new BoardDao();
			}
		}
		return instance;
	}
	//DB연동 인스턴스
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//전체 글 개수를 알아오는 메소드
	public int getArticleCount() {
		int count = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from BBS");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	//글 목록을 가져와서 List로 반환하는 메서드
	public List<BoardDto> getArticles(int start, int end){
		List<BoardDto> articleList = null;
		try {
			conn = ConnUtil.getConnection();
			String sql = "select * from "
					+"(select rownum rnum, num, nickname, subject, "
					+"regdate, readcount, ref, step, depth, content, ip from ("
					+"select * from bbs order by ref desc, step asc)) where rnum>=? and rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<BoardDto>();
				do {
					BoardDto article = new BoardDto();
					String subject = rs.getString("subject").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>");
					String nickname = rs.getString("nickname").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>");
					article.setNum(rs.getInt("num"));
					article.setNickname(nickname);
					article.setSubject(subject);
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return articleList;
	}
	//글 저장을 처리하는 메서드
	public void insertArticle(String id, BoardDto article) {
		Connection conn2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		int num = article.getNum(); 	int ref = article.getRef();
		int step = article.getStep();	int depth = article.getDepth();
		int number = 0;		String sql = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from BBS");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				number = rs.getInt(1) + 1;
			}else { number = 1; }
			if(num != 0) {//답글일 경우
				sql = "update BBS set step=step+1 where ref=? and step>?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step+1;
				depth = depth+1;
			}else { //새글일 경우
				ref = number;
				step = 0;
				depth = 0;
			}
			conn2 = ConnUtil.getConnection();
			pstmt2 = conn2.prepareStatement("select NICKNAME from MEMBER where id=?");
			pstmt2.setString(1, id);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				article.setNickname(rs2.getString("nickname"));
			}
			//쿼리 작성
			sql = "insert into BBS (num,nickname,subject,regdate,ref,step,depth,content,ip,id,filename) "
					+"values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getNickname());
			pstmt.setString(2, article.getSubject());
			pstmt.setTimestamp(3, article.getRegdate());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, step);
			pstmt.setInt(6, depth);
			pstmt.setString(7, article.getContent());
			pstmt.setString(8, article.getIp());
			pstmt.setString(9, article.getId());
			pstmt.setString(10, article.getFilename());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs2 != null) rs2.close();
				if(pstmt2 != null) pstmt2.close();
				if(conn2 != null) conn2.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public BoardDto getArticle(int num) {
		BoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update BBS set READCOUNT=READCOUNT+1 where NUM =?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement("select * from BBS where NUM=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDto();
				article.setNum(rs.getInt("num"));
				article.setNickname(rs.getString("nickname"));
				article.setSubject(rs.getString("subject"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setId(rs.getString("id"));
				article.setFilename(rs.getString("filename"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}
	public BoardDto updateGetArticle(int num) {
		BoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from BBS where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDto();
				String subject = rs.getString("subject").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>");
				String nickname = rs.getString("nickname").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>");
				article.setNum(rs.getInt("num"));
				article.setNickname(nickname);
				article.setSubject(subject);
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				article.setId(rs.getString("id"));
				article.setFilename(rs.getString("filename"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}
	
	public int updateArticle(BoardDto article) {
		int ret = -1;
		String sql = "update BBS set subject=?, content=?, filename=? where num=?";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getFilename());
			pstmt.setInt(4, article.getNum());
			
			ret = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	public int deleteArticle(int num) {
		int result = -1; //결과 값
		try {
			conn = ConnUtil.getConnection();
			pstmt=conn.prepareStatement("delete from BBS where NUM=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			result = 1; //글 삭제 성공
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int deleteFile(int num) {
		int result = -1; //결과 값
		try {
			conn = ConnUtil.getConnection();
			pstmt=conn.prepareStatement("update BBS set filename=? where NUM=?");
			pstmt.setString(1, null);
			pstmt.setInt(2, num);
			result = pstmt.executeUpdate();
			//파일 삭제 성공
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
