package db; 

import java.sql.*;

import common.db_validation;

public class d_shiftmaster extends db_validation{
	
	public String gh_id,sel_block,shift_id, createdby, lastmodified, createdtime, openbal_refund,openbal_regular, openbal_total,receipt_refund,receipt_regular,receipt_total,payment_refund,payment_regular,payment_total , status, par_shiftid, from_time,to_time, close_time;
	
	ngm NGM;
	
	public d_shiftmaster(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		//this.VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO tr_shift_master VALUES (?, ?, ?,now(), now(), ?, ?, ?,?,?,?,?,?,?,?,?, to_timestamp (?, 'DD-MM-YYYY HH24:MI'), to_timestamp(?, 'DD-MM-YYYY HH24:MI'),null,?)");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ps.setString(3, createdby);
 		ps.setString(4, openbal_refund);
		ps.setString(5, openbal_regular);
		ps.setString(6, openbal_total);
		ps.setString(7, receipt_refund);
		ps.setString(8, receipt_regular);
		ps.setString(9, receipt_total);
		ps.setString(10, payment_refund);
		ps.setString(11, payment_regular);
		ps.setString(12, payment_total);
		ps.setString(13, status);
		ps.setString(14, par_shiftid);
		ps.setString(15, from_time);
		ps.setString(16, to_time);
		ps.setString(17, sel_block);
		//ps.setString(10, close_time);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		//this.VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_shift_master SET openbal_refund=?,openbal_regular=?, openbal_total=? ,from_time = to_timestamp (?, 'DD-MM-YYYY HH24:MI'),to_time=to_timestamp (?, 'DD-MM-YYYY HH24:MI'),lastmodified= now() WHERE shift_id=?");
		
		ps.setString(1, openbal_refund);
		ps.setString(2, openbal_regular);
		ps.setString(3, openbal_total);
		ps.setString(4, from_time);
		ps.setString(5, to_time);
		ps.setString(6, shift_id);
		System.out.println(ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB_close() throws Exception
	{
		boolean l_status=false;
		//val_shiftid(shift_id);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_shift_master SET status=?,lastmodified= now(),close_time=now() WHERE shift_id=?");
		ps.setString(1, status);
		ps.setString(2, shift_id);
		 
		System.out.println(ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	
	public boolean selectDB_active() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_shift_master where gh_id=? and block_id=? and status=?");
		ps.setString(1, gh_id);
		ps.setString(2, sel_block);
		ps.setString(3, status);
 		System.out.println("sdfstatus active"+ps);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 l_status = true;
		 }
		 	rs.close();
			ps.close();
		return l_status;
	}

	public boolean selectDB_active_user() throws Exception
	{
		boolean l_status=false;
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_shift_master where gh_id=? and createdby=? and status=?");
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from tr_shift_master where gh_id=? and block_id=? and status=?)a join (select * from tr_shiftvsuserid where user_id=?)b on a.shift_id=b.shift_id");

		ps.setString(1, gh_id);
		ps.setString(2, sel_block);
		ps.setString(3, status);
		ps.setString(4, NGM.ins_sess.user_id);
 		System.out.println("sdfstatus active"+ps);

		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 
			 
			 l_status = true;
			 
		 }
		 	rs.close();
			ps.close();
		return l_status;
	}
	public boolean selectDB_ACT() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select shift_id from tr_shift_master where gh_id=? and block_id=? and status=?");
		ps.setString(1, gh_id);
		ps.setString(2, sel_block);
		ps.setString(3, status);
		System.out.println("sdfstatus active"+ps);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 shift_id = rs.getString("shift_id");
			 l_status = true;
		 }
		 	rs.close();
			ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("DELETE FROM tr_shift_master WHERE shift_id");
		ps.setString(1, shift_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}
	public boolean selectDB() throws Exception { //ok
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_shift_master where shift_id=?");
		ps.setString(1, shift_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			createdby=rs.getString("createdby");
			lastmodified=rs.getString("lastmodified");
			createdtime=rs.getString("createdtime");
			openbal_refund=rs.getString("openbal_refund");
			openbal_regular=rs.getString("openbal_regular");
			openbal_total=rs.getString("openbal_total");
			status=rs.getString("status");
			par_shiftid=rs.getString("par_shiftid");
			from_time=rs.getString("from_time");
			to_time=rs.getString("to_time");
			close_time=rs.getString("close_time");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
public String getparent_id()throws Exception{
		String parent_id="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select shift_id from tr_shift_master where lastmodified = (select MAX(lastmodified) from tr_shift_master)");
ResultSet rs = ps.executeQuery();
while(rs.next()){
	parent_id= rs.getString("shift_id");
}
rs.close();
ps.close();
	return parent_id;	
	}
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		
		if(!(val_todate(to_time)&&val_fromdate(from_time)&&val_shiftid(par_shiftid)&&val_status(status)&&val_amount_null(payment_total)&&val_amount_null(payment_regular)&&val_amount_null(payment_refund)&&val_amount_null(receipt_total)&&val_amount_null(receipt_regular)&&val_amount_null(receipt_refund)&&val_ghid(gh_id)&&val_shiftid(shift_id)&&val_user_id(createdby)&&val_amount(openbal_refund)&&val_amount(openbal_regular)&&val_amount(openbal_total))){
			throw NGM.ins_utility.setException("otp_data", "Invalid otp_data parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		/*val_ghid(gh_id);//
		val_shiftid(shift_id);//
		val_user_id(createdby);//
		val_amount(openbal_refund);
		val_amount(openbal_regular);
		val_amount(openbal_total);
		
		val_amount_null(receipt_refund);
		val_amount_null(receipt_regular);
		val_amount_null(receipt_total);
		val_amount_null(payment_refund);
		val_amount_null(payment_regular);
		val_amount_null(payment_total);
		
		val_status(status);
		val_shiftid(par_shiftid);
		val_fromdate(from_time);
		val_todate(to_time);*/
		
	}
	
	public boolean val_amount(String amt){
		if(amt!=null){
			if(!cLength(amt.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(amt)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_amount_null(String amt){
		if(amt!=null){
			if(!cLength(amt.toString(), 1, 10)){
					return false;
				}
				if (!cNumeric(amt)){
						return false;
				}
				return true;
		}
			return true;
		
	}
	
	
	/*public boolean val_createdtime(String createdtime){
		if(createdtime!=null){
			if(!cDateTime(createdtime.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_lastmodified(String lastmodified){
		if(lastmodified!=null){
			if(!cDateTime(lastmodified.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}*/

}