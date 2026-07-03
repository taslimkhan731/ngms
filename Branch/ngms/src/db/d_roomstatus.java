package db;
import java.sql.*;
public class d_roomstatus {
	public String gh_id,block_id,r_no,from_date,to_date,status,remarks,rs_id,pid,guest_id,gender;
	ngm NGM;
	public d_roomstatus(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into room_status values(?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,Integer.parseInt(gh_id));
		ps.setString(2,block_id);
		ps.setString(3,r_no);
		ps.setString(4,from_date);
		ps.setString(5,to_date);
		ps.setString(6,status);
		ps.setString(7,remarks);
		ps.setInt(8,Integer.parseInt(rs_id));
		ps.setInt(9,Integer.parseInt(pid));
		ps.setString(10,guest_id);
		ps.setString(11,gender);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update room_status set status=?,remarks=?,rs_id=?,pid=?,guest_id=?,gender=? where gh_id=? and block_id=? and r_no=? and from_date=? and to_date=?");
		ps.setString(1,status);
		ps.setString(2,remarks);
		ps.setInt(3,Integer.parseInt(rs_id));
		ps.setInt(4,Integer.parseInt(pid));
		ps.setString(5,guest_id);
		ps.setString(6,gender);
		ps.setInt(7,Integer.parseInt(gh_id));
		ps.setString(8,block_id);
		ps.setString(9,r_no);
		ps.setString(10,from_date);
		ps.setString(11,to_date);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from room_status where gh_id=? and block_id=? and r_no=? and from_date=? and to_date=?");
		ps.setInt(1,Integer.parseInt(gh_id));
		ps.setString(2,block_id);
		ps.setString(3,r_no);
		ps.setString(4,from_date);
		ps.setString(5,to_date);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_status where gh_id=? and block_id=? and r_no=? and from_date=? and to_date=?");
		ps.setInt(1,Integer.parseInt(gh_id));
		ps.setString(2,block_id);
		ps.setString(3,r_no);
		ps.setString(4,from_date);
		ps.setString(5,to_date);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.gh_id=rs.getString("gh_id");
			this.block_id=rs.getString("block_id");
			this.r_no=rs.getString("r_no");
			this.from_date=rs.getString("from_date");
			this.to_date=rs.getString("to_date");
			this.status=rs.getString("status");
			this.remarks=rs.getString("remarks");
			this.rs_id=rs.getString("rs_id");
			this.pid=rs.getString("pid");
			this.guest_id=rs.getString("guest_id");
			this.gender=rs.getString("gender");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_status where gh_id=? and block_id=? and r_no=? and from_date=? and to_date=?");
		ps.setInt(1,Integer.parseInt(gh_id));
		ps.setString(2,block_id);
		ps.setString(3,r_no);
		ps.setString(4,from_date);
		ps.setString(5,to_date);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
				
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;

	}
	public void validate(String p_method) throws Exception 
	{
		 if(status!=null)
		 {
			if(!NGM.ins_val.cLength(status.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(status.toString()))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus details", p_method, NGM);
		
		 if(remarks!=null)
		 {
			if(!NGM.ins_val.cLength(remarks.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(remarks.toString()))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus details", p_method, NGM);
		 
		 if(rs_id!=null)
		 {
			if(!NGM.ins_val.cLength(rs_id.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(rs_id.toString()))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus details", p_method, NGM);
		 
		 if(pid!=null)
		 {
			if(!NGM.ins_val.cLength(pid.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(pid.toString()))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus details", p_method, NGM);
		 
		 if(guest_id!=null)
		 {
			if(!NGM.ins_val.cLength(guest_id.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(guest_id.toString()))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus details", p_method, NGM);
		 
		 if(gender!=null)
		 {
			if(!NGM.ins_val.cLength(gender.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(gender.toString()))
				throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus details", p_method, NGM);
			 
	}
	public void validate_key(String p_method) throws Exception 
	{	
		if(gh_id!=null){
		 		if(p_method=="insertDB"){
		 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_status", "gh_id", gh_id.toString(),NGM))
		 				throw NGM.ins_utility.setException("m_roomstatus", " Already existing", p_method, NGM);
		 		}
		 		if(!NGM.ins_val.cLength(gh_id.toString(), 1, 100))
		 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus length", p_method, NGM);
		 		else if (!NGM.ins_val.cNumeric(gh_id.toString()))
		 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus code", p_method, NGM);
		 }
		 else
		 		throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus code", p_method, NGM);
		
		if(block_id!=null){
	 		if(p_method=="insertDB"){
	 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_status", "block_id", block_id.toString(),NGM))
	 				throw NGM.ins_utility.setException("m_roomstatus", " Already existing", p_method, NGM);
	 		}
	 		if(!NGM.ins_val.cLength(block_id.toString(), 1, 100))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus length", p_method, NGM);
	 		else if (!NGM.ins_val.cAlphaNumeric(block_id.toString()))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus code", p_method, NGM);
	 	}
	 	else
	 		throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus code", p_method, NGM);
	
		if(r_no!=null){
	 		if(p_method=="insertDB"){
	 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_status", "r_no", r_no.toString(),NGM))
	 				throw NGM.ins_utility.setException("m_roomstatus", " Already existing", p_method, NGM);
	 		}
	 		if(!NGM.ins_val.cLength(r_no.toString(), 1, 100))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus length", p_method, NGM);
	 		else if (!NGM.ins_val.cAlphaNumeric(r_no.toString()))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus code", p_method, NGM);
	 	}
	 	else
	 		throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus code", p_method, NGM);
	
		if(from_date!=null){
	 		if(p_method=="insertDB"){
	 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_status", "from_date", from_date.toString(),NGM))
	 				throw NGM.ins_utility.setException("m_roomstatus", " Already existing", p_method, NGM);
	 		}
	 		if(!NGM.ins_val.cLength(from_date.toString(), 1, 100))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus length", p_method, NGM);
	 		else if (!NGM.ins_val.cAlphaNumeric(from_date.toString()))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus code", p_method, NGM);
	 	}
	 	else
	 		throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus code", p_method, NGM);
	
		if(to_date!=null){
	 		if(p_method=="insertDB"){
	 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_status", "to_date", to_date.toString(),NGM))
	 				throw NGM.ins_utility.setException("m_roomstatus", " Already existing", p_method, NGM);
	 		}
	 		if(!NGM.ins_val.cLength(to_date.toString(), 1, 100))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus length", p_method, NGM);
	 		else if (!NGM.ins_val.cAlphaNumeric(to_date.toString()))
	 			throw NGM.ins_utility.setException("m_roomstatus", "Invalid roomstatus code", p_method, NGM);
	 	}
	 	else
	 		throw NGM.ins_utility.setException("m_roomstatus", "Null roomstatus code", p_method, NGM);
	
	 }	
}