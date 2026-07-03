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

public class m_accept_pubrequest extends ngmodel{
	utility 		ins_util;
	 
 
	m_user_details ins_m_user;
	 
	String	client_ip;
	String password;
	String userid ;
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	JSONObject json= new JSONObject();
	d_guestdetails ins_guest;
	d_gadrequest ins_req;
	d_vehicle ins_veh;
	d_vehicle1 ins_veh1;
	d_publicguestdetails ins_impguest;
	d_pubrequest ins_impreq;
	d_publicvehicle ins_impveh;
	d_publicvehicle1 ins_impveh1;
	public m_accept_pubrequest(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guest = new d_guestdetails(NGM); 
		ins_req = new d_gadrequest(NGM); 
		ins_veh = new d_vehicle(NGM); 
		ins_veh1 = new d_vehicle1(NGM); 
		ins_impguest = new d_publicguestdetails(NGM); 
		ins_impreq = new d_pubrequest(NGM); 
		ins_impveh = new d_publicvehicle(NGM);
		ins_impveh1 = new d_publicvehicle1(NGM);
	  
		ins_m_user =new m_user_details(NGM);
		ins_val =new validation();
		 	
		
		}
		
	public boolean accept_data(HttpServletRequest request) throws Exception{
		boolean retval = false;
		System.out.println("acceptdata 3");
		String req_id = request.getParameter("req_id");
		System.out.println("acceptdata 4");
		String jsonobj = this.importdata(request,req_id);
		System.out.println("acceptdata 9");
		if(this.setpostdata(jsonobj,req_id)==true){
			System.out.println("acceptdata 16");
			this.changeread(req_id);
			System.out.println("acceptdata 21");
			retval = true;
		}				
		return retval;
	}

public String importdata(HttpServletRequest request,String req_id)throws Exception{
	
	JSONArray jsonobj = new JSONArray() ; 
	JSONObject json = new JSONObject();
	
 	if(ins_impguest.select_acceptdata(req_id)) {
		jsonobj = ins_impguest.array1;
		json.put("guestdetails", jsonobj);
		System.out.println("acceptdata 5");
		System.out.println("asdfasdfasdf"+jsonobj.toString());
	}
 	if(ins_impreq.select_acceptdata(req_id)) {
		jsonobj = ins_impreq.array1;
		json.put("requestdetails", jsonobj);
		System.out.println("acceptdata 6"); 
		System.out.println("asdfasdfasdf"+jsonobj.toString());
	}
 	if(ins_impveh.select_acceptdata(req_id)) {
		jsonobj = ins_impveh.array1;
		json.put("vehicle", jsonobj);
		System.out.println("acceptdata 7"); 
		System.out.println("asdfasdfasdf"+jsonobj.toString());
	}
 	if(ins_impveh1.select_acceptdata(req_id)) {
		jsonobj = ins_impveh1.array1;
		json.put("vehicle1", jsonobj);
		System.out.println("acceptdata 7"); 
		System.out.println("asdfasdfasdf"+jsonobj.toString());
	}
 	System.out.println("acceptdata 8");
 	System.out.println("asdfasdfasdf"+jsonobj.toString());
	return json.toString();
}

 
public boolean changeread(String req_id)throws Exception{
	 System.out.println("acceptdata 17 ");
	boolean retval = false;
	if(ins_impreq.updated_acceptdata(req_id)){
		System.out.println("acceptdata 20 ");
		retval = true;
	}
	return retval;
}
public boolean setpostdata(String jsonstr,String req_id) throws Exception{
	boolean l_status=false;
	System.out.println("acceptdata 10"); 
	System.out.println("sdfsdfsdfsfsdfsdf00000000000000"+jsonstr);
	 Object obj=JSONValue.parse(jsonstr);
	 JSONObject jsonObj1  = (JSONObject)obj;
	 	 
	 JSONArray reqarray = (JSONArray)jsonObj1.get("requestdetails");
	 JSONArray guestarray = (JSONArray)jsonObj1.get("guestdetails");
	 JSONArray veharray = (JSONArray)jsonObj1.get("vehicle");
	 JSONArray veharray1 = (JSONArray)jsonObj1.get("vehicle1");
	 
	 if(this.update_guestdetails(guestarray,req_id)) {
		l_status = true;
		 System.out.println("acceptdata 11 "+l_status);
		 
	 }
     if(this.update_pub_request(reqarray,req_id)){
			l_status = true;
			 System.out.println("acceptdata 12 "+l_status);
     } 
	 if(this.update_veh_details(veharray,req_id)){
	  				l_status = true;
	  				 System.out.println("acceptdata 13 "+l_status);
	 }
	 if(this.update_veh_details1(veharray1,req_id)){
			l_status = true;
			 System.out.println("acceptdata 14 "+l_status);
	 }
	 if(this.changeread(req_id)){
	  					l_status = true;
	  					 System.out.println("acceptdata 14 "+l_status);
	 }
	 System.out.println("acceptdata 15 "+l_status);
     return l_status;
}

