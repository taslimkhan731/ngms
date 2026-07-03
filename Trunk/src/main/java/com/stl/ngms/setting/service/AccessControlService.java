package com.stl.ngms.setting.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.stl.ngms.admin.model.AdminDashoardCount;
import com.stl.ngms.changepassword.controller.ChangePasswordModel;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.ForgetPasswordModel1;
import com.stl.ngms.common.model.LoginUserDetailsModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.orrisaone.model.OrisaoneModel;
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

public interface AccessControlService{

	List<UserModel> getUserDetails(String type);
	boolean saveUserDetails(UserModel user);
	boolean saveOrisaoneDetails(OrisaoneModel oomodel);
	boolean saveGuestDetails(UserModel user);
	boolean updateUserDetails(UserModel user);
	boolean deleteUser(String userId,String userType);
	
	boolean saveUserGroup(UserGroupHasRoles user);
	
	List<Group> getGroupList();
	boolean saveGroup(Group group);
	boolean updateGroup(Group group);
	boolean deleteGroup(long groupId);	
	Group getGroupListById(long groupId);
	
	
	List<UserModel> getMappedUserDetails(long groupId);	
	boolean mapUsersToGroup(long groupId, UserModel[] selectedUsers);
	boolean deleteMappedUser(String userId,String userType);
	List<Resource> getResourceList();
	List<Resource> getUnselectedResourceList(long roleId);
	boolean saveUserResourceTable(String resource_name, String resourceType,String description, String type,long resourceId);
	List<ResourceModel> getDataResourceTable();
	boolean deleteCreateUserResource(String resource_id);
	List<Resource> getMappedResources(long roleId);	
	boolean deleteMappedResources(long roleId, long resourceId);
	boolean mapResourcesToRole(long roleId, Resource[] resources);
	boolean saveUserRoleTable(String role_name, String descriptaion,String type, String id);
	List<RoleModel> getRoleDetails();
	boolean deleteCreateUserRole(String role_id);
	List<Role> getRoleList();
	
	List<Role> getMappedRolesToGroup(long groupId);	
	boolean deleteMappedRole(long groupId, long roleId);
	boolean mapRoleToGroup(long groupId, Role[] roles);
	List<UserModel> getMappedParametersToGroup();
	JSONObject getParameterValueTable(String tableName, String paramName,
			long paramId);
	boolean mapParamvaluesToParam(List<String> paramList, long selectedGroupId,
			String paramName);
	boolean deleteMappedParam(long sl_no);
	
	List<Long> getGroupListByUserId(String uname);
	JSONObject getResourcesAndParamList(List<Long> lstGroupId);
	/**
	 * This method will give the mapped roles to a particular
	 * user by its user id
	 * @param userId 
	 * @return List<String>
	 * @date 04-03-2017
	 */
	public List<String> getMappedRolesToUser(final String userId,final String userType);
	/**
	 * This method will return the unique resources mapped to these
	 * given roles
	 * Note:- Always an User will invoke with single role
	 *  
	 * @param allowedRoles
	 * @return List<ResourceVO>
	 * @date 04-03-2017
	 */
	List<ResourceVO> getUniqueResourcesForRoles(List<String> allowedRoles);
	/**
	 * Retrieve all the available additional parameters to the user/user_group
	 * with the provided values to these parameters for dynamic data control
	 * @param userId
	 * @return HashMap<String, List<String>>
	 * @date 06-03-2017
	 */
	HashMap<String, List<String>> getDataControlDetails(String userId);
	
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	JSONObject validateUidAndEmail(String userid,String userType);
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	JSONObject validateUid(String userid,String userType);
	/************************************************
	 * After sending the mail successfully the corresponding userid and captcha 
	 * will save in tha database table
	 ************************************************/
	boolean saveOtpInDb(String captchaCode, String userid);
	
	/**********************************************
	 * Method that will take the otp and userid as input and 
	 * check in the table that wheather this otp is matching for this userid or not
	 ***************************************************/
	boolean validateOtp(String uid, String input_otp);
	
