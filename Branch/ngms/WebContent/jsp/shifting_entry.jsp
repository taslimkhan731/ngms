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
		
		String gh_id = (String) request.getAttribute("gh_id");
		String shift_id = (String) request.getAttribute("shift_id");
		System.out.println(gh_id);
		gh_id="1";
		shift_id="37";
		
		int opn_adv=0,opn_reg=0,opn_tot=0,rcp_adv=0,rcp_reg=0,rcp_tot=0,pay_adv=0,pay_reg=0,pay_tot=0;
		
		int total_advance=0,total_regular=0,total_all=0;
		int close_adv = 0,close_reg=0,close_tot=0;
		int total_opn =0,total_rcp=0;
		 
		int tot_tot = 0;
		String criteria="";
		m_shifting	m_shft = new m_shifting(NGM);
		/* if(shift_id==null){
			 shift_id="";
		 }*/
			criteria = m_shft.getshift(shift_id);
		 
		PreparedStatement ps = NGM.tr_conn.prepareStatement("Select * from tr_shift_master where gh_id=?"+criteria);
		ps.setString(1, gh_id);
	System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			opn_adv=rs.getInt("openbal_refund");
			opn_reg = rs.getInt("openbal_regular");
			opn_tot = rs.getInt("openbal_total");
			rcp_adv = rs.getInt("receipt_refund");
			rcp_reg=rs.getInt("receipt_regular");
			rcp_tot = rs.getInt("receipt_total");
			pay_adv = rs.getInt("payment_refund");
			pay_reg = rs.getInt("payment_regular");
			pay_tot = rs.getInt("payment_total");
			
		}
		//close
		rs.close();
		ps.close();
		total_advance =  opn_adv + rcp_adv;
		total_regular =  rcp_adv + rcp_reg;
		total_all = opn_tot + rcp_tot;
		 
		 
		close_adv = total_advance + pay_adv;
		close_reg = total_regular + pay_reg;
		tot_tot = total_all + pay_tot;
		
			 
		
		 
		
		%>
		<form id="shift-entry">
		<div class="row">
		
		 
			
		<div class="box">
			<div class="box-header">
			<table>
			<tr><th></th>
			<th> Advance</th>
			<th> Regular</th>
			<th> Total</th>
			<tr>
			<td>Opening Balance</td>
			<td><input type="text"  name="bal_adv" id="bal_adv" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('bal_adv')" onblur="placezero('bal_adv')" onkeyup="settotal_opb()" value ="<%=opn_adv%>">	</td>		  
		 	<td><input type="text"  name="bal_reg" id="bal_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('bal_reg')" onblur="placezero('bal_reg')"  onkeyup="settotal_opb()" value = "<%=opn_reg%>">	</td>		  
		 	<td><input type="text" name="bal_tot" id="bal_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('bal_tot')" onblur="placezero('bal_tot')" value = "<%=opn_tot%>">	</td>		  
		 	</tr>
		 	<tr>
		 	<td>Receipts</td>
			<td><input type="text" name="rcp_adv" id="rcp_adv" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('rcp_adv')" onblur="placezero('rcp_adv')" onkeyup="settotal_opb()" value = "<%=rcp_adv%>">	</td>		  
		 	<td><input type="text" name="rcp_reg" id="rcp_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('rcp_reg')" onblur="placezero('rcp_reg')" onkeyup="settotal_opb()" value = "<%=rcp_reg%>">	</td>		  
		 	<td><input type="text" name="rcp_tot" id="rcp_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('rcp_tot')" onblur="placezero('rcp_tot')"  value = "<%=rcp_tot%>">	</td>		  
		 	</tr>
		 	<tr>
		 	<td>Total</td>
		 	<td><input type="text" name="tot_adv" id="tot_adv" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('tot_adv')" onblur="placezero('tot_adv')"  value ="<%=total_advance%>">	</td>		  
		 	<td><input type="text" name="tot_reg" id="tot_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('tot_reg')" onblur="placezero('tot_reg')"  value ="<%=total_regular%>">	</td>		  
		 	<td><input type="text" name="tot_tot" id="tot_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('tot_tot')" onblur="placezero('tot_tot')"  value ="<%=total_all%>">	</td>		  
		 	</tr><tr>
		 	<td>Payment Remittance</td>
		 	<td><input type="text" name="rem_adv" id="rem_adv" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('rem_adv')" onblur="placezero('rem_adv')"  value = "<%=pay_adv%>">	</td>		  
		 	<td><input type="text" name="rem_reg" id="rem_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('rem_reg')" onblur="placezero('rem_reg')"  value = "<%=pay_reg%>">	</td>		  
		 	<td><input type="text" name="rem_tot" id="rem_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('rem_tot')" onblur="placezero('rem_tot')"   value = "<%=pay_tot%>">	</td>		  
			</tr><tr>
			 <td>Closing balance wide Nomination</td>
		 	<td><input type="text" name="clo_adv" id="clo_adv" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('clo_adv')" onblur="placezero('clo_adv')"  value = "<%=close_adv%>">	</td>		  
		 	<td><input type="text" name="clo_reg" id="clo_reg" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('clo_reg')" onblur="placezero('clo_reg')"  value = "<%=close_reg%>">	</td>		  
		 	<td><input type="text" name="clo_tot" id="clo_tot" onkeypress="return restrictInput(this, event, digitsOnly);" min="1" class="lg"  onfocus="replacezero('clo_tot')" onblur="placezero('clo_tot')"   value = "<%=tot_tot%>">	</td>		  
			 </tr>
		 	</table>
						 
			</div>
			<div class="form-group">
			<button class="btn btn-success" type="button" onclick="submitdata()" > Submit</button>
			</div>
			</div>
			</div>
		 
		
		
		
	  
	 
		  	 
            
		  
		 </form>
<div id="viewdata"></div>	 
	
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	