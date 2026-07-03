package com.stl.ngms.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stl.ngms.admin.controller.AdminController;

import net.jodah.expiringmap.ExpiringMap;

@Component
public class LoggedOutJwtTokenCache {
	final static Logger log = Logger.getLogger(AdminController.class);
	private final ExpiringMap<String, OnUserLogoutSuccessEvent> tokenEventMap;
	private final JwtUtil tokenProvider;

	@Autowired
	public LoggedOutJwtTokenCache(JwtUtil tokenProvider) {
		this.tokenProvider = tokenProvider;
		this.tokenEventMap = ExpiringMap.builder().variableExpiration().maxSize(1000).build();
	}

	public void markLogoutEventForToken(OnUserLogoutSuccessEvent event) {
		String token = event.getToken();
		if (tokenEventMap.containsKey(token)) {
			log.info(
					String.format("Log out token for user [%s] is already present in the cache", event.getUserEmail()));

		} else {
			Date tokenExpiryDate = tokenProvider.getExpirationDateFromToken(token);
			long ttlForToken = getTTLForToken(tokenExpiryDate);
			log.info(String.format(
					"Logout token cache set for [%s] with a TTL of [%s] seconds. Token is due expiry at [%s]",
					event.getUserEmail(), ttlForToken, tokenExpiryDate));
			tokenEventMap.put(token, event, ttlForToken, TimeUnit.SECONDS);
		}
	}

	public OnUserLogoutSuccessEvent getLogoutEventForToken(String token) {
		return tokenEventMap.get(token);
	}

	private long getTTLForToken(Date date) {
		long secondAtExpiry = date.toInstant().getEpochSecond();
		long secondAtLogout = Instant.now().getEpochSecond();
		return Math.max(0, secondAtExpiry - secondAtLogout);
	}
}
