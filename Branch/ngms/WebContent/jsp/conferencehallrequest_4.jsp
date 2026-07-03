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
		String conf_req_id = (String) request.getAttribute("conf_req_id");
		String gh_id = (String) request.getAttribute("gh_id");
		String sel_block = (String) request.getAttribute("sel_block");
		String charge = (String) request.getAttribute("charge");
		String additionalcharge = (String) request.getAttribute("additionalcharge");
		System.out.print("hggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhggggggggggggggggggghhhhhhhhhhhhhhh"+conf_req_id);
		%>
		
	<br>
		<div id="bill" name="bill" class="form-group" >	
		
		<form name="form-gen_bill" method="post" id="form-gen_bill">	
		<%----------------------------------------------------------------- --%>
		<div class="row">
	 	<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg" value='<%=gh_id%>'> 
		 <input type="hidden" name="sel_block" id="sel_block" min="1" class="lg" value='<%=sel_block%>'> 
		<div class="container" style="width:800px;">
			<%
		d_ghdetails gh = new d_ghdetails(NGM);
		gh.gh_id = gh_id;
		gh.block_id = sel_block;
		gh.selectDB();
		%>
		<div class="panel panel-default">
		
		<div class="panel-heading">
			<div class="panel-title"><%=gh.block_name%>,<%=gh.gh_name %></div>
			<div class="pull-right panel-tools">
              <!-- <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button> -->                                        
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
			<span class="pull-left"><strong>Bill No: </strong><%=conf_req_id %>(R)</span>
			
			<span class="pull-right"><strong>Date: </strong><%=dateFormat.format(cal.getTime())%></span>
			</div>
			<table class="table-bordered table">
			<thead>
			<tr>
		<th>Name</th>	
	    <th>Address</th>	
		<th>Category</th>	
		<th>From Date</th>	
		<th>To Date</th>	
		<th>Type</th>	
		<th>Charge</th>	
		<th>Additional</th>	
		</tr>
		</thead>
	
	
	<%		
	String conf_cat_id="",fromdate="",todate="",conf_use_id="",additional="",status="",name="",address="",conf_cat_desc="",conf_use_desc="";


	String sql = "select * from ((select * from tr_conf_request where gh_id=? and conf_req_id=?) a join (select * from m_conf_hall_category) b on a.conf_cat_id=b.conf_cat_id) c join (select * from m_conf_hall_use) d on c.conf_use_id=d.conf_use_id";

	PreparedStatement ps = NGM.conn.prepareStatement(sql);
	ps.setString(1, gh_id);
	ps.setString(2, conf_req_id);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
		conf_cat_id=rs.getString("conf_cat_id");
		conf_cat_desc=rs.getString("conf_cat_desc");
		fromdate=rs.getString("fromdate");
		todate=rs.getString("todate");
		conf_use_id=rs.getString("conf_use_id");
		conf_use_desc=rs.getString("conf_use_desc");
		additional=rs.getString("additional");
		status=rs.getString("status");
		name=rs.getString("name");
		address=rs.getString("address");
	}
		 //close
	 
		%>
		
		
		<td><%=name%></td>	
		<td><%=address %></td>	
		<td><%=conf_cat_desc %></td>
		<td><%=fromdate %></td>	
		<td><%=todate %></td>		
		<td><%=conf_use_desc%></td>	
		<td><%=charge%></td>		
		<td><%=additionalcharge%></td>		
			

<% 

	 //close
    rs.close();
	ps.close();
	int total = Integer.parseInt(charge)+Integer.parseInt(additionalcharge);
	%>	
		
		</table>
		<br>
				
					
					
		<h6><strong>Amount to pay</strong>: <%=total%>
		 <h6>(Rupees  </span><span id="rupees" class="border-bottom" style="width: 79%;"><script>document.getElementById('rupees').innerHTML = inWords(<%=total %>);</script></span>)</h6>
		 	<h6>R No. & Date</h6><h6 align="right">Name & Signature of Receptionist With date</h6>
				<!-- 	<input type="button" value="cancel" class="btn btn-primary" onclick="close_nicpopup()">
					<input type="button" value="Print pdf" class="btn btn-primary" onclick="printview_receipt()"> -->
					
					
		
		 	 </div>
		 	 
		 	 
		 	 <div class="panel-footer clearfix">
		 	 	
		 	 	<div class="pull-right">
					<button type="button" class="btn btn-success" onClick="pdf_bill_conference('<%=gh_id%>','<%=conf_req_id%>')" ><i class="fa fa-file-pdf-o"></i> Print PDF</button>
		<button type="button"  class="btn btn-danger" onclick="cancelcloseconferencehallrequest('1')"><i class="fa fa-times"></i>  Close</button>
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
	