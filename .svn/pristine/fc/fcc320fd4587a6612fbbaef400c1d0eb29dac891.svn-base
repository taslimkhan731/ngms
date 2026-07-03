package model;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;
import db.*;

public class m_billing extends ngmodel{
	public String p_bill_id="";
	public String p_gh_id="";
	public String p_sel_block="";
	  utility 		ins_util;
	  d_guestothercharges ins_guest_other;
	  d_gadrequest ins_gad;
	  d_billmaster ins_bill;
	  d_billvsguest  ins_biluser;
	  d_shiftmaster ins_shift;
	  d_billvsguestvscharge ins_billusercharge;
	  d_checkin ins_chk;
	  common ins_common;  
	public m_billing(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_guest_other	  = new d_guestothercharges(NGM);
		ins_gad = new d_gadrequest(NGM);
		ins_bill = new d_billmaster(NGM);
		ins_biluser = new d_billvsguest(NGM);
		ins_shift = new d_shiftmaster(NGM);
		ins_billusercharge = new d_billvsguestvscharge(NGM);
		ins_chk = new d_checkin(NGM);
		ins_common = new common(NGM);
		}
		
	
	public void VJ_billprint(String pgh_id,String pbill_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_bill_id(pbill_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "billprint", NGM);
		}
	}
	public void VJ_addbilling(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "addbilling", NGM);
		}
	}
	public void VJ_showRentBillAuto(String pgh_id,String vc_id,String purpose,String from,String to,String adults,String child,String guest_id) throws Exception
	{
		System.out.println("pgh_id"+pgh_id);
		System.out.println("vc_id"+vc_id);
		System.out.println("purpose"+purpose);
		System.out.println("from"+from);
		System.out.println("to"+to);
		System.out.println("adults"+adults);
		System.out.println("child"+child);
		System.out.println("guest_id"+guest_id);
		String[] t_from = from.split(" ");
		String[] t_to = to.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_vccategory(vc_id)&&NGM.ins_dbval.val_purpose(purpose)&&NGM.ins_dbval.val_date(t_from[0])&&NGM.ins_dbval.val_date(t_to[0])&&NGM.ins_dbval.val_seq_id(adults)&&NGM.ins_dbval.val_seq_id(child)&&NGM.ins_dbval.val_guestid(guest_id))) 
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "showRentBillAuto", NGM);
		}
	}
	public void VJ_showFoodBillAuto(String pgh_id,String req_id,String guest_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_reqid(req_id)&&NGM.ins_dbval.val_guestid(guest_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "showFoodBillAuto", NGM);
		}
	}
	public void VJ_addguestbilling(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "addguestbilling", NGM);
		}
	}
	public void VJ_makeguestbilling(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "makeguestbilling", NGM);
		}
	}
	public void VJ_showbills(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "showbills", NGM);
		}
	}
	public void VJ_showbillforpdf(String pgh_id,String pbill_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_bill_id(pbill_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "showbillforpdf", NGM);
		}
	}
	public void VJ_cancelbilling(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "cancelbilling", NGM);
		}
	}
	public void VJ_cancelguestbilling(String pgh_id,String ppage_no,String pbill_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_bill_id(pbill_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "cancelguestbilling", NGM);
		}
	}
	public void VJ_billdataentry(String pguest_id,String pcharge_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_guestid(pguest_id)&&NGM.ins_dbval.val_chargesid(pcharge_id)&&NGM.ins_dbval.val_reqid(preq_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "billdataentry", NGM);
		}
	}
	public void VJ_search(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "search", NGM);
		}
	}
	public void VJ_searchCancel(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "searchCancel", NGM);
		}
	}
	public void VJ_searchShow(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_billing", "Validation fails", "searchCancel", NGM);
		}
	}
	
	//cancelBill
	public boolean cancelBill(HttpServletRequest p_request) throws Exception{
		String gh_id = p_request.getParameter("gh_id");
		
		ins_bill.bill_id = p_request.getParameter("bill_id");
		ins_bill.status = "CAN";
		if(!ins_bill.updateDBstatus()) return false;
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_billvsguest where bill_id=?");
		ps.setString(1, ins_bill.bill_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			ins_chk.req_id = rs.getString("req_id");
			ins_chk.guest_id = rs.getString("guest_id");
			ins_chk.status = "COUT";
			if(!ins_chk.updateDB_status()) return false;		
		}
		 //close
	    rs.close();
		ps.close();
		return true;
	}
	
	public String searchGuestCOUT(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
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
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +" date('"+date+"') between date(arrival_time_date) and date(departure_time_date)";
								//" date(arrival_time_date) = date('"+date+"')";  //"'"+date+"' between date(from_date) and date(to_date)";
					}
				}catch (NullPointerException x)	{} 
				
				
				 System.out.println("COU GUEST                "+criteria);
				 return criteria;
				
}
	
	public String searchCancelBill(HttpServletRequest request)throws Exception{
		  
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
				
				
				 System.out.println("CANCEL BILL LIST           "+criteria);
				 return criteria;
				
}
	
	
	public boolean submitBillCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("rentcalc saveCreation");
		String gh_id = p_request.getParameter("gh_id");
		String sel_block = p_request.getParameter("sel_block");
		String charge_count = p_request.getParameter("charge_count");
		String guest_count = p_request.getParameter("guest_count");
		
		ins_bill.bill_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "bill_id").toString(); 
		p_bill_id=ins_bill.bill_id;
		p_gh_id=gh_id;
		p_sel_block=sel_block;
		ins_bill.gh_id = gh_id;
		ins_bill.sel_block = sel_block;
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "bill_id", Integer.parseInt(ins_bill.bill_id));
	    ins_bill.billno = NGM.ins_utility.get_seqID(NGM.tr_conn, "bill_no").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "bill_no", Integer.parseInt(ins_bill.billno));
	    ins_bill.createdby = NGM.ins_sess.user_id;
	    
	    ins_shift.gh_id = gh_id;
	    ins_shift.sel_block = sel_block;
	    ins_shift.status = "A";
	    if(!ins_shift.selectDB_ACT())  return false;
	    ins_bill.shift_id = ins_shift.shift_id;
	    ins_bill.status = "BILL";
	    if(!ins_bill.insertDB()) return false;

		for(int i=1;i<=Integer.parseInt(guest_count) ;i++){
			for(int j=1;j<=Integer.parseInt(charge_count);j++){
				ins_guest_other.seq_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_other_id").toString(); 
			    NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_other_id", Integer.parseInt(ins_guest_other.seq_id));
				ins_guest_other.guest_id = ins_util.setNullParameter(p_request, "gue_id"+i);
				ins_guest_other.req_id = ins_common.getRequestID(ins_guest_other.guest_id);
				ins_guest_other.charges_id = ""+j;
				ins_guest_other.total  = ins_util.setNullParameter(p_request, "amount"+ins_guest_other.guest_id+j);
				ins_guest_other.title = null;
				//ins_guest_other.title    						= ins_util.setNullParameter(p_request, "descr");
				if(!ins_guest_other.insertDB()) return false;
				
				ins_billusercharge.bill_id = ins_bill.bill_id;
				ins_billusercharge.req_id = ins_guest_other.req_id;
				ins_billusercharge.guest_id = ins_guest_other.guest_id;
				ins_billusercharge.charges_id = ins_guest_other.charges_id;
				ins_billusercharge.amount = ins_guest_other.total;
				if(!ins_billusercharge.insertDB()) return false;
				
				ins_chk.req_id = ins_guest_other.req_id; 
				ins_chk.guest_id = ins_billusercharge.guest_id;
				ins_chk.status = "BILL";
				if(!ins_chk.updateDB_status()) return false;
			
						
			}
			ins_biluser.bill_id = ins_bill.bill_id;
			ins_biluser.req_id = ins_guest_other.req_id;
			ins_biluser.guest_id = ins_guest_other.guest_id;
			if(!ins_biluser.insertDB()) return false;
		}
		return true;
	}
	
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("rentcalc saveCreation    for billing");
		ins_guest_other.seq_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_other_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_other_id", Integer.parseInt(ins_guest_other.seq_id));
		ins_guest_other.guest_id = p_request.getParameter("guest_id");
		ins_guest_other.charges_id = p_request.getParameter("charge_id");
		ins_guest_other.req_id = p_request.getParameter("req_id");
		ins_guest_other.total    						= ins_util.setNullParameter(p_request, "total_rent");
		ins_guest_other.title    						= ins_util.setNullParameter(p_request, "descr");
		System.out.println("ins_guest_other.charges_id "+ins_guest_other.charges_id);
		if(ins_guest_other.charges_id.equals("1")){
				if(!ins_guest_other.insertDB())return false;
		}
		return true;
	}
	
	//getrentdetails
			public String getrentdetails(String vc_id, String from, String to, String purpose, String guest_id, String req_id,String basic_pay)throws Exception
			{
				Integer total=0;
				String rentdate="";
				d_gadrequest ins_gad = new d_gadrequest(NGM);
				ins_gad.req_id = req_id;
				ins_gad.selectDB_gad();
				
				d_rent rent=new d_rent(NGM);
				rent.vc_id=vc_id;
				if(purpose.equals("private")){
					purpose = "Private";
					rent.type="Private";
				}else{
					rent.type=purpose;
				}
				System.out.println("purpose 1 "+purpose);
				rent.family=ins_gad.family;
				if(!rent.selectDB()) return "No Data in Table";
					
				String fdate=ins_common.display_date_rent(rent.from_date);
				String tdate=ins_common.display_date_rent(rent.to_date);
				
				String date1=ins_common.datesbetween(from, to);
				String[] array = date1.split("/"); 
				System.out.println("------------------------------------------------------------------------------------");
				for(int i=0;i<array.length;i++)
				{
					if((i<=5)||purpose.equals("Onduty")||purpose.equals("civil service(PT)"))   ///////////////////////////////////////purpose Onduty
					{
						if(!array[i].equals(""))
						{
							String dates=array[i];
							System.out.println("purpose 2 "+purpose);
							rentdate=getvalidrent(dates,fdate,tdate,purpose,vc_id,ins_gad.family,basic_pay);
							System.out.println("rentdate 1 "+rentdate);
							if(rentdate==null||rentdate.equals("")){
								rentdate = "0";
							}
							System.out.println("rentdate.......................... "+i+" : "+rentdate);
							total=total+Integer.parseInt(rentdate);
							System.out.println("total.......................... "+i+" : "+total);
						}
												
					}
					else
					{
						if(!array[i].equals(""))
						{
							String dates=array[i];
							System.out.println("purpose 3 "+purpose);
							rentdate=getvalidrent(dates,fdate,tdate,purpose,vc_id,ins_gad.family,basic_pay);
							System.out.println("rentdate 2 "+rentdate);
							if(rentdate==null||rentdate.equals("")){
								rentdate = "0";
							}
							System.out.println("rentdate................>5.......... "+i+" : "+rentdate);
							total=total+(2*Integer.parseInt(rentdate));
							System.out.println("total....................>5...... "+i+" : "+total);
							//System.out.println("TOTAL                         "+total);
							
						}
					}
					
				 }
				String renttotal=total.toString();
				return renttotal;
			}//end method


		//getvalidrent
		public String getvalidrent(String date, String from_date,String to_date,String purpose,String vc_id,String family,String basic_pay) throws Exception{
			 
			int rent1;
			 String mode="",da="",rent="";
			 String pay_from="",pay_to="",da_mode="",da_value="",value="";
			 String date1=ins_common.display_time_zonenew(date);
			 PreparedStatement ps=NGM.tr_conn.prepareStatement("select rent,f_rent,mode_of_payment,da_percentage from rent_table where to_timestamp(?,'yyyy-MM-dd') between to_timestamp(?,'dd-MM-yyyy') and to_timestamp(?,'dd-MM-yyyy') and vc_id=? and type=? and family=?");
			 ps.setString(1,date1);
			 ps.setString(2,from_date);
			 ps.setString(3,to_date);
			 ps.setString(4,vc_id);
			 ps.setString(5,purpose);
			 ps.setString(6,family);
			 System.out.println("SYSO "+ps);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
				 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr "+rs.getString("rent"));
				 if(rs.getString("rent")!="0"){   //////////////// =======================
					 System.out.println("rent 0 ");
					 rent=rs.getString("rent");
					 mode=rs.getString("mode_of_payment");
					 da=rs.getString("da_percentage");
				 }
				 else
				 {
					 	System.out.println("else... rent ");
					 	rent=rs.getString("f_rent");
						mode=rs.getString("mode_of_payment");
						da=rs.getString("da_percentage");
					 
				 }
				
			 }
			 //close
			    rs.close();
				ps.close();
				System.out.println("modemodemodemodemode "+mode);
			 if(mode.equals("D"))
			 {
				  	PreparedStatement pss=NGM.tr_conn.prepareStatement("select basis_pay_from,basis_pay_to,da_value from basicpay_da where da_cal_mode=? and CAST (basis_pay_from AS INTEGER) < ? and  CAST (basis_pay_to AS INTEGER) > ?");
				 	pss.setString(1,mode);
				 	pss.setInt(2, Integer.parseInt(basic_pay));
				 	pss.setInt(3, Integer.parseInt(basic_pay));
				 	ResultSet rss=pss.executeQuery();
				 	while(rss.next())
				 	{
				 		pay_from=rss.getString("basis_pay_from");
				 		pay_to=rss.getString("basis_pay_to");
				 		value=rss.getString("da_value");
				 		rent1=(int) (Integer.parseInt(value) * Float.parseFloat(da));
				 		rent=Integer.toString(rent1);	
				 		System.out.println("rentrentrentrentrentrentrent "+rent);
				 		System.out.println("rent1rent1rent1rent1rent1rent1 "+rent1);
					 }
				 	 //close
				    rss.close();
					pss.close();
			}
			 
			 rs.close();
			 ps.close();
			 System.out.println("rent................ "+rent);
		  return rent;
		}//end method
		
		
}






