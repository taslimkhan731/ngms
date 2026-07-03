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
	switch (task) {
	
	case 0:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String shift_id = (String) request.getAttribute("shift_id");
		System.out.println(gh_id);
		System.out.println(shift_id);
		
		int opn_adv=0,opn_reg=0,opn_tot=0;
		
		int total_advance=0,total_regular=0,total_all=0,tot_adv_rec=0;
		int close_adv = 0,close_reg=0,close_tot=0;
		int total_opn =0,total_rcp=0;
		 String open_time="",close_time="",status="";
		int tot_tot = 0;
		String criteria="";
		m_shifting	m_shft = new m_shifting(NGM);
		 if(shift_id==null){
			 shift_id="";
		 }
			//criteria = m_shft.getshift(shift_id);
		 
		PreparedStatement ps = NGM.tr_conn.prepareStatement("Select * from tr_shift_master where gh_id=? and shift_id=?");
		ps.setString(1, gh_id);
		ps.setString(2, shift_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			opn_adv=rs.getInt("openbal_refund");
			opn_reg = rs.getInt("openbal_regular");
			opn_tot = rs.getInt("openbal_total");
			open_time = con.display_timestamp(rs.getString("from_time"));
			close_time = con.display_timestamp(rs.getString("close_time"));
			status = rs.getString("status");

		}
		//close
		rs.close();
		ps.close();
		String mode="";
		if(status.equals("A")){
			
			mode = "(DRAFT)";
		}
		else if(status.equals("C")){
			
			mode = "";
		}
		 
		String roll_no="";	 
		 String advce_recipt="";
		PreparedStatement ps_roll = NGM.tr_conn.prepareStatement("select max(roll_no)as roll_no, max(adv_bill_id)as billno from tr_checkin where gh_id=? and shift_id=? and status!='CAN'");
		ps_roll.setString(1, gh_id);
		ps_roll.setString(2, shift_id);
		ResultSet rs_roll = ps_roll.executeQuery();
		while(rs_roll.next()){
			roll_no  = rs_roll.getString("roll_no")!=null?rs_roll.getString("roll_no"):""; 
			advce_recipt  = rs_roll.getString("billno")!=null?rs_roll.getString("billno"):"";  
			
		}
		//close
		rs_roll.close();
		ps_roll.close();
		String billno="";	 
		PreparedStatement ps_bill = NGM.tr_conn.prepareStatement("select max(billno) from tr_bill_master where gh_id=? and shift_id=? and status!='CAN'");
		ps_bill.setString(1, gh_id);
		ps_bill.setString(2, shift_id);
		ResultSet rsbill = ps_bill.executeQuery();
		while(rsbill.next()){
			billno  = rsbill.getString("max")!=null?rsbill.getString("max"):"";  
			
		}
		//close
		rsbill.close();
		ps_bill.close();
		String recp_no="";	 
		PreparedStatement ps_recpt = NGM.tr_conn.prepareStatement("select max(rec_id) from tr_receipt where gh_id=? and shift_id=? and status!='CAN'");
		ps_recpt.setString(1, gh_id);
		ps_recpt.setString(2, shift_id);
		ResultSet rs_recpt = ps_recpt.executeQuery();
		while(rs_recpt.next()){
			recp_no  = rs_recpt.getString("max")!=null?rs_recpt.getString("max"):"";  
			
		}	
		//close
		rs_recpt.close();
		ps_recpt.close();
		d_dcb ins_dcb = new d_dcb(NGM);
		ins_dcb.shift_id = shift_id;
		ins_dcb.gh_id = gh_id;
		String dcbnumber  = ins_dcb.selectDB_ShiftDCB();
		dcbnumber=dcbnumber!=null?dcbnumber:"";
		
		d_checkin ins_checkin = new d_checkin(NGM);
		ins_checkin.shift_id = shift_id;
		ins_checkin.gh_id = gh_id;
		String advtotal = ins_checkin.selectDB_shiftAdv();
		advtotal=advtotal!=null?advtotal:"0";
		
		d_receipt ins_receipt = new d_receipt(NGM);
		ins_receipt.shift_id = shift_id;
		ins_receipt.gh_id = gh_id;
		String receipttotal = ins_receipt.selectDB_shiftRecTot();
		receipttotal=receipttotal!=null?receipttotal:"0";
		
		 d_dcb_payment ins_dcbpay = new d_dcb_payment(NGM);
		 ins_dcbpay.shift_id = shift_id;
		 ins_dcbpay.gh_id = gh_id;
		 String dcbtotal = ins_dcbpay.selectDB_DcbTotal();
		 dcbtotal = dcbtotal!=null?dcbtotal:"0";
		 String dcbadv = "-";
		 

			total_advance =  opn_adv + Integer.parseInt(advtotal);
			total_regular =  opn_reg + Integer.parseInt(receipttotal);
			tot_adv_rec = Integer.parseInt(advtotal)+Integer.parseInt(receipttotal);
			total_all = opn_tot + tot_adv_rec;
			 
			close_adv = total_advance;
			close_reg = total_regular - Integer.parseInt(dcbtotal);
			tot_tot = total_all - Integer.parseInt(dcbtotal);
			
		%>
		<div class="panel panel-default">
		 
		 <div class="panel-heading">
		 	<div class="panel-title">Certificate</div>
		 	<div class="pull-right panel-tools">
              <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            </div>
		 </div>
		 <div class="min-height-popup">
		 
		 
		 <div class="panel-body">
		<h4 align="CENTER">ODISHA GUEST HOUSE, ODISHA</h4>
		<h5 align="CENTER">CERTIFICATE OF TRANSFER OF CHARGE OF RECEPTION DUTY</h5>
		 <p align="CENTER"><%=mode %> </p><br><br>
		<P> Duty Hours : From  <%=open_time%>  To  <%=close_time %>
		 <p align="Right"> Reception Assistants on Duty</p>
		 <table align="right">
 				<%PreparedStatement ps1 =NGM.tr_conn.prepareStatement("select * from  (select userid,username from m_user_details )a join tr_shiftvsuserid as b on  a.userid=b.user_id where shift_id=?"); 
 					
 					 ps1.setString(1,shift_id);
								
 						//ps.setString(1,"11");
							
							ResultSet rs1 = ps1.executeQuery();
							int i=0;
							while(rs1.next()){
							i=i+1;
							%>
							<tr> <td> (<%=i %>)  Name :</td> <td><%=rs1.getString("username") %></td></tr>
						 
				<%} %>
				</table>

			<table  class="table table-striped table-bordered">

			<tr><th></th>
			<th> Advance</th>
			<th> Regular</th>
			<th> Total</th>
			<tr>
			<td>Opening Balance</td>
			<td> <%=opn_adv	%></td>		  
		 	<td> <%=opn_reg%>	</td>		  
		 	<td> <%=opn_tot%></td>		  
		 	</tr>
		 	<tr>
		 	<td>Receipts</td>
			<td> <%=advtotal%>	</td>		  
		 	<td> <%=receipttotal%></td>		  
		 	<td> <%=tot_adv_rec%>	</td>		  
		 	</tr>
		 	<tr>
		 	<td>Total</td>
		 	<td> <%=total_advance%>	</td>		  
		 	<td> <%=total_regular%>	</td>		  
		 	<td> <%=total_all%>	</td>		  
		 	</tr><tr>
		 	<td>Payment Remittance</td>
		 	<td> <%=dcbadv%></td>		  
		 	<td> <%=dcbtotal%></td>		  
		 	<td> <%=dcbtotal%>	</td>		  
			</tr><tr>
			 <td>Closing balance wide Nomination</td>
		 	<td> <%=close_adv%>	</td>		  
		 	<td> <%=close_reg%></td>		  
		 	<td> <%=tot_tot%>	</td>		  
			 </tr>
			 <tr><td colspan="3"> Closing Balance Handed Over : Rs</td>
			 <td><%=tot_tot%></td><tr>
		 	<tr>
			<td colspan="4">			 
			   Rupees <span id="rupees" class="border-bottom" style="width: 75%;">  <script>document.getElementById('rupees').innerHTML = inWords(<%= tot_tot%>);</script></span> )</td> </tr>
			</table>
			
			<br/>
			<table>
			
			<tr>
			
			<th colspan="4" align="center" colspan="4"><u>DETAILS OF RECORDS HANDED OVER</u></th></tr>
			<tr>
			<td> Occupation Register No </td>
			<td>........................</td>
			<td> Written Upto Roll No </td>
			<td><%= roll_no!=null?roll_no:""%></td></tr>
			<tr>
			<td>Bill Book No</td>
			<td>........................</td>
			<td> Written Upto Bill No </td>
			<td><%= billno!=null?billno:""%> </td></tr>
			<tr>
			<td> Cash Receipt Book No </td>
			<td>........................</td>
			<td>Writen upto Receipt No </td>
			<td><%= recp_no!=null?recp_no:""%></td></tr>
			<tr>
			<td> Avance Cash Receipt Book No </td>
			<td>........................</td>
			<td>Written upto Receipt No </td>
			<td><%=advce_recipt!=null?advce_recipt:"" %></td></tr>
			<tr>
			<td>D.C.B. File and D.C.B.Statement of the day Written upto...</td><td><%= dcbnumber!=null?dcbnumber:""%></td></tr>
			<tr><td colspan="4">Status Report on detailing of Vehicles completed upto.....................a.m/p.m/</td></tr>
			<tr><td colspan="2">(7) Reservation Register</td>
			 <td colspan="2">(8) MessageFile</td>
			</tr>
			
			

			<tr> <td colspan="4" ><!-- <textArea class="form-control"></textArea> --> 
			<div style="height:"150px"></div>
			</td></tr>
			<!-- <tr><td colspan="3">Signature of the Reception Assistant (Take Over Charge)</td>
			<td >Name and signature of the Reception Assistant (Receiving Charge)</td>
			</tr> -->
			
			</table>
<div style="height:150px;padding:10px;border:1px solid #222;">Messages if Any/ Remarks</div><br/>
		<span  class="pull-left" >Name Signature of the <br> Reception Assistant <br> <small>(Take Over Charge)</small></span>
			<span  class="pull-right">Name and signature of the  <br> Reception Assistant  <br> <small>(Receiving Charge)</small></span>

		
			
			 <div class="clearfix"></div>
<br/>
			

	 		 
	  
	 
</div>	

<div class="panel-footer clearfix">
		<div class="pull-right">
			<button class="btn btn-danger" type="button" onclick="printshiftreport('<%=shift_id%>','<%=gh_id%>')" ><i class="fa fa-print"></i> Print PDF</button>
			 <button class="btn btn-danger" type="button" onclick="close_nicpopup()" ><i class="fa fa-times"></i> Close</button>
	 
		</div>
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
	