package db; 

import java.sql.*;

import common.db_validation;

public class d_billvsguest extends db_validation{
	
	public String  bill_id, req_id, guest_id;
	
	ngm NGM;
	
	public d_billvsguest(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_billvsguest VALUES (?, ?, ?)");
		ps.setString(1, bill_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_billvsguest SET req_id=?, guest_id=? WHERE bill_id=?");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_billvsguest WHERE bill_id");
		ps.setString(1, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_billvsguest where bill_id=?");
		ps.setString(1, bill_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			req_id=rs.getString("req_id");
			guest_id=rs.getString("guest_id");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_reqid(req_id)&&val_guestid(guest_id))){
			throw NGM.ins_utility.setException("tr_billvsguest", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
		}
		/*val_bill_id(bill_id);
		val_reqid(req_id);
		val_guestid(guest_id);*/
	}
	
	public void VAL_updateDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_reqid(req_id)&&val_guestid(guest_id))){
			throw NGM.ins_utility.setException("tr_billvsguest", "Invalid tr_allotment parameter", "VAL_updateDB", NGM);
		}
		/*val_bill_id(bill_id);
		val_reqid(req_id);
		val_guestid(guest_id);*/
	}
	
	public void VAL_deleteDB() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_billvsguest", "Invalid tr_allotment parameter", "VAL_deleteDB", NGM);
		}
		//val_bill_id(bill_id);
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_billvsguest", "Invalid tr_allotment parameter", "VAL_selectDB", NGM);
		}
		//val_bill_id(bill_id);
	}
	
	

}