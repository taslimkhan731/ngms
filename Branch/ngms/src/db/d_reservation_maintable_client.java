package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_reservation_maintable_client extends db_validation
{
	public String res_id,from_date,to_date,checkin_date,checkout_date,type,status,block_id,req_id,processid,gh_id,quota_type,total_number;
	public int total_number1;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	utility 	  ins_util;
	public d_reservation_maintable_client(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		System.out.println("11111111111111111111111111 d_reservation_maintable insertDB ");
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into reservation_main_table_client values(?,TO_DATE(?,'yyyy-MM-dd'),TO_DATE(?,'yyyy-MM-dd'),TO_DATE(?,'yyyy-MM-dd'),TO_DATE(?,'yyyy-MM-dd'),?,?,?,?,?,?,?,?)");
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
		ps.setInt(12, Integer.parseInt(total_number));
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
		 
		 this.block_id = jsonObj.get("block_id").toString();
		 this.req_id = jsonObj.get("req_id").toString();
		 
		 this.processid = jsonObj.get("processid").toString();
		 this.gh_id = jsonObj.get("gh_id").toString();
		 
		 this.total_number = jsonObj.get("total_number").toString();
		 this.quota_type = jsonObj.get("quota_type").toString();
		 
		 
	} 
	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from reservation_main_table_client where req_id=?");
		 
		pss.setString(1, req_id);
		
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("psssss delete gad   "+pss);
		pss.close();
		return l_status;		
	} 
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table_client where res_id=?");
		ps.setString(1, res_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from reservation_main_table_client where res_id=?");
			ps1.setString(1, res_id);
			ps1.execute();
			ps1.close();
			 
		}
		rs.close();
		ps.close();	 
	 
		
	}
	
	
	 
	
	 
//-------------------------------------validation------------------------------------------------------------------------	
	
public void VAL_insertDB() throws Exception 
{
	 
	/* val_ghid(gh_id);	 
	 val_fromdate(from_date);
	 val_todate(to_date);
	 val_nu_of_rooms(nor_req);
	 val_nu_of_bedsgens(nob_g_req);
	 val_nu_of_bedsladies(nob_l_req);
	 val_vehicle(is_veh_req);
	 val_purpose(purpose);	
	 val_address(address1);*/
	 
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
 
 
}
