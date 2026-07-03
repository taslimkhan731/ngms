package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;

public class d_reservation_waitinglist_client extends db_validation
{
	public String res_wid,mod_id,req_id,block_id,number,type,par_process_id,process_id,status,gh_id;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	utility 	  ins_util;
	public d_reservation_waitinglist_client(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into reservation_waitinglist_client values(?,?,?,?,?,?,?,?,?,?)");
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
		 
		 this.res_wid =   ins_util.setNullvalue(jsonObj.get("res_wid"));
		 
		 this.type =  ins_util.setNullvalue(jsonObj.get("type"));
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
		 this.type =  ins_util.setNullvalue(jsonObj.get("type").toString());
		 this.status =  ins_util.setNullvalue(jsonObj.get("status").toString());
		 
		 this.block_id =  ins_util.setNullvalue(jsonObj.get("block_id"));
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 
		 this.par_process_id =  ins_util.setNullvalue(jsonObj.get("par_process_id"));
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
	} 
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist_client where res_wid=?");
		ps.setString(1, res_wid);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from reservation_waitinglist_client where res_wid=?");
			ps1.setString(1, res_wid);
			ps1.execute();
			ps1.close(); 
		}
		rs.close();
		ps.close();	 
	 
		
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
public boolean selectimportdata(String time)throws Exception{ 
	boolean retval = false;
		JSONArray jobj = new JSONArray();
		String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
//		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
//		ps.setString(1,time);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist");

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
