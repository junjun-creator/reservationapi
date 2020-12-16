package kr.or.connect.reservationrestapi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservationrestapi.dto.*;
import kr.or.connect.reservationrestapi.service.ReservationService;

@RestController
public class ReservationApiController {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/reservationlist")
	public Map<String, Object> list(@RequestParam(name="start", required=false, defaultValue="0") int start, HttpSession session) {
		List<FileInfo> list = reservationService.getPromotionImage();
		//List<Product> productInfo = reservationService.getProductInfo();
		//List<DisplayInfo> placeName = reservationService.getPlaceName();
		List<FileInfo> productImg = reservationService.getProductImage();
		List<WholeServiceInfo> wholeServiceInfo = reservationService.getAllItems(start);
		int sessionCount=0;
		String sessionEmail =null;
		
		int count = reservationService.getCount();
		List<Integer> countCategory = new ArrayList<>();
		for(int categoryId=1;categoryId<=5;categoryId++) {
			countCategory.add(reservationService.getCountCategory(categoryId));
		}
		int pageCount = count/ReservationService.LIMIT;
		if(count % ReservationService.LIMIT > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * ReservationService.LIMIT);
		}
		
		if(session.getAttribute("email")  != null) {
			sessionCount = 1;
			sessionEmail = (String)session.getAttribute("email");
		}
		else {
			sessionCount = 0;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list",list);
		map.put("productImg",productImg);
		map.put("allItem",wholeServiceInfo);
		map.put("pageStartList",pageStartList);
		map.put("count",count);
		map.put("countCategory",countCategory);
		map.put("sessionCount",sessionCount);
		map.put("sessionEmail",sessionEmail);
		return map;
	}
	
	@PostMapping("/reservationlist")//ajax 통신 할것임
	public List<WholeServiceInfo> moreItems(@RequestBody CategoryIdStartNum categoryItem) throws Exception {
		int category = categoryItem.getCategoryId();
		int startNum = categoryItem.getStart();
		if(category != 0) {
			
			List<WholeServiceInfo> moreItemsCategory = reservationService.getItemsCategory(category, startNum);
			
			return moreItemsCategory;
		}
		else {
			
			List<WholeServiceInfo> moreServiceInfo = reservationService.getAllItems(startNum);
			
			return moreServiceInfo;
		}
		//if 조건문으로 어떤 list를 전송해 줄것인지 선택하는 로직 작성
	}
	
