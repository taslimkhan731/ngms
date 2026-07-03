package model;

import common.SendGmail;
import common.sendsms;

//import common.conversion;
import common.utility;
//import common.todaysDate;
import common.validation;
import db.d_otp_data;
import db.ngm;
import db.d_publicregister;


//import java.util.ArrayList;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


//import nic.in.sms.SendSMS;
//import in.gov.uidai.auth.aua.httpclient.AuthClient;
public class m_publicregister extends ngmodel {


	public String refno,message1, message2, t_capcha,t_password,t_repassword,t_smscheck,t_emailcheck;

	sendsms sendSMS = new sendsms();


	d_publicregister ins_pubreg = null;
	//SendGmail ins_mail;
	validation ins_val = null;

	//d_verification_code ins_verf_code			    = null;
	//d_verification_code_email ins_verf_email        = null;
	d_otp_data ins_otp = null;

	public m_publicregister(ngm p_NGM) {
		this.NGM = p_NGM;
		ins_val = new validation();
		ins_cal = Calendar.getInstance();
		ins_pubreg = new d_publicregister(NGM);
		//ins_mail = new SendGmail();

		//ins_verf_code		= new d_verification_code(NGM);
		//ins_verf_email      = new d_verification_code_email(NGM);
		ins_otp = new d_otp_data(NGM);
	}

	public void setPostData(HttpServletRequest p_request) {
		System.out.println("setPostData");
		String userid = null;
		String email = null;
		ins_pubreg.name =  p_request.getParameter("txt_name");
		ins_pubreg.dob = p_request.getParameter("txt_yy")+"-"+p_request.getParameter("txt_mm")+"-"+p_request.getParameter("txt_dd");;
		ins_pubreg.state =  p_request.getParameter("txt_state");

		ins_pubreg.mobile =  p_request.getParameter("txt_mobile");
		ins_pubreg.password = p_request.getParameter("txt_password");
		System.out.println("...............................ins_pubreg.password.... "+ins_pubreg.password);
		email = ins_pubreg.email =  p_request.getParameter("txt_eaddress");
		ins_pubreg.login_id = userid = p_request.getParameter("txt_mobile");
		//		if(userid != null){
		//			String [] s = userid.split("atdot");
		//			userid = s[0] + "@" + s[1];
		//			s = userid.split("attdot");
		//			userid = s[0];
		//			for(int i=1;i<s.length;i++){
		//				userid = userid + "." + s[i]; 
		//			}
		//		}

		if(email != null){
			if(!email.isEmpty()){
				String [] s = email.split("atdot");
				email = s[0] + "@" + s[1];
				s = email.split("attdot");
				email = s[0];
				for(int i=1;i<s.length;i++){
					email = email + "." + s[i]; 
				}
			}
		}
		ins_pubreg.email = email;
		ins_pubreg.login_id =  userid;
		this.t_password = p_request.getParameter("txt_password");
		this.t_repassword = p_request.getParameter("txt_repassword");//
		this.t_smscheck = "Mobile";
		System.out.println(" this.t_smscheck "+this.t_smscheck);
		//		this.t_emailcheck = p_request.getParameter("chk_mail");
		System.out.println(" this.t_emailcheck "+this.t_emailcheck);

		//this.t_capcha = p_request.getParameter("register-captcha");// 


	}





	public boolean checkemail(HttpServletRequest p_request) throws Exception {
		if((this.t_emailcheck== null) || (this.t_emailcheck.length()==0)){
			System.out.println(" checkemail false this.t_emailcheck "+this.t_emailcheck);

			return false;
		}
		System.out.println(" checkemail true this.t_emailcheck "+this.t_emailcheck);

		return true;
	}

	public boolean checkmobile(HttpServletRequest p_request) throws Exception {
		System.out.println("dddddd "+( (this.t_smscheck==null) || (this.t_smscheck.length()==0)));
		if((this.t_smscheck==null) || (this.t_smscheck.length()==0)){
			System.out.println(" checkmobile false this.t_emailcheck "+this.t_emailcheck);

			return false;
		}
		System.out.println(" checkmobile true this.t_emailcheck "+this.t_emailcheck);

		return true;
	}

