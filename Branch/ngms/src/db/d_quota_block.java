package db;
import java.sql.*;

import model.ngmodel;
public class d_quota_block extends ngmodel{
	public String gh_id,block_id,quota_type;
	public int max_beds,nu_of_rooms,dormitory_gents,dormitory_ladies;
	
	ngm NGM;
	public d_quota_block(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into m_quota_block values(?,?,?,?,?,?,?)");
		ps.setString(1,gh_id);
		ps.setString(2,block_id);
		ps.setString(3,quota_type);
		ps.setInt(4,nu_of_rooms);
		ps.setInt(5,max_beds);
		ps.setInt(6,dormitory_gents);
		ps.setInt(7,dormitory_ladies);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update m_quota_block set gh_id=?,nu_of_rooms=?,max_beds=?,dormitory_gents=?,dormitory_ladies=? where block_id=? and quota_type=?");
		ps.setString(1,gh_id);
		ps.setInt(2,nu_of_rooms);
		ps.setInt(3,max_beds);
		ps.setInt(4,dormitory_gents);
		ps.setInt(5,dormitory_ladies);
		ps.setString(6,block_id);
		ps.setString(7,quota_type);
		System.out.println("Uopadet        "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		System.out.println("Uopadet        "+ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_quota_block where block_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,quota_type);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	
	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_quota_block where block_id=? and gh_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ps.setString(3,quota_type);
		System.out.println("chkCodeDB     "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_quota_block where block_id=? and gh_id=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.block_id=rs.getString("block_id");
			this.gh_id=rs.getString("gh_id");
			this.quota_type=rs.getString("quota_type");
			this.nu_of_rooms=rs.getInt("nu_of_rooms");
			this.max_beds=rs.getInt("max_beds");
			this.dormitory_gents=rs.getInt("dormitory_gents");
			this.dormitory_ladies=rs.getInt("dormitory_ladies");

			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public int selectDBrooms() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select nu_of_rooms from m_quota_block where block_id=? and gh_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ps.setString(3,quota_type);
		System.out.println("ps...................... "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.nu_of_rooms=rs.getInt("nu_of_rooms");
			l_status=true;
		}
		rs.close();
		ps.close();
		return nu_of_rooms;
	} 
	public int selectDBbeds() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select max_beds from m_quota_block where block_id=? and gh_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ps.setString(3,quota_type);
		System.out.println("AAAAAAAAAAA "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.max_beds=rs.getInt("max_beds");
			l_status=true;
		}
		rs.close();
		ps.close();
		return max_beds;
	} 
	public int selectDBdorGents() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select dormitory_gents from m_quota_block where block_id=? and gh_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ps.setString(3,quota_type);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.dormitory_gents=rs.getInt("dormitory_gents");
			l_status=true;
		}
		rs.close();
		ps.close();
		return dormitory_gents;
	} 
	public int selectDBdorLadies() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select dormitory_ladies from m_quota_block where block_id=? and gh_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ps.setString(3,quota_type);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.dormitory_ladies=rs.getInt("dormitory_ladies");
			l_status=true;
		}
		rs.close();
		ps.close();
		return dormitory_ladies;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_quota_block where block_id=? and gh_id=?");
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
	
}