package db; 

import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;

public class d_importindex extends db_validation
{
	public String  userid,starttime,end_time,status;
	 
	ngm NGM;
	 
	public d_importindex(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insert_db() throws Exception
	{		
	 boolean l_status = false;
	  // VAL_insertDB();
	 
	 	PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into import_index values(?,now(),now(),?)");
		 ps.setString(1,NGM.ins_sess.user_id);
		 ps.setString(2,"S");
		 System.out.println(ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean update_db() throws Exception
	{		
	 boolean l_status = false;
	  // VAL_insertDB();
	 
	 	PreparedStatement ps=NGM.tr_conn.prepareStatement("update  import_index  set values(?,now(),now(),?)");
		 ps.setString(1,NGM.ins_sess.user_id);
		 ps.setString(2,"S");
		 System.out.println(ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	//-------------------------------------validation------------------------------------------------------------------------	
	
 
 
}
