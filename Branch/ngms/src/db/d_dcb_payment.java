package db; 

import java.sql.*;

import common.db_validation;

public class d_dcb_payment extends db_validation{
	
	public String payment_id,dcb_id,shift_id,shiftuser_id,total,gh_id,sel_block,last_modified;
	ngm NGM;
	
	public d_dcb_payment(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO dcb_payment VALUES (?,?,?,?,?,now(),?,?)");
		ps.setString(1, payment_id);
		ps.setString(2, dcb_id);
		ps.setString(3, shift_id);
		ps.setString(4, shiftuser_id);
		ps.setString(5, total);
		ps.setString(6, gh_id);
		ps.setString(7, sel_block);
		System.out.println("DCBPAYMENT "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	

	
	public String selectDB_DcbTotal() throws Exception { //ok
	VAL_selectDB_DcbTotal();
		String total="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total::Integer) from dcb_payment where shift_id=? and gh_id=?");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			total=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return total;
	} 
	
	public boolean selectDB() throws Exception { //ok
		VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from dcb_payment where dcb_id=? and gh_id=?");
		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			dcb_id=rs.getString("dcb_id");
			payment_id=rs.getString("payment_id");
			total=rs.getString("total");
			gh_id=rs.getString("gh_id");
			shift_id=rs.getString("shift_id");
			shiftuser_id=rs.getString("shiftuser_id");
			last_modified=rs.getString("last_modified");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	

	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		if(!(val_seq_id(dcb_id)&&val_ghid(gh_id)&&val_seq_id(payment_id)&&val_shiftid(shift_id)&&val_user_id(shiftuser_id)&&val_total(total))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		
		/*val_seq_id(payment_id);
		val_seq_id(dcb_id);
		val_shiftid(shift_id);
		val_user_id(shiftuser_id);
		val_total(total);
		val_ghid(gh_id);*/
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
		
	}
}