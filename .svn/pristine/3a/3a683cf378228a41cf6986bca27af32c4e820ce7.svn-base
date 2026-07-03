function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_sms_allotment", arr_fieldname, arr_fieldvalue, 'header_div');
}
//list_dcb(
function sms_list(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
	arr_fieldname =["task","process_id","gh_id","page_no"];
	arr_fieldvalue =['listsms',getprocessid(),gh_id,page_no];	
	$('#popup').show();
	ajax_post_field_div("s_sms_allotment",handle_sms_list,arr_fieldname,arr_fieldvalue,'sms_allotment_emptyhome');
	}
}

function handle_sms_list()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

function search_sms(page_no){

	var req_id =  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&date==""&&name==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_sms(req_id,date,name)){
		arr_fieldname=["task","process_id","gh_id","req_id","date","name","page_no"];
		arr_fieldvalue=["searchSMS",getprocessid(),gh_id,req_id,date,name,page_no];
		$('#popup').show();
		ajax_post_field_divH("s_sms_allotment",arr_fieldname,arr_fieldvalue,'SMSShowList');	
	}}
}



function val_search_sms(req_id,date,name){

	if(!req_id==""){
		if(!isLEN(req_id,1,25)){
			 
			nic_alert("Invalid Length for Request Id.","V");
			return false;
		}
		else if(!VAL_ANHYP(req_id)){
			 
			nic_alert("Invalid Request Id.","V");
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
	
	if(!name==""){
		if(!isLEN(name,1,50)){
			nic_alert("Invalid Length for Name","V");
			return false;
		}
		else if(!val_name(name)){
			 
			nic_alert("Invalid Name.","V");
			return false;
		}
	}
	
	return true;
}


function send_sms(page_no)
{
	if($("#sms_id").is(':checked'))
	{
	if(confirm("Do you want to send the selected items?")){
        	var gh_id = document.getElementById('gh_id').value;
    		var ins_form= document.getElementById('form-sms-list');
    		arr_fieldname =["task","process_id","gh_id","page_no"];
    		arr_fieldvalue =["submitSMS",getprocessid(),gh_id,page_no];
    		$('#popup').show();
    		ajax_post_form_field(ins_form,"s_sms_allotment",handle_send_sms,arr_fieldname,arr_fieldvalue);	
	}}else
	{
		nic_alert("Please Select an Item","N");
		}
}
function handle_send_sms()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			nic_alert("Message Sent","N");
			sms_list('1');
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function cancel_sms(page_no)
{
	if($("#sms_id").is(':checked'))
	{
	if(confirm("Do you want to cancel the selected items?")){
        	var gh_id = document.getElementById('gh_id').value;
    		var ins_form= document.getElementById('form-sms-list');
    		arr_fieldname =["task","process_id","gh_id","page_no"];
    		arr_fieldvalue =["cancelSMS",getprocessid(),gh_id,page_no];
    		$('#popup').show();
    		ajax_post_form_field(ins_form,"s_sms_allotment",handle_cancel_sms,arr_fieldname,arr_fieldvalue);	
	}}else
	{
		nic_alert("Please Select an Item","N");
		}
}
function handle_cancel_sms()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			nic_alert("Message Cancelled","N");
			sms_list('1');
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
