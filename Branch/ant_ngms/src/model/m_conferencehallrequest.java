package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.*;
import db.*;

public class m_conferencehallrequest extends ngmodel{
	
	  utility 		ins_util;
	  d_conf_request ins_confdrequest;
	  d_conf_bill ins_confbill;
	  d_conf_receipt ins_rec;
	  
	public m_conferencehallrequest(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_confdrequest	  = new d_conf_request(NGM);
		ins_confbill = new d_conf_bill(NGM);
		ins_rec = new d_conf_receipt(NGM);
		
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
	
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		if(!ins_confdrequest.insertDB())return false;
		return true;
	}
	
	public boolean setPostconfrequest(HttpServletRequest p_request) throws Exception{
	
		String conf_req_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "conf_req_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "conf_req_id", Integer.parseInt(conf_req_id));
	    ins_confdrequest.conf_req_id            = conf_req_id;
	    ins_confdrequest.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
	    ins_confdrequest.sel_block  				= ins_util.setNullParameter(p_request, "sel_block");
	    ins_confdrequest.conf_cat_id    		= ins_util.setNullParameter(p_request, "sel_conf_cate");
	    ins_confdrequest.fromdate    				= ins_util.setNullParameter(p_request, "from");
	    
	    ins_confdrequest.todate    				= ins_util.setNullParameter(p_request, "to");
	    ins_confdrequest.conf_use_id            = ins_util.setNullParameter(p_request, "conf_use_type");
	    ins_confdrequest.additional             = ins_util.setNullParameter(p_request, "additional");

	    ins_confdrequest.status    				= "A";
	    ins_confdrequest.name    		        = ins_util.setNullParameter(p_request, "name");
	    ins_confdrequest.address    			= ins_util.setNullParameter(p_request, "address");
		
		return true;
	}
	
	public boolean cancelRequest(HttpServletRequest p_request) throws Exception{
		ins_confdrequest.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
	    ins_confdrequest.conf_req_id    		= ins_util.setNullParameter(p_request, "conf_req_id");
	    ins_confdrequest.status                 = "C";
		if(!ins_confdrequest.updateDB_status())return false;
		return true;
	}

	public boolean isDataExist(HttpServletRequest p_request) throws Exception{
		boolean temp_status = true;
		try{
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_conf_request where gh_id=? and block_id=? and status != 'C' and ((to_timestamp(?,'dd/MM/YYYY hh24:mi:ss') between fromdate and todate) or (to_timestamp(?,'dd/MM/YYYY hh24:mi:ss') between fromdate and todate) or ((to_timestamp(?,'dd/MM/YYYY hh24:mi:ss') <= fromdate) and (to_timestamp(?,'dd/MM/YYYY hh24:mi:ss') >= todate)))");
			ps.setString(1, ins_confdrequest.gh_id);
			ps.setString(2, ins_confdrequest.sel_block);
			ps.setString(3, ins_confdrequest.fromdate);
			ps.setString(4, ins_confdrequest.todate);
			ps.setString(5, ins_confdrequest.fromdate);
			ps.setString(6, ins_confdrequest.todate);
			System.out.println("ps HHH : "+ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println("falseeee");
				temp_status = false;
			}
			
		}catch(Exception e){
			
		}
		System.out.println("temp_status "+temp_status);
		return temp_status;
	}
	
	public boolean setPostconfbill(HttpServletRequest p_request) throws Exception{
		
		String conf_bill_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "conf_bill_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "conf_bill_id", Integer.parseInt(conf_bill_id));
	    ins_confbill.conf_bill_id            = conf_bill_id;
	    ins_confbill.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
	    ins_confbill.conf_req_id    		= ins_util.setNullParameter(p_request, "conf_req_id");
	    
	    String charge = ins_util.setNullParameter(p_request, "charge");
	    String additionalcharge = ins_util.setNullParameter(p_request, "additionalcharge");
	    System.out.println("chargeeeeeeeeeeeeeeeeee"+charge);
	    System.out.println("additionalcharge chargeeeeeeeeeeeeeeeeee"+additionalcharge);
	    int amount = Integer.parseInt(charge) + Integer.parseInt(additionalcharge);
	    
	    ins_confbill.status    				= "A";
	    ins_confbill.charge    		        = ""+Integer.parseInt(charge);
	    ins_confbill.additionalcharge       = ""+Integer.parseInt(additionalcharge);
		return true;
	}
	
	public boolean saveBill(HttpServletRequest p_request) throws Exception{
		if(!ins_confbill.insertDB())return false;
		else{
			ins_confdrequest.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
		    ins_confdrequest.conf_req_id    		= ins_util.setNullParameter(p_request, "conf_req_id");
		    ins_confdrequest.status                 = "B";
			if(!ins_confdrequest.updateDB_status())return false;
		}
		return true;
	}
	
public boolean setPostconfreceipt(HttpServletRequest p_request) throws Exception{
		String conf_receipt_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "conf_receipt_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "conf_receipt_id", Integer.parseInt(conf_receipt_id));
	    ins_rec.conf_receipt_id            = conf_receipt_id;
	    ins_rec.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
	    ins_rec.conf_req_id    		= ins_util.setNullParameter(p_request, "conf_req_id");
	    ins_rec.status = "A";
	    
	    ins_confbill.conf_req_id = ins_rec.conf_req_id;
	    ins_confbill.selectDB_reqid();
	    
	    ins_rec.conf_bill_id = ins_confbill.conf_bill_id;
	    int amount = Integer.parseInt(ins_confbill.additionalcharge) + Integer.parseInt(ins_confbill.charge);
	    ins_rec.payed = ""+amount;
	    
		return true;
	}
	
	public boolean saveReceipt(HttpServletRequest p_request) throws Exception{
		if(!ins_rec.insertDB())return false;
		else{
			ins_confdrequest.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
		    ins_confdrequest.conf_req_id    		= ins_util.setNullParameter(p_request, "conf_req_id");
		    ins_confdrequest.status                 = "R";
			if(!ins_confdrequest.updateDB_status())return false;
			else{
				ins_confbill.gh_id    				= ins_util.setNullParameter(p_request, "gh_id");
				ins_confbill.conf_bill_id    		= ins_rec.conf_bill_id;
				ins_confbill.status                 = "R";
				if(!ins_confbill.updateDB_status())return false;
				
			}
		}
		return true;
	}
	}






