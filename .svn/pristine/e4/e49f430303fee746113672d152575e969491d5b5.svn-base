<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%
	common con = new common(NGM);
	switch (task) {
	case 0:
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvv " + gh_id);
%>

<div id="cancel_bill">


	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg"
		value='<%=gh_id%>'> <input type="hidden" name="sel_block"
		id="sel_block" min="1" class="lg" value='<%=sel_block%>'>
	<div class="col-sm-10 center needCalendar">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title">
					<i class="fa fa-search"></i> Search
				</div>
			</div>

			<div class="panel-body no-padding">

				<div class="f">
					<label>By Request Id</label> <input type="text" name="req_id"
						id="req_id" size="15" autofocus="autofocus">
				</div>

				<div class="f">
					<label>By Receipt No</label> <input type="text" name="rec_no"
						id="rec_no" size="20">
				</div>


				<div class="f">
					<label>By Name</label> <input type="text" name="name" id="name"
						size="30" autocomplete="off">
				</div>

				<div class="f">
					<label>By Date</label> <input type="text" name="date" id="date"
						class="calendarOnly" autocomplete="off">


				</div>

				<div class="f marg">
					<button class="btn btn-success btn-flat" id="searchbt"
						type="button" onclick="search_show_receipt('1')">
						<i class="fa fa-search"></i> Search
					</button>
					<button class="btn btn-success btn-flat" id="searchbt"
						type="button" onclick="listreceipt('1')">
						<i class="fa fa-search"></i> List All
					</button>
				</div>
			</div>
		</div>
	</div>

	<%
		ResultSet rss = null;
			PreparedStatement pss = null;
			int offset1 = 0;
			int n_row = Integer.parseInt(NGM.ins_ngmprop
					.getpropvalue("no_rows"));
			String page_no_s = (String) request.getAttribute("page_no");
			gh_id = (String) request.getAttribute("gh_id");
			sel_block = (String) request.getAttribute("sel_block");
			System.out.println("------------ sel_block ----------------- "
					+ sel_block);
			int page_no = Integer.parseInt(page_no_s);
			if (page_no != 1) {
				offset1 = n_row * (page_no - 1);
			}

			pss = NGM.tr_conn
					.prepareStatement("select count(*) from (select *  from ((((select *  from tr_receipt where status='CNF')a join (select rec_id as b_recid,bill_id from tr_receiptvsbill)b on a.rec_id=b.b_recid)c join (select bill_id,req_id as bg_reqid,guest_id as bg_guestid from tr_billvsguest)d on c.bill_id=d.bill_id)e join (select * from guest_details)f on e.bg_reqid=f.req_id and e.bg_guestid=f.guest_id)g join (select req_id as c_reqid,guest_id asc_guestid,arrival_time_date,departure_time_date from tr_checkin where status!='CAN' and block_id= ?)h on g.guest_id=h.asc_guestid and g.req_id=h.c_reqid)aa");
			pss.setString(1, sel_block);
			rss = pss.executeQuery();
			int total_row = 0;
			while (rss.next()) {
				total_row = rss.getInt("count");
			}
			//close
			rss.close();
			pss.close();
			System.out.println("total_row " + total_row);
	%>

	<div id="ReceiptShowList">
		<div class="panel panel-success">

			<div class="panel-heading">
				<div class="panel-title">
					<i class="fa fa-newspaper-o"></i> List of Receipts
				</div>
			</div>

			<div class="panel-body">


				<table id="sum_table" class="table table-bordered">
					<thead>
						<tr class="titlerow">
							<th>Receipt Id</th>
							<th>User Id</th>
							<th>Date of Receipt</th>

							<th>Bill Id</th>
							<th>Request Id/Guest Id</th>
							<th>Guest Details</th>
							<th>Stayed From</th>
							<th>Stayed To</th>
							<th>Mode</th>
							<th>Amount</th>
							<th></th>
						</tr>
					</thead>
					<%
						String t_recid = "";
							PreparedStatement ps = NGM.conn
									.prepareStatement("select *  from ((((select *  from tr_receipt where status='CNF')a join (select rec_id as b_recid,bill_id from tr_receiptvsbill)b on a.rec_id=b.b_recid)c join (select bill_id,req_id as bg_reqid,guest_id as bg_guestid from tr_billvsguest)d on c.bill_id=d.bill_id)e join (select * from guest_details)f on e.bg_reqid=f.req_id and e.bg_guestid=f.guest_id)g join (select req_id as c_reqid,guest_id asc_guestid,arrival_time_date,departure_time_date from tr_checkin where status!='CAN' and block_id=?)h on g.guest_id=h.asc_guestid and g.req_id=h.c_reqid order by rec_id desc limit ? offset ?");
							ps.setString(1, sel_block);
							ps.setInt(2, n_row);
							ps.setInt(3, offset1);
							System.out.println("total_row ps " + ps);
							ResultSet rs = ps.executeQuery();

							while (rs.next()) {
					%><tr>

						<%
							if (!rs.getString("rec_id").equals(t_recid)) {
						%>
						<td><%=rs.getString("rec_id")%></td>
						<td><%=rs.getString("user_id")%></td>
						<td><%=con.display_timestamp(rs
								.getString("date_rece"))%></td>
						<%
							} else {
						%>
						<td></td>
						<td></td>
						<td></td>

						<%
							}
						%>
						<td><%=rs.getString("bill_id")%></td>
						<td><%=rs.getString("req_id")%>/<%=rs.getString("guest_id")%></td>
						<td><%=rs.getString("name")%><br><%=rs.getString("designation") != null ? ","
							+ rs.getString("designation") : ""%><%=rs.getString("department") != null ? ","
							+ rs.getString("department") : ""%></td>

						<td><%=con.display_timestamp(rs
							.getString("arrival_time_date"))%></td>
						<td><%=con.display_timestamp(rs
							.getString("departure_time_date"))%></td>

						<td><%=rs.getString("mode")%></td>
						<td><%=rs.getString("amount")%></td>
						<%
							if (!rs.getString("rec_id").equals(t_recid)) {
						%>
						<td><input type="button" class="btn btn-primary btn-xs"
							value="SHOW RECEIPT"
							onClick="show_receiptforpdf('<%=gh_id%>','<%=rs.getString("rec_id")%>')" /></td>
						<%
							}
						%>
					</tr>
					<%
						t_recid = rs.getString("rec_id");
							}
							//close
							rs.close();
							ps.close();
					%>

				</table>



			</div>


			<div class="panel-footer clearfix">

				<div class="pull-right" id="datapage">
					<%=NGM.ins_doc.pagination_form(NGM.ins_sess.process_id,
						gh_id, total_row, n_row, "listreceipt", page_no)%>
				</div>

			</div>



		</div>

	</div>

</div>
<%
	break;

	default:
%>Invalid Task;<%
	break;
	}
%>
