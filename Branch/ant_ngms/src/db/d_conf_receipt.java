package db; 

import java.sql.*;

import common.db_validation;

public class d_conf_receipt extends db_validation{
	  
	public String  conf_receipt_id, conf_bill_id, payed, status, entrydate, gh_id, balance, conf_req_id;
	
	ngm NGM;
	
	public d_conf_receipt(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_conf_receipt VALUES (?, ?, ?, ?, now(), ?, ?,?)");
		ps.setString(1, conf_receipt_id);
		ps.setString(2, conf_bill_id);
		ps.setString(3, payed);
		ps.setString(4, status);
		ps.setString(5, gh_id);
		ps.setString(6, balance);
		ps.setString(7, conf_req_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_conf_receipt set  conf_req_id=?, payed=?, status=?, gh_id=?,balance=? WHERE conf_receipt_id=?");
		ps.setString(1, conf_bill_id);
		ps.setString(2, payed);
		ps.setString(3, status);
		ps.setString(4, gh_id);
		ps.setString(5, balance);
		ps.setString(6, conf_receipt_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB_status() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_conf_receipt set  status=? WHERE conf_receipt_id=? and gh_id=?");
		ps.setString(1, status);
		ps.setString(2, conf_receipt_id);
		ps.setString(3, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		VAL_deleteDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_conf_receipt WHERE conf_receipt_id");
		ps.setString(1, conf_receipt_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_conf_receipt where conf_receipt_id=?");
		ps.setString(1, conf_receipt_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			conf_bill_id=rs.getString("conf_bill_id");
			conf_req_id=rs.getString("conf_req_id");
			payed=rs.getString("payed");
			status=rs.getString("status");
			entrydate=rs.getString("entrydate");
			gh_id=rs.getString("gh_id");
			balance=rs.getString("balance");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean selectDB_reqid() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_conf_receipt where conf_req_id=? and status='A'");
		ps.setString(1, conf_req_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			conf_receipt_id=rs.getString("conf_receipt_id");
			conf_req_id=rs.getString("conf_req_id");
			payed=rs.getString("payed");
			status=rs.getString("status");
			entrydate=rs.getString("entrydate");
			gh_id=rs.getString("gh_id");
			balance=rs.getString("balance");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_id(conf_req_id);
		val_id(conf_bill_id);
		val_status(status);
		val_amount(payed);
		val_amount(balance);
		val_id(gh_id);
	}
	public void VAL_deleteDB() throws Exception {
		val_id(conf_bill_id);
	}
	public void VAL_selectDB() throws Exception {
		val_id(conf_bill_id);
	}
	public boolean val_entrydate(String entrydate){
		if(entrydate!=null){
			if(!cDateTime(entrydate.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	
}