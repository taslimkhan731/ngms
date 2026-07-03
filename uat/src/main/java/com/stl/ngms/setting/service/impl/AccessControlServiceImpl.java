package com.stl.ngms.setting.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.stl.ngms.admin.model.AdminDashoardCount;
import com.stl.ngms.admin.service.AdminService;
import com.stl.ngms.changepassword.controller.ChangePasswordModel;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.ForgetPasswordModel1;
import com.stl.ngms.common.model.LoginUserDetailsModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.service.GuestService;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.orrisaone.model.OrisaoneModel;
import com.stl.ngms.setting.dao.AccessControlDao;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.Group;
import com.stl.ngms.setting.model.MaappedUserDetailsResponseModel;
import com.stl.ngms.setting.model.Resource;
import com.stl.ngms.setting.model.ResourceModel;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.model.Role;
import com.stl.ngms.setting.model.RoleModel;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.Utils;
import com.stl.ngms.utils.ValidationUtil;

@Service("accessControlService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@PropertySource("classpath:media.properties")
public class AccessControlServiceImpl implements AccessControlService {
	final static Logger logger = Logger.getLogger(AccessControlServiceImpl.class);
	@Autowired
    private AccessControlDao accessControlDao;
	
	@Autowired GuestService guestService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired Environment env;
	
	@Value("${default.user.password}")
	private String defaultUserPassword;
	
	@Override
	public List<UserModel> getUserDetails(String type) {
		return accessControlDao.getUserDetails(type);
	}
	
	
	@Override
	public boolean saveUserDetails(UserModel user) {
		if(user.getPassword()==null){			;
			user.setPassword(defaultUserPassword);
		}
//		String encryptPassword = encoder.getEncriptedPassword(user.getPassword());
//		user.setPassword(encryptPassword);
		user.setActive(true);
		return accessControlDao.saveUserDetails(user);
	}
	@Override
	public boolean saveOrisaoneDetails(OrisaoneModel oomodel) {
		
		return accessControlDao.saveOrisaoneDetails(oomodel);
	}
	@Override
	public boolean saveGuestDetails(UserModel user) {
		if(user.getPassword()==null){			;
			user.setPassword(defaultUserPassword);
		}
//		String encryptPassword = encoder.getEncriptedPassword(user.getPassword());
//		user.setPassword(encryptPassword);
		user.setActive(true);
		return accessControlDao.saveGuestDetails(user);
	}
	
	@Override
	public boolean updateUserDetails(UserModel user) {
		return accessControlDao.updateUserDetails(user);
	}
	
	@Override
	public boolean updateGuestDetails(UserModel user, Boolean updateProfile) {
		return accessControlDao.updateGuestDetails(user, updateProfile);
	}
	
	@Override
	public boolean updateUserDetailsOtp(UserModel user) {
		return accessControlDao.updateUserDetailsOtp(user);
	}
	
	@Override
	public boolean deleteUser(String userId,String userType) {
		return accessControlDao.deleteUser(userId,userType);
	}
	
	@Override
	public boolean saveUserGroup(UserGroupHasRoles user) {
		return accessControlDao.saveUserGroup(user);
	}
	
	@Override
	public List<Group> getGroupList() {
		return accessControlDao.getGroupList();
	}
	
	@Override
	public Group getGroupListById(long groupId) {
		return accessControlDao.getGroupListById(groupId);
	}

	@Override
	public boolean saveGroup(Group group) {
		group.setActive(true);
		return accessControlDao.saveGroup(group);
	}

	@Override
	public boolean updateGroup(Group group) {
		return accessControlDao.updateGroup(group);
	}

	@Override
	public boolean deleteGroup(long groupId) {
		return accessControlDao.deleteGroup(groupId);
	}

	@Override
	public List<UserModel> getMappedUserDetails(long groupId) {
		return accessControlDao.getMappedUserDetails(groupId);
	}
	@Override
	public boolean mapUsersToGroup(long groupId, UserModel[] users) {
		return accessControlDao.mapUsersToGroup(groupId, users);
	}
	@Override
	public boolean deleteMappedUser(String userId,String userType) {
		return accessControlDao.deleteMappedUser(userId,userType);
	}
	/*************************Create Resouce**********************************************************/	
	@Override
	public boolean saveUserResourceTable(String resource_name,String resourceType,String description,String type,long resourceId) {
		// TODO Auto-generated method stub
		return accessControlDao.saveUserResourceTable(resource_name,resourceType,description,type,resourceId);
	}
	
	@Override
	public List<ResourceModel> getDataResourceTable() {
		return accessControlDao.getDataResourceTable();
	}
	@Override
	public boolean deleteCreateUserResource(String resource_id) {
		return accessControlDao.deleteCreateUserResource(resource_id);
	}
	
	@Override
	public List<Resource> getResourceList() {
		return accessControlDao.getResourceList();
	}

/*	@Override
	public boolean saveResource(Resource resource) {
		resource.setActive(true);
		return accessControlDao.saveResource(resource);
	}

	@Override
	public boolean updateResource(Resource resource) {
		return accessControlDao.updateResource(resource);
	}

	@Override
	public boolean deleteResource(long resourceId) {
		return accessControlDao.deleteResource(resourceId);
	}*/
	
	@Override
	public List<Resource> getMappedResources(long roleId) {
		return accessControlDao.getMappedResources(roleId);
	}
	
	@Override
	public boolean deleteMappedResources(long roleId, long resourceId) {
		return accessControlDao.deleteMappedResources(roleId, resourceId);
	}
	@Override
	public boolean mapResourcesToRole(long roleId, Resource[] resources) {
		return accessControlDao.mapResourcesToRole(roleId, resources);
	}	
	
	/*************************Create Role**********************************************************/		
	@Override
	public boolean saveUserRoleTable(String role_name, String description,
			String type, String id) {
		// TODO Auto-generated method stub
		return accessControlDao.saveUserRoleTable(role_name,description,type,id);
	}
	
	@Override
	public List<RoleModel> getRoleDetails() {
		return accessControlDao.getRoleDetails();
	}
	@Override
	public boolean deleteCreateUserRole(String role_id) {
		return accessControlDao.deleteCreateUserRole(role_id);
	}
	
	@Override
	public List<Role> getRoleList() {
		return accessControlDao.getRoleList();
	}
	@Override
	public List<Role> getMappedRolesToGroup(long groupId) {
		return accessControlDao.getMappedRolesToGroup(groupId);
	}
	
	@Override
	public boolean deleteMappedRole(long groupId, long roleId) {
		return accessControlDao.deleteMappedRole(groupId, roleId);
	}
	@Override
	public boolean mapRoleToGroup(long groupId, Role[] roles) {
		return accessControlDao.mapRoleToGroup(groupId, roles);
	}

	/**************************************
	 * list of mapped parameter to group
	 *************************************/
	@Override
	public List<UserModel> getMappedParametersToGroup() {
		// TODO Auto-generated method stub
		return accessControlDao.getMappedParametersToGroup();
	}

	@Override
	public JSONObject getParameterValueTable(String tableName, String paramName,
			long paramId) {
		// TODO Auto-generated method stub
		return accessControlDao.getParameterValueTable(tableName,paramName,paramId);
	}

	@Override
	public boolean mapParamvaluesToParam(List<String> paramList,
			long selectedGroupId, String paramName) {
		// TODO Auto-generated method stub
		return accessControlDao.mapParamvaluesToParam(paramList,selectedGroupId,paramName);
	}

	@Override
	public boolean deleteMappedParam(long sl_no) {
		// TODO Auto-generated method stub
		return accessControlDao.deleteMappedParam(sl_no);
	}

	@Override
	public List<Long> getGroupListByUserId(String uname) {
		// TODO Auto-generated method stub
		return accessControlDao.getGroupListByUserId(uname);
	}

	@Override
	public JSONObject getResourcesAndParamList(
			List<Long> lstGroupId) {
		// TODO Auto-generated method stub
		return accessControlDao.getResourcesAndParamList(lstGroupId);
	}
	/**
	 * This method will give the mapped roles to a particular
	 * user by its user id
	 * @param userId 
	 * @return List<String>
	 * @date 04-03-2017
	 */
	@Override
	public List<String> getMappedRolesToUser(String userId,String userType) {
		
		return accessControlDao.getMappedRolesToUser(userId,userType);
	}
	/**
	 * This method will return the unique resources mapped to these
	 * given roles
	 * Note:- Always an User will invoke with single role
	 *  
	 * @param allowedRoles
	 * @return List<ResourceVO>
	 * @date 04-03-2017
	 */
	@Override
	public List<ResourceVO> getUniqueResourcesForRoles(List<String> allowedRoles) {
		return accessControlDao.getUniqueResourcesForRoles(allowedRoles);
	}
	/**
	 * Retrieve all the available additional parameters to the user/user_group
	 * with the provided values to these parameters for dynamic data control
	 * @param userId
	 * @return HashMap<String, List<String>>
	 * @date 06-03-2017
	 */
	@Override
	public HashMap<String, List<String>> getDataControlDetails(String userId) {
		return accessControlDao.getDataControlDetails(userId);
	}
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	@Override
	public JSONObject validateUidAndEmail(String userid,String userType) {
		// TODO Auto-generated method stub
		
		return accessControlDao.validateUidAndEmail(userid,userType);
	}
	
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	@Override
	public JSONObject validateUid(String userid,String userType) {
		// TODO Auto-generated method stub
		return accessControlDao.validateUid(userid,userType);
	}
	
	/************************************************
	 * After sending the mail successfully the corresponding userid and captcha 
	 * will save in tha database table
	 ************************************************/
	@Override
	public boolean saveOtpInDb(String captchaCode, String userid) {
		// TODO Auto-generated method stub
		return accessControlDao.saveOtpInDb(captchaCode,userid);
	}
	/**********************************************
	 * Method that will take the otp and userid as input and 
	 * check in the table that wheather this otp is matching for this userid or not
	 ***************************************************/
	@Override
	public boolean validateOtp(String uid, String input_otp) {
		// TODO Auto-generated method stub
		return accessControlDao.validateOtp(uid,input_otp);
	}
	/**********************************************************************
	 * method thaqt will check wheather any otp is present or not if present update or else insert
	 * @input:-uid
	 ************************************************/
	@Override
	public boolean resendOtp(String uid, String captchaCode) {
		// TODO Auto-generated method stub
		return accessControlDao.resendOtp(uid,captchaCode);
	}
	/***************************************************************************
	 * Method will set the new password of a corresponding UserId
	 * Input is password and userid
	 ***************************************************************************/
	@Override
	public boolean savePassword(String uid, String encryptPassword,String userType) {
		// TODO Auto-generated method stub
		return accessControlDao.savePassword(uid,encryptPassword,userType);
	}	
	
	/*
	 * Used for security authentication
	 * @since 23-10-2017
	 * @author Lusi Dash
	 */
	@Override
	public UserModel getUserByUserId(String userName,String userType) {
		return accessControlDao.getUserByUserId(userName,userType);
	}
	@Override
	public UserModel getUserByUserId(String userName) {
		return accessControlDao.getUserByUserId(userName);
	}

	@Override
	public List<MaappedUserDetailsResponseModel> getListMappedUserDetails() {
		return accessControlDao.getListMappedUserDetails();
	}

	@Override
	public List<AllLocationModel> getAllLocationAccordingToRole(long groupId) {
		// TODO Auto-generated method stub
		return accessControlDao.getAllLocationAccordingToRole(groupId);
	}

	@Override
	public boolean mapLocationToUser(String mapUserId, String districtId, String type, Principal auth, String mapUserType) {
		// TODO Auto-generated method stub
		return accessControlDao.mapLocationToUser(mapUserId,districtId,type,auth, mapUserType);
	}

	@Override
	public List<AllLocationModel> getAllDistrict() {
		// TODO Auto-generated method stub
		return accessControlDao.getAllDistrict();
	}

	@Override
	public List<AllLocationModel> getAllBlockByDistrict(String districtcode) {
		// TODO Auto-generated method stub
		return accessControlDao.getAllBlockByDistrict(districtcode);
	}

	@Override
	public Map<String, Object> getUserIdLocationId(String userId,String userType) {

		return accessControlDao.getUserIdLocationId(userId,userType);
	}
	
	@Override
	public JSONObject getAllMappedCityLocByUser(String userId, String role){
		return accessControlDao.getAllMappedCityLocByUser(userId, role);
	}
	@Override
	public JSONObject getAllMappedCityLocByUser1(String userId, String role){
		return accessControlDao.getAllMappedCityLocByUser1(userId, role);
	}
	@Override
	public List<LoginUserDetailsModel> getFirstNameLastName(String userId) {
		return accessControlDao.getFirstNameLastName(userId);
	}

	@Override
	public boolean changePasswordOnValid(ChangePasswordModel password, String userId,String userType) {
		return accessControlDao.changePasswordOnValid(password, userId, userType);
	}

	@Override
	public String getOldPasswordFromDB(String userId,String userType) {
		return accessControlDao.getOldPasswordFromDB(userId,userType);
	}

	@Override
	public String uploadDocument(PasswordResetModel forgetPassword) {
		String filepathlist = null;
		try {
			if (forgetPassword.getSupportDoc() != null) {
				final List<String> allowedFileExtentions = Arrays.asList(new String[] {"pdf" , "jpg", "jpeg", "png" });
				final List<String> allowedMimeTypes = Arrays.asList(new String[] {"application/pdf", "image/jpg", "image/jpeg", "image/png" });
				boolean validFileStatus = ValidationUtil.isValidFile(forgetPassword.getSupportDoc(), allowedFileExtentions,
						allowedMimeTypes);
				if (validFileStatus == false) {
					filepathlist = "failure";
				}else {
					String data = String.valueOf(new Date().getTime()) + "_" + String.valueOf(forgetPassword.getMobile());
					String directory = env.getProperty("media.base.path") + File.separator
							 + File.separator + forgetPassword.getMobile() + File.separator
							+ data;
					filepathlist = Utils.saveFileInSysPath(forgetPassword.getSupportDoc(), directory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepathlist;
	}
	
	@Override
	public String uploadDocument(GuestHouseModel gh) {
		String filepathlist = null;
		System.out.println("service"+gh.toString());
		try {
			if (gh.getSupportDoc() != null &&  !gh.getSupportDoc().isEmpty()) {
				final List<String> allowedFileExtentions = Arrays.asList(new String[] {"jpg", "jpeg" });
				final List<String> allowedMimeTypes = Arrays.asList(new String[] {"image/jpg", "image/jpeg" });
				boolean validFileStatus = ValidationUtil.isValidFile(gh.getSupportDoc(), allowedFileExtentions,
						allowedMimeTypes);
				if (validFileStatus == false) {
					filepathlist = "failure";
				}else {
					String directory = env.getProperty("media.base.path") + File.separator+ env.getProperty("gh_doc.folder") ;							
					filepathlist = Utils.saveFileInSysPathGH(gh.getSupportDoc(), directory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepathlist;
	}
	
	@Override
	public String uploadDocument1(ForgetPasswordModel1 forgetPassword) {
		String filepathlist = null;
		try {
			if (forgetPassword.getSuppDoc() != null) {
				final List<String> allowedFileExtentions = Arrays.asList(new String[] {"pdf" , "jpg", "jpeg" });
				final List<String> allowedMimeTypes = Arrays.asList(new String[] {"application/pdf", "image/jpg", "image/jpeg" });
				boolean validFileStatus = ValidationUtil.isValidFile(forgetPassword.getSuppDoc(), allowedFileExtentions,
						allowedMimeTypes);
				if (validFileStatus == false) {
					filepathlist = "failure";
				}else {
					String data = String.valueOf(new Date().getTime()) + "_" + String.valueOf(forgetPassword.getJ_username());
					String directory = env.getProperty("media.base.path") + File.separator
							 + File.separator + forgetPassword.getJ_username() + File.separator
							+ data;
					filepathlist = Utils.saveFileInSysPath(forgetPassword.getSuppDoc(), directory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepathlist;
	}
	@Override
	public void mediaDownloadService(HttpServletResponse response,HttpServletRequest request,String imageCode,Principal principal,HttpSession session) {
		String groupName=null;
		System.out.println((String)session.getAttribute("userId"));
		if ((String)session.getAttribute("userId") != null) {
		Map<String, Object> groupIdLocationId=(Map<String, Object>)session.getAttribute("groupIdLocationId");
		groupName= (String) groupIdLocationId.get("group_name");
		}
		String path = env.getProperty("media.rootfolder") + File.separator;
		String path1 = env.getProperty("media.base.path") + File.separator+ env.getProperty("gh_doc.folder");
		String path2 = env.getProperty("media.base.path") + File.separator+ env.getProperty("home_doc.folder");
		String imageName = null;
		String actualImage=null;
		
		String[] filePath = imageCode.split("_");
		System.out.println(filePath[0]);
		if (filePath[0].equalsIgnoreCase("user")) {
			UserModel userModel = null;
			try {
				userModel = accessControlDao.getUserByUserId(filePath[1],"GUEST");
			} catch (Exception e) {}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getDocPath() == null || userModel.getDocPath() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getDocPath();
			}
			path = path + imageName;
		}else if (filePath[0].equalsIgnoreCase("applicationSup")) {
			RoomBookingPojo userModel = null;
			try {
				if(groupName.equalsIgnoreCase("GUEST"))
				{
				userModel = guestService.getBookingDetailsById(filePath[1],principal.getName());
				}else {
					userModel=	adminService.admingetBookingDetailsById(filePath[1]);
				}
				System.out.println("userModel"+userModel.toString());
			} catch (Exception e) {}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getSuppDocName() == null || userModel.getSuppDocName() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getSuppDocName();
			}
			path = path + imageName;
		} else if (filePath[0].equalsIgnoreCase("applicationCat")) {
			RoomBookingPojo userModel = null;
			try {
				if(groupName.equalsIgnoreCase("GUEST"))
				{
				userModel = guestService.getBookingDetailsById(filePath[1],principal.getName());
				}else {
					userModel=	adminService.admingetBookingDetailsById(filePath[1]);
				}
			} catch (Exception e) {}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getCatgDocName() == null || userModel.getCatgDocName() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getCatgDocName();
			}
			path = path + imageName;
		} else if (filePath[0].equalsIgnoreCase("guestDoc")) {
			RoomBookingPojo userModel = null;
			try {
				if(groupName.equalsIgnoreCase("GUEST"))
				{
				userModel = guestService.getBookingDetailsById(filePath[1],principal.getName());
				}else {
					userModel=	adminService.admingetBookingDetailsById(filePath[1]);
					System.out.println("user model   "+userModel);
				}
			} catch (Exception e) {}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getgDocName() == null || userModel.getgDocName() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getgDocName();
			}
			path = path + imageName;
		}
		else if (filePath[0].equalsIgnoreCase("homedoc")) {
			path = path2+ File.separator +filePath[1] ;
			
		}else if(filePath[0].equalsIgnoreCase("ghimage")){
			actualImage = path1+ File.separator +filePath[1] ;
			File file = new File(actualImage);
			response.setContentType("image/jpeg");
		     ServletOutputStream out;
		     try {
				out = response.getOutputStream();
				 downloadFile(file, out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		   return;
		}else if(filePath[0].equalsIgnoreCase("officeOrder")){
			PasswordResetModel userModel = null;
			try {
				userModel=	adminService.getOfficeorderDocById(filePath[1]);
				System.out.println("user model   "+userModel);
			} catch (Exception e) {}
			if (null == userModel) {
				throw new UsernameNotFoundException(String.format("Admin not found for the given Id :" + filePath[1]));
			}
			if (userModel.getDocumentpath() == null || userModel.getDocumentpath() == "") {
				imageName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				imageName = userModel.getDocumentpath();
			}
			path = path + imageName;
		}else {
			throw new UsernameNotFoundException("Code not found :" + filePath[1]);
		}
		try {
			if(actualImage ==null) {
			File file = new File(path);
			int i = 0;
			String fileName = "";
			logger.info(path);
			try {
				i = path.lastIndexOf('/');
			} catch (Exception e) {
				throw new ArrayIndexOutOfBoundsException();
			}
			logger.info(i);
			if (i == 0) {
				fileName = env.getProperty("default.image.path") + File.separator + "SampleTest.jpg";
			} else {
				fileName = path.substring(i + 1);
			}
			String mimeType = null;
			try {
				mimeType = URLConnection.guessContentTypeFromName(path);
			} catch (Exception e) {
				logger.info("mimetype is not detectable, will take default");
			}
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", fileName));
			//"Content-Disposition", "inline; filename=OfficeOrderList.pdf;"
			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());
			logger.info("Downloaded File Path1 : " + fileName);
			logger.info("Downloaded File Path : " + path.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 private void downloadFile(File file, ServletOutputStream out) throws IOException {
	        FileInputStream fin = null;
	        BufferedInputStream bin;
	        BufferedOutputStream bout;
	        fin = new FileInputStream(file);
	        bin = new BufferedInputStream(fin);
	        bout = new BufferedOutputStream(out);
	        int ch = 0;
	        while ((ch = bin.read()) != -1) {
	            bout.write(ch);
	        }
	        bin.close();
	        fin.close();
	        bout.close();
	        out.close();
	    }

	    

	
	@Override
	public List<AllLocationModel> getAllLocationAccordingToUser(String selectedUserId, String userType) {
		return accessControlDao.getAllLocationAccordingToUser(selectedUserId, userType);
	}

	@Override
	public Boolean deleteLoc(Integer id) {
		// TODO Auto-generated method stub
		return accessControlDao.deleteLoc(id);
	}

	@Override
	public List<AllLocationModel> getAllState() {
		return accessControlDao.getAllState();
	}

	@Override
	public boolean saveMasterGuestDetails(PasswordResetModel forgetPassword) {
		return accessControlDao.saveMasterGuestDetails(forgetPassword);
	}
	@Override
	public boolean saveMasterGuestDetailss(PasswordResetModel forgetPassword) {
		return accessControlDao.saveMasterGuestDetailss(forgetPassword);
	}
	@Override
	public UserModel getUserMasterData(UserModel lstUserDetails) {
		return accessControlDao.getUserMasterData(lstUserDetails);
	}

	@Override
	public boolean updateMasterGuestDetails(PasswordResetModel forgetPassword) {
		return accessControlDao.updateMasterGuestDetails(forgetPassword);
	}
	@Override
	public Boolean saveAuditLog(AuditlogRequest request,HttpServletRequest httprequest) {
		return accessControlDao.saveAuditLog(request,httprequest);
	}

	@Override
	public JSONObject validateforFeedback(ChangePasswordWithoutAuthModel forgetPassword) {
		return accessControlDao.validatefeedback(forgetPassword);
	}

	@Override
	public JSONObject validateforFeedbackforQr(String reqId) {
		return accessControlDao.validateFeedbackforQr(reqId);
	}

	@Override
	public JSONObject validateforFeedbackforDt(String reqId, String date) {
		return accessControlDao.validateforFeedbackforDt(reqId,date);
	}

	@Override
	public List<UserModel> getRegisterDetails(String userId,String role) {
		return accessControlDao.getRegistrationDetails(userId,role) ;
	}

	@Override
	public boolean updateUserDetail(PasswordResetModel details) {
		return accessControlDao.updateUserDetail(details);
	}

	@Override
	public boolean updateAllUserDetails(PasswordResetModel details) {
		return accessControlDao.updateAllUserDetail(details);
	}

	@Override
	public boolean updateForActivateUser(UserModel user) {
		return accessControlDao.updateForActivateUser(user);
	}

	@Override
	public List<Resource> getUnselectedResourceList(long roleId) {
		// TODO Auto-generated method stub
		return accessControlDao.getUnselectedResourceList(roleId);
	}


	@Override
	public String getroleFromUserType(String userType) {
		return accessControlDao.getroleFromUserType(userType);
	}


	@Override
	public boolean uploadHomeDocument(GuestHouseModel gh) {
		System.out.println("service"+gh.toString());
		boolean validFileStatus=false;
		try {
			String homeDocType="";
			if(gh.getHomeDocType().equalsIgnoreCase("Regulation")) {
				homeDocType="Regulation";
			}else if(gh.getHomeDocType().equalsIgnoreCase("Tariff")) {
				homeDocType="Tariff";
			}else if(gh.getHomeDocType().equalsIgnoreCase("Services")) {
				homeDocType="Services";
			}else {
				homeDocType="Faq";
			}
			String directory = env.getProperty("media.base.path") + File.separator+ env.getProperty("home_doc.folder") ;
			File f= new File(directory+ File.separator + homeDocType);
			f.delete();
			if (gh.getHomeDoc() != null &&  !gh.getHomeDoc().isEmpty()) {
				final List<String> allowedFileExtentions = Arrays.asList(new String[] {"pdf"});
				final List<String> allowedMimeTypes = Arrays.asList(new String[] {"application/pdf"});
				validFileStatus = ValidationUtil.isValidFile(gh.getHomeDoc(), allowedFileExtentions,
						allowedMimeTypes);
				if (validFileStatus) {														
					if(!gh.getHomeDoc().isEmpty()) {
						Path path = null;
						File dir = new File(directory);
						if (!dir.exists())
							dir.mkdirs();
						byte[] bytes = gh.getHomeDoc().getBytes();
						path = Paths.get(directory + File.separator + homeDocType+".pdf");
						Files.write(path, bytes);
					}				
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validFileStatus;
	}


	@Override
	public String uploadPdfDocument(PasswordResetModel dataforPdf) {

		String filepathlist = null;
		try {
			if (dataforPdf.getDocUpload() != null) {
				final List<String> allowedFileExtentions = Arrays.asList(new String[] {"pdf"});
				final List<String> allowedMimeTypes = Arrays.asList(new String[] {"application/pdf"});
				boolean validFileStatus = ValidationUtil.isValidFile(dataforPdf.getDocUpload(), allowedFileExtentions,
						allowedMimeTypes);
				if (validFileStatus == false) {
					filepathlist = "failure";
				}else {
					String data = String.valueOf(new Date().getTime()) ;
					String directory = env.getProperty("media.base.path") + File.separator +env.getProperty("office_doc.folder")+ File.separator +  data;
					filepathlist = Utils.saveFileInSysPath(dataforPdf.getDocUpload(), directory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepathlist;
	
	}

	@Override
	public AdminDashoardCount getDashboardCount() {
		return accessControlDao.getDashboardCount();
	}


	@Override
	public String getRoleByUserType(String userType) {
		return accessControlDao.getRoleByUserType(userType);
	}
	
	@Override
	public Boolean saveOnPasswordHistory(String password, String userId, String groupName,String perform) {
		return accessControlDao.saveOnPasswordHistory(password,userId,groupName,perform);
	}


	@Override
	public Boolean checkPasswordIsPresentOrNot(String userId, String role,String newpassword) {
		List<String> passwordGroup=accessControlDao.getRecentPassword(userId,role);
		if(passwordGroup.contains(newpassword))
			return true;
		
		return false;
	}


	@Override
	public Boolean checkLastTimeExceed(String userId, String role) {
		LocalDateTime lastUpdateTime=accessControlDao.getLastUpdatedTime(userId,role);
		lastUpdateTime=lastUpdateTime.plusMinutes(15);
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(currentDateTime.isBefore(lastUpdateTime))
			return false;
		
		return true;
	}
}

