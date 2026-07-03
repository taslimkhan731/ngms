package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;

public class m_moderator extends ngmodel{
	utility 		ins_util;
	d_mod_req_room ins_mod;
	d_mod_req ins_req;
	ArrayList <d_guestdetails> ins_guest = new ArrayList<d_guestdetails>();
	d_gadrequest ins_gadrequest;
	d_guestdetails ins_guestdetails;
	d_vehicle ins_vehicle;
	d_vehicle1 ins_vehicle1;
	public String p_quota_type="";
	public m_moderator(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_mod		= new d_mod_req_room(NGM);
		ins_req =new d_mod_req(NGM);
		ins_gadrequest=new d_gadrequest(NGM);
		ins_guestdetails=new d_guestdetails(NGM);
		ins_vehicle = new d_vehicle(NGM);
		ins_vehicle1 = new d_vehicle1(NGM);

	}

	public void VJ_createlist(String pgh_id,String ppage_no_s) throws Exception
	{
		System.out.println("Deepak.................");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_moderator", "Validation fails", "createlist", NGM);
		}
	}

	public void VJ_createlistedit(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_moderator", "Validation fails", "createlistedit", NGM);
		}
	}
	public void VJ_search(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_moderator", "Validation fails", "search", NGM);
		}
	}
	public void VJ_showformmod(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "showformmod", NGM);
		}
	}
	public void VJ_searcheditlist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_moderator", "Validation fails", "searcheditlist", NGM);
		}
	}
	public void VJ_showformedit(String pgh_id,String preq_id) throws Exception
	{
		if(!(NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_ghid(pgh_id)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "showformedit", NGM);
		}
	}
	public void VJ_roomstatusedit(String preq_id) throws Exception
	{
		if(!NGM.ins_dbval.val_reqid(preq_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_dataentry", "Validation fails", "roomstatusedit", NGM);
		}
	}

	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "count2"));
		ins_req.mod_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mod_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "mod_id", Integer.parseInt(ins_req.mod_id));
		String vehicle = ins_util.setNullParameter(p_request, "vehicle");
		String duringstay=ins_util.setNullParameter(p_request, "duringstay");
		ins_req.vc_id   = ins_util.setNullParameter(p_request, "vc_id");
		ins_req.quota_type   = ins_util.setNullParameter(p_request, "quota");
		ins_req.req_id 	=	ins_util.setNullParameter(p_request, "req_id");
		ins_req.gh_id = ins_util.setNullParameter(p_request, "gh_id");
		if(vehicle.equalsIgnoreCase("1")){
			ins_req.is_veh_req   = ins_util.setNullParameter(p_request, "conveyance1");
			ins_req.is_veh_req1   = ins_util.setNullParameter(p_request, "conveyance2");
		}else{
			ins_req.is_veh_req   = "0";
			ins_req.is_veh_req1   = "0";
		}
		if(duringstay.equalsIgnoreCase("1")){
			ins_req.duringstay="1";
		}else{
			ins_req.duringstay="0";
		}
		if(!ins_req.insertDB_mod())return false;
		ins_gadrequest.status="MOD";
		ins_gadrequest.req_id=ins_req.req_id;
		if(!ins_gadrequest.update_status())return false;
		ins_mod.gh_id = ins_util.setNullParameter(p_request, "gh_id");
		ins_mod.req_id = ins_req.req_id;
		System.out.println(" eeee gh_id************   "+ins_mod.gh_id);
		String block_id = null;
		for(int i=1;i<=count;i++)
		{
			String sel_id="sel_id"+i;
			String b="b"+i;
			String c="c"+i;
			String d="d"+i;
			String f="f"+i;
			String g="g"+i;

			ins_mod.block_id			= ins_util.setNullParameter(p_request, sel_id);
			ins_mod.mod_id = ins_req.mod_id;
			System.out.println("-----------ins_req.mod_id---------------------- "+ins_req.mod_id);
			System.out.println("-----xxxxx------ins_mod.block_id---------------------- "+ins_mod.block_id);

			String rooms    		= ins_util.setNullParameter(p_request, b);
			String bg		= ins_util.setNullParameter(p_request, c);
			String bl    = "-";/*ins_util.setNullParameter(p_request, d);*/

			String dg    = ins_util.setNullParameter(p_request, f);
			String dl    = "-";/*ins_util.setNullParameter(p_request, g);*/
			if(rooms.equals("-")){
				rooms = "0";
			}
			if(bg.equals("-")){
				bg = "0";
			}
			if(bl.equals("-")){
				bl = "0";
			}

			if(dg.equals("-")){
				dg = "0";
			}
			if(dl.equals("-")){
				dl = "0";
			}
			for(int ii=0;ii<Integer.parseInt(rooms);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "R";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bg);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bl);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BL";
				if(!ins_mod.insertDB())return false;
			}

			for(int ii=0;ii<Integer.parseInt(dg);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dl);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DL";
				if(!ins_mod.insertDB())return false;
			}

		}
		ins_gadrequest.req_id 	=	ins_util.setNullParameter(p_request, "req_id");
		ins_gadrequest.gh_id = ins_util.setNullParameter(p_request, "gh_id");
		ins_gadrequest.is_veh_req   = ins_util.setNullParameter(p_request, "conveyance1");
		ins_gadrequest.is_veh_req1   = ins_util.setNullParameter(p_request, "conveyance2");
		
		System.out.println("Convijhgsdjkcvs"+ ins_gadrequest.is_veh_req + "sgfiulsdgflsconveyance2"+ ins_gadrequest.is_veh_req1);
		try {
			ins_gadrequest.req_id=ins_req.req_id;
			if(!ins_gadrequest.update_block(block_id))return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(vehicle.equalsIgnoreCase("1")){
			if(!ins_gadrequest.update_conveyance())return false;
			if(!ins_gadrequest.update_conveyance1())return false;
		}else{
			ins_gadrequest.is_veh_req   = "0";
			ins_gadrequest.is_veh_req1 = "0";
			if(!ins_gadrequest.update_conveyance())return false;
			if(!ins_gadrequest.update_conveyance1())return false;
		}
		if(duringstay.equalsIgnoreCase("1")){
			ins_gadrequest.duringstay="1";
			if(!ins_gadrequest.update_duringstay())return false;
		}else{
			ins_gadrequest.duringstay="0";
			if(!ins_gadrequest.update_duringstay())return false;
		}
		
		return true;
	}
	public boolean cancel(HttpServletRequest p_request) throws Exception{
		ins_req.reqst =   p_request.getParameter("reqid");
		ins_req.req_id =   p_request.getParameter("reqid");

		System.out.println("cancelledddddddddddddddddd"+ ins_req.req_id);
		if(!ins_req.cancel_mod())return false;
		return true;
	}

	public boolean cancelApplication(HttpServletRequest request) throws Exception {
		String mob = null;
		String msg = null;
		String reason = null;
		ins_req.req_id =   request.getParameter("reqid");
		reason = request.getParameter("remarks");
		System.out.println("cancelledddddddddddddddddd"+ ins_req.req_id);
		try {
			mob = ins_req.selectDB_mobDetails(ins_req.req_id);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if(mob!=null){
			try {
				msg = "Your request number " + ins_req.req_id + " for guest house is cancelled for reason"+reason+", Odisha Home Dept";
				sendVerificationCode(mob,msg);
			} catch (Exception e) {
				System.out.println("Some Exception Occured.. " + e);
			}
		}
		if(!(ins_req.cancelgad() || ins_req.cancelpub()))return false;
		return true;
	}
	public boolean cancelreservation(HttpServletRequest p_request) throws Exception{
		ins_req.req_id =   p_request.getParameter("reqid");
		System.out.println("reqest Id" +ins_req.req_id);
		if(!(ins_req.cancelgad() && ins_req.cancelmodreq()))return false;
	
			
	return true;
	}
	public boolean cancelApplicationMod(HttpServletRequest request) throws Exception {
		String mob = null;
		String msg = null;
		String reason = null;
		ins_req.req_id =   request.getParameter("req_id");
		reason = request.getParameter("remarks");
		try {
			mob = ins_req.selectDB_mobDetails(ins_req.req_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("cancelledddddddddddddddddd"+ ins_req.req_id);
		if(mob!=null){
			try {
				msg = "Your request number " + ins_req.req_id + "  for reservation is cancelled for reason"+reason;
				sendVerificationCode(mob,msg);
			} catch (Exception e) {
				System.out.println("Some Exception Occured.. " + e);
			}
		}
		if(!((ins_req.cancelgad() && ins_req.cancel_mod()) || ins_req.cancelpub()))
			return false;
		return true;
	}

	public boolean deletelist(HttpServletRequest p_request) throws Exception{
		ins_req.reqst =   p_request.getParameter("reqid");
		ins_mod.reqst =   p_request.getParameter("reqid");
		ins_gadrequest.req_id= p_request.getParameter("reqid");
		// ins_guestdetails.req_id= p_request.getParameter("reqid");
		// ins_vehicle.req_id= p_request.getParameter("reqid");
		if(ins_gadrequest.selectDB_gad()){
			if(!ins_gadrequest.cancelcancel())return false;
		}
		if(!ins_req.deletelist())return false;
		if(!ins_mod.deletemrr())return false;
		//if(!ins_gadrequest.deletegad())return false;
		//if(!ins_guestdetails.deleteguest())return false;
		//if(!ins_vehicle.deleteveh())return false;


		return true;
	}
	public boolean setPostUpdate(HttpServletRequest p_request) throws Exception
	{

		int count= Integer.parseInt(ins_util.setNullParameter(p_request, "count2"));
		//	ins_req.mod_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mod_id").toString(); 
		//    NGM.ins_utility.updateseqID(NGM.tr_conn, "mod_id", Integer.parseInt(ins_req.mod_id));
		ins_mod.gh_id=ins_util.setNullParameter(p_request, "ghid");
		ins_mod.mod=ins_util.setNullParameter(p_request, "mod_id");
		ins_mod.mod_id=ins_util.setNullParameter(p_request, "mod_id");
		System.out.println("MODDDDDDDDDDDDD"+ ins_mod.mod_id);
		ins_mod.req_id=ins_util.setNullParameter(p_request, "req_id");
		System.out.println("REQUESTTTTTTTTTT"+ ins_mod.req_id);
		if(!ins_mod.deletemod()) return false;
		System.out.println(" ins_mod.req_id................ "+ins_mod.req_id);
		ins_req.req_id = ins_mod.req_id;
		ins_req.vc_id   = ins_util.setNullParameter(p_request, "sel_vcid");
		System.out.println(" ins_req.vc_id................ "+ins_req.vc_id);

		ins_req.quota_type   = ins_util.setNullParameter(p_request, "sel_q");
		System.out.println(" ins_req.quota_type............... "+ins_req.quota_type);
		String vehicle = ins_util.setNullParameter(p_request, "vehicle");
		String duringstay=ins_util.setNullParameter(p_request, "duringstay");
		System.out.println(duringstay);
		if(vehicle.equalsIgnoreCase("1")){
			ins_req.is_veh_req   = "1";
			ins_req.is_veh_req1   = "1";
			if(!ins_req.update_vehicle())return false;
		}else{
			ins_req.is_veh_req   = ins_util.setNullParameter(p_request, "conveyance1");
			ins_req.is_veh_req1   = ins_util.setNullParameter(p_request, "conveyance2");
			if(!ins_req.update_vehicle())return false;
		}
		if(duringstay.equalsIgnoreCase("1")){
			ins_req.duringstay="1";
		}else{
			ins_req.duringstay="0";
		}
		if(!ins_req.update_vc_q())return false;
		ins_gadrequest.duringstay=ins_req.duringstay;
		ins_gadrequest.req_id=ins_req.req_id;
		ins_gadrequest.gh_id=ins_mod.gh_id;
		if(!ins_gadrequest.update_duringstay())return false;
		/*PreparedStatement pss = NGM.conn.prepareStatement("select count(*) from room_block where gh_id=? and block_id=?");
		pss.setString(1, ins_mod.gh_id);
		pss.setString(2, ins_mod.block_id);
		ResultSet rss = pss.executeQuery();
		int total_row=0;
		while (rss.next()) {
			total_row = rss.getInt("count");
		}
		rss.close();
		pss.close();*/
		String block_id = null;
		for(int i=1;i<=count;i++)
		{
			String sel_id="sel_id"+i;
			String b="b"+i;
			String c="c"+i;
			String d="d"+i;

			String f="f"+i;
			String g="g"+i;

			ins_mod.block_id			= ins_util.setNullParameter(p_request, sel_id);
			ins_mod.mod_id = ins_mod.mod_id;
			System.out.println("-----------ins_req.mod_id---------------------- "+ins_req.mod_id);
			System.out.println("-----xxxxx------ins_mod.block_id---------------------- "+ins_mod.block_id);

			String rooms    		= ins_util.setNullParameter(p_request, b);
			String bg		= ins_util.setNullParameter(p_request, c);
			String bl    = "-";/*ins_util.setNullParameter(p_request, d);*/

			String dg    = ins_util.setNullParameter(p_request, f);
			String dl    = "-";/*ins_util.setNullParameter(p_request, g);*/
			if(rooms.equals("-")){
				rooms = "0";
			}
			if(bg.equals("-")){
				bg = "0";
			}
			if(bl.equals("-")){
				bl = "0";
			}

			if(dg.equals("-")){
				dg = "0";
			}
			if(dl.equals("-")){
				dl = "0";
			}
			for(int ii=0;ii<Integer.parseInt(rooms);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "R";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bg);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bl);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BL";
				if(!ins_mod.insertDB())return false;
			}

			for(int ii=0;ii<Integer.parseInt(dg);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dl);ii++){
				block_id = ins_mod.block_id;
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DL";
				if(!ins_mod.insertDB())return false;
			}

		}
		
		try {
			ins_gadrequest.req_id=ins_req.req_id;
			if(!ins_gadrequest.update_block(block_id))return false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*for(int i=1;i<=total_row;i++)
		{
				String sel_id="sel_id"+i;
				String b="b"+i;
				String c="c"+i;
				String d="d"+i;

				String f="f"+i;

				String g="g"+i;

			ins_mod.block_id	= ins_util.setNullParameter(p_request, sel_id);
			System.out.println("sel_id ===> "+sel_id);
			ins_mod.nor_req    		= ins_util.setNullParameter(p_request, b);
			ins_mod.nob_g_req		= ins_util.setNullParameter(p_request, c);
			ins_mod.nob_l_req    = ins_util.setNullParameter(p_request, d);
			String rooms    		= ins_util.setNullParameter(p_request, b);

			String bg	 = ins_util.setNullParameter(p_request, c);
			System.out.println("bg ===> "+bg);
			String bl    = ins_util.setNullParameter(p_request, d);
			System.out.println("bl ===> "+bl);
			String dg    = ins_util.setNullParameter(p_request, f);
			System.out.println("dg ===> "+dg);
			String dl    = ins_util.setNullParameter(p_request, g);
			System.out.println("dl ===> "+dl);
			for(int ii=0;ii<Integer.parseInt(rooms);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "R";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(bl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "BL";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dg);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DG";
				if(!ins_mod.insertDB())return false;
			}
			for(int ii=0;ii<Integer.parseInt(dl);ii++){
				ins_mod.mrr_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "mrr_id").toString(); 
			     NGM.ins_utility.updateseqID(NGM.tr_conn, "mrr_id", Integer.parseInt(ins_mod.mrr_id));
				ins_mod.type = "DL";
				if(!ins_mod.insertDB())return false;
			}
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii "+i);
		}*/

		ins_gadrequest.req_id 	=	ins_util.setNullParameter(p_request, "req_id");
		ins_gadrequest.gh_id = ins_util.setNullParameter(p_request, "gh_id");
		ins_gadrequest.is_veh_req   = ins_util.setNullParameter(p_request, "conveyance1");
		ins_gadrequest.is_veh_req1   = ins_util.setNullParameter(p_request, "conveyance2");
		System.out.println("Convijhgsdjkcvs"+ ins_gadrequest.is_veh_req + "sgfiulsdgflsconveyance2"+ ins_gadrequest.is_veh_req1);
		if(vehicle.equalsIgnoreCase("1")){
			ins_gadrequest.is_veh_req   = "1";
			ins_gadrequest.is_veh_req1 = "1";
			if(!ins_gadrequest.update_conveyance())return false;
			if(!ins_gadrequest.update_conveyance1())return false;
		}else{
			ins_gadrequest.is_veh_req   = "0";
			ins_gadrequest.is_veh_req1 = "0";
			if(!ins_gadrequest.update_conveyance())return false;
			if(!ins_gadrequest.update_conveyance1())return false;
		}

		System.out.println("TRUE G");
		return true;
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
		ins_gadrequest.purpose						= ins_util.setNullParameter(p_request, "purpose");
		ins_gadrequest.category						= ins_util.setNullParameter(p_request, "category");
		ins_gadrequest.desg					=  ins_util.setNullParameter(p_request, "desg");
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
	public String searchrequest(HttpServletRequest request)throws Exception{

		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String designation=(String)request.getParameter("desig");
		String date=(String)request.getParameter("date");
		String category = (String)request.getParameter("category");
		common con = new common(NGM);

		String criteria ="" ;
		String join = "";
		try{
			if (!name.equals("")) {
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" upper(name) like upper('%"+name+"%')";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!req_id.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" guest_details.req_id like '"+req_id+"'";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!designation.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" guest_details.designation like '%"+designation+"%'";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!date.equals("")){
				date = con.display_date_calander(date);
				if (!criteria.equals("")) {
					criteria += join +" and ";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				criteria += join +"'"+date+"' between date(from_date) and date(to_date)";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!category.equals("0")){
				if (!criteria.equals("")) {
					criteria += join +" and ";
				}
				criteria += join +" category_type = '"+category+"'";
			}
		}catch (NullPointerException x)	{}

		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;

	}


	public String searchrequestedit(HttpServletRequest request)throws Exception{

		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String designation=(String)request.getParameter("desig");
		String category=(String)request.getParameter("category");
		String date=(String)request.getParameter("date");
		common con = new common(NGM);

		String criteria ="" ;
		String join = "";

		try{
			if (!name.equals("")) {
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" upper(name) like upper('%"+name+"%')";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!req_id.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" a.req_id like '"+req_id+"'";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!designation.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" upper(a.designation) like upper('%"+designation+"%')";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!date.equals("")){
				date = con.display_date_calander(date);
				if (!criteria.equals("")) {
					criteria += join +" and ";
				}
				//criteria += join +" c.from_date = '"+date+"'"; // 
				criteria += join +"'"+date+"' between date(from_date) and date(to_date)";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!category.equals("0")){
				if (!criteria.equals("")) {
					criteria += join +" and ";
				}
				criteria += join +" category_type = '"+category+"'";
			}
		}catch (NullPointerException x)	{}

		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;

	}



	//roomstatus

	public ArrayList<String> roomstatus(HttpServletRequest p_request) throws Exception{

		String wait = "";

		ArrayList<String> statusrooms= new ArrayList<String>();
		String status = "";
		db.d_roomblock obj=new db.d_roomblock(NGM);
		ArrayList<String> block= new ArrayList<String>();
		block=obj.getallblock(p_request);
		System.out.println("block sixzee="+block.size()+"and "+block.get(0));
		int n = 4;
		for(int ii=0;ii<block.size();ii++){
			try {
				String i =""+ ii;
				d_quota_block ins_qb = new d_quota_block(NGM);
				//ins_qb.block_id = i;
				ins_qb.block_id =block.get(ii);
				System.out.println("block idss="+ins_qb.block_id);
				ins_qb.gh_id = ins_util.setNullParameter(p_request, "ghid");
				ins_qb.quota_type = ins_util.setNullParameter(p_request, "sel_q");
				System.out.println("sel_q ins_qb.quota_type  "+ins_qb.quota_type);
				wait = ins_qb.quota_type;
				p_quota_type = ins_qb.quota_type;
				int total = ins_qb.selectDBrooms();
				int maxbeds = ins_qb.selectDBbeds();
				int dor_gents = ins_qb.selectDBdorGents();
				int dor_ladies = ins_qb.selectDBdorLadies();

				int total_rep = 0;
				if(ins_qb.quota_type.equals(NGM.ins_sess.def_quota)){
					d_repair_block ins_rb = new d_repair_block(NGM);
					ins_rb.block_id = i;
					ins_rb.gh_id = ins_util.setNullParameter(p_request, "ghid");
					total_rep = ins_rb.selectDBrooms();
				}

				int total_r = total - total_rep;
				d_reservation_maintable ins_res = new d_reservation_maintable(NGM);
				ins_res.gh_id = ins_util.setNullParameter(p_request, "ghid");
				ins_res.block_id = i;
				ins_res.quota_type = ins_util.setNullParameter(p_request, "sel_q");
				ins_res.status = "CNF";
				ins_res.checkin_date = ins_util.setNullParameter(p_request, "from_date");
				ins_res.checkout_date = ins_util.setNullParameter(p_request, "to_date");

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
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("total_r "+total_r);
				System.out.println("occ_rooms "+occ_rooms);
				System.out.println("occ_GB "+occ_GB);
				System.out.println("occ_LB "+occ_LB);
				System.out.println("occ_DG "+occ_DG);
				System.out.println("occ_DL "+occ_DL);

				System.out.println("maxbeds "+maxbeds);


				if(total>0||maxbeds>0){
					double occu_rooms =total_r - ( Double.parseDouble(occ_rooms) +  Math.ceil(Double.parseDouble(occ_GB)/maxbeds) + Math.ceil(Double.parseDouble(occ_LB)/maxbeds));
					System.out.println("occu_rooms "+occu_rooms);

					status = (int)occu_rooms +"/"+total_r+"-Rooms";
				}

				else if(dor_gents>0||dor_ladies>0){
					double occu_rooms = dor_gents - ( Double.parseDouble(occ_DG));
					System.out.println("occu_roomsg "+occu_rooms);

					status = (int)occu_rooms +"/"+dor_gents+"-DG&";

					double occu_roomsl = dor_ladies - ( Double.parseDouble(occ_DL));
					System.out.println("occu_roomsl "+occu_roomsl);

					status = status + (int)occu_roomsl +"/"+dor_ladies+"-DL";
				}

				System.out.println("status="+status);
				if(!(status==null||status=="")){
					System.out.println("statusrooms "+statusrooms);
					statusrooms.add(status);
				}
			} catch (Exception e) {
				System.out.println("Exception ="+e);
			}
		}
		System.out.println("statusrooms return "+statusrooms);
		return statusrooms;
	}

	public boolean sendVerificationCode(String mob,String msg)throws Exception {	
		//ok
		String tepmlateId="1107160707775267623";
		NGM.ins_utility.SENDSMS(mob, msg, "NGM",tepmlateId);//this.ins_user.userid
		return true;
	}
}


