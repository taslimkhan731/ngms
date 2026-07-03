package model;
import java.net.InetAddress;


import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.itextpdf.text.log.SysoLogger;
import com.sun.jersey.json.impl.writer.JsonEncoder;

import common.utility;
import common.validation;
import db.*;

public class m_importdata extends ngmodel{
	utility 		ins_util;
	 
 
	m_user_details ins_m_user;
	 
	String	client_ip;
	String password;
	String userid ;
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	public JSONObject json= new JSONObject();
	d_publicguestdetails ins_guest;
	d_pubrequest ins_req;
	d_publicvehicle ins_veh;
	d_cancel_main ins_can;
	d_cancel_reservation ins_res_can;
	public m_importdata(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guest = new d_publicguestdetails(NGM); 
		ins_req = new d_pubrequest(NGM); 
		ins_veh = new d_publicvehicle(NGM); 
	  ins_can = new d_cancel_main(NGM);
	  ins_res_can = new d_cancel_reservation(NGM);
		ins_m_user =new m_user_details(NGM);
		ins_val =new validation();
		 	
		
		}
		
	public void setuserdata(HttpServletRequest p_request) {
		this.userid = p_request.getParameter("username");
		System.out.println("dasdfdfddddddddddd"+this.userid);
		try {
			InetAddress thisIp = InetAddress.getLocalHost();
			client_ip = thisIp.getHostAddress();
//			 client_ip = p_request.getRemoteAddr();
		}
		catch (Exception e) {

		}
		this.password = p_request.getParameter("password");
		System.out.println("asdfasdfasdf"+this.password);
	}
	public boolean validatePostData() throws Exception {
		System.out.println(this.userid);
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
	public boolean isUservalid(HttpServletRequest p_request) throws Exception {
		boolean retval = false;
		ins_m_user.userid =   this.userid;
		HttpSession lsession	= p_request.getSession(true);
		String captcha_id  = (String)lsession.getAttribute("imp_capid");
		System.out.println(this.userid);
		if (ins_m_user.selectDB()){
		 System.out.println("sdfssuccess");
			String l_salt = ins_util.getSalt(ins_m_user.password,captcha_id);
		

		if (this.userid.equals(ins_m_user.userid) && this.password.equals(l_salt) && ins_m_user.status.equals("Y")) {
			 System.out.println("sdfssuccessuserid");  	 
				retval= true;
		}
		}
		return retval;
	}

public boolean importdata(HttpServletRequest request)throws Exception{
	boolean retval = false;
	JSONArray jsonobj = new JSONArray() ; 
	//JSONObject json= new JSONObject();
	String time= request.getParameter("time");
	if(ins_req.selectimportdata(time)) {
		jsonobj = ins_req.array1;
		json.put("requestdetails", jsonobj);
	}
 	if(ins_guest.selectimportdata(time)) {
		jsonobj = ins_guest.array1;
		json.put("guestdetails", jsonobj);
 	}
		
			if(ins_veh.selectimportdata(time)) {
				jsonobj = ins_veh.array1;
				json.put("vehicle", jsonobj);
			}
		/*	if(ins_can.selectimportdata(time)) {
				jsonobj = ins_can.array1;
				json.put("tr_cancel_main", jsonobj);
			}*/
			if(ins_res_can.selectimportdata(time)) {
				jsonobj = ins_res_can.array1;
				json.put("cancel_reservation", jsonobj);
			}
 	System.out.println("asdfasdfasdf"+jsonobj.toString());
	return true;
}

 
public boolean changeread(String time)throws Exception{
	
	boolean retval = false;
	
	if(ins_req.updated_impdata(time)){
		retval = true;
	}
	return retval;
}

 
 
}
	



