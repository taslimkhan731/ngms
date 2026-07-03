package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_reservation_maintable extends db_validation
{
	public String res_id,from_date,to_date,checkin_date,checkout_date,type,status,block_id,req_id,processid,gh_id,quota_type,total_number1;
	public int total_number;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	utility 	  ins_util;
	public d_reservation_maintable(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		System.out.println("11111111111111111111111111 d_reservation_maintable insertDB ");
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into reservation_main_table values(?,to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,now())");
		ps.setString(1, res_id);
		ps.setString(2, from_date);
		ps.setString(3, to_date);
		ps.setString(4, checkin_date);
		ps.setString(5, checkout_date);
		ps.setString(6, type);
		ps.setString(7, status);
		ps.setString(8, block_id);
		ps.setString(9, req_id);
		ps.setString(10, processid);
		ps.setString(11, gh_id);
		ps.setInt(12, total_number);
		ps.setString(13, quota_type);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean update_checkout_status() throws Exception
	{		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_main_table set checkout_date=to_timestamp(?,'dd-MM-yyyy HH24:MI'),last_modified=now() where res_id=?");
	    pss.setString(1, checkout_date);
	    pss.setString(2, res_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean update_status() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_main_table set status=?,last_modified=now() where req_id=?");
	    pss.setString(1, status);
	    pss.setString(2, req_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean update_status_alot() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_main_table set status=?,last_modified=now() where res_id=?");
	    pss.setString(1, status);
	    pss.setString(2, res_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean update_checkout() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_main_table set checkout_date=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),last_modified=now() where res_id=?");
	    pss.setString(1, checkout_date);
	    pss.setString(2, res_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	}
	
	public boolean update_statuspro() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update reservation_main_table set status=?,last_modified=now() where process_id=?");
	    pss.setString(1, status);
	    pss.setString(2, processid);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	

	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from reservation_main_table where req_id=?");
		 
		pss.setString(1, req_id);
		
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss delete gad   "+pss);
		pss.close();
		return l_status;		
	} 
	
	
	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where req_id=?");
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
	
	public boolean selectDB_cnf() throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where req_id=? and status!='CAN'");
		ps.setString(1, req_id);
		System.out.println("selectDB_cnf ***** "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			status=rs.getString("status");
			processid=rs.getString("process_id");
			gh_id=rs.getString("gh_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean selectDBpro() throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where process_id=?");
		ps.setString(1, processid);
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
	
	public String selectDBoccupied(String t_type) throws Exception {		
		boolean l_status=false;	
	    // VAL_insertDB();
		String sum = "";
		System.out.println("{{{{{{{{{{{{{{{{{{ checkin_date "+checkin_date);
		System.out.println("{{{{{{{{{{{{{{{{{{ checkout_date "+checkout_date  );
	    //PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total_number) from reservation_main_table  where gh_id=? and block_id=? and quota_type=? and type=? and status=?");// and from_date between to_timestamp(?,'yyyy-MM-dd') and to_timestamp(?,'yyyy-MM-dd')
		//select * from reservation_main_table where gh_id='1' and block_id='1' and quota_type='RES' and type='R' and status!='CAN' and (from_date between '2014-11-15 00:00:00' and '2014-11-20 00:00:00' or to_date between '2014-11-15 00:00:00' and '2014-11-20 00:00:00' or (from_date<'2014-11-15 00:00:00' and to_date>'2014-11-20 00:00:00'))
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total_number) from reservation_main_table where gh_id=? and block_id=? and quota_type=? and type=? and status!='CAN' and (checkin_date between to_timestamp(?,'yyyy-MM-dd HH24:MI:SS') and to_timestamp(?,'yyyy-MM-dd HH24:MI:SS') or checkout_date between to_timestamp(?,'yyyy-MM-dd HH24:MI:SS') and to_timestamp(?,'yyyy-MM-dd HH24:MI:SS') or (checkin_date<to_timestamp(?,'yyyy-MM-dd HH24:MI:SS') and checkout_date>to_timestamp(?,'yyyy-MM-dd HH24:MI:SS')))");
	    ps.setString(1, gh_id);
		ps.setString(2, block_id);
		ps.setString(3, quota_type);
		ps.setString(4, t_type);
		//ps.setString(5, status);
		ps.setString(5, checkin_date);
		ps.setString(6, checkout_date);
		ps.setString(7, checkin_date);
		ps.setString(8, checkout_date);
		ps.setString(9, checkin_date);
		ps.setString(10, checkout_date);
		System.out.println("pssssssssssssssssss "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			sum=rs.getString("sum");
			l_status=true;
		}
		rs.close();
		ps.close();
		return sum;
	} 	
	
	public boolean insertDB_upload() throws Exception
	{		
		boolean l_status=false;	
		System.out.println("11111111111111111111111111 d_reservation_maintable insertDB ");
		VAL_insertDB_upload();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into reservation_main_table values(?,to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,now())");
		ps.setString(1, res_id);
		ps.setString(2, from_date);
		ps.setString(3, to_date);
		ps.setString(4, checkin_date);
		ps.setString(5, checkout_date);
		ps.setString(6, type);
		ps.setString(7, status);
		ps.setString(8, block_id);
		ps.setString(9, req_id);
		ps.setString(10, processid);
		ps.setString(11, gh_id);
		ps.setInt(12, Integer.parseInt(total_number1));
		ps.setString(13, quota_type);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	 
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.res_id =  ins_util.setNullvalue(jsonObj.get("res_id"));
		 this.from_date =  ins_util.setNullvalue(jsonObj.get("from_date"));
		 this.to_date =  ins_util.setNullvalue(jsonObj.get("to_date"));
		 this.checkin_date = ins_util.setNullvalue(jsonObj.get("checkin_date"));
		 this.checkout_date =  ins_util.setNullvalue(jsonObj.get("checkout_date"));
		 this.type =  ins_util.setNullvalue(jsonObj.get("type"));
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
		 this.type =  ins_util.setNullvalue(jsonObj.get("type"));
		 this.status =  ins_util.setNullvalue(jsonObj.get("status"));
		 this.block_id = ins_util.setNullvalue(jsonObj.get("block_id"));
		 this.req_id = ins_util.setNullvalue(jsonObj.get("req_id"));
		 
		 this.processid = ins_util.setNullvalue(jsonObj.get("process_id"));
		 this.gh_id = ins_util.setNullvalue(jsonObj.get("gh_id"));
		 
		 this.total_number1 = ins_util.setNullvalue(jsonObj.get("total_number"));
		 this.quota_type = ins_util.setNullvalue(jsonObj.get("quota_type"));
		 
		 
	} 
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where res_id=?");
		ps.setString(1, res_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from reservation_main_table where res_id=?");
			ps1.setString(1, res_id);
			ps1.execute();
			ps1.close();
			 
		}
		rs.close();
		ps.close();
	}
	
	public boolean existdata_accept()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where res_id=?");
		ps.setString(1, res_id);
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
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
			ps.setString(1,time);
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table");

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
	
	public String select_res_guests() throws Exception
	{
		String tname = "",prev_req="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select a.req_id,name from (select distinct req_id from reservation_main_table where block_id=? and (to_date(?,'yyyy-MM-dd') between date(from_date) and date(to_date)) and gh_id=? and status!='CAN') a join (select name,req_id,guest_id from guest_details) b on a.req_id=b.req_id order by b.guest_id");
		ps.setString(1, block_id);
		ps.setString(2, from_date);
		ps.setString(3, gh_id);
		System.out.println("LLLLLLKKKK req_id : "+ps);
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
					//tname = "<tr><td>"+rs.getString("name");
				
				if(tname.equals("")){
					tname = tname+"<\td><\tr>";
				}
			}
			//+"</td></tr>";
			/*}else{
				//tname = tname +", \n "+rs.getString("name");
				tname = tname +"<td>"+rs.getString("name")+"</td></tr>";
			}*/
			prev_req = rs.getString("req_id");
		}
		/*if(!tname.equals("")){
			tname = tname+"</tr>";
		}*/
		
		System.out.println("tname "+tname);
		
		ps.close();
		rs.close();
		return tname; 		
	}
