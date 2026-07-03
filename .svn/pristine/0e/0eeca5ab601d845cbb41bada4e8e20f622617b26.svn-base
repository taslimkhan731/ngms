

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


function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_misc_billing", arr_fieldname, arr_fieldvalue, 'header_div');
}

function miscbilling(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["miscbilling",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_misc_billing",handlemiscbilling, arr_fieldname,arr_fieldvalue,'list');
	}
}

function handlemiscbilling(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
		}
		else{
			nic_alert(results.substr(8),"N");
		}
	}
}

//miscbillingList
function miscbillingList(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["miscbillinglist",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_misc_billing",handlemiscbilling, arr_fieldname,arr_fieldvalue,'list');
	}
}

function addmiscbilling(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
//	var roll_misc  = document.getElementById('roll_misc').value;
	var misc  = document.getElementById('sel_misc').value;
	var dcb_type  = document.getElementById('sel_dcb_type').value;
	var misc_amount  = document.getElementById('misc_amount').value;
	if(VAL_addmiscbilling())
	{
		arr_fieldname=["task","process_id","gh_id","sel_block","misc","dcb_type","misc_amount"];
		arr_fieldvalue=["addmiscbilling",getprocessid(),gh_id,sel_block,misc,dcb_type,misc_amount];
		ajax_post_field_div("s_misc_billing",handleaddmiscbilling, arr_fieldname,arr_fieldvalue,'listlist');
	}
}
function handleaddmiscbilling(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Bill Submitted Successfully.","N");
			miscbilling();
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function VAL_addmiscbilling(){
//	var roll_misc  = document.getElementById('roll_misc').value;
	var misc  = document.getElementById('sel_misc').value;
	var dcb_type  = document.getElementById('sel_dcb_type').value;
	var misc_amount  = document.getElementById('misc_amount').value;
	/*if(!val_rollnumber(roll_misc)){
		return false;
	}*/
	if(misc=="0"){
		nic_alert("Please Select Miscellaneous Bill Type","N");
		return false;
	}
	else if(dcb_type=="0"){
		nic_alert("Please Select DCB Type","N");
		return false;
	}
	else if(!val_charge(misc_amount)){
		return false;
	}
		return true;
}

function billmiscview(misc_bill_id,gh_id,sel_block)
{
		arr_fieldname=["task","process_id","misc_bill_id","gh_id","sel_block"];
		arr_fieldvalue=["miscbillview",getprocessid(),misc_bill_id,gh_id,sel_block];
		ajax_post_field_div("s_misc_billing",handlebillmiscview,arr_fieldname,arr_fieldvalue,'list');
}

function handlebillmiscview()
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

function pdf_bill_misc(misc_bill_id,gh_id,block)
{
	arr_fieldname =["task","process_id","misc_bill_id","gh_id","block"];
	arr_fieldvalue =['billprintpdfmisc',getprocessid(),misc_bill_id,gh_id,block];	
	post_field("doc_handler", arr_fieldname,arr_fieldvalue,true);

}

function receiptmisc(misc_bill_id,gh_id,sel_block)
{
	
		arr_fieldname=["task","process_id","misc_bill_id","gh_id","sel_block"];
		arr_fieldvalue=["receipt",getprocessid(),misc_bill_id,gh_id,sel_block];
		ajax_post_field_div("s_misc_billing",handlereceiptconference,arr_fieldname,arr_fieldvalue,'list');
		$('#popup').show();
	
}

function handlereceiptconference()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			show_nicpopup(results);
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function receipt_misc_submit(misc_bill_id,gh_id,amount)
{
	var sel_block  = document.getElementById('sel_block').value;
	
		arr_fieldname=["task","process_id","misc_bill_id","gh_id","amount","sel_block"];
		arr_fieldvalue=["submitreceipt",getprocessid(),misc_bill_id,gh_id,amount,sel_block];
		ajax_post_field_div("s_misc_billing",handlereceipt_misc_submit,arr_fieldname,arr_fieldvalue,'list');
}

function handlereceipt_misc_submit()
{
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Receipt Submitted Successfully.","N");
			close_nicpopup();
			miscbillingList();
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function receiptmiscview(misc_bill_id,gh_id,sel_block)
{
		arr_fieldname=["task","process_id","misc_bill_id","gh_id","sel_block"];
		arr_fieldvalue=["miscreceiptview",getprocessid(),misc_bill_id,gh_id,sel_block];
		ajax_post_field_div("s_misc_billing",handlebillmiscview,arr_fieldname,arr_fieldvalue,'list');
}

function handlebillmiscview()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function print_pdf_misc(misc_bill_id,gh_id,sel_block)
{
	//var ins_form= document.getElementById('form-general');
	arr_fieldname =["task","process_id","misc_bill_id","gh_id","sel_block"];
	arr_fieldvalue =['printpdfmisc',getprocessid(),misc_bill_id,gh_id,sel_block];	
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function cancelmisc(misc_bill_id,gh_id)
{
	/*charge  = document.getElementById('charge').value;
	additionalcharge  = document.getElementById('additional_charge').value;
	alert('charge '+charge);*/
	if(confirm("Do you want to cancel?")){
		arr_fieldname=["task","process_id","misc_bill_id","gh_id"];
		arr_fieldvalue=["cancelmisc",getprocessid(),misc_bill_id,gh_id];
		ajax_post_field_div("s_misc_billing",handlereceipt_misc_submit_cancel,arr_fieldname,arr_fieldvalue,'list');
	}
}

function handlereceipt_misc_submit_cancel()
{
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Cancelled Successfully.","N");
			miscbillingList();
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}