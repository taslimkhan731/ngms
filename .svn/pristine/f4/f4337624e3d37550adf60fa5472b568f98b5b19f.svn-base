package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;
public class m_extension_checkin extends ngmodel{
	utility 		ins_util;
	common ins_con;
	d_checkin ins_check;
	d_checkin_allot ins_check_allot;
	d_allotment ins_allotment;
	d_gadrequest ins_gad;
	d_reservation_maintable ins_res;
	d_reservation_waitinglist ins_wait;
	d_checkin_reqid ins_chk_req;
	d_shiftmaster ins_shiftmaster;

	public m_extension_checkin(ngm p_ngm){
		NGM		= p_ngm;
		ins_con=new common(NGM);
		ins_check=new d_checkin(NGM);
		ins_check_allot=new d_checkin_allot(NGM);
		ins_allotment=new d_allotment(NGM);
		ins_gad=new d_gadrequest(NGM);
		ins_res=new d_reservation_maintable(NGM);
		ins_wait=new d_reservation_waitinglist(NGM);
		ins_chk_req = new d_checkin_reqid(NGM);
		ins_shiftmaster = new d_shiftmaster(NGM);

		ins_util		= NGM.ins_utility;
	}
	
	public void VJ_extcheckinlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_extension_checkin", "Validation fails", "extcheckinlist", NGM);
		}
	}
	public void VJ_searchext(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_extension_checkin", "Validation fails", "searchext", NGM);
		}
	}
	public void VJ_selectfromcheckin(String pgh_id,String ppage_no,String preq_id,String pguest_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_guestid(pguest_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_extension_checkin", "Validation fails", "selectfromcheckin", NGM);
		}
	}
	public void VJ_viewlistext(String pgh_id,String ppage_no,String pnreq_id,String pnguest_id,String ppreq_id,String ppguest_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(pnreq_id)&&NGM.ins_dbval.val_guestid(pnguest_id)&&NGM.ins_dbval.val_reqid(ppreq_id)&&NGM.ins_dbval.val_guestid(ppguest_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_extension_checkin", "Validation fails", "viewlistext", NGM);
		}
	}
	
	
	
	
	public String search(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String pro_id=(String)request.getParameter("pro_id");
		String date=(String)request.getParameter("date");
		if(!date.equals("")){
		date = ins_con.display_time_zonenew(date);
		}
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(c.name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" c.req_id like '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!pro_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" d.process_id like '"+pro_id+"'";
					}
				}catch (NullPointerException x)	{}
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +" date('"+date+"') between date(from_date) and date(to_date)";
					}
				}catch (NullPointerException x)	{}
				
				
				 System.out.println("ccccccccccEXTcccccccccccc"+criteria);
				 return criteria;
				
}
	public boolean cancelCreation(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "noofrows"));
		for(int i=1;i<=count;i++)
		{
			ins_check.req_id=ins_util.setNullParameter(p_request, "req_id");
			String hidvalue=ins_util.setNullParameter(p_request, "hidvalue");
			ins_check.guest_id = "'"+hidvalue+"'";
			if(!ins_check.cancelcan())return false;
			String allotid="",tr_id="";
			PreparedStatement pss=NGM.tr_conn.prepareStatement("select * from tr_checkin where req_id='"+ins_check.req_id+"' and guest_id in ("+ins_check.guest_id+")");
			ResultSet rss = pss.executeQuery();
			while(rss.next()){
				ins_check_allot.tr_id = rss.getString("tr_id");
				ins_check_allot.status = "CAN";
				if(!ins_check_allot.updateDB_status_trid()) return false;
				
				if(!ins_check_allot.selectDB()) return false;
				ins_allotment.allot_id=ins_check_allot.allot_id;
				ins_allotment.status = "ALO";
				if(!ins_allotment.update_db_status()) return false;
				
			}
			 //close
		    rss.close();
			pss.close();
			ins_gad.req_id=ins_check.req_id;
			int total=ins_con.checking1(ins_gad.req_id);
			if(total==count)
				{
					if(!ins_gad.cancelalooo())return false;
				}
				else
				{
					int current = ins_con.checking2(ins_gad.req_id);
					if(current==count)
					{
						if(!ins_gad.cancelalooo())return false;
					}
					else{
						
						if(!ins_gad.cancelalo())return false;
					}
				}
			}
		
			ins_allotment.allot_id=ins_check_allot.allot_id;
			if(!ins_allotment.select_DB()) return false;
			else{
				if(ins_allotment.res_wl.equals("R")){
					ins_res.res_id = ins_allotment.res_wl_id;
					ins_res.status = "ALO";
					if(!ins_res.update_status_alot()) return false;
				}
				else if(ins_allotment.res_wl.equals("W")){
					ins_wait.res_wid = ins_allotment.res_wl_id;
					ins_wait.status = "ALO";
					if(!ins_wait.update_status_alot()) return false;
				}
			
			}
			return true;
		}
			
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		
			String new_tr_id="",t_p_roll="",t_p_arrival_time_date="",t_p_advance_amt="",t_p_advance_billno="",t_adults="",t_p_no_of_children_below12="",t_p_tr_id="",t_p_charges_realised="",t_p_bill_no="",t_p_remarks="",t_p_status="",t_p_shift_id="",t_p_shiftuser_id="";
			String t_vc_id="",t_purpose="";
			String t_gh_id =ins_util.setNullParameter(p_request, "gh_id");
			String t_sel_block =ins_util.setNullParameter(p_request, "sel_block");
			int count= Integer.parseInt(ins_util.setNullParameter(p_request, "noofrows"));
			String hidvalue=(String) p_request.getAttribute("hidvalue");
		 	for(int i=1;i<=count;i++){
			String resid_res=ins_util.setNullParameter(p_request, "sel_room_allotid"+i);
			System.out.println("resid_res******************************* "+resid_res);
			String t_res_details[] = resid_res.split("-");
			
			String newreq_id=p_request.getParameter("newreq_id");
			//String newguest_id=p_request.getParameter("newguest_id");
			
			String prev_reqid=p_request.getParameter("prev_reqid");
			//String prev_guestid=p_request.getParameter("prev_guestid");
			String prev_guestid=ins_util.setNullParameter(p_request, "gusetid"+i);
			String newguest_id=ins_util.setNullParameter(p_request, "prevguest"+i);
			System.out.println("SOUMYABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBb"+prev_guestid);
			System.out.println("SOUMYABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBb"+newguest_id);
			ins_check.req_id = prev_reqid;
			ins_check.guest_id = prev_guestid;
			ins_check.gh_id = t_gh_id;
			ins_check.sel_block = t_sel_block;
			if(!ins_check.selectDB_prev_ext()) return false;
			else{
				t_p_roll= ins_check.roll_no;
				t_p_arrival_time_date=ins_check.arrival_time_date;
				System.out.println("from table............. "+t_p_arrival_time_date);
				t_p_arrival_time_date = ins_con.display_date_newfull(t_p_arrival_time_date);
				System.out.println("to table............... "+t_p_arrival_time_date);
				t_p_advance_amt=ins_check.advance_amt;
				t_p_advance_billno=ins_check.adv_bill_id;
				t_p_no_of_children_below12=ins_check.no_of_children_below12;
				t_p_tr_id=ins_check.tr_id;
				t_adults = ins_check.adults;
				t_p_charges_realised=ins_check.charges_realised;
				t_p_bill_no=ins_check.bill_no;
				t_p_remarks=ins_check.remarks;
				t_p_status=ins_check.status;
				t_vc_id = ins_check.vc_id;
				t_purpose = ins_check.purpose;
				//t_p_shift_id=ins_check.shift_id;
				//t_p_shiftuser_id=ins_check.shiftuser_id;
				 ins_shiftmaster.gh_id = ins_check.gh_id;
				 ins_shiftmaster.sel_block = ins_check.sel_block;
				    ins_shiftmaster.status = "A";
				    if(!ins_shiftmaster.selectDB_ACT()) return false;
				    t_p_shift_id = ins_shiftmaster.shift_id;
				    t_p_shiftuser_id = NGM.ins_sess.user_id;
			}

			new_tr_id =  NGM.ins_utility.get_seqID(NGM.tr_conn, "tr_id").toString(); 
			ins_check.tr_id = new_tr_id;
		    NGM.ins_utility.updateseqID(NGM.tr_conn, "tr_id", Integer.parseInt(new_tr_id));
		    
		    ins_check.roll_no = t_p_roll;
		    ins_check.arrival_time_date = t_p_arrival_time_date;
		    ins_check.advance_amt = t_p_advance_amt;
		    ins_check.adv_bill_id = t_p_advance_billno;
		    ins_check.no_of_children_below12 = t_p_no_of_children_below12;
		    ins_check.adults = t_adults;
		    ins_check.charges_realised = t_p_charges_realised;
		    
		    ins_check.bill_no = t_p_bill_no;
		    ins_check.remarks = t_p_remarks;
		    ins_check.status = "CIN";
		    ins_check.shift_id = t_p_shift_id;
		    ins_check.shiftuser_id = t_p_shiftuser_id;
		    
		    ins_check.gh_id = t_gh_id;
		    ins_check.sel_block = t_sel_block;
		    ins_check.req_id = newreq_id;
		    ins_check.guest_id = newguest_id;
		    ins_check.parent_trid = t_p_tr_id;
		    ins_check.vc_id = t_vc_id;
		    ins_check.purpose = t_purpose;
		    
		    if(!ins_check.insertDB_check()) return false;
		    else{
		    
		    	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where tr_id=? and status='LIV'");
		  		ps.setString(1, t_p_tr_id);
		  		ResultSet rs=ps.executeQuery();
		  		while(rs.next()){
		  			ins_check_allot.allot_id = rs.getString("allot_id");
		  			ins_check_allot.tr_id = new_tr_id;
		  			if(!ins_check_allot.insertDB_check_allot()) return false;
		  			else{
		  				ins_check_allot.tr_id = t_p_tr_id;
		  				ins_check_allot.allot_id = rs.getString("allot_id");
		  				ins_check_allot.status = "PCAN";
		  				if(!ins_check_allot.updateDB_status()) return false;
		  			}
		  			
		  		}
		  		 //close
			    rs.close();
				ps.close();
		  		/*ins_check_allot.allot_id = new_allotid;
	  			ins_check_allot.tr_id = new_tr_id;
	  			if(!ins_check_allot.insertDB_check_allot()) return false;
	  			else{
	  				ins_allotment.allot_id=new_allotid;
	  				ins_allotment.status = "CIN";
	  				ins_allotment.gh_id = t_gh_id;
	  				if(!ins_allotment.update_db_status()) return false;
	  			}*/
		  		String new_allotid = "";
		  		 PreparedStatement psal=NGM.tr_conn.prepareStatement("select allot_id from tr_allotment where res_wl_id=? and res_wl=? and status!='CAN' and gh_id=?");
		  		psal.setString(1, t_res_details[0]);
		  		psal.setString(2, t_res_details[1]);
		  		psal.setString(3, t_gh_id);
					ResultSet rsal=psal.executeQuery();
					while(rsal.next()){
						new_allotid = rsal.getString("allot_id");
						ins_check_allot.allot_id = new_allotid;
						ins_check_allot.tr_id = new_tr_id;
						//ins_allotment.allot_id = t_allot_id;
						if(!ins_check_allot.insertDB_check_allot())return false;
						
						ins_allotment.allot_id=new_allotid;
						ins_allotment.status = "CIN";
						ins_allotment.gh_id = t_gh_id;
						ins_allotment.block_id = t_sel_block;
						if(!ins_allotment.update_db_status()) return false;
					}
					 //close
				    rsal.close();
					psal.close();
	  			
	  			ins_chk_req.req_id = prev_reqid;
				ins_chk_req.tr_id = t_p_tr_id;
				ins_chk_req.status = "CAN";
				if(!ins_chk_req.insertDB()) return false;
				
	  			ins_chk_req.req_id = prev_reqid;
				ins_chk_req.tr_id = new_tr_id;
				ins_chk_req.status = "LIV";
				if(!ins_chk_req.insertDB()) return false;
				
				ins_chk_req.req_id = newreq_id;
				ins_chk_req.tr_id = new_tr_id;
				ins_chk_req.status = "LIV";
				if(!ins_chk_req.insertDB()) return false;
		    	
		    }
		    
		    ins_check.req_id = prev_reqid;
		    ins_check.guest_id = prev_guestid;
		    ins_check.status = "PCAN";
		    if(!ins_check.updateDB_status()) return false;
		    
		    ins_gad.req_id = newreq_id;
		    ins_gad.status = "CIN";
		    if(!ins_gad.update_status()) return false;
		    
		    ins_gad.req_id = prev_reqid;
		    ins_gad.status = "EXT";
		    if(!ins_gad.update_status()) return false;
		 }    
		   
		return true;
	
}
}