<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@ include file="common_ajax.jsp"%>
<%
common con = new common(NGM);
	switch (task) {
	
	case 3:
		utility ngm_utility = new utility();
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String[] bill_values = (String[]) request.getAttribute("values");
		String billquery = "",h_billvalue="";
		System.out.println("hidvalue billquery  JSP   "+bill_values.length);
		for(int i=0;i<bill_values.length;i++){
			System.out.println("ddddddd "+bill_values[i]);
			if(!billquery.equals("")){
				billquery = billquery + ",";
				h_billvalue=h_billvalue+",";
			}
			billquery = billquery + "'"+bill_values[i]+"'";
			h_billvalue = h_billvalue+bill_values[i];
			
		}
			
		//String hidvalue=(String) request.getAttribute("hidvalue");
		//String hid=(String) request.getAttribute("hidvalue");
		System.out.println("hidvalue billquery  JSP   "+billquery);
		System.out.println("hidvalue 22222  JSP   "+bill_values);
		%>
		 
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 	<input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
<%-- 	 						<input type="hidden" name="hidvaluepop" id="hidvaluepop" min="1" class="lg" value="<%=hidvalue%>"> 
 --%>	 	
		<div class="col-sm-12">
		
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="panel-title">Receipt Details</div>
				
		 </div>
		
		 	<div class="panel-body">
				<table class="table table-bordered">
				<tr>
					<th>BIll ID</th>	
	<th>Bill No</th>
		<th>Created by</th>	
		<th>Date</th>
		<th>Request ID</th>
		<th>Guest Details</th>
		<th>Stayed From</th>
		<th>Stayed To</th>
		<th>Room</th>
		<th>Amount</th>
										
					</tr>
					
					<%
					String createdby="",bill_id="",date="",amount="",name="",total="",amount1="";
					int count=0;
				 	
				  // String str =ngm_utility.getrolelists(hidvalue);
				 	
				  	//hidvalue = "'"+hidvalue+"'";
				  	PreparedStatement pss = NGM.conn.prepareStatement("select sum(amount::Integer)as amount from tr_billvsguestvscharges where bill_id in ("+billquery+")");
				  			
				  			//select sum(a.amount::Integer) as total from tr_billvsguestvscharges a join guest_details b on a.guest_id = b.guest_id where b.guest_id in("+hidvalue+")");
				  	System.out.println("A233333new    "+pss);
				  	ResultSet rss=pss.executeQuery();
				  	int j=0;
				  	while(rss.next())
				  	{
				  		j=j+1;
				  		total=rss.getString("amount");
				  	}
				 	 //close
					rss.close();
					pss.close();
				  //String t_total_adv = "";
				  	
					/* PreparedStatement psadv = NGM.conn.prepareStatement("select sum(advance_amt::Integer) as advance from (select * from tr_billvsguest where bill_id in ("+billquery+"))a join (select req_id,guest_id,advance_amt from tr_checkin where status!='CAN')b on a.req_id=b.req_id and a.guest_id=b.guest_id");
		  			ResultSet rsadv=psadv.executeQuery();
		  			while(rsadv.next())
		  			{
		  				t_total_adv=rsadv.getString("advance");
		  			} */
				 
				  	
					//hidvalue = "'"+hidvalue+"'";
					int i=0;
					int tmp=0 ; 
					//String array[] = hidvalue.split(","); 
				  	for(int k=0;k<bill_values.length;k++){
				  		String t_billid = "",trid="",t_indiv_adv="";
				 	PreparedStatement ps1 = NGM.conn.prepareStatement("select * from((((select bill_id,createdby,date,billno from tr_bill_master where status='BILL')a join (select sum(amount::Integer)as amount from tr_billvsguestvscharges where bill_id in ('"+bill_values[k]+"'))b on a.bill_id in ('"+bill_values[k]+"'))x join (select bill_id,guest_id as b_gid,req_id as b_reqid  from tr_billvsguest)y on x.bill_id=y.bill_id)z join (select * from guest_details)p on z.b_gid=p.guest_id)q join (select distinct on (guest_id) guest_id as c_gid,arrival_time_date,departure_time_date,tr_id,advance_amt from tr_checkin where status!='CAN')r on q.b_gid=r.c_gid");
				 			//"select * from(select bill_id,createdby,date from tr_bill_master where status='BILL')a join (select sum(amount::Integer)as amount from tr_billvsguestvscharges where bill_id in ('"+bill_values[k]+"'))b on a.bill_id in ('"+bill_values[k]+"')");
				 			
					System.out.println("A3    "+ps1);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){ 
						i=i+1;
						bill_id=rs1.getString("bill_id");
						createdby=rs1.getString("createdby");
						date=rs1.getString("date");
						amount=rs1.getString("amount");
						t_indiv_adv = rs1.getString("advance_amt");
						tmp = ((Integer.parseInt(amount))- (Integer.parseInt(t_indiv_adv)));
					//	amount = String.valueOf(tmp);
						trid = rs1.getString("tr_id");
						String t_roomno="";
						PreparedStatement psroom = NGM.conn.prepareStatement("select * from (select type,room_no,bed_no,gh_id,block_id from tr_allotment where allot_id=(select min(allot_id) from tr_checkin_allot where tr_id=?)) a join (select block_id,block_name_short,gh_id from room_block)b on a.gh_id=b.gh_id and a.block_id=b.block_id");// and status='LIV'
						
						psroom.setString(1, trid);
						System.out.println("psroom............................. "+psroom);
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
						<%if(!rs1.getString("bill_id").equals(t_billid)){ %>
						<td><%=rs1.getString("bill_id") %></td>		
								<td><%=rs1.getString("billno") %></td>					
								<td><%=rs1.getString("createdby") %></td>					
								<td><%=con.display_timestamp(rs1.getString("date"))%></td>	
						<%} else{
							%><td></td>
							<td></td>
							<td></td>
							<td></td>
							<%
						}
						%>
								<td><%=rs1.getString("req_id") %></td>	
								<td><%=rs1.getString("name") %><br><%=rs1.getString("designation")!=null?","+rs1.getString("designation"):"" %><%=rs1.getString("department")!=null?","+rs1.getString("department"):"" %></td>	
								<td><%=con.display_timestamp(rs1.getString("arrival_time_date")) %></td>	
								<td><%=con.display_timestamp(rs1.getString("departure_time_date")) %></td>
								<td><%=t_roomno %></td>		
 							<%if(!rs1.getString("bill_id").equals(t_billid)){ %>	
								<td><%=Integer.parseInt(amount) %></td>	
								<%} else{
							%><td></td>
							<%
						}
							t_billid = rs1.getString("bill_id");%>		
									</tr>	
					<input type="hidden" name="gusetid<%=i%>" id="gusetid<%=i%>"  value='<%=bill_id%>'> 
					
					<%}
					
					//close
					rs1.close();
					ps1.close();
				  	} %>
				
					<%System.out.println("counttttttttttttttttttt     "+count);%>
										
					<tr>
					<td colspan="8"></td>
					
					<td><strong>Total value</strong></td>
					<td><%=total%></td>
					</table>
					</div>
					<input type="hidden" name="countbill" id="countbill"  value='<%=i%>'> 
					<div class="panel-footer clearfix">
						<div class="pull-right">
					<button type="button" class="btn btn-primary" value="" onClick="add_guest_receipt('<%=gh_id%>','<%=sel_block%>')" ><i class="fa fa-plus"></i> Add Bill </button>
					<button type="button" value="" class="btn btn-primary" onclick="printview_receipt('<%=h_billvalue %>')"><i class="fa fa-check-square-o"></i> Make Receipt</button>
					</div>
					</div>
		 	
				
			</div> 
			<div class="box-body no-padding">

	 </div>
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
		
<div id="viewdata"></div>	 

	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	