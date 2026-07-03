package db; 

import java.sql.*;

import common.db_validation;

public class d_balancesheet extends db_validation{
	
	public String  bal_id, shift_id, advance_credit, advance_debit, receipt_credit,receipt_debit, receipt_id, adv_rec_bank_id, date_of_transaction,last_modified, status, description, adv_rec_bank_mode, mode_c_q,shift_userid,adv_receipt_id,gh_id,sel_block;
	
	ngm NGM;
	
	public d_balancesheet(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_balancesheet VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?, ?, ?, ?,?,?,?,?)");
		ps.setString(1, bal_id);
		ps.setString(2, shift_id);
		ps.setString(3, advance_credit);
		ps.setString(4, advance_debit);
		ps.setString(5, receipt_credit);
		ps.setString(6, receipt_debit);
		ps.setString(7, receipt_id);
		ps.setString(8, adv_rec_bank_id);
		//ps.setString(9, date_of_transaction);
		ps.setString(9, status);
		ps.setString(10, description);
		ps.setString(11, adv_rec_bank_mode);
		ps.setString(12, mode_c_q);
		ps.setString(13, shift_userid);
		ps.setString(14, adv_receipt_id);
		ps.setString(15, gh_id);
		ps.setString(16, sel_block);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB_status() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB_status();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_balancesheet  SET status=? WHERE adv_receipt_id=? and gh_id=?");
		ps.setString(1, status);
		ps.setString(2, adv_receipt_id);
		ps.setString(3, gh_id);
		System.out.println("PSSSS balancesheet "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_balancesheet where bal_id=?");
		ps.setString(1, bal_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			shift_id=rs.getString("shift_id");
			advance_credit=rs.getString("advance_credit");
			advance_debit=rs.getString("advance_debit");
			receipt_credit=rs.getString("receipt_credit");
			receipt_debit=rs.getString("receipt_debit");
			receipt_id=rs.getString("receipt_id");
			adv_rec_bank_id=rs.getString("adv_rec_bank_id");
			date_of_transaction=rs.getString("date_of_transaction");
			last_modified=rs.getString("last_modified");
			status=rs.getString("status");
			description=rs.getString("description");
			adv_rec_bank_mode=rs.getString("adv_rec_bank_mode");
			mode_c_q=rs.getString("mode_c_q");
			shift_userid=rs.getString("shift_userid");
			adv_receipt_id=rs.getString("adv_receipt_id");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_ghid(gh_id)&&val_userid(shift_userid)&&val_adv_receiptid(adv_receipt_id)&&val_mode_c_q(mode_c_q)&&val_adv_rec_bank_mode(adv_rec_bank_mode)&&val_description(description)&&val_status(status)&&val_adv_rec_bank_id(adv_rec_bank_id)&&val_receiptid(receipt_id)&&val_receiptdebit(receipt_debit)&&val_receiptcredit(receipt_credit)&&val_balid(bal_id)&&val_shiftid(shift_id)&&val_advancecredit(advance_credit)&&val_advancedebit(advance_debit))){
			throw NGM.ins_utility.setException("tr_balancesheet", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
		}
		
		//val_balid(bal_id);
		//val_shiftid(shift_id);
		//val_advancecredit(advance_credit);
		//val_advancedebit(advance_debit);
		//val_receiptcredit(receipt_credit);
		//val_receiptdebit(receipt_debit);
		//val_receiptid(receipt_id);
		//val_adv_rec_bank_id(adv_rec_bank_id);
		
		/*val_status(status);
		val_description(description);
		val_adv_rec_bank_mode(adv_rec_bank_mode);
		val_mode_c_q(mode_c_q);
		val_adv_receiptid(adv_receipt_id);
		val_userid(shift_userid);
		val_ghid(gh_id);*/
	}
	
	public void VAL_updateDB_status() throws Exception {
		if(!(val_status(status)&&val_adv_receiptid(adv_receipt_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_balancesheet", "Invalid tr_allotment parameter", "VAL_updateDB_status", NGM);
		}
		
		/*val_status(status);
		val_adv_receiptid(adv_receipt_id);
		val_ghid(gh_id);*/
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_balid(bal_id))){
			throw NGM.ins_utility.setException("tr_balancesheet", "Invalid tr_allotment parameter", "VAL_selectDB", NGM);
		}
		//val_balid(bal_id);
	}
	
	public boolean val_balid(String bal_id){
		if(bal_id!=null){
			if(!cLength(bal_id.toString(), 1, 25)){
					return false;
				}
				if (!cNumeric(bal_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	
	public boolean val_advancecredit(String advance_credit){
		if(advance_credit!=null){
			if(!cLength(advance_credit.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(advance_credit)){
						return false;
				}
				return true;
		}
		return true;
	}
	public boolean val_advancedebit(String advance_debit){
		if(advance_debit!=null){
			if(!cLength(advance_debit.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(advance_debit)){
						return false;
				}
				return true;
		}
		return true;
	}
	
	public boolean val_receiptcredit(String receipt_credit){
		if(receipt_credit!=null){
			if(!cLength(receipt_credit.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(receipt_credit)){
						return false;
				}
				return true;
		}
			return true;
	}
	
	public boolean val_receiptdebit(String receipt_debit){
		if(receipt_debit!=null){
			if(!cLength(receipt_debit.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(receipt_debit)){
						return false;
				}
				return true;
		}
			return true;
	}
	
	public boolean val_receiptid(String receipt_id){
		if(receipt_id!=null){
			if(!cLength(receipt_id.toString(), 1, 25)){
					return false;
				}
				if (!cNumeric(receipt_id)){
						return false;
				}
				return true;
		}
			return true;
	}
	
	public boolean val_adv_receiptid(String adv_receipt_id){
		if(adv_receipt_id!=null){
			if(!cLength(adv_receipt_id.toString(), 1, 25)){
					return false;
				}
				if (!cNumeric(adv_receipt_id)){
						return false;
				}
				return true;
		}
			return true;
	}
	
	public boolean val_adv_rec_bank_id(String adv_rec_bank_id){
		if(adv_rec_bank_id!=null){
			if(!cLength(adv_rec_bank_id.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(adv_rec_bank_id)){
						return false;
				}
				return true;
		}
			return true;
	}
	public boolean val_description(String description){
		if(description!=null){
			if(!cLength(description.toString(), 1, 200)){
					return false;
				}
				if (!cAlphaNumeric(description)){
						return false;
				}
				return true;
		}
			return true;
	}
	public boolean val_adv_rec_bank_mode(String adv_rec_bank_mode){
		if(adv_rec_bank_mode!=null){
			if(!cLength(adv_rec_bank_mode.toString(), 1, 5)){
					return false;
				}
				if (!cAlphaNumeric(adv_rec_bank_mode)){
						return false;
				}
				return true;
		}
			return true;
	}
	public boolean val_mode_c_q(String mode_c_q){
		if(mode_c_q!=null){
			if(!cLength(mode_c_q.toString(), 1, 6)){
					return false;
				}
				if (!cAlphabet(mode_c_q)){
						return false;
				}
				return true;
		}else
			return false;
	}

	public Boolean updateRecStatus() throws Exception {
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_balancesheet  SET status=? WHERE receipt_id=?");
		ps.setString(1, status);
		ps.setString(2, receipt_id);
		System.out.println("PSSSS balancesheet "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	

	/*public boolean val_date_of_transaction(String date_of_transaction){
		if(date_of_transaction!=null){
			if(!cDateTime(date_of_transaction.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}*/
	/*public boolean val_lastmodified(String lastmodified){
		if(lastmodified!=null){
			if(!cDateTime(lastmodified.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
*/
}