package db; 
import java.sql.*;

import common.db_validation;

public class d_document extends db_validation{
	public String doc_id; 
	public String title;
	public String from_date;
	public String to_date;
	public String created_by; 
	public String doc_type; 

	ngm NGM;
	public d_document(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean selectDB() throws Exception {  //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_document where doc_id=?");
		ps.setString(1, doc_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			title=rs.getString("title");
			from_date=rs.getString("from_date");
			to_date=rs.getString("to_date");
			created_by=rs.getString("created_by");
			doc_type=rs.getString("doc_type");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean insertDB() throws Exception {		//ok
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_document values(?,?,TO_TIMESTAMP(?,'YYYY-MM-DD'),TO_TIMESTAMP(?,'YYYY-MM-DD'),?,?)");
		ps.setString(1, doc_id);
		ps.setString(2, title);
		ps.setString(3, from_date);
		ps.setString(4, to_date);
		ps.setString(5, created_by);
		ps.setString(6, doc_type);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception { //ok
		boolean l_status=false;
		VAL_updateDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update tr_document set title=?,from_date=TO_TIMESTAMP(?,'YYYY-MM-DD'),to_date=TO_TIMESTAMP(?,'YYYY-MM-DD'),created_by=?,doc_type=? where doc_id=?");
		ps.setString(1, title);
		ps.setString(2, from_date);
		ps.setString(3, to_date);
		ps.setString(4, created_by);
		ps.setString(5, doc_type);
		ps.setString(6, doc_id);
		System.out.println("doc_iddoc_iddoc_iddoc_id"+doc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		System.out.println("l_statusl_statusl_status...."+l_status);
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{ //ok
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_document where doc_id=?");
		ps.setString(1, doc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	
/***********************************************************Validation*****************************************************/
	public void VAL_insertDB() throws Exception {
		val_doc_id(doc_id);
		val_title(title);
		db_datecheck(from_date);
		db_datecheck(to_date);
		cDateComp(from_date,to_date,"<=");
		val_userid(created_by);
		val_doctype(doc_type);
	}
	
	public void VAL_selectDB() throws Exception {
		val_doc_id(doc_id);
	}
	
	public void VAL_updateDB() throws Exception {
		val_doc_id(doc_id);
		val_title(title);
		db_datecheck(from_date);
		db_datecheck(to_date);
		cDateComp(from_date,to_date,"<=");
		val_userid(created_by);
		val_doctype(doc_type);
		
	}
	
	public void VAL_deleteDB() throws Exception {
		val_doc_id(doc_id);
	}
	

}//end class