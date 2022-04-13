package egovframework.myblog.core.vo;

public class BoardDTO extends BoardVo {
	private String nm;
	private int categoryPk;
	private String categoryName;
	private String type;
	private int pageNo;
	private int firstRecordIndex;
	private int recordCountPerPage;
	

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public int getCategoryPk() {
		return categoryPk;
	}

	public void setCategoryPk(int categoryPk) {
		this.categoryPk = categoryPk;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	@Override
	public String toString() {
		return "userId=" + super.getUserId() + ", categoryName=" + categoryName + ", type=" + type;
	}
	
}
