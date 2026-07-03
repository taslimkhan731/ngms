package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_quotaa extends ngmodel{
	
	  utility 		ins_util;
	  m_quota ins_quota;
	 
	public m_quotaa(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_quota  = new m_quota(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		ins_quota.quota_type=ins_util.setNullParameter(p_request, "a");
		ins_quota.quota_desc=ins_util.setNullParameter(p_request, "b");
		if(!ins_quota.insertDB())return false;
	  	return true;
	}
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_quota.quota_type=ins_util.setNullParameter(p_request, "a");
		ins_quota.quota_desc=ins_util.setNullParameter(p_request, "b");
		if(!ins_quota.chkCodeDB()) 
			return false;
		else  
			return true;	
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_quota.quota_type=ins_util.setNullParameter(p_request, "a");
		ins_quota.quota_desc=ins_util.setNullParameter(p_request, "b");
		if(!ins_quota.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_quota.quota_type=ins_util.setNullParameter(p_request, "quota_type");
		if(!ins_quota.deleteDB())return false;
	  	return true;
	}
}	
		



