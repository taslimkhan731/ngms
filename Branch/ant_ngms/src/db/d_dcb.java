package db; 

import java.sql.*;

import common.db_validation;

public class d_dcb extends db_validation{
	
	public String  dcb_id,date_trans,roll_no,bill_no,name,category,room_no,adults,children,rent,catering,dailies,transport,telephone,television,others,total_bill,rec_no,total_rec,balance_amount,reason,remarks,bill_receipt;
	public String shift_id,shift_userid,gh_id,sel_block;
	ngm NGM;
	
	public d_dcb(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		//VAL_insertDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("INSERT INTO dcb VALUES (?,to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?)");
		ps.setString(1, dcb_id);
		ps.setString(2, date_trans);
		ps.setString(3, roll_no);
		ps.setString(4, bill_no);
		ps.setString(5, name);
		ps.setString(6, category);
		ps.setString(7, room_no);
		ps.setString(8, adults);
		ps.setString(9, children);
		ps.setString(10, rent);
		ps.setString(11, catering);
		ps.setString(12, dailies);
		ps.setString(13, transport);
		ps.setString(14, telephone);
		ps.setString(15, television);
		ps.setString(16, others);
		ps.setString(17, total_bill);
		ps.setString(18, rec_no);
		ps.setString(19, total_rec);
		ps.setString(20, balance_amount);
		ps.setString(21, reason);
		ps.setString(22, remarks);
		ps.setString(23, bill_receipt);
		ps.setString(24, shift_id);
		ps.setString(25, shift_userid);
		ps.setString(26, gh_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public String selectDB_sumBill() throws Exception { //ok
		//VAL_selectDB_sumBill();
		String sumBill="";
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total_bill::Integer) from dcb where dcb_id=? and gh_id=?");
		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sumBill=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return sumBill;
	} 
	
	public String selectDB_sumReceipt() throws Exception { //ok
		String sumReceipt="";
		//VAL_selectDB_sumReceipt();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total_rec::Integer) from dcb where dcb_id=? and gh_id=?");
		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sumReceipt=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return sumReceipt;
	} 
	
	public String selectDB_sumDue() throws Exception { //ok
		String sumDue="";
		//VAL_selectDB_sumDue();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(balance_amount::Integer) from dcb where dcb_id=? and gh_id=?");
		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sumDue=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return sumDue;
	} 
	
	public String selectDB_ShiftsumBill() throws Exception { //ok
		String sumBill="";
		//VAL_selectDB_ShiftsumBill();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total_bill::Integer) from dcb where shift_id=? and gh_id=?");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sumBill=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return sumBill;
	} 
	
	public String selectDB_ShiftsumReceipt() throws Exception { //ok
		String sumReceipt="";
		//VAL_selectDB_ShiftsumReceipt();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(total_rec::Integer) from dcb where shift_id=? and gh_id=?");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sumReceipt=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return sumReceipt;
	} 
	
	public String selectDB_ShiftsumDue() throws Exception { //ok
		String sumDue="";
		//VAL_selectDB_ShiftsumDue();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(balance_amount::Integer) from dcb where shift_id=? and gh_id=?");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			sumDue=rs.getString("sum");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return sumDue;
	} 
	
	public boolean selectDB() throws Exception { //ok
		//VAL_selectDB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from dcb where dcb_id=? and gh_id=?");
		ps.setString(1, dcb_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			dcb_id=rs.getString("dcb_id");
			date_trans=rs.getString("date");
			roll_no=rs.getString("roll_no");
			bill_no=rs.getString("bill_no");
			name=rs.getString("name");
			category=rs.getString("category");
			room_no=rs.getString("room_no");
			adults=rs.getString("adults");
			children=rs.getString("children");
			rent=rs.getString("rent");
			catering=rs.getString("catering");
			dailies=rs.getString("dailies");
			transport=rs.getString("transport");

			telephone=rs.getString("telephone");
			television=rs.getString("television");
			others=rs.getString("others");
			total_bill=rs.getString("total_bill");
			rec_no=rs.getString("rec_no");
			total_rec=rs.getString("total_rec");
			balance_amount=rs.getString("balance_amount");
			reason=rs.getString("reason");
			
			remarks=rs.getString("remarks");
			bill_receipt=rs.getString("bill_receipt");
			shift_id=rs.getString("shift_id");
			shift_userid=rs.getString("shift_userid");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 
	
	public boolean isDcbExist() throws Exception { //ok
		//VAL_isDcbExist();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from dcb where date between (date(?)-1)+ time '12:00:00' and date(?)+ time '12:00:00' and gh_id=?");//date=to_timestamp(?,'yyyy-MM-dd HH24:MI:ss')
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from dcb where date(date) = date(?) and gh_id=?");
		ps.setString(1, date_trans);
		//ps.setString(2, date_trans);
		ps.setString(2, gh_id);
		System.out.println("isDcbExist ps "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			dcb_id=rs.getString("dcb_id");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 

	public String selectDB_ShiftDCB() throws Exception { //ok
		String max="";
		//VAL_selectDB_ShiftDCB();
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select max(dcb_id) from dcb where shift_id=? and gh_id=?");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			max=rs.getString("max");
			
			l_status=true;
		}
		rs.close();
		ps.close();
		return max;
	} 
	
//-------------------------------------------------------------Validation----------------------------------------------------------------------------------------------
	public void VAL_insertDB() throws Exception {
		boolean sss=true;
		
		//val_seq_id(dcb_id);
		//val_date_trans(date_trans);
		//val_rollno(roll_no);
		//val_seq_id(bill_no);
		
		if(!(val_ghid(gh_id)&&val_user_id(shift_userid)&&val_shiftid(shift_id)&&val_status(bill_receipt)&&val_remark(remarks)&&val_remark(reason)&&val_amount(balance_amount)&&val_amount(total_rec)&&val_amount(rec_no)&&val_amount(total_bill)&&val_amount(others)&&val_amount(television)&&val_amount(telephone)&&val_amount(transport)&&val_amount(dailies)&&val_amount(catering)&&val_amount(rent)&&val_no_of_person(adults)&&val_no_of_person(children)&&cNameComma(name," ")&&val_seq_id(dcb_id)&&val_date_trans(date_trans)&&val_rollno(roll_no)&&val_seq_id(bill_no)&&val_category(category)&&val_roomno(room_no))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_insertDB", NGM);
		}
		
		//val_category(category);
		//val_roomno(room_no);
		//val_no_of_person(children);
		//val_no_of_person(adults);
		//val_amount(rent);
		//val_amount(catering);
		//val_amount(dailies);
		//val_amount(transport);
		//val_amount(telephone);
		//val_amount(television);
		//val_amount(others);
		//val_amount(total_bill);
		//val_amount(rec_no);
		//val_amount(total_rec);
		//val_amount(balance_amount);
		//val_remark(reason);
		//val_remark(remarks);
		//val_status(bill_receipt);
		//val_shiftid(shift_id);
		//val_user_id(shift_userid);
		//val_ghid(gh_id);
	}

	public void VAL_selectDB_sumBill() throws Exception {
		if(!(val_seq_id(dcb_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumBill", NGM);
		}
		//val_seq_id(dcb_id);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB_sumReceipt() throws Exception {
		if(!(val_seq_id(dcb_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumReceipt", NGM);
		}
		//val_seq_id(dcb_id);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB_sumDue() throws Exception {
		if(!(val_seq_id(dcb_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_sumDue", NGM);
		}
		//val_seq_id(dcb_id);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB_ShiftsumBill() throws Exception {
		if(!(val_shiftid(shift_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_ShiftsumBill", NGM);
		}
		//val_shiftid(shift_id);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB_ShiftsumReceipt() throws Exception {
		if(!(val_shiftid(shift_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_ShiftsumReceipt", NGM);
		}
		//val_shiftid(shift_id);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB_ShiftsumDue() throws Exception {
		if(!(val_shiftid(shift_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_ShiftsumDue", NGM);
		}
		//val_shiftid(shift_id);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB() throws Exception {
		if(!(val_seq_id(dcb_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB", NGM);
		}
		//val_seq_id(dcb_id);
		//val_ghid(gh_id);
	}
	public void VAL_isDcbExist() throws Exception {
		if(!(val_date_trans(date_trans)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_isDcbExist", NGM);
		}
		//val_date_trans(date_trans);
		//val_ghid(gh_id);
	}
	
	public void VAL_selectDB_ShiftDCB() throws Exception {
		if(!(val_shiftid(shift_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("dcb", "Invalid dcb parameter", "VAL_selectDB_ShiftDCB", NGM);
		}
		//val_shiftid(shift_id);
		//val_ghid(gh_id);
	}
	
	public boolean val_date_trans(String date_trans){
		if(date_trans!=null){
			if(!cDateTime(date_trans.toString(),"yyyy-MM-dd HH:mm:ss")){
					return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_rollno(String roll_no){
		if(roll_no!=null){
			if(!cLength(roll_no.toString(), 0, 15)){
					return false;
				}
				if (!cNumeric(roll_no)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_category(String category){
		if(category!=null){
			if(!cLength(category.toString(), 0, 50)){
					return false;
				}
				if (!cAlphabet(category)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	public boolean val_no_of_person(String num){
		if(num!=null){
			if(!cLength(num.toString(), 0, 50)){
					return false;
				}
				if (!cNumeric(num)){
						return false;
				}
				return true;
		}else
			return false;
		
	}
	
	public boolean val_amount(String total){
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
	
	public boolean val_remark(String remarks){
		if(remarks!=null){
			if(!cLength(remarks.toString(), 0, 100)){
					return false;
				}
				if (!cAlphaNumeric(remarks)){
						return false;
				}
				return true;
		}
			return true;
		
	}
}