package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.db_validation;

public class d_otp_data extends db_validation {
	public String otp_id;
	public String refno;
	public String lastupdate;
	public String login_id;
	public String email_mobile;
	public String status;
	public String password;

	ngm NGM;

	public d_otp_data(ngm p_NGM){		
		NGM=p_NGM;
	}

	public boolean insertDB() throws Exception {	
		boolean l_status=false;	
	//	VAL_insertDB();
		String password1=NGM.ins_utility.getHash(password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into otp_data values(?,?,now(),?,?,?,?)");
		ps.setString(1, otp_id);
		ps.setString(2, refno);
		ps.setString(3, login_id);
		ps.setString(4, email_mobile);
		ps.setString(5, status);
		ps.setString(6, password1);

		System.out.println("password1password1password1password1:"+password1);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
		
	public boolean updateDB_status(String oldstatus) throws Exception {
		boolean l_status=false;
	//	VAL_updateDB_status();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update otp_data set status=?,lastupdate=now() where login_id=? and status=? ");
		ps.setString(1, status);
		ps.setString(2, login_id);
		ps.setString(3, oldstatus);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean selectDB() throws Exception {
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from otp_data where login_id=?");
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
	
	public boolean selectDB_verification_code(String otp_exp_time) throws Exception {
		//VAL_selectDB_verification_code();
		System.out.println("pass++1" + this.password);
		System.out.println("pass++2" + password);
		
		String password1=NGM.ins_utility.getHash(password);
		System.out.println("pass++3" + password);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select password from otp_data where password=? and login_id=? and refno=? and status=? and (lastupdate+interval '"+otp_exp_time+" hours')>now()");
		System.out.println("pspspspspspspspspsps"+ps);
		
		ps.setString(1, password1);
		ps.setString(2, login_id);
		ps.setString(3, refno);
		ps.setString(4, status);

		ResultSet rs=ps.executeQuery();
		System.out.println("rsrsrsrsrsrsrsrsrsrsrsrs"+ps);

		boolean l_status=false;
		while(rs.next()){
			password=rs.getString("password");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}


	public String selectDBrefno() throws Exception {
		//VAL_selectDBrefno();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select max(refno) from otp_data where login_id=?");
		ps.setString(1, login_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			refno=rs.getString("max");
			l_status=true;
		}
		rs.close();
		ps.close();
		return refno;
	} 
	public String selectDBmailormobile() throws Exception {
		//VAL_selectDBmailormobile();
		String mailormobile="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select email_mobile from otp_data where login_id=? and status=?");
		ps.setString(1, login_id);
		ps.setString(2, status);
		System.out.println("pssssssssssssssssssssssssssssssss  : "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			mailormobile=rs.getString("email_mobile");
		}
		rs.close();
		ps.close();
		return mailormobile;
	}
	
/***********************************************************Validation*****************************************************/

	public void VAL_insertDB() throws Exception {
		if(!(val_otp_id(otp_id)&&val_refno(refno)&&val_loginid(login_id)&&val_email_mobile(email_mobile)&&val_status(status)&&val_password(password))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		/*val_otp_id(otp_id);
		val_refno(refno);
		val_loginid(login_id);
		val_email_mobile(email_mobile);
		val_status(status);
		val_password(password);*/
	}
	
	public void VAL_updateDB_status() throws Exception {
		if(!(val_loginid(login_id)&&val_status(status))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		//val_loginid(login_id);
		//val_status(status);
	}

	public void VAL_selectDB() throws Exception {
		if(!(val_loginid(login_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_loginid(login_id);
	}

	public void VAL_selectDB_verification_code() throws Exception {
		if(!(val_refno(refno)&&val_loginid(login_id)&&val_status(status)&&val_password(password))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		/*val_refno(refno);
		val_loginid(login_id);
		val_status(status);
		val_password(password);*/
	}

	public void VAL_selectDBrefno() throws Exception {
		if(!(val_loginid(login_id))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_loginid(login_id);
	}
	
	public void VAL_selectDBmailormobile() throws Exception {
		if(!(val_loginid(login_id)&&val_status(status))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_loginid(login_id);
		//val_status(status);
	}

	public boolean val_otp_id(String otp_id){
		if(otp_id!=null){
			if(!cLength(otp_id.toString(), 1, 50)){
					return false;
				}
				if (!cNumeric(otp_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_refno(String refno){
		if(refno!=null){
			if(!cLength(refno.toString(), 1, 50)){
					return false;
				}
				if (!cNumeric(refno)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_email_mobile(String email_mobile){
		if(email_mobile!=null){
			if(!cLength(email_mobile.toString(), 1, 1)){
					return false;
				}
				if (!cAlphabet(email_mobile)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_status(String status){
		if(status!=null){
			if(!cLength(status.toString(), 3, 3)){
					return false;
				}
				if (!cAlphabet(status)){
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
				if (!cNumeric(password)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

}//end class
	
	

