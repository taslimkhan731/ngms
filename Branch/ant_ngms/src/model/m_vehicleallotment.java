package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;
import db.*;

public class m_vehicleallotment extends ngmodel{
	String date= "";
	private static final String HttpServletRequest = null;
	utility 		ins_util;
	common ins_con;
	d_vehicleallotment ins_vc;
	d_vehicle ins_vehicle;
	d_vehicle1 ins_vehicle1;
	d_sms_alltment ins_sms_allot;
	public m_vehicleallotment(ngm p_ngm){
		NGM		= p_ngm;
		ins_con=new common(NGM);
		ins_util		= NGM.ins_utility;
		ins_vc  = new d_vehicleallotment(NGM);
		ins_sms_allot = new d_sms_alltment(NGM);
		ins_vehicle = new d_vehicle(NGM);
		ins_vehicle1 = new d_vehicle1(NGM);
	}

	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		String test = ins_util.setNullParameter(p_request, "is_veh");
		String test1 = ins_util.setNullParameter(p_request, "is_veh1");
		String sel_block = ins_util.setNullParameter(p_request, "sel_block");
		ins_vc.req_id=ins_util.setNullParameter(p_request, "a_reqid");
		ins_vc.block_id = sel_block;
		ins_vc.gh_id=p_request.getParameter("gh_id");
		ins_vc.status="AAA";

		if(test.equals("1")){
			String v_reqid = NGM.ins_utility.get_seqID(NGM.tr_conn, "v_allot").toString(); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "v_allot", Integer.parseInt(v_reqid));
			ins_vc.vehicle_reqid=v_reqid;
			ins_vc.vehicle_details=ins_util.setNullParameter(p_request, "b_vehicledetails");
			ins_vc.vehicle_no=ins_util.setNullParameter(p_request, "c_vehicleno");
			ins_vc.driver_name=ins_util.setNullParameter(p_request, "d_drivername");
			ins_vc.driver_mobileno=ins_util.setNullParameter(p_request, "e_drivermobileno");
			
			ins_vc.protocol_name=ins_util.setNullParameter(p_request, "d_protocolname");
			ins_vc.protocol_mobileno=ins_util.setNullParameter(p_request, "e_protocolmobileno");
			
