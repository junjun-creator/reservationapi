package kr.or.connect.reservationrestapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservationrestapi.dao.ReservationDao;
import kr.or.connect.reservationrestapi.dto.*;
import kr.or.connect.reservationrestapi.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;

	@Override
	@Transactional
	public List<FileInfo> getPromotionImage() {
		List<FileInfo> list = reservationDao.selectAllPromotionFileName();
		return list;
	}
	
	@Override
	@Transactional
	public List<WholeServiceInfo> getAllItems(Integer start) {
		List<WholeServiceInfo> list = reservationDao.selectAllWholeServiceInfo(start,ReservationService.LIMIT);
		return list;
	}
	@Override
	@Transactional
	public List<WholeServiceInfo> getItemDetail(Integer id) {
		List<WholeServiceInfo> list = reservationDao.selectItemDetail(id);
		return list;
	}
	@Override
	public int getCount() {
		return reservationDao.selectCount();
	}
	@Override
	public int getCountCategory(Integer categoryId) {
		return reservationDao.selectCountCategory(categoryId);
	}
	@Override
	@Transactional
	public List<WholeServiceInfo> getItemsCategory(Integer categoryId, Integer start){
		List<WholeServiceInfo> list = reservationDao.selectItemsCategory(categoryId, start, ReservationService.LIMIT);
		return list;
	}

	@Override
	@Transactional
	public List<FileInfo> getProductImage() {
		List<FileInfo> list = reservationDao.selectAllProductImage();
		return list;
	}
	
	@Override
	@Transactional
	public List<CommentLists> getCommentLists(Integer productId) {
		List<CommentLists> list = reservationDao.selectComment(productId, ReservationService.COMMENTLIMIT);
		return list;
	}
	
	@Override
	public List<CommentLists> getAllComment(Integer productId) {
		List<CommentLists> list = reservationDao.selectAllComment(productId);
		return list;
	}

	@Override
	public double avgRate(Integer productId) {
		return reservationDao.avgRate(productId);
	}
	@Override
	public int getCountComment(Integer productId) {
		return reservationDao.countComment(productId);
	}

	@Override
	@Transactional
	public List<DisplayInfo> getLocation(Integer productId) {
		List<DisplayInfo> list = reservationDao.selectLocation(productId);
		return list;
	}

	@Override
	public List<DisplayInfo> getId(Integer id) {
		List<DisplayInfo> list = reservationDao.getId(id);
		return list;
	}

	@Override
	public List<FileInfo> getMapImg(Integer id) {
		List<FileInfo> list = reservationDao.getMapImg(id);
		return list;
	}
	@Override
	public List<DisplayInfo> getPlaceAndOpeninghours(Integer id) {
		List<DisplayInfo> list = reservationDao.getPlaceAndOpeninghours(id);
		return list;
	}

	@Override
	public List<ReservationInfo> getMyReservation(String email) {
		List<ReservationInfo> list = reservationDao.getMyReservation(email);
		return list;
	}
	
	@Override
	@Transactional(readOnly=false)
	public ReservationInfo addReservation(ReservationInfo reservationInfo) {
		Long id = reservationDao.insert(reservationInfo);
		System.out.println(id);
		
		return reservationInfo;
	}

	@Override
	@Transactional(readOnly=false)
	public int cancelItem(ReservationInfo reservationInfo) {
		int update = reservationDao.cancelItem(reservationInfo);
		return update;
	}
}
