package com.stl.ngms.setting.dao;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.stl.ngms.changepassword.controller.ChangePasswordModel;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.LoginUserDetailsModel;
import com.stl.ngms.common.model.UserRegistrationModel;
import com.stl.ngms.orrisaone.model.OrisaoneModel;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.ApplicationModel;
import com.stl.ngms.setting.model.CountryModal;
import com.stl.ngms.setting.model.EmailModel;
import com.stl.ngms.setting.model.Employee;
import com.stl.ngms.setting.model.Group;
import com.stl.ngms.setting.model.HybridSeededSurveyConfigDto;
import com.stl.ngms.setting.model.HybridSeededSurveyDto;
import com.stl.ngms.setting.model.LocationModel;
import com.stl.ngms.setting.model.MaappedUserDetailsResponseModel;
import com.stl.ngms.setting.model.Parameter;
import com.stl.ngms.setting.model.ParameterModel;
import com.stl.ngms.setting.model.Resource;
import com.stl.ngms.setting.model.ResourceModel;
import com.stl.ngms.setting.model.ResourceVO;
import com.stl.ngms.setting.model.Role;
import com.stl.ngms.setting.model.RoleModel;
import com.stl.ngms.setting.model.SmsModel;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;

public interface AccessControlDao {

	List<UserModel> getUserDetails();
	boolean saveUserDetails(UserModel user);
	boolean saveOrisaoneDetails(OrisaoneModel oomodel);
	boolean saveGuestDetails(UserModel user);
	boolean updateUserDetails(UserModel user);
	boolean deleteUser(String userId);
	
	boolean saveUserGroup(UserGroupHasRoles user);
	
	List<Group> getGroupList();
	boolean saveGroup(Group group);
	boolean updateGroup(Group group);
	boolean deleteGroup(long groupId);	
	Group getGroupListById(long groupId);
	
	
	List<UserModel> getMappedUserDetails(long groupId);	
	boolean mapUsersToGroup(long groupId, UserModel[] selectedUsers);
	boolean deleteMappedUser(String userId);
	List<Resource> getResourceList();
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
	List<UserModel> getMappedParametersToGroup(long groupId);
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
	public List<String> getMappedRolesToUser(final String userId);
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
	JSONObject validateUidAndEmail(String userid);
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	JSONObject validateUid(String userid);
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
	boolean savePassword(String uid, String encryptPassword);
	
	UserModel getUserByUserId(String userName);
	
	
	
	List<MaappedUserDetailsResponseModel> getListMappedUserDetails();
	
	List<AllLocationModel> getAllLocationAccordingToRole(long groupId);
	
	boolean mapLocationToUser(String mapUserId, String districtId,String type, Principal auth);
	
	List<AllLocationModel> getAllDistrict();
	
	List<AllLocationModel> getAllBlockByDistrict(String districtcode);
	
	Map<String, Object> getUserIdLocationId(String userId);
	List<LoginUserDetailsModel> getFirstNameLastName(String userId);
	boolean changePasswordOnValid(ChangePasswordModel password, String userId);
	String getOldPasswordFromDB(String userId);
	boolean updateUserDetailsOtp(UserModel user);
	boolean updateGuestDetails(UserModel user);
	
	List<AllLocationModel> getAllLocationAccordingToUser(String selectedUserId);
	
	Boolean deleteLoc(Integer id);
	List<AllLocationModel> getAllState();
	
	boolean saveMasterGuestDetails(PasswordResetModel forgetPassword);
	boolean saveMasterGuestDetailss(UserRegistrationModel forgetPassword);
	UserModel getUserMasterData(UserModel lstUserDetails);
	
	boolean updateMasterGuestDetails(UserRegistrationModel forgetPassword);
	Boolean saveAuditLog(AuditlogRequest loginRequest, HttpServletRequest request);
	JSONObject validatefeedback(ChangePasswordWithoutAuthModel forgetPassword);
	JSONObject validateFeedbackforQr(String reqId);
	JSONObject validateforFeedbackforDt(String reqId, String date);
	List<UserModel> getRegistrationDetails(String userId);
	boolean updateUserDetail(UserModel user);
	
	
}