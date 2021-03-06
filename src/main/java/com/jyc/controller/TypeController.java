package com.jyc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.GoodsType;
import com.jyc.model.TypeSearch;
import com.jyc.service.GoodsTypeService;

/**
 * 类别控制器
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/type")
public class TypeController {
	@Autowired
	private GoodsTypeService service;

	@RequestMapping("/list")
	public String type(Map<String, Object> map, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, GoodsType one) {

		PageHelper.startPage(pageNumber, pageSize);
		List<GoodsType> list = service.findAll(one);
		PageInfo<GoodsType> page = new PageInfo<>(list);

		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("list", list);
		map.put("type", one);
		return "background/type/type-list";
	}

	@GetMapping("/add")
	public String add(Map<String, Object> map) {
		return "background/type/type-add";
	}

	@PostMapping("/add")
	public String addSub(GoodsType goodsType, Map<String, Object> map) {
		if (goodsType.getName() != null && goodsType.getName().trim().length() > 0 && goodsType.getHidden() != null
				&& goodsType.getHidden().trim().length() > 0) {
			goodsType.setParentId(0);
			List<GoodsType> goodsTypes = service.findByParentId(0);
			goodsType.setTypeId(goodsTypes.get(goodsTypes.size() - 1).getTypeId() + 1);
			int row = service.insert(goodsType);
			if (row > 0) {
				map.put("detail", "添加成功");
				return "background/type/type-list";
			}
		}
		map.put("detail", "添加失败");
		map.put("goodsType", goodsType);
		return "background/type/type-add";
	}

	@GetMapping("/update")
	public String update(Integer id, Map<String, Object> map) {
		map.put("id", id);
		GoodsType goodsType = new GoodsType();
		goodsType.setParentId(0);
		List<GoodsType> list = service.findAll(goodsType);
		map.put("type", list);
		return "background/type/type-update";
	}

	@PostMapping("/update")
	public String updateSub(TypeSearch typeSearch) {
		GoodsType type = service.findById(typeSearch.getId());
		if (typeSearch.getOneId() != null) {
			if (typeSearch.getTwoId() != null) {
				if (typeSearch.getTypeId() != null) {
					List<GoodsType> type1 = service.findByParentId(typeSearch.getTypeId());
					if (type1 != null && type1.size() > 0) {
						type.setTypeId(type1.get(type1.size() - 1).getTypeId() + 1);
					} else {
						type.setTypeId(Integer.valueOf(typeSearch.getTypeId() + "01"));
					}
					type.setParentId(typeSearch.getTypeId());
				} else {
					List<GoodsType> type1 = service.findByParentId(typeSearch.getTwoId());
					if (type1 != null && type1.size() > 0) {
						type.setTypeId(type1.get(type1.size() - 1).getTypeId() + 1);
					} else {
						type.setTypeId(Integer.valueOf(typeSearch.getTwoId() + "01"));
					}
					type.setParentId(typeSearch.getTwoId());
				}
			} else {
				List<GoodsType> type1 = service.findByParentId(typeSearch.getOneId());
				if (type1 != null && type1.size() > 0) {
					type.setTypeId(type1.get(type1.size() - 1).getTypeId() + 1);
				} else {
					type.setTypeId(Integer.valueOf(typeSearch.getOneId() + "01"));
				}
				type.setParentId(typeSearch.getOneId());
			}

		} else {
			List<GoodsType> list = service.findByParentId(0);
			if (list != null && list.size() > 0) {
				type.setTypeId(list.get(list.size() - 1).getTypeId() + 1);
			} else {
				type.setTypeId(Integer.valueOf(1));
			}
			type.setParentId(0);
		}
		int row = service.update(type);
		if (row > 0) {
			return "background/type/type-list";
		} else {
			return "forward:/background/type/update";
		}
	}

	@RequestMapping("/type-edit")
	public String typeEdit(GoodsType onetype, Map<String, Object> map) {
		if (onetype.getName() != null && onetype.getName().trim().length() > 0 && onetype.getHidden() != null) {
			int row = service.update(onetype);
			if (row > 0) {
				GoodsType type = service.findById(onetype.getId());
				return "redirect:/background/type/list?parentId=" + type.getParentId();
			} else {
				map.put("type", onetype);
			}
		} else {
			GoodsType one = service.findById(onetype.getId());
			map.put("type", one);
		}
		return "background/type/type-detail";
	}

	/**
	 * 删除一个分类，ajax
	 * 
	 * @param ids 通过ajax得到的一个ids
	 * @return 返回一个JSON数据
	 */
	@RequestMapping(value = "/type-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids") Integer ids) {
		Map<String, Object> map = new HashMap<>();
		int row = 0;
		row = service.deleteById(ids);
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
	@RequestMapping(value = "/type-delete-many", produces = "application/json;charset=utf-8")
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
