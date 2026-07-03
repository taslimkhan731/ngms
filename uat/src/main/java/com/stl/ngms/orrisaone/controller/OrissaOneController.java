package com.stl.ngms.orrisaone.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.stl.ngms.common.model.LoginUserDetailsModel;
import com.stl.ngms.common.model.LoginUserDetailsVO;
import com.stl.ngms.guest.model.TpiReqEncDataReqDTO;
import com.stl.ngms.orrisaone.model.QuarksConstants;
import com.stl.ngms.orrisaone.model.TpiValidateRequestResDTO;
import com.stl.ngms.orrisaone.model.UserModel;
import com.stl.ngms.orrisaone.model.VerifyRequestDTO;
import com.stl.ngms.orrisaone.service.OrisaoneService;
import com.stl.ngms.orrisaone.util.ApiConnector;
import com.stl.ngms.orrisaone.util.TpiReqResJsonUtils;
import com.stl.ngms.setting.model.Resource;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.BCrypt;
import com.stl.ngms.utils.Sha512;


@Controller
@RequestMapping("/orissa-one")
public class OrissaOneController {
	private final static SecureRandom srandom = new SecureRandom();
    public static String accessKey = "AD29HACDGFZBBBLL";
    public static String checkSUMKey = "QEVZVB6MMO78LEVJAVMKHMKPPLNTOH";
    String baseUrl="http://52.172.129.49/odisha-one";
    @Autowired
    private ApiConnector apiConnector;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
	private OrisaoneService orisaService;
    @Autowired
	private AccessControlService accessControlService;
	@Autowired
	private Sha512 sha512;
	final static Logger logger = Logger.getLogger(OrissaOneController.class);	
	@RequestMapping(value = {"/guestDashboardview"}, method = RequestMethod.POST)
    public ModelAndView showRegister(@ModelAttribute TpiReqEncDataReqDTO encData1 ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("start calling....guestDashboardview.....");
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		String encData = encData1.getEncData();
	    resp.put("encData", encData);
		logger.info(encData);
		ModelAndView mav=new ModelAndView();
		 
	        JsonNode node = TpiReqResJsonUtils.decryptAndReturnJsonNode(encData1.getEncData(),accessKey);
	        logger.info("Node  : "+node.toString());
	        request.setAttribute("node", node.toString());
	        String checkSUm = TpiReqResJsonUtils.calculateCheckSum(node,checkSUMKey);
	        boolean isValid = TpiReqResJsonUtils.validateCheckSum(node,checkSUm);
	        logger.info(checkSUm);
	        logger.info(isValid);
	       
	        if(isValid){
	        	 VerifyRequestDTO verifyRequestDTO = new VerifyRequestDTO();
	        	 String cancelUrl = node.get("CANCELURL").asText();
	             String successUrl = node.get("SUCCESSURL").asText();
	             String paymentUrl = node.get("PAYMENTURL").asText();
	             String departementId = node.get("DEPARTEMENTID").asText();
	             logger.info(departementId);
	             String SERVICEID = node.get("SERVICEID").asText();
	             String REQUESTID = node.get("REQUESTID").asText();
	             String OOUSERTOKEN = node.get("OOUSERTOKEN").asText();
	             String OOUSERCODE = node.get("OOUSERCODE").asText();
	             String REQTIMESTAMP = node.get("REQTIMESTAMP").asText();
	             String reqTimeStamp=TpiReqResJsonUtils.getReqTimeStamp();
	             verifyRequestDTO.setDepartmentId(departementId);
	             verifyRequestDTO.setServiceId(SERVICEID);
	             verifyRequestDTO.setSubServiceId("");
	             verifyRequestDTO.setRequestId(REQUESTID);
	             verifyRequestDTO.setRequestTimeStamp(reqTimeStamp);
	             verifyRequestDTO.setOoUserCode(OOUSERCODE);
	             
	             logger.info(verifyRequestDTO);
	             String encData2 = TpiReqResJsonUtils.generateEncData(verifyRequestDTO,accessKey,checkSUMKey);
	             logger.info(encData2);
	             TpiValidateRequestResDTO tpiValidateRequestResDTO = new TpiValidateRequestResDTO();
	             tpiValidateRequestResDTO.setEncData(encData2);
	             logger.info(tpiValidateRequestResDTO);
	            
	             String hostToHostUrl = baseUrl+"/api/v1/tpi/verify-request?departementId="+departementId+"&serviceId="+SERVICEID;
	             logger.info("Host to host API Url : {}"+hostToHostUrl);
	             String userid= node.get("OOUSERMOBILENO").asText();
            	 mav.setViewName("redirectLogin");
            	 mav.addObject("j_username", userid);
            	 mav.addObject("jsondata", encData);
            	    		

	             try {
	                 JsonNode jsonNode = apiConnector.hitServer(tpiValidateRequestResDTO,hostToHostUrl);
	                 if (jsonNode == null) {
	                     logger.info("informTransactionStatus(): Currently "+SERVICEID+" Service is unavailable. Please try after some time");
	                 } else {
	                     logger.info("informTransactionStatus() Response {}"+ jsonNode.asText());
	                     String resEncData = jsonNode.get("encData").asText();     
	                     logger.info("adadadad"+resEncData);
	                     if (true){
	                    // if (resEncData != null) {
	                         JsonNode resJsoNnode = TpiReqResJsonUtils.decryptAndReturnJsonNode(resEncData,accessKey);
	                         if(true){
	                         //if(resJsoNnode.has(QuarksConstants.REQSTATUSCODE) && resJsoNnode.has(QuarksConstants.ORIGINEDFROMOO)){
	                             //mav.addObject("validRequest", "Valid incoming request");
	                             String reqCode = resJsoNnode.get(QuarksConstants.REQSTATUSCODE).asText();
	                             String reqOrginated =  resJsoNnode.get(QuarksConstants.ORIGINEDFROMOO).asText();
	                             if(true){
	                             //if(reqCode.equalsIgnoreCase("200")&& reqOrginated.equalsIgnoreCase("YES")){
	                            	
	                            	// JSONObject resObj = orisaService.validateUidAndEmail(node.get("OOUSERMOBILENO").asText());
	                       			//logger.info("existance status of userid and emailId of a user:"+resObj.toString());
	                            
//	                       			if(domainUser != null){
//	                       				authWithAuthManager(request,domainUser.getUserId(),domainUser.getPassword());
//	                       				SecurityContextHolder.getContext().getAuthentication();
//	                       				logger.info(SecurityContextHolder.getContext().getAuthentication().getName());			
//	                       				resp.put("message","You are already registered");
//	                       				resp.put("status",false);
//	                       			}else{
//	                       				UserModel user = new UserModel();
//                  						user.setActive(true);
//                  						user.setUserId(node.get("OOUSERMOBILENO").asText());
//                  						user.setFirstName(node.get("OOUSERFULLNAME").asText());
//                  						user.setAdmin(false);
//                  						user.setFirstLogin(true);
//                  						user.setPassword(node.get("OOUSERCODE").asText());
//                  						user.setEmail(node.get("OOUSEREMAIL").asText());
//                  						user.setCreatedBy(node.get("OOUSERMOBILENO").asText());
//                  						user.setCreatedDate(new Date());
//                  						user.setContactNo(node.get("OOUSERMOBILENO").asText());
//                  						user.setOtpLogin(false);
//                  						user.setUserType("GUEST");
//                  						boolean status = orisaService.saveGuestDetails(user);
//                                        mav.addObject("successUrl", successUrl);
//	                  						
//	                 					
//	                    
//	                           }                 
	                       }
	                     }
	                 }
	                
	                 }   
	             
	             }catch (Exception ex){
	                 logger.error("Unable to hit odisha-one server. {} "+ex.getMessage());
	             }
	             
	        
	        }
		     //return  new ModelAndView("redirect:/guest/guestDashboard.do");
	        //return new ModelAndView("redirect:/buildSession.do");
	        return mav;
    		
    }
	
