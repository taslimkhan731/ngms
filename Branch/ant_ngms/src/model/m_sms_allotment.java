package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;
import db.*;

public class m_sms_allotment extends ngmodel{
	public String p_dcb_id="";
	public String p_gh_id="";
	  utility 		ins_util;
	  d_sms_alltment ins_smsallot;
	  common ins_common;  
	public m_sms_allotment(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_smsallot = new d_sms_alltment(NGM);
		ins_common = new common(NGM);
		}
		
	public void VJ_listsms(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "listsms", NGM);
		}
	}
	public void VJ_searchSMS(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "searchSMS", NGM);
		}
	}

	public String searchDCB(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
		
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!req_id.equals("")) {
						criteria += join +" req_id = '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!name.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" upper(guest_details) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" date(last_modified) = date('"+date+"')";
					}
				}catch (NullPointerException x)	{} 
				
				 System.out.println("SMS            "+criteria);
				 return criteria;
				
}
	
	public String multiplechech(HttpServletRequest p_request) throws Exception{
		String multiple="";
		String select[] = p_request.getParameterValues("sms_id"); 
		if (select != null && select.length != 0) {
		System.out.println("You have selected: ");
		for (int i = 0; i < select.length; i++) {
		System.out.println(select[i]); 
			      	
        	multiple=multiple+","+select[i];
        	System.out.println("DELTEEEEEEEEEEEEEEEEEEEEEEEEEE       "+multiple);
        	
            System.out.println("Split1 Element "+i+" :"+select[i]);
            
		}
		}	
		return multiple;
	}
	
	public boolean submitSMS(HttpServletRequest p_request) throws Exception{
		String msg_head="";
		String select[] = p_request.getParameterValues("sms_id"); 
		if (select != null && select.length != 0) {
			System.out.println("You have selected: ");
			for (int i = 0; i < select.length; i++) {
					System.out.println("sms id..............send.................... "+select[i]); 
					ins_smsallot.a_sms_id = select[i];
					ins_smsallot.status = "P";
					if(!ins_smsallot.selectDB_id()) return false;
					else{
						
						if(ins_smsallot.equals("ALT")){
							msg_head = "NGMS : Allotment";
						}else if(ins_smsallot.equals("VALTV")){
							msg_head = "NGMS : Protocol/Driver Details";
						}else if(ins_smsallot.equals("VALTV")){
							msg_head = "NGMS : Guest Details";
						}else if(ins_smsallot.equals("CVALTV")){
							msg_head = "NGMS : Protocol/Driver Details Cancelled";
						}else if(ins_smsallot.equals("CVALTG")){
							msg_head = "NGMS : Guest Details Cancelled";
						}else{
							msg_head = "NGMS : ";
						}

						String templateId="1107160707714316860";
						NGM.ins_utility.SENDSMS(ins_smsallot.mobile_no, msg_head+ins_smsallot.subject, "NGM",templateId);//this.ins_user.userid
						
						ins_smsallot.a_sms_id = ins_smsallot.a_sms_id;
						ins_smsallot.status = "S";
						if(!ins_smsallot.updateDB_status()) return false;
					}
			}
		}	
		return true;
	}
	
	public boolean cancelSMS(HttpServletRequest p_request) throws Exception{
		String select[] = p_request.getParameterValues("sms_id"); 
		if (select != null && select.length != 0) {
			System.out.println("You have selected: ");
			for (int i = 0; i < select.length; i++) {
					System.out.println("sms id................cancel.................. "+select[i]); 
			      	
					ins_smsallot.a_sms_id = select[i];
					ins_smsallot.status = "D";
					if(!ins_smsallot.updateDB_status()) return false;
					
					
					
			}
		}	
		return true;
	}

}






