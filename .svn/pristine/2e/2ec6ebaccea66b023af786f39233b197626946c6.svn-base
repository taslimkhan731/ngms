package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.SendGmail;
import common.c_ngm_fileitem;
import common.c_ngm_fileupload;
import common.common;
import common.validation;
import db.d_otp_data;
import db.d_publicregister;
import db.ngm;
import db.m_user_details;
import db.m_role;

public class m_changepublicprofile extends ngmodel{

	public String t_smscheck,t_emailcheck,refno;
	
	m_user_details	ins_m_user;
	m_role ins_orgdesig;
	d_publicregister ins_pubreg;
	d_otp_data ins_otp = null;

public m_changepublicprofile(ngm p_ngm) {
	NGM		= p_ngm;
	ins_m_user		= new m_user_details(p_ngm);
	ins_orgdesig	= new m_role(p_ngm);
	ins_val			= new validation();
	ins_cal 		= Calendar.getInstance();
	ins_pubreg = new d_publicregister(NGM);
	ins_otp = new d_otp_data(NGM);
	}

public void setPostData(HttpServletRequest p_request){
	System.out.println("setPostData");
	ins_pubreg.name =  p_request.getParameter("txt_name");
	ins_pubreg.dob = p_request.getParameter("txt_yy")+"-"+p_request.getParameter("txt_mm")+"-"+p_request.getParameter("txt_dd");;
	ins_pubreg.state =  p_request.getParameter("txt_state");
	ins_pubreg.email =  p_request.getParameter("txt_eaddress");
	System.out.println("...............................ins_pubreg.email.... "+ins_pubreg.email);
    ins_pubreg.mobile =  p_request.getParameter("txt_mobile");
	ins_pubreg.login_id =  p_request.getParameter("username");
	
	this.t_smscheck = "Mobile";
	System.out.println(" this.t_smscheck "+this.t_smscheck);
}
	

public boolean savechangepubprofile(HttpServletRequest p_request, List fILEMULTIPARTITEMS, common ins_common) throws Exception {
	setPostDataFile(fILEMULTIPARTITEMS,ins_common,null);
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

public boolean savechangepubprofile_Y(HttpServletRequest p_request) throws Exception {

	setPostData(p_request);
	
	if (!ins_pubreg.updateDB_Y()) {
		System.out.println(" insert false... ins_pubreg.login_id "+ins_pubreg.login_id);

	//	this.message1 = ins_pubreg.returnmessage;
		//this.message2 = ins_pubreg.returnmessagekey;
		return false;
	}else{
		System.out.println(" insert true... ins_pubreg.login_id "+ins_pubreg.login_id);

		return true;
	}
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
public boolean change_no(HttpServletRequest p_request) throws Exception {
	setPostData(p_request);
	if (!ins_pubreg.select_mob_email()) 
 		return false;
	else
	   return true;
}

public boolean change_no_check(HttpServletRequest request, List fILEMULTIPARTITEMS, common ins_common) throws Exception {
	String path = null;
	String fileName = null;
	String temp_curdate = NGM.ins_utility.getcurdatestring();
	c_ngm_fileupload ins_bfupload = new c_ngm_fileupload();
	
	ins_bfupload.read_file_request(fILEMULTIPARTITEMS, NGM.ins_sess.user_id, NGM.ins_ngmprop,temp_curdate);
	ArrayList<c_ngm_fileitem> fileitem_list	= ins_bfupload.fileitem_list;
	
	Iterator itr = fileitem_list.iterator();
	while (itr.hasNext()) {
		c_ngm_fileitem cfi = (c_ngm_fileitem)itr.next();
		path=cfi.filepath;
		fileName = cfi.s_filename;
	}
	
	System.out.println(path);
	setPostDataFile(fILEMULTIPARTITEMS,ins_common,path);
	
	if(fileName != null){
		if(!fileName.isEmpty()){
			ins_pubreg.updateDB_file();
		}else{
			ins_pubreg.updateDB_Y();
		}
	}else{
		ins_pubreg.updateDB_Y();
	}
	if (!ins_pubreg.select_mob()){ 
		return false;
	}
	else{
	   return true;
	}
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

public boolean sendVerificationCode()throws Exception {		//ok
	this.refno = this.ins_otp.refno;
	String templateId="1107160707717945638";
		NGM.ins_utility.SENDSMS(this.ins_pubreg.mobile, "Your verification code is "+this.ins_otp.password+" for the reference no "+this.ins_otp.refno+"", "NGM",templateId);//this.ins_user.userid
		//emaillll
		
		return true;
	}
public boolean generateVerificationCodeEmail(HttpServletRequest p_request)throws Exception {		//ok
	System.out.println("+++generateVerificationCodeEmail+++");
	Boolean l_status=false;	
	ins_pubreg.selectDB();
	
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
	return l_status;
	
	}

public boolean sendVerificationCodeEmail()throws Exception {		//ok
	this.refno = this.ins_otp.refno;
	SendGmail gmail = new SendGmail();
	//gmail.email(t_email, Subject, security_key_mail);
	
	gmail.email(this.ins_pubreg.email, "Your verification code is "+this.ins_otp.password+" for the transaction id"+this.ins_otp.refno+"", "NGM","");//this.ins_user.userid
		return true;
	}

public boolean updatechangeprofile(HttpServletRequest p_request) throws Exception {

	setPostData(p_request);
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
public void setPostDataChangepswd(HttpServletRequest p_request){//ok
	
	ins_otp.login_id			= p_request.getParameter("username");
	ins_otp.password		=p_request.getParameter("fverfcode");
	ins_otp.refno = p_request.getParameter("refno");
	}

public boolean verifyCode(HttpServletRequest p_request) throws Exception{	//ok
	System.out.println("     inside verifyCode   ");
	String otp_exp_time=NGM.ins_ngmprop.getpropvalue("otp_exp_time");
	ins_otp.status = "ACT";
	if(ins_otp.selectDB_verification_code(otp_exp_time))
	{ 
		System.out.println("trueeeeeeeeeeeee");
		ins_otp.status="OVE";
		ins_otp.login_id = p_request.getParameter("username");
		ins_otp.updateDB_status("ACT");
			return true;
	}
	else{
		System.out.println("falseeeeeeeeeeee");
		ins_otp.status="CAN";
		ins_otp.login_id = p_request.getParameter("username");
		ins_otp.updateDB_status("ACT");
		return false;
	}

}
public boolean saveDetails(HttpServletRequest p_request) throws Exception{   //ok
	System.out.println("     inside saveDetails   ");
	ins_otp.login_id = p_request.getParameter("username");
	ins_otp.status = "OVE";
	String mailormobile=ins_otp.selectDBmailormobile();
	String parameter="";
	if(mailormobile.equals("E")){
		parameter = "is_email_verified";
	}
	else if(mailormobile.equals("M")){
		parameter = "is_mobile_verified";
	}
	ins_pubreg.login_id = p_request.getParameter("username");
	ins_pubreg.is_active = "Y";
	if(!ins_pubreg.updateDBactive(parameter)) return false;
	return true;
}

public boolean usernameexist(HttpServletRequest p_request) throws Exception {
	ins_pubreg.login_id = p_request.getParameter("username");
	if (!NGM.ins_utility.checkConstrain_reg(NGM.tr_conn,"pub_register", "login_id",ins_pubreg.login_id)) {
		System.out.println(" false ins_pubreg.login_id "+ins_pubreg.login_id);
		return false;
	} else {
		System.out.println(" true ins_pubreg.login_id "+ins_pubreg.login_id);
		return true;
	}
}

public void setPostDataFile(List fILEMULTIPARTITEMS, common ins_common, String path){
	System.out.println("setPostData");
	ins_pubreg.name =  ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_name");
	ins_pubreg.dob = ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_yy")+"-"+ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_mm")+"-"+ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_dd");;
	ins_pubreg.state =  ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_state");
	ins_pubreg.email =  ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_eaddress");
	System.out.println("...............................ins_pubreg.email.... "+ins_pubreg.email);
    ins_pubreg.mobile =  ins_common.getparametervalue(fILEMULTIPARTITEMS,"txt_mobile");
	ins_pubreg.login_id =  ins_common.getparametervalue(fILEMULTIPARTITEMS,"loginId");
	ins_pubreg.docType =  ins_common.getparametervalue(fILEMULTIPARTITEMS,"docType");
	ins_pubreg.path = path;
	this.t_smscheck = "Mobile";
	System.out.println(" this.t_smscheck "+this.t_smscheck);
}

public String getFilePath(String req_id) throws Exception{
		String path = null;
		try {
			if(req_id != null){
				if(!req_id.isEmpty()){
					path = ins_pubreg.getFilePath(req_id.trim());
				}
			}
		} catch (Exception e) {}
		return path;
	}

}