	public boolean usernameexist(HttpServletRequest p_request) throws Exception {
		String userid = null;
		userid = p_request.getParameter("txt_mobile");
		//		if(userid != null){
		//			String [] s = userid.split("atdot");
		//			userid = s[0] + "@" + s[1];
		//			s = userid.split("attdot");
		//			userid = s[0];
		//			for(int i=1;i<s.length;i++){
		//				userid = userid + "." + s[i]; 
		//			}
		//		}
		ins_pubreg.login_id =  userid;
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOO "+ins_pubreg.login_id);

		if (!NGM.ins_utility.checkConstrain_reg(NGM.tr_conn,"pub_register", "login_id",ins_pubreg.login_id)) {
			System.out.println(" false ins_pubreg.login_id "+ins_pubreg.login_id);
			return false;
		} else {
			System.out.println(" true ins_pubreg.login_id "+ins_pubreg.login_id);
			return true;
		}

	}

	public boolean saveregistration(HttpServletRequest p_request) throws Exception {

		if (!ins_pubreg.insertDB()) {
			System.out.println(" insert false... ins_pubreg.login_id "+ins_pubreg.login_id);

			//	this.message1 = ins_pubreg.returnmessage;
			//this.message2 = ins_pubreg.returnmessagekey;
			return false;
		}else{
			System.out.println(" insert true... ins_pubreg.login_id "+ins_pubreg.login_id);

			return true;
		}
	}

	public boolean updateregistration(HttpServletRequest p_request) throws Exception {


		if (!ins_pubreg.updateDB()) {
			System.out.println(" insert false... ins_pubreg.login_id "+ins_pubreg.login_id);

			//	this.message1 = ins_pubreg.returnmessage;
			//this.message2 = ins_pubreg.returnmessagekey;
			return false;
		}else{
			System.out.println(" insert true... ins_pubreg.login_id "+ins_pubreg.login_id);

			return true;
		}
	}

	public boolean captcha(HttpServletRequest p_request) throws Exception {
		this.t_capcha = p_request.getParameter("sha2txt");
		if (this.t_capcha.equals(NGM.ins_sess.captcha_id)) {
			return true;
		}
		else{
			return false;
		}
	}

	public boolean sendVerificationCode()throws Exception {		//ok
		this.refno = this.ins_otp.refno;
		String templateId="1107160707698779534";
		NGM.ins_utility.SENDSMS(this.ins_pubreg.mobile, "Your verification code is "+this.ins_otp.password+" for the reference no "+this.ins_otp.refno+"", "NGM",templateId);//this.ins_user.userid
		//emaillll
		return true;
	}
	
	public boolean sendConfirmation()throws Exception {		//ok
		this.refno = this.ins_otp.refno;
		String templateId="1107160707714316860";
		NGM.ins_utility.SENDSMS(this.ins_pubreg.mobile, "Dear " + this.ins_pubreg.name + " You have successfully registered.", "NGM",templateId);//this.ins_user.userid
		return true;
	}


	public boolean sendVerificationCodeEmail()throws Exception {		//ok
		this.refno = this.ins_otp.refno;
		SendGmail gmail = new SendGmail();
		//gmail.email(t_email, Subject, security_key_mail);

		gmail.email(this.ins_pubreg.email, "Your verification code is "+this.ins_otp.password+" for the transaction id"+this.ins_otp.refno+"", "NGM","");//this.ins_user.userid


		return true;
	}
	public boolean generateVerificationCode(HttpServletRequest p_request)throws Exception {		//ok
		System.out.println("+++generateVerificationCode+++");

		Boolean l_status=false;	
		ins_pubreg.selectDB();

		String security_key=NGM.ins_utility.getsecurity_key_mobile();

		System.out.println("NGM verf function this.t_mobile:"+ins_pubreg.mobile);
		ins_otp.login_id = ins_pubreg.login_id;
		if(ins_otp.selectDB()){
			ins_otp.status = "CAN";
			ins_otp.updateDB_status("ACT");
		}

		ins_otp.otp_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "otp_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "otp_id", Integer.parseInt(ins_otp.otp_id));
		ins_otp.refno = ins_otp.otp_id;
		this.refno = ins_otp.refno; ///remove ; same in sending fun
		ins_otp.login_id = ins_pubreg.login_id;
		ins_otp.email_mobile = "M";
		ins_otp.status = "ACT";
		ins_otp.password = security_key;

		if(ins_otp.insertDB()){
			l_status= true;

			System.out.println("...................mobile security_key........................."+security_key);	
			this.sendVerificationCode();
		}
		return l_status;


	}
	
	public boolean generateConfirmation(HttpServletRequest p_request)throws Exception {		//ok
		System.out.println("+++generateConfirmationCode+++");

		Boolean l_status=false;	
		ins_pubreg.selectDB();

		System.out.println("NGM verf function this.t_mobile:"+ins_pubreg.mobile);
		ins_otp.login_id = ins_pubreg.login_id;
		if(ins_otp.selectDB()){
			ins_otp.status = "CAN";
			ins_otp.updateDB_status("ACT");
		}
		l_status = this.sendConfirmation();
		return l_status;
	}

