package model;


import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;

import db.ngm;

public class m_dailyguest extends ngmodel{

	public String p_gh_id="";
	  utility 		ins_util;
	  common ins_common;  
	public m_dailyguest(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_common = new common(NGM);
		}
		

	public void VJ_showguestlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dailyguest", "Validation fails", "dailyguest", NGM);
		}
	}
	public void VJ_searchShow(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dailyguest", "Validation fails", "searchShow", NGM);
		}
	}
	

	
	public String searchDailyguest(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" req_id = '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				
								
				 System.out.println("CANCEL BILL LIST           "+criteria);
				 return criteria;
				
}
	
	
	
		
}






