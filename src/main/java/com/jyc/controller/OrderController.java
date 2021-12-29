package com.jyc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyc.model.User;
import com.jyc.service.OrderService;

@Controller
@RequestMapping("/forward/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService service;

	@PostMapping(value = "/pay", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> pay(@RequestParam(name = "cartList") String cartList, HttpSession session) {
		Map<String, Object> resp = new HashMap<>();

		boolean success = service.handler(cartList, (User) session.getAttribute("####user_login####"));
		resp.put("success", success);
		System.out.println(success);
		return resp;
	}

}
