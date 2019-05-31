package member.model;

import java.sql.*;
import java.time.LocalDateTime;

import util.ConnUtil;

public class MemberDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static MemberDao instance = null;
	
	private MemberDao() {}
	public static MemberDao getInstance() {
		if(instance == null) {
			synchronized(MemberDao.class) {
				instance = new MemberDao();
			}
		}
		return instance;
	}
	//로그인 시도 메소드
	public int login(String id, String pass) {
		String sql = "select pass from MEMBER where id=?";
		int check = -1;
		try {
			conn = ConnUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					check = 1; //로그인 성공
				}
				else check = 0; //비밀번호 틀림
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
		return check; // 아이디 없음
	}
	//아이디 중복 체크
	public boolean idCheck(String id) {
		boolean ret = true;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) ret=false;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return ret;
	}
	//회원가입
	@SuppressWarnings("finally")
	public boolean userInsert(MemberDto dto) {
		boolean flag = false;
		try {
			conn = ConnUtil.getConnection();
			String sql = "insert into MEMBER values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getEmail());
			pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(5, dto.getNickname());
			pstmt.setString(6, dto.getGender());
			pstmt.setInt(7, dto.getAge());
			pstmt.setString(8, dto.getLoc());
			int count = pstmt.executeUpdate();
			if(count>0) flag = true;
		}catch(Exception e) {
			System.out.println("Error : "+e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			return flag;
		}
	}
	public int userDelete(String id, String pass) {
		int ret = -1;
		String dbPass = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select PASS from MEMBER where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbPass = rs.getString("pass");
				if(dbPass.equals(pass)) {
					pstmt.close();
					pstmt = conn.prepareStatement("delete from BBS where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					pstmt.close();
					pstmt = conn.prepareStatement("delete from MEMBER where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					ret = 1; //삭제 성공
				}else {
					ret = 0; //본인확인 실패
				}
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
		return ret;
	}
	public MemberDto getUser(String id) {
		MemberDto udto = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				udto = new MemberDto();
				udto.setId(rs.getString("id"));
				udto.setPass(rs.getString("pass"));
				udto.setEmail(rs.getString("email"));
				udto.setRegdate(rs.getTimestamp("regdate"));
				udto.setNickname(rs.getString("nickname"));
				udto.setGender(rs.getString("gender"));
				udto.setAge(rs.getInt("age"));
				udto.setLoc(rs.getString("loc"));
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
		return udto;
	}
}
