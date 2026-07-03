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
common ins_common = new common(NGM);
	
	switch (task) {
	
	case 0:
		common con = new common(NGM);
		String t_roomno="",block_id = "";
		String bill_id = (String) request.getAttribute("bill_id");
		String gh_id = (String) request.getAttribute("gh_id");
System.out.print("hggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhggggggggggggggggggghhhhhhhhhhhhhhh"+bill_id);
		%>
		
	
		<div id="bill" name="bill" class="form-group" >	
		
		<form name="form-gen_bill" method="post" id="form-gen_bill">	
		<%----------------------------------------------------------------- --%>
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="container" style="width:800px;">
			<%
		d_ghdetails gh = new d_ghdetails(NGM);
		%>
		<%		
	String chk_id="",roll_no="",arrival_time_date="",departure_time_date="",no_of_children_below12="",adults="";
	
	int adv=0;
	d_checkin_allot ins_chkalot = new d_checkin_allot(NGM);
	d_allotment ins_allot = new d_allotment(NGM);
	d_guestdetails ins_guest = new d_guestdetails(NGM);
	PreparedStatement ps = NGM.conn.prepareStatement("select * from (select * from tr_billvsguest where bill_id=?) a join (select tr_id,roll_no,arrival_time_date,departure_time_date,no_of_children_below12,advance_amt,adv_bill_id,guest_id,status,adults,vc_id,purpose from tr_checkin where status!='CAN') b on a.guest_id=b.guest_id");
	ps.setString(1, bill_id);
	System.out.print("...................psdet bill_id "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
		
		no_of_children_below12 = rs.getString("no_of_children_below12");
		adults = rs.getString("adults");
		arrival_time_date =con.display_timestamp(rs.getString("arrival_time_date"));
		departure_time_date =con.display_timestamp(rs.getString("departure_time_date"));
		
		ins_chkalot.tr_id = rs.getString("tr_id");
		ins_chkalot.selectDB();
		
		ins_allot.allot_id = ins_chkalot.allot_id;
		ins_allot.select_DB();
		
		ins_guest.req_id = rs.getString("req_id");
		ins_guest.guest_id = rs.getString("guest_id");
		ins_guest.selectDB();
		
		
		/* d_mod_req ins_modreq=new d_mod_req(NGM);
		ins_modreq.req_id = rs.getString("req_id");
		String purpose_vc= ins_modreq.selectDB_VC_purpose();
		String[] pur_vc = purpose_vc.split("-"); */
		
		String vc_id = rs.getString("vc_id");
		String purpose = rs.getString("purpose");

		d_visitorcategory ins_vc = new d_visitorcategory(NGM);
		ins_vc.vc_id = vc_id;
		ins_vc.selectDB();
		
		
		String trid = rs.getString("tr_id");
		PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");// and status='LIV'
		psroom.setString(1, trid);
		ResultSet rsroom = psroom.executeQuery();
		while(rsroom.next()){
			t_roomno=rsroom.getString("block_name_short")+" - "+rsroom.getString("room_no");
			String t_bedno=rsroom.getString("bed_no");
			block_id = rsroom.getString("block_id");
			if(!rsroom.getString("type").equals("R")){
				t_roomno = t_roomno+"-"+t_bedno;
			}
		}
		gh.gh_id = gh_id;
		gh.block_id = block_id;
		gh.selectDB();
		 //close
	    rsroom.close();
		psroom.close();
		%>
		<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title"><%=gh.block_name%>,<%=gh.gh_name %></div>
			<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            </div>
		</div>
		
		<div class="panel-body">
		
		
			<h4 align="center"><u>BILL</u></h4>
	
			
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total);
			%>
			<div class="clearfix">
			<span class="pull-left"><strong>Bill No: </strong><%=bill_id %>(R)</span>
			
			<span class="pull-right"><strong>Date: </strong><%=dateFormat.format(cal.getTime())%></span>
			</div>
			<table class="table-bordered table">
			<thead>
			<tr>
		<th>Roll No</th>	
	    <th>Guest Details</th>	
		<th>Room No</th>	
		<th>Category</th>	
		<th>Purpose</th>	
		<th>No of Adults</th>	
		<th>No of children below 12</th>	
		<th>Stay From</th>	
		<th>Stay To</th>	
		</tr>
		</thead>
	
	
	
			<tr>	<td><%=rs.getString("roll_no")%></td>	
		<td><%=ins_guest.name%><%=ins_guest.designation!=null?","+ins_guest.designation:""%><%=ins_guest.department!=null?","+ins_guest.department:""%></td>		
		
		<td><%=t_roomno%></td>	
		<td><%=ins_vc.desg_category+"("+ins_vc.category+","+ins_vc.sub_cat+")" %></td>	
		<td><%=purpose %></td>		
		<td><%=adults%></td>	
		<td><%=no_of_children_below12%></td>		
		<td><%=arrival_time_date%></td>		
		<td><%=departure_time_date%></td>	

<% 
int adv_amt = rs.getInt("advance_amt");
//	if(adv_amt==null){
	//adv_amt = 0;
	//}
	adv = adv + adv_amt;
	
	}
	 //close
    rs.close();
	ps.close();
	
	%>	
		
		</table>
		<br>
					
					<table class="table-bordered">
							<tr>
