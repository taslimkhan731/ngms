package model;

import java.net.InetAddress;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import common.utility;
import common.validation;
import db.m_audit_login;
import db.m_uservsrole;
import db.ngm;
import db.m_role;
import db.m_user_details;

public class m_login extends ngmodel {

	String				userid;
	String				password;
	String				domain;
	String				client_ip;
	m_user_details		ins_m_user;
	m_audit_login		ins_audlogin;
	m_uservsrole        ins_userrole;
	Integer				l_trail_id;
	String def_quo;

	m_role	ins_orgdesig;
	utility			ins_util;

	public m_login(ngm p_ngm) {
		this.NGM = p_ngm;
		ins_m_user = new m_user_details(p_ngm);
		ins_userrole=new m_uservsrole(p_ngm);
		ins_util = new utility();
		ins_audlogin = new m_audit_login(p_ngm);
		ins_orgdesig = new m_role(p_ngm);
		ins_val = new validation();
		ins_cal = Calendar.getInstance();
	}

	public void setPostData(HttpServletRequest p_request) {
		this.userid = p_request.getParameter("username");
		try {
			//InetAddress thisIp = InetAddress.getLocalHost();
			//client_ip = thisIp.getHostAddress();
			 client_ip = p_request.getRemoteAddr();
		}
		catch (Exception e) {

		}
		this.password = p_request.getParameter("password");
	}

	public boolean validatePostData() throws Exception {
		if (this.userid.equals(null))
			return false;
		else if (!ins_val.cAlphaNumeric(this.userid.toString()))
			return false;
		if (this.password.equals(null))
			return false;
		if (!ins_val.cHashPassword(this.password))
			return false;
		else
			return true;
	}
	
	public boolean concurrent_login(Long time) throws Exception {
		ins_audlogin.userid = this.userid;
		if(ins_audlogin.concurrent_login(time)){
			return true;
		}
		return false;
	}

	public boolean isvalidUser(HttpServletRequest p_request) throws Exception {
		try {
			Date today = new Date();
			Long time = (today.getTime() + ((1000 * 60 * 60 * 1) / 6));
			
			ins_m_user.userid = ins_audlogin.userid = this.userid;
			if (!ins_m_user.selectDB())
				return false;
			//ins_orgdesig.role_code = ins_m_user.role_code;
			String l_salt = ins_util.getSalt(ins_m_user.password, NGM.ins_sess.captcha_id);
			if (this.userid.equals(ins_m_user.userid) && this.password.equals(l_salt) && ins_m_user.status.equals("Y")) {
				NGM.ins_sess.username = ins_m_user.username;
				NGM.ins_sess.user_id = ins_m_user.userid;
				System.out.println("---------------------------------------------------------------- "+NGM.ins_sess.user_id);
				NGM.ins_sess.rolename = ins_orgdesig.role_name;
				if (!ins_userrole.selectDB())
					return false;
				NGM.ins_sess.role_code=ins_userrole.rolelist;
				//NGM.ins_sess.role_id = ins_m_user.role_code;
				ins_audlogin.status = "Success";
				ins_audlogin.logout = time == null ? null : String.valueOf(time);
				ins_audlogin.client_ip = client_ip;
				NGM.ins_sess.client_ip = client_ip;
			PreparedStatement ps_org = NGM.tr_conn.prepareStatement("SELECT max(trail_id) as trail_id from m_audit_login where userid=? and status='Success'");
			ps_org.setString(1, this.userid);			
			ResultSet rs = ps_org.executeQuery();
			rs.next();
			l_trail_id = rs.getInt("trail_id");
			rs.close();	
			ps_org.close();
			
			if (!ins_audlogin.insertDB()) {
			
				NGM.ins_sess = null;
					p_request.getSession(true).invalidate();
					return false;
					
			}
			if (l_trail_id != null || !l_trail_id.equals(null)){
				if(l_trail_id != 0){
					ins_audlogin.trail_id = l_trail_id;
				}
			}
			if(l_trail_id != 0){
				if (!ins_audlogin.selectDB()) {
					NGM.ins_sess = null;
					p_request.getSession(true).invalidate();
					return false;
				}
			}
				PreparedStatement ps_quo = NGM.tr_conn.prepareStatement("Select prop_value from property where prop_id='default_quota'");
				ResultSet rs_quo = ps_quo.executeQuery();
				rs_quo.next();
				def_quo = rs_quo.getString("prop_value");
				rs_quo.close();
				ps_quo.close();
				
				PreparedStatement ps_org1 = NGM.tr_conn.prepareStatement("SELECT max(trail_id) as trail_id from m_audit_login where userid=? and status='Success'");
				ps_org1.setString(1, this.userid);			
				ResultSet rs1 = ps_org1.executeQuery();
				rs1.next();
				NGM.ins_sess.trail_id = rs1.getInt("trail_id");
				rs1.close();	
				ps_org1.close();
				System.out.println(NGM.ins_sess.trail_id );
				NGM.ins_sess.def_quota = def_quo;
				NGM.ins_sess.last_login_date = ins_audlogin.login_time;
								
				return true;
			}
			else {
				
				return false;
			}
	
	}
		catch (Exception e) {
			e.printStackTrace();
			NGM.ins_sess = null;
			p_request.getSession(true).invalidate();

			throw NGM.ins_utility.setException("m_login", "Login Problem", "isvalidUser", NGM);
		}
	
	}
	public boolean isUservalid(HttpServletRequest p_request) throws Exception {
		 
		 
		String l_salt = NGM.ins_utility.getHash(this.password);
		
		System.out.println("okkkkkkkkkkkksaltkkk"+l_salt);

		if (this.userid.equals(ins_m_user.userid) && l_salt.equals(ins_m_user.password) && ins_m_user.status.equals("Y")) {
			  
		 
				return false;
		}
		return true;
	}
}