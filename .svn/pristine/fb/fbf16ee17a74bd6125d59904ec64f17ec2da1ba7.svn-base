 package db;
import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.utility;

import model.ngmodel;
public class d_process_table_client extends ngmodel{
	public String process_id,created_by,created_date,status,officeorderno,gh_id;
	public JSONArray array1 = new JSONArray();
	ngm NGM;
	utility 	  ins_util;
	public d_process_table_client(ngm p_ngm){
		NGM=p_ngm;
		ins_util = new utility();
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		System.out.println(" d_process_table insertDB process_id: "+process_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into process_table_client values(?,?,now(),?,?,?)");
		ps.setString(1,process_id);
		ps.setString(2,created_by);
		ps.setString(3,status);
		ps.setString(4,officeorderno);
		ps.setString(5,gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	  
	 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		//validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from process_table_client where process_id=?");
		ps.setString(1,process_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.process_id=  ins_util.setNullvalue(jsonObj.get("process_id"));
		 this.created_by = ins_util.setNullvalue(jsonObj.get("created_by"));
		 this.status = ins_util.setNullvalue(jsonObj.get("status").toString());
		 this.officeorderno =ins_util.setNullvalue(jsonObj.get("officeorderno"));
		 this.gh_id =ins_util.setNullvalue(jsonObj.get("gh_id"));
		 
	} 
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from process_table_client where process_id=?");
		ps.setString(1, process_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from process_table_client where process_id=?");
			ps1.setString(1, process_id);
			ps1.execute();
			ps1.close();
		}
		rs.close();
		ps.close();	 
	 
		
	}
	 
}