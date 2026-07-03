<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <% String  task =(String) request.getAttribute("task");
  String  process_id =(String) request.getAttribute("process_id");
   %>
<form name="myRedirectForm" action="s_document" method="post">
    <input name="process_id" type="hidden" value="<%=process_id %>" />
    <input name="task" type="hidden" value="<%=task %>" />
    <noscript>
        <input type="submit" value="Click here to continue" />
    </noscript>
</form>
<script type="text/javascript">

document.myRedirectForm.submit();
/*    $(document).ready(function() {
    	alert('d');
        document.myRedirectForm.submit();
    });
*/
</script>


</body>
</html>