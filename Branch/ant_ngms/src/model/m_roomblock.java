package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_roomblock extends ngmodel{
	
	  utility 		ins_util;
	  d_roomblock ins_roomblock;
	 
	public m_roomblock(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_roomblock  = new d_roomblock(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		ins_roomblock.block_id=ins_util.setNullParameter(p_request, "a");
		ins_roomblock.block_name=ins_util.setNullParameter(p_request, "b");
		ins_roomblock.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_roomblock.nu_of_floors=ins_util.setNullParameter(p_request, "c");
		ins_roomblock.nu_of_rooms=ins_util.setNullParameter(p_request, "d");
		ins_roomblock.block_name_short=ins_util.setNullParameter(p_request, "e");
		
		if(!ins_roomblock.insertDB())return false;
	  	return true;
	}
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_roomblock.block_id=ins_util.setNullParameter(p_request, "a");
		ins_roomblock.block_name=ins_util.setNullParameter(p_request, "b");
		ins_roomblock.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_roomblock.nu_of_floors=ins_util.setNullParameter(p_request, "c");
		ins_roomblock.nu_of_rooms=ins_util.setNullParameter(p_request, "d");
		ins_roomblock.block_name_short=ins_util.setNullParameter(p_request, "e");
		if(!ins_roomblock.chkCodeDB()) 
			return false;
		else  
			return true;	
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("Inside the modelllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
		ins_roomblock.block_id=ins_util.setNullParameter(p_request, "a");
		ins_roomblock.block_name=ins_util.setNullParameter(p_request, "b");
		ins_roomblock.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_roomblock.nu_of_floors=ins_util.setNullParameter(p_request, "c");
		ins_roomblock.nu_of_rooms=ins_util.setNullParameter(p_request, "d");
		ins_roomblock.block_name_short=ins_util.setNullParameter(p_request, "e");
		System.out.println(""+p_request.getParameter("sel_gh"));
		if(!ins_roomblock.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_roomblock.block_id=ins_util.setNullParameter(p_request, "block_id");
		if(!ins_roomblock.deleteDB())return false;
	  	return true;
	}
}	
		



