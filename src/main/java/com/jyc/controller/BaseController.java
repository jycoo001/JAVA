package com.jyc.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.service.BaseService;

/**
 * 基本的控制器，此控制器中有得到属性名为service的方法，基本的list、insert、update、delete
 * 
 * @author 12430
 *
 */
public class BaseController {

	private BaseService getService() {
		try {
			Field field = this.getClass().getDeclaredField("service");
			field.setAccessible(true);
			return (BaseService) field.get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/list")
	public <T> String findAll(T t, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "8") Integer pageSize, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<T> list1 = getService().findAll(t);
		PageInfo<T> page = new PageInfo<>(list1);
		page.calcByNavigatePages(5);

		map.put("list", list1);
		map.put("page", page);
		return t.getClass().getSimpleName().toLowerCase() + "/list";
	}

	@GetMapping(value = { "/insert", "/update/{id}" })
	public <T> String toAddEdit(@PathVariable(required = false) Integer id, Map<String, Object> map,
			HttpServletRequest req) {
		if (id != null) {
			T t = getService().findById(id);
			map.put("t", t);
		}
		String path = req.getServletPath().substring(1, req.getServletPath().length());
		if (path.contains("insert")) {
			path = path.replace("insert", "update");
		}
		return path;
	}

	@PostMapping(value = { "/insert", "/update" })
	public <T> String edit(HttpSession session, HttpServletRequest req, T t, Map<String, Object> map) {
		String path = req.getServletPath();
		if (path.contains("insert")) {
			if (t.equals("")) {
				int row = getService().insert(t);
				if (row > 0) {
					session.setAttribute("detail", "添加成功");
					return "redirect:" + path.replace("insert", "") + "list";
				} else {
					map.put("detail", "添加失败");
					map.put("t", t);
					return path.substring(1);
				}
			} else {
				map.put("detail", "添加项不能为空");
				map.put("t", t);
				return path.substring(1);
			}
		} else {
			if (t.equals("")) {
				int row = getService().update(t);
				if (row > 0) {
					session.setAttribute("detail", "修改成功");
					return "redirect:" + path.replace("insert", "") + "list";
				} else {
					map.put("detail", "修改失败");
					map.put("t", t);
					return path.substring(1);
				}
			} else {
				map.put("detail", "修改项不能为空");
				map.put("t", t);
				return path.substring(1);
			}
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(required = true) Integer id, HttpSession session, HttpServletRequest req) {
		int index = req.getServletPath().lastIndexOf("/delete/");
		String path = req.getServletPath().substring(0, index);
		if (id != null) {
			int row = getService().deleteById(id);
			if (row > 0) {
				session.setAttribute("detail", "删除成功");
			} else {
				session.setAttribute("detail", "删除失败");
			}
		}
		return "redirect:" + path + "/list";
	}

	@PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(name = "che[]") Integer[] ids) {
		Map<String, Object> resp = new HashMap<>();
		if (ids.length > 0) {
			int rows = getService().deleteByIds(ids);
			if (rows > 0) {
				resp.put("success", true);
				resp.put("rows", rows);
			} else {
				resp.put("success", false);
				resp.put("error", "删除失败");
			}
		} else {
			resp.put("success", false);
			resp.put("error", "删除的项不能为空");
		}
		return resp;
	}
}
