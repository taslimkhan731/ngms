package db; //author_nic_r_15/3/2012

import java.sql.*;
import java.text.SimpleDateFormat;

public class m_audit_login {
	public Integer trail_id;
	public String userid;
	public String login_time;
	public String logout_time;
	public String logout;
	public String client_ip;
	public String status;
	ngm NGM;

	public m_audit_login(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {	
		System.out.println("insertdb");
		boolean l_status=true;
		trail_id=NGM.ins_utility.getID(NGM.tr_conn, "trail_id");
		NGM.ins_utility.updateID(NGM.tr_conn, "trail_id", trail_id);
		validate("insertDB");
		PreparedStatement ps = NGM.tr_conn.prepareStatement("INSERT INTO m_audit_login VALUES(?,?,now(),null,?,?,?)");
		ps.setInt(1,trail_id);
		ps.setString(2,userid);
		//ps.setString(3,logout_time);
		ps.setString(3,client_ip);
		ps.setString(4,status);
		ps.setString(5,logout);
		System.out.println("audi login insert"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();	
		return l_status;		
	} 

	public boolean updateDB() throws Exception {		
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_audit_login set userid=?,logout_time=now(),client_ip=?,status=? where trail_id=?");	
		ps.setString(1,userid);		
		//ps.setString(2,logout_time);
		ps.setString(2,client_ip);
		ps.setString(3,status);
		ps.setInt(5,trail_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB_logout() throws Exception {
		boolean l_status=false;
		validate_key("updateDB_logout");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_audit_login set logout_time=now() where trail_id=?");
		ps.setInt(1,trail_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean concurrent_login(Long time) throws Exception {
		boolean l_status=false;
		String log = null;
		String log1 = null;
		String curr = null;
		String current = null;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_audit_login where userid=? order by trail_id desc limit 1");
		ps.setString(1,userid);	
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			curr = rs.getTimestamp("login_time") == null ? null : NGM.ins_utility.dispDate(rs.getTimestamp("login_time"),"dd/MM/yyyy");
			log = rs.getTimestamp("logout_time") == null ? null : NGM.ins_utility.dispDate(rs.getTimestamp("logout_time"),"dd/MM/yyyy");
			log1 = rs.getString("valid_upto");
		}
		rs.close();
		ps.close();
		
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("select now()");
		ResultSet rs1=ps1.executeQuery();
		while(rs1.next()){
			current = rs1.getTimestamp(1) == null ? null : NGM.ins_utility.dispDate(rs1.getTimestamp(1),"dd/MM/yyyy");
		}
		rs1.close();
		ps1.close();
		
		System.out.println("LogOut Time" + log + curr + current + log1 + time);
		if(log == null){
			if(time > (log1 == null ? 0 : Long.parseLong(log1))){
				l_status=false;
			}else if(!(current.equals(curr))){
				l_status=false;
			}else{
				l_status=true;
			}
		}else{
			l_status=false;
		}
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_audit_login where trail_id=?");
		ps.setInt(1, trail_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean selectDB() throws Exception {	
		validate_key("selectDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_audit_login where trail_id=?");
		ps.setInt(1, trail_id);
		System.out.println("psdfsdfsdf"+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			trail_id=rs.getInt("trail_id");
			userid=rs.getString("userid");
		    login_time=NGM.ins_utility.dispDate(rs.getTimestamp("login_time"),"dd/MM/yyyy hh:mm");
			logout_time=NGM.ins_utility.dispDate(rs.getTimestamp("logout_time"),"dd/MM/yyyy hh:mm");
			status=rs.getString("status");			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 

/**********************************************************Validation**************************************************/
	
	public void validate(String p_method) throws Exception {			
			if(userid!=null){
				if(!NGM.ins_val.cLength(userid, 3, 50))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid userid length", p_method, NGM);
				else if ((!NGM.ins_val.cAlphaNumeric(userid))&&(!NGM.ins_val.cEmail(userid)))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid userid", p_method, NGM);
				//Gopika
				/*else if (!NGM.ins_val.cEmail(userid))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid userid", p_method, NGM);
			*///Gopika
			}
			else
				throw NGM.ins_utility.setException("user_details", "Null userid", p_method, NGM);		 
			/*if(login_time!=null){
				if (!NGM.ins_val.cDate(login_time, "dmy"))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid login time", p_method, NGM);	
			}*/
			if(logout_time!=null){
				if (!NGM.ins_val.cDate(logout_time, "dmy"))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid logout time", p_method,NGM);	
			}
			/*if(client_ip!=null){
				if(!NGM.ins_val.cAlphaNumericSpecial(client_ip,".:"))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid client ip", p_method, NGM);	
			}*/
			if(status!=null){
				if (!NGM.ins_val.cAlphabet(status))
				throw NGM.ins_utility.setException("m_audit_login", "Invalid status", p_method, NGM);
			}
	}
	
	public void validate_key(String p_method) throws Exception {				
			if(trail_id!=null){
				if (!NGM.ins_val.cNumeric(trail_id.toString()))
					throw NGM.ins_utility.setException("m_audit_login", "Invalid trailId", p_method, NGM);
			}
			else
				throw NGM.ins_utility.setException("m_audit_login", "Null trailId", p_method, NGM);
	}

}//end class