	public boolean generateVerificationCodeEmail(HttpServletRequest p_request)throws Exception {		//ok
		System.out.println("+++generateVerificationCodeEmail+++");
		Boolean l_status=false;	
		ins_pubreg.selectDB();
		if(ins_pubreg.email != null){
			if(!ins_pubreg.email.isEmpty()){
				String security_key_email=NGM.ins_utility.getsecurity_key_mobile();

				System.out.println("NGM verf function this.t_mobile:"+ins_pubreg.mobile);


				ins_otp.login_id = ins_pubreg.login_id;
				if(ins_otp.selectDB()){
					ins_otp.status = "CAN";
					ins_otp.updateDB_status("ACT");
				}

				ins_otp.otp_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "otp_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "otp_id", Integer.parseInt(ins_otp.otp_id));
				ins_otp.refno = ins_otp.otp_id;
				this.refno = ins_otp.refno; ///remove ; same in sending fun
				ins_otp.login_id = ins_pubreg.login_id;
				ins_otp.email_mobile = "E";
				ins_otp.status = "ACT";
				ins_otp.password = security_key_email;
				if(ins_otp.insertDB()){
					l_status= true;

					System.out.println("...................mail security_key1........................."+security_key_email);	
					this.sendVerificationCodeEmail();
				}
			}
		}
		return l_status;

	}

	public void setPostDataChangepswd(HttpServletRequest p_request){//ok
		String userid = null;
		userid = p_request.getParameter("txt_mobile");
		//		if(userid != null){
		//			String [] s = userid.split("atdot");
		//			userid = s[0] + "@" + s[1];
		//			s = userid.split("attdot");
		//			userid = s[0];
		//			for(int i=1;i<s.length;i++){
		//				userid = userid + "." + s[i]; 
		//			}
		//		}
		ins_otp.login_id =  userid;
		System.out.println("RRRRR "+ins_otp.login_id);
		ins_otp.password		=p_request.getParameter("fverfcode");
		ins_otp.refno = p_request.getParameter("refno");
	}

	public boolean verifyCode(HttpServletRequest p_request) throws Exception{	//ok
		System.out.println("     inside verifyCode   ");
		String userid = null;
		userid = p_request.getParameter("txt_mobile");
		//		if(userid != null){
		//			String [] s = userid.split("atdot");
		//			userid = s[0] + "@" + s[1];
		//			s = userid.split("attdot");
		//			userid = s[0];
		//			for(int i=1;i<s.length;i++){
		//				userid = userid + "." + s[i]; 
		//			}
		//		}
		String otp_exp_time=NGM.ins_ngmprop.getpropvalue("otp_exp_time");
		ins_otp.status = "ACT";
		if(ins_otp.selectDB_verification_code(otp_exp_time))
		{ 
			System.out.println("trueeeeeeeeeeeee");
			ins_otp.status="OVE";
			ins_otp.login_id =  userid;
			ins_otp.updateDB_status("ACT");
			return true;
		}
		else{
			System.out.println("falseeeeeeeeeeee");
			ins_otp.status="CAN";
			ins_otp.login_id =  userid;
			ins_otp.updateDB_status("ACT");
			return false;
		}

	}
	public boolean saveDetails(HttpServletRequest p_request) throws Exception{   //ok
		System.out.println("     inside saveDetails   ");
		String userid = null;
		userid = p_request.getParameter("txt_mobile");
		//		if(userid != null){
		//			String [] s = userid.split("atdot");
		//			userid = s[0] + "@" + s[1];
		//			s = userid.split("attdot");
		//			userid = s[0];
		//			for(int i=1;i<s.length;i++){
		//				userid = userid + "." + s[i]; 
		//			}
		//		}
		ins_otp.login_id = userid;
		ins_otp.status = "OVE";
		String mailormobile=ins_otp.selectDBmailormobile();
		String parameter="";
		if(mailormobile.equals("E")){
			parameter = "is_email_verified";
		}
		else if(mailormobile.equals("M")){
			parameter = "is_mobile_verified";
		}
		ins_pubreg.login_id = userid;
		ins_pubreg.is_active = "Y";
		if(!ins_pubreg.updateDBactive(parameter)) return false;
		return true;
	}

	public boolean nouser(HttpServletRequest p_request) throws Exception {
		String userid = p_request.getParameter("username");
		if (userid == "") {
			return true;
		}
		return false;
	}


}