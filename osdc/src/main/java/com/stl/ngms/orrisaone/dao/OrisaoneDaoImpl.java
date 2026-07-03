package com.stl.ngms.orrisaone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stl.ngms.orrisaone.model.UserModel;

@Repository("orisaoneDao")
public class OrisaoneDaoImpl implements OrisaoneDao{
	final static Logger logger = Logger.getLogger(OrisaoneDaoImpl.class);
    @Autowired JdbcTemplate jdbcTemplate;
	
	@Autowired private Properties adminQueries;
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
					logger.info("Email " + rs.getString("email_id"));
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
	@Override
	public boolean saveGuestDetails(UserModel user) {
		logger.info(user.getUserId());
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
		});		
		return true;
	}
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
	public UserModel getUserByUserId(String userName) {
		String sql = adminQueries.getProperty("getUserByUserId");
		logger.info("Entry User Model");
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
				return user;
			}
		}, userName);
		if(null != userList && !userList.isEmpty()){
			user = userList.get(0);
		}
		return user;
	}
}
