package com.stl.ngms.common.service;

public interface OtpStore {
	
	/**********************
	 * Adds a username/otp secret pair to the OtpStore.
	 * and return one otp value
	 ***********************/
	public String storeOtp(String username);

	/**
	 * Determines if a otp is valid.  If the otp is valid, this method
	 * should return true, and invalidate the underlying otp.  That is,
	 * two successive calls to this method with the same username and
	 * otp should not return true.
	 */
	public boolean isOtpValid(String username, String otp);
	/**
	 * Retrieve a duplicate otp from store if key available 
	 * else return null
	 */
	public String getDuplicateOtp(String username);
	
	
	
	
}
