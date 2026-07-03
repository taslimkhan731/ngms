<%@ page import="java.io.*,java.util.*"%>

<html>
 <head> 
 <title>Page Redirection</title>
</head> 
<body> 


 <h1 align="center">Page Redirection</h1>
<%
 	String site =
 	new String("login");
 	response.setStatus(response.SC_MOVED_TEMPORARILY);
 	response.setHeader("Location", site);
 %>

</body> </html>

