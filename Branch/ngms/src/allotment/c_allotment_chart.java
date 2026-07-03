package allotment;

import db.d_allotment;
import db.d_checkin_allot;
import db.d_gadrequest;
import db.d_reservation_maintable;
import db.d_reservation_waitinglist;
import db.ngm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.joda.time.DateTime;

import common.common;
import common.utility;
import db.*;

public class c_allotment_chart {

	public ngm NGM;
	d_allotment ins_alot;
	d_reservation_maintable ins_resrv;
	d_reservation_waitinglist ins_waitl;
	d_gadrequest ins_gad;
	utility 		ins_util;
	common ins_common;

	public String res_id,req_id,from,todate, table_name, room_type, gh_id,sel_block;

	public ArrayList<c_blockchart> block_list = new ArrayList<c_blockchart>();


	public c_allotment_chart(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_resrv	 = new d_reservation_maintable(NGM);
		ins_alot		= new d_allotment(NGM);
		ins_waitl = new  d_reservation_waitinglist(NGM);
		ins_gad = new d_gadrequest(NGM);
		ins_common = new common(NGM);
	}

	public boolean generate_blocklist(String pghid) throws SQLException
	{
		System.out.println("GEN BLOCK LIST 2 "+pghid);

		PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from room_block where gh_id=? order by block_id::Integer");
		ps.setString(1, pghid);
		System.out.println("PS gh blk "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){

			String tblock_id=rs.getString("block_id");
			System.out.println("BLLLLID="+tblock_id);
			String tblock_name=rs.getString("block_name");
			c_blockchart temp_block_chartobj = new c_blockchart();
			temp_block_chartobj.block_id = tblock_id;
			temp_block_chartobj.block_title = tblock_name;

			c_table_chart temp_obj = new c_table_chart();
			temp_obj.block_id=tblock_id;
			temp_obj.gh_id=pghid;  /////gh


			c_table_chart temp_obj_dorm = new c_table_chart();
			temp_obj_dorm.block_id = tblock_id;
			temp_obj_dorm.gh_id=pghid; /////gh

			temp_block_chartobj.room_tablechart= temp_obj;
			temp_block_chartobj.dorm_tablechart = temp_obj_dorm;
			System.out.println("DO_ACTION block_id "+temp_obj.block_id);

			block_list.add(temp_block_chartobj);

		}
		//close
		rs.close();
		ps.close();
		return true;
	}

	public ArrayList<String> generate_datelist() throws Exception
	{
		// for i this.from date to this.todate for the arrarylist
		System.out.println("GEN DATE LIST 4");
		try{
			if(from != null){
				if(!from.isEmpty()){
					String tmp[] = from.split("-");
					if(tmp != null){
						if(tmp[0].length() == 4){
							from = tmp[2] + "-" + tmp[1] + "-" + tmp[0] + " 00:00";;
						}
					}
				}
			}
			if(todate != null){
				if(!todate.isEmpty()){
					String tmp[] = todate.split("-");
					if(tmp != null){
						if(tmp[0].length() == 4){
							todate = tmp[2] + "-" + tmp[1] + "-" + tmp[0] + " 00:00";
						}
					}
				}
			}
		}catch(Exception e){}
		ArrayList<String> between= (ArrayList<String>) ins_common.getdateString(ins_common.display_date_calander(from),ins_common.display_date_calander(todate));   
		/* System.out.println("date"+between);

		     System.out.println("date22"+between.get(2));
		      for (DateTime d : between) {
		          String date1 = ins_common.display_time_zone(d.toString());

		      }*/
		/*	System.out.println("between 0 "+between.get(0));
		  	System.out.println("between 1 "+between.get(1));
		  	System.out.println("between 2 "+between.get(2));
		 */
		return between;
	}

	public boolean generate_tablelist(c_table_chart ptableobj, String R_D) throws Exception
	{
		System.out.println("GEN TABLE LIST 3");
		ptableobj.date_list  = generate_datelist();

		ArrayList<String> room_list  = new ArrayList<String>();
		///ArrayList<String> dorm_list  = new ArrayList<String>();////

		ArrayList<c_row_chart> row_chart_list   = new ArrayList<c_row_chart>();

		boolean l_return = false;
		PreparedStatement ps = NGM.tr_conn.prepareStatement("select * from room_details where block_id=? and gh_id=? and room_type=? order by (room_no)");

		ps.setString(1, ptableobj.block_id);
		ps.setString(2, ptableobj.gh_id);
		ps.setString(3, R_D);

		System.out.println("GEN TABLE LIST 3    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			///String temptype = rs.getString("room_no");
			String temproomno = rs.getString("room_no");
			String temproombeds = rs.getString("max_no_beds");

			room_list.add(temproomno);

			//System.out.println("temproomno "+temproomno); 

			c_row_chart temp_rowchart = new c_row_chart();
			//loop date_list
			int i=0;
			while(i<ptableobj.date_list.size())
			{
//				c_column_chart temp_col_chart=  generate_col_chart(temproomno,ptableobj.date_list.get(i),Integer.parseInt(temproombeds));
				c_column_chart temp_col_chart = generate_col_chart_by_block(temproomno,ptableobj.date_list.get(i),Integer.parseInt(temproombeds),ptableobj.gh_id,ptableobj.block_id);
				temp_rowchart.col_list.add(temp_col_chart);
				i++;
			}
			row_chart_list.add(temp_rowchart);
			l_return = true;
		}
		//close
		rs.close();
		ps.close();
		ptableobj.room_list = room_list;
		ptableobj.row_chart_list = row_chart_list;
		return l_return;
		// select room
	}

