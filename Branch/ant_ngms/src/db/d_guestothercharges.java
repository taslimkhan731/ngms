package db; 

import java.sql.*;

import common.db_validation;

public class d_guestothercharges extends db_validation{
	
	public String  seq_id, req_id, guest_id, charges_id, total, dateandtime, title;
	
	ngm NGM;
	
	public d_guestothercharges(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
	//	VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_guest_othercharges VALUES (?, ?, ?, ?, ?, now(), ?)");
		ps.setString(1, seq_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		ps.setString(4, charges_id);
		ps.setString(5, total);
		ps.setString(6, title);
		System.out.println("ps....*** "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
	//	VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_guest_othercharges  SET  req_id=?, guest_id=?, charges_id=?, total=?, dateandtime=?, title=? WHERE seq_id=?");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, charges_id);
		ps.setString(4, total);
		ps.setString(5, dateandtime);
		ps.setString(6, title);
		ps.setString(7, seq_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		//VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_guest_othercharges WHERE seq_id");
		ps.setString(1, seq_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_guest_othercharges where seq_id=?");
		ps.setString(1, seq_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			req_id=rs.getString("req_id");
			guest_id=rs.getString("guest_id");
			charges_id=rs.getString("charges_id");
			total=rs.getString("total");
			dateandtime=rs.getString("dateandtime");
			title=rs.getString("title");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	///select sum(total::Integer) from tr_guest_othercharges where req_id='G_599' and guest_id='610' and charges_id='1'
	public String selectDBsum() throws Exception { //ok
		//VAL_selectDBsum();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select total from tr_guest_othercharges where req_id=? and guest_id=? and charges_id=?");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, charges_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			total=rs.getString("total");
		}
		rs.close();
		ps.close();
		return total;
	} 
	
	public String selectDBguestsum() throws Exception { //ok
	//	VAL_selectDBguestsum();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total::Integer) from tr_guest_othercharges where req_id=? and guest_id=?");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ResultSet rs=ps.executeQuery();
		String sum="";
		while(rs.next()){
			sum=rs.getString("sum");
		}
		rs.close();
		ps.close();
		return sum;
	}
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_seqid(seq_id)&&val_reqid(req_id)&&val_guestid(guest_id)&&val_chargesid(charges_id)&&val_total(total)&&val_title(title))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		/*val_seqid(seq_id);
		val_reqid(req_id);
		val_guestid(guest_id);
		val_chargesid(charges_id);
		val_total(total);
		val_title(title);*/
	}
	
	public void VAL_updateDB() throws Exception {
		if(!(val_seqid(seq_id)&&val_reqid(req_id)&&val_guestid(guest_id)&&val_chargesid(charges_id)&&val_total(total)&&val_title(title))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		/*val_seqid(seq_id);
		val_reqid(req_id);
		val_guestid(guest_id);
		val_chargesid(charges_id);
		val_total(total);
		val_title(title);*/
	}
	
	public void VAL_deleteDB() throws Exception {
		if(!(val_seqid(seq_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_seqid(seq_id);
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_seqid(seq_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
	//	val_seqid(seq_id);
	}
	
	public void VAL_selectDBsum() throws Exception {
		if(!(val_reqid(req_id)&&val_guestid(guest_id)&&val_chargesid(charges_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		/*val_reqid(req_id);
		val_guestid(guest_id);
		val_chargesid(charges_id);*/
	}
	
	public void VAL_selectDBguestsum() throws Exception {
		if(!(val_reqid(req_id)&&val_guestid(guest_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_reqid(req_id);
		//val_guestid(guest_id);
	}
	
	
	public boolean val_total(String total){
		if(total!=null){
			if(!cLength(total.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(total)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_dateandtime(String dateandtime){
		if(dateandtime!=null){
			if(!cDateTime(dateandtime.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
}