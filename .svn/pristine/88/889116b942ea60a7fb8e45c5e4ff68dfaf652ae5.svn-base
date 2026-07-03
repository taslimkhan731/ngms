package db; 

import java.sql.*;

public class d_chequedetails{
	
	public String cheque_id,cheque_no,cheque_date,bank_name;
	
	ngm NGM;
	
	public d_chequedetails(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into cheque_details values(?,?,?,?)");
		ps.setInt(1,Integer.parseInt(cheque_id));
		ps.setString(2, cheque_no);
		ps.setString(3, cheque_date);
		ps.setString(4, bank_name);
		
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update cheque_details set cheque_no=?,chaque_date=?,bank_name=?  where cheque_id=?");
		ps.setString(1, cheque_no);
		ps.setString(2, cheque_date);
		ps.setString(3, bank_name);
		ps.setInt(4,Integer.parseInt(cheque_id));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from cheque_details where cheque_id=?");
		ps.setInt(1,Integer.parseInt(cheque_id));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from cheque_details where cheque_id=?");
		ps.setString(1, cheque_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.cheque_id=rs.getString("cheque_id");
			this.cheque_no=rs.getString("cheque_no");
			this.cheque_date=rs.getString("cheque_date");
			this.bank_name=rs.getString("bank_name");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from cheque_details where cheque_id=?");
	ps.setInt(1,Integer.parseInt(cheque_id));
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
		
	 if(cheque_no!=null) {
		if(!NGM.ins_val.cLength(cheque_no.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque details", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(cheque_no.toString()))
			throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque details", p_method, NGM);	
	 }
	 else
		 throw NGM.ins_utility.setException("m_chequedetails", "Null cheque details", p_method, NGM);
	
	 if(cheque_date!=null) {
			if(!NGM.ins_val.cLength(cheque_date.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(cheque_date.toString()))
				throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque details", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_chequedetails", "Null cheque details", p_method, NGM);
		
	 if(bank_name!=null) {
			if(!NGM.ins_val.cLength(bank_name.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(bank_name.toString()))
				throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque detailst", p_method, NGM);	
		 }
		 else
			 throw NGM.ins_utility.setException("m_chequedetails", "Null cheque details", p_method, NGM);
	 
	
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(cheque_id!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "cheque_details", "cheque_id", cheque_id.toString(),NGM))
				throw NGM.ins_utility.setException("m_chequedetails", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(cheque_id.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_chequedetails", "Invalid cheque details", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(cheque_id.toString()))
			throw NGM.ins_utility.setException("m_chequedetails", "Invalid  cheque details", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_chequedetails", "Null cheque details", p_method, NGM);
		  
}

}