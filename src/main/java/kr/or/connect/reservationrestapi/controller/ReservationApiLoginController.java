package kr.or.connect.reservationrestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservationrestapi.dto.*;
import kr.or.connect.reservationrestapi.service.ReservationService;

@Controller
public class ReservationApiLoginController {
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/myreservation")
	public String myreservation(@RequestParam(name="resrv_email", required=true) String resrv_email, HttpSession session){
		System.out.println(resrv_email);
		
		session.setAttribute("resrv_email", "true");
		session.setAttribute("email", resrv_email);
		
		return "myreservation";
	}
	
	@GetMapping("/myreservation")
	public @ResponseBody Object myemail(HttpSession session) {
		return session.getAttribute("email");
	}
	
}
