package com.stl.ngms.setting.dao.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stl.ngms.changepassword.controller.ChangePasswordModel;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.model.AuditlogRequest;
import com.stl.ngms.common.model.ChangePasswordWithoutAuthModel;
import com.stl.ngms.common.model.LoginUserDetailsModel;
import com.stl.ngms.common.model.UserRegistrationModel;
import com.stl.ngms.orrisaone.model.OrisaoneModel;
import com.stl.ngms.setting.dao.AccessControlDao;
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
import com.stl.ngms.setting.model.UserGroupParameter;
import com.stl.ngms.setting.model.UserModel;

@Repository("accessControlDao")
public class AccessControlDaoImpl implements AccessControlDao {
	
final static Logger logger = Logger.getLogger(AccessControlDaoImpl.class);
	
	@Autowired private SessionFactory sessionFactory ;
	
	@Autowired JdbcTemplate jdbcTemplate;
	
	@Autowired private Properties adminQueries;
	
	@Override
	public List<UserModel> getUserDetails() {
		String sql = adminQueries.getProperty("getUserListSQL");
		List<UserModel> users = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel user = new UserModel();
				user.setUserId(rs.getString(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setContactNo(rs.getString(5));
				user.setUserType(rs.getString(6));
				user.setFirstLogin(rs.getBoolean(7));
				user.setAdmin(rs.getBoolean(8));
				user.setActive(rs.getBoolean(9));
				user.setCreatedBy(rs.getString(10));
				user.setMenuVisible(rs.getBoolean(12));
				return user;
			}
		});
		return users;
	}	
	
	@Override
	public boolean saveUserDetails(UserModel user) {
		System.out.println(user.getUserId());
		String sql = adminQueries.getProperty("saveUserDetailsSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				user.getUserId(), 
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getContactNo(),
				user.getPassword(),
				user.getUserType(),
				user.isFirstLogin(),
				user.isAdmin(),
				user.isActive(),
				user.getCreatedBy(),
				user.getCreatedDate(),
				user.isMenuVisible(),
				user.isOtpLogin(),
				user.getDocType(),
				user.getDocPath(),
				user.isUpload_doc()
		});		
		return true;
	}
	@Override
	public boolean saveOrisaoneDetails(OrisaoneModel oomodel) {
		
		String sql = adminQueries.getProperty("saveorisaoneDetailsSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				oomodel.getServiceId(),
				oomodel.getRequestId(),
				oomodel.getSuccessurl(),
				oomodel.getCancelurl()
		});		
		return true;
	}
	
	@Override
	public boolean saveGuestDetails(UserModel user) {
		System.out.println(user.getUserId());
		String sql = adminQueries.getProperty("saveGuestDetailsSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				user.getUserId(), 
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getContactNo(),
				user.getPassword(),
				user.getUserType(),
				user.isFirstLogin(),
				user.isAdmin(),
				user.isActive(),
				user.getCreatedBy(),
				user.getCreatedDate(),
				user.isMenuVisible(),
				user.isOtpLogin(),
				user.getDocType(),
				user.getDocPath(),
				user.isUpload_doc()
		});		
		return true;
	}
	
	@Override
	public boolean updateUserDetails(UserModel user) {
		String sql = adminQueries.getProperty("updateUserDetailsSQL");
		jdbcTemplate.update(sql, new Object[] {
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getContactNo(),	
				user.isOtpLogin(),
				user.getUserId()
		});		
		return true;
	}
	
	@Override
	public boolean updateGuestDetails(UserModel user) {
		UserModel userDet = getUserByUserId(user.getUserId());
		if(user != null) {
			if(user.getDocPath() == null) {
				user.setDocPath(userDet.getDocPath());
			}
		}
		String sql = adminQueries.getProperty("updateGuestDetailsSQL");
		jdbcTemplate.update(sql, new Object[] {
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getContactNo(),	
				user.isOtpLogin(),
				user.getPassword(),
				user.getUserId()
		});		
		return true;
	}
	
	@Override
	public boolean deleteUser(String userId) {
		System.out.println("To delete"+ userId);
		String sql = adminQueries.getProperty("deleteUserSQL");
		jdbcTemplate.update(sql, new Object[] {	false, userId });		
		return true;
	}
	
	@Override
	public boolean saveUserGroup(UserGroupHasRoles user) {
		System.out.println(user.getUserId());
		String sql = adminQueries.getProperty("saveUserGroupDetailsSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				user.getGroup_id(),
				user.getUserId()
		});		
		return true;
	}
	
	
	@Override
	public List<Group> getGroupList() {
		String sql = adminQueries.getProperty("getGroupListSQL");
		List<Group> groups = jdbcTemplate.query(sql, new RowMapper<Group>() {
			@Override
			public Group mapRow(ResultSet rs, int rownumber) throws SQLException {
				Group group = new Group();
				group.setGroupId(rs.getLong(1));
				group.setGroupName(rs.getString(2));
				group.setDescription(rs.getString(3));
				group.setActive(rs.getBoolean(4));
				group.setNoOfUsers(rs.getInt(5));
				return group;
			}
		});
		return groups;
	}	
	
	@Override
	public Group getGroupListById(long groupId) {
		Group group = null;
		String sql = adminQueries.getProperty("getGroupListByIdSQL");
		List<Group> groups = jdbcTemplate.query(sql, new RowMapper<Group>() {
			@Override
			public Group mapRow(ResultSet rs, int rownumber) throws SQLException {
				Group group = new Group();
				group.setGroupId(rs.getLong(1));
				group.setGroupName(rs.getString(2));
				group.setDescription(rs.getString(3));
				group.setActive(rs.getBoolean(4));
				group.setNoOfUsers(rs.getInt(5));
				return group;
			}
		});
		if(groups != null) {
			if(groups.size() != 0) {
				group = groups.get(0);
			}
		}
		return group;
	}
	
	@Override
	public boolean saveGroup(Group group) {
		String sql = adminQueries.getProperty("saveGroupSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				group.getGroupName(), 
				group.getDescription(),
				group.isActive()
		});		
		return true;
	}
	
	@Override
	public boolean updateGroup(Group group) {
		String sql = adminQueries.getProperty("updateGroupSQL");
		jdbcTemplate.update(sql, new Object[] {
				group.getGroupName(),
				group.getDescription(),				 
				group.getGroupId()
		});		
		return true;
	}
	
	@Override
	public boolean deleteGroup(long groupId) {
		System.out.println("groupId"+groupId);
		String sql = adminQueries.getProperty("deleteGroupSQL");
		jdbcTemplate.update(sql, new Object[] {false,groupId});		
		return true;
	}

	@Override
	public List<UserModel> getMappedUserDetails(long groupId) {
		String sql = adminQueries.getProperty("getMappedUserListSQL");
		List<UserModel> users = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel user = new UserModel();
				user.setUserId(rs.getString(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setContactNo(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setUserType(rs.getString(7));
				user.setFirstLogin(rs.getBoolean(8));
				user.setAdmin(rs.getBoolean(9));
				user.setActive(rs.getBoolean(10));
				user.setCreatedBy(rs.getString(11));
				user.setMenuVisible(rs.getBoolean(13));
				return user;
			}
		},groupId);
		return users;
	}
	
	@Override
	public boolean mapUsersToGroup(long groupId, UserModel[] users) {
		String sql = adminQueries.getProperty("mapUsersToGroupSQL");		
		for(UserModel user : users){
			jdbcTemplate.update(sql, new Object[] {
					groupId,
					user.getUserId()
			});		
		}
		return true;
	}
	
	@Override
	public boolean deleteMappedUser(String userId) {
		String sql = adminQueries.getProperty("deleteMappedUserSQL");		
		jdbcTemplate.update(sql, new Object[] {userId});	
		return true;
	}
	
	@Override
	public List<Resource> getResourceList() {
		String sql = adminQueries.getProperty("getResourceListSQL");
		List<Resource> resources = jdbcTemplate.query(sql, new RowMapper<Resource>() {
			@Override
			public Resource mapRow(ResultSet rs, int rownumber) throws SQLException {
				Resource resource = new Resource();
				resource.setResourceId(rs.getLong(1));
				resource.setResourceName(rs.getString(2));
				resource.setResourceType(rs.getString(3));
				resource.setDescription(rs.getString(4));
				resource.setActive(rs.getBoolean(5));
				return resource;
			}
		});
		return resources;
	}
	
	/*****************************************Create Resource************************************************/	
	@Override
	public boolean saveUserResourceTable(String resource_name, String resourceType,String description, String type,long resourceId) {
		System.out.println("Inside saveUserResourceTable daoimpl............");
		if("ADD".equalsIgnoreCase(type)){
			System.out.println("Inside type Add");
			String sql = adminQueries.getProperty("saveResourceSQL");
			
			try {
				jdbcTemplate.update(sql, new Object[] { 
						resource_name,resourceType,description,true
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(type)){
			System.out.println("Inside type Update");
			String sql = adminQueries.getProperty("updateResourceSQL");
			
			try {
				jdbcTemplate.update(sql, new Object[] { 
						resource_name,resourceType,description,resourceId
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
			
		return true;
	}
	
	
	@Override
	public List<ResourceModel> getDataResourceTable() {
		String sql = adminQueries.getProperty("getResourceListSQL");
		System.out.println("sql:"+sql);
		List<ResourceModel> resourceModal = jdbcTemplate.query(sql, new RowMapper<ResourceModel>() {
			@Override
			public ResourceModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				ResourceModel resource = new ResourceModel();
				resource.setResource_id(rs.getLong(1));
				resource.setResource_name(rs.getString(2));
				resource.setResource_type(rs.getString(3));
				resource.setDescription(rs.getString(4));
				return resource;
			}
		});
		return resourceModal;
	}
	
	@Override
	public boolean deleteCreateUserResource(String resource_id) {
		String sql = adminQueries.getProperty("deleteResourceSQL");
		int id = Integer.parseInt(resource_id);
		jdbcTemplate.update(sql, new Object[] {false,id});		
		return true;
	}


	@Override
	public List<Resource> getMappedResources(long roleId) {
		String sql = adminQueries.getProperty("getMappedResourcesSQL");
		List<Resource> resources = jdbcTemplate.query(sql, new RowMapper<Resource>() {
			@Override
			public Resource mapRow(ResultSet rs, int rownumber) throws SQLException {
				Resource resource = new Resource();
				resource.setResourceId(rs.getLong(1));
				resource.setResourceName(rs.getString(2));
				resource.setResourceType(rs.getString(3));
				resource.setDescription(rs.getString(4));
				resource.setActive(rs.getBoolean(5));
				return resource;
			}
		}, roleId);
		return resources;
	}
	
	@Override
	public boolean deleteMappedResources(long roleId, long resourceId) {
		String sql = adminQueries.getProperty("deleteMappedResourceSQL");
		jdbcTemplate.update(sql, new Object[] {	roleId, resourceId });		
		return true;
	}
	
	@Override
	public boolean mapResourcesToRole(long roleId, Resource[] resources) {
		String sql = adminQueries.getProperty("mapResourcesToRoleSQL");		
		for(Resource resource : resources){
			jdbcTemplate.update(sql, new Object[] {
					roleId,
					resource.getResourceId()
			});		
		}
		return true;
	}
	
	/*********************************************CREATE ROLE**************************************************/	
	@Override
	public boolean saveUserRoleTable(String role_name, String description,
			String type, String id) {
		System.out.println(role_name);
		System.out.println(description);
		System.out.println(id);
		System.out.println("Inside saveUserRoleTable daoimpl............");
		
		if("ADD".equalsIgnoreCase(type)){
			System.out.println("Inside type Add");
			String sql = adminQueries.getProperty("saveRoleSQL");
			
			try {
				jdbcTemplate.update(sql, new Object[] { 
						role_name,description,true
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(type)){
			System.out.println("Inside type Update");
			int role_id  = Integer.parseInt(id);
			String sql = adminQueries.getProperty("updateRoleSQL");
			System.out.println(sql);
			try {
				jdbcTemplate.update(sql, new Object[] { 
						role_name,description,role_id
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
			
		return true;
	}
	
	@Override
	public boolean deleteCreateUserRole(String role_id) {
		String sql = adminQueries.getProperty("deleteRoleSQL");
		System.out.println(role_id);
		int id = Integer.parseInt(role_id);
		jdbcTemplate.update(sql, new Object[] {	
				false,id 
		});		
		return true;
	}
	
	
	
	@Override
	public List<RoleModel> getRoleDetails() {
		String sql = adminQueries.getProperty("getRoleListSQL");
		System.out.println("sql:"+sql);
		List<RoleModel> RoleModel = jdbcTemplate.query(sql, new RowMapper<RoleModel>() {
			@Override
			public RoleModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				RoleModel Role = new RoleModel();
				Role.setrole_id(rs.getString(1));
				Role.setrole_name(rs.getString(2));
				Role.setdescription(rs.getString(3));
				return Role;
				
			}
		});
		return RoleModel;
	}
	
	
	@Override
	public List<Role> getRoleList() {
		String sql = adminQueries.getProperty("getRoleListSQL");
		List<Role> roles = jdbcTemplate.query(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int rownumber) throws SQLException {
				Role role = new Role();
				role.setRoleId(rs.getLong(1));
				role.setRoleName(rs.getString(2));
				role.setDescription(rs.getString(3));
				role.setActive(rs.getBoolean(4));
				return role;
			}
		});
		return roles;
	}

	
	
	@Override
	public List<Role> getMappedRolesToGroup(long groupId) {
		String sql = adminQueries.getProperty("getMappedRolesToGroupSQL");
		List<Role> roles = jdbcTemplate.query(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int rownumber) throws SQLException {
				Role role = new Role();
				role.setRoleId(rs.getLong(1));
				role.setRoleName(rs.getString(2));
				role.setDescription(rs.getString(3));
				role.setActive(rs.getBoolean(4));
				return role;
			}
		}, groupId);
		return roles;
	}
	
	@Override
	public boolean deleteMappedRole(long groupId, long roleId) {
		String sql = adminQueries.getProperty("deleteMappedRoleSQL");
		try {
			jdbcTemplate.update(sql, new Object[] {	groupId, roleId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	@Override
	public boolean mapRoleToGroup(long groupId, Role[] roles) {
		String sql = adminQueries.getProperty("mapRoleToGroupSQL");		
		for(Role role : roles){
			jdbcTemplate.update(sql, new Object[] {
				groupId,
				role.getRoleId()
			});		
		}
		return true;
	}

	@Override
	public List<UserModel> getMappedParametersToGroup(long groupId) {
		String sql = adminQueries.getProperty("getMappedParametersToGroupSQL");
		
		List<UserModel> mappedParams = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel mappedParam = new UserModel();
				/*mappedParam.setSlno(rs.getLong(1));
				mappedParam.setParam_name(rs.getString(2));
				mappedParam.setParam_val(rs.getString(3));*/
				mappedParam.setUserId(rs.getString(1));
				mappedParam.setFirstName(rs.getString(2));
				mappedParam.setEmail(rs.getString(3));
				mappedParam.setContactNo(rs.getString(4));
				return mappedParam;
			}
		}, groupId);
		//user_id,first_name,email_id,contact_no
		return mappedParams;
	}

	@Override
	public JSONObject getParameterValueTable(String tableName, String paramName,
			long paramId)  {
		      JSONArray dataArr = new JSONArray();
		      JSONArray colArr = new JSONArray();
		      JSONObject retObj = new JSONObject();
		      ArrayList<String> strColName = new ArrayList<String>();
		      JSONObject obj = null;
			  String sql = "select * from "+tableName+"";
			 // System.out.println("sql is:"+sql);
			  List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new  Object[] {}, new int[] {});
			  for (Map<String, Object> row : list) {
				  List<String> keys = new ArrayList<String>(row.keySet()) ;
				  obj = new JSONObject();
				  for (int j = 0;j<keys.size();j++) {
					strColName.add(keys.get(j));
					try {
						obj.put(keys.get(j), row.get(keys.get(j)));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }//end of inner for loop
					 dataArr.put(obj);
		        } //outer loop  
			  HashSet<String> hs=new HashSet<>(strColName);
			  List<String> col = new ArrayList<String>(hs);
			  for(int i =0;i<col.size();i++) {
				  colArr.put(col.get(i));
			  }
			  try {
				retObj.put("data", dataArr);
				retObj.put("col_name", colArr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return retObj;
	}

	@Override
	public boolean mapParamvaluesToParam(List<String> paramList,
			long selectedGroupId, String paramName) {
			List<UserGroupParameter> arList = new ArrayList<UserGroupParameter>();
			for(int i=0;i<paramList.size();i++) {
				UserGroupParameter userGrpParams = new UserGroupParameter();
				userGrpParams.setGroup_id(selectedGroupId);
				userGrpParams.setParam_name(paramName);
				userGrpParams.setParam_val(paramList.get(i));
				arList.add(userGrpParams);
			}
		
			String sql = adminQueries.getProperty("mapParamvaluesToParamSql");
			for(UserGroupParameter ugp : arList){
				jdbcTemplate.update(sql, new Object[] {
						ugp.getGroup_id(),
						ugp.getParam_name(),
						ugp.getParam_val()
					});	
			}
		return true;
	}

	@Override
	public boolean deleteMappedParam(long sl_no) {
		String sql = adminQueries.getProperty("deleteMappedParamSQL");
		jdbcTemplate.update(sql, new Object[] {	sl_no });		
		return true;
		
	}

	//commented by lusi on Dt-26-10-2017
/*	@Override
	public UserModel getUserInfo(String user_id, String password) {
		logger.info("user_id-->"+user_id+" Password-->"+password);
		String sql = adminQueries.getProperty("getUserInfoSql");
		UserModel userInfo = null;
		List<UserModel> userList = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel users = new UserModel();
				users.setUserId(rs.getString(1));
				users.setFirstName(rs.getString(2));
				users.setLastName(rs.getString(3));
				users.setEmail(rs.getString(4));
				users.setContactNo(rs.getString(5));
				users.setUserType(rs.getString(7));
				return users;
			}
		}, user_id,password);
		if(null != userList && !userList.isEmpty()){
			userInfo = userList.get(0);
		}
		return userInfo;
	}*/

	@Override
	public List<Long> getGroupListByUserId(String uname) {
		String sql = adminQueries.getProperty("getGroupIdListByUserIdSql");
		List<Long> groupIdList = jdbcTemplate.query(sql, new RowMapper<Long>() {
			@Override
			public  Long mapRow(ResultSet rs, int rownumber) throws SQLException {
				Long groupId = rs.getLong(1);
				return groupId;
			}
		}, uname);
		
		return groupIdList;
	}

	@Override
	public JSONObject getResourcesAndParamList(
			List<Long> lstGroupId) {
		JSONObject retObj = new JSONObject();
		JSONArray jArrResourceName = new JSONArray();
		String getListResourceNameSql = adminQueries.getProperty("getGroupIdListByUserIdSql");
		List<Map<String, Object>> resourceList = jdbcTemplate.queryForList(getListResourceNameSql,lstGroupId.toString().replace("[", "(").replace("]", ")"));
		 for (Map<String, Object> row : resourceList) {       
			 for(int i =0;i<row.size();i++) {
				 jArrResourceName.put(row.get(i));
			 }
		 }
		try {
			retObj.put("resourceName", jArrResourceName);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retObj;
	}
	
	/**
	 * This method will give the mapped roles to a particular
	 * user by its user id
	 * @param userId 
	 * @return List<String>
	 * @date 04-03-2017
	 */
	@Override
	public List<String> getMappedRolesToUser(final String userId) {
		final String sql = adminQueries.getProperty("getMappedRolesToUserSQL");
		List<String> roles = (List<String>)jdbcTemplate.query(new PreparedStatementCreator() {
			
		      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		          return connection
		              .prepareStatement(sql);
		        }
		      }, new PreparedStatementSetter() {
		        public void setValues(PreparedStatement preparedStatement) throws SQLException {
		          preparedStatement.setString(1, userId);
		        }
		      }, new ResultSetExtractor<List<String>>() {
		        public List<String> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		        	List<String> roleList = new ArrayList<>();
		          while(resultSet.next()) {
		        	  roleList.add(resultSet.getString(1));
		          }
		          return roleList;
		        }
		      });
		System.out.println("Entry getMappedRolesToUser"+roles);
		return roles;
	}//end of getMappedRolesToUser
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
		
		StringBuffer sb = new StringBuffer();
		if(allowedRoles != null && !allowedRoles.isEmpty()){
			int i = 0 ; 
			for(String role : allowedRoles){
				i++;
				sb.append(role);
				if(i < allowedRoles.size()){
					sb.append(",");
				}
			}
		}
		final String param = sb.toString();
		final String sql = adminQueries.getProperty("getUniqueResourcesForRolesSQL");
		
		return (List<ResourceVO>)jdbcTemplate.query(new PreparedStatementCreator() {
		      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		          return connection
		              .prepareStatement(sql);
		        }
		      }, new PreparedStatementSetter() {
		        public void setValues(PreparedStatement preparedStatement) throws SQLException {
		          preparedStatement.setInt(1, Integer.parseInt(param));
		        }
		      }, new ResultSetExtractor<List<ResourceVO>>() {
		        public List<ResourceVO> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		        	List<ResourceVO> resourceList = new ArrayList<>();
		        	ResourceVO resource = null ;
		        	//rs.resource_id, rs.resource_name, rs.resource_type, rs.description, rs.is_active
		          while(resultSet.next()) {
		        	  resource = new ResourceVO();
		        	  resource.setResourceId(resultSet.getLong(1));
		        	  resource.setResourceName(resultSet.getString(2));
		        	  resource.setResourceType(resultSet.getString(3));
		        	  resource.setDescription(resultSet.getString(4));
		        	  resource.setActive(resultSet.getBoolean(5));
		        	  resourceList.add(resource);
		          }
		          return resourceList;
		        }
		      });
		
	}//end of getUniqueResourcesForRoles
	/**
	 * Retrieve all the available additional parameters to the user/user_group
	 * with the provided values to these parameters for dynamic data control
	 * @param userId
	 * @return HashMap<String, List<String>>
	 * @date 06-03-2017
	 */
	@Override
	public HashMap<String, List<String>> getDataControlDetails(final String userId) {
		final String sql = adminQueries.getProperty("getDataControlSQL");
		HashMap<String, List<String>> hmParamVal = (HashMap<String, List<String>>)jdbcTemplate.query(new PreparedStatementCreator() {
		      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		          return connection
		              .prepareStatement(sql);
		        }
		      }, new PreparedStatementSetter() {
		        public void setValues(PreparedStatement preparedStatement) throws SQLException {
		          preparedStatement.setString(1, userId);
		        }
		      }, new ResultSetExtractor<HashMap<String, List<String>>>() {
		        public HashMap<String, List<String>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		        	HashMap<String, List<String>> hmParamVal = new HashMap<>();
		        	String keyParam = "";
		        	String tempPrevParam = "";
		        	List<String> listVals = null;
		        	String value = "";
		          while(resultSet.next()) {
		        	  keyParam = resultSet.getString(1);
		        	  value = resultSet.getString(2);
		        	  if(StringUtils.isEmpty(tempPrevParam) || !StringUtils.equals(tempPrevParam, keyParam)){
		        		  listVals = new ArrayList<>();
		        	  }
		        	  tempPrevParam = resultSet.getString(1);
		        	  listVals.add(value);
		        	  hmParamVal.put(keyParam, listVals);
		          }
		          return hmParamVal;
		        }
		      });
		return hmParamVal;
	}//end of getDataControlDetails
	
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	@Override
	public JSONObject validateUidAndEmail(String userid) {
		String methodName = "validateUidAndEmail";
		logger.info("EntryDao--------------->"+methodName);
		JSONObject retObj = new JSONObject();
		String sql = "select * from adminr.user where user_id=? and is_otp_login = false";
		retObj = jdbcTemplate.query(sql, new Object[] {userid}, new ResultSetExtractor<JSONObject>(){
			@Override
			public JSONObject extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONObject retObj1 = new JSONObject();
				if(rs.next()) {
					System.out.println("Email " + rs.getString("email_id"));
					try {
						retObj1.put("email", rs.getString("email_id"));
						retObj1.put("status", "success");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else {
					try {
						retObj1.put("status", "falure");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return retObj1;
			}
		});
		logger.info("Exitdao--------------->"+methodName);
		return retObj;
	}
	/************************************************
	 * After sending the mail successfully the corresponding userid and captcha 
	 * will save in tha database table
	 ************************************************/
	@Override
	public boolean saveOtpInDb(String captchaCode, String userid) {
		String methodName = "saveOtpInDb";
		logger.info("EntryDao--------------->"+methodName);
		logger.info("OTP"+captchaCode);
		boolean state = false;
		String sql = "INSERT INTO adminr.otp (key,otp_secret) select '"+userid+"','"+captchaCode+"'  WHERE NOT EXISTS (SELECT otp_secret FROM adminr.otp WHERE key = '"+userid+"')";
		int status = jdbcTemplate.update(sql, new Object[] {}, new int[] {});
		System.out.println(status);
		if(status == 1) {
			state = true;
		}
		if(status < 1 ) {
			String sql2 = "update adminr.otp set otp_secret='"+captchaCode+"' where key='"+userid+"'";
			jdbcTemplate.update(sql2);	
			state= true;
		}
		logger.info("Exitdao--------------->"+methodName);
		return state;
	}
	/**********************************************
	 * Method that will take the otp and userid as input and 
	 * check in the table that wheather this otp is matching for this userid or not
	 ***************************************************/
	@Override
	public boolean validateOtp(String uid, String input_otp) {
		String methodName = "validateOtp";
		logger.info("EntryDao--------------->"+methodName);
		String sql = "select * from adminr.otp where key=? and otp_secret=? ";
		boolean status = jdbcTemplate.query(sql, new Object[] {uid,input_otp}, new ResultSetExtractor<Boolean>(){
			@Override
			public Boolean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return true;
				}else {
					return false;
				}
			}
		});
		logger.info("Exitdao--------------->"+methodName);
		return status;
	}
	/**********************************************************************
	 * method thaqt will check wheather any otp is present or not if present update or else insert
	 * @input:-uid
	 ************************************************/
	@Override
	public boolean resendOtp(String uid, String captchaCode) {
		String methodName = "resendOtp";
		logger.info("EntryDao--------------->"+methodName);
		logger.info("OTP"+captchaCode);
		boolean state = true;
		String sql = "INSERT INTO adminr.otp (key,otp_secret) select '"+uid+"','"+captchaCode+"'  WHERE NOT EXISTS (SELECT otp_secret FROM adminr.otp WHERE key = '"+uid+"')";
		int status = jdbcTemplate.update(sql, new Object[] {}, new int[] {});
		System.out.println(status);
		if(status < 1 ) {
			String sql2 = "update adminr.otp set otp_secret='"+captchaCode+"' where key='"+uid+"'";
			jdbcTemplate.update(sql2);	
			state= true;
		}
		logger.info("Exitdao--------------->"+methodName);
		return state;
	}
	
	/***************************************************************************
	 * Method will set the new password of a corresponding UserId
	 * Input is password and userid
	 ***************************************************************************/
	@Override
	public boolean savePassword(String uid, String encryptPassword) {
		String methodName = "savePassword";
		logger.info("EntryDao--------------->"+methodName);
		String sql = "update adminr.user set password=? where user_id=?";
		jdbcTemplate.update(sql, new Object[] {
				encryptPassword,uid
		});		
		logger.info("Exitdao--------------->"+methodName);
		return true;
	}
	/*
	 * Used for security authentication
	 * @since 23-10-2017
	 * @author Lusi Dash
	 */
	@Override
	public UserModel getUserByUserId(String userName) {
		String sql = adminQueries.getProperty("getUserByUserId");
		System.out.println("Entry AccessControlDaoImpl..........");
		UserModel user = null;
		List<UserModel> userList = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel user = new UserModel();
				user.setUserId(rs.getString("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email_id"));
				user.setContactNo(rs.getString("contact_no"));
				user.setPassword(rs.getString("password"));
				user.setUserType(rs.getString("user_type"));
				user.setActive(rs.getBoolean("is_active"));
				user.setOtpLogin(rs.getBoolean("is_otp_login"));
				user.setDocType(rs.getString("doc_type"));
				user.setDocPath(rs.getString("doc_path"));
				user.setUpload_doc(rs.getBoolean("upload_doc"));
				return user;
			}
		}, userName);
		if(null != userList && !userList.isEmpty()){
			user = userList.get(0);
		}
		return user;
	}

	
	
	/*******************************************
	 * method to get all mapped user datails
	 * @author sankar patra
	 * @Date 25-08-2018
	 *****************************************/
	@Override
	public List<MaappedUserDetailsResponseModel> getListMappedUserDetails() {
		String method = "getMappedUserDetails";
		logger.info("Entry dao---->"+method);
		String mappedUserDetailsQuery = adminQueries.getProperty("mappedUserDetailsQuery");
		logger.info("query to get user mapped details-->"+mappedUserDetailsQuery);
		List<MaappedUserDetailsResponseModel> lstRspnse = jdbcTemplate.query(mappedUserDetailsQuery, new RowMapper<MaappedUserDetailsResponseModel>() {
			@Override
			public MaappedUserDetailsResponseModel mapRow(ResultSet rs,
					int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MaappedUserDetailsResponseModel mappedUser = new MaappedUserDetailsResponseModel();
				mappedUser.setSurveyId(rs.getLong(1));
				mappedUser.setClusterId(rs.getLong(3));
				mappedUser.setSurveyName(rs.getString(5));
				mappedUser.setClusterName(rs.getString(6));
				mappedUser.setSchoolName(rs.getString(7));
				mappedUser.setSchoolCategory(rs.getString(8));
				return mappedUser;
			}
		});
		logger.info("Exit dao---->"+method);
		return lstRspnse;
	}
	@Override
	public List<AllLocationModel> getAllLocationAccordingToRole(long groupId) {
		// TODO Auto-generated method stub
		System.out.println("inside method to get all location like block and district");
		//here 33 is for deo i.e. district officer
		//here 34 is for beo i.e. block officer
		String sql = null;
		if(groupId == 3) {
			sql = adminQueries.getProperty("getAllCity");
			System.out.println("query for all district:->"+sql);
		}else {
			sql = adminQueries.getProperty("getAllBlockDetails");
			System.out.println("query for all district:->"+sql);
		}
		
		List<AllLocationModel> lstLocation = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setCityId(rs.getString("gh_id"));
				location.setCityName(rs.getString("gh_name"));
				return location;
			}
		});
		return lstLocation;
	}
	
	@Override
	public boolean mapLocationToUser(String mapUserId, String districtId,String type, Principal auth) {
		// TODO Auto-generated method stub
		System.out.println("method to mapped the location id to user id");
		System.out.println("Location id:-"+districtId);
		System.out.println("User id:-"+mapUserId);
		List<AllLocationModel> list = null;
		
		if(type.equalsIgnoreCase("C")) {
			list = getAllBlockByDistrict(districtId);
		}
		String sql = adminQueries.getProperty("mappingUseridToLocation");
		if(list != null) {
			for(AllLocationModel item : list) {
				AllLocationModel loc = getAllLocationAccordingToUserAndLoc(mapUserId, item.getCityId());
				if(loc == null) {
					Integer id = 0;
					try {
						id = get_maxID("adminr.user_location");
					} catch (Exception e) {
						e.printStackTrace();
					}
					jdbcTemplate.update(sql,new Object[] {
						id,
						mapUserId,
						item.getCityId(),
						true,
						auth.getName(),
						new Date()
					});
				}
			}
		}else {
			AllLocationModel loc = getAllLocationAccordingToUserAndLoc(mapUserId, districtId);
			if(loc == null) {
				Integer id = 0;
				try {
					id = get_maxID("adminr.user_location");
				} catch (Exception e) {
					e.printStackTrace();
				}
				jdbcTemplate.update(sql,new Object[] {
					id,
					mapUserId,
					districtId,
					true,
					auth.getName(),
					new Date()
				});
			}
		}
		return true;
	}
	
	@Override
	public List<AllLocationModel> getAllDistrict() {
		String sql =  adminQueries.getProperty("getAllCity");
		List<AllLocationModel> lstDistrict= jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setCityId(rs.getString("gh_id"));
				location.setCityName(rs.getString("gh_name"));
				return location;
			}
		});
		return lstDistrict;
	}
	@Override
	public List<AllLocationModel> getAllBlockByDistrict(String districtcode) {
		// TODO Auto-generated method stub
		
		String sql =  adminQueries.getProperty("getAllGHByCity");
		List<AllLocationModel> lstDistrict= jdbcTemplate.query(sql,new Object[] {districtcode}, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setCityId(rs.getString("block_id"));
				location.setCityName(rs.getString("block_name"));
				return location;
			}
		});
		return lstDistrict;
	}
	
	@Override
	public Map<String, Object> getUserIdLocationId(String userId) {
		List<Map<String, Object>> groupIdLocationId = new ArrayList<Map<String,Object>>();
		try{
			final String insertConfigSql = adminQueries.getProperty("getUserIdLocationIdSQL");
				groupIdLocationId = jdbcTemplate.queryForList(insertConfigSql,userId);
				System.out.println(groupIdLocationId.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		String grpName = (String) groupIdLocationId.get(0).get("group_name");// USED for Cross Check
		
		
//		if(grpName.contains("stc")){
//			String districtName = jdbcTemplate.queryForObject("select district_name from master_district  where  district_id = ? limit 1", String.class, locationId);
//			groupIdLocationId.get(0).put("districtId", locationId);
//			groupIdLocationId.get(0).put("districtName", districtName);
//		}else if(grpName.contains("tc")){
//			Map<String, Object> districtId = jdbcTemplate.queryForMap("select a.district_id, b.district_name, a.block_name from master_block a,  master_district b  where a.district_id = b.district_id  and block_id = ? limit 1", locationId);
//			groupIdLocationId.get(0).put("districtId", districtId.get("district_id"));
//			groupIdLocationId.get(0).put("districtName", districtId.get("district_name"));
//			groupIdLocationId.get(0).put("blockName", districtId.get("block_name"));
//			groupIdLocationId.get(0).put("blockId", locationId);
//		}
		return groupIdLocationId.get(0);
	}
	@Override
	public List<LoginUserDetailsModel> getFirstNameLastName(String userId) {
		String sql = adminQueries.getProperty("getFirstNameLastNameSQL");
		List<LoginUserDetailsModel> firstNameLastName = jdbcTemplate.query(sql, new RowMapper<LoginUserDetailsModel>() {
			@Override
			public LoginUserDetailsModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				LoginUserDetailsModel fnln = new LoginUserDetailsModel();
				fnln.setFirst_name(rs.getString("first_name"));
				fnln.setLast_name(rs.getString("last_name"));
				return fnln;
			}
		}, userId);
		return firstNameLastName;
	}
	@Override
	public boolean changePasswordOnValid(ChangePasswordModel password, String userId) {
		String sql = adminQueries.getProperty("changePasswordSQL");

		String newPassword = password.getPassword();
		String confPassword = password.getConfPassword();
		
		if(newPassword.equalsIgnoreCase(confPassword)){
			jdbcTemplate.update(sql, newPassword, userId);
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public String getOldPasswordFromDB(String userId) {
		String sql1 = adminQueries.getProperty("getPasswordByuserIdSQL");
		String passwordindb = jdbcTemplate.queryForObject(sql1, String.class, new Object[] { userId });		
	
		return passwordindb;
	}
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	@Override
	public JSONObject validateUid(String userid) {
		String methodName = "validateUid";
		logger.info("EntryDao--------------->"+methodName);
		JSONObject retObj = new JSONObject();
		String sql = "select * from adminr.user where user_id=? and is_otp_login = true";
		retObj = jdbcTemplate.query(sql, new Object[] {userid}, new ResultSetExtractor<JSONObject>(){
			@Override
			public JSONObject extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONObject retObj1 = new JSONObject();
				if(rs.next()) {
					try {
						retObj1.put("status", "success");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else {
					try {
						retObj1.put("status", "failure");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return retObj1;
			}
		});
		logger.info("Exitdao--------------->"+methodName);
		return retObj;
	}
	
	@Override
	public boolean updateUserDetailsOtp(UserModel user) {
		String sql = adminQueries.getProperty("updateUserDetailsOtpSQL");
		jdbcTemplate.update(sql, new Object[] {
				user.getPassword(),	
				user.getUserId()
		});		
		return true;
	}
	
	public Integer get_maxID(String table) throws Exception {
		String sql = "select max(id) as id from " + table;
		Integer l_curvalue =0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer l_curvalue =0;
				l_curvalue = rs.getInt("id");
				return l_curvalue;
			}
		});

		if(null != users && !users.isEmpty()){
			l_curvalue = users.get(0);
		}
		return l_curvalue + 1;
	}



	@Override
	public List<AllLocationModel> getAllLocationAccordingToUser(String selectedUserId) {
		System.out.println("inside method to get all location like block and district");
		String sql = null;
		
		sql = adminQueries.getProperty("getAllMappedLocByUser");
		System.out.println("query for all district:->"+sql);
	
		List<AllLocationModel> lstLocation = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setId(rs.getInt("id"));
				location.setUserId(rs.getString("user_id"));
				location.setCityId(rs.getString("location_id"));
				location.setCityName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				return location;
			}
		},selectedUserId);
		return lstLocation;
	}

	@Override
	public Boolean deleteLoc(Integer id) {
		String sql = adminQueries.getProperty("deleteLoc");
		jdbcTemplate.update(sql, new Object[] {
				id
		});		
		return true;
	}
	
	public AllLocationModel getAllLocationAccordingToUserAndLoc(String selectedUserId,String locId) {
		System.out.println("inside method to get all location like block and district");
		String sql = null;
		AllLocationModel loc = null;
		sql = adminQueries.getProperty("getAllMappedLocByUserAndLoc");
		System.out.println("query for all district:->"+sql);
	
		List<AllLocationModel> lstLocation = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setId(rs.getInt("id"));
				location.setUserId(rs.getString("user_id"));
				location.setCityId(rs.getString("location_id"));
				location.setCityName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				return location;
			}
		},selectedUserId,locId);
		if(lstLocation != null) {
			if(lstLocation.size() != 0) {
				loc = lstLocation.get(0);
			}
		}
		return loc;
	}

	@Override
	public List<AllLocationModel> getAllState() {
		String sql =  adminQueries.getProperty("getAllState");
		List<AllLocationModel> lstDistrict= jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setCityId(rs.getString("state_code"));
				location.setCityName(rs.getString("state_name"));
				return location;
			}
		});
		return lstDistrict;
	}

	@Override
	public boolean saveMasterGuestDetails(PasswordResetModel forgetPassword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = null;
		if(forgetPassword.getDob() != null) {
			if(!forgetPassword.getDob().isEmpty()) {
				try {
					dob = sdf.parse(forgetPassword.getDob());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		String sql = adminQueries.getProperty("saveMasterGuestDetailsSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				forgetPassword.getMobile(),
				forgetPassword.getDept(),
				forgetPassword.getOfficeAddr(),
				forgetPassword.getResAddr(),
				forgetPassword.getGuestGender(),
				dob
		});		
		return true;
	}
	@Override
	public boolean saveMasterGuestDetailss(UserRegistrationModel forgetPassword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = null;
		if(forgetPassword.getDob() != null) {
			if(!forgetPassword.getDob().isEmpty()) {
				try {
					dob = sdf.parse(forgetPassword.getDob());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		String sql = adminQueries.getProperty("saveMasterGuestDetailsSQL");		
		jdbcTemplate.update(sql, new Object[] { 
				forgetPassword.getMobile(),
				forgetPassword.getDept(),
				forgetPassword.getOfficeAddr(),
				forgetPassword.getResAddr(),
				forgetPassword.getGuestGender(),
				dob
		});		
		return true;
	}

	@Override
	public UserModel getUserMasterData(UserModel lstUserDetails) {
		String sql = adminQueries.getProperty("getUserByProfile");
		List<UserModel> userList = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel user = new UserModel();
				user.setUserId(rs.getString("user_id"));
				user.setDept(rs.getString("department"));
				user.setOfficeAddr(rs.getString("office_address"));
				user.setResAddr(rs.getString("residence_address"));
				user.setGuestGender(rs.getString("gender"));
				user.setDob(rs.getDate("dob"));
				return user;
			}
		}, lstUserDetails.getUserId());
		if(null != userList && !userList.isEmpty()){
			lstUserDetails.setUserId(userList.get(0).getUserId());
			lstUserDetails.setDept(userList.get(0).getDept());
			lstUserDetails.setOfficeAddr(userList.get(0).getOfficeAddr());
			lstUserDetails.setResAddr(userList.get(0).getResAddr());
			lstUserDetails.setGuestGender(userList.get(0).getGuestGender());
			lstUserDetails.setDob(userList.get(0).getDob());
		}
		return lstUserDetails;
	}

	@Override
	public boolean updateMasterGuestDetails(UserRegistrationModel forgetPassword) {
		Boolean flag = false;
		Date dob = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(forgetPassword.getDob() != null) {
			if(!forgetPassword.getDob().isEmpty()) {
				try {
					dob = sdf.parse(forgetPassword.getDob());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		String sql = adminQueries.getProperty("updateMasterGuestDetailsSQL");		
		Integer status = jdbcTemplate.update(sql, new Object[] { 
				forgetPassword.getDept(),
				forgetPassword.getOfficeAddr(),
				forgetPassword.getResAddr(),
				forgetPassword.getGuestGender(),
				dob,
				forgetPassword.getUserId()
		});
		if(status != 0) {
			flag = true;
		}
		return flag;
	}
	@Override
	public Boolean saveAuditLog(AuditlogRequest loginRequest, HttpServletRequest request) {
		Boolean flag = false;
		String sql = adminQueries.getProperty("saveauditlogin");		
		Integer status = jdbcTemplate.update(sql, new Object[] { 
				loginRequest.getUserId(),
				loginRequest.getIpAddress(),
				loginRequest.getAction(),
				loginRequest.getRecordStatus()
		});
		if(status != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public JSONObject validatefeedback(ChangePasswordWithoutAuthModel forgetPassword) {
		String methodName = "validatefeedback";
		logger.info("EntryDao--------------->"+methodName);
		JSONObject retObj = new JSONObject();
		String sql =" SELECT a.*,b.name,c.status,c.departure_time_date FROM public.gad_request a join public.guest_details b "
				+ "on a.req_id =b.req_id join public.tr_checkin c on a.req_id=c.req_id where a.mobile_no=? "
				+ "and a.gh_id=? and a.block_id=? "
				+ "and c.status='COUT'"  ;
		System.out.println(sql);
		retObj = jdbcTemplate.query(sql, new Object[] {forgetPassword.getMobile(),forgetPassword.getCity(),forgetPassword.getgHouse()}, new ResultSetExtractor<JSONObject>(){
			
			@Override
			public JSONObject extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONObject retObj1 = new JSONObject();
				if(rs.next()) {
					try {
						retObj1.put("email", rs.getString("email_id"));
						retObj1.put("Name", rs.getString("name"));
						retObj1.put("req_id", rs.getString("req_id"));
						retObj1.put("mobile_no", rs.getString("mobile_no"));
						retObj1.put("date", rs.getString("departure_time_date"));
						retObj1.put("cityy", rs.getString("gh_id"));
						retObj1.put("guestHouse", rs.getString("block_id"));
						retObj1.put("status", "success");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else {
					try {
						retObj1.put("status", "falure");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return retObj1;
			}
		});
		logger.info("Exitdao--------------->"+methodName);
		return retObj;
	}

	@Override
	public JSONObject validateFeedbackforQr(String reqId) {
		String methodName = "validateFeedbackforQr";
		logger.info("EntryDao--------------->"+methodName);
		JSONObject retObj = new JSONObject();
		String sql = "select req_id,status from public.feedback_system where req_id='"+reqId+"' and status='CIN' and status='COUT'";
		retObj = jdbcTemplate.query(sql, new Object[] {reqId}, new ResultSetExtractor<JSONObject>(){
			@Override
			public JSONObject extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONObject retObj1 = new JSONObject();
				if(rs.next()) {
					System.out.println("reqId " + rs.getString("req_id"));
					try {
						retObj1.put("reqId", rs.getString("req_id"));
						retObj1.put("status", "success");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else {
					try {
						retObj1.put("status", "falure");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return retObj1;
			}
		});
		logger.info("Exitdao--------------->"+methodName);
		return retObj;
	}

	@Override
	public JSONObject validateforFeedbackforDt(String reqId, String datee) {
		String methodName = "validateFeedbackforQr";
		logger.info("EntryDao--------------->"+methodName);
		JSONObject retObj = new JSONObject();
		String sql = "SELECT departure_time_date::date,req_id FROM public.tr_checkin where req_id=?";
		retObj = jdbcTemplate.query(sql, new Object[] {reqId}, new ResultSetExtractor<JSONObject>(){
			@Override
			public JSONObject extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONObject retObj1 = new JSONObject();				
				if(rs.next()) {
					
					try {
						final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						retObj1.put("reqId", rs.getString("req_id"));
						Date date = new Date();
						Date date2=rs.getDate("departure_time_date");
			    	    SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd");
			    	    Calendar c1 = Calendar.getInstance();
			    	    String currentDate = df.format(date);// get current date here
			    	    String departDate = df.format(date2);
			    	    System.out.println("CurrentDate"+ currentDate);
			    	    System.out.println("Departure Date " + departDate);
			    	    try {
							c1.setTime(df.parse(departDate));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
			    	    // now add 30 day in Calendar instance 
			    	    System.out.println("Calendar.DAY_OF_MONTH====" + Calendar.DAY_OF_MONTH);
			    	    c1.add(Calendar.DAY_OF_MONTH, 30);
			    	    String dateAfter = df.format(c1.getTime());  

			    	    // print the result
			    	    System.out.println("DATE_DATE :-> "+currentDate);
			    	    System.out.println("DUE_DATE :-> "+dateAfter);
						
							
							/*	Date curDate = formatter.parse(currentDate);
								Date departureDate = formatter.parse(dateAfter);
								System.out.println(curDate+ "and" +departureDate);
							 
							
						  int result=curDate.compareTo(departureDate);

						  if(result == 0)
							  System.out.println("You are not eligible");
						  else if (result > 0)
								retObj1.put("status", "success");
						  else
								System.out.println("You are not eligible");*/
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else {
					try {
						retObj1.put("status", "falure");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return retObj1;
			}
		});
		logger.info("Exitdao--------------->"+methodName);
		return retObj;
	}

	@Override
	public List<UserModel> getRegistrationDetails(String userId) {
		
				
		String sql = adminQueries.getProperty("getFirstNameLastNameSQL");
		List<UserModel> uploadDocument = jdbcTemplate.query(sql, new RowMapper<UserModel>() {
			@Override
			public UserModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserModel user = new UserModel();
				user.setFirstName((rs.getString("first_name")));
				user.setLastName((rs.getString("last_name")));
				user.setEmail(rs.getString("email_id"));
				user.setUpload_doc(rs.getBoolean("upload_doc"));
				/*user.setUserType(rs.getString(6));
				user.setFirstLogin(rs.getBoolean(7));
				user.setAdmin(rs.getBoolean(8));
				user.setActive(rs.getBoolean(9));
				user.setCreatedBy(rs.getString(10));
				user.setMenuVisible(rs.getBoolean(12));*/
				return user;
			}
		}, userId);
		return uploadDocument;
	}

	@Override
	public boolean updateUserDetail(UserModel user) {
		System.out.println(user.toString());
		String sql = adminQueries.getProperty("updateUserDetailSQL");
		jdbcTemplate.update(sql, new Object[] {
				user.getDocType(),
				user.getDocPath(),
				user.isUpload_doc(),
				user.getUserId()
				});
		return true;
	}
		
}

	
	
	
	

