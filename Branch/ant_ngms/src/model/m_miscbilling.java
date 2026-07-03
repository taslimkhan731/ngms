package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import common.common;
import db.ngm;

public class m_miscbilling extends ngmodel{
	  common ins_common;  
	public m_miscbilling(ngm p_ngm){
		NGM		= p_ngm;
		ins_common = new common(NGM);
		}
		
	public boolean createMiscBill(HttpServletRequest p_request) throws Exception{
		boolean l_status = false;
		String gh_id = p_request.getParameter("gh_id");
		String sel_block = p_request.getParameter("sel_block");
		//String roll_misc = p_request.getParameter("roll_misc");
		String misc = p_request.getParameter("misc");
		String dcb_type = p_request.getParameter("dcb_type");
		String misc_amount = p_request.getParameter("misc_amount");
		String misc_bill_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "misc_bill_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "misc_bill_id", Integer.parseInt(misc_bill_id));
	    String misc_rollno = NGM.ins_utility.get_seqID(NGM.tr_conn, "misc_rollno").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "misc_rollno", Integer.parseInt(misc_rollno));
	    
			PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_misc_billing(misc_bill_id, misc_rollno, misc_id, dcb_type_id, amount, status, gh_id, misc_bill_date,block_id) VALUES (?, ?, ?, ?, ?, ?, ?, now(),?)");
			ps.setString(1, misc_bill_id);
			ps.setString(2, misc_rollno);
			ps.setString(3, misc);
			ps.setString(4, dcb_type);
			ps.setString(5, misc_amount);
			ps.setString(6, "BILL");
			ps.setString(7, gh_id);
			ps.setString(8, sel_block);
			System.out.println("insert MISC bill"+ps);
			l_status=NGM.ins_utility.executeDB(ps);
			ps.close();
		return true;
	}
	
	public boolean createMiscReceipt(HttpServletRequest p_request) throws Exception{
		boolean l_status = false;
		String gh_id = p_request.getParameter("gh_id");
		String misc_bill_id = p_request.getParameter("misc_bill_id");
		String amount = p_request.getParameter("amount");
		String sel_block = p_request.getParameter("sel_block");
	    String misc_receipt_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "misc_receipt_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "misc_receipt_id", Integer.parseInt(misc_receipt_id));
	    
			PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_misc_receipt(misc_rec_id, misc_bill_id, amount, status, gh_id, misc_rec_date,block_id) VALUES (?, ?, ?, ?, ?, now(),?)");
			ps.setString(1, misc_receipt_id);
			ps.setString(2, misc_bill_id);
			ps.setString(3, amount);
			ps.setString(4, "REC");
			ps.setString(5, gh_id);
			ps.setString(6, sel_block);
			System.out.println("insert MISC rec"+ps);
			l_status=NGM.ins_utility.executeDB(ps);
			ps.close();
			
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("UPDATE tr_misc_billing SET status=? WHERE gh_id=? and misc_bill_id=?");
			ps1.setString(1, "REC");
			ps1.setString(2, gh_id);
			ps1.setString(3, misc_bill_id);
			System.out.println("insert MISC bill update"+ps1);
			l_status=NGM.ins_utility.executeDB(ps1);
			ps1.close();
		return true;
	}
	
	public boolean cancelMiscReceipt(HttpServletRequest p_request) throws Exception{
		boolean l_status = false;
		String gh_id = p_request.getParameter("gh_id");
		String misc_bill_id = p_request.getParameter("misc_bill_id");
		String status = "";
	    
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_misc_billing WHERE gh_id=? and misc_bill_id=?");
			ps.setString(1, gh_id);
			ps.setString(2, misc_bill_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				status = rs.getString("status");
			}
			
			if(status.equals("BILL")){
				PreparedStatement ps1=NGM.tr_conn.prepareStatement("UPDATE tr_misc_billing SET status=? WHERE gh_id=? and misc_bill_id=?");
				ps1.setString(1, "CAN");
				ps1.setString(2, gh_id);
				ps1.setString(3, misc_bill_id);
				System.out.println("MISC bill update "+ps1);
				l_status=NGM.ins_utility.executeDB(ps1);
				ps1.close();
			}
			else if(status.equals("REC")){
				
				PreparedStatement ps1=NGM.tr_conn.prepareStatement("UPDATE tr_misc_receipt SET status=? WHERE gh_id=? and misc_bill_id=?");
				ps1.setString(1, "CAN");
				ps1.setString(2, gh_id);
				ps1.setString(3, misc_bill_id);
				System.out.println("MISC rec update222 "+ps1);
				l_status=NGM.ins_utility.executeDB(ps1);
				
				ps1=NGM.tr_conn.prepareStatement("UPDATE tr_misc_billing SET status=? WHERE gh_id=? and misc_bill_id=?");
				ps1.setString(1, "CAN");
				ps1.setString(2, gh_id);
				ps1.setString(3, misc_bill_id);
				System.out.println("MISC bill update222 "+ps1);
				l_status=NGM.ins_utility.executeDB(ps1);
				
				ps1.close();
			}
			
		return true;
	}
	
}






