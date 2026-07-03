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
	switch (task) {
	
	case 0:
		common con = new common(NGM);
		String misc_bill_id = (String) request.getAttribute("misc_bill_id");
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String status="",misc_rollno="",amount="",misc_bill_date="",misc_title="",dcb_type_title="",misc_rec_id="";
		
		String sql = "select * from (((select * from tr_misc_billing where gh_id=? and misc_bill_id=?) aa join (select * from tr_misc_receipt)bb on aa.gh_id=bb.gh_id and aa.misc_bill_id=bb.misc_bill_id)a join (select * from m_misc_bill_types) b on a.misc_id=b.misc_id) c join (select * from m_dcb_types) d on c.dcb_type_id=d.dcb_type_id";

		PreparedStatement ps = NGM.conn.prepareStatement(sql);
		ps.setString(1, gh_id);
		ps.setString(2, misc_bill_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			misc_bill_id=rs.getString("misc_bill_id");
			status=rs.getString("status");
			misc_bill_date=rs.getString("misc_bill_date");
			misc_rollno=rs.getString("misc_rollno");
			misc_title=rs.getString("misc_title");
			amount=rs.getString("amount");
			dcb_type_title=rs.getString("dcb_type_title");
			misc_rec_id = rs.getString("misc_rec_id");
		}
		%>

		<div id="bill" name="bill" class="" >	
		
		<form name="form-gen_bill" method="post" id="form-gen_bill">	
		<%----------------------------------------------------------------- --%>
		<div class="">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 
		<div class="" style="">
			<%
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = sel_block;
		gh.selectDB();
		%>
		
		<div>
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
	 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="" style="">
		<div class="panel panel-default">
			
				<div class="panel-heading">
				<div class="panel-title"><%=gh.block_name%>, <%=gh.gh_name %></div>
				<div class="pull-right panel-tools">
             		 <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button>                                        
            	</div>
            	</div>
		
		<div class="panel-body Receipt">
			
			<h4 align="center"><u>CASH RECEIPT</u></h4>
			<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
			   Date date = new Date();
			   System.out.println(dateFormat.format(date));
		 	    Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			
			String words="";
			//words=inWords(total); 
			%>
			
			<span class="pull-left">No: <%=misc_rec_id%>(R)</span><span class="pull-right">Date:<%=dateFormat.format(cal.getTime())%></span>
			<div class="clearfix"></div><br>
			Received from   <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 80%;"><%= misc_title%></span>	<br>
			<!-- <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 100%;">address</span> <br> -->
			a Sum of INR <span class="border-bottom"><%=Integer.parseInt(amount)%> ( Rupees :   </span><span id="rupees" class="border-bottom" style="width: 79%;"><script>document.getElementById('rupees').innerHTML = inWords(<%=amount%>);</script></span>)
			by Cash in full settlement.
			<h6>Bill No:<%=misc_bill_id%>  <strong>Dated :</strong> <%=dateFormat.format(cal.getTime())%><br></h6>
			
			
			<h6></h6>
			<h6></h6>
			<h6>INR.<%=Integer.parseInt(amount)%></h6><h6 align="right">For Controller</h6>
					
					
		 	 </div>
		 	 
		 	 <div class="panel-footer clearfix">
		 	 	<div class="pull-right">
		 	
					<button type="button" class="btn btn-primary" onclick="print_pdf_misc('<%=misc_bill_id%>','<%=gh_id%>','<%=sel_block%>')"><i class="fa fa-file-pdf-o"></i> Receipt PDF</button>
					<button type="button"  class="btn btn-danger" onclick="close_nicpopup()"><i class="fa fa-times"></i>  Close</button>
					
				</div>
				</div>
			</div> 
			<div class="box-body no-padding">

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
	