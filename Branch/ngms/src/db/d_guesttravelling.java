package db; 

import java.sql.*;

import common.db_validation;

public class d_guesttravelling extends db_validation{
	
	public String seq_id, req_id, guest_id, vehicle_no, distance, from_place, to_place,charges_id, dateandtime, unitprice, total, lastupdated;
	
	ngm NGM;
	
	public d_guesttravelling(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_guest_travelling VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, seq_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		ps.setString(4, vehicle_no);
		ps.setString(5, distance);
		ps.setString(6, from_place);
		ps.setString(7, to_place);
		ps.setString(5, charges_id);
		ps.setString(6, dateandtime);
		ps.setString(7, unitprice);
		ps.setString(5, total);
		ps.setString(6, lastupdated);
	
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_guest_travelling   SET req_id=?, guest_id=?, vehicle_no=?, distance=?, from_place=?, to_place=?, charges_id=?, dateandtime=?, unitprice=?, total=?,lastupdated=? WHERE seq_id=?");
		
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, vehicle_no);
		ps.setString(4, distance);
		ps.setString(5, from_place);
		ps.setString(6, to_place);
		ps.setString(7, charges_id);
		ps.setString(8, dateandtime);
		ps.setString(9, unitprice);
		ps.setString(10, total);
		ps.setString(11, lastupdated);
		ps.setString(12, seq_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_guest_travelling WHERE seq_id");
		ps.setString(1, seq_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_guest_travelling where seq_id=?");
		ps.setString(1, seq_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			req_id=rs.getString("req_id");
			guest_id=rs.getString("guest_id");
			vehicle_no=rs.getString("vehicle_no");
			distance=rs.getString("distance");
			from_place=rs.getString("from_place");
			to_place=rs.getString("to_place");
			charges_id=rs.getString("charges_id");
			dateandtime=rs.getString("dateandtime");
			unitprice=rs.getString("unitprice");
			total=rs.getString("total");
			lastupdated=rs.getString("lastupdated");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		val_seqid(seq_id);
		val_reqid(req_id);
		val_guestid(guest_id);
		val_vehicleno(vehicle_no);
		val_distance(distance);
		val_fromplace(from_place);
		val_toplace(to_place);
		
		val_chargesid(charges_id);
		val_dateandtime(dateandtime);
		val_unitprice(unitprice);
		val_total(total);
		val_lastupdated(lastupdated);
		
	}
	
	public boolean val_vehicleno(String vehicle_no){
		if(vehicle_no!=null){
			if(!cLength(vehicle_no.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(vehicle_no)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_distance(String distance){
		if(distance!=null){
			if(!cLength(distance.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(distance)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_fromplace(String from_place){
		if(from_place!=null){
			if(!cLength(from_place.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(from_place)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_toplace(String to_place){
		if(to_place!=null){
			if(!cLength(to_place.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(to_place)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_unitprice(String unitprice){
		if(unitprice!=null){
			if(!cLength(unitprice.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(unitprice)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_total(String total){
		if(total!=null){
			if(!cLength(total.toString(), 1, 25)){
					return false;
				}
				if (!cAlphaNumeric(total)){
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

	public boolean val_lastupdated(String date){
		if(lastupdated!=null){
			if(!cDateTime(lastupdated.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	
}