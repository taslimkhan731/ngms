<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>

<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
	<title>Sub-merchant checkout page</title>
	<!-- For jquery  -->
	<script src="../plugins/jquery/jquery.min.js"></script>
	
<style>

	@keyframes blink {
	    0% {
	      opacity: .2;
	    }
	    20% {
	      opacity: 1;
	    }
	    100% {
	      opacity: .2;
	    }
	}
	
	.dots {
	    text-align: center;
	}
	
	.dots h2 span {
	    font-size: 200px;
	    animation-name: blink;
	    animation-duration: 1.4s;
	    animation-iteration-count: infinite;
	    animation-fill-mode: both;
	}
	
	.dots h2 span:nth-child(2) {
	    animation-delay: .2s;
	}
	
	.dots h2 span:nth-child(3) {
	    animation-delay: .4s;
	}
	
	.message {
	    text-align: center;
	    font-size: 25px;
	    font-variant: small-caps;
	    font-weight: bold;
	}
</style>
	
</head>
<body>
	<div class="dots">
        <h2><span>.</span><span>.</span><span>.</span></h2>
    </div>

    <div class="message">
        Please wait for a moment while we<br> process your payment.
    </div>
	<%
	 Map<String,String> parametersTID = new HashMap<String,String>();
     parametersTID.put("01", "262622");
     parametersTID.put("02", "262355");
     parametersTID.put("03", "259102");
     parametersTID.put("04", "262619");
     parametersTID.put("05", "262354");
     parametersTID.put("06", "262620");
     
     Map<String,String> parametersAccessCode = new HashMap<String,String>();
     parametersAccessCode.put("01", "AVLM92HF86BL95MLLB");
     parametersAccessCode.put("02", "AVNI92HF81BH68INHB");
     parametersAccessCode.put("03", "AVKB92HE24BQ20BKQB");
     parametersAccessCode.put("04", "AVHM92HF86BL92MHLB");
     parametersAccessCode.put("05", "AVLI92HF81BH61ILHB");
     parametersAccessCode.put("06", "AVJM92HF86BL93MJLB");
     
     Map<String,String> parametersWorkingKey = new HashMap<String,String>();
     parametersWorkingKey.put("01", "2A82884A3107F1E8DFA559095F9B134C");
     parametersWorkingKey.put("02", "55BDC4153DF19B4285AA9393E08BDBD5");
     parametersWorkingKey.put("03", "C52E419CFBE97155F923FD6FBDD53EE5");
     parametersWorkingKey.put("04", "32B1147B26DB16869C1A31CCC1321F13");
     parametersWorkingKey.put("05", "A57EB0989AB5CCACD1769A2EABEA1061");
     parametersWorkingKey.put("06", "6C2DDD3E97E3CAF0C100133634CD0FE7");
    
	 String accessCode= null;		//Put in the Access Code in quotes provided by CCAVENUES.
	 String workingKey = null;    //Put in the 32 Bit Working Key provided by CCAVENUES.  
     Map<String,String> parameters = new HashMap<String,String>();
	 String mchId = null;
	 if(session.getAttribute("blockId") != null){
		 mchId = parametersTID.get(session.getAttribute("blockId"));	
		 accessCode = parametersAccessCode.get(session.getAttribute("blockId"));
		 workingKey = parametersWorkingKey.get(session.getAttribute("blockId"));
		 session.setAttribute("workingKey", workingKey);
	 }
	 System.out.println("mCID "+mchId);
	 System.out.println("wKey "+workingKey);
	 System.out.println("aCode "+accessCode);
	 parameters.put("merchant_id",mchId);
	 System.out.println("order_id "+session.getAttribute("transactionId"));
	 parameters.put("order_id",session.getAttribute("transactionId").toString());
	 parameters.put("currency","INR");
	 parameters.put("amount",session.getAttribute("amountToPay").toString());
	 parameters.put("blockName",session.getAttribute("blockName").toString());
	 parameters.put("blockId",session.getAttribute("blockId").toString());
	 System.out.println("amount "+session.getAttribute("amountToPay"));
	 System.out.println("blockId "+session.getAttribute("blockId"));
	 System.out.println("blockName "+session.getAttribute("blockName"));
	 parameters.put("redirect_url","https://bhawan.homeodisha.gov.in/ngms/s_receipt?task=paymentStatus"); //https://ngms.wifi10x.com/s_receipt?task=paymentStatus
	 parameters.put("cancel_url","https://bhawan.homeodisha.gov.in/ngms/s_receipt?task=paymentStatus"); //http://203.193.144.93:8080/ngms
	 parameters.put("language","EN"); //http://localhost:8085/ngms //https://bhawan.homeodisha.gov.in/ngms/
	 parameters.put("process_id",request.getParameter("process_id").toString());
	 parameters.put("billing_city",session.getAttribute("cityId").toString());
	 System.out.println("billing_city "+session.getAttribute("cityId").toString());
	
	 String ccaRequest="", pname="", pvalue="";
	 Set<String> keys = parameters.keySet();
	 for(String key:keys)
	 {
	 	ccaRequest = ccaRequest + key + "=" + URLEncoder.encode(parameters.get(key),"UTF-8") + "&";
	 }
	 
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);  
	 
	%> 
	
	<form id="nonseamless" method="post" name="redirect" action="https://secure.ccavenue.com/transaction.do?command=initiateTransaction"> 
		<input type="hidden" id="encRequest" name="encRequest" value="<%= encRequest %>">
		<input type="hidden" name="access_code" id="access_code" value="<%= accessCode %>">
		<script language='javascript'>document.redirect.submit();</script>
	</form>
 </body> 
</html>
