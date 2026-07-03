package db; 

import java.sql.*;

import common.db_validation;

public class d_conf_request extends db_validation{
	  
	public String  conf_req_id, conf_cat_id, fromdate, todate, conf_use_id, additional, status, entrydate, name, address, gh_id,sel_block;
	
	ngm NGM;
	
	public d_conf_request(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_conf_request VALUES (?, ?, to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), ?, ?, ?, now(), ?, ?, ?,?)");
		ps.setString(1, conf_req_id);
		ps.setString(2, conf_cat_id);
		ps.setString(3, fromdate);
		ps.setString(4, todate);
		ps.setString(5, conf_use_id);
		ps.setString(6, additional);
		ps.setString(7, status);
		ps.setString(8, name);
		ps.setString(9, address);
		ps.setString(10, gh_id);
		ps.setString(11, sel_block);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_conf_request set  conf_cat_id=?, fromdate=?, todate=?, conf_use_id=?, additional=?, status=?, name=?, address=?, gh_id=? WHERE conf_req_id=?");
		ps.setString(1, conf_cat_id);
		ps.setString(2, fromdate);
		ps.setString(3, todate);
		ps.setString(4, conf_use_id);
		ps.setString(5, additional);
		ps.setString(6, status);
		ps.setString(7, name);
		ps.setString(8, address);
		ps.setString(9, gh_id);
		ps.setString(10, conf_req_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB_status() throws Exception
	{
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_conf_request set  status=? WHERE conf_req_id=? and gh_id=?");
		ps.setString(1, status);
		ps.setString(2, conf_req_id);
		ps.setString(3, gh_id);
		System.out.println("ps.........  updateDB_status  "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		VAL_deleteDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_conf_request WHERE conf_req_id");
		ps.setString(1, conf_req_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_conf_request where conf_req_id=?");
		ps.setString(1, conf_req_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			conf_cat_id=rs.getString("conf_cat_id");
			fromdate=rs.getString("fromdate");
			todate=rs.getString("todate");
			conf_use_id=rs.getString("conf_use_id");
			additional=rs.getString("additional");
			status=rs.getString("status");
			name=rs.getString("name");
			address=rs.getString("address");
			gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		
		val_id(conf_req_id);
		val_id(conf_cat_id);
		val_fromdate(fromdate);
		val_fromdate(todate);
		val_id(conf_use_id);
		val_address(additional);
		val_status(status);
		val_name(name);
		val_address(address);
		val_id(gh_id);
	}
	public void VAL_deleteDB() throws Exception {
		val_id(conf_req_id);
	}
	public void VAL_selectDB() throws Exception {
		val_id(conf_req_id);
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