package db; 
import java.sql.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;
//import db.*;
public class d_checkin_allot extends db_validation
{
	ngm NGM;
	utility 	  ins_util;
	d_checkin ins_check;	
	public String tr_id="",allot_id="",status;
	public JSONArray array1 = new JSONArray();

	public d_checkin_allot(ngm p_ngm){	
		ins_check=new d_checkin(NGM);		
		NGM=p_ngm;
		ins_util = new utility();

	}

	public boolean insertDB_check_allot() throws Exception
	{	

		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_checkin_allot VALUES (?, ?, ?,now())");
		ps.setString(1, tr_id);
		ps.setString(2, allot_id);
		ps.setString(3, "LIV");
		System.out.println("guestAAAAAAAA      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 

	public boolean insertDB_check_allot_upload() throws Exception
	{	

		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_checkin_allot VALUES (?, ?, ?,now())");
		ps.setString(1, tr_id);
		ps.setString(2, allot_id);
		ps.setString(3, "LIV");
		System.out.println("guestAAAAAAAA    upload  "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 

	public boolean updateDB_status() throws Exception
	{	

		boolean l_status=false;	
		VAL_updateDB_status();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_checkin_allot SET status=?,lastmodified=now() WHERE tr_id=? and allot_id=?");
		ps.setString(1,status);
		ps.setString(2,tr_id);
		ps.setString(3,allot_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	}

	public boolean updateDB_allot() throws Exception
	{	

		boolean l_status=false;	

		// String guest_id1 = String.valueOf(guest_id);
		VAL_updateDB_allot();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_checkin_allot SET allot_id=?,status=?,lastmodified=now() WHERE tr_id=?");
		ps.setString(1,allot_id);
		ps.setString(2, "CAN");
		ps.setString(3,tr_id);

		System.out.println("quert guest +++++++++++++++++++++++++++++++++++++  "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 

	public boolean updateDB_status_trid() throws Exception
	{	
		boolean l_status=false;	
		VAL_updateDB_status_trid();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_checkin_allot SET status=?,lastmodified=now() WHERE tr_id=?");
		ps.setString(1,status);
		ps.setString(2,tr_id);
		System.out.println("quert guest +++++++++++++++++++++++++++++++++++++  "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	}

	public boolean updateDB_allott() throws Exception
	{	
		boolean l_status=false;	
		VAL_updateDB_allott();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_checkin_allot SET status=?,lastmodified=now() WHERE tr_id=? and status='CIN'");
		ps.setString(1, "COUT");
		ps.setString(2,  tr_id);

		System.out.println("quert guest +++++++++++++++++++++++++++++++++++++  "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deletecheckin_allot() throws Exception {		
		boolean l_status=false;	
		VAL_deletecheckin_allot();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from tr_checkin_allot where tr_id=? and allot_id");
		pss.setString(1, tr_id);
		pss.setString(2, allot_id);
		System.out.println("DELETE       "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 


	public boolean select_check_allot() throws Exception {		
		boolean l_status=false;	
		VAL_select_check_allot();      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where allot_id=? and status!='CAN'");
		pss.setString(1, allot_id);
		ResultSet rs = pss.executeQuery();
		while(rs.next()){
			tr_id = rs.getString("tr_id");
			l_status= true;
		}
		rs.close();
		pss.close();

		return l_status;		
	} 
	public String select_Rooms() throws Exception {		
		boolean l_status=false;	
		VAL_select_Rooms();      
		String t_rooms=""; 
		PreparedStatement pss=NGM.tr_conn.prepareStatement("select block_name,room_no,address from(select DISTINCT ON(room_no,block_id,gh_id) room_no,block_id,gh_id from (select * from tr_checkin_allot where status!='CAN' and tr_id=?)bb join (select room_no,allot_id,block_id,gh_id from tr_allotment where status!='CAN')cc on bb.allot_id=cc.allot_id)aa join (select * from room_block)bb on aa.gh_id=bb.gh_id and aa.block_id=bb.block_id");
		//"select DISTINCT ON(room_no,block_id) room_no,block_id from (select * from tr_checkin_allot where status!='CAN' and tr_id=?)bb join (select room_no,allot_id,block_id from tr_allotment)cc on bb.allot_id=cc.allot_id ");
		pss.setString(1, tr_id);
		ResultSet rs = pss.executeQuery();
		while(rs.next()){
			if(!t_rooms.equals("")){
				t_rooms = t_rooms+",";
			}
			t_rooms = t_rooms + rs.getString("room_no")+"("+rs.getString("block_name")+")"+(rs.getString("address") == null ? "" : "-" + rs.getString("address"));
			l_status= true;
		}
		rs.close();
		pss.close();
		return t_rooms;		
	} 

	public int select_check_LIV() throws Exception {		
		boolean l_status=false;	
		VAL_select_check_LIV();   
		int count=0;
		PreparedStatement pss=NGM.tr_conn.prepareStatement("select count(*) from tr_checkin_allot where allot_id=? and status=?");
		pss.setString(1, allot_id);
		pss.setString(2, status);
		System.out.println("ps count CIN "+pss);
		ResultSet rs = pss.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
			l_status= true;
		}

		rs.close();
		pss.close();
		return count;		
	}


	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where tr_id = ? ");
		ps.setString(1,tr_id);
		System.out.println("gfhdsgfhd    "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			l_status= true;
			allot_id = rs.getString("allot_id");
			status = rs.getString("status");
		}
		rs.close();
		ps.close();
		return l_status;
	}

	public void setpost_upload(JSONObject jsonObj)throws Exception{
		System.out.println("jsong objeecct"+jsonObj);
		this.tr_id = ins_util.setNullvalue(jsonObj.get("tr_id"));

		this.allot_id =  ins_util.setNullvalue(jsonObj.get("allot_id"));
		this.status =  ins_util.setNullvalue(jsonObj.get("status"));



	}
	public void existdata()throws Exception{

		VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where tr_id=? and allot_id=? ");
		ps.setString(1, tr_id);
		ps.setString(1, allot_id);
		ResultSet rs = ps.executeQuery();             
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from tr_checkin_allot where tr_id=? and allot_id=?");
			ps1.setString(1, tr_id);
			ps.setString(1, allot_id);

			ps1.execute();
			ps1.close();

		}
		rs.close();
		ps.close(); 

	}
	public boolean selectimportdata(String time)throws Exception{ 
		boolean retval = false;
		System.out.println("importdata.........................");
		JSONArray jobj = new JSONArray();
		String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
		//			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
		// 
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin_allot where lastmodified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
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

	public void VAL_insertDB() throws Exception 
	{
		if(!(val_allotid(allot_id)&&val_trid(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_insertDB", NGM);
		}
		/*val_trid(tr_id);
		 val_allotid(allot_id);*/
	}
	public void VAL_updateDB_status() throws Exception 
	{
		if(!(val_allotid(allot_id)&&val_trid(tr_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_updateDB_status", NGM);
		}
		/* val_trid(tr_id);
		 val_allotid(allot_id);
		 val_status(status);*/
	}
	public void VAL_updateDB_allot() throws Exception 
	{
		if(!(val_allotid(allot_id)&&val_trid(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_updateDB_allot", NGM);
		}
		/*val_trid(tr_id);
		 val_allotid(allot_id);*/
	}
	public void VAL_updateDB_status_trid() throws Exception 
	{
		if(!(val_allotid(allot_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_updateDB_status_trid", NGM);
		}
	}
	public void VAL_updateDB_allott() throws Exception 
	{
		if(!(val_trid(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_updateDB_allott", NGM);
		}
		//val_trid(tr_id);
	}
	public void VAL_deletecheckin_allot() throws Exception 
	{
		if(!(val_trid(tr_id)&&val_allotid(allot_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_deletecheckin_allot", NGM);
		}
		//val_trid(tr_id);
		//val_allotid(allot_id);
	}
	public void VAL_select_check_allot() throws Exception 
	{
		if(!(val_allotid(allot_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_select_check_allot", NGM);
		}
		//val_allotid(allot_id);
	}
	public void VAL_select_Rooms() throws Exception 
	{
		if(!(val_trid(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_select_Rooms", NGM);
		}
		//val_trid(tr_id);
	}
	public void VAL_select_check_LIV() throws Exception 
	{
		System.out.println("allot_id "+allot_id);
		System.out.println("status "+status);
		if(!(val_allotid(allot_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_select_check_LIV", NGM);
		}
		/*val_allotid(allot_id);
		 val_status(status);*/
	}
	public void VAL_selectDB() throws Exception 
	{
		if(!(val_trid(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_selectDB", NGM);
		}
		//val_trid(tr_id);
	}
	public void VAL_existdata() throws Exception 
	{
		if(!(val_allotid(allot_id)&&val_trid(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin_allot", "Invalid tr_allotment parameter", "VAL_existdata", NGM);
		}
		/*val_trid(tr_id);
		val_allotid(allot_id);*/
	}

	public boolean val_trid(String tr_id){
		if(tr_id!=null){
			if(!cLength(tr_id.toString(), 0, 15)){
				return false;
			}
			if (!cNumeric(tr_id)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_allotid(String allot_id){
		if(allot_id!=null){
			if(!cLength(allot_id.toString(), 0, 15)){
				return false;
			}
			if (!cNumeric(allot_id)){
				return false;
			}
			return true;
		}else
			return false;

	}

}