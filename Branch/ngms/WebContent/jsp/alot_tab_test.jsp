<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.*"%>
<%@page import="common.utility"%>
<%@page import="db.*"%>
<%@page import="model.*"%>
<%@page import="template.*"%>
<%@page import=" org.joda.time.DateTime"%>;
 <%@page import="java.util.List" %>
 <%@page import="java.util.Date" %>
<%@ include file="common_ajax.jsp"%>

<%

	switch (task) {
	case 0:
	
		%>

<head>
<link href="assets/main.css" rel="stylesheet" type="text/css" />
<link href="assets/style.css" rel="stylesheet" type="text/css" />
<link rel="StyleSheet" href="assets/font-awesome/css/font-awesome.css" type="text/css">
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
</head>

<body class="skin-nic">

<div claas="maindiv">

<div class="allot-contents">

<h3> Allottment </h3>

<div class="allot-details">
Full details
</div> <br>


<span class="btn btn-xs btn-warning btn-flat" id="r">R</span> Selected Room
<span class="btn btn-xs btn-info" id="r">R</span> Not Selected 
<span class="btn btn-xs btn-danger" id="r">R </span> Already Selected 

<br><br>

<div class="main-tab">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#Block1"><i class="fa fa-bank"></i> Block1 </a>
        </li>
        <li><a href="#Block2"><i class="fa fa-bank"></i> Block 2</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane fade active in" id="Block1">
            <div class="inner-tab">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#rooms1">Rooms</a>
                    </li>
                    <li><a href="#Doormetory1">Doormetory</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="rooms1">
                     
					 
					 
					 
					 
					 
		<table class="table-bordered table-hover table-allotment">
				  <thead> 
				 <tr>
					 <th>Room No</th>
					 <th>19-11-2014<span id="day">Monday</span></th>
					 <th>20-11-2014<span id="day">Tuesday</span></th>    
					 <th>21-11-2014<span id="day">Wednesday</span></th>   
					 <th>22-11-2014<span id="day">Thursday</span></th>    
					 <th>23-11-2014<span id="day">Friday</span></th>    
					 <th>24-11-2014<span id="day">Saturday</span></th>
					<th>25-11-2014<span id="day">Sunday</span></th>
					<th>26-11-2014<span id="day">Monday</span></th>
					 <th>27-11-2014<span id="day">Tuesday</span></th>   
					<th>28-11-2014<span id="day">Wednesday</span></th>
				</tr>      
      
        </thead> 

			
			
			<tr>
			
				<th class="roomno">101</th>

				<td>
					<table>
					  <tr>
					  <td colspan="2">
					  <span  class="selectedbox btn-block" >R</span>				  
					</td>
					</tr> 						
					</table>
				</td>
	 

				<td>
					<table id="">					 
					<tr><td> <span class="notselected" id="aa">B1</span></td>
					<td><span class="notselected"> B2</span>
					</td></tr>
					</table>
				</td>
	 
		 
		 
				<td>
					<table id="">			 
					<tr><td> <span class="selectedbox alreadyselected" id="aa">R</span></td>
					</tr>
					</table>
				</td>
		
			
			<td>
					<table id=""> 			 
					<tr><td> <span class="alreadyselected" id="aa">BL</span></td>
					<td><span class="alreadyselected"> BL</span>
					</td></tr>
					</table>
				</td>
	 
		 
				<td>
					<table id=""> 			 
					<tr><td> <span class="notselected" id="aa">B1</span></td>
					<td><span class="notselected"> B2</span></td>
					<td><span class="notselected"> B3</span></td>
					<td><span class="notselected"> B4</span></td>
					<td><span class="notselected"> B5</span></td>
					</tr>
					</table>
				</td>
			
				<td>
					<table id=""> 			 
					<tr><td colspan="4"> <span class="notselected btn-block" id="aa">R</span></td>
					</tr>
					<tr>
					<td><span class="notselected"> B1</span></td>
					<td><span class="notselected"> B2</span></td>
					<td><span class="notselected"> B3</span></td>
					<td><span class="notselected"> B4</span></td>
					</tr>
					</table>
				</td>
			 
				<td>
					<table id=""> 			 
					<tr><td colspan="4"> <span class="notselected btn-block" id="aa">R</span></td>
					</tr>
					<tr>
					<td><span class="notselected"> B1</span></td>
					<td><span class="notselected"> B2</span></td>
					<td><span class="notselected"> B3</span></td>
					<td><span class="notselected"> B4</span></td>
					</tr>
					</table>
				 </td>
			  
				<td>
					<table id=""> 			 
					<tr><td colspan="4"> <span class="notselected btn-block" id="aa">R</span></td>
					</tr>
					<tr>
					<td><span class="notselected"> B1</span></td>
					<td><span class="notselected"> B2</span></td>
					<td><span class="notselected"> B3</span></td>
					<td><span class="notselected"> B4</span></td>
					</tr>
					</table>
				</td>
			  
				<td>
					<table id=""> 			 
					<tr><td colspan="4"> <span class="notselected btn-block" id="aa">R</span></td>
					</tr>
					<tr>
					<td><span class="notselected"> B1</span></td>
					<td><span class="notselected"> B2</span></td>
					<td><span class="notselected"> B3</span></td>
					<td><span class="notselected"> B4</span></td>
					</tr>
					</table>
				</td>
			  
				<td>
					<table id=""> 			 
					<tr>
					<td colspan="4"> <span class="notselected btn-block" id="aa">R</span></td>
					</tr>
					<tr>
					<td><span class="notselected"> B1</span></td>
					<td><span class="notselected"> B2</span></td>
					<td><span class="notselected"> B3</span></td>
					<td><span class="notselected"> B4</span></td>
					</tr>
					</table>
				</td>

			</tr>

		</table>
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
                    </div>
                    <div class="tab-pane fade" id="Doormetory1">
                        <p>Block 1 Doormetory</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="Block2">
            <div class="inner-tab">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#Room2">Block 2.1</a>
                    </li>
                    <li><a href="#Doormetory2">Doormetory 2.1</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="Room2">
                        <p>Rooms in Block 2</p>
                    </div>
                    <div class="tab-pane fade" id="Doormetory2">
                        <p>Doormetory in Block 2</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>

</div>

  <script src="assets/jquery.js"></script>
        <script src="assets/bootstrap.min.js" type="text/javascript"></script>
		
		<script>
		$("ul.nav-tabs a").click(function (e) {
  e.preventDefault();  
    $(this).tab('show');
});

		</script>
		
</body>
<% 
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>