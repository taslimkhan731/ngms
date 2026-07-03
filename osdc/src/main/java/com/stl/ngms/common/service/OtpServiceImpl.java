package com.stl.ngms.common.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.ngms.common.dao.OtpDao;
import com.stl.ngms.common.model.OtpModel;



@Service("otpService")
public class OtpServiceImpl implements OtpService {
	final static Logger logger = Logger.getLogger(OtpServiceImpl.class);
	@Autowired
    private OtpDao otpDao;
    
	/*************************************************
	 * This method gets the otp entity for the key
	 * @param otp key
	 * @return OtpModel
	 * @date 11-11-2017
	 * @author Ranavir
	 *************************************************/
	@Override
	public OtpModel getOtp(String key) {
		return otpDao.getOtp(key);
	}//end of getOtp
	/************************************
	 * Save or update otp in database
	 * @param OtpModel
	 * @return boolean
	 * @date 11-11-2017
	 * @author Ranavir
	 ************************************/
	@Override
	public boolean saveOrUpdateOtp(OtpModel otpObj) {
		return otpDao.saveOrUpdateOtp(otpObj);
	}//end of saveOrUpdateOtp
	@Override
	/************************************
	 * remove otp from database
	 * @param OtpModel
	 * @return boolean
	 * @date 11-11-2017
	 * @author Ranavir
	 ************************************/
	public boolean remove(String key) {
		return otpDao.remove(key);
	}
}
