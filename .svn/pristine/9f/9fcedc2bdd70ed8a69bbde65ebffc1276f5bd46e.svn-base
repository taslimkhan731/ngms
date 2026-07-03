package com.stl.ngms.aop;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
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
				System.err.println(""+a.toString());
				throw new RuntimeException("Invalid Request");
			}
		});
	}

//	@Before(value = "execution(!String com.stl.ngms.*.*controller.*.*(..))")
//	public void dataTableSqlEnjectionCheck(JoinPoint joinPoint) {
//		System.out.println("=============================================$$$$$$$$$++++++++++++++This is the EntryPoint of SQL Injection ");
//		Arrays.stream(joinPoint.getArgs()).forEach(param -> {
//			try {
//				HttpServletRequest request = (HttpServletRequest) param;
//				Collections.list(request.getParameterNames()).forEach(a -> {
//				System.err.println("Key = " + a + " :: Value = " + request.getParameter(a));
//					if ((request.getParameter(a).toUpperCase().contains("SELECT"))
//							|| (request.getParameter(a).toUpperCase().contains("AND"))
//							|| (request.getParameter(a).toUpperCase().contains("WHERE"))
//							|| (request.getParameter(a).toUpperCase().contains("HAVING"))
//							|| (request.getParameter(a).toUpperCase().contains("UNION"))
//							|| (request.getParameter(a).toUpperCase().contains("INSERT"))
//							|| (request.getParameter(a).toUpperCase().contains("UPDATE"))
//							|| (request.getParameter(a).toUpperCase().contains("ALTER"))
//							|| (request.getParameter(a).toUpperCase().contains("CREATE"))
//							|| (request.getParameter(a).toUpperCase().contains("<"))
//							|| (request.getParameter(a).toUpperCase().contains("/>"))
//							|| (request.getParameter(a).toUpperCase().contains("DROP "))
//							|| (request.getParameter(a).toUpperCase().contains("SAVEPOINT "))
//							|| (request.getParameter(a).toUpperCase().contains("CALL "))
//							|| (request.getParameter(a).toUpperCase().contains("ROLLBACK "))
//							|| (request.getParameter(a).toUpperCase().contains("KILL "))
//							|| (request.getParameter(a).toUpperCase().contains("TRUNCATE "))
//							|| (request.getParameter(a).toUpperCase().contains("LOCK "))
//							|| (request.getParameter(a).toUpperCase().contains("UNLOCK "))
//							|| (request.getParameter(a).toUpperCase().contains("RELEASE "))
//							|| (request.getParameter(a).toUpperCase().contains("DESCRIBE "))
//							|| (request.getParameter(a).toUpperCase().contains(";"))) {
//						System.out.println("=============================================$$$$$$$$$++++++++++++++This is the ExitPoint of SQL Injection ");
//						throw new RuntimeException("Invalid Request");
//						
//					}
//				});
//			} catch (Exception e) {
//				System.err.println(""+e.getMessage());
//			}
//		});
//		
//	}
//	@Before(value = "execution(!String com.stl.ngms.*.*controller.*.*(..))")
//	@Before(value = "execution(!String com.stl.ngms.*.*controller.*.*(..)) && execution(* com.stl.ngms.common.LoginController.*(..))")
//	@Before(value = "execution(* com.stl.ngms.common.LoginController.*(..))")
	@Before(value = "execution(* com.stl.ngms.common.LoginController.updateProfile(..)) || execution(!String com.stl.ngms.*.*controller.*.*(..))")
	public void dataTableSqlEnjectionCheck(JoinPoint joinPoint) {
	    System.out.println("=== EntryPoint of SQL Injection Check ===");
	    
	    // Loop through the method's arguments (params)
	    Arrays.stream(joinPoint.getArgs()).forEach(param -> {
	        // If the parameter is an HttpServletRequest, handle it as a request
	        if (param instanceof HttpServletRequest) {
	            HttpServletRequest request = (HttpServletRequest) param;
	            
	            // Iterate through all request parameter names
	            Collections.list(request.getParameterNames()).forEach(paramName -> {
	                String paramValue = request.getParameter(paramName);
	                System.err.println("Key = " + paramName + " :: Value = " + paramValue);

	                // Check if the parameter contains any SQL injection keywords
	                if (isSuspicious(paramValue)) {
	                    System.out.println("=== ExitPoint of SQL Injection ===");
	                    throw new RuntimeException("Invalid Request: SQL Injection detected in parameter '" + paramName + "'");
	                }
	            });
	        }
	        // If the parameter is a LinkedHashMap, handle it as a map
	        else if (param instanceof LinkedHashMap) {
	            LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) param;
	            
	            // Iterate through the entries in the LinkedHashMap
	            map.forEach((key, value) -> {
	                System.err.println("Key = " + key + " :: Value = " + value);

	                if (value instanceof String && isSuspicious((String) value)) {
	                    System.out.println("=== ExitPoint of SQL Injection ===");
	                    throw new RuntimeException("Invalid Request: SQL Injection detected in map key '" + key + "'");
	                }
	            });
	        }else if (param instanceof String) {
	            // Handle String parameters (like @RequestParam)
	            String paramValue = (String) param;
	            System.out.println("String Parameter: " + paramValue);
	            if (isSuspicious(paramValue)) {
	                throw new RuntimeException("Invalid Request: SQL Injection detected in parameter value '" + paramValue + "'");
	            }
	        } 
	        // Log if the parameter is not a HttpServletRequest or LinkedHashMap
	        else {
	            System.err.println("Parameter is not an instance of HttpServletRequest or LinkedHashMap: " + param.getClass().getName());
	            if (isModelClass(param)) {
	                checkModelForSqlInjection(param);
	            }
	        }
	    });
	}

	// Helper method to detect suspicious SQL keywords in the request parameters
	private boolean isSuspicious(String value) {
	    if (value == null) return false;
	    
	    // Define SQL keywords to look for
	    String[] sqlKeywords = {
	        "SELECT", "WHERE", "HAVING", "UNION", "INSERT", "UPDATE", 
	        "ALTER", "<", "/>", "DROP", "SAVEPOINT", "CALL", 
	        "ROLLBACK", "KILL", "TRUNCATE", "UNLOCK", "RELEASE",
	        "DESCRIBE", ";"
	    };

	    // Check if the value contains any of the suspicious keywords
	    return Arrays.stream(sqlKeywords).anyMatch(keyword -> value.toUpperCase().contains(keyword));
	}
	private boolean isModelClass(Object param) {
	    return param != null && !(param instanceof String || param instanceof Number || param instanceof Boolean);
	}
	private void checkModelForSqlInjection(Object model) {
	    System.out.println("Checking model class fields for SQL injection...");

	    // Get all fields of the model class (including private ones)
	    Field[] fields = model.getClass().getDeclaredFields();

	    for (Field field : fields) {
	        field.setAccessible(true); // Make private fields accessible
	        try {
	            Object fieldValue = field.get(model);
	            if (fieldValue instanceof String) {
	                String value = (String) fieldValue;
	                System.err.println("Field = " + field.getName() + " :: Value = " + value);

	                // Check if the value of the field contains any SQL injection keywords
	                if (isSuspicious(value)) {
	                    System.out.println("=== ExitPoint of SQL Injection ===");
	                    throw new RuntimeException("Invalid Request: SQL Injection detected in field '" + field.getName() + "' of model class '" + model.getClass().getName() + "'");
	                }
	            }
	        } catch (IllegalAccessException e) {
	            // Handle potential issues accessing fields, though this is rare if the field is public or accessible
	            System.err.println("Error accessing field: " + field.getName());
	        }
	    }	
//	@AfterThrowing(
//	        pointcut = "execution(!String com.stl.ngms.*.*controller.*.*(..))",
//	        throwing = "ex"
//	    )
//	    public ResponseEntity<String> handleDatabaseException(JoinPoint joinPoint, Throwable ex) {
//	        if (ex instanceof DataIntegrityViolationException) {
//	            System.err.println("=== SQL Exception Detected ===: " + ex.getMessage());
//	            // Handle PSQL specific exception if needed
//	            if (ex.getMessage().contains("PSQLException")) {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//	                    .body("Database Error: PSQL Exception detected. Please check your request.");
//	            }
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body("Database Error: An unexpected error occurred.");
//	        } else if (ex instanceof RuntimeException) {
//	            System.err.println("=== Runtime Exception Detected ===: " + ex.getMessage());
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//	                .body(ex.getMessage());
//	        }
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	            .body("An unexpected error occurred.");
//	    }
	}
}
