package model;

import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;
import db.d_feedback;
import db.ngm;


public class m_feedback extends ngmodel {

	utility 		ins_util;
	d_feedback ins_feedback;
	public m_feedback(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_feedback= new d_feedback(NGM);

	}
	public boolean verifyFeedback(HttpServletRequest request) throws Exception {
		String mob = null;
		String msg = null;
		String reason = null;
		String templateid=null;
		String reasonOne=null;
		ins_feedback.req_id =   request.getParameter("reqid");
		ins_feedback.feedback_id =   request.getParameter("feedbackid");
		templateid=request.getParameter("templateid");
		System.out.println(request.getParameter("feedbackid"));
		reason = request.getParameter("remarks");
		reasonOne= request.getParameter("remarkOne");
		ins_feedback.replied_sms=reason;
		System.out.println(reasonOne);
		try {
			mob = ins_feedback.selectDB_mobDetails(ins_feedback.req_id ,ins_feedback.feedback_id);
		} catch (Exception e) {
			System.out.println("Some Exception Occured.. " + e);
		}

		if(mob!=null){
			try {
				if(!reasonOne.isEmpty()) {
					msg = reason+reasonOne+" -Odisha Home Dept";
					ins_feedback.replied_sms=msg;
					if(templateid.equals("1107164967721333473")) {
						templateid="1107165000905613799";
						sendVerificationCode(mob,msg,templateid);
					}else if(templateid.equals("1107164967740329102")) {
						templateid="1107165000919722835";
					}else {
						templateid="1107165009359372891";
						sendVerificationCode(mob,msg,templateid);
					}
					
				}else {
					if(!templateid.equals("1107164967740329102"))
					{
					msg = reason+" -Odisha Home Dept";
					sendVerificationCode(mob,msg,templateid);
					} 
				}
			} catch (Exception e) {
				System.out.println("Some Exception Occured.. " + e);
			}
		}
		if(!(ins_feedback.update_feedback_replied()))return false;
		return true;
	}
	
	public boolean sendVerificationCode(String mob,String msg,String templateid)throws Exception {	
		//ok
		String tepmlateId=templateid;
		NGM.ins_utility.SENDSMS(mob, msg, "NGM",tepmlateId);//this.ins_user.userid
		return true;
	}
	public String searchrequest(HttpServletRequest request)throws Exception{

		String startdate = (String) request.getParameter("startdate");	 
		String enddate = (String)request.getParameter("enddate");	 
		String category=(String)request.getParameter("category");
		Integer feedback=Integer.parseInt(request.getParameter("feedback"));
		
		common con = new common(NGM);
		startdate = con.display_date_calander(startdate);
		enddate=con.display_date_calander(enddate);
		String criteria ="" ;
		String join = "",categoryy="",feedbackk="";
		if(category.equals("checkin")) {
			categoryy="checkin_out";
		}else if(category.equals("keeproom")) {
			categoryy="keep_room";
		}else if(category.equals("clean")) {
			categoryy="cleanliness_hygiene";
		}else if(category.equals("food")) {
			categoryy="quality_food";
		}else if(category.equals("conduct")){
			categoryy="conduct_of_staff";
		}else {
			categoryy="overall";
		}
		try{
			if (!startdate.equals("")&& !startdate.equals("") ) {
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" feedback_system.replied_on between date('"+startdate+"') and date('"+enddate+"')";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!category.equals("") && ! feedback.equals(0)){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" "+categoryy+"="+feedback;
			}
		}catch (NullPointerException x)	{}
		
		
		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;

	}
	public String searchrequestfilter(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		common con = new common(NGM);		 
		String criteria ="" ;
		String join = ""; 		 
				try{
					if (!name.equals("")) {
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" upper(name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" reqid like '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
				
}
	public void VJ_search(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_moderator", "Validation fails", "search", NGM);
		}
	}
}
