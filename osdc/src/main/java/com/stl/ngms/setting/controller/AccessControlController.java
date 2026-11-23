package com.stl.ngms.setting.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
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
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stl.ngms.admin.model.AdminDashoardCount;
import com.stl.ngms.common.MailUtil;
import com.stl.ngms.common.SendSMS;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.Group;
import com.stl.ngms.setting.model.GroupRoleMapModel;
import com.stl.ngms.setting.model.MaappedUserDetailsResponseModel;
import com.stl.ngms.setting.model.Resource;
import com.stl.ngms.setting.model.ResourceModel;
import com.stl.ngms.setting.model.Role;
import com.stl.ngms.setting.model.RoleModel;
import com.stl.ngms.setting.model.UserGroupHasRoles;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.PasswordEncryptionUtil;
import com.stl.ngms.utils.Sha512;

@Controller
@RequestMapping("/setting")
public class AccessControlController {


	final static Logger logger = Logger.getLogger(AccessControlController.class);
 
	@Autowired
	private AccessControlService accessControlService;

	@Autowired
	private Sha512 sha512;
	
	@Autowired MailUtil mail;
	
	@Autowired SendSMS smsSend;
	
	@RequestMapping(value = { "/sysAdminDashboardView" }, method = RequestMethod.GET)
	public String adminDashboardView(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveDashboard");
		logger.debug("redirecting to defaultSettingView");
		return "adminDashboardView";
	}

	@RequestMapping(value = { "/default" }, method = RequestMethod.GET)
	public String defaultSettingView(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		logger.debug("redirecting to defaultSettingView");
		return "defaultSettingView";
	}

	@RequestMapping(value = { "/createUser" }, method = RequestMethod.GET)
	public String createUserView(HttpSession session) {
		logger.debug("redirecting to  createUserView");
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		return "createUserView";
	}

	@RequestMapping(value = { "/userGroups" }, method = RequestMethod.GET)
	public String userGroupsView(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		logger.debug("redirecting to  userGroupsView");

		return "userGroupsView";
	}

	@RequestMapping(value = { "/createResources" }, method = RequestMethod.GET)
	public String defineResourcesView(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		logger.debug("redirecting to  defineResourcesView");
		return "createResourcesView";
	}

	@RequestMapping(value = { "/createRole" }, method = RequestMethod.GET)
	public String createRoleView(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		logger.debug("redirecting to  createRoleView");
		return "createRoleView";
	}


	@RequestMapping(value = { "/groupRoleMap" }, method = RequestMethod.GET)
	public String groupRoleMap(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		logger.debug("redirecting to  createRoleView");

		return "roleResourcesMapView";
	}

