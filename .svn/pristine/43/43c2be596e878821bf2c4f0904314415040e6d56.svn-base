package db; 
import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import db.*;
public class d_importdata extends db_validation{
	public String vc_id,quota_type,process_id,is_veh_req,reqst,status,last_modified;
	public String req_id;
	public int mod_id,mrr_id;
	public String id, tablename,aliasname,tr_uin_id,tr_up_id,name,address,dob,mod_id1;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	public JSONObject jsonObj;
	
	public d_importdata(ngm p_ngm){		
		NGM=p_ngm;
		jsonObj = new JSONObject(); 
		 
			
	}
	
public boolean insertDB_mod() throws Exception {		
		boolean l_status=false;	
		 /*req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
		 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);*/
		 
	     mod_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mod_id"); 
	     NGM.ins_utility.updateseqID(NGM.tr_conn, "mod_id", mod_id);
	     
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("insert into mod_req values(?,?,?,?,?,?,?,now())");
		pss.setInt(1, mod_id);
		pss.setString(2, req_id);
		pss.setString(3, vc_id);
		pss.setString(4, quota_type);
		pss.setString(5, is_veh_req);
		pss.setString(6, process_id);
		pss.setString(7, "MOD");
		System.out.println("QUERY      "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("QUERY      "+pss);
		pss.close();
		return l_status;		
	} 


public boolean cancel_mod() throws Exception {		
	boolean l_status=false;	
	      
    PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set status='CAN',lastupdate=now() where req_id=?");
	 
	pss.setString(1, reqst);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public boolean update_vc_q() throws Exception {		
	boolean l_status=false;	
	      
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set vc_id=?,quota_type=?,lastupdate=now() where req_id=?");
    pss.setString(1, vc_id);
    pss.setString(2, quota_type);
	pss.setString(3, req_id);
	System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 
public boolean deletelist() throws Exception {		
	boolean l_status=false;	
	      
    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from mod_req where req_id=?");
	 
	pss.setString(1, reqst);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public void setpost_mod(JSONObject jsonObj)throws Exception{
	 
	 this.mod_id = Integer.parseInt(jsonObj.get("mod_id").toString());
	 this.req_id = jsonObj.get("req_id").toString();
	 this.vc_id = jsonObj.get("vc_id").toString();
	 this.quota_type =jsonObj.get("quota_type").toString();
	 this.is_veh_req = jsonObj.get("is_veh_req").toString();
	 this.process_id = jsonObj.get("process_id").toString();
	 this.status = jsonObj.get("status").toString();
	 this.last_modified = jsonObj.get("last_modified").toString();
	 
}
	 
	 
	 
	public boolean insert_received_data()throws Exception{
	
	boolean l_status= false;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
	ps.setInt(1, this.mod_id);
	ps.setString(2,this.req_id);
	ps.setString(3, this.vc_id);
	ps.setString(4, this.quota_type);
	ps.setString(5, this.is_veh_req);
	ps.setString(6, this.process_id);
	ps.setString(7, this.status);
	System.out.println("dbs"+ps);
	l_status=NGM.ins_utility.executeDB(ps);
	ps.close();
	return l_status;
	
}
public boolean updatedb()throws Exception{
	boolean l_status = false;
			
			return l_status;
	
}

public void existdata()throws Exception{
 
 PreparedStatement ps2=NGM.tr_conn.prepareStatement("select * from test_mod where mod_id = ?");
      
	 
	ps2.setString(1,this.mod_id1);
	System.out.println("000000000"+ps2);
	ResultSet rs2 = ps2.executeQuery() ;
	if(rs2.next()){
		
		 PreparedStatement ps3=NGM.tr_conn.prepareStatement("delete from test_mod where mod_id = ?");
          		 
			ps3.setString(1, this.mod_id1);
			ps3.execute();
			ps3.close();
	}
	rs2.close();
	ps2.close();
}
public boolean selectmdb()throws Exception{
boolean retval = false;
PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_upload_tables");

ResultSet rs=ps.executeQuery();
while(rs.next()){
id =rs.getString("m_id"); 
tablename= rs.getString("table_name");
aliasname= rs.getString("table_name");
 

retval= true;
}
rs.close();
ps.close();
return retval;
}

public boolean selectdb1()throws Exception{
boolean retval = false;
String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+" where last_modified <( now() - interval '"+tabletime+" hours') ");
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
		     
			//int fieldtype = rsmet.getColumnType(i);
		      
		      jsonObj1.put(fieldname, fieldvalue);
			//setJsonobject(fieldname, fieldtype, rs, jsonbj);
		}
		System.out.println("sdffffffffffffff"+jsonObj.toString());
		 
		array1.add(jsonObj1);
		System.out.println("sdffffffffffffff"+array1);
retval = true;
}
 	rs.close();
	ps.close();
return retval;
}
public void setJsonobject(String fieldname, int fieldtype, ResultSet prs, JSONObject pjson)throws Exception
{

if (fieldtype == Types.VARCHAR || fieldtype== Types.CHAR || fieldtype == Types.INTEGER || fieldtype == Types.DATE) 
{
String fieldvalue = prs.getString(fieldname);
//fieldname = fieldname + rownum;
pjson.put(fieldname, fieldvalue);
} 
if (fieldtype == Types.TIMESTAMP) {


Timestamp fieldvalue = prs.getTimestamp("theTime");
//fieldname = fieldname + rownum;
pjson.put(fieldname, fieldvalue);
System.out.println("+"+pjson.toString());
}
array1.add(jsonObj);
System.out.println("+++"+pjson.toString());

}


public boolean updatedb1()throws Exception{
boolean retval = false;
PreparedStatement ps=NGM.tr_conn.prepareStatement("update "+this.tablename+" set ");
ResultSet rs=ps.executeQuery();
rs.close();
ps.close(); 
return retval;
}
}

