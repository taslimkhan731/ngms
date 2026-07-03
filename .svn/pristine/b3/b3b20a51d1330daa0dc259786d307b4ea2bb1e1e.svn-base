package com.stl.ngms.changepassword.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stl.ngms.setting.service.AccessControlService;

@Controller
@RequestMapping("/passwordcontroller")

public class ChangePasswordController {
	
	final static Logger logger = Logger.getLogger(ChangePasswordController.class);
	
	@Autowired
	AccessControlService accessControlService;
	
	@RequestMapping(value={"/changePasswordOnValid"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePasswordOnValid(@Valid @RequestBody ChangePasswordModel password, BindingResult bresult,HttpSession session) {
		System.out.println("Inside:: @@ Controller"); 
		String method="changePasswordOnValid";
		System.out.println("method starts--- :"+method);
		Map<String, Object> resp = new HashMap<String, Object>();
		String userId=(String) session.getAttribute("userId");
		System.out.println(password.toString());

		String oldPassword = password.getOldPassword();
		
		String passwordindb = accessControlService.getOldPasswordFromDB(userId);
		
		String oldPasswordinDB = passwordindb;
		
		
		if(bresult.hasErrors()){
			System.out.println("Inside::Server Side Validation @@ hasErrors"); 
			System.out.println("error occured" +bresult.toString());
			resp.put("status", false);
			resp.put("msg", "Invalid Request.");

		}else{
			if(oldPasswordinDB.equalsIgnoreCase(oldPassword)){
				boolean passwordStatus = accessControlService.changePasswordOnValid(password,userId);
				if(passwordStatus){
					resp.put("status", true);
					resp.put("msg", "Password Changed Successfully");
				}else{
					resp.put("status", false);
					resp.put("msg", "Failed To Change Password");
				}
			}else{
				resp.put("status", false);
				resp.put("msg", "Please Enter Your Valid Old Password");
			}
		}
		System.out.println("method Ends--- :"+method);
		return resp;
	}
}
