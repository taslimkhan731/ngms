package db; 

import java.sql.*;

import common.db_validation;

public class d_m_conf_hall_category extends db_validation{

	public String  conf_cat_id, conf_cat_desc, type, status, entrydate, govtorderno, gh_id;
	
	ngm NGM;
	
	public d_m_conf_hall_category(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO m_conf_hall_category VALUES (?, ?, ? ,? now(), ?, ?)");
		ps.setString(1, conf_cat_id);
		ps.setString(2, conf_cat_desc);
		ps.setString(3, type);
		ps.setString(4, status);
		ps.setString(5, govtorderno);
		ps.setString(6, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE m_conf_hall_category set  conf_cat_desc=?, type=?, status=?, entrydate=now(), govtorderno=?, gh_id=? WHERE conf_cat_id=?");
		ps.setString(1, conf_cat_desc);
		ps.setString(2, type);
		ps.setString(3, status);
		ps.setString(4, govtorderno);
		ps.setString(5, gh_id);
		ps.setString(6, conf_cat_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		VAL_deleteDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM m_conf_hall_category WHERE conf_cat_id");
		ps.setString(1, conf_cat_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_conf_hall_category where conf_cat_id=?");
		ps.setString(1, conf_cat_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			conf_cat_desc=rs.getString("conf_cat_desc");
			type=rs.getString("type");
			status=rs.getString("status");
			entrydate=rs.getString("entrydate");
			govtorderno=rs.getString("govtorderno");
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
		val_conf_cat_desc(conf_cat_desc);
		val_id(type);
		val_status(status);
		val_amount(govtorderno);
		val_id(gh_id);
	}
	public void VAL_deleteDB() throws Exception {
		val_id(conf_cat_id);
	}
	public void VAL_selectDB() throws Exception {
		val_id(conf_cat_id);
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