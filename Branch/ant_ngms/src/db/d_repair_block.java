package db;
import java.sql.*;

import model.ngmodel;
public class d_repair_block extends ngmodel{
	public String gh_id,block_id;
	public int nu_of_repair_rooms=0;
	
	ngm NGM;
	public d_repair_block(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into m_repair_block values(?,?,?)");
		ps.setString(1,gh_id);
		ps.setString(2,block_id);
		ps.setInt(3,nu_of_repair_rooms);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update m_repair_block set nu_of_repair_rooms=? where block_id=? and gh_id=?");
		ps.setInt(1,nu_of_repair_rooms);
		ps.setString(2,block_id);
		ps.setString(3,gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_repair_block where block_id=? and gh_id=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_repair_block where block_id=? and gh_id=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.block_id=rs.getString("block_id");
			this.gh_id=rs.getString("gh_id");
			this.nu_of_repair_rooms=rs.getInt("nu_of_repair_rooms");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_repair_block where block_id=? and gh_id=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
				
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;

	}
	
	public int selectDBrooms() throws Exception 
	{
		nu_of_repair_rooms = 0;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select nu_of_repair_rooms from m_repair_block where block_id=? and gh_id=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		System.out.println("ps...................... "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		 
		while(rs.next()){
			nu_of_repair_rooms=rs.getInt("nu_of_repair_rooms");
			l_status=true;
			System.out.println(" ---- nu_of_repair_rooms ----- "+nu_of_repair_rooms);
		}
		System.out.println(" ---- gh_id ----- "+gh_id);
		System.out.println(" ---- block_id ----- "+block_id);
		System.out.println(" ---- nu_of_repair_rooms ----- "+nu_of_repair_rooms);
		rs.close();
		ps.close();
		System.out.println("AAAAA DBBBB   "+nu_of_repair_rooms);
		return nu_of_repair_rooms;
	} 
	 
	
	
}