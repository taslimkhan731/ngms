package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;

public class m_processing extends ngmodel{
	utility 		ins_util;
	d_mod_req_room ins_mod;
	d_mod_req ins_req;
	d_gadrequest ins_gadrequest;
	ArrayList <d_guestdetails> ins_guest = new ArrayList<d_guestdetails>();
	d_guestdetails ins_guestdetails;
	d_vehicle ins_vehicle;
	d_vehicle1 ins_vehicle1;
	d_quota_block ins_quotablk;
	d_reservation_maintable ins_res;
	d_reservation_waitinglist ins_wl;
	d_cancel_reservation ins_cancel;
	d_process_table ins_process;
	d_repair_block ins_repair;
	common ins_common;

	public m_processing(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_mod		= new d_mod_req_room(NGM);
		ins_req =new d_mod_req(NGM);
		ins_gadrequest=new d_gadrequest(NGM);
		ins_guestdetails=new d_guestdetails(NGM);
		ins_vehicle = new d_vehicle(NGM);
		ins_vehicle1 = new d_vehicle1(NGM);

		ins_quotablk = new d_quota_block(NGM);
		ins_res = new d_reservation_maintable(NGM);
		ins_wl = new d_reservation_waitinglist(NGM);
		ins_cancel=new d_cancel_reservation(NGM);
		ins_process = new d_process_table(NGM);
		ins_repair = new d_repair_block(NGM);
		ins_common = new common(NGM);
	}

