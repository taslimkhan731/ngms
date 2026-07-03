package db; 

import java.sql.*;

public class d_floorno
{
	public Integer floorno;
	public String floorname;
	ngm NGM;
	
	public d_floorno(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception
	{		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into floorno values(?,?)");
		ps.setInt(1, floorno);
		ps.setString(2, floorname);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception 
	{
		boolean l_status=false;
		
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update floorno set floorname=? where floorno=?");
		ps.setString(1, floorname);
		ps.setInt(2, floorno);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from floorno where floorno=?");
		ps.setInt(1, floorno);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean selectDB() throws Exception 
	{
		validate_key("selectDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from floorno where floorno=?");
		ps.setInt(1, this.floorno);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next())
		{
			this.floorname = rs.getString("floorname");
			this.floorno = rs.getInt("floorno");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkfloor() throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from floorno where floorno=?");
		ps.setInt(1, floorno);
		ResultSet rs=ps.executeQuery();
		boolean i_status=false;
		while(rs.next())
		{
			i_status=true;
		}
		rs.close();
		ps.close();
		return i_status;
	}
	
	public void validate(String p_method) throws Exception 
	{
		 if(floorname!=null)
		 {
			if(!NGM.ins_val.cLength(floorname.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_floorno", "Invalid form details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(floorname.toString()))
				throw NGM.ins_utility.setException("m_floorno", "Invalid form details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_floorno", "Null form details", p_method, NGM);
		
		 if(floorname!=null)
		 {
			 if(!NGM.ins_val.cLength(floorname.toString(), 1, 1000))
				 throw NGM.ins_utility.setException("m_floorno", "Invalid floor length", p_method, NGM);
			 else if (!NGM.ins_val.cAlphaNumeric(floorname.toString()))
				 throw NGM.ins_utility.setException("m_floorno", "Invalid floor", p_method,NGM);
		 }	
		 else{
			 NGM.ins_writer.write("Message: Department code is null ");
			 throw NGM.ins_utility.setException("m_floorno", "Null floor", p_method, NGM);
		 }
	}

	public void validate_key(String p_method) throws Exception 
	{	
		if(floorno!=null){
			if(p_method=="insertDB"){
				if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "floorno", "floorno", floorno.toString(),NGM))
					throw NGM.ins_utility.setException("m_floorno", " Already existing", p_method, NGM);
			}
			if(!NGM.ins_val.cLength(floorno.toString(), 1, 1000))
				throw NGM.ins_utility.setException("m_floorno", "Invalid floor code length", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(floorno.toString()))
				throw NGM.ins_utility.setException("m_floorno", "Invalid floor code", p_method, NGM);
		}
		else
			throw NGM.ins_utility.setException("m_floorno", "Null floor code", p_method, NGM);
		
	}
			
}