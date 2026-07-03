package model;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.utility;
import db.*;

public class m_roomdetails extends ngmodel{
	
	  utility 		ins_util;
	  d_roomdetails ins_roomdetails;
	 
	public m_roomdetails(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_roomdetails  = new d_roomdetails(NGM);
		
	}
		
	
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		ins_roomdetails.room_no=ins_util.setNullParameter(p_request, "a");
		ins_roomdetails.room_name=ins_util.setNullParameter(p_request, "b");
		ins_roomdetails.room_type=ins_util.setNullParameter(p_request, "r");
		ins_roomdetails.gh_id= ins_util.setNullParameter(p_request, "sel_gh");
	    ins_roomdetails.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_roomdetails.floor_no=ins_util.setNullParameter(p_request, "f");
		ins_roomdetails.max_no_beds=ins_util.setNullParameter(p_request, "g");
	    if(!ins_roomdetails.insertDB())return false;
	  
		return true;
	}
	
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_roomdetails.room_no=ins_util.setNullParameter(p_request, "a");
		ins_roomdetails.room_name=ins_util.setNullParameter(p_request, "b");
		ins_roomdetails.room_type=ins_util.setNullParameter(p_request, "r");
		ins_roomdetails.gh_id= ins_util.setNullParameter(p_request, "sel_gh");
	    ins_roomdetails.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_roomdetails.floor_no=ins_util.setNullParameter(p_request, "f");
		ins_roomdetails.max_no_beds=ins_util.setNullParameter(p_request, "g");
	    if(!ins_roomdetails.updateDB())return false;
	  
		return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		String[] block_id = null;
		String  room_no = ins_util.setNullParameter(p_request, "room_no");
		if(room_no != null){
			if(!room_no.isEmpty()){
				block_id = room_no.split("B");
			}
		}
		ins_roomdetails.room_no = block_id[0];
		ins_roomdetails.block_id = block_id[1];
		 if(!ins_roomdetails.deleteroomDB())return false;
		  	return true;
	}
}	
		



