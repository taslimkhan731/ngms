package db; 

import java.sql.*;

public class d_ghdownload_index
{
	public String name,addr,dob,tr_uin_id,tr_up_id,status,user_id;
	public int seqid1;
	
	ngm NGM;
	
	public d_ghdownload_index(ngm p_ngm){		
		NGM=p_ngm;
	}
	
	public boolean insertindex()throws Exception{
		boolean lstatus=false;
		//	NGM.ins_dbval.val_userid(user_id);	      
        PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into gh_download_index values(?,now(),now(),?)");
        ps.setString(1,NGM.ins_sess.user_id);
 		ps.setString(2, status);
					 
		lstatus=NGM.ins_utility.executeDB(ps);
		  	 
		ps.close();
         return lstatus;
	
}	
	 
 	
 
}	

	

	
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	
	
