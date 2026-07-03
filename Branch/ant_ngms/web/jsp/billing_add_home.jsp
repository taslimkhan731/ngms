<%@page import="javax.xml.crypto.KeySelector.Purpose"%>
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
	String t_gh_name ="";
	String t_sel_name ="";
	d_ghdetails ins_gh = new d_ghdetails(NGM);
	switch (task) {
	case 0:
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		System.out.println("vvvvvvvvvvvvvvvvvvvvvv "+sel_block);
		ins_gh.gh_id = gh_id;
		ins_gh.block_id = sel_block;
		ins_gh.selectDB();
		t_gh_name = ins_gh.gh_name;
		t_sel_name = ins_gh.block_name;
		System.out.println("t_gh_name"+t_gh_name);
		%>
			
			
	<input type="hidden" name="sel_block" id="sel_block" value="<%=sel_block %>">
	<div id="change_guest">
	
			<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-home"></i> Billing Home</div>
			</div>
			 <div class="panel-body min-height">
			 
			 <h4>Please select Add Guest to proceed</h4>
			 
			 </div>
			 
			 <div class="panel-footer clearfix">
				 <input type="button" class="btn btn-primary" value="Add Guest" onClick="add_guest_billins('<%=gh_id%>')" />
			 </div>
			 </div>
			 
			 
		</div>
	
		
		
		
<%
	break;
	case 1:
		common con = new common(NGM);
		String[] guest_values = (String[]) request.getAttribute("values");
		gh_id = (String) request.getAttribute("gh_id");
		sel_block = (String) request.getAttribute("sel_block");
		String div = (String) request.getAttribute("div");
		d_ghdetails ins_gh1 = new d_ghdetails(NGM);
		ins_gh1.gh_id = gh_id;
		ins_gh1.block_id = sel_block;
		ins_gh1.selectDB();
		t_gh_name = ins_gh1.gh_name;
		t_sel_name = ins_gh1.block_name;
		System.out.println("t_gh_name"+t_gh_name);
		System.out.println("guest_valuesguest_valuesguest_valuesguest_values "+guest_values[0]);
		%>
		<form name="form-gen_bill" method="post" id="form-gen_bill">
		
		<div class="panel panel-info">
		
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-home"></i> Calculate Amount</div>
			</div>
			 <div class="panel-body">
		
		<table >
		<tr class="titlerow well">
		<th >Basic Pay</th>
			
		<td ><input type="text" name="basic_pay" id="basic_pay" onblur="placezero('basic_pay')" onfocus="replacezero('basic_pay')" value="0" size="10" onkeypress="return restrictInput(this, event, digitsOnly);"></td>
		</tr>
		</table>	
		
		<div class="table-responsive">
	<table id="sum_table" class="table table-bordered">
		<tr class="titlerow">
		<th>Request ID/Guest ID</th>	
		<th>Roll No</th>
		<th>Guest Details</th>	
		<th>Arrival Date</th>	
		<th>Departure Date </th>	
		<th>Visitor Category</th>	
		<th>Purpose</th>
		<th>No. of Adults</th>
		<th>No. of Children</th>
		<th>Advance</th>
	
				<%
					int mcount = 0;
					PreparedStatement psm = NGM.conn.prepareStatement("select *  from master_charges order by charges_id asc");
					ResultSet rsm = psm.executeQuery();
					 
					while(rsm.next()){
						mcount++;
						%>
								<th><%=rsm.getString("charges_title") %></th>					
					<%}
					
					 //close
				    rsm.close();
					psm.close();
				%>
		<%
		for(int i=0;i<guest_values.length;i++){
			System.out.println("ddddddd "+guest_values[i]);
		%>
		
	<tr>

	

	
	

<%
d_guestdetails ins_guest = new d_guestdetails(NGM);
ins_guest.guest_id = guest_values[i];
ins_guest.selectDB();

d_checkin ins_chk= new d_checkin(NGM);
ins_chk.guest_id = guest_values[i];
ins_chk.status = "COUT";
ins_chk.gh_id = gh_id;
ins_chk.req_id = ins_guest.req_id;
ins_chk.selectDB_COUT();

/*  d_mod_req ins_mod = new d_mod_req(NGM);
ins_mod.req_id = ins_guest.req_id;
String vc_purpose = ins_mod.selectDB_VC_purpose();
String[] vc_purp = vc_purpose.split("-");  */

String vc_id = ins_chk.vc_id;
String purpose = ins_chk.purpose; 

d_visitorcategory ins_vc = new d_visitorcategory(NGM);
ins_vc.vc_id = vc_id;
ins_vc.selectDB();
String req = ins_guest.req_id;

%>
<td class="titlerow"><input type="hidden" id="gue_id<%=i+1%>" name="gue_id<%=i+1%>" value='<%=guest_values[i] %>'><%=ins_guest.req_id%>/<%=guest_values[i] %></td>
	<td><%=ins_chk.roll_no %></td>
	<td class="titlerow"><strong><%=ins_guest.name%></strong><br><%=ins_guest.designation!=null?","+ins_guest.designation:""%><%=ins_guest.department!=null?","+ins_guest.department:""%></td>		
	<td><%=con.display_timestamp(ins_chk.arrival_time_date)%></td>
	<td><%=con.display_timestamp(ins_chk.departure_time_date) %></td>
	<td><%=ins_vc.desg_category+"("+ins_vc.category+","+ins_vc.sub_cat+")" %></td>
	<td><%=purpose %></td>
	<td><%=ins_chk.adults %></td>
	<td><%=ins_chk.no_of_children_below12 %></td>
	<td><%=ins_chk.advance_amt %></td>
	
		<%
					int t_chargeid = 1;
					while(t_chargeid<= mcount){ 
					
						/* d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

						ins_gu_other.guest_id = guest_values[i];
						ins_gu_other.req_id = ins_common.getRequestID(ins_gu_other.guest_id);
						ins_gu_other.charges_id = Integer.toString(t_chargeid);
						String sum = ins_gu_other.selectDBsum();
						if(sum==null){
							sum = "0";
						} */
						%>
						
				<%
					int room_rent = 0;
				
					try{
						PreparedStatement psmreq = NGM.conn.prepareStatement("select * from gad_request where req_id=?");
						psmreq.setString(1, req);
						ResultSet rsmreq = psmreq.executeQuery();
						String extReqId = null;
						while(rsmreq.next()){
							if(rsmreq.getString("ext") == null ? false : rsmreq.getString("ext").equalsIgnoreCase("1")){
								extReqId = rsmreq.getString("existing_req_id");
							}
						}
						
						rsmreq.close();
						psmreq.close();
						
						PreparedStatement psrent = NGM.conn.prepareStatement("select sum(CAST(room_rent AS numeric)) from tr_allotment where req_id=? and status!='CAN'");
						psrent.setString(1, extReqId);
						ResultSet rsmrent = psrent.executeQuery();
						 
						while(rsmrent.next()){
							room_rent = rsmrent.getInt(1);
							System.out.println("Rent"+room_rent);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				
					PreparedStatement psm1 = NGM.conn.prepareStatement("select sum(CAST(room_rent AS numeric)) from tr_allotment where req_id=? and status!='CAN'");
					psm1.setString(1, req);
					ResultSet rsm1 = psm1.executeQuery();
					 
					while(rsm1.next()){
						room_rent = room_rent + rsm1.getInt(1);
					%>
													
					<%
					System.out.println("Rent"+room_rent);
					}
					
					 //close
				    rsm1.close();
					psm1.close();
				%>
						
						<td class="rowDataSd">
  

  <div class="input-group">
  <% if(t_chargeid==1){ %>
           <input type="text" id="amount<%=guest_values[i]%><%=t_chargeid%>" name="amount<%=guest_values[i]%><%=t_chargeid%>" size="4"  value="<%=room_rent %>"  onfocus="replacezero('amount<%=guest_values[i]%><%=t_chargeid%>')" onblur="placezero('amount<%=guest_values[i]%><%=t_chargeid%>')" onkeypress="return restrictInput(this, event, digitsOnly);">
 <span class="input-group-btn">
            <button class="btn btn-danger btn-sm" type="button" onClick="showRentBill('<%=vc_id %>','<%=purpose %>','<%=con.display_timestamp(ins_chk.arrival_time_date) %>','<%=con.display_timestamp(ins_chk.departure_time_date) %>','<%=ins_chk.adults %>','<%=ins_chk.no_of_children_below12 %>','<%=ins_chk.guest_id %>')" >Auto</button>
          </span>
  <%}else if(t_chargeid==2){ %>
  <input type="text" id="amount<%=guest_values[i]%><%=t_chargeid%>" name="amount<%=guest_values[i]%><%=t_chargeid%>" size="4"  value="0"  onfocus="replacezero('amount<%=guest_values[i]%><%=t_chargeid%>')" onblur="placezero('amount<%=guest_values[i]%><%=t_chargeid%>')" onkeypress="return restrictInput(this, event, digitsOnly);">
<span class="input-group-btn">
            <button class="btn btn-danger btn-sm" type="button" onClick="showFoodBill('<%=ins_guest.req_id %>','<%=ins_guest.guest_id %>')" >A</button>
          </span>
<%}else{
	
	%>
         <input type="text" id="amount<%=guest_values[i]%><%=t_chargeid%>" name="amount<%=guest_values[i]%><%=t_chargeid%>" size="6"  value="0"  onfocus="replacezero('amount<%=guest_values[i]%><%=t_chargeid%>')" onblur="placezero('amount<%=guest_values[i]%><%=t_chargeid%>')" onkeypress="return restrictInput(this, event, digitsOnly);">
         <%}%>
          
        </div>

  </td>
					<%
						t_chargeid = t_chargeid + 1;	
					}
					%>
	</tr>
	
	<% } %>
	
	<tr class="totalColumn">
	<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	<td>Total</td>
	<%int t_chargeid = 1;
					while(t_chargeid<= mcount){ 
					
						%>
						<%-- <td class="totalCol"><input type="text" size="6"  id="total<%=t_chargeid%>" name="total<%=t_chargeid%>" value=""></td> --%>
						<td class="totalCol"><input type="text" size="6"  id="total<%=t_chargeid%>" name="total<%=t_chargeid%>" value="" disabled="disabled"></td>
						<%
						
						
						t_chargeid = t_chargeid + 1;	
						
					}
					//amount1011+amount1021+
					%>
	</tr>
		</table>
		</div>
		</div>
		
		<div class="panel-footer clearfix">
		
		<a href="#bill-d" class="btn btn-primary" onClick="create_bill('<%=gh_id%>','<%=sel_block%>','<%=mcount%>','<%=guest_values.length%>')" >Create Bill</a>
		<%-- 	<input type="button" class="btn btn-primary" value="Create Bill" onClick="create_bill('<%=gh_id%>','<%=mcount%>','<%=guest_values.length%>')" /> --%>
			<input type="button" class="btn btn-primary" value="Add Guest" onClick="add_guest_billins('<%=gh_id%>')" />
		</div>
		
		</div>
		
		
			
			
			
			<a id="bill-d"></a>
		<div id="bill" name="bill" class="form-group hide" >	
		<%----------------------------------------------------------------- --%>
		<div class="row">
		<div class="col-sm-8 center" style="">
		<div class="panel panel-success">
		<%
			System.out.println("Gh="+t_gh_name + "SEL"+t_sel_name);
		%>
			<div class="panel-heading">
				<div class="panel-title"> <%=t_sel_name%>,<%=t_gh_name %></div>
			</div>
			
			<div class="panel-body">
			
		
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		
		
		
			
			<h4 align="center"><u>BILL</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total);
			%>
			<h6 align="left" class="pull-left"><strong>Bill No:</strong> ..........................(R)</h6><h6 align="right" class="pull-right"><strong>Date:</strong> <%=dateFormat.format(cal.getTime())%></h6>
			<br><br>
			<table class="table-bordered">
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
	
	<%		int adv=0;
	String vc_id="",purpose="";
	d_visitorcategory ins_vc = new d_visitorcategory(NGM);
	for(int i=0;i<guest_values.length;i++){
		System.out.println("ddddddd000 "+guest_values[i]);
		String chk_id="",roll_no="",arrival_time_date="",departure_time_date="",no_of_children_below12="",adults="",reqqq_id="";
		PreparedStatement psdet = NGM.conn.prepareStatement("select tr_id,roll_no,arrival_time_date,departure_time_date,no_of_children_below12,advance_amt,adv_bill_id,adults,req_id,vc_id,purpose from tr_checkin where guest_id=? and status!='CAN'");
		psdet.setString(1, guest_values[i]);
		System.out.print("...................psdet "+psdet);
		ResultSet rsdet = psdet.executeQuery();
		while(rsdet.next()){
			chk_id = rsdet.getString("tr_id");
			reqqq_id = rsdet.getString("req_id");
			roll_no = rsdet.getString("roll_no");
			arrival_time_date =con.display_timestamp(rsdet.getString("arrival_time_date"));
			departure_time_date =con.display_timestamp(rsdet.getString("departure_time_date"));
			no_of_children_below12 = rsdet.getString("no_of_children_below12");
			adults = rsdet.getString("adults");
			if(no_of_children_below12==null){
			no_of_children_below12 = "";
			}
			System.out.println("00000000000000000"+arrival_time_date);
			
			int adv_amt = rsdet.getInt("advance_amt");
		//	if(adv_amt==null){
			//adv_amt = 0;
			//}
			adv = adv + adv_amt;
			
			
			vc_id = rsdet.getString("vc_id");
			purpose = rsdet.getString("purpose");

			
			ins_vc.vc_id = vc_id;
			ins_vc.selectDB();
			
		} 
		 //close
	    rsdet.close();
		psdet.close();
		d_checkin_allot ins_chkalot = new d_checkin_allot(NGM);
		ins_chkalot.tr_id = chk_id;
		ins_chkalot.selectDB();
		
		d_allotment ins_allot = new d_allotment(NGM);
		ins_allot.allot_id = ins_chkalot.allot_id;
		ins_allot.gh_id = gh_id;
		ins_allot.select_DB();
		
		/* d_mod_req ins_modreq=new d_mod_req(NGM);
		ins_modreq.req_id = reqqq_id;
		String purpose_vc= ins_modreq.selectDB_VC_purpose();
		String[] pur_vc = purpose_vc.split("-"); */
		
		
		
		
		String trid = chk_id;
		String t_roomno="";
		PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");// and status='LIV'
		
		psroom.setString(1, trid);
		ResultSet rsroom = psroom.executeQuery();
		while(rsroom.next()){
			t_roomno=rsroom.getString("block_name_short")+" - "+rsroom.getString("room_no");
			String t_bedno=rsroom.getString("bed_no");
			if(!rsroom.getString("type").equals("R")){
				t_roomno = t_roomno+"-"+t_bedno;
			}
		}
		 //close
	    rsroom.close();
		psroom.close();
		%>
		
		
	<tr>
	<td><%=roll_no %></td>
<%
d_guestdetails ins_guest = new d_guestdetails(NGM);
ins_guest.guest_id = guest_values[i];
ins_guest.selectDB();

//int aaaaa=con.no_of_days_twodates(arrival_time_date, departure_time_date);
//System.out.print("ddddaaaaaaaaaaaaaaaaaaaaa "+aaaaa);
%>	
    	<td><strong><%=ins_guest.name%></strong><br><%=ins_guest.designation!=null?","+ins_guest.designation:""%><%=ins_guest.department!=null?","+ins_guest.department:""%></td>		
		
		<td><%=t_roomno%></td>	
		<td><%=ins_vc.desg_category+"("+ins_vc.category+","+ins_vc.sub_cat+")" %></td>	
		<td><%=purpose %></td>		
		<td><%=adults%></td>	
		<td><%=no_of_children_below12%></td>		
		<td><%=arrival_time_date%></td>		
		<td><%=departure_time_date%></td>		

	</tr>
	
	<% } %>
	<tr>
	
	</tr>
		</table>
		<br>
					
					<table id="sumtable1" class="table-bordered table">
						<thead>
						<tr>
							<th>Particulars</th>
							<th>Rupees</th>
						</tr>
						</thead>
					<%int total = 0;
					mcount = 0;
				psm = NGM.conn.prepareStatement("select *  from master_charges order by charges_id asc");
			    rsm = psm.executeQuery();
					 
					while(rsm.next()){
						mcount++;
						%>
						
							<tr>	<td><%=rsm.getString("charges_title") %></td><td class="totalCol1 strong"></td> 
								
					</tr>
					

					<%} %>
						<tr> <td><strong>TOTAL  </strong></td><td> <span id="tot-value"> 0</span></td></tr>
					</table>
			<!-- 	<h6> <strong>TOTAL  </strong>: <span id="tot-value"> 0</span></h6>  -->
				
					
				<%
				
				//int pay = total - adv;
				//int pay = Integer.parseInt(billtotal) - adv;
				%>	
				
				<input type="hidden" id="adv" value="<%=adv%>">	
					
		<h6>Deduct Advance Collected : <%=adv%> 
		<h6>Balance to be realised / refunded :<span id="deduct"></span></h6>
			<h6>Rupees: <span id="towords" class="strong"></span> </h6>
			<h6>R No. & Date</h6><h6 align="right">Name & Signature of Receptionist With date</h6>
				<!-- 	<input type="button" value="cancel" class="btn btn-primary" onclick="close_nicpopup()">
					<input type="button" value="Print pdf" class="btn btn-primary" onclick="printview_receipt()"> -->
					
		 	 
				
			
			
		 			 		
		 	 
		 
		
		 
		  
			
			</div>
			
			<div class="panel-footer clearfix">
				<input type="button" class="btn btn-primary" value="Submit Bill" onClick="submit_bill('<%=gh_id%>','<%=sel_block%>','<%=mcount%>','<%=guest_values.length%>','<%=div %>')" />
				</div>
			
			
			
			</div>
			
				
			</div>
		
<div id="viewdata"></div>	


<!-- //convert the rupees and words -->
	
	
		
			
	
		
				<%----------------------------------------------------------------- --%>
			</div>
			
		</form>	
	
			
<%
	break;


	case 2:
		gh_id = (String) request.getAttribute("gh_id");
		String[] guestvalues = (String[]) request.getAttribute("guest_values");

		System.out.println("createbilljsppppppppppppppppppp "+guestvalues);
		String ghname="";
		PreparedStatement pss = NGM.conn.prepareStatement("select *from gh_details where gh_id=?");
		pss.setString(1,gh_id);
		ResultSet rss=pss.executeQuery();
	  	int j=0;
	  	while(rss.next())
	  	{
	  		j=j+1;
	  		ghname=rss.getString("gh_name");
	  	}
	  	 //close
	    rss.close();
		pss.close();
		%>
		<%-- 
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="container" style="width:800px;">
		<div class="box addCalendar">
		
		<div class="box-body">
			<h4 align="center"><%=ghname%>,ODISHA</h4>
			<h4 align="center"><u>BILL</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total);
			%>
			<h6 align="left">No..........................(R)</h6><h6 align="right">Date:<%=dateFormat.format(cal.getTime())%></h6>
			<table>
		<th>Guest ID</th>	
	<th>Guest Name</th>	
		<%int mcount1 = 0;
				PreparedStatement psm1 = NGM.conn.prepareStatement("select *  from master_charges order by charges_id asc");
					ResultSet rsm1 = psm1.executeQuery();
					 
					while(rsm1.next()){
						mcount1++;
						%>
								<th><%=rsm1.getString("charges_title") %></th>					
					<%} %>
	<%
	for(int i=0;i<guestvalues.length;i++){
		System.out.println("ddddddd000 "+guestvalues[0]);
		System.out.println("ddddddd111 "+guestvalues[1]);

	%>
	<tr>
	<td><%=guestvalues[i] %></td>
<%
d_guestdetails ins_guest = new d_guestdetails(NGM);
ins_guest.guest_id = guestvalues[i];
ins_guest.selectDB();

%>	
	<td><%=ins_guest.name%></td>		
	
	
	<%int t_chargeid1 = 1;
					while(t_chargeid1<= mcount1){ 
					
						d_guestothercharges ins_gu_other = new d_guestothercharges(NGM);

						ins_gu_other.req_id = req_id;
						ins_gu_other.guest_id = guestvalues[i];
						ins_gu_other.charges_id = Integer.toString(t_chargeid1);
						String sum = ins_gu_other.selectDBsum();
						if(sum==null){
							sum = "0";
						}
						%>
						<td><input type="text" size="6"  value="<%=sum%>"></td>
						ondblclick="bill_dataentry('<%=req_id %>','<%=t_chargeid %>','<%=guest_values[i]%>')"
						<%
						t_chargeid1 = t_chargeid1 + 1;	
					}
					
					%>
	</tr>
	
	<% } %>
	<tr>
	<td></td>
	<td>Total</td>
	<%int t_chargeid1 = 1;
					while(t_chargeid1<= mcount1){ 
					
						%>
						<td><input type="text" size="6"  value="calc"></td>
						<%
						t_chargeid1 = t_chargeid1 + 1;	
					}
					
					%>
	</tr>
		</table>
			<h6>Rs.<%=gh_id%></h6><h6 align="right">For Controller</h6>
				<!-- 	<input type="button" value="cancel" class="btn btn-primary" onclick="close_nicpopup()">
					<input type="button" value="Print pdf" class="btn btn-primary" onclick="printview_receipt()"> -->
					
		 	 </div>
				
			</div> 
			<div class="box-body no-padding">

	 </div>
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		
<div id="viewdata"></div>	


<!-- //convert the rupees and words -->



		
			
	<input type="button" class="btn btn-primary" value="Submit" onClick="submit_bill('<%=gh_id%>')" /> --%>
<%
	break;


	default:
%>Invalid Task;<%
	break;
	}
%>
	