package com.jyc.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jyc.dao.BaseDAO;
import com.jyc.model.ViewDate;

public class BaseServiceImpl {

	private BaseDAO getDAO() {
		try {
			Field field = this.getClass().getDeclaredField("dao");
			field.setAccessible(true);
			return (BaseDAO) field.get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> List<T> findAll(T t) {
		return getDAO().findAll(t);
	}

	public <T> T findById(Integer id) {
		return getDAO().findById(id);
	}

	public <T> List<T> findByParentId(Integer id) {
		return getDAO().findByParentId(id);
	}

	public <T> int insert(T t) {
		return getDAO().insert(t);
	}

	public <T> int update(T t) {
		return getDAO().update(t);
	}

	public int deleteById(Integer id) {
		return getDAO().deleteById(id);
	}

	public int deleteByIds(Integer[] ids) {
		return getDAO().deleteByIds(ids);
	}

	public int findCount() {
		return getDAO().findCount();
	}

	public List<ViewDate> findFromTo(String from, Date to) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toStr = sdf.format(to);
		return getDAO().findFromTo(new String[] { from, toStr });
	}

}
