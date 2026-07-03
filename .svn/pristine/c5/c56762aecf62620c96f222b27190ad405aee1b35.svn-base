package db; 

import java.sql.*;

import common.db_validation;

public class d_shiftvsuserid extends db_validation{
	
	public String  shift_id, user_id, lastmodified;
	
	ngm NGM;
	
	public d_shiftvsuserid(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_shiftvsuserid VALUES (?, ?, now())");
		ps.setString(1, shift_id);
		ps.setString(2, user_id);
 
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_shiftvsuserid  SET  user_id=?, lastmodified=now() WHERE shift_id=?");
		ps.setString(1, user_id);
		//ps.setString(2, lastmodified);
		ps.setString(2, shift_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		//VAL_deleteDB();
		System.out.println("deleted");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_shiftvsuserid WHERE shift_id=?");
		ps.setString(1, shift_id);
		System.out.println(ps);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_shiftvsuserid where shift_id=?");
		ps.setString(1, shift_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			user_id=rs.getString("user_id");
			lastmodified=rs.getString("lastmodified");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_shiftid(shift_id)&&val_userid(user_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_shiftid(shift_id);
		//val_userid(user_id);
	}
	
	public void VAL_deleteDB() throws Exception {
		if(!(val_shiftid(shift_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_shiftid(shift_id);
	}

	public void VAL_selectDB() throws Exception {
		if(!(val_shiftid(shift_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_shiftid(shift_id);
	}

}