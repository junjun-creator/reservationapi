package kr.or.connect.reservationrestapi.dto;

public class CategoryIdStartNum {
	private int categoryId;
	private int start;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "CategoryIdStartNum [categoryId=" + categoryId + ", start=" + start + "]";
	}
	
	
}
