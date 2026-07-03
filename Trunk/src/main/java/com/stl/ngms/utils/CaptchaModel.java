package com.stl.ngms.utils;

public class CaptchaModel {
    private String captchaKey;
	
	private Long expireTime;
	
	private String hostAddr;

	/**
	 * @return the captchaKey
	 */
	public String getCaptchaKey() {
		return captchaKey;
	}

	/**
	 * @param captchaKey the captchaKey to set
	 */
	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}

	/**
	 * @return the expireTime
	 */
	public Long getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * @return the hostAddr
	 */
	public String getHostAddr() {
		return hostAddr;
	}

	/**
	 * @param hostAddr the hostAddr to set
	 */
	public void setHostAddr(String hostAddr) {
		this.hostAddr = hostAddr;
	}

	@Override
	public String toString() {
		return "CaptchaModel [captchaKey=" + captchaKey + ", expireTime=" + expireTime + ", hostAddr=" + hostAddr + "]";
	}
	

}
