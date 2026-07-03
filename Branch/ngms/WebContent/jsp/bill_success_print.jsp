<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%
	common con = new common(NGM);

	switch (task) {
	case 7:
		String bill_id = (String) request.getAttribute("bill_id");
		String div = (String) request.getAttribute("div");
		String gh_id = (String) request.getAttribute("gh_id");
		System.out.println("bill_id jsp" + bill_id);
%>

<div id="home_value">

	<div class="col-sm-8 center">

		<div class="box">
			<div class="box-body">

				<h3 align="center">
					<span class="fa fa-check text-red"></span> Bill Successfully
					Created.
				</h3>

				<h4 align="center" class="text-blue">
					Your Bill ID is: <span class="text-red"><%=bill_id%></span>
				</h4>
				<hr>
			</div>

			<div class="box-footer clearfix">
				<button style="margin-right: 5px;"
					onclick="show_billforpdf('<%=gh_id%>','<%=bill_id%>')"
					class="btn btn-primary pull-right hidden-print">
					<i class="fa fa-download"></i> Download PDF Application
				</button>
				<%
					if (div.equals("billing")) {
				%>
				<button style="margin-right: 5px;" onclick="add_bill_lists()"
					class="btn btn-primary pull-right hidden-print">
					<i class="fa fa-times"></i> Close
				</button>
				<%
					} else {
				%>

				<button style="margin-right: 5px;" onclick="checkout('1')"
					class="btn btn-primary pull-right hidden-print">
					<i class="fa fa-times"></i> Close
				</button>

				<%
					}
				%>

			</div>



		</div>

	</div>
	<!-- <p>This Portal is designed and developed by National Informatics Centre. The content is managed by GAD political department of Government of Odisha. 
It is hosted at State Data Centre, Goverment of Odisha.</p> -->
</div>
<%
	break;
	default:
%>Invalid Task;<%
	break;
							}
%>
