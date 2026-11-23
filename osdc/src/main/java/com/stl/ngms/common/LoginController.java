package com.stl.ngms.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Key;
import java.security.Principal;
import java.security.SecureRandom;
import java.text.AttributedString;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stl.ngms.admin.service.AdminService;
import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.common.model.LoginUserDetailsVO;
import com.stl.ngms.common.model.SimpleModel;
import com.stl.ngms.common.model.UserRegistrationModel;
import com.stl.ngms.common.service.OtpStore;
import com.stl.ngms.config.AbstractResponse;
import com.stl.ngms.config.JwtTokenResponse;
import com.stl.ngms.config.KeyGenerator;
import com.stl.ngms.config.MobileLoginPojo;
import com.stl.ngms.config.StatusResponse;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.jwt.JwtUtil;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.service.MdmService;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.Resource;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.BCrypt;
import com.stl.ngms.utils.CaptchaModel;
import com.stl.ngms.utils.CaptchaUtils;
import com.stl.ngms.utils.Sha512;
import com.stl.ngms.utils.Utils;

@Controller
@CrossOrigin
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
	@Autowired
	MdmService mdmService;
	
	@Autowired
	AdminService adminService;
	private final static SecureRandom srandom = new SecureRandom();

	@Value("${captcha.login-captcha-time}")
	private Long loginCaptchaValidity;
	private static final int CAPTCHA_LENGTH = 5;
	private static final long serialVersionUID = 1512371749422L;
	@Value("${jwt.token.validity.in.minutes}")
	private String jwtvalidity;
	@Value("${captcha.secret.salt}")
	private String captchaSalt;
	@Autowired
	private AccessControlService acService;
	@Autowired
	KeyGenerator keyGenerator;
	@Autowired JwtUtil jwtUtil;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpSession session, RedirectAttributes redirect,Principal principal,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "sessExp", required = false) String sessExp,
			@RequestParam(value = "login", required = false) String loginn) {

		logger.info("inside login page request with url /login");

		// session.invalidate();// Required to generate a new session id on logout
		// or upon login page request so that any url rewriting not works

		// Added by Lusi on Date-26-Oct-2017
		session = request.getSession();
		String saltKey = BCrypt.generateSalt(srandom);
		session.setAttribute("saltKey", saltKey);
		logger.info(saltKey);
		ModelAndView model = new ModelAndView("login");
		List<AllLocationModel> lstLocation = guestService.getAllLocation();
		if (loginn != null) {
			if (loginn.equalsIgnoreCase("guest")) {
				model.addObject("userType", "no");
			} else {
				model.addObject("userType", "yes");
			}
		}
		model.addObject("lstLocation", lstLocation);	
		model.addObject("saltKey", saltKey);
		if (error != null) {
// 			failure login attempt store in m_audit_login
			String userId=(String) session.getAttribute("userId");
			System.out.println("****************************"+userId);
			AuditlogRequest loginRequest = new AuditlogRequest();
			loginRequest.setUserId(userId);
			loginRequest.setRecordStatus("failure");
			loginRequest.setAction("Login Attempt");
			String rmtAddr = request.getRemoteAddr();
			loginRequest.setIpAddress(rmtAddr);
			Boolean auditTrail = accessControlService.saveAuditLog(loginRequest, request);
			if (auditTrail) {
				logger.info("Audit Trail Implemented");
			} else {
				logger.info("Audit Trail Not Implemented");
			}
			logger.info("error-->" + session.getAttribute("userType"));
//			model.addObject("error", "Invalid username or password!");//Not required as we are giving spring customized message from Authenticator
			redirect.addFlashAttribute("error", "Bad Credentials!");
			if(session.getAttribute("userType").equals("GUEST")) {
			   return new ModelAndView("redirect:/login.html?login=guest");
			}else {
				return new ModelAndView("redirect:/login.html?login=admin");
			}
		}
		if (logout != null) {
			if (session != null) {
				session.invalidate();
			}
			logger.info("logout-->" + logout);
//			model.addObject("msg", "You've been logged out successfully.");
			redirect.addFlashAttribute("msg", "You've been logged out successfully.");
			return new ModelAndView("redirect:/login.html");
		}
		if (sessExp != null) {
			logger.info("sessExp-->" + sessExp);
//			model.addObject("msg", "Your current session expired!.");
			logger.info("session coming");
			redirect.addFlashAttribute("msg", "Your current session expired!.");
			return new ModelAndView("redirect:/login.html");
		}
		// model.setViewName("login");
		return model;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView homeView(HttpSession session) {
		logger.info("redirecting to Home page");
		/*if (session != null) {
			session.invalidate();
		}*/
		ModelAndView model = new ModelAndView("home");
		List<GuestHouseModel> guestHouseModel = mdmService.getDataGHTable();
		model.addObject("guestHouseModel", guestHouseModel);
		return model;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registerPage(HttpSession session) {
		logger.info("redirecting to registration page");
		session.setAttribute("sessionRegister", "styleRegister");
		return "registration";
	}

	@RequestMapping(value = { "/feedback" }, method = RequestMethod.GET)
	public String feedbackPage(HttpSession session) {
		logger.info("redirecting to registration page");
		session.setAttribute("sessionRegister", "styleRegister");
		// mail.sendMimeMail("deepak.sahoo@silicontechlab.com","OTP For Portal
		// Login","Dear Deepak <br> Your Otp for forget password is <h3><a
		// href='http://localhost:8088/ngms/mobilefeedback.html?request_id=P7916&mobile_no=9337417561'
		// target=\"_self\">VERIFY</a></h3> <br><br> Thanks <br>e-Bhawan Team");

		return "feedback";
	}

	@RequestMapping(value = { "/mobilefeedback" }, method = RequestMethod.GET)
	public ModelAndView feedbackPageview(@RequestParam("requestid") String requestId,
			@RequestParam("mobileno") String mobileNo, HttpSession session) {
		logger.info("redirecting to registration page" + requestId);
		List<BookingResponseModel> bookingResponseModel = guestService.getFeedback(requestId, mobileNo);
		logger.info(bookingResponseModel.toString());
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
		logger.info("redirecting to registration page");
		session.setAttribute("sessionRegister", "styleRegister");
		return "password";
	}

	@RequestMapping(value = { "/reregistration" }, method = RequestMethod.GET)
	public ModelAndView reregistration(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  registration");
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		String userID = session.getAttribute("userId").toString();
		String role = session.getAttribute("role").toString();
		ModelAndView model = new ModelAndView();
		UserModel userModel = accessControlService.getRegisterDetails(userID,role).get(0);
		System.out.println("Datattttttt " + userID);
		model.addObject("userModel", userModel);
		model.setViewName("reregistration");
		return model;
	}

	@RequestMapping(value = { "/adminRegistrationDetails" }, method = RequestMethod.GET)
	public ModelAndView adminRegistrationDetails(HttpSession session, HttpServletRequest req) {
		logger.debug("redirecting to  adminRegistrationDetails");
		session.setAttribute("sessionfDashboardTab", "styleActiveDashboard");
		String userID = session.getAttribute("userId").toString();
		String role = session.getAttribute("role").toString();
		ModelAndView model = new ModelAndView();
		UserModel userModel = accessControlService.getRegisterDetails(userID,role).get(0);
		if (userModel != null) {
			userModel = accessControlService.getUserMasterData(userModel);
		}
		System.out.println("usermodel__________________" + userModel);
		model.addObject("userModel", userModel);
		model.setViewName("adminRegistrationDetails");
		return model;
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
	public ModelAndView accesssDenied(Principal user) {
		String methodName = "accesssDenied";
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);

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
		String role = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
		}
		if (authentication != null) {
			List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority ga : grantedAuths) {
				allowedRoles.add(ga.getAuthority());
				role = ga.getAuthority();
			}
		}
		String userId = principal.getName();
		session.setAttribute("role", role);
		logger.info("principal username/Id : " + userId);
		// Save user id/name in session scope
		session.setAttribute("userId", userId);
		logger.info("currentSessionId : " + session.getId());
		session.setAttribute("currentSessionId", session.getId());
		// get first name and last name from adminr.user and set in session
		/*List<LoginUserDetailsVO> getFirstNameLastName = new ArrayList<LoginUserDetailsVO>();
		List<LoginUserDetailsModel> firstNameLastName = accessControlService.getFirstNameLastName(userId);*/
		List<UserModel> document_upload = accessControlService.getRegisterDetails(userId,role);
		System.out.println("document_upload" + document_upload.get(0).toString());
		LoginUserDetailsVO fnln = null;
		if (document_upload.get(0).isUpload_doc() == false) {
			if (document_upload.get(0).getUserType().equalsIgnoreCase("GUEST")) {
				return new ModelAndView("redirect:/reregistration.html");
			} else {
				return new ModelAndView("redirect:/adminRegistrationDetails.html");
			}
		}
		/*if (firstNameLastName != null && firstNameLastName.size() > 0) {
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
		session.setAttribute("last_name", last_name);*/

		session.setAttribute("showNameWelcomeSession", "true");
		
		logger.info("allowedRoles-->" + allowedRoles);
		session.setAttribute("roleId", allowedRoles);
		// Used for Role Base Resource Controller by Vamsi Krish
		Map<String, Object> groupIdLocationId = accessControlService.getUserIdLocationId(userId,
				document_upload.get(0).getUserType());
		session.setAttribute("groupIdLocationId", groupIdLocationId);
		System.out.println("groupIdLocationId--->" + groupIdLocationId.toString());
		JSONObject mappedCityLocsByUser = accessControlService.getAllMappedCityLocByUser(userId, role);
		JSONObject mappedCityLocsByUser1 = accessControlService.getAllMappedCityLocByUser1(userId, role);
		System.out.println("role--->" + role);

		try {
			System.out.println("mappedCityLocsByUser--->" + mappedCityLocsByUser.get("data"));
			session.setAttribute("mappedCityLocsByUser", mappedCityLocsByUser.get("data").toString());
			session.setAttribute("mappedCityLocsByUser1", mappedCityLocsByUser1.get("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		/*
		 * Retrieve all allowed unique resources to this user and putting in session
		 */
		System.out.println("allowedRoles"+allowedRoles.toString());
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
			resp.put("message", "Successfully OTP sent to your Mobile");
			resp.put("status", true);
		} else {
			resp.put("message", "Invalid Credentials");
			resp.put("status", false);
		}
		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return resp;
	}// end of validateOtp

	@RequestMapping(value = "/retryOtp", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> retryOtp(HttpServletRequest req, HttpServletResponse res,
			@RequestBody SimpleModel model) {

//		String id = String.valueOf(reqMap.get("id"));
		logger.info("Resend OTP request userid: " + model.getUid());
		Map<String, Object> resp = new HashMap<String, Object>();

		String otp = otpStore.getDuplicateOtp(model.getUid());
		logger.info("Retry otp :" + otp);

		if (null == otp) {
			resp.put("status", false);
			resp.put("message", "Invalid Request.");
			return resp;
		}
		// send otp in mail and sms
		// Send in mail
//		try {
//			if (!StringUtils.isEmpty(model.getEmailId())) {
//				if (!model.getEmailId().isEmpty()) {
//					mail.sendMimeMail(model.getEmailId(), "OTP For e-Bhawan Login", "Dear " + model.getUid()
//							+ "<br> Your Otp for OBMS registration is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
//																														// is
//																														// send
//																														// to
//																														// the
//																														// corresponding
//																														// Email
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}
		if (!StringUtils.isEmpty(model.getUid())) {
			try {
				String templateID = "1077572430132285401";
				smsSend.sendSMS(model.getUid(), "OTP for OBMS Registration " + otp + ". For details please login to the portal. Home Dept., Govt. of Odishat", templateID, "sendOTPSMS");
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
		logger.info("Inside captcha ====================");
		String reqCaptchaVal = req.getParameter("captcha");
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		session.removeAttribute(CAPTCHA_KEY);

		if (!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)) {
			if(forgetPassword.getUserType().equalsIgnoreCase("GUEST")) {
			redirectAttributes.addFlashAttribute("errCaptcha", true);
//			return new ModelAndView("redirect:/forgotPasswordd.html?forgetPassword=guest");
			redirectAttributes.addFlashAttribute("forgetPassword", "guest");
			return new ModelAndView("redirect:/forgotPasswordd.html");
			}else {
				redirectAttributes.addFlashAttribute("errCaptcha", true);
//				return new ModelAndView("redirect:/forgotPasswordd.html?forgetPassword=admin");
				redirectAttributes.addFlashAttribute("forgetPassword", "admin");
				return new ModelAndView("redirect:/forgotPasswordd.html");
			}
		}
		JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getMobile(),
				forgetPassword.getUserType());
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "falure") {
			if(forgetPassword.getUserType().equalsIgnoreCase("GUEST")) {
				redirectAttributes.addFlashAttribute("userNotFound", true);
				redirectAttributes.addFlashAttribute("forgetPassword", "guest");
				return new ModelAndView("redirect:/forgotPasswordd.html");
//				return new ModelAndView("redirect:/forgotPasswordd.html?forgetPassword=guest");
				}else {
					redirectAttributes.addFlashAttribute("userNotFound", true);
					redirectAttributes.addFlashAttribute("forgetPassword", "admin");
					return new ModelAndView("redirect:/forgotPasswordd.html");
//					return new ModelAndView("redirect:/forgotPasswordd.html?forgetPassword=admin");
				}
		}
		String myEmailId = resObj.get("email").toString();
		String otp = otpStore.storeOtp(forgetPassword.getMobile());
		logger.info("OTP:" + otp);
//		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For Ebhawan Portal",
//							"OTP For Forget Password " + otp + ",<br><br> Odisha Home Dept");// mail is send to the
//																								// corresponding Email
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send.." + e.getMessage());
//		}
		try {
			System.out.println("Inside Forgot Password Send SMS");
			String templateID = "1077106720132389062";
			smsSend.sendSMS(forgetPassword.getMobile(), "OTP for Forget Password " + otp + ". For details please login to the portal. Home Dept., Govt. of Odisha",
					templateID, "sendOTPSMS");
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		// save the randomcaptca in the data base

		if (otp != null) {
			model.addObject("userId", forgetPassword.getMobile());
			model.addObject("userType", forgetPassword.getUserType());
			model.setViewName("resetKt");
		} else {
			if(forgetPassword.getUserType().equalsIgnoreCase("GUEST")) {
				redirectAttributes.addFlashAttribute("forgetPassword", "guest");
				return new ModelAndView("redirect:/forgotPasswordd.html");
//				model= new ModelAndView("redirect:/forgotPasswordd.html?forgetPassword=guest");
				}else {
					redirectAttributes.addFlashAttribute("forgetPassword", "admin");
					return new ModelAndView("redirect:/forgotPasswordd.html");
//				model= new ModelAndView("redirect:/forgotPasswordd.html?forgetPassword=admin");
				}
		}
		/*
		 * UserRegistrationModel user = new UserRegistrationModel(); if
		 * (!StringUtils.isEmpty(regAttr.getUserId())) { user =
		 * commonService.getUserDetails(regAttr.getUserId()); }
		 */

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;

	}// End of savePassword

	@RequestMapping(value = "/resetForgetPassword", method = RequestMethod.POST)
	public ModelAndView resetForgetPassword(
			@Valid @ModelAttribute("forgetRequest") ChangePasswordWithoutAuthModel pwdAttr,
			RedirectAttributes redirectAttributes, BindingResult validationResult, HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		logger.info("Reset password request..");
		logger.info("details:" + pwdAttr);
		// this object will be used for returning the page
		ModelAndView model = new ModelAndView();
		if (validationResult.hasErrors()) {
			System.err.println(validationResult.getAllErrors());
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("userType", pwdAttr.getUserType());
			model.addObject("errPwdFormat", true);
			model.setViewName("resetKt");
			return model;
		}
		logger.info("valid inputs..");

		if (!otpStore.isOtpValid(pwdAttr.getUserId(), pwdAttr.getOtp())) {

			model.addObject("invalidOtp", true);
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("userType", pwdAttr.getUserType());
			model.setViewName("resetKt");
			return model;
		}
		logger.info("valid otp..");
		System.err.println("data: " + pwdAttr);

		if (!StringUtils.equals(pwdAttr.getPassword(), pwdAttr.getCpassword())) {

			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("userType", pwdAttr.getUserType());
			model.addObject("errPwdFormat", true);
			model.setViewName("resetKt");
			return model;
		}
		if(!accessControlService.checkLastTimeExceed(pwdAttr.getUserId(),pwdAttr.getUserType())) {
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("userType", pwdAttr.getUserType());
			model.addObject("resetPasswordBlock", true);
			model.setViewName("resetKt");
			return model;
		}
		if(accessControlService.checkPasswordIsPresentOrNot(pwdAttr.getUserId(),pwdAttr.getUserType(),pwdAttr.getPassword())) {
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("userType", pwdAttr.getUserType());
			model.addObject("recentPassword", true);
			model.setViewName("resetKt");
			return model;
		}
		boolean status = accessControlService.savePassword(pwdAttr.getUserId(), pwdAttr.getPassword(),pwdAttr.getUserType());// method thaqt
		boolean historystatus=accessControlService.saveOnPasswordHistory(pwdAttr.getPassword(),pwdAttr.getUserId(),pwdAttr.getUserType(),"Forgot password");																			
		if (status == true && historystatus==true) {
			if(pwdAttr.getUserType().equalsIgnoreCase("GUEST")) {
				redirectAttributes.addFlashAttribute("pwdustatus", true);
				model= new ModelAndView("redirect:/login.html?login=guest");
				}else {
					redirectAttributes.addFlashAttribute("pwdustatus", true);
					model= new ModelAndView("redirect:/login.html?login=admin");
				}
		} else {
			if(pwdAttr.getUserType().equalsIgnoreCase("GUEST")) {
				redirectAttributes.addFlashAttribute("pwdustatus", false);
				model= new ModelAndView("redirect:/login.html?login=guest");
				}else {
					redirectAttributes.addFlashAttribute("pwdustatus", false);
					model= new ModelAndView("redirect:/login.html?login=admin");
				}
		}
		return model;
	}// end of resetForgetPassword

	@RequestMapping(value = "/forgotPasswordd", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView forgotPassword(@ModelAttribute("forgetPassword") String forgetPassword) {
	    String methodName = "forgotPassword";
	    logger.info("Entry---> " + this.getClass() + " method : " + methodName);
	    System.out.println("++++++++++" + forgetPassword);

	    ModelAndView model = new ModelAndView();
	    if (forgetPassword != null) {
	        if (forgetPassword.equalsIgnoreCase("guest")) {
	            model.addObject("userType", "no");
	        } else {
	            model.addObject("userType", "yes");
	        }
	    }
	    model.setViewName("forgotPassword");

	    logger.info("Exit---> " + this.getClass() + " method : " + methodName);
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
	// This work made by sandip. To manually handeled  rate limit in a api
	private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
    private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
    private void scheduleRequestReset(String clientIP) {
        executorService.schedule(() -> requestCounts.remove(clientIP), 1, TimeUnit.SECONDS);
    }
    private boolean exceedsRateLimit(String clientIP) {
        int requestCount = requestCounts.getOrDefault(clientIP, 0);
        if (requestCount >= 1) {
            return true;
        } else {
            requestCounts.put(clientIP, requestCount + 1);
            scheduleRequestReset(clientIP);
            return false;
        }
    }
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("registerRequest") UserRegistrationModel regAttr,
			BindingResult validationResult, HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws IOException, JSONException {
		logger.info("New Registration request------------->" + regAttr.toString());
		String clientIP = req.getRemoteAddr(); 
        if (exceedsRateLimit(clientIP)) {
        	ModelAndView model = new ModelAndView();
        	model.addObject("status", false);
			model.addObject("msg", "Limit Exceed.");
			model.setViewName("registration");
			return model;
        }
		// this object will be used for returning the page
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
		JSONObject resObj = accessControlService.validateUidAndEmail(regAttr.getMobile(), "GUEST");
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			model.addObject("userAlreadyReg", true);
			model.setViewName("registration");
			return model;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        Date currentDate=null;
		Date dateOFBirth = null;
		try {
			currentDate = simpleDateFormat.parse(simpleDateFormat.format(date));
			dateOFBirth = simpleDateFormat.parse(regAttr.getDob());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(currentDate.compareTo(dateOFBirth) <= 0) {
			System.out.println("working");
			model.addObject("dobValid", true);
			model.setViewName("registration");
			return model;
		}
		// Go for password creation with otp validation
		String myEmailId = regAttr.getEmail();
		String otp = otpStore.storeOtp(regAttr.getMobile());
		System.out.println("OTP:" + otp);
		// communicate otp to user
		// Send in sms
//		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For e-Bhawan Login", "Dear " + regAttr.getMobile()
//							+ "<br> Your Otp for OBMS registration is " + otp + "<br><br> Thanks <br>e-Bhawan Team");
//					System.out.println("Mail Sent successfully");
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}
		try {
			String templateID = "1077572430132285401";
			smsSend.sendSMS(regAttr.getMobile(), "OTP for OBMS Registration " + otp + ". For details please login to the portal. Home Dept., Govt. of Odishat", templateID, "sendOTPSMS");
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
		model.addObject("dob", regAttr.getDob());
		/* model.addObject("officeAddr", regAttr.getOfficeAddr()); */
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

	@Transactional
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
			/* model.addObject("officeAddr", forgetPassword.getOfficeAddr()); */
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
			/* model.addObject("officeAddr", forgetPassword.getOfficeAddr()); */
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
			/* model.addObject("officeAddr", forgetPassword.getOfficeAddr()); */
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

		JSONObject resValid = accessControlService.validateUid(forgetPassword.getMobile(),"GUEST");
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
			forgetPassword.setUserType("GUEST");
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
				accessControlService.saveOnPasswordHistory(forgetPassword.getPassword(), forgetPassword.getUserId(), forgetPassword.getUserType(), "Registration");
			}
			String myEmailId = forgetPassword.getEmail();
			try {
//				if (myEmailId != null) {
//					if (!myEmailId.isEmpty()) {
//						mail.sendMimeMail(myEmailId, "You are successfully registered In OBMS",
//								"Dear " + forgetPassword.getMobile()
//										+ "<br> You are successfully registered In e-Bhawan"
//										+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
//																				// Email
//					}
//				}
				String templateID = "1107160707714316860";
				smsSend.sendSMS(forgetPassword.getMobile(), "You are Successfully register in OBMS, Odisha Home Dept",
						templateID, "singleSMS");
			} catch (Exception e) {
				logger.error("error occured in sms or email send..");
			}

		} else {
			System.out.println("forgetPassword.getPassword()"+forgetPassword.getPassword());
			UserModel user = new UserModel();
			user.setFirstName(forgetPassword.getName());
			user.setAdmin(false);
			user.setFirstLogin(true);
			user.setPassword(forgetPassword.getPassword());
			user.setEmail(forgetPassword.getEmail());
			user.setContactNo(forgetPassword.getMobile());
			user.setUserId(forgetPassword.getMobile());
			user.setCreatedBy(forgetPassword.getMobile());
			user.setOtpLogin(false);
			user.setUserType("GUEST");
			forgetPassword.setUserType("GUEST");
			// user.setUpload_doc(false);
			/*
			 * if(path != null) { user.setDocType(forgetPassword.getDocType());
			 * user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\")) :
			 * path.substring(path.indexOf("/"))); }
			 */
			status = accessControlService.updateGuestDetails(user, false);
			if (status) {
				status = accessControlService.saveMasterGuestDetails(forgetPassword);
				//status = accessControlService.updateUserDetailsOtp(user);
			}
			if (status) {
				String myEmailId = forgetPassword.getEmail();
//				try {
//					if (myEmailId != null) {
//						if (!myEmailId.isEmpty()) {
//							mail.sendMimeMail(myEmailId, "You are successfully registered In e-Bhawan",
//									"Dear " + forgetPassword.getMobile()
//											+ "<br> You are successfully registered In OBMS"
//											+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
//																					// Email
//						}
//					}
//				} catch (Exception e) {
//					logger.error("error occured in sms or email send..");
//				}
				try {
					String templateID = "1107160707714316860";
					smsSend.sendSMS(forgetPassword.getMobile(),
							"You are Successfully register in OBMS, Odisha Home Dept", templateID, "singleSMS");
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}
			}
		}
		if (status == true) {
			redirectAttributes.addFlashAttribute("regstatus", true);
			model = new ModelAndView("redirect:/login.html?login=guest");
		} else {
			redirectAttributes.addFlashAttribute("regstatus", false);
			model = new ModelAndView("redirect:/login.html");
		}

		logger.info("Exit---> " + this.getClass() + " method :  " + methodName);
		return model;
	}

	@RequestMapping(value = "/guestUpload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> guestUpload(@ModelAttribute PasswordResetModel forgetPassword,
			BindingResult bresult, Principal principal) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "guestUpload";
		logger.info("Entry---> " + this.getClass() + " method :  " + forgetPassword.toString());
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		System.out.println("Inside GuestUpload_________" + forgetPassword);
		String userId = principal.getName();
		System.out.println("userId ======================" + userId);
		forgetPassword.setMobile(userId);
		forgetPassword.setUserId(userId);
		System.out.println("forgetPassword"+forgetPassword.toString());
		String path = null;
		path = accessControlService.uploadDocument(forgetPassword);
		if (path.equalsIgnoreCase("failure")) {
			resp.put("message", "Failed to upload file or File Format is not correct.");
			resp.put("status", false);
		} else {
			forgetPassword.setDocType(forgetPassword.getDocType());
			forgetPassword.setDocumentpath(
					path.contains("\\") ? path.substring(path.indexOf("\\")) : path.substring(path.indexOf("/")));
			System.out.println("User Type =======" + forgetPassword.getUserType());
			if (forgetPassword.getUserType().equalsIgnoreCase("GUEST")) {
				boolean status = accessControlService.updateUserDetail(forgetPassword);
				resp.put("message", "Successfully Updated");
				resp.put("status", status);
			} else {
				boolean userModel = accessControlService.updateAllUserDetails(forgetPassword);
				if (userModel) {
					userModel = accessControlService.updateMasterGuestDetails(forgetPassword);
					if (!userModel) {
						userModel = accessControlService.saveMasterGuestDetailss(forgetPassword);
					}
				} else {
					resp.put("message", "Not Updated");
					resp.put("status", userModel);
				}
				resp.put("message", "Successfully Updated");
				resp.put("status", userModel);
			}
		}
		return resp;
	}

	@RequestMapping(value = { "/userProfile" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getEmailDetails(HttpServletRequest request, HttpServletResponse response,
			Principal principal,HttpSession session) {
		String userId = principal.getName();
		logger.debug("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		Map<String, Object> groupIdLocationId=(Map<String, Object>)session.getAttribute("groupIdLocationId");
		String groupName= (String) groupIdLocationId.get("group_name");
		System.out.println("groupName"+groupName);
		UserModel lstUserDetails = accessControlService.getUserByUserId(userId,groupName);
		if (lstUserDetails != null) {
			lstUserDetails = accessControlService.getUserMasterData(lstUserDetails);
		}
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);

		return resp;
	}

	@RequestMapping(value = { "/updateProfile" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateProfile(@Valid @ModelAttribute PasswordResetModel forgetPassword,
			BindingResult bresult, HttpSession session) throws JSONException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "updateProfile";
		System.out.println("forgetPassword"+forgetPassword.toString());
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		// Validate dob field 
	    if (!Utils.isValidDob(forgetPassword.getDob())) {
	    	System.out.println("+++++Inside IsValidDob +++++++"+forgetPassword.getDob());
	        resp.put("message", "Date of Birth cannot be in the future and must be at least 100 years ago.");
	        resp.put("status", false);
	        return resp;
	    }
		if (bresult.hasErrors()) {
			logger.info("Inside::Server Side Validation @@ hasErrors");
			logger.info("error occured" + bresult.toString());
			/*
			 * resp.put("status", false); resp.put("message", "Invalid Request.");
			 */
			return (Map<String, Object>) new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		} else {
			logger.info("Requested userId :" + forgetPassword.getUserId());
			JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getUserId(),
					forgetPassword.getUserType());
			logger.info("existance status of userid and emailId of a user:" + resObj.toString());
			if (resObj.get("status") == "success") {
				String path = accessControlService.uploadDocument(forgetPassword);
				if (path != null ? path.equals("failure") : false) {
					resp.put("message", "Failed to upload file or File Format is not correct");
					resp.put("status", false);
				} else {
					boolean status = false;
					// JSONObject resValid =
					// accessControlService.validateUid(forgetPassword.getUserId());
					// logger.info("existance status of userid and emailId of a
					// user:"+resObj.toString());
					// if(resValid.get("status") == "failure") {
					UserModel user = new UserModel();
					user.setUserId(forgetPassword.getUserId());
					user.setFirstName(forgetPassword.getName());
					user.setEmail(forgetPassword.getEmail());
					user.setCreatedBy(forgetPassword.getUserId());
					user.setContactNo(forgetPassword.getMobile());
					user.setDocType(forgetPassword.getDocType());
					user.setUserType(forgetPassword.getUserType());
					System.out.println("LIST"+user.toString());
					if (path != null) {
						user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\"))
								: path.substring(path.indexOf("/")));
					}
					status = accessControlService.updateGuestDetails(user, true);
					/*
					 * }else { resp.put("message","otp based login user can not update profile");
					 * resp.put("status",false); }
					 */
					if (status == true) {
						status = accessControlService.updateMasterGuestDetails(forgetPassword);
						
						 if(!status) { 
							 forgetPassword.setMobile(forgetPassword.getUserId()); 
						 status =accessControlService.saveMasterGuestDetails(forgetPassword); 
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
	public void mediaDownload(HttpServletResponse response,HttpServletRequest request, @PathVariable("imageCode") String imageCode,
			Principal principal, HttpSession session) throws IOException {
		logger.debug("Inside /imagedownload/{imageCode}");
		accessControlService.mediaDownloadService(response,request, imageCode, principal, session);
	}

	@RequestMapping(value = { "/getAllLocation" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllLocationAccordingToRole(HttpServletRequest req,
			HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("getAllLocation: ");

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = guestService.getAllLocation();
		resp.put("data", lstLocation);

		return resp;
	}

	@RequestMapping(value = { "/getAllProcess" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllProcess(@RequestBody AllLocationModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("getAllProcess: ");

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = guestService.getAllProcess(request);
		resp.put("data", lstLocation);
		return resp;
	}

	@RequestMapping(value = { "/savefeedbackDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveFeedback(@RequestBody FeedbackRequestModel request,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("saveFeedback: " + request.toString());
		logger.info("Inside:: @@ hasNotErrors" + request.toString());
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
		System.out.println("inside captcha validation   "+request.getCaptcha());
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("validatecaptch: " + req.toString());
		logger.info("Inside:: @@ hasNotErrors" + req.toString());
		logger.info("Inside captcha ====================");
		String reqCaptchaVal = request.getCaptcha();
		logger.info("Inside captcha ====================" + reqCaptchaVal);
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		logger.info("Inside captcha sessCaptchaVal ====================" + sessCaptchaVal);

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
		logger.info(forgetPassword.toString());
		String methodName = "savePassword";
		ModelAndView model = new ModelAndView();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("Entry---> " + this.getClass() + " method :  " + methodName);
		logger.info("Inside captcha ====================");
		String reqCaptchaVal = req.getParameter("captcha");
		String sessCaptchaVal = (String) session.getAttribute(CAPTCHA_KEY);
		session.removeAttribute(CAPTCHA_KEY);

		if (!StringUtils.equals(reqCaptchaVal, sessCaptchaVal)) {
			model.addObject("errCaptcha", true);
			model.addObject("mobile", forgetPassword.getMobile());
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
		String desg = null;
		if (resObj.get("status") == "success") {
			mobile = resObj.get("mobile_no").toString();
			reqId = resObj.get("req_id").toString();
			myEmailId = resObj.get("email").toString();
			name = resObj.get("Name").toString();
			desg = resObj.get("desg").toString();

			// JSONObject resObj2=accessControlService.validateforFeedbackforDt(reqId,date);

			bookingResponseModel = guestService.getFeedback(reqId, mobile);
			logger.info("bookingResponseModel" + bookingResponseModel.toString());
			System.out.println("bookingResponseModel" + bookingResponseModel.toString());
			if (bookingResponseModel.isEmpty()) {
				model.addObject("status", false);
				model.addObject("mobile", forgetPassword.getMobile());
				model.addObject("msg", "You have already submitted the feedback");
				model.setViewName("qrCode");
				return model;
			}
		} else {
			model.addObject("status", false);
			model.addObject("msg", "Bad Credentials!");
			model.addObject("mobile", forgetPassword.getMobile());
			model.setViewName("qrCode");
			return model;
		}

		String otp = otpStore.storeOtp(forgetPassword.getMobile());
		logger.info("OTP:" + otp);
		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For feedback", "Dear " + forgetPassword.getMobile()
//							+ "<br> Your Otp for forget password is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
//																													// is
//																													// send
//																													// to
//																													// the
//																													// corresponding
//																													// Email
//				}
//			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send.." + e.getMessage());
		}
		try {

			String templateID = "1077090220133834097";
			smsSend.sendSMS(forgetPassword.getMobile(), "OTP for Portal Login " + otp + ". For details please login to the portal. Home Dept., Govt. of Odisha",
					templateID, "sendOTPSMS");
		} catch (Exception e) {
			logger.error("error occured in sms or email send..");
		}

		// save the randomcaptca in the data base
		logger.info("mobile" + mobile);
		if (otp != null) {
			model.addObject("mobile", mobile);
			model.addObject("Name", name);
			model.addObject("requestId", reqId);
			model.addObject("desg",desg);
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
		logger.info("details:" + pwdAttr.toString());
		System.out.println("value  "+pwdAttr.toString()); 
		
		// this object will be used for returning the page
		ModelAndView model = new ModelAndView();
		if (validationResult.hasErrors()) {
			System.err.println(validationResult.getAllErrors());
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("mobile", pwdAttr.getUserId());
			model.addObject("Name", pwdAttr.getName());
			model.addObject("requestId", pwdAttr.getReqId());
			model.addObject("desg",pwdAttr.getDesg());
			model.setViewName("qrCodekt");
			return model;
		}
		logger.info("valid inputs..");
		logger.info("userId" + pwdAttr.getUserId() + "and otp" + pwdAttr.getOtp());
		if (!otpStore.isOtpValid(pwdAttr.getUserId(), pwdAttr.getOtp())) {

			model.addObject("invalidOtp", true);
			model.addObject("userId", pwdAttr.getUserId());
			model.addObject("mobile", pwdAttr.getUserId());
			model.addObject("Name", pwdAttr.getName());
			model.addObject("requestId", pwdAttr.getReqId());
			model.addObject("desg",pwdAttr.getDesg());
			model.setViewName("qrCodekt");
			return model;
		}
		logger.info("valid otp..");
		System.err.println("data: " + pwdAttr);
		model = new ModelAndView(
				"redirect:/mobilefeedback.html?requestid=" + pwdAttr.getReqId() + "&mobileno=" + pwdAttr.getUserId());
		return model;
	}// end

	
	// Not used in current requirement

	/******************************************************************************************************
	 * Generate Otp
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws MessagingException
	 **********************************************************************************************/
	/*
	 * @RequestMapping(value={"/forgetPassword"}, method = RequestMethod.POST)
	 * public @ResponseBody Map<String, Object> fogetPassword(@Valid @RequestBody
	 * ForgetPasswordModel forgetPassword, BindingResult bresult, HttpSession
	 * session) throws JSONException, IOException { Map<String, Object> resp = new
	 * HashMap<String, Object>(); String methodName = "forgetPassword";
	 * logger.info("Entry---> "+this.getClass()+" method :  "+ methodName);
	 * if(bresult.hasErrors()){
	 * logger.info("Inside::Server Side Validation @@ hasErrors");
	 * logger.info("error occured" +bresult.toString()); resp.put("status", false);
	 * resp.put("msg", "Invalid Request.");
	 * 
	 * }else{ logger.info("Requested userId :"+forgetPassword.getUserId());
	 * JSONObject resObj =
	 * accessControlService.validateUidAndEmail(forgetPassword.getUserId());
	 * logger.info("existance status of userid and emailId of a user:"+resObj.
	 * toString()); if(resObj.get("status") == "success") { String myEmailId =
	 * resObj.get("email").toString(); String otp =
	 * otpStore.storeOtp(forgetPassword.getUserId()); logger.info("OTP:"+otp); try {
	 * if(myEmailId != null) { if(!myEmailId.isEmpty()) {
	 * mail.sendMimeMail(myEmailId,"OTP For Portal Login","Dear " +
	 * forgetPassword.getUserId() + "<br> Your Otp for forget password is " + otp +
	 * "<br><br> Thanks <br>e-Bhawan Team");//mail is send to the corresponding
	 * Email } } } catch (Exception e) {
	 * logger.error("error occured in sms or email send.." + e.getMessage()); } try
	 * { String templateID="1107160707717945638";
	 * smsSend.sendSMS(forgetPassword.getUserId(), "OTP For Forget Password " +
	 * otp+", Odisha Home Dept",templateID); } catch (Exception e) {
	 * logger.error("Error occured in sms " + e.getMessage()); } //save the
	 * randomcaptca in the data base
	 * 
	 * if(otp != null) {
	 * resp.put("message","Successfully OTP sent to your Mobile or Email Id");
	 * resp.put("status",true); }else { resp.put("message","Invalid Credentials");
	 * resp.put("status",false); } }else {
	 * resp.put("message","You have Not a Registered User ");
	 * resp.put("status",false); } }
	 * 
	 * logger.info("Exit---> "+this.getClass()+" method :  "+ methodName); return
	 * resp; }
	 * 
	 *//********************************************
		 * Method is taking the input UserId and it will create a one time password then
		 * it will check in otp table wheather the userid has any otp previously if it
		 * found then it will update a new otp and send to the mail if any otp is not
		 * found to this userid then it will insert a new row for this user in otp table
		 * and send to the mail
		 * 
		 * @return
		 * @throws MessagingException
		 *******************************************/

	/*
	 * @RequestMapping(value={"/resendOtp"}, method = RequestMethod.POST)
	 * public @ResponseBody Map<String, Object> resendOtp(@Valid @RequestBody
	 * ForgetPasswordModel forgetPassword, BindingResult bresult, HttpSession
	 * session) throws JSONException, IOException, MessagingException { String
	 * methodName = "resendOtp"; Map<String, Object> resp = new HashMap<String,
	 * Object>(); logger.info("Entry---> "+this.getClass()+" method :  "+
	 * methodName);
	 * 
	 * JSONObject resObj =
	 * accessControlService.validateUidAndEmail(forgetPassword.getUserId());
	 * logger.info("existance status of userid and emailId of a user:"+resObj.
	 * toString()); if(resObj.get("status") == "success") { String myEmailId =
	 * resObj.get("email").toString(); String otp =
	 * otpStore.storeOtp(forgetPassword.getUserId()); logger.info("OTP:"+otp); try {
	 * if(myEmailId != null) { if(!myEmailId.isEmpty()) {
	 * mail.sendMimeMail(myEmailId,"ReSend OTP For Portal Login","Dear " +
	 * forgetPassword.getUserId() + "<br> Your Re send Otp for forget password is "
	 * + otp + "<br><br> Thanks <br>e-Bhawan Team");//mail is send to the
	 * corresponding Email } } } catch (Exception e) {
	 * logger.error("error occured in sms or email send.."); } try { String
	 * templateID="1107161251118912084";
	 * 
	 * smsSend.sendSMS(forgetPassword.getUserId(), "OTP For Portal Login " +
	 * otp+", Odisha Home Dept",templateID); } catch (Exception e) {
	 * logger.error("Error occured in sms " + e.getMessage()); }
	 * 
	 * if(otp != null) {
	 * resp.put("message","Successfully OTP sent to your Mobile or Email Id");
	 * resp.put("status",true); }else { resp.put("message","Invalid Credentials");
	 * resp.put("status",false); } }else {
	 * resp.put("message","Invalid Credentials"); resp.put("status",false); }
	 * logger.info("Exit---> "+this.getClass()+" method :  "+ methodName); return
	 * resp; }//End of resendOtp
	 * 
	 *//******************************************************************************************************
		 * Generate Otp Based Login
		 * 
		 * @author jayant
		 * @throws IOException
		 * @throws JSONException
		 * @throws MessagingException
		 **********************************************************************************************//*
																										 * @RequestMapping
																										 * (value={
																										 * "/otpForLogin"
																										 * }, method =
																										 * RequestMethod
																										 * .POST)
																										 * public @ResponseBody
																										 * Map<String,
																										 * Object>
																										 * otpForLogin(@
																										 * ModelAttribute
																										 * ForgetPasswordModel1
																										 * forgetPassword,
																										 * BindingResult
																										 * bresult,
																										 * HttpSession
																										 * session)
																										 * throws
																										 * JSONException,
																										 * IOException {
																										 * Map<String,
																										 * Object> resp
																										 * = new
																										 * HashMap<
																										 * String,
																										 * Object>();
																										 * String
																										 * methodName =
																										 * "otpForLogin";
																										 * logger.info(
																										 * forgetPassword
																										 * ); logger.
																										 * info("Entry---> "
																										 * +this.
																										 * getClass()
																										 * +" method :  "
																										 * +
																										 * methodName);
																										 * logger.info(
																										 * forgetPassword
																										 * );
																										 * if(bresult.
																										 * hasErrors()){
																										 * logger.
																										 * info("Inside::Server Side Validation @@ hasErrors"
																										 * ); logger.
																										 * info("error occured"
																										 * +bresult.
																										 * toString());
																										 * resp.put(
																										 * "status",
																										 * false);
																										 * resp.put(
																										 * "msg",
																										 * "Invalid Request."
																										 * ); }else{
																										 * logger.
																										 * info("Requested userId :"
																										 * +forgetPassword
																										 * .
																										 * getJ_username
																										 * ());
																										 * JSONObject
																										 * resObj =
																										 * accessControlService
																										 * .
																										 * validateUidAndEmail
																										 * (
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ()); logger.
																										 * info("existance status of userid and emailId of a user:"
																										 * +resObj.
																										 * toString());
																										 * if(resObj.get
																										 * ("status") ==
																										 * "success") {
																										 * resp.put(
																										 * "message","You are registered user so you can login through mobile number and password"
																										 * ); resp.put(
																										 * "status",
																										 * false); }else
																										 * { String path
																										 * =
																										 * accessControlService
																										 * .
																										 * uploadDocument1
																										 * (
																										 * forgetPassword
																										 * );
																										 * logger.info(
																										 * path);
																										 * if(path ==
																										 * null ? true :
																										 * path.equals(
																										 * "failure")) {
																										 * resp.put(
																										 * "message","File Format is not correct"
																										 * ); resp.put(
																										 * "status",
																										 * false); }else
																										 * { JSONObject
																										 * resValid =
																										 * accessControlService
																										 * .validateUid(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ()); logger.
																										 * info("existance status of userid and emailId of a user:"
																										 * +resValid.
																										 * toString());
																										 * String
																										 * captchaCode =
																										 * Utils.
																										 * generateCaptchaTextMethod1
																										 * ();//
																										 * generating a
																										 * captcha
																										 * code/random
																										 * number
																										 * logger.
																										 * info("otp :"
																										 * +captchaCode)
																										 * ; boolean
																										 * status =
																										 * false;
																										 * if(resValid.
																										 * get("status")
																										 * == "failure")
																										 * { UserModel
																										 * user = new
																										 * UserModel();
																										 * user.
																										 * setActive(
																										 * true); user.
																										 * setUserId(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ());
																										 * user.setAdmin
																										 * (false);
																										 * user.
																										 * setFirstLogin
																										 * (true); user.
																										 * setPassword(
																										 * sha512.SHA512
																										 * (captchaCode.
																										 * trim()));
																										 * user.
																										 * setCreatedBy(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ()); user.
																										 * setCreatedDate
																										 * (new Date());
																										 * user.
																										 * setContactNo(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ()); user.
																										 * setOtpLogin(
																										 * true); user.
																										 * setUserType(
																										 * "GUEST");
																										 * if(path !=
																										 * null) { user.
																										 * setDocType(
																										 * forgetPassword
																										 * .getDocType()
																										 * ); user.
																										 * setDocPath(
																										 * path.
																										 * contains("\\") ? path.substring(path.indexOf("
																										 * \\")) : path.
																										 * substring(
																										 * path.indexOf(
																										 * "/"))); }
																										 * status =
																										 * accessControlService
																										 * .
																										 * saveUserDetails
																										 * (user);
																										 * if(status) {
																										 * UserGroupHasRoles
																										 * userGroup =
																										 * new
																										 * UserGroupHasRoles
																										 * ();
																										 * userGroup.
																										 * setUserId(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ());
																										 * userGroup.
																										 * setGroup_id(2
																										 * );
																										 * accessControlService
																										 * .
																										 * saveUserGroup
																										 * (userGroup);
																										 * }
																										 * 
																										 * try {
																										 * 
																										 * String
																										 * templateID=
																										 * "1107161251118912084";
																										 * smsSend.
																										 * sendSMS(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * (),
																										 * "OTP For Portal Login "
																										 * +
																										 * captchaCode+", Odisha Home Dept"
																										 * ,templateID);
																										 * } catch
																										 * (Exception e)
																										 * { logger.
																										 * error("error occured in sms or email send.."
																										 * ); } }else {
																										 * UserModel
																										 * user = new
																										 * UserModel();
																										 * user.
																										 * setUserId(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * ()); user.
																										 * setPassword(
																										 * sha512.SHA512
																										 * (captchaCode.
																										 * trim()));
																										 * if(path !=
																										 * null) { user.
																										 * setDocType(
																										 * forgetPassword
																										 * .getDocType()
																										 * ); user.
																										 * setDocPath(
																										 * path.
																										 * contains("\\") ? path.substring(path.indexOf("
																										 * \\")) : path.
																										 * substring(
																										 * path.indexOf(
																										 * "/"))); }
																										 * status =
																										 * accessControlService
																										 * .
																										 * updateUserDetailsOtp
																										 * (user); try {
																										 * 
																										 * String
																										 * templateID=
																										 * "1107161251118912084";
																										 * smsSend.
																										 * sendSMS(
																										 * forgetPassword
																										 * .
																										 * getJ_username
																										 * (),
																										 * "OTP For Portal Login "
																										 * +
																										 * captchaCode+", Odisha Home Dept"
																										 * ,templateID);
																										 * } catch
																										 * (Exception e)
																										 * { logger.
																										 * error("error occured in sms or email send.."
																										 * ); } } //save
																										 * the
																										 * randomcaptca
																										 * in the data
																										 * base
																										 * if(status ==
																										 * true) {
																										 * resp.put(
																										 * "message","Successfully OTP sent to your Mobile"
																										 * ); resp.put(
																										 * "status",true
																										 * ); }else {
																										 * resp.put(
																										 * "message","Invalid Credentials"
																										 * ); resp.put(
																										 * "status",
																										 * false); } } }
																										 * } logger.
																										 * info("Exit---> "
																										 * +this.
																										 * getClass()
																										 * +" method :  "
																										 * +
																										 * methodName);
																										 * return resp;
																										 * }//end otp
																										 * for login
																										 * 
																										 * 
																										 * @RequestMapping
																										 * (value={
																										 * "/generateOtp"
																										 * }, method =
																										 * RequestMethod
																										 * .POST)
																										 * public @ResponseBody
																										 * Map<String,
																										 * Object>
																										 * generateOtp(@
																										 * Valid @RequestBody
																										 * UserRegistrationModel
																										 * forgetPassword,
																										 * BindingResult
																										 * bresult,
																										 * HttpSession
																										 * session)
																										 * throws
																										 * JSONException,
																										 * IOException {
																										 * Map<String,
																										 * Object> resp
																										 * = new
																										 * HashMap<
																										 * String,
																										 * Object>();
																										 * String
																										 * methodName =
																										 * "generateOtp";
																										 * logger.
																										 * info("Entry---> "
																										 * +this.
																										 * getClass()
																										 * +" method :  "
																										 * +
																										 * methodName);
																										 * if(bresult.
																										 * hasErrors()){
																										 * logger.
																										 * info("Inside::Server Side Validation @@ hasErrors"
																										 * ); logger.
																										 * info("error occured"
																										 * +bresult.
																										 * toString());
																										 * resp.put(
																										 * "status",
																										 * false);
																										 * resp.put(
																										 * "msg",
																										 * "Invalid Request."
																										 * ); }else{
																										 * logger.
																										 * info("Requested userId :"
																										 * +forgetPassword
																										 * .getUserId())
																										 * ; JSONObject
																										 * resObj =
																										 * accessControlService
																										 * .
																										 * validateUidAndEmail
																										 * (
																										 * forgetPassword
																										 * .getUserId())
																										 * ; logger.
																										 * info("existance status of userid and emailId of a user:"
																										 * +resObj.
																										 * toString());
																										 * if(resObj.get
																										 * ("status") ==
																										 * "success") {
																										 * resp.put(
																										 * "message","You are already registered"
																										 * ); resp.put(
																										 * "status",
																										 * false); }else
																										 * { String
																										 * myEmailId =
																										 * forgetPassword
																										 * .getEmail();
																										 * String otp =
																										 * otpStore.
																										 * storeOtp(
																										 * forgetPassword
																										 * .getUserId())
																										 * ;
																										 * logger.info(
																										 * "OTP:"+otp);
																										 * try {
																										 * if(myEmailId
																										 * != null) {
																										 * if(!myEmailId
																										 * .isEmpty()) {
																										 * mail.
																										 * sendMimeMail(
																										 * myEmailId,"OTP For e-Bhawan Login"
																										 * ,"Dear " +
																										 * forgetPassword
																										 * .getUserId()
																										 * +
																										 * "<br> Your Otp for OBMS registration is "
																										 * + otp +
																										 * "<br><br> Thanks <br>e-Bhawan Team"
																										 * );//mail is
																										 * send to the
																										 * corresponding
																										 * Email } } }
																										 * catch
																										 * (Exception e)
																										 * { logger.
																										 * error("error occured in sms or email send.."
																										 * ); } try {
																										 * String
																										 * templateID=
																										 * "1107160707698779534";
																										 * smsSend.
																										 * sendSMS(
																										 * forgetPassword
																										 * .getUserId(),
																										 * "OTP For OBMS Registration "
																										 * + otp+
																										 * ", Odisha Home Dept"
																										 * ,templateID);
																										 * } catch
																										 * (Exception e)
																										 * { logger.
																										 * error("Error occured in sms "
																										 * +
																										 * e.getMessage(
																										 * )); } //save
																										 * the
																										 * randomcaptca
																										 * in the data
																										 * base boolean
																										 * status =
																										 * accessControlService
																										 * .saveOtpInDb(
																										 * captchaCode,
																										 * forgetPassword
																										 * .getUserId())
																										 * ; if(otp !=
																										 * null) {
																										 * resp.put(
																										 * "message","Successfully OTP sent to your Mobile or Email Id"
																										 * ); resp.put(
																										 * "status",true
																										 * ); }else {
																										 * resp.put(
																										 * "message","Invalid Credentials"
																										 * ); resp.put(
																										 * "status",
																										 * false); } } }
																										 * 
																										 * logger.
																										 * info("Exit---> "
																										 * +this.
																										 * getClass()
																										 * +" method :  "
																										 * +
																										 * methodName);
																										 * return resp;
																										 * }
																										 */
	@RequestMapping(value = { "/mobileLogin" }, method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> authenticate(@RequestBody MobileLoginPojo loginPojo,
			HttpServletRequest req) throws Exception {
		System.out.println("inside login controller");
		UserModel domainUser = new UserModel();
		Boolean authentication = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		String role;
//		responseHeaders.set("Access-Control-Allow-Origin", "*");
//		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//		responseHeaders.set("Access-Control-Max-Age", "3600");
//		responseHeaders.set("Access-Control-Allow-Headers", "Authorization, Content-Type");
//		responseHeaders.set("Access-Control-Expose-Headers", "xsrf-token,Authorization");
//		responseHeaders.set("Access-Control-Allow-Origin", "*"); //Origin http://localhost:4200
//		responseHeaders.set("Access-Control-Allow-Credentials", "true");
//		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");//"GET, POST, PUT, DELETE"
//		responseHeaders.set("Access-Control-Max-Age", "1800");
//      //response.setHeader("Access-Control-Allow-Headers", "authorization, content-type,captchaKey, xsrf-token, rgid, caseno,Set-Cookie,Origin");
//		responseHeaders.set("Access-Control-Allow-Headers", "proxyId,X-Requested-With,captchaKey,content-type, Authorization,caseno,Set-Cookie,Origin");
//		responseHeaders.add("Access-Control-Expose-Headers", "xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin");
		try {
			CaptchaModel cm = null;

			try {
				System.out.println("req"+req);
				cm = CaptchaUtils.setCaptchaModelClassfromString(Sha512.decrypt(req.getHeader("captchaKey")));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("cm" + cm);
			if (CaptchaUtils.checkCaptchaValidity(req)) {
				System.out.println("this is working");
				String decodedCaptcha = Sha512.decrypt(cm.getCaptchaKey());
				// String expireTime = req.getHeader("captchaKeyObj.expireTime");

				String actualCaptcha = decodedCaptcha.substring(0, decodedCaptcha.indexOf("#"));

				if (loginPojo.getCaptcha() == null || loginPojo.getCaptcha().equals("")) {
					logger.warn("Captcha should not be blank.");
					authentication = false;
					// return new ResponseEntity<>(new StatusResponse(0, "Captcha should not be
					// blank."), HttpStatus.OK);
					return ResponseEntity.ok().headers(responseHeaders)
							.body(new JwtTokenResponse(null, "Bearer", "Captcha should not be blank", 1));
				}
				if (loginPojo.getCaptcha().equals("")) {
					logger.warn("Captcha should not be blank.");
					authentication = false;
					// return new ResponseEntity<>(new StatusResponse(0, "Captcha should not be
					// blank."), HttpStatus.OK);
					return ResponseEntity.ok().headers(responseHeaders)
							.body(new JwtTokenResponse(null, "Bearer", "Captcha should not be blank", 0));
				}

				if (!loginPojo.getCaptcha().equals(actualCaptcha)) {
					logger.warn("Invalid Captcha.");
					authentication = false;
					// return new ResponseEntity<>(new StatusResponse(0, "Invalid Captcha"),
					// HttpStatus.OK);
					return ResponseEntity.ok().headers(responseHeaders)
							.body(new JwtTokenResponse(null, "Bearer", "Invalid Captcha", 0));

				}
			} else {
				// return new ResponseEntity<>(new StatusResponse(0, "Invalid Captcha"),
				// HttpStatus.OK);
				return ResponseEntity.ok().headers(responseHeaders)
						.body(new JwtTokenResponse(null, "Bearer", "Invalid Captcha", 0));
			}

			try {
				domainUser = acService.getUserByUserId(loginPojo.getUserId(), loginPojo.getUserType());
				System.out.println("domainUser"+domainUser);
				if (domainUser == null) {
					authentication = false;
					// return new ResponseEntity<>(new StatusResponse(0, "User Not Found"),
					// HttpStatus.OK);
					return ResponseEntity.ok().headers(responseHeaders)
							.body(new JwtTokenResponse(null, "Bearer", "User Not Found", 0));
				}
				if (!loginPojo.getPassword()
						.equals(sha512.SHA512(domainUser.getPassword() + "#" + loginPojo.getCaptcha()))) {
					System.out.println();
					authentication = false;
					// return new ResponseEntity<>(new StatusResponse(0, "Wrong password"),
					// HttpStatus.OK);
					return ResponseEntity.ok().headers(responseHeaders)
							.body(new JwtTokenResponse(null, "Bearer", "Wrong password", 0));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			authentication = false;
			e.printStackTrace();
		}
		// Generate JWT Token and send in response header
		final Key key = keyGenerator.generateKey();
//		Claims claims = Jwts.claims();
//		claims.put("organization", "STL");
//		claims.put("email", "santanusritamstl@gmail.com");
//		claims.put("user", domainUser);
		Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("role", domainUser.getUserType());
        customClaims.put("user", domainUser);
        role = acService.getRoleByUserType(domainUser.getUserType());        
        JSONObject mappedCityLocsByUser = accessControlService
				.getAllMappedCityLocByUser(domainUser.getUserId(), role);
		List<Map<String, Object>> location;
		try {
			location = Utils.getCityIdListFromJsonArrayString(mappedCityLocsByUser.get("data").toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting location list from json array string");
		}
        customClaims.put("location", location);
//		final String jwtToken = Jwts.builder().setClaims(claims).setSubject("SocietyAdminUser")
//				.setIssuer(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()).setIssuedAt(new Date())
//				.setExpiration(Date.from(LocalDateTime.now().plusMinutes(Long.parseLong(jwtvalidity))
//						.atZone(ZoneId.systemDefault()).toInstant()))
//				.signWith(SignatureAlgorithm.HS512, key).compact();
		String accessToken = jwtUtil.generateAccessToken(domainUser, customClaims);

		//responseHeaders.set(AUTHORIZATION, "Bearer " + jwtToken);
		responseHeaders.set("Access-Control-Allow-Headers", "Authorization, Content-Type");
		responseHeaders.set("Access-Control-Expose-Headers", "xsrf-token,Authorization");
//		responseHeaders.set("Access-Control-Allow-Origin", "*"); // Origin http://localhost:4200
//		responseHeaders.set("Access-Control-Allow-Credentials", "true");
		responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");// "GET, POST, PUT,
//																								// DELETE"
//		responseHeaders.set("Access-Control-Max-Age", "1800");
//		// response.setHeader("Access-Control-Allow-Headers", "authorization,
//		// content-type,captchaKey, xsrf-token, rgid, caseno,Set-Cookie,Origin");
//		responseHeaders.set("Access-Control-Allow-Headers",
//				"proxyId,X-Requested-With,captchaKey,content-type, Authorization,caseno,Set-Cookie,Origin");
//		responseHeaders.add("Access-Control-Expose-Headers",
//				"xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin");

		// return ResponseEntity.ok().headers(responseHeaders).body(new
		// JwtTokenResponse(jwtToken, "Bearer"));
		System.out.println("authentication"+authentication);
		if (authentication) {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(accessToken, "Bearer", "Login Successful", 1,domainUser.isUpload_doc()));
		} else {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "Bearer", "Login Failed", 0));
		}

	}

	@RequestMapping(value = { "/getCaptchaForMobile" }, method = RequestMethod.GET)
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "height", required = false) String h,
			@RequestParam(value = "width", required = false) String w,
			@RequestParam(value = "status", required = false) String status) throws IOException {
		int cHeight = 45;
		int cWidth = 150;
		if (!StringUtils.isEmpty(h) && !StringUtils.isEmpty(w)) {
			cHeight = Integer.parseInt(h);
			cWidth = Integer.parseInt(w);
		}
		//response.setHeader("Access-Control-Allow-Origin", "*"); // Origin http://localhost:4200
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.70:3001");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");// "GET, POST, PUT,
																								// DELETE"
		response.setHeader("Access-Control-Max-Age", "1800");
		// response.setHeader("Access-Control-Allow-Headers", "authorization,
		// content-type,captchaKey, xsrf-token, rgid, caseno,Set-Cookie,Origin");
		response.setHeader("Access-Control-Allow-Headers",
				"proxyId,X-Requested-With,captchaKey,content-type, Authorization,caseno,Set-Cookie,Origin");
		response.addHeader("Access-Control-Expose-Headers",
				"xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin");

		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		response.setContentType("image/jpeg");
		Random r = new Random();
		int f = System.currentTimeMillis() > serialVersionUID ? 1 : 0;
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		String ch = token.substring(0, CAPTCHA_LENGTH);// no of characters in captcha image
		Color[] color = { Color.RED, Color.BLUE, new Color(0.6662f, 0.4569f, 0.3232f), Color.BLACK, Color.LIGHT_GRAY,
				Color.YELLOW, Color.LIGHT_GRAY, Color.cyan, Color.GREEN, Color.black, Color.DARK_GRAY, Color.MAGENTA };
		BufferedImage image = new BufferedImage(cWidth, cHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		graphics2D.setColor(color[4]); // or the background color u want
		graphics2D.fillRect(0, 0, cWidth, cHeight);
		Color c = new Color(0.662f, 0.469f, 0.232f);
		GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.black, true);
		graphics2D.setPaint(gp);
		Font font = new Font("Verdana", Font.CENTER_BASELINE, 20);
		FontMetrics metrics = graphics2D.getFontMetrics(font);
		int x = (cWidth - metrics.stringWidth(ch)) / 2;
		int y = ((cHeight - metrics.getHeight()) / 2) + metrics.getAscent();
		AttributedString as1 = new AttributedString(ch);
		as1.addAttribute(TextAttribute.FONT, font);
		as1.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, 0, CAPTCHA_LENGTH);
		graphics2D.drawString(as1.getIterator(), x, y);
		graphics2D.dispose();

		String encCaptcha = Sha512.encrypt(ch + "#" + captchaSalt);
		System.out.println("captchaSalt" + captchaSalt);
		OutputStream outputStream = response.getOutputStream();
		CaptchaModel captchaModel = new CaptchaModel();
		captchaModel.setCaptchaKey(encCaptcha);
		captchaModel.setExpireTime(new Date().getTime() + loginCaptchaValidity);
		captchaModel.setHostAddr(request.getRemoteAddr());
		System.out.println("captcha time" + captchaModel.getExpireTime());
		response.addHeader("captchaKey", Sha512.encryptClass(captchaModel));
		System.out.println("catchaKey" + Sha512.encryptClass(captchaModel));
		response.setHeader("Access-Control-Expose-Headers", "captchaKey");

		ImageIO.write(image, "jpeg", outputStream);
		outputStream.close();

	}

	@RequestMapping(value = "/mobileRegistration", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> registration(@RequestBody UserRegistrationModel regAttr,
			BindingResult validationResult, HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws IOException, JSONException {
		CaptchaModel cm = null;
		Boolean authentication = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		System.out.println("Register data +++++----------++++"+regAttr);
		try {
			cm = CaptchaUtils.setCaptchaModelClassfromString(Sha512.decrypt(req.getHeader("captchaKey")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("cm" + cm);
		if (CaptchaUtils.checkCaptchaValidity(req)) {
			System.out.println("this is working");
			String decodedCaptcha = Sha512.decrypt(cm.getCaptchaKey());
			// String expireTime = req.getHeader("captchaKeyObj.expireTime");

			String actualCaptcha = decodedCaptcha.substring(0, decodedCaptcha.indexOf("#"));

			if (regAttr.getCaptcha() == null || regAttr.getCaptcha().equals("")) {
				logger.warn("Captcha should not be blank.");
				authentication = false;
				// return new ResponseEntity<>(new StatusResponse(0, "Captcha should not be
				// blank."), HttpStatus.OK);
				return ResponseEntity.ok().headers(responseHeaders)
						.body(new JwtTokenResponse(null, "Bearer", "Captcha should not be blank", 1));
			}
			if (regAttr.getCaptcha().equals("")) {
				logger.warn("Captcha should not be blank.");
				authentication = false;
				// return new ResponseEntity<>(new StatusResponse(0, "Captcha should not be
				// blank."), HttpStatus.OK);
				return ResponseEntity.ok().headers(responseHeaders)
						.body(new JwtTokenResponse(null, "Bearer", "Captcha should not be blank", 0));
			}

			if (!regAttr.getCaptcha().equals(actualCaptcha)) {
				logger.warn("Invalid Captcha.");
				authentication = false;
				// return new ResponseEntity<>(new StatusResponse(0, "Invalid Captcha"),
				// HttpStatus.OK);
				return ResponseEntity.ok().headers(responseHeaders)
						.body(new JwtTokenResponse(null, "Bearer", "Invalid Captcha", 0));

			}
		}
		logger.info("New Registration request------------->" + regAttr.toString());

		System.err.println(regAttr.toString());
		logger.info("Requested userId :" + regAttr.getMobile());
		JSONObject resObj = accessControlService.validateUidAndEmail(regAttr.getMobile(), "GUEST");
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "User Alreday Exists", 0));
		}

		String myEmailId = regAttr.getEmail();
		String otp = otpStore.storeOtp(regAttr.getMobile());
		System.out.println("OTP:" + otp);
		// communicate otp to user
		// Send in sms
		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For e-Bhawan Login", "Dear " + regAttr.getMobile()
//							+ "<br> Your Otp for OBMS registration is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
//																														// is
//																														// send
//																														// to
//																														// the
//																														// corresponding
//																														// Email
//					System.out.println("Mail Sent successfully");
//				}
//			}
		} catch (Exception e) {
			authentication = false;
		}
		logger.error("error occured in sms or email send..");

		try {
			String templateID = "1077572430132285401";
			smsSend.sendSMS(regAttr.getMobile(), "OTP for OBMS Registration " + otp + ". For details please login to the portal. Home Dept., Govt. of Odisha", templateID, "sendOTPSMS");
		} catch (Exception e) {
			authentication = false;
			logger.error("Error occured in sms " + e.getMessage());
		}
		// Send in mail
		if (true) {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "OTP has been sent to your mobile no", 1));
		} else {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "Something went wrong", 0));
		}

	}

	@RequestMapping(value = "/createUserForMobile", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> createUserForMobile(
			@RequestBody PasswordResetModel forgetPassword, BindingResult bresult, HttpSession session,
			HttpServletRequest req, HttpServletResponse res, RedirectAttributes redirectAttributes)
			throws JSONException, IOException {
		// Map<String, Object> resp = new HashMap<String, Object>();
		String methodName = "createUserForMobile";
		String path = null;
		System.out.println("valid password format..");
		HttpHeaders responseHeaders = new HttpHeaders();
		;
		if (!forgetPassword.getPassword().equalsIgnoreCase(forgetPassword.getCpassword())) {

			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "password doesnot match", 0));
		}
		logger.info("Requested userId :" + forgetPassword.getUserId());

		if (!otpStore.isOtpValid(forgetPassword.getMobile(), forgetPassword.getOtp())) {
			return ResponseEntity.ok().headers(responseHeaders).body(new JwtTokenResponse(null, "", "Invalid OTP", 0));
		}
		forgetPassword.setUserType("GUEST");
		// path=forgetPassword.getDocumentpath();
		/* System.out.println("valid otp.."+path); */

		JSONObject resValid = accessControlService.validateUid(forgetPassword.getMobile(),forgetPassword.getUserType());
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
			System.out.println("LOGIN CONT Guest details************.."+user.toString());
			System.out.println("LOGIN CONT Guest USER************.."+user.getUserType());
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
//				if (myEmailId != null) {
//					if (!myEmailId.isEmpty()) {
//						mail.sendMimeMail(myEmailId, "You are successfully registered In OBMS",
//								"Dear " + forgetPassword.getMobile()
//										+ "<br> You are successfully registered In e-Bhawan"
//										+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
//																				// Email
//					}
//				}
				String templateID = "1107160707714316860";
				smsSend.sendSMS(forgetPassword.getMobile(), "You are Successfully register in OBMS, Odisha Home Dept",
						templateID, "singleSMS");
			} catch (Exception e) {
				return ResponseEntity.ok().headers(responseHeaders)
						.body(new JwtTokenResponse(null, "", "Some error occured", 0));
			}

		} else {
			UserModel user = new UserModel();
			user.setFirstName(forgetPassword.getName());
			user.setAdmin(false);
			user.setFirstLogin(true);
			user.setPassword(forgetPassword.getPassword());
			user.setEmail(forgetPassword.getEmail());
			user.setContactNo(forgetPassword.getMobile());
			user.setCreatedBy(forgetPassword.getMobile());
			user.setUserId(forgetPassword.getMobile());
			user.setOtpLogin(false);
			user.setUserType("GUEST");
			// user.setUpload_doc(false);
			/*
			 * if(path != null) { user.setDocType(forgetPassword.getDocType());
			 * user.setDocPath(path.contains("\\") ? path.substring(path.indexOf("\\")) :
			 * path.substring(path.indexOf("/"))); }
			 */
			status = accessControlService.updateGuestDetails(user, false);
			if (status) {
				status = accessControlService.saveMasterGuestDetails(forgetPassword);
//				status = accessControlService.updateUserDetailsOtp(user);
			}
			if (status) {
				String myEmailId = forgetPassword.getEmail();
				try {
//					if (myEmailId != null) {
//						if (!myEmailId.isEmpty()) {
//							mail.sendMimeMail(myEmailId, "You are successfully registered In e-Bhawan",
//									"Dear " + forgetPassword.getMobile()
//											+ "<br> You are successfully registered In OBMS"
//											+ "<br><br> Thanks <br>e-Bhawan Team");// mail is send to the corresponding
//																					// Email
//						}
//					}
				} catch (Exception e) {
					logger.error("error occured in sms or email send..");
				}
				try {
					String templateID = "1107160707714316860";
					smsSend.sendSMS(forgetPassword.getMobile(),
							"You are Successfully register in OBMS, Odisha Home Dept", templateID, "singleSMS");
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
					return ResponseEntity.ok().headers(responseHeaders)
							.body(new JwtTokenResponse(null, "", "Error occured in sms", 0));
				}
			}
		}
		if (status == true) {
			redirectAttributes.addFlashAttribute("regstatus", true);
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "Registration Successful", 1));
		} else {
			redirectAttributes.addFlashAttribute("regstatus", false);
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "Some error occured", 0));
		}

		// logger.info("Exit---> " + this.getClass() + " method : " + methodName);
		// return model;
	}

	// Mobile forgot password
	@RequestMapping(value = "/mobileresetPasswordRequest", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> mobileSavePassword(
			@RequestBody ChangePasswordWithoutAuthModel forgetPassword, RedirectAttributes redirectAttributes,
			BindingResult bresult, HttpSession session, HttpServletRequest req, HttpServletResponse res)
			throws JSONException, IOException, MessagingException {
		String methodName = "savePassword";
		ModelAndView model = new ModelAndView();
		Boolean authentication = true;

		CaptchaModel cm = CaptchaUtils.setCaptchaModelClassfromString(Sha512.decrypt(req.getHeader("captchaKey")));
		System.out.println("cm" + cm);
		if (CaptchaUtils.checkCaptchaValidity(req)) {
			System.out.println("this is working");
			String decodedCaptcha = Sha512.decrypt(cm.getCaptchaKey());
			// String expireTime = req.getHeader("captchaKeyObj.expireTime");

			String actualCaptcha = decodedCaptcha.substring(0, decodedCaptcha.indexOf("#"));

			if (forgetPassword.getCaptcha() == null || forgetPassword.getCaptcha().equals("")) {
				logger.warn("Captcha should not be blank.");
				authentication = false;
				return new ResponseEntity<>(new StatusResponse(0, "Captcha should not be blank."), HttpStatus.OK);
			}
			if (forgetPassword.getCaptcha().equals("")) {
				logger.warn("Captcha should not be blank.");
				authentication = false;
				return new ResponseEntity<>(new StatusResponse(0, "Captcha should not be blank."), HttpStatus.OK);
			}

			if (!forgetPassword.getCaptcha().equals(actualCaptcha)) {
				logger.warn("Invalid Captcha.");
				authentication = false;
				return new ResponseEntity<>(new StatusResponse(0, "Invalid Captcha"), HttpStatus.OK);

			}
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Invalid Captcha"), HttpStatus.OK);
		}

		JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getMobile(),
				forgetPassword.getUserType());
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "falure") {
			return new ResponseEntity<>(new StatusResponse(0, "User not found"), HttpStatus.OK);
		}
		String myEmailId = resObj.get("email").toString();
		String otp = otpStore.storeOtp(forgetPassword.getMobile());
		logger.info("OTP:" + otp);
		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For Ebhawan Portal",
//							"OTP For Forget Password " + otp + ",<br><br> Odisha Home Dept");// mail is send to the
//																								// corresponding Email
//				}
//			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send.." + e.getMessage());
		}
		try {
			String templateID = "1077106720132389062";
			smsSend.sendSMS(forgetPassword.getMobile(), "OTP for Forget Password " + otp + ". For details please login to the portal. Home Dept., Govt. of Odishat",
					templateID, "sendOTPSMS");
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		// save the randomcaptca in the data base

		if (otp != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Otp sent successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}// End of savePassword
	@RequestMapping(value = "/resendOTPForGuest", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> resendOTPForGuest(@RequestBody UserRegistrationModel regAttr,
			BindingResult validationResult, HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws IOException, JSONException {
		HttpHeaders responseHeaders = new HttpHeaders();
		System.err.println(regAttr.toString());
		logger.info("Requested userId :" + regAttr.getMobile());
		JSONObject resObj = accessControlService.validateUidAndEmail(regAttr.getMobile(), "GUEST");
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "success") {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "User Alreday Exists", 0));
		}

		String myEmailId = regAttr.getEmail();
		String otp = otpStore.storeOtp(regAttr.getMobile());
		System.out.println("OTP:" + otp);
		// communicate otp to user
		// Send in sms
//		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For e-Bhawan Login", "Dear " + regAttr.getMobile()
//							+ "<br> Your Otp for OBMS registration is " + otp + "<br><br> Thanks <br>e-Bhawan Team");// mail
//																														// is
//																														// send
//																														// to
//																														// the
//																														// corresponding
//																														// Email
//					System.out.println("Mail Sent successfully");
//				}
//			}
//		} catch (Exception e) {
//		}
		logger.error("error occured in sms or email send..");

		try {
			String templateID = "1077572430132285401";
			smsSend.sendSMS(regAttr.getMobile(), "OTP for OBMS Registration " + otp + ". For details please login to the portal. Home Dept., Govt. of Odisha", templateID, "sendOTPSMS");
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		// Send in mail
		if (true) {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "OTP has been sent to your email id and mobile no", 1));
		} else {
			return ResponseEntity.ok().headers(responseHeaders)
					.body(new JwtTokenResponse(null, "", "Something went wrong", 0));
		}

	}
	@RequestMapping(value = "/resendOTP", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> resendOTP(
			@RequestBody ChangePasswordWithoutAuthModel forgetPassword, RedirectAttributes redirectAttributes,
			BindingResult bresult, HttpSession session, HttpServletRequest req, HttpServletResponse res)
			throws JSONException, IOException, MessagingException {
		String methodName = "savePassword";
		ModelAndView model = new ModelAndView();
		Boolean authentication = true;

		

		JSONObject resObj = accessControlService.validateUidAndEmail(forgetPassword.getMobile(),
				forgetPassword.getUserType());
		logger.info("existance status of userid and emailId of a user:" + resObj.toString());
		if (resObj.get("status") == "falure") {
			return new ResponseEntity<>(new StatusResponse(0, "User not found"), HttpStatus.OK);
		}
		String myEmailId = resObj.get("email").toString();
		String otp = otpStore.storeOtp(forgetPassword.getMobile());
		logger.info("OTP:" + otp);
//		try {
//			if (myEmailId != null) {
//				if (!myEmailId.isEmpty()) {
//					mail.sendMimeMail(myEmailId, "OTP For Ebhawan Portal",
//							"OTP For Forget Password " + otp + ",<br><br> Odisha Home Dept");// mail is send to the
//																								// corresponding Email
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send.." + e.getMessage());
//		}
		try {
			String templateID = "1077106720132389062";
			smsSend.sendSMS(forgetPassword.getMobile(), "OTP for Forget Password " + otp + ". For details please login to the portal. Home Dept., Govt. of Odisha",
					templateID, "sendOTPSMS");
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		// save the randomcaptca in the data base

		if (otp != null) {
			return new ResponseEntity<>(new StatusResponse(1, "Otp sent successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}

	}// End of savePassword
	@RequestMapping(value = "/validateForgotPasswordOtp", method = RequestMethod.POST)
	public ResponseEntity<? extends AbstractResponse> validateForgotPasswordOtp(
			@Valid @RequestBody ChangePasswordWithoutAuthModel pwdAttr,
			RedirectAttributes redirectAttributes, BindingResult validationResult, HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		logger.info("Reset password request..");
		logger.info("details:" + pwdAttr);
		// this object will be used for returning the page
		//ModelAndView model = new ModelAndView();
		if (validationResult.hasErrors()) {
			return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
		}
		logger.info("valid inputs..");

		if (!otpStore.isOtpValid(pwdAttr.getUserId(), pwdAttr.getOtp())) {

			return new ResponseEntity<>(new StatusResponse(0, "Invalid Otp"), HttpStatus.OK);
		}
		logger.info("valid otp..");
		System.err.println("data: " + pwdAttr);

		if (!StringUtils.equals(pwdAttr.getPassword(), pwdAttr.getCpassword())) {

			return new ResponseEntity<>(new StatusResponse(0, "Password does not match "), HttpStatus.OK);
		}
		boolean status = accessControlService.savePassword(pwdAttr.getUserId(), pwdAttr.getPassword(),pwdAttr.getUserType());// method thaqt
																									
		if (status == true) {
				return new ResponseEntity<>(new StatusResponse(1, "Password reset successful"), HttpStatus.OK);
	
		} else {
				return new ResponseEntity<>(new StatusResponse(0, "Something went wrong"), HttpStatus.OK);
				
		}
		
	}

	@RequestMapping(value = "/getResults", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> getResults(@RequestBody Map<String, Object> query) {
		Map<String, Object> resp = mdmService.getResults(query);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePgInfoTable", method = RequestMethod.GET)
	public ResponseEntity<? extends AbstractResponse> testingSystem(@RequestParam Map<String, Object> data) {
		Map<String, Object> resp = adminService.testing(data);
		return new ResponseEntity<>(new StatusResponse(1, "Successfully Updated", resp), HttpStatus.OK);
	}

}// end class
