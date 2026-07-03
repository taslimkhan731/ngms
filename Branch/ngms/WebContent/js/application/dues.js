
 

$(document).on('click', '.rad', function () {
	
	$('#radioselect input').on('change', function() {
		  // alert($('input[name=radioName]:checked', '#radioselect').val()); 
		
		var ccd= $('#radioselect input:checked').val();
		//alert(ccd);
		
		if (ccd=="CHEQUE")
			{
			
			$("#text_se").removeAttr('disabled','disabled');
			$('#text_se').attr('placeholder','Enter check Number');
		//	$('#text_se').val("").prop("placeholder","Enter Check Number");
			
			}
		
		if (ccd=="DD")
			{
			
			$("#text_se").removeAttr('disabled','disabled');
			$('#text_se').attr('placeholder','Enter DD Number');

			//$('#text_se').val("").prop("placeholder","Enter DD Number");
			}
		
		if (ccd=="CASH")
		{
			$("#text_se").attr('disabled','disabled');
		//$('#text_se').val("").prop("placeholder","");
		}
		/*if ($(ccd.val("CHEQUE")))
			{
			alert();
			$('#se').val("CHEQUE");
			$('#se').removeAttr('disabled','disabled');
			$('#se').attr('enabled','');
			}*/
		/*
		if ($('input[id=radioselect]:checked').val("CHEQUE"))
				{
					alert("CHEQUE");
				}
		else if ($('input[id=radioselect]').val("DD"))
		{
			alert("DD");
		}
		
		else if ($('input[id=radioselect]').val("CASH"))
		{
			alert("CASH");
		}*/
				
		});
	
	
});


//multiple checkbox value stored in one variable
$(document).on('click', '#chck', function () {
	var cx=	$('#chck:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});

//convert the rupees to word
var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];


function inWords(num) {
	 

    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only ' : '';
  
   return str;
	
}


/////////////////////////////////////////////////////////////////////////////////////////////////


function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_dues", arr_fieldname, arr_fieldvalue, 'header_div');
}
function duesList(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["list",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_dues",handle_duesList,arr_fieldname,arr_fieldvalue,'dues');	
	}
}

function handle_duesList()
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

function duesReport(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["duesreport",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_dues",handle_duesReport,arr_fieldname,arr_fieldvalue,'dues');	
	}
}

function handle_duesReport()
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

function duesBillReport(pageno)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["duesbillreport",getprocessid(),pageno,gh_id,sel_block];
		ajax_post_field_div("s_dues",handle_duesBillReport,arr_fieldname,arr_fieldvalue,'dues');	
	}
}

function handle_duesBillReport()
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

function search_dues(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('sel_id').value;
	var receipt_no = document.getElementById('receipt_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&receipt_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_dues(req_id,name,date)){
			if(!receipt_no==""){
				if(!isLEN(receipt_no,1,25)){
					 
					nic_alert("Invalid Length for Receipt No.","V");
					return false;
				}
				else if(!VAL_N(receipt_no)){
					 
					nic_alert("Invalid Receipt No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","receipt_no","page_no"];
		arr_fieldvalue=["searchDues",getprocessid(),gh_id,req_id,name,date,receipt_no,page_no];
		ajax_post_field_divH("s_dues",arr_fieldname,arr_fieldvalue,'duesdiv');	
	}}
}

function val_search_dues(req_id,name,date){

	if(!req_id==""){
		if(!isLEN(req_id,1,25)){
			 
			nic_alert("Invalid Length for Request ID.","V");
			return false;
		}
		else if(!VAL_ANHYP(req_id)){
			 
			nic_alert("Invalid Request ID.","V");
			return false;
		}
	}
	if(name!=""){
	if(!isLEN(name,1,50)){
		 
		nic_alert("Invalid Length for Name.","V");
		return false;
	}
	else if(!VAL_NAME(name)){
		 
		nic_alert("Invalid Name.","V");
		return false;
	}
	}
	if(date!=""){
	if(!isLEN(date,1,25)){
		 
		nic_alert("Invalid Length for Date.","V");
		return false;
	}
	else if(!VAL_NC(date)){ 
		 
		nic_alert("Invalid Date.","V");
		return false;
	}
	}
	
	return true;
}

//pay_dues(
function pay_dues(gh_id,due_id,bill_values,req_id)
{
	    var sel_block= document.getElementById('sel_block').value;
	   // var gh_id=document.getElementById('gh_id').value;
	    arr_fieldname=["task","process_id","bill_values","gh_id","sel_block","flag","due_id","req_id"];
		arr_fieldvalue=["printview",getprocessid(),bill_values,gh_id,sel_block,"DUES",due_id,req_id];
		ajax_post_field_div("s_receipt",handle_pay_dues,arr_fieldname,arr_fieldvalue,'duesdiv');
}
function handle_pay_dues(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function search_duesReport(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('sel_id').value;
	var receipt_no = document.getElementById('receipt_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&receipt_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_dues(req_id,name,date)){
			if(!receipt_no==""){
				if(!isLEN(receipt_no,1,25)){
					 
					nic_alert("Invalid Length for Receipt No.","V");
					return false;
				}
				else if(!VAL_N(receipt_no)){
					 
					nic_alert("Invalid Receipt No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","receipt_no","page_no"];
		arr_fieldvalue=["searchDuesReport",getprocessid(),gh_id,req_id,name,date,receipt_no,page_no];
		ajax_post_field_divH("s_dues",arr_fieldname,arr_fieldvalue,'duesdiv');	
	}}
}

function pdf_dues(task){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =[task,getprocessid()];	
	post_field("doc_handler", arr_fieldname,arr_fieldvalue,true);
}

function search_billReport(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('sel_id').value;
	var bill_no = document.getElementById('bill_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&bill_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_dues(req_id,name,date)){
			if(!bill_no==""){
				if(!isLEN(bill_no,1,25)){
					 
					nic_alert("Invalid Length for Bill No.","V");
					return false;
				}
				else if(!VAL_N(bill_no)){
					 
					nic_alert("Invalid Bill No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","bill_no","page_no"];
		arr_fieldvalue=["searchBillReport",getprocessid(),gh_id,req_id,name,date,bill_no,page_no];
		ajax_post_field_divH("s_dues",arr_fieldname,arr_fieldvalue,'duesdiv');	
	}}
}