package db; 

import java.sql.*;

public class d_ghimport_index
{
	public String status;
	public int seqid1;
	
	ngm NGM;
	
	public d_ghimport_index(ngm p_ngm){		
		NGM=p_ngm;
	}

	
	
	public boolean insertindex()throws Exception{
		boolean lstatus=false;
		
		      
        PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into gh_import_index values(?,now(),now(),?)");
        ps.setString(1,NGM.ins_sess.user_id);
 		ps.setString(2,  status);
					 
		lstatus=NGM.ins_utility.executeDB(ps);
		
	        	 
        ps.close();
         return lstatus;
	
}	
 
 
}	

	

	
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	
	
