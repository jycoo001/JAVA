package com.jyc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.Admin;
import com.jyc.model.Order;
import com.jyc.model.User;
import com.jyc.service.OrderService;
import com.jyc.service.UserService;

/**
 * 前台用户管理
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/forward/user")
public class UserController extends BaseController {

	@Autowired
	private UserService service;
	@Autowired
	private OrderService orderService;

	@GetMapping(value = { "/login", "/register", "/back", "/update" })
	public String toL(HttpSession session, HttpServletRequest req, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return req.getServletPath().replace("/forward", "forward");
	}

	@PostMapping("/login")
	public String login(HttpSession session, Map<String, Object> map, User user, String code) {
		String sessionCode = "" + session.getAttribute("verification");
		if (code.equals(sessionCode)) {
			session.removeAttribute("verification");
			Admin adminLogin = (Admin) session.getAttribute("####admin_login####");
			if (adminLogin != null) {
				session.removeAttribute("####admin_login####");
			}
			User userLogin = (User) session.getAttribute("####user_login####");
			if (userLogin != null) {
				session.removeAttribute("####user_login####");
			}
			User user1 = service.login(user);
			if (user1 != null) {
				session.setAttribute("detail", "登录成功！");
				session.setAttribute("####user_login####", user1);
				session.setMaxInactiveInterval(10 * 60);
				map.put("detail", "登陆成功");
				map.put("user", user1);
				return "forward/user/login";
			} else {
				map.put("detail", "登陆失败！用户名或密码错误！");
				map.put("user", user);
				return "forward/user/login";
			}
		} else {
			map.put("detail", "登陆失败！验证码有误！");
			map.put("user", user);
			return "forward/user/login";
		}
	}

	@PostMapping("/register")
	public String register(HttpSession session, Map<String, Object> map, User user) {
		int rows = service.register(user);
		if (rows > 0) {
			session.setAttribute("detail", "注册成功！");
			return "redirect:/forward/user/login";
		} else {
			map.put("detail", "注册失败");
			map.put("user", user);
			return "forward/user/register";
		}
	}

	@PostMapping("/back")
	public String back(HttpSession session, Map<String, Object> map, User user) {
		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			User us = service.login(user);
			map.put("user", us);
			return "forward/user/back";
		} else {
			int rows = service.update(user);
			if (rows > 0) {
				session.setAttribute("detail", "修改成功！");
				return "redirect:/forward/user/login";
			} else {
				map.put("detail", "修改失败");
				map.put("user", user);
				return "forward/user/back";
			}
		}
	}

	@PostMapping("/update")
	public String update(HttpSession session, Map<String, Object> map, User user) {
		if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
			user.setPassword("");
		}
		int rows = service.update(user);
		if (rows > 0) {
			session.setAttribute("detail", "修改成功！");
			return "redirect:/user/list";
		} else {
			map.put("detail", "修改失败");
			map.put("user", user);
			return "user/update";
		}
	}

	@RequestMapping("/my")
	public String my(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("####user_login####");
		map.put("user", user);
		Order order = new Order();
		order.setUserId(user.getId());
		List<Order> list = orderService.findAll(order);
		map.put("order", list);
		return "forward/user/my";
	}

	@RequestMapping("/address")
	public String address(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		map.put("user", user);
		return "forward/address";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("####user_login####");
		return "redirect:/index";
	}

}
