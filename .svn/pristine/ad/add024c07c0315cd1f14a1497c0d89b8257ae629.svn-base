package model;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import common.validation;
import db.d_publicregister;
import db.m_user_details;
import db.m_role;
import db.m_uservsrole;
import db.ngm;

public class m_cpwd extends ngmodel{

	String current_password;
	String new_password;
	String confirm_password;
	
	d_publicregister	ins_m_user;
	m_uservsrole	ins_m_users;
	m_role ins_orgdesig;
	
public m_cpwd(ngm p_ngm){
	NGM		= p_ngm;
	ins_m_user		= new d_publicregister(p_ngm);
	ins_m_users     = new m_uservsrole(p_ngm);
	ins_orgdesig	= new m_role(p_ngm);
	ins_val			= new validation();
	ins_cal 		= Calendar.getInstance();
	}

public void setPostData(HttpServletRequest p_request){
	current_password	= p_request.getParameter("txt_currentpassword");
	new_password		= p_request.getParameter("txt_newpassword");
	confirm_password	= p_request.getParameter("txt_confirmpassword");
}
	
public boolean isvalidCurrentPassword()throws Exception{
	System.out.println("model66666      ");
	System.out.println("model66666      "+NGM.ins_sess.user_id);
	ins_m_user.login_id =NGM.ins_sess.user_id;
	System.out.println("model777777");
	if(!ins_m_user.selectDB()) return false;	
	//NGM.ins_utility.getHash(current_password)
	if(ins_m_user.password.equals(current_password)){
		return true;
	}
	else{
		return false;
	}
}
public boolean isvalidNewPassword()throws Exception{
	ins_m_user.login_id=NGM.ins_sess.user_id;
	ins_m_user.selectDB();
	if(ins_val.cPasswordConfirm(new_password, confirm_password)){
		return true;
	}
	else{
		return false;
	}
}

public boolean changePassword(HttpServletRequest p_request)throws Exception{
	System.out.println("modellllllllll");
	setPostData(p_request);
	System.out.println("modelll222222");
	if(isvalidCurrentPassword()){
		System.out.println("model333333l");
		if(isvalidNewPassword()){
			System.out.println("model4444444");
			ins_m_user.login_id=NGM.ins_sess.user_id;
			ins_m_users.role_code=NGM.ins_sess.role_id;
			ins_m_user.password=new_password;
			ins_m_user.password=new_password;
			if(ins_m_user.select_password_history()){
				NGM.ins_writer.write("Message: Password should not be same as last 3 password");
			}else{
				if(ins_m_user.select_password()){
					if(ins_m_user.insert_password_history()){
						ins_m_user.password=new_password;
						if(!ins_m_user.updateDB_password())
						{System.out.println("here......");
							return false;
						}
						return true;
					}
				}else{
					NGM.ins_writer.write("Message: Password should not be same");
				}
			}
			/*ins_m_user.updateDB_password();
			
			if(NGM.ins_sess.user_id.equals("admin")||ins_m_users.role_code.equals("18"))
				NGM.ins_writer.write("index2-abc-"+NGM.ins_sess.process_id);
			else if(ins_m_user.role_code.equals("05")||ins_m_user.role_code.equals("04")||ins_m_user.role_code.equals("06"))
				NGM.ins_writer.write("index-abc-"+NGM.ins_sess.process_id);*/
		}
		else{
			NGM.ins_writer.write("Message: confirm password failed");
		}
	}
	else{
		NGM.ins_writer.write("Message: current password not matching");
	}
	return true;
}
}

