package db; 

import java.sql.*;

import common.db_validation;

public class d_banktransaction extends db_validation{
	
	public String   ba_trid, date_deposit, amount, bank_details, status, shift_id,user_id;
	
	ngm NGM;
	
	public d_banktransaction(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_banktransaction VALUES (?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, ba_trid);
		ps.setString(2, date_deposit);
		ps.setString(3, amount);
		ps.setString(4, bank_details);
		ps.setString(5, status);
		ps.setString(6, shift_id);
		ps.setString(7, user_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_banktransaction  SET date_deposit=?, amount=?, bank_details=?, status=?, shift_id=?, user_id=? WHERE ba_trid=?");
		ps.setString(1, ba_trid);
		ps.setString(2, date_deposit);
		ps.setString(3, amount);
		ps.setString(4, bank_details);
		ps.setString(5, status);
		ps.setString(6, shift_id);
		ps.setString(7, user_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		val_batrid(ba_trid);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_banktransaction WHERE ba_trid");
		ps.setString(1, ba_trid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_banktransaction where ba_trid=?");
		ps.setString(1, ba_trid);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			date_deposit=rs.getString("date_deposit");
			amount=rs.getString("amount");
			bank_details=rs.getString("bank_details");
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
		val_batrid(ba_trid);
		val_datedeposit(date_deposit);
		val_amount(amount);
		val_bank_details(bank_details);
		val_status(status);
		val_shiftid(shift_id);
		val_userid(user_id);
	}
	
	public void VAL_updateDB() throws Exception {
		val_batrid(ba_trid);
		val_datedeposit(date_deposit);
		val_amount(amount);
		val_bank_details(bank_details);
		val_status(status);
		val_shiftid(shift_id);
		val_userid(user_id);
	}
	public void VAL_selectDB() throws Exception {
		val_batrid(ba_trid);
		val_datedeposit(date_deposit);
		val_amount(amount);
		val_bank_details(bank_details);
		val_status(status);
		val_shiftid(shift_id);
		val_userid(user_id);
	}
	
	
	
	
	
	
	public boolean val_batrid(String ba_trid){
		if(ba_trid!=null){
			if(!cLength(ba_trid.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(ba_trid)){
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
	public boolean val_bank_details(String bank_details){
		if(bank_details!=null){
			if(!cLength(bank_details.toString(), 1, 200)){
					return false;
				}
				if (!cAlphaNumeric(bank_details)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_datedeposit(String date_deposit){
		if(date_deposit!=null){
			if(!cDateTime(date_deposit.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}

}