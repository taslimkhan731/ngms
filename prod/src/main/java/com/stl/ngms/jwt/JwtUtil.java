package com.stl.ngms.jwt;

import static com.stl.ngms.jwt.Constants.JWT_CLAIM_KEY_TOKEN_TYPE;
import static com.stl.ngms.jwt.Constants.JWT_TOKEN_TYPE_ACCESS_TOKEN;
import static com.stl.ngms.jwt.Constants.JWT_TOKEN_TYPE_REFRESH_TOKEN;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.stl.ngms.admin.controller.AdminController;
import com.stl.ngms.setting.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil implements Serializable{
	final static Logger logger = Logger.getLogger(AdminController.class);
	

	private static final long serialVersionUID = 1L;
	
	@Value("${jwt.token.access.validity}")
	public long JWT_TOKEN_VALIDITY;
	
	@Value("${jwt.token.refresh.validity}")
	private  long JWT_REFRESH_TOKEN_VALIDITY;
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	
	@Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;

    public String tokenIssuer;

    @Autowired
     LoggedOutJwtTokenCache loggedOutJwtTokenCache;


	/*
	private Key getSigningKey() {
		  byte[] keyBytes = Decoders.BASE64.decode(this.SECRET_KEY);
		  return Keys.hmacShaKeyFor(keyBytes);
		}
	*/
	
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
	
	

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

  //for retrieving any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
    	return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

  //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
        	System.out.println("username"+userDetails.getUsername());
            final String username = getUsernameFromToken(token);
            System.out.println("username"+username);
            validateTokenIsNotForALoggedOutDevice(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (MalformedJwtException e) {
        	e.printStackTrace();
           // logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	e.printStackTrace();
            //logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
        	e.printStackTrace();
           // logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
        	e.printStackTrace();
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;

    }

    private void validateTokenIsNotForALoggedOutDevice(String token) {
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache.getLogoutEventForToken(token);
        if (previouslyLoggedOutEvent != null) {
            String userEmail = previouslyLoggedOutEvent.getUserEmail();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userEmail, logoutEventDate);
            throw new InvalidTokenRequestException("JWT", token, errorMessage);
        }
    }

    public Boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

  //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
  //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
  //generate access token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
        		.setClaims(claims)
        		.setSubject(userDetails.getUsername())
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
        		
    }

  //generate refresh token for user
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public String generateAccessToken(UserModel user, Map<String, Object> customClaims) {
        return generateToken(user, true, customClaims);
    }

    public String generateRefreshToken(UserModel user, Map<String, Object> customClaims) {
        return generateToken(user, false, customClaims);
    }

    private String generateToken(UserModel user, boolean accessOrRefresh, Map<String, Object> customClaims) {
//        logger.debug("Generating token for user {} for roles {} access/refresh token {}.", user.getUserId(), null,
//                accessOrRefresh);
    	System.out.println("user.getUserId()"+user.getUserId());
        Claims claims = Jwts.claims().setSubject(user.getUserId());
        if (customClaims != null) {
            // custom claims goes first. Any key-conflicts with other entries will override
            // customClaims
            claims.putAll(customClaims);
        }
        claims.put(JWT_CLAIM_KEY_TOKEN_TYPE,
                accessOrRefresh ? JWT_TOKEN_TYPE_ACCESS_TOKEN : JWT_TOKEN_TYPE_REFRESH_TOKEN);
//		claims.put(JWT_CLAIM_KEY_USER_INFO, user);
        return buildToken(accessOrRefresh, claims);
    }

    private String buildToken(boolean accessOrRefresh, Claims claims) {
        logger.trace("building token.");
        JwtBuilder builder = Jwts.builder();
        long tokenValidity = accessOrRefresh ? JWT_TOKEN_VALIDITY : JWT_REFRESH_TOKEN_VALIDITY;
        builder.setClaims(claims).setIssuer(tokenIssuer).setIssuedAt(new Date(System.currentTimeMillis()));
        builder.setExpiration(new Date(System.currentTimeMillis() + (tokenValidity * 1000)));
        builder.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
        return builder.compact();
    }
}

