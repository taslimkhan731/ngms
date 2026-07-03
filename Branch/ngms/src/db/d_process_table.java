package db;
import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

import model.ngmodel;
public class d_process_table  extends db_validation{
	public String process_id,created_by,created_date,status,officeorderno,gh_id,block_id,gosignature,gosignaturedesig;
	public JSONArray array1 = new JSONArray();
	ngm NGM;
	utility 	  ins_util;
	public d_process_table(ngm p_ngm){
		NGM=p_ngm;
		ins_util = new utility();
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		VAL_insertDB();
		System.out.println(" d_process_table insertDB process_id: "+process_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into process_table values(?,?,now(),?,?,now(),?,?,?,?)");
		ps.setString(1,process_id);
		ps.setString(2,created_by);
		ps.setString(3,status);
		ps.setString(4,officeorderno);
		ps.setString(5,gh_id);
		ps.setString(6, gosignature);
		ps.setString(7, gosignaturedesig);
		ps.setString(8, block_id);
		System.out.println("process table "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update process_table set created_by=?,created_date=now(),status=?,officeorderno=?,last_modified=now() where and process_id=?");
		ps.setString(1,created_by);
		ps.setString(2,status);
		ps.setString(3,officeorderno);
		ps.setString(4,process_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDBofcorder() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		VAL_updateDBofcorder();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update process_table set officeorderno=?,status=?,last_modified=now() where process_id=?");
		ps.setString(1,officeorderno);
		ps.setString(2,status);
		ps.setString(3,process_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDBstatus() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		VAL_updateDBstatus();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update process_table set status=?,last_modified=now() where process_id=?");
		ps.setString(1,status);
		ps.setString(2,process_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		//validate_key("deleteDB");
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from process_table where process_id=?");
		ps.setString(1,process_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from process_table where process_id=?");
		ps.setString(1,process_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.process_id=rs.getString("process_id");
			this.created_by=rs.getString("created_by");
			this.created_date=rs.getString("created_date");
			this.status=rs.getString("status");
			this.officeorderno=rs.getString("officeorderno");
			this.gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean insertDB_upload() throws Exception {		
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		VAL_insertDB_upload();
		System.out.println(" d_process_table insertDB process_id: "+process_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into process_table values(?,?,to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?,?,now(),?)");
		ps.setString(1,process_id);
		ps.setString(2,created_by);
		ps.setString(3,created_date);
		ps.setString(4,status);
		ps.setString(5,officeorderno);
		ps.setString(6,gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	  
	 
	 
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.process_id=  ins_util.setNullvalue(jsonObj.get("process_id"));
		 this.created_by = ins_util.setNullvalue(jsonObj.get("created_by"));
		 this.created_date = ins_util.setNullvalue(jsonObj.get("created_date"));
		 this.status = ins_util.setNullvalue(jsonObj.get("status").toString());
		 this.officeorderno =ins_util.setNullvalue(jsonObj.get("officeorderno"));
		 this.gh_id =ins_util.setNullvalue(jsonObj.get("gh_id"));
		 
	} 
	public void existdata()throws Exception{
		VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from process_table where process_id=?");
		ps.setString(1, process_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from process_table where process_id=?");
			ps1.setString(1, process_id);
			ps1.execute();
			ps1.close(); 
		}
		rs.close();
		ps.close();	 
	 
		
	}
	
	public boolean existdata_accept()throws Exception{
		VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from process_table where process_id=?");
		ps.setString(1, process_id);
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
			JSONArray jobj = new JSONArray();
			String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from process_table where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
			ps.setString(1,time);
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from process_table");

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
	/*******************************************************validation*******************************************************************/
	public void VAL_insertDB() throws Exception {
		
		if(!(val_pro_id(process_id))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_insertDB", NGM);
		}
		if(!(val_user_id(created_by))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid created_by", "VAL_insertDB", NGM);
		}
		if(!(val_status(status))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid status", "VAL_insertDB", NGM);
		}
		if(!(val_officeorder(officeorderno))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid officeorderno", "VAL_insertDB", NGM);
		}
	}
public void VAL_updateDB() throws Exception {
		
		if(!(val_pro_id(process_id))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_updateDB", NGM);
		}
		if(!(val_user_id(created_by))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid created_by", "VAL_updateDB", NGM);
		}
		if(!(val_status(status))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid status", "VAL_updateDB", NGM);
		}
		if(!(val_officeorder(officeorderno))){
			throw NGM.ins_utility.setException("d_process_table", "Invalid officeorderno", "VAL_updateDB", NGM);
		}
	}
public void VAL_updateDBofcorder() throws Exception {
	
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_updateDBofcorder", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid status", "VAL_updateDBofcorder", NGM);
	}
	if(!(val_officeorder(officeorderno))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid officeorderno", "VAL_updateDBofcorder", NGM);
	}
}
public void VAL_updateDBstatus() throws Exception {
	
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_updateDBstatus", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid status", "VAL_updateDBstatus", NGM);
	}
}
public void VAL_deleteDB() throws Exception {
	
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_deleteDB", NGM);
	}
}
public void VAL_selectDB() throws Exception {
	
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_selectDB", NGM);
	}
}
public void VAL_insertDB_upload() throws Exception {
	
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_user_id(created_by))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid created_by", "VAL_insertDB_upload", NGM);
	}
	String[] t_date=created_date.split(" ");
	if(!(val_date(t_date[0]))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid created_date", "VAL_insertDB_upload", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid status", "VAL_insertDB_upload", NGM);
	}
	if(!(val_officeorder(officeorderno))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid officeorderno", "VAL_insertDB_upload", NGM);
	}
}
public void VAL_existdata() throws Exception {
	
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_process_table", "Invalid process_id", "VAL_existdata", NGM);
	}
}
}