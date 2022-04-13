package egovframework.myblog.core.vo;

public class BoardVo {
	private int iBoard;
	private String userId;
	private String title;
	private String content;
	private String postDt;
	
	public int getiBoard() {
		return iBoard;
	}
	public void setiBoard(int iBoard) {
		this.iBoard = iBoard;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostDt() {
		return postDt;
	}
	public void setPostDt(String postDt) {
		this.postDt = postDt;
	}
	
}
