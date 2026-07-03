package db; 

import java.sql.*;

import common.db_validation;

public class d_vehicleallotment extends db_validation{
	
	public String req_id="",block_id="",time_of_report1="",vehicle_reqid="",vehicle_details="",vehicle_no="",driver_name="",driver_mobileno="",protocol_name="",protocol_mobileno="",time_of_report="",place_of_report="",status="",gh_id="",vehicle_start_time="",vehicle_end_time="",mode="";
	
	ngm NGM;
	
	public d_vehicleallotment(ngm p_ngm){		
		NGM=p_ngm;
	}

	public String getplacedetails()
	{
		String tempstr="";
	
		tempstr=tempstr+this.place_of_report+" on "+this.time_of_report +" AM/PM";
			return tempstr;
	}
	
	public String getvehicledetail()
	{
		String tempstr="";
	
		tempstr=tempstr+this.vehicle_no + " and Driver " + this.driver_name+" Mob "+this.driver_mobileno+" ";
			return tempstr;
	}
	
	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into vehicle_allotment values(?,?,?,?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?, ?, ?, ?)");
		ps.setString(1, req_id);
		ps.setString(2, vehicle_reqid);
		ps.setString(3, vehicle_details);
		ps.setString(4, vehicle_no);
		ps.setString(5, driver_name );
		ps.setString(6, driver_mobileno);
		ps.setString(7, time_of_report);
		ps.setString(8, place_of_report );
		ps.setString(9, status);
		ps.setString(10, gh_id);
		ps.setString(11, vehicle_start_time);
		ps.setString(12, vehicle_end_time);
		ps.setString(13, block_id);
		ps.setString(14, protocol_name);
		ps.setString(15, mode);
		ps.setString(16, protocol_mobileno);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE vehicle_allotment SET   vehicle_details=?, vehicle_no=?, driver_name=?, driver_mobileno=?,time_of_report=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'), place_of_report=?, status=?,gh_id=?,vehicle_start_time=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),vehicle_end_time=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss') WHERE req_id=? and vehicle_reqid=?");
		ps.setString(1, vehicle_details);
		ps.setString(2, vehicle_no);
		ps.setString(3, driver_name );
		ps.setString(4, driver_mobileno);
		ps.setString(5, time_of_report);
		ps.setString(6, place_of_report);
		ps.setString(7, status);
		ps.setString(8, gh_id);
		ps.setString(9, vehicle_start_time);
		ps.setString(10, vehicle_end_time);
		ps.setString(11, req_id);
		ps.setString(12, vehicle_reqid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		System.out.println(ps);
		return l_status;
	}

	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_allotment where req_id=?");
		ps.setString(1,req_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	
	public boolean chkvehicle_time_ok() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_allotment where req_id != ? and vehicle_no=? and " +
				"((to_timestamp(?,'dd-MM-yyyy HH24:MI:ss') between vehicle_start_time  and vehicle_end_time) or" +
				" (to_timestamp(?,'dd-MM-yyyy HH24:MI:ss') between vehicle_start_time  and vehicle_end_time)) " +
				" and status!='CAN'");
		ps.setString(1,req_id);
		ps.setString(2,vehicle_no);
		ps.setString(3,vehicle_start_time);
		ps.setString(4, vehicle_end_time);
		System.out.println("DATEEEEEEEEEEEEEEVehicle     "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	
	
//	public boolean deleteDB() throws Exception
//	{
//		boolean l_status=false;
//		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from vehicle_allotment where req_id=?");
//		ps.setString(1,req_id);
//		l_status=NGM.ins_utility.executeDB(ps);
//		ps.close();
//		return l_status;			
//	}

	public boolean cancelDB() throws Exception
	{
		boolean l_status=false;
		VAL_cancelDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE vehicle_allotment SET status='CAN' where gh_id = ? and req_id=? and vehicle_reqid=?");
		ps.setString(1,gh_id);
		ps.setString(2,req_id);
		ps.setString(3,vehicle_reqid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_allotment where req_id=?");
		ps.setString(1,req_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.req_id=rs.getString("req_id");
			this.vehicle_reqid=rs.getString("vehicle_reqid");
			this.vehicle_details=rs.getString("vehicle_details");
			this.vehicle_no=rs.getString("vehicle_no");
			this.driver_name=rs.getString("driver_name");
			this.driver_mobileno=rs.getString("driver_mobileno");
			this.time_of_report=rs.getString("time_of_report");
			this.place_of_report=rs.getString("place_of_report");
			this.status=rs.getString("status");
			this.vehicle_start_time=rs.getString("vehicle_start_time");
			this.vehicle_end_time=rs.getString("vehicle_end_time");
			this.gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	
	public String selectDB_getplacedetails(String gh_id) throws Exception {		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_allotment where req_id=?");
		ps.setString(1,req_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			time_of_report=rs.getString("time_of_report");
			place_of_report=rs.getString("place_of_report");
			l_status=true;
		}
		rs.close();
		ps.close();
		return time_of_report;
	} 	

////--------------------------------------------validation-----------------------------------------------------------------

public void VAL_insertDB() throws Exception 
{
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
		if(!(val_vehicleid(vehicle_reqid))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
		if(!(val_vehicle_no(vehicle_no))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
		if(!(val_driver_name(driver_name))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
		if(!(val_driver_mobileno(driver_mobileno))){
		throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
		if(!(val_place_of_report(place_of_report))){
		throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
			if(!(val_status(status))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
				if(!(val_ghid(gh_id))){
				throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
			}
					if(!(val_time_validation(vehicle_start_time,vehicle_end_time))){
					throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
				}
}

public void VAL_updateDB() throws Exception 
{
		if(!(val_reqid(req_id) && val_vehicleid(vehicle_reqid) && val_vehicle_details(vehicle_details) && val_vehicle_no(vehicle_no) && val_driver_name(driver_name) && val_driver_mobileno(driver_mobileno) && val_place_of_report(place_of_report) && val_status(status) && val_ghid(gh_id))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_updateDB", NGM);
		}
}

public void VAL_cancelDB() throws Exception {
	if(!(val_reqid(req_id) && val_vehicleid(vehicle_reqid) && val_ghid(gh_id))){
		throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_cancelDB", NGM);
	}
}

public void VAL_selectDB() throws Exception 
{
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("vehicle_allotment", "Invalid vehicle allotment parameter", "VAL_insertDB", NGM);
		}
}




public boolean val_vehicleid(String veh_id){
if(veh_id!=null){
		if(!cLength(veh_id.toString(), 1, 25)){
				return false;
		}
		if (!cNumeric(veh_id)){
			return false;
		}
		return true;
}else
			
			return false;
}

public boolean val_vehicle_details(String vehicle_details){
	if(vehicle_details!=null){
		if(!cLength(vehicle_details.toString(), 0, 200)){
				return false;
			}
		if (!cAlphaNumericSpecial(vehicle_details," ")){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_vehicle_no(String vehicle_no){
	if(vehicle_no!=null){
		if(!cLength(vehicle_no.toString(), 0, 50)){
				return false;
			}
			if (!cRemark(vehicle_no)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_driver_name(String driver_name){
	if(driver_name!=null){
		if(!cLength(driver_name.toString(), 0, 100)){
				return false;
			}
			if (!cAlphaSpecial(driver_name," ")){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_driver_mobileno(String driver_mobileno){
	if(driver_mobileno!=null){
		if(!cLength(driver_mobileno.toString(), 0, 15)){
				return false;
			}
			if (!cNumeric(driver_mobileno)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_place_of_report(String place_of_report){
	if(place_of_report!=null){
		if(!cLength(place_of_report.toString(), 0, 100)){
				return false;
			}
			if (!cAlphaNumericSpecial(place_of_report," ")){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_status(String status){
	if(status!=null){
		if(!cLength(status.toString(), 0, 10)){
				return false;
			}
			if (!cAlphabet(status)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_time_validation(String vehicle_start_time,String vehicle_end_time) throws Exception{
			if(!cDateTimeCompDB(vehicle_start_time,vehicle_end_time,"<=")){
				return false;
			}
			return true;
	
	
}
public boolean val_vehicle_start_time(String vehicle_start_time){
	if(vehicle_start_time!=null){
		if(!cDateTime(vehicle_start_time.toString(),"dmy")){
				return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_vehicle_end_time(String vehicle_end_time){
	if(vehicle_end_time!=null){
		if(!cDateTime(vehicle_end_time.toString(),"dmy")){
				return false;
			}
			return true;
	}else
		return false;
	
}


}