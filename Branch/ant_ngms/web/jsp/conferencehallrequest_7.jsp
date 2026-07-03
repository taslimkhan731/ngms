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
	String t_gh_name = "";
	switch (task) {

		case 0 :
			common con = new common(NGM);
			String conf_req_id = (String) request.getAttribute("conf_req_id");
			String gh_id = (String) request.getAttribute("gh_id");
			String sel_block = (String) request.getAttribute("sel_block");
			d_conf_request ins_req = new d_conf_request(NGM);
			ins_req.conf_req_id = conf_req_id;
			ins_req.selectDB();
			d_conf_bill ins_bill = new d_conf_bill(NGM);
			ins_bill.conf_req_id = conf_req_id;
			ins_bill.gh_id = gh_id;
			ins_bill.selectDB_reqid();
			String charge = ins_bill.charge;
			String additionalcharge = ins_bill.additionalcharge;
			int amount = Integer.parseInt(charge) + Integer.parseInt(additionalcharge);
			/* String charge = (String) request.getAttribute("charge");
			String additionalcharge = (String) request.getAttribute("additionalcharge"); */
			System.out.print(
					"hggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhggggggggggggggggggghhhhhhhhhhhhhhh" + conf_req_id);
%>


<div id="bill" name="bill" class="">

	<form name="form-gen_bill" method="post" id="form-gen_bill">
		<%----------------------------------------------------------------- --%>
		<div class="">
			<input type="hidden" name="gh_id" id="gh_id" min="1" class="lg"
				value='<%=gh_id%>'> <input type="hidden" name="sel_block"
				id="sel_block" min="1" class="lg" value='<%=sel_block%>'>
			<div class="" style="">
				<%
					d_ghdetails gh = new d_ghdetails(NGM);
							gh.gh_id = gh_id;
							gh.block_id = sel_block;
							gh.selectDB();
				%>
				<div class="panel panel-default">

					<div class="panel-heading">
						<div class="panel-title"><%=gh.block_name%>,<%=gh.gh_name%></div>
						<div class="pull-right panel-tools">
							<!-- <button type="button" class="btn btn-sm" onclick="close_nicpopup()"><i class="fa fa-times"></i></button> -->
						</div>
					</div>

					<div class="panel-body">
						<h4 align="center"><%=gh.block_name%>,<%=gh.gh_name%></h4>
						<h4 align="center">
							<u>CASH RECEIPT</u>
						</h4>
						<%
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
									Date date = new Date();
									System.out.println(dateFormat.format(date));
									Calendar cal = Calendar.getInstance();
									System.out.println(dateFormat.format(cal.getTime()));

									String words = "";
								//words=inWords(total);
						%>

						<span class="pull-left">No..........................(R)</span><span
							class="pull-right">Date:<%=dateFormat.format(cal.getTime())%></span>
						<div class="clearfix"></div>
						<br> Received from Shri/Smt <span
							style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 80%;"><%=ins_req.name%>,<%=ins_req.address%></span>
						<br>
						<!-- <span style="border-bottom: 1px dotted rgb(34, 34, 34); display: inline-block; width: 100%;">address</span> <br> -->
						a sum of INR <span class="border-bottom"><%=amount%>
							(Rupees </span><span id="rupees" class="border-bottom"
							style="width: 79%;"><script>
								document.getElementById('rupees').innerHTML = inWords(
							<%=amount%>
								);
							</script>)</span>


						<!-- <select name="se1" id="se"><option value="C">CASH</option><option value="Q">CHEQUE</option></select> -->



						<div class="f clearfix" id="radioselect">
							by <label class="radio-inline"> <INPUT TYPE="radio"
								class="rad" name=see id="se" VALUE="CASH" checked> CASH
							</label> <label class="radio-inline"> <INPUT TYPE="radio"
								class="rad" name=see id="se" VALUE="POS"> POS
							</label> <!-- <label class="radio-inline"> <INPUT TYPE="radio"
								class="rad" name=see id="se" VALUE="ONLINE"> ONLINE
							</label> --> in full settlement of <label class="radio-inline"><INPUT
								TYPE="text" autocomplete="off" NAME="no" id="text_se" VALUE=""
								disabled="disabled"> </label>

						</div>
						<div class="clearfix"></div>
						<h6>
							Bill No:

							<%=ins_bill.conf_bill_id%>
							dated :<%=dateFormat.format(cal.getTime())%><br>
							<%
								//close
							%>
						</h6>
						<%-- <%for(i=1;i<=p;i++){ %>
				<input type="hidden" name="bill<%=i%>" id="bill<%=i%>"  value='<%=billid%>'> 
			<%} %> --%>
						<%-- <input type="hidden" name="bill<%=e%>" id="bill<%=e%>"  value='<%=billid%>'> 
			 --%>





						<h6></h6>
						<h6></h6>
						<h6>
							INR.<%=amount%></h6>
						<h6 align="right">For Controller</h6>


					</div>

					<div class="panel-footer clearfix">

						<div class="pull-right">
							<input type="button" value="Submit Receipt"
								class="btn btn-primary"
								onclick="receipt_conferencerequest('<%=conf_req_id%>','<%=gh_id%>')" />
							<input type="button" value="Cancel" class="btn btn-primary"
								onclick="close_nicpopup()">
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
