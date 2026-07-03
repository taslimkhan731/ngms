package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import allotment.c_column_chart;
import allotment.c_row_chart;
import allotment.c_selected_details;
import common.SendGmail;
import common.common;
import common.utility;
import db.*;

public class m_allotment extends ngmodel{

	utility 		ins_util;

	d_allotment ins_alot;
	d_reservation_maintable ins_resrv;
	d_reservation_waitinglist ins_waitl;
	d_gadrequest ins_gad;
	d_checkin_allot ins_chk;
	// 
	public ArrayList<c_selected_details> selectedlistdet =null;
	public m_allotment(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_resrv	 = new d_reservation_maintable(NGM);
		ins_alot		= new d_allotment(NGM);
		ins_waitl = new  d_reservation_waitinglist(NGM);
		ins_gad = new d_gadrequest(NGM);
		ins_chk = new d_checkin_allot(NGM);

		selectedlistdet =new ArrayList<c_selected_details>();

	}


	public void VJ_list(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "list", NGM);
		}
	}
	public void VJ_changelist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "changelist", NGM);
		}
	}
	public void VJ_cancellist(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "cancellist", NGM);
		}
	}
	public void VJ_getsearchrequest(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "getsearchrequest", NGM);
		}
	}
	public void VJ_searchrequestchange(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "searchrequestchange", NGM);
		}
	}
	public void VJ_allotpopup(String pgh_id,String ppage_no,String preq_id,String ptype,String pres_id,String ptablename,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_type(ptype)&&NGM.ins_dbval.val_res_id(pres_id)&&NGM.ins_dbval.val_tablename(ptablename)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "allotpopup", NGM);
		}
	}
	public void VJ_doprocessavailabilty(String pgh_id,String ppage_no,String preq_id,String ptype,String pres_id,String ptablename,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_type(ptype)&&NGM.ins_dbval.val_res_id(pres_id)&&NGM.ins_dbval.val_tablename(ptablename)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "doprocessavailabilty", NGM);
		}
	}
	public void VJ_searchrequestcancel(String pgh_id,String ppage_no_s) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no_s)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "searchrequestcancel", NGM);
		}
	}
	public void VJ_allotchagepopup(String pgh_id,String ppage_no,String preq_id,String ptype,String pres_id,String ptablename,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_type(ptype)&&NGM.ins_dbval.val_res_id(pres_id)&&NGM.ins_dbval.val_tablename(ptablename)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "allotchagepopup", NGM);
		}
	}
	public void VJ_doprocesschangeavail(String pgh_id,String ppage_no,String preq_id,String ptype,String pres_id,String ptablename,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_type(ptype)&&NGM.ins_dbval.val_res_id(pres_id)&&NGM.ins_dbval.val_tablename(ptablename)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "doprocesschangeavail", NGM);
		}
	}
	public void VJ_allotcancelpopupindiv(String pgh_id,String ppage_no,String preq_id,String ptype,String pres_id,String ptablename,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_type(ptype)&&NGM.ins_dbval.val_res_id(pres_id)&&NGM.ins_dbval.val_tablename(ptablename)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "allotcancelpopupindiv", NGM);
		}
	}
	public void VJ_allotmentreport(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "allotmentreport", NGM);
		}
	}
	public void VJ_allotmentReportView(String pgh_id,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "allotmentReportView", NGM);
		}
	}
	public void VJ_allotcancelpopup(String pgh_id,String ppage_no,String preq_id,String ptype,String pres_id,String ptablename,String pfrom,String pto) throws Exception
	{
		String[] t_pfrom = pfrom.split(" ");
		String[] t_to = pto.split(" ");
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)&&NGM.ins_dbval.val_reqid(preq_id)&&NGM.ins_dbval.val_type(ptype)&&NGM.ins_dbval.val_res_id(pres_id)&&NGM.ins_dbval.val_tablename(ptablename)&&NGM.ins_dbval.val_date(t_pfrom[0])&&NGM.ins_dbval.val_date(t_to[0])))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_allotment", "Validation fails", "allotcancelpopup", NGM);
		}
	}

	public int getcount_guest(String req_id)throws Exception{
		int count=0;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select count(*) from guest_details where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;	
	}

	public int getcount_alot(String req_id)throws Exception{
		int count=0;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select count(*) from (select req_id,res_wl_id from tr_allotment where req_id=? and status!='CAN' group by req_id,res_wl_id) a");
		ps.setString(1, req_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;	
	}

	public String searchrequest(HttpServletRequest request)throws Exception{

		String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String rno=(String)request.getParameter("processid");
		String date=(String)request.getParameter("date");
		if(!date.equals("")){
			String[] date1=date.split("-");
			date = date1[2]+"-"+date1[1]+"-"+date1[0];
		}

		System.out.println("combooooooooooooooo "+rno);
		String t_req_list = "";
		if((req_id.equals(""))&&(!name.equals(""))){
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.name = "%"+name+"%";
			t_req_list = ins_guest.selectDB_reqidList();
			System.out.println("t_req_list "+t_req_list);
			if(!t_req_list.equals("")){ //selectDB_reqid
				req_id = t_req_list;
			}else{
				req_id = "NO";
			}
		}

		if(rno.equals("0")){
			rno = "";
		}

		String criteria ="" ;
		String join = "";

		/*	try{
					if (!name.equals("")) {
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" c.name like '"+name+"'";
					}
				}catch (NullPointerException x)	{}*/

		try{
			if (!req_id.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" c.req_id in ('"+req_id+"')";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!rno.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" c.process_id like '"+rno+"'";
			}
		}catch (NullPointerException x)	{}
		try{
			if (!date.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				//criteria += join +" c.from_date = '"+date+"'"; 
				// criteria += join +" date('"+date+"') between date(d.from_date) and date(d.to_date)";
				criteria += join +" date('"+date+"') = date(d.from_date)";
			}
		}catch (NullPointerException x)	{}


		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;

	}


	public String getdateid(String pdate)
	{
		String retvalue ="";
		for (String retval: pdate.split("-")){
			retvalue=retvalue+retval;
			//  System.out.println(retval);
		}

		return retvalue;
	}
	public String vac_room_box(String source_status,String p_room_type, String roomno, String pdate, int max_no_bed,String pgh_id,String pblock_id)

	{
		String dateid = this.getdateid(pdate);
		String tableid="TAB_"+roomno+"_"+dateid + "_" + pblock_id;
		String roomcellid="CELL_"+roomno+"_"+dateid+pblock_id;
		String draw="";
		//<span onclick=selectroom(pre_id, 'R','R', roomno, pdate, max_no_room)> room </room>
		//do_select(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno, pnumberofbeds,p_res_type)
		draw = "<td><table id='"+tableid+"'>"+		 
				" <tr><td"
				+" onclick=\" do_select('"+tableid+"','"+roomcellid+"',"+"'R','R','"+roomno+"','"+pdate+"','0',"+max_no_bed+",'"+p_room_type+"',"+pgh_id+",'"+pblock_id+"')\" id='"+roomcellid+"' colspan=\"4\"> <span class=\"notselected btn-block\" id=\"aa\" >R</span></td>"+
				"</tr>"+
				"<tr>";

		for(int k=1;k<=max_no_bed;k++)
		{
			String tempbedno="B"+k;
			String bedcellid="CELL_"+roomno+"_"+dateid+tempbedno;
			//<span onclick=selectbed(pre_id, 'R','B', roomno, pdate, max_no_room, bk);
			draw = 	draw + "<td "
					+" onclick=\"do_select('"+tableid+"','"+bedcellid+"',"+"'R','B','"+roomno+"','"+pdate+"','"+tempbedno+"',"+max_no_bed+",'"+p_room_type+"',"+pgh_id+",'"+pblock_id+"')\" id='"+bedcellid+"'><span class=\"notselected\" > B"+k+"</span></td>";

		}			draw = draw + "</tr>"+
				"</table></td>";
		return draw;
	}      
	public String selected_room_draw(String source_status,String p_room_type, String proomno, String pbedno,String pdate, int max_no_bed,String res_type,String pgh_id,String pblock_id) throws SQLException
	{
		String dateid = this.getdateid(pdate);
		String tableid="TAB_"+proomno+"_"+dateid + "_" + pblock_id;
		String roomcellid="CELL_"+proomno+"_"+dateid+pblock_id;
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! rooom" + tableid);
		c_selected_details selectedlist = new c_selected_details();
		selectedlist.setParameters(tableid, roomcellid, source_status, p_room_type, proomno, pdate, pbedno,max_no_bed, res_type, pgh_id, pblock_id);
		selectedlistdet.add(selectedlist);
		String draw="";

		draw = "<td><table id='"+tableid+"'>"+	
				" <tr><td id='"+roomcellid+"' colspan=\"4\"> "  +
				" <span  class=\"selectedbox btn-block\" onclick=\" do_deselect('"+tableid+"','"+roomcellid+"','"+source_status+"','"+p_room_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+res_type+"',"+pgh_id+",'"+pblock_id+"')\" >"+
				p_room_type+" </span>	 "+		//proomno	  
				"</td> "+
				"</tr> 		 "+				
				"</table></td>";
		return draw;
	}

	public String vac_bed_draw(String source_status,String p_room_type, String proomno, String pbedno,String pdate, int max_no_bed,String res_type,String pgh_id,String pblock_id)
	{
		String dateid = this.getdateid(pdate);
		String tableid="TAB_"+proomno+"_"+dateid + "_" + pblock_id;
		String roomcellid="CELL_"+proomno+"_"+dateid+pblock_id;
		String draw="";
		String tempbedno=pbedno;
		String bedcellid="CELL_"+proomno+"_"+dateid+tempbedno+pblock_id;
		//System.out.println("p_room_type?????????????????????????????????????????????????? "+p_room_type);
		draw= "<td  id='"+bedcellid+"' > "+
				" <span class=\"notselected\"  onclick=\" do_select('"+tableid+"','"+bedcellid+"','"+source_status+"','"+p_room_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+res_type+"',"+pgh_id+","+pblock_id+")\""+
				" > "+pbedno+"</span></td>";

		return draw;
	}

	public String selected_bed_draw(String source_status,String p_room_type, String proomno, String pbedno,String pdate, int max_no_bed,String res_type,String pgh_id,String pblock_id) throws SQLException
	{
		String dateid = this.getdateid(pdate);
		String tableid="TAB_"+proomno+"_"+dateid + "_" + pblock_id;
		String roomcellid="CELL_"+proomno+"_"+dateid+pblock_id;
		String draw="";
		String tempbedno=pbedno;
		String bedcellid="CELL_"+proomno+"_"+dateid+tempbedno+pblock_id;
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! beddd");

		c_selected_details selectedlist = new c_selected_details();
		selectedlist.setParameters(tableid, roomcellid, source_status, p_room_type, proomno, pdate, pbedno,max_no_bed, res_type, pgh_id, pblock_id);
		selectedlistdet.add(selectedlist);



		draw= "<td  id='"+bedcellid+"' > "+
				" <span class=\"selected_bed\" onclick=\" do_deselect('"+tableid+"','"+bedcellid+"','"+source_status+"','"+p_room_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+res_type+"',"+pgh_id+","+pblock_id+")\""+ //////selectedbox
				" > "+pbedno+"</span></td>";

		System.out.println("drawdraw drawdraw SELECTED., "+draw);
		return draw;
	}

	public String occ_room_box(String req_id, String guest_id,String roomno,String tdate)
	{
		System.out.println("0000000000000000000000000000000000000000000000000000 req_id "+req_id+" - guest_id "+guest_id+" - roomno "+roomno+" - tdate "+tdate);
		String name= "";
		try{
			ins_alot.room_no = roomno;
			ins_alot.bed_no = "0";
			common ins_com = new common(NGM);
			String t_ghid = ins_alot.gh_id;
			String t_reqid = ins_alot.req_id;
			ins_alot.from_date = ins_com.display_time_zonenew(tdate);
			ins_alot.select_room_guest_details();

			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.req_id = t_reqid;  //GOPIKA 05-04-2016
			ins_guest.selectDB_guestDetails(t_ghid);

			name = ins_guest.name;
		}catch (Exception e) {
			// TODO: handle exception
		}

		String draw="";
		//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
		draw = "<td><table id=\"\">"+		 
				"<tr><td> <span data-toggle='tooltip' title='"+name+"' data-placement='bottom' class=\"selectedbox alreadyselected showTooltip\" id=\"aa\">R</span></td>"+
				"</tr>"+
				"</table></td>";
		return draw;
	}

	public String occ_room_box_gh(String req_id, String guest_id,String roomno,String tdate,String gh_id,String sel_block)
	{
		System.out.println("0000000000000000000000000000000000000000000000000000NAMEEEEEEEEEE req_id "+req_id+" - guest_id "+guest_id+" - roomno "+roomno+" - tdate "+tdate);
		String name= "";
		try{
			ins_alot.room_no = roomno;
			ins_alot.bed_no = "0";
			common ins_com = new common(NGM);
			String t_ghid = gh_id;
			ins_alot.from_date = ins_com.display_time_zonenew(tdate);
			ins_alot.gh_id = gh_id;
			ins_alot.block_id = sel_block;
			ins_alot.select_room_guest_details();
			String t_reqid = ins_alot.req_id;
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.req_id = t_reqid;  //GOPIKA 05-04-2016
			ins_guest.selectDB_guestDetails(t_ghid);


			name = ins_guest.name;
		}catch (Exception e) {
			// TODO: handle exception
		}

		String draw="";
		//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
		draw = "<td><table id=\"\">"+		 
				"<tr><td> <span data-toggle='tooltip' title='"+name+"' data-placement='bottom' class=\"selectedbox alreadyselected showTooltip\" id=\"aa\">R</span></td>"+
				"</tr>"+
				"</table></td>";
		return draw;
	}

	public String occ_room_bed_draw(String req_id, String guest_id,String room_type,String roomno, String bedno)//String room_type(BL,BG)
	{

		String name= "";
		try{
			ins_alot.room_no = roomno;
			ins_alot.bed_no = bedno;
			ins_alot.select_room_guest();
			ins_alot.req_id = req_id; //done by shaik
			String t_ghid = ins_alot.gh_id;
			String t_reqid = ins_alot.req_id;

			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.req_id = t_reqid;
			System.out.println("before="+ins_guest.name);
			ins_guest.selectDB_guestDetails(t_ghid);
			System.out.println("after="+ins_guest.name);


			name = ins_guest.name;
			System.out.println("NAMEEEEEEEEEEE "+name);
		}catch (Exception e) {
			// TODO: handle exception
		}

		String draw="";
		/*System.out.println("0000000000000000000000000000000000000000000000000000");
			System.out.println("req_id "+req_id);
			System.out.println("guest_id "+guest_id);
			System.out.println("room_type "+room_type);
			System.out.println("roomno "+roomno);
			System.out.println("bedno "+bedno);*/

		//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
		draw = "<td> <span class=\"alreadyselected showTooltip\" data-toggle='tooltip' title='"+name+"' data-placement='bottom' id=\"aa\">"+room_type+"</span></td>";
		System.out.println("Occtest****************************8");
		System.out.println(draw);

		return draw;
	}


	public String draw_room_box(c_column_chart temp_col_chart,String res_id,String ptablename,String prestype,String pgh_id,String pblock_id) throws Exception //String cur_allotid
	{
		System.out.println("_________________________________ - - - ");
		//System.out.println(",,,,,,,,,,,,,,,,,,draw_room_box,,,,,,,,,,,,,,,,,,,,,,,,,"+prestype);
		String drawbox = "";
		d_allotment ins_allot = new d_allotment(NGM);
		ins_allot.res_wl_id = res_id;
		ins_allot.res_wl = ptablename;
		ins_allot.from_date = temp_col_chart.date;
		//System.out.println("ins_allot.from_date "+ins_allot.from_date);
		String cur_allotid = "",tguest_id="",treq_id="";
		if(ins_allot.isalloted()){
			cur_allotid = ins_allot.allot_id;
			d_checkin_allot ins_chkalot = new d_checkin_allot(NGM);
			ins_chkalot.allot_id = cur_allotid;
			//System.out.println("ins_chkalot.allot_id "+ins_chkalot.allot_id);
			ins_chkalot.select_check_allot();

			d_checkin ins_chk = new d_checkin(NGM);
			ins_chk.tr_id = ins_chkalot.tr_id;
			System.out.println("ins_chk.tr_id "+ins_chk.tr_id);

			ins_chk.selectDB();
			tguest_id = ins_chk.guest_id;
			treq_id = ins_chk.req_id;
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa****treq_id  "+treq_id);
			System.out.println("tguest_id  "+tguest_id);
		}else{

		}
		String t_roomno = temp_col_chart.roomno;
		String t_date = temp_col_chart.date;
		ArrayList<d_allotment> pallotedlist = temp_col_chart.list_alloted;
		int t_max_no_beds = temp_col_chart.max_no_beds;
		boolean isheading = temp_col_chart.is_heading;


		String temp_alloted_roomtype ="";
		int i = 0;

		while(i < pallotedlist.size())
		{
			d_allotment temp_d_allot = pallotedlist.get(i);
			temp_alloted_roomtype =temp_d_allot.type;
			break;
		}
		//System.out.println("temp_alloted_roomtype iii "+temp_alloted_roomtype);


		if(temp_alloted_roomtype.equals("")) { //
			// System.out.println("temp_alloted_roomtype gopika: "+temp_alloted_roomtype); 
			System.out.println("CCCCCC="+pblock_id);
			drawbox = drawbox + vac_room_box("R", prestype, t_roomno,t_date, t_max_no_beds,pgh_id,pblock_id);
			///////////////////////////////String source_status,String p_room_type, String roomno, String pdate, int max_no_bed
		}

		else if(temp_alloted_roomtype.equals("R")) {
			//System.out.println("rrr---00000-----rrr");

			d_allotment temp_d_allot = pallotedlist.get(0);

			if(temp_d_allot.res_wl_id.equals(res_id) && temp_d_allot.res_wl.equals(ptablename) && ( temp_d_allot.room_no.equals(t_roomno)) ){

				drawbox = drawbox + selected_room_draw("R", temp_d_allot.type, temp_d_allot.room_no,"0",t_date, t_max_no_beds,prestype,pgh_id,pblock_id); //max_no_beds max_no_room

			}
			else			
			{   
				drawbox = drawbox + occ_room_box_gh(treq_id, tguest_id, t_roomno,t_date,pgh_id,pblock_id);
			}
		} //end of else if

		else if(temp_alloted_roomtype.equals("BL")||temp_alloted_roomtype.equals("BG"))  {
			//System.out.println("bbb---00000-----bbb   *g1");
			drawbox = drawbox + occ_bed_box(temp_col_chart,res_id, ptablename,tguest_id,prestype,pgh_id,pblock_id);
		} //end if



		return drawbox;
	}  // end for method

	public String findsourcetype(int p_max_no_beds,ArrayList<d_allotment> pallotedlist, String res_id,String ptablename)
	{
		String source_type="R";

		for(int ii=0;ii < pallotedlist.size() ;++ii)
		{
			d_allotment temp_d_allot = pallotedlist.get(ii);




			if(temp_d_allot.res_wl_id.equals(res_id) && temp_d_allot.res_wl.equals(ptablename))
			{		

				/*System.out.println("temp_d_allot.res_wl_id ?;1 "+temp_d_allot.res_wl_id);
							System.out.println(".res_id ?; 1 "+res_id);

							System.out.println("temp_d_allot.res_wl ?; 1  "+temp_d_allot.res_wl);

							System.out.println(".ptablename ?; 1 "+ptablename);*/

			}
			else
			{

				/*System.out.println("temp_d_allot.res_wl_id ?; "+temp_d_allot.res_wl_id);
							System.out.println(".res_id ?; "+res_id);

							System.out.println("temp_d_allot.res_wl ?; "+temp_d_allot.res_wl);

							System.out.println(".ptablename ?; "+ptablename);*/
				source_type=temp_d_allot.type;
			}
		}
		return source_type;
	} //end of method

	public String occ_bed_box(c_column_chart temp_col_chart,String res_id,String ptablename, String pguestid,String prestype,String pgh_id,String pblock_id) throws SQLException
	{
		System.out.println("*g2 ");
		String troomno = temp_col_chart.roomno;
		String t_date = temp_col_chart.date;
		ArrayList<d_allotment> pallotedlist = temp_col_chart.list_alloted;
		int t_max_no_beds = temp_col_chart.max_no_beds;
		boolean isheading = temp_col_chart.is_heading;

		String source_type=findsourcetype(t_max_no_beds,pallotedlist, res_id,ptablename);
		System.out.println("anzar..... "+source_type);

		String dateid = this.getdateid(t_date);
		String tableid="TAB_"+troomno+"_"+dateid + "_" + pblock_id;



		String draw="";
		draw = "<td><table id=\""+tableid+"\">	<tr>";



		for(int k=1;k<=t_max_no_beds;k++) {
			//System.out.println("no -of bed iter *g3"+k );
			String tempbedno="B"+k;
			boolean tmpstatus = false; 
			//int ii = 0;
			String temp_selected_type="";
			//	System.out.println("Size is getting 2 or not "+pallotedlist.size() );
			for(int ii=0;ii < pallotedlist.size() ;++ii)
			{
				//System.out.println("Iteration ="+ii);
				//System.out.println("*g4 ");

				d_allotment temp_d_allot = pallotedlist.get(ii);
				temp_selected_type = temp_d_allot.type;
				if( temp_d_allot.bed_no.equals(tempbedno)) {
					//System.out.println("*g5 ");

					if(temp_d_allot.res_wl_id.equals(res_id) && temp_d_allot.res_wl.equals(ptablename) && ( temp_d_allot.room_no.equals(troomno))) {

						//System.out.println("Iteration inside selected ="+ii);
						//System.out.println("*g6 "+temp_d_allot.room_no);
						//System.out.println("*shaik "+source_type);

						draw = draw + selected_bed_draw(source_type, temp_d_allot.type, temp_d_allot.room_no,tempbedno,t_date, t_max_no_beds, prestype,pgh_id,pblock_id); //max_no_beds max_no_room
						//System.out.println("qweeeen  ----- sel "+draw);                            

						tmpstatus = true; //break;
					}
					else			
					{  
						//System.out.println("Iteration inside occupied ="+ii);
						//System.out.println("*g7 "+temp_d_allot.room_no);
						// System.out.println("qweeeen  ----- occu "+draw);                            

						//if(pallotedlist.size()>1)

						draw = draw + occ_room_bed_draw(temp_d_allot.req_id, pguestid,temp_d_allot.type,temp_d_allot.room_no, temp_d_allot.bed_no);//pallotedlist.get(ii).guest_id
						tmpstatus = true;	//break;				
					}
				}
			} //end for new
			if(tmpstatus==false) {
				//String source_status,String p_room_type, String roomno, String pbedno,String pdate, int max_no_bed,String res_type)
				draw = draw + vac_bed_draw(source_type, temp_selected_type, troomno,tempbedno,t_date, t_max_no_beds, prestype,pgh_id,pblock_id); //max_no_beds max_no_room);//max_no_room  // "" pre_id

			};

		} //end for 

		draw = draw+ 	"</tr> </table></td>";
		return draw;

	}
	public boolean sendVerificationCode(String mob, String msg)throws Exception {	
		//ok
		String templateId="1107166028578492328";
		NGM.ins_utility.SENDSMS(mob, msg , "NGM",templateId);//this.ins_user.userid
		return true;
	}

	public boolean sendVerificationCode(String mob,String room_no,String block_name)throws Exception {	
		//ok
		String templateId="1107160707775267623";
		NGM.ins_utility.SENDSMS(mob, "Room Number Is Cancelled "+room_no+" ", "NGM",templateId);//this.ins_user.userid

		return true;
	}

	public boolean sendVerificationCodeEmail(String email,String room_no,String block_name)throws Exception {		//ok
		SendGmail gmail = new SendGmail();
		gmail.email(email, "Room Number Is Cancelled"+room_no+" ", "NGM","");//this.ins_user.userid
		return true;
	}

	public boolean sendVerificationCodeEmail(String email,String room_no,String block_name,String name)throws Exception {		//ok
		SendGmail gmail = new SendGmail();
		gmail.email(email, name+"Room Number Is Allotted "+room_no+" "+block_name, "NGM","");//this.ins_user.userid
		return true;
	}
	public boolean doAllotment(HttpServletRequest p_request) throws Exception{ 
		common ins_common = new common(NGM);
		String from = p_request.getParameter("from");
		String mob = null;
		String city = null;
		String name = null;
		String block_name = null;
		String msg = "";
		if(from != null){
			from = ins_common.display_date_calander(from);
		}
		String to = p_request.getParameter("to");
		if(to != null){
			to = ins_common.display_date_calander(to);
		}
		System.out.println("nagg "+from);
		System.out.println("nagg"+to);
		DateTime startstr = DateTime.parse(from);
		System.out.println("Start from date: " + startstr);
		String room_no="";
		DateTime endstr = DateTime.parse(to);
		System.out.println("End to date: " + endstr);
		ArrayList<String> between = ins_common.getDateRangeString(startstr,endstr);
		name = p_request.getParameter("g_name");
		System.out.println(between.get(0));
		if(name != null){
			if(!name.isEmpty()){
				name = "Dear " + name ;/*+ " " + " against your request No " + ins_alot.req_id;*/
				msg = name;
			}
		}

		for(int i=0;i<between.size();i++){

			String date_data =p_request.getParameter("dateO"+between.get(i));
			String t_room_data =p_request.getParameter("roomnoO"+between.get(i));

			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%gopika&&&&&&&&&&&&&&&&&&&&&&&"+between.get(i));
			System.out.println("date_data : "+date_data);
			System.out.println("date_data : "+t_room_data);
			if(!t_room_data.equals("")){

				String rent="";
				ins_alot.allot_id  = NGM.ins_utility.get_seqID(NGM.tr_conn, "allot_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "allot_id", Integer.parseInt(ins_alot.allot_id));
				ins_alot.req_id = p_request.getParameter("req_id"); //
				ins_alot.res_wl_id = p_request.getParameter("res_id"); //h
				ins_alot.from_date = p_request.getParameter("dateO"+between.get(i));
				//				ins_alot.to_date = p_request.getParameter("dateO"+between.get(i));
				ins_alot.checkin_date = p_request.getParameter("dateO"+between.get(i));
				//				ins_alot.checkout_date = p_request.getParameter("dateO"+between.get(i));
				ins_alot.type = p_request.getParameter("selected_typeO"+between.get(i));
				ins_alot.status = "ALO";
				ins_alot.gh_id = p_request.getParameter("gh_id"); //
				String block = p_request.getParameter("block_idO"+between.get(i));
				
				Date dt = null;
				if(ins_alot.from_date != null){
					if(!ins_alot.from_date.isEmpty()){
						dt = new SimpleDateFormat("dd-MM-yyyy").parse(ins_alot.from_date);
					}
				}
				System.out.println(dt);
				Calendar c = Calendar.getInstance(); 
				if(dt != null){
					c.setTime(dt); 
					c.add(Calendar.DATE, 1);
					dt = c.getTime();
				}
				if(dt != null){
					ins_alot.to_date = ins_common.parse_string(dt);
					ins_alot.checkout_date = ins_common.parse_string(dt);
				}
				if(block.length()<2){
					block = "0"+block ;
				}
				ins_alot.block_id  = block;
				rent = p_request.getParameter("rent"+between.get(i));
				if(rent.equals("")){
					rent = "0";
				}
				ins_alot.room_rent = rent;
				ins_alot.room_no = p_request.getParameter("roomnoO"+between.get(i));
				ins_alot.bed_no = p_request.getParameter("bednoO"+between.get(i));
				ins_alot.res_wl = p_request.getParameter("tablename"); //h
				System.out.println("%%%%%%%%%%"+ins_alot.room_rent);
				String g_room_prev = "";
				if(!ins_alot.insertDB())
				{
					return false;	
				}else{
					System.out.println("Enter SMS Table");
					d_sms_alltment ins_sms_allot = new d_sms_alltment(NGM);
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = ins_alot.gh_id;
					ins_sms_allot.mode = "ALT";
					//ins_sms_allot.status = "P";
					ins_sms_allot.selectDB_status();
					ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
					System.out.println("SMS Table status"+ins_sms_allot.status);
					if(!ins_sms_allot.status.equals("P")){
						System.out.println("Create SMS Table Data");
						ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
						System.out.println("ins_sms_allot.a_sms_id "+ins_sms_allot.a_sms_id);
						NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
						ins_sms_allot.req_id = ins_alot.req_id;
						ins_sms_allot.gh_id = ins_alot.gh_id;

						d_guestdetails ins_guest=new d_guestdetails(NGM);
						ins_guest.req_id = ins_alot.req_id;   
						mob = ins_guest.selectDB_guestDetails(ins_alot.gh_id);
						city = ins_guest.selectDB_cityDetails(ins_sms_allot.gh_id);
						block_name = ins_guest.selectDB_guesthouseDetails(block);
						String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
						//    String name1 = ins_sms_allot.guest_details;
						ins_sms_allot.mobile_no = mob;
						ins_sms_allot.guest_details = name;//ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");
						if(ins_alot.type.equals("R")){
							ins_sms_allot.subject = ins_alot.room_no;
						}else{
							System.out.println("11111111111111111111111111111111111111111111111"+ ins_alot.type.equals("R"));
							ins_sms_allot.subject = ins_alot.room_no+"-"+ins_alot.bed_no;
						}
						System.out.println("2222222222222222222222222222222222222222222222"+mob+","+ins_sms_allot.subject+","+block_name);
						ins_sms_allot.mode = "ALT";
						ins_sms_allot.status = "P";
						ins_sms_allot.exp_time = ins_alot.to_date;
						g_room_prev = ins_alot.room_no;
					}else{
						d_guestdetails ins_guest=new d_guestdetails(NGM);
						ins_guest.req_id = ins_alot.req_id;   
						mob = ins_guest.selectDB_guestDetails(ins_alot.gh_id);
						block_name = ins_guest.selectDB_guesthouseDetails(block);
						city = ins_guest.selectDB_cityDetails(ins_sms_allot.gh_id);
						String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
						ins_sms_allot.req_id = ins_alot.req_id;
						ins_sms_allot.gh_id = ins_alot.gh_id;
						ins_sms_allot.status = "P";
						ins_sms_allot.mode = "ALT";
						if(!g_room_prev.equals(ins_alot.room_no)){

							if(ins_alot.type.equals("R")){
								ins_sms_allot.subject = ins_sms_allot.subject +","+ ins_alot.room_no;
								System.out.println("33333333333333333333333333333333333333333333333333"+ins_sms_allot.subject);
							}else{
								ins_sms_allot.subject = ins_sms_allot.subject +","+ins_alot.room_no+"-"+ins_alot.bed_no;
								System.out.println("4444444444444444444444444444444444444444444444444444"+ins_sms_allot.subject);
							}
							
							if(!ins_sms_allot.addSubject())
								return false;
							else{
								
							}

						}
						g_room_prev = ins_alot.room_no;
					}
					///////////////////////////


					if(ins_alot.res_wl.equals("R")){
						ins_resrv.status="ALO";
						ins_resrv.res_id= ins_alot.res_wl_id;
						ins_resrv.gh_id= ins_alot.gh_id;
						if(!ins_resrv.update_status_alot())return false;
					}
					if(ins_alot.res_wl.equals("W")){
						ins_waitl.status="ALO";
						ins_waitl.res_wid = ins_alot.res_wl_id;
						ins_waitl.gh_id= ins_alot.gh_id;
						if(!ins_waitl.update_status_alot())return false;
					}
				}
				String req_id = p_request.getParameter("req_id");
				boolean t_pending_pro=false;
				PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where req_id=? and gh_id=? union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where req_id=? and gh_id=?)a where a.status='CNF' or a.status='WMOD'");
				ps.setString(1, req_id);
				ps.setString(2, ins_alot.gh_id);
				ps.setString(3, req_id);
				ps.setString(4, ins_alot.gh_id);

				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					t_pending_pro = true;
				}

				if(t_pending_pro){
					ins_gad.status="PALO";
					ins_gad.req_id=req_id;
					ins_gad.gh_id=ins_alot.gh_id;
					if(!ins_gad.update_status())return false; 
				}else{
					ins_gad.status="ALO";
					ins_gad.req_id=req_id;
					ins_gad.gh_id=ins_alot.gh_id;
					if(!ins_gad.update_status())return false; 
				}
				/* */
			} //end if 
			
//			if(room_no == ""){
//				room_no = " Room number " + p_request.getParameter("roomnoO"+between.get(i));
//				if(!p_request.getParameter("bednoO"+between.get(i)).equalsIgnoreCase("0")){
//					room_no = room_no + " Bed number "+p_request.getParameter("bednoO"+between.get(i));
//				}
//				msg = msg + room_no + " is alloted in " + block_name + " " + city + " for " + ins_alot.checkin_date ;
//			}else{
//				room_no = " "+ " and Room number " + p_request.getParameter("roomnoO"+between.get(i));
//				
//				if(!p_request.getParameter("bednoO"+between.get(i)).equalsIgnoreCase("0")){
//					room_no = room_no + " Bed number "+p_request.getParameter("bednoO"+between.get(i));
//				}
//				msg = msg + room_no + " is alloted in " + block_name + " " + city + " for " + ins_alot.checkin_date ;
//			}
		}  // end for
		room_no = " Room number " + p_request.getParameter("roomnoO"+between.get(0));
		if(!p_request.getParameter("bednoO"+between.get(0)).equalsIgnoreCase("0")){
			room_no = room_no + " Bed number "+p_request.getParameter("bednoO"+between.get(0));
		}
		msg = msg + room_no + " is allotted in " + block_name+", "+ city + " for " + ins_common.display_date_calander1(from)+ " to " + ins_common.display_date_calander1(to) + " against your Request No "+ins_alot.req_id+" -Odisha Home Dept";
		System.out.println(msg);
		try {
			if(mob!=null){
				
				sendVerificationCode(mob,msg);
			}
		} catch (Exception e) {}
		return true;
	}////end function doAllotment


	public boolean doChangeAllotment(HttpServletRequest p_request) throws Exception{ 
		common ins_common = new common(NGM);
		String mob = null;
		String city = null;
		String name = null;
		String block_name = null;
		String room_no1="";
		String msg = "";
		String from = p_request.getParameter("from");
		if(from != null){
			from = ins_common.display_date_calander(from);
		}
		String to = p_request.getParameter("to");
		if(to != null){
			to = ins_common.display_date_calander(to);
		}
		System.out.println("nagg "+from);
		DateTime startstr = DateTime.parse(from);
		System.out.println("Start: " + startstr);
		DateTime endstr = DateTime.parse(to);
		System.out.println("End: " + endstr);
		ArrayList<String> between = ins_common.getDateRangeString(startstr,endstr);
		
		name = p_request.getParameter("g_name");
		if(name != null){
			if(!name.isEmpty()){
				name = "Dear " + name ;/*+ " " + " against your request No " + ins_alot.req_id;*/
				msg = name;
			}
		}
		for(int i=0;i<between.size();i++){

			String date_data =p_request.getParameter("dateO"+between.get(i));
			String t_room_data =p_request.getParameter("roomnoO"+between.get(i));
			String rent =p_request.getParameter("rent"+between.get(i));
			System.out.println("Rent"+rent);
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%gopika&&&&&&&&&&&&&&&&&&&&&&&"+between.get(i));
			System.out.println("date_data : "+date_data);
			if(!t_room_data.equals("")){
				boolean flag=false;
				String room_data =p_request.getParameter("roomnoO"+between.get(i));
				String block_data =p_request.getParameter("block_idO"+between.get(i));
				String gh_id_data =p_request.getParameter("gh_id");
				ins_alot.res_wl = p_request.getParameter("tablename");
				ins_alot.res_wl_id = p_request.getParameter("res_id"); //h
				ins_alot.from_date = p_request.getParameter("dateO"+between.get(i));
				
				if(ins_alot.isalloted()){
					String t_allot_id = ins_alot.allot_id;
					System.out.println(",,,,,,,t_allot_id "+t_allot_id);
					ins_alot.req_id = p_request.getParameter("req_id"); //
					ins_alot.res_wl_id = p_request.getParameter("res_id"); //h
					ins_alot.from_date = p_request.getParameter("dateO"+between.get(i));
					//					ins_alot.to_date = p_request.getParameter("dateO"+between.get(i));
					ins_alot.checkin_date = p_request.getParameter("dateO"+between.get(i));
					//					ins_alot.checkout_date = p_request.getParameter("dateO"+between.get(i));
					ins_alot.type = p_request.getParameter("selected_typeO"+between.get(i));
					ins_alot.status = "ALO";
					ins_alot.gh_id = p_request.getParameter("gh_id"); //
					ins_alot.block_id = p_request.getParameter("block_idO"+between.get(i));
					ins_alot.room_no = p_request.getParameter("roomnoO"+between.get(i));
					ins_alot.room_rent = p_request.getParameter("rent"+between.get(i));
					ins_alot.bed_no = p_request.getParameter("bednoO"+between.get(i));
					ins_alot.res_wl = p_request.getParameter("tablename");
					Date dt = null;
					if(ins_alot.from_date != null){
						if(!ins_alot.from_date.isEmpty()){
							dt = new SimpleDateFormat("dd-MM-yyyy").parse(ins_alot.from_date);
						}
					}
					Calendar c = Calendar.getInstance(); 
					if(dt != null){
						c.setTime(dt); 
						c.add(Calendar.DATE, 1);
						dt = c.getTime();
					}
					if(dt != null){
						ins_alot.to_date = ins_common.parse_string(dt);
						ins_alot.checkout_date = ins_common.parse_string(dt);
					}
					if(ins_alot.isAllotedSame()){
						flag = true;
						// break;
					} 
					else{
						ins_alot.allot_id = t_allot_id;
						ins_alot.status = "CAN";
						ins_alot.gh_id = gh_id_data;
						ins_alot.room_rent = rent;
						if(!ins_alot.update_db_status()) return false; 

					}
				}

				if(!flag){ 	 
					ins_alot.allot_id  = NGM.ins_utility.get_seqID(NGM.tr_conn, "allot_id").toString(); 
					NGM.ins_utility.updateseqID(NGM.tr_conn, "allot_id", Integer.parseInt(ins_alot.allot_id));
					ins_alot.tr_id = null;
					ins_alot.req_id = p_request.getParameter("req_id"); //
					ins_alot.res_wl_id = p_request.getParameter("res_id"); //h
					ins_alot.from_date = p_request.getParameter("dateO"+between.get(i));
					//					ins_alot.to_date = p_request.getParameter("dateO"+between.get(i));
					ins_alot.checkin_date = p_request.getParameter("dateO"+between.get(i));
					//					ins_alot.checkout_date = p_request.getParameter("dateO"+between.get(i));
					ins_alot.type = p_request.getParameter("selected_typeO"+between.get(i));
					ins_alot.status = "ALO";
					ins_alot.gh_id = p_request.getParameter("gh_id"); //
					ins_alot.block_id = p_request.getParameter("block_idO"+between.get(i));
					ins_alot.room_no = p_request.getParameter("roomnoO"+between.get(i));
					ins_alot.room_rent = p_request.getParameter("rent"+between.get(i));
					ins_alot.bed_no = p_request.getParameter("bednoO"+between.get(i));
					ins_alot.res_wl = p_request.getParameter("tablename");
					Date dt = null;
					if(ins_alot.from_date != null){
						if(!ins_alot.from_date.isEmpty()){
							dt = new SimpleDateFormat("dd-MM-yyyy").parse(ins_alot.from_date);
						}
					}
					Calendar c = Calendar.getInstance(); 
					if(dt != null){
						c.setTime(dt); 
						c.add(Calendar.DATE, 1);
						dt = c.getTime();
					}
					if(dt != null){
						ins_alot.to_date = ins_common.parse_string(dt);
						ins_alot.checkout_date = ins_common.parse_string(dt);
					}
					if(!ins_alot.insertDB())
					{
						return false;	
					}else{

						System.out.println("TESTTTT");
						d_sms_alltment ins_sms_allot = new d_sms_alltment(NGM);
						ins_sms_allot.req_id = ins_alot.req_id;
						ins_sms_allot.gh_id = ins_alot.gh_id;
						ins_sms_allot.mode = "ALT";
						//ins_sms_allot.status = "P";
						ins_sms_allot.selectDB_status();
						ins_sms_allot.guest_details = name;
						ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
						if(!ins_sms_allot.equals("P")){
							System.out.println("TESTTTT11");
							ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
							NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
							ins_sms_allot.req_id = ins_alot.req_id;
							ins_sms_allot.gh_id = ins_alot.gh_id;
							d_guestdetails ins_guest=new d_guestdetails(NGM);
							ins_guest.req_id = ins_alot.req_id;
							mob = ins_guest.selectDB_guestDetails(ins_alot.gh_id);
							String block =  ins_alot.block_id ;
							block_name = ins_guest.selectDB_guesthouseDetails(block);
							city = ins_guest.selectDB_cityDetails(ins_sms_allot.gh_id);
							String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
							ins_sms_allot.mobile_no = mob;
							ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

							/*ins_sms_allot.req_id = ins_alot.req_id;
									 ins_sms_allot.gh_id = ins_alot.gh_id;
									 ins_sms_allot.mode = "ALT";
									 ins_sms_allot.status = "S";
									 if(ins_sms_allot.selectDB()){
										 ins_sms_allot.subject = "Allotment Changed, Current Allotments: ";
									 }else{*/
							ins_sms_allot.subject = "";
							// }
							boolean flagggg = false;
							String bed_no="",room_no="",type="";
							PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
							ps.setString(1, ins_alot.gh_id);
							ps.setString(2, ins_alot.req_id);
							ps.setString(3, "ALO");
							ResultSet rs = ps.executeQuery();
							while(rs.next()){
								if(flagggg){
									ins_sms_allot.subject = ins_sms_allot.subject + "," ;
								}
								bed_no = rs.getString("bed_no");
								room_no= rs.getString("room_no");
								type= rs.getString("type");

								if(type.equals("R")){
									System.out.println("TESTTTT4444");
									ins_sms_allot.subject = ins_sms_allot.subject + room_no;
								}else{
									ins_sms_allot.subject = ins_sms_allot.subject + room_no+"-"+bed_no;
								}
								flagggg = true;
							}
							//close
							rs.close();
							ps.close();
							ins_sms_allot.mode = "ALT";
							ins_sms_allot.status = "P";
							ins_sms_allot.exp_time = ins_alot.to_date;
						}else{
							System.out.println("TESTTTT22");		 
							ins_sms_allot.req_id = ins_alot.req_id;
							ins_sms_allot.gh_id = ins_alot.gh_id;
							ins_sms_allot.status = "P";
							ins_sms_allot.mode = "ALT";
							ins_sms_allot.subject = "";
							boolean flagggg = false;
							String bed_no="",room_no="",type="";
							PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * tr_allotment where gh_id=? and req_id=? and status=?");
							ps.setString(1, ins_alot.gh_id);
							ps.setString(2, ins_alot.req_id);
							ps.setString(3, "ALO");
							ResultSet rs = ps.executeQuery();
							while(rs.next()){
								if(flagggg){
									ins_sms_allot.subject = ins_sms_allot.subject + ",";
								}
								bed_no = rs.getString("bed_no");
								room_no= rs.getString("room_no");
								type= rs.getString("type");

								if(type.equals("R")){
									System.out.println("TESTTTT333");
									ins_sms_allot.subject = ins_sms_allot.subject + room_no;
								}else{
									ins_sms_allot.subject = ins_sms_allot.subject + room_no+"-"+bed_no;
								}
								flagggg = true;
							}
							//close
							rs.close();
							ps.close();
							if(!ins_sms_allot.addSubject()) return false;
						}
						///////////////////////////

						if(ins_alot.res_wl.equals("R")){
							ins_resrv.status="ALO";
							ins_resrv.res_id= ins_alot.res_wl_id;
							ins_resrv.gh_id = ins_alot.gh_id;
							if(!ins_resrv.update_status_alot())return false;
						}
						if(ins_alot.res_wl.equals("W")){
							ins_waitl.status="ALO";
							ins_waitl.res_wid = ins_alot.res_wl_id;
							ins_waitl.gh_id = ins_alot.gh_id;
							if(!ins_waitl.update_status_alot())return false;
						}
					}
					String req_id = p_request.getParameter("req_id");
					boolean t_pending_pro=false;
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where req_id=? and gh_id=? union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where req_id=? and gh_id=? )a where a.status='CNF' or a.status='WMOD'");
					ps.setString(1, req_id);
					ps.setString(2, ins_alot.gh_id);
					ps.setString(3, req_id);
					ps.setString(4, ins_alot.gh_id);
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						t_pending_pro = true;
					}
					//close
					rs.close();
					ps.close();
					if(t_pending_pro){
						ins_gad.status="PALO";
						ins_gad.req_id=req_id;
						ins_gad.gh_id = ins_alot.gh_id;
						if(!ins_gad.update_status())return false; 
					}else{
						ins_gad.status="ALO";
						ins_gad.req_id=req_id;
						ins_gad.gh_id = ins_alot.gh_id;
						if(!ins_gad.update_status())return false; 
					}
				}
				/**/
			} //end if   


			if(t_room_data.equals("")){

				ins_alot.res_wl = p_request.getParameter("tablename");
				ins_alot.res_wl_id = p_request.getParameter("res_id"); //h
				ins_alot.from_date = p_request.getParameter("dateO"+between.get(i));
				if(ins_alot.isalloted()){
					String t_allot_id = ins_alot.allot_id;
					ins_alot.allot_id = t_allot_id;
					ins_alot.status = "CAN";
					ins_alot.gh_id = ins_alot.gh_id;
					if(!ins_alot.update_db_status()) return false; 
				}

			}
