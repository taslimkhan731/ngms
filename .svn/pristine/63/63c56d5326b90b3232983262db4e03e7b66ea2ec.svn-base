package model;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;

public class m_confirmationorder extends ngmodel{
	utility 		ins_util;
	d_mod_req_room ins_mod;
	d_mod_req ins_req;
	d_gadrequest ins_gadrequest;
	d_guestdetails ins_guestdetails;
	d_vehicle ins_vehicle;
	
	d_quota_block ins_quotablk;
	d_reservation_maintable ins_res;
	d_reservation_waitinglist ins_wl;
	d_process_table ins_process;
	d_repair_block ins_repair;
	common ins_common;
	
	public m_confirmationorder(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_mod		= new d_mod_req_room(NGM);
		ins_req =new d_mod_req(NGM);
		ins_gadrequest=new d_gadrequest(NGM);
		ins_guestdetails=new d_guestdetails(NGM);
		ins_vehicle = new d_vehicle(NGM);
		
		ins_quotablk = new d_quota_block(NGM);
		ins_res = new d_reservation_maintable(NGM);
		ins_wl = new d_reservation_waitinglist(NGM);
		ins_process = new d_process_table(NGM);
		ins_repair = new d_repair_block(NGM);
		ins_common = new common(NGM);
		}
		
	
	public String searchrequestlistGO(HttpServletRequest request)throws Exception{
		common con = new common(NGM);  
		String govno = (String)request.getParameter("process");	 
 		System.out.println("process id@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      "+govno);
		String rno=(String)request.getParameter("office");
		System.out.println("rno   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+rno);
		String date=(String)request.getParameter("date");
		System.out.println("date @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+date);
		
		 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!govno.equals("0")) {
						criteria += join +" process_id like '"+govno+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!rno.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" officeorderno like '"+rno+"'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						//criteria += join +"'"+date+"' between date(created_date) and date(created_date)";
						criteria += join +" created_date::date = to_timestamp('"+date+"','dd-mm-yyyy HH24:MI:SS')::date ";

						//criteria += join +" created_date= '"+date+"'";
					}
				}catch (NullPointerException x)	{}
				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
}		
	
	
	
}


