package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.utility;
import db.*;

public class m_current_booking extends ngmodel{
	public String sel_block = "";
	d_mod_req_room ins_mod;
	d_mod_req ins_req;
	  utility 		ins_util;
	  d_gadrequest ins_gadrequest;
	  ArrayList <d_guestdetails> ins_guest = new ArrayList<d_guestdetails>();
	  d_guestdetails ins_guestdetails;
	  d_vehicle    ins_veh;
	  d_process_table ins_pro;
	  d_reservation_maintable ins_res;
		public String p_quota_type="";

	public m_current_booking(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_gadrequest	  = new d_gadrequest(NGM);
		ins_guestdetails  =  new d_guestdetails(NGM);
		ins_veh     = new d_vehicle(NGM);
		ins_req		= new d_mod_req(NGM);
		ins_mod		= new d_mod_req_room(NGM);
		ins_req = new d_mod_req(NGM);
		ins_pro = new d_process_table(NGM);
		ins_res = new d_reservation_maintable(NGM);
		}
		
	public void VJ_createlistdatacurrent(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "createlistdatacurrent", NGM);
		}
	}
	public void VJ_showformedit(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "showformedit", NGM);
		}
	}
	public void VJ_showformview(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "showformview", NGM);
		}
	}
	public void VJ_roomstatusedit(String preq_id) throws Exception
	{
		if(!NGM.ins_dbval.val_reqid(preq_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "roomstatusedit", NGM);
		}
	}
	public void VJ_showformeditmod(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "showformeditmod", NGM);
		}
	}
	public void VJ_createlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "createlistdatacurrent", NGM);
		}
	}
	public void VJ_createlisteditmod(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "createlisteditmod", NGM);
		}
	}
	public void VJ_showformmod(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "showformmod", NGM);
		}
	}
	public void VJ_deletelist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "createlistdatacurrent", NGM);
		}
	}
	public void VJ_deletelistRequest(String pgh_id,String ppage_no) throws Exception
	{
		System.out.println("pgh_id"+pgh_id);
		System.out.println("ppage_no"+ppage_no);
		if(!(NGM.ins_dbval.val_ghid(pgh_id)))//&&NGM.ins_dbval.val_pageno(ppage_no)
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_current_booking", "Validation fails", "createlistdatacurrent", NGM);
		}
	}
	
	
	public boolean doProcess(HttpServletRequest p_request) throws Exception{
		
		String gh_id = p_request.getParameter("gh_id");
		String  sel_block = p_request.getParameter("sel_block");
		
		ins_pro.process_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "process_id").toString();
		    NGM.ins_utility.updateseqID(NGM.tr_conn, "process_id", Integer.parseInt(ins_pro.process_id));
		    ins_pro.process_id = "CUR_"+ins_pro.process_id;
		    ins_pro.created_by = NGM.ins_sess.user_id;
		    ins_pro.status = "CNF";
		    ins_pro.officeorderno = "CURRENTBOOKING";
		    ins_pro.gh_id = gh_id;
		    ins_pro.block_id = sel_block;
		    ins_pro.gosignature   = "";
		    ins_pro.gosignaturedesig   = "";
		    if(!ins_pro.insertDB()) return false;
		    PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from ((select * from mod_req where req_id like 'H%' and status='MOD') a join (select mrr_id,mod_id,req_id as rid,block_id,number,type,gh_id from mod_req_room where gh_id=?) b on a.req_id=b.rid) c join (select req_id,from_date,to_date,req_date from gad_request where gh_id=?) d on c.req_id=d.req_id"); //H_
		    ps.setString(1, gh_id);
		    ps.setString(2, gh_id);
		    System.out.println("ps  CURRENT "+ps);
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()){
		    		ins_res.res_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_id").toString();
		 		    NGM.ins_utility.updateseqID(NGM.tr_conn, "res_id", Integer.parseInt(ins_res.res_id));
		 		   ins_res.res_id = "h_"+ins_res.res_id;
		 		    ins_res.from_date = rs.getString("from_date");
		 		    ins_res.to_date = rs.getString("to_date");;
		 		    ins_res.checkin_date = rs.getString("from_date");
		 		    ins_res.checkout_date = rs.getString("to_date");
		 		    ins_res.type = rs.getString("type");
		 		    ins_res.status = "CNF";
		 		    ins_res.block_id  = rs.getString("block_id");
		 		    ins_res.req_id = rs.getString("req_id");
		 		    ins_res.processid = ins_pro.process_id;
		 		    ins_res.gh_id = gh_id;
		 		    ins_res.total_number = 1;
		 		    ins_res.quota_type = rs.getString("quota_type");
		 		    if(!ins_res.insertDB()) return false;
		 	    	ins_req.req_id =  ins_res.req_id;
		 		    ins_req.status = "PRO";
		 		    ins_req.process_id = ins_pro.process_id;
		 		    if(!ins_req.updateDBstatus())  return false;
		 		    
		 		    ins_gadrequest.req_id = ins_res.req_id;
					ins_gadrequest.status = "PRO";
					ins_gadrequest.gh_id =gh_id;
					if(!ins_gadrequest.update_status()) return false;
		 	    }
		    //close
		    rs.close();
			ps.close();
		    return true;
	}
	
	public boolean setPostgadrequest(HttpServletRequest p_request) throws Exception{ 
		String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	    ins_gadrequest.req_id ="H"+req_id1; //H_
	    ins_gadrequest.gh_id    						= ins_util.setNullParameter(p_request, "gh_id");
	    ins_gadrequest.block_id    						= ins_util.setNullParameter(p_request, "sel_block");
	    ins_gadrequest.from_date    					= ins_util.setNullParameter(p_request, "from");
	    System.out.println("from date         222222222        "+ins_gadrequest.from_date);
	    ins_gadrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
	    ins_gadrequest.mobile_no   = ins_util.setNullParameter(p_request, "mobile");
	    ins_gadrequest.email_id   = ins_util.setNullParameter(p_request, "email");

		ins_gadrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_gadrequest.is_veh_req1    				= ins_util.setNullParameter(p_request, "checkedval1");
		ins_gadrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		System.out.println("ins_gadrequest.address1  "+ins_gadrequest.address1  );
		ins_gadrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		if(ins_gadrequest.ext.equals("1")){
			ins_gadrequest.existing_req_id    				= ins_util.setNullParameter(p_request, "exist_req_id");
		}else{
			ins_gadrequest.existing_req_id 					= null;
		}
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.category						= ins_util.setNullParameter(p_request, "category");
		ins_gadrequest.desg=ins_util.setNullParameter(p_request, "desg");
		ins_gadrequest.duringstay						= ins_util.setNullParameter(p_request, "duringstay");
		ins_gadrequest.family= ins_util.setNullParameter(p_request, "familyval");
	
			ins_gadrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
			ins_gadrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
			ins_gadrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		
			ins_gadrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
			ins_gadrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
		
		if(!ins_gadrequest.insertDB_gh_req())return false;
		return true;
	}
	public boolean setPostguestdetails(HttpServletRequest p_request) throws Exception{ 
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "rowCnt"));
		System.out.println("count    "+count);
		
		for(int i=0;i<count;i++)
		{
				String gender="gender";
				System.out.println("iiiiii       "+i);
				String name="name";
				String age="age";
				String desg="desg";
				String dept="dept";
				String address="address";
		
			//ins_dataentry.guest_id    					= ins_util.setNullParameter(p_request, "name");
			
			//System.out.println("djfhdjshf gender    "+ins_dataentry.gender);
				ins_guestdetails.req_id = ins_gadrequest.req_id;
				ins_guestdetails.name    				= ins_util.setNullParameter(p_request, name+i);
				ins_guestdetails.age    				= ins_util.setNullParameter(p_request, age+i);
				ins_guestdetails.gender    				= ins_util.setNullParameter(p_request, gender+i);
				ins_guestdetails.designation    				= ins_util.setNullParameter(p_request, desg+i);
				ins_guestdetails.department   					= ins_util.setNullParameter(p_request, dept+i);
				ins_guestdetails.address2    					= ins_util.setNullParameter(p_request, address+i);
				
				int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
			     ins_guestdetails.guest_id = String.valueOf(guest_id1);
			     ins_guestdetails.guest_id = "h_"+ins_guestdetails.guest_id;
				ins_guest.add(ins_guestdetails);
				if(!ins_guestdetails.insertDB_guestGH())return false;
		}
		return true;
	}
	public void setPostvehicle(HttpServletRequest p_request) throws Exception{ 
		ins_veh.req_id = ins_gadrequest.req_id;
		ins_veh.mode_of_travel=ins_util.setNullParameter(p_request, "mode");
		ins_veh.arrival_time=ins_util.setNullParameter(p_request, "arrival");
		ins_veh.arrival_place=ins_util.setNullParameter(p_request, "place");
		ins_veh.no_seats_req=ins_util.setNullParameter(p_request, "seat");
		ins_veh.ftno=ins_util.setNullParameter(p_request, "ftno");
	}
	public void setPostvehicle2(HttpServletRequest p_request) throws Exception{ 
		ins_veh.req_id = ins_gadrequest.req_id;
		ins_veh.mode_of_travel1=ins_util.setNullParameter(p_request, "mode1");
		ins_veh.arrival_time1=ins_util.setNullParameter(p_request, "arrival1");
		ins_veh.arrival_place1=ins_util.setNullParameter(p_request, "place1");
		ins_veh.no_seats_req1=ins_util.setNullParameter(p_request, "seat1");
		ins_veh.ftno1=ins_util.setNullParameter(p_request, "ftno1");
	}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		setPostgadrequest(p_request);
		setPostguestdetails(p_request);
		setPostvehicle(p_request);
		setPostvehicle2(p_request);
		
		if(ins_gadrequest.is_veh_req.equals("1")){
			
			int veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
		    NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
		    ins_veh.veh_id = String.valueOf(veh_id1);
		    ins_veh.veh_id = "h_" + ins_veh.veh_id;
		    
				if(!ins_veh.insertDB_vehGH())return false;
		}
		if(ins_gadrequest.is_veh_req1.equals("1")){
			
			int veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
		    NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
		    ins_veh.veh_id = String.valueOf(veh_id1);
		    ins_veh.veh_id = "h_" + ins_veh.veh_id;
		    
				if(!ins_veh.insertDB_vehGH1())return false;
		}
		return true;
	}
	public boolean setPostgadrequest1(HttpServletRequest p_request) throws Exception{ 
		//String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
	   // NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	   // ins_gadrequest.req_id ="G"+req_id1; //G_
		ins_gadrequest.req_id=ins_util.setNullParameter(p_request, "req_id");
	    ins_gadrequest.gh_id    						= ins_util.setNullParameter(p_request, "sel_id");
	    ins_gadrequest.block_id    						= ins_util.setNullParameter(p_request, "sel_block");
	    ins_gadrequest.from_date    					= ins_util.setNullParameter(p_request, "from");
	    System.out.println("from date         222222222        "+ins_gadrequest.from_date);
	    ins_gadrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
	    ins_gadrequest.mobile_no   = ins_util.setNullParameter(p_request, "mobile");
	    ins_gadrequest.email_id   = ins_util.setNullParameter(p_request, "email");
		ins_gadrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_gadrequest.is_veh_req1    				= ins_util.setNullParameter(p_request, "checkedval1");
		ins_gadrequest.duringstay                   = ins_util.setNullParameter(p_request, "duringstay");
		ins_gadrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		System.out.println("ins_gadrequest.address1  "+ins_gadrequest.address1  );
		ins_gadrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.category						= ins_util.setNullParameter(p_request, "category");
		ins_gadrequest.desg					=  ins_util.setNullParameter(p_request, "desg");
		ins_gadrequest.family= ins_util.setNullParameter(p_request, "familyval");
		
			ins_gadrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
			ins_gadrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
			ins_gadrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
			ins_gadrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
			ins_gadrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
			if(!ins_gadrequest.updateDB_gadreq())return false;
		return true;
	}
	public boolean setPostguestdetails1(HttpServletRequest p_request) throws Exception{ 
		System.out.println("INSIDE THE GUEST DETAILS in model");
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "rowCnt"));
		System.out.println("count    "+count);
		ins_guestdetails.req_id			    = ins_gadrequest.req_id;
		if(!ins_guestdetails.deleteguest()) return false;
		for(int i=0;i<count;i++)
		{
			//String guest = "guest";
				String gender="gender";
				System.out.println("iiiiii       "+i);
				String name="name";
				String age="age";
				String desg="desg";
				String dept="dept";
				String address="address";
		
			
				ins_guestdetails.req_id				    = ins_gadrequest.req_id;
				//ins_guestdetails.guest_id				= ins_util.setNullParameter(p_request, guest+i);
				ins_guestdetails.name    				= ins_util.setNullParameter(p_request, name+i);
				System.out.println("dsffsdfsdfds"+ins_guestdetails.name);
				ins_guestdetails.age    				= ins_util.setNullParameter(p_request, age+i);
				ins_guestdetails.gender    				= ins_util.setNullParameter(p_request, gender+i);
				ins_guestdetails.designation    		= ins_util.setNullParameter(p_request, desg+i);
				ins_guestdetails.department   			= ins_util.setNullParameter(p_request, dept+i);
				ins_guestdetails.address2    			= ins_util.setNullParameter(p_request, address+i);
		
				int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
			     ins_guestdetails.guest_id = String.valueOf(guest_id1);
			     ins_guestdetails.guest_id = "h_"+ins_guestdetails.guest_id;
				ins_guest.add(ins_guestdetails);
				
				ins_guest.add(ins_guestdetails);
				System.out.println("hfsdhjfhsdjhfjsd");
				if(!ins_guestdetails.insertDB_guestGH())return false;
		}
		return true;
	}
	public void setPostvehicle1(HttpServletRequest p_request) throws Exception{ 
		ins_veh.req_id = ins_gadrequest.req_id;
		ins_veh.mode_of_travel=ins_util.setNullParameter(p_request, "mode");
		ins_veh.arrival_time=ins_util.setNullParameter(p_request, "arrival");
		ins_veh.arrival_place=ins_util.setNullParameter(p_request, "place");
		ins_veh.no_seats_req=ins_util.setNullParameter(p_request, "seat");
		ins_veh.veh_id=ins_util.setNullParameter(p_request, "vehid");
		ins_veh.ftno=ins_util.setNullParameter(p_request, "ftno");
	}
	
	public void setPostvehicle3(HttpServletRequest p_request) throws Exception{ 
		ins_veh.req_id = ins_gadrequest.req_id;
		ins_veh.mode_of_travel1=ins_util.setNullParameter(p_request, "mode1");
		ins_veh.arrival_time1=ins_util.setNullParameter(p_request, "arrival1");
		ins_veh.arrival_place1=ins_util.setNullParameter(p_request, "place1");
		ins_veh.no_seats_req1=ins_util.setNullParameter(p_request, "seat1");
		ins_veh.veh_id1=ins_util.setNullParameter(p_request, "vehid1");
		ins_veh.ftno1=ins_util.setNullParameter(p_request, "ftno1");
	}
	public boolean UpdateCreation(HttpServletRequest p_request) throws Exception{
		setPostgadrequest1(p_request);
		setPostguestdetails1(p_request);
		setPostvehicle1(p_request);
		setPostvehicle3(p_request);
		
			if(ins_gadrequest.is_veh_req.equals("1")){
				if(ins_veh.selectDB()){
				 ins_veh.deleteveh();}
				
				int veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
			    NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
			    ins_veh.veh_id = String.valueOf(veh_id1);
			    ins_veh.veh_id = "h_" + ins_veh.veh_id;
			    
				if(!ins_veh.insertDB_vehGH())return false;
			}
			 if(ins_gadrequest.is_veh_req.equals("0")){
				 if(ins_veh.selectDB()){
				if(!ins_veh.deleteveh())return false;}
			}
			 
			 if(ins_gadrequest.is_veh_req1.equals("1")){
					if(ins_veh.selectDB1()){
					 ins_veh.deleteveh1();}
					
					int veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
				    NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
				    ins_veh.veh_id = String.valueOf(veh_id1);
				    ins_veh.veh_id = "h_" + ins_veh.veh_id;
				    
					if(!ins_veh.insertDB_vehGH1())return false;
				}
				 if(ins_gadrequest.is_veh_req1.equals("0")){
					 if(ins_veh.selectDB1()){
					if(!ins_veh.deleteveh1())return false;}
				}
			return true;
		}
	public boolean deletelist(HttpServletRequest p_request) throws Exception{
		
	 ins_req.reqst =   p_request.getParameter("reqid");
	 ins_mod.reqst =   p_request.getParameter("reqid");
	 ins_gadrequest.req_id= p_request.getParameter("reqid");
	// ins_guestdetails.req_id= p_request.getParameter("reqid");
	// ins_vehicle.req_id= p_request.getParameter("reqid");
	 if(ins_gadrequest.selectDB_gad()){
		 if(!ins_gadrequest.cancelcancel())return false;
	 }
     if(!ins_req.deletelist())return false;
	 if(!ins_mod.deletemrr())return false;
			//if(!ins_gadrequest.deletegad())return false;
			//if(!ins_guestdetails.deleteguest())return false;
			//if(!ins_vehicle.deleteveh())return false;

		
return true;

	}
	
	public boolean deletelistRequest(HttpServletRequest p_request) throws Exception{/*
		boolean retval = false;
		 ins_gadrequest.req_id= p_request.getParameter("reqid");
		 ins_guestdetails.req_id= p_request.getParameter("reqid");
		 ins_veh.req_id= p_request.getParameter("reqid");
	        	if(!ins_gadrequest.deletegad()){ retval= true;}
				if(!ins_guestdetails.deleteguest()){retval= true;} 
				if(!ins_veh.deleteveh()){retval= true;}
	
			
	return retval;
	*/

		boolean retval = false;
		 ins_gadrequest.req_id= p_request.getParameter("reqid");
		 ins_guestdetails.req_id= p_request.getParameter("reqid");
		 String t_gh_id = p_request.getParameter("gh_id");
		 String t_sel_block = p_request.getParameter("sel_block");
		 ins_veh.req_id= p_request.getParameter("reqid");
		 if(ins_gadrequest.selectDB_gad()){
	        	if(!ins_gadrequest.deletegad()){ retval= true;}}
		 if(ins_guestdetails.selectDB_reqGuest(t_gh_id)){
				if(!ins_guestdetails.deleteguest()){retval= true;} }
		 if(ins_veh.selectDB()){
				if(!ins_veh.deleteveh()){retval= true;}}
		 if(ins_veh.selectD1()){
				if(!ins_veh.deleteveh2()){retval= true;}}
	
			
	return retval;
		
	}
	
	
	public boolean cancel(HttpServletRequest p_request) throws Exception{
		ins_gadrequest.req_id =   p_request.getParameter("reqid");
		if(!ins_gadrequest.cancelgad())return false;
	
			
	return true;
	}
	public boolean saveCreation_mod(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "count2"));
		String t_gh_id= ins_util.setNullParameter(p_request, "gh_id");
		int modgh = NGM.ins_utility.get_seqID(NGM.tr_conn, "mod_id"); 
	     NGM.ins_utility.updateseqID(NGM.tr_conn, "mod_id", modgh);
	     ins_req.mod_id = "h_"+modgh;
	     
		ins_req.vc_id   = ins_util.setNullParameter(p_request, "vc_id");
		ins_req.quota_type   = ins_util.setNullParameter(p_request, "quota");
		ins_req.is_veh_req   = ins_util.setNullParameter(p_request, "is_veh_req");
		ins_req.is_veh_req1   = ins_util.setNullParameter(p_request, "is_veh_req1");
		ins_req.req_id 	=	ins_util.setNullParameter(p_request, "req_id");
		ins_req.gh_id = ins_util.setNullParameter(p_request, "gh_id");
		ins_req.sel_block = ins_util.setNullParameter(p_request, "sel_block");
		if(!ins_req.insertDB_modGH())return false;
		ins_gadrequest.status="MOD";
		ins_gadrequest.req_id=ins_req.req_id;
		ins_gadrequest.gh_id=t_gh_id;
		if(!ins_gadrequest.update_status())return false;
		ins_mod.gh_id = ins_util.setNullParameter(p_request, "ghid");
		ins_mod.req_id = ins_req.req_id;
		System.out.println(" eeee gh_id************   "+ins_mod.gh_id);
		for(int i=1;i<=count;i++)
		{
				String sel_id="sel_id"+i;
				String b="b"+i;
				String c="c"+i;
				String d="d"+i;
				
				String f="f"+i;
				String g="g"+i;
				
			ins_mod.block_id			= ins_util.setNullParameter(p_request, sel_id);
			ins_mod.mod_id = ins_req.mod_id;
			System.out.println("-----xxxxx------ins_mod.block_id---------------------- "+ins_mod.block_id);

			String rooms    		= ins_util.setNullParameter(p_request, b);
			String bg		= ins_util.setNullParameter(p_request, c);
			String bl    = ins_util.setNullParameter(p_request, d);

			String dg    = ins_util.setNullParameter(p_request, f);
			String dl    = ins_util.setNullParameter(p_request, g);
			if(rooms.equals("-")){
				rooms = "0";
			}
			if(bg.equals("-")){
				bg = "0";
			}
			if(bl.equals("-")){
				bl = "0";
			}
			
			if(dg.equals("-")){
				dg = "0";
			}
			if(dl.equals("-")){
				dl = "0";
			}
			for(int ii=0;ii<Integer.parseInt(rooms);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
			     ins_mod.mrr_id = "h_"+ins_mod.mrr_id;
				ins_mod.type = "R";
				if(!ins_mod.insertDB_GH())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
			     ins_mod.mrr_id = "h_"+ins_mod.mrr_id;
				ins_mod.type = "BG";
				if(!ins_mod.insertDB_GH())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
			     ins_mod.mrr_id = "h_"+ins_mod.mrr_id;
				ins_mod.type = "BL";
				if(!ins_mod.insertDB_GH())return false;
			}
			
			for(int ii=0;ii<Integer.parseInt(dg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
			     ins_mod.mrr_id = "h_"+ins_mod.mrr_id;
				ins_mod.type = "DG";
				if(!ins_mod.insertDB_GH())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
			     ins_mod.mrr_id = "h_"+ins_mod.mrr_id;
				ins_mod.type = "DL";
				if(!ins_mod.insertDB_GH())return false;
			}
			
		}
		
		
	return true;
	}
	public boolean cancel_mod(HttpServletRequest p_request) throws Exception{
		 ins_req.reqst =   p_request.getParameter("reqid");
		 
		 System.out.println("cancelledddddddddddddddddd"+ ins_req.req_id);
		if(!ins_req.cancel_mod())return false;
	
			
	return true;
	}
	public boolean deletelist_mod(HttpServletRequest p_request) throws Exception{
		 ins_req.reqst =   p_request.getParameter("reqid");
		 ins_mod.reqst =   p_request.getParameter("reqid");
		 ins_gadrequest.req_id= p_request.getParameter("reqid");
		// ins_guestdetails.req_id= p_request.getParameter("reqid");
		// ins_vehicle.req_id= p_request.getParameter("reqid");
		 if(ins_gadrequest.selectDB_gad()){
			 if(!ins_gadrequest.cancelcancel())return false;
		 }
	     if(!ins_req.deletelist())return false;
		 if(!ins_mod.deletemrr())return false;
				//if(!ins_gadrequest.deletegad())return false;
				//if(!ins_guestdetails.deleteguest())return false;
				//if(!ins_vehicle.deleteveh())return false;
	
			
	return true;
	}
	public boolean setPostUpdate(HttpServletRequest p_request) throws Exception
	{
		

		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "count2"));
	//	ins_req.mod_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mod_id").toString(); 
	//    NGM.ins_utility.updateseqID(NGM.tr_conn, "mod_id", Integer.parseInt(ins_req.mod_id));
		ins_mod.gh_id=ins_util.setNullParameter(p_request, "ghid");
		ins_mod.mod=ins_util.setNullParameter(p_request, "mod_id");
		ins_mod.mod_id=ins_util.setNullParameter(p_request, "mod_id");
		System.out.println("MODDDDDDDDDDDDD"+ ins_mod.mod_id);
		ins_mod.req_id=ins_util.setNullParameter(p_request, "req_id");
		System.out.println("REQUESTTTTTTTTTT"+ ins_mod.req_id);
		if(!ins_mod.deletemod()) return false;
		System.out.println(" ins_mod.req_id................ "+ins_mod.req_id);
		ins_req.mod_id = ins_mod.mod_id;
		ins_req.req_id = ins_mod.req_id;
		ins_req.vc_id   = ins_util.setNullParameter(p_request, "sel_vcid");
		System.out.println(" ins_req.vc_id................ "+ins_req.vc_id);

		ins_req.quota_type   = ins_util.setNullParameter(p_request, "sel_q");
		System.out.println(" ins_req.quota_type............... "+ins_req.quota_type);

		if(!ins_req.update_vc_q())return false;
		
		/*PreparedStatement pss = NGM.conn.prepareStatement("select count(*) from room_block where gh_id=? and block_id=?");
		pss.setString(1, ins_mod.gh_id);
		pss.setString(2, ins_mod.block_id);
		ResultSet rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		rss.close();
		pss.close();*/
		
		for(int i=1;i<=count;i++)
		{
				String sel_id="sel_id"+i;
				String b="b"+i;
				String c="c"+i;
				String d="d"+i;
				
				String f="f"+i;
				String g="g"+i;
				
			ins_mod.block_id			= ins_util.setNullParameter(p_request, sel_id);
			ins_mod.mod_id = ins_mod.mod_id;
			System.out.println("-----------ins_req.mod_id---------------------- "+ins_req.mod_id);
			System.out.println("-----xxxxx------ins_mod.block_id---------------------- "+ins_mod.block_id);

			String rooms    		= ins_util.setNullParameter(p_request, b);
			String bg		= ins_util.setNullParameter(p_request, c);
			String bl    = ins_util.setNullParameter(p_request, d);

			String dg    = ins_util.setNullParameter(p_request, f);
			String dl    = ins_util.setNullParameter(p_request, g);
			if(rooms.equals("-")){
				rooms = "0";
			}
			if(bg.equals("-")){
				bg = "0";
			}
			if(bl.equals("-")){
				bl = "0";
			}
			
			if(dg.equals("-")){
				dg = "0";
			}
			if(dl.equals("-")){
				dl = "0";
			}
			for(int ii=0;ii<Integer.parseInt(rooms);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "R";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BL";
				if(!ins_mod.insertDB())return false;
			}
			
			for(int ii=0;ii<Integer.parseInt(dg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DL";
				if(!ins_mod.insertDB())return false;
			}
			
		}
		
		/*for(int i=1;i<=total_row;i++)
		{
				String sel_id="sel_id"+i;
				String b="b"+i;
				String c="c"+i;
				String d="d"+i;
				
				String f="f"+i;

				String g="g"+i;
				
			ins_mod.block_id	= ins_util.setNullParameter(p_request, sel_id);
			System.out.println("sel_id ===> "+sel_id);
			ins_mod.nor_req    		= ins_util.setNullParameter(p_request, b);
			ins_mod.nob_g_req		= ins_util.setNullParameter(p_request, c);
			ins_mod.nob_l_req    = ins_util.setNullParameter(p_request, d);
			String rooms    		= ins_util.setNullParameter(p_request, b);
			
			String bg	 = ins_util.setNullParameter(p_request, c);
			System.out.println("bg ===> "+bg);
			String bl    = ins_util.setNullParameter(p_request, d);
			System.out.println("bl ===> "+bl);
			String dg    = ins_util.setNullParameter(p_request, f);
			System.out.println("dg ===> "+dg);
			String dl    = ins_util.setNullParameter(p_request, g);
			System.out.println("dl ===> "+dl);
			for(int ii=0;ii<Integer.parseInt(rooms);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "R";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BL";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DL";
				if(!ins_mod.insertDB())return false;
			}
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii "+i);
		}*/
		
		ins_gadrequest.req_id 	=	ins_util.setNullParameter(p_request, "req_id");
		ins_gadrequest.gh_id = ins_util.setNullParameter(p_request, "gh_id");
		ins_gadrequest.is_veh_req   = ins_util.setNullParameter(p_request, "is_veh_req");
		ins_gadrequest.is_veh_req1   = ins_util.setNullParameter(p_request, "is_veh_req1");
		System.out.println("Convijhgsdjkcvs"+ ins_gadrequest.is_veh_req + "sgfiulsdgflsconveyance2"+ ins_gadrequest.is_veh_req1);
		if(!ins_gadrequest.update_conveyance())return false;
		if(!ins_gadrequest.update_conveyance1())return false;
		
		System.out.println("TRUE G");
		return true;
	}
	
	//roomstatus
	public ArrayList<String> roomstatus(HttpServletRequest p_request) throws Exception{
		ArrayList<String> statusrooms= new ArrayList<String>();
		String status = "";
		int n = 3;
		for(int ii=1;ii<=n;ii++){
			try {
				String i =""+ ii;
				d_quota_block ins_qb = new d_quota_block(NGM);
				ins_qb.block_id = sel_block;
				ins_qb.gh_id = ins_util.setNullParameter(p_request, "ghid");
				ins_qb.quota_type = ins_util.setNullParameter(p_request, "sel_q");
				System.out.println("sel_q ins_qb.quota_type  "+ins_qb.quota_type);
				p_quota_type = ins_qb.quota_type;
				int total = ins_qb.selectDBrooms();
				int maxbeds = ins_qb.selectDBbeds();
				int dor_gents = ins_qb.selectDBdorGents();
				int dor_ladies = ins_qb.selectDBdorLadies();
				
				int total_rep = 0;
				if(ins_qb.quota_type.equals(NGM.ins_sess.def_quota)){
					d_repair_block ins_rb = new d_repair_block(NGM);
					ins_rb.block_id = i;
					ins_rb.gh_id = ins_util.setNullParameter(p_request, "ghid");
					total_rep = ins_rb.selectDBrooms();
				}
	
				int total_r = total - total_rep;
				
				d_reservation_maintable ins_res = new d_reservation_maintable(NGM);
				ins_res.gh_id = ins_util.setNullParameter(p_request, "ghid");
				ins_res.block_id = i;
				ins_res.quota_type = ins_util.setNullParameter(p_request, "sel_q");
				ins_res.status = "CNF";
				ins_res.checkin_date = ins_util.setNullParameter(p_request, "from_date");
				ins_res.checkout_date = ins_util.setNullParameter(p_request, "to_date");
				
				String occ_rooms = null;
				String occ_GB = null;
				String occ_LB = null;
				String occ_DG = null;
				String occ_DL = null;
				try{
					occ_rooms = ins_res.selectDBoccupied("R");
					occ_GB = ins_res.selectDBoccupied("BG");
					occ_LB = ins_res.selectDBoccupied("BL");
					occ_DG = ins_res.selectDBoccupied("DG");
					occ_DL = ins_res.selectDBoccupied("DL");
				}catch(Exception e){
					System.out.println("Exception Occured" + e);
				}
	
				System.out.println(" occ_rooms    "+occ_rooms);
				if(occ_rooms==null){
					occ_rooms = "0";
				}
				if(occ_GB==null){
					occ_GB = "0";
				}
				if(occ_LB==null){
					occ_LB = "0";
				}
				if(occ_DG==null){
					occ_DG = "0";
				}
				if(occ_DL==null){
					occ_DL = "0";
				}
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("total_r "+total_r);
				System.out.println("occ_rooms "+occ_rooms);
				System.out.println("occ_GB "+occ_GB);
				System.out.println("occ_LB "+occ_LB);
				System.out.println("occ_DG "+occ_DG);
				System.out.println("occ_DL "+occ_DL);
	
				System.out.println("maxbeds "+maxbeds);
	
				
				if(total>0||maxbeds>0){
					double occu_rooms =total_r - ( Double.parseDouble(occ_rooms) +  Math.ceil(Double.parseDouble(occ_GB)/maxbeds) + Math.ceil(Double.parseDouble(occ_LB)/maxbeds));
					System.out.println("occu_rooms "+occu_rooms);
	
					status = (int)occu_rooms +"/"+total_r+"-Rooms";
				}
				
				else if(dor_gents>0||dor_ladies>0){
					double occu_rooms = dor_gents - ( Double.parseDouble(occ_DG));
					System.out.println("occu_roomsg "+occu_rooms);
	
					status = (int)occu_rooms +"/"+dor_gents+"-DG&";
					
					double occu_roomsl = dor_ladies - ( Double.parseDouble(occ_DL));
					System.out.println("occu_roomsl "+occu_roomsl);
	
					status = status + (int)occu_roomsl +"/"+dor_ladies+"-DL";
				}
	
				System.out.println("status="+status);
				if(!(status==null||status=="")){
				System.out.println("statusrooms "+statusrooms);
				statusrooms.add(status);
				}
			} catch (Exception e) {
				System.out.println("Exception ="+e);
			}
		}
		System.out.println("statusrooms return "+statusrooms);
		return statusrooms;
	}
	
}






