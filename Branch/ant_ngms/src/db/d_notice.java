package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class d_notice {
	public String subject,desc,from,tdate,id1;
	public Integer id;
	ngm NGM;
	public d_notice(ngm p_ngm){
		NGM=p_ngm;
	}
	
	private Integer getMaxId() throws SQLException{
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select max(id) from m_notice");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			this.id=rs.getInt(1);
		}
		rs.close();
		ps.close();
		return id;
	}
	
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		Integer maxId = getMaxId() + 1;
		//validate("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into m_notice values(?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'))");
		ps.setInt(1,maxId);
		ps.setString(2,subject);
		ps.setString(3,desc);
		ps.setString(4,from);
		ps.setString(5,tdate);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception {		
		boolean l_status=false;
		id  = Integer.parseInt(id1);
		//validate("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_notice set subject=?,description=?,from_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss') where id=?");
		ps.setString(1,subject);
		ps.setString(2,desc);
		ps.setString(3,from);
		ps.setString(4,tdate);
		ps.setInt(5,id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		id  = Integer.parseInt(id1);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM m_notice WHERE id=?");
		ps.setInt(1,id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 	
}
