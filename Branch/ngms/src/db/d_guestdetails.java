package db; 
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itextpdf.text.log.SysoLogger;

import common.db_validation;
import common.utility;
import db.*;
public class d_guestdetails extends db_validation
{
	public String name,age,gender,address2,designation,department,sstatus;
	public String req_id;
	public String guest_id;
	public JSONArray array1 = new JSONArray();
	ngm NGM;
	d_gadrequest ins_gadrequest;
	utility 	  ins_util;
	public d_guestdetails(ngm p_ngm){		
		NGM=p_ngm;
		ins_gadrequest=new d_gadrequest(NGM);
		ins_util = new utility();
		
	}
	public String getGuestDetail()
	{
		String temp="";
		temp=this.name+(this.designation!=null?","+this.designation:"")+(this.department!=null?","+this.department:"");
		return temp;
		
	}	
	public boolean insertDB_guest() throws Exception
	{	
		
		boolean l_status=false;	
		
		int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
	    guest_id = String.valueOf(guest_id1);
	    VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guest_details values(?,?,?,?,?,?,?,?,now())");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, name);
		ps.setString(4, age);
		ps.setString(5, gender);
		ps.setString(6, designation);
		ps.setString(7, department);
		ps.setString(8,	address2);
		System.out.println("guestAAAAAAAA      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_guestGH() throws Exception
	{	
		
		boolean l_status=false;	
		
	    VAL_insertDB_guestGH();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guest_details values(?,?,?,?,?,?,?,?,now())");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, name);
		ps.setString(4, age);
		ps.setString(5, gender);
		ps.setString(6, designation);
		ps.setString(7, department);
		ps.setString(8,	address2);
		System.out.println("guestAAAAAAAA      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB_guest() throws Exception
	{	
		
		boolean l_status=false;	
		
		// String guest_id1 = String.valueOf(guest_id);
	/////    VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update guest_details set req_id=?,name=?,age=?,gender=?,designation=?,department=?,address=?,last_modified=now() where guest_id=?,last_modified=now()");
		ps.setString(1, req_id);
		ps.setString(2, name);
		ps.setString(3, age);
		ps.setString(4, gender);
		ps.setString(5, designation);
		ps.setString(6, department);
		ps.setString(7,	address2);
		ps.setString(8, guest_id);
		System.out.println("quert guest +++++++++++++++++++++++++++++++++++++  "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	
	public boolean deleteguest() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from guest_details where req_id=?");
		 
		pss.setString(1, req_id);
		System.out.println("DELETE       "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean insertDB_guest_upload() throws Exception
	{			
		boolean l_status=false;	
				 
	    VAL_insertDB_guest_upload();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into guest_details values(?,?,?,?,?,?,?,?,now())");
		ps.setString(1, req_id);
		ps.setString(2, guest_id);
		ps.setString(3, name);
		ps.setString(4, age);
		ps.setString(5, gender);
		ps.setString(6, designation);
		ps.setString(7, department);
		ps.setString(8,	address2);
		l_status=NGM.ins_utility.executeDB(ps);
		System.out.println("HHHJJJJ"+ps);
		ps.close();
		return l_status;		
	} 
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 this.guest_id =  ins_util.setNullvalue(jsonObj.get("guest_id"));
		 this.name =  ins_util.setNullvalue(jsonObj.get("name"));
		 this.age = ins_util.setNullvalue(jsonObj.get("age"));
		 this.gender =  ins_util.setNullvalue(jsonObj.get("gender"));
		 this.designation =  ins_util.setNullvalue(jsonObj.get("designation"));
		 this.department =  ins_util.setNullvalue(jsonObj.get("department"));
		 this.address2 =  ins_util.setNullvalue(jsonObj.get("address"));
		  
	}
	 
	public String selectDB_reqidList()throws Exception{
		
		boolean l_status=false;
		String t_req_list = "";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select req_id from guest_details where upper(name) like upper(?)");
		ps.setString(1, name);
		System.out.println("like............. "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			 req_id = rs.getString("req_id");
			 
			 if(!t_req_list.equals("")){
				 t_req_list = t_req_list+"','";
			 }
			 t_req_list = t_req_list + req_id;
			 System.out.println("t_req_list............. "+t_req_list);
			 l_status = true;
		}
		rs.close();
		ps.close();
		return t_req_list;
	} 
	public void existdata_upload()throws Exception{
		
		 
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where guest_id=? and req_id=?");
		ps.setString(1, guest_id);
		ps.setString(2, req_id);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from guest_details where guest_id=? and req_id=?");
			ps1.setString(1, guest_id);
			ps1.setString(2, req_id);
			ps1.execute();
			ps1.close(); 
		}
		rs.close();
		ps.close();
		
	}
	
	public boolean selectDB()throws Exception{
		
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where guest_id=?");
		ps.setString(1, guest_id);
		System.out.println("ggg"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			req_id = rs.getString("req_id");
			 name = rs.getString("name");
			 designation = rs.getString("designation");
			 department = rs.getString("department");
			 address2 = rs.getString("address");

			 l_status = true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
/*public boolean selectDB_reqGuest()throws Exception{
		
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select MIN(guest_id::Integer) from guest_details where req_id=?");
		ps.setString(1, req_id);
		String min="";
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			 min = rs.getString("min");
			 PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from guest_details where guest_id=?");
				ps1.setString(1, min);
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()){
					 name = rs1.getString("name");
					 designation = rs1.getString("designation");				 
					 department = rs1.getString("department");				 

			 l_status = true;
		}}
		rs.close();
		ps.close();
		return l_status;
	}*/
	
	public boolean selectDB_reqGuest(String gh_id)throws Exception{
	boolean l_status=false;	
    //PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from gad_request where req_id=? and gh_id=?) a join (select * from guest_details)b on a.req_id=b.req_id order by guest_id limit 1");
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (SELECT ROW_NUMBER() OVER (PARTITION BY  gad_request.req_id order by guest_details.guest_id) AS i, guest_details.*,gad_request.* from guest_details join gad_request  on gad_request.req_id=guest_details.req_id where gad_request.req_id=? and gad_request.gh_id=? order by guest_details.req_id,guest_details.guest_id )f1 where f1.i =1");
    ps.setString(1, req_id);
	ps.setString(2, gh_id);
	ResultSet rs=ps.executeQuery();
	System.out.println("Deepak"+ps);
	while(rs.next()){
		 name = rs.getString("name");
		 designation = rs.getString("designation");				 
		 department = rs.getString("department");		
		l_status=true;
	}
	rs.close();
	ps.close();
	return l_status;
	}
	
	public boolean selectDB_reqGuestDetails(String gh_id,String block)throws Exception{
		boolean l_status=false;	
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from gad_request where req_id=? and gh_id=? and block_id =?) a join (select * from guest_details)b on a.req_id=b.req_id order by guest_id limit 1");
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		ps.setString(3, block);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			 name = rs.getString("name");
			 designation = rs.getString("designation");				 
			 department = rs.getString("department");		
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
		}
	
	public String selectDB_Guests()throws Exception{
		boolean l_status=false;	
		String namelist = "";
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			if(namelist!=""){
				namelist = namelist+", ";
			}
			namelist = namelist+rs.getString("name");
			 /*designation = rs.getString("designation");				 
			 department = rs.getString("department");*/		
			l_status=true;
		}
		rs.close();
		ps.close();
		return namelist;
		}
	
public boolean selectDB_reqid()throws Exception{
	
	boolean l_status=false;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select req_id from guest_details where upper(name) like upper('%"+name+"%')");
	//ps.setString(1, name);
	System.out.println("like............. "+ps);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		 req_id = rs.getString("req_id");
		 l_status = true;
	}
	rs.close();
	ps.close();
	return l_status;
} 
public boolean selectimportdata(String time)throws Exception{ 
boolean retval = false;
	JSONArray jobj = new JSONArray();
	String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details");

	//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
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
public void setpost_mod(JSONObject jsonObj,String req_id)throws Exception{
	 
	 this.req_id =  req_id;
	 this.guest_id =  ins_util.setNullvalue(jsonObj.get("guest_id"));
	 this.name =  ins_util.setNullvalue(jsonObj.get("name"));
	 this.age = ins_util.setNullvalue(jsonObj.get("age"));
	 this.gender =  ins_util.setNullvalue(jsonObj.get("gender"));
	 this.designation =  ins_util.setNullvalue(jsonObj.get("designation"));
	 this.department =  ins_util.setNullvalue(jsonObj.get("department"));
	 this.address2 =  ins_util.setNullvalue(jsonObj.get("address"));
	 System.out.println("DAta posted");
} 
	 
public boolean existdata()throws Exception{
		
		boolean l_status= false;
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where guest_id=? and req_id=?");
		ps.setString(1, this.guest_id);
		ps.setString(2, this.req_id);
		System.out.println("sdfsdf"+ps);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			l_status = true;
		}
		rs.close();
		ps.close();	 
	return l_status;
		
	}

public String selectDB_guestDetails(String gh_id) throws Exception {		
	boolean l_status=false;	
	String mob_no="";
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from gad_request where req_id=? and gh_id=?) a join (select * from guest_details)b on a.req_id=b.req_id order by guest_id limit 1");
	ps.setString(1, req_id);
	ps.setString(2, gh_id);
	System.out.println("SSSSSSSSSSS  "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		this.name=rs.getString("name");
		this.sstatus=rs.getString("status");
		this.guest_id=rs.getString("guest_id");
		mob_no = rs.getString("mobile_no");
		l_status=true;
	}
	rs.close();
	ps.close();
	return mob_no;
} 
public String selectDB_guestDetailsEmail(String gh_id) throws Exception {		
	boolean l_status=false;	
	String email_id="";
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from gad_request where req_id=? and gh_id=?) a join (select * from guest_details)b on a.req_id=b.req_id order by guest_id limit 1");
	ps.setString(1, req_id);
	ps.setString(2, gh_id);
	System.out.println("SSSSSSSSSSS  "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		this.name=rs.getString("name");
		this.sstatus=rs.getString("status");
		this.guest_id=rs.getString("guest_id");
		email_id = rs.getString("email_id");
		l_status=true;
	}
	rs.close();
	ps.close();
	return email_id;
} 

public String selectDB_guesthouseDetails(String block_id) throws Exception {		
	boolean l_status=false;	
	String block_name="";
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select block_name from room_block where block_id = ?");
	ps.setString(1, block_id);
	System.out.println("SSSSSSSSSSS  "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		block_name = rs.getString("block_name");
		l_status=true;
	}
	rs.close();
	ps.close();
	return block_name;
} 

public String selectDB_cityDetails(String gh_id) throws Exception {		
	String block_name="";
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select gh_name from gh_details where gh_id = ?");
	ps.setString(1, gh_id);
	System.out.println("SSSSSSSSSSS  "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		block_name = rs.getString("gh_name");
	}
	rs.close();
	ps.close();
	return block_name;
} 


public boolean selectDB_guestName() throws Exception {		
	boolean l_status=false;	
    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where req_id=? and guest_id=?");
	ps.setString(1, req_id);
	ps.setString(2, guest_id);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		name=rs.getString("name");
		guest_id=rs.getString("guest_id");
		l_status=true;
	}
	rs.close();
	ps.close();
	return l_status;
} 

//-------------------------------------validation------------------------------------------------------------------------	
public void VAL_insertDB() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid req_id", "VAL_insertDB", NGM);
	}
	if(!(val_guestid(guest_id))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid guest_id", "VAL_insertDB", NGM);
	}
	if(!(val_address_opt(address2))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid address", "VAL_insertDB", NGM);
	}
	if(!(val_age(age))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid age", "VAL_insertDB", NGM);
	}
	if(!(val_gender(gender))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid gender", "VAL_insertDB", NGM);
	}
	if(!(val_name(name))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid name", "VAL_insertDB", NGM);
	}
	if(!(val_department(department))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid department", "VAL_insertDB", NGM);
	}
	if(!(val_designation(designation))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid designation", "VAL_insertDB", NGM);
	}
}
public void VAL_insertDB_guestGH() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid req_id", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_guestid(guest_id))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid guest_id", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_address_opt(address2))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid address", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_age(age))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid age", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_gender(gender))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid gender", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_name(name))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid name", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_department(department))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid department", "VAL_insertDB_guestGH", NGM);
	}
	if(!(val_designation(designation))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid designation", "VAL_insertDB_guestGH", NGM);
	}
}
public void VAL_insertDB_guest_upload() throws Exception {
	
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid req_id", "VAL_insertDB_guest_upload", NGM);
	}
	if(!(val_guestid(guest_id))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid guest_id", "VAL_insertDB_guest_upload", NGM);
	}
	System.out.println("address2 "+address2);
	if(!(val_address_opt(address2))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid address", "VAL_insertDB_guest_upload", NGM);
	}
	if(!(val_age(age))){
		System.out.println("Age>>>"+age);
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid age", "VAL_insertDB_guest_upload", NGM);
	}
	if(!(val_gender(gender))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid gender", "VAL_insertDB_guest_upload", NGM);
	}
	if(!(val_name(name))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid name", "VAL_insertDB_guest_upload", NGM);
	}
	if(!(val_department(department))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid department", "VAL_insertDB_guest_upload", NGM);
	}
	if(!(val_designation(designation))){
		throw NGM.ins_utility.setException("d_guestdetails", "Invalid designation", "VAL_insertDB_guest_upload", NGM);
	}
}
}