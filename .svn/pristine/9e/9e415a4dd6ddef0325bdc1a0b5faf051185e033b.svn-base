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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.setting.service.AccessControlService;

@Controller
@RequestMapping("/passwordcontroller")

public class ChangePasswordController {
	
	final static Logger logger = Logger.getLogger(ChangePasswordController.class);
	
	@Autowired
	AccessControlService accessControlService;
	
	@RequestMapping(value={"/changePasswordOnValid"}, method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Map<String, Object> changePasswordOnValid(@Valid @RequestBody ChangePasswordModel password, BindingResult bresult,HttpSession session,HttpServletRequest request) {
		String method="changePasswordOnValid";
		logger.info("method starts--- :"+method);
		Map<String, Object> resp = new HashMap<String, Object>();
		String userId=(String) session.getAttribute("userId");
		Map<String, Object> groupIdLocationId=(Map<String, Object>)session.getAttribute("groupIdLocationId");
		String groupName= (String) groupIdLocationId.get("group_name");
		logger.info(password.toString());

		String oldPassword = password.getOldPassword();
		
		String passwordindb = accessControlService.getOldPasswordFromDB(userId,groupName);
		
		String oldPasswordinDB = passwordindb;
		
		
		if(bresult.hasErrors()){
			logger.info("Inside::Server Side Validation @@ hasErrors"); 
			logger.info("error occured" +bresult.toString());
			resp.put("status", false);
			resp.put("msg", "Invalid Request.");

		}else{
			if(oldPasswordinDB.equalsIgnoreCase(oldPassword)){
				if(!accessControlService.checkLastTimeExceed(userId,groupName)) {
					resp.put("status", false);
					resp.put("msg", "Password change is temporarily locked. Please try again after 15 minutes.");
					return resp;
				}
				if(accessControlService.checkPasswordIsPresentOrNot(userId,groupName,password.getConfPassword())) {
					resp.put("status", false);
					resp.put("msg", "The new password must be different from your recent passwords.");
					return resp;	
				}
				boolean passwordStatus = accessControlService.changePasswordOnValid(password,userId,groupName);
				AuditlogRequest loginRequest = new AuditlogRequest();
				loginRequest.setUserId(userId);
				String rmtAddr = request.getRemoteAddr();
				loginRequest.setIpAddress(rmtAddr);				
				if(passwordStatus){
					loginRequest.setRecordStatus("success");
					loginRequest.setAction("Change Password");
					Boolean auditTrail = accessControlService.saveAuditLog(loginRequest, request);
					Boolean passwordHistory=accessControlService.saveOnPasswordHistory(password.getPassword(),userId,groupName,"Change Password");
					if (auditTrail && passwordHistory) {
						logger.info("Audit Trail Implemented");
					} else {
						logger.info("Audit Trail Not Implemented");
					}
					resp.put("status", true);
					resp.put("msg", "Password Changed Successfully");
				}else{
					loginRequest.setRecordStatus("failure");
					loginRequest.setAction("Attempt to change password");
					Boolean auditTrail = accessControlService.saveAuditLog(loginRequest, request);
					if (auditTrail) {
						logger.info("Audit Trail Implemented");
					} else {
						logger.info("Audit Trail Not Implemented");
					}
					resp.put("status", false);
					resp.put("msg", "Failed To Change Password");
				}
			}else{
				resp.put("status", false);
				resp.put("msg", "Please Enter Your Valid Old Password");
			}
		}
		logger.info("method Ends--- :"+method);
		return resp;
	}
}
