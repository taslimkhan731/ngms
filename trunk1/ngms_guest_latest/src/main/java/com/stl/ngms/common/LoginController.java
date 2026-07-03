package com.stl.ngms.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.stl.ngms.changepassword.controller.ChangePasswordModel;
import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.common.model.ForgetPasswordModel;
import com.stl.ngms.common.model.ForgetPasswordModel1;
import com.stl.ngms.common.model.LoginUserDetailsModel;
import com.stl.ngms.common.model.LoginUserDetailsVO;
import com.stl.ngms.common.model.SimpleModel;
import com.stl.ngms.common.model.UserRegistrationModel;
import com.stl.ngms.common.service.OtpStore;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.Resource;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.BCrypt;
import com.stl.ngms.utils.Sha512;
import com.stl.ngms.utils.Utils;

@Controller
public class LoginController {

	static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private AccessControlService accessControlService;
	@Value("${message.captcha.invalid}")
	private String msgInvalidCaptcha;
	@Autowired
	private Sha512 sha512;
	public static final String CAPTCHA_KEY = "STL_CAPTCHA";
	@Autowired
	MailUtil mail;
	@Autowired
	private OtpStore otpStore;
	@Autowired
	SendSMS smsSend;
	@Autowired
	GuestService guestService;
	private final static SecureRandom srandom = new SecureRandom();

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "sessExp", required = false) String sessExp) {

		logger.info("inside login page request with url /login");

		// session.invalidate();// Required to generate a new session id on logout
		// or upon login page request so that any url rewriting not works

		// Added by Lusi on Date-26-Oct-2017
		session = request.getSession();
		String saltKey = BCrypt.generateSalt(srandom);
		session.setAttribute("saltKey", saltKey);
		System.out.println(saltKey);
		logger.info("Deepakkkkkkkkkkkkkkkkk");
		ModelAndView model = new ModelAndView("login");
		List<AllLocationModel> lstLocation = guestService.getAllLocation();
		model.addObject("lstLocation", lstLocation);
		model.addObject("saltKey", saltKey);
		if (error != null) {
			logger.info("error-->" + error);
			model.addObject("error", "Invalid username or password!");// Not required as we are giving spring customized
																		// message from Authenticator
		}
		if (logout != null) {
			if (session != null) {
				session.invalidate();
			}
			logger.info("logout-->" + logout);
			model.addObject("msg", "You've been logged out successfully.");
		}
		if (sessExp != null) {
			logger.info("sessExp-->" + sessExp);
			model.addObject("msg", "Your current session expired!.");
			System.out.println("session coming");
		}
		// model.setViewName("login");
		return model;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registerPage(HttpSession session) {
		System.out.println("redirecting to registration page");
		session.setAttribute("sessionRegister", "styleRegister");
		return "registration";
	}

	@RequestMapping(value = { "/feedback" }, method = RequestMethod.GET)
	public String feedbackPage(HttpSession session) {
		System.out.println("redirecting to registration page");
		session.setAttribute("sessionRegister", "styleRegister");
		// mail.sendMimeMail("deepak.sahoo@silicontechlab.com","OTP For Portal
		// Login","Dear Deepak <br> Your Otp for forget password is <h3><a
		// href='http://localhost:8088/ngms_guest/mobilefeedback.html?request_id=P7916&mobile_no=9337417561'
		// target=\"_self\">VERIFY</a></h3> <br><br> Thanks <br>e-Bhawan Team");

		return "feedback";
	}

	@RequestMapping(value = { "/mobilefeedback" }, method = RequestMethod.GET)
	public ModelAndView feedbackPageview(@RequestParam("requestid") String requestId,
			@RequestParam("mobileno") String mobileNo, HttpSession session) {
		System.out.println("redirecting to registration page" + requestId);
		List<BookingResponseModel> bookingResponseModel = guestService.getFeedback(requestId, mobileNo);
		System.out.println(bookingResponseModel.toString());
		ModelAndView mav = new ModelAndView();
		if (!bookingResponseModel.isEmpty()) {
			mav.addObject("bookingResponseModel", bookingResponseModel);
			mav.addObject("status", true);
			mav.setViewName("mobilefeedback");
		} else {
			mav.addObject("status", false);
			mav.addObject("msg", "You have already submitted the feedback");
			mav.setViewName("mobilefeedback");
		}
		return mav;
	}

	@RequestMapping(value = { "/createpassword" }, method = RequestMethod.GET)
	public String passwordPage(HttpSession session) {
		System.out.println("redirecting to registration page");
		session.setAttribute("sessionRegister", "styleRegister");
		return "password";
	}

	/**********************************************************************
	 * This methos is used to perform logout functionality
	 * 
	 * @param request,session
	 * @author Deepak
	 * @since 05-july-2021
	 ***********************************************************************/
	@RequestMapping(value = { "/perform_logout" }, method = RequestMethod.POST)
	public String performLogout(HttpServletRequest request, HttpSession session) {
		logger.info("Inside Method ---> perform_logout");
		/***********************************************************************
		 * This method is used to save user loggedin details Added By Deepak Added On
		 * 02-07-2021
		 ************************************************************************/
		AuditlogRequest logoutRequest = new AuditlogRequest();
		logoutRequest.setUserId(session.getAttribute("userId").toString());
		logoutRequest.setRecordStatus("success");
		logoutRequest.setAction("Logout Request");
		String rmtAddr = request.getRemoteAddr();
		logoutRequest.setIpAddress(rmtAddr);
		Boolean auditTrail = accessControlService.saveAuditLog(logoutRequest, request);
		if (auditTrail) {
			logger.info("Audit Trail Implemented");
		} else {
			logger.info("Audit Trail Not Implemented");
		}
		if (session != null) {
			session.invalidate();
		}
		logger.info("logout-->");
		return "redirect:/login.html?logout=true";
	}// end of performLogout

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user, HttpServletResponse response) {
		String methodName = "accesssDenied";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		System.out.println("status" + response.getStatus());
		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;

	}// end of accessDenied

	@RequestMapping(value = "/errors", method = RequestMethod.GET)
	public ModelAndView error(Principal user, HttpServletResponse response, HttpServletRequest request) {
		String methodName = "errors";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);

		ModelAndView model = new ModelAndView();
		model.addObject("errorCode", response.getStatus());
		System.out.println(request.getAttribute(response.getCharacterEncoding()));
		if (response.getStatus() == 404) {
			model.addObject("errorMessage", "URL/METHOD NOT FOUND");
		} else if (response.getStatus() == 401) {
			model.addObject("errorMessage", "Unauthorized");
		} else if (response.getStatus() == 400) {
			model.addObject("errorMessage", "Bad Request");
		} else if (response.getStatus() == 405) {
			model.addObject("errorMessage", "Method Not Allowed");
		} else {
			model.addObject("errorMessage", "Forbidden");
		}
		model.setViewName("error");

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;

	}// end of accessDenied

	/**
	 * This handler will build the session for the authenticated users - Here from
	 * the role ids first it will find out the allowed resources - Then
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/buildSession")
	public ModelAndView buildSession(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Principal principal) throws IOException {
		System.out.println("Entry Build  Session");
		String methodName = "buildSession";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);

		Authentication authentication = null;
		List<String> allowedRoles = new ArrayList<>();// This will store the allowed roles to the user/user_group(most
														// cases it will hold a single role)
		List<ResourceVO> availableResources = new ArrayList<ResourceVO>();// This will store all the available resources
																			// with the system
		List<ResourceVO> allowedResources = null;// This will store all the allowed resources to the user
		HashMap<String, List<String>> hmParamVal = null;// This will store all the additional parameter values if
														// available

		/*
		 * Retrieving user information and then the allowed roles from Spring security
		 * context
		 */
		String userId = principal.getName();
		logger.info("principal username/Id : " + userId);
		// Save user id/name in session scope
		session.setAttribute("userId", userId);
		logger.info("currentSessionId : " + session.getId());
		session.setAttribute("currentSessionId", session.getId());
		// get first name and last name from adminr.user and set in session
		List<LoginUserDetailsVO> getFirstNameLastName = new ArrayList<LoginUserDetailsVO>();
		List<LoginUserDetailsModel> firstNameLastName = accessControlService.getFirstNameLastName(userId);
		List<UserModel> document_upload = accessControlService.getRegisterDetails(userId);
		System.out.println("document_upload" + document_upload.get(0).toString());
		LoginUserDetailsVO fnln = null;
		if (document_upload.get(0).isUpload_doc() == false) {
			return new ModelAndView("redirect:/reregistration.html");
		}
		if (firstNameLastName != null && firstNameLastName.size() > 0) {
			for (LoginUserDetailsModel res : firstNameLastName) {
				fnln = new LoginUserDetailsVO();
				fnln.setFirst_name(res.getFirst_name());
				fnln.setLast_name(res.getLast_name());
				getFirstNameLastName.add(fnln);
			}
		} // end if
		logger.info("First Name Last Name--->" + getFirstNameLastName);
		String first_name = getFirstNameLastName.get(0).getFirst_name();
		String last_name = getFirstNameLastName.get(0).getLast_name();
		session.setAttribute("first_name", first_name);
		session.setAttribute("last_name", last_name);

		session.setAttribute("showNameWelcomeSession", "true");
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				allowedRoles.add(ga.getAuthority());
			}
		}
		logger.info("allowedRoles-->" + allowedRoles);
		session.setAttribute("roleId", allowedRoles);
		// Used for Role Base Resource Controller by Vamsi Krish
		Map<String, Object> groupIdLocationId = accessControlService.getUserIdLocationId(userId);
		System.out.println("groupIdLocationId--->" + groupIdLocationId.toString());
		session.setAttribute("groupIdLocationId", groupIdLocationId);

		/*
		 * Retrieve all allowed unique resources to this user and putting in session
		 */
		allowedResources = accessControlService.getUniqueResourcesForRoles(allowedRoles);
		System.out.println("Total AllowedResources--->" + allowedResources);
		logger.info("Total AllowedResources--->" + allowedResources);
		session.setAttribute("allowedResources", allowedResources);

		/*
		 * Retrieve all available resources in the system and putting in session
		 */
		List<Resource> alAvailResources = accessControlService.getResourceList();
		ResourceVO resVo = null;
		if (alAvailResources != null && alAvailResources.size() > 0) {
			for (Resource res : alAvailResources) {
				resVo = new ResourceVO();
				resVo.setActive(res.isActive());
				resVo.setDescription(res.getDescription());
				resVo.setResourceId(res.getResourceId());
				resVo.setResourceName(res.getResourceName());
				resVo.setResourceType(res.getResourceType());
				availableResources.add(resVo);
			}
		} // end if
		logger.info("Total AvailableResources--->" + availableResources);
		session.setAttribute("availableResources", availableResources);

		AuditlogRequest loginRequest = new AuditlogRequest();
		loginRequest.setUserId(userId);
		loginRequest.setRecordStatus("success");
		loginRequest.setAction("Login Attempt");
		String rmtAddr = request.getRemoteAddr();
		loginRequest.setIpAddress(rmtAddr);
		Boolean auditTrail = accessControlService.saveAuditLog(loginRequest, request);
		if (auditTrail) {
			logger.info("Audit Trail Implemented");
		} else {
			logger.info("Audit Trail Not Implemented");
		}
		/*
		 * Finding out the initial redirect URL for the user
		 */
		String defaultResource = "login.html?error=true";
		if (allowedResources != null && allowedResources.size() > 0) {
			defaultResource = allowedResources.get(0).getResourceName() + ".do";
		} // end if

		System.out.println(defaultResource);
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);

		return new ModelAndView("redirect:/" + defaultResource);
	}// end of buildSession

	@RequestMapping(value = { "/reregistration" }, method = RequestMethod.GET)
	public String reregistration(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  registration");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		String appId = req.getParameter("id");
		System.out.println("Datattttttt " + appId);
		return "reregistration";
	}

	/**********************************************
	 * Generating captcha code
	 ************************************************/
	@RequestMapping(value = { "/generateCaptcha" }, method = RequestMethod.POST)
	public @ResponseBody void getCaptchaCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String methodName = "getCaptchaCode";
		res.setContentType("text/html");
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		PrintWriter out = res.getWriter();
		String captchaCode = Utils.generateCaptchaTextMethod1();
		logger.info("captcha" + captchaCode);
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		out.print(captchaCode);
	}

	/******************************************************************************************************
	 * Generate Otp
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws MessagingException
	 **********************************************************************************************/
	@RequestMapping(value = { "/forgetPassword" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> fogetPassword(@Valid @RequestBody ForgetPasswordModel forgetPassword,
			BindingResult bresult, HttpSession session) throws JSONException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "forgetPassword";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		if (bresult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + bresult.toString());
			resp.put("status", false);
			resp.put("msg", "Invalid Request.");

		} else {
			logger.info("Requested userId :" + forgetPassword.getUserId());
			JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getUserId());
			logger.info("existance status of userid and emailId of a user:" + resObj.toString());
			if (resObj.get("status") == "success") {
				String myEmailId = resObj.get("email").toString();
				String otp = otpStore.storeOtp(forgetPassword.getUserId());
				System.out.println("OTP:" + otp);
				try {
					if (myEmailId != null) {
						if (!myEmailId.isEmpty()) {
							mail.sendMimeMail(myEmailId, "OTP For Portal Login",
									"Dear " + forgetPassword.getUserId() + "<br> Your Otp for forget password is " + otp
											+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
																					// Email
						}
					}
				} catch (Exception e) {
					logger.error("error occured in sms or email send.." + e.getMessage());
				}
				try {
					String templateID = "1107160707717945638";
					smsSend.sendSMS(forgetPassword.getUserId(), "OTP For Forget Password " + otp + ", Odisha Home Dept",
							templateID);
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}
				// save the randomcaptca in the data base

				if (otp != null) {
					resp.put("message", "Successfully OTP sent to your Mobile or Email Id");
					resp.put("status", true);
				} else {
					resp.put("message", "Invalid Credentials");
					resp.put("status", false);
				}
			} else {
				resp.put("message", "You have Not a Registered User ");
				resp.put("status", false);
			}
		}

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}

	/**********************************************
	 * Method that will take the otp and userid as input and check in the table that
	 * wheather this otp is matching for this userid or not
	 * 
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 ***************************************************/
	@RequestMapping(value = { "/validateOtp" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> validateOtp(
			@Valid @RequestBody ChangePasswordWithoutAuthModel forgetPassword, BindingResult bresult,
			HttpSession session) throws JSONException, IOException {
		String methodName = "validateOtp";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		boolean status = accessControlService.validateOtp(forgetPassword.getUserId(), forgetPassword.getOtp());// method
																												// thaqt
																												// will
																												// check
																												// wheather
																												// any
																												// otp
																												// is
																												// present
																												// or
																												// not
																												// if
																												// present
																												// update
																												// or
																												// else
																												// insert
		if (status == true) {
			resp.put("message", "Successfully OTP sent to your Mobile or Email Id");
			resp.put("status", true);
		} else {
			resp.put("message", "Invalid Credentials");
			resp.put("status", false);
		}
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}// end of validateOtp

	/********************************************
	 * Method is taking the input UserId and it will create a one time password then
	 * it will check in otp table wheather the userid has any otp previously if it
	 * found then it will update a new otp and send to the mail if any otp is not
	 * found to this userid then it will insert a new row for this user in otp table
	 * and send to the mail
	 * 
	 * @return
	 * @throws MessagingException
	 *******************************************/
	@RequestMapping(value = { "/resendOtp" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> resendOtp(@Valid @RequestBody ForgetPasswordModel forgetPassword,
			BindingResult bresult, HttpSession session) throws JSONException, IOException, MessagingException {
		String methodName = "resendOtp";
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);

		JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getUserId());
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			String myEmailId = resObj.get("email").toString();
			String otp = otpStore.storeOtp(forgetPassword.getUserId());
			System.out.println("OTP:" + otp);
			try {
				if (myEmailId != null) {
					if (!myEmailId.isEmpty()) {
						mail.sendMimeMail(myEmailId, "ReSend OTP For Portal Login",
								"Dear " + forgetPassword.getUserId() + "<br> Your Re send Otp for forget password is "
										+ otp + "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the
																						// corresponding Email
					}
				}
			} catch (Exception e) {
				logger.error("error occured in sms or email send..");
			}
			try {
				String templateID = "1107161251118912084";

				smsSend.sendSMS(forgetPassword.getUserId(), "OTP For Portal Login " + otp + ", Odisha Home Dept",
						templateID);
			} catch (Exception e) {
				logger.error("Error occured in sms " + e.getMessage());
			}

			if (otp != null) {
				resp.put("message", "Successfully OTP sent to your Mobile or Email Id");
				resp.put("status", true);
			} else {
				resp.put("message", "Invalid Credentials");
				resp.put("status", false);
			}
		} else {
			resp.put("message", "Invalid Credentials");
			resp.put("status", false);
		}
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}// End of resendOtp

	@RequestMapping(value = "/retryOtp", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> retryOtp(HttpServletRequest req, HttpServletResponse res,
			@RequestBody SimpleModel model) {

		// String id = String.valueOf(reqMap.get("id"));
		logger.info("Resend OTP request userid: " + model.getUid());
		Map<String, Object> resp = new HashMap<String, Object>();

		String otp = otpStore.storeOtp(model.getUid());
		System.out.println("Retry otp :" + otp);

		if (null == otp) {
			resp.put("status", false);
			resp.put("message", "Invalid Request.");
			return resp;
		}
		// send otp in mail and sms
		// Send in mail
		try {
			if (!StringUtils.isEmpty(model.getEmailId())) {
				if (!model.getEmailId().isEmpty()) {
					mail.sendMimeMail(model.getEmailId(), "OTP For e-Bhawan Login", "Dear " + model.getUid()
							+ "<br> Your Otp for OBMS registration is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
																														// is
																														// send
																														// to
																														// the
																														// corresponding
																														// Email
				}
			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send..");
		}
		if (!StringUtils.isEmpty(model.getUid())) {
			try {
				String templateID = "1107160707698779534";
				smsSend.sendSMS(model.getUid(), "OTP For OBMS Registration " + otp + ", Odisha Home Dept", templateID);
			} catch (Exception e) {
				logger.error("Error occured in sms " + e.getMessage());
			}
		}

		// send response
		resp.put("status", true);
		resp.put("message", "OTP Resent successfully.");
		return resp;
	}

	/***************************************************************************
	 * Method will set the new password of a corresponding UserId Input is password
	 * and userid
	 * 
	 * @return
	 ***************************************************************************/

	@RequestMapping(value = "/resetPasswordRequest", method = RequestMethod.POST)
	public ModelAndView savePassword(@ModelAttribute("forgetRequest") ChangePasswordWithoutAuthModel forgetPassword,
			RedirectAttributes redirectAttributes, BindingResult bresult, HttpSession session, HttpServletRequest req,
			HttpServletResponse res) throws JSONException, IOException, MessagingException {
		String methodName = "savePassword";
		ModelAndView model = new ModelAndView();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		if (null == session || (null == session.getAttribute(CAPTCHA_KEY))) {
			throw new AuthenticationServiceException(msgInvalidCaptcha);// Invalid username or password
		}
		System.out.println("Inside captcha ====================");
		String reqCaptchaVal = req.getParameter("captcha");
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		session.removeAttribute(CAPTCHA_KEY);

		if (!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)) {
			model.addObject("errCaptcha", true);
			model.setViewName("forgotPassword");
			return model;
		}
		JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getMobile());
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "falure") {
			model.addObject("userNotFound", true);
			model.setViewName("forgotPassword");
			return model;
		}
		String myEmailId = resObj.get("email").toString();
		String otp = otpStore.storeOtp(forgetPassword.getMobile());
		System.out.println("OTP:" + otp);
		try {
			if (myEmailId != null) {
				if (!myEmailId.isEmpty()) {
					mail.sendMimeMail(myEmailId, "OTP For Portal Login", "Dear " + forgetPassword.getMobile()
							+ "<br> Your Otp for forget password is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
																													// is
																													// send
																													// to
																													// the
																													// corresponding
																													// Email
				}
			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send.." + e.getMessage());
		}
		try {
			String templateID = "1107160707717945638";
			smsSend.sendSMS(forgetPassword.getMobile(), "OTP For Forget Password " + otp + ", Odisha Home Dept",
					templateID);
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		// save the randomcaptca in the data base

		if (otp != null) {
			model.addObject("userId", forgetPassword.getMobile());
			model.setViewName("resetKt");
		} else {
			model.addObject("userId", forgetPassword.getMobile());
			model.setViewName("forgotPassword");
		}
		/*
		 * UserRegistrationModel user = new UserRegistrationModel(); if
		 * (!StringUtils.isEmpty(regAttr.getUserId())) { user =
		 * commonService.getUserDetails(regAttr.getUserId()); }
		 */

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;

	}

	// End of savePassword

	@RequestMapping(value = "/guestUpload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> guestUpload(@ModelAttribute UserRegistrationModel forgetPassword,
			BindingResult bresult, Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "guestUpload";
		logger.info("Entry---> " + this.getClass() + " method :  " + forgetPassword.toString());
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		String userId = principal.getName();
		forgetPassword.setMobile(userId);
		String path = accessControlService.uploadDocument(forgetPassword);
		if (path == null ? true : path.equals("failure")) {
			resp.put("message", "Failed to upload file or File Format is not correct.");
			resp.put("status", false);
		} else {
			
			System.out.println("file uploaded successfully");
			logger.info("Requested userId :" + userId);
			UserModel user = new UserModel();
			user.setUserId(userId);
			user.setUpload_doc(true);
			if (path != null) {
				user.setDocType(forgetPassword.getDocType());
				user.setDocPath(
						path.contains("\\") ? path.substring(path.indexOf("\\")) : path.substring(path.indexOf("/")));
			}
			boolean status = accessControlService.updateUserDetail(user);
			System.out.println("ststus--------------"+status);
			if (status == true) {
				resp.put("message", "File uploaded successfully");
				resp.put("status", true);
			} else {
				resp.put("message", "File uploaded not successfully");
				resp.put("status", false);
			}
		}
		return resp;
	}
	
	@RequestMapping(value = "/resetForgetPassword", method = RequestMethod.POST)
	public ModelAndView resetForgetPassword(
			@Valid @ModelAttribute("forgetRequest") ChangePasswordWithoutAuthModel pwdAttr,
			RedirectAttributes redirectAttributes, BindingResult validationResult, HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		logger.info("Reset password request..");
		System.out.println("details:" + pwdAttr);
		// this object will be used for returning the page
		ModelAndView model = new ModelAndView();
		if (validationResult.hasErrors()) {
			System.err.println(validationResult.getAllErrors());
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("errPwdFormat", true);
			model.setViewName("resetKt");
			return model;
		}
		System.out.println("valid inputs..");

		if (!otpStore.isOtpValid(pwdAttr.getUserId(), pwdAttr.getOtp())) {

			model.addObject("invalidOtp", true);
			model.addObject("userId", pwdAttr.getUserId());
			model.setViewName("resetKt");
			return model;
		}
		System.out.println("valid otp..");
		System.err.println("data: " + pwdAttr);

		if (!StringUtils.equals(pwdAttr.getPassword(), pwdAttr.getCpassword())) {

			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("errPwdFormat", true);
			model.setViewName("resetKt");
			return model;
		}
		boolean status = accessControlService.savePassword(pwdAttr.getUserId(), pwdAttr.getPassword());// method thaqt
																										// will check
																										// wheather any
																										// otp is
																										// present or
																										// not if
																										// present
																										// update or
																										// else insert
		if (status == true) {
			redirectAttributes.addFlashAttribute("pwdustatus", true);
			model = new ModelAndView("redirect:/login.html");
		} else {
			redirectAttributes.addFlashAttribute("pwdustatus", false);
			model = new ModelAndView("redirect:/login.html");
		}
		return model;
	}// end of resetForgetPassword

	/******************************************************************************************************
	 * Generate Otp Based Login
	 * 
	 * @author jayant
	 * @throws IOException
	 * @throws JSONException
	 * @throws MessagingException
	 **********************************************************************************************/
	@RequestMapping(value = { "/otpForLogin" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> otpForLogin(@ModelAttribute ForgetPasswordModel1 forgetPassword,
			BindingResult bresult, HttpSession session) throws JSONException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "otpForLogin";
		System.out.println(forgetPassword);
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		System.out.println(forgetPassword);
		if (bresult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + bresult.toString());
			resp.put("status", false);
			resp.put("msg", "Invalid Request.");
		} else {
			logger.info("Requested userId :" + forgetPassword.getJ_username());
			JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getJ_username());
			logger.info("existance status of userid and emailId of a user:" + resObj.toString());
			if (resObj.get("status") == "success") {
				resp.put("message", "You are registered user so you can login through mobile number and password");
				resp.put("status", false);
			} else {
				//String path = accessControlService.uploadDocument1(forgetPassword);
				/*System.out.println(path);
				if (path == null ? true : path.equals("failure")) {
					resp.put("message", "File Format is not correct");
					resp.put("status", false);
				} else {
*/
					JSONObject resValid = accessControlService.validateUid(forgetPassword.getJ_username());
					logger.info("existance status of userid and emailId of a user:" + resValid.toString());
					String captchaCode = Utils.generateCaptchaTextMethod1();// generating a captcha code/random number
					logger.info("otp :" + captchaCode);
					boolean status = false;
					if (resValid.get("status") == "failure") {
						UserModel user = new UserModel();
						user.setActive(true);
						user.setUserId(forgetPassword.getJ_username());
						user.setAdmin(false);
						user.setFirstLogin(true);
						user.setPassword(sha512.SHA512(captchaCode.trim()));
						user.setCreatedBy(forgetPassword.getJ_username());
						user.setCreatedDate(new Date());
						user.setContactNo(forgetPassword.getJ_username());
						user.setOtpLogin(true);
						user.setUpload_doc(false);
						user.setUserType("GUEST");
						/*if (path != null) {
							user.setDocType(forgetPassword.getDocType());
							user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\"))
									: path.substring(path.indexOf("/")));
						}
*/
						status = accessControlService.saveUserDetails(user);
						if (status) {
							UserGroupHasRoles userGroup = new UserGroupHasRoles();
							userGroup.setUserId(forgetPassword.getJ_username());
							userGroup.setGroup_id(2);
							accessControlService.saveUserGroup(userGroup);
						}

						try {

							String templateID = "1107161251118912084";
							smsSend.sendSMS(forgetPassword.getJ_username(),
									"OTP For Portal Login " + captchaCode + ", Odisha Home Dept", templateID);
						} catch (Exception e) {
							logger.error("error occured in sms or email send..");
						}
					} else {
						UserModel user = new UserModel();
						user.setUserId(forgetPassword.getJ_username());
						user.setPassword(sha512.SHA512(captchaCode.trim()));

						/*if (path != null) {
							user.setDocType(forgetPassword.getDocType());
							user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\"))
									: path.substring(path.indexOf("/")));
						}*/

						status = accessControlService.updateUserDetailsOtp(user);
						try {

							String templateID = "1107161251118912084";
							smsSend.sendSMS(forgetPassword.getJ_username(),
									"OTP For Portal Login " + captchaCode + ", Odisha Home Dept", templateID);
						} catch (Exception e) {
							logger.error("error occured in sms or email send..");
						}
					}
					// save the randomcaptca in the data base
					if (status == true) {
						resp.put("message", "Successfully OTP sent to your Mobile");
						resp.put("status", true);
					} else {
						resp.put("message", "Invalid Credentials");
						resp.put("status", false);
					}
				}

			
		}
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}

	@RequestMapping(value = { "/generateOtp" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> generateOtp(@Valid @RequestBody UserRegistrationModel forgetPassword,
			BindingResult bresult, HttpSession session) throws JSONException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "generateOtp";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		if (bresult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + bresult.toString());
			resp.put("status", false);
			resp.put("msg", "Invalid Request.");
		} else {
			logger.info("Requested userId :" + forgetPassword.getUserId());
			JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getUserId());
			logger.info("existance status of userid and emailId of a user:" + resObj.toString());
			if (resObj.get("status") == "success") {
				resp.put("message", "You are already registered");
				resp.put("status", false);
			} else {
				String myEmailId = forgetPassword.getEmail();
				String otp = otpStore.storeOtp(forgetPassword.getUserId());
				System.out.println("OTP:" + otp);
				try {
					if (myEmailId != null) {
						if (!myEmailId.isEmpty()) {
							mail.sendMimeMail(myEmailId, "OTP For e-Bhawan Login",
									"Dear " + forgetPassword.getUserId() + "<br> Your Otp for OBMS registration is "
											+ otp + "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the
																							// corresponding Email
						}
					}
				} catch (Exception e) {
					logger.error("error occured in sms or email send..");
				}
				try {
					String templateID = "1107160707698779534";
					smsSend.sendSMS(forgetPassword.getUserId(),
							"OTP For OBMS Registration " + otp + ", Odisha Home Dept", templateID);
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}
				// save the randomcaptca in the data base
				/*
				 * boolean status =
				 * accessControlService.saveOtpInDb(captchaCode,forgetPassword.getUserId());
				 */
				if (otp != null) {
					resp.put("message", "Successfully OTP sent to your Mobile or Email Id");
					resp.put("status", true);
				} else {
					resp.put("message", "Invalid Credentials");
					resp.put("status", false);
				}
			}
		}

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}

	@RequestMapping(value = "/forgotPasswordd", method = RequestMethod.GET)
	public ModelAndView forgotPassword() {
		String methodName = "forgotPassword";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);

		ModelAndView model = new ModelAndView();

		model.setViewName("forgotPassword");

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;

	}

	@RequestMapping(value = "/qrCode", method = RequestMethod.GET)
	public ModelAndView qrCode() {
		String methodName = "qrCode";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);

		ModelAndView model = new ModelAndView();

		model.setViewName("qrCode");

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;
	}

	@RequestMapping(value = "/registerUser",method = RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("registerRequest") UserRegistrationModel regAttr,
			BindingResult validationResult, HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws IOException, JSONException {
		logger.info("New Registration request------------->" + regAttr.toString());
		// this object will be used for returning the page
		Map<String, Object> resp = new HashMap<String, Object>();
		ModelAndView model = new ModelAndView();
		if (validationResult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + validationResult.toString());
			model.addObject("status", false);
			model.addObject("msg", "Invalid Request.");
			model.setViewName("registration");
			return model;
		}

		System.err.println(regAttr.toString());
		if (null == session || (null == session.getAttribute(CAPTCHA_KEY))) {
			throw new AuthenticationServiceException(msgInvalidCaptcha);// Invalid username or password
		}
		System.out.println("Inside captcha ====================");
		String reqCaptchaVal = req.getParameter("captcha");
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		session.removeAttribute(CAPTCHA_KEY);

		if (!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)) {
			model.addObject("errCaptcha", true);
			model.setViewName("registration");
			return model;
		}
		logger.info("Requested userId :" + regAttr.getMobile());
		JSONObject resObj = accessControlService.validateUidAndEmail(regAttr.getMobile());
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			model.addObject("userAlreadyReg", true);
			model.setViewName("registration");
			return model;
		}
		/*
		 * String path = accessControlService.uploadDocument(regAttr); if(path == null ?
		 * true : path.equals("failure")) { model.addObject("fileFormatFailed", true);
		 * model.setViewName("registration"); return model; }
		 */
		// Go for password creation with otp validation
		String myEmailId = regAttr.getEmail();
		String otp = otpStore.storeOtp(regAttr.getMobile());
		System.out.println("OTP:" + otp);
		// communicate otp to user
		// Send in sms
		try {
			if (myEmailId != null) {
				if (!myEmailId.isEmpty()) {
					mail.sendMimeMail(myEmailId, "OTP For e-Bhawan Login", "Dear " + regAttr.getMobile()
							+ "<br> Your Otp for OBMS registration is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
																														// is
																														// send
																														// to
																														// the
																														// corresponding
																														// Email
					System.out.println("Mail Sent successfully");
				}
			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send..");
		}
		try {
			String templateID = "1107160707698779534";
			smsSend.sendSMS(regAttr.getMobile(), "OTP For OBMS Registration " + otp + ", Odisha Home Dept", templateID);
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		// Send in mail
		model.addObject("userId", regAttr.getMobile());
		model.addObject("name", regAttr.getName());
		model.addObject("mobile", regAttr.getMobile());
		model.addObject("email", regAttr.getEmail());
		model.addObject("otp", otp);
		model.addObject("regstatus", true);
		model.addObject("dob.", regAttr.getDob());
		model.addObject("officeAddr", regAttr.getOfficeAddr());
		model.addObject("resAddr", regAttr.getResAddr());
		model.addObject("guestGender", regAttr.getGuestGender());
		model.addObject("dept", regAttr.getDept());
		/*
		 * model.addObject("documentpath", path); model.addObject("docType",
		 * regAttr.getDocType());
		 */
		model.setViewName("password");
		return model;

	}// end of register

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView saveGuestDetailss(@ModelAttribute("userregister") PasswordResetModel forgetPassword,
			BindingResult bresult, HttpSession session, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redirectAttributes) throws JSONException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "generateOtp";
		String path = null;
		ModelAndView model = new ModelAndView();
		logger.info("Entry---> " + this.getClass() + " method :  " + forgetPassword.toString());
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		if (bresult.hasErrors()) {
			model.addObject("errPwdFormat", true);
			model.addObject("userId", forgetPassword.getUserId());
			model.addObject("name", forgetPassword.getName());
			model.addObject("mobile", forgetPassword.getMobile());
			model.addObject("email", forgetPassword.getEmail());
			model.addObject("regstatus", true);
			model.addObject("dob", forgetPassword.getDob());
			model.addObject("officeAddr", forgetPassword.getOfficeAddr());
			model.addObject("resAddr", forgetPassword.getResAddr());
			model.addObject("guestGender", forgetPassword.getGuestGender());
			model.addObject("dept", forgetPassword.getDept());

			/*
			 * model.addObject("documentpath", forgetPassword.getDocumentpath());
			 * model.addObject("docType", forgetPassword.getDocType());
			 */
			model.setViewName("password");
			return model;
		}
		System.out.println("valid password format..");

		if (!forgetPassword.getPassword().equalsIgnoreCase(forgetPassword.getCpassword())) {
			model.addObject("errPwdFormat", true);
			model.addObject("userId", forgetPassword.getUserId());
			model.addObject("name", forgetPassword.getName());
			model.addObject("mobile", forgetPassword.getMobile());
			model.addObject("email", forgetPassword.getEmail());
			model.addObject("regstatus", true);
			model.addObject("dob", forgetPassword.getDob());
			model.addObject("officeAddr", forgetPassword.getOfficeAddr());
			model.addObject("resAddr", forgetPassword.getResAddr());
			model.addObject("guestGender", forgetPassword.getGuestGender());
			model.addObject("dept", forgetPassword.getDept());
			/*
			 * model.addObject("documentpath", forgetPassword.getDocumentpath());
			 * model.addObject("docType", forgetPassword.getDocType());
			 */
			model.setViewName("password");
			return model;
		}
		logger.info("Requested userId :" + forgetPassword.getUserId());

		if (!otpStore.isOtpValid(forgetPassword.getMobile(), forgetPassword.getOtp())) {
			model.addObject("invalidOtp", true);
			model.addObject("userId", forgetPassword.getUserId());
			model.addObject("name", forgetPassword.getName());
			model.addObject("mobile", forgetPassword.getMobile());
			model.addObject("email", forgetPassword.getEmail());
			model.addObject("regstatus", true);
			model.addObject("dob", forgetPassword.getDob());
			model.addObject("officeAddr", forgetPassword.getOfficeAddr());
			model.addObject("resAddr", forgetPassword.getResAddr());
			model.addObject("guestGender", forgetPassword.getGuestGender());
			model.addObject("dept", forgetPassword.getDept());
			/*
			 * model.addObject("documentpath", forgetPassword.getDocumentpath());
			 * model.addObject("docType", forgetPassword.getDocType());
			 */
			model.setViewName("password");
			return model;
		}
		// path=forgetPassword.getDocumentpath();
		/* System.out.println("valid otp.."+path); */

		JSONObject resValid = accessControlService.validateUid(forgetPassword.getMobile());
		logger.info("existance status of userid and emailId of a user:" + resValid.toString());
		boolean status = false;
		if (resValid.get("status") == "failure") {
			UserModel user = new UserModel();
			user.setActive(true);
			user.setUserId(forgetPassword.getMobile());
			user.setFirstName(forgetPassword.getName());
			user.setAdmin(false);
			user.setFirstLogin(true);
			user.setPassword(forgetPassword.getPassword());
			user.setEmail(forgetPassword.getEmail());
			user.setCreatedBy(forgetPassword.getMobile());
			user.setCreatedDate(new Date());
			user.setContactNo(forgetPassword.getMobile());
			user.setOtpLogin(false);
			user.setUpload_doc(false);
			user.setUserType("GUEST");
			/*
			 * if(path != null) { user.setDocType(forgetPassword.getDocType());
			 * user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\")) :
			 * path.substring(path.indexOf("/"))); }
			 */
			status = accessControlService.saveGuestDetails(user);
			if (status) {
				status = accessControlService.saveMasterGuestDetails(forgetPassword);
				UserGroupHasRoles userGroup = new UserGroupHasRoles();
				userGroup.setUserId(user.getUserId());
				userGroup.setGroup_id(2);
				accessControlService.saveUserGroup(userGroup);
			}
			String myEmailId = forgetPassword.getEmail();
			try {
				if (myEmailId != null) {
					if (!myEmailId.isEmpty()) {
						mail.sendMimeMail(myEmailId, "You are successfully registered In OBMS",
								"Dear " + forgetPassword.getMobile()
										+ "<br> You are successfully registered In e-Bhawan"
										+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
																				// Email
					}
				}
				String templateID = "1107160707714316860";
				smsSend.sendSMS(forgetPassword.getMobile(), "You are Successfully register in OBMS, Odisha Home Dept",
						templateID);
			} catch (Exception e) {
				logger.error("error occured in sms or email send..");
			}

		} else {
			UserModel user = new UserModel();
			user.setFirstName(forgetPassword.getName());
			user.setAdmin(false);
			user.setFirstLogin(true);
			user.setPassword(forgetPassword.getPassword());
			user.setEmail(forgetPassword.getEmail());
			user.setContactNo(forgetPassword.getMobile());
			user.setUserId(forgetPassword.getMobile());
			user.setOtpLogin(false);
			//user.setUpload_doc(false);
			/*
			 * if(path != null) { user.setDocType(forgetPassword.getDocType());
			 * user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\")) :
			 * path.substring(path.indexOf("/"))); }
			 */
			status = accessControlService.updateGuestDetails(user);
			if (status) {
				status = accessControlService.saveMasterGuestDetails(forgetPassword);
				status = accessControlService.updateUserDetailsOtp(user);
			}
			if (status) {
				String myEmailId = forgetPassword.getEmail();
				try {
					if (myEmailId != null) {
						if (!myEmailId.isEmpty()) {
							mail.sendMimeMail(myEmailId, "You are successfully registered In e-Bhawan",
									"Dear " + forgetPassword.getMobile()
											+ "<br> You are successfully registered In OBMS"
											+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
																					// Email
						}
					}
				} catch (Exception e) {
					logger.error("error occured in sms or email send..");
				}
				try {
					String templateID = "1107160707714316860";
					smsSend.sendSMS(forgetPassword.getMobile(),
							"You are Successfully register in OBMS, Odisha Home Dept", templateID);
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}
			}
		}
		if (status == true) {
			redirectAttributes.addFlashAttribute("regstatus", true);
			model = new ModelAndView("redirect:/login.html");
		} else {
			redirectAttributes.addFlashAttribute("regstatus", false);
			model = new ModelAndView("redirect:/login.html");
		}

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;
	}

	@RequestMapping(value = { "/userProfile" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getEmailDetails(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();

		UserModel lstUserDetails = accessControlService.getUserByUserId(userId);
		if (lstUserDetails != null) {
			lstUserDetails = accessControlService.getUserMasterData(lstUserDetails);
		}
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);

		return resp;
	}

	@RequestMapping(value = { "/updateProfile" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateProfile(@Valid @ModelAttribute UserRegistrationModel forgetPassword,
			BindingResult bresult, HttpSession session) throws JSONException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "updateProfile";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		if (bresult.hasErrors()) {
			System.out.println("Inside::Server Side Validation @@ hasErrors");
			System.out.println("error occured" + bresult.toString());
			/*
			 * resp.put("status", false); resp.put("message", "Invalid Request.");
			 */
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		} else {
			logger.info("Requested userId :" + forgetPassword.getUserId());
			JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getUserId());
			logger.info("existance status of userid and emailId of a user:" + resObj.toString());
			if (resObj.get("status") == "success") {
				String path = accessControlService.uploadDocument(forgetPassword);
				if (path != null ? path.equals("failure") : false) {
					resp.put("message", "File Format is not correct");
					resp.put("status", false);
				} else {
					JSONObject resValid = accessControlService.validateUid(forgetPassword.getUserId());
					logger.info("existance status of userid and emailId of a user:" + resObj.toString());
					boolean status = false;
					if (resValid.get("status") == "failure") {
						UserModel user = new UserModel();
						user.setActive(true);
						user.setUserId(forgetPassword.getUserId());
						user.setFirstName(forgetPassword.getName());
						user.setAdmin(false);
						user.setFirstLogin(true);
						user.setEmail(forgetPassword.getEmail());
						user.setCreatedBy(forgetPassword.getUserId());
						user.setCreatedDate(new Date());
						user.setContactNo(forgetPassword.getMobile());
						user.setOtpLogin(false);
						user.setUpload_doc(false);
						user.setUserType("GUEST");
						user.setDocType(forgetPassword.getDocType());
						if (path != null) {
							user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\"))
									: path.substring(path.indexOf("/")));
						}
						status = accessControlService.updateGuestDetails(user);
					} else {
						resp.put("message", "otp based login user can not update profile");
						resp.put("status", false);
					}
					if (status == true) {
						status = accessControlService.updateMasterGuestDetails(forgetPassword);
						if (!status) {
							forgetPassword.setMobile(forgetPassword.getUserId());
							status = accessControlService.saveMasterGuestDetailss(forgetPassword);
						}
						resp.put("message", "Successfully Updated");
						resp.put("status", true);
					} else {
						resp.put("message", "Invalid Credentials");
						resp.put("status", false);
					}
				}
			}
		}
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}

	@RequestMapping(value = "/imagedownload/{imageCode}", method = RequestMethod.GET)
	public void mediaDownload(HttpServletResponse response, @PathVariable("imageCode") String imageCode,
			Principal principal) throws IOException {
		logger.debug("Inside /imagedownload/{imageCode}");
		accessControlService.mediaDownloadService(response, imageCode, principal);
	}

	@RequestMapping(value = { "/getAllLocation" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllLocationAccordingToRole(HttpServletRequest req,
			HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("getAllLocation: ");

		System.out.println("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = guestService.getAllLocation();
		resp.put("data", lstLocation);

		return resp;
	}

	@RequestMapping(value = { "/getAllProcess" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllProcess(@RequestBody AllLocationModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("getAllProcess: ");

		System.out.println("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = guestService.getAllProcess(request);
		resp.put("data", lstLocation);
		return resp;
	}

	@RequestMapping(value = { "/savefeedbackDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveFeedback(@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("saveFeedback: " + request.toString());
		System.out.println("Inside:: @@ hasNotErrors" + request.toString());
		boolean status = guestService.saveFeedback(request);
		if (status) {
			resp.put("message", "Feedback Successfully Saved");
			resp.put("status", status);
		} else {
			resp.put("message", "Failed to Add");
			resp.put("status", status);
		}

		return resp;
	}

	@RequestMapping(value = { "/validatecaptcha" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> validatecaptch(@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("validatecaptch: " + req.toString());
		System.out.println("Inside:: @@ hasNotErrors" + req.toString());
		System.out.println("Inside captcha ====================");
		String reqCaptchaVal = request.getCaptcha();
		System.out.println("Inside captcha ====================" + reqCaptchaVal);
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		System.out.println("Inside captcha sessCaptchaVal ====================" + sessCaptchaVal);

		if (!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)) {
			resp.put("message", "Invalid Captcha");
			resp.put("status", false);
		} else {
			session.removeAttribute(CAPTCHA_KEY);
			resp.put("status", true);
		}

		return resp;
	}

	@RequestMapping(value = "/printOrderCopy", method = RequestMethod.GET)
	public Object printOrderCopy(@RequestParam("ghId") String ghId, @RequestParam("cityId") String cityId,
			@RequestParam("processId") String processId, HttpServletResponse response) {
		logger.debug("Inside printOrderCopy");
		return guestService.printOrderCopy(ghId, cityId, processId, response);
	}

	@RequestMapping(value = { "/allDataListforQr" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getallValueDetails(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resp = new HashMap<String, Object>();

		List<BookingResponseModel> bookingdata = guestService.getallValueDetails(request);
		resp.put("status", true);

		resp.put("data", bookingdata);
		if (bookingdata != null) {
			resp.put("msg", "Successfully received");
		} else {
			resp.put("msg", "no data received");
		}
		return resp;
	}

	@RequestMapping(value = { "/getGuestHouseListDetailsforQr/{id}" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getGuestHouseListDetailsforQr(
			@PathVariable("id") String id) {
		return guestService.getGuestHouseListforQr(id);
	}

	@RequestMapping(value = { "/getCityDetailsforQr" }, method = RequestMethod.GET)
	public @ResponseBody List<CommonComboBoxResponseModel> getCityDetailsforQr() {
		return guestService.getCityListforQr();
	}

	@RequestMapping(value = "/qrCodeValidation", method = RequestMethod.POST)
	public ModelAndView qrCodeValidation(
			@ModelAttribute("qrCodeValidation") ChangePasswordWithoutAuthModel forgetPassword,
			RedirectAttributes redirectAttributes, BindingResult bresult, HttpSession session, HttpServletRequest req,
			HttpServletResponse res) throws JSONException, IOException, MessagingException {
		System.out.println(forgetPassword.toString());
		String methodName = "savePassword";
		ModelAndView model = new ModelAndView();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		System.out.println("Inside captcha ====================");
		String reqCaptchaVal = req.getParameter("captcha");
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		session.removeAttribute(CAPTCHA_KEY);

		if (!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)) {
			model.addObject("errCaptcha", true);
			model.setViewName("qrCode");
			return model;
		}
		// JSONObject resObj =
		// accessControlService.validateUidAndEmail(forgetPassword.getMobile());
		JSONObject resObj = accessControlService.validateforFeedback(forgetPassword);
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		List<BookingResponseModel> bookingResponseModel = null;
		String myEmailId = null;
		String mobile = null;
		String reqId = null;
		String name = null;
		if (resObj.get("status") == "success") {
			mobile = resObj.get("mobile_no").toString();
			reqId = resObj.get("req_id").toString();
			myEmailId = resObj.get("email").toString();
			name = resObj.get("Name").toString();
			String date = resObj.get("date").toString();

			// JSONObject resObj2=accessControlService.validateforFeedbackforDt(reqId,date);

			bookingResponseModel = guestService.getFeedback(reqId, mobile);
			System.out.println("bookingResponseModel" + bookingResponseModel.toString());
			if (bookingResponseModel.isEmpty()) {
				model.addObject("status", false);
				model.addObject("msg", "You have already submitted the feedback");
				model.setViewName("qrCode");
				return model;
			}
		} else {
			model.addObject("status", false);
			model.addObject("msg", "Your mobile no is not registered with us");
			model.setViewName("qrCode");
			return model;
		}

		String otp = otpStore.storeOtp(forgetPassword.getMobile());
		System.out.println("OTP:" + otp);
		try {
			if (myEmailId != null) {
				if (!myEmailId.isEmpty()) {
					mail.sendMimeMail(myEmailId, "OTP For feedback", "Dear " + forgetPassword.getMobile()
							+ "<br> Your Otp for forget password is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
																													// is
																													// send
																													// to
																													// the
																													// corresponding
																													// Email
				}
			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send.." + e.getMessage());
		}
		try {

			String templateID = "1107161251118912084";
			smsSend.sendSMS(forgetPassword.getMobile(), "OTP For Portal Login " + otp + ", Odisha Home Dept",
					templateID);
		} catch (Exception e) {
			logger.error("error occured in sms or email send..");
		}

		// save the randomcaptca in the data base
		System.out.println("mobile" + mobile);
		if (otp != null) {
			model.addObject("mobile", mobile);
			model.addObject("Name", name);
			model.addObject("mobile", mobile);
			model.addObject("requestId", reqId);
			model.setViewName("qrCodekt");

		} else {
			model.addObject("mobile", forgetPassword.getMobile());
			model.setViewName("qrCode");
		}

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;

	}

	@RequestMapping(value = "/qrCodeValidationKt", method = RequestMethod.POST)
	public ModelAndView qrCodeValidationKt(
			@Valid @ModelAttribute("qrCodeValidationKt") ChangePasswordWithoutAuthModel pwdAttr,
			RedirectAttributes redirectAttributes, BindingResult validationResult, HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		logger.info("Elisible to give feedback..");
		System.out.println("details:" + pwdAttr.toString());
		// this object will be used for returning the page
		ModelAndView model = new ModelAndView();
		if (validationResult.hasErrors()) {
			System.err.println(validationResult.getAllErrors());
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("errPwdFormat", true);
			model.setViewName("qrCodekt");
			return model;
		}
		System.out.println("valid inputs..");
		System.out.println("userId" + pwdAttr.getUserId() + "and otp" + pwdAttr.getOtp());
		if (!otpStore.isOtpValid(pwdAttr.getUserId(), pwdAttr.getOtp())) {

			model.addObject("invalidOtp", true);
			model.addObject("userId", pwdAttr.getUserId());
			model.setViewName("qrCodekt");
			return model;
		}
		System.out.println("valid otp..");
		System.err.println("data: " + pwdAttr);
		//
		//
		// boolean status=false;
		// if(status == true) {
		model = new ModelAndView(
				"redirect:/mobilefeedback.html?requestid=" + pwdAttr.getReqId() + "&mobileno=" + pwdAttr.getUserId());
		// }else {
		// model= new ModelAndView("redirect:/feedback.html?");
		// }
		return model;
	}// end of resetForgetPassword

}// end class
