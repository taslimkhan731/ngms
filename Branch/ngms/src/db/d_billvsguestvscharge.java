package db; 

import java.sql.*;

import common.db_validation;

public class d_billvsguestvscharge extends db_validation{
	
	public String   bill_id, req_id, guest_id, charges_id, amount;
	
	ngm NGM;
	
	public d_billvsguestvscharge(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_billvsguestvscharges  VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, bill_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		ps.setString(4, charges_id);
		ps.setString(5, amount);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_billvsguestvscharges SET req_id=?, guest_id=?, charges_id=?, amount=? WHERE bill_id=?");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, charges_id);
		ps.setString(4, amount);
		ps.setString(5, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_billvsguestvscharges WHERE bill_id");
		ps.setString(1, bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception {
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_billvsguestvscharges where bill_id=?");
		ps.setString(1, bill_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			req_id=rs.getString("req_id");
			guest_id=rs.getString("guest_id");
			charges_id=rs.getString("charges_id");
			amount=rs.getString("amount");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public String selectDBamount() throws Exception { 
		VAL_selectDBamount();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(amount::Integer) from tr_billvsguestvscharges where bill_id=? and charges_id=?");
		ps.setString(1, bill_id);
		ps.setString(2, charges_id);
		String sum = "";
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
	public String selectDBbill() throws Exception { 
		VAL_selectDBbill();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(amount::Integer) from tr_billvsguestvscharges where bill_id=?");
		ps.setString(1, bill_id);
		String billtot = "";
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			billtot=rs.getString("sum");
			l_status=true;
		}
		rs.close();
		ps.close();
		return billtot;
	}
	public String selectDBbillguest() throws Exception { 
		VAL_selectDBbillguest();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(amount::Integer) from tr_billvsguestvscharges where bill_id=? and req_id=? and guest_id=?");
		ps.setString(1, bill_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		String billtot = "";
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			billtot=rs.getString("sum");
			l_status=true;
		}
		rs.close();
		ps.close();
		return billtot;
	}
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_reqid(req_id)&&val_guestid(guest_id)&&val_amount(amount)&&val_charges_id(charges_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
		}
		
		/*val_bill_id(bill_id);
		val_reqid(req_id);
		val_guestid(guest_id);
		val_amount(amount);
		val_charges_id(charges_id);*/
	}
	public void VAL_updateDB() throws Exception {
		if(!(val_bill_id(bill_id)&&val_reqid(req_id)&&val_guestid(guest_id)&&val_amount(amount)&&val_charges_id(charges_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_updateDB", NGM);
		}
		
	/*	val_bill_id(bill_id);
		val_reqid(req_id);
		val_guestid(guest_id);
		val_amount(amount);
		val_charges_id(charges_id);*/
	}
	public void VAL_selectDB() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_selectDB", NGM);
		}
		//val_bill_id(bill_id);
	}
	public void VAL_deleteDB() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_deleteDB", NGM);
		}
		//val_bill_id(bill_id);
	}
	public void VAL_selectDBamount() throws Exception {
		if(!(val_bill_id(bill_id)&&val_charges_id(charges_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_selectDBamount", NGM);
		}
		/*val_bill_id(bill_id);
		val_charges_id(charges_id);*/
	}
	public void VAL_selectDBbill() throws Exception {
		if(!(val_bill_id(bill_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_selectDBbill", NGM);
		}
		//val_bill_id(bill_id);
	}
	public void VAL_selectDBbillguest() throws Exception {
		if(!(val_bill_id(bill_id)&&val_reqid(req_id)&&val_guestid(guest_id))){
			throw NGM.ins_utility.setException("tr_billvsguestvscharges", "Invalid tr_allotment parameter", "VAL_selectDBbillguest", NGM);
		}
		/*val_bill_id(bill_id);
		val_reqid(req_id);
		val_guestid(guest_id);*/
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
	public boolean val_charges_id(String charges_id){
		if(charges_id!=null){
			if(!cLength(charges_id.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(charges_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

}