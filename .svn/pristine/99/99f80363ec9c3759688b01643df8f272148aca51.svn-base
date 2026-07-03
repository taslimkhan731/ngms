package com.stl.ngms.aop;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Aspect
@Controller
public class CrossInterceptor {
	

	@Before(value = "execution(!String com.stl.ngms.*.*controller.*.*(..))")
	public void scrossCheck(JoinPoint joinPoint) {
		Arrays.stream(joinPoint.getArgs()).forEach(a -> {
			System.err.println("param is "+a.toString());
			if (a.toString().contains("<") || a.toString().contains(">") || a.toString().contains("/>")
					|| a.toString().toUpperCase().contains("script>".toUpperCase())
					|| a.toString().toUpperCase().contains("style>".toUpperCase())) {
				System.err.println("DEEPAKKKKKKKK"+a.toString());
				throw new RuntimeException("Invalid Request");
			}
		});
	}

	@Before(value = "execution(!String com.stl.ngms.*.*controller.*.*(..))")
	public void dataTableSqlEnjectionCheck(JoinPoint joinPoint) {
		Arrays.stream(joinPoint.getArgs()).forEach(param -> {
			try {
				HttpServletRequest request = (HttpServletRequest) param;
				Collections.list(request.getParameterNames()).forEach(a -> {
				System.err.println("Key = " + a + " :: Value = " + request.getParameter(a));
					if ((request.getParameter(a).toUpperCase().contains("SELECT"))
							|| (request.getParameter(a).toUpperCase().contains("AND"))
							|| (request.getParameter(a).toUpperCase().contains("WHERE"))
							|| (request.getParameter(a).toUpperCase().contains("HAVING"))
							|| (request.getParameter(a).toUpperCase().contains("UNION"))
							|| (request.getParameter(a).toUpperCase().contains("INSERT"))
							|| (request.getParameter(a).toUpperCase().contains("UPDATE"))
							|| (request.getParameter(a).toUpperCase().contains("ALTER"))
							|| (request.getParameter(a).toUpperCase().contains("CREATE"))
							|| (request.getParameter(a).toUpperCase().contains("<"))
							|| (request.getParameter(a).toUpperCase().contains("/>"))
							|| (request.getParameter(a).toUpperCase().contains("DROP "))
							|| (request.getParameter(a).toUpperCase().contains("SAVEPOINT "))
							|| (request.getParameter(a).toUpperCase().contains("CALL "))
							|| (request.getParameter(a).toUpperCase().contains("ROLLBACK "))
							|| (request.getParameter(a).toUpperCase().contains("KILL "))
							|| (request.getParameter(a).toUpperCase().contains("TRUNCATE "))
							|| (request.getParameter(a).toUpperCase().contains("LOCK "))
							|| (request.getParameter(a).toUpperCase().contains("UNLOCK "))
							|| (request.getParameter(a).toUpperCase().contains("RELEASE "))
							|| (request.getParameter(a).toUpperCase().contains("DESCRIBE "))
							|| (request.getParameter(a).toUpperCase().contains(";"))) {
						System.out.println("FFFFFFFFFFFFFFFFFFF"+a);
						throw new RuntimeException("Invalid Request");
						
					}
				});
			} catch (Exception e) {
				System.err.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJ"+e.getMessage());
			}
		});
		
	}
}
