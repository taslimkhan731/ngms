package db; 

import java.sql.*;




public class m_user_upload{
	public String userid;
	public String username;
	public String password;
	//public String role_code;
	public String status;
	//public String email_id;
	//public String mob;
	//public String dob;
	ngm NGM;
	
	public m_user_upload(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		validate_key("insertDB");
		validate("insertDB");
		String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_user_upload values(?,?,?,?,?)");
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_user_upload values(?,?,?,?,?,?,?,TO_TIMESTAMP(?,'YYYY-MM-DD'))");
		ps.setString(1, userid);
		ps.setString(2, username);
		ps.setString(3, l_password);
		//ps.setString(4, role_code);
		ps.setString(5, status);
		/*ps.setString(6, email_id);
		ps.setString(7, mob); 
		ps.setString(8, dob);*/
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception {
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_upload set username=?,password=?,role_code=?,status=? where userid=?");
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_upload set username=?,password=?,role_code=?,status=?,email_id=?,mob=?,dob=TO_TIMESTAMP(?,'YYYY-MM-DD') where userid=?");
		ps.setString(1, username);
		ps.setString(2, l_password);
		//ps.setString(3, role_code);
		ps.setString(4, status);
		/*ps.setString(5, email_id);
		ps.setString(6, mob);
		ps.setString(7, dob);*/
		ps.setString(5, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean updateDB_user() throws Exception {
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_upload set username=?,role_code=?,status=? where userid=?");
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_upload set username=?,role_code=?,status=?,email_id=?,mob=?,dob=TO_TIMESTAMP(?,'YYYY-MM-DD') where userid=?");
		ps.setString(1, username);
		//ps.setString(2, role_code);
		ps.setString(3, status);
		/*ps.setString(4, email_id);
		ps.setString(5, mob);
		ps.setString(6, dob);*/
		ps.setString(4, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean updateDB_password() throws Exception {
		System.out.println("updateDB_password.................");

		boolean l_status=false;
		validate_key("updateDB");
		//validate("updateDB");
		String l_password=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_user_upload set password=? where userid=?");
		ps.setString(1, l_password);
		ps.setString(2, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_user_upload where userid=?");
		ps.setString(1, userid);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception {
		validate_key("selectDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_user_upload where userid=?");
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			userid=rs.getString("userid");
			username=rs.getString("username");
			password=rs.getString("password");
			//role_code=rs.getString("role_code");
			status=rs.getString("status");	
			/*email_id=rs.getString("email_id");
			mob=rs.getString("mob");
			dob=rs.getString("dob");*/

			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	

	public String selectDBusername(String userid) throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_user_upload where userid=?");
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			username=rs.getString("username");
		}
		rs.close();
		ps.close();
		return username;
	} 	

	//getRole
//	public String getRole(String userid) throws Exception {
//		PreparedStatement ps=NGM.tr_conn.prepareStatement("select role_code from m_user_upload where userid=?");
//		ps.setString(1, userid);
//		ResultSet rs=ps.executeQuery();
//		while(rs.next()){
//			//role_code=rs.getString("role_code");
//		}
//		rs.close();
//		ps.close();
//		//return role_code;
//	} 	
	
/***********************************************************Validation*****************************************************/
	
	public void validate(String p_method) throws Exception {
			 if(username!=null){
				if(!NGM.ins_val.cLength(username, 3, 50))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid username length", p_method, NGM);
				else if (!NGM.ins_val.cAlphaNumericSpecial(username," ."))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid username", p_method, NGM);	
			}
			 else
					throw NGM.ins_utility.setException("m_user_upload", "Null username", p_method, NGM);
			if(p_method.equals("insertDB")) {
				if(password!=null){
					if(!NGM.ins_val.cLength(password, 6, 20))
						throw NGM.ins_utility.setException("m_user_upload", "Invalid password length", p_method, NGM);
					else if (!NGM.ins_val.cPassword(password))
						throw NGM.ins_utility.setException("m_user_upload", "Invalid password", p_method,NGM);
				}	
				else
					throw NGM.ins_utility.setException("m_user_upload", "Null password", p_method, NGM);
			}
			//if(role_code!=null){
				//if(!NGM.ins_val.cLength(role_code.toString(), 2, 2))
					//throw NGM.ins_utility.setException("m_user_upload", "Invalid organisation length", p_method, NGM);
				//else if (!NGM.ins_val.cNumeric(role_code.toString()))
				//throw NGM.ins_utility.setException("m_user_upload", "Invalid organisation code", p_method, NGM);
			//}	
			 else
					throw NGM.ins_utility.setException("m_user_upload", "Null organisationcode", p_method, NGM);
			if(status!=null) {
					if(!NGM.ins_val.cLength(status, 1, 1))
						throw NGM.ins_utility.setException("m_user_upload", "Invalid status length", p_method, NGM);					
					else if (!NGM.ins_val.cAlphabet(status))
						throw NGM.ins_utility.setException("m_user_upload", "Invalid status", p_method, NGM);
			}	
			 else
					throw NGM.ins_utility.setException("m_user_upload", "Null status", p_method, NGM);
			
			/*if(email_id!=null){
				if(!NGM.ins_val.cLength(email_id, 0, 50))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid email_id length", p_method, NGM);
				else if (!NGM.ins_val.cEmail(email_id))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid email_id", p_method, NGM);	
			}

			if(mob!=null){
				if(!NGM.ins_val.cLength(mob, 0, 15))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid mob length", p_method, NGM);
				else if (!NGM.ins_val.cPhoneMobile(mob))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid mob", p_method,NGM);
			}	
			
			if(dob!=null){
				if(!NGM.ins_val.cLength(dob.toString(), 0, 15))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid dob length", p_method, NGM);
				else if (!NGM.ins_val.cDate(dob.toString(),"dmy"))
					throw NGM.ins_utility.setException("m_user_upload", "Invalid dob", p_method,NGM);
			}*/
	}
	
	public void validate_key(String p_method) throws Exception {	
		if(userid!=null){
			if(p_method=="insertDB"){
				if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "m_user_upload", "userid", userid,NGM))
					throw NGM.ins_utility.setException("m_user_upload", " Already existing", p_method, NGM);
			}
			if(!NGM.ins_val.cLength(userid, 3, 15))
				throw NGM.ins_utility.setException("m_user_upload", "Invalid userid length", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(userid))
				throw NGM.ins_utility.setException("m_user_upload", "Invalid userid", p_method, NGM);
		}
		else
			throw NGM.ins_utility.setException("m_user_upload", "Null userid", p_method, NGM);
	}

}//end class