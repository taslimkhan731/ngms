package model;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_guesthouse extends ngmodel{
	
	  utility 		ins_util;
	  d_guesthouse ins_guesthouse;
	 
	public m_guesthouse(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guesthouse  = new d_guesthouse(NGM);
		
		}
		
	
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		
		ins_guesthouse.gh_id=ins_util.setNullParameter(p_request, "a");
		ins_guesthouse.gh_name=ins_util.setNullParameter(p_request, "b");
		ins_guesthouse.mode=ins_util.setNullParameter(p_request, "c");
		ins_guesthouse.heading1=ins_util.setNullParameter(p_request, "d");
		ins_guesthouse.heading2= ins_util.setNullParameter(p_request, "e");
	    ins_guesthouse.heading3=ins_util.setNullParameter(p_request, "f");
		ins_guesthouse.heading4=ins_util.setNullParameter(p_request, "g");
		
	    if(!ins_guesthouse.insertDB())return false;
	  
		return true;
	}
	
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_guesthouse.gh_id=ins_util.setNullParameter(p_request, "a");
		ins_guesthouse.gh_name=ins_util.setNullParameter(p_request, "b");
		ins_guesthouse.mode=ins_util.setNullParameter(p_request, "c");
		ins_guesthouse.heading1=ins_util.setNullParameter(p_request, "d");
		ins_guesthouse.heading2= ins_util.setNullParameter(p_request, "e");
	    ins_guesthouse.heading3=ins_util.setNullParameter(p_request, "f");
		ins_guesthouse.heading4=ins_util.setNullParameter(p_request, "g");
		if(!ins_guesthouse.updateDB())return false;
	  
		return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_guesthouse.gh_id=ins_util.setNullParameter(p_request, "gh_id");
		 if(!ins_guesthouse.deleteDB())return false;
		  	return true;
	}
	public boolean checkghid(HttpServletRequest p_request) throws Exception
	{
		ins_guesthouse.gh_id=ins_util.setNullParameter(p_request, "a");
		if(!ins_guesthouse.checkghid())return false;
		return true;
	}
//-------------------------------------------------------------------------------Validation-------------------------------------------------------
	
	public void VJ_paginationdata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_guesthouse", "Validation fails", "paginationdata", NGM);
		}
	}
	public void VJ_editdata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_guesthouse", "Validation fails", "editdata", NGM);
		}
	}
	public void VJ_deletedata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_guesthouse", "Validation fails", "deletedata", NGM);
		}
	}
	public void VJ_editguesthouselist(String gh_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(gh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_guesthouse", "Validation fails", "editguesthouselist", NGM);
		}
	}
	public void VJ_deleteghlist(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_guesthouse", "Validation fails", "deleteghlist", NGM);
		}
	}
}	
		



