package model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.c_ngm_fileitem;
import common.c_ngm_fileupload;
import common.common;
import common.utility;
import db.*;

public class m_publicdataentry extends ngmodel{
	
	  utility 		ins_util;
	  d_pubrequest ins_publicrequest;
	  ArrayList <d_publicguestdetails> ins_guest = new ArrayList<d_publicguestdetails>();
	  d_publicguestdetails ins_publicguestdetails;
	  d_publicvehicle    ins_publicveh;
	  d_publicvehicle1    ins_publicveh1;
	  public String p_req_id = "";
	public m_publicdataentry(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_publicrequest	  = new d_pubrequest(NGM);
		ins_publicguestdetails  =  new d_publicguestdetails(NGM);
		ins_publicveh     = new d_publicvehicle(NGM);
		ins_publicveh1    = new d_publicvehicle1(NGM);
		}
		
	public void VJ_successmessage(String preq_id) throws Exception
	{
		if(!NGM.ins_dbval.val_reqid(preq_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "successmessage", NGM);
		}
	}
	public void VJ_createlistedit(String preq_id,String page_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_pageno(page_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "createlistedit", NGM);
		}
	}
	public void VJ_createlistdata(String preq_id,String page_no_s) throws Exception
	{
		System.out.println("preq_id "+preq_id);
		System.out.println("page_no_s "+page_no_s);
		if(!NGM.ins_dbval.val_pageno(page_no_s))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "createlistdata", NGM);
		}
	}
	public void VJ_showformedit(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "showformedit", NGM);
		}
	}
	public void VJ_showformview(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "showformview", NGM);
		}
	}
	public void VJ_printview(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "printview", NGM);
		}
	}
	public void VJ_printapplication(String pgh_id,String ppage_no) throws Exception
	{
		if(!NGM.ins_dbval.val_pageno(ppage_no))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "printapplication", NGM);
		}
	}
	public void VJ_createliststatus(String ppage_no) throws Exception
	{
		if(!NGM.ins_dbval.val_pageno(ppage_no))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "createliststatus", NGM);
		}
	}
	public void VJ_reqidstatus(String preq_id) throws Exception
	{
		if(!NGM.ins_dbval.val_reqid(preq_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "reqidstatus", NGM);
		}
	}
	public void VJ_createhomelist(String ppage_no) throws Exception
	{
		if(!NGM.ins_dbval.val_pageno(ppage_no))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "createhomelist", NGM);
		}
	}
	public void VJ_statusshow(String preq_id,String ppro_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_pro_id(ppro_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_publicdataentry", "Validation fails", "statusshow", NGM);
		}
	}
	
	
	
	public boolean setPostpublicrequest(HttpServletRequest p_request) throws Exception{ 
		String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	    ins_publicrequest.req_id ="P"+req_id1;//P_
	    
	    p_req_id =  ins_publicrequest.req_id ;
	    ins_publicrequest.gh_id    						= ins_util.setNullParameter(p_request, "gh_id");
	    ins_publicrequest.block_id    						= ins_util.setNullParameter(p_request, "sel_block");
		ins_publicrequest.from_date    					= ins_util.setNullParameter(p_request, "from");
		ins_publicrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
		//ins_publicrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
		//ins_publicrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
		//ins_publicrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		ins_publicrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_publicrequest.is_veh_req1    				= ins_util.setNullParameter(p_request, "checkedval1");
		ins_publicrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		System.out.println("ins_publicrequest.address1  "+ins_publicrequest.address1  );
		ins_publicrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		if(ins_publicrequest.ext.equals("1")){
			ins_publicrequest.existing_req_id    				= ins_util.setNullParameter(p_request, "exist_req_id");
		}else{
			ins_publicrequest.existing_req_id 					= null;
		}
		ins_publicrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_publicrequest.family= ins_util.setNullParameter(p_request, "familyval");
		//ins_publicrequest.roomtype = ins_util.setNullParameter(p_request, "roomtype");
		//System.out.println("roomtype      "+ins_publicrequest.roomtype);
		//if(ins_publicrequest.roomtype.equals("R"))
		//{
			ins_publicrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
			ins_publicrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
			ins_publicrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		//}
		//else if(ins_publicrequest.roomtype.equals("D"))
		//{
			ins_publicrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
			System.out.println("  NIC dddd           "+ins_publicrequest.nod_g_req);
			ins_publicrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
		//}
		
		ins_publicrequest.mobile_no					=	ins_util.setNullParameter(p_request, "mobile");
		ins_publicrequest.email_id					=	ins_util.setNullParameter(p_request, "email");
		if(!(ins_publicrequest.insertDB_publicreq() && ins_publicrequest.insertDB_publicreq_Gad()))return false;
		return true;
	}
	
	public boolean setPostpublicguestdetails(HttpServletRequest p_request) throws Exception{ 
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "rowCnt"));
		System.out.println("count    "+count);
		
		for(int i=0;i<count;i++)
		{
			int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
			ins_publicguestdetails.guest_id = "p_"+String.valueOf(guest_id1);
			String gender="gender";
			System.out.println("iiiiii       "+i);
				String name="name";
				String age="age";
				String desg="desg";
				String dept="dept";
				String address="address";
		
			//ins_dataentry.guest_id    					= ins_util.setNullParameter(p_request, "name");
			
			//System.out.println("djfhdjshf gender    "+ins_dataentry.gender);
				ins_publicguestdetails.req_id = ins_publicrequest.req_id;
				ins_publicguestdetails.name    				= ins_util.setNullParameter(p_request, name+i);
				ins_publicguestdetails.age    					= ins_util.setNullParameter(p_request, age+i);
				ins_publicguestdetails.gender    				= ins_util.setNullParameter(p_request, gender+i);
				ins_publicguestdetails.designation    				= ins_util.setNullParameter(p_request, desg+i);
				ins_publicguestdetails.department   					= ins_util.setNullParameter(p_request, dept+i);
				ins_publicguestdetails.address2    					= ins_util.setNullParameter(p_request, address+i);
				
				if(ins_publicguestdetails.address2.equals("Same As Above")){
					ins_publicguestdetails.address2 = ins_util.setNullParameter(p_request, "addressoffice");
				}
				
				
				ins_guest.add(ins_publicguestdetails);
				if(!(ins_publicguestdetails.insertDB_guest() && ins_publicguestdetails.insertDB_guest_Gad()))return false;
		}
		return true;
	}
	
	public void setPostpublicvehicle(HttpServletRequest p_request) throws Exception{ 
		ins_publicveh.req_id = ins_publicrequest.req_id;
		ins_publicveh.mode_of_travel=ins_util.setNullParameter(p_request, "mode");
		ins_publicveh.arrival_time=ins_util.setNullParameter(p_request, "arrival");
		ins_publicveh.arrival_place=ins_util.setNullParameter(p_request, "place");
		ins_publicveh.no_seats_req=ins_util.setNullParameter(p_request, "seat");
		ins_publicveh.ftno=ins_util.setNullParameter(p_request, "ftno");
		
	}
	public void setPostpublicvehicle1(HttpServletRequest p_request) throws Exception{ 
		ins_publicveh1.req_id = ins_publicrequest.req_id;
		ins_publicveh1.mode_of_travel=ins_util.setNullParameter(p_request, "mode1");
		ins_publicveh1.arrival_time=ins_util.setNullParameter(p_request, "arrival1");
		ins_publicveh1.arrival_place=ins_util.setNullParameter(p_request, "place1");
		ins_publicveh1.no_seats_req=ins_util.setNullParameter(p_request, "seat1");
		ins_publicveh1.ftno=ins_util.setNullParameter(p_request, "ftno1");
		
	}
	
	public void setPostpublicvehicle2(List  pitemlist, common ins_common) throws Exception{ 
		ins_publicveh.req_id = ins_publicrequest.req_id;
		ins_publicveh.mode_of_travel=ins_common.getparametervalue(pitemlist, "mode");
		ins_publicveh.arrival_time=ins_common.getparametervalue(pitemlist, "arrival");
		ins_publicveh.arrival_place=ins_common.getparametervalue(pitemlist, "place");
		ins_publicveh.no_seats_req=ins_common.getparametervalue(pitemlist, "seat");
		ins_publicveh.ftno=ins_common.getparametervalue(pitemlist, "ftno");
		
	}
	public void setPostpublicvehicle3(List  pitemlist, common ins_common) throws Exception{ 
		ins_publicveh1.req_id = ins_publicrequest.req_id;
		ins_publicveh1.mode_of_travel=ins_common.getparametervalue(pitemlist, "mode1");
		ins_publicveh1.arrival_time=ins_common.getparametervalue(pitemlist, "arrival1");
		ins_publicveh1.arrival_place=ins_common.getparametervalue(pitemlist, "place1");
		ins_publicveh1.no_seats_req=ins_common.getparametervalue(pitemlist, "seat1");
		ins_publicveh1.ftno=ins_common.getparametervalue(pitemlist, "ftno1");
		
	}
		
	public boolean saveCreation(HttpServletRequest p_request, List FILEMULTIPARTITEMS, common ins_common) throws Exception{
		String path = null;
		String temp_curdate = NGM.ins_utility.getcurdatestring();
		c_ngm_fileupload ins_bfupload = new c_ngm_fileupload();
		ins_bfupload.read_file_request(FILEMULTIPARTITEMS, NGM.ins_sess.user_id, NGM.ins_ngmprop,temp_curdate);
		ArrayList<c_ngm_fileitem> fileitem_list	= ins_bfupload.fileitem_list;
		
		Iterator itr = fileitem_list.iterator();
		while (itr.hasNext()) {
			c_ngm_fileitem cfi = (c_ngm_fileitem)itr.next();
			path=cfi.filepath;
		}
		
		setPostpublicrequest(FILEMULTIPARTITEMS,ins_common,path);
		setPostpublicguestdetails(FILEMULTIPARTITEMS,ins_common);
		
//		setPostpublicrequest(p_request);
//		setPostpublicguestdetails(p_request);
		setPostpublicvehicle2(FILEMULTIPARTITEMS,ins_common);
		setPostpublicvehicle3(FILEMULTIPARTITEMS,ins_common);
		
		int veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
	    ins_publicveh.veh_id = String.valueOf(veh_id1);
	    
	    veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
	    ins_publicveh1.veh_id = String.valueOf(veh_id1);
		
		if(ins_publicrequest.is_veh_req.equals("1")){
				if(!(ins_publicveh.insertDB_veh() && ins_publicveh.insertDB_veh_Gad()))return false;
		}
		if(ins_publicrequest.is_veh_req1.equals("1")){
			if(!(ins_publicveh1.insertDB_veh() && ins_publicveh1.insertDB_veh_Gad()))return false;
		}
		return true;
	}
	
	//update
	
	
	public boolean setPostgadrequest1(HttpServletRequest p_request) throws Exception{ 
		System.out.println("model       "+p_request);
		//String req_id1 = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id").toString(); 
	   // NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	    //ins_publicrequest.req_id ="G"+req_id1; //G_
		 ins_publicrequest.req_id    						= ins_util.setNullParameter(p_request, "req_id");
	    System.out.println("req_id====       "+ins_publicrequest.req_id);
	    ins_publicrequest.gh_id    						= ins_util.setNullParameter(p_request, "sel_id");
	    ins_publicrequest.block_id    						= ins_util.setNullParameter(p_request, "sel_block");
	    System.out.println("gh_id     "+ins_publicrequest.gh_id);
		ins_publicrequest.from_date   					= ins_util.setNullParameter(p_request, "from");
		System.out.println(ins_publicrequest.from_date);
		ins_publicrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
		ins_publicrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
		ins_publicrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
		ins_publicrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		ins_publicrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_publicrequest.is_veh_req1    				= ins_util.setNullParameter(p_request, "checkedval1");
		ins_publicrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		System.out.println("ins_publicrequest.address1  "+ins_publicrequest.address1  );
		ins_publicrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		ins_publicrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_publicrequest.category						= ins_util.setNullParameter(p_request, "category");
		ins_publicrequest.family= ins_util.setNullParameter(p_request, "familyval");
		ins_publicrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
		System.out.println("  NIC dddd   gg        "+ins_publicrequest.nod_g_req);
		ins_publicrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
		System.out.println("  NIC dddd   lll        "+ins_publicrequest.nod_l_req);

	//}
	
	ins_publicrequest.mobile_no					=	ins_util.setNullParameter(p_request, "mobile");
	ins_publicrequest.email_id					=	ins_util.setNullParameter(p_request, "email");
	
		

	if(!(ins_publicrequest.updateDB_publicreq() && ins_publicrequest.updateDB_publicreq_Gad()))return false;
		return true;	
	}
	public boolean setPostguestdetails1(HttpServletRequest p_request) throws Exception{ 
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "rowCnt"));
		ins_publicguestdetails.req_id				    = ins_publicrequest.req_id;
		if(!(ins_publicguestdetails.deleteguest() && ins_publicguestdetails.deleteguest_Gad())) return false;
		System.out.println("count    "+count);
		for(int i=0;i<count;i++)
		{
				int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
				ins_publicguestdetails.guest_id = "p_"+String.valueOf(guest_id1);
			  	String guest = "guest";
				String gender="gender";
				System.out.println("iiiiii       "+i);
				String name="name";
				String age="age";
				String desg="desg";
				String dept="dept";
				String address="address";
		
			//ins_dataentry.guest_id    					= ins_util.setNullParameter(p_request, "name");
			
			//System.out.println("djfhdjshf gender    "+ins_dataentry.gender);
				ins_publicguestdetails.req_id = ins_publicrequest.req_id;
				System.out.println("req_idggadgf        "+ins_publicguestdetails.req_id);
				/*ins_publicguestdetails.guest_id=	ins_util.setNullParameter(p_request, guest+i);*/
				ins_publicguestdetails.name    				= ins_util.setNullParameter(p_request, name+i);
				System.out.println("--------------------ins_publicguestdetails.name   "+ins_publicguestdetails.name );
				ins_publicguestdetails.age    					= ins_util.setNullParameter(p_request, age+i);
				ins_publicguestdetails.gender    				= ins_util.setNullParameter(p_request, gender+i);
				ins_publicguestdetails.designation    				= ins_util.setNullParameter(p_request, desg+i);
				ins_publicguestdetails.department   					= ins_util.setNullParameter(p_request, dept+i);
				ins_publicguestdetails.address2    					= ins_util.setNullParameter(p_request, address+i);
		
				ins_guest.add(ins_publicguestdetails);
				if(!(ins_publicguestdetails.insertDB_guest() && ins_publicguestdetails.insertDB_guest_Gad()))return false;
		}
		return true;
	}
	public void setPostvehicle1(HttpServletRequest p_request) throws Exception{ 
		ins_publicveh.req_id = ins_publicrequest.req_id;
		ins_publicveh.mode_of_travel=ins_util.setNullParameter(p_request, "mode");
		ins_publicveh.arrival_time=ins_util.setNullParameter(p_request, "arrival");
		ins_publicveh.arrival_place=ins_util.setNullParameter(p_request, "place");
		ins_publicveh.no_seats_req=ins_util.setNullParameter(p_request, "seat");
		ins_publicveh.veh_id=ins_util.setNullParameter(p_request, "vehid");
		ins_publicveh.ftno=ins_util.setNullParameter(p_request, "ftno");
	}
	
	public void setPostvehicle3(HttpServletRequest p_request) throws Exception{ 
		ins_publicveh1.req_id = ins_publicrequest.req_id;
		ins_publicveh1.mode_of_travel=ins_util.setNullParameter(p_request, "mode1");
		ins_publicveh1.arrival_time=ins_util.setNullParameter(p_request, "arrival1");
		ins_publicveh1.arrival_place=ins_util.setNullParameter(p_request, "place1");
		ins_publicveh1.no_seats_req=ins_util.setNullParameter(p_request, "seat1");
		ins_publicveh1.veh_id=ins_util.setNullParameter(p_request, "vehid1");
		ins_publicveh1.ftno=ins_util.setNullParameter(p_request, "ftno1");
	}
	
	public boolean UpdateCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("Inside model updatecreation");
		setPostgadrequest1(p_request);
		setPostguestdetails1(p_request);
		setPostvehicle1(p_request);
		setPostvehicle3(p_request);
		ins_publicveh.selectDB();
		ins_publicveh1.selectDB();
		
		if(ins_publicrequest.is_veh_req.equals("1")){
			ins_publicveh.deleteveh();
			ins_publicveh.deleteveh_Gad();
			if(!ins_publicveh.insertDB_veh())return false;
		}
		 if(ins_publicrequest.is_veh_req.equals("0")){
			ins_publicveh.deleteveh();
			ins_publicveh.deleteveh_Gad();
		}
		 
		 if(ins_publicrequest.is_veh_req1.equals("1")){
				ins_publicveh1.deleteveh();
				ins_publicveh1.deleteveh_Gad();
				if(!ins_publicveh1.insertDB_veh())return false;
			}
			 if(ins_publicrequest.is_veh_req1.equals("0")){
				ins_publicveh1.deleteveh_Gad();
				ins_publicveh1.deleteveh();
			}
		 
		return true;
	}
		 
	
	public boolean cancel(HttpServletRequest p_request) throws Exception{
		ins_publicrequest.req_id =   p_request.getParameter("reqid");
		if(!(ins_publicrequest.cancelpublic() && ins_publicrequest.cancelpublic_Gad()))return false;
	
			
	return true;
	}
	
	public boolean deletelist(HttpServletRequest p_request) throws Exception{
		ins_publicrequest.req_id= p_request.getParameter("reqid");
		ins_publicguestdetails.req_id= p_request.getParameter("reqid");
		ins_publicveh.req_id= p_request.getParameter("reqid");
		ins_publicveh1.req_id= p_request.getParameter("reqid");
		if(!(ins_publicrequest.deletepublic() && ins_publicrequest.deletepublic_Gad()))return false;
		if(!(ins_publicguestdetails.deleteguest() && ins_publicguestdetails.deleteguest_Gad()))return false;
		if(!(ins_publicveh.deleteveh() && ins_publicveh.deleteveh_Gad()))return true;
		if(!(ins_publicveh1.deleteveh() && ins_publicveh1.deleteveh_Gad()))return true;
		return true;
	}
	
	//New Added By jayant
	
	public boolean setPostpublicrequest(List  pitemlist, common ins_common, String path) throws Exception{ 
		String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
	    ins_publicrequest.req_id ="P"+req_id1;//P_
	    
	    p_req_id =  ins_publicrequest.req_id ;
	    
	    ins_publicrequest.gh_id    						= ins_common.getparametervalue(pitemlist, "gh_id");
	    ins_publicrequest.block_id    						= ins_common.getparametervalue(pitemlist, "block_sel");
		ins_publicrequest.from_date    					= ins_common.getparametervalue(pitemlist, "from");
		ins_publicrequest.to_date    					= ins_common.getparametervalue(pitemlist, "to");
		ins_publicrequest.is_veh_req    				= ins_common.getparametervalue(pitemlist, "checkedval");
		ins_publicrequest.is_veh_req1    				= ins_common.getparametervalue(pitemlist, "checkedval1");
		ins_publicrequest.address1    					    = ins_common.getparametervalue(pitemlist, "addressoffice");
		System.out.println("ins_publicrequest.address1  "+ins_publicrequest.address1  );
		ins_publicrequest.ext    				= ins_common.getparametervalue(pitemlist, "extval");
		if(ins_publicrequest.ext.equals("1")){
			ins_publicrequest.existing_req_id    				= ins_common.getparametervalue(pitemlist, "exist_req_id");
		}else{
			ins_publicrequest.existing_req_id 					= null;
		}
		ins_publicrequest.purpose						= ins_common.getparametervalue(pitemlist, "purpose");
		ins_publicrequest.category  					= ins_common.getparametervalue(pitemlist, "category");
		ins_publicrequest.family= ins_common.getparametervalue(pitemlist, "familyval");
		ins_publicrequest.nor_req    					= ins_common.getparametervalue(pitemlist, "rooms");
		ins_publicrequest.nob_g_req    					= ins_common.getparametervalue(pitemlist, "nbg");
		ins_publicrequest.nob_l_req    					= ins_common.getparametervalue(pitemlist, "nbl");
		
		ins_publicrequest.nod_g_req = ins_common.getparametervalue(pitemlist, "ndg");
		System.out.println("  NIC dddd           "+ins_publicrequest.nod_g_req);
		ins_publicrequest.nod_l_req = ins_common.getparametervalue(pitemlist, "ndl");
		
		ins_publicrequest.mobile_no					=	ins_common.getparametervalue(pitemlist, "mobile");
		ins_publicrequest.email_id					=	ins_common.getparametervalue(pitemlist, "email");
		ins_publicrequest.path = path;
		if(!(ins_publicrequest.insertDB_publicreq() && ins_publicrequest.insertDB_publicreq_Gad()))return false;
		return true;
	}

	public boolean setPostpublicguestdetails(List  pitemlist, common ins_common) throws Exception{ 
		int count= Integer.parseInt(ins_common.getparametervalue(pitemlist, "rowCnt"));
		System.out.println("count    "+count);
		for(int i=0;i<count;i++)
		{
			int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
			ins_publicguestdetails.guest_id = "p_"+String.valueOf(guest_id1);
			String gender="gender";
			System.out.println("iiiiii       "+i);
				String name="name";
				String age="age";
				String desg="desg";
				String dept="dept";
				String address="address";
		
			//ins_dataentry.guest_id    					= ins_util.setNullParameter(p_request, "name");
			
			//System.out.println("djfhdjshf gender    "+ins_dataentry.gender);
				ins_publicguestdetails.req_id = ins_publicrequest.req_id;
				ins_publicguestdetails.name    				= ins_common.getparametervalue(pitemlist, name+i);
				ins_publicguestdetails.age    					= ins_common.getparametervalue(pitemlist, age+i);
				ins_publicguestdetails.gender    				= ins_common.getparametervalue(pitemlist, gender+i);
				ins_publicguestdetails.designation    				= ins_common.getparametervalue(pitemlist, desg+i);
				ins_publicguestdetails.department   					= ins_common.getparametervalue(pitemlist, dept+i);
				ins_publicguestdetails.address2    					= ins_common.getparametervalue(pitemlist, address+i);
				
				if(ins_publicguestdetails.address2.equals("Same As Above")){
					ins_publicguestdetails.address2 = ins_common.getparametervalue(pitemlist, "addressoffice");
				}
				ins_guest.add(ins_publicguestdetails);
				if(!(ins_publicguestdetails.insertDB_guest() && ins_publicguestdetails.insertDB_guest_Gad()))return false;
		}
		return true;
	}
	
	public String getFilePath(String req_id) throws Exception{
		String path = null;
		try {
			if(req_id != null){
				if(!req_id.isEmpty()){
					path = ins_publicguestdetails.getFilePath(req_id.trim());
				}
			}
		} catch (Exception e) {}
		return path;
	}
	public String getFilePath1(String req_id) throws Exception{
		String path = null;
		try {
			if(req_id != null){
				if(!req_id.isEmpty()){
					path = ins_publicguestdetails.getFilePath1(req_id.trim());
				}
			}
		} catch (Exception e) {}
		return path;
	}
	public boolean checkProfile() throws SQLException {
		ins_publicguestdetails.guest_id = NGM.ins_sess.user_id;
		return ins_publicguestdetails.checkProfile();
	}
	
	public boolean checkEligibility(String gh_id) throws SQLException {
		ins_publicguestdetails.guest_id = NGM.ins_sess.user_id;
		return ins_publicguestdetails.checkEligibility(gh_id);
	}
	
	public boolean checkLocation() throws SQLException {
		ins_publicguestdetails.guest_id = NGM.ins_sess.user_id;
		return ins_publicguestdetails.checkLocation();
	}
	
}