//			if(room_no1 == ""){
//				room_no1 = " Room number " + p_request.getParameter("roomnoO"+between.get(i));
//				if(!p_request.getParameter("bednoO"+between.get(i)).equalsIgnoreCase("0")){
//					room_no1 = room_no1 + " Bed number "+p_request.getParameter("bednoO"+between.get(i));
//				}
//				msg = msg + room_no1 + " is alloted in " + block_name + " " + city + " for " + ins_alot.checkin_date ;
//			}else{
//				room_no1 = " "+ " and Room number " + p_request.getParameter("roomnoO"+between.get(i));
//				if(!p_request.getParameter("bednoO"+between.get(i)).equalsIgnoreCase("0")){
//					room_no1 = room_no1 + " Bed number "+p_request.getParameter("bednoO"+between.get(i));
//				}
//				msg = msg + room_no1 + " is alloted in " + block_name + " " + city + " for " + ins_alot.checkin_date ;
//			}
		}  // end for
		room_no1 = " Room number " + p_request.getParameter("roomnoO"+between.get(0));
		if(!p_request.getParameter("bednoO"+between.get(0)).equalsIgnoreCase("0")){
			room_no1 = room_no1 + " Bed number "+p_request.getParameter("bednoO"+between.get(0));
		}
		msg = msg + room_no1 + " is allotted in " + block_name + ", " + city + " for " + ins_common.display_date_calander1(from)+ " to " +ins_common.display_date_calander1( to) + " against your Request No "+ins_alot.req_id+", -Odisha Home Dept";
		System.out.println(msg);
		try {
			if(mob!=null){
				sendVerificationCode(mob,msg);
			}
		} catch (Exception e) {}
		return true;
	}////end function doChangeAllotment

	public boolean doCancelAllotment(HttpServletRequest p_request) throws Exception{ 
		//,,,,,,
		String from = p_request.getParameter("from");
		String to = p_request.getParameter("to");
		String req_id = p_request.getParameter("req_id");
		String tablename = p_request.getParameter("tablename");
		String type = p_request.getParameter("type");
		String res_id = p_request.getParameter("res_id");
		String gh_id = p_request.getParameter("gh_id");
		String hidvaluecancel = p_request.getParameter("hidvaluecancel");

		String[] res_table = hidvaluecancel.split(",");

		String t_res_tab[]=null;
		for(int ii=0;ii<res_table.length;ii++)
		{
			System.out.println("**********************ii*******************"+ii);
			t_res_tab = res_table[ii].split("-");

			ins_alot.req_id = req_id;
			ins_alot.res_wl = t_res_tab[1];
			ins_alot.res_wl_id = t_res_tab[0];
			ins_alot.status = "CAN";
			if(!ins_alot.Update_status_reqres()) return false;
			else{

				System.out.println("Entry Cancel Allotment and Sms Contect");
				d_sms_alltment ins_sms_allot = new d_sms_alltment(NGM);
				ins_sms_allot.req_id = ins_alot.req_id;
				ins_sms_allot.gh_id = gh_id;
				ins_sms_allot.mode = "ALT";
				//ins_sms_allot.status = "P";
				ins_sms_allot.selectDB_status();
				ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";
				System.out.println("11111111111111111111111"+ins_sms_allot.status);
				if(ins_sms_allot.status.equals("P")){
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;
					ins_sms_allot.status = "P";
					ins_sms_allot.subject = "";//Allotment Cancelled, Current Allotments: 
					boolean flag = false;
					String bed_no="",room_no="",t_type="";
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
					ps.setString(1, gh_id);
					ps.setString(2, ins_alot.req_id);
					ps.setString(3, "ALO");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						bed_no = rs.getString("bed_no");
						room_no= rs.getString("room_no");
						t_type= rs.getString("type");

						if(t_type.equals("R")){
							ins_sms_allot.subject = ins_sms_allot.subject + "," + room_no;
							System.out.println("222222222222222222222222222222"+ins_sms_allot.subject);
						}else{
							ins_sms_allot.subject = ins_sms_allot.subject + "," + room_no+"-"+bed_no;
							System.out.println("3333333333333333333333333333333"+ins_sms_allot.subject);
						}
						flag = true;
					}
					//close
					rs.close();
					ps.close();
					if(flag){
						ins_sms_allot.mode = "ALT";
						if(!ins_sms_allot.addSubject()) return false;
					}else{
						ins_sms_allot.req_id = ins_alot.req_id;
						ins_sms_allot.gh_id = gh_id;
						ins_sms_allot.status = "D";
						ins_sms_allot.mode = "ALT";
						if(!ins_sms_allot.updateDB()) return false;
					}



				}else if(ins_sms_allot.status.equals("S")){
					/*ins_sms_allot.req_id = ins_alot.req_id;
								 ins_sms_allot.gh_id = gh_id;
								 ins_sms_allot.mode = "ALT";
								 ins_sms_allot.status = "S";
								 if(ins_sms_allot.selectDB()){*/
					System.out.println("444444444444444444444444444444444"+ins_sms_allot.status);
					ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
					NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;
					d_guestdetails ins_guest=new d_guestdetails(NGM);
					ins_guest.req_id = ins_alot.req_id;
					String mob = ins_guest.selectDB_guestDetails(gh_id);
					String block =  ins_alot.block_id ;
					String block_name = ins_guest.selectDB_guesthouseDetails(block);
					String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
					ins_sms_allot.mobile_no = mob;
					ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

					ins_sms_allot.subject = ""; //Allotment Cancelled.
					boolean flag = false;
					String bed_no="",room_no="",t_type="";
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
					ps.setString(1, gh_id);
					ps.setString(2, ins_alot.req_id);
					ps.setString(3, "ALO");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						if(flag){
							ins_sms_allot.subject = ins_sms_allot.subject + " "; // Current Allotments: 
						}
						bed_no = rs.getString("bed_no");
						room_no= rs.getString("room_no");
						t_type= rs.getString("type");

						if(t_type.equals("R")){
							ins_sms_allot.subject = ins_sms_allot.subject  + room_no;
							System.out.println("5555555555555555555555555555555"+ins_sms_allot.subject);
						}else{
							ins_sms_allot.subject = ins_sms_allot.subject + room_no+"-"+bed_no;
							System.out.println("6666666666666666666666666666666666"+ins_sms_allot.subject);
						}
						flag = true;
					}
					//close
					rs.close();
					ps.close();
					if(!flag){
						ins_sms_allot.subject = "No Rooms"; 
					}

					ins_sms_allot.mode = "ALT";
					ins_sms_allot.status = "P";
					ins_sms_allot.exp_time = ins_alot.to_date;
					if(ins_sms_allot.mobile_no!=null){
						sendVerificationCode(mob,ins_sms_allot.subject,block_name);	
						if(!ins_sms_allot.insertDB()) return false;
					}

				}else if(ins_sms_allot.status.equals("D")){
					/*ins_sms_allot.req_id = ins_alot.req_id;
											 ins_sms_allot.gh_id = gh_id;
											 ins_sms_allot.mode = "ALT";
											 ins_sms_allot.status = "S";
											 if(ins_sms_allot.selectDB()){*/
					System.out.println("77777777777777777777777777777777777"+ins_sms_allot.status);
					ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
					NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;

					d_guestdetails ins_guest=new d_guestdetails(NGM);
					ins_guest.req_id = ins_alot.req_id;
					String mob = ins_guest.selectDB_guestDetails(gh_id);
					String block =  ins_alot.block_id ;
					String block_name = ins_guest.selectDB_guesthouseDetails(block);
					String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
					ins_sms_allot.mobile_no = mob;
					ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

					ins_sms_allot.subject = ""; //Allotment Cancelled.
					boolean flag = false;
					String bed_no="",room_no="",t_type="";
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
					ps.setString(1, gh_id);
					ps.setString(2, ins_alot.req_id);
					ps.setString(3, "ALO");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						if(flag){
							ins_sms_allot.subject = ins_sms_allot.subject + " "; // Current Allotments: 
						}
						bed_no = rs.getString("bed_no");
						room_no= rs.getString("room_no");
						t_type= rs.getString("type");

						if(t_type.equals("R")){
							ins_sms_allot.subject = ins_sms_allot.subject  + room_no;
							System.out.println("8888888888888888888888888888888888888"+ins_sms_allot.subject);
						}else{
							ins_sms_allot.subject = ins_sms_allot.subject + room_no+" "+bed_no;
							System.out.println("99999999999999999999999999999999999999"+ins_sms_allot.subject);
						}
						flag = true;
					}
					//close
					rs.close();
					ps.close();
					if(flag){
						ins_sms_allot.mode = "ALT";
						ins_sms_allot.status = "P";
						ins_sms_allot.exp_time = ins_alot.to_date;
						if(ins_sms_allot.mobile_no!=null){
							sendVerificationCode(mob,ins_sms_allot.subject,block_name);	
							if(!ins_sms_allot.insertDB()) return false;
							System.out.println("100000000000000000000000000"+ins_sms_allot.insertDB());
						}
					}
				}else{

				}

				if(ins_alot.res_wl.equals("R")){
					ins_resrv.status="CNF";
					ins_resrv.res_id= ins_alot.res_wl_id;
					ins_resrv.gh_id= gh_id;
					if(!ins_resrv.update_status_alot())return false;
				}
				if(ins_alot.res_wl.equals("W")){
					ins_waitl.status="WMOD";
					ins_waitl.res_wid = ins_alot.res_wl_id;
					ins_waitl.gh_id= gh_id;
					if(!ins_waitl.update_status_alot())return false;
				}

			}

			req_id = p_request.getParameter("req_id");
			int count_guest = this.getcount_guest(req_id); 
			int count_allot = this.getcount_alot(req_id);//,ins_alot.res_wl_id
			System.out.println("*************count_guest "+count_guest);
			System.out.println("*************count_allot "+count_allot);

			if(count_allot>0){
				ins_gad.status="PALO";
				ins_gad.req_id=req_id;
				ins_gad.gh_id = gh_id;
				if(!ins_gad.update_status())return false; 
			}else{
				ins_gad.status="PRO";
				ins_gad.req_id=req_id;
				ins_gad.gh_id = gh_id;
				if(!ins_gad.update_status())return false; 
			}
		}//end of
		return true;
	}////end function doCancelAllotment
	public boolean doCancelAllotment1(HttpServletRequest p_request) throws Exception{ 
		//,,,,,,
		String from = p_request.getParameter("from");
		String to = p_request.getParameter("to");
		String req_id = p_request.getParameter("req_id");
		String tablename = p_request.getParameter("tablename");
		String type = p_request.getParameter("type");
		String res_id = p_request.getParameter("res_id");
		String gh_id = p_request.getParameter("gh_id");
		String hidvaluecancel = p_request.getParameter("hidvaluecancel");

		String[] res_table = hidvaluecancel.split(",");
		System.out.println("ddddffffffffffffffffffff"+res_table);
		String t_res_tab[]=null;
		for(int ii=0;ii<res_table.length;ii++)
		{
			System.out.println("**********************ii*******************"+ii);
			t_res_tab = res_table[ii].split("-");
			System.out.println("ANITAAAAAAAAAA"+t_res_tab);
			ins_alot.req_id = req_id;
			ins_alot.res_wl = t_res_tab[1];
			ins_alot.res_wl_id = t_res_tab[0];
			ins_alot.status = "CAN";
			if(!ins_alot.Update_status_reqres()) return false;
			else{

				System.out.println("ALOKKKKKKKKKKKKKKKKKKKKKKKK");
				d_sms_alltment ins_sms_allot = new d_sms_alltment(NGM);
				ins_sms_allot.req_id = ins_alot.req_id;
				ins_sms_allot.gh_id = gh_id;
				ins_sms_allot.mode = "ALT";
				//ins_sms_allot.status = "P";
				ins_sms_allot.selectDB_status();
				ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";

				if(ins_sms_allot.status.equals("P")){
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;
					ins_sms_allot.status = "P";
					ins_sms_allot.subject = "";//Allotment Cancelled, Current Allotments: 
					boolean flag = false;
					String bed_no="",room_no="",t_type="";
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
					ps.setString(1, gh_id);
					ps.setString(2, ins_alot.req_id);
					ps.setString(3, "ALO");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						bed_no = rs.getString("bed_no");
						room_no= rs.getString("room_no");
						t_type= rs.getString("type");

						if(t_type.equals("R")){
							ins_sms_allot.subject = ins_sms_allot.subject + "," + room_no;
						}else{
							ins_sms_allot.subject = ins_sms_allot.subject + "," + room_no+"-"+bed_no;
						}
						flag = true;
					}
					//close
					rs.close();
					ps.close();
					if(flag){
						ins_sms_allot.mode = "ALT";
						if(!ins_sms_allot.addSubject()) return false;
					}else{
						ins_sms_allot.req_id = ins_alot.req_id;
						ins_sms_allot.gh_id = gh_id;
						ins_sms_allot.status = "D";
						ins_sms_allot.mode = "ALT";
						if(!ins_sms_allot.updateDB()) return false;
					}



				}else if(ins_sms_allot.status.equals("S")){
					/*ins_sms_allot.req_id = ins_alot.req_id;
								 ins_sms_allot.gh_id = gh_id;
								 ins_sms_allot.mode = "ALT";
								 ins_sms_allot.status = "S";
								 if(ins_sms_allot.selectDB()){*/

					ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
					NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;
					d_guestdetails ins_guest=new d_guestdetails(NGM);
					ins_guest.req_id = ins_alot.req_id;
					String mob = ins_guest.selectDB_guestDetails(gh_id);
					String block =  ins_alot.block_id ;
					String block_name = ins_guest.selectDB_guesthouseDetails(block);
					String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
					ins_sms_allot.mobile_no = mob;
					ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

					ins_sms_allot.subject = ""; //Allotment Cancelled.
					boolean flag = false;
					String bed_no="",room_no="",t_type="";
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
					ps.setString(1, gh_id);
					ps.setString(2, ins_alot.req_id);
					ps.setString(3, "ALO");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						if(flag){
							ins_sms_allot.subject = ins_sms_allot.subject + " "; // Current Allotments: 
						}
						bed_no = rs.getString("bed_no");
						room_no= rs.getString("room_no");
						t_type= rs.getString("type");

						if(t_type.equals("R")){
							ins_sms_allot.subject = ins_sms_allot.subject  + room_no;
						}else{
							ins_sms_allot.subject = ins_sms_allot.subject + room_no+"-"+bed_no;
						}
						flag = true;
					}
					//close
					rs.close();
					ps.close();
					if(!flag){
						ins_sms_allot.subject = "No Rooms"; 
					}

					ins_sms_allot.mode = "ALT";
					ins_sms_allot.status = "P";
					ins_sms_allot.exp_time = ins_alot.to_date;
					if(ins_sms_allot.mobile_no!=null){
						sendVerificationCode(mob,ins_sms_allot.subject,block_name);	
						if(!ins_sms_allot.insertDB()) return false;
					}

				}else if(ins_sms_allot.status.equals("D")){
					/*ins_sms_allot.req_id = ins_alot.req_id;
											 ins_sms_allot.gh_id = gh_id;
											 ins_sms_allot.mode = "ALT";
											 ins_sms_allot.status = "S";
											 if(ins_sms_allot.selectDB()){*/

					ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
					NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;

					d_guestdetails ins_guest=new d_guestdetails(NGM);
					ins_guest.req_id = ins_alot.req_id;
					String mob = ins_guest.selectDB_guestDetails(gh_id);
					String block =  ins_alot.block_id ;
					String block_name = ins_guest.selectDB_guesthouseDetails(block);
					String email_id = ins_guest.selectDB_guestDetailsEmail(ins_alot.gh_id);
					ins_sms_allot.mobile_no = mob;
					ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

					ins_sms_allot.subject = ""; //Allotment Cancelled.
					boolean flag = false;
					String bed_no="",room_no="",t_type="";
					PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
					ps.setString(1, gh_id);
					ps.setString(2, ins_alot.req_id);
					ps.setString(3, "ALO");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						if(flag){
							ins_sms_allot.subject = ins_sms_allot.subject + " "; // Current Allotments: 
						}
						bed_no = rs.getString("bed_no");
						room_no= rs.getString("room_no");
						t_type= rs.getString("type");

						if(t_type.equals("R")){
							ins_sms_allot.subject = ins_sms_allot.subject  + room_no;
						}else{
							ins_sms_allot.subject = ins_sms_allot.subject + room_no+" "+bed_no;
						}
						flag = true;
					}
					//close
					rs.close();
					ps.close();
					if(flag){
						ins_sms_allot.mode = "ALT";
						ins_sms_allot.status = "P";
						ins_sms_allot.exp_time = ins_alot.to_date;
						if(ins_sms_allot.mobile_no!=null){
							sendVerificationCode(mob,ins_sms_allot.subject,block_name);	
							if(!ins_sms_allot.insertDB()) return false;
						}
					}
				}else{

				}
				System.out.println("#############################################");
				if(ins_alot.res_wl.equals("R")){
					ins_resrv.status="CNF";
					ins_resrv.res_id= ins_alot.res_wl_id;
					ins_resrv.gh_id= gh_id;
					if(!ins_resrv.update_status_alot())return false;
				}
				if(ins_alot.res_wl.equals("W")){
					ins_waitl.status="WMOD";
					ins_waitl.res_wid = ins_alot.res_wl_id;
					ins_waitl.gh_id= gh_id;
					if(!ins_waitl.update_status_alot())return false;
				}

			}

			req_id = p_request.getParameter("req_id");
			int count_guest = this.getcount_guest(req_id); 
			int count_allot = this.getcount_alot(req_id);//,ins_alot.res_wl_id
			System.out.println("*************count_guest "+count_guest);
			System.out.println("*************count_allot "+count_allot);

			if(count_allot>0){
				ins_gad.status="PALO";
				ins_gad.req_id=req_id;
				ins_gad.gh_id = gh_id;
				if(!ins_gad.update_status())return false; 
			}else{
				ins_gad.status="CAN";
				ins_gad.req_id=req_id;
				ins_gad.gh_id = gh_id;
				if(!ins_gad.update_status())return false; 
			}
		}//end of
		return true;
	}////end function doCancelAllotment
	public boolean doCancelAllotmentIndiv(HttpServletRequest p_request) throws Exception{ 
		//,,,,,,
		String from = p_request.getParameter("from");
		String to = p_request.getParameter("to");
		String req_id = p_request.getParameter("req_id");
		String tablename = p_request.getParameter("tablename");
		String type = p_request.getParameter("type");
		String res_id = p_request.getParameter("res_id");
		String gh_id = p_request.getParameter("gh_id");

		ins_alot.req_id = req_id;
		ins_alot.res_wl = tablename;
		ins_alot.res_wl_id = res_id;
		ins_alot.status = "CAN";
		if(!ins_alot.Update_status_reqres()) return false;
		else{

			System.out.println("                          elseGOPIKA");
			///////////////////
			d_sms_alltment ins_sms_allot = new d_sms_alltment(NGM);
			ins_sms_allot.req_id = ins_alot.req_id;
			ins_sms_allot.gh_id = gh_id;
			ins_sms_allot.mode = "ALT";
			//ins_sms_allot.status = "P";
			ins_sms_allot.selectDB_status();
			ins_sms_allot.status = ins_sms_allot.status!=null?ins_sms_allot.status:"";

			if(ins_sms_allot.status.equals("P")){
				ins_sms_allot.req_id = ins_alot.req_id;
				ins_sms_allot.gh_id = gh_id;
				ins_sms_allot.status = "P";
				ins_sms_allot.subject = "";//Allotment Cancelled, Current Allotments: 
				boolean flag = false;
				String bed_no="",room_no="",t_type="";
				PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
				ps.setString(1, gh_id);
				ps.setString(2, ins_alot.req_id);
				ps.setString(3, "ALO");
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					bed_no = rs.getString("bed_no");
					room_no= rs.getString("room_no");
					t_type= rs.getString("type");

					if(t_type.equals("R")){
						ins_sms_allot.subject = ins_sms_allot.subject + "," + room_no;
					}else{
						ins_sms_allot.subject = ins_sms_allot.subject + "," + room_no+"-"+bed_no;
					}
					flag = true;
				}
				//close
				rs.close();
				ps.close();
				if(flag){
					ins_sms_allot.mode = "ALT";
					if(!ins_sms_allot.addSubject()) return false;
				}else{
					ins_sms_allot.req_id = ins_alot.req_id;
					ins_sms_allot.gh_id = gh_id;
					ins_sms_allot.status = "D";
					ins_sms_allot.mode = "ALT";
					if(!ins_sms_allot.updateDB()) return false;
				}



			}else if(ins_sms_allot.status.equals("S")){
				/*ins_sms_allot.req_id = ins_alot.req_id;
								 ins_sms_allot.gh_id = gh_id;
								 ins_sms_allot.mode = "ALT";
								 ins_sms_allot.status = "S";
								 if(ins_sms_allot.selectDB()){*/

				ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
				ins_sms_allot.req_id = ins_alot.req_id;
				ins_sms_allot.gh_id = gh_id;

				d_guestdetails ins_guest=new d_guestdetails(NGM);
				ins_guest.req_id = ins_alot.req_id;
				String mob = ins_guest.selectDB_guestDetails(gh_id);
				ins_sms_allot.mobile_no = mob;
				ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

				ins_sms_allot.subject = ""; //Allotment Cancelled.
				boolean flag = false;
				String bed_no="",room_no="",t_type="";
				PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
				ps.setString(1, gh_id);
				ps.setString(2, ins_alot.req_id);
				ps.setString(3, "ALO");
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					if(flag){
						ins_sms_allot.subject = ins_sms_allot.subject + ","; // Current Allotments: 
					}
					bed_no = rs.getString("bed_no");
					room_no= rs.getString("room_no");
					t_type= rs.getString("type");

					if(t_type.equals("R")){
						ins_sms_allot.subject = ins_sms_allot.subject  + room_no;
					}else{
						ins_sms_allot.subject = ins_sms_allot.subject + room_no+"-"+bed_no;
					}
					flag = true;
				}
				//close
				rs.close();
				ps.close();
				if(!flag){
					ins_sms_allot.subject = "No Rooms"; 
				}

				ins_sms_allot.mode = "ALT";
				ins_sms_allot.status = "P";
				ins_sms_allot.exp_time = ins_alot.to_date;
				if(ins_sms_allot.mobile_no!=null){
					if(!ins_sms_allot.insertDB()) return false;
				}

			}else if(ins_sms_allot.status.equals("D")){
				/*ins_sms_allot.req_id = ins_alot.req_id;
											 ins_sms_allot.gh_id = gh_id;
											 ins_sms_allot.mode = "ALT";
											 ins_sms_allot.status = "S";
											 if(ins_sms_allot.selectDB()){*/

				ins_sms_allot.a_sms_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "a_sms_id").toString(); 
				NGM.ins_utility.updateseqID(NGM.tr_conn, "a_sms_id", Integer.parseInt(ins_sms_allot.a_sms_id));
				ins_sms_allot.req_id = ins_alot.req_id;
				ins_sms_allot.gh_id = gh_id;

				d_guestdetails ins_guest=new d_guestdetails(NGM);
				ins_guest.req_id = ins_alot.req_id;
				String mob = ins_guest.selectDB_guestDetails(gh_id);
				ins_sms_allot.mobile_no = mob;
				ins_sms_allot.guest_details = ins_guest.name+(ins_guest.designation!=null?","+ins_guest.designation:"")+(ins_guest.department!=null?","+ins_guest.department:"");

				ins_sms_allot.subject = ""; //Allotment Cancelled.
				boolean flag = false;
				String bed_no="",room_no="",t_type="";
				PreparedStatement ps=NGM.tr_conn.prepareStatement("select DISTINCT ON(room_no) * from tr_allotment where gh_id=? and req_id=? and status=?");
				ps.setString(1, gh_id);
				ps.setString(2, ins_alot.req_id);
				ps.setString(3, "ALO");
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					if(flag){
						ins_sms_allot.subject = ins_sms_allot.subject + ","; // Current Allotments: 
					}
					bed_no = rs.getString("bed_no");
					room_no= rs.getString("room_no");
					t_type= rs.getString("type");

					if(t_type.equals("R")){
						ins_sms_allot.subject = ins_sms_allot.subject  + room_no;
					}else{
						ins_sms_allot.subject = ins_sms_allot.subject + room_no+"-"+bed_no;
					}
					flag = true;
				}
				//close
				rs.close();
				ps.close();
				if(flag){
					ins_sms_allot.mode = "ALT";
					ins_sms_allot.status = "P";
					ins_sms_allot.exp_time = ins_alot.to_date;
					if(ins_sms_allot.mobile_no!=null){
						if(!ins_sms_allot.insertDB()) return false;
					}
				}
			}else{

			}
			///////////////////////////

			if(ins_alot.res_wl.equals("R")){
				ins_resrv.status="CNF";
				ins_resrv.res_id= ins_alot.res_wl_id;
				ins_resrv.gh_id=gh_id;
				if(!ins_resrv.update_status_alot())return false;
			}
			if(ins_alot.res_wl.equals("W")){
				ins_waitl.status="WMOD";
				ins_waitl.res_wid = ins_alot.res_wl_id;
				ins_waitl.gh_id=gh_id;
				if(!ins_waitl.update_status_alot())return false;
			}

		}

		req_id = p_request.getParameter("req_id");
		int count_guest = this.getcount_guest(req_id); 
		int count_allot = this.getcount_alot(req_id);//,ins_alot.res_wl_id
		System.out.println("*************count_guest "+count_guest);
		System.out.println("*************count_allot "+count_allot);

		if(count_allot>0){
			ins_gad.status="PALO";
			ins_gad.req_id=req_id;
			ins_gad.gh_id = gh_id;
			if(!ins_gad.update_status())return false; 
		}else{
			ins_gad.status="PRO";
			ins_gad.req_id=req_id;
			ins_gad.gh_id = gh_id;
			if(!ins_gad.update_status())return false; 
		}
		/*d_sms_alltment ins_sms_allot = new d_sms_alltment(NGM);
			 ins_sms_allot.req_id = req_id;
			 ins_sms_allot.gh_id = gh_id;
			 ins_sms_allot.status = "P";
			 if(!ins_sms_allot.selectDB()){
			///////////////////
				 ins_sms_allot.req_id = req_id;
				 ins_sms_allot.gh_id = gh_id;
		    	ins_sms_allot.status = "D";
		    	if(!ins_sms_allot.updateDB()) return false;
		    ///////////////////////////
			 }*/
		return true;
	}////end function doCancelAllotment

	public boolean doSaveAllotmentOnduty(HttpServletRequest p_request) throws Exception{ 
		boolean retval = false;
		String from = p_request.getParameter("from");
		String to = p_request.getParameter("to");
		String req_id = p_request.getParameter("req_id");
		String rent = p_request.getParameter("rent");
		String gh_id = p_request.getParameter("gh_id");
		String block = p_request.getParameter("block");
		String room = p_request.getParameter("room");
		String tablename = p_request.getParameter("tablename");
		String type = p_request.getParameter("type");
		String res_id = p_request.getParameter("res_id");
		String roomtype = p_request.getParameter("roomtype");
		String[] roomtypeval = roomtype.split("-");

		ins_alot.req_id = req_id;
		ins_alot.res_wl_id = res_id;

		ins_alot.type = roomtypeval[1];  //// type;
		ins_alot.status = "ALO";
		ins_alot.gh_id = gh_id;
		ins_alot.block_id = block;
		ins_alot.room_no = room;
		ins_alot.bed_no = roomtypeval[0];
		ins_alot.res_wl = tablename;
		ins_alot.tr_id = "";

		//////////////////////////////

		System.out.println("nagg "+from);
		DateTime startstr = DateTime.parse(from);
		System.out.println("Start: " + startstr);

		DateTime endstr = DateTime.parse(to);
		System.out.println("End: " + endstr);
		common ins_common = new common(NGM);
		ArrayList<String> between = ins_common.getDateRangeString(startstr,endstr);
		for(int i=0;i<between.size();i++){
			ins_alot.allot_id  = NGM.ins_utility.get_seqID(NGM.tr_conn, "allot_id").toString(); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "allot_id", Integer.parseInt(ins_alot.allot_id));

			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%gopika&&&&&&&&&&&&&&&&&&&&&&&"+between.get(i));
			ins_alot.from_date = between.get(i);
			//			ins_alot.to_date = between.get(i);
			ins_alot.checkin_date = between.get(i);
			//			ins_alot.checkout_date = between.get(i);
			ins_alot.room_rent = rent;

			Date dt = null;
			if(ins_alot.from_date != null){
				if(!ins_alot.from_date.isEmpty()){
					dt = new SimpleDateFormat("dd-MM-yyyy").parse(ins_alot.from_date);
				}
			}
			Calendar c = Calendar.getInstance(); 
			if(dt != null){
				c.setTime(dt); 
				c.add(Calendar.DATE, 1);
				dt = c.getTime();
			}
			if(dt != null){
				ins_alot.to_date = ins_common.parse_string(dt);
				ins_alot.checkout_date = ins_common.parse_string(dt);
			}

			if(!ins_alot.insertDB())
			{
				retval =  false;	
			}
			else{
				if(ins_alot.res_wl.equals("R")){
					ins_resrv.status="ALO";
					ins_resrv.res_id= ins_alot.res_wl_id;
					ins_resrv.gh_id= ins_alot.gh_id;
					if(!ins_resrv.update_status_alot())return false;
				}
				if(ins_alot.res_wl.equals("W")){
					ins_waitl.status="ALO";
					ins_waitl.res_wid = ins_alot.res_wl_id;
					ins_waitl.gh_id= ins_alot.gh_id;
					if(!ins_waitl.update_status_alot())return false;
				}
				retval = true;
			}
		}

		boolean t_pending_pro=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where req_id=? and gh_id=? union select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where req_id=? and gh_id=?)a where a.status='CNF' or a.status='WMOD'");
		ps.setString(1, req_id);
		ps.setString(2, ins_alot.gh_id);
		ps.setString(3, req_id);
		ps.setString(4, ins_alot.gh_id);

		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			t_pending_pro = true;
		}

		if(t_pending_pro){
			ins_gad.status="PALO";
			ins_gad.req_id=req_id;
			ins_gad.gh_id=ins_alot.gh_id;
			if(!ins_gad.update_status())return false; 
		}else{
			ins_gad.status="ALO";
			ins_gad.req_id=req_id;
			ins_gad.gh_id=ins_alot.gh_id;
			if(!ins_gad.update_status())return false; 
		}

		return retval;
	}
}







