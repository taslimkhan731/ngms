package db; 
import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itextpdf.text.log.SysoLogger;

import common.db_validation;
import common.utility;

public class d_mod_req_room extends db_validation{
	public String block_id,type,vc_id,quota_type,process_id,is_veh_req,req_id,reqst,gh_id,mod,mrr_id1,mod_id1,number,mod_id,mrr_id ;   //nor_req,nob_g_req,nob_l_req,
	ngm NGM;
	utility 	  ins_util;
	public JSONArray array1 = new JSONArray();
	public d_mod_req_room(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}
	
	public boolean insertDB() throws Exception {		
		boolean l_status=false;	
		VAL_insertDB();
//		 req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
//		 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);
		 
	     //mod_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "mod_id"); 
	    	     
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into mod_req_room values(?,?,?,?,?,?,?,now())");
		ps.setString(1, mrr_id);
		ps.setString(2, mod_id);
		ps.setString(3, req_id);
		ps.setString(4, block_id);
		ps.setInt(5, 1);
		ps.setString(6, type);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		System.out.println("QUERY1111      "+ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_GH() throws Exception {		
		boolean l_status=false;	
		//VAL_insertDB();
//		 req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
//		 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);
		 
	     //mod_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "mod_id"); 
		VAL_insertDB_GH();     
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into mod_req_room values(?,?,?,?,?,?,?,now())");
		ps.setString(1, mrr_id);
		ps.setString(2, mod_id);
		ps.setString(3, req_id);
		ps.setString(4, block_id);
		ps.setInt(5, 1);
		ps.setString(6, type);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		System.out.println("QUERY1111      "+ps);
		ps.close();
		return l_status;		
	} 
	public boolean insertDB_upload() throws Exception {		
		boolean l_status=false;	
		VAL_insertDB_upload();    
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into mod_req_room values(?,?,?,?,?,?,?,now())");
		ps.setString(1, mrr_id1);
		ps.setString(2, mod_id1);
		ps.setString(3, req_id);
		ps.setString(4, block_id);
		ps.setInt(5, Integer.parseInt(number));
		ps.setString(6, type);
		ps.setString(7, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		System.out.println("QUERY1111      "+ps);
		ps.close();
		return l_status;		
	} 
	public void existdata()throws Exception{
		VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req_room where mrr_id=?");
		ps.setString(1, mrr_id1);
		System.out.println("vehicle"+ps);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from mod_req_room where mrr_id=?");
			ps1.setString(1, mrr_id1);
			ps1.execute();
			ps1.close(); 
		}
		rs.close();
		ps.close();	
	}
			 
		public boolean existdata_accept()throws Exception{
			VAL_existdata();
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req_room where mrr_id=?");
			ps.setString(1, mrr_id1);
			System.out.println("vehicle"+ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
				 
			}
			rs.close();
			ps.close();
			return false;
		
	}
	public void setpost_mod(JSONObject jsonObj)throws Exception{
		 
		 this.mrr_id1 =  ins_util.setNullvalue( jsonObj.get("mrr_id"));
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 this.mod_id1 =  ins_util.setNullvalue(jsonObj.get("mod_id"));
		 this.block_id = ins_util.setNullvalue(jsonObj.get("block_id"));
		 this.number = ins_util.setNullvalue(jsonObj.get("number"));
		 this.type =  ins_util.setNullvalue(jsonObj.get("type"));
		 this.gh_id =  ins_util.setNullvalue(jsonObj.get("gh_id"));
 		 
	} 
	public boolean deletemrr() throws Exception {		
		boolean l_status=false;	
		      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from mod_req_room where req_id=?");
		 
		pss.setString(1, reqst);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean deletemod() throws Exception {		
		boolean l_status=false;	
		VAL_deletemod();
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from mod_req_room where req_id=? and mod_id=? and gh_id=?");
		pss.setString(1, req_id);
		pss.setString(2, mod);
		pss.setString(3, gh_id);
System.out.println("gopika   ps "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	
	public boolean updateDB() throws Exception 
	{
		boolean l_status=false;
//		 req_id = NGM.ins_utility.get_seqcurrent(NGM.tr_conn, "req_id"); 
//		 NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", req_id);
		System.out.println("req_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_id "+req_id);
		String req_id1 = String.valueOf(req_id);
		System.out.println("req_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_idreq_id1111 "+req_id1);

		PreparedStatement ps=NGM.tr_conn.prepareStatement("update mod_req_room set type=?,last_modified=now() where block_id=? and req_id=?");
		ps.setString(1, type);
		
		ps.setString(2, block_id);
		ps.setString(3, req_id1 );
		System.out.println("SSSSS       "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		
		ps.close();
		return l_status;
	}
	
	public boolean selectimportdata(String time)throws Exception{ 
		boolean retval = false;
			JSONArray jobj = new JSONArray();
			String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req_room where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from "+this.tablename+"");
			ps.setString(1,time);
			//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from mod_req_room");

			System.out.println("@@@"+ps);
			ResultSet rs=ps.executeQuery();
			System.out.println("@@@"+ps);
			ResultSetMetaData rsmet = rs.getMetaData();
			int col_count = rsmet.getColumnCount();
			String fieldname="";
			int rownum =0;
			int count=0;
			 while(rs.next()){ 
				 JSONObject jsonObj1 = new JSONObject();
					   count = count +1;
					for (int i = 1; i < col_count + 1; i++ ) 
					{ 	 
					      fieldname  = rsmet.getColumnName(i);
					      String fieldvalue = rs.getString(fieldname);
					      jsonObj1.put(fieldname, fieldvalue);
						 
					}
							 
					array1.add(jsonObj1);
				
					System.out.println("sdffffffffffffff"+array1);
			retval = true;
			}
			 	rs.close();
				ps.close();
			return retval;
			}
//-------------------------------------------------validation-------------------------------------------------------------------

	public void VAL_insertDB() throws Exception {
		
		if(!(val_modid(mrr_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid mrr_id", "VAL_insertDB", NGM);
		}
		if(!(val_modid(mod_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id", "VAL_insertDB", NGM);
		}
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_insertDB", NGM);
		}
		if(!(val_blockid(block_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid block_id", "VAL_insertDB", NGM);
		}
		if(!(val_type(type))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid type", "VAL_insertDB", NGM);
		}
		if(!(val_ghid(gh_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid gh_id", "VAL_insertDB", NGM);
		}
	}
	
	public void VAL_insertDB_GH() throws Exception {
		
		if(!(val_modid(mrr_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid mrr_id", "VAL_insertDB_GH", NGM);
		}
		if(!(val_modid(mod_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id", "VAL_insertDB_GH", NGM);
		}
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_insertDB_GH", NGM);
		}
		if(!(val_blockid(block_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid block_id", "VAL_insertDB_GH", NGM);
		}
		if(!(val_type(type))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid type", "VAL_insertDB_GH", NGM);
		}
		if(!(val_ghid(gh_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid gh_id", "VAL_insertDB_GH", NGM);
		}
	}
public void VAL_insertDB_upload() throws Exception {
		
		if(!(val_modid(mrr_id1))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid mrr_id1", "VAL_insertDB_upload", NGM);
		}
		if(!(val_modid(mod_id1))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid mod_id1", "VAL_insertDB_upload", NGM);
		}
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_insertDB_upload", NGM);
		}
		if(!(val_blockid(block_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid block_id", "VAL_insertDB_upload", NGM);
		}
		if(!(val_type(type))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid type", "VAL_insertDB_upload", NGM);
		}
		if(!(val_ghid(gh_id))){
			throw NGM.ins_utility.setException("d_mod_req", "Invalid gh_id", "VAL_insertDB_upload", NGM);
		}
	}
public void VAL_existdata() throws Exception {
	
	if(!(val_modid(mrr_id1))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid mrr_id1", "VAL_existdata", NGM);
	}
}
public void VAL_deletemod() throws Exception {
	
	if(!(val_modid(mod))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid mod", "VAL_deletemod", NGM);
	}
	if(!(val_reqid(req_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid req_id", "VAL_deletemod", NGM);
	}
	if(!(val_ghid(gh_id))){
		throw NGM.ins_utility.setException("d_mod_req", "Invalid gh_id", "VAL_deletemod", NGM);
	}
}

}
