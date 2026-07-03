package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;
public class m_billdetails extends ngmodel{
	utility 		ins_util;
	common ins_con;
	d_checkin ins_check;
	d_checkin_allot ins_check_allot;
	d_allotment ins_allotment;
	d_gadrequest ins_gad;
	d_reservation_maintable ins_res;
	d_reservation_waitinglist ins_wait;
	d_balancesheet ins_balance;
	d_checkin_reqid ins_chk_req;
	d_shiftmaster ins_shiftmaster;
	public m_billdetails(ngm p_ngm){
		NGM		= p_ngm;
		ins_con=new common(NGM);
		ins_check=new d_checkin(NGM);
		ins_check_allot=new d_checkin_allot(NGM);
		ins_allotment=new d_allotment(NGM);
		ins_gad=new d_gadrequest(NGM);
		ins_res=new d_reservation_maintable(NGM);
		ins_wait=new d_reservation_waitinglist(NGM);
		ins_balance=new d_balancesheet(NGM);
		ins_chk_req = new d_checkin_reqid(NGM);
		ins_shiftmaster = new d_shiftmaster(NGM);
		ins_util		= NGM.ins_utility;
	}
	public String search(HttpServletRequest request)throws Exception{
		  
	 	//String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String billno=(String)request.getParameter("billno");
		//String date=(String)request.getParameter("date");
		 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(d.name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!billno.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" c.bill_id like '"+billno+"'";
					}
				}catch (NullPointerException x)	{}
//				try{
//					if (!pro_id.equals("")){
//						if (!criteria.equals("")) {
//							criteria += join +" and";
//						}
//						criteria += join +" d.process_id like '"+pro_id+"'";
//					}
//				}catch (NullPointerException x)	{}
//				try{
//					if (!date.equals("")){
//						if (!criteria.equals("")) {
//							criteria += join +" and";
//						}
//						//criteria += join +" c.from_date = '"+date+"'"; // 
//						criteria += join +"'"+date+"' between date(from_date) and date(to_date)";
//					}
//				}catch (NullPointerException x)	{}
//				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
				
}

}