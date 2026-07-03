package db;

import java.sql.*;

import common.db_validation;

public class m_quota extends db_validation{
	public String quota_type,quota_desc;
	ngm NGM;
	
	public m_quota(ngm p_ngm){		
		NGM=p_ngm;
	}
	public boolean insertDB() throws Exception {
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into quota values(?,?)");
		ps.setString(1, quota_type);
		ps.setString(2, quota_desc);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	} 
	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from quota where quota_type=?");
		ps.setString(1, quota_type);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public boolean updateDB() throws Exception {	
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update quota set quota_desc=? where quota_type=?");
		ps.setString(1, quota_desc);	
		ps.setString(2, quota_type);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{	
		boolean l_status=false;
		 val_quotatype(quota_type);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from quota where quota_type=?");
		ps.setString(1, quota_type);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from quota where quota_type=?");
		ps.setString(1, quota_type);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			quota_desc=rs.getString("quota_desc");
			
		}
		rs.close();
		ps.close();
		return l_status;
	} 	

/***********************************************************Validation*****************************************************/	
	public void VAL_insertDB() throws Exception 
	{
		 val_quotatype(quota_type);
		 val_quotadesc(quota_desc);
	}

	public void VAL_updateDB() throws Exception 
	{
		 val_quotatype(quota_type);
		 val_quotadesc(quota_desc);
	}

	
	
	public boolean val_quotatype(String quota_type){
		if(quota_type!=null){
			if(!cLength(quota_type.toString(), 0, 10)){
					return false;
				}
				if (!cAlphaNumeric(quota_type)){
						return false;
				}
				return true;
		}else
			return false;
		
	}	
	public boolean val_quotadesc(String quota_desc){
		if(quota_desc!=null){
			if(!cLength(quota_desc.toString(), 0, 10)){
					return false;
				}
				if (!cAlphaNumeric(quota_desc)){
						return false;
				}
				return true;
		}else
			return false;
		
	}	

}//end class