package db; 

import java.sql.*;

import common.db_validation;

public class d_dues extends db_validation{
	
	public String  due_id, rec_id, date_of_due, lastupdated, status, balance,block_id;
	
	ngm NGM;
	
	public d_dues(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_dues VALUES (?, ?, now() ,now() ,? ,?,?)");
		ps.setString(1, due_id);
		ps.setString(2, rec_id);
		/*ps.setString(3, date_of_due);
		ps.setString(4, lastupdated);*/
		ps.setString(3, status);
		ps.setString(4, balance);
		ps.setString(5, block_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_dues set  rec_id=?, date_of_due=?, lastupdated=?, status=?, balance=? WHERE due_id=?");
		ps.setString(1, rec_id);
		ps.setString(2, date_of_due);
		ps.setString(3, lastupdated);
		ps.setString(4, status);
		ps.setString(5, balance);
		ps.setString(6, due_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean updateDBstatus() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_dues set lastupdated=now(), status=? WHERE due_id=?");
		ps.setString(1, status);
		ps.setString(2, due_id);
		System.out.println("UPPPPPPPPPPPPPPPPPPPPPPPP "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDBAmtstatus() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_dues set lastupdated=now(), balance=?, status=? WHERE rec_id=?");
		ps.setString(1, balance);
		ps.setString(2, status);
		ps.setString(3, rec_id);
		System.out.println("UPPPPPPPPPPPPPPPPPPPPPPPP "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_dues WHERE due_id");
		ps.setString(1, due_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_dues where due_id=?");
		ps.setString(1, due_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			rec_id=rs.getString("rec_id");
			date_of_due=rs.getString("date_of_due");
			lastupdated=rs.getString("lastupdated");
			status=rs.getString("status");
			balance=rs.getString("balance");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean deleteDuest() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_dues WHERE rec_id = ?");
		ps.setString(1, rec_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_rec_id(balance);
		val_status(status);
		val_date_of_due(date_of_due);
		val_lastupdated(lastupdated);
		val_amount(balance);
	}
	public boolean val_date_of_due(String date_of_due){
		if(date_of_due!=null){
			if(!cDateTime(date_of_due.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_lastupdated(String lastupdated){
		if(lastupdated!=null){
			if(!cDateTime(lastupdated.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}

}