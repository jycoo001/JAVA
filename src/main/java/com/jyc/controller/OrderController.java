package com.jyc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyc.model.Order;
import com.jyc.model.User;
import com.jyc.service.OrderService;

/**
 * 订单控制器
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/forward/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService service;

	@PostMapping(value = "/pay", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> pay(@RequestParam(name = "cartList") String cartList, HttpSession session) {
		Map<String, Object> resp = new HashMap<>();

		boolean success = service.handler(cartList, (User) session.getAttribute("####user_login####"), resp);
		resp.put("success", success);
		System.out.println(success);
		return resp;
	}

	@GetMapping("/all")
	public String allOrder(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute("####user_login####");
		Order order = new Order();
		order.setUserId(user.getId());
		List<Order> list = service.findAll(order);
		map.put("order", list);
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		map.put("user", user);
		return "forward/user/order";
	}

	@GetMapping("/orderUpdate")
	public String orderUpdate(Integer id) {
		Order order = service.findById(id);
		order.setUserStatus("已收货");
		int row = service.update(order);
		if (row > 0) {
			return "redirect:/forward/user/my";
		} else {
			return "redirect:/forward/user/order";
		}
	}

	@GetMapping("/noorder")
	public String nofo(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute("####user_login####");
		Order order = new Order();
		order.setUserId(user.getId());
		order.setStatus("未发货");
		List<Order> list = service.findAll(order);
		map.put("order", list);
		map.put("user", user);
		return "forward/user/order";
	}

	@GetMapping("/orderno")
	public String no(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute("####user_login####");
		Order order = new Order();
		order.setUserId(user.getId());
		order.setUserStatus("未收货");
		List<Order> list = service.findAll(order);
		map.put("order", list);
		map.put("user", user);
		return "forward/user/order";
	}

	@GetMapping("/orderyes")
	public String yes(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute("####user_login####");
		Order order = new Order();
		order.setUserId(user.getId());
		order.setUserStatus("已收货");
		List<Order> list = service.findAll(order);
		map.put("order", list);
		map.put("user", user);
		return "forward/user/order";
	}

	@PostMapping(value = "/desc-page", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> descPage(@RequestParam(name = "id") Integer id) {
		Map<String, Object> map = new HashMap<>();
		Order order = service.findById(id);
		map.put("order", order);
		return map;
	}

	@RequestMapping("/desc")
	public String addDesc(Integer id, String desc, HttpSession session) {
		Order order = service.findById(id);
		order.setDesc(desc);
		int row = service.update(order);
		if (row > 0) {
			session.setAttribute("detail", "评论成功");
		} else {
			session.setAttribute("detail", "评论失败");
		}
		return "redirect:/forward/order/all";
	}
}
