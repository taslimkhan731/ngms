package com.stl.ngms.common.dao;

import com.stl.ngms.common.model.OtpModel;

public interface OtpDao {
	boolean saveOrUpdateOtp(OtpModel otpObj);
	OtpModel getOtp(String key);
	boolean remove(String key);
}