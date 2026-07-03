package db; 
import java.sql.*;

import common.db_validation;

public class d_document_items extends db_validation{
	public String attachment_id; //15
	public String doc_id; //15
	public String a_filename; //50
	public String s_filename; //50
	public String file_type; //25
	public String file_path; //100
	public String filesize; //25

	ngm NGM;
	public d_document_items(ngm p_ngm){		
		NGM=p_ngm;
	}


	
	public boolean insertDB() throws Exception {		//ok
		boolean l_status=false;	
		//validate("insertDB");
		//validate_key("insertDB");
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_document_items values(?,?,?,?,?,?,?)");
		ps.setString(1, attachment_id);
		ps.setString(2, doc_id);
		ps.setString(3, a_filename);
		ps.setString(4, s_filename);
		ps.setString(5, file_type);
		ps.setString(6, file_path);
		ps.setString(7, filesize);

		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean selectDBdoc() throws Exception {  //ok
		VAL_selectDBdoc();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_document_items where doc_id=?");
		ps.setString(1, doc_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			attachment_id =rs.getString("attachment_id");
			file_type=rs.getString("file_type");
			file_path=rs.getString("file_path");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_document_items where attachment_id=?");
		ps.setString(1, attachment_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			doc_id=rs.getString("doc_id");
			a_filename=rs.getString("a_filename");
			s_filename=rs.getString("s_filename");
			file_type=rs.getString("file_type");
			file_path=rs.getString("file_path");
			filesize=rs.getString("filesize");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean deleteDB() throws Exception{ //ok
		boolean l_status=false;
		VAL_deleteDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_document_items where attachment_id=?");
		ps.setString(1, attachment_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean deleteDBdocid() throws Exception{ //ok
		boolean l_status=false;
		VAL_deleteDBdocid();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_document_items where doc_id=?");
		ps.setString(1, doc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	public boolean deleteDBitem() throws Exception{  //ok
		boolean l_status=false;
		VAL_deleteDBitem();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_document_items where attachment_id=? and doc_id=?");
		ps.setString(1, attachment_id);
		ps.setString(2, doc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	
	

/***********************************************************Validation*****************************************************/
	public void VAL_insertDB() throws Exception {
		val_attachment_id(attachment_id);
		val_doc_id(doc_id);
		val_a_filename(a_filename);
		val_s_filename(s_filename);
		val_file_type(file_type);
		val_file_path(file_path);
		val_filesize(filesize);
	}
	
	public void VAL_selectDB() throws Exception {
		val_attachment_id(attachment_id);
	}
	
	public void VAL_selectDBdoc() throws Exception {
		val_doc_id(doc_id);
	}
	
	public void VAL_deleteDBdocid() throws Exception {
		val_doc_id(doc_id);
	}
	
	public void VAL_deleteDB() throws Exception {
		val_attachment_id(attachment_id);
	}
	
	public void VAL_deleteDBitem() throws Exception {
		val_doc_id(doc_id);
		val_attachment_id(attachment_id);
	}
	
	public boolean val_attachment_id(String attachment_id){
		if(attachment_id!=null){
			if(!cLength(attachment_id.toString(), 1, 15)){
					return false;
				}
				if (!cNumeric(attachment_id)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	
	public boolean val_s_filename(String s_filename){
		if(s_filename!=null){
			if(!cLength(s_filename.toString(), 1, 50)){
					return false;
				}
				if (!cAlphaNumeric(s_filename)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_a_filename(String a_filename){
		if(a_filename!=null){
			if(!cLength(a_filename.toString(), 1, 50)){
					return false;
				}
				if (!cAlphaNumeric(a_filename)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_file_type(String file_type){
		if(file_type!=null){
			if(!cLength(file_type.toString(), 1, 25)){
					return false;
				}
				if (!cAlphabet(file_type)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_file_path(String file_path){
		if(file_path!=null){
			if(!cLength(file_path.toString(), 1, 100)){
					return false;
				}
				if (!cfilepath(file_path)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_filesize(String filesize){
		if(filesize!=null){
			if(!cLength(filesize.toString(), 1, 25)){
					return false;
				}
				if (!cNumeric(filesize)){
						return false;
				}
				return true;
		}else
			return false;
		
	}

}//end class