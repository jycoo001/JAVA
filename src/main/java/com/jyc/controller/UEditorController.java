package com.jyc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyc.util.Constant;

/**
 * UEditor富文本编辑器配置
 * 
 * @author 12430
 *
 */
@Controller
public class UEditorController {
	private static final ObjectMapper om = new ObjectMapper();

	@RequestMapping(value = "/ueditor", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String handerUEdirotRequest(String action, MultipartFile upfile, HttpServletRequest req) {

		if ("config".equals(action)) {// 获取服务器端配置
			try (InputStream is = req.getServletContext().getResourceAsStream("/static/ueditor/config.json");) {
				String json = StreamUtils.copyToString(is, Charset.forName("utf-8"));
				return json;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("uploadimage".equals(action)) {
			String targetDir = Constant.ueditorUploadImageDirector;// 上传路径
			String name = upfile.getOriginalFilename();
			try {
				upfile.transferTo(new File(targetDir, name));

				Map<String, String> result = new HashMap<>();
				result.put("state", "SUCCESS");
				result.put("url", "/ueditor/upload/images/" + name);
				return om.writeValueAsString(result);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

}