			ins_vc.time_of_report=ins_util.setNullParameter(p_request, "f_timeofreport");
			ins_vc.place_of_report=ins_util.setNullParameter(p_request, "g_placeofreport");
			ins_vc.vehicle_start_time=ins_util.setNullParameter(p_request, "h_pick");
			ins_vc.vehicle_end_time=ins_util.setNullParameter(p_request, "i_releave");
			date = ins_vc.time_of_report;
			try {
				ins_vehicle.req_id = ins_vc.req_id;
				ins_vehicle.selectVehicleReq();
				ins_vc.mode = "A";
				if(!ins_vc.insertDB()){return false;}
				else
					send_sms_insert(ins_vc.gh_id,"N","A");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(test1.equals("1")){
			String v_reqid = NGM.ins_utility.get_seqID(NGM.tr_conn, "v_allot").toString(); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "v_allot", Integer.parseInt(v_reqid));
			ins_vc.vehicle_reqid=v_reqid;
			ins_vc.vehicle_details=ins_util.setNullParameter(p_request, "vehicledetails");
			ins_vc.vehicle_no=ins_util.setNullParameter(p_request, "vehicleno");
			ins_vc.driver_name=ins_util.setNullParameter(p_request, "drivername");
			ins_vc.driver_mobileno=ins_util.setNullParameter(p_request, "drivermobileno");
			
			ins_vc.protocol_name=ins_util.setNullParameter(p_request, "protocolname");
			ins_vc.protocol_mobileno=ins_util.setNullParameter(p_request, "protocolmobileno");
			
			ins_vc.time_of_report=ins_util.setNullParameter(p_request, "arr1");
			ins_vc.place_of_report=ins_util.setNullParameter(p_request, "arrp1");
			ins_vc.vehicle_start_time=ins_util.setNullParameter(p_request, "t_pick");
			ins_vc.vehicle_end_time=ins_util.setNullParameter(p_request, "t_releave");
			date = ins_vc.vehicle_end_time;
			System.out.println("Test = "+test +",,"+test1+",,,,,"+sel_block);
			System.out.println("PICK TIME******************************INSERT************************************"+ins_vc.vehicle_start_time);
			// AAA - alloted CAN - cancelled  USE - Used 
			try {
				ins_vehicle1.req_id = ins_vc.req_id;
				ins_vehicle1.selectVehicleReq();
				ins_vc.mode = "D";
				if(!ins_vc.insertDB()){return false;}
				else
					send_sms_insert(ins_vc.gh_id,"N","D");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return true;
	}




	public boolean send_sms_insert(String pghid, String pmode,String status) throws Exception{
		ins_sms_allot.req_id = ins_vc.req_id;
		ins_sms_allot.gh_id =pghid;

		if(pmode =="N" || pmode =="E")
		{
			ins_sms_allot.mode = "VALTG";
			ins_sms_allot.selectDB_status();
			ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
			if(ins_sms_allot.status!=null && !ins_sms_allot.status.equals("P")){
				System.out.println("Inside");    
				ins_sms_allot.can_all_pending();
				add_smsguest(pghid,"VALTG");
			}
			else
				update_smsguest(pghid,"VALTG");

			ins_sms_allot.mode = "VALTV";
			ins_sms_allot.selectDB_status();
			ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
			if(ins_sms_allot.status!=null && !ins_sms_allot.status.equals("P")){
				ins_sms_allot.can_all_pending();
				add_smsvehicle(pghid,"VALTV");
			}
			else {
				update_smsvehicle(pghid,"VALTV");
			}
			
			ins_sms_allot.mode = "VALTP";
			ins_sms_allot.selectDB_status();
			ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
			if(ins_sms_allot.status!=null && !ins_sms_allot.status.equals("P")){
				ins_sms_allot.can_all_pending();
				add_sms_protocol(pghid,"VALTP",status);
			}
			else {
				update_sms_protocol(pghid,"VALTP",status);
			}
		}

		else if(pmode=="D")
		{
			ins_sms_allot.mode = "VALTG";
			ins_sms_allot.selectDB_status();
			ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
			if(ins_sms_allot.status!=null && ins_sms_allot.status.equals("S")){
				//ins_sms_allot.updateDB_guest();
				add_smsguest(pghid,"CVALTG");
			}
			else
			{
				cancel_smsvehicle(pghid);
			}
			ins_sms_allot.mode = "VALTV";
			ins_sms_allot.selectDB_status();
			ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
			if( ins_sms_allot.status!=null && ins_sms_allot.status.equals("S")){
				add_smsvehicle(pghid,"CVALTV");
				//ins_sms_allot.updateDB_guest();
			}
			else
			{
				cancel_smsvehicle(pghid);
			}
		}	
		return true;
	}

	public boolean cancel_smsvehicle(String pghid) throws Exception {
		ins_sms_allot.status="D";
		if(ins_sms_allot.updateDB())
			return false;
		return true;
	}

	public boolean add_smsguest(String pghid, String pmode) throws Exception {
		d_sms_alltment temp_ins_sms_allot = new d_sms_alltment(NGM);
		String id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(id));

		temp_ins_sms_allot.a_sms_id="G"+id; //G_
		temp_ins_sms_allot.req_id = ins_vc.req_id;
		temp_ins_sms_allot.gh_id =pghid;

		d_guestdetails temp_ins_guest=new d_guestdetails(NGM);
		temp_ins_guest.req_id = ins_vc.req_id;
		String mob = temp_ins_guest.selectDB_guestDetails(ins_sms_allot.gh_id);
		// String sub=ins_vc.selectDB_getplacedetails(ins_sms_allot.gh_id); 
		temp_ins_sms_allot.mobile_no =  mob;
		temp_ins_sms_allot.guest_details = temp_ins_guest.getGuestDetail();
		try {
			if(temp_ins_sms_allot.guest_details.equalsIgnoreCase("null")) {
				temp_ins_sms_allot.guest_details = null;
			}
		} catch (Exception e) {}
		temp_ins_sms_allot.subject = "Protocol Officer " + ins_vc.protocol_name + " Mob " + ins_vc.protocol_mobileno + " will receive you at " + ins_vc.getplacedetails() +" along with Vehicle No. " + ins_vc.getvehicledetail()+", Odisha Home Dept";
		if(pmode != null) {
			if(pmode.equalsIgnoreCase("CVALTG")) {
				temp_ins_sms_allot.subject = "Dear " + (temp_ins_sms_allot.guest_details == null ? " Guest " : temp_ins_sms_allot.guest_details) + " Your Vehicle Request Is Canceled";
			}
		}
		temp_ins_sms_allot.mode = pmode ;
		temp_ins_sms_allot.status = "P";
		System.out.println("Jayant3"+ins_vc.time_of_report);
		temp_ins_sms_allot.exp_time=date;
		if(temp_ins_sms_allot.insertDB()){
			String templateId="1107160707746117446";
			sendVerificationCode(mob,temp_ins_sms_allot.subject,templateId);
			return false;
		}
		else{
			return true;
		}
	}
	public boolean sendVerificationCode(String mob,String subject,String templateId)throws Exception {	
		//ok
		NGM.ins_utility.SENDSMS(mob, subject, "NGM",templateId);//this.ins_user.userid
		return true;
	}
	public boolean add_smsvehicle(String pghid, String pmode) throws Exception {
		d_sms_alltment temp_ins_sms_allot = new d_sms_alltment(NGM);
		String id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(id));

