<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="allotment.*"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="common_ajax.jsp"%>


<%
	class date_rec {
		String oc_date = "";
		ArrayList<String> guestlist = new ArrayList<String>();
	}
	class allot_row_class {
		String roomno = "";
		ArrayList<date_rec> listofdate = new ArrayList<date_rec>();
	}

	common ins_common = new common(NGM);
	switch (task) {
	case 1: {

		String from = (String) request.getAttribute("from");
		String to = (String) request.getAttribute("to");
		String block = (String) request.getAttribute("block");
		String gh_id = (String) request.getAttribute("gh_id");
		d_ghdetails ins_gh = new d_ghdetails(NGM);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = block;
		ins_gh.selectDB();

		from = ins_common.display_date_calander(from);
		to = ins_common.display_date_calander(to);
%>
<div class="col-sm-12">
	<div class="panel panel-green">

		<div class="panel-heading">
			<div class="panel-title">
				<i class="fa fa-building-o"></i>
				<%=ins_gh.block_name%>,<%=ins_gh.gh_name%>
			</div>
		</div>

		<div class="panel-body">
			<h4 style="border-bottom: 1px solid #ddd;">
				ALLOTMENT & VACANCY REPORT FROM
				<%=ins_common.display_date_yr(from)%>
				TO
				<%=ins_common.display_date_yr(to)%></h4>

			<!-- <form name="form-general" method="post" id="form-general">	 -->
			<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg"
				value='<%=gh_id%>'> <input type="hidden" name="sel_block"
				id="sel_block" min="1" class="lg" value='<%=block%>'>
			<div class="min-height">
				<table class="table-bordered table">
					<thead>
						<tr>
							<%
								List<String> between = ins_common.getdateString(from, to);
									int i = 0;
							%>
							<th>Room No</th>
							<%
								while (i < between.size()) {
							%>
							<th><%=between.get(i)%></th>
							<%
								i++;
									}
							%>
						</tr>
					</thead>
					<tr>

						<%
							String t_from = "", t_name = "", t_block_id = "", t_block_name = "", room_no = "";

								/*  */

								ArrayList<allot_row_class> list_allot_row_class = new ArrayList<allot_row_class>();
								String temp_roomno = "";
								String temp_guest_str = "";
								String temp_date = "";

								PreparedStatement psblock = NGM.tr_conn
										.prepareStatement("select * from room_block where gh_id=? and block_id=?");
								psblock.setString(1, gh_id);
								psblock.setString(2, block);
								System.out.println("ps... allot report1 : " + psblock);
								ResultSet rsblock = psblock.executeQuery();
								while (rsblock.next()) {
									t_block_id = rsblock.getString("block_id");
									t_block_name = rsblock.getString("block_name");
									/*  */
									PreparedStatement ps = NGM.conn
											.prepareStatement("select room,from_date,name,designation from ((select req_id,from_date,room_no from tr_allotment where status!='CAN' and gh_id=? and block_id=? and date(from_date) between date (?) and date(?) order by room_no,from_date ASC)a join (select name,designation,guest_id,req_id from guest_details)b on a.req_id=b.req_id)c right join(select room_name,room_no as room from room_details where block_id=? order by room_no ASC)d on c.room_no=d.room");
									//Ajeesh    select room_no,from_date,name,designation from (select req_id,from_date,room_no from tr_allotment where status!='CAN' and gh_id=? and block_id=? and date(from_date) between date (?) and date(?) order by room_no,from_date)a join (select name,designation,guest_id,req_id from guest_details)b on a.req_id=b.req_id order by room_no,from_date");
									//"select req_id,from_date,room_no from tr_allotment where status!='CAN' and gh_id=? and block_id=? and date(from_date) between date(?) and date(?) order by room_no,from_date");
									ps.setString(1, gh_id);
									ps.setString(2, t_block_id);
									ps.setString(3, from);
									ps.setString(4, to);
									ps.setString(5, t_block_id);
									System.out.println("ps... allot report : " + ps);
									String prev_room = "";
									ResultSet rs = ps.executeQuery();
									while (rs.next()) {
										room_no = rs.getString("room");
										t_name = rs.getString("name")
												+ " " + (rs.getString("designation") != null ? " "
														+ rs.getString("designation") : " ");
										t_from = rs.getString("from_date");
										if (!temp_roomno.equals(room_no)) {
											temp_roomno = room_no;
											temp_date = t_from;
											allot_row_class temp_ins_allot_row_class = new allot_row_class();

											date_rec ins_temp_date_rec = new date_rec();
											
											ins_temp_date_rec.oc_date = temp_date;

											ins_temp_date_rec.guestlist.add(t_name);

											temp_ins_allot_row_class.roomno = room_no;
											System.out.println(temp_ins_allot_row_class.roomno
													+ "output2");

											temp_ins_allot_row_class.listofdate.add(ins_temp_date_rec);
											
											list_allot_row_class.add(temp_ins_allot_row_class);
										} else {
											if (!temp_date.equals(t_from)) {
												temp_date = t_from;
												date_rec ins_temp_date_rec = new date_rec();
												ins_temp_date_rec.oc_date = temp_date;
												ins_temp_date_rec.guestlist.add(t_name);

												allot_row_class temp_ins_allot_row_class = (allot_row_class) list_allot_row_class
														.get(list_allot_row_class.size() - 1);
												temp_ins_allot_row_class.listofdate
														.add(ins_temp_date_rec);

											} else {
												temp_date = t_from;
												allot_row_class temp_ins_allot_row_class = (allot_row_class) list_allot_row_class
														.get(list_allot_row_class.size() - 1);
												date_rec ins_temp_date_rec = (date_rec) temp_ins_allot_row_class.listofdate
														.get(temp_ins_allot_row_class.listofdate
																.size() - 1);
												ins_temp_date_rec.guestlist.add(t_name);
											}

										}
									} //end while
										//close
									rs.close();
									ps.close();

								}
								//close
								rsblock.close();
								psblock.close();

								System.out.println(list_allot_row_class.size() + "Size=====");

								for (int ii = 0; ii < list_allot_row_class.size(); ++ii) {
									System.out.println("inside");
						%>
					<tr>
						<%
							allot_row_class temparrobj = (allot_row_class) list_allot_row_class.get(ii);
							String roomno = temparrobj.roomno;
							ArrayList temparrlist = temparrobj.listofdate;
						%>
						
						<td><%=roomno%></td>

						<%
							int di = 0;
							while (di < between.size()) {
						%>
						<td>
							<%
								String guest_name="";
								String tempseqdate = between.get(di);
								System.out.println("test"+tempseqdate);
								for (int j = 0; j < temparrlist.size(); ++j) {
									date_rec ins_date_rec = (date_rec) temparrlist.get(j);
									String tempocdate = ins_common.display_date(ins_date_rec.oc_date);
									System.out.println("roomNo" + roomno);
									System.out.println("tempocdate" + tempocdate);
									if (tempseqdate.equals(tempocdate)) {
										for (int k = 0; k < ins_date_rec.guestlist
												.size(); ++k) {
											t_name = (String) ins_date_rec.guestlist
													.get(k);
											System.out
													.println("%%%%%%%%%%%%5" + t_name);
											if(t_name != null || !t_name.isEmpty()){
												if(!guest_name.isEmpty()){
													guest_name = guest_name + ", " +t_name;
												}else{
													guest_name = t_name;
												}
											} 
									%> 
									<p></p> 
									<%}
		 					}//end if
		 				} //for loop
		 				
		 				if(!guest_name.isEmpty()){%>
		 					<%=guest_name%>
		 				<%}else {
	 						String s = "Available";
	 						System.out.println("%%%%%%%%%%%%6" + s);
	 					%> <%=s%> <%
					 	} 
		 				 %>
						</td>
						<%
							di++;
									} //while loop
						%>
					</tr>
					<%
						} // outer loop
					%>

				</table>
			</div>
		</div>

		<div class="panel-footer clearfix">
			<input type="button" class="btn btn-primary" value="Cancel"
				onClick="allotmentreport()" /> <input type="button"
				class="btn btn-primary" value="Print PDF"
				onClick="allotmentpdf('<%=gh_id%>','<%=from%>','<%=to%>','<%=block%>')" />
		</div>
	</div>
</div>
<%
	}
		break;
	default:
%>Invalid Task;<%
	break;
	}
%>