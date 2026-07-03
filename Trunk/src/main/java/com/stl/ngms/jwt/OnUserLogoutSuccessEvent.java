package com.stl.ngms.jwt;

import java.time.Instant;
import java.util.Date;

import org.springframework.context.ApplicationEvent;


public class OnUserLogoutSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private final String userEmail;
    private final String token;
    private final transient LogoutRequest logoutRequest;
    private final Date eventTime;

    public OnUserLogoutSuccessEvent(String userName, String token, LogoutRequest logoutRequest) {
        super(userName);
        this.userEmail = userName;
        this.token = token;
        this.logoutRequest = logoutRequest;
        this.eventTime = Date.from(Instant.now());
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getToken() {
		return token;
	}

	public LogoutRequest getLogoutRequest() {
		return logoutRequest;
	}

	public Date getEventTime() {
		return eventTime;
	}
    
    
}
