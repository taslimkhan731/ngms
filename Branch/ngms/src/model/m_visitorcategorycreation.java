package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_visitorcategorycreation extends ngmodel{
	
	  utility 		ins_util;
	  d_visitorcategory ins_vc;
	 
	public m_visitorcategorycreation(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_vc  = new d_visitorcategory(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		ins_vc.vc_id=ins_util.setNullParameter(p_request, "a");
		ins_vc.desg_category=ins_util.setNullParameter(p_request, "b");
		ins_vc.priority=ins_util.setNullParameter(p_request, "c");
		ins_vc.category=ins_util.setNullParameter(p_request, "d");
		ins_vc.sub_cat=ins_util.setNullParameter(p_request, "e");
		if(!ins_vc.insertDB())return false;
	  	return true;
	}
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_vc.vc_id=ins_util.setNullParameter(p_request, "a");
		if(!ins_vc.chkCodeDB()) 
			return false;
		else  
			return true;	
	}

	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_vc.vc_id=ins_util.setNullParameter(p_request, "a");
		ins_vc.desg_category=ins_util.setNullParameter(p_request, "b");
		ins_vc.priority=ins_util.setNullParameter(p_request, "c");
		ins_vc.category=ins_util.setNullParameter(p_request, "d");
		ins_vc.sub_cat=ins_util.setNullParameter(p_request, "e");
		if(!ins_vc.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_vc.vc_id=ins_util.setNullParameter(p_request, "vc_id");
		if(!ins_vc.deleteDB())return false;
	  	return true;
	}
}	
		



