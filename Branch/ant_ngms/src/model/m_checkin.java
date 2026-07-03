package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;
public class m_checkin extends ngmodel{
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
	public m_checkin(ngm p_ngm){
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
	public void VJ_checkinlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "checkinlist", NGM);
		}
	}
	public void VJ_checkinSlip(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "checkinSlip", NGM);
		}
	}
	public void VJ_viewCheckinSlip(String pgh_id,String ppage_no,String ptr_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_seq_id(ptr_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "viewCheckinSlip", NGM);
		}
	}
	public void VJ_checkoutlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "checkoutlist", NGM);
		}
	}
	public void VJ_searchSlip(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "searchSlip", NGM);
		}
	}
	public void VJ_search(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "search", NGM);
		}
	}
	public void VJ_searchcancel(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "searchcancel", NGM);
		}
	}
	public void VJ_searchchange(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "searchchange", NGM);
		}
	}
	public void VJ_viewlist(String pgh_id,String ppage_no,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "viewlist", NGM);
		}
	}
	public void VJ_viewlistout(String pgh_id,String ppage_no,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "viewlistout", NGM);
		}
	}
	public void VJ_cancellist(String pgh_id,String ppage_no,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "cancellist", NGM);
		}
	}
	public void VJ_canceloutlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "canceloutlist", NGM);
		}
	}
	public void VJ_checkincancel(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "checkincancel", NGM);
		}
	}
	public void VJ_checkoutcancel(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_checkin", "Validation fails", "checkoutcancel", NGM);
		}
	}


	public String search(HttpServletRequest request)throws Exception{

		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String pro_id=(String)request.getParameter("pro_id");
		String date=(String)request.getParameter("date");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_con.display_time_zonenew(date);
		}
		if(pro_id.equals("0")){
			pro_id = "";
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


		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;

	}
	public String searchSlip(HttpServletRequest request)throws Exception{

		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		//String pro_id=(String)request.getParameter("pro_id");
		String date=(String)request.getParameter("date");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_con.display_time_zonenew(date);
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
				criteria += join +" req_id like '"+req_id+"'";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!date.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				criteria += join +" date(arrival_time_date) = date('"+date+"')";  //"'"+date+"' between date(from_date) and date(to_date)";
			}
		}catch (NullPointerException x)	{}


		System.out.println("SLIP                "+criteria);
		return criteria;

	}
	public boolean cancelCreation(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "noofrows"));
		String t_gh_id  =ins_util.setNullParameter(p_request, "gh_id");
		String t_sel_block  =ins_util.setNullParameter(p_request, "sel_block");
		System.out.println("PREETH "+count);

		//for(int i=1;i<=count;i++)
		//{
		ins_check.req_id=ins_util.setNullParameter(p_request, "req_id");
		String hidvalue=ins_util.setNullParameter(p_request, "hidvalue");
		ins_check.guest_id = "'"+hidvalue+"'";
		ins_check.gh_id = t_gh_id;
		ins_check.sel_block = t_sel_block;
		if(!ins_check.cancelcan()){
			System.out.println("false");
			return false;
		}
		else{
			//if(ins_check.selectDB_Remove()){
			System.out.println("true");
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where req_id=? and guest_id in ("+ins_check.guest_id+") and gh_id=?");
			ps.setString(1, ins_check.req_id);
			//ps.setString(2, guest_id);
			ps.setString(2, t_gh_id);
			System.out.println("ps REM "+ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				if(!rs.getString("advance_amt").equals("0")){
					ins_balance.adv_receipt_id = rs.getString("adv_bill_id");
					ins_balance.status = "CAN";
					ins_balance.gh_id = t_gh_id;
					ins_balance.sel_block = t_sel_block;
					if(!ins_balance.updateDB_status())return false;
				}
			}
			//close
			rs.close();
			ps.close();
			//}
		}
		String allotid="",tr_id="";
		PreparedStatement pss=NGM.tr_conn.prepareStatement("select * from tr_checkin where gh_id=? and req_id='"+ins_check.req_id+"' and guest_id in ("+ins_check.guest_id+")");// and status!='CAN'
		pss.setString(1, t_gh_id);
		System.out.println("pss 2 Gopika "+pss);
		ResultSet rss = pss.executeQuery();
		while(rss.next()){
			ins_check_allot.tr_id = rss.getString("tr_id");
			ins_check_allot.status = "CAN";

			//if(!ins_check_allot.selectDB()) return false;
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where tr_id = ? and status!='CAN'");
			ps.setString(1,ins_check_allot.tr_id);
			System.out.println("pspspspsps Gopika "+ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ins_allotment.allot_id = rs.getString("allot_id");
				ins_allotment.status = "ALO";
				ins_allotment.gh_id = t_gh_id;
				ins_allotment.block_id = t_sel_block;
				if(!ins_allotment.update_db_status()) return false;
			}
			//close

			if(!ins_check_allot.updateDB_status_trid()) return false;

			rs.close();
			ps.close();
		}
		//close
		rss.close();
		pss.close();
		ins_gad.req_id=ins_check.req_id;
		ins_gad.gh_id = t_gh_id;
		ins_gad.block_id = t_sel_block;
		int total=ins_con.checking1(ins_gad.req_id);
		if(total==count)
		{
			System.out.println("ifffff ");
			if(!ins_gad.cancelalooo())return false;
		}
		else
		{
			int current = ins_con.checking2(ins_gad.req_id);
			if(current==count)
			{
				System.out.println("else ifffff ");
				if(!ins_gad.cancelalooo())return false;
			}
			else{
				System.out.println("else else ");
				if(!ins_gad.cancelalo())return false;
			}
		}
		System.out.println("aaaaaaaaaaaaaaaaa");
		// }
		return true;
	}

	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "noofrows"));
		//String t_gh_id = ins_util.setNullParameter(p_request, "gh_id");
		for(int i=1;i<=count;i++)
		{
			ins_check.roll_no=ins_util.setNullParameter(p_request, "roll"+i);
			ins_check.advance_amt=ins_util.setNullParameter(p_request, "amount"+i);
			ins_check.adults=ins_util.setNullParameter(p_request, "adults"+i);
			ins_check.arrival_time_date=ins_util.setNullParameter(p_request, "time"+i);
			ins_check.guest_id=ins_util.setNullParameter(p_request, "gusetid"+i);
			//ins_check_allot.allot_id=ins_util.setNullParameter(p_request, "sel_res_wl"+i);

			String t_res_wl = ins_util.setNullParameter(p_request, "sel_res_wl"+i);
			System.out.println("t_res_wl//////////////// "+t_res_wl);
			String t_res_details[] = t_res_wl.split("-");
			String t_allot_id= "";

			ins_check.no_of_children_below12=ins_util.setNullParameter(p_request, "children"+i);	
			ins_check.vc_id = ins_util.setNullParameter(p_request, "sel_vcid"+i);
			System.out.println("sel_vcidsel_vcidsel_vcid//////////////// "+ins_check.vc_id);
			ins_check.purpose = ins_util.setNullParameter(p_request, "purpose"+i);
			System.out.println("purposepurposepurposepurpose//////////////// "+ins_check.purpose);

			ins_check.req_id=ins_util.setNullParameter(p_request, "req_id");
			ins_check.gh_id=ins_util.setNullParameter(p_request, "gh_id");
			ins_check.sel_block=ins_util.setNullParameter(p_request, "sel_block");
			System.out.println("ghidddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd    "+ins_check.sel_block);
			//ins_allotment.allot_id=ins_check_allot.allot_id;
			ins_check.tr_id =  NGM.ins_utility.get_seqID(NGM.tr_conn, "tr_id").toString(); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "tr_id", Integer.parseInt(ins_check.tr_id));
			//ins_check_allot.tr_id=ins_check.tr_id;
			//ins_check.status ="CIN";
			ins_shiftmaster.gh_id = ins_check.gh_id;
			ins_shiftmaster.sel_block = ins_check.sel_block;
			ins_shiftmaster.status = "A";
			if(!ins_shiftmaster.selectDB_ACT()) return false;
			ins_check.shift_id = ins_shiftmaster.shift_id;
			ins_check.shiftuser_id = NGM.ins_sess.user_id;

			ins_check.adv_bill_id=  NGM.ins_utility.get_seqID(NGM.tr_conn, "advance_bill").toString(); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "advance_bill", Integer.parseInt(ins_check.adv_bill_id));

			if(!ins_check.insertDB_check()) return false;
			if(ins_check.selectDB()){
				////********************************************/////
				ins_gad.req_id=ins_check.req_id;
				ins_gad.gh_id=ins_check.gh_id;
				ins_gad.block_id=ins_check.sel_block;
				int total=ins_con.checking1(ins_gad.req_id);
				int current = ins_con.checking2(ins_gad.req_id);
				String t_gadstatus = "";
				if(total==(count+current))
				{
					if(!ins_gad.cancelaloo())return false;
					t_gadstatus = "CIN";
				}
				else
				{
					if(!ins_gad.cancelalo())return false;
					t_gadstatus = "PCIN";
				}
				////********************************************//// GOPIKA***
				ins_check_allot.tr_id=ins_check.tr_id;
				PreparedStatement ps=NGM.tr_conn.prepareStatement("select allot_id,type from tr_allotment where res_wl_id=? and res_wl=? and status!='CAN' and gh_id=?"); //CAN
				ps.setString(1, t_res_details[0]);
				ps.setString(2, t_res_details[1]);
				ps.setString(3, ins_check.gh_id);
				System.out.println("ps............... GOPIKA "+ps);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					t_allot_id = rs.getString("allot_id");
					ins_check_allot.allot_id = t_allot_id;
					//ins_allotment.allot_id = t_allot_id;
					if(!ins_check_allot.insertDB_check_allot())return false;

					ins_allotment.allot_id=t_allot_id;
					if(rs.getString("type").equals("R")){
						if(t_gadstatus.equals("CIN")){
							ins_allotment.status = "CIN";
						}else{
							ins_allotment.status = "ALO";
						}
					}else{
						ins_allotment.status = "CIN";
					}
					ins_allotment.gh_id = ins_check.gh_id;
					if(!ins_allotment.update_db_status()) return false;
				}
				//close
				rs.close();
				ps.close();
				ins_chk_req.req_id = ins_check.req_id;
				ins_chk_req.tr_id = ins_check.tr_id;
				ins_chk_req.status = "LIV";
				if(!ins_chk_req.insertDB()) return false;

			}
			if(!ins_check.advance_amt.equals("0")){

				ins_balance.bal_id=  NGM.ins_utility.get_seqID(NGM.tr_conn, "bal_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "bal_id", Integer.parseInt(ins_balance.bal_id));
				ins_balance.advance_credit=ins_util.setNullParameter(p_request, "amount"+i);
				ins_balance.date_of_transaction= ins_util.setNullParameter(p_request, "time"+i);
				ins_balance.adv_receipt_id= ins_check.adv_bill_id;
				ins_balance.mode_c_q = "CASH";
				ins_balance.shift_id = ins_shiftmaster.shift_id;
				ins_balance.shift_userid = NGM.ins_sess.user_id;
				ins_balance.gh_id =ins_check.gh_id;
				ins_balance.sel_block =ins_check.sel_block;
				ins_balance.status="CNF";
				if(!ins_balance.insertDB())return false;
			}
		}
		return true;
	}

	public String multiplechech(HttpServletRequest p_request) throws Exception{
		String multiple="";
		String select[] = p_request.getParameterValues("chck"); 
		if (select != null && select.length != 0) {
			System.out.println("You have selected: ");
			for (int i = 0; i < select.length; i++) {
				System.out.println(select[i]); 

				multiple=multiple+","+select[i];
				System.out.println("DELTEEEEEEEEEEEEEEEEEEEEEEEEEE       "+multiple);

				System.out.println("Split1 Element "+i+" :"+select[i]);

			}
		}	
		return multiple;
	}

	public boolean savecheckout(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "noofrows"));
		System.out.println("11111111 count "+count);
		String t_gh_id = ins_util.setNullParameter(p_request, "gh_id");
		String t_sel_block = ins_util.setNullParameter(p_request, "sel_block");
		for(int i=1;i<=count;i++)
		{
			ins_check.req_id=ins_util.setNullParameter(p_request, "req_id");
			ins_check.departure_time_date=ins_util.setNullParameter(p_request, "time"+i);//"time"+i
			System.out.println("22222222222 ins_check.departure_time_date "+ins_check.departure_time_date);
			System.out.println("22222222222 ins_check.guest_id "+ins_check.guest_id);
			String hidvalue=ins_util.setNullParameter(p_request, "hidvalue");
			ins_check.guest_id = "'"+hidvalue+"'";
			ins_check.gh_id = t_gh_id;
			ins_check.sel_block = t_sel_block;
			ins_gad.req_id=ins_util.setNullParameter(p_request, "req_id");
			ins_check.cancelcann();
			System.out.println("4444444444444");
			PreparedStatement pss1=NGM.tr_conn.prepareStatement("select * from tr_checkin where req_id='"+ins_gad.req_id+"' and status!='CAN' and gh_id=? and guest_id in ("+ins_check.guest_id+")");
			pss1.setString(1, t_gh_id);
			System.out.println("pss      hghdsagfhds   "+pss1);
			ResultSet rss1 = pss1.executeQuery();
			while(rss1.next()){
				ins_check_allot.tr_id = rss1.getString("tr_id");
				System.out.println("555555555555 ins_check_allot.tr_id "+ins_check_allot.tr_id);
				ins_check_allot.status = "COUT";
				if(!ins_check_allot.updateDB_status_trid()) return false;
				PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where tr_id = ? ");
				ps.setString(1,ins_check_allot.tr_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					ins_allotment.allot_id = rs.getString("allot_id");
					ins_check_allot.allot_id = ins_allotment.allot_id;
					ins_check_allot.status = "CIN";
					System.out.println("ins_allotment.allot_id "+ins_allotment.allot_id);
					System.out.println("ins_check_allot.select_check_LIV() LLLVV"+ins_check_allot.select_check_LIV());
					if(ins_check_allot.select_check_LIV()>=1){
						///
					}else{
						ins_allotment.status = "COUT";
						ins_allotment.checkout_date = ins_check.departure_time_date;
						ins_allotment.gh_id= t_gh_id;
						ins_allotment.block_id= t_sel_block;
						if(!ins_allotment.update_checkout_status()) return false;

						if(!ins_allotment.select_DB()) return false;
						else{
							if(ins_allotment.res_wl.equals("R")){
								ins_res.res_id = ins_allotment.res_wl_id;
								ins_res.checkout_date = ins_check.departure_time_date;
								ins_res.gh_id = t_gh_id;
								ins_res.block_id = t_sel_block;
								try {
									if(!ins_res.update_checkout_status()) return false;
								} catch (Exception e) {
									System.out.println("Some Exception Occured.." + e);
								}
							}
							else if(ins_allotment.res_wl.equals("W")){
								ins_wait.res_wid = ins_allotment.res_wl_id;
								ins_wait.status = "COUT";
								ins_wait.gh_id = t_gh_id;
								if(!ins_wait.update_status_alot()) return false;
							}
						}
					}
				}
				//close
				rs.close();
				ps.close();
			}
			//close
			rss1.close();
			pss1.close();
			ins_gad.req_id=ins_check.req_id;
			ins_gad.gh_id = t_gh_id;
			int total=ins_con.checking1(ins_gad.req_id);
			int current = ins_con.checking4(ins_gad.req_id);
			if(total==(count+current))
			{
				if(!ins_gad.cancelcinn())return false;

			}
			else
			{
				if(!ins_gad.cancelal())return false;
			}
		}//for count
		return true;
	}


	public boolean cancelCheckout(HttpServletRequest p_request) throws Exception{
		common con = new common(NGM);
		String hidvalue1=ins_util.setNullParameter(p_request, "guestlist");
		System.out.println("hidvalue1 %%% "+hidvalue1);
		String t_gh_id = ins_util.setNullParameter(p_request, "gh_id");
		String t_sel_block = ins_util.setNullParameter(p_request, "sel_block");
		String guestlist=ins_util.setNullParameter(p_request, "guestlist");

		String a[]=guestlist.split(",");
		String a1="";
		int ii=0;
		for(ii=0;ii<a.length;ii++){
			if(a1==""){
				a1= a1 +""+ a[ii];
			}
			else
				a1= a1 +"','"+ a[ii];

		}	

		int count = a.length;

		ins_check.guest_id = "'"+a1+"'";
		ins_check.departure_time_date = null;
		if(!ins_check.cancelCheckoutguest())return false;
		String allotid="",tr_id="";
		PreparedStatement pss=NGM.tr_conn.prepareStatement("select * from (select * from tr_checkin where guest_id in ("+ins_check.guest_id+") and status!='CAN') a join (select req_id,from_date,to_date from gad_request)b on a.req_id=b.req_id");
		System.out.println("pss O "+pss);
		ResultSet rss = pss.executeQuery();
		while(rss.next()){
			ins_check_allot.tr_id = rss.getString("tr_id");
			ins_check.req_id = rss.getString("req_id");
			ins_check_allot.status = "CIN";
			if(!ins_check_allot.updateDB_status_trid()) return false;
			String test = con.display_timestamp(rss.getString("to_date"));
			ins_allotment.checkout_date = con.display_timestamp(rss.getString("to_date"));
			System.out.println(ins_allotment.checkout_date);
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where tr_id = ? ");
			ps.setString(1,ins_check_allot.tr_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ins_allotment.allot_id = rs.getString("allot_id");
				ins_check_allot.allot_id = ins_allotment.allot_id;
				ins_check_allot.status = "COUT";

				if(ins_check_allot.select_check_LIV()>=1){

					///
				}else{
					ins_allotment.allot_id = rs.getString("allot_id");
					ins_allotment.status = "CIN";
					ins_allotment.gh_id = t_gh_id;
					ins_allotment.checkout_date = con.display_timestamp(rss.getString("to_date"));
					System.out.println(ins_allotment.checkout_date);
					if(!ins_allotment.update_checkout_status()) return false;
					
					if(!ins_allotment.select_DB()) return false;
					else{
						if(ins_allotment.res_wl.equals("R")){
							ins_res.res_id = ins_allotment.res_wl_id;
							ins_res.checkout_date = test;
							ins_res.gh_id = t_gh_id;
							ins_res.block_id = t_sel_block;
							try {
								if(!ins_res.update_checkout_status()) return false;
							} catch (Exception e) {
								System.out.println("Some Exception Occured.." + e);
							}
						}
						else if(ins_allotment.res_wl.equals("W")){
							ins_wait.res_wid = ins_allotment.res_wl_id;
							ins_wait.status = "CIN";
							ins_wait.gh_id= t_gh_id;
							if(!ins_wait.update_status_alot()) return false;
						}

					}

				}

				///////////////////////////////////////
			}
			//close
			rs.close();
			ps.close();
			ins_gad.req_id=ins_check.req_id;
			ins_gad.gh_id = t_gh_id;
			ins_gad.block_id = t_sel_block;
			int total=ins_con.checking1(ins_gad.req_id);
			if(total==count)
			{
				System.out.println("ifffff ");
				//if(!ins_gad.cancelaloo())return false;
				ins_gad.status="CIN";
				ins_gad.gh_id = t_gh_id;
				ins_gad.block_id = t_sel_block;
				if(!ins_gad.update_status())return false;
			}
			else
			{
				int current = ins_con.checking4(ins_gad.req_id);
				if(current==count)
				{
					ins_gad.status="CIN";
					ins_gad.gh_id = t_gh_id;
					ins_gad.block_id = t_sel_block;
					if(!ins_gad.update_status())return false;
				}
				else{
					System.out.println("else else ");
					if(!ins_gad.cancelal())return false;
				}
			}
			System.out.println("aaaaaaaaaaaaaaaaa");
			/* ins_gad.req_id=ins_check.req_id;
				 int count1=0;
					int total = ins_con.checking1(ins_gad.req_id);
					count1 = ins_con.checking2(ins_gad.req_id);
					System.out.println("total "+total);
					System.out.println("count1 "+count1);
					if((count1+1)==total)
					{
					System.out.println("1");
					ins_gad.status="CIN";
					ins_gad.gh_id = t_gh_id;
						if(!ins_gad.update_status())return false;
					}else{
					ins_gad.status="PCOUT";
					ins_gad.gh_id = t_gh_id;
					if(!ins_gad.update_status())return false;
					} */

			/* this for checkout cancel if(count1==total)
						{
						System.out.println("1");
						ins_gad.status="COUT";
							if(!ins_gad.update_status())return false;
						}else if(count1>1){
							System.out.println("2");
							if(!ins_gad.cancelal())return false;
						}
						else
						{
							int current_chkin = ins_con.checking2(ins_gad.req_id);
							if(current_chkin==total)
							{
								System.out.println("3");
								if(!ins_gad.cancelaloo())return false;
							}
							else if(current_chkin>1)
							{
								System.out.println("4");
								if(!ins_gad.cancelalo())return false;
							}
						} this for checkout cancel */
			//////
			/* this for checkin cancel ins_gad.req_id=ins_check.req_id;
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
						} this for checkin cancel  */
			/////
		}
		//close
		rss.close();
		pss.close();



		//} //for

		return true;
	}

	public boolean isShiftOpen(HttpServletRequest p_request) throws Exception{
		System.out.println("isShiftOpen ");
		d_shiftmaster ins_shiftm=new d_shiftmaster(NGM);
		ins_shiftm.gh_id=ins_util.setNullParameter(p_request, "gh_id");
		ins_shiftm.sel_block=ins_util.setNullParameter(p_request, "sel_block");
		System.out.println("isShiftOpen sel_block "+ins_shiftm.sel_block);
		ins_shiftm.status = "A";
		if(ins_shiftm.selectDB_active()) return true;
		else return false;
	}


	//Ajeesh
	public String check_checkout(HttpServletRequest p_request) throws Exception{	
		String req_id = (String) p_request.getParameter("req_id");
		String room_no="",rid="",r_id="",name="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select room_no from tr_allotment where req_id=?");
		ps.setString(1,req_id);
		System.out.println("5555555555555555555555555  1     "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			room_no=rs.getString("room_no");
		}
		//close
		rs.close();
		ps.close();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("select req_id from tr_allotment where room_no=?");
		pss.setString(1,room_no);
		System.out.println("5555555555555555555555555   2    "+pss);
		ResultSet rss = pss.executeQuery();
		while(rss.next()){
			rid=rss.getString("req_id");
			PreparedStatement psss=NGM.tr_conn.prepareStatement("select req_id from tr_checkin where req_id=? and status='CIN'");
			psss.setString(1,rid);
			System.out.println("5555555555555555555555555  3     "+psss);
			ResultSet rsss = psss.executeQuery();
			while(rsss.next()){
				r_id=rsss.getString("req_id");

			}
			//close
			rsss.close();
			psss.close();

		}
		//close
		rss.close();
		pss.close();
		//		PreparedStatement psss=NGM.tr_conn.prepareStatement("select req_id from tr_checkin where req_id=? and status='CIN'");
		//		psss.setString(1,rid);
		//		System.out.println("5555555555555555555555555       "+psss);
		//		ResultSet rsss = psss.executeQuery();
		//		while(rsss.next()){
		//			r_id=rsss.getString("req_id");
		//			
		//		}
		PreparedStatement pssss=NGM.tr_conn.prepareStatement("select name from guest_details where req_id=?");
		pssss.setString(1,r_id);
		System.out.println("5555555555555555555555555   4    "+pssss);
		ResultSet rssss = pssss.executeQuery();
		while(rssss.next()){
			name=rssss.getString("name");

		}
		//close
		rssss.close();
		pssss.close();
		String check=name+"("+r_id+")";
		System.out.println("5555555555555555555555555    5   "+check);
		return check;
	}

	public String getvcid(String rid)throws Exception{
		String vcid="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select vc_id from mod_req where req_id=?");
		ps.setString(1,rid);
		System.out.println("vccccccccccccc    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			vcid=rs.getString("vc_id");
		}
		//close
		rs.close();
		ps.close();
		return vcid;
	}

	public String getpurpose(String rid)throws Exception{
		String pp="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select purpose from gad_request where req_id=?");
		ps.setString(1,rid);
		System.out.println("purpssssssss   "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			pp=rs.getString("purpose");
		}
		//close
		rs.close();
		ps.close();
		return pp;
	}
	//end
}