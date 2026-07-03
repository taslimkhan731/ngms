package db; 

import java.sql.*;

import common.db_validation;

public class d_publicregister  extends db_validation 
{
	public String login_id,name,dob,mobile,email,state,password,is_email_verified,is_mobile_verified,is_active,lastupdate,path,docType;
	ngm NGM;
	public d_publicregister(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{	
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pub_register values(?,?,?,?,?,?,?,?,?,now(),?,?,?)");
		ps.setString(1, login_id);
		ps.setString(2, name);
		ps.setString(3, mobile);
		ps.setString(4, email);
		ps.setString(5, state);
		ps.setString(6, password);
		ps.setString(7, "N");
		ps.setString(8, "N");
		ps.setString(9, "N");
		ps.setString(10, dob);
		ps.setString(11, null);
		ps.setString(12, null);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pub_register set name=?,mobile=?,email=?,state=?,is_email_verified=?,is_mobile_verified=?,is_active=?,dob=?,lastupdate=now() where login_id=?");
		ps.setString(1, name);
		ps.setString(2, mobile);
		ps.setString(3, email);
		ps.setString(4, state);
		ps.setString(5, "N");
		ps.setString(6, "N");
		ps.setString(7, "N");
		ps.setString(8, dob);
		ps.setString(9, login_id);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean updateDB_Y() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pub_register set name=?,mobile=?,email=?,state=?,dob=?,lastupdate=now() where login_id=?");
		ps.setString(1, name);
		ps.setString(2, mobile);
		ps.setString(3, email);
		ps.setString(4, state);
		ps.setString(5, dob);
		ps.setString(6, login_id);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from pub_register where login_id=?");
		ps.setString(1, login_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{System.out.println("ps     sdfsdfssekect db " );
	VAL_selectDB();
	System.out.println("ps     sdfsdfssekect db " );
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=?");
	ps.setString(1, login_id);
	System.out.println("ps      "+ps);
	ResultSet rs=ps.executeQuery();

	boolean l_status=false;
	while(rs.next()){
		System.out.println("enteeeddddddddddd     ");

		this.login_id=rs.getString("login_id");
		this.name=rs.getString("name");
		this.dob=rs.getString("dob");
		this.mobile=rs.getString("mobile");
		this.email=rs.getString("email");
		this.state=rs.getString("state");
		this.password=rs.getString("password");
		this.is_email_verified=rs.getString("is_email_verified");
		this.is_mobile_verified=rs.getString("is_mobile_verified");
		this.is_active=rs.getString("is_active");
		this.lastupdate=rs.getString("lastupdate");
		l_status=true;
		System.out.println("ps 12     "+ps);
	}
	rs.close();
	ps.close();
	return l_status;
	} 

	public boolean select_mob_email()throws Exception{

		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=? and mobile=? and email=?");
		ps.setString(1, login_id);
		ps.setString(2, mobile);
		ps.setString(3, email);
		System.out.println("mobile"+ps);

		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			l_status = true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	public boolean updateDBactive(String parameter) throws Exception
	{
		boolean l_status=false;
		VAL_updateDBactive();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pub_register set is_active=?,lastupdate=now(),"+parameter+"='Y' where login_id=?");
		ps.setString(1, is_active);
		ps.setString(2, login_id);

		System.out.println("psssssssssssssss mail/mob sssssssssssssssss  : "+ps);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean checkdemand()throws Exception
	{
		VAL_checkdemand();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=?");
		ps.setString(1, login_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){

			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;

	}
	public boolean selectDBisValid() throws Exception 
	{
		VAL_selectDBisValid();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=? and mobile=? and email=? and dob=?");
		ps.setString(1, login_id);
		ps.setString(2, mobile);
		ps.setString(3, email);
		ps.setString(4, dob);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.login_id=rs.getString("login_id");
			this.name=rs.getString("name");
			this.dob=rs.getString("dob");
			this.mobile=rs.getString("mobile");
			this.email=rs.getString("email");
			this.state=rs.getString("state");
			this.password=rs.getString("password");
			this.is_email_verified=rs.getString("is_email_verified");
			this.is_mobile_verified=rs.getString("is_mobile_verified");
			this.is_active=rs.getString("is_active");
			this.lastupdate=rs.getString("lastupdate");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 

	public boolean selectUserValid() throws Exception 
	{
		VAL_selectUserValid();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=? and dob=?");
		ps.setString(1, login_id);
		ps.setString(2, dob);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.login_id=rs.getString("login_id");
			this.name=rs.getString("name");
			this.dob=rs.getString("dob");
			this.mobile=rs.getString("mobile");
			this.email=rs.getString("email");
			this.state=rs.getString("state");
			this.password=rs.getString("password");
			this.is_email_verified=rs.getString("is_email_verified");
			this.is_mobile_verified=rs.getString("is_mobile_verified");
			this.is_active=rs.getString("is_active");
			this.lastupdate=rs.getString("lastupdate");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 

	public boolean select_password_history() throws Exception {
		System.out.println("select_password_history................."+login_id+password);
		boolean l_status=false;
		VAL_updateDB();
		//	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_user_password_history where user_id = ? order by create_date desc limit 3");
		ps.setString(1, login_id);
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
		System.out.println("select_password................."+login_id+password);
		boolean l_status=false;
		VAL_updateDB();
		//	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id = ?");
		ps.setString(1, login_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			login_id=rs.getString("login_id");
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
		System.out.println("insert_password_history................."+login_id+password);
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_user_password_history values(?,?,now())");
		ps.setString(1, password);
		ps.setString(2, login_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	}

	public boolean updateDB_password() throws Exception {
		System.out.println("updateDB_password.................");
		boolean l_status=false;
		VAL_updateDB_password();
		//	/	String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pub_register set password=? where login_id=?");
		ps.setString(1, password);
		ps.setString(2, login_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	//-------------------------------------------validation------------------------------------------------------------------

	public void VAL_insertDB() throws Exception {
		val_loginid(login_id);
		val_name(name);
		val_mobile(mobile);
		val_email(email);
		val_state(state);
		val_password(password);
		val_is_email_verified(is_email_verified);
		val_is_mobile_verified(is_mobile_verified);
		val_is_active(is_active);
		val_dob(dob);
	}

	public void VAL_updateDB() throws Exception {
		val_loginid(login_id);
		val_name(name);
		val_mobile(mobile);
		val_email(email);
		val_state(state);
		val_is_mobile_verified(is_mobile_verified);
		val_is_email_verified(is_email_verified);
		val_is_active(is_active);
		val_dob(dob);
	}

	public void VAL_selectDB() throws Exception {
		val_loginid(login_id);
	}

	public void VAL_deleteDB() throws Exception {
		val_loginid(login_id);
	}

	public void VAL_updateDBactive() throws Exception {
		val_loginid(login_id);
		val_is_active(is_active);
	}

	public void VAL_checkdemand() throws Exception {
		val_loginid(login_id);
	}

	public void VAL_selectDBisValid() throws Exception {
		val_loginid(login_id);
		val_mobile(mobile);
		val_email(email);
		val_dob(dob);	
	}

	public void VAL_selectUserValid() throws Exception {
		val_loginid(login_id);
		val_dob(dob);	
	}

	public void VAL_updateDB_password() throws Exception {
		val_loginid(login_id);
		val_password(password);
	}

	public boolean val_name(String name){
		if(name!=null){
			if(!cLength(name.toString(), 3, 100)){
				return false;
			}
			if (!cAlphaSpecial(name, " ")){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_mobile(String mobile){
		if(mobile!=null){
			if(!cLength(mobile.toString(), 10, 10)){
				return false;
			}
			if (!cNumeric(mobile)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_email(String email){
		if(email!=null){
			if(!cLength(email.toString(), 10, 50)){
				return false;
			}
			if (!cEmail(email)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_state(String state){
		if(state!=null){
			if(!cLength(state.toString(), 3, 50)){
				return false;
			}
			if (!cAlphaSpecial(state," ")){
				return false;
			}
			return true;
		}else
			return false;

	}

	public boolean val_password(String password){
		if(password!=null){
			if(!cLength(password.toString(), 3, 100)){
				return false;
			}
			if (!cPassword(password)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_is_email_verified(String is_email_verified){
		if(is_email_verified!=null){
			if(!cLength(is_email_verified.toString(), 1, 1)){
				return false;
			}
			if (!cAlphaNumeric(is_email_verified)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_is_mobile_verified(String is_email_verified){
		if(is_email_verified!=null){
			if(!cLength(is_email_verified.toString(), 1, 1)){
				return false;
			}
			if (!cAlphaNumeric(is_email_verified)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_is_active(String is_email_verified){
		if(is_email_verified!=null){
			if(!cLength(is_email_verified.toString(), 1, 1)){
				return false;
			}
			if (!cAlphaNumeric(is_email_verified)){
				return false;
			}
			return true;
		}else
			return false;

	}
	public boolean val_dob(String dob){
		if(dob!=null){
			if(!cLength(dob.toString(), 1, 10)){
				return false;
			}
			if (!cDate(dob,"ymd")){
				return false;
			}
			return true;
		}else
			return false;

	}
	
	public boolean select_mob()throws Exception{

		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_register where login_id=? and mobile=?");
		ps.setString(1, login_id);
		ps.setString(2, mobile);
		System.out.println("mobile"+ps);

		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			l_status = true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public void updateDB_file() throws Exception
	{
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pub_register set name=?,mobile=?,email=?,state=?,dob=?,lastupdate=now(),file_path=?,doc_type=? where login_id=?");
		ps.setString(1, name);
		ps.setString(2, mobile);
		ps.setString(3, email);
		ps.setString(4, state);
		ps.setString(5, dob);
		ps.setString(6, path);
		ps.setString(7, docType);
		ps.setString(8, login_id);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
	}

	public String getFilePath(String req_id) throws SQLException {
		String l_status= null;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from adminr.user where user_id=?");
		ps.setString(1, req_id);
		System.out.println("@@@"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			l_status = rs.getString("doc_path");
		}
		rs.close();
		ps.close();	 
		return l_status;
	}
	
}
