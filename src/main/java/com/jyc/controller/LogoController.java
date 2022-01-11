package com.jyc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.jyc.model.Logo;
import com.jyc.service.LogoService;
import com.jyc.util.Constant;

@Controller
@RequestMapping("/background/logo")
public class LogoController {
	@Autowired
	private LogoService service;

	@RequestMapping(value = { "", "/" })
	public String logo(Logo logo, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Logo> list = service.findAll(logo);
		PageInfo<Logo> page = new PageInfo<>(list);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("logo", logo);
		map.put("list", list);
		return "background/logo/logo-list";
	}

	@GetMapping("/logo-add")
	public String addUserGet() {
		return "background/logo/logo-add";
	}

	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/logo-add")
	public String addUser(Logo logo, @RequestParam(name = "picturex", required = false) MultipartFile multipartFile,
			Map<String, Object> map) {
		if (logo.equals("")) {
			if (multipartFile != null) {
				String uuId = UUID.randomUUID().toString();
				int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
				String name = "logo/" + uuId + "." + multipartFile.getOriginalFilename().substring(idx + 1);
				String url = Constant.PICTURE_URL + name;
				File file = new File(url);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				logo.setAddress(name);
			}
			int row1 = service.insert(logo);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/logo/logo-list";
			} else {
				map.put("logo", logo);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "输入有空");
			map.put("logo", logo);
		}
		return "background/logo/logo-add";
	}

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/logo-detail")
	public String upda(Logo logo, Map<String, Object> map, HttpSession session) {
		if (logo.equals("") && logo.getId() != null) {
			int row = service.update(logo);
			if (row > 0) {
				session.setAttribute("detail", "修改成功");
				return "redirect:/background/logo";
			} else {
				map.put("logo", logo);
				map.put("detail", "修改失败");
				return "background/logo/logo-detail";
			}
		} else {
			if (logo.getId() != null) {
				Logo logo1 = service.findById(logo.getId());
				map.put("logo", logo1);
			}
		}
		return "background/logo/logo-detail";
	}

	@RequestMapping(value = "/logo-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids") Integer ids) {
		Map<String, Object> map = new HashMap<>();
		int row = service.deleteById(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@RequestMapping(value = "/logo-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
		Map<String, Object> map = new HashMap<>();
		int row = service.deleteByIds(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}
}
