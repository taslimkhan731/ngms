package db; 

import java.sql.*;

public class d_checkincheckout{
	
	public String checkin_id,pid,guest_id,roll_no,checkin_time,checkout_time;
	
	ngm NGM;
	
	public d_checkincheckout(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into checkin_checkout values(?,?,?,?,?,?)");
		ps.setString(1, checkin_id);
		ps.setInt(2,Integer.parseInt(pid));
		ps.setString(3, guest_id);
		ps.setString(4, roll_no);
		ps.setString(5, checkin_time);
		ps.setString(6, checkout_time);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update checkin_checkout set checkin_id=?,guest_id=?,roll_no=?,checkin_time=?,checkout_time=? where pid=?");
		ps.setString(1, checkin_id);
		ps.setString(3, guest_id);
		ps.setString(4, roll_no);
		ps.setString(5, checkin_time);
		ps.setString(6, checkout_time);
		ps.setInt(2,Integer.parseInt(pid));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from checkin_checkout where pid=?");
		ps.setInt(1,Integer.parseInt(pid));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from checkin_checkout where pid=?");
		ps.setString(1, pid);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.checkin_id=rs.getString("checkin_id");
			this.pid=rs.getString("pid");
			this.guest_id=rs.getString("guest_id");
			this.roll_no=rs.getString("roll_no");
			this.checkin_time=rs.getString("checkin_time");
			this.checkout_time=rs.getString("checkout_time");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from checkin_checkout where pid=?");
	ps.setInt(1,Integer.parseInt(pid));
	ResultSet rs=ps.executeQuery();
	boolean l_status=false;
	while(rs.next()){
			
		l_status=true;
	}
	rs.close();
	ps.close();
	return l_status;

}

//--------------------------------------------validation-----------------------------------------------------------------


public void validate(String p_method) throws Exception 
{
		
	 if(checkin_id!=null) {
		if(!NGM.ins_val.cLength(checkin_id.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(checkin_id.toString()))
			throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);	
	 }
	 else
		 throw NGM.ins_utility.setException("m_checkincheckout", "Null checkin checkout", p_method, NGM);
	
	 if(guest_id!=null) {
			if(!NGM.ins_val.cLength(guest_id.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(guest_id.toString()))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_checkincheckout", "Null checkin checkout", p_method, NGM);
		
	 if(roll_no!=null) {
			if(!NGM.ins_val.cLength(roll_no.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(roll_no.toString()))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_checkincheckout", "Null checkin checkout", p_method, NGM);
	 
	 if(checkin_time!=null) {
			if(!NGM.ins_val.cLength(checkin_time.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(checkin_time.toString()))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_checkincheckout", "Null checkin checkout", p_method, NGM);
	 
	 if(checkout_time!=null) {
			if(!NGM.ins_val.cLength(checkout_time.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(checkout_time.toString()))
				throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_checkincheckout", "Null checkin checkout", p_method, NGM);
		
	 
	  
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(pid!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "checkin_checkout", "pid", pid.toString(),NGM))
				throw NGM.ins_utility.setException("m_checkincheckout", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(pid.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_checkincheckout", "Invalid checkin checkout", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(pid.toString()))
			throw NGM.ins_utility.setException("m_checkincheckout", "Invalid  checkin checkout", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_checkincheckout", "Null checkin checkout", p_method, NGM);
		  
}

}