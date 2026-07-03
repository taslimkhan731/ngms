package db; 
import java.sql.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;
import db.*;
public class d_checkin extends db_validation
{
	ngm NGM;
	utility 	  ins_util;
	public JSONArray array1 = new JSONArray();
	public String tr_id="",gh_id="",sel_block="",roll_no,guest_id,arrival_time_date,departure_time_date,advance_amt,req_id,charges_realised,bill_no,remarks,status,no_of_children_below12,shift_id,shiftuser_id,adults,parent_trid="0",adv_bill_id;
	public String vc_id="", purpose="";
	public d_checkin(ngm p_ngm){	
		NGM=p_ngm;
		ins_util = new utility();
	}
	public boolean insertDB_check() throws Exception {	
		  boolean l_status=false;	
		  VAL_insertDB();
		  PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_checkin values(?,?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?)");
		  ps.setString(1, tr_id);
		  ps.setString(2,roll_no);
		  ps.setString(3,guest_id);
		  ps.setString(4,arrival_time_date);
		  ps.setString(5,departure_time_date);
		  ps.setString(6,advance_amt);
		  ps.setString(7,req_id);
		  ps.setString(8,charges_realised);
		  ps.setString(9,bill_no );
		  ps.setString(10,remarks );
		  ps.setString(11,"CIN");
		  ps.setString(12, no_of_children_below12);
		  ps.setString(13,shift_id);
		  ps.setString(14,shiftuser_id);
		  ps.setString(15, adults);	
		  ps.setString(16, gh_id);
		  ps.setString(17, parent_trid);
		  ps.setString(18, adv_bill_id);
		  ps.setString(19, vc_id);
		  ps.setString(20, purpose);
		  ps.setString(21, sel_block);
System.out.println("CAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa "+ps);
		  l_status=NGM.ins_utility.executeDB(ps);
		  ps.close();
		  return l_status;		
	} 
	public boolean insertDB_check_upload() throws Exception {	
		  boolean l_status=false;	
		  System.out.println("adv_bill_id "+adv_bill_id);
		  VAL_insertDB();
		  PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_checkin values(?,?,?,to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),to_timestamp(?,'yyyy-MM-dd HH24:MI:ss'),?,?,?,?,?,?,?,?,?,?,now(),?,?,?)");
		  ps.setString(1, tr_id);
		  ps.setString(2,roll_no);
		  ps.setString(3,guest_id);
		  ps.setString(4,arrival_time_date);
		  ps.setString(5,departure_time_date);
		  ps.setString(6,advance_amt);
		  ps.setString(7,req_id);
		  ps.setString(8,charges_realised);
		  ps.setString(9,bill_no );
		  ps.setString(10,remarks );
		  ps.setString(11,"CIN");
		  ps.setString(12, no_of_children_below12);
		  ps.setString(13,shift_id);
		  ps.setString(14,shiftuser_id);
		  ps.setString(15, adults);	
		  ps.setString(16, gh_id);
		  ps.setString(17, parent_trid);
		  ps.setString(18, adv_bill_id);
System.out.println("CAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa "+ps);
		  l_status=NGM.ins_utility.executeDB(ps);
		  ps.close();
		  return l_status;		
	}
	public boolean updateDB_check() throws Exception {	
		  boolean l_status=false;	
		  VAL_updateDB();
		  PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_checkin SET tr_id=?, roll_no=?, guest_id=?, arrival_time_date=?, departure_time_date=?,advance_amt=?, req_id=?, charges_realised=?, bill_no=?, remarks=?,status=?,no_of_children_below12=?,shift_id=?,shiftuser_id=?,adults=?,lastmodified=now() WHERE tr_id=? and status!='CAN'");
		  ps.setString(1,roll_no);
		  ps.setString(2,guest_id);
		  ps.setString(3,arrival_time_date);
		  ps.setString(4,departure_time_date);
		  ps.setString(5,advance_amt);
		  ps.setString(6,req_id);
		  ps.setString(7,charges_realised);
		  ps.setString(8,bill_no );
		  ps.setString(9,remarks );
		  ps.setString(10,status );
		  ps.setString(11, no_of_children_below12);
		  ps.setString(12,shift_id);
		  ps.setString(13,shiftuser_id);
		  ps.setString(14, adults);
		  ps.setString(15, tr_id);
		
		  l_status=NGM.ins_utility.executeDB(ps);
		  ps.close();
		  return l_status;		
	}
	
	public boolean updateDB_status() throws Exception {	
		  boolean l_status=false;	
		  VAL_updateDB_status();
		  PreparedStatement ps=NGM.tr_conn.prepareStatement("UPDATE tr_checkin SET status=?,lastmodified=now() WHERE req_id=? and guest_id=? and status!='CAN'");
		  ps.setString(1,status );
		  ps.setString(2,req_id);
		  ps.setString(3,guest_id);
		  l_status=NGM.ins_utility.executeDB(ps);
		  ps.close();
		  return l_status;		
	} 
	
	
	public boolean deletecheckin() throws Exception {		
		boolean l_status=false;	
		VAL_deletecheckin();      
	    PreparedStatement pss=NGM.tr_conn.prepareStatement("delete from tr_checkin where tr_id=?");
		pss.setString(1, tr_id);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
		//VAL_selectDB(); 
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where tr_id=? and status!='CAN'");
		ps.setString(1, tr_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			guest_id = rs.getString("guest_id");
			req_id = rs.getString("req_id");
			l_status= true;
		}
		rs.close();
		ps.close();
			return l_status;
	}
	
	public boolean selectDB_prev_ext() throws Exception {		
		boolean l_status=false;	
		VAL_selectDB_prev_ext();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where gh_id=? and req_id=? and guest_id=? and status!='CAN'");
		ps.setString(1, gh_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);

		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			roll_no = rs.getString("roll_no");
			arrival_time_date = rs.getString("arrival_time_date");
			advance_amt = rs.getString("advance_amt");
			adults = rs.getString("adults");
			no_of_children_below12 = rs.getString("no_of_children_below12");
			tr_id = rs.getString("tr_id");
			adv_bill_id = rs.getString("adv_bill_id");
			charges_realised = rs.getString("charges_realised");
			bill_no = rs.getString("bill_id");
			remarks = rs.getString("remarks");
			status = rs.getString("status");
			shift_id = rs.getString("shift_id");
			shiftuser_id = rs.getString("shiftuser_id");
			parent_trid = rs.getString("parent_trid");
			vc_id = rs.getString("vc_id");
			purpose = rs.getString("purpose");
			l_status= true;
		}
		rs.close();
		ps.close();
			return l_status;
	}
	
	public boolean selectDB_Remove() throws Exception {		
		boolean l_status=false;	
		VAL_selectDB_Remove();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where req_id=? and guest_id in ("+guest_id+") and gh_id=?");
		ps.setString(1, req_id);
		//ps.setString(2, guest_id);
		ps.setString(2, gh_id);
System.out.println("ps REM "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			roll_no = rs.getString("roll_no");
			arrival_time_date = rs.getString("arrival_time_date");
			advance_amt = rs.getString("advance_amt");
			adults = rs.getString("adults");
			no_of_children_below12 = rs.getString("no_of_children_below12");
			tr_id = rs.getString("tr_id");
			adv_bill_id = rs.getString("adv_bill_id");
			charges_realised = rs.getString("charges_realised");
			bill_no = rs.getString("bill_id");
			remarks = rs.getString("remarks");
			status = rs.getString("status");
			shift_id = rs.getString("shift_id");
			shiftuser_id = rs.getString("shiftuser_id");
			parent_trid = rs.getString("parent_trid");
			
			l_status= true;
		}
		rs.close();
		ps.close();
			return l_status;
	}
	
	
	public boolean selectDB_COUT() throws Exception {		
		boolean l_status=false;	
		VAL_selectDB_COUT();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where gh_id=? and req_id=? and guest_id=? and status=?");
		ps.setString(1, gh_id);
		ps.setString(2, req_id);
		ps.setString(3, guest_id);
		ps.setString(4, status);

		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			roll_no = rs.getString("roll_no");
			arrival_time_date = rs.getString("arrival_time_date");
			departure_time_date = rs.getString("departure_time_date");
			advance_amt = rs.getString("advance_amt");
			adults = rs.getString("adults");
			no_of_children_below12 = rs.getString("no_of_children_below12");
			tr_id = rs.getString("tr_id");
			adv_bill_id = rs.getString("adv_bill_id");
			charges_realised = rs.getString("charges_realised");
			bill_no = rs.getString("bill_id");
			remarks = rs.getString("remarks");
			status = rs.getString("status");
			shift_id = rs.getString("shift_id");
			shiftuser_id = rs.getString("shiftuser_id");
			parent_trid = rs.getString("parent_trid");
			vc_id = rs.getString("vc_id");
			purpose = rs.getString("purpose");
			l_status= true;
		}
		rs.close();
		ps.close();
			return l_status;
	}
	
	public boolean selectDBstatus() throws Exception {		
		boolean l_status=false;	
		VAL_selectDBstatus();
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where req_id=? and guest_id=? and status!='CAN'");
		ps.setString(1,req_id);
		ps.setString(2,guest_id);
		System.out.println("ps gop "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			l_status= true;
			status = rs.getString("status");
		}
		rs.close();
		ps.close();
			return l_status;
	}
	
	
		
	public boolean cancelcan() throws Exception {		
		boolean l_status=false;	
		VAL_cancelcan();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update tr_checkin set status='CAN',lastmodified=now() where req_id=? and gh_id=? and status!='CAN' and guest_id in ("+guest_id+")");
		pss.setString(1, req_id);
		pss.setString(2, gh_id);
		System.out.println("ps...... cancelcan "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelcheckout() throws Exception {		
		boolean l_status=false;	
		VAL_cancelcheckout();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update tr_checkin set departure_time_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),lastmodified=now() where req_id=? and guest_id in ("+guest_id+") and status!='CAN'");
		pss.setString(1, departure_time_date);
		pss.setString(2, req_id);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	public boolean cancelCheckoutguest() throws Exception {		
		boolean l_status=false;	
		//VAL_cancelCheckoutguest();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update tr_checkin set  status='CIN',departure_time_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),lastmodified=now() where guest_id in ("+guest_id+") and status='COUT'");
		pss.setString(1, departure_time_date);
		System.out.println(" pss cancelcheckout : "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
	
	public boolean cancelcann() throws Exception {		
		boolean l_status=false;	
		VAL_cancelcann();
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update tr_checkin set status='COUT', departure_time_date=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),lastmodified=now() where req_id=? and gh_id=? and status='CIN' and guest_id in ("+guest_id+")");
		pss.setString(1, departure_time_date); 
		pss.setString(2, req_id);
		pss.setString(3, gh_id);
		System.out.println("3333333333      "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		System.out.println("3333333333  l_status  "+l_status);
		pss.close();
		return l_status;		
	} 
	
	public void setpost_upload(JSONObject jsonObj)throws Exception{
		 System.out.println("jsong objeecct"+jsonObj);
		 this.req_id =  ins_util.setNullvalue(jsonObj.get("req_id"));
		 this.tr_id = ins_util.setNullvalue(jsonObj.get("tr_id"));
		 this.roll_no = ins_util.setNullvalue(jsonObj.get("roll_no"));
		 this.guest_id =ins_util.setNullvalue(jsonObj.get("guest_id"));
		 this.arrival_time_date = ins_util.setNullvalue(jsonObj.get("arrival_time_date"));
		 this.departure_time_date = ins_util.setNullvalue(jsonObj.get("departure_time_date"));
		 this.advance_amt = ins_util.setNullvalue(jsonObj.get("advance_amt"));
 		 this.charges_realised =  ins_util.setNullvalue(jsonObj.get("charges_realised"));

		 this.bill_no =  ins_util.setNullvalue(jsonObj.get("bill_id"));

		 this.remarks =  ins_util.setNullvalue(jsonObj.get("remarks"));

		 this.status =  ins_util.setNullvalue(jsonObj.get("status"));

		 this.no_of_children_below12 =  ins_util.setNullvalue(jsonObj.get("no_of_children_below12"));

		 this.shift_id =  ins_util.setNullvalue(jsonObj.get("shift_id"));
		 this.shiftuser_id =  ins_util.setNullvalue(jsonObj.get("shiftuser_id"));

		 this.adults =  ins_util.setNullvalue(jsonObj.get("adults"));
		 this.gh_id = ins_util.setNullvalue(jsonObj.get("gh_id"));
		 this.adv_bill_id = ins_util.setNullvalue(jsonObj.get("adv_bill_id"));
		 

			 
	}
	public void existdata()throws Exception{
		
		 VAL_existdata();
		//PreparedStatement ps=NGM.tr_conn.prepareStatement("Insert into test_mod values(?,?,?,?,?,?,?,now())");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where tr_id=?");
		ps.setString(1, tr_id);
 		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("delete from tr_checkin where tr_id=?");
			ps1.setString(1, tr_id);
			ps1.execute();
			ps1.close();
			 
		}
		rs.close();
		ps.close();	 
	 
		
	}
	public boolean selectimportdata(String time)throws Exception{ 
		boolean retval = false;
		System.out.println("importdata.........................");
			JSONArray jobj = new JSONArray();
			String tabletime = NGM.ins_ngmprop.getpropvalue("TABLEUPDATE");
//			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_request where last_modified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS'))- interval '2 hours' ");
		// 
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_checkin where lastmodified > ( to_timestamp (?, 'YYYY-MM-DD HH24:MI:SS')- interval '2 hours') ");
			ps.setString(1,time);
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
	
	public String selectDB_shiftAdv() throws Exception {		
		boolean l_status=false;	
		VAL_selectDB_shiftAdv();
		String sum_adv="";
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(advance_amt::Integer) from tr_checkin where shift_id=? and gh_id=? and status!='CAN'");
		ps.setString(1, shift_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			sum_adv = rs.getString("sum");
			l_status= true;
		}
		rs.close();
		ps.close();
		return sum_adv;
	}
	
			 
	//-------------------------------------validation------------------------------------------------------------------------	

	public void VAL_insertDB() throws Exception 
	{
		//String[] t_departure = departure_time_date.split(" ");
		String[] t_arrival   = arrival_time_date.split(" ");
		if(!(val_seq_id(adv_bill_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_shiftid(shift_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_seq_id(adults))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_seq_id(no_of_children_below12))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_bill_id(adv_bill_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_reqid(req_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_seq_id(advance_amt))){//val_departuretimedate(departure_time_date)&&val_arrivaltimedate(arrival_time_date)&&
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_guestid(guest_id))){//val_departuretimedate(departure_time_date)&&val_arrivaltimedate(arrival_time_date)&&
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_seq_id(roll_no))){//val_departuretimedate(departure_time_date)&&val_arrivaltimedate(arrival_time_date)&&
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_seq_id(tr_id))){//val_departuretimedate(departure_time_date)&&val_arrivaltimedate(arrival_time_date)&&
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		if(!(val_date(t_arrival[0]))){//val_departuretimedate(departure_time_date)&&val_arrivaltimedate(arrival_time_date)&&
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
	}
	public void VAL_updateDB() throws Exception 
	{
		String[] t_departure = departure_time_date.split(" ");
		String[] t_arrival   = arrival_time_date.split(" ");
		if(!(val_status(status)&&val_shiftid(shift_id)&&val_seq_id(adults)&&val_seq_id(no_of_children_below12)&&val_date(t_departure[0])&&val_date(t_arrival[0])&&val_bill_id(bill_no)&&val_chargesid(charges_realised)&&val_reqid(req_id)&&val_seqid(advance_amt)&&val_guestid(guest_id)&&val_seqid(roll_no)&&val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
	}
	  
	public void VAL_selectDB_prev_ext() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_guestid(guest_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_guestid(guest_id);
		 //val_reqid(req_id);
		 //val_ghid(gh_id);
	}
	public void VAL_selectDB_Remove() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_reqid(req_id);
		 //val_ghid(gh_id);
	}
	public void VAL_selectDB_COUT() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_ghid(gh_id)&&val_guestid(guest_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_reqid(req_id);
		 //val_ghid(gh_id);
		 //val_guestid(guest_id);
		 //val_status(status);
	}
	public void VAL_selectDBstatus() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_guestid(guest_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_reqid(req_id);
		 //val_guestid(guest_id);
	}
	public void VAL_cancelcan() throws Exception 
	{
		if(!(val_reqid(req_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_reqid(req_id);
		 //val_ghid(gh_id);
	}
	public void VAL_cancelcheckout() throws Exception 
	{
		String[] t_departure = departure_time_date.split(" ");
		if(!(val_reqid(req_id)&&val_date(t_departure[0]))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_reqid(req_id);
		 //val_departure(departure_time_date);
	}
	public void VAL_cancelCheckoutguest() throws Exception 
	{
		String[] t_departure = departure_time_date.split(" ");
		if(!(val_date(t_departure[0]))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_departure(departure_time_date);
	}
	public void VAL_cancelcann() throws Exception 
	{
		String[] t_departure = departure_time_date.split(" ");
		if(!(val_reqid(req_id)&&val_date(t_departure[0])&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
	}
	public void VAL_existdata() throws Exception 
	{
		if(!(val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_seq_id(tr_id);	
	}
	
	public void VAL_updateDB_status() throws Exception 
	{
		if(!(val_guestid(guest_id)&&val_reqid(req_id)&&val_status(status))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 /*val_guestid(guest_id);
		 val_reqid(req_id);
		 val_status(status);*/
	}
	
	public void VAL_deletecheckin() throws Exception 
	{
		if(!(val_seq_id(tr_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_seq_id(tr_id);
	}
	public void VAL_selectDB() throws Exception 
	{
		if(!(val_seq_id(tr_id))){
			System.out.println("------------------------------------------ tr_id "+tr_id);
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		 //val_seq_id(tr_id);
	}
	public void VAL_selectDB_shiftAdv() throws Exception 
	{
		if(!(val_seq_id(shift_id)&&val_ghid(gh_id))){
			throw NGM.ins_utility.setException("tr_checkin", "Invalid tr_checkin parameter", "VAL_insertDB", NGM);
		}
		// val_ghid(gh_id);
		// val_shiftid(shift_id);
	}
	
	
}