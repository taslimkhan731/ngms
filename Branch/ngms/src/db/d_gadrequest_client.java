package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_gadrequest_client extends db_validation
{
	public String address1,guest_id,purpose,nor_req,nob_g_req,nob_l_req,nod_g_req,nod_l_req,total_no_adults,total_no_childs,from_date,to_date,status,is_veh_req,mode_of_req,gh_id,lastupdate,ext;
	public String req_id,user_id;
	ngm NGM;
	utility 		ins_util;
	public JSONArray array1 = new JSONArray();
	public d_gadrequest_client(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB_gadreq() throws Exception
	{		
		boolean l_status=false;	
		
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gad_request_client values(?,?,now(),TO_DATE(?,'yyyy-MM-dd'),TO_DATE(?,'yyyy-MM-dd'),?,?,?,?,?,?,?,?,?,?,now())");
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
		System.out.println("insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public void setpost_mod2(JSONObject jsonObj)throws Exception{
		 System.out.println("jsong objeecct"+jsonObj);
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id").toString());
		 this.gh_id = ins_util.setNullvalue(jsonObj.get("gh_id").toString());
		 this.from_date = ins_util.setNullvalue(jsonObj.get("from_date").toString());
		 this.to_date =ins_util.setNullvalue(jsonObj.get("to_date").toString());
		 this.nor_req = ins_util.setNullvalue(jsonObj.get("nor_req").toString());
		 this.nob_g_req = ins_util.setNullvalue(jsonObj.get("nob_g_req").toString());
		 this.nob_l_req = ins_util.setNullvalue(jsonObj.get("nob_l_req").toString());
		 this.status = ins_util.setNullvalue(jsonObj.get("status").toString());
		 this.is_veh_req =  ins_util.setNullvalue(jsonObj.get("is_veh_req").toString());

		 this.mode_of_req =  ins_util.setNullvalue(jsonObj.get("mode_of_req").toString());

		 this.purpose =  ins_util.setNullvalue(jsonObj.get("purpose").toString());

		 this.ext =  ins_util.setNullvalue(jsonObj.get("ext").toString());

		 this.address1 =  ins_util.setNullvalue(jsonObj.get("address").toString());

		 this.user_id =  ins_util.setNullvalue(jsonObj.get("userid").toString());

			 
	}
	
	
	 
	public boolean update_status() throws Exception {		
		boolean l_status=false;			      
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status=? where req_id=?");
	    pss.setString(1, status);
	    pss.setString(2, req_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	 
	 
	public boolean deletegad() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from gad_request where req_id=?");
		 
		pss.setString(1, req_id);
		
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss delete gad   "+pss);
		pss.close();
		return l_status;		
	} 
	
	
	 
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
		if(!cLength(name.toString(), 0, 200)){
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
	if(gender!=null){
		if(!cLength(gender.toString(), 0, 200)){
				return false;
			}
			if (!cNumeric(gender)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean updated_impdata()throws Exception{
	boolean retval = false;
	
PreparedStatement ps = NGM.tr_conn.prepareStatement("update pub_request set status ='RED'");
	retval=NGM.ins_utility.executeDB(ps);
	ps.close();
	return retval;
}

public void setpost_mod(JSONObject jsonObj,String req_id)throws Exception{
	 
	System.out.println(jsonObj.toString());
	 this.req_id =   req_id;
	 this.gh_id = jsonObj.get("gh_id").toString();
	 this.from_date =   jsonObj.get("from_date").toString();
	 this.to_date = jsonObj.get("to_date").toString();
	 this.nor_req = jsonObj.get("nor_req").toString();
	 this.nob_g_req = jsonObj.get("nob_g_req").toString();
	 this.status = jsonObj.get("status").toString();
	 this.is_veh_req = jsonObj.get("is_veh_req").toString();
	 this.mode_of_req = jsonObj.get("mode_of_req").toString();
	 this.purpose = jsonObj.get("purpose").toString();
	 this.ext = jsonObj.get("ext").toString();
	 this.address1 = jsonObj.get("address").toString();
	 this.user_id = jsonObj.get("userid").toString();
	
	 
	
} 
public boolean selectimportdata(String time)throws Exception{ 
boolean retval = false;
System.out.println("sdfnisdfnisdfnsidfnsidfsnisndifnsdfisdnfis");
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
// 	ps.setString(1,time);
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request");
	 
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


	public boolean insert_received_data()throws Exception{
	
	boolean l_status= false;
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into gad_request values(?,?,now(),TO_DATE(?,'yyyy-MM-dd'),TO_DATE(?,'yyyy-MM-dd'),?,?,?,?,?,?,?,?,?,?,now())");
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
	l_status=NGM.ins_utility.executeDB(ps);
	ps.close();
	 
return l_status;
	
}
	
public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from gad_request_client where req_id=?");
		ps.setString(1, req_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from gad_request_client where req_id=?");
			ps1.setString(1, req_id);
			ps1.execute();
			ps1.close();
			 
		}
		rs.close();
		ps.close();	 
	 
		
	}
}
