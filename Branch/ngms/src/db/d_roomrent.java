package db;
import java.sql.*;
public class d_roomrent {
	public String priority,off_room_rent,off_bed_rent,pvt_room_rent,pvt_bed_rent;
	ngm NGM;
	public d_roomrent(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into room_rent values(?,?,?,?,?)");
		ps.setInt(1,Integer.parseInt(priority));
		ps.setInt(2,Integer.parseInt(off_room_rent));
		ps.setInt(3,Integer.parseInt(off_bed_rent));
		ps.setInt(4,Integer.parseInt(pvt_room_rent));
		ps.setInt(5,Integer.parseInt(pvt_bed_rent));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update into room_rent set off_room_rent=?,off_bed_rent=?,pvt_room_rent=?,pvt_bed_rent=? where priority=?");
		ps.setInt(1,Integer.parseInt(off_room_rent));
		ps.setInt(2,Integer.parseInt(off_bed_rent));
		ps.setInt(3,Integer.parseInt(pvt_room_rent));
		ps.setInt(4,Integer.parseInt(pvt_bed_rent));
		ps.setInt(5,Integer.parseInt(priority));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from room_rent where priority=?");
		ps.setInt(1,Integer.parseInt(priority));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_rent where priority=?");
		ps.setInt(1,Integer.parseInt(priority));
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.priority=rs.getString("priority");
			this.off_room_rent=rs.getString("off_room_rent");
			this.off_bed_rent=rs.getString("off_bed_rent");
			this.pvt_room_rent=rs.getString("pvt_room_rent");
			this.pvt_bed_rent=rs.getString("pvt_bed_rent");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_rent where priority=?");
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
	public void validate(String p_method) throws Exception 
	{
		 if(off_room_rent!=null)
		 {
			if(!NGM.ins_val.cLength(off_room_rent.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(off_room_rent.toString()))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomrent", "Null roomrent details", p_method, NGM);
		
		 if(off_bed_rent!=null)
		 {
			if(!NGM.ins_val.cLength(off_bed_rent.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(off_bed_rent.toString()))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomrent", "Null roomrent details", p_method, NGM);
		 
		 if(pvt_room_rent!=null)
		 {
			if(!NGM.ins_val.cLength(pvt_room_rent.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(pvt_room_rent.toString()))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomrent", "Null roomrent details", p_method, NGM);
		 
		 if(pvt_bed_rent!=null)
		 {
			if(!NGM.ins_val.cLength(pvt_bed_rent.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(pvt_bed_rent.toString()))
				throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_roomrent", "Null roomrent details", p_method, NGM);
		 
	}
	public void validate_key(String p_method) throws Exception 
	{	
		if(priority!=null){
		 		if(p_method=="insertDB"){
		 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "room_rent", "priority", priority.toString(),NGM))
		 				throw NGM.ins_utility.setException("m_roomrent", " Already existing", p_method, NGM);
		 		}
		 		if(!NGM.ins_val.cLength(priority.toString(), 1, 100))
		 			throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent length", p_method, NGM);
		 		else if (!NGM.ins_val.cAlphaNumeric(priority.toString()))
		 			throw NGM.ins_utility.setException("m_roomrent", "Invalid roomrent code", p_method, NGM);
		 	}
		 	else
		 		throw NGM.ins_utility.setException("m_roomrent", "Null roomrent code", p_method, NGM);
		
	 }	
}