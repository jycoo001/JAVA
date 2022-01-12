package com.jyc.util;

import java.util.TimeZone;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 常量配置类
 * 
 * @author 12430
 *
 */
public class Constant {

	// 一个重量级对象，提前创建好，提高性能
	public static final ObjectMapper JACKSON_OBJECT_MAPPER = new ObjectMapper();
	// 头像临时存储目录，在应用上下文目录下。也可考虑放在系统临时目录下
	public static String avatarTmpDirectory = "upload/tmp";
	// 头像正式存储目录，在应用上下文目录下
	public static String avatarDirectory = "upload/avatar";
	// ueditor上传图片路径
	public static String ueditorUploadImageDirector = "d:/图片/ssmShopping/ueditor/upload/images/";
	// UEditor的服务器端配置
	public static JSONObject UEDITOR_SERVER_CONFIG;

	static {
		// 设置为jvm默认时区，否则jackson在序列化和反序列化时，会使日期相差8个小时
		// 在中国地区，时区为GMT+8，所以TimeZone.getDefault()等同于TimeZone.getTimeZone("GMT+8")
		JACKSON_OBJECT_MAPPER.setTimeZone(TimeZone.getDefault());
	}

	public static final String PICTURE_URL = "d:/图片/ssmShopping/";

}
