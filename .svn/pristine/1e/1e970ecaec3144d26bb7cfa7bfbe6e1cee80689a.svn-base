package db; 
import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;
import db.*;
public class d_mod_req extends db_validation{
	public String vc_id,quota_type,process_id,is_veh_req,is_veh_req1,reqst,status,last_modified,gh_id,sel_block,duringstay;
	public String req_id,mod_id,mrr_id;
	public String id, tablename,aliasname,tr_uin_id,tr_up_id,name,address,dob,mod_id1;
	ngm NGM;
	public JSONArray array1 = new JSONArray();
	public JSONObject jsonObj;
	utility 	  ins_util;
	public d_mod_req(ngm p_ngm){		
		NGM=p_ngm;
		jsonObj = new JSONObject(); 
		ins_util = new utility();
		 
			
	}
	
public boolean insertDB_mod() throws Exception {		
		boolean l_status=false;	
		/*req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);*/
		VAL_insertDB_mod();  
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("insert into mod_req values(?,?,?,?,?,?,?,now(),?,?,?)");
		pss.setString(1, mod_id);
		pss.setString(2, req_id);
		pss.setString(3, vc_id);
		pss.setString(4, quota_type);
		pss.setString(5, is_veh_req);
		pss.setString(6, process_id);
		pss.setString(7, "MOD");
		pss.setString(8, gh_id);
		pss.setString(9, is_veh_req1);
		pss.setString(10, duringstay);
		System.out.println("QUERY      "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 

public String selectDB_mobDetails(String req_id) throws Exception {		
	String mobile_no="";
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select mobile_no from gad_request where req_id = ?");
	ps.setString(1, req_id);
	System.out.println("SSSSSSSSSSS  "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		mobile_no = rs.getString("mobile_no");
	}
	rs.close();
	ps.close();
	return mobile_no;
} 

public boolean insertDB_modGH() throws Exception {		
	boolean l_status=false;	
	 /*req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
	 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);*/
	VAL_insertDB_modGH();    
    PreparedStatement pss=NGM.tr_conn.prepareStatement("insert into mod_req values(?,?,?,?,?,?,?,now(),?,?)");
	pss.setString(1, mod_id);
	pss.setString(2, req_id);
	pss.setString(3, vc_id);
	pss.setString(4, quota_type);
	pss.setString(5, is_veh_req);
	pss.setString(6, process_id);
	pss.setString(7, "MOD");
	pss.setString(8, gh_id);
	pss.setString(9, is_veh_req1);
	System.out.println("QUERY      "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 


public boolean cancel_mod() throws Exception {		
	boolean l_status=false;	
	VAL_cancel_mod();  
    PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set status='CAN',lastupdate=now() where req_id=?");
	pss.setString(1, req_id);
	System.out.println("del mod     "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public boolean cancelgad() throws Exception {		
	boolean l_status=false;	
	VAL_cancel_mod();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update gad_request set status='CAN',lastupdated=now() where req_id=?");
	pss.setString(1, req_id);
	System.out.println("cancel$#@    "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 
public boolean cancelmodreq() throws Exception {		
	boolean l_status=false;	
	VAL_cancel_mod();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set status='CREQ',lastupdate=now() where req_id=?");
	pss.setString(1, req_id);
	System.out.println("cancel$#@    "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 
public boolean cancelpub() throws Exception {		
	boolean l_status=false;	
	VAL_cancel_mod();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update pub_request set status='CAN',lastupdated=now() where req_id=?");
	pss.setString(1, req_id);
	System.out.println("cancel$#@    "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public boolean update_vc_q() throws Exception {		
	boolean l_status=false;	
	VAL_update_vc_q();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set vc_id=?,quota_type=?,lastupdate=now(),during_stay_conveancy=? where req_id=?");
    pss.setString(1, vc_id);
    pss.setString(2, quota_type);
    pss.setString(3, duringstay);
	pss.setString(4, req_id);
	System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public boolean update_vehicle() throws Exception {		
	boolean l_status=false;	
	VAL_update_vc_q();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set is_veh_req=?,is_veh_req1=?,lastupdate=now() where req_id=?");
    pss.setString(1, is_veh_req);
    pss.setString(2, is_veh_req1);
	pss.setString(3, req_id);
	System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public boolean insertDB_mod_upload() throws Exception {		
	boolean l_status=false;	
	 /*req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
	 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);*/
	VAL_insertDB_mod_upload();
    PreparedStatement pss=NGM.tr_conn.prepareStatement("insert into mod_req values(?,?,?,?,?,?,?,now(),?)");
	pss.setString(1, mod_id1);
	pss.setString(2, req_id);
	pss.setString(3, vc_id);
	pss.setString(4, quota_type);
	pss.setString(5, is_veh_req);
	pss.setString(6, process_id);
	pss.setString(7, status);
	pss.setString(8, gh_id);
	System.out.println("QUERY      "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	System.out.println("QUERY      "+pss);
	pss.close();
	return l_status;		
} 





public boolean deletelist() throws Exception {		
	boolean l_status=false;	
    VAL_deletelist();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from mod_req where req_id=?");
	pss.setString(1, reqst);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
	return l_status;		
} 

public void setpost_mod(JSONObject jsonObj)throws Exception{
 
 this.mod_id1 =   ins_util.setNullvalue(jsonObj.get("mod_id"));
 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
 this.vc_id =  ins_util.setNullvalue(jsonObj.get("vc_id"));
 this.quota_type = ins_util.setNullvalue(jsonObj.get("quota_type"));
 this.is_veh_req =  ins_util.setNullvalue(jsonObj.get("is_veh_req"));
 this.process_id =  ins_util.setNullvalue(jsonObj.get("process_id"));
 this.status =  ins_util.setNullvalue(jsonObj.get("status"));
 this.last_modified =  ins_util.setNullvalue(jsonObj.get("lastupdate"));
 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
} 
 
public String selectDB_VC_purpose() throws Exception {		
    VAL_selectDB_VC_purpose();
	String purpose_vc="";
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from ((select vc_id,req_id from mod_req where req_id=?)x join (select req_id,purpose from gad_request)y on x.req_id=y.req_id)a join (select * from visitor_category)b on a.vc_id=b.vc_id");
	ps.setString(1, req_id);
	System.out.println("ps.................. "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		purpose_vc=rs.getString("desg_category")+"("+rs.getString("category")+","+rs.getString("sub_cat")+")";
		purpose_vc=purpose_vc+"-"+rs.getString("purpose");
		purpose_vc=purpose_vc+"-"+rs.getString("vc_id");
	}
	rs.close();
	ps.close();
	System.out.println("purpose_vcpurpose_vcpurpose_vc "+purpose_vc);
	return purpose_vc;
} 	


public void existdata()throws Exception{

    VAL_existdata();
    //PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req where mod_id=?");
    ps.setString(1, mod_id1);
    System.out.println("vehicle"+ps);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
    	PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from mod_req  where mod_id=?");
    	ps1.setString(1, mod_id1);
    	ps1.execute();
    	ps1.close();
    }
    rs.close();
	ps.close();
}

public boolean existdata_accept()throws Exception{

    VAL_existdata();
    //PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req where mod_id=?");
    ps.setString(1, mod_id1);
    System.out.println("vehicle"+ps);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
    	
    	return true;
    }
    rs.close();
	ps.close();
return false;
} 
	 
	 
public boolean updateDBstatus()throws Exception{
	boolean l_status = false;
	System.out.println(req_id);
	System.out.println(status);
	VAL_updateDBstatus();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set status=?,process_id=?,lastupdate=now() where req_id=?");
    pss.setString(1, status);
	pss.setString(2, process_id);
	pss.setString(3, req_id);
	System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
			return l_status;
	
}
public boolean updateDBstatus1()throws Exception{
	boolean l_status = false;
	VAL_updateDBstatus();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set status=?,lastupdate=now() where req_id=? and process_id=? ");
    pss.setString(1, status);
    pss.setString(2, req_id);
	pss.setString(3, process_id);
	System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
			return l_status;
	
}
public boolean updateDBsta()throws Exception{
	boolean l_status = false;
	VAL_updateDBsta();
	PreparedStatement pss=NGM.tr_conn.prepareStatement("update mod_req set status=?,lastupdate=now() where process_id=?");
    pss.setString(1, status);
	pss.setString(2, process_id);
	System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
	l_status=NGM.ins_utility.executeDB(pss);
	pss.close();
			return l_status;
	
}

 

public boolean selectDB_mod() throws Exception {		
	boolean l_status=false;	
	System.out.println("TTTTTTTT");
    // VAL_insertDB();
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req where req_id=?");
	ps.setString(1, req_id);
	System.out.println("mod cancel   "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		status=rs.getString("status");
		l_status=true;
	}
	rs.close();
	ps.close();
	return l_status;
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

public boolean selectimportdata(String time)throws Exception{ 
boolean retval = false;
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req where lastupdate > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours' )");
 	ps.setString(1,time);
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req");

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
///////////////////////////////////////////////////*****validation*****//////////////////////////////////////////////
public void VAL_insertDB_mod() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_insertDB_mod", NGM);
	}
	if(!(val_modid(mod_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id", "VAL_insertDB_mod", NGM);
	}
	if(!(val_vccategory(vc_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid vc_id", "VAL_insertDB_mod", NGM);
	}
	if(!(val_quota_type(quota_type))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid quota_type", "VAL_insertDB_mod", NGM);
	}
	if(!(val_one_zero(is_veh_req))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid is_veh_req", "VAL_insertDB_mod", NGM);
	}
	if(!(val_pro_id_opt(process_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid process_id", "VAL_insertDB_mod", NGM);
	}
}
public void VAL_insertDB_modGH() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_insertDB_modGH", NGM);
	}
	if(!(val_modid(mod_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id", "VAL_insertDB_modGH", NGM);
	}
	if(!(val_vccategory(vc_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid vc_id", "VAL_insertDB_modGH", NGM);
	}
	if(!(val_quota_type(quota_type))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid quota_type", "VAL_insertDB_modGH", NGM);
	}
	if(!(val_one_zero(is_veh_req))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid is_veh_req", "VAL_insertDB_modGH", NGM);
	}
	if(!(val_pro_id_opt(process_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid process_id", "VAL_insertDB_mod", NGM);
	}
}
public void VAL_cancel_mod() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_cancel_mod", NGM);
	}
}
public void VAL_update_vc_q() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_update_vc_q", NGM);
	}
	if(!(val_vccategory(vc_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid vc_id", "VAL_update_vc_q", NGM);
	}
	if(!(val_quota_type(quota_type))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid quota_type", "VAL_update_vc_q", NGM);
	}
}
public void VAL_insertDB_mod_upload() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_insertDB_mod_upload", NGM);
	}
	if(!(val_modid(mod_id1))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id1", "VAL_insertDB_mod_upload", NGM);
	}
	if(!(val_vccategory(vc_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid vc_id", "VAL_insertDB_mod_upload", NGM);
	}
	if(!(val_quota_type(quota_type))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid quota_type", "VAL_insertDB_mod_upload", NGM);
	}
	if(!(val_one_zero(is_veh_req))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid is_veh_req", "VAL_insertDB_mod_upload", NGM);
	}
	if(!(val_pro_id_opt(process_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid process_id", "VAL_insertDB_mod_upload", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid status", "VAL_insertDB_mod_upload", NGM);
	}
}
public void VAL_deletelist() throws Exception {
	
	if(!(val_reqid(reqst))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_deletelist", NGM);
	}
}
public void VAL_selectDB_VC_purpose() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_selectDB_VC_purpose", NGM);
	}
}
public void VAL_existdata() throws Exception {
	
	if(!(val_modid(mod_id1))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id1", "VAL_existdata", NGM);
	}
}
public void VAL_updateDBstatus() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_updateDBstatus", NGM);
	}
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid status", "VAL_updateDBstatus", NGM);
	}
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid process_id", "VAL_updateDBstatus", NGM);
	}
}
public void VAL_updateDBsta() throws Exception {
	
	if(!(val_status(status))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid status", "VAL_updateDBsta", NGM);
	}
	if(!(val_pro_id(process_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid process_id", "VAL_updateDBsta", NGM);
	}
}
}

