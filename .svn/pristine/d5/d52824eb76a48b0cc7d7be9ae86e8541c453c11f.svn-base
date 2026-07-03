package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.db_validation;

public class d_servlet_task extends db_validation{
	public String serv_name,role_code,task_id;
	public int slno;
	ngm NGM;
	
	public d_servlet_task(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		
		System.out.println("Inside draft");
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into servlet_task values(?,?,?,?)");
		
		ps.setInt(1, slno);
		ps.setString(2, serv_name);
		ps.setString(3, task_id);
		ps.setString(4, role_code);
		System.out.println("sdsdsad"+slno+serv_name+task_id+role_code);
		System.out.println("PPPPPPPPPPPPPPPPPPP      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
		
	
	} 
	public boolean updateDB(String old_serv_name,String old_task_id) throws Exception {		
		boolean l_status=false;	
		VAL_insertDB();
		System.out.println("tttttttt"+old_serv_name+old_task_id+task_id);
		//validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update servlet_task set serv_name=?,task_id=?,role_code=? where serv_name=? and task_id=?");
		
		ps.setString(1, serv_name);
		ps.setString(2, task_id);
		ps.setString(3,role_code);
		
		ps.setString(4, old_serv_name);
		ps.setString(5, old_task_id);
		System.out.println("tttttttt"+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
		} 
	public boolean chknameDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from servlet_task where serv_name=? and task_id=? and role_code=?");
		ps.setString(1,serv_name);
		ps.setString(2,task_id);
		ps.setString(3,role_code);
		System.out.println("chknameDB     "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}

	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		VAL_insertDB();
		System.out.println(slno);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from servlet_task where serv_name=? and task_id=?");
		ps.setString(1, serv_name);
		ps.setString(2, task_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean deleteforupdateDB(String serv_name,String task_id) throws Exception
	{
		boolean l_status=false;
		VAL_insertDB();
		System.out.println(serv_name);
		System.out.println(task_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from servlet_task where serv_name=? and task_id=?");
		ps.setString(1, serv_name);
		ps.setString(2, task_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	
	
	
	/*public boolean selectupdateDB(String serv_name,String task_id) throws Exception 
	{
		System.out.println("serv_name"+serv_name+task_id);
		boolean l_status=false;
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("SELECT serv_name, task_id, role_code FROM servlet_task where serv_name=? and task_id=?");
		System.out.println("serv_name"+serv_name+task_id);
		ps.setString(1,serv_name);
		ps.setString(2,task_id);
		System.out.println("serv_name"+serv_name+task_id);
		ResultSet rs=ps.executeQuery();
		System.out.println("serv_name"+ps);
		
		while(rs.next()){
			System.out.println("serv_name1111"+serv_name+task_id);
			this.serv_name=rs.getString("serv_name");
			System.out.println("serv_name222222"+serv_name+task_id);
			this.task_id=rs.getString("task_id");
			System.out.println("serv_name33333"+serv_name+task_id+role_code);
			this.role_code=rs.getString("role_code");
			System.out.println("serv_name"+serv_name+task_id);
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	*/
//	--------------------------------------------------validation----------------------------------------------------
	public void VAL_insertDB() throws Exception 
	{
		 val_serv_name(serv_name);
		 val_task_id(task_id);
	}
	public boolean val_serv_name(String serv_name){
		if(serv_name!=null){
			if(!cLength(serv_name.toString(),1, 20)){
					return false;
				}
				if (!cAlphaSpecial(serv_name,"_")){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_task_id(String task_id){
		if(task_id!=null){
			if(!cLength(task_id.toString(),1, 20)){
					return false;
				}
				if (!cAlphaNumeric(task_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public void VAL_updateDB() throws Exception {
		
			}
	
	public void VAL_updateDBstatus() throws Exception {
		
			}
public void VAL_deleteDB() throws Exception {
		
	
	
			}
public void VAL_selectDB() throws Exception {
	
	
	
			}
}


