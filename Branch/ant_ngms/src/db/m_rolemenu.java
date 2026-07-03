package db;

import java.sql.*;

import common.db_validation;

public class m_rolemenu extends db_validation{
	public String role_code;
	public int menu_id;
	
	ngm NGM;
	
	public m_rolemenu(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_role_menu values(?,?)");
		ps.setInt(1, menu_id);
		ps.setString(2, role_code);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	} 
	
	public boolean updateDB() throws Exception {	
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_role_menu set role_code=? where menu_id=?");
		ps.setString(1, role_code);
		ps.setInt(2, menu_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{	
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_role_menu where menu_id=?");
		ps.setInt(1, menu_id);
		System.out.println("DDDDDDDDDDDDDDDDDDDD      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;				
	}

	public boolean selectDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from m_role_menu where menu_id=?");
		ps.setInt(1, menu_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			menu_id=rs.getInt("menu_id");
			role_code=rs.getString("role_code");
			
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
			if(!(val_role_code(role_code))){
				throw NGM.ins_utility.setException("rolemenu", "Invalid role menu", "VAL_insertDB", NGM);
			}
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
	
}//end class