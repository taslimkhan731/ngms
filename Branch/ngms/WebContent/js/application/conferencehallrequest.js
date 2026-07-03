$(document).on('click', '.rad', function () {
	$('#radioselect input').on('change', function() {
		  // alert($('input[name=radioName]:checked', '#radioselect').val()); 
		
		var ccd= $('#radioselect input:checked').val();
		//alert(ccd);
		
		if (ccd=="POS"){
			$("#text_se").removeAttr('disabled','disabled');
			$('#text_se').attr('placeholder','Enter Transaction Number');
		//	$('#text_se').val("").prop("placeholder","Enter Check Number");
			
		}
//		if (ccd=="ONLINE"){
//			$("#text_se").attr('disabled','disabled');
//		}
		
		if (ccd=="CASH"){
			$("#text_se").attr('disabled','disabled');
		}
		});
	
	
});

function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_conferencehallrequest", arr_fieldname, arr_fieldvalue, 'header_div');
}

//click Add ghdetails submenu
function addconferencehallrequest(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["add",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handle_add,arr_fieldname,arr_fieldvalue,'conference');	
	}
}

function cancelconferencehallrequest(pageno,gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","page_no","gh_id"];
		arr_fieldvalue =["add",getprocessid(),pageno,gh_id];
		//ajax_post_field_div("s_conferencehallrequest",handle_add,arr_fieldname,arr_fieldvalue,'conference');	
		ajax_post_field_divH("s_conferencehallrequest",arr_fieldname,arr_fieldvalue,'conference');	
	}
}

var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
var b = ['', '', 'Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety'];


function inWords(num) {
	 
    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'Only ' : '';
  
   return str;
	
}

function handle_add()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function add_conferencerequest()
{
	if(VAL_savedetails()){
		var gh_id = document.getElementById('sel_id').value;
		var sel_block = document.getElementById('sel_block').value;
		if(gh_id==0){
			nic_alert("Please Select the  City","V");
		}
		else if(sel_block==0){
			nic_alert("Please Select the guest house","V");
		}else{ 
			var ins_form= document.getElementById('form-general-conf');
			arr_fieldname=["task","process_id","gh_id","sel_block"];
			arr_fieldvalue=["save",getprocessid(),gh_id,sel_block];
			ajax_post_form_field(ins_form,"s_conferencehallrequest",handle_add_confreq,arr_fieldname,arr_fieldvalue);
		}
	}
}
function handle_add_confreq()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Saved Successfully.","N");
			addconferencehallrequest('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function VAL_savedetails(){
	var sel_conf_cate  = document.getElementById('sel_conf_cate').value;
	if(sel_conf_cate=='0'){
		nic_alert("Please Select the Category","N");
		return false;
	}
	var from  = document.getElementById('from').value;
	var to  = document.getElementById('to').value;
	if(from==''){
		nic_alert("Please Select the From Date","N");
		return false;
	}
	if(to==''){
		nic_alert("Please Select the To Date","N");
		return false;
	}
	var conf_use_type = document.getElementById('conf_use_type').value;
	if(conf_use_type=='0'){
		nic_alert("Please Select the Type","N");
		return false;
	}
	var additional  = document.getElementById('additional').value;
	if(!VAL_REMARK(additional)){
		nic_alert("Please Enter valid additional requirements","N");
		return false;
	}
	var name  = document.getElementById('name').value;	
	if(name==''){
		nic_alert("Please Enter name","N");
		return false;
	}
	else if(!VAL_NAME(name)){
		nic_alert("Please Enter valid name","N");
		return false;
	}
	var address  = document.getElementById('address').value;	
	if(address==''){
		nic_alert("Please Enter address","N");
		return false;
	}
	else if(!VAL_REMARK(address)){
		nic_alert("Please Enter valid address","N");
		return false;
	}
	else
		return true;
	}

function cancelcloseconferencehallrequest(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["list",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handle_cancelclose,arr_fieldname,arr_fieldvalue,'conference');	
	}
}
function handle_cancelclose()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function cancelconference(conf_req_id)
{
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		if(confirm("Do you want to cancel the request?")){
			arr_fieldname=["task","process_id","conf_req_id","gh_id"];
			arr_fieldvalue=["cancel",getprocessid(),conf_req_id,gh_id];
			ajax_post_field("s_conferencehallrequest",handlecancelconference,arr_fieldname,arr_fieldvalue);	
		}
	}
}
function handlecancelconference()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Cancelled Successfully.","N");
			cancelcloseconferencehallrequest('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function closeconference(conf_req_id,gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname=["task","process_id","conf_req_id","gh_id","sel_block"];
		arr_fieldvalue=["close",getprocessid(),conf_req_id,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handlecloseconference,arr_fieldname,arr_fieldvalue,'conference');
	}
}

function handlecloseconference()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function cancel_conf(){
	close_nicpopup();
}

