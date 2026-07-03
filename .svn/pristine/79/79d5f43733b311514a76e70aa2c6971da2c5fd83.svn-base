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
<style>
<!--
.selectData {
	height: 50px;
	overflow: auto;
}
-->
</style>

<%
	String drawbox = "";
	String tt_name = "";
	Integer amount = 0;
	m_allotment ins_m_allotment = new m_allotment(NGM);
	common ins_common = new common(NGM);
	switch (task) {
	case 1: {
		String dorm_type = NGM.ins_ngmprop
				.getpropvalue("dorm_accomodation");
		String page_no = (String) request.getAttribute("page_no");
		ArrayList<c_blockchart> block_list = new ArrayList<c_blockchart>();
		block_list = (ArrayList<c_blockchart>) request
				.getAttribute("blocklist");

		String req_id = (String) request.getAttribute("req_id");
		if(req_id != null){
			if(!req_id.isEmpty()){
				if(req_id.substring(0,1).equalsIgnoreCase("H")){
					amount = 100;
				}
			}
		}
		String reswlstatus = "";
		String tablename = (String) request.getAttribute("tablename");
		String type = (String) request.getAttribute("type");
		String res_id = (String) request.getAttribute("res_id");
		String from = (String) request.getAttribute("from");
		String to = (String) request.getAttribute("to");
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");

		int i = 0;
		PreparedStatement ps1 = NGM.tr_conn
				.prepareStatement("select * from guest_details where req_id=?");
		ps1.setString(1, req_id);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			if (tt_name == "") {
				tt_name = rs1.getString("name");
			} else {
				tt_name = tt_name + " , " + rs1.getString("name");
			}
		}
		ps1.close();
		rs1.close();
%>


<br>
<div class="main-tab col-sm-7 no-padding">
	<ul class="nav nav-tabs">
		<%
			while (i < block_list.size()) {
					c_blockchart tempblock = (c_blockchart) block_list.get(i);
					System.out.println("outtttt=" + tempblock.block_id);
					String temp_blockid = tempblock.block_id;
					String temp_block_title = tempblock.block_title;
					System.out.println("hi i" + i);
		%>
		<li><a href="#<%="BLOCK_" + temp_blockid%>" data-toggle="tab"
			data-target="#<%="BLOCK_" + temp_blockid%>"><i class="fa fa-bank"></i>
				<%=temp_block_title%> </a></li>

		<%
			i++;
				} //end while
		%>

	</ul>
	<div class="tab-content table-responsive">

		<%
			int k = 0;
				while (k < block_list.size()) {

					c_blockchart tempblock = (c_blockchart) block_list.get(k);
					String temp_blockid = tempblock.block_id;
					String temp_block_title = tempblock.block_title;
					c_table_chart room_tablechart = tempblock.room_tablechart;
					c_table_chart dorm_tablechart = tempblock.dorm_tablechart;
		%>

		<%
			if (k == 0) {
		%>
		<div class="tab-pane" id="<%="BLOCK_" + temp_blockid%>">
			<%
				} else {
			%>
			<div class="tab-pane" id="<%="BLOCK_" + temp_blockid%>">
				<%
					}
				%>

				<div class="inner-tab">
					<ul class="nav nav-tabs">

						<%
							if (k == 0) {
						%>
						<li class="active"><a href="#<%="room_" + temp_blockid%>"
							data-target="#<%="room_" + temp_blockid%>" data-toggle="tab">Rooms</a></li>
						<%
							} else {
						%>
						<li><a href="#<%="room_" + temp_blockid%>"
							data-target="#<%="room_" + temp_blockid%>" data-toggle="tab">Rooms</a></li>
						<%
							}
						%>
						<li><a href="#<%="dormitory_" + temp_blockid%>"
							data-target="#<%="dormitory_" + temp_blockid%>" data-toggle="tab">Dormitory</a>
						</li>
					</ul>
					<div class="tab-content">


						<div class="tab-pane fade active in"
							id="<%="room_" + temp_blockid%>">
							<table
								class="table-bordered table-hover table-allotment header-fixed">
								<thead class="theading">
									<tr>
										<th>Room No</th>
										<%
											ArrayList<String> date_list = room_tablechart.date_list;//dorm_tablechart
													int kk = 0;
													while (kk < date_list.size()) {
										%>
										<th><%=date_list.get(kk)%><span id="day"></span></th>
										<%
											kk++;
													}
										%>
									</tr>

								</thead>

								<tbody class="tbody">
									<%
										int kkk = 0;
												ArrayList<String> room_list = room_tablechart.room_list;
												ArrayList<c_row_chart> row_chart_list = room_tablechart.row_chart_list;
												while (kkk < room_list.size()) {
									%>

									<tr>
										<td class="roomno"><%=room_list.get(kkk)%></td>

										<%
											c_row_chart temp_row_chart = row_chart_list.get(kkk);
														ArrayList<c_column_chart> col_list = temp_row_chart.col_list;
														System.out.println(" inside col list room chart list"
																+ col_list.size());
														int kkkkk = 0;
														while (kkkkk < col_list.size()) {
															System.out.println("boxxxxxx=" + temp_blockid);
															c_column_chart tempcolchart = col_list.get(kkkkk);
															drawbox = ins_m_allotment.draw_room_box(
																	tempcolchart, res_id, tablename, type,
																	gh_id, temp_blockid);
										%>

										<%=drawbox%>
										<%
											kkkkk = kkkkk + 1;
														}
										%>
									</tr>


									<%
										kkk++;
												} //end of roomlist
									%>
								</tbody>

							</table>

						</div>


						<div class="tab-pane" id="<%="dormitory_" + temp_blockid%>">

							<table
								class="table-bordered table-hover table-allotment header-fixed">
								<thead class="theading">
									<tr>
										<th>Room No</th>
										<%
											ArrayList<String> date_listdd = dorm_tablechart.date_list;//dorm_tablechart
													int kkdd = 0;
													while (kkdd < date_listdd.size()) {
										%>

										<th><%=date_listdd.get(kkdd)%><span id="day"></span></th>
										<%
											kkdd++;
													}
										%>

									</tr>

								</thead>

								<tbody class="tbody">
									<%
										int kkkdd = 0;
												ArrayList<String> room_listdd = dorm_tablechart.room_list;
												ArrayList<c_row_chart> row_chart_listdd = dorm_tablechart.row_chart_list;

												while (kkkdd < room_listdd.size()) {
													System.out.println("Inside whitDorrmetory");
									%>

									<tr>
										<td class="roomno"><%=room_listdd.get(kkkdd)%></td>

										<%
											c_row_chart temp_row_chart = row_chart_listdd
																.get(kkkdd);
														ArrayList<c_column_chart> col_list = temp_row_chart.col_list;
														System.out.println(" inside col list room chart list"
																+ col_list.size());
														int kkkkkdd = 0;
														while (kkkkkdd < col_list.size()) {
															c_column_chart tempcolchart = col_list.get(kkkkkdd);
															drawbox = ins_m_allotment.draw_room_box(
																	tempcolchart, res_id, tablename, type,
																	gh_id, temp_blockid);
										%>

										<%=drawbox%>
										<%
											//System.out.println("GOGGOOOOOO "+drawbox);

															kkkkkdd = kkkkkdd + 1;
														}
										%>
									</tr>


									<%
										kkkdd++;
												} //end of roomlist
									%>
								</tbody>

							</table>
						</div>



					</div>
				</div>


			</div>

			<%
				k++;
					} //end of blocklist
			%>

		</div>

	</div>
	<%
		System.out
					.println("dorm_typedorm_typedorm_typedorm_typedorm_typedorm_typedorm_typedorm_typedorm_typedorm_type "
							+ dorm_type);
	%>
	<input type="hidden" id="dorm_type_prop" name="dorm_type_prop"
		value="<%=dorm_type%>">
</div>

<div id="sticky-anchor"></div>
<div id="final_data" class="col-sm-5 no-padding">

	<div class="box"
		style="border: 1px solid #eeeeee; box-shadow: 0 2px 10px #dddddd;">

		<div class="box-header">
			<div class="box-title text-red">
				Allotment for :<%=ins_common.getRESTYPE(type)%>(<%=type%>)
			</div>
		</div>

		<div class="box-body">

			<form id="allotment-data" name="allotment-data">

				<div id="headfinal"></div>

				<table class="table allot-input table-bordered" ">
					<thead>
						<tr>
							<th>Date</th>
							<th>Type</th>
							<th>BlockName-Room-BedNo</th>
							<th>Rent</th>
						</tr>
					</thead>
					<tr>
						<%
							c_blockchart tempblock15 = (c_blockchart) block_list.get(0);
								c_table_chart room_tablechartdata5 = tempblock15.room_tablechart;
								ArrayList<String> date_listdata5 = room_tablechartdata5.date_list;
								int z5 = 0;
								//System.out.println("while            eeeee "+date_listdata5.size());
								String tempdate5 = "";
								while (z5 < date_listdata5.size())//
								{
									tempdate5 = date_listdata5.get(z5);
						%>

						<td><input type="text" id="dateO<%=tempdate5%>"
							name="dateO<%=tempdate5%>" value="<%=tempdate5%>" size="7"
							disabled></td>
						<td><input type="text" id="selected_typeO<%=tempdate5%>"
							name="selected_typeO<%=tempdate5%>" value="" size="1" disabled style="width: 47px;"></td>
						<td><input type="text" id="block_idO<%=tempdate5%>"
							name="block_idO<%=tempdate5%>" value="" size="3" disabled>
							- <input type="text" id="roomnoO<%=tempdate5%>"
							name="roomnoO<%=tempdate5%>" value="" size="1" disabled style="width: 47px;">
							- <input type="text" id="bednoO<%=tempdate5%>"
							name="bednoO<%=tempdate5%>" value="" size="1" disabled></td>
						<td><select name="rent<%=tempdate5%>" id="rent<%=tempdate5%>"
							class="col-sm-10 selectData">
								<%
									ResultSet rss = null;
											PreparedStatement pss = null;
											pss = NGM.tr_conn
													.prepareStatement("select * from mst_room_rent order by rent_amount");
											rss = pss.executeQuery();
											int total_row = 0;
											System.out.println("count %%%%%%%%%%        " + pss);
											String option = "";
											while (rss.next()) {
												if(amount != 0){
													option = option.concat("<option value=\""
															+ (Integer.parseInt(rss.getString("rent_amount")) + amount) + "\" >"
															+ (Integer.parseInt(rss.getString("rent_amount")) + amount) + "</option>");
													
												}else{
													option = option.concat("<option value=\""
															+ rss.getString("rent_amount") + "\" >"
															+ rss.getString("rent_amount") + "</option>");
												}
											}
											//close
											rss.close();
											pss.close();
								%>
								<%=option%>
						</select> <%-- <input type="text" name="rent<%=tempdate5%>" id="rent<%=tempdate5%>" size="10" onkeypress="return restrictInput(this, event, digitsOnly);"> --%>
						</td>
						<input type="hidden" id="tableidO<%=tempdate5%>"
							name="tableidO<%=tempdate5%>" value="">
						<input type="hidden" id="tdidO<%=tempdate5%>"
							name="tdidO<%=tempdate5%>" value="">
						<input type="hidden" id="source_typeO<%=tempdate5%>"
							name="source_typeO<%=tempdate5%>" value="">
						<input type="hidden" id="res_typeO<%=tempdate5%>"
							name="res_typeO<%=tempdate5%>" value="">
						<input type="hidden" id="gh_idO<%=tempdate5%>"
							name="gh_idO<%=tempdate5%>" value="">
						<input type="hidden" id="idd<%=z5%>" name="idd<%=z5%>"
							value="<%=tempdate5%>">

						<%--     	 			<input type="text" size="100" disabled="disabled" id="datarow<%=tempdate5%>" name="datarow<%=date_listdata5.get(z5)%>" value="">
 --%>
						<div id=<%=tempdate5%>></div>
					</tr>

					<%
						z5++;
							}
					%>

				</table>
				<input type="hidden" id="tablename" name="tablename"
					value='<%=tablename%>'> <input type="hidden" id="count"
					name="count" value='<%=date_listdata5.size()%>'> <input
					type="hidden" id="res_id" name="res_id" value='<%=res_id%>'>
				<input type="hidden" id="g_name" name="g_name" value='<%=tt_name%>'>

			</form>

		</div>

		<div class="box-footer">
			<button class="btn btn-success" id="btn-sub-alot" type="button"
				onclick="save_allotment_data('<%=gh_id%>','<%=req_id%>','<%=from%>','<%=to%>','<%=page_no%>')">
				Confirm and Proceed</button>
		</div>


	</div>
	<br>
	<div class="panel panel-default" style="margin: 10px">
		<div class="panel-heading">
			<div class="panel-title">Colour Indication</div>
		</div>
		<div class="panel-body">
			<ul class="list-unstyled indication">
				<li><span class="notselected"> </span> <strong> Available Room/Bed</strong></li>
				<li><span id="aa" class="selectedbox alreadyselected"></span> <strong>
						Already Alloted Room/ Bed</strong></li>
				<li><span class="selectedbox"></span> <strong>
						Currently Allocated Room/ Bed </strong></li>

			</ul>
		</div>
	</div>

</div>

<div id="finahiddenl_data" class="col-sm-12 center">
	<%
		int i1 = 0;
			c_blockchart tempblock1 = (c_blockchart) block_list.get(i1);
			c_table_chart room_tablechartdata = tempblock1.room_tablechart;
			ArrayList<String> date_listdata = room_tablechartdata.date_list;
			int z = 0;
			//System.out.println("while            eeeee "+date_listdata.size());

			while (z < date_listdata.size())//
			{
				//System.out.println("while            eeeee ");

				String tempdate = "", temptableid = "", tempdivid = "", tempsource = "", tempselected = "", temproom = "", tempbedno = "", tempmaxbed = "", temprestype = "", tempghid = "", tempblock = "";
				boolean tempfound = false;
				ArrayList<c_selected_details> selectedlistdet = (ArrayList<c_selected_details>) ins_m_allotment.selectedlistdet;
				//System.out.println("ArrayList<c_selected_details>  "+selectedlistdet.size());
				tempdate = date_listdata.get(z);
				for (int m = 0; m < selectedlistdet.size(); m++) {
					//System.out.println("for            rrrrr ");

					c_selected_details temp_cselobj = (c_selected_details) selectedlistdet
							.get(m);
					if (temp_cselobj.edate.equals(tempdate)) {
						tempfound = true;
						temptableid = temp_cselobj.etableid;
						tempdivid = temp_cselobj.etdid;
						tempsource = temp_cselobj.esource_status;
						tempselected = temp_cselobj.eroom_type;
						temproom = temp_cselobj.eroomno;
						tempbedno = temp_cselobj.ebedno;
						tempmaxbed = "" + temp_cselobj.emax_no_bed;
						temprestype = temp_cselobj.eres_type;
						tempghid = temp_cselobj.egh_id;
						tempblock = temp_cselobj.eblock_id;
						System.out.println("if            ffffff ");
						break;
					}

				}
	%>

	<input type="hidden" id="tableidhidden<%=tempdate%>"
		name="tableidhidden<%=tempdate%>" value=<%=temptableid%>> <input
		type="hidden" id="tdidhidden<%=tempdate%>"
		name="tdidhidden<%=tempdate%>" value=<%=tempdivid%>> <input
		type="hidden" id="source_typehidden<%=tempdate%>"
		name="source_typehidden<%=tempdate%>" value=<%=tempsource%>> <input
		type="hidden" id="selected_typehidden<%=tempdate%>"
		name="selected_typehidden<%=tempdate%>" value=<%=tempselected%>>
	<input type="hidden" id="roomnohidden<%=tempdate%>"
		name="roomnohidden<%=tempdate%>" value=<%=temproom%>> <input
		type="hidden" id="datehidden<%=tempdate%>"
		name="datehidden<%=tempdate%>" value=<%=tempdate%>> <input
		type="hidden" id="bednohidden<%=tempdate%>"
		name="bednohidden<%=tempdate%>" value=<%=tempbedno%>> <input
		type="hidden" id="numberofbedsnohidden<%=tempdate%>"
		name="numberofbedsnohidden<%=tempdate%>" value=<%=tempmaxbed%>>
	<input type="hidden" id="res_typehidden<%=tempdate%>"
		name="res_typehidden<%=tempdate%>" value=<%=temprestype%>> <input
		type="hidden" id="gh_idhidden<%=tempdate%>"
		name="gh_idhidden<%=tempdate%>" value=<%=tempghid%>> <input
		type="hidden" id="block_idhidden<%=tempdate%>"
		name="block_idhidden<%=tempdate%>" value=<%=tempblock%>>

	<%
		z++;
			} //}
	%>

</div>
<%
	} //end case 
		break;

	default:
%>Invalid Task;<%
	break;
	}
%>