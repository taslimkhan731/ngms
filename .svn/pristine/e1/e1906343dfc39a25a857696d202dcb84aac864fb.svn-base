package db;
import java.sql.*;

import model.ngmodel;
public class d_master_charges extends ngmodel{
	public String charges_id,charges_title;
	ngm NGM;
	public d_master_charges(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into master_charges values(?,?)");
		ps.setString(1,charges_id);
		ps.setString(2,charges_title);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update master_charges set charges_title=? where charges_id=?");
		ps.setString(1,charges_title);
		ps.setString(2,charges_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from master_charges where charges_id=?");
		ps.setString(1,charges_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from master_charges where charges_id=?");
		ps.setString(1,charges_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.charges_title=rs.getString("charges_title");
			this.charges_id=rs.getString("charges_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
}