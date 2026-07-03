package db; 
import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;

public class d_pubvehicle extends db_validation{
	public String mode_of_arrival,arrival_time,arrival_place,no_seats_req,veh_id1,req_id1; 
	public int req_id,veh_id;
	
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	public d_pubvehicle(ngm p_ngm){		
		NGM=p_ngm;
	}
	
	public boolean insertDB_veh() throws Exception {		
		boolean l_status=false;	
		 req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
	    
	     
	     veh_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "veh_id"); 
	     NGM.ins_utility.updateseqID(NGM.tr_conn, "veh_id", veh_id); 
	     
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pub_vehicle_req values(?,?,?,TO_DATE(?,'yyyy-MM-dd HH:mm:ss'),?,?)");
		ps.setInt(1, veh_id);
		ps.setInt(2, req_id);
		ps.setString(3, mode_of_arrival);
		ps.setString(4, arrival_time);
		ps.setString(5, arrival_place);
		ps.setString(6, no_seats_req);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
//-------------------------------------------------------validation--------------------------------------------------------------------	
	
public void VAL_insertDB() throws Exception	{
	val_modeofarrival(mode_of_arrival);
	val_arrival_time(arrival_time);
	val_arrival_place(arrival_place);
	val_noseatsreq(no_seats_req);
}

public boolean val_modeofarrival(String mode_of_arrival){
	if(mode_of_arrival!=null){
		if(!cLength(mode_of_arrival.toString(), 0, 9)){
				return false;
			}
			if (!cNumeric(mode_of_arrival)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_arrival_time(String arrival_time){
	if(arrival_time!=null){
		if(!cLength(arrival_time.toString(), 0, 9)){
				return false;
			}
			if (!cAlphaNumeric(arrival_time)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_arrival_place(String arrival_place){
	if(mode_of_arrival!=null){
		if(!cLength(arrival_place.toString(), 0, 9)){
				return false;
			}
			if (!cNumeric(arrival_place)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_noseatsreq(String no_seats_req){
	if(no_seats_req!=null){
		if(!cLength(no_seats_req.toString(), 0, 9)){
				return false;
			}
			if (!cNumeric(no_seats_req)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean selectimportdata(String time)throws Exception{
boolean retval = false;
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_vehicle_req where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')) - interval '2 hours' ");
 ps.setString(1, time);
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

public void setpost_mod(JSONObject jsonObj)throws Exception{
	  
	 this.veh_id1 =  jsonObj.get("veh_id").toString();
	 this.req_id1 = jsonObj.get("req_id").toString();
	 this.mode_of_arrival = jsonObj.get("mode_of_travel").toString();
	 this.arrival_time =jsonObj.get("arrival_time").toString();
	 this.arrival_place = jsonObj.get("arrival_place").toString();
	 this.no_seats_req = jsonObj.get("no_seats_req").toString();
	 
	 
	 
} 
	 
	public boolean insert_received_data()throws Exception{
	
	boolean l_status= false;
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	 PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pub_vehicle_test values(?,?,?,to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'),?,?)");
		ps.setString(1, veh_id1);
		ps.setString(2, req_id1);
		ps.setString(3, mode_of_arrival);
		ps.setString(4, arrival_time);
		ps.setString(5, arrival_place);
		ps.setString(6, no_seats_req);
		l_status=NGM.ins_utility.executeDB(ps);
		
		ps.close();
return l_status;
	
}
	
	 
public boolean existdata()throws Exception{
	
	boolean l_status= false;
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_vehicle_test where req_id=?");
	ps.setString(1, req_id1);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		l_status = true;
	}
	rs.close();
	ps.close();
	 
return l_status;
	
}


}
	
 

