package model;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;
import java.sql.*;

public class m_foodbill extends ngmodel{
	  utility 		ins_util;
	public  d_guestcatering ins_food;
	  String a="",b="",c="",d="",e="",guest="",dddd="";
	public m_foodbill(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_food  =  new d_guestcatering(NGM);
	}
	public void VJ_foodbilllist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_foodbill", "Validation fails", "foodbilllist", NGM);
		}
	}
	public void VJ_cancelfoodbill(String pgh_id,String pseq_id,String pguest_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_seqid(pseq_id)&&NGM.ins_dbval.val_guestid(pguest_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_foodbill", "Validation fails", "cancelfoodbill", NGM);
		}
	}
	public void VJ_search(String pgh_id,String ppage_no) throws Exception
	{
		System.out.println("pgh_id "+pgh_id);
		System.out.println("ppage_no "+ppage_no);
		if(!(NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_foodbill", "Validation fails", "search", NGM);
		}
	}
	
	
	
	
	
	
	
	public String guest_id(HttpServletRequest p_request) throws Exception{
		String guest= ins_util.setNullParameter(p_request,"sel_parent");
		return guest;
	}
	public boolean setPostguestdetails(HttpServletRequest p_request) throws Exception{ 
				int count= Integer.parseInt(ins_util.setNullParameter(p_request, "count2"));
				ins_food.req_id=ins_util.setNullParameter(p_request, "req_id");
				ins_food.guest_id    				= ins_util.setNullParameter(p_request,"sel_parent");
				String ssss				= ins_util.setNullParameter(p_request, "sel_item");
				PreparedStatement pss = NGM.conn.prepareStatement("select item_id,item_title,unitprice  from m_food_items where item_id=?");
				pss.setString(1, ssss);
				ResultSet rss=pss.executeQuery();
				System.out.println("Quesry                "+pss);
				while(rss.next())
				{
					dddd= rss.getString("item_title");
					System.out.println("Inside the ddddd         "+dddd);
				}
				 //close
			    rss.close();
				pss.close();
				if(dddd.equals("Other Items"))
				{
					ins_food.item_details= ins_util.setNullParameter(p_request, "other");
					ins_food.unitprice   				= ins_util.setNullParameter(p_request, "unit_price");
					ins_food.no_of_unit   				= ins_util.setNullParameter(p_request,"no_unit");
					ins_food.total   					= ins_util.setNullParameter(p_request, "total_food");
					ins_food.foodtime   					= ins_util.setNullParameter(p_request, "food");
					
				}
				else
				{
					ins_food.item_details= dddd;
					ins_food.unitprice   				= ins_util.setNullParameter(p_request, "unit_price");
					ins_food.no_of_unit   				= ins_util.setNullParameter(p_request,"no_unit");
					ins_food.total   					= ins_util.setNullParameter(p_request, "total_food");
					ins_food.foodtime   					= ins_util.setNullParameter(p_request, "food");
				}
				if(!ins_food.insertDB())return false;
			return true;
	}
	
	public boolean deletelist(HttpServletRequest p_request) throws Exception{ 
		ins_food.seq_id=ins_util.setNullParameter(p_request, "seq_id");
		System.out.println("ins_food.seq_id       "+ins_food.seq_id);
		if(!ins_food.deleteDB())return false;
			return true;
		
}
	public String search(HttpServletRequest request)throws Exception{
		  
	 	String room_no = (String) request.getParameter("room_no");	 
		String name = (String)request.getParameter("name");	 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(f.name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!room_no.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" e.room_no like '"+room_no+"'";
					}
				}catch (NullPointerException x)	{}  
				
				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
}
//	public String hidvalue(HttpServletRequest request)throws Exception{
//		  
//	 	String guest_id = (String) request.getParameter("guest_id");	 
//		String guest ="" ;
//				String join = "";
//			 	
//				try{
//					if (!guest_id.equals("")){
//						if (!guest_id.equals("")) {
//							guest_id += join +" and";
//						}
//						guest_id += join +" guest_id like '"+guest_id+"'";
//					}
//				}catch (NullPointerException x)	{}
//				
//				
//				
//				 System.out.println("ccccccccccccccccccccccDDDDDDDDDDDDDDDD              "+guest);
//				 return guest;
//}
}
