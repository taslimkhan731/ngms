package db; 

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_pubrequest extends db_validation
{
	public String address1,guest_id,purpose,category,nor_req,nob_g_req,nob_l_req,nod_g_req,nod_l_req,total_no_adults,total_no_childs,from_date,to_date,status,is_veh_req,is_veh_req1,mode_of_req,gh_id,block_id,lastupdate,ext,mobile_no,email_id,user_id,path;
	public String req_id1, roomtype,family,req_date,existing_req_id;
	public String req_id; 
	ngm NGM;
	utility 	  ins_util;
	public JSONArray array1 = new JSONArray();
	public d_pubrequest(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}
	
	public boolean insertDB_publicreq() throws Exception
	{		
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pub_request values(?,?,now(),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?)");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, nor_req);
		ps.setString(6, nob_g_req);
		ps.setString(7, nob_l_req);
		ps.setString(8, "ACE");
		ps.setString(9, is_veh_req);
		ps.setString(10, "PUB");
		ps.setString(11, purpose);
		ps.setString(12, ext);
		ps.setString(13, address1);
		ps.setString(14, mobile_no);
		ps.setString(15,email_id);
		ps.setString(16, NGM.ins_sess.user_id);
		ps.setString(17, nod_g_req);
		ps.setString(18, nod_l_req);
		ps.setString(19, roomtype);
		ps.setString(20, family);
		ps.setString(21, existing_req_id);
		ps.setString(22, is_veh_req1);
		ps.setString(23, block_id);
		ps.setString(24, path);
		ps.setString(25, category);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	}
	
	public boolean insertDB_publicreq_Gad() throws Exception
	{		
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gad_request values(?,?,now(),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, nor_req);
		ps.setString(6, nob_g_req);
		ps.setString(7, nob_l_req);
		ps.setString(8, "REQ");
		ps.setString(9, is_veh_req);
		ps.setString(10, "PUB");
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
		ps.setString(24,path);
		ps.setString(25,category);
		System.out.println("ssdf"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	} 
	
	public boolean updateDB_publicreq() throws Exception
	{		
		boolean l_status=false;	
		 VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pub_request set gh_id=?,lastupdated =now(),from_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),nor_req=?,nob_g_req=?,nob_l_req=?,is_veh_req=?,purpose=?,ext=?,address=?,nod_g_req=?,nod_l_req=?,family=?,mobile_no=?,email_id=?,existing_req_id=?,is_veh_req1=?,block_id=?,category_type=? where req_id=?");
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
		ps.setString(16, existing_req_id);
		ps.setString(17, is_veh_req1);
		ps.setString(18, block_id);
		ps.setString(19, category);
		ps.setString(20, req_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB_publicreq_Gad() throws Exception
	{		
		boolean l_status=false;	
		 VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update gad_request set gh_id=?,lastupdated =now(),from_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),nor_req=?,nob_g_req=?,nob_l_req=?,is_veh_req=?,purpose=?,ext=?,address=?,nod_g_req=?,nod_l_req=?,family=?,mobile_no=?,email_id=?,existing_req_id=?,is_veh_req1=?,block_id=?,category_type=? where req_id=?");
		ps.setString(1, gh_id);
		ps.setString(2, from_date);
		ps.setString(3, to_date);
		ps.setString(4, nor_req);
		ps.setString(5, nob_g_req);
		ps.setString(6, nob_l_req);
		ps.setString(7, is_veh_req);
		ps.setString(8, purpose);
		ps.setString(9, ext);
		ps.setString(10, address1);
		ps.setString(11,nod_g_req);
		ps.setString(12,nod_l_req);
		ps.setString(13,family);
		ps.setString(14, mobile_no);
		ps.setString(15,email_id);
		ps.setString(16, existing_req_id);
		ps.setString(17, is_veh_req1);
		ps.setString(18, block_id);
		ps.setString(19, category);
		ps.setString(20, req_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean update_status() throws Exception {		
		boolean l_status=false;	
		      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update pub_request set status=? where req_id=?");
	    pss.setString(1, status);
	    pss.setString(2, req_id);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean cancelpublic() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("update pub_request set status='CAN',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean cancelpublic_Gad() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='CAN',lastupdated=now() where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("cancel    "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean deletepublic() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from pub_request where req_id=?");
		 
		pss.setString(1, req_id);
		
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss delete gad   "+pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean deletepublic_Gad() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from gad_request where req_id=?");
		 
		pss.setString(1, req_id);
		
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss delete gad   "+pss);
		pss.close();
		return l_status;		
	} 
//-------------------------------------validation------------------------------------------------------------------------	
	
	//-------------------------------------validation------------------------------------------------------------------------	

	public void VAL_insertDB() throws Exception 
	{
		 
		 val_ghid(gh_id);	 
		 val_fromdate(from_date);
		 val_todate(to_date);
		 val_nu_of_rooms(nor_req);
		 val_nu_of_bedsgens(nob_g_req);
		 val_nu_of_bedsladies(nob_l_req);
		 val_vehicle(is_veh_req);
		 val_purpose(purpose);	
		 val_address(address1);
		 val_nu_of_dorgens(nod_g_req);
		 val_nu_of_dorladies(nod_l_req);
		 val_mobile_no(mobile_no);
		 val_email_id(email_id);
		 
	}
	public boolean val_email_id(String email_id){
		if(email_id!=null){
			if(!cLength(email_id.toString(), 0, 15)){
					return false;
				}
				if (!cEmail(email_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_mobile_no(String mobile_no){
		if(mobile_no!=null){
			if(!cLength(mobile_no.toString(), 0, 15)){
					return false;
				}
				if (!cPhoneMobile(mobile_no)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_nu_of_rooms(String nor_req){
		if(nor_req!=null){
			if(!cLength(nor_req.toString(), 0, 9)){
					return false;
				}
				if (!cNumeric(nor_req)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_nu_of_dorgens(String nod_g_req){
		if(nod_g_req!=null){
			if(!cLength(nod_g_req.toString(), 0, 9)){
					return false;
				}
				if (!cNumeric(nod_g_req)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_nu_of_dorladies(String nod_l_req){
		if(nod_l_req!=null){
			if(!cLength(nod_l_req.toString(), 0, 9)){
					return false;
				}
				if (!cNumeric(nod_l_req)){
						return false;
				}
				return true;
		}else
			return false;
		
	}


	public boolean val_nu_of_bedsgens(String nob_g_req){
		if(nob_g_req!=null){
			if(!cLength(nob_g_req.toString(), 0, 9)){
					return false;
				}
				if (!cNumeric(nob_g_req)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

	public boolean val_nu_of_bedsladies(String nob_l_req){
		if(nob_l_req!=null){
			if(!cLength(nob_l_req.toString(), 0, 9)){
					return false;
				}
				if (!cNumeric(nob_l_req)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_vehicle(String is_veh_req){
		if(is_veh_req!=null){
			if(!cLength(is_veh_req.toString(), 0, 3)){
					return false;
				}
				if (!cNumeric(is_veh_req)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_purpose(String purpose){
		if(purpose!=null){
			if(!cLength(purpose.toString(), 0, 9)){
					return false;
				}
				if (!cAlphaNumeric(purpose)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_address(String address1){
		if(address1!=null){
			if(!cLength(address1.toString(), 0, 200)){
					return false;
				}
				if (!cNumeric(address1)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_name(String name){
		if(name!=null){
			if(!cLength(name.toString(), 0, 50)){
					return false;
				}
				if (!cNumeric(name)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_age(String age){
		if(age!=null){
			if(!cLength(age.toString(), 0, 3)){
					return false;
				}
				if (!cNumeric(age)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_designation(String designation){
		if(designation!=null){
			if(!cLength(designation.toString(), 0, 25)){
					return false;
				}
				if (!cNumeric(designation)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_department(String department){
		if(department!=null){
			if(!cLength(department.toString(), 0, 20)){
					return false;
				}
				if (!cNumeric(department)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_gender(String gender){
		if(gender!=""){
			if(!cLength(gender.toString(), 0, 2)){
					return false;
				}
				if (!cNumeric(gender)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
public void setpost_mod(JSONObject jsonObj)throws Exception{
	 
	System.out.println(jsonObj.toString());
	 this.req_id1 =  ins_util.setNullvalue(jsonObj.get("req_id"));
	 this.gh_id = ins_util.setNullvalue(jsonObj.get("gh_id"));
	 this.from_date =   ins_util.setNullvalue(jsonObj.get("from_date"));
	 this.to_date = ins_util.setNullvalue(jsonObj.get("to_date"));
	 this.nor_req = ins_util.setNullvalue(jsonObj.get("nor_req"));
	 this.nob_g_req = ins_util.setNullvalue(jsonObj.get("nob_g_req"));
	 this.nob_l_req = ins_util.setNullvalue(jsonObj.get("nob_l_req"));
	 this.status = ins_util.setNullvalue(jsonObj.get("status"));
	 this.is_veh_req = ins_util.setNullvalue(jsonObj.get("is_veh_req"));
	 this.mode_of_req = ins_util.setNullvalue(jsonObj.get("mode_of_req"));
	 this.purpose = ins_util.setNullvalue(jsonObj.get("purpose"));
	 this.ext = ins_util.setNullvalue(jsonObj.get("ext"));
	 this.address1 = ins_util.setNullvalue(jsonObj.get("address"));
	 this.email_id = ins_util.setNullvalue(jsonObj.get("email_id"));
	 this.mobile_no = ins_util.setNullvalue(jsonObj.get("mobile_no"));
	
	 this.user_id = ins_util.setNullvalue(jsonObj.get("userid"));
	 this.nod_g_req = ins_util.setNullvalue(jsonObj.get("nod_g_req"));
	 this.nod_l_req = ins_util.setNullvalue(jsonObj.get("nod_l_req"));
	 this.family = ins_util.setNullvalue(jsonObj.get("family"));
	 this.req_date = ins_util.setNullvalue(jsonObj.get("req_date"));
 	 
	
} 
	 
	public boolean insert_received_data()throws Exception{
	
	boolean l_status= false;
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pub_request values(?,?,now(),to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_timestamp(?,'yyyy-mm-dd HH24:MI:ss'),?)");
	ps.setString(1, req_id1);
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
	ps.setString(14, mobile_no);
	ps.setString(15, email_id);
	ps.setString(16,user_id);
 	ps.setString(17, nod_g_req);
	ps.setString(18, nod_l_req);
	ps.setString(19, roomtype);
	ps.setString(20, family);
	ps.setString(21, req_date);
	ps.setString(22, existing_req_id);
	System.out.println(ps);
	l_status=NGM.ins_utility.executeDB(ps);
	ps.close();
	 
return l_status;
	
}
public boolean selectimportdata(String time)throws Exception{
boolean retval = false;
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where status='REQ' and lastupdated > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
//	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request ");
//	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
	ps.setString(1, time);
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
public boolean updated_impdata(String time)throws Exception{
	boolean retval = false;
	
PreparedStatement ps = NGM.tr_conn.prepareStatement("update pub_request set status ='RED' where status='REQ' and lastupdated > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours')");
ps.setString(1, time);
retval=NGM.ins_utility.executeDB(ps);
	ps.close();
	return retval;
}
public boolean updated_acceptdata(String req_id)throws Exception{
	boolean retval = false;
	System.out.println("acceptdata 18 ");
PreparedStatement ps = NGM.tr_conn.prepareStatement("update pub_request set status ='ACE' where req_id=?");
ps.setString(1,req_id);
System.out.println("acceptdata 19 "+ps);
	retval=NGM.ins_utility.executeDB(ps);
	ps.close();
	return retval;
}

 
	
public boolean existdata()throws Exception{
		
		boolean l_status= false;
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where req_id=?");
		ps.setString(1, req_id1);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			l_status = true;
		}
		rs.close();
		ps.close();
		 
	return l_status;
		
	}
public boolean select_acceptdata(String req_id)throws Exception{
	boolean retval = false;
		JSONArray jobj = new JSONArray();
		String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where req_id=?");
		ps.setString(1,req_id);
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
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
}
