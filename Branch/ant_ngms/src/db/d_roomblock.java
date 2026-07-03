package db;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.db_validation;
public class d_roomblock extends db_validation {
	public String block_id,block_name,gh_id,nu_of_floors,nu_of_rooms,block_name_short,address;
	ngm NGM;
	public d_roomblock(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into room_block values(?,?,?,?,?,?)");
		ps.setString(1,block_id);
		ps.setString(2,block_name);
		ps.setString(3,gh_id);
		ps.setString(4,nu_of_floors);
		ps.setString(5,nu_of_rooms);
		ps.setString(6,block_name_short);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update room_block set block_name=?,gh_id=?,nu_of_floors=?,nu_of_rooms=?,block_name_short=? where block_id=?");
		ps.setString(1,block_name);
		ps.setString(2,gh_id);
		ps.setString(3,nu_of_floors);
		ps.setString(4,nu_of_rooms);
		ps.setString(5,block_name_short);
		ps.setString(6,block_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from room_block where block_id=?");
		ps.setString(1,block_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where block_id=? and gh_id=?");
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
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where block_id=? and gh_id=?");
		ps.setString(1,block_id);
		ps.setString(2,gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.block_id=rs.getString("block_id");
			this.block_name=rs.getString("block_name");
			this.gh_id=rs.getString("gh_id");
			this.nu_of_floors=rs.getString("nu_of_floors");
			this.nu_of_rooms=rs.getString("nu_of_rooms");
			this.block_name_short=rs.getString("block_name_short");
			this.address=rs.getString("address");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean getAddress() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where block_name=? and gh_id=?");
		ps.setString(1,block_name);
		ps.setString(2,gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.block_id=rs.getString("block_id");
			this.block_name=rs.getString("block_name");
			this.gh_id=rs.getString("gh_id");
			this.nu_of_floors=rs.getString("nu_of_floors");
			this.nu_of_rooms=rs.getString("nu_of_rooms");
			this.block_name_short=rs.getString("block_name_short");
			this.address=rs.getString("address");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean selectDBblocks() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where gh_id=?");
		ps.setString(1,gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.block_id=rs.getString("block_id");
			this.block_name=rs.getString("block_name");
			this.address=rs.getString("address");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where block_id=? and gh_id=?");
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
	
	public ArrayList<String> getallblock(HttpServletRequest p_request) throws Exception
	
	{
		String ghid=p_request.getParameter("ghid");
		System.out.println("gh id neww="+ghid);
		 ArrayList<String> objList=new  ArrayList<String>();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from room_block where gh_id=? order by block_id asc");
		ps.setString(1, ghid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			
			String blockid=rs.getString("block_id");
			objList.add(blockid);
			
		}
		
		rs.close();
		ps.close();
		return objList;
		
	}
//----------------------------------------------------------------Validation------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception 
	{
		
		 val_ghid(gh_id);	 
		 val_blockid(block_id);
		
	}	
	
}