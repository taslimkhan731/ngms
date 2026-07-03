package db; 

import java.sql.*;

import common.db_validation;

public class d_billmaster extends db_validation{
	
	public String  bill_id,billno,createdby, date,lastmodified,status, shift_id,gh_id,sel_block;
	
	ngm NGM;
	
	public d_billmaster(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_bill_master VALUES (?, ?, ?, now(),now(), ?, ?,?,?)");
		ps.setString(1, bill_id);
		ps.setString(2, billno);
		ps.setString(3, createdby);
		ps.setString(4, status);
		ps.setString(5, shift_id);
		ps.setString(6, gh_id);
		ps.setString(7, sel_block);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_bill_master  SET  billno=?, createdby=?, date=?, lastmodified=now(), status=?, shift_id=? WHERE bill_id=?");
		ps.setString(1, billno);
		ps.setString(2, createdby);
		ps.setString(3, date);
		ps.setString(4, status);
		ps.setString(5, shift_id);
		ps.setString(6, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean updaterectDB() throws Exception
	{
		boolean l_status=false;
		VAL_updaterectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_bill_master  SET  status=? WHERE bill_id=?");
		ps.setString(1, status);
		ps.setString(2, bill_id);
		System.out.println("pssss"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDBstatus() throws Exception
	{
		boolean l_status=false;
		VAL_updateDBstatus();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_bill_master  SET  lastmodified=now(), status=? WHERE bill_id=?");
		ps.setString(1, status);
		ps.setString(2, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}


	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_bill_master WHERE bill_id");
		ps.setString(1, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_bill_master where bill_id=?");
		ps.setString(1, bill_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			billno=rs.getString("billno");
			createdby=rs.getString("createdby");
			date=rs.getString("date");
			lastmodified=rs.getString("lastmodified");
			status=rs.getString("status");
			shift_id=rs.getString("shift_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_billno(billno)&&val_ghid(gh_id)&&val_user_id(createdby)&&val_status(status)&&val_shiftid(shift_id))){
			throw NGM.ins_utility.setException("tr_bill_master", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
		}
		
	/*	val_bill_id(bill_id);
		val_billno(billno);
		val_user_id(createdby);
		val_ghid(gh_id);
		val_status(status);
		val_shiftid(shift_id);*/
	}
	public void VAL_updateDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_billno(billno)&&val_date(date)&&val_user_id(createdby)&&val_status(status)&&val_shiftid(shift_id))){
			throw NGM.ins_utility.setException("tr_bill_master", "Invalid tr_allotment parameter", "VAL_updateDB", NGM);
		}
		
		/*val_bill_id(bill_id);
		val_billno(billno);
		val_user_id(createdby);
		val_date(date);
		val_status(status);
		val_shiftid(shift_id);*/
	}
	
	public void VAL_updaterectDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_bill_master", "Invalid tr_allotment parameter", "VAL_updaterectDB", NGM);
		}
		/*val_bill_id(bill_id);
		val_status(status);*/
	}
	
	public void VAL_updateDBstatus() throws Exception {
		if(!(val_bill_id(bill_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_bill_master", "Invalid tr_allotment parameter", "VAL_updateDBstatus", NGM);
		}
		/*val_bill_id(bill_id);
		val_status(status);*/
	}
	
	public void VAL_deleteDB() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_bill_master", "Invalid tr_allotment parameter", "VAL_deleteDB", NGM);
		}
		//val_bill_id(bill_id);
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_bill_master", "Invalid tr_allotment parameter", "VAL_selectDB", NGM);
		}
		//val_bill_id(bill_id);
	}
	
	public boolean val_billno(String billno){
		if(billno!=null){
			if(!cLength(billno.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(billno)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_date(String date){
		if(date!=null){
			if(!cDateTime(date.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	/*public boolean val_lastmodified(String lastmodified){
		if(lastmodified!=null){
			if(!cDateTime(lastmodified.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}*/

}