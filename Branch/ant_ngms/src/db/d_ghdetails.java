package db; 

import java.sql.*;

import org.json.simple.JSONObject;

import common.db_validation;

public class d_ghdetails extends db_validation
{
	public String gh_id;
	public String block_id;
	public String block_name;
	public String gh_name;
	public String street_name;
	public String city_town_name;
	public String district_name;
	public String state_name;
	public String pin_code;
	ngm NGM;
	
	public d_ghdetails(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gh_details values(?,?,?,?,?,?,?)");
		ps.setString(1, gh_name);
		ps.setString(2, street_name);
		ps.setString(3, city_town_name);
		ps.setString(4, district_name);
		ps.setString(5, state_name);
		ps.setString(6, pin_code);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception 
	{
		boolean l_status=false;
		
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update gh_details set gh_name=?,street_name=?,city_town_name=?,district_name=?,state_name=?,pin_code=? where gh_id=?");
		ps.setString(1, gh_name);
		ps.setString(2, street_name);
		ps.setString(3, city_town_name);
		ps.setString(4, district_name);
		ps.setString(5, state_name);
		ps.setString(6, pin_code);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean checkghid()throws Exception
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gh_details where gh_id=?");
		ps.setString(1, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
				
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;

	}
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from gh_details where gh_id=?");
		ps.setString(1, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean checkin_details() throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gh_details where gh_id=?");
		ps.setString(1, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean i_status=false;
		while(rs.next())
		{
			i_status=true;
		}
		rs.close();
		ps.close();
		return i_status;
	}
	
	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select gh_details.*,room_block.block_name from gh_details , room_block where gh_details.gh_id=room_block.gh_id AND gh_details.gh_id=? AND room_block.block_id=?");
		ps.setString(1, this.gh_id);
		ps.setString(2, this.block_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next())
		{
			this.gh_id = rs.getString("gh_id");
			this.gh_name = rs.getString("gh_name");
			this.block_name = rs.getString("block_name");
			this.street_name = rs.getString("street_name");
			this.city_town_name = rs.getString("city_town_name");
			this.district_name = rs.getString("district_name");
			this.state_name = rs.getString("state_name");
			this.pin_code = rs.getString("pin_code");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	public void VAL_insertDB() throws Exception 
	{
		 val_ghname(gh_name);
		 val_ghid(gh_id);	 
		 val_streetname(street_name);
		 val_city_town_name(city_town_name);
		 val_district_name(district_name);
		 val_state_name(state_name);
		 val_pin_code(pin_code);
		
	}
	public void VAL_updateDB() throws Exception 
	{
		 val_ghname(gh_name);
		 val_ghid(gh_id);	 
		 val_streetname(street_name);
		 val_city_town_name(city_town_name);
		 val_district_name(district_name);
		 val_state_name(state_name);
		 val_pin_code(pin_code);
		
	}
	public void VAL_deleteDB() throws Exception 
	{
		 val_ghid(gh_id);	 
			
	}
	public boolean val_ghname(String gh_name){
		if(gh_name!=null){
			if(!cLength(gh_name.toString(), 0, 25)){
					return false;
				}
				if (!cAlphaNumericSpecial(gh_name," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_streetname(String street_name){
		if(street_name!=null){
			if(!cLength(street_name.toString(), 0, 25)){
					return false;
				}
				if (!cAlphaNumericSpecial(street_name," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_city_town_name(String city_town_name){
		if(city_town_name!=null){
			if(!cLength(city_town_name.toString(), 0, 25)){
					return false;
				}
			if (!cAlphaNumericSpecial(city_town_name," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_district_name(String district_name){
		if(district_name!=null){
			if(!cLength(district_name.toString(), 0, 25)){
					return false;
				}
				if (!cAlphaNumericSpecial(district_name," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_state_name(String state_name){
		if(state_name!=null){
			if(!cLength(state_name.toString(), 0, 25)){
					return false;
				}
				if (!cAlphaNumericSpecial(state_name," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}


	public boolean val_pin_code(String pin_code){
		if(pin_code!=null){
			if(!cLength(pin_code.toString(), 0, 9)){
					return false;
				}
				if (!cNumeric(pin_code)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	
}