package com.jyc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Admin;
import com.jyc.model.User;
import com.jyc.service.AdminService;
import com.jyc.service.UserService;
import com.jyc.util.Constant;

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
	@Autowired
	private UserService usService;

	@GetMapping(value = { "/login", "/back", "/update" })
	public String toL(HttpSession session, HttpServletRequest req, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return req.getServletPath().replace("/background", "background");
	}

	@RequestMapping("/user")
	public String list(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, User user, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<User> list = usService.findAll(user);
		PageInfo<User> page = new PageInfo<>(list);
		map.put("list", list);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("user", user);
		return "background/user/user-list";
	}

	@GetMapping("/user-add")
	public String addUser(User user, Map<String, Object> map) {
		if (user.equals("")) {
			int row1 = usService.insert(user);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/user/user-list";
			} else {
				map.put("user", user);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "输入有空");
			map.put("user", user);
		}
		return "background/user/user-add";
	}

	// 未使用
	@RequestMapping(value = "/user-up", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> addpic(User user, @RequestParam(name = "pic") MultipartFile multipartFile) {
		Map<String, Object> map = new HashMap<>();
		if (user.equals("")) {
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
			}
			int row1 = usService.insert(user);
			if (row1 > 0) {
				map.put("detail", "修改成功");
			} else {
				map.put("detail", "修改失败");
			}
		} else {
			map.put("detail", "输入为空");
		}
		return map;
	}

	@RequestMapping("/user-detail")
	public String upda(User user, Map<String, Object> map, HttpSession session) {
		if (user.getUserId() != null && user.getUserId().trim().length() > 0) {
			int row = usService.update(user);
			if (row > 0) {
				session.setAttribute("detail", "修改成功");
				return "redirect:/background/admin/user";
			} else {
				map.put("user", user);
				map.put("detail", "修改失败");
				return "/background/user/user-detail";
			}
		} else {
			User user1 = usService.findById(user.getId());
			map.put("user", user1);
			return "background/user/user-detail";
		}
	}

	@RequestMapping(value = "/user-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids") Integer ids) {
		Map<String, Object> map = new HashMap<>();
		int row = usService.deleteById(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@RequestMapping(value = "/user-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
		Map<String, Object> map = new HashMap<>();
		int row = usService.deleteByIds(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	// 管理员登陆
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
				if (admin1.getLocalLastLoginTime() != null) {
					session.setAttribute("detail", "登录成功！您最后一次登陆是在" + admin1.getLocalLastLoginTime());
				} else {
					session.setAttribute("detail", "登录成功！");
				}
				admin1.setLastLoginTime(Calendar.getInstance().getTime());
				service.update(admin1);
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

	// 管理员退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("####admin_login####");
		return "redirect:/background/admin/login";
	}

	// 后台用户
	@RequestMapping("/admin")
	public String listAdmin(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Admin admin, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Admin> list = service.findAll(admin);
		PageInfo<Admin> page = new PageInfo<>(list);
		map.put("list", list);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("admin", admin);
		return "background/admin/admin-list";
	}

	@RequestMapping(value = "/admin-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAdmin(@RequestParam(required = true, name = "ids") Integer ids) {
		Map<String, Object> map = new HashMap<>();
		Admin admin = service.findById(ids);
		admin.setDeleteFlag("1");
		int row = service.update(admin);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@RequestMapping(value = "/admin-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAdmin(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
		Map<String, Object> map = new HashMap<>();
		int row = service.updateFlagIds(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@GetMapping("/admin-add")
	public String addAdmin(Admin admin, Map<String, Object> map) {
		admin.setCreatTime(Calendar.getInstance().getTime());

		if (admin.equals("")) {
			int row1 = service.insert(admin);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/admin/admin-list";
			} else {
				map.put("admin", admin);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "输入有空");
			map.put("admin", admin);
		}
		return "background/admin/admin-add";
	}

	@RequestMapping("/admin-detail")
	public String updateAdmin(Admin admin, Map<String, Object> map, HttpSession session) {
		if (admin.getName() != null && admin.getName().trim().length() > 0) {
			int row = service.update(admin);
			if (row > 0) {
				session.setAttribute("detail", "修改成功");
				return "redirect:/background/admin/admin";
			} else {
				map.put("user", admin);
				map.put("detail", "修改失败");
				return "/background/admin/admin-detail";
			}
		} else {
			Admin admin1 = service.findById(admin.getId());
			map.put("admin", admin1);
			return "background/admin/admin-detail";
		}
	}
}