 public boolean update_pub_request(JSONArray array,String req_id)throws Exception{
	 
	  boolean retval = true;
	  if(array!=null){
	  for(int i=0;i<array.size();i++) {
			 
	 		 JSONObject Jvalue = (JSONObject)array.get(i); 
	 		ins_req.setpost_mod(Jvalue,req_id);
	 		if(!ins_req.existdata_accept()){
	 			
	 			System.out.println("vehicle data exist");
			if(!ins_req.insertDB_gadreq_upload()){
//						 
				retval= false;
					 }
			
	 		}
	  }
	 		
				 } 
	  
	  return retval;
  }
 public boolean update_veh_details(JSONArray array,String req_id)throws Exception{
	 
	 boolean retval = true;
	 if(array!=null){
	  for(int i=0;i<array.size();i++) {
			 
	 		 JSONObject Jvalue = (JSONObject)array.get(i); 
	 		ins_veh.setpost_mod(Jvalue,req_id);
	 		if(!ins_veh.existdata_accept()){
	 			System.out.println("the values existst..");
			if(!ins_veh.insert_received_data()){
//						 
				retval= false;
				 }
			
	 		}
	  }
	 }
	 
	  return retval;
  }
public boolean update_veh_details1(JSONArray array,String req_id)throws Exception{
	 
	 boolean retval = true;
	 if(array!=null){
	  for(int i=0;i<array.size();i++) {
			 
	 		 JSONObject Jvalue = (JSONObject)array.get(i); 
	 		ins_veh1.setpost_mod(Jvalue,req_id);
	 		if(!ins_veh1.existdata_accept()){
	 			System.out.println("the values existst..");
			if(!ins_veh1.insert_received_data()){
//						 
				retval= false;
				 }
			
	 		}
	  }
	 }
	 
	  return retval;
  }
 public boolean update_guestdetails(JSONArray array,String req_id)throws Exception{
	 boolean retval = true;
	 if(array!=null){
	  for(int i=0;i<array.size();i++){
			 
	 		JSONObject Jvalue = (JSONObject)array.get(i); 
	 		ins_guest.setpost_mod(Jvalue,req_id);
	 		if(!ins_guest.existdata()){
	 			System.out.println("the values existst..");
			    if(!ins_guest.insertDB_guest_upload()){
				     retval= false;
			    }
			}
	  }
	} 
	  
	return retval;
  }
 
 
 public String search(HttpServletRequest request)throws Exception{
	  
		 	String req_id = (String) request.getParameter("req_id");	 
			String userid = (String)request.getParameter("userid");	  
			 
			String criteria ="" ;
					String join = "and";
				 		 
					try{
						if (!userid.equals("")) {
							criteria += join +" upper(name) like upper('%"+userid+"%')";
						}
					}catch (NullPointerException x)	{}
					
					try{
						if (!req_id.equals("")){
							criteria += join +" b.req_id like '"+req_id+"'";
						}
					}catch (NullPointerException x)	{}
					 System.out.println("cccccccccccccccccccccc"+criteria);
					 return criteria;
					
 }

  
}
	



