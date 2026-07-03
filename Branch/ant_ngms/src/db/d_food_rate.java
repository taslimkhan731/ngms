package db; 

import java.sql.*;

import common.db_validation;

public class d_food_rate extends db_validation{
	
	public String  cat_id, item, purpose_visit, gh_id, eff_date_from, eff_date_to, lastupdate, status;
	public double rate;
	public int rate_id;
	ngm NGM;
	
	public d_food_rate(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO food_rate VALUES (?, ?, ?, ? ,?, ?, ?, to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), now())");
		ps.setInt(1, rate_id);
		ps.setString(2, cat_id);
		ps.setString(3, item);
		ps.setString(4, purpose_visit);
		ps.setDouble(5, rate);
		ps.setString(6, gh_id);
		ps.setString(7, status);
		ps.setString(8, eff_date_from);
		ps.setString(9, eff_date_to);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE food_rate set cat_id=?,  item=?, purpose_visit=?, rate=?, gh_id=?, status=?, eff_date_from=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), eff_date_to=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), lastupdate=now() WHERE rate_id=?");
		ps.setString(1, cat_id);
		ps.setString(2, item);
		ps.setString(3, purpose_visit);
		ps.setDouble(4, rate);
		ps.setString(5, gh_id);
		ps.setString(6, status);
		ps.setString(7, eff_date_from);
		ps.setString(8, eff_date_to);
		ps.setInt(9, rate_id);
		System.out.println("psssssssss:"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM food_rate WHERE rate_id=?");
		ps.setInt(1, rate_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from food_rate where rate_id=?");
		ps.setInt(1, rate_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			
			cat_id=rs.getString("cat_id");
			item=rs.getString("item");
			purpose_visit=rs.getString("purpose_visit");
			rate=rs.getDouble("rate");
			gh_id=rs.getString("gh_id");
			status=rs.getString("status");
			eff_date_from=rs.getString("eff_date_from");
			eff_date_to=rs.getString("eff_date_to");
			lastupdate=rs.getString("lastupdate");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	 public boolean getDBisExist(){
		 boolean status = false;
		 ResultSet rs= null;
		 try{
			 PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from food_rate where cat_id=? and item=? and gh_id=?");
			 ps.setString(1,cat_id);
			 ps.setString(2,item);
			 ps.setString(3,gh_id);
			 rs = ps.executeQuery();
			 while(rs.next()){
				
				 status = true;
			 }
			 ps.close();
			 rs.close();
		 }catch (SQLException e){
			 e.printStackTrace();
		 }
		 return status;
	 }
	 public boolean getDBisExistforupdate(){
		 boolean status = false;
		 ResultSet rs= null;
		 try{
			 PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from food_rate where cat_id=? and item=? and gh_id=? and rate_id!=? and rate_id!=? and rate_id!=?");
			 ps.setString(1,cat_id);
			 ps.setString(2,item);
			 ps.setString(3,gh_id);
			 ps.setInt(4,rate_id);
			 ps.setInt(5,rate_id+1);
			 ps.setInt(6,rate_id+2);
			 System.out.println("ps"+ps);
			 rs = ps.executeQuery();
			 while(rs.next()){
			
				 status = true;
			 }
			 ps.close();
			 rs.close();
		 }catch (SQLException e){
			 e.printStackTrace();
		 }
		 return status;
	 }
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		
		val_cat_id(cat_id);
		val_item(item);
		val_name(purpose_visit);
		val_amount(String.valueOf(rate));
		val_ghid(gh_id);
		val_status(status);
		val_effect_date(eff_date_from);
		val_effect_date(eff_date_to);
		val_lastupdated(lastupdate);
		
	}
	//cat_id	
	public boolean val_cat_id(String cat_id){
		if(cat_id!=null){
			
				if (!cNumeric(cat_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_effect_date(String eff_date){
		if(eff_date!=null){
			if(!cDateTime(eff_date.toString(),"yyyy-MM-dd HH:mm:ss")){
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