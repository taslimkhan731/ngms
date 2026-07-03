package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.*;
import db.*;

public class m_dues extends ngmodel{
	
	  utility 		ins_util;
	  d_gadrequest ins_gadrequest;
	  ArrayList <d_guestdetails> ins_guest = new ArrayList<d_guestdetails>();
	  d_guestdetails ins_guestdetails;
	  d_vehicle    ins_veh;
	  d_mod_req ins_mod;
	  d_reservation_maintable ins_res;
	  d_reservation_waitinglist ins_wait;
	  d_cancel_reservation ins_cancel_res;
	  common ins_common;
	public m_dues(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_gadrequest	  = new d_gadrequest(NGM);
		ins_guestdetails  =  new d_guestdetails(NGM);
		ins_veh     = new d_vehicle(NGM);
		ins_mod		= new d_mod_req(NGM);
		ins_res = new d_reservation_maintable(NGM);
		ins_wait = new d_reservation_waitinglist(NGM);
		ins_cancel_res = new d_cancel_reservation(NGM);
		ins_common = new common(NGM);
		}
	
	public void VJ_createlistdata(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "createlistdata", NGM);
		}
	}
	
	public void VJ_createlistcancel(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "createlistcancel", NGM);
		}
	}
	
	public void VJ_createlistconformation(String ppage_no_s) throws Exception
	{
		if(!NGM.ins_dbval.val_pageno(ppage_no_s))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "createlistconformation", NGM);
		}
	}
	
	public void VJ_search(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "search", NGM);
		}
	}
	
	public void VJ_showformview(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "showformview", NGM);
		}
	}
	public void VJ_showformedit(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "showformedit", NGM);
		}
	}
	public void VJ_deletelist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			System.out.println("ppage_no_s "+ppage_no_s);
			System.out.println("pgh_id "+pgh_id);
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "deletelist", NGM);
		}
	}
	public void VJ_searchcancel(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "searchcancel", NGM);
		}
	}
	public void VJ_searchconformlist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "searchconformlist", NGM);
		}
	}
	public void VJ_conformationshow(String ppro_id) throws Exception
	{
		if(!NGM.ins_dbval.val_pro_id(ppro_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "conformationshow", NGM);
		}
	}
	
	public boolean setPostgadrequest(HttpServletRequest p_request) throws Exception{ 
		String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	    ins_gadrequest.req_id ="G"+req_id1; //G_ 
	    ins_gadrequest.gh_id    						= ins_util.setNullParameter(p_request, "gh_id");
	    
	    ins_gadrequest.from_date    					= ins_util.setNullParameter(p_request, "from");
	    System.out.println("from date         222222222        "+ins_gadrequest.from_date);
	    ins_gadrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
	    ins_gadrequest.mobile_no   = ins_util.setNullParameter(p_request, "mobile");
	    ins_gadrequest.email_id   = ins_util.setNullParameter(p_request, "email");

		ins_gadrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_gadrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		System.out.println("ins_gadrequest.address1  "+ins_gadrequest.address1  );
		ins_gadrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.family= ins_util.setNullParameter(p_request, "familyval");
	
			ins_gadrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
			ins_gadrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
			ins_gadrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		
			ins_gadrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
			ins_gadrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
		
		
		
		
		if(!ins_gadrequest.insertDB_gadreq())return false;
		return true;
	}
	
	public String searchDues(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
		String receipt_no=(String)request.getParameter("receipt_no");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_common.display_time_zonenew(date);
		} 
		
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" f.req_id = '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" date('"+date+"') = date(date_of_due) ";
					}
				}catch (NullPointerException x)	{} 
				
				try{
					if (!receipt_no.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" rec_id = '"+receipt_no+"' ";
					}
				}catch (NullPointerException x)	{} 
				
				 System.out.println("COU GUEST  DUES...................              "+criteria);
				 return criteria;
				
}
	
	public String searchDuesBill(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
		String bill_no=(String)request.getParameter("bill_no");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_common.display_time_zonenew(date);
		} 
		
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" f.req_id = '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" date('"+date+"') = date(date) ";
					}
				}catch (NullPointerException x)	{} 
				
				try{
					if (!bill_no.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" bill_id = '"+bill_no+"' ";
					}
				}catch (NullPointerException x)	{} 
				
				 System.out.println("COU GUEST  DUES.........BILL..........              "+criteria);
				 return criteria;
				
}
	
}