	@RequestMapping(value = { "/userRoleMap" }, method = RequestMethod.GET)
	public String userRoleMapView(HttpSession session) {
		session.setAttribute("sessionDashboardTab", "styleActiveSettings");
		logger.debug("redirecting to  userRoleMapView");

		return "userRoleMapView";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	/*****************************************************
	 * Method to Get UserDetails
	 *******************************************************/
	@RequestMapping(value={"/getUserDetails/{type}"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserDetails(@PathVariable("type") String type,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <UserModel> lstUserDetails = accessControlService.getUserDetails(type);
		/*//System.out.println("usermodel______________"+lstUserDetails);*/		
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);

		return resp;
	}

	@RequestMapping(value = { "/saveUserDetails" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUserDetails(@RequestBody @Valid UserModel user, BindingResult bresult,
			Principal principal) throws JSONException { // @ModelAttribute UserModel user,
		String userId = principal.getName();
		logger.info("Inside:: @@ Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug(user.toString());
		if (bresult.hasErrors()) {
			logger.info("Inside:: @@ hasErrors");
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			// return result.toString();

	} else {
			Group grp = accessControlService.getGroupListById(Long.parseLong(user.getUserType()));
			logger.info("Inside:: @@ NotErrors");
			JSONObject resObj = accessControlService.validateUidAndEmail(user.getUserId(), grp.getGroupName());
			if (resObj.get("status") == "success") {
				resp.put("msg", "You are already registered");
				resp.put("success", false);
			} else {
				if (grp != null) {
					user.setUserType(grp.getGroupName());
					user.setCreatedBy(userId);
					user.setCreatedDate(new Date());
					user.setUpload_doc(false);
					String password = PasswordEncryptionUtil.getPasswordString();
					System.out.println(password);
					user.setPassword(sha512.SHA512(password.trim()));
					boolean status = accessControlService.saveUserDetails(user);
					if (status) {
						UserGroupHasRoles userGroup = new UserGroupHasRoles();
						userGroup.setUserId(user.getUserId());
						userGroup.setGroup_id(grp.getGroupId());
						 status = accessControlService.saveUserGroup(userGroup);
						if (status) {
							resp.put("success", true);
							resp.put("msg", "Successfully saved user details");
//							try {
//								if (user.getEmail() != null) {
//									if (!user.getEmail().isEmpty()) {
//										mail.sendMimeMail(user.getEmail(), "Application Details", "Dear " + user.getFirstName()
//											+ ", Your account has been created successfully. Login with your User ID: "
//											+ user.getContactNo() + " and Password: " + password + " as Role "
//											+ grp.getGroupName() + ".<br><br> Thanks <br>Odisha Home Dept");				
//									}
//								}
//							} catch (Exception e) {
//								logger.error("error occured in sms or email send..");
//							}

							try {
								if (user.getContactNo() != null) {
									if (!user.getContactNo().isEmpty()) {
										String templateID = "1077505190132248250";

										smsSend.sendSMS(user.getContactNo(), "Dear Madam/Sir, Your account has been created successfully. Login with your User ID: "
												+ user.getContactNo() + " and Password: " + password + " as Role "
												+ grp.getGroupName() + ". For details please login to the portal. Home Dept., Govt. of Odisha", templateID, "singleSMS");
									}
								}
							} catch (Exception e) {
								logger.error("Error occured in sms " + e.getMessage());
							}
						} else {
							resp.put("success", false);
							resp.put("msg", "Failed to save user details");
						}
					} else {
						resp.put("success", false);
						resp.put("msg", "Failed to save user details");
					}
				}

			}
		
			}

		return resp;
	}

	@RequestMapping(value={"/updateUserDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateUserDetails(@RequestBody @Valid UserModel user, BindingResult bresult) {

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug(user.toString());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasnotErrors"); 
			boolean status = accessControlService.updateUserDetails(user);
			if(status){
				resp.put("success", true);
				resp.put("msg", "Successfully updated user details");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to update user details");
			}
		}


		return resp;
	}

	@RequestMapping(value={"/deleteUser"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteUser(@RequestBody @Valid UserModel user, BindingResult bresult) {//@RequestParam("userId") String userId
		System.out.println("user obj in delete controller"+user);
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("userId: " + user.getUserId());
		logger.debug("userId: " + user.getUserId());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			boolean status = accessControlService.deleteUser(user.getUserId(),user.getUserType());
			if(status){
				resp.put("success", true);
				resp.put("msg", "User Deactivate Successfully");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to Deactivate User");
			}
		}
		return resp;
	}

/*******************************************************************
 * activate user group
 **********************************************************************/
	@RequestMapping(value = { "/activate" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> activateUser(@RequestBody @Valid UserModel userModel, BindingResult bresult)  { 
		System.out.println("Inside activate controller");
		logger.info("Inside:: @@ activateUser Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		if (bresult.hasErrors()) {
			logger.info("Inside:: @@ hasErrors");
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		} else {
					userModel.setUpload_doc(false);
					userModel.setActive(true);
					String password = PasswordEncryptionUtil.getPasswordString();
					System.out.println(password);
					userModel.setPassword(sha512.SHA512(password.trim()));
					boolean status = accessControlService.updateForActivateUser(userModel);
					String role=accessControlService.getroleFromUserType(userModel.getUserType());
					if (status) {
						userModel=accessControlService.getRegisterDetails(userModel.getUserId(),role).get(0);
//							try {
//								if (userModel.getEmail() != null) {
//									if (!userModel.getEmail().isEmpty()) {
//										mail.sendMimeMail(userModel.getEmail(), "Application Details", "Dear "
//												+ userModel.getFirstName()
//												+ "<br> Your user has been created Successfully,Please login through username "
//												+ userModel.getContactNo() + " and password " + password + " as "
//												+ userModel.getUserType() + "<br><br> Thanks <br>e-Bhawan Team");// mail is
//																											// send to
//																											// the
//																											// corresponding
//																											// Email
//									}
//								}
//							} catch (Exception e) {
//								logger.error("error occured in sms or email send..");
//							}

							try {
								if (userModel.getContactNo() != null) {
									if (!userModel.getContactNo().isEmpty()) {
										String templateID = "1107161485663493236";
										smsSend.sendSMS(userModel.getContactNo(), "Dear" + userModel.getFirstName()
												+ "Your user has been created Successfully,Please login through username "
												+ userModel.getContactNo() + " and password " + password + " as "
												+ userModel.getUserType() + ", Odisha Home Dept", templateID, "singleSMS");
									}
								}
							} catch (Exception e) {
								logger.error("Error occured in sms " + e.getMessage());
							}
							
							resp.put("success", true);
							resp.put("msg", "Successfully Activate User ");
					} else {
						resp.put("success", false);
						resp.put("msg", "Failed to activate user");
					}
		
		}

		return resp;
	}


	/*****************************************************
	 * Method to Get Group Details
	 *******************************************************/
	@RequestMapping(value={"/getGroupDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getGroupDetails(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <Group> groupList = accessControlService.getGroupList();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", groupList);

		return resp;
	}

	@RequestMapping(value={"/saveGroupDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveGroupDetails(@ModelAttribute Group group) {

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug(group.toString());

		boolean status = accessControlService.saveGroup(group);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully saved group details");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to save group details");
		}

		return resp;
	}

	@RequestMapping(value={"/updateGroupDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateGroupDetails(@ModelAttribute Group group) {

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug(group.toString());

		boolean status = accessControlService.updateGroup(group);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully updated user details");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to update user details");
		}

		return resp;
	}

	@RequestMapping(value={"/deleteGroup"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteGroup(@RequestBody Group group) {

		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("groupId: " + group.getGroupId());
		logger.debug("groupId: " + group.getGroupId());

		boolean status = accessControlService.deleteGroup(group.getGroupId());
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully deleted group details");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to delete group details");
		}

		return resp;
	}

	@RequestMapping(value={"/getMappedUserDetails/{groupId}"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMappedUserDetails(@PathVariable("groupId") long groupId, HttpServletRequest request, HttpServletResponse response) {
		logger.info(groupId);
		Map<String, Object> resp = new HashMap<String, Object>();

		List<UserModel> lstUser = accessControlService.getMappedUserDetails(groupId);

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUser);

		return resp;
	}

	@RequestMapping(value={"/mapUsersToGroup"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> mapUsersToGroup(HttpServletRequest request, HttpServletResponse response, @RequestBody UserModel[]  selectedUsers, @RequestParam("groupId") long groupId) {

		Map<String, Object> resp = new HashMap<String, Object>();

		boolean status = accessControlService.mapUsersToGroup(groupId, selectedUsers);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully mapped");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to mapped");
		}
		return resp;
	}

	@RequestMapping(value={"/deleteMappedUser/{userId}/{userType}"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteMappedUser(@PathVariable("userId") String userId,@PathVariable("userType") String userType, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug("userId : " + userId);
		logger.info(userId);
		boolean status = accessControlService.deleteMappedUser(userId,userType);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully removed user from group");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to remove user from group");
		}

		return resp;
	}

	/*****************************************Create Resource************************************************/


	/*****************************************************
	 * Method to Add saveUserResourceTable
	 *******************************************************/

	@RequestMapping(value={"/saveUserResourceTable"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUserResourceTable( @RequestBody @Valid ResourceModel resource, BindingResult bresult,HttpServletRequest req,HttpServletResponse res){ //@ModelAttribute ResourceModel resource,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Inside saveUserResourceTable Controller");
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.info("resource----"+resource.toString());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasNoErrors"); 
			String resource_name = resource.getResource_name();
			String resourceType =resource.getResource_type();
			String description = resource.getDescription();
			String type = resource.getType();
			String id = resource.getId();
			long resourceId = Long.parseLong(id); 

			boolean status = accessControlService.saveUserResourceTable(resource_name,resourceType,description,type,resourceId);
			//logger.info("resource_id------"+resource_id+" "+"resource_name-----"+resource_name+" "+"resource_type-----"+resource_type+" "+"description-----"+description+" "+"type-----"+type+"");

			if(status){
				resp.put("success", true);
				resp.put("msg", "Record has been saved Successfully.");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to add resource");
			}
		}
		return resp;
	}

	/*****************************************************
	 * Method to Get getDataResourceTable
	 *******************************************************/
	@RequestMapping(value={"/getDataResourceTable"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDataResourceTable(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <ResourceModel> lstUserDetails = accessControlService.getDataResourceTable();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstUserDetails);

		return resp;
	}


	/*****************************************************
	 * Method to deleteCreateUserResource
	 *******************************************************/
	@RequestMapping(value={"/deleteCreateUserResource"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteCreateUserResource(@RequestBody @Valid ResourceModel resource, BindingResult bresult){ //@RequestParam("resource_id") String resource_id) {

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("resource_id: " + resource.getResource_id());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasNoErrors"); 
			boolean status = accessControlService.deleteCreateUserResource(Long.toString(resource.getResource_id()));
			if(status){
				resp.put("success", true);
				resp.put("msg", "Successfully deleted user details");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to delete user details");
			}
		}

		return resp;
	}

	/*********************************************CREATE ROLE**************************************************/

	/*****************************************************
	 * Method to Add CREATE ROLE
	 *******************************************************/

	@RequestMapping(value={"/saveUserRoleTable"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUserRoleTable(HttpServletRequest req,HttpServletResponse res, 
			@RequestBody @Valid Role role, BindingResult bresult) {
		logger.info("Inside saveUserRoleTable Controller");


		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("role---: " + role.toString());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//				return result.toString();

		}else{
			logger.info("Inside:: @@ hasNotErrors"); 
			String rolename = role.getRoleName();
			String descriptaion = role.getDescription();
			String type = role.getType();
			String id = role.getId();
			boolean status = accessControlService.saveUserRoleTable(rolename,descriptaion,type,id);

			if(status){
				if(type.equalsIgnoreCase("ADD")){
					resp.put("success", true);
					resp.put("msg", "Record has been saved successfully.");
				}else if(type.equalsIgnoreCase("UPDATE")){
					resp.put("success", true);
					resp.put("msg", "Record has been updated successfully.");
				}
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed.");
			}
		}


		return resp;
	}

	/*****************************************************
	 * Method to Get getDataRoleTable
	 *******************************************************/
	@RequestMapping(value={"/getRoleDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRoleDetails(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List <RoleModel> lstRoleDetails = accessControlService.getRoleDetails();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", lstRoleDetails);

		return resp;
	}

	/*****************************************************
	 * Method to deleteCreateUserRole
	 *******************************************************/
	@RequestMapping(value={"/deleteCreateUserRole"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteCreateUserRole(@RequestBody @Valid Role role, BindingResult bresult) {//@RequestParam("role_id") String role_id

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("role_id: " + role.getRoleId());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			boolean status = accessControlService.deleteCreateUserRole( Long.toString(role.getRoleId()));
			if(status){
				resp.put("success", true);
				resp.put("msg", "Successfully deleted user details");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to delete user details");
			}
		}
		return resp;
	}
	@RequestMapping(value={"/getMappedResources"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMappedResources(@RequestBody @Valid Role role, BindingResult bresult) {//@RequestParam("roleId") long roleId

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug("roleId: " + role.getRoleId());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasNotErrors"); 
			List<Resource> resourceList = accessControlService.getMappedResources(role.getRoleId());

			resp.put("success", true);
			resp.put("msg", "Successfully received");
			resp.put("data", resourceList);
		}


		return resp;
	}

	@RequestMapping(value={"/deleteMappedResources"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteMappedResources(@RequestBody @Valid Role role, BindingResult bresult) {//@RequestParam("resourceId") long resourceId, @RequestParam("roleId") long roleId

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug("resourceId: " + role.getResource_id());
		logger.debug("roleId: " + role.getRoleId());

		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			boolean status = accessControlService.deleteMappedResources(role.getRoleId(), role.getResource_id());
			if(status){
				resp.put("success", true);
				resp.put("msg", "Successfully deleted resource");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to delete resource");
			}
		}

		return resp;
	}


	/*****************************************************
	 * Method to Get Resource Details
	 *******************************************************/
	@RequestMapping(value={"/getUnselectedResourceList"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getResourceList(@RequestBody @Valid Role role, BindingResult bresult) {

		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("roleId: " + role.getRoleId());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasNotErrors"); 
			List<Resource> resourceList = accessControlService.getUnselectedResourceList(role.getRoleId());

			resp.put("success", true);
			resp.put("msg", "Successfully received");
			resp.put("data", resourceList);
		}

		return resp;
	}

	@RequestMapping(value={"/mapResourcesToRole.do"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> mapResourcesToRole(HttpServletRequest request, HttpServletResponse response, @RequestBody Resource[]  selectedResources,
			@RequestParam("roleId") String role_Id) {

		Map<String, Object> resp = new HashMap<String, Object>();
		if(role_Id.length() >= 8){
			resp.put("success", false);
			resp.put("msg", "Invalid Request");
			return resp;
		}
		long roleId = Long.parseLong(role_Id);
		boolean status = accessControlService.mapResourcesToRole(roleId, selectedResources);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully mapped");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to mapped");
		}
		return resp;
	}



	/*****************************************************
	 * Method to Get Role Details
	 *******************************************************/
	@RequestMapping(value={"/getRoleList"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRoleList(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resp = new HashMap<String, Object>();

		List<Role> resourceList = accessControlService.getRoleList();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", resourceList);

		return resp;
	}


	@RequestMapping(value={"/getMappedRolesToGroup"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMappedRolesToGroup(@RequestBody @Valid GroupRoleMapModel grmap, BindingResult bresult) {

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug("groupId: " + grmap.getGroupId());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasNotErrors"); 
			List<Role> roleList = accessControlService.getMappedRolesToGroup(grmap.getGroupId());

			resp.put("success", true);
			resp.put("msg", "Successfully received");
			resp.put("data", roleList);
		}


		return resp;
	}

	@RequestMapping(value={"/deleteMappedRole"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteMappedRole(@RequestBody @Valid GroupRoleMapModel grmap, BindingResult bresult) {//@RequestParam("groupId") long groupId, @RequestParam("roleId") long roleId

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug("groupId: " + grmap.getGroupId());
		logger.debug("roleId: " + grmap.getRoleId());
		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors asdasd asd" + grmap.getGroupId()); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
			//			return result.toString();

		}else{
			logger.info("Inside:: @@ hasNotErrors");
			boolean status = accessControlService.deleteMappedRole(grmap.getGroupId(), grmap.getRoleId());
			if(status){
				resp.put("success", true);
				resp.put("msg", "Successfully deleted resource");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to delete resource");
			}
		}

		return resp;
	}

	@RequestMapping(value={"/mapRoleToGroup.do"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> mapRoleToGroup(HttpServletRequest request, HttpServletResponse response, @RequestBody Role[]  selectedRoles, @RequestParam("groupId") long groupId) {

		Map<String, Object> resp = new HashMap<String, Object>();

		boolean status = accessControlService.mapRoleToGroup(groupId, selectedRoles);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully mapped");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to mapped");
		}
		return resp;
	}

	//File download code	
	@RequestMapping(value={"/download"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> download(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		Map<String, Object> resp = new HashMap<String, Object>();		
		File downloadFile = new File("C:/WebDataReportTamplate.xls");		
		if(downloadFile.exists()){
			FileInputStream inputStream = new FileInputStream(downloadFile);	 
			// set content attributes for the response
			response.setContentType("application/octet-stream");
			response.setContentLength((int) downloadFile.length());	 
			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);	 
			// get output stream of the response
			OutputStream outStream = response.getOutputStream();	 
			byte[] buffer = new byte[4096];
			int bytesRead = -1;	 
			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}	 
			inputStream.close();
			outStream.close();	        
			return resp;		
		}else{
			resp.put("success", false);
			resp.put("msg", "File not exist");
			return resp;
		}
	}

	/**************************************
	 * list of mapped parameter to group
	 *************************************/
	@RequestMapping(value = { "/getMappedParametersToGroup" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMappedParametersToGroup(HttpServletRequest req,
			HttpServletResponse res) {// @RequestParam("groupId") long groupId

		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("Inside:: @@ hasNotErrors");

		List<UserModel> mappedParamList = accessControlService.getMappedParametersToGroup();

		resp.put("success", true);
		resp.put("msg", "Successfully received");
		resp.put("data", mappedParamList);

		return resp;
	}

	@RequestMapping(value={"/getParameterValueTable"}, method = RequestMethod.POST)
	public @ResponseBody void getParameterValueTable(@RequestBody @Valid GroupRoleMapModel grmap, BindingResult bresult,HttpServletRequest req,HttpServletResponse  res) throws JSONException, IOException {
		logger.info("inside getParameterValueTable method");
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		JSONObject mappedParamList = new JSONObject();
		logger.debug("tableName: " + grmap.getTableName());
		logger.debug("paramName: " + grmap.getParamName());
		logger.debug("paramId: " + grmap.getParamId());
		logger.info(grmap.getTableName() + "::" +  grmap.getParamName() + "::" + grmap.getParamId() );
		if(bresult.hasErrors()){
			logger.info("Inside:: @### hasErrors"); 
			mappedParamList.put("success", false);
			mappedParamList.put("msg", "Invalid Request");

		}else{
			mappedParamList = accessControlService.getParameterValueTable(grmap.getTableName(),grmap.getParamName(),grmap.getParamId());
			mappedParamList.put("success", true);
			mappedParamList.put("msg", "Successfully received");
		}
		out.print(mappedParamList);
	}
	@RequestMapping(value={"/mapParamWithValues"},method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> mapParamvaluesToParam(HttpServletRequest req,HttpServletResponse res,@RequestParam("selectedGroupId") long selectedGroupId,
			@RequestParam ("paramName") String paramName) {

		String[] myJsonData = req.getParameterValues("selectedParamValues[]");
		logger.info(myJsonData.length);
		List<String> paramList = new ArrayList<String>();
		for (int i=0; i < myJsonData.length; i++) {
			paramList.add(myJsonData[i]); 
		}

		Map<String, Object> resp = new HashMap<String, Object>();

		boolean status = accessControlService.mapParamvaluesToParam(paramList,selectedGroupId,paramName);
		if(status){
			resp.put("success", true);
			resp.put("msg", "Successfully mapped");
		}else{
			resp.put("success", false);
			resp.put("msg", "Failed to map");
		}
		return resp;
	}



	@RequestMapping (value={"/deleteMappedParam"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteMappedParam (@RequestBody @Valid GroupRoleMapModel grmap, BindingResult bresult) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.debug("sl_no: " + grmap.getSl_no());

		if(bresult.hasErrors()){
			logger.info("Inside:: @@ hasErrors"); 
			resp.put("success", false);
			resp.put("status", "failure");
			resp.put("msg", "Invalid Request.");
		}else{
			logger.info("Inside:: @@ hasNotErrors"); 
			boolean status = accessControlService.deleteMappedParam( grmap.getSl_no());
			if(status){
				resp.put("success", true);
				resp.put("msg", "Successfully deleted resource");
			}else{
				resp.put("success", false);
				resp.put("msg", "Failed to delete resource");
			}
		}
		return resp;
	}


	/*******************************************
	 * method to get all mapped user datails
	 * @author sankar patra
	 * @Date 25-08-2018
	 *****************************************/
	@RequestMapping(value={"/getMappedUserDetails"}, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMappedUserDetails(HttpServletRequest req,HttpServletResponse res) {
		String methodName = "getMappedUserDetails";
		logger.info("Entry controller--->"+methodName);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<MaappedUserDetailsResponseModel> lstMappedUserDetails = accessControlService.getListMappedUserDetails();
		resp.put("data",lstMappedUserDetails);
		logger.info("Exit controller--->"+methodName);
		return resp;
	}


	/**************************************************
	 * get all location details according to role
	 *******************************************************/

	@RequestMapping(value={"/{selectedGroupId}/getAllLocationAccordingToRole"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllLocationAccordingToRole(@PathVariable("selectedGroupId") long selectedGroupId,HttpServletRequest req,
			HttpServletResponse res) {
		// @RequestParam("roleId") long roleId
		//long groupId = Long.parseLong(req.getParameter("selectedGroupId"));
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("groupId: " + selectedGroupId);

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = accessControlService.getAllLocationAccordingToRole(selectedGroupId);
		resp.put("data",lstLocation);

		return resp;
	}

	/*************************************
	 * method to map location to user id
	 **************************************/
	@RequestMapping(value={"/{cityId}/{mapUserId}/{mapUserType}/mapLocationToUserForBlock"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> mapLocationToUserForBlock(@PathVariable("cityId") String cityId,
			@PathVariable("mapUserId") String mapUserId, @PathVariable("mapUserType") String mapUserType, HttpServletRequest req,HttpServletResponse res,Principal auth) {
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("status", "Failure");
		resp.put("msg", "Already this location Mapped !!!");
		logger.info("userid:->"+mapUserId + ", userType:->"+mapUserType);
		logger.info("location id:->"+cityId);
		boolean status = accessControlService.mapLocationToUser(mapUserId,cityId,"B",auth, mapUserType);
		if(status) {
			resp.put("status", "Success");
			resp.put("msg", "Location Mapped successfully");
		}

		return resp;
	}

	/*************************************
	 * method to map location to user id
	 **************************************/
	@RequestMapping(value={"/{cityId}/{mapUserId}/{mapUserType}/mapLocationToUser"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> mapLocationToUser(@PathVariable("cityId") String cityId,
			@PathVariable("mapUserId") String mapUserId, @PathVariable("mapUserType") String mapUserType, HttpServletRequest req,HttpServletResponse res,Principal auth) {
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("status", "Failure");
		resp.put("msg", "Already this Location Mapped !!!");
		logger.info("userid:->"+mapUserId  + ", userType:->"+mapUserType);
		logger.info("location id:->"+cityId);
		boolean status = accessControlService.mapLocationToUser(mapUserId,cityId,"C",auth, mapUserType);
		if(status) {
			resp.put("status", "Success");
			resp.put("msg", "Location Mapped successfully");
		}

		return resp;
	}

	@RequestMapping(value={"/getAllDistrict"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllDistrict(HttpServletRequest req,HttpServletResponse res) {
		// @RequestParam("roleId") long roleId
		//long groupId = Long.parseLong(req.getParameter("selectedGroupId"));
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstDistrict = accessControlService.getAllDistrict();
		resp.put("data",lstDistrict);
		return resp;
	}

	@RequestMapping(value={"/{districtcode}/getAllBlockLocationAccordingToRole"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllBlockLocationAccordingToRole(HttpServletRequest req,
			HttpServletResponse res,@PathVariable("districtcode") String districtcode) {
		// @RequestParam("roleId") long roleId
		//long groupId = Long.parseLong(req.getParameter("selectedGroupId"));
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstDistrict = accessControlService.getAllBlockByDistrict(districtcode);
		resp.put("data",lstDistrict);
		return resp;
	}

	/**************************************************
	 * get all location details according to role
	 *******************************************************/

	@RequestMapping(value={"/{selectedUserId}/{userType}/getAllLocationAccordingToUser"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllLocationAccordingToUser(@PathVariable("selectedUserId") String selectedUserId,
																		   @PathVariable("userType") String userType,
																		   HttpServletRequest req,
			HttpServletResponse res) {
		// @RequestParam("roleId") long roleId
		//long groupId = Long.parseLong(req.getParameter("selectedGroupId"));
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("userId: " + selectedUserId + ", userType: " + userType);

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstLocation = accessControlService.getAllLocationAccordingToUser(selectedUserId, userType);
		resp.put("data",lstLocation);

		return resp;
	}

	@RequestMapping(value={"/deleteLoc/{id}"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteLoc(@PathVariable("id") Integer id,HttpServletRequest req,
			HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();
		logger.debug("id: " + id);

		logger.info("Inside:: @@ hasNotErrors");
		Boolean lstLocation = accessControlService.deleteLoc(id);
		if(lstLocation) {
			resp.put("success",lstLocation);
			resp.put("msg","Location deleted successfully");
		}else {
			resp.put("success",lstLocation);
			resp.put("msg","Something went wrong");
		}
		return resp;
	}
	
	@RequestMapping(value={"/getAllState"}, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllState(HttpServletRequest req,HttpServletResponse res) {
		Map<String, Object> resp = new HashMap<String, Object>();

		logger.info("Inside:: @@ hasNotErrors");
		List<AllLocationModel> lstState = accessControlService.getAllState();
		resp.put("data",lstState);
		return resp;
	}
	
	@RequestMapping(value = { "/dashboardCount" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> dashboardCount(HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws Exception{
		String userId = principal.getName();
		logger.info("request received by user " + userId);
		Map<String, Object> resp = new HashMap<String, Object>();
		AdminDashoardCount dashbrdCount = accessControlService.getDashboardCount();
		logger.info(dashbrdCount.toString());
		resp.put("data", dashbrdCount);
		resp.put("success", true);
		resp.put("msg", "Successfully received");
		return resp;
	}
		
}
