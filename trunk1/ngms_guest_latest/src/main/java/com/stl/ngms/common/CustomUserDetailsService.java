package com.stl.ngms.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.Sha512;
/**
 * 
 * @author R. Dash
 * @since  15-09-2017
 * 
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AccessControlService acService;	
	@Autowired
	private Sha512 sha512;
	
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        System.out.println(roles);
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        System.out.println("Exit CustomUserDetailsService1111111111");
        System.out.println(authorities);
        return authorities;
    }
	@Override
	public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
		System.out.println("Entry,,,,,,,,,,33333333333333 : {} "+userName);
		//Filter the username and salt
		String saltKey = "", reqType="";
		try {
			JSONObject jObjUserName = new JSONObject(userName);
			userName = jObjUserName.getString("j_username");//updated
			saltKey = jObjUserName.getString("saltKey");
			reqType = jObjUserName.getString("reqType");
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new UsernameNotFoundException(String.format("Invalid user '%s'.", userName));
		}
		
		UserModel domainUser = acService.getUserByUserId(userName);
		System.out.println("ddddd"+domainUser);
		if(domainUser == null){
			throw new UsernameNotFoundException(String.format("Invalid user '%s'.", userName));
		}
		
		if(reqType.equals("register") && domainUser.isOtpLogin()){
			throw new UsernameNotFoundException(String.format("Invalid user '%s'.", userName));
		}
		
        boolean enabled = domainUser.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        //update the password with the salted hashed password
        String saltedPassword = sha512.SHA512(domainUser.getPassword()+"#"+saltKey);
        System.out.println(saltedPassword);
        System.out.println("Exit CustomUserDetailsService2222222");
        return new org.springframework.security.core.userdetails.User(
                domainUser.getUserId(),
                saltedPassword,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getUserId())
        );
    }
	public Collection<? extends GrantedAuthority> getAuthorities(String uid) {
		List<String> userRoles =(List<String>) acService.getMappedRolesToUser(uid);
		System.out.println(getGrantedAuthorities(userRoles));
        return getGrantedAuthorities(userRoles);
        
    }
}
