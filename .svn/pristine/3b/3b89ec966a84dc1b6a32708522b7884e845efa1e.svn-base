package com.stl.ngms.common.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stl.ngms.common.model.OtpModel;

@Repository("otpDao")
public class OtpDaoImpl implements OtpDao {
	
final static Logger logger = Logger.getLogger(OtpDaoImpl.class);
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	/*************************************************
	 * This method gets the otp entity for the key
	 * @param otp key
	 * @return OtpModel
	 * @date 11-11-2017
	 * @author Ranavir
	 *************************************************/
	@Override
	public OtpModel getOtp(String key) {
		String methodName = "getOtp";
		logger.info("Entry--------------->"+methodName);
		logger.info("otp key :"+key);
		
		final String sql = "select * from adminr.otp where key = ?";
		logger.info("query--->"+sql);
		List<OtpModel> otps = (List<OtpModel>)jdbcTemplate.query(
				sql, new Object[] { key }, new BeanPropertyRowMapper<OtpModel>(OtpModel.class));
		
		if(null == otps || otps.isEmpty()){
			return null;
		}
		logger.info("Exit--------------->"+methodName);
		return otps.get(0);
	}//end of getOtp
	/************************************
	 * Save or update otp in database
	 * @param adminUser
	 * @return boolean
	 * @date 11-11-2017
	 * @author Ranavir
	 ************************************/
	@Override
	public boolean saveOrUpdateOtp(OtpModel otpObj) {
		String methodName = "saveOrUpdateOtp";
		logger.info("Entry--------------->"+methodName);
		boolean status = true ;
		int rc = 0 ;
		if (otpObj.getSlno() > 0) {
	        // update
	        String sql = "UPDATE adminr.otp SET otp_secret=?, otp=?, updated_on=CURRENT_TIMESTAMP "
	                    + "WHERE slno=?";
	        logger.info("query--->"+sql);
	        rc = jdbcTemplate.update(sql, otpObj.getOtp_secret(), otpObj.getOtp(), otpObj.getSlno());
	    } else {
	    	// delete if any exists
	        String sql = "DELETE from adminr.otp WHERE key=?";
	        logger.info("query--->"+sql);
	        rc = jdbcTemplate.update(sql, otpObj.getKey());
			
			if(rc > 0){
				logger.info("removed previously unused otp..");
			}
	        // insert
	        sql = "INSERT INTO adminr.otp (key, otp_secret, otp)"
	                    + " VALUES (?, ?, ?)";
	        logger.info("query--->"+sql);
	        rc = jdbcTemplate.update(sql, otpObj.getKey(), otpObj.getOtp_secret(), otpObj.getOtp());
	    }
		
		if(rc != 1){
			status = false;
		}
		logger.info("Exit--------------->"+methodName);
		return status;
	}//end of saveOrUpdateOtp
	/************************************
	 * remove otp from database
	 * @param OtpModel
	 * @return boolean
	 * @date 11-11-2017
	 * @author Ranavir
	 ************************************/
	@Override
	public boolean remove(String key) {
		String methodName = "remove";
		logger.info("Entry--------------->"+methodName);
		boolean status = true ;
		int rc = 0 ;
        // delete
        String sql = "DELETE from adminr.otp WHERE key=?";
        logger.info("query--->"+sql);
        rc = jdbcTemplate.update(sql, key);
		
		if(rc != 1){
			status = false;
		}
		logger.info("delete status:"+status);
		logger.info("Exit--------------->"+methodName);
		return status;
	}//remove
}