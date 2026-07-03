package db; 

import java.sql.*;
import java.text.SimpleDateFormat;

import common.db_validation;

public class d_sms_alltment extends db_validation{
	
	public String a_sms_id,req_id,guest_details,subject,mobile_no,status,last_modified,gh_id,mode,exp_time;
	ngm NGM;
	
	public d_sms_alltment(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean can_all_pending() throws Exception
	{
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement(" update  tr_sms_alltment set status ='D'  where status ='P' and " +
				"req_id =? and  (mode ='CVALTG' or  mode = 'CVALTV' or  mode = 'CVALTP')");
		ps.setString(1, req_id);
		System.out.println("Uuuuuuuuuuuuuuuuuuuuuuuuuuu   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	
	}
	
	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		//VAL_insertDB();
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");//Yes, it works without .SSSSSS
		Timestamp ts = new Timestamp(sdf.parse(exp_time).getTime());
		System.out.println("ts"+ts);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_sms_alltment VALUES (?,?,?,?,?,?,now(),?,?,?)");
		ps.setString(1, a_sms_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_details);
		ps.setString(4, subject);
		ps.setString(5, mobile_no);
		ps.setString(6, status);
		ps.setString(7, gh_id);
		ps.setString(8, mode);
		ps.setTimestamp(9, ts);
		
		System.out.println("Uuuuuuuuuuuuuuuuuuuuuuuuuuu   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		}catch(Exception ep){
			System.out.println(ep.getMessage());
		}
		return l_status;		
	} 
	

	
	public boolean selectDB() throws Exception { //ok
	//	VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_sms_alltment where req_id=? and gh_id=? and status=? and mode=?"); //
		ps.setString(1, req_id);
		ps.setString(2, gh_id);
		ps.setString(3, status);
		ps.setString(4, mode);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			a_sms_id=rs.getString("a_sms_id");
			guest_details=rs.getString("guest_details");
			subject=rs.getString("subject");
			mobile_no=rs.getString("mobile_no");
			status=rs.getString("status");
			last_modified=rs.getString("last_modified");
			mode=rs.getString("mode");
			exp_time=rs.getString("exp_time");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean selectDB_status() throws Exception { //ok
		//	VAL_selectDB();
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_sms_alltment where req_id=? and gh_id=? and mode=?"); //
			ps.setString(1, req_id);
			ps.setString(2, gh_id);
			ps.setString(3, mode);
			System.out.println("Uuuuuuuuuuuuuuuuuuuuuuuuuuu   "+ps);
			ResultSet rs=ps.executeQuery();
			boolean l_status=false;
			while(rs.next()){
				status=rs.getString("status");
				a_sms_id=rs.getString("a_sms_id");
				guest_details=rs.getString("guest_details");
				subject=rs.getString("subject");
				mobile_no=rs.getString("mobile_no");
				status=rs.getString("status");
				last_modified=rs.getString("last_modified");
				mode=rs.getString("mode");
				exp_time=rs.getString("exp_time");
				l_status=true;
			}
			rs.close();
			ps.close();
			return l_status;
		} 
	
	public boolean selectDB_id() throws Exception { //ok
		//	VAL_selectDB();
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_sms_alltment where a_sms_id=? and status=?");
			ps.setString(1, a_sms_id);
			ps.setString(2, status);
			ResultSet rs=ps.executeQuery();
			boolean l_status=false;
			while(rs.next()){
				guest_details=rs.getString("guest_details");
				req_id=rs.getString("req_id");
				gh_id=rs.getString("gh_id");
				subject=rs.getString("subject");
				mobile_no=rs.getString("mobile_no");
				last_modified=rs.getString("last_modified");
				mode=rs.getString("mode");
				exp_time=rs.getString("exp_time");
				l_status=true;
			}
			rs.close();
			ps.close();
			return l_status;
		} 
	
	public boolean updateDB_status() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment  SET  status=?, last_modified=now() WHERE a_sms_id=?");
		ps.setString(1, status);
		ps.setString(2, a_sms_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment  SET  status=?, last_modified=now() WHERE req_id=? and gh_id=? and mode=?");//
		ps.setString(1, status);
		ps.setString(2, req_id);
		ps.setString(3, gh_id);
		ps.setString(4, mode);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean addSubject() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment SET subject=?,last_modified=now() WHERE req_id=? and gh_id=? and status=? and mode=?");//
		ps.setString(1, subject);
		ps.setString(2, req_id);
		ps.setString(3, gh_id);
		ps.setString(4, status);
		ps.setString(5, mode);
		System.out.println("pppppppppppp+++++++++++++++++++++++++      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
///ajeesh
	public boolean selectDB_req() throws Exception { //ok
		//	VAL_selectDB();
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_sms_alltment where req_id=?");
			ps.setString(1, req_id);
			System.out.println("FRISE     SELECT BE   "+ps);
			ResultSet rs=ps.executeQuery();
			boolean l_status=false;
			while(rs.next()){
				guest_details=rs.getString("guest_details");
				req_id=rs.getString("req_id");
				gh_id=rs.getString("gh_id");
				subject=rs.getString("subject");
				mobile_no=rs.getString("mobile_no");
				last_modified=rs.getString("last_modified");
				mode=rs.getString("mode");
				
				l_status=true;
			}
			rs.close();
			ps.close();
			return l_status;
		} 

///ajeesh
	
	public boolean updateSubject() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment SET subject=?, mobile_no=?, last_modified=now() WHERE req_id=? and gh_id=? and status=? and mode=?");//
		ps.setString(1, subject);
		ps.setString(2, mobile_no);
		ps.setString(3, req_id);
		ps.setString(4, gh_id);
		ps.setString(5, status);
		ps.setString(6, mode);
		System.out.println("pppppppppppp+++++++++++++++++++++++++      "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}


public boolean updateDB_guest_re() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment SET subject=?, mode=?,last_modified=now() WHERE req_id=? and gh_id=? and status=? and mode=?");// and mode=?
		ps.setString(1, subject);
		ps.setString(2, mode);
		ps.setString(3, req_id);
		ps.setString(4, gh_id);
		ps.setString(5, status);
		ps.setString(6, "CVALTG");
		System.out.println("Update^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^         +ps");
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

public boolean updateDB_vehicle_re() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment SET subject=?, mode=?,last_modified=now() WHERE req_id=? and gh_id=? and status=? and mode=?");// and mode=?
		ps.setString(1, subject);
		ps.setString(2, mode);
		ps.setString(3, req_id);
		ps.setString(4, gh_id);
		ps.setString(5, status);
		ps.setString(6, "CVALTV");
		System.out.println("Update^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^         +ps");
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean updateDB_guest() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment SET mode=?,status=?,last_modified=now() WHERE req_id=? and gh_id=? and status=? and mode=?");// and mode=?
		ps.setString(1, "CVALTG");
		ps.setString(2, "P");
		ps.setString(3, req_id);
		ps.setString(4, gh_id);
		ps.setString(5, status);
		ps.setString(6, mode);
		System.out.println("ppppppppppppppppppppppp    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}


	public boolean updateDB_driver() throws Exception
	{
		boolean l_status=false;
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_sms_alltment SET mode=?,status=?,last_modified=now() WHERE req_id=? and gh_id=? and status=? and mode=?");// and mode=?
		ps.setString(1, "CVALTV");
		ps.setString(2, "P");
		ps.setString(3, req_id);
		ps.setString(4, gh_id);
		ps.setString(5, status);
		ps.setString(6, mode);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	public boolean deleteDB() throws Exception {		
		boolean l_status=false;	
		//VAL_deletecheckin_allot();
        PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from tr_sms_alltment where req_id=? and mode=?");
		pss.setString(1, req_id);
		pss.setString(2, mode);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 

	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
/*	public void VAL_insertDB() throws Exception {
		if(!(val_seq_id(dcb_id)&&val_ghid(gh_id)&&val_seq_id(payment_id)&&val_shiftid(shift_id)&&val_user_id(shiftuser_id)&&val_total(total))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		val_seq_id(payment_id);
		val_seq_id(dcb_id);
		val_shiftid(shift_id);
		val_user_id(shiftuser_id);
		val_total(total);
		val_ghid(gh_id);
	}
	
	public void VAL_selectDB_DcbTotal() throws Exception {
		if(!(val_ghid(gh_id)&&val_shiftid(shift_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
	//	val_shiftid(shift_id);
	//	val_ghid(gh_id);
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_ghid(gh_id)&&val_seq_id(dcb_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_seq_id(dcb_id);
		//val_ghid(gh_id);
	}
	
	public boolean val_total(String total){
		if(total!=null){
			if(!cLength(total.toString(), 0, 25)){
					return false;
				}
				if (!cNumeric(total)){
						return false;
				}
				return true;
		}else
			return false;
		
	}*/
}