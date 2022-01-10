package com.jyc.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.jyc.model.Admin;
import com.jyc.model.Shuffling;
import com.jyc.model.User;
import com.jyc.service.AdminService;
import com.jyc.service.ShufflingService;
import com.jyc.service.UserService;

@Controller
public class ExcelController {
	@Autowired
	private UserService service;
	@Autowired
	private AdminService adService;
	@Autowired
	private ShufflingService shufflingService;

	@GetMapping(value = "/background/user/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportUser(Integer pageNumber, Integer pageSize, User user, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}

		if (pageSize == null) {
			pageSize = 5;
		}

		// 自动进行分页，注意：分页时要指定排序字段
		PageHelper.startPage(pageNumber, pageSize);

		// 查询全部
		List<User> list = service.findAll(user);

		// 导出操作
		// 1.设置响应类型
		// 2.获取要导出的数据
		// 3.指定导出时的文件名称
		LocalDateTime ldt = LocalDateTime.now();
		// 导出的文件名
		String fileName = "用户信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH:mm:ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		// 4.导出操作，需要在模型类中使用注解指定哪些属性需要导出
		EasyExcel.write(resp.getOutputStream(), User.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("用户信息表").doWrite(list);
	}

	@GetMapping(value = "/background/admin/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportAdmin(Integer pageNumber, Integer pageSize, Admin admin, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<Admin> list = adService.findAll(admin);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "管理员信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH:mm:ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), Admin.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("管理员信息表").doWrite(list);
	}

	@GetMapping(value = "/background/shuffling/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportShuffling(Integer pageNumber, Integer pageSize, Shuffling shuffling, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<Shuffling> list = shufflingService.findAll(shuffling);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "轮播图信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH-mm-ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), Shuffling.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("轮播图信息表").doWrite(list);
	}
}
