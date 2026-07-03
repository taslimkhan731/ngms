package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.*;

import javax.servlet.http.HttpServletRequest;

import common.utility;
import db.*;

public class m_quotablock extends ngmodel{
	
	  utility 		ins_util;
	  d_quota_block ins_block;
	  int count_room,sum_room,total_room;
	public m_quotablock(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_block  = new d_quota_block(NGM);
		
		}
		
	public boolean validation_room(HttpServletRequest p_request) throws Exception{
		String roomnum = p_request.getParameter("d");
		ins_block.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_block.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_block.quota_type=ins_util.setNullParameter(p_request, "sel_quota");
		if(roomnum.equals("")){
			ins_block.nu_of_rooms=0;	
		}else{
		ins_block.nu_of_rooms=Integer.parseInt(ins_util.setNullParameter(p_request, "d"));
		}
		System.out.println("ins_block.nu_of_rooms       "+ins_block.nu_of_rooms);
		PreparedStatement ps=NGM.conn.prepareStatement("select count(room_type) from room_details where gh_id=? and block_id=?");
		ps.setString(1,ins_block.gh_id);
		ps.setString(2,ins_block.block_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			count_room=rs.getInt("count");
		}
		 //close
	    rs.close();
		ps.close();
		PreparedStatement pss=NGM.conn.prepareStatement("select SUM(nu_of_rooms :: Integer) from m_quota_block where gh_id=? and block_id=?");
		pss.setString(1,ins_block.gh_id);
		pss.setString(2,ins_block.block_id);
		ResultSet rss=pss.executeQuery();
		while(rss.next()){
			sum_room=rss.getInt("sum");
		}
		 //close
	    rss.close();
		pss.close();
		System.out.println("COUNT       "+count_room);
		System.out.println("SUM         "+sum_room);
		total_room=count_room-sum_room;
		System.out.println("total_room       "+total_room);
		if(ins_block.nu_of_rooms>total_room)
		{
			NGM.ins_writer.write("Message: Overflow room..only available room is "+total_room);
			 return false;
		}
		System.out.println("total_room       "+total_room);
		return true;
	}
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("Inside the save model................................................................");
        String roomnum = p_request.getParameter("d");
        String maxbed = p_request.getParameter("e");
		String dormtrygen = p_request.getParameter("f");
		String dormtrylad = p_request.getParameter("g");
		
		ins_block.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_block.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_block.quota_type=ins_util.setNullParameter(p_request, "sel_quota");
		if(roomnum.equals("")){
			ins_block.nu_of_rooms=0;	
		}else{
		ins_block.nu_of_rooms=Integer.parseInt(ins_util.setNullParameter(p_request, "d"));
		}
		if(maxbed.equals("")){
			ins_block.max_beds=0;	
		}else{
		ins_block.max_beds=Integer.parseInt(ins_util.setNullParameter(p_request, "e"));
		}
		if(dormtrygen.equals("")){
			ins_block.dormitory_gents=0;	
		}else{
		ins_block.dormitory_gents=Integer.parseInt(ins_util.setNullParameter(p_request, "f"));
		}
		if(dormtrylad.equals("")){	
			ins_block.dormitory_ladies=0;
		}else{
		ins_block.dormitory_ladies=Integer.parseInt(ins_util.setNullParameter(p_request, "g"));
		}
		if(!ins_block.insertDB())return false;
	  	return true;
	}

	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_block.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_block.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_block.quota_type=ins_util.setNullParameter(p_request, "sel_quota");
		ins_block.nu_of_rooms=Integer.parseInt(ins_util.setNullParameter(p_request, "d"));
		ins_block.max_beds=Integer.parseInt(ins_util.setNullParameter(p_request, "e"));
		ins_block.dormitory_gents=Integer.parseInt(ins_util.setNullParameter(p_request, "f"));
		ins_block.dormitory_ladies=Integer.parseInt(ins_util.setNullParameter(p_request, "g"));
		if(!ins_block.updateDB())return false;
	  	return true;
	}
	
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_block.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		ins_block.block_id=ins_util.setNullParameter(p_request, "sel_block");
		ins_block.quota_type=ins_util.setNullParameter(p_request, "sel_quota");
		if(!ins_block.chkCodeDB()) 
			return false;
		else  
			return true;	
	}

	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_block.block_id=ins_util.setNullParameter(p_request, "block_id");
		ins_block.quota_type=ins_util.setNullParameter(p_request, "quota_type");
		
		if(!ins_block.deleteDB())return false;
	  	return true;
	}
}	
		



