package com.jyc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Back;
import com.jyc.service.BackService;

@Controller
@RequestMapping("/background/back")
public class BackController {
	/**
	 * 退货
	 */
	@Autowired
	private BackService service;

	/**
	 * 退货单显示操作
	 * 
	 * @param map        page、order、第几页
	 * @param pageNumber 第几页
	 * @param pageSize   一页几条
	 * @param o          查询实体
	 * @return 订单列表
	 */
	@RequestMapping(value = { "/", "" })
	public String orderManage(Map<String, Object> map, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Back b) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Back> back = service.findAll(b);
		PageInfo<Back> page = new PageInfo<>(back);
		map.put("list", back);
		map.put("back", b);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		return "background/back/back-list";
	}

	/**
	 * 修改退货订单
	 * 
	 * @param id     订单号
	 * @param userId 用户号
	 * @param status 状态
	 * @param map    提示
	 * @return 成功list，失败回显
	 */
	@RequestMapping("/back-detail")
	public String detail(Integer id, String orderId, String backId, Map<String, Object> map) {
		if (orderId != null) {
			Back back = service.findById(id);
			map.put("back", back);
			back.setId(id);
			back.setBackId(backId);
			back.setOrderId(orderId);
			int row = service.update(back);
			if (row > 0) {
				return "redirect:/background/back";
			} else {
				map.put("detail", "修改失败！");
			}
		} else {
			Back back = service.findById(id);
			map.put("back", back);
		}

		return "background/back/back-detail";
	}

	/**
	 * 删除退货单
	 * 
	 * @param ids 要删除的id
	 * @return 返回JSON数据
	 */
	@RequestMapping(value = "/back-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAdmin(@RequestParam(required = true, name = "ids") Integer ids) {
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

	/**
	 * 删除多个，实际不删，ajax
	 * 
	 * @param ids 传来的管理员数组
	 * @return 返回一个JSON
	 */
	@RequestMapping(value = "/back-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAdmin(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
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
