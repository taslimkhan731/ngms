
package db; 

import java.sql.*;

import common.db_validation;

public class d_m_food_items extends db_validation{
	
	public String   item_title, unitprice, item_id;
	
	ngm NGM;
	
	public d_m_food_items(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO m_food_items VALUES ( ?, ?, ?)");
		ps.setString(1, item_id);
		ps.setString(2, item_title);
		ps.setString(3, unitprice);
		
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE m_food_items set unitprice=? ,item_title=? WHERE item_id=?");
		ps.setString(1, unitprice);
		ps.setString(2, item_title);
		ps.setString(3, item_id);
		System.out.println("psssssssss:"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM m_food_items WHERE item_id=?");
		ps.setString(1, item_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_food_items where item_id=?");
		ps.setString(1, item_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			
			item_id=rs.getString("item_id");
			item_title=rs.getString("item_title");
			unitprice=rs.getString("unitprice");
			
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
			 PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_food_items where  item_title=? ");
			 ps.setString(1,item_title);
			
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
//	 public boolean getDBisExistforupdate(){
//		 boolean status = false;
//		 ResultSet rs= null;
//		 try{
//			 PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from food_rate where cat_id=? and item=? and gh_id=? and rate_id!=? and rate_id!=? and rate_id!=?");
//			 ps.setString(1,cat_id);
//			 ps.setString(2,item);
//			 ps.setString(3,gh_id);
//			 ps.setInt(4,rate_id);
//			 ps.setInt(5,rate_id+1);
//			 ps.setInt(6,rate_id+2);
//			 System.out.println("ps"+ps);
//			 rs = ps.executeQuery();
//			 while(rs.next()){
//			
//				 status = true;
//			 }
//			 ps.close();
//			 rs.close();
//		 }catch (SQLException e){
//			 e.printStackTrace();
//		 }
//		 return status;
//	 }
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		
//		val_cat_id(item_id);
		val_item(item_title);
		val_amount(String.valueOf(unitprice));
		
		
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