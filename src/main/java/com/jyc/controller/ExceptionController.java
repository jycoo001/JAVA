package com.jyc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常转发数页面
 * 
 * @author 12430
 *
 */
@ControllerAdvice
public class ExceptionController {

	/**
	 * 转发到error（404）界面
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	public String exception(Exception e) {
		System.out.println(exceptionToString(e));
		return "error";
	}

	/**
	 * 异常处理
	 * 
	 * @param ex
	 * @return
	 */
	public String exceptionToString(Throwable ex) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ex.printStackTrace(new PrintStream(os));

		try {
			os.flush();
			os.close();
			return os.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
