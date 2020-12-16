package kr.or.connect.reservationrestapi.service;

import java.util.List;

import kr.or.connect.reservationrestapi.dto.*;

public interface ReservationService {
	public List<FileInfo> getPromotionImage();
	public List<WholeServiceInfo> getAllItems(Integer start);
	public List<WholeServiceInfo> getItemDetail(Integer id);
	public List<CommentLists> getCommentLists(Integer productId);
	public List<DisplayInfo> getLocation(Integer productId);
	public List<DisplayInfo> getId(Integer id);
	public List<FileInfo> getMapImg(Integer id);
	public List<CommentLists> getAllComment(Integer productId);
	public int getCount();
	public double avgRate(Integer productId);
	public int getCountComment(Integer productId);
	public List<WholeServiceInfo> getItemsCategory(Integer categoryId, Integer start);
	public int getCountCategory(Integer categoryId);
	public List<FileInfo> getProductImage();
	public static final Integer LIMIT = 4;
	public static final Integer COMMENTLIMIT = 3;
	public List<DisplayInfo> getPlaceAndOpeninghours(Integer id);
	public List<ReservationInfo> getMyReservation(String email);
	public ReservationInfo addReservation(ReservationInfo reservationInfo);
	public int cancelItem(ReservationInfo reservationInfo);
}
