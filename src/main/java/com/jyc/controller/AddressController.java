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

@Controller
@RequestMapping("/forward/address")
public class AddressController {

	@Autowired
	private OneAddressService service;
	@Autowired
	private TwoAddressService two;
	@Autowired
	private ThreeAddressService three;
	@Autowired
	private FourAddressService four;
	@Autowired
	private UserAddressService usService;

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
