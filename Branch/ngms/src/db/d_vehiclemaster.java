package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.db_validation;

public class d_vehiclemaster extends db_validation{
	public String driver_id,driver_name,driver_mobileno,vehicle_no,vehicle_description,gh_id,sel_block,protocol_name,protocol_mobileno;
	ngm NGM;
	
	public d_vehiclemaster(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {
		//VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_vehicle values(?,?,?,?,?,?,?,?,?)");
		ps.setString(1, driver_id);
		ps.setString(2, driver_name);
		ps.setString(3, driver_mobileno);
		ps.setString(4, vehicle_no);
		ps.setString(5, vehicle_description);
		ps.setString(6, gh_id);
		ps.setString(7, sel_block);
		ps.setString(8, protocol_name);
		ps.setString(9, protocol_mobileno);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	} 
	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from m_vehicle where driver_id=?");
		ps.setString(1, driver_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public boolean updateDB() throws Exception {	
		boolean l_status=false;
		//VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_vehicle set driver_name=?,driver_mobileno=?,vehicle_number=?,vehicle_description=?,block_id=?,protocol_name=?,protocol_mobileno=? where driver_id=?");
		
		ps.setString(1, driver_name);
		ps.setString(2, driver_mobileno);
		ps.setString(3, vehicle_no);
		ps.setString(4, vehicle_description);
		ps.setString(5, sel_block);
		ps.setString(6, protocol_name);
		ps.setString(7, protocol_mobileno);
		ps.setString(8, driver_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{	
		boolean l_status=false;
		// val_quotatype(quota_type);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_vehicle where driver_id=?");
		ps.setString(1, driver_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from m_vehicle where driver_id=?");
		ps.setString(1, driver_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			driver_name=rs.getString("driver_name");
			driver_mobileno=rs.getString("driver_mobileno");
			vehicle_no=rs.getString("vehicle_no");
			
		}
		rs.close();
		ps.close();
		return l_status;
	}
	public List<String> ghId() throws SQLException {
		List<String> list = new ArrayList<String>();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from uservsgh where userid=?");
		ps.setString(1, driver_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			list.add(rs.getString("gh_id"));
		}
		rs.close();
		ps.close();
		return list;
	} 	

/***********************************************************Validation*****************************************************/	
//	public void VAL_insertDB() throws Exception 
//	{
//		 val_quotatype(quota_type);
//		 val_quotadesc(quota_desc);
//	}
//
//	public void VAL_updateDB() throws Exception 
//	{
//		 val_quotatype(quota_type);
//		 val_quotadesc(quota_desc);
//	}
//
//	
//	
//	public boolean val_quotatype(String quota_type){
//		if(quota_type!=null){
//			if(!cLength(quota_type.toString(), 0, 10)){
//					return false;
//				}
//				if (!cAlphaNumeric(quota_type)){
//						return false;
//				}
//				return true;
//		}else
//			return false;
//		
//	}	
//	public boolean val_quotadesc(String quota_desc){
//		if(quota_desc!=null){
//			if(!cLength(quota_desc.toString(), 0, 10)){
//					return false;
//				}
//				if (!cAlphaNumeric(quota_desc)){
//						return false;
//				}
//				return true;
//		}else
//			return false;
//		
//	}	

}//end class