//-------------------------------------validation------------------------------------------------------------------------	
public void VAL_insertDB() throws Exception {
		
		if(!(val_res_id(res_id))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid res_id", "VAL_insertDB", NGM);
		}
		 String[] t_from = from_date.split(" ");
		 System.out.println("t_from "+t_from);
		 if(!(val_date_dmy(t_from[0]))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid from_date", "VAL_insertDB", NGM);
		}
		 String[] t_to = to_date.split(" ");
		 if(!(val_date_dmy(t_to[0]))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid to_date", "VAL_insertDB", NGM);
		}
		 String[] t_checkin = checkin_date.split(" ");
		 if(!(val_date_dmy(t_checkin[0]))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid checkin_date", "VAL_insertDB", NGM);
		}
		 String[] t_checkout = checkout_date.split(" ");
		 if(!(val_date_dmy(t_checkout[0]))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid checkout_date", "VAL_insertDB", NGM);
		}
		if(!(val_type(type))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid type", "type", NGM);
		}
		if(!(val_status(status))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid status", "status", NGM);
		}
		if(!(val_blockid(block_id))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid block_id", "block_id", NGM);
		}
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid req_id", "VAL_insertDB", NGM);
		}
		if(!(val_pro_id(processid))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid processid", "VAL_insertDB", NGM);
		}
		if(!(val_ghid(gh_id))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid gh_id", "VAL_insertDB", NGM);
		}
		if(!(val_one_zero(""+total_number))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid total_number", "VAL_insertDB", NGM);
		}
		if(!(val_type(quota_type))){
			throw NGM.ins_utility.setException("reservation_main_table", "Invalid quota_type", "VAL_insertDB", NGM);
		}
	}