<!-- 						<h6 align="left">Particulars</h6>	<h6 align="right">Rupees</h6>
 -->					<th align="left">Particulars</th>	<th align="right">Rupees</th>
 					</tr>
					<%String total = "",billtotal ="";
					int mcount = 0;
			    PreparedStatement psm = NGM.conn.prepareStatement("select *  from master_charges order by charges_id asc");
			    ResultSet rsm = psm.executeQuery();
					 
					while(rsm.next()){
						mcount++;
						d_billvsguestvscharge ins_billusercharge = new d_billvsguestvscharge(NGM);
						ins_billusercharge.charges_id = rsm.getString("charges_id");
						ins_billusercharge.bill_id = bill_id;
					    total = ins_billusercharge.selectDBamount();
						billtotal = ins_billusercharge.selectDBbill();
						%>
							<tr>	<td><%=rsm.getString("charges_title") %></td>
							
							
							<td><%=total%></td> 		
					</tr>
					<%} %>
					<tr> <td><strong>TOTAL  </strong></td><td><%=billtotal%></td></tr>
					</table>
				<%-- <h6><strong>TOTAL :</strong> <%=billtotal%> </h6> --%>
					
				<%
				
				int pay = Integer.parseInt(billtotal) - adv;
				
				%>	
					
					
		<h6><strong>Deduct Advance Collected: </strong><%=adv%>
		<h6><strong>Balance to be realised / refunded </strong>: <%=pay%>
		<h6>(Rupees  </span><span id="rupees" class="border-bottom" style="width: 79%;"><script>document.getElementById('rupees').innerHTML = inWords(<%= pay%>);</script></span>)</h6>
			<h6>R No. & Date</h6><h6 align="right">Name & Signature of Receptionist With date</h6>
				<!-- 	<input type="button" value="cancel" class="btn btn-primary" onclick="close_nicpopup()">
					<input type="button" value="Print pdf" class="btn btn-primary" onclick="printview_receipt()"> -->
					
					
		
		 	 </div>
		 	 
		 	 
		 	 <div class="panel-footer clearfix">
		 	 	
		 	 	<div class="pull-right">
					<button type="button" class="btn btn-success" onClick="pdf_bill('<%=gh_id%>','<%=bill_id%>','<%=block_id%>')" ><i class="fa fa-file-pdf-o"></i> Print PDF</button>
		<button type="button"  class="btn btn-danger" onclick="close_nicpopup()"><i class="fa fa-times"></i>  Close</button>
		</div>
		
		</div>
				
			</div> 
			
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		
<div id="viewdata"></div>	


<!-- //convert the rupees and words -->
	
	
		
			
	
				<%----------------------------------------------------------------- --%>
				</form>	
			</div>
		
	
			
<%
	break;

	default:
%>Invalid Task;<%
	break;
	}
%>
	