package db; 

import java.sql.*;

import org.json.simple.JSONObject;

import common.db_validation;

public class d_guesthouse extends db_validation
{
	public String gh_id;
	public String gh_name;
	public String mode;
	public String heading1;
	public String heading2;
	public String heading3;
	public String heading4;
	ngm NGM;
	
	public d_guesthouse(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guest_house values(?,?,?,?,?,?,?)");
		ps.setString(1, gh_id);
		ps.setString(2, gh_name);
		ps.setString(3, mode);
		ps.setString(4, heading1);
		ps.setString(5, heading2);
		ps.setString(6, heading3);
		ps.setString(7, heading4);
		
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception 
	{
		boolean l_status=false;
		
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update guest_house set gh_name=?,mode=?,heading1=?,heading2=?,heading3=?,heading4=? where gh_id=?");
		ps.setString(1, gh_name);
		ps.setString(2, mode);
		ps.setString(3, heading1);
		ps.setString(4, heading2);
		ps.setString(5, heading3);
		ps.setString(6, heading4);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean checkghid()throws Exception
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_house where gh_id=?");
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
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from guest_house where gh_id=?");
		ps.setString(1, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean checkin_details() throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_house where gh_id=?");
		ps.setString(1, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean i_status=false;
		while(rs.next())
		{
			i_status=true;
		}
		ps.close();
		ps.close();
		return i_status;
	}
	
	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_house where gh_id=?");
		ps.setString(1, this.gh_id);
		
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next())
		{
			this.gh_id = rs.getString("gh_id");
			this.gh_name = rs.getString("gh_name");
			this.mode = rs.getString("mode");
			this.heading1 = rs.getString("heading1");
			this.heading2 = rs.getString("heading2");
			this.heading3 = rs.getString("heading3");
			this.heading4 = rs.getString("heading4");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	
	public void VAL_insertDB() throws Exception 
	{
			if(!(val_ghname(gh_name) &&  val_ghid(gh_id) &&  val_mode(mode) &&  val_heading1(heading1) &&  val_heading1(heading2) &&  val_heading1(heading3) &&  val_heading1(heading4))){
				throw NGM.ins_utility.setException("guesthouse", "Invalid guest house", "VAL_insertDB", NGM);
			}
	}
	public void VAL_updateDB() throws Exception 
	{
			if(!(val_ghname(gh_name) &&  val_ghid(gh_id) &&  val_mode(mode) &&  val_heading1(heading1) &&  val_heading1(heading2) &&  val_heading1(heading3) &&  val_heading1(heading4))){
				throw NGM.ins_utility.setException("guesthouse", "Invalid guest house", "VAL_insertDB", NGM);
			}
	}
//	public void VAL_insertDB() throws Exception 
//	{
//		 val_ghname(gh_name);
//		 val_ghid(gh_id);	 
//		 val_mode(mode);
//		 val_heading1(heading1);
//		 val_heading2(heading2);
//		 val_heading3(heading3);
//		 val_heading4(heading4);
//		
//	}
//	public void VAL_updateDB() throws Exception 
//	{
//		 val_ghname(gh_name);
//		 val_ghid(gh_id);	 
//		 val_mode(mode);
//		 val_heading1(heading1);
//		 val_heading2(heading2);
//		 val_heading3(heading3);
//		 val_heading4(heading4);
//		
//	}
	public void VAL_deleteDB() throws Exception 
	{
		 val_ghid(gh_id);	 
			
	}
	public boolean val_ghname(String gh_name){
		if(gh_name!=null){
			if(!cLength(gh_name.toString(), 0, 100)){
					return false;
				}
				if (!cAlphaNumericSpecial(gh_name," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_mode(String mode){
		if(mode!=null){
			if(!cLength(mode.toString(), 0, 10)){
					return false;
				}
				if (!cAlphabet(mode)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_heading1(String heading1){
		if(heading1!=null){
			if(!cLength(heading1.toString(), 0, 250)){
					return false;
				}
			if (!cAlphaNumericSpecial(heading1," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_heading2(String heading2){
		if(heading2!=null){
			if(!cLength(heading2.toString(), 0, 250)){
					return false;
				}
				if (!cAlphaNumericSpecial(heading2," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_heading3(String heading3){
		if(heading3!=null){
			if(!cLength(heading3.toString(), 0, 250)){
					return false;
				}
				if (!cAlphaNumericSpecial(heading3," . _")){
						return false;
				}
				return true;
		}else
			return false;
		
	}


	public boolean val_heading4(String heading4){
		if(heading4!=null){
			if(!cLength(heading4.toString(), 0, 250)){
					return false;
				}
				if (!cAlphaNumeric(heading4)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	
}