		temp_ins_sms_allot.a_sms_id="D_"+id;
		temp_ins_sms_allot.req_id = ins_vc.req_id;
		temp_ins_sms_allot.gh_id =pghid;
		d_guestdetails temp_ins_guest=new d_guestdetails(NGM);
		temp_ins_guest.req_id = ins_vc.req_id;
		temp_ins_sms_allot.mobile_no =ins_vc.driver_mobileno;
		String mob = temp_ins_guest.selectDB_guestDetails(ins_sms_allot.gh_id);
		temp_ins_sms_allot.guest_details =temp_ins_guest.getGuestDetail();
		temp_ins_sms_allot.mode =  pmode;
		temp_ins_sms_allot.status = "P";
		//	    System.out.println("Jayant2"+ins_con.display_date(ins_vc.time_of_report));
		temp_ins_sms_allot.exp_time=date;
		ins_vc.selectDB();
		try {
			if(temp_ins_sms_allot.guest_details.equalsIgnoreCase("null")) {
				temp_ins_sms_allot.guest_details = null;
			}
		} catch (Exception e) {}
		temp_ins_sms_allot.subject = (temp_ins_guest.name == null ? "": temp_ins_guest.name ) +  " will arrive at " + ins_vc.getplacedetails() + ". Contact Protocol officer " + ins_vc.protocol_name + " Mob " + ins_vc.protocol_mobileno+", Odisha Home Dept";
		if(temp_ins_sms_allot.insertDB()){
			String templateId="1107160707757247742";
			sendVerificationCode(temp_ins_sms_allot.mobile_no,temp_ins_sms_allot.subject,templateId);
			return false;
		}

