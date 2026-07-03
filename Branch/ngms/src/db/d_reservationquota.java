package db;
import java.sql.*;
public class d_reservationquota {
	public String block_id,gh_id,quota_type,quota;
	ngm NGM;
	public d_reservationquota(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into reservation_quota values(?,?,?,?)");
		ps.setString(1,block_id);
		ps.setInt(2,Integer.parseInt(gh_id));
		ps.setString(3,quota_type);
		ps.setInt(4,Integer.parseInt(quota));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update into reservation_quota set gh_id=?,quota=? where block_id=? and quota_type=?");
		ps.setInt(1,Integer.parseInt(gh_id));
		ps.setInt(2,Integer.parseInt(quota));
		ps.setString(3,block_id);
		ps.setString(4,quota_type);
		
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from reservation_quota where block_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,quota_type);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_quota where block_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,quota_type);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.block_id=rs.getString("block_id");
			this.gh_id=rs.getString("gh_id");
			this.quota_type=rs.getString("quota_type");
			this.quota=rs.getString("quota");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_quota where block_id=? and quota_type=?");
		ps.setString(1,block_id);
		ps.setString(2,quota_type);
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
		 if(gh_id!=null)
		 {
			if(!NGM.ins_val.cLength(gh_id.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(gh_id.toString()))
				throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_reservationquota", "Null reservationquota details", p_method, NGM);
		
		 if(quota!=null)
		 {
			if(!NGM.ins_val.cLength(quota.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(quota.toString()))
				throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_reservationquota", "Null reservationquota details", p_method, NGM);
		 
	}
	public void validate_key(String p_method) throws Exception 
	{	
		if(block_id!=null){
		 		if(p_method=="insertDB"){
		 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "reservation_quota", "block_id", block_id.toString(),NGM))
		 				throw NGM.ins_utility.setException("m_reservationquota", " Already existing", p_method, NGM);
		 		}
		 		if(!NGM.ins_val.cLength(block_id.toString(), 1, 100))
		 			throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota length", p_method, NGM);
		 		else if (!NGM.ins_val.cNumeric(block_id.toString()))
		 			throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota code", p_method, NGM);
		 	}
		 	else
		 		throw NGM.ins_utility.setException("m_reservationquota", "Null reservationquota code", p_method, NGM);
		
		if(quota_type!=null){
	 		if(p_method=="insertDB"){
	 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "reservation_quota", "quota_type", quota_type.toString(),NGM))
	 				throw NGM.ins_utility.setException("m_reservationquota", " Already existing", p_method, NGM);
	 		}
	 		if(!NGM.ins_val.cLength(quota_type.toString(), 1, 100))
	 			throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota length", p_method, NGM);
	 		else if (!NGM.ins_val.cNumeric(quota_type.toString()))
	 			throw NGM.ins_utility.setException("m_reservationquota", "Invalid reservationquota code", p_method, NGM);
	 	}
	 	else
	 		throw NGM.ins_utility.setException("m_reservationquota", "Null reservationquota code", p_method, NGM);
	
	 }	
}