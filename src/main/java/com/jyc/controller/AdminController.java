package com.jyc.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Admin;
import com.jyc.model.User;
import com.jyc.service.AdminService;
import com.jyc.service.UserService;

/**
 * 后台管理员相关操作
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/admin")
public class AdminController {

	/**
	 * 管理员
	 */
	@Autowired
	private AdminService service;
	/**
	 * 用户
	 */
	@Autowired
	private UserService usService;

	/**
	 * 根据请求的参数，转发到相应的jsp页面
	 * 
	 * @param session 移除会话中的detail提示信息
	 * @param req     通过request得到请求的地址
	 * @param map     封装数据
	 * @return 返回一个jsp页面
	 */
	@GetMapping(value = { "/login", "/back", "/update" })
	public String toL(HttpSession session, HttpServletRequest req, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return req.getServletPath().replace("/background", "background");
	}

	/**
	 * 转发至我的页面
	 * 
	 * @param session 会话得到管理员数据
	 * @param map     封装一个admin实体类
	 * @return 返回一个jsp页面
	 */
	@GetMapping("/my")
	public String my(HttpSession session, Map<String, Object> map) {
		Admin admin = (Admin) session.getAttribute("####admin_login####");
		map.put("admin", admin);
		return "background/admin/admin-info";
	}

	/**
	 * 管理员修改除密码外相关
	 * 
	 * @param admin   前台通过springMVC封装的实体admin
	 * @param session 会话数据，更新管理员数据
	 * @param map     用于失败后回显,和提示信息封装
	 * @return 返回一个jsp页面
	 */
	@PostMapping("/update")
	public String update(Admin admin, HttpSession session, Map<String, Object> map) {
		if (admin.getId() != null) {
			int row = service.update(admin);
			if (row > 0) {
				session.setAttribute("####admin_login####", admin);
				map.put("admin", admin);
				map.put("detail", "修改成功");
			} else {
				map.put("detail", "修改失败");
				map.put("admin", admin);
			}
		}
		return "background/admin/admin-info";
	}

	/**
	 * 管理员修改密码
	 * 
	 * @param password1 原始密码
	 * @param password2 新的密码
	 * @param session   会话数据，更新admin管理员
	 * @param map       封装回显和提示信息
	 * @return 返回一个jsp页面
	 */
	@PostMapping("/update-password")
	public String updatePassword(String password1, String password2, HttpSession session, Map<String, Object> map) {
		Admin admin = (Admin) session.getAttribute("####admin_login####");
		admin.setPassword(password1);
		Admin admin1 = service.login(admin);
		if (admin1 != null) {
			admin1.setPassword(password2);
			int row = service.update(admin1);
			if (row > 0) {
				map.put("detail", "修改成功");
				session.setAttribute("####admin_login####", admin1);
			} else {
				map.put("detail", "修改失败");
			}
		} else {
			map.put("detail", "密码错误");
		}
		return "redirect:/background/admin/my";
	}

	// 管理员对用户进行操作
	/**
	 * 显示用户并分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param user
	 * @param map
	 * @return
	 */
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

	/**
	 * 添加用户
	 * 
	 * @param user 要添加的用户
	 * @param map  回显数据，显示提示
	 * @return 返回一个jsp页面，error：回显add，success：回显用户列表页
	 */
	@SuppressWarnings("unlikely-arg-type")
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

	/**
	 * 修改用户
	 * 
	 * @param user    要修改的用户
	 * @param map     封装的数据
	 * @param session 会话储存重定向后的请求提示
	 * @return 返回一个success：重定向至用户列表，error：在修改jsp中回显
	 */
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

	/**
	 * 删除一个用户，ajax
	 * 
	 * @param ids 通过ajax得到的一个ids
	 * @return 返回一个JSON数据
	 */
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

	/**
	 * 删除多个用户，ajax
	 * 
	 * @param ids 用户的id数组
	 * @return 返回一个JSON数据
	 */
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

	/**
	 * 管理员登陆
	 * 
	 * @param session 将原先的管理员或者用户数据删除，并且添加新的管理员信息
	 * @param map     提示信息
	 * @param admin   封装了用户名和密码
	 * @param code    验证码和session中的验证码进行比较
	 * @return success：重定向至后台主页，error：login页面，并回显
	 */
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
				return "background/admin/login";
			}
		} else {
			map.put("detail", "登陆失败！验证码有误！");
			map.put("admin", admin);
			return "background/user/login";
		}
	}

	/**
	 * 管理员退出登录
	 * 
	 * @param session 移除admin信息
	 * @return 返回至登陆页面
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("####admin_login####");
		return "redirect:/background/admin/login";
	}

	/**
	 * 后台管理员列表
	 * 
	 * @param pageNumber 第几页
	 * @param pageSize   一页有几条数据
	 * @param admin      查询封装的实体
	 * @param map        返回页面、管理员、搜索回显
	 * @return 返回一个jsp页面
	 */
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

	/**
	 * 删除管理员，实际不删，加了一个deleteFlag标记，ajax
	 * 
	 * @param ids 要删除的id
	 * @return 返回JSON数据
	 */
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

	/**
	 * 删除多个，实际不删，ajax
	 * 
	 * @param ids 传来的管理员数组
	 * @return 返回一个JSON
	 */
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

	/**
	 * 添加一个管理员
	 * 
	 * @param admin 要添加的admin
	 * @param map   封装要回显的数据和提示
	 * @return success：list，error：添加回显
	 */
	@SuppressWarnings("unlikely-arg-type")
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

	/**
	 * 修改管理员
	 * 
	 * @param admin   要修改的管理员实体
	 * @param map     回显或者提示
	 * @param session
	 * @return success：admin的list重定向，error：回显，提示
	 */
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
