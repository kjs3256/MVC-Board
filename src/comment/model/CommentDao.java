package comment.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.ConnUtil;

public class CommentDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static CommentDao instance = null;
	private CommentDao() {}
	public static CommentDao getInstance() {
		if(instance == null) {
			synchronized(CommentDao.class) {
				instance = new CommentDao();
			}
		}
		return instance;
	}
	
	public void insertComment(CommentDto comm) {
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("insert into COMMENT_BBS values(?,?,?,?,COMMENT_SEQ.nextval,?,?)");
			pstmt.setString(1, comm.getComment_nickname());
			pstmt.setTimestamp(2, comm.getComment_regdate());
			pstmt.setString(3, comm.getComment_content());
			pstmt.setString(4, comm.getComment_ip());
			pstmt.setString(5, comm.getId());
			pstmt.setInt(6, comm.getNum());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int getCommentCount(int num) {
		int x = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from COMMENT_BBS where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
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
		return x;
	}
	public List<CommentDto> getComments(int num){
		List<CommentDto> commList = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from COMMENT_BBS where num=? order by comment_regdate asc");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				commList = new ArrayList<CommentDto>();
				do {
					CommentDto tmp = new CommentDto();
					tmp.setComment_nickname(rs.getString("comment_nickname"));
					tmp.setComment_regdate(rs.getTimestamp("comment_regdate"));
					tmp.setComment_content(rs.getString("comment_content"));
					tmp.setComment_ip(rs.getString("comment_ip"));
					tmp.setComment_num(rs.getInt("comment_num"));
					tmp.setId(rs.getString("id"));
					tmp.setNum(rs.getInt("num"));
					commList.add(tmp);
				}while(rs.next());
			}
		}catch(Exception e){
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
		return commList;
	}
	public int deleteComment(int comment_num) {
		int result = -1; //결과 값
		try {
			conn = ConnUtil.getConnection();
			pstmt=conn.prepareStatement("delete from COMMENT_BBS where COMMENT_NUM=?");
			pstmt.setInt(1, comment_num);
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
}
