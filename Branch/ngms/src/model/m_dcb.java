package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;
import db.*;

public class m_dcb extends ngmodel{
	public String p_dcb_id="";
	public String p_gh_id="";
	  utility 		ins_util;
	  d_guestothercharges ins_guest_other;
	  d_gadrequest ins_gad;
	  d_billmaster ins_bill;
	  d_billvsguest  ins_biluser;
	  d_shiftmaster ins_shift;
	  d_billvsguestvscharge ins_billusercharge;
	  d_checkin ins_chk;
	  d_dcb ins_dcb;
	  d_dcb_payment ins_dcbpay;
	  common ins_common;  
	public m_dcb(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guest_other	  = new d_guestothercharges(NGM);
		ins_gad = new d_gadrequest(NGM);
		ins_bill = new d_billmaster(NGM);
		ins_biluser = new d_billvsguest(NGM);
		ins_shift = new d_shiftmaster(NGM);
		ins_billusercharge = new d_billvsguestvscharge(NGM);
		ins_chk = new d_checkin(NGM);
		ins_dcb = new d_dcb(NGM);
		ins_dcbpay = new d_dcb_payment(NGM);
		ins_common = new common(NGM);
		}
		
	public void VJ_dcb(String pgh_id,String pdate) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_date(pdate)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dcb", "Validation fails", "dcb", NGM);
		}
	}
	public void VJ_newdcb(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dcb", "Validation fails", "newdcb", NGM);
		}
	}
	public void VJ_listdcb(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dcb", "Validation fails", "listdcb", NGM);
		}
	}
	public void VJ_showdcbpopup(String pgh_id,String pdcb_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_seqid(pdcb_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dcb", "Validation fails", "showdcbpopup", NGM);
		}
	}
	public void VJ_searchDCB(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dcb", "Validation fails", "searchDCB", NGM);
		}
	}
	public void VJ_showdcbPaymentpopup(String pgh_id,String ppage_no,String pdcb_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_seqid(pdcb_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dcb", "Validation fails", "showdcbPaymentpopup", NGM);
		}
	}
	
	public boolean isExistDCB(HttpServletRequest p_request) throws Exception{
		p_dcb_id="";
		String gh_id = p_request.getParameter("gh_id");
		String date = p_request.getParameter("date");
		p_gh_id=gh_id;
		ins_dcb.gh_id = gh_id;
		ins_dcb.date_trans = ins_common.display_time_zonenew(date);
		if(!ins_dcb.isDcbExist()){
			return false;
		}
		else{
			p_dcb_id = ins_dcb.dcb_id;
			return true;
		}
		
	}
	public boolean submitDCBold(HttpServletRequest p_request) throws Exception{
		String gh_id = p_request.getParameter("gh_id");
		String date = p_request.getParameter("date");
		String t_id="",t_date="",t_fromtable="",t_number="",t_tabledescr="";
		int gross_rent=0,gross_cetering=0,gross_news=0,gross_travel=0,gross_other=0,gross_tele=0,gross_tv=0,gross_total=0,gross_rec_amt=0,gross_due=0;

		String t_bill_id = "";
		boolean flag=false;
		
		  ins_dcb.dcb_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "dcb_id").toString(); 
		  NGM.ins_utility.updateseqID(NGM.tr_conn, "dcb_id", Integer.parseInt(ins_dcb.dcb_id));
		  ins_dcb.gh_id = gh_id;
		
		  p_gh_id = gh_id;
		  p_dcb_id = ins_dcb.dcb_id;
		  
		  ins_shift.gh_id = gh_id;
		  ins_shift.status = "A";
		  ins_shift.selectDB_ACT();
		  
		  ins_dcb.shift_id = ins_shift.shift_id;
		  ins_dcb.shift_userid = NGM.ins_sess.user_id;
		  
		/*PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(id) * from ((select bill_id as id,date,billno as number,'BI' as fromtable from tr_bill_master where status!='CAN' and date between (date(?)-1)+ time '12:00:00' and date(?)+ time '12:00:00') union (select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN' and date_of_transaction between (date(?)-1)+ time '12:00:00' and date(?)+ time '12:00:00')x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa");
		ps.setString(1, ins_common.display_time_zonenew(date));
		ps.setString(2, ins_common.display_time_zonenew(date));
		ps.setString(3, ins_common.display_time_zonenew(date));
		ps.setString(4, ins_common.display_time_zonenew(date));*/
		  //PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(id) * from ((select bill_id as id,date,billno as number,'BI' as fromtable from tr_bill_master where status!='CAN' and date(date) = date(?)) union (select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN' and date(date_of_transaction) = date(?))x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa");
			
		  PreparedStatement ps=NGM.tr_conn.prepareStatement("select  * from ((select bill_id as id,date,billno as number,'BI' as fromtable,'' as receipt from tr_bill_master where status='BILL' and date(date) = date(?)) union "+
					"(select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable,rec_id from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN'  "+
					"and date(date_of_transaction) = date(?))x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa order by date");
					
		  ps.setString(1, ins_common.display_time_zonenew(date));
		  ps.setString(2, ins_common.display_time_zonenew(date));

		System.out.println("Bill/Receipt ps "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		int i = 0; 
		while(rs.next())
		{
			
			t_id = rs.getString("id");
			t_date = rs.getString("date");
			t_fromtable = rs.getString("fromtable");
			t_number=rs.getString("number");
			//if(t_fromtable.equals("BI")){
				if(t_fromtable.equals("BI")){
					t_tabledescr="Bill";
				}else if(t_fromtable.equals("RE")){
					t_tabledescr="Receipt";
				}
				
				i++;
				t_bill_id = t_id;
				flag = true;
				
				ins_dcb.bill_receipt = t_fromtable;///
				ins_dcb.date_trans = t_date;///
				
				String t_roll_child = ins_common.getRollno(t_bill_id, gh_id);
				String[] t_r_c = t_roll_child.split(":");
			
				ins_dcb.roll_no = t_r_c[0];///
				ins_dcb.bill_no = t_number;///
				
				String t_names = ins_common.getNames(t_bill_id, gh_id);
				String[] count = t_names.split(",");
				
				ins_dcb.name = t_names;///
				ins_dcb.category = ins_common.getCategory(t_bill_id, gh_id);///
				ins_dcb.room_no = ins_common.getRoomno(t_bill_id, gh_id);///
				ins_dcb.adults = t_r_c[2];///
				ins_dcb.children = t_r_c[1];///
				
				Map<String,Integer> map_obj=ins_common.getCharges(t_bill_id, gh_id);
				int t_rent = map_obj.get("1");
				gross_rent = gross_rent + t_rent;
				
				int t_catering = map_obj.get("2");
				gross_cetering = gross_cetering + t_catering;
				
				int t_news = map_obj.get("3");
				gross_news = gross_news + t_news;
				
				int t_travel = map_obj.get("4");
				gross_travel = gross_travel + t_travel;
				
				int t_other = map_obj.get("7");
				gross_other = gross_other + t_other;
				
				int t_tele = map_obj.get("5");
				gross_tele = gross_tele + t_tele;
				
				int t_tv = map_obj.get("6");
				gross_tv = gross_tv + t_tv;
				
				int t_tot = map_obj.get("total");
				gross_total = gross_total + t_tot;
				
				ins_dcb.rent = ""+t_rent;///
				ins_dcb.catering = ""+t_catering;///
				ins_dcb.dailies = ""+t_news;///
				ins_dcb.transport = ""+t_travel;///
				ins_dcb.others = ""+t_other;///
				ins_dcb.telephone = ""+t_tele;///
				ins_dcb.television = ""+t_tv;///
				ins_dcb.total_bill = ""+t_tot;///
				
				Map<String,String> map_rec=ins_common.getReceipt(t_bill_id, gh_id);
				String t_receipt = map_rec.get("rec_id");
				if(t_receipt==null){
					t_receipt = "-";
				}
				String t_receipt_amt = map_rec.get("rec_amount");
				if(t_receipt_amt==null){
					t_receipt_amt = "0";
				}
				
				ins_dcb.rec_no = t_receipt;///
				ins_dcb.total_rec = t_receipt_amt;///
				
				gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
				int t_due = t_tot - Integer.parseInt(t_receipt_amt);
				gross_due = gross_due+t_due;
				
				ins_dcb.balance_amount = ""+t_due;///
				
				ins_dcb.reason = null;///
				ins_dcb.remarks = null;///
				if(!ins_dcb.insertDB()){
					return false;
				}
			
			
		}
		return true;
	}
	

	public boolean submitDCB(HttpServletRequest p_request) throws Exception{
		String gh_id = p_request.getParameter("gh_id");
		String date = p_request.getParameter("date");

		
		  ins_dcb.dcb_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "dcb_id").toString(); 
		  NGM.ins_utility.updateseqID(NGM.tr_conn, "dcb_id", Integer.parseInt(ins_dcb.dcb_id));
		  ins_dcb.gh_id = gh_id;
		
		  p_gh_id = gh_id;
		  p_dcb_id = ins_dcb.dcb_id;
		  
		  ins_shift.gh_id = gh_id;
		  ins_shift.status = "A";
		  ins_shift.selectDB_ACT();
		  
		  ins_dcb.shift_id = ins_shift.shift_id;
		  ins_dcb.shift_userid = NGM.ins_sess.user_id;
	
		  String t_bill_id = "",prev_rec_id="",temp_rec_id="";
			String g_rollno="",g_bill_no="",g_name="",g_category="",g_room_no="",g_adult="",g_children="",g_receipt="",g_receipt_amt="",g_due="",g_tabledescr="",g_date="";
			int g_rent=0,g_catering=0,g_news=0,g_travel=0,g_tele=0,g_tv=0,g_other=0,g_tot=0;
			
			String p_rollno="",p_bill_no="",p_name="",p_category="",p_room_no="",p_adult="",p_children="",p_receipt="",p_receipt_amt="",p_due="",p_tabledescr="",p_date="";
			int p_rent=0,p_catering=0,p_news=0,p_travel=0,p_tele=0,p_tv=0,p_other=0,p_tot=0;
							
			boolean flag=false;
			int gross_rent=0,gross_cetering=0,gross_news=0,gross_travel=0,gross_other=0,gross_tele=0,gross_tv=0,gross_total=0,gross_rec_amt=0,gross_due=0;
			String t_id="",t_date="",t_fromtable="",t_number="",t_tabledescr="";
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select  * from ((select bill_id as id,date,billno as number,'BI' as fromtable,'' as receipt from tr_bill_master where status='BILL' and date(date) = date(?)) union "+
					"(select bill_id,date_of_transaction,bill_id as number,'RE' as fromtable,rec_id from (select rec_id,date_of_transaction,rec_id as number,'RE' as fromtable from tr_receipt where status!='CAN'  "+
					"and date(date_of_transaction) = date(?))x join (select rec_id as b_recid,bill_id from tr_receiptvsbill)y on x.rec_id=y.b_recid) order by date)aa order by date");
					
			ps.setString(1, ins_common.display_time_zonenew(date));
			ps.setString(2, ins_common.display_time_zonenew(date));
		
			
			System.out.println("Bill/Receipt ps "+ps);
			ResultSet rs=ps.executeQuery();
			boolean l_status=false;
			int i = 0; 
			while(rs.next())
			{
				

				temp_rec_id = rs.getString("receipt");
				t_id = rs.getString("id");
				t_date = rs.getString("date");
				System.out.println("ttttttttttttttt.............................. "+t_date);
				t_fromtable = rs.getString("fromtable");
				t_number=rs.getString("number");
					if(t_fromtable.equals("BI")){
						t_tabledescr="Bill";
					}else if(t_fromtable.equals("RE")){
						t_tabledescr="Receipt";
					}
					
					i++;
					t_bill_id = t_id;
					flag = true;
				   if(prev_rec_id.equals(temp_rec_id)){
							
						String t_roll_child = ins_common.getRollno(t_bill_id, gh_id);
							System.out.println("t_roll_child   "+t_roll_child);
						String[] t_r_c = t_roll_child.split(":");
						p_rollno = p_rollno+","+t_r_c[0];
						p_bill_no = p_bill_no+","+t_number;
						
						String t_names = ins_common.getNames(t_bill_id, gh_id);
						String[] count = t_names.split(",");
						p_name = p_name+", "+t_names;
						p_category = p_category+","+ins_common.getCategory(t_bill_id, gh_id);
						p_room_no = p_room_no+","+ins_common.getRoomno(t_bill_id, gh_id);
						p_adult = p_adult+","+t_r_c[2];
						p_children = p_children+","+t_r_c[1];
							Map<String,Integer> map_obj=ins_common.getCharges(t_bill_id, gh_id);
							int t_rent = map_obj.get("1");
							gross_rent = gross_rent + t_rent;
							
							int t_catering = map_obj.get("2");
							gross_cetering = gross_cetering + t_catering;
							
							int t_news = map_obj.get("3");
							gross_news = gross_news + t_news;
							
							int t_travel = map_obj.get("4");
							gross_travel = gross_travel + t_travel;
							
							int t_other = map_obj.get("7");
							gross_other = gross_other + t_other;
							
							int t_tele = map_obj.get("5");
							gross_tele = gross_tele + t_tele;
							
							int t_tv = map_obj.get("6");
							gross_tv = gross_tv + t_tv;
							
							int t_tot = map_obj.get("total");
							gross_total = gross_total + t_tot;
							
							p_rent += t_rent;
							p_catering += t_catering;
							p_news += t_news;
							p_travel += t_travel;
							p_tele += t_tele;
							p_tv += t_tv;
							p_other += t_other;
							p_tot += t_tot;
							
							Map<String,String> map_rec=ins_common.getReceipt(t_bill_id, gh_id);
							String t_receipt = map_rec.get("rec_id");
							if(t_receipt==null){
								t_receipt = "-";
							}
							String t_receipt_amt = map_rec.get("rec_amount");
							if(t_receipt_amt==null){
								t_receipt_amt = "0";
							}
							
							gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
							int t_due = t_tot - Integer.parseInt(t_receipt_amt);
							gross_due = gross_due+t_due;
							
							p_receipt = t_receipt;
							p_receipt_amt = t_receipt_amt;
							//p_due = ""+t_due;
							p_due = ""+(p_tot - Integer.parseInt(p_receipt_amt));
							p_tabledescr = t_tabledescr;
							p_date = ins_common.display_timestamp(t_date);
							
					}else{ 
						
				String t_roll_child = ins_common.getRollno(t_bill_id, gh_id);
					System.out.println("t_roll_child   "+t_roll_child);
				String[] t_r_c = t_roll_child.split(":");
				g_rollno = t_r_c[0];
				g_bill_no = t_number;
				
				String t_names = ins_common.getNames(t_bill_id, gh_id);
				String[] count = t_names.split(",");
				g_name = t_names;
				g_category = ins_common.getCategory(t_bill_id, gh_id);
				g_room_no = ins_common.getRoomno(t_bill_id, gh_id);
				g_adult = t_r_c[2];
				g_children = t_r_c[1];
					Map<String,Integer> map_obj=ins_common.getCharges(t_bill_id, gh_id);
					int t_rent = map_obj.get("1");
					gross_rent = gross_rent + t_rent;
					
					int t_catering = map_obj.get("2");
					gross_cetering = gross_cetering + t_catering;
					
					int t_news = map_obj.get("3");
					gross_news = gross_news + t_news;
					
					int t_travel = map_obj.get("4");
					gross_travel = gross_travel + t_travel;
					
					int t_other = map_obj.get("7");
					gross_other = gross_other + t_other;
					
					int t_tele = map_obj.get("5");
					gross_tele = gross_tele + t_tele;
					
					int t_tv = map_obj.get("6");
					gross_tv = gross_tv + t_tv;
					
					int t_tot = map_obj.get("total");
					gross_total = gross_total + t_tot;
					
					g_rent = t_rent;
					g_catering = t_catering;
					g_news = t_news;
					g_travel = t_travel;
					g_tele = t_tele;
					g_tv = t_tv;
					g_other = t_other;
					g_tot = t_tot;
					Map<String,String> map_rec=ins_common.getReceipt(t_bill_id, gh_id);
					String t_receipt = map_rec.get("rec_id");
					if(t_receipt==null){
						t_receipt = "-";
					}
					String t_receipt_amt = map_rec.get("rec_amount");
					if(t_receipt_amt==null){
						t_receipt_amt = "0";
					}
					
					gross_rec_amt = gross_rec_amt + Integer.parseInt(t_receipt_amt);
					int t_due = t_tot - Integer.parseInt(t_receipt_amt);
					gross_due = gross_due+t_due;
					
					g_receipt = t_receipt;
					g_receipt_amt = t_receipt_amt;
					g_due = ""+(g_tot - Integer.parseInt(g_receipt_amt));
					
					g_tabledescr = t_tabledescr;
					g_date = ins_common.display_timestamp(t_date);
					if(!p_rollno.equals("")){

						ins_dcb.bill_receipt = t_fromtable;
						ins_dcb.date_trans = t_date;
						
						ins_dcb.roll_no = p_rollno;
						ins_dcb.bill_no = p_bill_no;
						
						ins_dcb.name = p_name;
						ins_dcb.category = p_category;
						ins_dcb.room_no = p_room_no;
						ins_dcb.adults = p_adult;
						ins_dcb.children = p_children;
						
						ins_dcb.rent = ""+p_rent;
						ins_dcb.catering = ""+p_catering;///
						ins_dcb.dailies = ""+p_news;///
						ins_dcb.transport = ""+p_travel;///
						ins_dcb.others = ""+p_other;///
						ins_dcb.telephone = ""+p_tele;///
						ins_dcb.television = ""+p_tv;///
						ins_dcb.total_bill = ""+p_tot;///
						
						ins_dcb.rec_no = p_receipt;///
						ins_dcb.total_rec = p_receipt_amt;///
						
						ins_dcb.balance_amount = ""+p_due;///
						
						ins_dcb.reason = null;///
						ins_dcb.remarks = null;///
						if(!ins_dcb.insertDB()){
							return false;
						}
					
					
					}
		p_rollno = g_rollno;
		p_bill_no = g_bill_no;
		p_name = g_name;
		p_category = g_category;
		p_room_no = g_room_no;
		p_adult = g_adult;
		p_children = g_children;
		p_rent = g_rent;
		p_catering = g_catering;
		p_news = g_news;
		p_travel = g_travel;
		p_tele = g_tele;
		p_tv = g_tv;
		p_other = g_other;
		p_tot = g_tot;
		p_receipt = g_receipt;
		p_receipt_amt = g_receipt_amt;
		p_due = g_due;
		p_tabledescr = g_tabledescr;
		p_date = g_date;
			prev_rec_id = temp_rec_id;
				}
			
				   l_status = true;
			}		
			
			if(l_status){
			ins_dcb.bill_receipt = t_fromtable;
			ins_dcb.date_trans = t_date;
			
			ins_dcb.roll_no = p_rollno;
			ins_dcb.bill_no = p_bill_no;
			
			ins_dcb.name = p_name;
			ins_dcb.category = p_category;
			ins_dcb.room_no = p_room_no;
			ins_dcb.adults = p_adult;
			ins_dcb.children = p_children;
			
			ins_dcb.rent = ""+p_rent;
			ins_dcb.catering = ""+p_catering;///
			ins_dcb.dailies = ""+p_news;///
			ins_dcb.transport = ""+p_travel;///
			ins_dcb.others = ""+p_other;///
			ins_dcb.telephone = ""+p_tele;///
			ins_dcb.television = ""+p_tv;///
			ins_dcb.total_bill = ""+p_tot;///
			
			ins_dcb.rec_no = p_receipt;///
			ins_dcb.total_rec = p_receipt_amt;///
			
			ins_dcb.balance_amount = ""+p_due;///
			
			ins_dcb.reason = null;///
			ins_dcb.remarks = null;///
			if(!ins_dcb.insertDB()){
				return false;
			}
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			boolean flag1 = false;
			String c_tabledescr = "",c_date="",c_billid="",c_conf_req_id="",c_receipt="",c_charge="",c_category="",c_name="",c_due="",c_receipt_amt="";
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("select  * from (((select conf_bill_id as id,entrydate,conf_bill_id as number,'BI' as fromtable,'' as receipt,conf_req_id,charge,additionalcharge from tr_conf_bill where status='A' and date(entrydate) = date(?)) union "+
					"(select number,entrydate,number,'RE' as fromtable,conf_receipt_id,conf_req_id,payed,'' from (select conf_receipt_id,entrydate,conf_bill_id as number,'RE' as fromtable,conf_req_id,payed from tr_conf_receipt "+
					"where status!='C'  and date(entrydate) = date(?))ll)) a join (select * from tr_conf_request) b on a.conf_req_id=b.conf_req_id) c join (select * from m_conf_hall_category)d on c.conf_cat_id=d.conf_cat_id");
					
			ps1.setString(1, ins_common.display_time_zonenew(date));
			ps1.setString(2, ins_common.display_time_zonenew(date));
			System.out.println("conf ps 1 model "+ps1);
			ResultSet rs1=ps1.executeQuery();
			int i1 = 0; 
			while(rs1.next())
			{
				i++;
				c_tabledescr = c_date=c_billid=c_conf_req_id=c_receipt=c_charge=c_category=c_name=c_due=c_receipt_amt="";
				c_tabledescr = rs1.getString("fromtable");
				if(c_tabledescr.equals("RE")){
					c_tabledescr = "Conference Receipt";
					c_receipt = rs1.getString("receipt");
					c_receipt_amt = rs1.getString("charge");
					c_charge = rs1.getString("charge");
					gross_rent = gross_rent + Integer.parseInt(c_charge);
					gross_total = gross_total + Integer.parseInt(c_charge);
					c_due = "0";
				}
				else{
					c_tabledescr = "Conference Bill";
					c_receipt = "-";
					c_charge = ""+(Integer.parseInt(rs1.getString("charge"))+Integer.parseInt(rs1.getString("additionalcharge")));
					c_receipt_amt = "0";
					gross_rent = gross_rent + Integer.parseInt(c_charge);
					gross_total = gross_total + Integer.parseInt(c_charge);
					c_due = c_charge;
				}
				c_date = rs1.getString("entrydate");
				c_billid = rs1.getString("number");
				c_conf_req_id = rs1.getString("conf_req_id");
				c_name = rs1.getString("name");
				c_category = rs1.getString("conf_cat_desc");
				
		
/*				<td><%=i %></td>
				<td><%=c_tabledescr %></td>
				<td><%=c_date %></td>
				
				<td><%=c_conf_req_id %></td>
				<td><%=c_billid %></td>
				
				<td><%=c_name %>&nbsp;</td>
				<td><%=c_category %>&nbsp;</td>
				<td><%="Conference Hall" %>&nbsp;</td>
				<td><%="-" %>&nbsp;</td>
				<td><%="-" %></td>
				
				<td><%=c_charge %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%="-" %></td>
				<td><%=c_charge %>&nbsp;</td>
				
				<td><%=c_receipt %></td>
				<td><%=c_receipt_amt %></td>
				
	<!-- 			<td></td>
	 -->			<td>&nbsp;<%=c_due %></td>
				<td>&nbsp;</td>
		 	 </tr>
		 	 <%
*/				ins_dcb.bill_receipt = c_tabledescr;
				ins_dcb.date_trans = c_date;
				
				ins_dcb.roll_no = c_conf_req_id;
				ins_dcb.bill_no = c_billid;
				
				ins_dcb.name = c_name;
				ins_dcb.category = c_category;
				ins_dcb.room_no = "Conference Hall";
				ins_dcb.adults = "-";
				ins_dcb.children = "-";
				
				ins_dcb.rent = ""+c_charge;
				ins_dcb.catering = "0";///
				ins_dcb.dailies = "0";///
				ins_dcb.transport = "0";///
				ins_dcb.others = "0";///
				ins_dcb.telephone = "0";///
				ins_dcb.television = "0";///
				ins_dcb.total_bill = ""+c_charge;///
				
				ins_dcb.rec_no = c_receipt;///
				ins_dcb.total_rec = c_receipt_amt;///
				
				ins_dcb.balance_amount = ""+c_due;///
				
				ins_dcb.reason = null;///
				ins_dcb.remarks = null;///
				if(!ins_dcb.insertDB()){
					return false;
				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			boolean flagm = false;
			String m_tabledescr="",m_receipt="",m_receipt_amt="",m_charge="",m_date="",m_billid="",m_name="",m_dcb="",m_roll="",m_due="";
			int gross_tot=0,gross1=0,gross2=0,gross3=0,gross4=0,gross5=0,gross6=0,gross7=0;
			PreparedStatement psm=NGM.tr_conn.prepareStatement("select  * from ((select misc_bill_id as bill_id,misc_bill_date,misc_rollno,'BI' as fromtable,'' as receipt_id,amount,misc_id,dcb_type_id from tr_misc_billing where "+
					 		" status='BILL' and date(misc_bill_date) = date(?) and gh_id=?) union (select misc_bill_id,misc_rec_date,misc_rollno as number,'RE' as fromtable,misc_rec_id,amount,misc_id,dcb_type_id from  "+
							" ((select misc_rec_id,misc_rec_date,'RE' as fromtable,amount,misc_bill_id as mid from tr_misc_receipt where status='REC' and date(misc_rec_date) = date(?) and gh_id=?) a join "+
							 " (select misc_id,dcb_type_id,misc_bill_id,misc_rollno from tr_misc_billing) b on a.mid=b.misc_bill_id)ll)) hhh");
					
					//"select  * from ((select conf_bill_id as id,entrydate,conf_bill_id as number,'BI' as fromtable,'' as receipt,conf_req_id from tr_conf_bill where status='A' and date(entrydate) = date(?)) union "+
					//"(select number,entrydate,number,'RE' as fromtable,conf_receipt_id,conf_req_id from (select conf_receipt_id,entrydate,conf_bill_id as number,'RE' as fromtable,conf_req_id from tr_conf_receipt "+
					//"where status!='C'  and date(entrydate) = date(?))ll)) kk order by entrydate");
					
			psm.setString(1, ins_common.display_time_zonenew(date));
			psm.setString(2, gh_id);
			psm.setString(3, ins_common.display_time_zonenew(date));
			psm.setString(4, gh_id);
			System.out.println("misc ps m "+psm);
			ResultSet rsm=psm.executeQuery();
			while(rsm.next())
			{
				i++;
				//c_tabledescr = c_date=c_billid=c_conf_req_id=c_receipt=c_charge=c_category=c_name=c_due=c_receipt_amt=c_additionalcharge="";
				m_tabledescr = rsm.getString("fromtable");
				if(m_tabledescr.equals("RE")){
					m_tabledescr = "Miscellaneous Receipt";
					m_receipt = rsm.getString("receipt_id");
					m_receipt_amt = rsm.getString("amount");
					m_charge = rsm.getString("amount");
					gross_total = gross_total + Integer.parseInt(m_charge);
					//gross_total = gross_total + Integer.parseInt(c_charge) + Integer.parseInt(c_additionalcharge);
					m_due = "0";
				}
				else{
					m_tabledescr = "Miscellaneous Bill";
					m_receipt = "-";
					m_charge = rsm.getString("amount");
					m_receipt_amt = "0";
					gross_total = gross_total + Integer.parseInt(m_charge);
					m_due = ""+m_charge;
					
				}
				m_date = rsm.getString("misc_bill_date");
				m_billid = rsm.getString("bill_id");
				m_name = rsm.getString("misc_id");
				m_dcb = rsm.getString("dcb_type_id");
				m_roll = rsm.getString("misc_rollno");
				
				String m_title = "";
				PreparedStatement name=NGM.tr_conn.prepareStatement("select misc_title from m_misc_bill_types where misc_id=?");
				name.setString(1, m_name);
				ResultSet na=name.executeQuery();
				while(na.next())
				{
					m_title = na.getString("misc_title");
				}
			
			/*<td><%=i %></td>
			<td><%=c_tabledescr %></td>
			<td><%=c_date %></td>
			
			<td><%=c_conf_req_id %></td>
			<td><%=c_billid %></td>
			
			<td><%=c_name %>&nbsp;</td>
			<td><%=c_category %>&nbsp;</td>
			<td><%="Conference Hall" %>&nbsp;</td>
			<td><%="-" %>&nbsp;</td>
			<td><%="-" %></td>
			
			<td><%=c_charge %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%="-" %></td>
			<td><%=c_charge %>&nbsp;</td>
			
			<td><%=c_receipt %></td>
			<td><%=c_receipt_amt %></td>
			
			<!-- 			<td></td>
			-->			<td>&nbsp;<%=c_due %></td>
			<td>&nbsp;</td>
			</tr>
			<%*/
			ins_dcb.bill_receipt = m_tabledescr;
			ins_dcb.date_trans = m_date;
			
			ins_dcb.roll_no = m_roll;
			ins_dcb.bill_no = m_billid;
			
			ins_dcb.name = m_title;
			ins_dcb.category = "-";
			ins_dcb.room_no = "-";
			ins_dcb.adults = "-";
			ins_dcb.children = "-";
			
			
			if(m_dcb.equals("1")){//Rent Charge
				 gross1=gross1+Integer.parseInt(m_charge);
				 
				 	ins_dcb.rent = ""+m_charge;
					ins_dcb.catering = "0";
					ins_dcb.dailies = "0";
					ins_dcb.transport = "0";
					ins_dcb.others = "0";
					ins_dcb.telephone = "0";
					ins_dcb.television = "0";
			
			} else if(m_dcb.equals("2")){//Catering
				 gross2=gross2+Integer.parseInt(m_charge);
				    ins_dcb.rent = "0";
					ins_dcb.catering = ""+m_charge;
					ins_dcb.dailies = "0";
					ins_dcb.transport = "0";
					ins_dcb.others = "0";
					ins_dcb.telephone = "0";
					ins_dcb.television = "0";

			} else if(m_dcb.equals("3")){//Dailies
				 gross3=gross3+Integer.parseInt(m_charge);
				    ins_dcb.rent = "0";
					ins_dcb.catering = "0";
					ins_dcb.dailies = ""+m_charge;
					ins_dcb.transport = "0";
					ins_dcb.others = "0";
					ins_dcb.telephone = "0";
					ins_dcb.television = "0";

			}else if(m_dcb.equals("4")){//Transport
				 gross4=gross4+Integer.parseInt(m_charge);
				    ins_dcb.rent = "0";
					ins_dcb.catering = "0";
					ins_dcb.dailies = "0";
					ins_dcb.transport = ""+m_charge;
					ins_dcb.others = "0";
					ins_dcb.telephone = "0";
					ins_dcb.television = "0";

			} else if(m_dcb.equals("5")){//Telephone
					 gross5=gross5+Integer.parseInt(m_charge);
					    ins_dcb.rent = "0";
						ins_dcb.catering = "0";
						ins_dcb.dailies = "0";
						ins_dcb.transport = "0";
						ins_dcb.others = "0";
						ins_dcb.telephone = ""+m_charge;
						ins_dcb.television = "0";

			} else if(m_dcb.equals("6")){//Television
				 gross6=gross6+Integer.parseInt(m_charge);
				    ins_dcb.rent = "0";
					ins_dcb.catering = "0";
					ins_dcb.dailies = "0";
					ins_dcb.transport = "0";
					ins_dcb.others = "0";
					ins_dcb.telephone = "0";
					ins_dcb.television = ""+m_charge;

			} else if(m_dcb.equals("7")){//Other Charges
				 gross7=gross7+Integer.parseInt(m_charge);
				    ins_dcb.rent = "0";
					ins_dcb.catering = "0";
					ins_dcb.dailies = "0";
					ins_dcb.transport = "0";
					ins_dcb.others = ""+m_charge;
					ins_dcb.telephone = "0";
					ins_dcb.television = "0";
			System.out.println("gross7 "+gross7);

			}
			
			
			ins_dcb.total_bill = ""+m_charge;///
			
			ins_dcb.rec_no = m_receipt;///
			ins_dcb.total_rec = m_receipt_amt;///
			
			ins_dcb.balance_amount = ""+m_due;///
			
			ins_dcb.reason = null;///
			ins_dcb.remarks = null;///
			if(!ins_dcb.insertDB()){
			return false;
			}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		return true;
	}
	
	public String searchDCB(HttpServletRequest request)throws Exception{
		  
	 	String dcb_id = (String) request.getParameter("dcb_id");	 
		String shift_id = (String)request.getParameter("shift_id");	 
		String shift_user = (String)request.getParameter("shift_user");	 
		String date=(String)request.getParameter("date");
		if(!date.equals("")){
			date = ins_common.display_time_zonenew(date);
		} 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!dcb_id.equals("")) {
						criteria += join +" dcb_id = '"+dcb_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!shift_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" shift_id = '"+shift_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!shift_user.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" shift_userid = '"+shift_user+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}//date between (date(?)-1)+ time '12:00:00' and date(?)+ time '12:00:00')
						criteria += join +" date::date = '"+date+"'";
						//criteria += join +" date between (date('"+date+"')-1)+ time '12:00:00' and date('"+date+"')+ time '12:00:00'";
					}
				}catch (NullPointerException x)	{} 
				
				
				 System.out.println("DCB SEARCH               "+criteria);
				 return criteria;
				
}
	
	public boolean submitDCBPayment(HttpServletRequest p_request) throws Exception{
		String gh_id = p_request.getParameter("gh_id");
		String sel_block = p_request.getParameter("sel_block");
		String dcb_id = p_request.getParameter("dcb_id");
		String shift_id = p_request.getParameter("shift_id");
		String shift_userid = p_request.getParameter("shift_userid");
		String receiptSum = p_request.getParameter("receiptSum");
		
		ins_shift.gh_id = gh_id;
		ins_shift.sel_block = sel_block;
		ins_shift.status = "A";
		if(!ins_shift.selectDB_ACT()){
			return false;
		}
		
		ins_dcbpay.payment_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "payment_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "payment_id", Integer.parseInt(ins_dcbpay.payment_id));
		
		ins_dcbpay.dcb_id = dcb_id;
		ins_dcbpay.shift_id = ins_shift.shift_id;
		ins_dcbpay.shiftuser_id = NGM.ins_sess.user_id;
		ins_dcbpay.total = receiptSum;
		ins_dcbpay.gh_id = gh_id;
		ins_dcbpay.sel_block = sel_block;
		if(!ins_dcbpay.insertDB()){
			return false;
		}
		
		return true;
		
	}
		
}






