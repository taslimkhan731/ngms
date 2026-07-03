package db; 
import java.sql.*;
import java.util.ArrayList;

import common.db_validation;
public class m_uservsgh extends db_validation{
	public String gh_id,userid;
	public  ArrayList<String> rolelist = new ArrayList<String>();
		
	ngm NGM;
	
	
	public m_uservsgh(ngm p_ngm){		
		NGM=p_ngm;
	}
//	public boolean selectDB() throws Exception {
//		//validate_key("selectDB");
//		
//		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from uservsrole where gh_id=?");
//		ps.setString(1, NGM.ins_sess.user_id);
//		ResultSet rs=ps.executeQuery();
//		boolean l_status=false;
//		while(rs.next()){
//			//gh_id=rs.getString("gh_id");
//			rolelist.add(rs.getString("gh_name"));
//			l_status=true;
//		}
//		System.out.println("rolelistadd    "+ps);
//		rs.close();
//		ps.close();
//		return l_status;
//	} 	
//	public boolean selectDBpublic() throws Exception {
//		//validate_key("selectDB");
//		
//		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from uservsrole where gh_id=?");
//		ps.setString(1, "public");
//		ResultSet rs=ps.executeQuery();
//		boolean l_status=false;
//		while(rs.next()){
//			//gh_id=rs.getString("gh_id");
//			rolelist.add(rs.getString("gh_name"));
//			l_status=true;
//		}
//		System.out.println("rolelistadd    "+ps);
//		rs.close();
//		ps.close();
//		return l_status;
//	} 	
	public boolean insertDB() throws Exception
	{	
		boolean l_status=false;	
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into uservsgh values(?,?)");
		ps.setString(1, userid);
		ps.setString(2, gh_id);
		System.out.println("pssssssssssssssssss!!!!!!!!!!!!!!!!!!            "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		VAL_deleteDB();     
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from uservsgh where userid=?");
		pss.setString(1, userid);
		System.out.println("DELETE       "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
//	public boolean updateDB() throws Exception
//	{	
//		boolean l_status=false;	
//		VAL_updateDB();
//		PreparedStatement ps=NGM.tr_conn.prepareStatement("update uservsrole set gh_name=? where gh_id=?");
//		ps.setString(1, gh_name);
//		ps.setString(2, gh_id);
//		l_status=NGM.ins_utility.executeDB(ps);
//		ps.close();
//		return l_status;		
//	} 
//	public boolean deleteDB() throws Exception {		
//		boolean l_status=false;	
//		VAL_deleteDB();     
//	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from uservsrole where gh_id=?");
//		pss.setString(1, gh_id);
//		System.out.println("DELETE       "+pss);
//		l_status=NGM.ins_utility.executeDB(pss);
//		pss.close();
//		return l_status;		
//	} 
//	
//	

/***********************************************************Validation*****************************************************/
public void VAL_insertDB() throws Exception 
{
	 val_ghid(gh_id);
	 val_userid(userid);
}
public void VAL_updateDB() throws Exception 
{
	 val_ghid(gh_id);
	 val_userid(userid);
}
public void VAL_deleteDB() throws Exception 
{
	 val_user_id(gh_id);
}
public boolean val_userid(String userid){
	if(userid!=null){
		if(!cLength(userid.toString(), 0, 25)){
				return false;
			}
			if (!cNumeric(userid)){
					return false;
			}
			return true;
	}else
		return false;
	
}	
}	