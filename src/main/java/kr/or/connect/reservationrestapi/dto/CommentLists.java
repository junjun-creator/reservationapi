package kr.or.connect.reservationrestapi.dto;

import java.util.Date;

public class CommentLists {
	private int productId;
	private String description;
	private String reservationName;
	private String comment;
	private int score;
	private Date createDate;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "CommentLists [productId=" + productId + ", description=" + description + ", reservationName="
				+ reservationName + ", comment=" + comment + ", score=" + score + ", createDate=" + createDate + "]";
	}
	
}
