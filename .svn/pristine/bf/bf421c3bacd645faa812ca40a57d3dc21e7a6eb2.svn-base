package db; 

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_gadrequest extends db_validation
{
	public String address1,guest_id,purpose,category,desg,nor_req,nob_g_req,nob_l_req,nod_g_req,nod_l_req,total_no_adults,total_no_childs,from_date,to_date,status,is_veh_req,is_veh_req1,mode_of_req,gh_id,block_id,lastupdate,ext,roomtype,family,mobile_no,email_id,duringstay;
	public String req_id,user_id,req_date,existing_req_id;
	ngm NGM;
	utility 		ins_util;
	public JSONArray array1 = new JSONArray();
	public d_gadrequest(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB_gadreq() throws Exception
	{		
		boolean l_status=false;	
		
		VAL_insertDB_gadreq();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gad_request values(?,?,now(),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, nor_req);
		ps.setString(6, nob_g_req);
		ps.setString(7, nob_l_req);
		ps.setString(8, "REQ");
		ps.setString(9, is_veh_req);
		ps.setString(10, "GAD");
		ps.setString(11, purpose);
		ps.setString(12, ext);
		ps.setString(13, address1);
		ps.setString(14, NGM.ins_sess.user_id);
		ps.setString(15, nod_g_req);
		ps.setString(16, nod_l_req);
		ps.setString(17, roomtype);
		ps.setString(18, family);
		ps.setString(19, mobile_no);
		ps.setString(20,email_id);
		ps.setString(21,existing_req_id);
		ps.setString(22,is_veh_req1);
		ps.setString(23,block_id);
		ps.setString(24,null);
		ps.setString(25,category);
		ps.setString(26, desg);
		ps.setString(27, null);	
		ps.setString(28, duringstay);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_gh_req() throws Exception
	{		
		boolean l_status=false;	
		
		VAL_insertDB_gh_req();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gad_request values(?,?,now(),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, nor_req);
		ps.setString(6, nob_g_req);
		ps.setString(7, nob_l_req);
		ps.setString(8, "REQ");
		ps.setString(9, is_veh_req);
		ps.setString(10, "GH");
		ps.setString(11, purpose);
		ps.setString(12, ext);
		ps.setString(13, address1);
		ps.setString(14, NGM.ins_sess.user_id);
		ps.setString(15, nod_g_req);
		ps.setString(16, nod_l_req);
		ps.setString(17, roomtype);
		ps.setString(18, family);
		ps.setString(19, mobile_no);
		ps.setString(20,email_id);
		ps.setString(21,existing_req_id);
		ps.setString(22,is_veh_req1);
		ps.setString(23,block_id);
		ps.setString(24,null);
		ps.setString(25,category);
		ps.setString(26, desg);
		ps.setString(27, null);	
		ps.setString(28, duringstay);
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	} 
	
	public boolean updateDB_gadreq() throws Exception
	{		
		boolean l_status=false;	
		
		VAL_updateDB_gadreq();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update gad_request set gh_id=?,lastupdated =now(),from_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),nor_req=?,nob_g_req=?,nob_l_req=?,is_veh_req=?,purpose=?,ext=?,address=?,nod_g_req=?,nod_l_req=?,family=?,mobile_no=?,email_id=?,existing_req_id=?,is_veh_req1=?,block_id=?,category_type=?,desg=?,during_stay_conveancy=? where req_id=?");
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
		ps.setString(11,nod_g_req);
		ps.setString(12,nod_l_req);
		ps.setString(13,family);
		ps.setString(14, mobile_no);
		ps.setString(15,email_id);
		ps.setString(16,existing_req_id);
		ps.setString(17,is_veh_req1);
		ps.setString(18,block_id);
		ps.setString(19, category);
		ps.setString(20, desg);
		ps.setString(21, duringstay);
//		ps.setString(22, roomtype);
		ps.setString(22, req_id);
		System.out.println("quert    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		
		ps.close();
		return l_status;		
	} 
	public boolean update_status() throws Exception {		
		boolean l_status=false;			 
		VAL_update_status();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status=?,lastupdated =now() where req_id=?");
	    pss.setString(1, status);
	    pss.setString(2, req_id);
	    System.out.println("processing...........status"+pss  );
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	}
	
	public boolean update_block(String block_id) throws Exception {		
		boolean l_status=false;			 
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set block_id=?,lastupdated =now() where req_id=?");
	    pss.setString(1, block_id);
	    pss.setString(2, req_id);
	    System.out.println("processing...........status"+pss  );
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean selectDB_gad_date() throws Exception {		
		boolean l_status=false;	
		VAL_selectDB_gad_date();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request where req_id=? and gh_id=?");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		System.out.println("ps... "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			from_date=rs.getString("from_date");
			to_date=rs.getString("to_date");
			is_veh_req = rs.getString("is_veh_req");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	
	public String selectDB_gad_date_conveyance() throws Exception {		
		boolean l_status=false;	
		String tnumber="",tplace="",tdate="",mode="";
		String conveyance = "No conveyance requested.";
		VAL_selectDB_gad_date();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request a left join vehicle_req b on a.req_id=b.req_id where a.req_id=? and gh_id=?");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		System.out.println("ps... "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			from_date=rs.getString("from_date");
			to_date=rs.getString("to_date");
			is_veh_req = rs.getString("is_veh_req");
			if(is_veh_req.equals("1")){
				tnumber = rs.getString("ftno");
				tplace = rs.getString("arrival_place");
				tdate = rs.getString("arrival_time");
				mode = rs.getString("mode_of_travel");
				System.out.println("mode "+mode);
				if(mode == null){
					mode = " ";
				}
				if(mode.equalsIgnoreCase("f")){
					conveyance = "Flight : "+tnumber+" at "+tdate+" in "+tplace;
				}else if(mode.equalsIgnoreCase("t")){
					conveyance = "Train : "+tnumber+" at "+tdate+" in "+tplace;
				}
			}
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return conveyance;
	} 	

	
	public String selectDB_gad_date_conveyance1() throws Exception {		
		boolean l_status=false;	
		String tnumber="",tplace="",tdate="",mode="";
		String conveyance = "No conveyance requested.";
		VAL_selectDB_gad_date();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request a left join vehicle_req1 b on a.req_id=b.req_id where a.req_id=? and gh_id=?");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		System.out.println("ps... "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			from_date=rs.getString("from_date");
			to_date=rs.getString("to_date");
			is_veh_req1 = rs.getString("is_veh_req1");
			if(is_veh_req1.equals("1")){
				tnumber = rs.getString("ftno");
				tplace = rs.getString("arrival_place");
				tdate = rs.getString("arrival_time");
				mode = rs.getString("mode_of_travel");
				if(mode == null){
					mode = " ";
				}
				System.out.println("mode "+mode);
				if(mode == null ? false : mode.equalsIgnoreCase("f")){
					conveyance = "Flight : "+tnumber+" at "+tdate+" in "+tplace;
				}else if(mode == null ? false : mode.equalsIgnoreCase("t")){
					conveyance = "Train : "+tnumber+" at "+tdate+" in "+tplace;
				}
			}
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return conveyance;
	} 	
	
	public boolean update_conveyance() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set is_veh_req=?,lastupdated=now() where gh_id=? and req_id=?");
	    pss.setString(1, is_veh_req);
	    pss.setString(2, gh_id);
		pss.setString(3, req_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean update_conveyance1() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set is_veh_req1=?,lastupdated=now() where gh_id=? and req_id=?");
	    pss.setString(1, is_veh_req1);
	    pss.setString(2, gh_id);
		pss.setString(3, req_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	}
	public boolean update_duringstay() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set during_stay_conveancy=?,lastupdated=now() where gh_id=? and req_id=?");
	    pss.setString(1, duringstay);
	    pss.setString(2, gh_id);
		pss.setString(3, req_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelgad() throws Exception {		
		boolean l_status=false;	
		VAL_cancelgad();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='CAN',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelgadreservation() throws Exception {		
		boolean l_status=false;	
		VAL_cancelgad();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='RCAN',lastupdated=now(),req_date=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelcancel() throws Exception {		
		boolean l_status=false;	
		VAL_cancelcancel();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='REQ',lastupdated=now() where req_id=?");
		pss.setString(1, req_id);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean deletegad() throws Exception {		
		boolean l_status=false;	
		VAL_deletegad();
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from gad_request where req_id=?");
		pss.setString(1, req_id);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelalo() throws Exception {		
		boolean l_status=false;	
		VAL_cancelalo();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='PCIN',lastupdated=now() where req_id=?");
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelal() throws Exception {		
		boolean l_status=false;	
		VAL_cancelal();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='PCOUT',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelaloo() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='CIN',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelalooo() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='ALO',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelcin() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='ALO',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean selectDB_gad() throws Exception {		
		boolean l_status=false;	
	    VAL_selectDB_gad();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			gh_id=rs.getString("gh_id");
			block_id=rs.getString("block_id");
			from_date=rs.getString("from_date");
			to_date=rs.getString("to_date");
			nor_req=rs.getString("nor_req");
			nob_g_req=rs.getString("nob_g_req");
			nob_l_req=rs.getString("nob_l_req");
			status=rs.getString("status");
			is_veh_req=rs.getString("is_veh_req");
			is_veh_req1=rs.getString("is_veh_req1");
			purpose=rs.getString("purpose");
			ext=rs.getString("ext");
			address1=rs.getString("address");
			nod_g_req=rs.getString("nod_g_req");
			nod_l_req=rs.getString("nod_l_req");
			roomtype=rs.getString("roomtype");
			family=rs.getString("family");
			mobile_no=rs.getString("mobile_no");
			email_id=rs.getString("email_id");
			existing_req_id=rs.getString("existing_req_id");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	

	public String selectDBcount() throws Exception {		
		boolean l_status=false;	
		String count="";
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select count(*) from gad_request where status=? and date(from_date)>=date(now())");
		ps.setString(1, status);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			count=rs.getString("count");
			l_status=true;
		}
		rs.close();
		ps.close();
		return count;
	} 	


	
	public boolean updated_impdata()throws Exception{
	boolean retval = false;
	
PreparedStatement ps = NGM.tr_conn.prepareStatement("update pub_request set status ='RED'");
	retval=NGM.ins_utility.executeDB(ps);
	ps.close();
	return retval;
}
	
public boolean cancelcinn() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='COUT',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel$#@    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 

public boolean insertDB_gadreq_upload() throws Exception
{		
	boolean l_status=false;	
	VAL_insertDB_gadreq_upload();
	PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gad_request values(?,?,now(),to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,?)");
	ps.setString(1, req_id);
	ps.setString(2, gh_id);
	ps.setString(3, from_date);
	ps.setString(4, to_date);
	ps.setString(5, nor_req);
	ps.setString(6, nob_g_req);
	ps.setString(7, nob_l_req);
	ps.setString(8, status);
	ps.setString(9, is_veh_req);
	ps.setString(10, mode_of_req);
	ps.setString(11, purpose);
	ps.setString(12, ext);
	ps.setString(13, address1);
	ps.setString(14, user_id);
	ps.setString(15, req_date);
	ps.setString(16, nod_g_req);
	ps.setString(17, nod_l_req);
	ps.setString(18, roomtype);
	ps.setString(19, family);
	ps.setString(20, mobile_no);
	ps.setString(21,email_id);
	ps.setString(22,existing_req_id);
	ps.setString(23,is_veh_req1);
	ps.setString(24,block_id);
	System.out.println("ssdf"+ps);
	l_status=NGM.ins_utility.executeDB(ps);
	ps.close();
	return l_status;		
} 

public void setpost_mod2(JSONObject jsonObj)throws Exception{
	 System.out.println("jsong objeecct"+jsonObj);
	 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
	 this.gh_id = ins_util.setNullvalue(jsonObj.get("gh_id"));
	 this.from_date = ins_util.setNullvalue(jsonObj.get("from_date"));
	 this.to_date =ins_util.setNullvalue(jsonObj.get("to_date"));
	 this.nor_req = ins_util.setNullvalue(jsonObj.get("nor_req"));
	 this.nob_g_req = ins_util.setNullvalue(jsonObj.get("nob_g_req"));
	 this.nob_l_req = ins_util.setNullvalue(jsonObj.get("nob_l_req"));
	 this.status = ins_util.setNullvalue(jsonObj.get("status"));
	 this.is_veh_req =  ins_util.setNullvalue(jsonObj.get("is_veh_req"));

	 this.mode_of_req =  ins_util.setNullvalue(jsonObj.get("mode_of_req"));

	 this.purpose =  ins_util.setNullvalue(jsonObj.get("purpose"));

	 this.ext =  ins_util.setNullvalue(jsonObj.get("ext"));

	 this.nod_g_req = ins_util.setNullvalue(jsonObj.get("nod_g_req"));
	 this.nod_l_req = ins_util.setNullvalue(jsonObj.get("nod_l_req"));
	 this.address1 =  ins_util.setNullvalue(jsonObj.get("address"));
	 this.req_date = ins_util.setNullvalue(jsonObj.get("req_date"));
	 this.user_id =  ins_util.setNullvalue(jsonObj.get("userid"));
	 this.roomtype = ins_util.setNullvalue(jsonObj.get("roomtype"));
	 this.mobile_no = ins_util.setNullvalue(jsonObj.get("mobile_no"));
	 this.email_id = ins_util.setNullvalue(jsonObj.get("email_id"));
	 this.family = ins_util.setNullvalue(jsonObj.get("family"));


	 

		 
}

public void setpost_mod(JSONObject jsonObj,String req_id)throws Exception{
	 System.out.println("jsong objeecct"+jsonObj);
	 this.req_id =  req_id;
	 this.gh_id = ins_util.setNullvalue(jsonObj.get("gh_id"));
	 this.from_date = ins_util.setNullvalue(jsonObj.get("from_date"));
	 this.to_date =ins_util.setNullvalue(jsonObj.get("to_date"));
	 this.nor_req = ins_util.setNullvalue(jsonObj.get("nor_req"));
	 this.nob_g_req = ins_util.setNullvalue(jsonObj.get("nob_g_req"));
	 this.nob_l_req = ins_util.setNullvalue(jsonObj.get("nob_l_req"));
	 this.status = ins_util.setNullvalue(jsonObj.get("status"));
	 this.is_veh_req =  ins_util.setNullvalue(jsonObj.get("is_veh_req"));

	 this.mode_of_req =  ins_util.setNullvalue(jsonObj.get("mode_of_req"));

	 this.purpose =  ins_util.setNullvalue(jsonObj.get("purpose"));

	 this.ext =  ins_util.setNullvalue(jsonObj.get("ext"));

	 this.address1 =  ins_util.setNullvalue(jsonObj.get("address"));
	 this.nod_g_req = ins_util.setNullvalue(jsonObj.get("nod_g_req"));
	 this.nod_l_req = ins_util.setNullvalue(jsonObj.get("nod_l_req"));
	 this.user_id =  ins_util.setNullvalue(jsonObj.get("userid"));
	 this.mobile_no = ins_util.setNullvalue(jsonObj.get("mobile_no"));
	 this.email_id = ins_util.setNullvalue(jsonObj.get("email_id"));
	 this.family = ins_util.setNullvalue(jsonObj.get("family"));
	 this.req_date =  ins_util.setNullvalue(jsonObj.get("req_date"));
	 this.existing_req_id =  ins_util.setNullvalue(jsonObj.get("existing_req_id"));
	 this.is_veh_req1 =  ins_util.setNullvalue(jsonObj.get("is_veh_req1"));
	 this.block_id =  ins_util.setNullvalue(jsonObj.get("block_id"));
		 
}
public void existdata()throws Exception{
	
	 
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request where req_id=?");
	ps.setString(1, req_id);
	System.out.println("vehicle"+ps);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from gad_request where req_id=?");
		ps1.setString(1, req_id);
		ps1.execute();
		ps1.close();
		 
	}
	rs.close();
	ps.close();	 
 
	
}
public boolean existdata_accept()throws Exception{
	
	 
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request where req_id=?");
	ps.setString(1, req_id);
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
System.out.println("importdata.........................");
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
// 
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request where lastupdated > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
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
	 
	
public String selectDB_gad_Purpose(String id) throws Exception {		
	String l_status=null;	
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_purpose_items where item_id=?");
	ps.setString(1, id);
	System.out.println("ps... "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		l_status=rs.getString("item_title");
	}
	rs.close();
	ps.close();
	return l_status;
} 
public String selectDB_gad_designation(String id) throws Exception {		
	String l_status=null;	
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from visitor_category where vc_id=?");
	ps.setString(1, id);
	System.out.println("ps... "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		l_status=rs.getString("desg_category");
	}
	rs.close();
	ps.close();
	return l_status;
} 

public String selectDB_gad_Room(String id,String gh_id, String block_id) throws Exception {		
	String l_status="";	
	System.out.println("REQID... "+ id + " GHID " + gh_id + " BLOCKID " + block_id);
	Set<String> set = new HashSet<String>();
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select req_id,room_no,bed_no from tr_allotment where gh_id=? and block_id=? and req_id = ? and status != 'CAN' order by room_no asc");
	ps.setString(1, gh_id);
	ps.setString(2, block_id);
	ps.setString(3, id);
	System.out.println("ps... "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		set.add(rs.getString("room_no"));
		
	}
	rs.close();
	ps.close();
	Integer count = 1;
	System.out.println(set);
	for(String item : set) {
		l_status = l_status + item;
		System.out.println(l_status);
		if(set.size() > count) {
			l_status = l_status + ",";
			System.out.println(l_status);	
		}
		count = count + 1;
	}
	System.out.println(l_status);
	return l_status;
} 
public String selectDB_gad_Room1(String id,String gh_id, String block_id) throws Exception {		
	String l_status="";	
	String m_status="";
	System.out.println("REQID... "+ id + " GHID " + gh_id + " BLOCKID " + block_id);
	Set<String> set = new HashSet<String>();
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select req_id,room_no,bed_no from tr_allotment where gh_id=? and block_id=? and req_id = ? and status != 'CAN' order by room_no asc");
	ps.setString(1, gh_id);
	ps.setString(2, block_id);
	ps.setString(3, id);
	System.out.println("ps... "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		set.add(rs.getString("room_no"));
		
	}
	
	Integer count = 1;
	System.out.println(set);
	for(String item : set) {
		System.out.println("REQID... "+ id + " GHID " + gh_id + " BLOCKID " + block_id);
		Set<String> setm = new HashSet<String>();
	    PreparedStatement psm=NGM.tr_conn.prepareStatement("select bed_no from tr_allotment where gh_id=? and block_id=? and req_id = ? and status != 'CAN' and room_no= ? order by bed_no ASC");
		psm.setString(1, gh_id);
		psm.setString(2, block_id);
		psm.setString(3, id);
		psm.setString(4, item);
		System.out.println("ps... "+psm);
		ResultSet rsm=psm.executeQuery();
		while(rsm.next()){
			setm.add(rsm.getString("bed_no"));
		}
		Integer count1=0;
		System.out.println("DDDDDDDDDDDDDDDDD"+setm);
		for(String item1 : setm) {
			m_status = m_status+item+"-"+item1;
			System.out.println("BBBBBBBBBBBBB"+m_status);
			
			if(setm.size() > count1) {
				m_status = m_status + ",";
				System.out.println("AAAAAAAAAAAA"+m_status);	
			}
			count1 = count1 + 1;
			rsm.close();
			psm.close();
		}
		
		System.out.println("Deepak"+setm);	
	}
	count = count + 1;
	rs.close();
	ps.close();
	if(m_status.endsWith(","))
	{
		m_status=m_status.substring(0, m_status.length()-1);
	}
	System.out.println(m_status);
	return m_status;
} 

public String selectDB_gad_RoomRent(String id,String gh_id, String block_id) throws Exception {		
	String m_status="";	
	System.out.println("REQID... "+ id + " GHID " + gh_id + " BLOCKID " + block_id);
	Set<String> set = new HashSet<String>();
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select req_id,room_no from tr_allotment where gh_id=? and block_id=? and req_id = ? and status != 'CAN' order by room_no asc");
	ps.setString(1, gh_id);
	ps.setString(2, block_id);
	ps.setString(3, id);
	System.out.println("ps... "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		set.add(rs.getString("room_no"));
		
	}
	Integer count = 1;
	System.out.println("CCCCCCCCCCCCCCC"+set);
	for(String item : set) {
		System.out.println("REQID... "+ id + " GHID " + gh_id + " BLOCKID " + block_id);
		Set<String> setm = new HashSet<String>();
	    PreparedStatement psm=NGM.tr_conn.prepareStatement("select room_rent from tr_allotment where gh_id=? and block_id=? and req_id = ? and status != 'CAN' and room_no= ? order by cast(allot_id as int) asc");
		psm.setString(1, gh_id);
		psm.setString(2, block_id);
		psm.setString(3, id);
		psm.setString(4, item);
		System.out.println("ps... "+psm);
		ResultSet rsm=psm.executeQuery();
		while(rsm.next()){
			setm.add(rsm.getString("room_rent"));
		}
		Integer count1=0;
		System.out.println("DDDDDDDDDDDDDDDDD"+setm);
		for(String item1 : setm) {
			m_status = m_status+item1;
			System.out.println("BBBBBBBBBBBBB"+m_status);
			
			if(setm.size() > count1) {
				m_status = m_status + ",";
				System.out.println("AAAAAAAAAAAA"+m_status);	
			}
			count1 = count1 + 1;
			rsm.close();
			psm.close();
		}
		
		System.out.println("Deepak"+setm);	
		}
		count = count + 1;
	rs.close();
	ps.close();
	if(m_status.endsWith(","))
	{
		m_status=m_status.substring(0, m_status.length()-1);
	}
	System.out.println("Dillip"+m_status);
	
	return m_status;
} 



//-------------------------------------validation------------------------------------------------------------------------	
public void VAL_insertDB_gadreq() throws Exception {
	
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_insertDB_gadreq", NGM);
	}
	
	 if(!(val_ghid(gh_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid gh_id", "VAL_insertDB_gadreq", NGM);
		}
	 String[] t_from = from_date.split(" ");
	 if(!(val_date_dmy(t_from[0]))){//
		 System.out.println("from_date fdgdfgdfg"+t_from[0]);
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid from_date", "VAL_insertDB_gadreq", NGM);
		}
	 String[] t_to = to_date.split(" ");
	 if(!(val_date_dmy(t_to[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid to_date", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nor_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nor_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nob_g_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_g_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nob_l_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_l_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_one_zero(is_veh_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid is_veh_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_one_zero(ext))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid ext", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_one_zero(family))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid family", "VAL_insertDB_gadreq", NGM);
		}
	 /*if(!(val_purpose(purpose))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid purpose", "VAL_insertDB_gadreq", NGM);
		}*/
//	 if(!(val_address(address1))){//
//			throw NGM.ins_utility.setException("d_gadrequest", "Invalid address", "VAL_insertDB_gadreq", NGM);
//		}
	 if(!(val_roomno(nod_g_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_g_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nod_l_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_l_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_mobile(mobile_no))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid mobile_no", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_email(email_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid email_id", "VAL_insertDB_gadreq", NGM);
		}
}
public void VAL_insertDB_gh_req() throws Exception {
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_insertDB_gh_req", NGM);
	}
	
	 if(!(val_ghid(gh_id))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid gh_id", "VAL_insertDB_gh_req", NGM);
		}
	 String[] t_from = from_date.split(" ");
	 if(!(val_date_dmy(t_from[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid from_date", "VAL_insertDB_gh_req", NGM);
		}
	 String[] t_to = to_date.split(" ");
	 if(!(val_date_dmy(t_to[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid to_date", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_roomno(nor_req))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nor_req", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_roomno(nob_g_req))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_g_req", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_roomno(nob_l_req))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_l_req", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_one_zero(is_veh_req))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid is_veh_req", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_one_zero(ext))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid ext", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_one_zero(family))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid family", "VAL_insertDB_gh_req", NGM);
		}
	 /*if(!(val_purpose(purpose))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid purpose", "VAL_insertDB_gh_req", NGM);
		}*/
//	 if(!(val_address(address1))){
//			throw NGM.ins_utility.setException("d_gadrequest", "Invalid address", "VAL_insertDB_gh_req", NGM);
//		}
	 if(!(val_roomno(nod_g_req))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_g_req", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_roomno(nod_l_req))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_l_req", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_mobile(mobile_no))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid mobile_no", "VAL_insertDB_gh_req", NGM);
		}
	 if(!(val_email(email_id))){
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid email_id", "VAL_insertDB_gh_req", NGM);
		}
}

public void VAL_updateDB_gadreq() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_updateDB_gadreq", NGM);
	}
	 if(!(val_ghid(gh_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid gh_id", "VAL_updateDB_gadreq", NGM);
		}
	 String[] t_from = from_date.split(" ");
	 if(!(val_date_dmy(t_from[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid from_date", "VAL_updateDB_gadreq", NGM);
		}
	 String[] t_to = to_date.split(" ");
	 if(!(val_date_dmy(t_to[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid to_date", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_roomno(nor_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nor_req", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_roomno(nob_g_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_g_req", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_roomno(nob_l_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_l_req", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_one_zero(is_veh_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid is_veh_req", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_one_zero(ext))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid ext", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_one_zero(family))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid family", "VAL_updateDB_gadreq", NGM);
		}
	/* if(!(val_purpose(purpose))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid purpose", "VAL_updateDB_gadreq", NGM);
		}*/
//	 if(!(val_address(address1))){//
//			throw NGM.ins_utility.setException("d_gadrequest", "Invalid address", "VAL_updateDB_gadreq", NGM);
//		}
	 if(!(val_roomno(nod_g_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_g_req", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_roomno(nod_l_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_l_req", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_mobile(mobile_no))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid mobile_no", "VAL_updateDB_gadreq", NGM);
		}
	 if(!(val_email(email_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid email_id", "VAL_updateDB_gadreq", NGM);
		}
}

public void VAL_update_status() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_update_status", NGM);
	}
	 if(!(val_status(status))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid status", "VAL_update_status", NGM);
		}
}
public void VAL_selectDB_gad_date() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_selectDB_gad_date", NGM);
	}
	 if(!(val_ghid(gh_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid gh_id", "VAL_selectDB_gad_date", NGM);
		}
}
public void VAL_cancelgad() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_cancelgad", NGM);
	}
}
public void VAL_cancelcancel() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_cancelcancel", NGM);
	}
}
public void VAL_deletegad() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_deletegad", NGM);
	}
}
public void VAL_cancelalo() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_cancelalo", NGM);
	}
}
public void VAL_cancelal() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_cancelal", NGM);
	}
}
public void VAL_selectDB_gad() throws Exception {
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_selectDB_gad", NGM);
	}
}

public void VAL_insertDB_gadreq_upload() throws Exception {
	
	
	if(!(val_reqid(req_id))){//
		throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_id", "VAL_insertDB_gadreq", NGM);
	}
	
	 if(!(val_ghid(gh_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid gh_id", "VAL_insertDB_gadreq", NGM);
		}
	 String[] t_from = from_date.split(" ");
	 if(!(val_date_dmy(t_from[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid from_date", "VAL_insertDB_gadreq", NGM);
		}
	 String[] t_to = to_date.split(" ");
	 if(!(val_date_dmy(t_to[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid to_date", "VAL_insertDB_gadreq", NGM);
		}
	 String[] t_req = req_date.split(" ");
	 if(!(val_date_dmy(t_req[0]))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid req_date", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nor_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nor_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nob_g_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_g_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nob_l_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nob_l_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_one_zero(is_veh_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid is_veh_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_one_zero(ext))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid ext", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_one_zero(family))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid family", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_purpose(purpose))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid purpose", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_address(address1))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid address", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nod_g_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_g_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_roomno(nod_l_req))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid nod_l_req", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_mobile(mobile_no))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid mobile_no", "VAL_insertDB_gadreq", NGM);
		}
	 if(!(val_email(email_id))){//
			throw NGM.ins_utility.setException("d_gadrequest", "Invalid email_id", "VAL_insertDB_gadreq", NGM);
		}
}

}