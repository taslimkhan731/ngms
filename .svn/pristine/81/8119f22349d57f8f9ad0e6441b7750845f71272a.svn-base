package db; 
import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_vehicle1 extends db_validation{
	public String mode_of_travel,arrival_time,arrival_place,no_seats_req,ftno; 
	public String req_id;
	public String  veh_id;
	public JSONArray array1 = new JSONArray();
	utility 	  ins_util;
	ngm NGM;
	public d_vehicle1(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB_veh() throws Exception {		
		boolean l_status=false;	
		int veh_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id1); 
		String veh_id = String.valueOf(veh_id1);
		VAL_insertDB_veh(veh_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into vehicle_req1 values(?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,now())");
		ps.setString(1, veh_id);
		ps.setString(2, req_id);
		ps.setString(3, mode_of_travel);
		ps.setString(4, arrival_time);
		ps.setString(5, arrival_place);
		ps.setString(6, no_seats_req);
		ps.setString(7, ftno);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_vehGH() throws Exception {		
		boolean l_status=false;	
		VAL_insertDB_vehGH();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into vehicle_req1 values(?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,now())");
		ps.setString(1, veh_id);
		ps.setString(2, req_id);
		ps.setString(3, mode_of_travel);
		ps.setString(4, arrival_time);
		ps.setString(5, arrival_place);
		ps.setString(6, no_seats_req);
		ps.setString(7, ftno);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB_veh() throws Exception {		
		boolean l_status=false;	

		VAL_updateDB_veh();//"update mod_req_room set nor_req=?,nob_g_req=?,nob_l_req=? where block_id=? and req_id=?"
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update vehicle_req1 set mode_of_travel=?,arrival_time=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),arrival_place=?,no_seats_req=?,last_modified=now() where req_id=?");
		ps.setString(1, mode_of_travel);
		ps.setString(2, arrival_time);
		ps.setString(3, arrival_place);
		ps.setString(4, no_seats_req);
		ps.setString(5, req_id);
		System.out.println("quert updateDB_veh   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
		// VAL_insertDB();"update mod_req_room set nor_req=?,nob_g_req=?,nob_l_req=? where block_id=? and req_id=?"
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_req1 where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			l_status= true;
		}
		rs.close();
		ps.close(); 
		return l_status;
	}
	
	public boolean selectVehicleReq() throws Exception {		
		boolean l_status=false;	
		// VAL_insertDB();"update mod_req_room set nor_req=?,nob_g_req=?,nob_l_req=? where block_id=? and req_id=?"
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_req1 where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			mode_of_travel = rs.getString("mode_of_travel");
			ftno = rs.getString("ftno");
			l_status= true;
		}
		rs.close();
		ps.close(); 
		return l_status;
	}
	
	public boolean deleteveh() throws Exception {		
		boolean l_status=false;	

		PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from vehicle_req1 where req_id=?");

		pss.setString(1, req_id);

		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss deleteveh+++++++  "+pss);
		pss.close();
		return l_status;		
	} 
	public boolean insertDB_veh_upload() throws Exception {		
		boolean l_status=false;	

		// VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into vehicle_req1 values(?,?,?,to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?,?,?,now())");
		ps.setString(1, veh_id);
		ps.setString(2, req_id);
		ps.setString(3, mode_of_travel);
		ps.setString(4, arrival_time);
		ps.setString(5, arrival_place);
		ps.setString(6, no_seats_req);
		ps.setString(7, ftno);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public void setpost_mod(JSONObject jsonObj)throws Exception{

		this.req_id =   ins_util.setNullvalue(jsonObj.get("req_id"));
		this.veh_id =  ins_util.setNullvalue(jsonObj.get("veh_id"));
		this.mode_of_travel =  ins_util.setNullvalue(jsonObj.get("mode_of_travel"));
		this.arrival_time = ins_util.setNullvalue(jsonObj.get("arrival_time"));
		this.arrival_place =  ins_util.setNullvalue(jsonObj.get("arrival_place"));
		this.no_seats_req =  ins_util.setNullvalue(jsonObj.get("no_seats_req"));
		this.ftno =  ins_util.setNullvalue(jsonObj.get("ftno"));


	}
	public void existdata()throws Exception{


		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_req1 where veh_id=? and req_id=?");
		ps.setString(1, veh_id);
		ps.setString(2, req_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from vehicle_req1 where veh_id=? and req_id=?");
			ps1.setString(1, veh_id);
			ps1.setString(2, req_id);
			ps1.execute();
			ps1.close(); 
		}
		rs.close();
		ps.close(); 	 


	}
	public boolean existdata_accept()throws Exception{


		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_req1 where veh_id=? and req_id=?");
		ps.setString(1, veh_id);
		ps.setString(2, req_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return true;

		}
		rs.close();
		ps.close(); 
		return false;	 


	}

	public boolean selectimportdata()throws Exception{
		boolean retval = false;
		JSONArray jobj = new JSONArray();
		String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_vehicle_req1");

		System.out.println("@@@"+ps);
		ResultSet rs=ps.executeQuery();
		System.out.println("@@@"+ps);
		ResultSetMetaData rsmet = rs.getMetaData();
		int col_count = rsmet.getColumnCount();
		String fieldname="";
		int rownum =0;
		int count=0;
		while(rs.next()){ 
			JSONObject jsonObj1 = new JSONObject();
			count = count +1;
			for (int i = 1; i < col_count + 1; i++ ) 
			{ 	 
				fieldname  = rsmet.getColumnName(i);
				String fieldvalue = rs.getString(fieldname);

				jsonObj1.put(fieldname, fieldvalue);

			}


			array1.add(jsonObj1);

			System.out.println("sdffffffffffffff"+array1);
			retval = true;
		}
		rs.close();
		ps.close(); 
		return retval;
	}
	public void setpost_mod(JSONObject jsonObj,String req_id)throws Exception{

		this.veh_id =  ins_util.setNullvalue( jsonObj.get("veh_id"));
		this.req_id = req_id;
		this.mode_of_travel =  ins_util.setNullvalue(jsonObj.get("mode_of_travel"));
		this.arrival_time = ins_util.setNullvalue(jsonObj.get("arrival_time"));
		this.arrival_place =  ins_util.setNullvalue(jsonObj.get("arrival_place"));
		this.no_seats_req =  ins_util.setNullvalue(jsonObj.get("no_seats_req"));
		this.ftno =  ins_util.setNullvalue(jsonObj.get("ftno"));


	} 

	public boolean insert_received_data()throws Exception{

		boolean l_status= false;
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into vehicle_req1 values(?,?,?,to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?,?,?,now())");
		ps.setString(1, veh_id);
		ps.setString(2, req_id);
		ps.setString(3, mode_of_travel);
		ps.setString(4, arrival_time);
		ps.setString(5, arrival_place);
		ps.setString(6, no_seats_req);
		ps.setString(7, ftno);
		System.out.println("vehicle"+ps);
		l_status=NGM.ins_utility.executeDB(ps);

		ps.close(); 
		return l_status;

	}



	public boolean selectimportdata(String time)throws Exception{ 
		boolean retval = false;
		JSONArray jobj = new JSONArray();
		String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_req1 where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
		ps.setString(1,time);
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from vehicle_req");

		System.out.println("@@@"+ps);
		ResultSet rs=ps.executeQuery();
		System.out.println("@@@"+ps);
		ResultSetMetaData rsmet = rs.getMetaData();
		int col_count = rsmet.getColumnCount();
		String fieldname="";
		int rownum =0;
		int count=0;
		while(rs.next()){ 
			JSONObject jsonObj1 = new JSONObject();
			count = count +1;
			for (int i = 1; i < col_count + 1; i++ ) 
			{ 	 
				fieldname  = rsmet.getColumnName(i);
				String fieldvalue = rs.getString(fieldname);
				jsonObj1.put(fieldname, fieldvalue);

			}

			array1.add(jsonObj1);

			System.out.println("sdffffffffffffff"+array1);
			retval = true;
		}
		rs.close();
		ps.close(); 
		return retval;
	}


	//-------------------------------------------------------validation--------------------------------------------------------------------	


	public void VAL_insertDB_veh(String veh_id) throws Exception	{

		if(!(val_reqid(req_id))){
			System.out.println("req_id  "+req_id);
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid req_id", "VAL_insertDB_veh", NGM);
		}
		if(!(val_vehid(veh_id))){
			System.out.println("dfdsfsdf veh "+veh_id);
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid veh_id", "VAL_insertDB_veh", NGM);
		}
		if(!(val_modeoftravel(mode_of_travel))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid mode_of_travel", "VAL_insertDB_veh", NGM);
		}
		String[] t_arr_time = arrival_time.split(" ");
		if(!(val_date_dmy(t_arr_time[0]))){//
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_time", "VAL_insertDB_veh", NGM);
		}
		if(!(val_address(arrival_place))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_place", "VAL_insertDB_veh", NGM);
		}
		if(!(val_roomno(no_seats_req))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid no_seats_req", "VAL_insertDB_veh", NGM);
		}
		if(!(val_flight(ftno))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid ftno", "VAL_insertDB_veh", NGM);
		}
	}
	public void VAL_insertDB_vehGH() throws Exception	{

		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid req_id", "VAL_insertDB_vehGH", NGM);
		}
		if(!(val_vehid(veh_id))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid veh_id", "VAL_insertDB_vehGH", NGM);
		}
		if(!(val_modeoftravel(mode_of_travel))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid mode_of_travel", "VAL_insertDB_vehGH", NGM);
		}
		String[] t_arr_time = arrival_time.split(" ");
		if(!(val_date_dmy(t_arr_time[0]))){//
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_time", "VAL_insertDB_vehGH", NGM);
		}
		if(!(val_address(arrival_place))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_place", "VAL_insertDB_vehGH", NGM);
		}
		if(!(val_roomno(no_seats_req))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_place", "VAL_insertDB_vehGH", NGM);
		}
		if(!(val_flight(ftno))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid ftno", "VAL_insertDB_vehGH", NGM);
		}
	}
	public void VAL_updateDB_veh() throws Exception	{

		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid req_id", "VAL_updateDB_veh", NGM);
		}
		if(!(val_modeoftravel(mode_of_travel))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid mode_of_travel", "VAL_updateDB_veh", NGM);
		}
		String[] t_arr_time = arrival_time.split(" ");
		if(!(val_date_dmy(t_arr_time[0]))){//
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_time", "VAL_updateDB_veh", NGM);
		}
		if(!(val_address(arrival_place))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_place", "VAL_updateDB_veh", NGM);
		}
		if(!(val_roomno(no_seats_req))){
			throw NGM.ins_utility.setException("d_vehicle1", "Invalid arrival_place", "VAL_updateDB_veh", NGM);
		}
	}
}

