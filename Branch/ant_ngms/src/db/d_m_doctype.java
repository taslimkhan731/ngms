package db; 
import java.sql.*;

import common.db_validation;

public class d_m_doctype extends db_validation{
	public String doc_type;
	public String doc_type_desc;
	ngm NGM;

	public d_m_doctype(ngm p_ngm){		
		NGM=p_ngm;
	}
	
	public boolean selectDB() throws Exception {
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_doctype where doc_type=?");
		ps.setString(1, doc_type);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			doc_type=rs.getString("doc_type");
			doc_type_desc=rs.getString("doc_type_desc");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
/*	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_doctype values(?,?)");
		ps.setString(1, doc_type);
		ps.setString(2, doc_type_desc);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception {
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_doctype set doc_type_desc=? where doc_type=?");
		ps.setString(1, doc_type_desc);
		ps.setString(2, doc_type);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_doctype where doc_type=?");
		ps.setString(1, doc_type);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
*/

	
/***********************************************************Validation*****************************************************/
	public void VAL_selectDB() throws Exception {
		val_doctype(doc_type);
	}
	
}//end class