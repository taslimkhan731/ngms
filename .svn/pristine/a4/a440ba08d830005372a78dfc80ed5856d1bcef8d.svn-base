package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_rolecreation extends ngmodel{
	
	  utility 		ins_util;
	  m_role ins_role;
	 
	public m_rolecreation(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_role  = new m_role(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		ins_role.role_code=ins_util.setNullParameter(p_request, "a");
		ins_role.role_name=ins_util.setNullParameter(p_request, "b");
		ins_role.hierarchy=ins_util.setNullParameter(p_request, "c");
		if(!ins_role.insertDB())return false;
	  	return true;
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_role.role_code=ins_util.setNullParameter(p_request, "a");
		ins_role.role_name=ins_util.setNullParameter(p_request, "b");
		ins_role.hierarchy=ins_util.setNullParameter(p_request, "c");
		if(!ins_role.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_role.role_code=ins_util.setNullParameter(p_request, "role_code");
		System.out.println("rollllllllllllllllllllllll     "+ins_role.role_code);
		if(!ins_role.deleteDB())return false;
	  	return true;
	}
	
	public boolean checkrole(HttpServletRequest p_request) throws Exception
	{
		ins_role.role_code=ins_util.setNullParameter(p_request, "a");
		if(!ins_role.checkrole())return false;
		return true;
	}
//---------------------------------------------------------------------------Validation----------------------------------------------------------------------
	public void VJ_paginationdata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_role", "Validation fails", "paginationdata", NGM);
		}
	}
	public void VJ_adddata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_role", "Validation fails", "adddata", NGM);
		}
	}
	public void VJ_editdata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_role", "Validation fails", "editdata", NGM);
		}
	}
	public void VJ_deletedata(String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_role", "Validation fails", "deletedata", NGM);
		}
	}
	public void VJ_editroleentry(String role_code) throws Exception
	{
		if(!(NGM.ins_dbval.val_role_code(role_code)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_role", "Validation fails", "editroleentry", NGM);
		}
	}
	public void VJ_deleterolelist(String role_code) throws Exception
	{
		if(!(NGM.ins_dbval.val_role_code(role_code)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_role", "Validation fails", "deleterolelist", NGM);
		}
	}
}	
		



