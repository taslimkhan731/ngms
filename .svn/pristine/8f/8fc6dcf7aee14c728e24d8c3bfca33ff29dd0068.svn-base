package db; 
import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;
import db.*;
public class d_mod_req_client extends db_validation{
	public String vc_id,quota_type,process_id,is_veh_req,reqst,status,last_modified,gh_id;
	public String req_id;
	public int mod_id,mrr_id;
	public String id, tablename,aliasname,tr_uin_id,tr_up_id,name,address,dob,mod_id1;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	public JSONObject jsonObj;
	utility 	  ins_util;
	public d_mod_req_client(ngm p_ngm){		
		NGM=p_ngm;
		jsonObj = new JSONObject(); 
		ins_util = new utility();
			
	}
	
public boolean insertDB_mod() throws Exception {		
		boolean l_status=false;	
		 /*req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
		 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);*/
		  
	     
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("insert into mod_req_client values(?,?,?,?,?,?,?,now(),?)");
		pss.setInt(1, mod_id);
		pss.setString(2, req_id);
		pss.setString(3, vc_id);
		pss.setString(4, quota_type);
		pss.setString(5, is_veh_req);
		pss.setString(6, process_id);
		pss.setString(7, status);
		pss.setString(8, gh_id);
		System.out.println("QUERY      "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("QUERY      "+pss);
		pss.close();
		return l_status;		
	} 


 

  
public boolean deletelist() throws Exception {		
	boolean l_status=false;	
	      
    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from mod_req_client where req_id=?");
	 
	pss.setString(1, reqst);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public void setpost_mod(JSONObject jsonObj)throws Exception{
	 
	 this.mod_id1 =   ins_util.setNullvalue(jsonObj.get("mod_id").toString());
	 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id").toString());
	 this.vc_id =  ins_util.setNullvalue(jsonObj.get("vc_id").toString());
	 this.quota_type = ins_util.setNullvalue(jsonObj.get("vc_id").toString());
	 this.is_veh_req =  ins_util.setNullvalue(jsonObj.get("is_veh_req").toString());
	 this.process_id =  ins_util.setNullvalue(jsonObj.get("process_id").toString());
	 this.status =  ins_util.setNullvalue(jsonObj.get("status").toString());
	 this.last_modified =  ins_util.setNullvalue(jsonObj.get("lastupdate").toString());
	 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id").toString());
} 
	 
	 
 

public void existdata()throws Exception{
	
	 
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req_client where mod_id=?");
	ps.setString(1, mod_id1);
	System.out.println("vehicle"+ps);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from mod_req_client where mod_id=?");
		ps1.setString(1, mod_id1);
		ps1.execute();
		ps1.close(); 
	}
	rs.close();
	ps.close();	 
 
	
}

 


 

 
 


 
}

