package model;


import java.util.ArrayList;

import common.common;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.utility;
import db.*;

public class m_receipt extends ngmodel{
	public String p_rec_id="";
	public String p_gh_id="",mode="";
	utility 		ins_util;
	d_guestothercharges ins_guest_other;
	d_gadrequest ins_gad;
	d_billmaster ins_bill;
	d_billvsguest  ins_biluser;
	d_receipt ins_receipt;
	d_receiptvsbill ins_receiptbill;
	d_shiftmaster ins_shiftmaster;
	d_balancesheet ins_balance;
	d_dues ins_due;
	common ins_common;
	public m_receipt(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guest_other	  = new d_guestothercharges(NGM);
		ins_gad = new d_gadrequest(NGM);
		ins_bill = new d_billmaster(NGM);
		ins_biluser = new d_billvsguest(NGM);
		ins_receipt=new d_receipt(NGM);
		ins_receiptbill=new d_receiptvsbill(NGM);
		ins_shiftmaster=new d_shiftmaster(NGM);
		ins_balance=new d_balancesheet(NGM);
		ins_due = new d_dues(NGM);
		ins_common = new common(NGM);
	}

	public void VJ_receiptprint(String pgh_id,String prec_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_rec_id(prec_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "receiptprint", NGM);
		}
	}
	public void VJ_addreceipt(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "addreceipt", NGM);
		}
	}
	public void VJ_listreceipt(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "listreceipt", NGM);
		}
	}
	public void VJ_addguestreceipt(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "addguestreceipt", NGM);
		}
	}
	public void VJ_listguestreceipt(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "listguestreceipt", NGM);
		}
	}
	public void VJ_viewlistreceipt(String pgh_id,String pbill_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_bill_id(pbill_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "viewlistreceipt", NGM);
		}
	}
	public void VJ_searchShowBill(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "searchShowBill", NGM);
		}
	}
	public void VJ_printview(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "printview", NGM);
		}
	}
	public void VJ_printviewlist(String pgh_id,String prec_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_rec_id(prec_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "printviewlist", NGM);
		}
	}
	public void VJ_submitreceipt(String preq_id,String ptotal) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_amount(ptotal)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "submitreceipt", NGM);
		}
	}
	public void VJ_searchShowRec(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "searchShowRec", NGM);
		}
	}
	public void VJ_makereceiptbilling(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_receipt", "Validation fails", "makereceiptbilling", NGM);
		}
	}


	public String searchReceipt(HttpServletRequest request)throws Exception{

		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
		String rec_no=(String)request.getParameter("rec_no");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_common.display_time_zonenew(date);
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

		try{
			if (!rec_no.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" rec_id = '"+rec_no+"'";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!date.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				criteria += join +" date('"+date+"') between date(arrival_time_date) and date(departure_time_date)";
				//" date(arrival_time_date) = date('"+date+"')";  //"'"+date+"' between date(from_date) and date(to_date)";
			}
		}catch (NullPointerException x)	{} 


		System.out.println("CANCEL rec_no LIST           "+criteria);
		return criteria;

	}

	public boolean submitReceiptCreation(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "countforsubmit"));
		String text_ruppees = ins_util.setNullParameter(p_request, "text_ruppees");
		System.out.println("text_ruppees **** "+text_ruppees);
		//int count= Integer.parseInt(ins_util.setNullParameter(p_request, "countbill"));

		System.out.println("rentcalc saveCreation"+count);
		String gh_id = p_request.getParameter("gh_id");
		String sel_block = p_request.getParameter("sel_block");
		String req_id = p_request.getParameter("req_id");
		String flag = p_request.getParameter("flag");
		String ss = p_request.getParameter("see");
		String no = p_request.getParameter("no");

		System.out.println("ss     "+ss);

		ins_receipt.rec_id= NGM.ins_utility.get_seqID(NGM.tr_conn, "rec_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "rec_id", Integer.parseInt(ins_receipt.rec_id));
		p_rec_id = ins_receipt.rec_id;
		p_gh_id = gh_id;
		mode = ss;
		ins_receipt.total_amount=ins_util.setNullParameter(p_request, "total");
		ins_receipt.amount=text_ruppees;
		System.out.println("ins_receipt.total_amount "+ins_receipt.total_amount);
		System.out.println("ins_receipt.amount "+ins_receipt.amount);
		if(Float.parseFloat(ins_receipt.total_amount)==Float.parseFloat(ins_receipt.amount)){
			ins_receipt.full_partial = "F";
		}else{
			ins_receipt.full_partial = "P";
			ins_due.due_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "due_id").toString(); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "due_id", Integer.parseInt(ins_due.due_id));
			ins_due.rec_id = ins_receipt.rec_id;
			if(flag.equals("DUES")){
				ins_due.status = "DUE";
			}else {
				try {
					if(mode != null) {
						if(!mode.isEmpty()) {
							if(mode.equalsIgnoreCase("ONLINE")) {
								ins_due.status = "PEND";
							}else {
								ins_due.status = "DUE";
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				if(mode != null) {
					if(!mode.isEmpty()) {
						if(mode.equalsIgnoreCase("ONLINE")) {
							ins_due.balance = ""+ ins_receipt.total_amount;
						}else {
							float balance = Float.parseFloat(ins_receipt.total_amount) - Float.parseFloat(ins_receipt.amount);
							ins_due.balance = ""+ balance;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			ins_due.block_id = sel_block;
			ins_due.insertDB();
		}
		ins_receipt.mode = ss;
		//	    ins_balance.receipt_credit=ins_util.setNullParameter(p_request, "total");
		ins_balance.receipt_credit=text_ruppees;

		try {
			if(mode != null) {
				if(!mode.isEmpty()) {
					if(mode.equalsIgnoreCase("ONLINE")) {
						ins_receipt.status= "PEND";
						ins_balance.status="PEND";
					}else {
						ins_receipt.status= "CNF";
						ins_balance.status="CNF";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ins_balance.receipt_id=ins_receipt.rec_id;
		ins_balance.mode_c_q=ss;
		//	    System.out.println("mmmm   "+ins_receipt.mode);
		ins_receipt.user_id=NGM.ins_sess.user_id;
		ins_shiftmaster.gh_id=gh_id;
		ins_shiftmaster.sel_block=sel_block;
		ins_shiftmaster.status="A";
		if(!ins_shiftmaster.selectDB_ACT())return false;
		ins_receipt.shift_id=ins_shiftmaster.shift_id;
		ins_receipt.gh_id = gh_id;
		ins_receipt.sel_block = sel_block;
		try {
			if(no != null) {
				if(!no.isEmpty()) {
					ins_receipt.receipt_debit = no;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("shift isd       "+ins_receipt.shift_id);

		if(!ins_receipt.insertDB()) return false;

		if(flag.equals("DUES")){
			HttpSession session  = p_request.getSession();
			ins_due.due_id = p_request.getParameter("due_id");
			session.setAttribute("dueId", ins_due.due_id);
			ins_due.status = "PAY";
			if(ins_receipt.full_partial != null){
				if(!ins_receipt.full_partial.isEmpty()){
					if(ins_receipt.full_partial.equalsIgnoreCase("F")){
						try {
							if(mode != null) {
								if(!mode.isEmpty()) {
									if(mode.equalsIgnoreCase("ONLINE")) {
										ins_due.status = "DUE";
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			ins_due.updateDBstatus();
		}

		ins_balance.bal_id=  NGM.ins_utility.get_seqID(NGM.tr_conn, "bal_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "bal_id", Integer.parseInt(ins_balance.bal_id));
		ins_balance.shift_id = ins_shiftmaster.shift_id;
		ins_balance.shift_userid = NGM.ins_sess.user_id;
		ins_balance.gh_id = gh_id;
		ins_balance.sel_block = sel_block;
		if(!ins_balance.insertDB()) return false;
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiCOUNT  "+count);
		for(int i=1;i<=count;i++)
		{
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii  "+i);
			//String bill_id = p_request.getParameter("bill"+i);
			ins_receiptbill.bill_id =ins_util.setNullParameter(p_request, "bill"+i);
			System.out.println("BILL ID   "+ins_receiptbill.bill_id);
			ins_receiptbill.rec_id=ins_receipt.rec_id;

			if(!ins_receiptbill.insertDB())return false;
			ins_bill.bill_id=ins_util.setNullParameter(p_request, "bill"+i);
			ins_bill.status="RECT";
			try {
				if(mode != null) {
					if(!mode.isEmpty()) {
						if(!mode.equalsIgnoreCase("ONLINE")) {
							if(!ins_bill.updaterectDB())return false;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("BILL ID   "+ins_bill.bill_id);	
		return true;
	}

	public String searchBill(HttpServletRequest request)throws Exception{
		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
		String bill_no=(String)request.getParameter("bill_no");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_common.display_time_zonenew(date);
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

		try{
			if (!bill_no.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" billno = '"+bill_no+"'";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!date.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				criteria += join +" date('"+date+"') between date(arrival_time_date) and date(departure_time_date)";
				//" date(arrival_time_date) = date('"+date+"')";  //"'"+date+"' between date(from_date) and date(to_date)";
			}
		}catch (NullPointerException x)	{} 


		System.out.println("FOR RECEIPT BILL LIST           "+criteria);
		return criteria;

	}

}	

//	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
//		System.out.println("rentcalc saveCreation");
//		ins_guest_other.seq_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_other_id").toString(); 
//	    NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_other_id", Integer.parseInt(ins_guest_other.seq_id));
//		ins_guest_other.guest_id = p_request.getParameter("guest_id");
//		ins_guest_other.charges_id = p_request.getParameter("charge_id");
//		ins_guest_other.req_id = p_request.getParameter("req_id");
//		ins_guest_other.total    						= ins_util.setNullParameter(p_request, "total_rent");
//		ins_guest_other.title    						= ins_util.setNullParameter(p_request, "descr");
//System.out.println("ins_guest_other.charges_id "+ins_guest_other.charges_id);
//		if(ins_guest_other.charges_id.equals("1")){
//				if(!ins_guest_other.insertDB())return false;
//		}
//		return true;
//	}
//	
//}
//