	public void VJ_createlist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "createlist", NGM);
		}
	}
	public void VJ_createdraftlist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "createdraftlist", NGM);
		}
	}
	public void VJ_createlistconformation(String ppage_no_s,String pgh_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_pageno(ppage_no_s)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "createlistconformation", NGM);
		}
	}
	public void VJ_conformationforshow(String ppro_id) throws Exception
	{
		if(!NGM.ins_dbval.val_pro_id(ppro_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "conformationforshow", NGM);
		}
	}
	public void VJ_acceptprocess(String ppro_id) throws Exception
	{
		if(!NGM.ins_dbval.val_pro_id(ppro_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "acceptprocess", NGM);
		}
	}
	public void VJ_searchconformlist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "searchconformlist", NGM);
		}
	}
	public void VJ_conformationshow(String ppro_id) throws Exception
	{
		if(!NGM.ins_dbval.val_pro_id(ppro_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_processing", "Validation fails", "conformationshow", NGM);
		}
	}

	public boolean setPostgadrequest1(HttpServletRequest p_request) throws Exception{ 
		//String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
		// NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
		// ins_gadrequest.req_id ="G"+req_id1; //G_
		ins_gadrequest.req_id=ins_util.setNullParameter(p_request, "req_id");
		ins_gadrequest.gh_id    						= ins_util.setNullParameter(p_request, "sel_id");
		ins_gadrequest.block_id    						= ins_util.setNullParameter(p_request, "sel_block");
		ins_gadrequest.from_date    					= ins_util.setNullParameter(p_request, "from");
		System.out.println("from date         222222222        "+ins_gadrequest.from_date);
		ins_gadrequest.to_date    					= ins_util.setNullParameter(p_request, "to");
		ins_gadrequest.mobile_no   = ins_util.setNullParameter(p_request, "mobile");
		ins_gadrequest.email_id   = ins_util.setNullParameter(p_request, "email");
		ins_gadrequest.is_veh_req    				= ins_util.setNullParameter(p_request, "checkedval");
		ins_gadrequest.is_veh_req1    				= ins_util.setNullParameter(p_request, "checkedval1");
		ins_gadrequest.address1    					    = ins_util.setNullParameter(p_request, "addressoffice");
		ins_gadrequest.duringstay                   = ins_util.setNullParameter(p_request, "duringstay");
		System.out.println("ins_gadrequest.address1  "+ins_gadrequest.address1  );
		ins_gadrequest.ext    				= ins_util.setNullParameter(p_request, "extval");
		if(ins_gadrequest.ext.equals("1")){
			ins_gadrequest.existing_req_id    				= ins_util.setNullParameter(p_request, "exist_req_id");
		}else{
			ins_gadrequest.existing_req_id 					= null;
		}
		ins_gadrequest.category						= ins_util.setNullParameter(p_request, "category");
		ins_gadrequest.desg					=  ins_util.setNullParameter(p_request, "desg");
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.family= ins_util.setNullParameter(p_request, "familyval");

		ins_gadrequest.nor_req    					= ins_util.setNullParameter(p_request, "rooms");
		ins_gadrequest.nob_g_req    					= ins_util.setNullParameter(p_request, "nbg");
		ins_gadrequest.nob_l_req    					= ins_util.setNullParameter(p_request, "nbl");
		ins_gadrequest.nod_g_req = ins_util.setNullParameter(p_request, "ndg");
		ins_gadrequest.nod_l_req = ins_util.setNullParameter(p_request, "ndl");
		if(!ins_gadrequest.updateDB_gadreq())return false;
		return true;
	}
	public boolean setPostguestdetails1(HttpServletRequest p_request) throws Exception{ 
		System.out.println("INSIDE THE GUEST DETAILS in model");
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "rowCnt"));
		System.out.println("count    "+count);
		ins_guestdetails.req_id			    = ins_gadrequest.req_id;
		if(!ins_guestdetails.deleteguest()) return false;
		for(int i=0;i<count;i++)
		{
			//String guest = "guest";
			String gender="gender";
			System.out.println("iiiiii       "+i);
			String name="name";
			String age="age";
			String desg="desg";
			String dept="dept";
			String address="address";


			ins_guestdetails.req_id				    = ins_gadrequest.req_id;
			//ins_guestdetails.guest_id				= ins_util.setNullParameter(p_request, guest+i);
			ins_guestdetails.name    				= ins_util.setNullParameter(p_request, name+i);
			System.out.println("dsffsdfsdfds"+ins_guestdetails.name);
			ins_guestdetails.age    				= ins_util.setNullParameter(p_request, age+i);
			ins_guestdetails.gender    				= ins_util.setNullParameter(p_request, gender+i);
			ins_guestdetails.designation    		= ins_util.setNullParameter(p_request, desg+i);
			ins_guestdetails.department   			= ins_util.setNullParameter(p_request, dept+i);
			ins_guestdetails.address2    			= ins_util.setNullParameter(p_request, address+i);

			ins_guest.add(ins_guestdetails);
			System.out.println("hfsdhjfhsdjhfjsd");
			if(!ins_guestdetails.insertDB_guest())return false;
		}
		return true;
	}
	public void setPostvehicle1(HttpServletRequest p_request) throws Exception{ 
		ins_vehicle.req_id = ins_gadrequest.req_id;
		ins_vehicle.mode_of_travel=ins_util.setNullParameter(p_request, "mode");
		ins_vehicle.arrival_time=ins_util.setNullParameter(p_request, "arrival");
		ins_vehicle.arrival_place=ins_util.setNullParameter(p_request, "place");
		ins_vehicle.no_seats_req=ins_util.setNullParameter(p_request, "seat");
		ins_vehicle.veh_id=ins_util.setNullParameter(p_request, "vehid");
		ins_vehicle.ftno=ins_util.setNullParameter(p_request, "ftno");
	}

	public void setPostvehicle3(HttpServletRequest p_request) throws Exception{ 
		ins_vehicle1.req_id = ins_gadrequest.req_id;
		ins_vehicle1.mode_of_travel=ins_util.setNullParameter(p_request, "mode1");
		ins_vehicle1.arrival_time=ins_util.setNullParameter(p_request, "arrival1");
		ins_vehicle1.arrival_place=ins_util.setNullParameter(p_request, "place1");
		ins_vehicle1.no_seats_req=ins_util.setNullParameter(p_request, "seat1");
		ins_vehicle1.veh_id=ins_util.setNullParameter(p_request, "vehid1");
		ins_vehicle1.ftno=ins_util.setNullParameter(p_request, "ftno1");
	}
	public boolean UpdateCreation(HttpServletRequest p_request) throws Exception{
		setPostgadrequest1(p_request);
		setPostguestdetails1(p_request);
		setPostvehicle1(p_request);
		setPostvehicle3(p_request);

		if(ins_gadrequest.is_veh_req.equals("1")){
			if(ins_vehicle.selectDB()){
				ins_vehicle.deleteveh();}
			if(!ins_vehicle.insertDB_veh())return false;
		}
		if(ins_gadrequest.is_veh_req.equals("0")){
			if(ins_vehicle.selectDB()){
				if(!ins_vehicle.deleteveh())return false;}
		}

		if(ins_gadrequest.is_veh_req1.equals("1")){
			if(ins_vehicle1.selectDB()){
				ins_vehicle1.deleteveh();}
			if(!ins_vehicle1.insertDB_veh())return false;
		}
		if(ins_gadrequest.is_veh_req1.equals("0")){
			if(ins_vehicle1.selectDB()){
				if(!ins_vehicle1.deleteveh())return false;}
		}
		return true;
	}
	//doAcceptProcess
	public boolean doAcceptProcess(HttpServletRequest p_request) throws Exception{
		Boolean flag = false;
		ins_process.process_id = p_request.getParameter("pro_id");
		System.out.println("pro_idpro_idpro_id    "+ins_process.process_id);
		ins_process.officeorderno	= ins_util.setNullParameter(p_request, "office_order");
		System.out.println("ins_process.officeordernoins_process.officeorderno    "+ins_process.officeorderno);
		ins_process.status="CNF";
		try {
			if(!ins_process.updateDBofcorder()) return false;
			ins_req.process_id = p_request.getParameter("pro_id");
			ins_req.status = "PRO";
			if(!ins_req.updateDBsta()) return false;

			System.out.println("abcccccccccccccccccc");
			ins_res.processid = p_request.getParameter("pro_id");
			//if(ins_res.selectDBpro()){
			PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from reservation_main_table where process_id=?");
			ps.setString(1, ins_res.processid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ins_res.req_id=rs.getString("req_id");

				System.out.println("abcccccccccccccccccc ins_res.selectDBpro()");

				ins_res.processid = p_request.getParameter("pro_id");
				ins_res.status = "CNF";
				if(!ins_res.update_statuspro()) return false;

				ins_gadrequest.req_id = ins_res.req_id;
				ins_gadrequest.status = "PRO";
				if(!ins_gadrequest.update_status()) return false;
			}
			//close
			rs.close();
			ps.close();
			System.out.println("abcccccccccccccccccc !ins_res.update_statuspro()");
			ins_wl.par_process_id = p_request.getParameter("pro_id");
			//if(ins_wl.selectDBpro()){
			PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from reservation_waitinglist where par_process_id=?");
			ps1.setString(1, ins_wl.par_process_id);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next()){
				ins_wl.req_id=rs1.getString("req_id");
				System.out.println("abcccccccccccccccccc ins_wl.selectDBpro()");

				ins_wl.par_process_id = p_request.getParameter("pro_id");
				ins_wl.status = "WMOD";
				if(!ins_wl.update_statuspro()) return false;

				ins_gadrequest.req_id = ins_wl.req_id;
				ins_gadrequest.status = "PRO";
				if(!ins_gadrequest.update_status()) return false;
			}
			//close
			rs1.close();
			ps1.close();
			System.out.println("abcccccccccccccccccc ins_wl.update_statuspro()");
			ins_cancel.process_id = p_request.getParameter("pro_id");
			//if(ins_wl.selectDBpro()){
			PreparedStatement ps2=NGM.tr_conn.prepareStatement("select * from cancel_reservation where process_id=?");
			ps2.setString(1, ins_cancel.process_id);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()){
				ins_cancel.req_id=rs2.getString("req_id");
				System.out.println("abcccccccccccccccccc ins_cancel.update_status()");
				ins_cancel.process_id = p_request.getParameter("pro_id");
				ins_req.req_id=ins_cancel.req_id;
				ins_cancel.status = "CAN";
				ins_req.status=ins_cancel.status;
				if(!ins_req.updateDBstatus())  return false;
				ins_cancel.status = "CCAN";
				if(!ins_cancel.update_status()) return false;
				
			}
			//close
			rs2.close();
			ps2.close();	
			
			flag = true;
		} catch (Exception e) {
			System.out.println("Some Exception Occured.." + e);
		}
		return flag;
	}
	public String searchrequestlist(HttpServletRequest request)throws Exception{
		common con = new common(NGM);  
		String govno = (String)request.getParameter("process");	 
		System.out.println("process id@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      "+govno);
		String rno=(String)request.getParameter("office");
		System.out.println("rno   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+rno);
		String date=(String)request.getParameter("date");
		System.out.println("date @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "+date);


		String criteria ="" ;
		String join = "";

		try{
			if (!govno.equals("0")) {
				criteria += join +" process_id like '"+govno+"'";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!rno.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" officeorderno like '"+rno+"'";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!date.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				//criteria += join +"'"+date+"' between date(created_date) and date(created_date)";
				criteria += join +" created_date::date = to_timestamp('"+date+"','dd-mm-yyyy HH24:MI:SS')::date ";

				//criteria += join +" created_date= '"+date+"'";
			}
		}catch (NullPointerException x)	{}


		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;
	}		


	public boolean doCancelProcess(HttpServletRequest p_request) throws Exception{
		ins_process.process_id = p_request.getParameter("pro_id");
		System.out.println("pro_idpro_idpro_id    "+ins_process.process_id);
		ins_process.status="CAN";
		ins_req.process_id = p_request.getParameter("pro_id");
		ins_req.status = "MOD";
		if(!ins_process.updateDBstatus()) return false;
		if(!ins_req.updateDBsta()) return false;
		System.out.println("abcccccccccccccccccc");
		ins_res.processid = p_request.getParameter("pro_id");
		if(ins_res.selectDBpro()){
			System.out.println("abcccccccccccccccccc ins_res.selectDBpro()");

			ins_res.processid = p_request.getParameter("pro_id");
			ins_res.status = "CAN";
			if(!ins_res.update_statuspro()) return false;

			ins_gadrequest.req_id = ins_res.req_id;
			System.out.println("requestid"+ins_res.req_id);
			ins_gadrequest.status = "MOD";
			if(!ins_gadrequest.update_status()) return false;
		}
		System.out.println("abcccccccccccccccccc !ins_res.update_statuspro()");

		ins_wl.par_process_id = p_request.getParameter("pro_id");
		if(ins_wl.selectDBpro()){
			System.out.println("abcccccccccccccccccc ins_wl.selectDBpro()");

			ins_wl.par_process_id = p_request.getParameter("pro_id");
			ins_wl.status = "CAN";
			if(!ins_wl.update_statuspro()) return false;

			ins_gadrequest.req_id = ins_wl.req_id;
			ins_gadrequest.status = "MOD";
			if(!ins_gadrequest.update_status()) return false;
		}
		
		System.out.println("abcccccccccccccccccc ins_wl.update_statuspro()");

		
		ins_cancel.process_id = p_request.getParameter("pro_id");
		PreparedStatement selcan = NGM.tr_conn.prepareStatement("select * from cancel_reservation where process_id=?");
		selcan.setString(1, ins_cancel.process_id);
		
		System.out.println("pscancel gad 111 : : : "+selcan);
		ResultSet rsselcancel = selcan.executeQuery();
		while(rsselcancel.next()){
			ins_cancel.req_id = rsselcancel.getString("req_id");
			System.out.println("DEEpak"+ins_cancel.req_id);
			ins_req.req_id=ins_cancel.req_id;
			
			ins_cancel.status = "CREQ";
			ins_req.status=ins_cancel.status;
			if(!ins_req.updateDBstatus())  return false;
			ins_cancel.status = "CAN";
			if(!ins_cancel.update_status()) return false;
		}

		return true;

	}

	public void resetProcesssid_cancellation(String p_process_id) throws Exception
	{
		d_cancel_reservation ins_dcan_res = new  d_cancel_reservation(this.NGM);
		ins_dcan_res.resetProcesssid_cancellation(p_process_id) ;
	}



	public boolean doProcess(HttpServletRequest p_request) throws Exception{

		/****************public*******************/
		String gh_id = p_request.getParameter("gh_id");
		String sel_block = p_request.getParameter("sel_block");
		String sign = p_request.getParameter("sign");
		String signature="",sign_desig="";

		ins_process.process_id      = NGM.ins_utility.get_seqID(NGM.tr_conn, "process_id").toString();
		NGM.ins_utility.updateseqID(NGM.tr_conn, "process_id", Integer.parseInt(ins_process.process_id));
		ins_process.created_by    = NGM.ins_sess.user_id;
		ins_process.status  	  = "TCNF";
		ins_process.officeorderno = null;
		ins_process.gh_id 		  = gh_id;
		ins_process.block_id  =  sel_block;
		if(sign.equals("2")){
			signature  = NGM.ins_ngmprop.getpropvalue("gad_go_sign_onduty");
			sign_desig = NGM.ins_ngmprop.getpropvalue("gad_designation_onduty");
		}else{
			signature  = NGM.ins_ngmprop.getpropvalue("gad_go_signature_name");
			sign_desig = NGM.ins_ngmprop.getpropvalue("gad_designation");
		}
		ins_process.gosignature   = signature;
		ins_process.gosignaturedesig   = sign_desig;
		if(!ins_process.insertDB()) return false;

		PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from ((select * from ((select * from v_current_mod where status!='CAN' and from_date::date>=now()::date) union (select * from v_waiting_mod where status!='CAN' and from_date::date>=now()::date)) as a where gh_id=? and block_id=?)aa join (select priority,vc_id from visitor_category)bb on aa.vc_id=bb.vc_id)xx order by priority,req_date");
		ps.setString(1, gh_id);
		ps.setString(2, sel_block);
		System.out.println("ps gad "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			String block_id="",quota_type="",mrr_id="",from_date="",to_date="",status="",req_id="",mod_id="",type="";
			int number=0,nor_req=0,nob_g_req=0,nob_l_req=0,nod_g_req=0,nod_l_req=0;
			double max_beds=0,total_r=0,total_rep=0,dor_tot_l=0,dor_tot_g=0,total_r_public=0,max_beds_public=0;

			ArrayList<Integer> occupied = new ArrayList<Integer>();
			ArrayList<Integer> occupied_pub = new ArrayList<Integer>();

			double occu_r = 0;
			double occu_bg = 0;
			double occu_bl = 0;

			double occu_dorl = 0;
			double occu_dorg = 0;

			double occu_r_pub = 0;
			double occu_bg_pub = 0;
			double occu_bl_pub = 0;

			double occu_dorl_pub = 0;
			double occu_dorg_pub = 0;

			double temp_cnf_r = 0;
			double temp_cnf_bg = 0;
			double temp_cnf_bl = 0;

			double temp_cnf_dorg = 0;
			double temp_cnf_dorl = 0;

			double temp_wl_r = 0;
			double temp_wl_bg = 0;
			double temp_wl_bl = 0;

			double temp_wl_dorg = 0;
			double temp_wl_dorl = 0;

			double mod_room_obj_r = 0;
			double mod_room_obj_bg = 0;
			double mod_room_obj_bl = 0;

			double mod_room_obj_dorg = 0;
			double mod_room_obj_dorl = 0;

			block_id = rs.getString("block_id");
			quota_type = rs.getString("quota_type");
			type = rs.getString("type");
			number = rs.getInt("number");
			mrr_id = rs.getString("mrr_id");
			from_date = rs.getString("from_date");
			to_date = rs.getString("to_date");
			req_id = rs.getString("req_id");
			mod_id = rs.getString("mod_id");
			status = rs.getString("status");

			total_r = this.get_total_rooms(gh_id,block_id,quota_type);
			max_beds = this.get_total_beds(gh_id,block_id,quota_type);

			dor_tot_l = this.get_total_dorl(gh_id, block_id, quota_type);
			dor_tot_g = this.get_total_dorg(gh_id, block_id, quota_type);

			if(quota_type.equals(NGM.ins_sess.def_quota)){
				////repair rooms
				total_rep = this.get_repair_rooms(gh_id, block_id);
				System.out.println("GoPiKa bfr total_r ::: "+total_r);
				total_r = total_r - total_rep;
				System.out.println("GoPiKa afr total_r ::: "+total_r);
				System.out.println("GoPiKa 0 bal_room_pub ::: "+total_rep);
				//balance public rooms
				try {
					total_r_public = this.get_total_rooms(gh_id,block_id,"PUBLIC");
					max_beds_public = this.get_total_beds(gh_id,block_id,"PUBLIC");
					occupied_pub = this.occupied_details(gh_id,block_id,"PUBLIC",from_date,to_date);
					occu_r_pub = Float.parseFloat(occupied_pub.get(0).toString());
					occu_bg_pub = Float.parseFloat(occupied_pub.get(1).toString());
					occu_bl_pub = Float.parseFloat(occupied_pub.get(2).toString());
					occu_dorg_pub = Float.parseFloat(occupied_pub.get(3).toString());
					occu_dorl_pub = Float.parseFloat(occupied_pub.get(4).toString());
				} catch (Exception e) {
					System.out.println("Something Went Wrong....."+e);
				}

				double bal_room_pub = total_r_public - occu_r_pub - (Math.ceil(occu_bl_pub/max_beds_public)) - Math.ceil(occu_bg_pub/max_beds_public);
				System.out.println("GoPiKa 0 bal_room_pub ::: "+bal_room_pub);
				total_r = total_r + bal_room_pub;
			}
			System.out.println("GoPiKa 1 total_r ::: "+total_r);

			try {
				occupied = this.occupied_details(gh_id,block_id,quota_type,from_date,to_date);
				occu_r = Float.parseFloat(occupied.get(0).toString());
				occu_bg = Float.parseFloat(occupied.get(1).toString());
				occu_bl = Float.parseFloat(occupied.get(2).toString());
				occu_dorg = Float.parseFloat(occupied.get(3).toString());
				occu_dorl = Float.parseFloat(occupied.get(4).toString());
			} catch (Exception e) {
				System.out.println("Something Went Wrong....."+e);
			}

			if(type.equals("R")) nor_req=1;
			else if(type.equals("BG")) nob_g_req=1; 
			else if(type.equals("BL")) nob_l_req=1;

			else if(type.equals("DG")) nod_g_req=1; 
			else if(type.equals("DL")) nod_l_req=1;

			mod_room_obj_r = nor_req;
			mod_room_obj_bg = nob_g_req;
			mod_room_obj_bl = nob_l_req;
			mod_room_obj_dorg = nod_g_req;
			mod_room_obj_dorl = nod_l_req;

			System.out.println("GoPiKa 2 :::occu_r "+occu_r);
			System.out.println("GoPiKa 3 ::: (Math.ceil(occu_bl/max_beds)) "+(Math.ceil(occu_bl/max_beds)));
			System.out.println("GoPiKa 4 ::: Math.ceil(occu_bg/max_beds) "+Math.ceil(occu_bg/max_beds));
			////////////////////////////////////////////////////////process
			double bal_room = 0.0;

			try {
				bal_room = total_r - occu_r - (Math.ceil(occu_bl/max_beds)) - Math.ceil(occu_bg/max_beds);
			} catch (Exception e) {
				System.out.println("Something Went Wrong....."+e);
			}

			System.out.println("GoPiKa 5 ::: bal_room "+bal_room);
			if(bal_room > 0 )   //if(bal_room - mod_no_of_room >=  0)
			{
				if(bal_room - mod_room_obj_r >=  0)
				{
					temp_cnf_r = mod_room_obj_r;
				}
				else
				{
					temp_cnf_r = mod_room_obj_r - bal_room;
					temp_wl_r = mod_room_obj_r - temp_cnf_r;
				}  
			}
			else 
			{
				temp_wl_r = mod_room_obj_r;

			}       

			bal_room = total_r + temp_cnf_r - occu_r - Math.ceil(occu_bl/max_beds);

			double bal_possible_GB = bal_room * max_beds;
			double	bal_GB = bal_possible_GB - occu_bg;
			if(bal_GB > 0 )  //     if(bal_gents_bed> 0 ) 
			{
				if(bal_GB - mod_room_obj_bg >=  0) //if bal_gents_bed- mod_no_of_GB >=  0)
				{
					temp_cnf_bg = mod_room_obj_bg;
				}
				else
				{
					temp_cnf_bg = mod_room_obj_bg-bal_GB;
					temp_wl_bg = mod_room_obj_bg-temp_cnf_bg;
				}   
			}
			else 
			{
				temp_wl_bg = mod_room_obj_bg;
			}       

			/////////////////////c
			bal_room = total_r + temp_cnf_r - occu_r - Math.ceil(occu_bg/max_beds);

			double bal_possible_LB = bal_room * max_beds;
			double	bal_LB = bal_possible_LB - occu_bl;

			if(bal_LB > 0 )  //     if(bal_gents_bed> 0 ) 
			{
				if(bal_LB - mod_room_obj_bl >=  0) //if bal_gents_bed- mod_no_of_GB >=  0)
				{
					temp_cnf_bl = mod_room_obj_bl;
				}
				else
				{
					temp_cnf_bl = mod_room_obj_bl-bal_LB;
					temp_wl_bl = mod_room_obj_bl-temp_cnf_bl;
				}   
			}
			else 
			{
				temp_wl_bl =    mod_room_obj_bl;
			}    


			//////////////////f
			bal_room = dor_tot_g - occu_dorg;
			if(bal_room > 0 )   //if(bal_room - mod_no_of_room >=  0)
			{
				if(bal_room - mod_room_obj_dorg >=  0)
				{
					temp_cnf_dorg = mod_room_obj_dorg;
				}
				else
				{
					temp_cnf_dorg = mod_room_obj_dorg - bal_room;
					temp_wl_dorg = mod_room_obj_dorg - temp_cnf_dorg;
				}  
			}
			else 
			{
				temp_wl_dorg = mod_room_obj_dorg;
			}       

			//////////////////g
			bal_room = dor_tot_l - occu_dorl;

			if(bal_room > 0 )   //if(bal_room - mod_no_of_room >=  0)
			{
				if(bal_room - mod_room_obj_dorl >=  0)
				{
					temp_cnf_dorl = mod_room_obj_dorl;
				}
				else
				{
					temp_cnf_dorl = mod_room_obj_dorl - bal_room;
					temp_wl_dorl = mod_room_obj_dorl - temp_cnf_dorl;
				}  
			}
			else 
			{
				temp_wl_dorl = mod_room_obj_dorl;
			}       

			boolean flag = false;

			for(int i=0;i<temp_cnf_r ;i++){
				ins_res.res_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_id").toString();
				NGM.ins_utility.updateseqID(NGM.tr_conn, "res_id", Integer.parseInt(ins_res.res_id));
				ins_res.from_date = from_date;
				ins_res.to_date = to_date;
				ins_res.checkin_date = from_date;
				ins_res.checkout_date = to_date;
				ins_res.type = "R";
				ins_res.status = "TCNF";
				ins_res.block_id  = block_id;
				ins_res.req_id = req_id;
				ins_res.processid = ins_process.process_id;
				ins_res.gh_id = gh_id;
				ins_res.total_number = 1;
				ins_res.quota_type = quota_type;
				if(!ins_res.insertDB()) return false;

				flag = true;
			}

			for(int i=0;i<temp_cnf_bg ;i++){
				ins_res.res_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_id").toString();
				NGM.ins_utility.updateseqID(NGM.tr_conn, "res_id", Integer.parseInt(ins_res.res_id));
				ins_res.from_date = from_date;
				ins_res.to_date = to_date;
				ins_res.checkin_date = from_date;
				ins_res.checkout_date = to_date;
				ins_res.type = "BG";
				ins_res.status = "TCNF";
				ins_res.block_id  = block_id;
				ins_res.req_id = req_id;
				ins_res.processid = ins_process.process_id;
				ins_res.gh_id = gh_id;
				ins_res.total_number = 1;
				ins_res.quota_type = quota_type;
				if(!ins_res.insertDB()) return false;
				flag = true;
			}

			for(int i=0;i<temp_cnf_bl ;i++){
				ins_res.res_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_id").toString();
				NGM.ins_utility.updateseqID(NGM.tr_conn, "res_id", Integer.parseInt(ins_res.res_id));
				ins_res.from_date = from_date;
				ins_res.to_date = to_date;
				ins_res.checkin_date = from_date;
				ins_res.checkout_date = to_date;
				ins_res.type = "BL";
				ins_res.status = "TCNF";
				ins_res.block_id  = block_id;
				ins_res.req_id = req_id;
				ins_res.processid = ins_process.process_id;
				ins_res.gh_id = gh_id;
				ins_res.total_number = 1;
				ins_res.quota_type = quota_type;
				if(!ins_res.insertDB()) return false;
				flag = true;
			}

			for(int i=0;i<temp_cnf_dorg ;i++){
				ins_res.res_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_id").toString();
				NGM.ins_utility.updateseqID(NGM.tr_conn, "res_id", Integer.parseInt(ins_res.res_id));
				ins_res.from_date = from_date;
				ins_res.to_date = to_date;
				ins_res.checkin_date = from_date;
				ins_res.checkout_date = to_date;
				ins_res.type = "DG";
				ins_res.status = "TCNF";
				ins_res.block_id  = block_id;
				ins_res.req_id = req_id;
				ins_res.processid = ins_process.process_id;
				ins_res.gh_id = gh_id;
				ins_res.total_number = 1;
				ins_res.quota_type = quota_type;
				if(!ins_res.insertDB()) return false;
				flag = true;
			}

			for(int i=0;i<temp_cnf_dorl ;i++){
				ins_res.res_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_id").toString();
				NGM.ins_utility.updateseqID(NGM.tr_conn, "res_id", Integer.parseInt(ins_res.res_id));
				ins_res.from_date = from_date;
				ins_res.to_date = to_date;
				ins_res.checkin_date = from_date;
				ins_res.checkout_date = to_date;
				ins_res.type = "DL";
				ins_res.status = "TCNF";
				ins_res.block_id  = block_id;
				ins_res.req_id = req_id;
				ins_res.processid = ins_process.process_id;
				ins_res.gh_id = gh_id;
				ins_res.total_number = 1;
				ins_res.quota_type = quota_type;
				if(!ins_res.insertDB()) return false;
				flag = true;
			}
			System.out.println(" go # gen "+flag);

			if(status.equals("MOD")){
				System.out.println("if go ###");

				ins_req.req_id =  req_id;
				ins_req.status = "PRO";
				ins_req.process_id = ins_process.process_id;
				if(!ins_req.updateDBstatus())  return false;
			}

			else if(status.equals("WMOD")&&flag){
				System.out.println("else go ###");

				ins_wl.req_id =  req_id;
				ins_wl.status = "PRO";
				ins_wl.process_id = ins_process.process_id;
				ins_wl.res_wid = mrr_id;
				if(!ins_wl.update_status("WMOD"))  return false;
			}

			if((temp_wl_r!=0||temp_wl_bg!=0||temp_wl_bl!=0||temp_wl_dorg!=0||temp_wl_dorl!=0)){
				if(!status.equals("WMOD")){
					ins_wl.res_wid = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_wid").toString();
					NGM.ins_utility.updateseqID(NGM.tr_conn, "res_wid", Integer.parseInt(ins_wl.res_wid));
					ins_wl.mod_id = mod_id;
					ins_wl.req_id = req_id;
					ins_wl.block_id = block_id;
					ins_wl.type = type;
					ins_wl.par_process_id = ins_process.process_id;
					ins_wl.process_id = null;
					ins_wl.status = "WMOD";
					ins_wl.gh_id = gh_id;
					if(!ins_wl.insertDB()) return false;
				}}


			////////////////////////////////////////////////////////process end
			/* newly done by Ajaeesh */
			setProcesssid_cancellation(ins_process.process_id);

			/* end done */
		}

		PreparedStatement pswait = NGM.tr_conn.prepareStatement("select * from ((select * from mod_req where status!='CAN' and quota_type='WAIT' and gh_id=?) a join (select req_id as rid,mrr_id,block_id,type from mod_req_room where gh_id=?) b on a.req_id=b.rid)c join (select * from gad_request where status='MOD' and gh_id=? and from_date::date>=now()::date)d on c.req_id=d.req_id");
		pswait.setString(1, gh_id);
		pswait.setString(2, gh_id);
		pswait.setString(3, gh_id);
		System.out.println("ps gad 111 : : : "+pswait);
		ResultSet rswait = pswait.executeQuery();
		while(rswait.next()){
			ins_wl.res_wid = NGM.ins_utility.get_seqID(NGM.tr_conn, "res_wid").toString();
			NGM.ins_utility.updateseqID(NGM.tr_conn, "res_wid", Integer.parseInt(ins_wl.res_wid));
			ins_wl.mod_id = rswait.getString("mod_id");
			ins_wl.req_id = rswait.getString("req_id");
			ins_wl.block_id = rswait.getString("block_id");
			ins_wl.type = rswait.getString("type");
			ins_wl.par_process_id = ins_process.process_id;
			ins_wl.process_id = ins_process.process_id;
			ins_wl.status = "WAIT";
			ins_wl.gh_id = gh_id;
			if(!ins_wl.insertDB()) return false;

			ins_req.req_id =  ins_wl.req_id;
			ins_req.status = "PRO";
			ins_req.process_id = ins_process.process_id;
			if(!ins_req.updateDBstatus())  return false;
		}
		PreparedStatement pscancel = NGM.tr_conn.prepareStatement("select Distinct(block_id),c.req_id, c.vc_id, c.quota_type,c.status,c.mrr_id,c.mod_id,c.gh_id,c.block_id,c.number,c.type,d.from_date,d.to_date,d.req_date FROM (( SELECT mod_req.req_id AS rid,mod_req.vc_id,mod_req.quota_type,mod_req.status FROM mod_req WHERE mod_req.status = 'CREQ') a JOIN ( SELECT mod_req_room.mrr_id,mod_req_room.mod_id,mod_req_room.req_id,mod_req_room.block_id,mod_req_room.number,mod_req_room.type,mod_req_room.gh_id FROM mod_req_room) b ON a.rid = b.req_id) c JOIN ( SELECT gad_request.req_id,gad_request.from_date,gad_request.to_date,gad_request.req_date FROM gad_request where status='CAN' and gh_id=?) d ON c.req_id = d.req_id where  gh_id=?  and from_date::date >= now()::date order by vc_id,req_date");
		pscancel.setString(1, gh_id);
		pscancel.setString(2, gh_id);
		System.out.println("pscancel gad 111 : : : "+pscancel);
		ResultSet rscancel = pscancel.executeQuery();
		while(rscancel.next()){
			ins_cancel.cancel_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "cancel_id").toString();
			NGM.ins_utility.updateseqID(NGM.tr_conn, "cancel_id", Integer.parseInt(ins_cancel.cancel_id));
			ins_cancel.req_id = rscancel.getString("req_id");
			ins_cancel.block_id = rscancel.getString("block_id");
			ins_cancel.process_id = ins_process.process_id;
			ins_cancel.status = "CREQ";
			ins_cancel.gh_id = gh_id;
			if(!ins_cancel.insertDB()) return false;

			ins_req.req_id =  ins_cancel.req_id;
			ins_req.status = "CAN";
			ins_req.process_id = ins_process.process_id;
			if(!ins_req.updateDBstatus())  return false;
			
		}
		return true;
		


	} //end function 

	public void setProcesssid_cancellation(String p_process_id) throws Exception
	{
		d_cancel_reservation ins_dcan_res = new  d_cancel_reservation(this.NGM);
		ins_dcan_res.setProcesssid_cancellation(p_process_id) ;
	}



	private int ceil(int i) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int get_total_rooms(String gh_id,String block_id,String quota_type) throws Exception{
		int total_r=0;
		ins_quotablk.gh_id = gh_id;
		ins_quotablk.block_id = block_id;
		ins_quotablk.quota_type = quota_type;
		total_r = ins_quotablk.selectDBrooms();
		return total_r;
	}
	public int get_repair_rooms(String gh_id,String block_id) throws Exception{
		int total_rep=0;
		ins_repair.gh_id = gh_id;
		ins_repair.block_id = block_id;
		total_rep = ins_repair.selectDBrooms();
		System.out.println("AAAAAAAAAAAAAAAAAA "+total_rep);
		return total_rep;
	}
	public int get_total_beds(String gh_id,String block_id,String quota_type) throws Exception{
		int total_beds=0;
		ins_quotablk.gh_id = gh_id;
		ins_quotablk.block_id = block_id;
		ins_quotablk.quota_type = quota_type;
		total_beds = ins_quotablk.selectDBbeds();
		return total_beds;
	}
	public int get_total_dorg(String gh_id,String block_id,String quota_type) throws Exception{
		int total_dorg=0;
		ins_quotablk.gh_id = gh_id;
		ins_quotablk.block_id = block_id;
		ins_quotablk.quota_type = quota_type;
		total_dorg = ins_quotablk.selectDBdorGents();
		return total_dorg;
	}
	public int get_total_dorl(String gh_id,String block_id,String quota_type) throws Exception{
		int total_dorl=0;
		ins_quotablk.gh_id = gh_id;
		ins_quotablk.block_id = block_id;
		ins_quotablk.quota_type = quota_type;
		total_dorl = ins_quotablk.selectDBdorLadies();
		return total_dorl;
	}

	public ArrayList<Integer> occupied_details(String gh_id,String block_id,String quota_type,String from_date,String to_date) throws Exception{
		ArrayList<Integer> occu = new ArrayList<Integer>();
		ins_res.gh_id = gh_id;
		ins_res.block_id = block_id;
		ins_res.quota_type = quota_type;
		ins_res.status = "TCNF";
		ins_res.checkin_date = from_date;
		ins_res.checkout_date = to_date;

		String occ_rooms = null;
		String occ_GB = null;
		String occ_LB = null;
		String occ_DG = null;
		String occ_DL = null;
		try{
			occ_rooms = ins_res.selectDBoccupied("R");
			occ_GB = ins_res.selectDBoccupied("BG");
			occ_LB = ins_res.selectDBoccupied("BL");
			occ_DG = ins_res.selectDBoccupied("DG");
			occ_DL = ins_res.selectDBoccupied("DL");
		}catch(Exception e){
			System.out.println("Exception Occured" + e);
		}

		System.out.println(" occ_rooms    "+occ_rooms);
		if(occ_rooms==null){
			occ_rooms = "0";
		}
		if(occ_GB==null){
			occ_GB = "0";
		}
		if(occ_LB==null){
			occ_LB = "0";
		}
		if(occ_DG==null){
			occ_DG = "0";
		}
		if(occ_DL==null){
			occ_DL = "0";
		}
		occu.add(Integer.parseInt(occ_rooms));
		occu.add(Integer.parseInt(occ_GB));
		occu.add(Integer.parseInt(occ_LB));
		occu.add(Integer.parseInt(occ_DG));
		occu.add(Integer.parseInt(occ_DL));

		return occu;
	}

	public boolean get_total_master(HttpServletRequest p_request) throws Exception{ //doubt
		ArrayList <d_quota_block> ins_total = new ArrayList<d_quota_block>();
		ins_quotablk.selectDB();
		ins_total.add(ins_quotablk);
		//  ins_total.get(index)
		return true;
	}

}


