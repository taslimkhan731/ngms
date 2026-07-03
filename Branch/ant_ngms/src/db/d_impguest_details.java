package db; 
import java.sql.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;
import db.*;
public class d_impguest_details extends db_validation
{
	public String name,age,gender,address2,designation,department;
	public String req_id;
	public String guest_id;
	public JSONArray array1 = new JSONArray();
	ngm NGM;
	d_gadrequest ins_gadrequest;
	utility 	  ins_util;
	public d_impguest_details(ngm p_ngm){		
		NGM=p_ngm;
		ins_gadrequest=new d_gadrequest(NGM);
		ins_util = new utility();
		
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
			if (!cNumeric(address2)){
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
public boolean selectimportdata(String req_id)throws Exception{
boolean retval = false;
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_guest_details where req_id=?");
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
	ps.setString(1, req_id);
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

public void setpost_mod(JSONObject jsonObj)throws Exception{
	 
	 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
	 
	 this.guest_id = ins_util.setNullvalue(jsonObj.get("guest_id"));
	 
	 this.name = ins_util.setNullvalue(jsonObj.get("name"));
	 this.age =ins_util.setNullvalue(jsonObj.get("age"));
	 this.gender = ins_util.setNullvalue(jsonObj.get("gender"));
	 this.designation = ins_util.setNullvalue(jsonObj.get("designation"));
	 this.department =ins_util.setNullvalue(jsonObj.get("department"));
	 this.address2 = ins_util.setNullvalue(jsonObj.get("address"));
	 
} 
	public boolean insert_received_data()throws Exception{
	
	boolean l_status= false;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pub_guest_test values(?,?,?,?,?,?,?,?)");
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
public boolean existdata()throws Exception{
		
		boolean l_status= false;
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_guest_test where guest_id=?");
		ps.setString(1, guest_id);
		System.out.println("is exist pubrequest");
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			l_status = true;
		}
	 
		rs.close();
		ps.close(); 
	return l_status;
		
	}
}
