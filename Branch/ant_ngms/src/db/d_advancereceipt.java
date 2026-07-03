package db; 

import java.sql.*;

import common.db_validation;

public class d_advancereceipt extends db_validation{
	
	public String  rec_id, amount, date_rece, mode, receipt_debit, date_of_transaction,lastmodified, status, shift_id, user_id;
	
	ngm NGM;
	
	public d_advancereceipt(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_advancereceipt  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, rec_id);
		ps.setString(2, amount);
		ps.setString(3, date_rece);
		ps.setString(4, mode);
		ps.setString(5, receipt_debit);
		ps.setString(6, date_of_transaction);
		ps.setString(7, lastmodified);
		ps.setString(8, status);
		ps.setString(9, shift_id);
		ps.setString(10, user_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_advancereceipt   SET amount=?, date_rece=?, mode=?, receipt_debit=?, date_of_transaction=?,lastmodified=?, status=?, shift_id=?, user_id=? WHERE rec_id=?");
		ps.setString(1, amount);
		ps.setString(2, date_rece);
		ps.setString(3, mode);
		ps.setString(4, receipt_debit);
		ps.setString(5, date_of_transaction);
		ps.setString(6, lastmodified);
		ps.setString(7, status);
		ps.setString(8, shift_id);
		ps.setString(9, user_id);
		ps.setString(10, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		val_recid(rec_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_advancereceipt WHERE rec_id");
		ps.setString(1, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_advancereceipt where rec_id=?");
		ps.setString(1, rec_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			amount=rs.getString("amount");
			date_rece=rs.getString("date_rece");
			mode=rs.getString("mode");
			receipt_debit=rs.getString("receipt_debit");
			date_of_transaction=rs.getString("date_of_transaction");
			lastmodified=rs.getString("lastmodified");
			status=rs.getString("status");
			shift_id=rs.getString("shift_id");
			user_id=rs.getString("user_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_amount(amount);
		val_daterece(date_rece);
		val_mode(mode);
		val_recid(rec_id);
		val_receiptdebit(receipt_debit);
		val_date_of_transaction(date_of_transaction);
		val_lastmodified(lastmodified);
		val_status(status);
		val_shiftid(shift_id);
		val_userid(user_id);
	}
	public void VAL_updateDB() throws Exception {
		val_amount(amount);
		val_daterece(date_rece);
		val_mode(mode);
		val_recid(rec_id);
		val_receiptdebit(receipt_debit);
		val_date_of_transaction(date_of_transaction);
		val_lastmodified(lastmodified);
		val_status(status);
		val_shiftid(shift_id);
		val_userid(user_id);
	}
	public void VAL_selectDB() throws Exception {
		val_amount(amount);
		val_daterece(date_rece);
		val_mode(mode);
		val_recid(rec_id);
		val_receiptdebit(receipt_debit);
		val_date_of_transaction(date_of_transaction);
		val_lastmodified(lastmodified);
		val_status(status);
		val_shiftid(shift_id);
		val_userid(user_id);
	}
	
	
	
	
	
	public boolean val_receiptdebit(String receipt_debit){
		if(receipt_debit!=null){
			if(!cLength(receipt_debit.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(receipt_debit)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_recid(String rec_id){
		if(rec_id!=null){
			if(!cLength(rec_id.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(rec_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_amount(String amount){
		if(amount!=null){
			if(!cLength(amount.toString(), 1, 10)){
					return false;
				}
				if (!cAlphaNumeric(amount)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_mode(String mode){
		if(mode!=null){
			if(!cLength(mode.toString(), 1, 5)){
					return false;
				}
				if (!cAlphaNumeric(mode)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_daterece(String date_rece){
		if(date_rece!=null){
			if(!cDateTime(date_rece.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_date_of_transaction(String date_of_transaction){
		if(date_of_transaction!=null){
			if(!cDateTime(date_of_transaction.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_lastmodified(String lastmodified){
		if(lastmodified!=null){
			if(!cDateTime(lastmodified.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}

}