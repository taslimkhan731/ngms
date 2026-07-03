package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.*;
import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_repairblock extends ngmodel{
	
	  utility 		ins_util;
	  d_repair_block ins_repair;
	  int count_room,sum_room,total_room;
	public m_repairblock(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_repair  = new d_repair_block(NGM);
		
		}
		
	public boolean validation_room(HttpServletRequest p_request) throws Exception{
		ins_repair.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_repair.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_repair.nu_of_repair_rooms=Integer.parseInt(ins_util.setNullParameter(p_request, "d"));

//		PreparedStatement ps=NGM.conn.prepareStatement("select count(room_type) from room_details where gh_id=? and block_id=?");
//		ps.setString(1,ins_repair.gh_id);
//		ps.setString(2,ins_repair.block_id);
//		ResultSet rs=ps.executeQuery();
//		while(rs.next()){
//			count_room=rs.getInt("count");
//		}
//		PreparedStatement pss=NGM.conn.prepareStatement("select SUM(nu_of_rooms :: Integer) from m_quota_block where gh_id=? and block_id=?");
//		pss.setString(1,ins_repair.gh_id);
//		pss.setString(2,ins_repair.block_id);
//		ResultSet rss=pss.executeQuery();
//		while(rss.next()){
//			sum_room=rss.getInt("sum");
//		}
//		System.out.println("COUNT       "+count_room);
//		System.out.println("SUM         "+sum_room);
//		total_room=count_room-sum_room;
//		if(ins_repair.nu_of_rooms>=total_room)
//		{
//			NGM.ins_writer.write("Message: Overflow room..only available room is "+total_room);
//			 return false;
//		 
//		}
		System.out.println("total_room       "+total_room);
		return true;
	}
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("Inside the save model................................................................");
		ins_repair.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_repair.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_repair.nu_of_repair_rooms=Integer.parseInt(ins_util.setNullParameter(p_request, "d"));
		
		if(!ins_repair.insertDB())return false;
	  	return true;
	}

	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_repair.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_repair.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_repair.nu_of_repair_rooms=Integer.parseInt(ins_util.setNullParameter(p_request, "d"));
		if(!ins_repair.updateDB())return false;
	  	return true;
	}
	
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_repair.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_repair.block_id=ins_util.setNullParameter(p_request, "sel_block");
		if(!ins_repair.checkregister()) 
			return false;
		else  
			return true;	
	}

	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_repair.block_id=ins_util.setNullParameter(p_request, "block_id");
		ins_repair.gh_id=ins_util.setNullParameter(p_request, "gh_id");
		
		if(!ins_repair.deleteDB())return false;
	  	return true;
	}
}	
		



