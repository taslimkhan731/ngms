package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_reservation_waitinglist extends db_validation
{
	public String res_wid,mod_id,req_id,block_id,number,type,par_process_id,process_id,status,gh_id;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	utility 	  ins_util;
	public d_reservation_waitinglist(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into reservation_waitinglist values(?,?,?,?,?,?,?,?,?,?,now())");
		ps.setString(1, res_wid);
		ps.setString(2, mod_id);
		ps.setString(3, req_id);
		ps.setString(4, block_id);
		ps.setInt(5, 1);
		ps.setString(6, type);
		ps.setString(7, par_process_id);
		ps.setString(8, process_id);
		ps.setString(9, status);
		ps.setString(10, gh_id);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB_gadreq() throws Exception
	{		
		boolean l_status=false;	
	/*	System.out.println("inside the updateDB_gad");
	  // VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update reservation_waitinglist set gh_id=?,lastupdated =now(),from_date=TO_DATE(?,'yyyy-MM-dd'),to_date=TO_DATE(?,'yyyy-MM-dd'),nor_req=?,nob_g_req=?,nob_l_req=?,is_veh_req=?,purpose=?,ext=?,address=? where req_id=?");
		
		ps.setString(1, gh_id);
		ps.setString(2, from_date);
		ps.setString(3, to_date);
		ps.setString(4, nor_req);
		ps.setString(5, nob_g_req);
		ps.setString(6, nob_l_req);
		//ps.setString(8, "REQ");
		ps.setString(7, is_veh_req);
		//ps.setString(10, "GAD");
		ps.setString(8, purpose);
		ps.setString(9, ext);
		ps.setString(10, address1);
		ps.setString(11, req_id);
		System.out.println("quert    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		
		ps.close();*/
		return l_status;		
	} 
	public boolean update_status(String cur_status) throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_waitinglist set status=?,process_id=? ,last_modified=now() where req_id=? and status=? and res_wid=?");
	    pss.setString(1, status);
	    pss.setString(2, process_id);
	    pss.setString(3, req_id);
	    pss.setString(4, cur_status);
	    pss.setString(5, res_wid);

		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean update_status_alot() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_waitinglist set status=? , last_modified=now() where res_wid=?");
	    pss.setString(1, status);
	    pss.setString(2, res_wid);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean update_status_can_req() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_waitinglist set status=?,last_modified=now() where req_id=?");
	    pss.setString(1, status);
	    pss.setString(2, req_id);
		System.out.println("++++++++++++++++fgbdf+++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean update_statuspro() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_waitinglist set status=?,last_modified=now() where par_process_id=?");
	    pss.setString(1, status);
	    pss.setString(2, par_process_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	

	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from reservation_waitinglist where req_id=?");
		pss.setString(1, req_id);
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss delete gad   "+pss);
		pss.close();
		return l_status;		
	} 
	
	
	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			status=rs.getString("status");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	
	public boolean selectDB_wait() throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where req_id=? and status='WMOD'");
		ps.setString(1, req_id);
		System.out.println("selectDB_wait ***** "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			status=rs.getString("status");
			gh_id=rs.getString("gh_id");
			par_process_id=rs.getString("par_process_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	
	public boolean selectDBpro() throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where par_process_id=?");
		ps.setString(1, par_process_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			status=rs.getString("status");
			req_id=rs.getString("req_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean insertDB_upload() throws Exception
	{		
		boolean l_status=false;	
		
		VAL_insertDB_upload();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into reservation_waitinglist values(?,?,?,?,?,?,?,?,?,?,now())");
		ps.setString(1, res_wid);
		ps.setString(2, mod_id);
		ps.setString(3, req_id);
		ps.setString(4, block_id);
		ps.setInt(5, 1);
		ps.setString(6, type);
		ps.setString(7, par_process_id);
		ps.setString(8, process_id);
		ps.setString(9, status);
		ps.setString(10, gh_id);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 System.out.println("in json for dsreserser"+jsonObj);
		 System.out.println(jsonObj.get("res_wid"));
		this.res_wid =   ins_util.setNullvalue(jsonObj.get("res_wid"));
		 this.type =  ins_util.setNullvalue(jsonObj.get("type"));
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
		 this.type =  ins_util.setNullvalue(jsonObj.get("type"));
		 this.status =  ins_util.setNullvalue(jsonObj.get("status"));
		 this.mod_id = ins_util.setNullvalue(jsonObj.get("mod_id"));
		 this.block_id =  ins_util.setNullvalue(jsonObj.get("block_id"));
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 
		 this.par_process_id =  ins_util.setNullvalue(jsonObj.get("par_process_id"));
		 this.process_id =  ins_util.setNullvalue(jsonObj.get("process_id"));
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
		 
		 
		 
		 
	} 
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where res_wid=?");
		ps.setString(1, res_wid);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from reservation_waitinglist where res_wid=?");
			ps1.setString(1, res_wid);
			ps1.execute();
			ps1.close();
		}
		rs.close();
		ps.close();
	}
	
	public boolean existdata_accept()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where res_wid=?");
		ps.setString(1, res_wid);
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
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours' )");
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
		ps.setString(1,time);
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist");

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
	
public String select_wait_guests(String tfrom_date) throws Exception
{
	String tname = "",prev_req="";
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select a.req_id,name from (select distinct req_id  from reservation_waitinglist where status!='CAN' and block_id=? and req_id in (select distinct req_id from gad_request where (to_date(?,'dd-MM-yyyy') between date(from_date) and date(to_date)) and gh_id=? and status!='CAN' and status!='REQ') and req_id not in (select distinct req_id from reservation_main_table where block_id=? and (to_date(?,'dd-MM-yyyy') between date(from_date) and date(to_date)) and gh_id=? and status!='CAN')) a join (select name,req_id,guest_id from guest_details) b on a.req_id=b.req_id order by b.guest_id");
	ps.setString(1, block_id);
	ps.setString(2, tfrom_date);
	ps.setString(3, gh_id);
	ps.setString(4, block_id);
	ps.setString(5, tfrom_date);
	ps.setString(6, gh_id);
	System.out.println("LLLLLLKKKK req_id wait : "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
		if(tname.equals("")){
			tname = "<tr><td>"+rs.getString("name");
		}else{
		
			if(prev_req.equals(rs.getString("req_id")))
			{
				tname = tname+", "+rs.getString("name");
			}else{
				tname = tname+"</td></tr><tr><td>"+rs.getString("name");
			}
			
			if(tname.equals("")){
				tname = tname+"<\td><\tr>";
			}
		}
		prev_req = rs.getString("req_id");
	}
	
	System.out.println("tname wait "+tname);
	
	ps.close();
	rs.close();
	return tname; 		
}

//-------------------------------------validation------------------------------------------------------------------------	
	
public void VAL_insertDB() throws Exception {
	if(!(val_res_id(res_wid))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid res_id", "VAL_insertDB", NGM);
	}
	if(!(val_modid(mod_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid mod_id", "VAL_insertDB", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid status", "VAL_insertDB", NGM);
	}
	if(!(val_blockid(block_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid block_id", "VAL_insertDB", NGM);
	}
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid req_id", "VAL_insertDB", NGM);
	}
	if(!(val_type(type))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid type", "VAL_insertDB", NGM);
	}
	if(!(val_ghid(gh_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid gh_id", "VAL_insertDB", NGM);
	}
	if(!(val_pro_id(par_process_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid par_process_id", "VAL_insertDB", NGM);
	}
	if(!(val_ghid(gh_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid gh_id", "VAL_insertDB", NGM);
	}
}
public void VAL_insertDB_upload() throws Exception {
	if(!(val_res_id(res_wid))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid res_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_modid(mod_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid mod_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid status", "VAL_insertDB_upload", NGM);
	}
	if(!(val_blockid(block_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid block_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid req_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_type(type))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid type", "VAL_insertDB_upload", NGM);
	}
	if(!(val_ghid(gh_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid gh_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_pro_id(par_process_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid par_process_id", "VAL_insertDB_upload", NGM);
	}
	if(!(val_ghid(gh_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid gh_id", "VAL_insertDB_upload", NGM);
	}
}
}
