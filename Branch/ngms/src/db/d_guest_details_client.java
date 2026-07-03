package db; 
import java.sql.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;
import db.*;
public class d_guest_details_client extends db_validation
{
	public String name,age,gender,address2,designation,department;
	public String req_id;
	public String guest_id;
	public JSONArray array1 = new JSONArray();
	ngm NGM;
	d_gadrequest ins_gadrequest;
	utility 	  ins_util;
	public d_guest_details_client(ngm p_ngm){		
		NGM=p_ngm;
		ins_gadrequest=new d_gadrequest(NGM);
		ins_util = new utility();
		
	}

	public boolean insertDB_guest() throws Exception
	{			
		boolean l_status=false;	
				 
	     //VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guest_details_client values(?,?,?,?,?,?,?,?)");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, name);
		ps.setString(4, age);
		ps.setString(5, gender);
		ps.setString(6, designation);
		ps.setString(7, department);
		ps.setString(8,	address2);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id").toString());
		 this.guest_id =  ins_util.setNullvalue(jsonObj.get("guest_id").toString());
		 this.name =  ins_util.setNullvalue(jsonObj.get("name").toString());
		 this.age = ins_util.setNullvalue(jsonObj.get("age").toString());
		 this.gender =  ins_util.setNullvalue(jsonObj.get("gender").toString());
		 this.designation =  ins_util.setNullvalue(jsonObj.get("designation").toString());
		 this.department =  ins_util.setNullvalue(jsonObj.get("department").toString());
		 this.address2 =  ins_util.setNullvalue(jsonObj.get("address").toString());
		  
	}
	 
	
	public boolean deleteguest() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from guest_details where req_id=?");
		 
		pss.setString(1, req_id);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
//-------------------------------------validation------------------------------------------------------------------------	
	
public void VAL_insertDB() throws Exception 
{
	 
	
	 val_address(address2);
	 val_age(age);
	 val_gender(gender);
	 val_name(name);
	 val_department(department);
	 val_designation(designation);
	 
}



public boolean val_address(String address2){
	if(address2!=null){
		if(!cLength(address2.toString(), 0, 200)){
				return false;
			}
			if (!cAlphaNumeric(address2)){
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
			if (!cAlphaNumeric(name)){
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
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details");

	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
	//ps.setString(1,time);
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
public void setpost_mod(JSONObject jsonObj,String req_id)throws Exception{
	 
	 this.req_id =  req_id;
	 this.guest_id = jsonObj.get("guest_id").toString();
	 this.name = jsonObj.get("name").toString();
	 this.age =jsonObj.get("age").toString();
	 this.gender = jsonObj.get("gender").toString();
	 this.designation = jsonObj.get("designation").toString();
	 this.department = jsonObj.get("department").toString();
	 this.address2 = jsonObj.get("address").toString();
	 System.out.println("DAta posted");
} 
	public boolean insert_received_data()throws Exception{
	
	boolean l_status= false;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guest_details values(?,?,?,?,?,?,?,?)");
	ps.setString(1, req_id);
	ps.setString(2, guest_id);
	ps.setString(3, name);
	ps.setString(4, age);
	ps.setString(5, gender);
	ps.setString(6, designation);
	ps.setString(7, department);
	ps.setString(8,	address2);
	l_status=NGM.ins_utility.executeDB(ps);
	 	
	ps.close();
	 
return l_status;
	
}
	public void existdata()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details_client where guest_id=?");
		ps.setString(1, guest_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from guest_details_client where guest_id=?");
			ps1.setString(1, guest_id);
			ps1.execute();
			ps1.close();
		}
			 
		rs.close();
		ps.close();
		
	}
}
