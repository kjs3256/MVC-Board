package comment.model;

import java.sql.Timestamp;

public class CommentDto {
	private String comment_nickname;
	private Timestamp comment_regdate;
	private String comment_content;
	private String comment_ip;
	private int comment_num;
	private String id;
	private int num;
	
	public String getComment_nickname() {
		return comment_nickname;
	}
	public void setComment_nickname(String comment_nickname) {
		this.comment_nickname = comment_nickname;
	}
	public Timestamp getComment_regdate() {
		return comment_regdate;
	}
	public void setComment_regdate(Timestamp comment_regdate) {
		this.comment_regdate = comment_regdate;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_ip() {
		return comment_ip;
	}
	public void setComment_ip(String comment_ip) {
		this.comment_ip = comment_ip;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
