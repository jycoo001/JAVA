package com.jyc.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 工具类
 * 
 * @author 12430
 *
 */
public class Base {

	public boolean equals(String[] names) {
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		boolean b = true;
		for (Field field : fields) {
			boolean b1 = true;
			for (String name : names) {
				if (field.getName().equals(name)) {
					b1 = false;
					break;
				}
			}
			if (!b1) {
				continue;
			}
			String meStr = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			try {
				Method method = clazz.getDeclaredMethod(meStr);
				if (method.invoke(this) == null) {
					b = false;
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public boolean hashCode(String[] name) {
		return equals(name);
	}

}