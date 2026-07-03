<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Date" %>
<%@page import="allotment.*" %>
<%@page import="java.util.ArrayList" %>

<%@ include file="common_ajax.jsp"%>


<%String drawbox="";
m_statusallotment ins_m_allotment = new m_statusallotment(NGM);
common ins_common = new common(NGM);
	switch (task) {
	case 1:
	{	
		ArrayList <c_blockchart> block_list = new ArrayList<c_blockchart>();
		block_list = (ArrayList<c_blockchart>) request.getAttribute("blocklist");
		String req_id = (String)request.getAttribute("req_id");
	String reswlstatus = "";
	String tablename = (String)request.getAttribute("tablename");
	String type = (String)request.getAttribute("type");
	String res_id = (String)request.getAttribute("res_id");
	String from = (String)request.getAttribute("from");
	String to = (String)request.getAttribute("to");
	String gh_id = (String)request.getAttribute("gh_id");
	 int i=0; %>
  
  <br>
   
 <div class="main-tab col-sm-14 no-padding"> 
   <ul class="nav nav-tabs">
<%		
		while(i<block_list.size())
		{ 
			c_blockchart tempblock = (c_blockchart)block_list.get(i);
			String temp_blockid = tempblock.block_id;
			String temp_block_title = tempblock.block_title;
			System.out.println("hi i" +i);
			%>
			  <li><a href="#<%="BLOCK_"+temp_blockid %>" data-toggle="tab" data-target="#<%="BLOCK_"+temp_blockid %>"><i class="fa fa-bank"></i> <%=temp_block_title %> </a>		        </li>
		         
		
		<%  i++; } //end while %>

 </ul>
	 <div class="tab-content table-responsive">
	 
	 <%		int k=0;
		while(k<block_list.size())
		{ 
		
			c_blockchart tempblock = (c_blockchart)block_list.get(k);
			String temp_blockid = tempblock.block_id;
			String temp_block_title = tempblock.block_title;
			c_table_chart room_tablechart   = tempblock.room_tablechart;
			c_table_chart dorm_tablechart  =  tempblock.dorm_tablechart;
			
			
			%>

	 <%if(k==0) { %>
        <div class="tab-pane active" id="<%="BLOCK_"+temp_blockid%>">
        <% } else {%>
        <div class="tab-pane" id="<%="BLOCK_"+temp_blockid%>">
        <% }%>
        
            <div class="inner-tab">
                 <ul class="nav nav-tabs">
                	
                	<%if(k==0) { %>
                    <li class="active"><a href="#rooms1" data-target="#rooms1" data-toggle="tab">Rooms</a></li>
                      <% } else {%>
                     	<li><a href="#rooms1" data-target="#rooms1" data-toggle="tab">Rooms</a></li>
                       <% }%>
                    <li><a href="#Dormitory1" data-target="#Dormitory1" data-toggle="tab">Dormitory</a>
                    </li>
                </ul> 
                <div class="tab-content">
                
                	
                    <div class="tab-pane fade active in" id="rooms1">
                      
                    	
                    
                    <table class="table-bordered table-hover table-allotment header-fixed">
				  <thead class="theading"> 
				 <tr>
					 <th>Room No</th>
					<% //	ArrayList<String> date_list = room_tablechart.date_list;
					ArrayList<String> date_list = room_tablechart.date_list;//dorm_tablechart
					int kk=0;
					while(kk < date_list.size())
					{
					%> 					 
					 
					 <th><%=date_list.get(kk)%><span id="day"></span></th>
					 <%kk++; }  %>
					
				</tr>      
      
        </thead> 
       
			<tbody class="tbody">
			<% 	
					int kkk=0;
					ArrayList<String> room_list = room_tablechart.room_list;
				//ArrayList<String> room_list  = new ArrayList<String>(); 
				ArrayList<c_row_chart> row_chart_list   = room_tablechart.row_chart_list;
				//new ArrayList<c_row_chart>();
					
					
					while(kkk < room_list.size())
					{
					%> 
					
					 <tr>				 
					 <td class="roomno" ><%=room_list.get(kkk) %></td>
					
								<% 									
									c_row_chart temp_row_chart = row_chart_list.get(kkk);
									ArrayList<c_column_chart> col_list = temp_row_chart.col_list;
									System.out.println(" inside col list room chart list"+col_list.size());
									//new ArrayList<c_column_chart>();	
									int kkkkk=0;
									   	while(kkkkk< col_list.size())
									   	{
									   		c_column_chart tempcolchart = col_list.get(kkkkk);
									   		//drawbox = drawbox + ins_m_allotment.draw_room_box(temp_row_chart, res_id, tablename);
									//drawbox = ins_m_allotment.draw_room_box(tempcolchart, res_id, tablename,type);
									drawbox = ins_m_allotment.draw_room_box(tempcolchart, res_id, tablename,type,gh_id,temp_blockid);%>
									
									<%=drawbox %>
									<%
									//System.out.println("***********shaik********"); 
									
									//System.out.println(drawbox);
									//System.out.println("***********shaik************");
									
									kkkkk=kkkkk+1;
										}
							
					%>
					</tr>
					
					
					 <% kkk++; } //end of roomlist  %>
					 </tbody>
			
			</table>
				
  </div> 
  
  
  <div class="tab-pane" id="Dormitory1">
<table class="table-bordered table-hover table-allotment header-fixed table">
				  <thead class="theading"> 
				 <tr>
					 <th>Room No</th>
					<% //	ArrayList<String> date_list = room_tablechart.date_list;
					ArrayList<String> date_listdd = dorm_tablechart.date_list;//dorm_tablechart
					int kkdd=0;
					while(kkdd < date_listdd.size())
					{
					%> 					 
					 
					 <th><%=date_listdd.get(kkdd)%><span id="day"></span></th>
					 <%kkdd++; }  %>
					
				</tr>      
      
        </thead> 
       
			<tbody class="tbody">
			<% 	
					int kkkdd=0;
					ArrayList<String> room_listdd = dorm_tablechart.room_list;
				//ArrayList<String> room_list  = new ArrayList<String>(); 
				ArrayList<c_row_chart> row_chart_listdd   = dorm_tablechart.row_chart_list;
				//new ArrayList<c_row_chart>();
					
					
					while(kkkdd < room_listdd.size())
					{
					%> 
					
					 <tr>				 
					 <td class="roomno" ><%=room_listdd.get(kkkdd) %></td>
					
								<% 									
									c_row_chart temp_row_chart = row_chart_listdd.get(kkkdd);
									ArrayList<c_column_chart> col_list = temp_row_chart.col_list;
									System.out.println(" inside col list99999999999999999999999999999999 room chart list"+col_list.size());
									//new ArrayList<c_column_chart>();	
									int kkkkkdd=0;
									   	while(kkkkkdd< col_list.size())
									   	{
									   		c_column_chart tempcolchart = col_list.get(kkkkkdd);
									   		drawbox = ins_m_allotment.draw_room_box(tempcolchart, res_id, tablename,type,gh_id,temp_blockid);%>
									
									<%=drawbox %>
									<%
									
									kkkkkdd=kkkkkdd+1;
										}
							
					%>
					</tr>
					
					
					 <% kkkdd++; } //end of roomlist  %>
					 </tbody>
			
			</table>  </div>
				
	                    
	                    
	                </div>
	                     </div>
	                    
	                    
	                </div>
                    
        		<% k++; } //end of blocklist %>  
        		
				 <button type="button" class="btn btn-danger" onClick="statusallotment()"><span class="fa fa-times"></span> Close</button> 
             
        		
	</div>

	</div>

	</div>  
	
	
     
	<%
	
		} //end case 
		break;
	
	default:
%>Invalid Task;<%
	break;
	}
%>