package db; 

import java.sql.*;

import common.db_validation;

public class m_user_details extends db_validation{
	public String userid;
	public String username;
	public String password;
	public String status;
	public String email_id;
	public String mob;
	ngm NGM;
	
	public m_user_details(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		VAL_insertDB();
	//	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_user_details values(?,?,?,?,?,?)");
		ps.setString(1, userid);
		ps.setString(2, username);
		ps.setString(3, password);
		ps.setString(4, status);
		ps.setString(5, email_id);
		ps.setString(6, mob); 
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception {
		boolean l_status=false;
		VAL_updateDB();
		String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_details set username=?,password=?,status=?,email_id=?,mob=? where userid=?");
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, status);
		ps.setString(4, email_id);
		ps.setString(5, mob);
		ps.setString(6, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean updateDB_R() throws Exception {
		boolean l_status=false;
		VAL_updateDB();
		String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_details set username=?,password=?,status=?,email_id=?,mob=? where userid=?");
		ps.setString(1, username);
		ps.setString(2, l_password);
		ps.setString(3, status);
		ps.setString(4, email_id);
		ps.setString(5, mob);
		ps.setString(6, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_user_details where userid=?");
		ps.setString(1, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_user_details where userid=?");
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			userid=rs.getString("userid");
			username=rs.getString("username");
			password=rs.getString("password");
			status=rs.getString("status");	
			email_id=rs.getString("email_id");
			mob=rs.getString("mob");
		l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	
	public boolean select_password_history() throws Exception {
		System.out.println("select_password_history................."+userid+password);
        boolean l_status=false;
		VAL_updateDB();
	//	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_user_password_history where user_id = ? order by create_date desc limit 3");
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			if(password != null){
				if(password.equals(rs.getString("user_password"))){
					l_status=true;
				}
			}
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public boolean select_password() throws Exception {
		System.out.println("select_password................."+userid+password);
        boolean l_status=false;
		VAL_updateDB();
	//	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_user_details where userid = ?");
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			userid=rs.getString("userid");
			if(password.equals(rs.getString("password"))){
				l_status=false;
			}else{
				password=rs.getString("password");
				l_status=true;
			}
		}
		rs.close();
		ps.close();
		return l_status;
	}

	public boolean insert_password_history() throws Exception {
		System.out.println("insert_password_history................."+userid+password);
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_user_password_history values(?,?,now())");
		ps.setString(1, password);
		ps.setString(2, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	}

	public boolean updateDB_password() throws Exception {
		System.out.println("updateDB_password................."+userid+password);
        boolean l_status=false;
		VAL_updateDB();
	//	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_details set password=? where userid=?");
		ps.setString(1, password);
		ps.setString(2, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
		


//----------------------------------------------------------------------------validation---------------------------------------------------------------------------------
public void VAL_insertDB() throws Exception 
{
	 val_user_id(userid);
	 val_username(username);
	 val_password(password);
	 val_email_id(email_id);
	 val_mobile_no(mob);
	
}
public void VAL_updateDB() throws Exception 
{
	 val_user_id(userid);
	 val_username(username);
	 val_password(password);
	 val_email_id(email_id);
	 val_mobile_no(mob);
	
}	
public void VAL_deleteDB() throws Exception 
{
	 val_user_id(userid);
		
}		
	
public boolean val_password(String password){
	if(password!=null){
		if(!cLength(password.toString(), 0, 100)){
				return false;
			}
			if (!cAlphaNumeric(password)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_username(String username){
	if(username!=null){
		if(!cLength(username.toString(), 0, 50)){
				return false;
			}
			if (!cAlphaNumeric(username)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_email_id(String email_id){
	if(email_id!=null){
		if(!cLength(email_id.toString(), 0, 50)){
				return false;
			}
			if (!cEmail(email_id)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_mobile_no(String mob){
	if(mob!=null){
		if(!cLength(mob.toString(), 0, 15)){
				return false;
			}
			if (!cNumeric(mob)){
					return false;
			}
			return true;
	}else
		return false;
	
}
}//end class