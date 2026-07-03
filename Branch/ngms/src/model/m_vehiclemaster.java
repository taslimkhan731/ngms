package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_vehiclemaster extends ngmodel{
	
	  utility 		ins_util;
	  d_vehiclemaster ins_vehiclemaster;
	 
	public m_vehiclemaster(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_vehiclemaster  = new d_vehiclemaster(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		String v_mallot = NGM.ins_utility.get_seqID(NGM.tr_conn, "v_mallot").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "v_mallot", Integer.parseInt(v_mallot));
		ins_vehiclemaster.driver_id=v_mallot;
		ins_vehiclemaster.driver_name=ins_util.setNullParameter(p_request, "b");
		ins_vehiclemaster.driver_mobileno=ins_util.setNullParameter(p_request, "c");
		
		ins_vehiclemaster.protocol_name=ins_util.setNullParameter(p_request, "pr");
		ins_vehiclemaster.protocol_mobileno=ins_util.setNullParameter(p_request, "pm");
		
		ins_vehiclemaster.vehicle_no=ins_util.setNullParameter(p_request, "d");
		ins_vehiclemaster.vehicle_description=ins_util.setNullParameter(p_request, "e");
		ins_vehiclemaster.gh_id = ins_util.setNullParameter(p_request, "sel_gh");
		ins_vehiclemaster.sel_block = ins_util.setNullParameter(p_request, "sel_block");
		if(!ins_vehiclemaster.insertDB())return false;
	  	return true;
	}
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_vehiclemaster.driver_id=ins_util.setNullParameter(p_request, "a");
		//ins_vehiclemaster.driver_name=ins_util.setNullParameter(p_request, "b");
		//ins_vehiclemaster.driver_mobileno=ins_util.setNullParameter(p_request, "c");
		//ins_vehiclemaster.vehicle_no=ins_util.setNullParameter(p_request, "d");
		if(!ins_vehiclemaster.chkCodeDB()) 
			return false;
		else  
			return true;	
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		//String v_mallot = NGM.ins_utility.get_seqID(NGM.tr_conn, "v_mallot").toString(); 
	   // NGM.ins_utility.updateseqID(NGM.tr_conn, "v_mallot", Integer.parseInt(v_mallot));
		ins_vehiclemaster.driver_id=ins_util.setNullParameter(p_request, "driver_id");
		ins_vehiclemaster.driver_name=ins_util.setNullParameter(p_request, "b");
		ins_vehiclemaster.driver_mobileno=ins_util.setNullParameter(p_request, "c");
		
		ins_vehiclemaster.protocol_name=ins_util.setNullParameter(p_request, "pr");
		ins_vehiclemaster.protocol_mobileno=ins_util.setNullParameter(p_request, "pm");
		
		ins_vehiclemaster.vehicle_no=ins_util.setNullParameter(p_request, "d");
		ins_vehiclemaster.sel_block = ins_util.setNullParameter(p_request, "sel_block");
		ins_vehiclemaster.vehicle_description=ins_util.setNullParameter(p_request, "e");
		if(!ins_vehiclemaster.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_vehiclemaster.driver_id=p_request.getParameter("driver_id");
		if(!ins_vehiclemaster.deleteDB())return false;
	  	return true;
	}
	
	public List<String> ghId(ngm nGM) throws Exception{
		ins_vehiclemaster.driver_id= nGM.ins_sess.username;
		return ins_vehiclemaster.ghId();
	}
}	
		



