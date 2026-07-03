package db; 

import java.sql.*;

public class d_pendingbills{
	
	public String bill_id,bill_date,status;
	
	ngm NGM;
	
	public d_pendingbills(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into pending_bills values(?,?,?)");
		ps.setInt(1,Integer.parseInt(bill_id));
		ps.setString(2, bill_date );
		ps.setString(3, status);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update pending_bills set bill_date=?,status=? where bill_id=?");
		ps.setString(1, bill_date);
		ps.setString(2, status);
		ps.setInt(3,Integer.parseInt(bill_id));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from pending_bills where bill_id=?");
		ps.setInt(1,Integer.parseInt(bill_id));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pending_bills where bill_id=?");
		ps.setString(1, bill_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.bill_id=rs.getString("bill_id");
			this.bill_date=rs.getString("bill_date");
			this.status=rs.getString("status");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pending_bills where bill_id=?");
	ps.setInt(1,Integer.parseInt(bill_id));
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
	if(bill_date!=null) {
		if(!NGM.ins_val.cLength(bill_date.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_pendingbills", "Invalid pendingbills", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(bill_date.toString()))
			throw NGM.ins_utility.setException("m_pendingbills", "Invalid pendingbills", p_method, NGM);	
	 }
	 else
			throw NGM.ins_utility.setException("m_pendingbills", "Null pendingbills", p_method, NGM);
	
	 if(status!=null) {
		if(!NGM.ins_val.cLength(status.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_pendingbills", "Invalid pendingbills", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(status.toString()))
			throw NGM.ins_utility.setException("m_pendingbills", "Invalid pendingbills", p_method, NGM);	
	 }
	 else
		 throw NGM.ins_utility.setException("m_pendingbills", "Null pendingbills", p_method, NGM);
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(bill_id!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "pending_bills", "bill_id", bill_id.toString(),NGM))
				throw NGM.ins_utility.setException("m_pendingbills", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(bill_id.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_pendingbills", "Invalid pendingbills length", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(bill_id.toString()))
			throw NGM.ins_utility.setException("m_pendingbills", "Invalid  pendingbills", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_pendingbills", "Null pendingbills", p_method, NGM);
		  
}

}