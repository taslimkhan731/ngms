package model;

//import nic.in.sms.SendSMS;

import common.*;
/*import db.common;
import db.dept_user_details;*/
import db.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import test.sendsms;

/*import test.SendGmail;
import test.sendsms;
 */


public class m_forgotpassword extends ngmodel{

	public String refno;
	/*String 			t_userid,name,emailverification,status,dob;
	String 			t_mobile;
	String          t_newpassword;
	String			t_verifymobile;
	String			random;
	String          t_email; 
	String          pass;*/
	String t_confirm_password;
	d_publicregister ins_pubuser;
	common ins_common;
	d_otp_data ins_otp			    = null;
	sendsms ins_sms;
	String password;

	public m_forgotpassword(ngm p_NGM){
		this.NGM		= p_NGM;
		ins_val			= new validation();
		ins_cal 		= Calendar.getInstance();
		ins_pubuser		= new d_publicregister(NGM);

		ins_otp		= new d_otp_data(NGM);
		ins_common =new common(NGM);
		ins_sms =new sendsms();
	}

	public void setPostData(HttpServletRequest p_request){ //ok
		ins_pubuser.login_id			= p_request.getParameter("fuserid");
//		ins_pubuser.mobile			= p_request.getParameter("fmob");
//		ins_pubuser.email		= p_request.getParameter("femailid");
		//ins_pubuser.dob			= p_request.getParameter("fdob");
		ins_pubuser.dob			= p_request.getParameter("txt_yy")+"-"+p_request.getParameter("txt_mm")+"-"+p_request.getParameter("txt_dd");
	}

