package com.jyc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyc.model.FourAddress;
import com.jyc.model.OneAddress;
import com.jyc.model.ThreeAddress;
import com.jyc.model.TwoAddress;
import com.jyc.model.User;
import com.jyc.model.UserAddress;
import com.jyc.service.FourAddressService;
import com.jyc.service.OneAddressService;
import com.jyc.service.ThreeAddressService;
import com.jyc.service.TwoAddressService;
import com.jyc.service.UserAddressService;

/**
 * 地址控制器
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/forward/address")
public class AddressController {
	/**
	 * 一级地址
	 */
	@Autowired
	private OneAddressService service;
	/**
	 * 二级地址
	 */
	@Autowired
	private TwoAddressService two;
	/**
	 * 三级地址
	 */
	@Autowired
	private ThreeAddressService three;
	/**
	 * 四级地址
	 */
	@Autowired
	private FourAddressService four;
	/**
	 * 用户地址
	 */
	@Autowired
	private UserAddressService usService;

	/**
	 * ajax实现查找所有,前台主页选择
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> all() {
		Map<String, Object> map = new HashMap<>();
		List<OneAddress> list1 = service.findAll(null);
		map.put("one", list1);
		List<TwoAddress> list2 = two.findAll(null);
		map.put("two", list2);
		List<ThreeAddress> list3 = three.findAll(null);
		map.put("three", list3);
		return map;
	}

	/**
	 * ajax实现下拉框选择给下一级赋地址
	 * 
	 * @param oneId     一级地址ID，查找parentId得到二级地址
	 * @param twoId     二级地址ID，查找parentId得到三级地址
	 * @param threeId   三级地址ID，查找parentId得到四级地址
	 * @param addressId 选定地址后的ajax按钮得到地址Id
	 * @param session   通过session得到有关登录的用户的相关，并且添加一个用户的地址
	 * @return 返回JSON
	 */
	@RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> find(@RequestParam(name = "oneId", required = false) Integer oneId,
			@RequestParam(name = "twoId", required = false) Integer twoId,
			@RequestParam(name = "threeId", required = false) Integer threeId,
			@RequestParam(name = "addressId", required = false) Integer addressId, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		List<OneAddress> list1 = service.findAll(null);
		map.put("one", list1);
		if (oneId != null) {
			System.out.println(oneId);
			List<TwoAddress> list2 = two.findByParentId(oneId);
			map.put("two", list2);
		}
		if (twoId != null) {
			List<ThreeAddress> list3 = three.findByParentId(twoId);
			map.put("three", list3);
		}

		if (threeId != null) {
			List<FourAddress> list4 = four.findByParentId(threeId);
			map.put("four", list4);
		}
		if (addressId != null) {
			UserAddress usAddress = new UserAddress();
			usAddress.setUserId(((User) (session.getAttribute("####user_login####"))).getId());
			usAddress.setAddressId(addressId);
			usAddress.setDefaults("0");
			int row = usService.insert(usAddress);
			if (row > 0) {
				map.put("detail", "添加成功");
			} else {
				map.put("detail", "添加失败");
			}
		}
		return map;
	}

	/**
	 * 修改地址，ajax实现
	 * 
	 * @param oneId     一级地址ID，查找parentId得到二级地址
	 * @param twoId     二级地址ID，查找parentId得到三级地址
	 * @param threeId   三级地址ID，查找parentId得到四级地址
	 * @param addressId 选定地址后的ajax按钮得到地址Id
	 * @param id        修改下标回显
	 * @param session   用户相关，修改后也修改session中数据
	 * @return 返回一个JSON
	 */
	@RequestMapping(value = "/edit", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> edit(@RequestParam(name = "oneId", required = false) Integer oneId,
			@RequestParam(name = "twoId", required = false) Integer twoId,
			@RequestParam(name = "threeId", required = false) Integer threeId,
			@RequestParam(name = "addressId", required = false) Integer addressId,
			@RequestParam(name = "id", required = false) Integer id, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		List<OneAddress> list1 = service.findAll(null);
		map.put("one", list1);
		if (oneId != null) {
			System.out.println(oneId);
			List<TwoAddress> list2 = two.findByParentId(oneId);
			map.put("two", list2);
		}
		if (twoId != null) {
			List<ThreeAddress> list3 = three.findByParentId(twoId);
			map.put("three", list3);
		}

		if (threeId != null) {
			List<FourAddress> list4 = four.findByParentId(threeId);
			map.put("four", list4);
		}
		if (addressId != null && id != null) {
			UserAddress usAddress = new UserAddress();
			usAddress.setId(id);
			usAddress.setAddressId(addressId);
			int row = usService.update(usAddress);
			if (row > 0) {
				map.put("detail", "修改成功");
			} else {
				map.put("detail", "修改失败");
			}
		}
		if (id != null) {
			UserAddress useradd = usService.findById(id);
			FourAddress fourOne = useradd.getAddress();
			map.put("address", fourOne);
			map.put("id", id);
		}
		return map;
	}

	/**
	 * 删除一个地址
	 * 
	 * @param id      要删除的id
	 * @param session 会话session
	 * @param map     要返回的数据封装到map中
	 * @return 转发到我的请求，或者返回一个重定向到我的的请求
	 */
	@RequestMapping("/delete")
	public String delete(Integer id, HttpSession session, Map<String, Object> map) {
		int row = usService.deleteById(id);
		if (row > 0) {
			map.put("detail", "删除成功");
			return "redirect:/forward/user/my";
		} else {
			map.put("detail", "删除失败");
			return "forward:/forward/user/my";
		}
	}

}
