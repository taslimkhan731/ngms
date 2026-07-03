package db; 

import java.sql.*;

public class d_uploadindex
{
	public String name,addr,dob,tr_uin_id,tr_up_id;
	public int seqid1;
	
	ngm NGM;
	
	public d_uploadindex(ngm p_ngm){		
		NGM=p_ngm;
	}

	
	
	public boolean insertindex()throws Exception{
		boolean lstatus=false;
		
		 PreparedStatement ps4=NGM.tr_conn.prepareStatement("select * from tr_upload_index where userid=? and status='P'");
         ps4.setString(1,NGM.ins_sess.user_id);
         ResultSet rs2= ps4.executeQuery();
         if(!rs2.next()){
        	        	        
         PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into tr_upload_index values(?,now(),?,?,now())");
         String Status="P";
         int seqid1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "tableindex"); 
	     NGM.ins_utility.updateseqID(NGM.tr_conn, "tableindex", seqid1); 
	   
        
		ps.setInt(1,seqid1);
		ps.setString(2,NGM.ins_sess.user_id);
 		ps.setString(3,  Status);
					 
		lstatus=NGM.ins_utility.executeDB(ps);
		
		ps.close();
         }else{
//        	 PreparedStatement ps5=NGM.tr_conn.prepareStatement("update tr_upload_index set status='P',tr_time=now(),success_date=now() where userid=?");
//             
//        	 ps5.setString(1,NGM.ins_sess.user_id);
//           System.out.println("asdfasdf"+ps5);
//             lstatus=NGM.ins_utility.executeDB(ps5);
        	 lstatus = true;
        	
     		
        }
         rs2.close();
         ps4.close(); 
         return lstatus;
	
}	
public int selectid()throws Exception{
		PreparedStatement ps4=NGM.tr_conn.prepareStatement("select * from tr_upload_index where userid=?");
        ps4.setString(1,NGM.ins_sess.user_id);
        ResultSet rs2= ps4.executeQuery();
		while(rs2.next()){
			seqid1= rs2.getInt("tr_uin_id");
		}
		 rs2.close();
         ps4.close(); 
		return seqid1;
		
	}
	public boolean cancelstatus()throws Exception{
		boolean l_status = false;
		PreparedStatement ps1=NGM.tr_conn.prepareStatement("update tr_upload_index set status='C' , success_date=now() where userid=? and status='P'");		 
		 ps1.setString(1,NGM.ins_sess.user_id);
		l_status=NGM.ins_utility.executeDB(ps1);
		if(l_status==true)
		{
			PreparedStatement ps2=NGM.tr_conn.prepareStatement("update tr_upload_details set status='C' where status='P'");	 
			 
		l_status=NGM.ins_utility.executeDB(ps2);
	}	
		
         ps1.close(); 
		return l_status;
	}
public boolean updateindex()throws Exception{
	 boolean l_status=false;

			
			PreparedStatement ps4=NGM.tr_conn.prepareStatement("update tr_upload_index set status='S', success_date=now()  where tr_uin_id=?");
			ps4.setString(1,this.tr_uin_id);
			 System.out.println("[[[[[["+ps4);
			l_status=NGM.ins_utility.executeDB(ps4);
			if(l_status==false){
			
				 ps4=NGM.tr_conn.prepareStatement("update tr_upload_index set status='F', success_date=now()  where tr_uin_id=?");
				ps4.setString(1,tr_uin_id);
			 
				l_status=NGM.ins_utility.executeDB(ps4);
			}
			
	         ps4.close(); 
	 return l_status;
 }
}	

	

	
	
	
//-------------------------------------validation------------------------------------------------------------------------	
	
	
