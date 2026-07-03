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
System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
	common con = new common(NGM);
	utility ngm_utility = new utility();
	String user_id = (String) request.getAttribute("user_id");
	String username="",password="",status="",email_id="",mob="",role_code="",aaa="",gh_id="",gh_name="",gh_array="",role_array="";
	//String status="",req_id="",gh_id="",vc_id="",lastupdate="",block_id="",created_date="",processid="";
	switch (task) {
		case 1:
			%>
			<div class="content" id="reset">
			<form name="form-general" method="post" id="form-general">	
			<div class="row">
			<div class="col-sm-4 center">
			<div class="panel panel-primary">
			<div class="panel-heading">
			<div class="panel-title"><i class="fa fa-file-text-o"></i> Reset Password</div>
			</div>
			<div class="panel-body">
			<%--current list --%>
			 <div id="addgh">
<%PreparedStatement ps = NGM.conn.prepareStatement(" select * from(select *  from m_user_details where userid=?)a left join (select * from uservsrole)b on a.userid=b.userid");
			ps.setString(1, user_id);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				user_id=rs.getString("userid");
				username=rs.getString("username");
				password=rs.getString("password");
				status=rs.getString("status")!=null?rs.getString("status"):"";
				email_id=rs.getString("email_id")!=null?rs.getString("email_id"):"";
				mob=rs.getString("mob")!=null?rs.getString("mob"):"";
				role_code=rs.getString("role_code");
			}
			//close
			rs.close();
			ps.close();
			%>
			 <input type=hidden name="u" id="u" value="<%=username%>">
			  <input type=hidden name="s" id="s" value="<%=status%>">
			   <input type=hidden name="e" id=e value="<%=email_id%>">
			    <input type=hidden name="m" id="m" value="<%=mob%>">
	
			<table class="table table-hover table-bordered" id="table_list">
					<thead>
					
					<tr>
						<th>User Id</th>
						<td><input type="text" name="a" id="a" maxlength="10" value="<%=user_id%>" disabled></td>
					</tr>
					<tr>
						<th>Password</th>
						<td><input class="showTooltip"  name="c" id="c" type="password" autocomplete="off" data-html="true" data-toggle="tooltip"  data-placement="left" title="Length must be within 8 to 32 characters ,<br>Any English lowercase and uppercase (a-z and A-Z) characters,Any numbers between 0-9,<br> Any special characters from the bracket [! @ # $ ^ * _ ~]"></td>
                     </tr>
                     <tr>
                        <th>Confirm Password</th>
                      	<td><input   name="c1" id="c1" type="password" autocomplete="off"> </td>
                    </tr>
				
								
					<tbody>
					<tr>
					<tr>
					
					<td colspan="2" style="text-align: center;"> <button type="button" class="btn btn-primary" onClick="resetpassword('<%=user_id%>')" > Reset</button>
					<input type="button" class="btn btn-default"  value="Cancel" onClick="cancel_creation()"/></td>
					</tr>
			</table>
			
		
</div>
</div>
		<%-- current list --%>	
			
			</div>
		</div>
	</div>

</form>

<!-- <input type=hidden name="req_id" id="req_id" value="">
 -->

	</div>	

<%
	break;
	default:
%>Invalid Task;<%
	break;
	}
%>
