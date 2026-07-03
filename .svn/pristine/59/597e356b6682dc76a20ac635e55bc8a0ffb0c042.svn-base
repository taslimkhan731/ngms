package com.stl.ngms.common.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stl.ngms.common.model.OtpModel;
import com.stl.ngms.utils.TOTPUtils;
/**
 * 
 * @author R. Dash
 * @since  18-09-2017
 *
 */
@Component
public class JdbcOtpStore implements OtpStore {
	@Autowired
	TOTPUtils otpUtil;
	@Autowired
	OtpService otpService;
	public JdbcOtpStore() {
		super();
	}

	@Override
	public String storeOtp(String key) {
		String secret = otpUtil.generateSecret();
		String otp = otpUtil.getOtp(secret);
		boolean status = otpService.saveOrUpdateOtp(new OtpModel(key,secret,otp));
		return status ? otp : null;
	}

	@Override
	public boolean isOtpValid(String key, String otp) {
		System.out.println("OTPPPPPPP"+otp+ "" +key);
		if(null == key || key.isEmpty())
			return false;
		OtpModel otpObj = otpService.getOtp(key);
		System.out.println("OTPPPPPPP"+otpObj.toString());
		if (otpObj == null) {
			return false;
		}else {
			try {
//				boolean isValid = otpUtil.checkCode(otpObj.getOtp_secret(), Long.parseLong(otp));//use this if u want multiple otp valid for a time window
				boolean isValid = (StringUtils.equals(otp, otpObj.getOtp())) ? 
						otpUtil.checkCode(otpObj.getOtp_secret(), Long.parseLong(otp)) : false;//use this if u want exact otp match
				//otpService.remove(key);
				System.out.println("SDGDGD"+isValid);
				return isValid;
				
			} catch (InvalidKeyException | NumberFormatException
					| NoSuchAlgorithmException e) {
				e.printStackTrace();
				return false;
			}
			
		}
	}

	@Override
	public String getDuplicateOtp(String key) {
		if(null == key || key.isEmpty())
			return null;
		OtpModel otpObj = otpService.getOtp(key);
		if (otpObj == null) {
			return null;
		}else {
//			return otpUtil.getOtp(otpObj.getOtp_secret());//use this if u want multiple otp valid for a time window
			String otp = otpUtil.getOtp(otpObj.getOtp_secret());
			otpObj.setOtp(otp);
			boolean status = otpService.saveOrUpdateOtp(otpObj);
			return status ? otp : null;
		}
	}

}
