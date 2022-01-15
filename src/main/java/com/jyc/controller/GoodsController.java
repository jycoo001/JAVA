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
import com.jyc.model.Goods;
import com.jyc.model.GoodsDetail;
import com.jyc.model.GoodsPicture;
import com.jyc.model.GoodsType;
import com.jyc.service.GoodsDetailService;
import com.jyc.service.GoodsPictureService;
import com.jyc.service.GoodsService;
import com.jyc.service.GoodsTypeService;
import com.jyc.util.Constant;

/**
 * 商品管理
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/goods")
public class GoodsController {
	/**
	 * 商品
	 */
	@Autowired
	private GoodsService service;
	/**
	 * 商品图片
	 */
	@Autowired
	private GoodsPictureService goodsPictureService;
	/**
	 * 分类
	 */
	@Autowired
	private GoodsTypeService one;
	/**
	 * 商品详情
	 */
	@Autowired
	private GoodsDetailService goodsDetailService;

	@RequestMapping(value = { "", "/" })
	public String goods(Goods goods, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Map<String, Object> map, HttpSession session) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Goods> list = service.findAll(goods);
		PageInfo<Goods> page = new PageInfo<>(list);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("goods", goods);
		map.put("list", list);
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return "background/goods/goods-list";
	}

	@GetMapping("/goods-add")
	public String addGoodsGet(Map<String, Object> map) {
		return "background/goods/goods-add";
	}

	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/goods-add")
	public String addGoods(Goods goods, Map<String, Object> map) {
		if (goods.equals("")) {
			int row1 = service.insert(goods);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/goods/goods-list";
			} else {
				map.put("goods", goods);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "输入有空");
			map.put("goods", goods);
		}
		return "background/goods/goods-add";
	}

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/goods-detail")
	public String upda(Goods goods, Map<String, Object> map, HttpSession session) {
		if (goods.equals("") && goods.getId() != null) {
			int row = service.update(goods);
			if (row > 0) {
				session.setAttribute("detail", "修改成功");
				return "redirect:/background/goods";
			} else {
				map.put("goods", goods);
				map.put("detail", "修改失败");
				return "background/goods/goods-detail";
			}
		} else {
			if (goods.getId() != null) {
				Goods goods1 = service.findById(goods.getId());
				map.put("goods", goods1);
			}
		}
		return "background/goods/goods-detail";
	}

	@RequestMapping(value = "/goods-delete", produces = "application/json;charset=utf-8")
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

	@RequestMapping(value = "/goods-delete-many", produces = "application/json;charset=utf-8")
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

	@GetMapping("/type")
	public String type(Map<String, Object> map, Integer id) {
		GoodsType type = new GoodsType();
		type.setParentId(0);
		List<GoodsType> list = one.findAll(type);
		map.put("type", list);
		map.put("id", id);
		return "background/goods/goods-type";
	}

	@PostMapping("/type")
	public String typeSub(Integer id, Integer typeId, Map<String, Object> map) {
		Goods goods = service.findById(id);
		goods.setTypeId(typeId);
		int row = service.update(goods);
		if (row > 0) {
			map.put("detail", "修改成功");
			map.put("row", row);
			return "redirect:/background/goods";
		} else {
			map.put("id", id);
			return "forward:/background/goods/type";
		}
	}

	// 商品图片
	@GetMapping("/picture")
	public String picGet(Integer id, HttpSession session, Map<String, Object> map) {

		if (id != null) {
			PageHelper.startPage(1, 5);
			List<GoodsPicture> list = goodsPictureService.findByGoodsId(id);
			PageInfo<GoodsPicture> page = new PageInfo<>(list);
			map.put("list", list);
			map.put("goodsId", id);
			session.setAttribute("goodsId", id);
			map.put("page", page);
			map.put("pageNumber", 1);
			return "background/goods/picture/picture-list";
		} else {
			PageHelper.startPage(1, 5);
			List<GoodsPicture> list = goodsPictureService.findByGoodsId((Integer) session.getAttribute("goodsId"));
			PageInfo<GoodsPicture> page = new PageInfo<>(list);
			map.put("list", list);
			map.put("goodsId", session.getAttribute("goodsId"));
			map.put("page", page);
			map.put("pageNumber", 1);
			return "background/goods/picture/picture-list";
		}

	}

	@PostMapping("/picture")
	public String pic(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, GoodsPicture goodsPicture, HttpSession session,
			Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GoodsPicture> list = goodsPictureService.findAll(goodsPicture);
		PageInfo<GoodsPicture> page = new PageInfo<>(list);
		map.put("list", list);
		map.put("goodsPicture", goodsPicture);
		map.put("page", page);
		map.put("goodsId", session.getAttribute("goodsId"));
		map.put("pageNumber", pageNumber);
		return "background/goods/picture/picture-list";
	}

	@GetMapping("/picture-add")
	public String addPicAdd(Map<String, Object> map) {
		return "background/goods/picture/picture-add";
	}

	@PostMapping("/picture-add")
	public String addPic(@RequestParam(required = true, name = "picturex") MultipartFile multipartFile,
			HttpSession session, Map<String, Object> map) {
		if (multipartFile != null) {
			String uuId = UUID.randomUUID().toString();
			int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
			String name = "picture/" + uuId + "." + multipartFile.getOriginalFilename().substring(idx + 1);
			String url = Constant.PICTURE_URL + name;
			File file = new File(url);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			GoodsPicture goodsPicture = new GoodsPicture();
			goodsPicture.setPicture(name);
			goodsPicture.setGoodsId((Integer) session.getAttribute("goodsId"));

			int row1 = goodsPictureService.insert(goodsPicture);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/goods/picture/picture-list";
			} else {
				map.put("goodsPicture", goodsPicture);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "图片为空");
		}
		return "background/goods/picture/picture-add";
	}

	@RequestMapping("/picture-detail")
	public String upda(@RequestParam(required = false, name = "picturex") MultipartFile multipartFile, Integer id,
			Map<String, Object> map, HttpSession session) {
		if (id != null) {
			if (multipartFile != null) {
				String uuId = UUID.randomUUID().toString();
				int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
				String name = "picture/" + uuId + "." + multipartFile.getOriginalFilename().substring(idx + 1);
				String url = Constant.PICTURE_URL + name;
				File file = new File(url);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				GoodsPicture goodsPicture = new GoodsPicture();
				goodsPicture.setPicture(name);
				goodsPicture.setId(id);
				goodsPicture.setGoodsId((Integer) session.getAttribute("goodsId"));
				int row = goodsPictureService.update(goodsPicture);
				if (row > 0) {
					session.setAttribute("detail", "修改成功");
					return "redirect:/background/goods/picture";
				} else {
					map.put("detail", "修改失败");
					map.put("id", id);
					return "background/goods/picture/picture-detail";
				}
			} else {
				map.put("id", id);
			}
			return "background/goods/picture/picture-detail";
		} else {
			return "redirect:/background/goods/picture";
		}

	}

	@RequestMapping(value = "/picture-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePic(@RequestParam(required = true, name = "ids") Integer ids) {
		Map<String, Object> map = new HashMap<>();
		int row = goodsPictureService.deleteById(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@RequestMapping(value = "/picture-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePic(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
		Map<String, Object> map = new HashMap<>();
		int row = goodsPictureService.deleteByIds(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@RequestMapping("/detail")
	public String toDetail(Integer id, HttpSession session, Map<String, Object> map) {
		session.setAttribute("goodsId", id);
		GoodsDetail goodsDetail = goodsDetailService.findByGoodsId(id);
		map.put("goodsDetail", goodsDetail);
		return "background/goods/goods-details/goods-detail";
	}

	@RequestMapping("/edit-detail")
	public String detail(GoodsDetail goodsDetail, HttpSession session, Map<String, Object> map) {
		if (goodsDetail.equals("")) {
			Integer goodsId = (Integer) session.getAttribute("goodsId");
			GoodsDetail goDetail = goodsDetailService.findByGoodsId(goodsId);
			if (goDetail != null) {
				goodsDetail.setId(goDetail.getId());
				int row = goodsDetailService.update(goodsDetail);
				if (row > 0) {
					map.put("detail", "修改成功");
					return "redirect:/background/goods";
				} else {
					map.put("detail", "修改失败");
				}
			} else {
				goodsDetail.setGoodsId(goodsId);
				int row = goodsDetailService.insert(goodsDetail);
				if (row > 0) {
					map.put("detail", "添加成功");
					return "redirect:/background/goods";
				} else {
					map.put("detail", "添加失败");
				}
			}
			map.put("goodsDetail", goodsDetail);
		}
		return "background/goods/goods-details/goods-detail";
	}

}