		else{
			return true;
		}
	}
	
	public boolean add_sms_protocol(String pghid, String pmode, String status) throws Exception {
		d_sms_alltment temp_ins_sms_allot = new d_sms_alltment(NGM);
		String id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(id));

		temp_ins_sms_allot.a_sms_id="P_"+id;
		temp_ins_sms_allot.req_id = ins_vc.req_id;
		temp_ins_sms_allot.gh_id =pghid;
		d_guestdetails temp_ins_guest=new d_guestdetails(NGM);
		temp_ins_guest.req_id = ins_vc.req_id;
		temp_ins_sms_allot.mobile_no =ins_vc.protocol_mobileno;
		String mob = temp_ins_guest.selectDB_guestDetails(ins_sms_allot.gh_id);
		temp_ins_sms_allot.guest_details =temp_ins_guest.getGuestDetail();
		temp_ins_sms_allot.mode =  pmode;
		temp_ins_sms_allot.status = "P";
		//	    System.out.println("Jayant2"+ins_con.display_date(ins_vc.time_of_report));
		temp_ins_sms_allot.exp_time=date;
		ins_vc.selectDB();
		String ftno = "";
		if(status != null) {
			if(status.equalsIgnoreCase("A")) {
				ins_vehicle.req_id = ins_vc.req_id;
				ins_vehicle.selectVehicleReq();
				try {
					if(ins_vehicle.mode_of_travel.equalsIgnoreCase("T")) {
						ftno = " by Train No " + ins_vehicle.ftno;
					}else {
						ftno = " by Flight No " + ins_vehicle.ftno;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else if(status.equalsIgnoreCase("D")) {
				ins_vehicle1.req_id = ins_vc.req_id;
				ins_vehicle1.selectVehicleReq();
				try {
					if(ins_vehicle1.mode_of_travel.equalsIgnoreCase("T")) {
						ftno = " by Train No " + ins_vehicle1.ftno;
					}else {
						ftno = " by Flight No " + ins_vehicle1.ftno;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		try {
			if(temp_ins_sms_allot.guest_details.equalsIgnoreCase("null")) {
				temp_ins_sms_allot.guest_details = null;
			}
		} catch (Exception e) {}
		temp_ins_sms_allot.subject = (temp_ins_guest.name == null ? "" : temp_ins_guest.name ) + " will arrive at " + ins_vc.place_of_report + "by flight no" + ftno + " on " + ins_vc.time_of_report + " Vehicle allocated " + ins_vc.getvehicledetail()+", Odisha Home Dept";
		if(temp_ins_sms_allot.insertDB()){
			String templateId="1107160707769787743";
			sendVerificationCode(temp_ins_sms_allot.mobile_no,temp_ins_sms_allot.subject,templateId);
			return false;
		}

		else{
			return true;
		}

	}
	
	public boolean update_smsguest(String pghid, String pmode) throws Exception {
		d_sms_alltment temp_ins_sms_allot = new d_sms_alltment(NGM);
		temp_ins_sms_allot.req_id = ins_vc.req_id;
		temp_ins_sms_allot.gh_id =pghid;
		d_guestdetails ins_guest=new d_guestdetails(NGM);
		ins_guest.req_id = ins_vc.req_id;
		String mob = ins_guest.selectDB_guestDetails(ins_sms_allot.gh_id);
		temp_ins_sms_allot.mobile_no =  mob;
		temp_ins_sms_allot.guest_details = ins_guest.getGuestDetail();
		try {
			if(temp_ins_sms_allot.guest_details.equalsIgnoreCase("null")) {
				temp_ins_sms_allot.guest_details = null;
			}
		} catch (Exception e) {}
		temp_ins_sms_allot.subject = "Protocol Officer " + ins_vc.protocol_name + " Mob " + ins_vc.protocol_mobileno + " will receive you at " + ins_vc.getplacedetails() + " along with Vehicle No. " + ins_vc.getvehicledetail();
		temp_ins_sms_allot.mode = pmode ;
		temp_ins_sms_allot.status = "P";
		System.out.println("Jayant1"+ins_vc.time_of_report);
		temp_ins_sms_allot.exp_time=date;
		if(temp_ins_sms_allot.updateSubject()){
			String templateId="1107160707746117446";
			sendVerificationCode(mob,temp_ins_sms_allot.subject,templateId);
			return false;
		}
		else{
			return true;
		}

	}


	public boolean update_smsvehicle(String pghid, String pmode) throws Exception {
		d_sms_alltment temp_ins_sms_allot = new d_sms_alltment(NGM);
		temp_ins_sms_allot.req_id = ins_vc.req_id;
		temp_ins_sms_allot.gh_id =pghid;
		d_guestdetails ins_guest=new d_guestdetails(NGM);
		ins_guest.req_id = ins_vc.req_id;
		String mob = ins_guest.selectDB_guestDetails(ins_sms_allot.gh_id);
		temp_ins_sms_allot.mobile_no =ins_vc.driver_mobileno;
		temp_ins_sms_allot.mode =  pmode;
		temp_ins_sms_allot.status = "P";
		System.out.println("Jayant"+ins_vc.time_of_report);
		temp_ins_sms_allot.exp_time=date;
		try {
			if(temp_ins_sms_allot.guest_details.equalsIgnoreCase("null")) {
				temp_ins_sms_allot.guest_details = null;
			}
		} catch (Exception e) {}
		temp_ins_sms_allot.subject = (ins_guest.name == null ? " Guest " : ins_guest.name ) + " Mob "+mob + " will arrive at " + ins_vc.getplacedetails() + " Contact Protocol Officer " + ins_vc.protocol_name + " Mob " + ins_vc.protocol_mobileno;
		if(temp_ins_sms_allot.updateSubject())
		{
			String templateId="1107160707757247742";
			sendVerificationCode(temp_ins_sms_allot.mobile_no,temp_ins_sms_allot.subject,templateId);
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean update_sms_protocol(String pghid, String pmode, String status) throws Exception {
		d_sms_alltment temp_ins_sms_allot = new d_sms_alltment(NGM);
		temp_ins_sms_allot.req_id = ins_vc.req_id;
		temp_ins_sms_allot.gh_id =pghid;
		d_guestdetails ins_guest=new d_guestdetails(NGM);
		ins_guest.req_id = ins_vc.req_id;
		String mob = ins_guest.selectDB_guestDetails(ins_sms_allot.gh_id);
		temp_ins_sms_allot.mobile_no =ins_vc.protocol_mobileno;
		temp_ins_sms_allot.mode =  pmode;
		temp_ins_sms_allot.status = "P";
		System.out.println("Jayant"+ins_vc.time_of_report);
		temp_ins_sms_allot.exp_time=date;
		String ftno ="";
		if(status != null) {
			if(status.equalsIgnoreCase("A")) {
				ins_vehicle.req_id = ins_vc.req_id;
				ins_vehicle.selectVehicleReq();
				try {
					if(ins_vehicle.mode_of_travel.equalsIgnoreCase("T")) {
						ftno = " by Train No " + ins_vehicle.ftno;
					}else {
						ftno = " by Flight No " + ins_vehicle.ftno;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else if(status.equalsIgnoreCase("D")) {
				ins_vehicle1.req_id = ins_vc.req_id;
				ins_vehicle1.selectVehicleReq();
				try {
					if(ins_vehicle1.mode_of_travel.equalsIgnoreCase("T")) {
						ftno = " by Train No " + ins_vehicle1.ftno;
					}else {
						ftno = " by Flight No " + ins_vehicle1.ftno;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		try {
			if(temp_ins_sms_allot.guest_details.equalsIgnoreCase("null")) {
				temp_ins_sms_allot.guest_details = null;
			}
		} catch (Exception e) {}
		temp_ins_sms_allot.subject = (ins_guest.name == null ? " Guest " : ins_guest.name ) + " Mob "+mob + " will arrive at " + ins_vc.place_of_report + " " + ftno + " on " + ins_vc.time_of_report + " Vehicle allotted " + ins_vc.getvehicledetail();
		if(temp_ins_sms_allot.updateSubject())
		{
			String templateId="1107160707769787743";
			sendVerificationCode(temp_ins_sms_allot.mobile_no,temp_ins_sms_allot.subject,templateId);
			return false;
		}
		else{
			return true;
		}
	}



	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_vc.req_id=ins_util.setNullParameter(p_request, "req_id");
		if(!ins_vc.chkCodeDB()) 
			return false;
		else  
			return true;	
	}
	public boolean chkvehicle_time(HttpServletRequest p_request) throws Exception {
		System.out.println("shaik1");
		ins_vc.vehicle_no=ins_util.setNullParameter(p_request, "c_vehicleno");
		ins_vc.time_of_report=ins_util.setNullParameter(p_request, "f_timeofreport");
		ins_vc.vehicle_end_time=ins_util.setNullParameter(p_request, "i_releave");
		ins_vc.vehicle_start_time=ins_util.setNullParameter(p_request, "h_pick");
		ins_vc.req_id=ins_util.setNullParameter(p_request, "req_id");

		if(!ins_vc.chkvehicle_time_ok()) 
			return false;
		else  
			return true;	
	}
	public boolean chkvehicle_time_add(HttpServletRequest p_request) throws Exception {
		String test = ins_util.setNullParameter(p_request, "is_veh");
		String test1 = ins_util.setNullParameter(p_request, "is_veh1");
		System.out.println("test"+test+",,,,"+test1);

		if(test.equals("1")){
			ins_vc.vehicle_no=ins_util.setNullParameter(p_request, "c_vehicleno");
			ins_vc.time_of_report=ins_util.setNullParameter(p_request, "f_timeofreport");
			ins_vc.vehicle_end_time=ins_util.setNullParameter(p_request, "i_releave");
			ins_vc.vehicle_start_time=ins_util.setNullParameter(p_request, "h_pick");
			ins_vc.req_id=ins_util.setNullParameter(p_request, "a_reqid");	 
			if(!ins_vc.chkvehicle_time_ok()) 
				return false;
			else{
				return true;
			}
		}
		if(test1.equals("1")){
			ins_vc.vehicle_no=ins_util.setNullParameter(p_request, "vehicleno");
			ins_vc.time_of_report=ins_util.setNullParameter(p_request, "arr1");
			ins_vc.vehicle_end_time=ins_util.setNullParameter(p_request, "t_releave");
			ins_vc.vehicle_start_time=ins_util.setNullParameter(p_request, "t_pick");
			ins_vc.req_id=ins_util.setNullParameter(p_request, "a_reqid");	 
			if(!ins_vc.chkvehicle_time_ok()) 
				return false;
			else{
				return true;
			}
		} 
		return false;
	}

	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		String status = "";
		ins_vc.req_id=ins_util.setNullParameter(p_request, "req_id");
		ins_vc.vehicle_reqid = ins_util.setNullParameter(p_request, "vehicle_reqid");
		ins_vc.vehicle_details=ins_util.setNullParameter(p_request, "b_vehicledetails");
		ins_vc.vehicle_no=ins_util.setNullParameter(p_request, "c_vehicleno");
		ins_vc.driver_name=ins_util.setNullParameter(p_request, "d_drivername");
		ins_vc.driver_mobileno=ins_util.setNullParameter(p_request, "e_drivermobileno");
		String ss=ins_util.setNullParameter(p_request, "f_timeofreport");
		String gh_id=ins_util.setNullParameter(p_request , "gh_id");
		
		ins_vc.protocol_name=ins_util.setNullParameter(p_request, "d_protocolname");
		ins_vc.protocol_mobileno=ins_util.setNullParameter(p_request, "e_protocolmobileno");
		status=ins_util.setNullParameter(p_request, "mode");
		ins_vc.mode = status;
		
		date = ss;
		System.out.println(date);
		ins_vc.time_of_report=ins_con.display_date_vahicle(ss);
		ins_vc.place_of_report=ins_util.setNullParameter(p_request, "g_placeofreport");
		ins_vc.status="AAA";
		//			String gh_id=p_request.getParameter("gh_id");
		ins_vc.gh_id=gh_id;
		String pick=ins_util.setNullParameter(p_request, "h_pick");
		ins_vc.vehicle_start_time=ins_con.display_date_vahicle(pick);
		String releave=ins_util.setNullParameter(p_request, "i_releave");
		ins_vc.vehicle_end_time=ins_con.display_date_vahicle(releave);

		System.out.println("Shaik 3333 "+ ins_vc.vehicle_reqid);

		try {
			if(!ins_vc.insertDB()){return false;}
			else
				send_sms_insert(ins_vc.gh_id,"E",status);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_vc.req_id=ins_util.setNullParameter(p_request, "req_id");
		//ins_vc.selectDB();
		ins_sms_allot.req_id=ins_util.setNullParameter(p_request, "req_id");
		String gh_id=ins_util.setNullParameter(p_request, "gh_id");
		ins_vc.gh_id = gh_id;
		ins_vc.vehicle_reqid = ins_util.setNullParameter(p_request, "vehicle_reqid");
		if(!ins_vc.cancelDB()){return false;}
		else{
			send_sms_insert(gh_id,"D","A");
		}
		return true;
	}
}	




