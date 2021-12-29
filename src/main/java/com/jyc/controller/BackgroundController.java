package com.jyc.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background")
public class BackgroundController {

	@RequestMapping("/index")
	public String toIndex(HttpSession session, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return "background/index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "background/welcome";
	}

}