public void VAL_insertDB_upload() throws Exception {
	
	if(!(val_res_id(res_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid res_id", "VAL_insertDB", NGM);
	}
	String[] t_from = from_date.split(" ");
	 if(!(val_date_dmy(t_from[0]))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid from_date", "VAL_insertDB", NGM);
	}
	 String[] t_to = to_date.split(" ");
	 if(!(val_date_dmy(t_to[0]))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid to_date", "VAL_insertDB", NGM);
	}
	 String[] t_checkin = checkin_date.split(" ");
	 if(!(val_date_dmy(t_checkin[0]))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid checkin_date", "VAL_insertDB", NGM);
	}
	 String[] t_checkout = checkout_date.split(" ");
	 if(!(val_date_dmy(t_checkout[0]))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid checkout_date", "VAL_insertDB", NGM);
	}
	if(!(val_type(type))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid type", "type", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid status", "status", NGM);
	}
	if(!(val_blockid(block_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid block_id", "block_id", NGM);
	}
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid req_id", "VAL_insertDB", NGM);
	}
	if(!(val_pro_id(processid))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid processid", "VAL_insertDB", NGM);
	}
	if(!(val_ghid(gh_id))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid gh_id", "VAL_insertDB", NGM);
	}
	if(!(val_one_zero(""+total_number1))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid total_number", "VAL_insertDB", NGM);
	}
	if(!(val_type(quota_type))){
		throw NGM.ins_utility.setException("reservation_main_table", "Invalid quota_type", "VAL_insertDB", NGM);
	}
}

public String select_res_main() throws Exception
{
	String tname = "",prev_req="";
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select a.req_id,name from (select distinct req_id from reservation_main_table where block_id=? and (to_date(?,'dd-MM-yyyy') between date(from_date) and date(to_date)) and gh_id=? and status!='CAN') a join (select name,req_id,guest_id from guest_details) b on a.req_id=b.req_id order by b.guest_id");
	ps.setString(1, block_id);
	ps.setString(2, from_date);
	ps.setString(3, gh_id);
	System.out.println("LLLLLLKKKK req_id : "+ps);
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
	System.out.println("tname "+tname);
	
	ps.close();
	rs.close();
	return tname; 		
}

 
}