	@GetMapping("/details")
	public Map<String, Object> detail(int id, HttpSession session) throws Exception{
		
		List<FileInfo> productImg = reservationService.getProductImage();
		List<WholeServiceInfo> itemDetail = reservationService.getItemDetail(id);
		List<DisplayInfo> location = reservationService.getLocation(id);
		List<DisplayInfo> to_id = reservationService.getId(id);
		int countComment = reservationService.getCountComment(to_id.get(0).getProductId());
		List<FileInfo> mapImg = reservationService.getMapImg(id);
		List<CommentLists> commentLists = reservationService.getCommentLists(to_id.get(0).getProductId());
		int sessionCount =0;
		String sessionEmail = null;
		double avg;
		try {
			avg = reservationService.avgRate(to_id.get(0).getProductId());
		}catch(NullPointerException e) {
			avg = 0.0;
		}
		
		if(session.getAttribute("email") != null) {
			sessionCount =1;
			sessionEmail = (String)session.getAttribute("email");
		}
		else {
			sessionCount = 0;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("id",id);
		map.put("countComment",countComment);
		map.put("productImg",productImg);
		map.put("itemDetail",itemDetail);
		map.put("location",location);
		map.put("toId",to_id);
		map.put("mapImg",mapImg);
		map.put("commentLists",commentLists);
		map.put("avg",avg);
		map.put("sessionCount",sessionCount);
		map.put("sessionEmail",sessionEmail);
		for(CommentLists a : commentLists) {
			System.out.println(a.getCreateDate());
		}
		/*
		ModelAndView mav = new ModelAndView();
        mav.setViewName("detail");
        mav.addObject("map", map);
        
        return mav;*/
		return map;
	}
	
	@GetMapping("/reviews")
	public Map<String, Object> review(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		List<DisplayInfo> to_id = reservationService.getId(id);
		int id_product = to_id.get(0).getProductId();
		int countComment = reservationService.getCountComment(id_product);
		List<CommentLists> allComment = reservationService.getAllComment(to_id.get(0).getProductId());
		List<FileInfo> productImg = reservationService.getProductImage();
		double avg;
		try {
			avg = reservationService.avgRate(to_id.get(0).getProductId());
		}catch(NullPointerException e) {
			avg = 0.0;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("id",id);
		map.put("id_product", id_product);
		map.put("productImg",productImg);
		map.put("avgRate",avg);
		map.put("countComment",countComment);
		map.put("allComment",allComment);
		
		return map;
	}
	
	@GetMapping("/reserves")
	public Map<String, Object> reserve(int id){
		List<DisplayInfo> to_id = reservationService.getId(id);
		int id_product = to_id.get(0).getProductId();
		List<FileInfo> productImg = reservationService.getProductImage();
		List<DisplayInfo> placeAndOpeninghours = reservationService.getPlaceAndOpeninghours(id);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.");//날짜 포맷 형식 지정
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String currentDate = dateFormat.format(new Date());
		System.out.println(currentDate);
		
		Calendar cal = Calendar.getInstance();//캘린더 클래스를 통해 날짜 인스턴스 생성
		cal.set(2020, Calendar.AUGUST, 26);//날짜 정보 입력
		cal.add(Calendar.DATE, (int)(Math.random()*5+1));//date를 1~5일 뒤의 날로 랜덤 수정
		System.out.println(dateFormat.format(cal.getTime()));//캘린더 클래스로 생성한 날짜를 날짜 포맷 형식으로 적용해서 출력
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("id",id);
		map.put("productImg",productImg);
		map.put("id_product",id_product);
		map.put("placeAndOpeninghours",placeAndOpeninghours);
		map.put("reservationDate",dateFormat.format(cal.getTime()));//예약 페이지에서 사용할 예약일자
		map.put("reservationDateTime",dateFormat2.format(cal.getTime()));//DB에 적용할 예약일자 포맷
		
		return map;
	}
	
	@PostMapping("/enroll")
	public Map<String,Object> enroll(@RequestParam(name="name", required=true) String name,
			@RequestParam(name="tel", required=true) String tel,
			@RequestParam(name="email", required=true) String email,
			@RequestParam(name="item_id", required=true) String id,
			@RequestParam(name="reserve_date", required=true) String reserve_date, HttpServletRequest req) {
		
		List<DisplayInfo> to_id = reservationService.getId(Integer.parseInt(id));
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String currentDate = dateFormat2.format(new Date());
		System.out.println(currentDate);
		
		int id_product = to_id.get(0).getProductId();
		ReservationInfo reservationInfo = new ReservationInfo();
		
		reservationInfo.setProductId(id_product);
		reservationInfo.setDisplayInfoId(Integer.parseInt(id));
		reservationInfo.setReservationName(name);
		reservationInfo.setReservationTel(tel);
		reservationInfo.setReservationEmail(email);
		reservationInfo.setReservationDate(reserve_date);
		reservationInfo.setCreateDate(new Date());
		reservationInfo.setModifyDate(new Date());
		
		//ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfo",reservationInfo);
		
		System.out.println(reservationInfo);
		System.out.println(id);
		System.out.println(id_product);
		System.out.println(name);
		System.out.println(tel);
		System.out.println(email);
		System.out.println(reserve_date);
		
		reservationService.addReservation(reservationInfo);
		//return "redirect:reservationlists";
		
		//mv.setViewName("redirect:reservationlists");
		//mv.addObject("reservationInfo", reservationInfo);
		return map;
	}
	
	@RequestMapping(path="/myreservations")
	public Map<String, Object> myreservations(@RequestBody ReservationInfo reservationInfo, HttpSession session){
		String email = reservationInfo.getReservationEmail();
		System.out.println(email+"111");
		
		//session.setAttribute("resrv_email", "true");
		//session.setAttribute("email", email);
		
		List<ReservationInfo> myReservation = reservationService.getMyReservation(email);
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("myreservation",myReservation);
		return map;
	}
	
	@PostMapping("/cancel")
	public Map<String,Object> cancel(@RequestParam(name="reservation_info_id", required=true) String display_id,
			@RequestParam(name="resrv_email", required=true) String resrv_email) {
		
		int item_display_id = Integer.parseInt(display_id);
		System.out.println(item_display_id);
		
		ReservationInfo reservationInfo = new ReservationInfo();
		reservationInfo.setId(item_display_id);
		//reservationInfo.setReservationEmail(resrv_email);
		
		int update = reservationService.cancelItem(reservationInfo);
		
		Map<String,Object> map = new HashMap<>();
		map.put("update",update);
		
		return map;
	}
}
