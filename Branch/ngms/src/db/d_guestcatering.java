package db; 

import java.sql.*;

import common.db_validation;

public class d_guestcatering extends db_validation{
	
	public String seq_id, req_id, guest_id, charges_id, total,unitprice,no_of_unit,food_time,item_details,dateandtime,foodtime;
	
	ngm NGM;
	
	public d_guestcatering(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		int seq1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "food_id"); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "food_id",seq1);
	    seq_id = String.valueOf(seq1);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_guest_catering VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(),?)");
		ps.setString(1, seq_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		ps.setString(4, item_details);
		ps.setString(5, unitprice);
		ps.setString(6, no_of_unit);
		ps.setString(7, total);
		ps.setString(8, charges_id);
		//ps.setString(9, dateandtime);
		ps.setString(9, foodtime);
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public String selectDB_Total_guest() throws Exception { //ok
		String sum="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total::Integer) from tr_guest_catering where req_id=? and guest_id=?");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sum=rs.getString("sum");
		
			l_status=true;
		}
		rs.close();
		ps.close();
		return sum;
	} 
	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_guest_catering WHERE seq_id=?");
		ps.setString(1, seq_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
}