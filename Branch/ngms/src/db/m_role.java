package db;

import java.sql.*;

import common.db_validation;

public class m_role extends db_validation{
	public String role_code;
	public String role_name;
	public String hierarchy;
	ngm NGM;
	
	public m_role(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_role values(?,?,?)");
		ps.setString(1, role_code);
		ps.setString(2, role_name);
		ps.setString(3, hierarchy);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	} 
	
	public boolean updateDB() throws Exception {	
		boolean l_status=false;
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_role set role_name=?,hierarchy=? where role_code=?");
		ps.setString(1, role_name);	
		ps.setString(2, hierarchy);
		ps.setString(3, role_code);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{	
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_role where role_code=?");
		ps.setString(1, role_code);
		System.out.println("DDDDDDDDDDDDDDDDDDDD      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;				
	}

	public boolean selectDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from m_role where role_code=?");
		ps.setString(1, role_code);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			role_code=rs.getString("role_code");
			role_name=rs.getString("role_name");
			hierarchy=rs.getString("hierarchy");
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	public boolean checkrole()throws Exception
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from m_role where role_code=?");
		ps.setString(1, role_code);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
				
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;

	}
/***********************************************************Validation*****************************************************/	
	public void VAL_insertDB() throws Exception 
	{
		 val_role_code(role_code);
		 val_role_name(role_name);
		 val_hierarchy(hierarchy);
			 
	}


	
	
	public boolean val_role_code(String role_code){
		if(role_code!=null){
			if(!cLength(role_code.toString(), 0, 2)){
					return false;
				}
				if (!cNumeric(role_code)){
						return false;
				}
				return true;
		}else
			return false;
		
	}	
	public boolean val_role_name(String role_name){
		if(role_name!=null){
			if(!cLength(role_name.toString(), 0, 50)){
					return false;
				}
				if (!cAlphaSpecial(role_name,"_")){
						return false;
				}
				return true;
		}else
			return false;
		
	}	
	public boolean val_hierarchy(String hierarchy){
		if(hierarchy!=null){
			if(!cLength(hierarchy.toString(), 0, 3)){
					return false;
				}
				if (!cNumeric(hierarchy)){
						return false;
				}
				return true;
		}else
			return false;
		
	}	

}//end class