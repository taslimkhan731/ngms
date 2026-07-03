package db; 
import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itextpdf.text.log.SysoLogger;

import common.db_validation;
import common.utility;

public class d_mod_req_room_client extends db_validation{
	public String block_id,type,vc_id,quota_type,process_id,is_veh_req,req_id,reqst,gh_id,mod;
	public String mod_id,mrr_id,number;
	public int number1;   //nor_req,nob_g_req,nob_l_req,
	ngm NGM;
	utility 	  ins_util;
	public JSONArray array1 = new JSONArray();
	public d_mod_req_room_client(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}
	
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
 
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into mod_req_room_client values(?,?,?,?,?,?,?)");
		ps.setString(1, mrr_id);
		ps.setString(2, mod_id);
		ps.setString(3, req_id);
		ps.setString(4, block_id);
		ps.setInt(5, Integer.parseInt(number));
		ps.setString(6, type);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		System.out.println("QUERY1111      "+ps);
		ps.close();
		return l_status;		
	} 
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.mrr_id =  ins_util.setNullvalue( jsonObj.get("mrr_id").toString());
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id").toString());
		 this.mod_id =  ins_util.setNullvalue(jsonObj.get("mod_id").toString());
		 this.block_id = ins_util.setNullvalue(jsonObj.get("block_id").toString());
		 this.number = ins_util.setNullvalue(jsonObj.get("number").toString());
		 this.type =  ins_util.setNullvalue(jsonObj.get("type").toString());
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id").toString());
 		 
	} 
	public boolean deletemrr() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from mod_req_room where req_id=?");
		 
		pss.setString(1, reqst);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	 
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req_room_client where mrr_id=?");
		ps.setString(1, mrr_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from mod_req_room_client where mrr_id=?");
			ps1.setString(1, mrr_id);
			ps1.execute();
			ps1.close();
			 
		}
		rs.close();
		ps.close();	 
	 
		
	}
	
	 

//-------------------------------------------------validation-------------------------------------------------------------------



 
	 
}
