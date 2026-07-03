package db; 

import java.sql.*;

public class d_prioritydetails{
	
	public String priority,priority_val,is_official,is_private;
	
	ngm NGM;
	
	public d_prioritydetails(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into priority_details values(?,?,?,?)");
		ps.setInt(1,Integer.parseInt(priority));
		ps.setInt(2,Integer.parseInt(priority_val));
		ps.setString(3, is_official);
		ps.setString(4, is_private);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update priority_details set priority_val=?,is_official=?,is_private=? where private=?");
		ps.setInt(1,Integer.parseInt(priority_val));
		ps.setString(2, is_official);
		ps.setString(3, is_private);
		ps.setInt(4,Integer.parseInt(priority));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from priority_details where priority=?");
		ps.setInt(1,Integer.parseInt(priority));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from priority_details where priority=?");
		ps.setString(1, priority);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.priority=rs.getString("priority");
			this.priority_val=rs.getString("priority_val");
			this.is_official=rs.getString("is_official");
			this.is_private=rs.getString("is_private");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from priority_details where priority=?");
	ps.setInt(1,Integer.parseInt(priority));
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
	if(priority_val!=null) {
		if(!NGM.ins_val.cLength(priority_val.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(priority_val.toString()))
			throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails", p_method, NGM);	
	 }
	 else
			throw NGM.ins_utility.setException("m_prioritydetails", "Null prioritydetails", p_method, NGM);
	
	 if(is_official!=null) {
		if(!NGM.ins_val.cLength(is_official.toString(), 1, 2))
			throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(is_official.toString()))
			throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails", p_method, NGM);	
	 }
	 else
		 throw NGM.ins_utility.setException("m_prioritydetails", "Null prioritydetails", p_method, NGM);
	 
	 if(is_private!=null) {
			if(!NGM.ins_val.cLength(is_private.toString(), 1, 2))
				throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(is_private.toString()))
				throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_prioritydetails", "Null prioritydetails", p_method, NGM);
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(priority!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "priority_details", "priority", priority.toString(),NGM))
				throw NGM.ins_utility.setException("m_prioritydetails", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(priority.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_prioritydetails", "Invalid prioritydetails length", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(priority.toString()))
			throw NGM.ins_utility.setException("m_prioritydetails", "Invalid  prioritydetails", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_prioritydetails", "Null prioritydetails", p_method, NGM);
		  
}

}