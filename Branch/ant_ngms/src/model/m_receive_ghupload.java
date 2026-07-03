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

public class m_receive_ghupload extends ngmodel{
	utility 		ins_util;
	 
public	d_uploadtable ins_upd;
gh_upload_index ins_upin;
	d_checkin ins_chkin;
d_checkin_allot ins_ckalot;
d_allotment ins_alot;
m_user_details ins_m_user;
d_cancel_main ins_can;
d_cancel_reservation ins_res_can;
d_gadrequest d_gad;
d_guestdetails d_guest;
d_mod_req mod_req;
d_mod_req_room room_req;
d_process_table pro_tbl;
d_reservation_maintable res_tbl;
d_reservation_waitinglist res_wtl;
	String	client_ip;
	String password;
	String userid ;
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	JSONObject json= new JSONObject();
	 
  String tr_up_id, tr_uin_id,tablename;

 

	public m_receive_ghupload(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_upin = new gh_upload_index(NGM);
		 
		ins_chkin= new d_checkin(NGM);
		ins_ckalot = new d_checkin_allot(NGM);
		ins_alot= new d_allotment(NGM);
		ins_m_user =new m_user_details(NGM);
ins_can = new d_cancel_main(NGM);
ins_res_can = new d_cancel_reservation(NGM);

d_gad = new d_gadrequest(NGM);
d_guest = new d_guestdetails(NGM);
mod_req = new d_mod_req(NGM);
room_req= new d_mod_req_room(NGM);
pro_tbl = new d_process_table(NGM);
res_tbl= new d_reservation_maintable(NGM);
res_wtl= new d_reservation_waitinglist(NGM);
		ins_val =new validation();
		 

		 
		
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
						 }}
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
						 }}
				
		 		 
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
						 }}
				
		 		 
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
						 }}
				
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
					 		 
					 } }
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
	 
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		  boolean l_status =false;
		 
		  if(!this.setpostdata(p_request)){
				// this.update_indextable(p_request, "S");
				 l_status = true;	
				  
			}else{
				//this.update_indextable(p_request, "F");
			 		 
			}		return l_status; 
						 
					 
				}	
	public boolean setpostdata(HttpServletRequest p_request) throws Exception{
		boolean l_status=false;
		String jsonstr =    p_request.getParameter("jobj");
		System.out.println("jjjjjjjjjjjjjsonnnnnnnnnnnnnnnnns"+jsonstr);
		 Object obj=JSONValue.parse(jsonstr);
		 JSONObject jsonObj1  = (JSONObject)obj;
		 	 
		 JSONArray tr_checkin = (JSONArray)jsonObj1.get("tr_checkin");
		 JSONArray tr_checkin_alot = (JSONArray)jsonObj1.get("tr_check_alot");
		 JSONArray tr_allotment = (JSONArray)jsonObj1.get("tr_allotment");
		 JSONArray cancel_main = (JSONArray)jsonObj1.get("cancel_reservation");
		 
		 JSONArray d_gadrequest = (JSONArray)jsonObj1.get("gad_request");
		 JSONArray d_guestdetails = (JSONArray)jsonObj1.get("guest_details");
		 JSONArray d_mod_req = (JSONArray)jsonObj1.get("mod_req");
		 JSONArray d_mod_req_room = (JSONArray)jsonObj1.get("mod_req_room");
		 JSONArray d_process_table = (JSONArray)jsonObj1.get("process_table");
		 JSONArray d_reservation_maintable = (JSONArray)jsonObj1.get("reservation_maintable");
		 JSONArray d_reservation_waitinglist = (JSONArray)jsonObj1.get("reservation_waitinglist");
			 
		 if(this.update_gadrequest(d_gadrequest)) {l_status=true;}
		 
		 if(this.update_guestdetails(d_guestdetails)){l_status=true;}
		 
	  				if(this.update_mode_req(d_mod_req)){l_status=true;}
	  					if(this.update_room_req(d_mod_req_room)){l_status=true;}
	  						if(this.update_processtable(d_process_table)){l_status=true;}
	  							if(this.update_reserv_main(d_reservation_maintable)){l_status=true;}
	  								if(this.update_waitinglist(d_reservation_waitinglist)){l_status=true;}
		  								
		 if(this.update_checkin(tr_checkin)) {l_status = true;}
			 
			 if(this.update_chekin_alot(tr_checkin_alot)){l_status = true;}
			 
		  			if(this.update_allotment(tr_allotment)) {l_status = true;}
		  				 
		  				 if(this.cancel_main(cancel_main)){
		  					  			 			 	  			 
		  				l_status = true;
		  			}
		 
		 return l_status;
	}
 
	public boolean update_checkin(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest"+array);
		  if(array!=null){
		 for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_chkin.setpost_upload(Jvalue);
		 		ins_chkin.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!ins_chkin.insertDB_check_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
		  }
		  }		 	  
		  return retval;
	  }
 
	public boolean update_chekin_alot(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_ckalot.setpost_upload(Jvalue);
		 		ins_ckalot.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!ins_ckalot.insertDB_check_allot_upload()){
							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		 		 
					 } 
		 }
		  
		  return retval;
	  }
	public boolean update_allotment(JSONArray array)throws Exception{
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		 if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_alot.setpost_upload(Jvalue);
		 		ins_alot.existdata();
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!ins_alot.insertDB_upload()){
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
				if(!ins_can.insertDB_upload()){
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
public boolean update_indextable(HttpServletRequest p_request,String status) throws Exception{
	  boolean l_status =false;
	  ins_upin.user_id = NGM.ins_sess.user_id;
	 	 ins_upin.status = status;
		if(ins_upin.insertindex()){
			l_status = true;
		}
	//ins_upin.insertindex_failed();
	  				 
	 		return l_status; 
				 
			}	

 
}
	
	



