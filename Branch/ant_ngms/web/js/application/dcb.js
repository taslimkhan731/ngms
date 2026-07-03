function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_dcb", arr_fieldname, arr_fieldvalue, 'header_div');
}


////print_dcb(
function print_dcb(gh_id,dcb_id)
{
	arr_fieldname =["task","process_id","dcb_id","gh_id"];
	arr_fieldvalue =["dcbpdf",getprocessid(),dcb_id,gh_id];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

///dcb_payment(

function dcb_payment(gh_id,sel_block,dcb_id,shift_id,shift_userid,receiptSum,page_no){
	
		arr_fieldname=["task","process_id","gh_id","sel_block","dcb_id","shift_id","shift_userid","receiptSum","page_no"];
		arr_fieldvalue=["submitPaymentDcb",getprocessid(),gh_id,sel_block,dcb_id,shift_id,shift_userid,receiptSum,page_no];
		ajax_post_field("s_dcb",handle_dcb_payment,arr_fieldname,arr_fieldvalue);
}

function handle_dcb_payment(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
			nic_alert("Payment Saved Successfully.","N");
			close_nicpopup();
			list_dcb(n[3]);
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}


//search_dcb
function search_dcb(page_no){

	var dcb_id=  document.getElementById('dcb_id').value;
	var shift_id =  document.getElementById('shift_id').value;
	var gh_id = document.getElementById('gh_id').value;
	var shift_user = document.getElementById('shift_user').value;
	var date=document.getElementById('date').value;
	if(dcb_id==""&&date==""&&shift_id==""&&shift_user==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_dcb(dcb_id,date,shift_id,shift_user)){
		arr_fieldname=["task","process_id","gh_id","dcb_id","date","shift_id","shift_user","page_no"];
		arr_fieldvalue=["searchDCB",getprocessid(),gh_id,dcb_id,date,shift_id,shift_user,page_no];
		ajax_post_field_divH("s_dcb",arr_fieldname,arr_fieldvalue,'DCBShowList');	
	}}
}


function val_search_dcb(dcb_id,date,shift_id,shift_user){

	if(!dcb_id==""){
		if(!isLEN(dcb_id,1,25)){
			 
			nic_alert("Invalid Length for DCB No.","V");
			return false;
		}
		else if(!VAL_N(dcb_id)){
			 
			nic_alert("Invalid DCB No.","V");
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
	
	if(!shift_id==""){
		if(!isLEN(shift_id,1,25)){
			 
			nic_alert("Invalid Length for Shift Id.","V");
			return false;
		}
		else if(!VAL_N(shift_id)){
			 
			nic_alert("Invalid Shift Id","V");
			return false;
		}
	}
	
	if(!shift_user==""){
		if(!isLEN(shift_user,1,50)){
			 
			nic_alert("Invalid Length for Shift User Id.","V");
			return false;
		}
		else if(!VAL_AN(shift_user)){
			 
			nic_alert("Invalid Shift User Id","V");
			return false;
		}
	}
	
	
	return true;
}

//dcb_payment_popup(
function dcb_payment_popup(dcb_id,gh_id,sel_block,page_no){
	
	arr_fieldname =["task","process_id","gh_id","sel_block","dcb_id","page_no"];
	arr_fieldvalue =['showdcbPaymentpopup',getprocessid(),gh_id,sel_block,dcb_id,page_no];	
	ajax_post_field("s_dcb",handle_dcb_show_popup,arr_fieldname,arr_fieldvalue);
	
}

function handle_dcb_show_popup()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//document.getElementById(resultDiv).innerHTML=results;
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}


function dcb_show_popup(dcb_id,gh_id,datetodisp){
	if(datetodisp === undefined){
		var date = document.getElementById('date').value;
		datetodisp = date;
	}
	
	arr_fieldname =["task","process_id","gh_id","dcb_id","datetodisp"];
	arr_fieldvalue =['showdcbpopup',getprocessid(),gh_id,dcb_id,datetodisp];	
	ajax_post_field("s_dcb",handle_dcb_show_popup,arr_fieldname,arr_fieldvalue);
	
}

function handle_dcb_show_popup()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//document.getElementById(resultDiv).innerHTML=results;
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function submit_dcb(gh_id,date){
	
	
	var ins_form= document.getElementById('dcb');
		arr_fieldname=["task","process_id","gh_id","date"];
		arr_fieldvalue=["submitdcb",getprocessid(),gh_id,date];
		ajax_post_form_field(ins_form,"s_dcb",handle_submit_dcb,arr_fieldname,arr_fieldvalue);
}

function handle_submit_dcb(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("DCB Created Successfully.","N");
			new_dcb();
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

//list_dcb(
function list_dcb(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
	arr_fieldvalue =['listdcb',getprocessid(),gh_id,sel_block,page_no];	
	ajax_post_field_div("s_dcb",handle_list_dcb,arr_fieldname,arr_fieldvalue,'dcb_emptyhome');
	}
}

function handle_list_dcb()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function new_dcb(){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname =["task","process_id","gh_id","sel_block"];
	arr_fieldvalue =['newdcb',getprocessid(),gh_id,sel_block];	
	ajax_post_field_div("s_dcb",handle_new_dcb,arr_fieldname,arr_fieldvalue,'dcb_emptyhome');
	}
}

function handle_new_dcb()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function dcb_go()
{
	
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		
		var date = document.getElementById('date').value;
		if(date==""){
			nic_alert("Please Select Date for DCB","V");
			return false;
		}else{
			if(dateCompLTodays2(date)){
				arr_fieldname =["task","process_id","gh_id","date","sel_block"];
				arr_fieldvalue =['dcb',getprocessid(),gh_id,date,sel_block];	
				$('#popup').show();
				ajax_post_field_div("s_dcb",handle_dcb,arr_fieldname,arr_fieldvalue,'dcb_report');
			}else{
				nic_alert("Future Date cannot be selected for DCB","V");
				document.getElementById('date').value="";
				return false;
			}
		}
	}
	
}

function handle_dcb()
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
			var n = results.substr(8).split("-");
			nic_alert(n[0],"N");
			$('#popup').hide();
			dcb_show_popup(n[1],n[2]);
		}
	}
}