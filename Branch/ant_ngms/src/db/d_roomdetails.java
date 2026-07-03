package db;
import java.sql.*;
public class d_roomdetails {
	public String room_no,room_name,room_type,gh_id,block_id,floor_no,max_no_beds,room_rent;
	ngm NGM;
	public d_roomdetails(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into room_details values(?,?,?,?,?,?,?)");
		ps.setString(1,room_no);
		ps.setString(2,room_name);
		ps.setString(3,room_type);
		ps.setString(4,gh_id);
		ps.setString(5,block_id);
		ps.setString(6,floor_no);
		ps.setString(7,max_no_beds);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE room_details SET room_name=?, room_type=?,floor_no=?, max_no_beds=? WHERE room_no=? and gh_id=? and block_id=?");
		ps.setString(1,room_name);
		ps.setString(2,room_type);
		ps.setString(3,floor_no);
		ps.setString(4,max_no_beds);
		ps.setString(5,room_no);
		ps.setString(6,gh_id);
		ps.setString(7,block_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM room_details WHERE room_no=?");
		ps.setString(1,room_no);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 	
	
	public boolean deleteroomDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM room_details WHERE room_no=? and block_id=?");
		ps.setString(1,room_no);
		ps.setString(2,block_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 	
	
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_details where gh_id=? and block_id=? and room_no=?");
		ps.setString(1,gh_id);
		ps.setString(2,block_id);
		ps.setString(3,room_no);

		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.room_name=rs.getString("room_name");
			this.room_type=rs.getString("room_type");
			this.floor_no=rs.getString("floor_no");
			this.max_no_beds=rs.getString("max_no_beds");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
//-------------------------------------------------------------------------------------Validation---------------------------------------------------------------------------	
	
}