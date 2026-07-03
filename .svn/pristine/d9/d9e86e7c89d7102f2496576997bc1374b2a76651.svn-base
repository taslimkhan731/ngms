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

public class m_request_upload extends ngmodel{
	utility 		ins_util;
	 
public	d_uploadtable ins_upd;
d_reqeustupload_index ins_upin;
	d_upload_details ins_det;
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
	
	 
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	public JSONObject json= new JSONObject();
	 
  String tr_up_id, tr_uin_id,tablename;

 

	public m_request_upload(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		 
		 
		ins_upin = new d_reqeustupload_index(NGM);
		ins_det = new d_upload_details(NGM);
		 ins_can = new d_cancel_main(NGM);
		 d_gad= new d_gadrequest(NGM);
		 d_guest = new d_guestdetails(NGM);
		  d_veh= new d_vehicle(NGM);
		  mod_req= new d_mod_req(NGM);
		  room_req= new d_mod_req_room(NGM);
		  pro_tbl= new d_process_table(NGM);
		  res_tbl= new d_reservation_maintable(NGM);
		  res_wtl= new d_reservation_waitinglist(NGM);
		ins_val =new validation();
		ins_res_can = new d_cancel_reservation(NGM);
		
		}
		
// 
	public boolean selectdata(HttpServletRequest request)throws Exception{
		boolean retval = false;
		JSONArray jsonobj = new JSONArray() ; 
		JSONObject json1 = new JSONObject();
		String time= request.getParameter("time");
		System.out.println("timeeee"+time);
	 	if(d_gad.selectimportdata(time)){
			jsonobj = d_gad.array1;
			json.put("gad_request", jsonobj);
			System.out.println("sdfsdfsdf"+json1); 
	 	}
			if(d_guest.selectimportdata(time)) {
				jsonobj = d_guest.array1;
				json.put("guest_details", jsonobj);
				System.out.println("sdfsdfsdf"+json1); 
			}
				if(d_veh.selectimportdata(time)) {
					jsonobj = d_veh.array1;
					json.put("veh_req", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
				}
				if(mod_req.selectimportdata(time)) {
					jsonobj = mod_req.array1;
					json.put("mod_req", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
			 	}
				if(room_req.selectimportdata(time)) {
					jsonobj = room_req.array1;
					json.put("mod_req_room", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
			 	}
				if(pro_tbl.selectimportdata(time)) {
					jsonobj = pro_tbl.array1;
					json.put("process_table", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
			 	}
				if(res_tbl.selectimportdata(time)) {
					jsonobj = res_tbl.array1;
					json.put("reservation_maintable", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
			 	}
				if(res_wtl.selectimportdata(time)) {
					jsonobj = res_wtl.array1;
					json.put("reservation_waitinglist", jsonobj);
					  
			 	}
				 
				/*if(ins_can.selectimportdata(time)) {
					jsonobj = ins_can.array1;
					json.put("tr_cancel_main", jsonobj);
					  
			 	}*/
				
				if(ins_res_can.selectimportdata(time)) {
					jsonobj = ins_res_can.array1;
					json.put("cancel_reservation", jsonobj);
					  
			 	}
				System.out.println("jsonobjeee"+json);
				 
		return true;
	}

 
 
	 
	

public boolean update_indextable(HttpServletRequest p_request) throws Exception{
	  boolean l_status =false;
	 	 ins_upin.status="S";
		if(ins_upin.insertindex()){
			l_status = true;
		}
	//ins_upin.insertindex_failed();
	  				 
	 		return l_status; 
				 
			}	
  

public boolean update_indextablefailed(HttpServletRequest p_request) throws Exception{
	  boolean l_status =false;
	 	 ins_upin.status = "F";
		if(ins_upin.insertindex()){
			l_status = true;
		}
	//ins_upin.insertindex_failed();
	  				 
	 		return l_status; 
				 
			}	

}
	
 



