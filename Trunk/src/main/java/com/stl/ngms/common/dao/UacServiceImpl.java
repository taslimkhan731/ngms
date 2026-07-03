package com.stl.ngms.common.dao;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.stl.ngms.common.UserLocationInfo;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.UserModel;

@Repository("uacService")
public class UacServiceImpl implements UacService {
	
	final static Logger logger = Logger.getLogger(UacServiceImpl.class);

	@Autowired JdbcTemplate jdbcTemplate;
	
	@Autowired private Properties adminQueries;
	
	@Override
	public UserLocationInfo getUserLocationInfo(Principal auth) {
		logger.info("request Received By User " + auth.getName());
		UserLocationInfo info = new UserLocationInfo();
		if(auth.getName() != null) {
			String sql = adminQueries.getProperty("getUserByUserId");
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
			}, auth.getName());
			if(null != userList && !userList.isEmpty()){
				info.setUser(userList.get(0));
			}
			
			//Location Based
			sql = adminQueries.getProperty("getAllMappedLocByUser");
			HashSet<String> setCity = new HashSet<String>();
			HashSet<String> setGh = new HashSet<String>();
			List<AllLocationModel> lstLocation = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
				@Override
				public AllLocationModel mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					// TODO Auto-generated method stub
					AllLocationModel location = new AllLocationModel();
					location.setId(rs.getInt("id"));
					location.setUserId(rs.getString("user_id"));
					location.setGhId(rs.getString("gh_id"));
					location.setCityId(rs.getString("location_id"));
					location.setCityName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
					return location;
				}
			},auth.getName());
			
			if(lstLocation != null) {
				for(AllLocationModel item : lstLocation) {
					setCity.add(item.getGhId());
					setGh.add(item.getCityId());
				}
			}
			
			if(setCity.size() != 0) {
				List<String> aCity = new ArrayList<String>(setCity); 
				List<String> aGH = new ArrayList<String>(setGh); 
				if(setCity.size() == 1 && setGh.size() == 1) {
					sql = adminQueries.getProperty("getAllMappedLocByUserFilter");
					List<AllLocationModel> lstLo = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
						@Override
						public AllLocationModel mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							// TODO Auto-generated method stub
							AllLocationModel location = new AllLocationModel();
							location.setId(rs.getInt("id"));
							location.setUserId(rs.getString("user_id"));
							location.setGhId(rs.getString("gh_id"));
							location.setCityId(rs.getString("location_id"));
							location.setCityName(rs.getString("block_name"));
							location.setGhName(rs.getString("gh_name"));
							return location;
						}
					},auth.getName(),aGH.get(0),aCity.get(0));
					info.setLocCity(lstLo);
					info.setLocGH(lstLo);
				}else {
					sql =  "select gh_id,gh_name from gh_details where gh_id in (";
					if(aCity != null && !aCity.isEmpty()){
						int i = 0 ; 
						for(String role : aCity){
							i++;
							sql = sql + "'" + role + "'";
							if(i < aCity.size()){
								sql = sql + ",";
							}
						}
					}
					sql = sql.concat(") order by gh_name asc");
					List<AllLocationModel> lstDistrict= jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
						@Override
						public AllLocationModel mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							// TODO Auto-generated method stub
							AllLocationModel location = new AllLocationModel();
							location.setGhId(rs.getString("gh_id"));
							location.setGhName(rs.getString("gh_name"));
							return location;
						}
					});
					info.setLocCity(lstDistrict);
				}
			}
		}
		return info;
	}

}
