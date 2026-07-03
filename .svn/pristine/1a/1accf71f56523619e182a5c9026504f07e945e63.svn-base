package db;
import java.sql.*;
public class d_register {
	public String start_rollno,register_id,create_date,created_by,end_rollno,current_rollno;
	ngm NGM;
	public d_register(ngm p_ngm){
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into register values(?,?,?,?,?,?)");
		ps.setInt(1,Integer.parseInt(start_rollno));
		ps.setString(2,register_id);
		ps.setString(3,create_date);
		ps.setString(4,created_by);
		ps.setInt(5,Integer.parseInt(end_rollno));
		ps.setInt(6,Integer.parseInt(current_rollno));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean updateDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Update into register set register_id=?,create_date=?,created_by=?,end_rollno=?,current_rollno=? where start_rollno=?");
		ps.setString(1,register_id);
		ps.setString(2,create_date);
		ps.setString(3,created_by);
		ps.setInt(4,Integer.parseInt(end_rollno));
		ps.setInt(5,Integer.parseInt(current_rollno));
		ps.setInt(6,Integer.parseInt(start_rollno));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from register where start_rollno=?");
		ps.setInt(1, Integer.parseInt(start_rollno));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from register where start_rollno=?");
		ps.setInt(1,Integer.parseInt(start_rollno));
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.start_rollno=rs.getString("start_rollno");
			this.register_id=rs.getString("register_id");
			this.create_date=rs.getString("create_date");
			this.created_by=rs.getString("created_by");
			this.end_rollno=rs.getString("end_rollno");
			this.current_rollno=rs.getString("current_rollno");
				
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	public boolean checkregister()throws Exception
	{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from register where start_rollno=?");
		ps.setInt(1, Integer.parseInt(start_rollno));
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
				
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;

	}
	public void validate(String p_method) throws Exception 
	{
		 if(register_id!=null)
		 {
			if(!NGM.ins_val.cLength(register_id.toString(), 1, 100))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(register_id.toString()))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("register", "Null register details", p_method, NGM);
		
		 if(create_date!=null)
		 {
			if(!NGM.ins_val.cLength(create_date.toString(), 1, 100))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);
			else if (!NGM.ins_val.cDateTime(create_date.toString(),""))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("register", "Null register details", p_method, NGM);
		
		 if(created_by!=null)
		 {
			if(!NGM.ins_val.cLength(created_by.toString(), 1, 100))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(created_by.toString()))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("register", "Null register details", p_method, NGM);
		
		 if(end_rollno!=null)
		 {
			if(!NGM.ins_val.cLength(end_rollno.toString(), 1, 100))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(end_rollno.toString()))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("register", "Null register details", p_method, NGM);
		 
		 if(current_rollno!=null)
		 {
			if(!NGM.ins_val.cLength(current_rollno.toString(), 1, 100))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(current_rollno.toString()))
				throw NGM.ins_utility.setException("register", "Invalid register details", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("register", "Null register details", p_method, NGM);
	}
	public void validate_key(String p_method) throws Exception 
	{	
		if(start_rollno!=null){
		 		if(p_method=="insertDB"){
		 			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "register", "start_rollno", start_rollno.toString(),NGM))
		 				throw NGM.ins_utility.setException("register", " Already existing", p_method, NGM);
		 		}
		 		if(!NGM.ins_val.cLength(start_rollno.toString(), 1, 100))
		 			throw NGM.ins_utility.setException("register", "Invalid register length", p_method, NGM);
		 		else if (!NGM.ins_val.cNumeric(start_rollno.toString()))
		 			throw NGM.ins_utility.setException("register", "Invalid register code", p_method, NGM);
		 	}
		 	else
		 		throw NGM.ins_utility.setException("register", "Null register code", p_method, NGM);
	 }	
}