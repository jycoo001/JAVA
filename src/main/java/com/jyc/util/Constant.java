package com.jyc.util;

/**
 * 常量类
 * 
 * @author 12430
 *
 */
public class Constant {

	public static final String PICTURE_URL = "d:/File/";

	/**
	 * sql语句，employee实体的数量
	 */
	public static final String SQL_COUNT_EMPLOYEE = "select count(id) count from employee e ";
	/**
	 * sql语句，department实体的数量
	 */
	public static final String SQL_COUNT_DEPARTMENT = "select count(id) count from department e ";

	/**
	 * department连接到employee的查询，一对多
	 */
	public static final String SQL_SELECT_DEPARTMENT_EMPLOYEE = "select d.id departmentId,d.`name` departmentName,count,e.id id,e.`name` `name`,sex,birthday,salary from "
			+ "department d left join employee e on d.id=e.department_id ";

}
