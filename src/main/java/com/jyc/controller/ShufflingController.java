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
import com.jyc.model.Shuffling;
import com.jyc.service.ShufflingService;
import com.jyc.util.Constant;

/**
 * 轮播图控制器
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/shuffling")
public class ShufflingController {
	@Autowired
	private ShufflingService service;

	@RequestMapping(value = { "", "/" })
	public String shufflings(Shuffling shuffling, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Shuffling> list = service.findAll(shuffling);
		PageInfo<Shuffling> page = new PageInfo<>(list);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("shuffling", shuffling);
		map.put("list", list);
		return "background/shuffling/shuffling-list";
	}

	@GetMapping("/shuffling-add")
	public String addUserGet() {
		return "background/shuffling/shuffling-add";
	}

	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/shuffling-add")
	public String addUser(Shuffling shuffling,
			@RequestParam(name = "picturex", required = false) MultipartFile multipartFile, Map<String, Object> map) {
		if (shuffling.equals("")) {
			if (multipartFile != null) {
				String uuId = UUID.randomUUID().toString();
				int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
				String name = "shuffling/" + uuId + "." + multipartFile.getOriginalFilename().substring(idx + 1);
				String url = Constant.PICTURE_URL + name;
				File file = new File(url);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				shuffling.setPicture(name);
			}
			int row1 = service.insert(shuffling);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/shuffling/shuffling-list";
			} else {
				map.put("shuffling", shuffling);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "输入有空");
			map.put("shuffling", shuffling);
		}
		return "background/shuffling/shuffling-add";
	}

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/shuffling-detail")
	public String upda(Shuffling shuffling, Map<String, Object> map, HttpSession session) {
		if (shuffling.equals("") && shuffling.getId() != null) {
			int row = service.update(shuffling);
			if (row > 0) {
				session.setAttribute("detail", "修改成功");
				return "redirect:/background/shuffling";
			} else {
				map.put("shuffling", shuffling);
				map.put("detail", "修改失败");
				return "/background/shuffling/shuffling-detail";
			}
		} else {
			if (shuffling.getId() != null) {
				Shuffling shuffling1 = service.findById(shuffling.getId());
				map.put("shuffling", shuffling1);
			}
		}
		return "background/shuffling/shuffling-detail";
	}

	@RequestMapping(value = "/shuffling-delete", produces = "application/json;charset=utf-8")
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

	@RequestMapping(value = "/shuffling-delete-many", produces = "application/json;charset=utf-8")
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
