package kr.or.connect.reservationrestapi.dto;

public class WholeServiceInfo {
	private int id;
	private String description;
	private String content;
	private String placeName;
	private int wholeId;
	
	public int getId() {
		return id;
	}
	public int getWholeId() {
		return wholeId;
	}
	public void setWholeId(int wholeId) {
		this.wholeId = wholeId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Override
	public String toString() {
		return "WholeServiceInfo [id=" + id + ", description=" + description + ", content=" + content + ", placeName="
				+ placeName + ", wholeId=" + wholeId + "]";
	}
	
}