	public boolean username_mob_mail_exist(HttpServletRequest p_request) throws Exception{	//ok

		if(ins_pubuser.selectUserValid()){
			System.out.println("TRUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			return true;
		}
		else{
			System.out.println("FALSEEEEEEEEEEEEEEEEEEEEEE22222222222");

			return false;
		}		
	}

	public boolean sendVerificationCode()throws Exception {		//ok
		this.refno = this.ins_otp.refno;
		String templateId="1107160707717945638";
		NGM.ins_utility.SENDSMS(this.ins_pubuser.mobile, "Your verification code is"+this.ins_otp.password+" for the reference no"+this.ins_otp.refno+"", "NGM",templateId);//this.ins_user.userid
		//emaillll

		return true;
	}

	public boolean sendVerificationCodeEmail()throws Exception {		//ok
		this.refno = this.ins_otp.refno;
		SendGmail gmail = new SendGmail();
		//gmail.email(t_email, Subject, security_key_mail);

		gmail.email(this.ins_pubuser.email, "Your verification code is"+this.ins_otp.password+" for the transaction id"+this.ins_otp.refno+"", "NGM","");//this.ins_user.userid
		return true;
	}
	public boolean generateVerificationCode(HttpServletRequest p_request)throws Exception {		//ok

		//ok
		System.out.println("+++generateVerificationCode+++");

		Boolean l_status=false;	
		ins_pubuser.selectDB();

		String security_key=NGM.ins_utility.getsecurity_key_mobile();

		System.out.println("NGM ins_pubuser verf function this.t_mobile:"+ins_pubuser.mobile);
		ins_otp.login_id = ins_pubuser.login_id;
		if(ins_otp.selectDB()){
			ins_otp.status = "CAN";
			ins_otp.updateDB_status("ACT");
		}

		ins_otp.otp_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "otp_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "otp_id", Integer.parseInt(ins_otp.otp_id));
		ins_otp.refno = ins_otp.otp_id;
		this.refno = ins_otp.refno; ///remove ; same in sending fun
		ins_otp.login_id = ins_pubuser.login_id;
		if(ins_pubuser.is_mobile_verified.equals("Y")){
			ins_otp.email_mobile = "M";
		}
		else if(ins_pubuser.is_email_verified.equals("Y")){
			ins_otp.email_mobile = "E";
		}
		ins_otp.status = "ACT";
		ins_otp.password = security_key;

		if(ins_otp.insertDB()){
			l_status= true;

			System.out.println("...................security_key........................."+security_key);	
			//this.sendVerificationCode();
			if(ins_pubuser.is_mobile_verified.equals("Y")){
				this.sendVerificationCode();
			}
			else if(ins_pubuser.is_email_verified.equals("Y")){
				this.sendVerificationCodeEmail();
			}
		}
		return l_status;
	}

	public boolean usernameexist(HttpServletRequest p_request) throws Exception{	//ok
		//this.t_userid				= p_request.getParameter("fuserid");
		if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "pub_register", "login_id", ins_pubuser.login_id,NGM)){//this.t_userid 
			return true;
		}
		else{
			return false;
		}
	}


	/*public boolean saveforgotpassword(HttpServletRequest p_request)throws Exception {

		ins_user.userid = this.t_userid;
		ins_user.selectDB();

		this.t_verifymobile = ins_user.mob;
		if (this.t_verifymobile.equals(this.t_mobile)) {
			this.random = NGM.ins_utility.random_security_key(5);///Remove
			ins_user.password = this.t_userid + "$A" + this.random;
			this.pass = ins_user.password;
	 *//**
	 * adding to password reset history
	 *//*
			ins_user_pass_reset_public.userid=this.t_userid;
			if(!ins_user_pass_reset_public.insertDB())return false;
		}

		if (!ins_user.updateDB_password())return false;
		return true;

	}*/

	public void setPostDataChangepswd(HttpServletRequest p_request){//ok

		ins_pubuser.login_id			= p_request.getParameter("fuserid");
		ins_pubuser.password = password = p_request.getParameter("fpswd");
		this.t_confirm_password		=p_request.getParameter("fcpswd");

		ins_otp.login_id = ins_pubuser.login_id;
		System.out.println("************BEFore"+p_request.getParameter("fverfcode"));
		ins_otp.password		=p_request.getParameter("fverfcode");
		System.out.println("************AFT"+ins_otp.password);
		ins_otp.refno = p_request.getParameter("transaction_id");
	}

	public boolean verifyCode(HttpServletRequest p_request) throws Exception{	//ok
		System.out.println("     inside verifyCode   ");
		String otp_exp_time=NGM.ins_ngmprop.getpropvalue("otp_exp_time");
		ins_otp.status = "ACT";
		if(ins_otp.selectDB_verification_code(otp_exp_time))
		{ 
			System.out.println("trueeeeeeeeeeeee");
			ins_otp.status="OVE";
			ins_otp.login_id = p_request.getParameter("fuserid");
			ins_otp.updateDB_status("ACT");
			return true;
		}
		else{
			System.out.println("falseeeeeeeeeeee");
			ins_otp.status="CAN";
			ins_otp.login_id = p_request.getParameter("fuserid");
			ins_otp.updateDB_status("ACT");
			return false;
		}


	}

	public boolean checkPasswordHist(HttpServletRequest p_request) throws Exception{   //ok
		System.out.println("checkPasswordHist.................");
		if(ins_pubuser.select_password_history()){
			return true;
		}
		return false;
	}

	public boolean select_password(HttpServletRequest p_request) throws Exception{   //ok
		System.out.println("select_password.................");
		if(ins_pubuser.select_password()){
			return true;
		}
		return false;
	}

	public boolean insert_password_history(HttpServletRequest p_request) throws Exception{   //ok
		System.out.println("select_password.................");
		if(ins_pubuser.insert_password_history()){
			return true;
		}
		return false;
	}

	public boolean saveChangePassword(HttpServletRequest p_request) throws Exception{   //ok
		System.out.println("saveChangePassword.................");
		ins_pubuser.password=password;
		if(!ins_pubuser.updateDB_password())
		{System.out.println("here......");
		return false;
		}
		return true;

	}

	public String  get_verification_subject() throws Exception{ //change
		String email_verification_subject ="";
		PreparedStatement ps=NGM.conn.prepareStatement("select * from property where prop_id='email_pdf_path'");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			email_verification_subject = rs.getString("prop_id");///remove email_verification_subject
		}
		//close
		rs.close();
		ps.close();
		return email_verification_subject;
	}



	/*public boolean sendmail(HttpServletRequest p_request, int i)
			throws Exception {

		ins_forgotpassword.tmobile = this.t_mobile;
		ins_forgotpassword.tuserid = this.t_userid;
		ins_forgotpassword.selectDB();
		this.t_email = ins_forgotpassword.temail;
		indutility ins_indutility = new indutility();

		System.out.println("inside");
		// s Integer id=2;
		String verification_path = ins_indutility.get_verification_subject(i,IND);

		String verification_path3 = verification_path.replace("$$n", "\n");
		String verification_path4 = verification_path3.replace("$$t", "\t");

		String verification_path1 = verification_path4.replace("$name",
				ins_forgotpassword.tname);

		String foot1 = "Your username is :" + t_userid
				+ " \n New Password is :" + this.pass + "";
		String verification_path2 = verification_path1.replace("$verification",
				foot1);

		String email = this.t_email;
		ins_mail.email(email, "ESS ODISHA PORTAL PASSWORD RESET",
				verification_path2);
		return true;

	}

	public boolean updateforgotpassword(HttpServletRequest p_request)
			throws Exception {

		// ins_registration.tappname=this.t_appname;
		// ins_registration.tappid=this.t_appid;
		// if(!ins_registration.updateDB())return false;

		return true;
	}

	public boolean sendSms(){
		System.out.println("Going to send Pwd Reset sms");
		if(this.pass==null){
		System.out.println("Pasword is Null");
			return false;
		}
	sendSMS.SmsMessage(t_mobile, "Password is Successfully Reset! Your New Password is "+this.pass, "ESS-PORTAL");
	return true;
	}*/
	/*

	 */

	/*
	public void setPostDataChangepswd(HttpServletRequest p_request){

		this.t_userid			= p_request.getParameter("fuserid");
		this.t_newpassword		=p_request.getParameter("fpswd");
		this.t_confirm_password		=p_request.getParameter("fcpswd");
		this.t_verifymobile		=p_request.getParameter("fverfcode");
		}
	public boolean verifyCode(HttpServletRequest p_request) throws Exception{
		this.t_userid			= p_request.getParameter("fuserid");
		this.t_verifymobile				= p_request.getParameter("fverfcode");

		ins_verf_code.login_id=this.t_userid;
		ins_verf_code.mobileverification=this.t_verifymobile;

		if(ins_verf_code.selectDB_verification_code())
		{ 
		return true;
		 }
		else{
		return false;
		}
	}
	public boolean saveChangePassword(HttpServletRequest p_request) throws Exception{
		ins_user_reg.login_id=this.t_userid;
		ins_user_reg.mobileverification=this.t_verifymobile;
		ins_user_reg.password=this.t_newpassword;
		ins_user_reg.updateDB_password();
		return true;
	}
	 */

}//end class