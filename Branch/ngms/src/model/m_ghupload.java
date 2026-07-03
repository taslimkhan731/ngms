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

public class m_ghupload extends ngmodel{
	utility 		ins_util;
	 
	gh_upload_index ins_upin;
 	d_checkin ins_chkin;
	d_checkin_allot ins_ckalot;
	d_allotment ins_alot;
	 d_cancel_main ins_can;
	d_cancel_reservation ins_res_can;
	 
	d_gadrequest d_gad;
	d_guestdetails d_guest;
	d_mod_req mod_req;
	d_mod_req_room room_req;
	d_process_table pro_tbl;
	d_reservation_maintable res_tbl;
	d_reservation_waitinglist res_wtl;
	
	//JSONObject json; 
	JSONArray jarray;
	JSONParser parser=new JSONParser();
	public JSONObject json= new JSONObject();
	 
  String tr_up_id, tr_uin_id,tablename;

 

	public m_ghupload(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		 
		 
		ins_upin = new gh_upload_index(NGM);
		ins_can = new d_cancel_main(NGM);
		ins_res_can = new d_cancel_reservation(NGM);
		ins_chkin= new d_checkin(NGM);
		ins_ckalot = new d_checkin_allot(NGM);
		ins_alot= new d_allotment(NGM);
		
		d_gad = new d_gadrequest(NGM);
		d_guest = new d_guestdetails(NGM);
		mod_req = new d_mod_req(NGM);
		room_req= new d_mod_req_room(NGM);
		pro_tbl = new d_process_table(NGM);
		res_tbl= new d_reservation_maintable(NGM);
		res_wtl= new d_reservation_waitinglist(NGM);
		 
		ins_val =new validation();
	 
		
		}
		
// 
	public boolean selectdata(HttpServletRequest request)throws Exception{
		boolean retval = false;
		JSONArray jsonobj = new JSONArray() ; 
		JSONObject json1 = new JSONObject();
		String time= request.getParameter("time");
		System.out.println("timeeee"+time);
	 	if(ins_chkin.selectimportdata(time)){
			jsonobj = ins_chkin.array1;
			json.put("tr_checkin", jsonobj);
			System.out.println("sdfsdfsdf"+json1); 
	 	}
			if(ins_ckalot.selectimportdata(time)) {
				jsonobj = ins_ckalot.array1;
				json.put("tr_check_alot", jsonobj);
				System.out.println("sdfsdfsdf"+json1); 
			}
				if(ins_alot.selectimportdata(time)) {
					jsonobj = ins_alot.array1;
					json.put("tr_allotment", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
				}
				/*if(ins_can.selectimportdata(time)) {
					jsonobj = ins_can.array1;
					json.put("tr_cancel_main", jsonobj);
					System.out.println("sdfsdfsdf"+json1); 
				}*/
				 
				if(ins_res_can.selectimportdata(time)) {
					jsonobj = ins_res_can.array1;
					json.put("cancel_reservation", jsonobj);
					System.out.println("cancel_reservation  "+json1); 
				}
				
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
				System.out.println("jsonobjeee"+json);
				 
		return true;
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
	
 



