package db; 

import java.sql.*;

import common.db_validation;

public class d_m_conf_hall_use extends db_validation{

	public String  conf_use_id, conf_use_desc, status, entrydate, gh_id;
	
	ngm NGM;
	
	public d_m_conf_hall_use(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO m_conf_hall_use VALUES (?, ?, ?, now(), ?)");
		ps.setString(1, conf_use_id);
		ps.setString(2, conf_use_desc);
		ps.setString(3, status);
		ps.setString(4, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE m_conf_hall_use set  conf_use_desc=?, status=?, entrydate=now(), gh_id=? WHERE conf_cat_id=?");
		ps.setString(1, conf_use_desc);
		ps.setString(2, status);
		ps.setString(3, gh_id);
		ps.setString(4, conf_use_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		VAL_deleteDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM m_conf_hall_use WHERE conf_use_id");
		ps.setString(1, conf_use_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_conf_hall_use where conf_use_id=?");
		ps.setString(1, conf_use_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			conf_use_desc=rs.getString("conf_use_desc");
			status=rs.getString("status");
			entrydate=rs.getString("entrydate");
			gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_id(conf_use_id);
		val_conf_cat_desc(conf_use_desc);
		val_status(status);
		val_id(gh_id);
	}
	public void VAL_deleteDB() throws Exception {
		val_id(conf_use_id);
	}
	public void VAL_selectDB() throws Exception {
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