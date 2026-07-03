package model;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_ghdetails extends ngmodel{
	
	  utility 		ins_util;
	  d_ghdetails ins_ghdetails;
	 
	public m_ghdetails(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_ghdetails  = new d_ghdetails(NGM);
		
		}
		
	
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		
		
		ins_ghdetails.gh_name=ins_util.setNullParameter(p_request, "b");
		ins_ghdetails.street_name=ins_util.setNullParameter(p_request, "c");
		ins_ghdetails.city_town_name=ins_util.setNullParameter(p_request, "d");
		ins_ghdetails.district_name= ins_util.setNullParameter(p_request, "e");
	    ins_ghdetails.state_name=ins_util.setNullParameter(p_request, "f");
		ins_ghdetails.pin_code=ins_util.setNullParameter(p_request, "g");
		ins_ghdetails.gh_id=ins_util.setNullParameter(p_request, "a");
	    if(!ins_ghdetails.insertDB())return false;
	  
		return true;
	}
	
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_ghdetails.gh_name=ins_util.setNullParameter(p_request, "b");
		ins_ghdetails.street_name=ins_util.setNullParameter(p_request, "c");
		ins_ghdetails.city_town_name=ins_util.setNullParameter(p_request, "d");
		ins_ghdetails.district_name= ins_util.setNullParameter(p_request, "e");
	    ins_ghdetails.state_name=ins_util.setNullParameter(p_request, "f");
		ins_ghdetails.pin_code=ins_util.setNullParameter(p_request, "g");
		ins_ghdetails.gh_id=ins_util.setNullParameter(p_request, "a");
	    if(!ins_ghdetails.updateDB())return false;
	  
		return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_ghdetails.gh_id=ins_util.setNullParameter(p_request, "gh_id");
		 if(!ins_ghdetails.deleteDB())return false;
		  	return true;
	}
	public boolean checkghid(HttpServletRequest p_request) throws Exception
	{
		ins_ghdetails.gh_id=ins_util.setNullParameter(p_request, "a");
		if(!ins_ghdetails.checkghid())return false;
		return true;
	}
	
	
}	
		



