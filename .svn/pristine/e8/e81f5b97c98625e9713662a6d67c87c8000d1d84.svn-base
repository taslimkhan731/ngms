package db; 

import java.sql.*;

import common.db_validation;

public class d_receipt extends db_validation{
	
	public String  rec_id, amount, date_rece, mode, receipt_debit, date_of_transaction,lastmodified, status, shift_id, user_id,gh_id,sel_block,total_amount,full_partial,bank_res_status,txnId;
	
	ngm NGM;
	
	public d_receipt(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		System.out.println("Inside the receipt model");
		boolean l_status=false;	
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_receipt  VALUES (?, ?, now(), ?, ?, now(), now(), ?, ?, ?,?,?,?,?)");
		ps.setString(1, rec_id);
		ps.setString(2, amount);
		//ps.setString(3, date_rece);
		ps.setString(3, mode);
		ps.setString(4, receipt_debit);
		//ps.setString(6, date_of_transaction);
		//ps.setString(7, lastmodified);
		ps.setString(5, status);
		ps.setString(6, shift_id);
		ps.setString(7, user_id);
		ps.setString(8, gh_id);
		ps.setString(9, total_amount);
		ps.setString(10, full_partial);
		ps.setString(11, sel_block);
		System.out.println("p5555    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		//VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_receipt   SET amount=?, date_rece=?, mode=?, receipt_debit=?, date_of_transaction=?,lastmodified=?, status=?, shift_id=?, user_id=? WHERE rec_id=?");
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
		//VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_receipt WHERE rec_id");
		ps.setString(1, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_receipt where rec_id=?");
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
			total_amount=rs.getString("total_amount");
			full_partial=rs.getString("full_partial");
			sel_block = rs.getString("block_id");
			gh_id = rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public String selectDB_shiftRecTot() throws Exception { //ok
		String sum = "";
		//VAL_selectDB_shiftRecTot();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(amount::Integer) from tr_receipt where shift_id=? and gh_id=?");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sum=rs.getString("sum");
			l_status=true;
		}
		rs.close();
		ps.close();
		return sum;
	} 
	
	public String selectDB_roomblock(String roomId) throws Exception { //ok
		String block_name = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where block_id = ?");
		ps.setString(1, roomId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			block_name=rs.getString("block_name");
		}
		rs.close();
		ps.close();
		return block_name;
	} 
	
	public String selectDB_city(String ghId) throws Exception { //ok
		String block_name = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gh_details where gh_id = ?");
		ps.setString(1, ghId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			block_name=rs.getString("gh_name");
		}
		rs.close();
		ps.close();
		return block_name;
	} 
	
	public String selectDB_billId(String rec_id) throws Exception { //ok
		String block_name = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_receiptvsbill where rec_id = ?");
		ps.setString(1, rec_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			block_name=rs.getString("bill_id");
		}
		rs.close();
		ps.close();
		return block_name;
	} 
	
	public Boolean updateRecStatus() throws Exception { //ok
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update tr_receipt set status = ?, receipt_debit=?,bank_resp_status=?,bank_ref=? where rec_id=?");
		ps.setString(1, status);
		ps.setString(2, receipt_debit);
		ps.setString(3, bank_res_status);
		ps.setString(4, txnId);
		ps.setString(5, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_amount(amount)&&val_mode(mode)&&val_seq_id(rec_id)&&val_receiptdebit(receipt_debit)&&val_status(status)&&val_shiftid(shift_id)&&val_userid(user_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		/*val_amount(amount);//
		val_mode(mode);//
		val_seq_id(rec_id);//
		val_receiptdebit(receipt_debit);//
		val_status(status);//
		val_shiftid(shift_id);//
		val_userid(user_id);
		val_ghid(gh_id);*/
	}
	
	public void VAL_deleteDB() throws Exception {
		if(!(val_seq_id(rec_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
	//	val_seq_id(rec_id);//
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_seq_id(rec_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_seq_id(rec_id);
	}
	
	public void VAL_selectDB_shiftRecTot() throws Exception {
		if(!(val_shiftid(shift_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		/*val_shiftid(shift_id);
		val_ghid(gh_id);*/
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
		}
			return true;
		
	}
	
	public boolean val_amount(String amount){
		if(amount!=null){
			if(!cLength(amount.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(amount)){
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
				if (!cAlphabet(mode)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	/*public boolean val_daterece(String date_rece){
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
		
	}*/

}