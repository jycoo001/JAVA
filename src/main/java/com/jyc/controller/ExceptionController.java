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

	@ExceptionHandler
	public String exception(Exception e) {
		System.out.println(exceptionToString(e));
		return "error";
	}

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
