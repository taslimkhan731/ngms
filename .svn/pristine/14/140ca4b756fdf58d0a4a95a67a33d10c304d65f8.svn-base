package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import allotment.c_column_chart;
import allotment.c_row_chart;
import allotment.c_selected_details;
import common.common;
import common.utility;
import db.*;

public class m_statusallotment extends ngmodel{
	
	  utility 		ins_util;
	   
	  d_allotment ins_alot;
	  d_reservation_maintable ins_resrv;
	  d_reservation_waitinglist ins_waitl;
	  d_gadrequest ins_gad;
	  d_checkin_allot ins_chk;
	  d_allotment ins_allot;
	 // 
	  public ArrayList<c_selected_details> selectedlistdet =null;
	public m_statusallotment(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_resrv	 = new d_reservation_maintable(NGM);
		ins_alot		= new d_allotment(NGM);
		ins_waitl = new  d_reservation_waitinglist(NGM);
		ins_gad = new d_gadrequest(NGM);
		ins_chk = new d_checkin_allot(NGM);
		ins_allot = new d_allotment(NGM);
		selectedlistdet =new ArrayList<c_selected_details>();
		
		}
	
	
	public int getcount_guest(String req_id)throws Exception{
		int count=0;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select count(*) from guest_details where req_id=?");
		ps.setString(1, req_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		 //close
	    rs.close();
		ps.close();
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
		 //close
	    rs.close();
		ps.close();
		return count;	
		}
	
	public String searchrequest(HttpServletRequest request)throws Exception{
		  
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String rno=(String)request.getParameter("processid");
		String date=(String)request.getParameter("date");
		System.out.println("combooooooooooooooo "+rno);
		
		/*if((req_id.equals(""))&&(!name.equals(""))){
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.name = name+"%";
			if(ins_guest.selectDB_reqid()){
				req_id = ins_guest.req_id;
			}else{
				req_id = "NO";
			}
		}*/
		
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
						criteria += join +" c.req_id like '"+req_id+"'";
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
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +"'"+date+"' between date(d.from_date) and date(d.to_date)";
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
		String tableid="TAB_"+roomno+"_"+dateid;
		String roomcellid="CELL_"+roomno+"_"+dateid;
		String draw="";
		//<span onclick=selectroom(pre_id, 'R','R', roomno, pdate, max_no_room)> room </room>
		//do_select(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno, pnumberofbeds,p_res_type)
		draw = "<td><table id='"+tableid+"'>"+		 
				" <tr><td"
				+" onclick=\" do_deselect('"+tableid+"','"+roomcellid+"','"+source_status+"','"+p_room_type+"','','"+pdate+"','',"+max_no_bed+",'',"+pgh_id+","+pblock_id+")\" id='"+roomcellid+"' colspan=\"4\"> <span class=\"notselected btn-block\" id=\"aa\" >R</span></td>"+
				"</tr>"+
				"<tr>";
		
		for(int k=1;k<=max_no_bed;k++)
		{
			String tempbedno="B"+k;
			String bedcellid="CELL_"+roomno+"_"+dateid+tempbedno;
				//<span onclick=selectbed(pre_id, 'R','B', roomno, pdate, max_no_room, bk);
			draw = 	draw + "<td "
					+"do_deselect('"+tableid+"','"+roomcellid+"','"+source_status+"','"+p_room_type+"','','"+pdate+"','',"+max_no_bed+",'',"+pgh_id+","+pblock_id+")\" id='"+bedcellid+"'><span class=\"notselected\" > B"+k+"</span></td>";

		}			draw = draw + "</tr>"+
				"</table></td>";
		return draw;
	}      
		public String selected_room_draw(String source_status,String p_room_type, String proomno, String pbedno,String pdate, int max_no_bed,String res_type,String pgh_id,String pblock_id) throws SQLException
		{
			String dateid = this.getdateid(pdate);
			String tableid="TAB_"+proomno+"_"+dateid;
			String roomcellid="CELL_"+proomno+"_"+dateid;
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! rooom");
			c_selected_details selectedlist = new c_selected_details();
			selectedlist.setParameters(tableid, roomcellid, source_status, p_room_type, proomno, pdate, pbedno,max_no_bed, res_type, pgh_id, pblock_id);
			selectedlistdet.add(selectedlist);
			String draw="";
			
			draw = "<td><table id='"+tableid+"'>"+	
					 " <tr><td id='"+roomcellid+"' colspan=\"4\"> "  +
					                                                                                     
					 " <span  class=\"\" do_select('"+tableid+"','"+roomcellid+"','"+source_status+"','"+p_room_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+res_type+"',"+pgh_id+","+pblock_id+")\" >"+
					 p_room_type+" </span>	 "+		//proomno	  
					"</td> "+
					"</tr> 		 "+				
					"</table></td>";
			return draw;
		}

		public String vac_bed_draw(String source_status,String p_room_type, String proomno, String pbedno,String pdate, int max_no_bed,String res_type,String pgh_id,String pblock_id)
		{
			String dateid = this.getdateid(pdate);
			String tableid="TAB_"+proomno+"_"+dateid;
			String roomcellid="CELL_"+proomno+"_"+dateid;
			String draw="";
			String tempbedno=pbedno;
			String bedcellid="CELL_"+proomno+"_"+dateid+tempbedno;
			System.out.println("p_room_type?????????????????????????????????????????????????? "+p_room_type);
			 draw= "<td  id='"+bedcellid+"' > "+
					" <span class=\"notselected\"do_select('"+tableid+"','"+bedcellid+"','"+source_status+"','"+p_room_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+res_type+"',"+pgh_id+","+pblock_id+")\""+
			" > "+pbedno+"</span></td>";
			                                                                
			return draw;
		}

		public String selected_bed_draw(String source_status,String p_room_type, String proomno, String pbedno,String pdate, int max_no_bed,String res_type,String pgh_id,String pblock_id) throws SQLException
		{
			String dateid = this.getdateid(pdate);
			String tableid="TAB_"+proomno+"_"+dateid;
			String roomcellid="CELL_"+proomno+"_"+dateid;
			String draw="";
			String tempbedno=pbedno;
			String bedcellid="CELL_"+proomno+"_"+dateid+tempbedno;
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! beddd");

			c_selected_details selectedlist = new c_selected_details();
			selectedlist.setParameters(tableid, roomcellid, source_status, p_room_type, proomno, pdate, pbedno,max_no_bed, res_type, pgh_id, pblock_id);
			selectedlistdet.add(selectedlist);


			
			draw= "<td  id='"+bedcellid+"' > "+
			" <span class=\"selected_bed\" onclick=\" do_deselect('"+tableid+"','"+bedcellid+"','"+source_status+"','"+p_room_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+res_type+"',"+pgh_id+","+pblock_id+")\""+ //////selectedbox
			" > "+pbedno+"</span></td>";
			                                                      
			System.out.println("drawdraw drawdraw SELECTED., "+draw);
			return draw;
		}

		public String occ_room_box(String req_id, String guest_id,String roomno)
		{
			String draw="";
			//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
			draw = "<td><table id=\"\">"+		 
					"<tr><td> <span class=\"selectedbox alreadyselected\" id=\"aa\">R</span></td>"+
					"</tr>"+
					"</table></td>";
			return draw;
		}

		public String occ_room_bed_draw(String req_id, String guest_id,String room_type,String roomno, String bedno)//String room_type(BL,BG)
		{
			String draw="";
			System.out.println("0000000000000000000000000000000000000000000000000000");
			System.out.println("req_id "+req_id);
			System.out.println("guest_id "+guest_id);
			System.out.println("room_type "+room_type);
			System.out.println("roomno "+roomno);
			System.out.println("bedno "+bedno);

			//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
		draw = "<td> <span class=\"alreadyselected\" id=\"aa\">"+room_type+"</span></td>";
		System.out.println("Occtest****************************8");
		System.out.println(draw);
		
			return draw;
		}


	public String draw_room_box(c_column_chart temp_col_chart,String res_id,String ptablename,String prestype,String pgh_id,String pblock_id) throws Exception //String cur_allotid
		{
		
		String status="";
		String drawbox = "";
		
		ins_allot.res_wl_id = res_id;
		ins_allot.res_wl = ptablename;
		ins_allot.from_date = temp_col_chart.date;
		//System.out.println("ins_allot.from_date "+ins_allot.from_date);
		String cur_allotid = "",tguest_id="",treq_id="";
		if(ins_allot.isallotedstatus()){
			status=ins_allot.status;
			cur_allotid = ins_allot.allot_id;
			d_checkin_allot ins_chkalot = new d_checkin_allot(NGM);
			ins_chkalot.allot_id = cur_allotid;
			System.out.println("ins_chkalot.allot_id "+ins_chkalot.allot_id);
			ins_chkalot.select_check_allot();
			
			d_checkin ins_chk = new d_checkin(NGM);
			ins_chk.tr_id = ins_chkalot.tr_id;
			System.out.println("ins_chk.tr_id "+ins_chk.tr_id);

			ins_chk.selectDB();
			tguest_id = ins_chk.guest_id;
			treq_id = ins_chk.req_id;
		}else{
			
		}
		
		
	
			String t_roomno = temp_col_chart.roomno;
			ins_allot.room_no=t_roomno;
			ins_allot.select_room();
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
		  				drawbox = drawbox + vac_room_box("R", prestype, t_roomno,t_date, t_max_no_beds,pgh_id,pblock_id);
		  				///////////////////////////////String source_status,String p_room_type, String roomno, String pdate, int max_no_bed
		  			}

		  			else if(temp_alloted_roomtype.equals("R")) {
		  				
							d_allotment temp_d_allot = pallotedlist.get(0);
						
				  				if(temp_d_allot.res_wl_id.equals(res_id) && temp_d_allot.res_wl.equals(ptablename) && ( temp_d_allot.room_no.equals(t_roomno)) ){
				  							  	
				  					drawbox = drawbox + selected_room_draw("R", temp_d_allot.type, temp_d_allot.room_no,"0",t_date, t_max_no_beds,prestype,pgh_id,pblock_id); //max_no_beds max_no_room
				  					                                       
								}
								else			
								{  
									
									drawbox = drawbox + occ_room_box1(treq_id, tguest_id, t_roomno, t_date);
								}
		  			
		  			
		  				 
		  			} //end of else if

		  		


  			else if(temp_alloted_roomtype.equals("BL")||temp_alloted_roomtype.equals("BG"))  {
  				System.out.println("bbb---00000-----bbb   *g1");
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
							
							System.out.println("temp_d_allot.res_wl_id ?;1 "+temp_d_allot.res_wl_id);
							System.out.println(".res_id ?; 1 "+res_id);

							System.out.println("temp_d_allot.res_wl ?; 1  "+temp_d_allot.res_wl);

							System.out.println(".ptablename ?; 1 "+ptablename);
							
							}
						else
						{
							
							System.out.println("temp_d_allot.res_wl_id ?; "+temp_d_allot.res_wl_id);
							System.out.println(".res_id ?; "+res_id);

							System.out.println("temp_d_allot.res_wl ?; "+temp_d_allot.res_wl);

							System.out.println(".ptablename ?; "+ptablename);
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
			String tableid="TAB_"+troomno+"_"+dateid;
			
			
			
			String draw="";
			draw = "<td><table id=\""+tableid+"\">	<tr>";
			
			

				for(int k=1;k<=t_max_no_beds;k++) {
					System.out.println("no -of bed iter *g3"+k );
					String tempbedno="B"+k;
					boolean tmpstatus = false; 
					//int ii = 0;
					String temp_selected_type="";
					System.out.println("Size is getting 2 or not "+pallotedlist.size() );
					for(int ii=0;ii < pallotedlist.size() ;++ii)
					{
						System.out.println("Iteration ="+ii);
						System.out.println("*g4 ");

						d_allotment temp_d_allot = pallotedlist.get(ii);
						temp_selected_type = temp_d_allot.type;
						if( temp_d_allot.bed_no.equals(tempbedno)) {
							System.out.println("*g5 ");

							if(temp_d_allot.res_wl_id.equals(res_id) && temp_d_allot.res_wl.equals(ptablename) && ( temp_d_allot.room_no.equals(troomno))) {
								
								System.out.println("Iteration inside selected ="+ii);
								System.out.println("*g6 "+temp_d_allot.room_no);
								System.out.println("*shaik "+source_type);
					
								draw = draw + selected_bed_draw(source_type, temp_d_allot.type, temp_d_allot.room_no,tempbedno,t_date, t_max_no_beds, prestype,pgh_id,pblock_id); //max_no_beds max_no_room
							    System.out.println("qweeeen  ----- sel "+draw);                            

								tmpstatus = true; //break;
							}
							else			
							{  
								System.out.println("Iteration inside occupied ="+ii);
								System.out.println("*g7 "+temp_d_allot.room_no);
							    System.out.println("qweeeen  ----- occu "+draw);                            

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
		
		
		
		public String occ_room_box1(String req_id, String guest_id,String roomno, String tdate)
		{
			String draw="";		
			String name= "",status="";
			try{
			ins_alot.room_no = roomno;
			ins_alot.bed_no = "0";
			common ins_com = new common(NGM);
			ins_alot.from_date = ins_com.display_time_zonenew(tdate);
			ins_alot.select_room_guest();
			String t_ghid = ins_alot.gh_id;
			String t_reqid = ins_alot.req_id;
			
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.req_id = t_reqid;
			ins_guest.selectDB_guestDetails(t_ghid);
			
			
			name = ins_guest.name;
			status=ins_guest.sstatus;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
			
			if(ins_allot.status.equals("CIN"))
			{
			
			//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
			draw = "<td><table id=\"\">"+		 
					"<tr><td> <span data-toggle='tooltip' title='CHECKED IN' data-placement='bottom' class=\"selectedbox alreadyselectedy showTooltip\" id=\"aa\">R</span></td>"+
					"</tr>"+
					"</table></td>";
			
			}
			else if(ins_allot.status.equals("ALO"))
			{
				draw = "<td><table id=\"\">"+		 
						"<tr><td> <span data-toggle='tooltip' title='ALLOTED' data-placement='bottom' class=\"selectedbox alreadyselected showTooltip\" id=\"aa\">R</span></td>"+
						"</tr>"+
						"</table></td>";
				
			}
			return draw;
		}
		
		
		public String occ_room_box2(String req_id, String guest_id,String roomno,String tdate)
		{
					
			String name= "";
			try{
			ins_alot.room_no = roomno;
			ins_alot.bed_no = "0";
			common ins_com = new common(NGM);
			ins_alot.from_date = ins_com.display_time_zonenew(tdate);
			ins_alot.select_room_guest();
			String t_ghid = ins_alot.gh_id;
			String t_reqid = ins_alot.req_id;
			
			d_guestdetails ins_guest = new d_guestdetails(NGM);
			ins_guest.req_id = t_reqid;
			ins_guest.selectDB_guestDetails(t_ghid);
			
			
			name = ins_guest.name;
		}catch (Exception e) {
			// TODO: handle exception
		}
			
			String draw="";
			//<span onclick=showdetails(reqid,guest_id,res_id,tablename)> occ_room </room>
			draw = "<td><table id=\"\">"+		 
					"<tr><td> <span data-toggle='tooltip' title='"+name+"' data-placement='bottom' class=\"selectedbox alreadyselectedy showTooltip\" id=\"aa\">R</span></td>"+
					"</tr>"+
					"</table></td>";
			return draw;
		}


}






