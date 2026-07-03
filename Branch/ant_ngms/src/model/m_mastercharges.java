package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_mastercharges extends ngmodel{
	
	  utility 		ins_util;
	  d_master_charges ins_master;
	 
	public m_mastercharges(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_master  = new d_master_charges(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("Inside the model master charges  ))))))))))))))))))))))))))))))))))");
		ins_master.charges_id=ins_util.setNullParameter(p_request, "a");
		ins_master.charges_title=ins_util.setNullParameter(p_request, "b");
		if(!ins_master.insertDB())return false;
	  	return true;
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_master.charges_id=ins_util.setNullParameter(p_request, "a");
		ins_master.charges_title=ins_util.setNullParameter(p_request, "b");
		if(!ins_master.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_master.charges_id=ins_util.setNullParameter(p_request, "charges_id");
		if(!ins_master.deleteDB())return false;
	  	return true;
	}
}	
		



