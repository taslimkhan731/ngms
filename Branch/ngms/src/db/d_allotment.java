package db;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.common;
import common.db_validation;
import common.utility;
public class d_allotment extends db_validation{
	public JSONArray array1 = new JSONArray();

	public String allot_id, req_id, res_wl_id, from_date, to_date, checkin_date,checkout_date, type, status, gh_id, block_id, room_no,room_rent, bed_no,tr_id, res_wl;
	ngm NGM;
	utility 	  ins_util;
	public d_allotment(ngm p_ngm){
		NGM=p_ngm;
		ins_util = new utility();

	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_allotment VALUES (?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?,?,now(),?)");
		ps.setString(1, allot_id);
		ps.setString(2, req_id);
		ps.setString(3, res_wl_id);
		ps.setString(4, from_date);
		ps.setString(5, to_date);
		ps.setString(6, checkin_date);
		ps.setString(7, checkout_date);
		ps.setString(8, type);
		ps.setString(9, status);
		ps.setString(10, gh_id);
		ps.setString(11, block_id);
		ps.setString(12, room_no);
		ps.setString(13, bed_no);
		ps.setString(14, tr_id);
		ps.setString(15, res_wl);
		ps.setString(16, room_rent);
		System.out.println("insertDB--------------------ins------------------------------------------- "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_upload() throws Exception {		
		boolean l_status=false;	
		System.out.println("GOPIKAaaaa");
       // VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_allotment VALUES (?,?,?,to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,now())");
		ps.setString(1, allot_id);
		ps.setString(2, req_id);
		ps.setString(3, res_wl_id);
		ps.setString(4, from_date);
		ps.setString(5, to_date);
		ps.setString(6, checkin_date);
		ps.setString(7, checkout_date);
		ps.setString(8, type);
		ps.setString(9, status);
		ps.setString(10, gh_id);
		ps.setString(11, block_id);
		ps.setString(12, room_no);
		ps.setString(13, bed_no);
		ps.setString(14, tr_id);
		ps.setString(15, res_wl);
		System.out.println("insertDB---------------------------upload------------------------------------ "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception
	{
		System.out.println("FFFFFFFFFFFFFFFFFFFFFF");
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_allotment SET req_id=?, res_wl_id=?, from_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), to_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), checkin_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), checkout_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'), type=?, status=?, gh_id=?, block_id=?, room_no=?, bed_no=?, tr_id=?, res_wl=?,lastmodified=now() WHERE allot_id=?");

		ps.setString(1, req_id);
		ps.setString(2, res_wl_id);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, checkin_date);
		ps.setString(6, checkout_date);
		ps.setString(7, type);
		ps.setString(8, status);
		ps.setString(9, gh_id);
		ps.setString(10, block_id);
		ps.setString(11, room_no);
		ps.setString(12, bed_no);
		ps.setString(13, tr_id);
		ps.setString(14, res_wl);
		ps.setString(15, allot_id);
		System.out.println("update        "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
		
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_allotment WHERE allot_id=?");
		ps.setString(1, allot_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean select_exist() throws Exception
	{
		boolean l_status=false;
		VAL_select_exist();
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and block_id=? and type=?");
		ps1.setString(1, room_no);
		ps1.setString(2, from_date);
		ps1.setString(3, block_id);
		ps1.setString(4, type);
		System.out.println("Data............."+ps1);
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
		 
			allot_id= rs1.getString("allot_id");
			System.out.println("data exist........."+allot_id);
			l_status = true; 
		}
		ps1.close();
		rs1.close();
		return l_status; 		
	}
	
	public boolean select_DB() throws Exception
	{
		boolean l_status=false;
		VAL_select_DB();
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from tr_allotment where allot_id=? and gh_id=?");
		ps1.setString(1, allot_id);
		ps1.setString(2, gh_id);
		System.out.println("ps1    "+ps1);
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
		 
			allot_id= rs1.getString("allot_id");
			req_id= rs1.getString("req_id");
			res_wl_id= rs1.getString("res_wl_id");
			from_date= rs1.getString("from_date");
			to_date= rs1.getString("to_date");
			checkin_date= rs1.getString("checkin_date");
			checkout_date= rs1.getString("checkout_date");
			type= rs1.getString("type");
			status= rs1.getString("status");
			gh_id= rs1.getString("gh_id");
			block_id= rs1.getString("block_id");
			room_no= rs1.getString("room_no");
			bed_no= rs1.getString("bed_no");
			tr_id= rs1.getString("tr_id");
			res_wl= rs1.getString("res_wl");
			l_status = true; 
		}
		ps1.close();
		rs1.close();
		return l_status; 		
	}
	
	public String select_reqres() throws Exception
	{
		boolean l_status=false;
		VAL_select_reqres();
		int i =0;
		String rooms="[";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where req_id=? and res_wl_id=? and res_wl=? and status!='CAN'"); // 
		ps.setString(1, req_id);
		ps.setString(2, res_wl_id);
		ps.setString(3, res_wl);
		//System.out.println("psall    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		 if(i!=0){rooms = rooms + "&";}
		 i++;
			from_date= rs.getString("from_date");
			room_no= rs.getString("room_no");
			bed_no= rs.getString("bed_no");
			rooms = rooms+from_date.substring(0,10)+":"+room_no+":"+bed_no;
			l_status = true; 
		}
		rooms = rooms + "]";
		ps.close();
		rs.close();
		return rooms; 		
	}
	public ArrayList<String > select_reqresA() throws Exception
	{
		boolean l_status=false;
		VAL_select_reqresA();
		common ins_com = new common(NGM);
		int i =0;
		ArrayList<String > ls=  new ArrayList<String>();
		String rooms="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where req_id=? and res_wl_id=? and res_wl=? and status!='CAN' and gh_id=?"); // 
		ps.setString(1, req_id);
		ps.setString(2, res_wl_id);
		ps.setString(3, res_wl);
		ps.setString(4, gh_id);
		//System.out.println("psall    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		 i++;
			from_date= rs.getString("from_date");
			room_no= rs.getString("room_no");
			bed_no= rs.getString("bed_no");
			//rooms = from_date.substring(0,10)+"/"+room_no+"/"+bed_no;
			rooms = ins_com.display_date(from_date)+"/"+room_no+"/"+bed_no;
			ls.add(rooms);
			l_status = true; 
		}
		ps.close();
		rs.close();
		return ls; 		
	}
	public boolean Update_status_reqres() throws Exception
	{
		boolean l_status=false;
		VAL_Update_status_reqres();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_allotment SET status=?,lastmodified=now() where req_id=? and res_wl_id=? and res_wl=?"); // 
		ps.setString(1, status);
		ps.setString(2, req_id);
		ps.setString(3, res_wl_id);
		ps.setString(4, res_wl);
		System.out.println("psall    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	}
	public boolean select_ifallot() throws Exception
	{
		boolean l_status=false;
		VAL_select_ifallot();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and status='ALO'");
		ps.setString(1, room_no);
		ps.setString(2, from_date);
		//System.out.println("psall    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allot_id = rs.getString("allot_id");
			room_no= rs.getString("room_no");
			bed_no= rs.getString("bed_no");
			type= rs.getString("type");
			res_wl_id= rs.getString("res_wl_id");
		    res_wl= rs.getString("res_wl");
			l_status = true; 
		}
		ps.close();
		rs.close();
		return l_status; 		
	}
	
	public boolean isAllotedSame() throws Exception
	{
		boolean l_status=false;
		VAL_isAllotedSame();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select allot_id from tr_allotment where req_id=? and res_wl_id=? and from_date=to_date(?,'dd-MM-yyyy') and to_date=to_date(?,'dd-MM-yyyy') and checkin_date=to_date(?,'dd-MM-yyyy') and checkout_date=to_date(?,'dd-MM-yyyy') and type=? and status=? and gh_id=? and block_id=? and room_no=? and bed_no=? and res_wl=? and allot_id=? and room_rent=?");
		ps.setString(1, req_id);
		ps.setString(2, res_wl_id);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, checkin_date);
		ps.setString(6, checkout_date);
		ps.setString(7, type);
		ps.setString(8, status);
		ps.setString(9, gh_id);
		ps.setString(10, block_id);
		ps.setString(11, room_no);
		ps.setString(12, bed_no);
		ps.setString(13, res_wl);
		ps.setString(14, allot_id);
		ps.setString(15, room_rent);
		System.out.println("isAllotedSame--------------------------------------------------------------- "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allot_id = rs.getString("allot_id");
			l_status = true;
		}
		ps.close();
		rs.close();
		return l_status; 		
	}
	
	public boolean isalloted() throws Exception
	{
		boolean l_status=false;
		VAL_isalloted();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where res_wl_id=? and res_wl=? and from_date=to_date(?,'dd-MM-yyyy') and status!='CAN'"); 
		ps.setString(1, res_wl_id);
		ps.setString(2, res_wl);
		ps.setString(3, from_date);
		System.out.println("isalloted--------------------------------------------------------------- "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allot_id= rs.getString("allot_id");
			req_id= rs.getString("req_id");
			res_wl_id= rs.getString("res_wl_id");
			from_date= rs.getString("from_date");
			to_date= rs.getString("to_date");
			checkin_date= rs.getString("checkin_date");
			checkout_date= rs.getString("checkout_date");
			type= rs.getString("type");
			status= rs.getString("status");
			gh_id= rs.getString("gh_id");
			block_id= rs.getString("block_id");
			room_no= rs.getString("room_no");
			bed_no= rs.getString("bed_no");
			tr_id= rs.getString("tr_id");
			res_wl= rs.getString("res_wl");
			room_rent=rs.getString("room_rent");
			l_status = true;
		}
		ps.close();
		rs.close();
		return l_status; 		
	}
	
	
	public boolean isallotedstatus() throws Exception
	{
		boolean l_status=false;
		//VAL_isalloted();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where res_wl_id=? and res_wl=? and from_date=to_date(?,'dd-MM-yyyy') and status!='CAN'"); 
		ps.setString(1, res_wl_id);
		ps.setString(2, res_wl);
		ps.setString(3, from_date);
		System.out.println("isalloted--------------------------------------------------------------- "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allot_id= rs.getString("allot_id");
			req_id= rs.getString("req_id");
			res_wl_id= rs.getString("res_wl_id");
			from_date= rs.getString("from_date");
			to_date= rs.getString("to_date");
			checkin_date= rs.getString("checkin_date");
			checkout_date= rs.getString("checkout_date");
			type= rs.getString("type");
			status= rs.getString("status");
			gh_id= rs.getString("gh_id");
			block_id= rs.getString("block_id");
			room_no= rs.getString("room_no");
			bed_no= rs.getString("bed_no");
			tr_id= rs.getString("tr_id");
			res_wl= rs.getString("res_wl");
			l_status = true;
		}
		ps.close();
		rs.close();
		return l_status; 		
	}
	
	public boolean select_room() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy')");
		ps.setString(1, room_no);
		ps.setString(2,from_date);
		System.out.println("KKKKKKKKKKKKKK     "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			status = rs.getString("status");
			l_status = true; 
		}
		ps.close();
		rs.close();
		return l_status; 		
	}
	
	public String select_room_acco() throws Exception
	{
		String tname = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select name from (select distinct req_id from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and gh_id=? and status!='CAN') a join (select name,req_id from guest_details) b on a.req_id=b.req_id");
				//"select req_id from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and gh_id=? and status!='CAN'");
		ps.setString(1, room_no);
		ps.setString(2, from_date);
		ps.setString(3, gh_id);
		System.out.println("KKKKKKKKKKKKKK req_id : "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(tname.equals("")){
				tname = rs.getString("name");
			}else{
				tname = tname +", "+rs.getString("name");
			}
			
		}
		ps.close();
		rs.close();
		return tname; 		
	}
	public String select_room_allooccup() throws Exception
	{
		String tname = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select name from (select distinct a.req_id from (select * from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and gh_id=? and status!='CAN' and status!='COUT' ) a left join (select * from tr_checkin where status='CIN')b on a.req_id=b.req_id)c join (select name,req_id from guest_details)d on c.req_id=d.req_id");
				//"select req_id from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and gh_id=? and status!='CAN'");
		ps.setString(1, room_no);
		ps.setString(2, from_date);
		ps.setString(3, gh_id);
		System.out.println("KKKKKKKKKKKKKK req_id : "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(tname.equals("")){
				tname = rs.getString("name");
			}else{
				tname = tname +", "+rs.getString("name");
			}
			
		}
		ps.close();
		rs.close();
		return tname; 		
	}
	
	public String select_arrival_List() throws Exception
	{
		String tname = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select name from (select distinct req_id from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and gh_id=? and status='ALO')a left join (select name,req_id from guest_details)b on a.req_id=b.req_id");
				//"select req_id from tr_allotment where room_no=? and from_date=to_date(?,'dd-MM-yyyy') and gh_id=? and status!='CAN'");
		System.out.println("from_date"+from_date);
		System.out.println("KKKKKKKKKKKKKK req_id : "+ps);
		ps.setString(1, room_no);
		ps.setString(2, from_date);
		ps.setString(3, gh_id);
		
		System.out.println("KKKKKKKKKKKKKK req_id : "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(tname.equals("")){
				tname = rs.getString("name");
			}else{
				tname = tname +", "+rs.getString("name");
			}
			
		}
		ps.close();
		rs.close();
		return tname; 		
	}
	
	public boolean update_db_alot() throws Exception
	{
		boolean l_status=false;
		VAL_update_db_alot();
 		PreparedStatement ps1=NGM.tr_conn.prepareStatement("update tr_allotment set status=?,lastmodified=now() where allot_id=?");
 		ps1.setString(1, status);

 		ps1.setString(2, allot_id);
//		ps1.setString(3, block_id);
//		ps1.setString(4, type);
		System.out.println(ps1);
		l_status=NGM.ins_utility.executeDB(ps1);
		ps1.close();
		return l_status;			
	}
	
	public boolean update_db_status() throws Exception
	{
		boolean l_status=false;
		System.out.println("status "+status);
		System.out.println("allot_id "+allot_id);
		System.out.println("gh_id "+gh_id);
		VAL_update_db_status();
 		PreparedStatement ps1=NGM.tr_conn.prepareStatement("update tr_allotment set status=?,lastmodified=now() where allot_id=? and gh_id=?");
		ps1.setString(1, status);
	//	ps1.setString(2, room_rent);
		ps1.setString(2, allot_id);
		ps1.setString(3, gh_id);
//		ps1.setString(4, type);
		System.out.println("update_db_status--------------------------------------------------------------- "+ps1);
		l_status=NGM.ins_utility.executeDB(ps1);
		ps1.close();
		return l_status;			
	}
	
	public boolean update_checkout() throws Exception
	{
		boolean l_status=false;
		VAL_update_checkout();
 		PreparedStatement ps1=NGM.tr_conn.prepareStatement("update tr_allotment set status=?,checkout_date=to_date(?,'dd-MM-yyyy'),lastmodified=now() where allot_id=? and gh_id=?");
		ps1.setString(1, status);
		ps1.setString(2, checkout_date);
		ps1.setString(3, allot_id);
		ps1.setString(4, gh_id);
		System.out.println("update_db_status--------------------------------------------------------------- "+ps1);
		l_status=NGM.ins_utility.executeDB(ps1);
		ps1.close();
		return l_status;			
	}
	
	public boolean update_checkout_status() throws Exception
	{
		boolean l_status=false;
		VAL_update_checkout();
 		PreparedStatement ps1=NGM.tr_conn.prepareStatement("update tr_allotment set status=?,checkout_date=to_date(?,'dd-MM-yyyy HH24:MI'),lastmodified=now() where allot_id=? and gh_id=?");
		ps1.setString(1, status);
		ps1.setString(2, checkout_date);
		ps1.setString(3, allot_id);
		ps1.setString(4, gh_id);
		System.out.println("update_db_status--------------------------------------------------------------- "+ps1);
		l_status=NGM.ins_utility.executeDB(ps1);
		ps1.close();
		return l_status;			
	}
	
	public boolean update_db_cancel() throws Exception
	{
		boolean l_status=false;
		VAL_update_db_cancel();
 		PreparedStatement ps1=NGM.tr_conn.prepareStatement("update tr_allotment set status='CAN',lastmodified=now() where res_wl_id=?");
		ps1.setString(1, res_wl_id);
//		ps1.setString(2, from_date);
//		ps1.setString(3, block_id);
//		ps1.setString(4, type);
		System.out.println(ps1);
		l_status=NGM.ins_utility.executeDB(ps1);
		ps1.close();
		return l_status;			
	}

	
	public boolean updateDB_trid() throws Exception
	{
		System.out.println("FFFFFFFFFFFFFFFFFFFFFF");
		boolean l_status=false;
		VAL_updateDB_trid();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_allotment SET tr_id=?,lastmodified=now() WHERE allot_id=?");

		ps.setString(1, tr_id);
		ps.setString(2, allot_id);
		System.out.println("update        "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public void setpost_upload(JSONObject jsonObj)throws Exception{
		 System.out.println("jsong objeecct"+jsonObj);
		this.allot_id  =  ins_util.setNullvalue(jsonObj.get("allot_id"));
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 this.res_wl_id = ins_util.setNullvalue(jsonObj.get("res_wl_id"));
		 this.from_date = ins_util.setNullvalue(jsonObj.get("from_date"));
		 this.to_date =ins_util.setNullvalue(jsonObj.get("to_date"));
		 this.checkin_date = ins_util.setNullvalue(jsonObj.get("checkin_date"));
		 this.checkout_date = ins_util.setNullvalue(jsonObj.get("checkout_date"));
		 this.type = ins_util.setNullvalue(jsonObj.get("type"));
 		 this.status =  ins_util.setNullvalue(jsonObj.get("status"));

		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));

		 this.block_id =  ins_util.setNullvalue(jsonObj.get("block_id"));

		 this.room_no =  ins_util.setNullvalue(jsonObj.get("room_no"));

		 this.bed_no =  ins_util.setNullvalue(jsonObj.get("bed_no"));

		 this.tr_id =  ins_util.setNullvalue(jsonObj.get("tr_id"));
		 this.res_wl =  ins_util.setNullvalue(jsonObj.get("  res_wl"));

 
	 

			 
	}
	public void existdata()throws Exception{
		
		VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where allot_id=? ");
 		ps.setString(1, allot_id);
		ResultSet rs = ps.executeQuery();             
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from tr_allotment where allot_id=?");
 			ps1.setString(1, allot_id);

			ps1.execute();
			ps1.close(); 
		}
	
		ps.close();
		rs.close();
		
	}
	public boolean selectimportdata(String time)throws Exception{ 
		boolean retval = false;
		System.out.println("importdata.........................");
			JSONArray jobj = new JSONArray();
			String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
		// 
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where lastmodified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
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

	public boolean select_room_guest() throws Exception
	{
		boolean l_status=false;
		VAL_select_ifallot();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and bed_no=? and (status='ALO' or status='CIN' or status='PCIN') and from_date=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss')");
		ps.setString(1, room_no);
		ps.setString(2, bed_no);
		ps.setString(3, from_date);
		/*if(room_no.equals("212")){
			System.out.println("psall room_no 212GOPIKA   "+ps);
		}*/
		System.out.println("ATHIIIIIII   "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			req_id = rs.getString("req_id");
			gh_id = rs.getString("gh_id");
			bed_no= rs.getString("bed_no");
			type= rs.getString("type");
			res_wl_id= rs.getString("res_wl_id");
		    res_wl= rs.getString("res_wl");
			l_status = true; 
		}
		ps.close();
		rs.close();
		return l_status; 		
	}
	
	public boolean select_room_guest_details() throws Exception
	{
		boolean l_status=false;
		VAL_select_ifallot();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and bed_no=? and gh_id=? and block_id=? and (status='ALO' or status='CIN' or status='PCIN') and from_date=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss')");
		ps.setString(1, room_no);
		ps.setString(2, bed_no);
		ps.setString(3, gh_id);
		ps.setString(4, block_id);
		ps.setString(5, from_date);
		/*if(room_no.equals("212")){
			System.out.println("psall room_no 212GOPIKA   "+ps);
		}*/
		System.out.println("ATHIIIIIII   "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			req_id = rs.getString("req_id");
			gh_id = rs.getString("gh_id");
			bed_no= rs.getString("bed_no");
			type= rs.getString("type");
			res_wl_id= rs.getString("res_wl_id");
		    res_wl= rs.getString("res_wl");
			l_status = true; 
		}
		ps.close();
		rs.close();
		return l_status; 		
	}

	//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
		public void VAL_insertDB() throws Exception {
			System.out.println("room "+room_no);
			System.out.println("bed "+bed_no);
			if(!(val_res_wl(res_wl))){
				
				throw NGM.ins_utility.setException("tr_allotment", "Invalid res_wl parameter", "VAL_insertDB", NGM);
			}
			
			if(!(val_bedno(bed_no))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid bed_no parameter", "VAL_insertDB", NGM);
			}
				
				if(!(val_roomno(room_no))){
					throw NGM.ins_utility.setException("tr_allotment", "Invalid room_no parameter", "VAL_insertDB", NGM);
				}
					if(!(val_blockid(block_id))){
						throw NGM.ins_utility.setException("tr_allotment", "Invalid block_id parameter", "VAL_insertDB", NGM);
					}
						if(!(val_ghid(gh_id))){
							throw NGM.ins_utility.setException("tr_allotment", "Invalid gh_id parameter", "VAL_insertDB", NGM);
						}
							
							if(!(val_status(status))){
								throw NGM.ins_utility.setException("tr_allotment", "Invalid status parameter", "VAL_insertDB", NGM);
							}
								if(!(val_type(type))){
									throw NGM.ins_utility.setException("tr_allotment", "Invalid type parameter", "VAL_insertDB", NGM);
								}
									if(!(val_seq_id(res_wl_id))){
										throw NGM.ins_utility.setException("tr_allotment", "Invalid res_wl_id parameter", "VAL_insertDB", NGM);
									}
										if(!(val_allotid(allot_id))){
											throw NGM.ins_utility.setException("tr_allotment", "Invalid allot_id parameter", "VAL_insertDB", NGM);
										}
											if(!(val_reqid(req_id))){//&&val_todate(checkout_date)&&val_fromdate(checkin_date)&&cDateComp(from_date,to_date,"<=")&&val_todate(to_date)&&val_fromdate(from_date)&&val_tr_id(tr_id)
												throw NGM.ins_utility.setException("tr_allotment", "Invalid req_id parameter", "VAL_insertDB", NGM);
											}
			
			//val_allotid(allot_id);
			//val_reqid(req_id);
			//val_seq_id(res_wl_id);
			//val_fromdate(from_date);
			//val_todate(to_date);
			//cDateComp(from_date,to_date,"<=");
			//val_fromdate(checkin_date);
			//val_todate(checkout_date);
			//val_type(type);
			//val_status(status);
			//val_ghid(gh_id);
			//val_blockid(block_id);
			//val_roomno(room_no);
			//val_bedno(bed_no);
			//val_tr_id(tr_id);
			//val_res_wl(res_wl);
			
		}
		
		public void VAL_updateDB() throws Exception {
			if(!(val_res_wl(res_wl)&&val_tr_id(tr_id)&&val_bedno(bed_no)&&val_roomno(room_no)&&val_blockid(block_id)&&val_ghid(gh_id)&&val_status(status)&&val_type(type)&&val_seq_id(res_wl_id)&&val_allotid(allot_id)&&val_reqid(req_id))){//&&val_todate(checkout_date)&&val_fromdate(checkin_date)&&val_todate(to_date)&&val_fromdate(from_date)
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_updateDB", NGM);
			}
		/*	val_allotid(allot_id);
			val_reqid(req_id);
			val_seq_id(res_wl_id);
			val_fromdate(from_date);
			val_todate(to_date);
			val_fromdate(checkin_date);
			val_todate(checkout_date);
			val_type(type);
			val_status(status);
			val_ghid(gh_id);
			val_blockid(block_id);
			val_roomno(room_no);
			val_bedno(bed_no);
			val_tr_id(tr_id);
			val_res_wl(res_wl);*/
			
		}
		
		public void VAL_deleteDB() throws Exception {
			if(!(val_allotid(allot_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_deleteDB", NGM);
			}
			//val_allotid(allot_id);
		}
		
		public void VAL_select_exist() throws Exception {
			if(!(val_type(type)&&val_blockid(block_id)&&val_roomno(room_no))){//&&val_fromdate(from_date)
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_select_exist", NGM);
			}
			//val_fromdate(from_date);
			//val_type(type);
			//val_blockid(block_id);
			//val_roomno(room_no);
		}
		
		public void VAL_select_DB() throws Exception {
			System.out.println("allot_id "+allot_id);
			System.out.println("gh_id "+gh_id);
			if(!(val_allotid(allot_id))){//&&val_ghid(gh_id)
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_select_DB", NGM);
			}
			//val_allotid(allot_id);
			//val_ghid(gh_id);
		}
		public void VAL_select_reqres() throws Exception {
			if(!(val_reqid(req_id)&&val_seq_id(res_wl_id)&&val_res_wl(res_wl))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_select_reqres", NGM);
			}
			//val_reqid(req_id);
			//val_seq_id(res_wl_id);
			//val_res_wl(res_wl);
		}
		public void VAL_select_reqresA() throws Exception {
			if(!(val_reqid(req_id)&&val_seq_id(res_wl_id)&&val_res_wl(res_wl)&&val_ghid(gh_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_select_reqresA", NGM);
			}
			/*val_reqid(req_id);
			val_seq_id(res_wl_id);
			val_res_wl(res_wl);
			val_ghid(gh_id);*/
		}
		public void VAL_Update_status_reqres() throws Exception {
			if(!(val_reqid(req_id)&&val_seq_id(res_wl_id)&&val_res_wl(res_wl)&&val_status(status))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_Update_status_reqres", NGM);
			}
			/*val_reqid(req_id);
			val_seq_id(res_wl_id);
			val_res_wl(res_wl);
			val_status(status);*/
		}
		public void VAL_select_ifallot() throws Exception {
			if(!(val_roomno(room_no))){//&&val_fromdate(from_date)
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_select_ifallot", NGM);
			}
			//val_roomno(room_no);
			//val_fromdate(from_date);
		}
		
		public void VAL_isAllotedSame() throws Exception {
			if(!(val_res_wl(res_wl)&&val_bedno(bed_no)&&val_roomno(room_no)&&val_blockid(block_id)&&val_ghid(gh_id)&&val_status(status)&&val_type(type)&&val_seq_id(res_wl_id)&&val_allotid(allot_id)&&val_reqid(req_id))){//val_todate(checkout_date)&&val_fromdate(checkin_date)&&val_todate(to_date)&&val_fromdate(from_date)&&
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_isAllotedSame", NGM);
			}
			
			/*val_allotid(allot_id);
			val_reqid(req_id);
			val_seq_id(res_wl_id);
			val_fromdate(from_date);
			val_todate(to_date);
			val_fromdate(checkin_date);
			val_todate(checkout_date);
			val_type(type);
			val_status(status);
			val_ghid(gh_id);
			val_blockid(block_id);
			val_roomno(room_no);
			val_bedno(bed_no);
			val_res_wl(res_wl);*/
		}
		
		public void VAL_isalloted() throws Exception {
			if(!(val_seq_id(res_wl_id)&&val_res_wl(res_wl))){//&&val_date(from_date)
				System.out.println("res_wl_id "+res_wl_id);
				System.out.println("res_wl "+res_wl);
				System.out.println("from_date "+from_date);
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_isalloted", NGM);
			}
			
			/*val_seq_id(res_wl_id);
			val_fromdate(from_date);
			val_res_wl(res_wl);*/
		}
		
		public void VAL_update_db_alot() throws Exception {
			if(!(val_status(status)&&val_allotid(allot_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_update_db_alot", NGM);
			}
			//val_status(status);
			//val_allotid(allot_id);
		}
		
		public void VAL_update_db_status() throws Exception {
			if(!(val_status(status)&&val_allotid(allot_id)&&val_ghid(gh_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_update_db_status", NGM);
			}
			/*val_status(status);
			val_allotid(allot_id);
			val_ghid(gh_id);*/
		}
		
		public void VAL_update_checkout() throws Exception {
			if(!(val_status(status)&&val_allotid(allot_id)&&val_ghid(gh_id))){//&&val_todate(checkout_date)
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_update_checkout", NGM);
			}
		}
		
		public void VAL_update_db_cancel() throws Exception {
			if(!(val_seq_id(res_wl_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_update_db_cancel", NGM);
			}
			//val_seq_id(res_wl_id);
		}
		
		public void VAL_updateDB_trid() throws Exception {
			if(!(val_tr_id(tr_id)&&val_allotid(allot_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_updateDB_trid", NGM);
			}
			//val_allotid(allot_id);
			//val_tr_id(tr_id);
		}
		
		public void VAL_existdata() throws Exception {
			if(!(val_allotid(allot_id))){
				throw NGM.ins_utility.setException("tr_allotment", "Invalid tr_allotment parameter", "VAL_existdata", NGM);
			}
			//val_allotid(allot_id);
		}
		


		public boolean val_tr_id(String tr_id){
			if(tr_id!=null){
				if(!cLength(tr_id.toString(), 1, 25)){
					return false;
				}
				if (!cNumeric(tr_id)){
					return false;
				}
				return true;
			}
				return true;
	
}
		
		public boolean val_allotid(String allot_id){
			if(allot_id!=null){
				if(!cLength(allot_id.toString(), 1, 25)){
						return false;
					}
					if (!cNumeric(allot_id)){
							return false;
					}
					return true;
			}else
				return false;
			
		}
		
		public boolean val_type(String type){
			if(type!=null){
				if(!cLength(type.toString(), 1, 5)){
						return false;
					}
					if (!cAlphabet(type)){
							return false;
					}
					return true;
			}else
				return false;
			
		}
		
		public boolean val_res_wl(String res_wl){
			if(res_wl!=null){
				if(!cLength(res_wl.toString(), 1, 2)){
						return false;
					}
					if (!cAlphabet(res_wl)){
							return false;
					}
					return true;
			}else
				return false;
			
		}
		
		public boolean val_date(String tfrom_date){
			if(tfrom_date!=null){
				if(!cDateTime(tfrom_date.toString(),"dmy")){
						return false;
					}
					return true;
			}else
				return false;
			
		}
}