	public void authWithAuthManager(HttpServletRequest request, String username, String password) throws Exception {
		String saltKey = BCrypt.generateSalt(srandom);
		String saltedPassword = sha512.SHA512(password+"#"+saltKey);
		logger.info(request);
		JSONObject json = new JSONObject();
		json.put("saltKey", saltKey);
		json.put("reqType", "register");
		json.put("j_username", username);
	    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(json.toString(), saltedPassword);
	    request.getSession();
	    authToken.setDetails(new WebAuthenticationDetails(request)); 
	    Authentication authentication = authenticationManager.authenticate(authToken); 
	    SecurityContextHolder.getContext().setAuthentication(authentication);	    
	    logger.info("========>>>>>> "+authentication.getName());
	    System.out.print("========>>>>>> "+request.getUserPrincipal().getName());  
	}
	
	@RequestMapping(value = { "/backToOdishaOne" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewApplication(HttpSession session, HttpServletRequest req)throws Exception {
		Map<String, Object> resp = new HashMap<String, Object>();
		JsonNode jsonData	= (JsonNode) session.getAttribute("node");
        String departementId = jsonData.get("DEPARTEMENTID").asText();
        String SERVICEID = jsonData.get("SERVICEID").asText();
        String REQUESTID = jsonData.get("REQUESTID").asText();
        String OOUSERTOKEN = jsonData.get("OOUSERTOKEN").asText();
        String OOUSERCODE = jsonData.get("OOUSERCODE").asText();
        String cancelUrl = jsonData.get("CANCELURL").asText();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("DEPARTEMENTID",departementId);
		jsonObject.put("SERVICEID",SERVICEID);
		jsonObject.put("SUBSERVICEID","");
		jsonObject.put("REQUESTID",REQUESTID);
		jsonObject.put("REQTIMESTAMP",TpiReqResJsonUtils.getReqTimeStamp());
		jsonObject.put("OOUSERTOKEN",OOUSERTOKEN);
		jsonObject.put("OOUSERCODE",OOUSERCODE);
		jsonObject.put("CHECKSUM","");
		JsonNode node1 = TpiReqResJsonUtils.convertJsonObjectToNode(jsonObject);
		logger.info("#######################&&&&&&& TPI APPLICATION Back to odishaone: {} "+node1.toString());
		String encData1 = TpiReqResJsonUtils.convertJsonNodeToEncData(node1,OrissaOneController.accessKey,OrissaOneController.checkSUMKey);
		logger.info("No Payment Success encData: "+encData1);
		resp.put("cancelEncData", encData1);
		resp.put("cancelUrl", cancelUrl);
		session.removeAttribute("requestid");
		session.removeAttribute("node");
		return resp;
		
	}

//	 @RequestMapping(value = "/download-document", method = RequestMethod.POST, produces={"application/json"})
//	    public @ResponseBody TpiValidateRequestResDTO documentDownload(@RequestBody @Valid TpiReqEncDataReqDTO reqEncDataReqDTO, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
////	        logger.info("Start Calling documentDownload in Test Application : {} , access Key : {} "+reqEncDataReqDTO.getEncData(),accessKey);
//	        JsonNode node = TpiReqResJsonUtils.decryptAndReturnJsonNode(reqEncDataReqDTO.getEncData(),accessKey);
//	        logger.info("Node  : "+node.toString());
//	        String checkSUm = TpiReqResJsonUtils.calculateCheckSum(node,checkSUMKey);
//	        boolean isValid = TpiReqResJsonUtils.validateCheckSum(node,checkSUm);
//	        if(!isValid){
//	        	// throw an exception
//	        }
//	       
//	        String applicationId = node.get("APPLICATIONID").textValue();
//	        String requstId = node.get("REQUESTID").textValue();
//	       
//	        
//	        
//	        
//	        
//	       
//	        
//	        JSONObject jsonObject = new JSONObject();
//	        jsonObject.put("APPLICATIONID",applicationId);
//			jsonObject.put("REQUESTID",requstId);
//			jsonObject.put("REQTIMESTAMP",TpiReqResJsonUtils.getReqTimeStamp());
//			jsonObject.put("FILEDATA","");
//			jsonObject.put("FILETYPE","pdf");
//			jsonObject.put("OOUSERCODE","");
//			jsonObject.put("CHECKSUM","");
//			JsonNode node1 = TpiReqResJsonUtils.convertJsonObjectToNode(jsonObject);
//			logger.info("#######################&&&&&&& TPI APPLICATION Back to odishaone: {} "+node1.toString());
//			String encData1 = TpiReqResJsonUtils.convertJsonNodeToEncData(node1,OrissaOneController.accessKey,OrissaOneController.checkSUMKey);
//			logger.info("No Payment Success encData: "+encData1);
//	        TpiValidateRequestResDTO tpiValidateRequestResDTO = new TpiValidateRequestResDTO();
//	        tpiValidateRequestResDTO.setEncData(encData1);
////	        log.info("Start Calling  : {}",tpiValidateRequestResDTO.toString());
//	        return tpiValidateRequestResDTO;
//	    }
//	
	 @RequestMapping(value = {"/cancelguestDashboardview"}, method = RequestMethod.POST)
	 public ModelAndView cancelRegister(@ModelAttribute TpiReqEncDataReqDTO encData1 ,HttpServletRequest request, HttpServletResponse response) throws Exception{
			logger.info("start calling....guestDashboardview.....");
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("success", true);
			resp.put("msg", "Successfully received");
			String encData = encData1.getEncData();
		    resp.put("encData", encData);
			logger.info(encData);
			ModelAndView mav=new ModelAndView();
			 
		        JsonNode node = TpiReqResJsonUtils.decryptAndReturnJsonNode(encData1.getEncData(),accessKey);
		        logger.info("Node  : "+node.toString());
		        request.setAttribute("node", node.toString());
		        String checkSUm = TpiReqResJsonUtils.calculateCheckSum(node,checkSUMKey);
		        boolean isValid = TpiReqResJsonUtils.validateCheckSum(node,checkSUm);
		        logger.info(checkSUm);
		        logger.info(isValid);
		       
		        if(isValid){
		        	 VerifyRequestDTO verifyRequestDTO = new VerifyRequestDTO();
		        	 String cancelUrl = node.get("CANCELURL").asText();
		             String successUrl = node.get("SUCCESSURL").asText();
		             String paymentUrl = node.get("PAYMENTURL").asText();
		             String departementId = node.get("DEPARTEMENTID").asText();
		             logger.info(departementId);
		             String SERVICEID = node.get("SERVICEID").asText();
		             String REQUESTID = node.get("REQUESTID").asText();
		             String OOUSERTOKEN = node.get("OOUSERTOKEN").asText();
		             String OOUSERCODE = node.get("OOUSERCODE").asText();
		             String REQTIMESTAMP = node.get("REQTIMESTAMP").asText();
		             String reqTimeStamp=TpiReqResJsonUtils.getReqTimeStamp();
		             verifyRequestDTO.setDepartmentId(departementId);
		             verifyRequestDTO.setServiceId(SERVICEID);
		             verifyRequestDTO.setSubServiceId("");
		             verifyRequestDTO.setRequestId(REQUESTID);
		             verifyRequestDTO.setRequestTimeStamp(reqTimeStamp);
		             verifyRequestDTO.setOoUserCode(OOUSERCODE);
		             
		             logger.info(verifyRequestDTO);
		             String encData2 = TpiReqResJsonUtils.generateEncData(verifyRequestDTO,accessKey,checkSUMKey);
		             logger.info(encData2);
		             TpiValidateRequestResDTO tpiValidateRequestResDTO = new TpiValidateRequestResDTO();
		             tpiValidateRequestResDTO.setEncData(encData2);
		             logger.info(tpiValidateRequestResDTO);
		            
		             String hostToHostUrl = baseUrl+"/api/v1/tpi/verify-request?departementId="+departementId+"&serviceId="+SERVICEID;
		             logger.info("Host to host API Url : {}"+hostToHostUrl);
		             String userid= node.get("OOUSERMOBILENO").asText();
	            	 mav.setViewName("redirectLogin");
	            	 mav.addObject("j_username", userid);
	            	 mav.addObject("jsondata", encData);
	            	    		

		             try {
		                 JsonNode jsonNode = apiConnector.hitServer(tpiValidateRequestResDTO,hostToHostUrl);
		                 if (jsonNode == null) {
		                     logger.info("informTransactionStatus(): Currently "+SERVICEID+" Service is unavailable. Please try after some time");
		                 } else {
		                     logger.info("informTransactionStatus() Response {}"+ jsonNode.asText());
		                     String resEncData = jsonNode.get("encData").asText();
		                     
		                     logger.info("adadadad"+resEncData);
		                     if (true){
		                    // if (resEncData != null) {
		                         JsonNode resJsoNnode = TpiReqResJsonUtils.decryptAndReturnJsonNode(resEncData,accessKey);
		                         if(true){
		                         //if(resJsoNnode.has(QuarksConstants.REQSTATUSCODE) && resJsoNnode.has(QuarksConstants.ORIGINEDFROMOO)){
		                             //mav.addObject("validRequest", "Valid incoming request");
		                             String reqCode = resJsoNnode.get(QuarksConstants.REQSTATUSCODE).asText();
		                             String reqOrginated =  resJsoNnode.get(QuarksConstants.ORIGINEDFROMOO).asText();
		                             if(true){
		                             //if(reqCode.equalsIgnoreCase("200")&& reqOrginated.equalsIgnoreCase("YES")){
		                            	
		                            	// JSONObject resObj = orisaService.validateUidAndEmail(node.get("OOUSERMOBILENO").asText());
		                       			//logger.info("existance status of userid and emailId of a user:"+resObj.toString());
		                            
//		                       			if(domainUser != null){
//		                       				authWithAuthManager(request,domainUser.getUserId(),domainUser.getPassword());
//		                       				SecurityContextHolder.getContext().getAuthentication();
//		                       				logger.info(SecurityContextHolder.getContext().getAuthentication().getName());			
//		                       				resp.put("message","You are already registered");
//		                       				resp.put("status",false);
//		                       			}else{
//		                       				UserModel user = new UserModel();
//	                  						user.setActive(true);
//	                  						user.setUserId(node.get("OOUSERMOBILENO").asText());
//	                  						user.setFirstName(node.get("OOUSERFULLNAME").asText());
//	                  						user.setAdmin(false);
//	                  						user.setFirstLogin(true);
//	                  						user.setPassword(node.get("OOUSERCODE").asText());
//	                  						user.setEmail(node.get("OOUSEREMAIL").asText());
//	                  						user.setCreatedBy(node.get("OOUSERMOBILENO").asText());
//	                  						user.setCreatedDate(new Date());
//	                  						user.setContactNo(node.get("OOUSERMOBILENO").asText());
//	                  						user.setOtpLogin(false);
//	                  						user.setUserType("GUEST");
//	                  						boolean status = orisaService.saveGuestDetails(user);
//	                                        mav.addObject("successUrl", successUrl);
//		                  						
//		                 					
//		                    
//		                           }                 
		                       }
		                     }else {
	                             mav.addObject("validRequest", "Invalid incoming request");
	                         }
		                 }
		                
		                 }   
		             
		             }catch (Exception ex){
		                 logger.error("Unable to hit odisha-one server. {} "+ex.getMessage());
		             }
		             
		        
		        }
			     //return  new ModelAndView("redirect:/guest/guestDashboard.do");
		        //return new ModelAndView("redirect:/buildSession.do");
		        return mav;
	    		
	    }
	
	
	
	
}

