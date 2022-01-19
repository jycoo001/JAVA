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
import com.jyc.model.Back;
import com.jyc.model.Goods;
import com.jyc.model.GoodsType;
import com.jyc.model.Logo;
import com.jyc.model.Order;
import com.jyc.model.Shuffling;
import com.jyc.model.User;
import com.jyc.service.AdminService;
import com.jyc.service.BackService;
import com.jyc.service.GoodsService;
import com.jyc.service.GoodsTypeService;
import com.jyc.service.LogoService;
import com.jyc.service.OrderService;
import com.jyc.service.ShufflingService;
import com.jyc.service.UserService;

/**
 * 导出至Excel的控制器
 * 
 * @author 12430
 *
 */
@Controller
public class ExcelController {
	/**
	 * 用户
	 */
	@Autowired
	private UserService service;
	/**
	 * 管理员
	 */
	@Autowired
	private AdminService adService;
	/**
	 * 轮播图
	 */
	@Autowired
	private ShufflingService shufflingService;

	/**
	 * 商品类别
	 */
	@Autowired
	private GoodsTypeService goodsTypeService;

	@Autowired
	private LogoService logoService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BackService backService;

	/**
	 * 用户信息导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param user
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping(value = "/background/user/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportUser(Integer pageNumber, Integer pageSize, User user, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}

		if (pageSize == null) {
			pageSize = 5;
		}

		PageHelper.startPage(pageNumber, pageSize);
		List<User> list = service.findAll(user);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "用户信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH:mm:ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), User.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("用户信息表").doWrite(list);
	}

	/**
	 * 管理员信息导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param admin
	 * @param resp
	 * @throws IOException
	 */
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

	/**
	 * 轮播图导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param shuffling
	 * @param resp
	 * @throws IOException
	 */
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

	/**
	 * 商品类别导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param type
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping(value = "/background/type/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportType(Integer pageNumber, Integer pageSize, GoodsType type, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<GoodsType> list = goodsTypeService.findAll(type);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "商品类别信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH-mm-ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), GoodsType.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("商品类别信息表").doWrite(list);
	}

	/**
	 * LOGO导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param type
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping(value = "/background/logo/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportLOGO(Integer pageNumber, Integer pageSize, Logo logo, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<Logo> list = logoService.findAll(logo);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "商城LOGO信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH-mm-ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), Logo.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("商城LOGO信息表").doWrite(list);
	}

	/**
	 * 商品导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param type
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping(value = "/background/goods/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportGoods(Integer pageNumber, Integer pageSize, Goods goods, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<Goods> list = goodsService.findAll(goods);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "商品信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH-mm-ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), Goods.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("商品信息表").doWrite(list);
	}

	/**
	 * 订单导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param type
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping(value = "/background/order/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportOrder(Integer pageNumber, Integer pageSize, Order order, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<Order> list = orderService.findAll(order);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "订单信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH-mm-ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), Order.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("订单信息表").doWrite(list);
	}

	/**
	 * 退货单导出
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param type
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping(value = "/background/back/excel", produces = "application/vnd.ms-excel;charset=utf-8")
	public void exportBack(Integer pageNumber, Integer pageSize, Back back, HttpServletResponse resp)
			throws IOException {
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNumber, pageSize);
		List<Back> list = backService.findAll(back);

		LocalDateTime ldt = LocalDateTime.now();
		String fileName = "退货单信息表_" + ldt.format(DateTimeFormatter.ofPattern("_yyyy-MM-dd-HH-mm-ss")) + ".xlsx";
		String finalName = URLEncoder.encode(fileName, "utf-8");
		resp.setHeader("Content-disposition", "attchment;filename=" + finalName);

		EasyExcel.write(resp.getOutputStream(), Back.class).excludeColumnFiledNames(Arrays.asList("handler"))
				.sheet("退货单信息表").doWrite(list);
	}
}
