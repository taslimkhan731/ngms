package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_cancel_main extends db_validation
{
	public String can_id,cancelled_by,req_id,cancelled_at;
 	ngm NGM;
	utility 		ins_util;
	public JSONArray array1 = new JSONArray();
	public d_cancel_main(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		
	//	VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_cancel_main values(?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,now())");
		ps.setString(1, can_id);
		ps.setString(2, cancelled_by);
		ps.setString(3, cancelled_at);
		ps.setString(4, req_id);
	 	 
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	 
	public boolean insertDB_upload() throws Exception
	{		
		boolean l_status=false;	
		
	//	VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_cancel_main values(?,?,to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),?,now())");
		ps.setString(1, can_id);
		ps.setString(2, cancelled_by);
		ps.setString(3, cancelled_at);
		ps.setString(4, req_id);
	 	 
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 


public void setpost_cancel(JSONObject jsonObj)throws Exception{
	 System.out.println("jsong objeecct"+jsonObj);
	 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
	 this.can_id = ins_util.setNullvalue(jsonObj.get("can_id"));
	 this.cancelled_at = ins_util.setNullvalue(jsonObj.get("cancelled_at"));
	 this.cancelled_by =ins_util.setNullvalue(jsonObj.get("cancelled_by"));
		 
	 
}

 
public void existdata()throws Exception{
	
	// VAL_existdata();
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_cancel_main where req_id=?");
	ps.setString(1, req_id);
	System.out.println("vehicle"+ps);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from tr_cancel_main where req_id=?");
		ps1.setString(1, req_id);
		ps1.execute();
		ps1.close(); 
	}
	rs.close();
	ps.close();
}
public boolean existdata_accept()throws Exception{
	
	//VAL_existdata_accept();
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_cancel_main where req_id=?");
	ps.setString(1, req_id);
	System.out.println("vehicle"+ps);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
	return true;	 
		 
	}
	rs.close();
	ps.close();	 
 return false;
	
}
public boolean selectimportdata(String time)throws Exception{ 
boolean retval = false;
System.out.println("importdata.........................");
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
// 
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_cancel_main where lastmodified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
	ps.setString(1,time);
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
	 
	
 




//-------------------------------------validation------------------------------------------------------------------------	

public void VAL_insertDB() throws Exception {
	if(!(val_reqid(req_id)&&val_cancelled_at(cancelled_at)&&val_userid(cancelled_by)&&val_seq_id(can_id))){
		throw NGM.ins_utility.setException("tr_cancel_main", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
	}
	
	/*val_reqid(req_id);
	val_cancelled_at(cancelled_at);
	val_userid(cancelled_by);
	val_seq_id(can_id);*/
}

public void VAL_existdata() throws Exception {
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("tr_cancel_main", "Invalid tr_allotment parameter", "VAL_existdata", NGM);
	}
	//val_reqid(req_id);
}

public void VAL_existdata_accept() throws Exception {
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("tr_cancel_main", "Invalid tr_allotment parameter", "VAL_existdata_accept", NGM);
	}
	//val_reqid(req_id);
}


public boolean val_cancelled_at(String cancelled_at){
	if(cancelled_at!=null){
		if(!cDateTime(cancelled_at.toString(),"yyyy-MM-dd HH:mm:ss")){
				return false;
			}
			return true;
	}else
		return false;
	
}
 
}