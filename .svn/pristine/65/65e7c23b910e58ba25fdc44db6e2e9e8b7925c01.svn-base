package com.stl.ngms.aop;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public final ResponseEntity<Object> customeHandleException(Exception ex, WebRequest request) throws Exception {
		System.out.println("==========================");
		Map<String, Object> resp = new HashMap<>();
		resp.put("msg", "Invalid Request");
		resp.put("timestamp", new Date());
		resp.put("details", ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}
}
