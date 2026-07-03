package db; 

import java.sql.*;

import common.db_validation;

public class d_doc_history extends db_validation{
	public String doc_id;
	public String title;
	public String userid;
	public String reason;
	ngm NGM;
	public d_doc_history(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
	//	VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_doc_history values(?,?,?,?)");
		ps.setString(1, doc_id);
		ps.setString(2, title);
		ps.setString(3, userid);
		ps.setString(4, reason);
		System.out.println(" ps..... "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
	//	VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_doc_history where doc_id=?");
		ps.setString(1, doc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception {
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_doc_history where doc_id=?");
		ps.setString(1, doc_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			doc_id=rs.getString("doc_id");
			title=rs.getString("title");
			userid=rs.getString("userid");
			reason=rs.getString("reason");		
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
	
/***********************************************************Validation*****************************************************/
	public void VAL_insertDB() throws Exception {
		val_doc_id(doc_id);
		val_title(title);
		val_userid(userid);
		val_reason(reason);
	}
	
	public void VAL_selectDB() throws Exception {
		val_doc_id(doc_id);
	}
	
	public void VAL_deleteDB() throws Exception {
		val_doc_id(doc_id);
	}
	
	public boolean val_reason(String reason){
		if(reason!=null){
			if(!cLength(reason.toString(), 2, 150)){
					return false;
				}
				if (!cRemark(reason)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

}//end class