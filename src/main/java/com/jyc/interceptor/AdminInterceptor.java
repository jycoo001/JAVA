package com.jyc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.jyc.model.Admin;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("####admin_login####");
		if (admin != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/background/admin/login");
			return false;
		}
	}

}
