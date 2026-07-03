package db; 

import java.sql.*;

public class d_upload_details
{
	public String name,addr,dob,tr_uin_id,tr_up_id,tablename,alias_name;
	public int seqid1;
	
	ngm NGM;
	 
	public d_upload_details(ngm p_ngm){		
		NGM=p_ngm;
		 
	}

		
	public boolean insert_upload()throws Exception{
		boolean l_status=false;
		
		PreparedStatement ps2=NGM.tr_conn.prepareStatement("select * from tr_upload_details where table_name=? and status ='P'");
	       
		ps2.setString(1, tablename);
		 
		ResultSet rs= ps2.executeQuery();
		if(!rs.next()){
			 
			
					PreparedStatement ps1=NGM.tr_conn.prepareStatement("Insert into tr_upload_details values(?,?,?,?,now(),now(),?)");
			        String Status1="P";
			        int seqid = NGM.ins_utility.get_seqID(NGM.tr_conn, "tabledetails"); 
			        NGM.ins_utility.updateseqID(NGM.tr_conn, "tabledetails", seqid); 
			        
					ps1.setInt(1,seqid);
					ps1.setInt(2,this.seqid1);
					ps1.setString(3,this.tablename);
					ps1.setString(4,this. alias_name);
			  		ps1.setString(5, Status1);
			  		
					 
					l_status=NGM.ins_utility.executeDB(ps1);
					ps1.close();
		}
					else{
						l_status= true;
						}
					
		rs.close();
		ps2.close();
		return l_status;
	}
	
	
	public boolean updatedetails()throws Exception{
		boolean l_status = false;
		PreparedStatement ps3=NGM.tr_conn.prepareStatement("update tr_upload_details set status='S', end_time=now()  where tr_up_id=?");
		ps3.setString(1,this.tr_up_id);
		 System.out.println("update details"+ps3);
		l_status=NGM.ins_utility.executeDB(ps3);
		if(l_status==false)
		{
		  ps3=NGM.tr_conn.prepareStatement("update tr_upload_details set status='F', end_time=now()  where tr_up_id=?");
		
		ps3.setString(1,this.tr_up_id);
		 System.out.println("update details"+ps3);
		l_status=NGM.ins_utility.executeDB(ps3);
		}	
		
		ps3.close();
		return l_status;
	}

	
	public boolean checkstatus()throws Exception{
		boolean l_status = false;
		String status;
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from tr_upload_details where tr_uin_id = ? and status='S'");	
		ps1.setString(1,this.tr_uin_id);
		
		 ResultSet rs = ps1.executeQuery();
		 while(rs.next()){
			 
			 l_status= true;
			  
			 }
		 rs.close();
			ps1.close();
		 return l_status;
		 }
		 
 
		
	
}	

	

	
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	
	
