package model;

import java.net.InetAddress;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import common.utility;
import common.validation;
import db.d_otp_data;
import db.d_publicregister;
import db.m_audit_login;
import db.m_uservsrole;
import db.ngm;
import db.m_role;
import db.m_user_details;

public class m_publiclogin extends ngmodel {

	String				userid;
	String				password;
	String				domain;
	String				client_ip;
	d_publicregister		ins_pub_user;
	m_audit_login		ins_audlogin;
	m_uservsrole        ins_userrole;
	Integer				l_trail_id;
	String def_quo;

	m_role	ins_orgdesig;
	utility			ins_util;
    public String refno="";
	public m_publiclogin(ngm p_ngm) {
		this.NGM = p_ngm;
		ins_pub_user = new d_publicregister(p_ngm);
		ins_userrole=new m_uservsrole(p_ngm);
		ins_util = new utility();
		ins_audlogin = new m_audit_login(p_ngm);
		ins_orgdesig = new m_role(p_ngm);
		ins_val = new validation();
		ins_cal = Calendar.getInstance();
	}

	public void setPostData(HttpServletRequest p_request) {
		this.userid = p_request.getParameter("username");
//		if(this.userid != null){
//			String [] s = this.userid.split("atdot");
//			this.userid = s[0] + "@" + s[1];
//			s = this.userid.split("attdot");
//			this.userid = s[0];
//			for(int i=1;i<s.length;i++){
//				this.userid = this.userid + "." + s[i]; 
//			}
//		}
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
//		else if (!ins_val.cEmail(this.userid.toString()))
//			return false;
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
			ins_pub_user.login_id = ins_audlogin.userid = this.userid;

			if (!ins_pub_user.selectDB())
				return false;
			//ins_orgdesig.role_code = ins_m_user.role_code;
			String l_salt = ins_util.getSalt(ins_pub_user.password, NGM.ins_sess.captcha_id);
				
			if (this.userid.equals(ins_pub_user.login_id) && this.password.equals(l_salt) && ins_pub_user.is_active.equals("Y")) {
				NGM.ins_sess.username = ins_pub_user.name;
				NGM.ins_sess.user_id = ins_pub_user.login_id;
				
				NGM.ins_sess.portal_id = "PUB";
				ins_orgdesig.role_code = "08";
				ins_orgdesig.selectDB();
				NGM.ins_sess.rolename = ins_orgdesig.role_name;
				System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj "+NGM.ins_sess.rolename);
				if (!ins_userrole.selectDBpublic())
					return false;
				NGM.ins_sess.role_code=ins_userrole.rolelist;
				//NGM.ins_sess.role_id = ins_m_user.role_code;
				ins_audlogin.status = "Success";
				ins_audlogin.logout = time == null ? null : String.valueOf(time);
				ins_audlogin.client_ip = client_ip;
				NGM.ins_sess.client_ip = client_ip;
				PreparedStatement ps_org = NGM.tr_conn.prepareStatement("SELECT max(trail_id) as trail_id from m_audit_login where userid=? and status='Success'");
			ps_org.setString(1, this.userid);	
			System.out.println("trail validation"+ps_org);
			ResultSet rs = ps_org.executeQuery();
			rs.next();
			l_trail_id = rs.getInt("trail_id");
				rs.close();
				ps_org.close();
			System.out.println("trail id no 1"+l_trail_id);
			
			if (!ins_audlogin.insertDB()) {
			
				NGM.ins_sess = null;
					p_request.getSession(true).invalidate();
					return false;
					
				}
			System.out.println("trail_id  check"+l_trail_id);
			if (l_trail_id !=0 || ins_audlogin.trail_id.equals(null)){
				System.out.println("trail_id"+l_trail_id);
				
				 ins_audlogin.trail_id= l_trail_id ;
				System.out.println("trassssss"+l_trail_id);
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
	
	public boolean isvalidUserWaiting(HttpServletRequest p_request) throws Exception {
			ins_pub_user.login_id = this.userid;

			if (!ins_pub_user.selectDB())
				return false;
			//ins_orgdesig.role_code = ins_m_user.role_code;
			String l_salt = ins_util.getSalt(ins_pub_user.password, NGM.ins_sess.captcha_id);
	
	if (this.userid.equals(ins_pub_user.login_id) && this.password.equals(l_salt) && ins_pub_user.is_active.equals("N")) {
		/////
						d_otp_data ins_otp = new d_otp_data(NGM);
						ins_otp.login_id = this.userid;
						refno = ins_otp.selectDBrefno();
						/////
						return true;
		}
	else{
		return false;
		}
		
	}
}