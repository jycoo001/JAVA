package com.jyc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.ViewDate;
import com.jyc.service.AdminService;
import com.jyc.service.OrderService;
import com.jyc.service.UserService;

/**
 * 后台主页
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background")
public class BackgroundController {
	@Autowired
	private UserService usService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/index")
	public String toIndex(HttpSession session, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return "background/index";
	}

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> map) {
		int count = usService.findCount();
		map.put("userCount", count);
		int count1 = adminService.findCount();
		map.put("adminCount", count1);
		int count2 = orderService.findCount();
		map.put("orderCount", count2);
		String start = "2021-01-06 00:00:00";
		Date date = Calendar.getInstance().getTime();

		List<ViewDate> list = usService.findFromTo(start, date);
		map.put("users", list);
		List<ViewDate> list1 = adminService.findFromTo(start, date);
		map.put("admins", list1);
		List<ViewDate> list2 = orderService.findFromTo(start, date);
		map.put("orders", list2);
		return "background/welcome";
	}

}
