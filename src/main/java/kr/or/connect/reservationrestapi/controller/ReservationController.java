package kr.or.connect.reservationrestapi.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservationrestapi.dto.*;
import kr.or.connect.reservationrestapi.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/detail")
	public String details() {
		return "detail";
	}
	
	@GetMapping("/review")
	public String review() {
		return "review";
	}
	
	@GetMapping("/reserve")
	public String reserve() {
		return "reserve";
	}
	
	@GetMapping("/reservationlists")
	public String main() {
		return "reservationlists";
	}
	
	@GetMapping("/bookinglogin")
	public String bookinglogin() {
		return "bookinglogin";
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "cancel";
	}
}
