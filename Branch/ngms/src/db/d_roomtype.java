package db; 

import java.sql.*;

public class d_roomtype{
	
	public String type_id,type_name,type_desc,nu_of_beds,type_cat;
	
	ngm NGM;
	
	public d_roomtype(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into room_type values(?,?,?,?,?)");
		ps.setInt(1,Integer.parseInt(type_id));
		ps.setString(2, type_name);
		ps.setString(3, type_desc);
		ps.setInt(4,Integer.parseInt(nu_of_beds));
		ps.setString(5, type_cat);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update room_type set type_name=?,type_desc=?,nu_of_beds=?,type_cat=? where type_id=?");
		ps.setString(1, type_name);
		ps.setString(2, type_desc);
		ps.setInt(3,Integer.parseInt(nu_of_beds));
		ps.setString(4, type_cat);
		ps.setInt(5,Integer.parseInt(type_id));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from room_type where type_id=?");
		ps.setInt(1,Integer.parseInt(type_id));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_type where type_id=?");
		ps.setInt(1,Integer.parseInt(type_id));
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.type_id=rs.getString("type_id");
			this.type_name=rs.getString("type_name");
			this.type_desc=rs.getString("type_desc");
			this.nu_of_beds=rs.getString("nu_of_beds");
			this.type_cat=rs.getString("type_cat");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_type where type_id=?");
	ps.setInt(1,Integer.parseInt(type_id));
	ResultSet rs=ps.executeQuery();
	boolean l_status=false;
	while(rs.next()){
			
		l_status=true;
	}
	rs.close();
	ps.close();
	return l_status;

}

//--------------------------------------------validation-----------------------------------------------------------------


public void validate(String p_method) throws Exception 
{
	
	 if(type_name!=null) {
		if(!NGM.ins_val.cLength(type_name.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(type_name.toString()))
			throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);	
	 }
	 else
			throw NGM.ins_utility.setException("m_roomtype", "Null roomtype", p_method, NGM);
	
	 if(type_desc!=null) {
		if(!NGM.ins_val.cLength(type_desc.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(type_desc.toString()))
			throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);	
	 }
	 else
		 throw NGM.ins_utility.setException("m_roomtype", "Null roomtype", p_method, NGM);
	
	 if(nu_of_beds!=null) {
			if(!NGM.ins_val.cLength(nu_of_beds.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(nu_of_beds.toString()))
				throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomtype", "Null roomtype", p_method, NGM);
		 
	 if(type_cat!=null) {
			if(!NGM.ins_val.cLength(type_cat.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(type_cat.toString()))
				throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomtype", "Null roomtype", p_method, NGM);
		 
	 
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(type_id!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_type", "type_id", type_id.toString(),NGM))
				throw NGM.ins_utility.setException("m_roomtype", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(type_id.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_roomtype", "Invalid roomtype length", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(type_id.toString()))
			throw NGM.ins_utility.setException("m_roomtype", "Invalid  roomtype", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_roomtype", "Null roomtype", p_method, NGM);
		  
}

}