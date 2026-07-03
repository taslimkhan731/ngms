package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_rent extends db_validation
{
	public String rent_id,from_date,to_date,vc_id,rent,type,family,f_rent,mode,da_per;
	
	ngm NGM;
	utility 		ins_util;
	
	public d_rent(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into rent_table values(?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?)");
		ps.setString(1, rent_id);
		ps.setString(2, from_date);
		ps.setString(3, to_date);
		ps.setString(4, vc_id);
		ps.setString(5, rent);
		ps.setString(6, type);
		ps.setString(7, family);
		ps.setString(8, f_rent);
		ps.setString(9, mode);
		ps.setString(10,da_per);
		System.out.println("rent pssssssssssssssss    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	} 
	
	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from rent_table where vc_id=? and type=? and family=?");
		ps.setString(1, vc_id);
		ps.setString(2, type);
		ps.setString(3, family);
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
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update rent_table set from_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),rent=?,f_rent=?,mode_of_payment=?,da_percentage=? where rent_id=? and vc_id=? and type=? and family=?");
		ps.setString(1, from_date);
		ps.setString(2, to_date);
		ps.setString(3, rent);
		ps.setString(4, f_rent);
		ps.setString(5, mode);
		ps.setString(6, da_per);
		ps.setString(7, rent_id);
		ps.setString(8, vc_id);
		ps.setString(9, type);
		ps.setString(10, family);
		System.out.println("psssssssssss   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{	
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from rent_table where rent_id=?");
		ps.setString(1, rent_id);
		System.out.println("pppppppppppsssssssssss   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	
	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from rent_table where vc_id=? and type=? and family=?");
		ps.setString(1, vc_id);
		ps.setString(2, type);
		ps.setString(3, family);
		System.out.println("psssssss     "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			from_date=rs.getString("from_date");
			to_date=rs.getString("to_date");
			rent=rs.getString("rent");
			f_rent=rs.getString("f_rent");
					
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
}