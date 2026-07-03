
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
	
	case 1:
		common con = new common(NGM);
		String gh_id = (String) request.getAttribute("gh_id");
		String vv = (String) request.getAttribute("guest_id");
		
		System.out.println(gh_id);
		%>
		
		<!----------------------------------------------------- SEARCH -------------------------------------------------------->
		<div class="row">
		<div class="col-sm-4 center addCalendar">
		<!-- <form onkeypress="if(event.keyCode==13){document.getElementById('searchbt').click();}"> -->
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-search"></i> Search</div>
				</div>
				
				<div class="panel-body no-padding">
			<div class="f">
			<label>By Room No</label>
			<input type="text" name="room_no" id="room_no" size="10" autofocus="autofocus">
			</div>
			
			<div class="f">
			<label>By Name</label>
			<input type="text" name="name" id="name"> 
            </div> 
            
			<div class="f marg">
			<button class="btn btn-success btn-flat" id="searchbt" type="button" onclick="searchguest()" ><i class="fa fa-search"></i>Go</button>
			</div>
			</div>
			</div>
			<!-- </form>   -->
		</div>
		</div>
<%
	break;

	case 2:
		m_foodbill ins_guest = new m_foodbill(NGM);
		d_guestcatering ins_food=new d_guestcatering(NGM);
		gh_id = (String) request.getAttribute("gh_id");
		String criteria = (String)request.getAttribute("criteria");	
		String req_id="",guest_id="";
		String as="";
		%>
		<form name="form-general" method="post" id="form-general">	
	 	 <div class="row">
		<div class="col-sm-12">
		<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i> Food Details</div>
				</div>
			
		 <div id="search" class="panel-body">
		 	
						
					
					<%PreparedStatement ps = NGM.conn.prepareStatement("select Distinct guest_id,name,req_id from(((select room_no,allot_id from tr_allotment where status='CIN')a join(select allot_id,tr_id from tr_checkin_allot)b on a.allot_id=b.allot_id)c join(select guest_id as sss,tr_id from tr_checkin where gh_id=?)d on c.tr_id=d.tr_id)e join (select* from guest_details)f on e.sss=f.guest_id where"+criteria);
					ps.setString(1, gh_id);
					ResultSet rss=ps.executeQuery();
					int count2=0;
					int i=0;
					System.out.println("Quesry                "+ps);
					while(rss.next())
					{
						i=i+1;
						req_id=rss.getString("req_id");
						guest_id=rss.getString("guest_id");%>
						<input type=hidden name="sel_id" id="sel_id" value="<%=guest_id%>">
						<%
						count2=count2+i;
					}
					PreparedStatement pss = NGM.conn.prepareStatement("select item_id,item_title,unitprice from m_food_items");
					%>
					
					<div class="f">
					<label>Guest Name</label>
					<%=NGM.ins_utility.getDBcombo_special_code("sel_parent", "form-general", "sel_parent", "aaa()",ps,"")%>
					
				
					
				
				<!--  <input type=hidden name="hidvalue" id="hidvalue" value="" onclick="aaa()">
				 -->
					</div>
					<div class="f">
					<label>Food Time</label>
					<select name="food" id="food" value="" onchange="aaa()">
					 	 <option value="B">Breakfast</option>
   						 <option value="L">Lunch</option>
    					 <option value="D">Dinner</option>
					</select>
					</div>
					<div class="clearfix"></div>
					<div id="toreset">
					
					<div class="f">
					<label>Food Items</label>
					<%=NGM.ins_utility.getDBcombo_special_food("sel_item", "form-general", "sel_item", "price(this.value)",pss,"")%>
					</div>
					
					<div class="f hide-others hide">
					<label>Other Items</label>
					<input type="text" size="10" maxlength="25" name="other" id="other" >
					</div>
					
					<div class="f">
					<label>Unit Price</label>
					<input type="text" size="10" maxlength="5" name="unit_price" id="unit_price" onblur="addvalues()" onkeyup="addvalues()" value="0">
					</div>
					<div class="f">
					<label>No of Units</label>
					<input type="text" size="10" maxlength="5" name="no_unit" id="no_unit" onblur="addvalues()" onkeyup="addvalues()" value="1">
					</div>
					
					<div class="f">
					<label>Total</label>
					<input type="text" size="10" maxlength="10" id="total_food" name="total_food" value="0">
					</div>
					
					</div>
					
					<div class="clearfix"></div>
					<div class="f">
					<label></label>
					<button class="btn btn-danger pull-right" type="button" onclick="next('<%=req_id%>')"><i class="fa fa-plus"></i> Add to Food Bill</button>
					</div>
					
		 	 <input type=hidden name="count2" id="count2" value="<%=i%>">
		 	
			
	
			</div> 
			
		 			 		
		 	 
		 
		 </div>
		 
		  
			
			</div>
	<%
	break;

	case 3:
	
		String amount="",total="";
	%>		
			
			<div class="">
			
			<div class="panel panel-info">
		<div class="panel-heading">
				<div class="panel-title"><i class="fa fa-sign-in"></i> Bill List</div>
				</div>
				<%String guest = (String)request.getAttribute("hidvalue"); %>
				<%String item = (String)request.getAttribute("food"); %>
				
				
				<div class="panel-body">
				<table class="table table-striped table-condensed">
				<tr>
				<th>Si.No:</th>
				<th>Item Details</th>
				<th>Unit Price</th>
				<th>Number of Units</th>
				<th>Total</th>
				<th></th>
				</tr>
				
				<%
				
				// d_guestcatering ins_food;
				//guest=ins_food.guest_id(request);
				//String ss=ins_food.guest_id;
				System.out.println("guest food @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ hidvalue      "+guest);
				PreparedStatement p2 = NGM.conn.prepareStatement("select * from tr_guest_catering where guest_id=? and foodtime=? ");
				p2.setString(1,guest);
				p2.setString(2,item);
				System.out.println("catering       "+p2);
				i=0;
				ResultSet r2=p2.executeQuery();
				int temptot=0;
				while(r2.next())
				{
				i=i+1;
				
				String seq_id=r2.getString("seq_id");%>
					<tr>
					<td><%=i%></td>
					<td><%=r2.getString("item_details")%></td>	
					<td><%=r2.getString("unitprice")%>	</td>
					<td><%=r2.getString("no_o_unit")%></td>	
					<td><%=r2.getString("total")%>	</td>
					<td><button data-original-title="Delete" type="button" onclick="showcancel('<%=seq_id%>','<%=guest%>','<%=item%>')" data-placement="top" data-toggle="tooltip" class="btn btn-danger btn-flat btn-xs showTooltip"><span class="fa fa-trash-o"></span></button></td>
					<%total=r2.getString("total")!=null?total=r2.getString("total"):"";
					int temptot2=Integer.parseInt(total);
					System.out.println("TOTAL     "+total);
					
					
					temptot=temptot+temptot2;
					%>
					</tr>
					
				<%}	
				
				//close
				r2.close();
				p2.close();
				System.out.println("TOTALLLLLLLLLLL     "+total);
				if(total!="")
				{	
				System.out.println("guest food @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ hidvalue      "+guest);
				PreparedStatement p3 = NGM.conn.prepareStatement("select sum(total::Integer)as amount from tr_guest_catering where guest_id=?");
				p3.setString(1,guest);
				i=0;
				ResultSet r3=p3.executeQuery();
				while(r3.next())
				{
				i=i+1;
					amount=r3.getString("amount");	
									
				}
				//close
				r3.close();
				p3.close();
				%>
				
				
			
				<tr>
				 
				<td colspan="4" style="text-align:right!important"><strong>Total </strong></td>
				<td colspan="2"><strong><%=temptot%></strong></td>
				 
				</tr>
				<%} %>
				</table>
					</div>
					</div>
					
				<div class="panel-body">
				
				</div>
			
		
			</div>
		</div>
<div id="viewdata"></div>	 
	</form>
	
<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
	