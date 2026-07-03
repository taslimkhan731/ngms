package db; 

import java.sql.*;

import common.db_validation;

public class d_conf_bill extends db_validation{
		  
	public String  conf_bill_id, conf_req_id, charge, additionalcharge, status, entrydate, gh_id;
	
	ngm NGM;
	
	public d_conf_bill(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_conf_bill VALUES (?, ?, ?, ?, now(), ?, ?)");
		ps.setString(1, conf_bill_id);
		ps.setString(2, conf_req_id);
		ps.setString(3, charge);
		ps.setString(4, status);
		ps.setString(5, gh_id);
		ps.setString(6, additionalcharge);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_conf_bill set  conf_req_id=?, charge=?, status=?, gh_id=?,additionalcharge=? WHERE conf_bill_id=?");
		ps.setString(1, conf_req_id);
		ps.setString(2, charge);
		ps.setString(3, status);
		ps.setString(4, gh_id);
		ps.setString(5, additionalcharge);
		ps.setString(6, conf_bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB_status() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_conf_bill set  status=? WHERE conf_bill_id=? and gh_id=?");
		ps.setString(1, status);
		ps.setString(2, conf_bill_id);
		ps.setString(3, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		VAL_deleteDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_conf_bill WHERE conf_bill_id");
		ps.setString(1, conf_bill_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_conf_bill where conf_bill_id=?");
		ps.setString(1, conf_bill_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			conf_req_id=rs.getString("conf_req_id");
			charge=rs.getString("charge");
			status=rs.getString("status");
			entrydate=rs.getString("entrydate");
			gh_id=rs.getString("gh_id");
			additionalcharge=rs.getString("additionalcharge");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean selectDB_reqid() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement pss = null;
		pss=NGM.tr_conn.prepareStatement("select * from tr_conf_bill where conf_req_id=? and status!='C'");
		pss.setString(1, conf_req_id);
		ResultSet rss=pss.executeQuery();
		boolean l_status=false;
		while(rss.next()){
			conf_bill_id=rss.getString("conf_bill_id");
			charge=rss.getString("charge");
			status=rss.getString("status");
			entrydate=rss.getString("entrydate");
			gh_id=rss.getString("gh_id");
			additionalcharge=rss.getString("additionalcharge");
			l_status=true;
		}
		rss.close();
		pss.close();
		return l_status;
	} 
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_id(conf_req_id);
		val_id(conf_bill_id);
		val_status(status);
		val_amount(charge);
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