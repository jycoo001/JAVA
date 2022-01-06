package com.jyc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jyc.model.Admin;
import com.jyc.model.Order;
import com.jyc.model.User;
import com.jyc.model.UserAddress;
import com.jyc.service.OrderService;
import com.jyc.service.UserAddressService;
import com.jyc.service.UserService;
import com.jyc.util.Constant;

/**
 * 前台用户管理
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/forward/user")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserAddressService usService;

	@GetMapping(value = { "/login", "/register", "/back", "/update" })
	public String toL(HttpSession session, HttpServletRequest req, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		User user = (User) session.getAttribute("####user_login####");
		if (user != null) {
			map.put("user", user);
		}
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
				if (user.getLocalLastTime() != null) {
					session.setAttribute("detail", "登录成功！您最后一次登录是在" + user.getLocalLastTime());
				} else {
					session.setAttribute("detail", "登录成功！");
				}
				user1.setLastTime(Calendar.getInstance().getTime());
				service.update(user1);
				session.setAttribute("####user_login####", user1);
				session.setMaxInactiveInterval(60 * 60);
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

	@RequestMapping("/my")
	public String my(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("####user_login####");
		List<UserAddress> list1 = usService.findByUserId(user.getId());
		user.setAddress(list1);
		session.setAttribute("####user_login####", user);
		map.put("user", user);
		Order order = new Order();
		order.setUserId(user.getId());
		List<Order> list = orderService.findAll(order);
		map.put("order", list);
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
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
		return "redirect:/";
	}

	@GetMapping("/updateImage")
	public String pic() {
		return "forward/user/edit-picture";
	}

	@PostMapping("updateImage")
	public String editImage(@RequestParam(name = "picturex") MultipartFile multipartFile, Map<String, Object> map,
			HttpSession session) {
		User user = (User) session.getAttribute("####user_login####");
		if (user != null) {
			if (multipartFile != null) {
				String uuId = UUID.randomUUID().toString();
				int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
				String name = "user/" + uuId + "." + multipartFile.getOriginalFilename().substring(idx + 1);
				String url = Constant.PICTURE_URL + name;
				File file = new File(url);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				user.setUserPicture(name);
				int row1 = service.update(user);
				if (row1 > 0) {
					session.setAttribute("detail", "修改成功!");
					return "redirect:/forward/user/my";
				} else {
					map.put("detail", "修改失败！");
					return "forward/user/edit-picture";
				}
			} else {
				return "redirect:/forward/user/my";
			}
		} else {
			return "redirect:/forward/user/login";
		}
	}

	@PostMapping("/update")
	public String update(User user, HttpSession session) {
		int row = service.update(user);
		if (row > 0) {
			session.removeAttribute("####user_login####");
			return "redirect:/forward/user/login";
		} else {
			return "redirect:/forward/user/update";
		}
	}

	@GetMapping("/address-edit")
	public String address(Integer userAddressId, HttpSession session) {
		int row = service.handlerAddress(userAddressId);
		if (row > 0) {
			session.setAttribute("detail", "修改成功");
		} else {
			session.setAttribute("detail", "修改失败");
		}
		return "redirect:/forward/user/my";
	}

}
