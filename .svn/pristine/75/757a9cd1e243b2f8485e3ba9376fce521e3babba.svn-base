package db; 

import java.sql.*;

import common.db_validation;

public class d_m_conf_hall_charge extends db_validation{

	public String  conf_cat_id, conf_use_id, charge, status, entrydate, gh_id;
	
	ngm NGM;
	
	public d_m_conf_hall_charge(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO m_conf_hall_charge VALUES (?, ?, ? ,? now(), ?)");
		ps.setString(1, conf_cat_id);
		ps.setString(2, conf_use_id);
		ps.setString(3, charge);
		ps.setString(4, status);
		ps.setString(5, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE m_conf_hall_charge set  charge=?, status=?, entrydate=now(), gh_id=? WHERE conf_use_id=? and conf_cat_id=?");
		ps.setString(1, charge);
		ps.setString(2, status);
		ps.setString(3, gh_id);
		ps.setString(4, conf_use_id);
		ps.setString(5, conf_cat_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		VAL_deleteDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM m_conf_hall_charge WHERE conf_use_id=? and conf_cat_id");
		ps.setString(1, conf_use_id);
		ps.setString(2, conf_cat_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_conf_hall_charge where conf_use_id=? and conf_cat_id=?");
		ps.setString(1, conf_use_id);
		ps.setString(2, conf_cat_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			status=rs.getString("status");
			entrydate=rs.getString("entrydate");
			charge=rs.getString("charge");
			gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_id(conf_cat_id);
		val_id(conf_use_id);
		val_id(charge);
		val_status(status);
		val_id(gh_id);
	}
	public void VAL_deleteDB() throws Exception {
		val_id(conf_cat_id);
		val_id(conf_use_id);
	}
	public void VAL_selectDB() throws Exception {
		val_id(conf_cat_id);
		val_id(conf_use_id);
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