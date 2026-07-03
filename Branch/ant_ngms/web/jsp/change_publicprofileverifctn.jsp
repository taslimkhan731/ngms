<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="common_ajax.jsp"%>

<%
	try {
		switch (task) {
	
		case 1:
			System.out.println("public reg inside case 1");
			
			String getcode = (String) request.getAttribute("getcode");
			String refno = (String) request.getAttribute("refno");
			System.out.println("refno "+refno);
		%>
		<div id="verifications" class="panel-footer">
		 <div class="form-group" id="form-change">
		
                    <div class="col-sm-7">Please Enter the verification code received for <%=refno %>  <div></div></div>
                  	<div class="col-sm-3"><input type="text" id="fverfcode" size="6" maxlength="6" name="fverfcode" value="" placeholder=""/></div>
                    
                  
                           
                       <%
                       System.out.println("refno middle "+refno);

                       %>
                             &nbsp;
                    <div class=""><br>
                     <input type="button" class="btn btn-primary" onclick="isDetailsValidChange()" value="Submit Application" />    
                      <input type="button" class="btn btn-danger" onclick="cancelchange()" value="Cancel" />
 							  <%
 							  if(getcode.equals("Y")){
 							  %>
 							     <input type="button" class="btn btn-primary" onclick="getAnotherCodeChange()" value="Resend code" />    
 							  <%
 							  }
 							  %>              
                    </div> 
                    
<%--                                         <input type="hidden" value="<%=%>" name="username" id="username">
 --%>                    <input type="hidden" value="<%=refno%>" name="refno" id="refno">
                    <input type="hidden" value="<%=process_id%>" name="process_id" id="process_id">
			
			
	</div>
		
	</div>
		
	
		
		<%	
			
			break;
			
		default:
%>
Invalid Task
<%
	break;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