	public c_column_chart generate_col_chart(String proomno, String pdate,int pno_of_beds) throws Exception
	{
		ArrayList<d_allotment> temp_d_allotmentlist = new ArrayList<d_allotment>();
		c_column_chart temp_col_chart = new c_column_chart();
		temp_col_chart.roomno = proomno;
		temp_col_chart.date  = pdate;
		temp_col_chart.max_no_beds=pno_of_beds;

		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and type in ('BL','BG','R') and from_date=to_date(?,'yyyy-MM-dd') and (status='ALO' or status='CIN')");// Additional Bed Modification
		ps.setString(1, proomno);
		ps.setString(2, ins_common.display_time_zonenew(pdate));
		System.out.println("pssssssssss    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			d_allotment temp_allot_obj= new d_allotment(NGM);
			temp_allot_obj.req_id = rs.getString("req_id");// done shaik
			temp_allot_obj.room_no = proomno;
			temp_allot_obj.from_date = pdate;
			temp_allot_obj.status=rs.getString("status");
			temp_allot_obj.allot_id = rs.getString("allot_id");
			temp_allot_obj.room_no = rs.getString("room_no");
			temp_allot_obj.bed_no = rs.getString("bed_no");
			temp_allot_obj.type = rs.getString("type");
			temp_allot_obj.res_wl_id = rs.getString("res_wl_id");
			temp_allot_obj.res_wl = rs.getString("res_wl");
			temp_col_chart.status=rs.getString("status"); 
			temp_d_allotmentlist.add(temp_allot_obj);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&psall    "+temp_col_chart.status);
		}
		//close
		rs.close();
		ps.close();

		temp_col_chart.list_alloted=temp_d_allotmentlist;
		return temp_col_chart;
	}
	
	public c_column_chart generate_col_chart_by_block(String proomno, String pdate,int pno_of_beds,String gh_id,String block_id) throws Exception
	{
		ArrayList<d_allotment> temp_d_allotmentlist = new ArrayList<d_allotment>();
		c_column_chart temp_col_chart = new c_column_chart();
		temp_col_chart.roomno = proomno;
		temp_col_chart.date  = pdate;
		temp_col_chart.max_no_beds=pno_of_beds;

		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_allotment where room_no=? and gh_id=? and block_id=? and type in ('BL','BG','R') and from_date=to_date(?,'yyyy-MM-dd') and (status='ALO' or status='CIN')");// Additional Bed Modification
		ps.setString(1, proomno);
		ps.setString(2, gh_id);
		ps.setString(3, block_id);
		ps.setString(4, ins_common.display_time_zonenew(pdate));
		System.out.println("pssssssssss    "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			d_allotment temp_allot_obj= new d_allotment(NGM);
			temp_allot_obj.req_id = rs.getString("req_id");// done shaik
			temp_allot_obj.room_no = proomno;
			temp_allot_obj.from_date = pdate;
			temp_allot_obj.status=rs.getString("status");
			temp_allot_obj.allot_id = rs.getString("allot_id");
			temp_allot_obj.room_no = rs.getString("room_no");
			temp_allot_obj.bed_no = rs.getString("bed_no");
			temp_allot_obj.type = rs.getString("type");
			temp_allot_obj.res_wl_id = rs.getString("res_wl_id");
			temp_allot_obj.res_wl = rs.getString("res_wl");
			temp_col_chart.status=rs.getString("status"); 
			temp_d_allotmentlist.add(temp_allot_obj);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&psall    "+temp_col_chart.status);
		}
		//close
		rs.close();
		ps.close();

		temp_col_chart.list_alloted=temp_d_allotmentlist;
		return temp_col_chart;
	}

	public boolean do_action() throws Exception
	{
		System.out.println("DO_ACTION 1 ");

		generate_blocklist(gh_id);
		int i=0;
		while(i<block_list.size())
		{
			c_blockchart  tblockchart =block_list.get(i);
			generate_tablelist(tblockchart.dorm_tablechart, "D");

			generate_tablelist(tblockchart.room_tablechart,"R");

			System.out.println("raju >>>>>> tblockchart.dorm_tablechart vv "+tblockchart.dorm_tablechart.room_list.size());
			System.out.println("raju >>>>>> tblockchart.room_tablechart vv "+tblockchart.room_tablechart.room_list.size());

			i++;
		}

		return true;
	}

	public static void main(String ar[])
	{
		//c_allotment_chart.from, to, req_id, table_name,res_id)
		//c_allotment_chart.doaction();
	}
}

