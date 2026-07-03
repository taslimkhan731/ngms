package db; 

import java.sql.*;

import org.json.simple.JSONObject;

import common.db_validation;

public class d_checkin_reqid extends db_validation
{
	public String tr_id;
	public String req_id;
	public String status;
	ngm NGM;
	
	public d_checkin_reqid(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_checkin_reqid values(?,?,?,now())");
		ps.setString(1, tr_id);
		ps.setString(2, req_id);
		ps.setString(3, status);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_checkin_reqid where tr_id=?");
		ps.setString(1, tr_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean updateDB() throws Exception 
	{
		boolean l_status=false;
		
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update tr_checkin_reqid set status=?,lastupdated=now() where tr_id=? and req_id=?");
		ps.setString(1, status);
		ps.setString(2, tr_id);
		ps.setString(3, req_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean selectDB() throws Exception 
	{
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_reqid where tr_id=? and req_id? and status!='CAN'");
		ps.setString(1, tr_id);
		ps.setString(2, req_id);

		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next())
		{
			this.tr_id = rs.getString("tr_id");
			this.req_id = rs.getString("req_id");
			this.status = rs.getString("status");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	public void VAL_insertDB() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_status(status)&&val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_reqid", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
		}
		 /*val_seq_id(tr_id);
		 val_status(status);
		 val_reqid(req_id);*/
	}
	public void VAL_deleteDB() throws Exception 
	{
		if(!(val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_reqid", "Invalid tr_allotment parameter", "VAL_deleteDB", NGM);
		}
		// val_seq_id(tr_id);
	}
	public void VAL_updateDB() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_status(status)&&val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_reqid", "Invalid tr_allotment parameter", "VAL_updateDB", NGM);
		}
		/* val_seq_id(tr_id);
		 val_status(status);
		 val_reqid(req_id);*/
	}
	public void VAL_selectDB() throws Exception 
	{
		if(!(val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_reqid", "Invalid tr_allotment parameter", "VAL_selectDB", NGM);
		}
		 //val_seq_id(tr_id);
	}
}