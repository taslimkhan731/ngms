package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_cancel_reservation extends db_validation
{
	public String cancel_id,req_id,process_id,gh_id,block_id,status,id_processid;
 	ngm NGM;
	utility 		ins_util;
	public JSONArray array1 = new JSONArray();
	public d_cancel_reservation(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	
	
	public boolean setProcesssid_cancellation(String p_process_id) throws Exception
	{
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update  cancel_reservation set process_id = ? where process_id is null");
		ps.setString(1, p_process_id);
		
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
		
		
	}
	public boolean update_status() throws Exception {		
		boolean l_status=false;			 
		
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update cancel_reservation set status=?,lastmodified  =now() where process_id=?");
	    pss.setString(1, status);
	    pss.setString(2, process_id);
	    System.out.println("processing...........status"+pss  );
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	}
	
	
	
	public boolean resetProcesssid_cancellation(String p_process_id) throws Exception
	{
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update  cancel_reservation set process_id = null  where process_id = ?");
		ps.setString(1, p_process_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		System.out.println("process_idprocess_idprocess_idprocess_id ::: "+process_id);
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into cancel_reservation(can_id,req_id, process_id,lastmodified,gh_id,block_id,status,id_processid ) values(?,?,?,now(),?,?,?,?)");
		ps.setString(1, cancel_id);
		ps.setString(2, req_id);
		ps.setString(3, process_id);
		ps.setString(4, gh_id);
		ps.setString(5, block_id);
		ps.setString(6, status);
		ps.setString(7, process_id);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_upload() throws Exception
	{		
		boolean l_status=false;	
		
	//	VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into cancel_reservation values(?,?,?,?,now(),?)");
		ps.setString(1, cancel_id);
		ps.setString(2, req_id);
		ps.setString(3, process_id);
		ps.setString(4, gh_id);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean select_DB() throws Exception
	{
		boolean l_status=false;
		VAL_select_DB();
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from cancel_reservation where process_id=?");
		ps1.setString(1, process_id);
		System.out.println("ps1    "+ps1);
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
			
			req_id= rs1.getString("req_id");
			status=rs1.getString("status");
			
			l_status = true; 
		}
		rs1.close();
		ps1.close();
		return l_status;
	}
	public boolean selectimportdata(String time)throws Exception{ 
		boolean retval = false;
		System.out.println("importdata.........................");
			JSONArray jobj = new JSONArray();
			String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
		// 
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from cancel_reservation where lastmodified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
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
			 
	public boolean existdata_accept()throws Exception{
		
		//VAL_existdata_accept();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from cancel_reservation where req_id=?");
		ps.setString(1, req_id);
		System.out.println("xfcdf "+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
		return true;	 
			 
		}
		rs.close();
		ps.close();	 
	 return false;
		
	}
	
	public void setpost_cancel(JSONObject jsonObj)throws Exception{
		 System.out.println("jsong objeecct"+jsonObj);
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 this.cancel_id = ins_util.setNullvalue(jsonObj.get("can_id"));
		 this.process_id = ins_util.setNullvalue(jsonObj.get("process_id"));
		
		 this.gh_id =ins_util.setNullvalue(jsonObj.get("gh_id"));	 
		 
	}
	 
	public void existdata()throws Exception{
		
		// VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from cancel_reservation where req_id=?");
		ps.setString(1, req_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from cancel_reservation where req_id=?");
			ps1.setString(1, req_id);
			ps1.execute();
			ps1.close();
			 
		}
		rs.close();
		ps.close();
	}
	/********************* validation **************************************/
	
	public void VAL_insertDB() throws Exception {
		
		if(!(val_pro_id(process_id))){
			throw NGM.ins_utility.setException("cancel_reservation", "Invalid process_id", "VAL_insertDB", NGM);
		}
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("cancel_reservation", "Invalid req_id", "VAL_insertDB", NGM);
		}
		if(!(val_reqid(cancel_id))){
			throw NGM.ins_utility.setException("cancel_reservation", "Invalid cancel_id", "VAL_insertDB", NGM);
		}
	}
	public void VAL_select_DB() throws Exception {
		
		if(!(val_pro_id(process_id))){
			throw NGM.ins_utility.setException("cancel_reservation", "Invalid process_id", "VAL_insertDB", NGM);
		}
	}
 
}