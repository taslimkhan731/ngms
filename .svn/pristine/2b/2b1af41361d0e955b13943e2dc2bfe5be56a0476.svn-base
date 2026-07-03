package db; 

import java.sql.*;

public class d_statelist{
	
	public String state_code,state_name;
	
	ngm NGM;
	
	public d_statelist(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into statelist values(?,?)");
		ps.setString(1, state_code);
		ps.setString(2, state_name );
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update statelist set state_name=? where state_code=?");
		ps.setString(4, state_name);
		ps.setString(5, state_code);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from statelist where state_code=?");
		ps.setString(1,state_code);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from statelist where state_code=?");
		ps.setString(1, state_code);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.state_code=rs.getString("state_code");
			this.state_name=rs.getString("state_name");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from statelist where state_code=?");
	ps.setString(1,state_code);
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
	if(state_name!=null) {
		if(!NGM.ins_val.cLength(state_name.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_statelist", "Invalid statelist", p_method, NGM);
		else if (!NGM.ins_val.cAlphaNumeric(state_name.toString()))
			throw NGM.ins_utility.setException("m_statelist", "Invalid statelist", p_method, NGM);	
	 }
	 else
			throw NGM.ins_utility.setException("m_statelist", "Null statelist", p_method, NGM);
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(state_code!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "statelist", "state_code", state_code.toString(),NGM))
				throw NGM.ins_utility.setException("m_statelist", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(state_code.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_statelist", "Invalid statelist length", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(state_code.toString()))
			throw NGM.ins_utility.setException("m_statelist", "Invalid  statelist", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_statelist", "Null statelist", p_method, NGM);
		  
}

}