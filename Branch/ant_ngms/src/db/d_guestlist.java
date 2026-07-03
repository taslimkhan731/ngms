package db; 

import java.sql.*;

public class d_guestlist
{
	public String pid,guest_id,guest_name,guest_age,guest_gender,guest_relation,p_of_visit,a_id;
	
	ngm NGM;
	
	public d_guestlist(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guestlist values(?,?,?,?,?,?,?,?)");
		ps.setString(1, pid);
		ps.setString(2, guest_id);
		ps.setString(3, guest_name);
		ps.setString(4, guest_age);
		ps.setString(5, guest_gender);
		ps.setString(6, guest_relation);
		ps.setString(7, p_of_visit);
		ps.setString(8,	a_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception 
	{
		boolean l_status=false;
		
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update guestlist set guest_name=?,guest_age=?,guest_gender=?,guest_relation=?,p_of_visit=?,a_id=? where gh_id=? and guest_id=?");
		ps.setString(1, guest_name);
		ps.setString(2, guest_age);
		ps.setString(3, guest_gender);
		ps.setString(4, guest_relation);
		ps.setString(5, p_of_visit);
		ps.setString(6,	a_id);
		ps.setString(7, pid);
		ps.setString(8, guest_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from guestlist where pid=? and guest_id=?");
		ps.setString(1, pid);
		ps.setString(2, guest_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean checkin_details() throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guestlist where pid=? and guest_id=?");
		ps.setString(1, pid);
		ps.setString(2, guest_id);
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
		validate_key("selectDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guestlist where pid=? and guest_id=?");
		ps.setString(1, pid);
		ps.setString(2, guest_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next())
		{
			this.pid = rs.getString("pid");
			this.guest_id = rs.getString("guest_id");
			this.guest_name = rs.getString("guest_name");
			this.guest_age = rs.getString("guest_age");
			this.guest_gender = rs.getString("guest_gender");
			this.guest_relation = rs.getString("guest_relation");
			this.p_of_visit = rs.getString("p_of_visit");
			this.a_id=rs.getString("a_id");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	
	public void validate(String p_method) throws Exception 
	{
		 if(guest_name!=null)
		 {
			if(!NGM.ins_val.cLength(guest_name.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(guest_name.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_guestlist", "Null guest list", p_method, NGM);
		
		 if(guest_age!=null)
		 {
			 if(!NGM.ins_val.cLength(guest_age.toString(), 1, 1000))
				 throw NGM.ins_utility.setException("m_guestlist", "Invalid guestlist length", p_method, NGM);
			 else if (!NGM.ins_val.cAlphaNumeric(guest_age.toString()))
				 throw NGM.ins_utility.setException("m_guestlist", "Invalid guestlist", p_method,NGM);
		 }	
		 else{
			 NGM.ins_writer.write("Message: Department code is null ");
			 throw NGM.ins_utility.setException("m_guestlist", "Null guestlist", p_method, NGM);
		 }
	
		 if(guest_gender!=null)
		 {
			if(!NGM.ins_val.cLength(guest_gender.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(guest_gender.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_guestlist", "Null guest list", p_method, NGM);
		 
		 if(guest_relation!=null)
		 {
			if(!NGM.ins_val.cLength(guest_relation.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(guest_relation.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_guestlist", "Null guest list", p_method, NGM);
		 if(p_of_visit!=null)
		 {
			if(!NGM.ins_val.cLength(p_of_visit.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(p_of_visit.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_guestlist", "Null guest list", p_method, NGM);
	
		 if(a_id!=null)
		 {
			if(!NGM.ins_val.cLength(a_id.toString(), 1, 6))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(a_id.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guest list", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_guestlist", "Null guest list", p_method, NGM);
		 
	}
	public void validate_key(String p_method) throws Exception 
	{	
		if(pid!=null){
			if(p_method=="insertDB"){
				if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "guestlist", "pid", pid.toString(),NGM))
					throw NGM.ins_utility.setException("m_guestlist", " Already existing", p_method, NGM);
			}
			if(!NGM.ins_val.cLength(pid.toString(), 1, 1000))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guestlist code length", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(pid.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guestlist code", p_method, NGM);
		}
		else
			throw NGM.ins_utility.setException("m_guestlist", "Null guestlist code", p_method, NGM);
		
		if(guest_id!=null){
			if(p_method=="insertDB"){
				if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "guestlist", "guest_id", guest_id.toString(),NGM))
					throw NGM.ins_utility.setException("m_guestlist", " Already existing", p_method, NGM);
			}
			if(!NGM.ins_val.cLength(guest_id.toString(), 1, 1000))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guestlist code length", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(guest_id.toString()))
				throw NGM.ins_utility.setException("m_guestlist", "Invalid guestlist code", p_method, NGM);
		}
		else
			throw NGM.ins_utility.setException("m_guestlist", "Null guestlist code", p_method, NGM);
		
	}
			
}