function bill_conferencerequest(conf_req_id,gh_id)
{
	charge  = document.getElementById('charge').value;
	sel_block  = document.getElementById('sel_block').value;
	additionalcharge  = document.getElementById('additional_charge').value;
	
		arr_fieldname=["task","process_id","conf_req_id","gh_id","charge","additionalcharge","sel_block"];
		arr_fieldvalue=["bill",getprocessid(),conf_req_id,gh_id,charge,additionalcharge,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handlebillconference,arr_fieldname,arr_fieldvalue,'conference');
		//ajax_post_form_field_div(ins_form,"s_billing",handle_make_bill_guestlist,arr_fieldname,arr_fieldvalue,'billing');
}

function handlebillconference()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
		    close_nicpopup();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function paymentconferencehallrequest(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["paymentlist",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handle_paymentlist,arr_fieldname,arr_fieldvalue,'conference');	
	}
}

function handle_paymentlist()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function receiptconferencehallrequest(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["billlist",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handle_receipt,arr_fieldname,arr_fieldvalue,'conference');	
	}
}

function handle_receipt()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function billconferenceview(conf_req_id,gh_id)
{
	    var sel_block = document.getElementById('sel_block').value;
		arr_fieldname=["task","process_id","conf_req_id","gh_id","sel_block"];
		arr_fieldvalue=["billview",getprocessid(),conf_req_id,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handlebillview,arr_fieldname,arr_fieldvalue,'conference');
}

function handlebillview()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//document.getElementById(resultDiv).innerHTML=results;
			/*close_nicpopup();*/
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

//receiptconference(

function receiptconference(conf_req_id,gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname=["task","process_id","conf_req_id","gh_id","sel_block"];
		arr_fieldvalue=["receipt",getprocessid(),conf_req_id,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handlereceiptconference,arr_fieldname,arr_fieldvalue,'conference');
		$('#popup').show();
	}
}

function handlereceiptconference()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			//show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;
			show_nicpopup(results);
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function receipt_conferencerequest(conf_req_id,gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
	if(VAL_submit()){
		arr_fieldname=["task","process_id","conf_req_id","gh_id","sel_block"];
		arr_fieldvalue=["submitreceipt",getprocessid(),conf_req_id,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handlerecconference,arr_fieldname,arr_fieldvalue,'conference');
	}
}

function handlerecconference()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
		    close_nicpopup();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

//receiptconferencehalllist
function receiptconferencehalllist(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","page_no","gh_id"];
		arr_fieldvalue =["receiptlist",getprocessid(),pageno,gh_id];
		ajax_post_field_div("s_conferencehallrequest",handle_receiptlist,arr_fieldname,arr_fieldvalue,'conference');	
	}
}

function handle_receiptlist()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function receiptconferenceview(conf_req_id,gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
		arr_fieldname=["task","process_id","conf_req_id","gh_id","sel_block"];
		arr_fieldvalue=["receiptview",getprocessid(),conf_req_id,gh_id,sel_block];
		ajax_post_field_div("s_conferencehallrequest",handlereceiptview,arr_fieldname,arr_fieldvalue,'conference');
}

function handlereceiptview()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//document.getElementById(resultDiv).innerHTML=results;
			
			show_nicpopup(results);
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function val_ghname(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to guesthouse name.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid guesthouse name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the guesthouse name","V");
		return false;
	}
	return true;
}
function val_street(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to street name.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid street name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the street name","V");
		return false;
	}
	return true;
}
function val_city(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to city name.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid city name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the city name","V");
		return false;
	}
	return true;
}
function val_district(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to district name.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid district name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the district name","V");
		return false;
	}
	return true;
}
function val_state(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to state name.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid state name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the state name","V");
		return false;
	}
	return true;
}
function val_pincode(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to pincode .","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid pincode.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the pincode","V");
		return false;
	}
	return true;
}

function pdf_bill_conference(gh_id,conf_req_id)
{
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["task","process_id","conf_req_id","gh_id","sel_block"];
	arr_fieldvalue =['billprintpdfconf',getprocessid(),conf_req_id,gh_id,sel_block];	
	post_field("doc_handler", arr_fieldname,arr_fieldvalue,true);

}

function print_pdf_conf(gh_id,rec_id)
{
	var sel_block = document.getElementById('sel_block').value;
	//var ins_form= document.getElementById('form-general');
	arr_fieldname =["task","process_id","rec_id","gh_id","sel_block"];
	arr_fieldvalue =['printpdfconf',getprocessid(),rec_id,gh_id,sel_block];	
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function VAL_submit(){
	var ss = $("#se:checked").val();
//	var ss= $('.rad:checked').val();
	if(ss=='POS'){
	 	
	var tt = document.getElementById("text_se").value;
	if(tt==""){
		nic_alert("plese enter the  "+ss+"  number.","N");
		return false;
	} 
	if(!VAL_AN(tt))
	{
		nic_alert("Only Numbers and characters are allowed","N");
		return false;
	} 
	 
}
	return true;
	
}