package db; 
import java.sql.*;
import java.util.ArrayList;

import common.db_validation;
public class m_uservsrole extends db_validation{
	public String userid,role_code;
	public  ArrayList<String> rolelist = new ArrayList<String>();
		
	ngm NGM;
	
	
	public m_uservsrole(ngm p_ngm){		
		NGM=p_ngm;
	}
	

	public boolean selectDB() throws Exception {
		//validate_key("selectDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from uservsrole where userid=?");
		ps.setString(1, NGM.ins_sess.user_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			rolelist.add(rs.getString("role_code"));
			l_status=true;
		}
		System.out.println("rolelistadd   GOPIKA "+ps);
		rs.close();
		ps.close();
		return l_status;
	} 	
	
	public boolean insertDB() throws Exception
	{	
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into uservsrole values(?,?)");
		ps.setString(1, userid);
		ps.setString(2, role_code);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{	
		boolean l_status=false;	
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update uservsrole set role_code=? where userid=?");
		ps.setString(1, role_code);
		ps.setString(2, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		VAL_deleteDB();     
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from uservsrole where userid=?");
		pss.setString(1, userid);
		System.out.println("DELETE       "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean selectDBpublic() throws Exception {
		//validate_key("selectDB");
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from uservsrole where userid=?");
		ps.setString(1, "public");
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			//userid=rs.getString("userid");
			rolelist.add(rs.getString("role_code"));
			l_status=true;
		}
		System.out.println("rolelistadd    "+ps);
		rs.close();
		ps.close();
		return l_status;
	}
	

/***********************************************************Validation*****************************************************/
public void VAL_insertDB() throws Exception 
{
	 val_user_id(userid);
	 val_role_code(role_code);
}
public void VAL_updateDB() throws Exception 
{
	 val_user_id(userid);
	 val_role_code(role_code);
}
public void VAL_deleteDB() throws Exception 
{
	 val_user_id(userid);
}
public boolean val_role_code(String role_code){
	if(role_code!=null){
		if(!cLength(role_code.toString(), 0, 2)){
				return false;
			}
			if (!cNumeric(role_code)){
					return false;
			}
			return true;
	}else
		return false;
	
}	
}	