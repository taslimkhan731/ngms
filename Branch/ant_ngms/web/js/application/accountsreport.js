function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_accountsreport", arr_fieldname, arr_fieldvalue, 'header_div');
}

//Accounts pdf
function accountspdf(dateFrom,dateTo,name,receipt_id,ad_rec_id,gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["task","process_id","dateFrom","dateTo","name","receipt_id","ad_rec_id","gh_id","sel_block"];
	arr_fieldvalue =["accountspdf",getprocessid(),dateFrom,dateTo,name,receipt_id,ad_rec_id,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function accountsreport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["accountssearch",getprocessid(),gh_id,sel_block];
	$('#popup').show();
	ajax_post_field_div("s_accountsreport",handleaccountsreport, arr_fieldname,arr_fieldvalue,'accountsReport');
	}
}
function handleaccountsreport(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;
			 $('#popup').hide();
			}else{
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

function search_accounts_report(){
	var dateFrom  = document.getElementById('dateFrom').value;
	var dateTo  = document.getElementById('dateTo').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var name = document.getElementById('name').value;
	var receipt_id = document.getElementById('receipt_id').value;
	var adv_id = document.getElementById('adv_id').value;
	if(dateFrom==""&&dateTo==""&&name==""&&receipt_id==""&&adv_id==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(VAL_search_accounts_report(dateFrom,dateTo,name,receipt_id,adv_id))
		{
			arr_fieldname=["task","process_id","dateFrom","dateTo","name","receipt_id","adv_id","gh_id","sel_block"];
			arr_fieldvalue=["showAccountsReport",getprocessid(),dateFrom,dateTo,name,receipt_id,adv_id,gh_id,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_accountsreport",handlesearch_accounts_report, arr_fieldname,arr_fieldvalue,'accountsReportShow');
		}
	}
}
function handlesearch_accounts_report(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		 $('#popup').hide();
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
			 $('#popup').hide();
		}
	}
}

function VAL_search_accounts_report(dateFrom,dateTo,name,receipt_id,adv_id){
	if((dateFrom!="")){
		if((dateTo=="")){
			nic_alert("Enter Date To","V");
			return false;
		}
	}
	else if((dateTo!="")){
		if((dateFrom=="")){
			nic_alert("Enter Date From","V");
			return false;
		}
	}
	if(dateTo!=""){

		if(!isLEN(dateFrom,1,25)){
			nic_alert("Invalid Length for to date.","V");
			return false;
		}
		else if(!VAL_NC(dateFrom)){
			nic_alert("Invalid Date.","V");
			return false;
		}else if(!dateCompLTodays2(dateFrom)){
			nic_alert("Future Date Cannot be Selected.","V");
			return false;
		}
		if(!isLEN(dateTo,1,25)){
			nic_alert("Invalid Length for to date.","V");
			return false;
		}
		else if(!VAL_NC(dateTo)){
			nic_alert("Invalid Date.","V");
			return false;
		}
		else if(!dateCompLTodays2(dateTo)){
			nic_alert("Future Date Cannot be Selected.","V");
			return false;
		}
		else if(dateCompTwo(dateTo,dateFrom))
		{
			nic_alert("From date should be less than To date","V");
			return false;
		}
	}
	
	if(name!=""){
		if(!isLEN(name,1,50)){
		 
			nic_alert("Invalid Length for Name.","V");
			return false;
		}
		else if(!VAL_AN(name)){ 
		 
			nic_alert("Invalid Name.","V");
			return false;
		}
	}
	
	if(receipt_id!=""){
		if(!isLEN(receipt_id,1,25)){
		 
			nic_alert("Invalid Length for Receipt Id.","V");
			return false;
		}
		else if(!VAL_N(receipt_id)){
		 
			nic_alert("Invalid Receipt Id.","V");
			return false;
		}
	}
	
	if(adv_id!=""){
		if(!isLEN(adv_id,1,25)){
		 
			nic_alert("Invalid Length for Advance Receipt Id.","V");
			return false;
		}
		else if(!VAL_N(adv_id)){
		 
			nic_alert("Invalid Advance Receipt Id.","V");
			return false;
		}
	}
	
	return true;
}