	/**********************************************************************
	 * method thaqt will check wheather any otp is present or not if present update or else insert
	 * @input:-uid
	 ************************************************/
	boolean resendOtp(String uid, String captchaCode);
	
	/***************************************************************************
	 * Method will set the new password of a corresponding UserId
	 * Input is password and userid
	 ***************************************************************************/
	boolean savePassword(String uid, String encryptPassword,String userType);
	
	UserModel getUserByUserId(String userName,String userType);
	UserModel getUserByUserId(String userName);
	
	
	List<MaappedUserDetailsResponseModel> getListMappedUserDetails();
	
	List<AllLocationModel> getAllLocationAccordingToRole(long groupId);
	
	boolean mapLocationToUser(String mapUserId, String districtId, String type, Principal auth, String mapUserType);
	
	List<AllLocationModel> getAllDistrict();
	
	List<AllLocationModel> getAllBlockByDistrict(String districtcode);
	
	Map<String, Object> getUserIdLocationId(String userId,String userType);
	JSONObject getAllMappedCityLocByUser(String userId, String role);
	JSONObject getAllMappedCityLocByUser1(String userId, String role);
	List<LoginUserDetailsModel> getFirstNameLastName(String userId);
	boolean changePasswordOnValid(ChangePasswordModel password, String userId,String userType);
	String getOldPasswordFromDB(String userId,String userType);
	boolean updateUserDetailsOtp(UserModel user);
	String uploadDocument(PasswordResetModel forgetPassword);
	String uploadDocument(GuestHouseModel gh);
	String uploadDocument1(ForgetPasswordModel1 forgetPassword);
	boolean updateGuestDetails(UserModel user, Boolean updateProfile);
	void mediaDownloadService(HttpServletResponse response,HttpServletRequest request, String imageCode,Principal principal,HttpSession session);
	
	List<AllLocationModel> getAllLocationAccordingToUser(String selectedUserId, String userType);
	
	Boolean deleteLoc(Integer id);
	
	List<AllLocationModel> getAllState();
	
	boolean saveMasterGuestDetails(PasswordResetModel forgetPassword);
	boolean saveMasterGuestDetailss(PasswordResetModel forgetPassword);
	UserModel getUserMasterData(UserModel lstUserDetails);
	
	boolean updateMasterGuestDetails(PasswordResetModel forgetPassword);
	Boolean saveAuditLog(AuditlogRequest loginRequest, HttpServletRequest request);
	JSONObject validateforFeedback(ChangePasswordWithoutAuthModel forgetPassword);
	JSONObject validateforFeedbackforQr(String reqId);
	JSONObject validateforFeedbackforDt(String reqId, String date);
	List<UserModel> getRegisterDetails(String userId,String role);
	boolean updateUserDetail(PasswordResetModel details);
	boolean updateAllUserDetails(PasswordResetModel details);
	boolean updateForActivateUser(UserModel user);
	String getroleFromUserType(String userType);
	boolean uploadHomeDocument(GuestHouseModel gh);
	String uploadPdfDocument(PasswordResetModel data);
	AdminDashoardCount getDashboardCount();
	String getRoleByUserType(String userId);
	/**
	 * This method will save the password history of individual user
	 * @param userId, password,user role
	 * @return Boolean
	 * @date 19-11-2024
	 */
	Boolean saveOnPasswordHistory(String password, String userId, String groupName,String perform);
	/**
	 * This method will get the last recent password from password history of
	 * individual user and checking it's recent use password or not.
	 * @param userId,user role,perform
	 * @return Boolean
	 * @date 19-11-2024
	 */
	Boolean checkPasswordIsPresentOrNot(String userId, String role,String newpassword);
	/**
	 * This method will get the last recent updated date with adding of 15min.
	 * @param userId,user role
	 * @return Boolean
	 * @date 20-11-2024
	 */
	Boolean checkLastTimeExceed(String userId, String role);
}