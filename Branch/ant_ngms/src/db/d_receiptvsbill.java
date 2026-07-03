package db; 

import java.sql.*;

import common.db_validation;

public class d_receiptvsbill extends db_validation{
	
	public String  rec_id, bill_id;
	
	ngm NGM;
	
	public d_receiptvsbill(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_receiptvsbill  VALUES (?, ?)");
		ps.setString(1, rec_id);
		ps.setString(2, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		//VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_receiptvsbill SET bill_id=? WHERE rec_id=?");
		ps.setString(1, bill_id);
		ps.setString(2, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		//VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_receiptvsbill WHERE rec_id");
		ps.setString(1, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_receiptvsbill where rec_id=?");
		ps.setString(1, rec_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			bill_id=rs.getString("bill_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_seq_id(rec_id)&&val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_bill_id(bill_id);
		//val_seq_id(rec_id);
	}
	public void VAL_updateDB() throws Exception {
		if(!(val_seq_id(rec_id)&&val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_bill_id(bill_id);
		//val_seq_id(rec_id);
	}
	public void VAL_selectDB() throws Exception {
		if(!(val_seq_id(rec_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_seq_id(rec_id);
	}
	
	public void VAL_deleteDB() throws Exception {
		if(!(val_seq_id(rec_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_seq_id(rec_id);
	}

}