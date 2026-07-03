package model;
import java.net.InetAddress;


import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.itextpdf.text.log.SysoLogger;
import com.sun.jersey.json.impl.writer.JsonEncoder;

import common.utility;
import common.validation;
import db.*;

public class m_receive_impdata extends ngmodel{
	utility 		ins_util;
	 
  
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	JSONObject json= new JSONObject();
	d_publicguestdetails ins_guest;
	d_pubrequest ins_req;
	d_publicvehicle ins_veh;
	d_importindex ins_indx;
	d_cancel_main ins_can;
	d_cancel_reservation ins_res_can;
	public m_receive_impdata(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guest = new d_publicguestdetails(NGM); 
		ins_req = new d_pubrequest(NGM); 
		ins_veh = new d_publicvehicle(NGM); 
	  ins_can = new d_cancel_main(NGM);
	  ins_res_can = new d_cancel_reservation(NGM);
		ins_val =new validation();
		ins_indx = new d_importindex(NGM);
		} 

	public boolean setpostdata(HttpServletRequest p_request) throws Exception{
		boolean l_status=false;
		String jsonstr =    p_request.getParameter("jobj");
		System.out.println(jsonstr);
		 Object obj=JSONValue.parse(jsonstr);
		 JSONObject jsonObj1  = (JSONObject)obj;
		 	 
		 JSONArray reqarray = (JSONArray)jsonObj1.get("requestdetails");
		 JSONArray guestarray = (JSONArray)jsonObj1.get("guestdetails");
		 JSONArray veharray = (JSONArray)jsonObj1.get("vehicle");
		 JSONArray cancel = (JSONArray)jsonObj1.get("cancel_reservation");
		
		System.out.println("sddddddddddddasdfdddddddsssata");
		 if(this.update_guestdetails(guestarray)) {l_status = true;}
			 
			 if(this.update_pub_request(reqarray)){l_status = true;}
				 
		  			if(this.update_veh_details(veharray)){l_status = true;}
		  				if(this.update_cancel_main(cancel)){l_status = true;}
		  			 
		  				if(ins_indx.insert_db()){
		  				l_status = true;
		  			} 
		  		 
		 
	 
		 return l_status;
	}
	
	 public boolean update_pub_request(JSONArray array)throws Exception{
		 System.out.println("asdfdddddddata guest");
		  boolean retval = true;
		  if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		 System.out.println(Jvalue);
		 		ins_req.setpost_mod(Jvalue);
		 		if(!ins_req.existdata()){
				if(!ins_req.insert_received_data()){
//							 
					retval= false;
						 }
				
		 		}
		 		 
					 } 
		  }
		  return retval;
	  }
	 public boolean update_veh_details(JSONArray array)throws Exception{
		 
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		  if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_veh.setpost_mod(Jvalue);
		 		if(!ins_veh.existdata()){
				if(!ins_veh.insert_received_data()){
//							 
					retval= false;
						 }
				
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
		 		ins_guest.setpost_mod(Jvalue);
		 		if(!ins_guest.existdata()){
		 			System.out.println("dffsdfis ediiiiiiited");
				if(!ins_guest.insert_received_data()){
 							 System.out.println("dffsdfis insertin");
					retval= false;
						 }
				
		 		}
		 		 
					 } 
			 
		 }
		  return retval;
	  }
	 
 /*public boolean update_cancel_main(JSONArray array)throws Exception{
		 
		 boolean retval = true;
		 System.out.println("asdfdddddddata guest");
		  if(array!=null){
		  for(int i=0;i<array.size();i++) {
				 
		 		 JSONObject Jvalue = (JSONObject)array.get(i); 
		 		ins_can.setpost_cancel(Jvalue);
		 		if(!ins_can.existdata_accept()){
				if(!ins_can.insertDB()){
//							 
					retval= false;
						 }
				
		 		}
		 		 
					 } 
	 
		  }
		  return retval;
	  }*/
 
 public boolean update_cancel_main(JSONArray array)throws Exception{
	 
	 boolean retval = true;
	 System.out.println("asdfdddddddata guest");
	  if(array!=null){
	  for(int i=0;i<array.size();i++) {
			 
	 		 JSONObject Jvalue = (JSONObject)array.get(i); 
	 		ins_res_can.setpost_cancel(Jvalue);
	 		if(!ins_res_can.existdata_accept()){
			if(!ins_res_can.insertDB_upload()){
//						 
				retval= false;
					 }
			
	 		}
	 		 
				 } 
 
	  }
	  return retval;
  }
}


