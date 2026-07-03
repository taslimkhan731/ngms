package db; 

import java.sql.*;

import common.db_validation;

public class d_food_category extends db_validation{
	
	public String  cat_id, description, status, gh_id;
	ngm NGM;
	
	public d_food_category(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO food_category VALUES (?, ?, ?, ?)");
		ps.setString(1, cat_id);
		ps.setString(2, description);
		ps.setString(3, status);
		ps.setString(4, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE food_category set description=?, status=?, gh_id=? WHERE cat_id=?");
		ps.setString(1, description);
		ps.setString(2, status);
		ps.setString(3, gh_id);
		ps.setString(4, cat_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM food_category WHERE cat_id=?");
		ps.setString(1, cat_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from food_category where cat_id=?");
		ps.setString(1, cat_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			description=rs.getString("description");
			status=rs.getString("status");
			gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
/*	public void VAL_insertDB() throws Exception {
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
		
	}*/

}