package db;

import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.sql.Types;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.utility;



public class d_uploadtable {
	ngm NGM;
	public String id, tablename,aliasname,tr_uin_id,tr_up_id,name,address,dob,mod_id;
	//public JSONObject json; 
	public int rollno;
	public JSONObject jsonObj;
	public JSONObject jsonObj1;
	public JSONArray array1 = new JSONArray();
	public d_uploadtable(ngm p_ngm){
		NGM		= p_ngm;
		jsonObj = new JSONObject(); 
		 
		
		}
	
	 
	public boolean insertdb_mod()throws Exception{
		boolean l_status = false;
		 
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into "+tablename+" values(?,?,?,?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),?)");
	      
      	ps.setString(1, jsonObj.get("mod_id").toString());
		//ps.setInt(2, Integer.parseInt(jsonObj.get("rollno").toString()));
      	ps.setString(2, jsonObj.get("req_id").toString());
		ps.setString(3, jsonObj.get("vc_id").toString());
		ps.setString(4, jsonObj.get("quota_type").toString());
		ps.setString(5, jsonObj.get("is_veh_req").toString());
		ps.setString(6, jsonObj.get("process_id").toString());
		ps.setString(7, jsonObj.get("status").toString());
		ps.setString(8, jsonObj.get("last_modified").toString()); 
		ps.setString(9, jsonObj.get("gh_id").toString()); 
   	 		 System.out.println("sdfsadfasdf"+ps);
   	 		 l_status=NGM.ins_utility.executeDB(ps);
   	 	
   	 		 	ps.close(); 
				return l_status;
		
	}
	public boolean updatedb()throws Exception{
		boolean l_status = false;
				
				return l_status;
		
	}
	
public void existdata()throws Exception{
	 
	 PreparedStatement ps2=NGM.tr_conn.prepareStatement("select * from "+this.tablename+" where mod_id = ?");
          
		 
		ps2.setString(1,this.mod_id);
		System.out.println("000000000"+ps2);
		ResultSet rs2 = ps2.executeQuery() ;
		if(rs2.next()){
			
			 PreparedStatement ps3=NGM.tr_conn.prepareStatement("delete from "+this.tablename+" where mod_id = ?");
	          		 
				ps3.setString(1, mod_id);
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

	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+" ");
	ResultSet rs=ps.executeQuery();
	ResultSetMetaData rsmet = rs.getMetaData();
	int col_count = rsmet.getColumnCount();
	String fieldname="";
	int rownum =0;
	while(rs.next()){ 
			 
			for (int i = 1; i < col_count + 1; i++ ) 
			{ 	 
			      fieldname  = rsmet.getColumnName(i);
			     
				int fieldtype = rsmet.getColumnType(i);	
				 
				setJsonobject(fieldname, fieldtype, rs, jsonObj);
			}
			array1.add(jsonObj);
		 
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
