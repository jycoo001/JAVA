package com.jyc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.Admin;
import com.jyc.model.User;
import com.jyc.service.AdminService;

/**
 * 后台管理员登录
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService service;

	@GetMapping(value = { "/login", "/back", "/update" })
	public String toL(HttpSession session, HttpServletRequest req, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return req.getServletPath().replace("/background", "background");
	}

	@PostMapping("/login")
	public String login(HttpSession session, Map<String, Object> map, Admin admin, String code) {
		String sessionCode = "" + session.getAttribute("verification");
		if (code.equals(sessionCode)) {
			session.removeAttribute("verification");
			User userLogin = (User) session.getAttribute("####user_login####");
			if (userLogin != null) {
				session.removeAttribute("####user_login####");
			}
			Admin adminLogin = (Admin) session.getAttribute("####admin_login####");
			if (adminLogin != null) {
				session.removeAttribute("####admin_login####");
			}
			Admin admin1 = service.login(admin);
			if (admin1 != null) {
				session.setAttribute("detail", "登录成功！");
				session.setAttribute("####admin_login####", admin1);
				session.setMaxInactiveInterval(10 * 60);
				return "redirect:/background/index";
			} else {
				map.put("detail", "登陆失败！用户名或密码错误！");
				map.put("user", admin);
				return "background/user/login";
			}
		} else {
			map.put("detail", "登陆失败！验证码有误！");
			map.put("user", admin);
			return "background/user/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("####admin_login####");
		return "redirect:/background/admin/login";
	}

}
