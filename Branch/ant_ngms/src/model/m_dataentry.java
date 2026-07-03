package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.*;
import db.*;

public class m_dataentry extends ngmodel{
	
	  utility 		ins_util;
	  d_gadrequest ins_gadrequest;
	  ArrayList <d_guestdetails> ins_guest = new ArrayList<d_guestdetails>();
	  d_guestdetails ins_guestdetails;
	  d_vehicle    ins_veh;
	  d_vehicle1    ins_veh1;
	  d_mod_req ins_mod;
	  d_reservation_maintable ins_res;
	  d_reservation_waitinglist ins_wait;
	  d_cancel_reservation ins_cancel_res;
	public m_dataentry(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_gadrequest	  = new d_gadrequest(NGM);
		ins_guestdetails  =  new d_guestdetails(NGM);
		ins_veh     = new d_vehicle(NGM);
		ins_veh1     = new d_vehicle1(NGM);
		ins_mod		= new d_mod_req(NGM);
		ins_res = new d_reservation_maintable(NGM);
		ins_wait = new d_reservation_waitinglist(NGM);
		ins_cancel_res = new d_cancel_reservation(NGM);
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
		System.out.println("--Inside setPostgadrequest Method---");
		String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
		System.out.println("req_id1::"+req_id1);
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	    ins_gadrequest.req_id ="G"+req_id1;//G_
	    ins_gadrequest.gh_id    						= ins_util.setNullParameter(p_request, "gh_id");
	    ins_gadrequest.block_id    						= ins_util.setNullParameter(p_request, "sel_block");
	    System.out.println("Block id == "+  ins_gadrequest.block_id );
	    ins_gadrequest.from_date    					= ins_util.setNullParameter(p_request, "from");
	    System.out.println("from date         222222222        "+ins_gadrequest.from_date);
	    ins_gadrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
	    ins_gadrequest.mobile_no   = ins_util.setNullParameter(p_request, "mobile");
	    ins_gadrequest.email_id   = ins_util.setNullParameter(p_request, "email");
	    ins_gadrequest.duringstay=ins_util.setNullParameter(p_request, "duringstay");
	    ins_gadrequest.desg=ins_util.setNullParameter(p_request, "desg"); 
		ins_gadrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_gadrequest.is_veh_req1    				= ins_util.setNullParameter(p_request, "checkedval1");
		ins_gadrequest.roomtype    				= ins_util.setNullParameter(p_request, "roomtype");
		 System.out.println("is_veh_req1  id == "+  ins_gadrequest.is_veh_req1  );
		ins_gadrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		 //ins_gadrequest.address1=p_request.getParameter("addressoffice");
		System.out.println("ins_gadrequest.address1  "+ins_gadrequest.address1  );
		ins_gadrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		if(ins_gadrequest.ext.equals("1")){
			ins_gadrequest.existing_req_id    				= ins_util.setNullParameter(p_request, "exist_req_id");
		}else{
			ins_gadrequest.existing_req_id 					= null;
		}
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.category						= ins_util.setNullParameter(p_request, "category");
		ins_gadrequest.family= ins_util.setNullParameter(p_request, "familyval");
	
			ins_gadrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
			ins_gadrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
			ins_gadrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		
			ins_gadrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
			ins_gadrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
		
		
		
		
		if(!ins_gadrequest.insertDB_gadreq())
			return false;
		return true;
	}
	public boolean setPostguestdetails(HttpServletRequest p_request) throws Exception{ 
		System.out.println("--Inside setPostguestdetails Method---");
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
		
				ins_guest.add(ins_guestdetails);
				if(!ins_guestdetails.insertDB_guest())return false;
		}
		return true;
	}
	public void setPostvehicle(HttpServletRequest p_request) throws Exception{ 
		System.out.println("--Inside setPostvehicle Method---");
		ins_veh.req_id = ins_gadrequest.req_id;
		ins_veh.mode_of_travel=ins_util.setNullParameter(p_request, "mode");
		ins_veh.arrival_time=ins_util.setNullParameter(p_request, "arrival");
		ins_veh.arrival_place=ins_util.setNullParameter(p_request, "place");
		ins_veh.no_seats_req=ins_util.setNullParameter(p_request, "seat");
		ins_veh.ftno=ins_util.setNullParameter(p_request, "ftno");
	}

	public void setPostvehicle2(HttpServletRequest p_request) throws Exception{ 
		System.out.println("--Inside setPostvehicle2 Method---");
		ins_veh1.req_id = ins_gadrequest.req_id;
		ins_veh1.mode_of_travel=ins_util.setNullParameter(p_request, "mode1");
		ins_veh1.arrival_time=ins_util.setNullParameter(p_request, "arrival1");
		ins_veh1.arrival_place=ins_util.setNullParameter(p_request, "place1");
		ins_veh1.no_seats_req=ins_util.setNullParameter(p_request, "seat1");
		ins_veh1.ftno=ins_util.setNullParameter(p_request, "ftno1");
	}
	
	
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("--Inside saveCreation Method---");
		setPostgadrequest(p_request);
		setPostguestdetails(p_request);
		setPostvehicle(p_request);
		setPostvehicle2(p_request);
		
		if(ins_gadrequest.is_veh_req.equals("1")){
				if(!ins_veh.insertDB_veh())return false;
		}

		if(ins_gadrequest.is_veh_req1.equals("1")){
				if(!ins_veh1.insertDB_veh())return false;
		}
		return true;
	}

	//update
	
	
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
//		ins_gadrequest.roomtype   				= ins_util.setNullParameter(p_request, "roomtype");
		ins_gadrequest.duringstay                   = ins_util.setNullParameter(p_request, "duringstay");
		ins_gadrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		System.out.println("ins_gadrequest.address1  "+ins_gadrequest.address1  );
		System.out.println("ins_gadrequest.duringstay "+ins_gadrequest.duringstay);
		ins_gadrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		if(ins_gadrequest.ext.equals("1")){
			ins_gadrequest.existing_req_id    				= ins_util.setNullParameter(p_request, "exist_req_id");
		}else{
			ins_gadrequest.existing_req_id 					= null;
		}
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.category					=  ins_util.setNullParameter(p_request, "category");
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
		
				ins_guest.add(ins_guestdetails);
				System.out.println("hfsdhjfhsdjhfjsd");
				if(!ins_guestdetails.insertDB_guest())return false;
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
		ins_veh1.req_id = ins_gadrequest.req_id;
		ins_veh1.mode_of_travel=ins_util.setNullParameter(p_request, "mode1");
		ins_veh1.arrival_time=ins_util.setNullParameter(p_request, "arrival1");
		ins_veh1.arrival_place=ins_util.setNullParameter(p_request, "place1");
		ins_veh1.no_seats_req=ins_util.setNullParameter(p_request, "seat1");
		ins_veh1.veh_id=ins_util.setNullParameter(p_request, "vehid1");
		ins_veh1.ftno=ins_util.setNullParameter(p_request, "ftno1");
	}
	public boolean UpdateCreation(HttpServletRequest p_request) throws Exception{
		setPostgadrequest1(p_request);
		setPostguestdetails1(p_request);
		setPostvehicle1(p_request);
		setPostvehicle3(p_request);
		
			if(ins_gadrequest.is_veh_req.equals("1")){
				if(ins_veh.selectDB()){
				 ins_veh.deleteveh();}
				if(!ins_veh.insertDB_veh())return false;
			}
			 if(ins_gadrequest.is_veh_req.equals("0")){
				 if(ins_veh.selectDB()){
				if(!ins_veh.deleteveh())return false;}
			}
			 
			 if(ins_gadrequest.is_veh_req1.equals("1")){
					if(ins_veh1.selectDB()){
					 ins_veh1.deleteveh();}
					if(!ins_veh1.insertDB_veh())return false;
				}
				 if(ins_gadrequest.is_veh_req1.equals("0")){
					 if(ins_veh1.selectDB()){
					if(!ins_veh1.deleteveh())return false;}
				}
			return true;
		}
	public boolean cancel(HttpServletRequest p_request) throws Exception{
		ins_gadrequest.req_id =   p_request.getParameter("reqid");
		if(!ins_gadrequest.cancelgad())return false;
	
			
	return true;
	}
	public boolean cancelreservation(HttpServletRequest p_request) throws Exception{
		ins_gadrequest.req_id    			= ins_util.setNullParameter(p_request, "req_id");
		 if(ins_gadrequest.selectDB_gad()){
			 if(ins_gadrequest.status.equals("ALO")){
		if(!ins_gadrequest.cancelgadreservation())return false;
		 }
	  }
		return true;
	}
	public boolean cancelcancel(HttpServletRequest p_request) throws Exception{
		System.out.println("CANCEL CANCEL");
		 ins_gadrequest.req_id    			= ins_util.setNullParameter(p_request, "req_id");
		    System.out.println("req_id====       "+ins_gadrequest.req_id);
		    if(ins_gadrequest.selectDB_gad()){
		    	 System.out.println("STATUS  ================    "+ins_gadrequest.status);
		  		    if(ins_gadrequest.status.equals("REQ")){
		  		    	 System.out.println("STATUS  ================    "+ins_gadrequest.status);
		  		    	if(!ins_gadrequest.cancelgad())return false;
		  		    	//else return true;
					}
		  		    
		  		    else if(ins_gadrequest.status.equals("MOD"))
		  		    {
		  		    	System.out.println("STATUS  11111================    "+ins_gadrequest.status);
		  		    	if(!ins_gadrequest.cancelgad())return false;
		  		    	 ins_mod.req_id    			= ins_util.setNullParameter(p_request, "req_id"); 
		  		    	if(ins_mod.selectDB_mod()){
		  		    		 System.out.println("req_id====222       "+ins_mod.req_id);
		  		    		 if(!ins_mod.cancel_mod())return false;
		  		    		// else return true;
		  		    	 }
		  		    	
		  		    	/////////////////////////////////////////FOR DRAFT
		  		    	
		  		    	ins_res.req_id = ins_util.setNullParameter(p_request, "req_id"); 
		  		    	if(ins_res.selectDB_cnf()){
		  		    		ins_res.status = "CAN";
		  		    		ins_res.update_status();
		  		    		
		  		    	/*String t_process_id="";
		  		        PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where req_id=? and status!='CAN'");
		  		   		ps.setString(1, ins_res.req_id);
		  		   		ResultSet rs=ps.executeQuery();
		  		   		while(rs.next()){
		  		   			t_process_id=rs.getString("process_id");*/
		  		   			String cancel_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "cancel_id").toString(); 
		  		   			NGM.ins_utility.updateseqID(NGM.tr_conn, "cancel_id", Integer.parseInt(cancel_id));
		  		   			
		  		   			ins_cancel_res.cancel_id = cancel_id;
		  		   			ins_cancel_res.req_id = ins_res.req_id;
		  		   			//ins_cancel_res.id_processid ="";
		  		   			ins_cancel_res.process_id = ins_res.processid;
		  		   			ins_cancel_res.gh_id = ins_res.gh_id;
		  		   			ins_cancel_res.block_id=ins_res.block_id;
		  		   			ins_cancel_res.status="CAN";
		  		   			ins_cancel_res.insertDB();
		  		   		//}
		  		   		
		  		   		
		  		   		
		  		    	}
		  		    	ins_wait.req_id = ins_util.setNullParameter(p_request, "req_id"); 
		  		    	if(ins_wait.selectDB_wait()){
		  		    		ins_wait.status = "CAN";
		  		    		ins_wait.update_status_can_req();
		  		    		
		  		    	/*String t_process_id="";
		  		        PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where req_id=? and status!='CAN'");
		  		   		ps.setString(1, ins_res.req_id);
		  		   		ResultSet rs=ps.executeQuery();
		  		   		while(rs.next()){
		  		   			t_process_id=rs.getString("process_id");*/
		  		   			String cancel_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "cancel_id").toString(); 
		  		   			NGM.ins_utility.updateseqID(NGM.tr_conn, "cancel_id", Integer.parseInt(cancel_id));
		  		   			
		  		   			ins_cancel_res.cancel_id = cancel_id;
		  		   			ins_cancel_res.req_id = ins_wait.req_id;
		  		   			//ins_cancel_res.id_processid = "";
		  		   			ins_cancel_res.process_id = ins_wait.par_process_id;
		  		   			ins_cancel_res.gh_id = ins_wait.gh_id;
		  		   		    ins_cancel_res.block_id=ins_wait.block_id;
	  		   			    ins_cancel_res.status="CAN";
		  		   			ins_cancel_res.insertDB();
		  		   		//}
		  		   		
		  		    	}
		  		    	////////////////////////////////DRAFT
		  		    }
		  		    
		  		  else if(ins_gadrequest.status.equals("PRO"))
		  		    {
		  		    	System.out.println("STATUS  11111================    "+ins_gadrequest.status);
		  		    	if(!ins_gadrequest.cancelgad())return false;
		  		    	 ins_mod.req_id    			= ins_util.setNullParameter(p_request, "req_id"); 
		  		    	if(ins_mod.selectDB_mod()){
		  		    		 System.out.println("req_id====222       "+ins_mod.req_id);
		  		    		 if(!ins_mod.cancel_mod())return false;
		  		    		 //else return true;
		  		    	 }
		  		    	System.out.println("okkkkkkkkkkk ");
		  		    	/* ins_mod.req_id    			= ins_util.setNullParameter(p_request, "req_id"); 
			  		    	if(ins_mod.selectDB_mod()){
			  		    		 System.out.println("req_id====222       "+ins_mod.req_id);
			  		    		 if(!ins_mod.cancel_mod())return false;
			  		    		 //else return true;
			  		    	 }*/
			  		    	
			  		    	ins_res.req_id = ins_util.setNullParameter(p_request, "req_id"); 
			  		    	if(ins_res.selectDB_cnf()){
			  		    		ins_res.status = "CAN";
			  		    		ins_res.update_status();
			  		    		
			  		    	/*String t_process_id="";
			  		        PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where req_id=? and status!='CAN'");
			  		   		ps.setString(1, ins_res.req_id);
			  		   		ResultSet rs=ps.executeQuery();
			  		   		while(rs.next()){
			  		   			t_process_id=rs.getString("process_id");*/
			  		   			String cancel_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "cancel_id").toString(); 
			  		   			NGM.ins_utility.updateseqID(NGM.tr_conn, "cancel_id", Integer.parseInt(cancel_id));
			  		   			
			  		   			ins_cancel_res.cancel_id = cancel_id;
			  		   			ins_cancel_res.req_id = ins_res.req_id;
			  		   		//ins_cancel_res.id_processid ="";
			  		   			ins_cancel_res.process_id = ins_res.processid;
			  		   		ins_cancel_res.gh_id = ins_res.gh_id;
			  		   			ins_cancel_res.insertDB();
			  		   		//}
			  		   		
			  		   		
			  		   		
			  		    	}
			  		    	ins_wait.req_id = ins_util.setNullParameter(p_request, "req_id"); 
			  		    	if(ins_wait.selectDB_wait()){
			  		    		ins_wait.status = "CAN";
			  		    		ins_wait.update_status_can_req();
			  		    		
			  		    	/*String t_process_id="";
			  		        PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where req_id=? and status!='CAN'");
			  		   		ps.setString(1, ins_res.req_id);
			  		   		ResultSet rs=ps.executeQuery();
			  		   		while(rs.next()){
			  		   			t_process_id=rs.getString("process_id");*/
			  		   			String cancel_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "cancel_id").toString(); 
			  		   			NGM.ins_utility.updateseqID(NGM.tr_conn, "cancel_id", Integer.parseInt(cancel_id));
			  		   			
			  		   			ins_cancel_res.cancel_id = cancel_id;
			  		   			ins_cancel_res.req_id = ins_wait.req_id;
			  		   		//ins_cancel_res.id_processid="";
			  		   			ins_cancel_res.process_id = ins_wait.par_process_id;
			  		   		ins_cancel_res.gh_id = ins_wait.gh_id;
			  		   			ins_cancel_res.insertDB();
			  		   		//}
			  		   		
			  		    	}
		  		    }
		  		    
		  		    
		}	
	return true;
	}
	public boolean deletelist(HttpServletRequest p_request) throws Exception{
		boolean retval = false;
		 ins_gadrequest.req_id= p_request.getParameter("reqid");
		 ins_guestdetails.req_id= p_request.getParameter("reqid");
		 String t_gh_id = p_request.getParameter("gh_id");
		 ins_veh.req_id= p_request.getParameter("reqid");
		 if(ins_gadrequest.selectDB_gad()){
	        	if(!ins_gadrequest.deletegad()){ retval= true;}}
		 if(ins_guestdetails.selectDB_reqGuest(t_gh_id)){
				if(!ins_guestdetails.deleteguest()){retval= true;} }
		 if(ins_veh.selectDB()){
				if(!ins_veh.deleteveh()){retval= true;}}
	
			
	return retval;
	}
	
	
	public String searchrequest(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
	String designation=(String)request.getParameter("desig");
		String date=(String)request.getParameter("date");
		common con = new common(NGM);
		
		//System.out.println("combooooooooooooooo "+rno);
		
		/*if((req_id.equals(""))&&(!name.equals(""))){
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.name = name;
			if(ins_guest.selectDB_reqid()){
				req_id = ins_guest.req_id;
			}else{
				req_id = "";
			}
		}*/
		
		 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" upper(name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" a.req_id like '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!designation.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" a.designation like '%"+designation+"%'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!date.equals("")){
						 date = con.display_date_calander(date);
						if (!criteria.equals("")) {
							criteria += join +" and ";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +"'"+date+"' between date(from_date) and date(to_date)";
					}
				}catch (NullPointerException x)	{}
				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
				
}
//	public String searchrequestlist(HttpServletRequest request)throws Exception{
//		  
//		String govno = (String)request.getParameter("process");	 
// 		System.out.println("process id@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      "+govno);
//		String rno=(String)request.getParameter("office");
//		System.out.println("rno   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+rno);
//		String date=(String)request.getParameter("date");
//		System.out.println("date @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+date);
//		
//		 
//		String criteria ="" ;
//				String join = "";
//			 		 
//				try{
//					if (!govno.equals("")) {
//						criteria += join +" process_id like '"+govno+"'";
//					}
//				}catch (NullPointerException x)	{}
//				
//				try{
//					if (!rno.equals("")){
//						if (!criteria.equals("")) {
//							criteria += join +" and";
//						}
//						criteria += join +" officeorderno like '"+rno+"'";
//					}
//				}catch (NullPointerException x)	{}
//				try{
//					if (!date.equals("")){
//						if (!criteria.equals("")) {
//							criteria += join +" and";
//						}
//						//criteria += join +" c.from_date = '"+date+"'"; // 
//						criteria += join +" created_date like  '"+date+"'";
//					}
//				}catch (NullPointerException x)	{}
//				
//				
//				 System.out.println("cccccccccccccccccccccc"+criteria);
//				 return criteria;
//}	
//	
	
	
	
	public String searchrequestlist(HttpServletRequest request)throws Exception{
		common con = new common(NGM);  
		String govno = (String)request.getParameter("process");	 
 		System.out.println("process id@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      "+govno);
		String rno=(String)request.getParameter("office");
		System.out.println("rno   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+rno);
		String date=(String)request.getParameter("date");
		System.out.println("date @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+date);
		
		 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!govno.equals("0")) {
						criteria += join +" process_id like '"+govno+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!rno.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" officeorderno like '"+rno+"'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +"'"+date+"' between date(created_date) and date(created_date)";
						//criteria += join +" created_date= '"+date+"'";
					}
				}catch (NullPointerException x)	{}
				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
}		
public String searchconfirmlist(HttpServletRequest request)throws Exception{
		String govno = (String)request.getParameter("process");	 
 		System.out.println("process id@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      "+govno);
		String rno=(String)request.getParameter("office");
		System.out.println("rno   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+rno);
		String date=(String)request.getParameter("date");
		System.out.println("date @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+date);
		
		 
		if(govno.equals("0")){
			govno = "";
		}
	
		String criteria ="" ;
				String join = "";
			 		 
			/*	try{
					if (!name.equals("")) {
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" c.name like '"+name+"'";
					}
				}catch (NullPointerException x)	{}*/
				try{
					if (!rno.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" process_id like '"+govno+"'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!govno.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" officeorderno like '"+rno+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +" created_date like  '"+date+"'";
					}
				}catch (NullPointerException x)	{}
				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
				
}
	
	
}






