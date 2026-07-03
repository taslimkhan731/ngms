package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_menucreation extends ngmodel{
	  utility 		ins_util;
	  m_menu_item ins_menudetails;
	  m_rolemenu ins_rolemenu;
	public m_menucreation(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_menudetails  = new m_menu_item(NGM);
		ins_rolemenu	= new m_rolemenu(NGM);
		}

	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		int p_curvalue = NGM.ins_utility.get_seqID(NGM.tr_conn, "menu_id"); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "menu_id",(p_curvalue));
	    ins_menudetails.menu_id=p_curvalue;
		ins_menudetails.menu_name=ins_util.setNullParameter(p_request, "menu_name");
		ins_menudetails.page_url=ins_util.setNullParameter(p_request, "page_url");
		ins_menudetails.parent_id=Integer.parseInt(ins_util.setNullParameter(p_request, "parent_id"));
		ins_menudetails.image_url=ins_util.setNullParameter(p_request, "image_url");
		ins_menudetails.fn_call=ins_util.setNullParameter(p_request, "fn_call");
		ins_menudetails.priority=Integer.parseInt(ins_util.setNullParameter(p_request, "priority"));
		String select[] = p_request.getParameterValues("rolecode"); 
		if(select == null){
			return false;
		}
		if(select.length == 0){
			return false;
		}
		if (select != null && select.length != 0) {
		System.out.println("You have selected: ");
		for (int i = 0; i < select.length; i++) {
		System.out.println(select[i]); 
				
			ins_rolemenu.menu_id=ins_menudetails.menu_id;
			ins_rolemenu.role_code=(select[i]);
        	System.out.println("DELTEEEEEEEEEEEEEEEEEEEEEEEEEE       ");
        	if(!ins_rolemenu.insertDB())return false;    	
        	
            System.out.println("Split1 Element "+i+" :"+select[i]);
        }
		}
	
		if(!ins_menudetails.insertDB())return false;
		
	  	return true;
	}

	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_menudetails.menu_id=Integer.parseInt(ins_util.setNullParameter(p_request, "menu_id"));
		System.out.println("1     "+ins_menudetails.menu_id);
		ins_rolemenu.menu_id=Integer.parseInt(ins_util.setNullParameter(p_request, "menu_id"));
		String select[] = p_request.getParameterValues("rolecode"); 
		if(select == null){
			return false;
		}
		if(select.length == 0){
			return false;
		}
		if(!ins_rolemenu.deleteDB())return false;
		 ins_menudetails.menu_id=Integer.parseInt(ins_util.setNullParameter(p_request, "menu_id"));
		ins_menudetails.menu_name=ins_util.setNullParameter(p_request, "menu_name");
		ins_menudetails.page_url=ins_util.setNullParameter(p_request, "page_url");
		ins_menudetails.parent_id=Integer.parseInt(ins_util.setNullParameter(p_request, "parent_id"));
		ins_menudetails.image_url=ins_util.setNullParameter(p_request, "image_url");
		ins_menudetails.fn_call=ins_util.setNullParameter(p_request, "fn_call");
		ins_menudetails.priority=Integer.parseInt(ins_util.setNullParameter(p_request, "priority"));
		if (select != null && select.length != 0) {
		System.out.println("You have selected: ");
		for (int i = 0; i < select.length; i++) {
		System.out.println(select[i]); 
				
			ins_rolemenu.menu_id=ins_menudetails.menu_id;
			ins_rolemenu.role_code=(select[i]);
        	System.out.println("DELTEEEEEEEEEEEEEEEEEEEEEEEEEE       ");
        	if(!ins_rolemenu.insertDB())return false;    	
        	
            System.out.println("Split1 Element "+i+" :"+select[i]);
        }
		}
       
		
		if(!ins_menudetails.updateDB())return false;
		return true;
	}

	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_menudetails.menu_id=Integer.parseInt(ins_util.setNullParameter(p_request, "menu_id"));
		ins_rolemenu.menu_id=Integer.parseInt(ins_util.setNullParameter(p_request, "menu_id"));
		System.out.println("rollllllllllllllllllllllll     "+ins_menudetails.menu_id);
		System.out.println("rollllllllllllllllllllllllins_rolemenu.menu_id     "+ins_rolemenu.menu_id);
		try{
			if(!ins_menudetails.deleteDB()){return false;}
			if(!ins_rolemenu.deleteDB()){return false;}
		}catch(Exception e){}
		return true;
	}
//---------------------------------------------------------------validation---------------------------------------------------------
	public void VJ_createlistdata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_menudetails", "Validation fails", "createlistdata", NGM);
		}
	}
	public void VJ_editlistdata(String menu_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(menu_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_menudetails", "Validation fails", "editlistdata", NGM);
		}
	}
	public void VJ_deletedata(String menu_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(menu_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_menudetails", "Validation fails", "deletedata", NGM);
		}
	}
}