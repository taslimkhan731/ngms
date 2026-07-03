package model;
import java.net.InetAddress;


import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
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

public class m_requestupload_client extends ngmodel{
	utility 		ins_util;
	 
public	d_uploadtable ins_upd;
	d_reqeustupload_index ins_upin;
	d_upload_details ins_det;
	m_user_details ins_m_user;
	d_gadrequest d_gad;
	d_guestdetails d_guest;
	d_vehicle d_veh;
	d_mod_req mod_req;
	d_mod_req_room room_req;
	d_process_table pro_tbl;
	d_reservation_maintable res_tbl;
	d_reservation_waitinglist res_wtl;
	d_cancel_main ins_can;
	d_cancel_reservation ins_res_can;
	String	client_ip;
	String password;
	String userid ;
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	JSONObject json= new JSONObject();
	 
  String tr_up_id, tr_uin_id,tablename;

 

	public m_requestupload_client(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_upin = new d_reqeustupload_index(NGM);
		ins_det = new d_upload_details(NGM);
		ins_m_user =new m_user_details(NGM);
		d_gad= new d_gadrequest(NGM);
		d_guest = new d_guestdetails(NGM);
		d_veh= new d_vehicle(NGM);
		mod_req= new d_mod_req(NGM);
		 room_req= new d_mod_req_room(NGM);
		 pro_tbl= new d_process_table (NGM);
		 res_tbl= new d_reservation_maintable(NGM);
		 res_wtl= new d_reservation_waitinglist(NGM);
		 ins_can = new d_cancel_main(NGM);
		 ins_res_can = new d_cancel_reservation(NGM);
		ins_val =new validation();
	 

		 
		
		}
 	 
	 
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		  boolean l_status =false;
		 
		if(!this.setpostdata(p_request)){
			 
			 l_status = true;	
			 
		}else{
		 
		 		 
		}		return l_status; 
					 
				}	
	public boolean setpostdata(HttpServletRequest p_request) throws Exception{
		boolean l_status=false;
		String jsonstr =    p_request.getParameter("jobj");
		System.out.println("jjjjjjjjjjjjjsonnnnnnnnnnnnnnnnns"+jsonstr);
		 Object obj=JSONValue.parse(jsonstr);
		 JSONObject jsonObj1  = (JSONObject)obj;
		 	 
		 JSONArray d_gadrequest = (JSONArray)jsonObj1.get("gad_request");
		 JSONArray d_guestdetails = (JSONArray)jsonObj1.get("guest_details");
		 JSONArray d_vehicle = (JSONArray)jsonObj1.get("veh_req");
		 JSONArray d_mod_req = (JSONArray)jsonObj1.get("mod_req");
		 JSONArray d_mod_req_room = (JSONArray)jsonObj1.get("mod_req_room");
		 JSONArray d_process_table = (JSONArray)jsonObj1.get("process_table");
		 JSONArray d_reservation_maintable = (JSONArray)jsonObj1.get("reservation_maintable");
		 JSONArray d_reservation_waitinglist = (JSONArray)jsonObj1.get("reservation_waitinglist");
		 JSONArray cancel_main = (JSONArray)jsonObj1.get("cancel_reservation");

		
		 
		 
		 if(this.update_gadrequest(d_gadrequest)) {l_status = true;}
			 
			 if(this.update_guestdetails(d_guestdetails)){l_status = true;}
			 
		  			if(this.update_vehicle(d_vehicle)){l_status = true;}
		  				if(this.update_mode_req(d_mod_req)){l_status = true;}
		  					if(this.update_room_req(d_mod_req_room)){l_status = true;}
		  						if(this.update_processtable(d_process_table)){l_status = true;}
		  							if(this.update_reserv_main(d_reservation_maintable)){l_status = true;}
		  								if(this.update_waitinglist(d_reservation_waitinglist)){l_status = true;}
		  									if(this.cancel_main(cancel_main)){l_status = true;}
		  									 
		  			 
		  				
		  			 
		  	 
		
		 return l_status;
	}
 
	public boolean update_gadrequest(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest"+array);
		  if(array!=null){
		 for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		d_gad.setpost_mod2(Jvalue);
		 		d_gad.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!d_gad.insertDB_gadreq_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
		  }
		  }		 	  
		  return retval;
	  }
 
	public boolean update_guestdetails(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		d_guest.setpost_mod(Jvalue);
		 d_guest.existdata_upload();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!d_guest.insertDB_guest_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		 		 
					 } 
		 }
		  
		  return retval;
	  }
	public boolean update_vehicle(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		d_veh.setpost_mod(Jvalue);
		 		d_veh.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!d_veh.insertDB_veh_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		 		 
					 } 
		 }
		  
		  return retval;
	  }
	public boolean update_mode_req(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		mod_req.setpost_mod(Jvalue);
		 		mod_req.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!mod_req.insertDB_mod_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		 		 
					 } 
		 }
		  
		  return retval;
	  }
	public boolean update_room_req(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		room_req.setpost_mod(Jvalue);
		 		room_req.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!room_req.insertDB_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		  }
					 } 
		  
		  return retval;
	  }
	public boolean update_processtable(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		pro_tbl.setpost_mod(Jvalue);
		 		pro_tbl.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!pro_tbl.insertDB_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
					 		 
					 } 
		 }
		  
		  return retval;
	  }
	public boolean update_reserv_main(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		res_tbl.setpost_mod(Jvalue);
		 		res_tbl.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!res_tbl.insertDB_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
					 		 
					 } 
		 }
		  
		  return retval;
	  }
	
	public boolean update_waitinglist(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata waiting list"+array);
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		res_wtl.setpost_mod(Jvalue);
		 		res_wtl.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!res_wtl.insertDB_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		  }
					 } 
		  
		  return retval;
	  }
	
	/*public boolean cancel_main(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest"+array);
		  if(array!=null){
		 for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_can.setpost_cancel(Jvalue);
		 		ins_can.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!ins_can.insertDB()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
		  }
		  }		 	  
		  return retval;
	  }*/
	public boolean cancel_main(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest"+array);
		  if(array!=null){
		 for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_res_can.setpost_cancel(Jvalue);
		 		ins_res_can.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!ins_res_can.insertDB_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
		  }
		  }		 	  
		  return retval;
	  }
	
public void setuserdata(HttpServletRequest p_request) {
	this.userid = p_request.getParameter("username");
	System.out.println("dasdfdfddddddddddd"+this.userid);
	try {
		InetAddress thisIp = InetAddress.getLocalHost();
		client_ip = thisIp.getHostAddress();
//		 client_ip = p_request.getRemoteAddr();
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
	String captcha_id  = (String)lsession.getAttribute("upload_capid");
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

 
}
	
	



