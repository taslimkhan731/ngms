
function addfooddetails()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addfooddetails",getprocessid()];
	ajax_post_field_div("s_foodmaster",handle_addfooddetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addfooddetails()
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

function cancel_creation1(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_foodmaster",arr_fieldname,arr_fieldvalue,false);
}

function add_fooddetailsentry()
{
	if(VAL_savefooddetails())
	{
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["savefooddetails",getprocessid()];
		ajax_post_form_field_div(ins_form,"s_foodmaster",handle_save_fooddetails,arr_fieldname,arr_fieldvalue,'add');
	}
}
function handle_save_fooddetails()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
//			addfooddetails();
			pagination_list("10");
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

//click Delete fooddetails submenu
function deletefooddetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletefooddetails",getprocessid(),"1"];
	ajax_post_field_div("s_foodmaster",handle_deletefooddetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletefooddetails()
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
//end

//click edit fooddetails submenu
function editfooddetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editfooddetails",getprocessid(),"1"];
	ajax_post_field_div("s_foodmaster",handle_editfooddetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editfooddetails()
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
//end

//click edit fooddetails

function edit_fooddetails(item_id)
{
	
	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","item_id"];
	arr_fieldvalue=["editfooddetailslist",getprocessid(),item_id];
	ajax_post_form_field_div(ins_form,"s_foodmaster",handle_editfooddetails,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editfooddetails()
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














//pagination data
function pagination_data(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editfooddetails",page_no];
	ajax_post_field_divH("s_foodmaster", arr_fieldname,arr_fieldvalue,'add');
}
//pagination data
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deletefooddetails",page_no];
	ajax_post_field_divH("s_foodmaster", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_foodmaster", arr_fieldname,arr_fieldvalue,'add');
}










function update_fooddetails(item_id)
{

//	if(VAL_updatefooddetails())
	if(VAL_savefooddetails())
	
	{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","item_id"];
			arr_fieldvalue=["updatefooddetails",getprocessid(),item_id];
			ajax_post_form_field_div(ins_form,"s_foodmaster",handle_update_fooddetails,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_fooddetails()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editfooddetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function delete_fooddetailsentry(item_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","item_id"];
		arr_fieldvalue=["deletefooddetailslist",getprocessid(),item_id];
		ajax_post_field("s_foodmaster",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletefooddetails();
						 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}





//-----------------------------------------------------------------Validation----------------------------------------------------------------------------------

function VAL_savefooddetails(){
	
//	var gh     = document.getElementById('sel_gh').value;
//	var cat_id = document.getElementById('cat_id').value;
	var item   = document.getElementById('item').value;
	var rate1  = document.getElementById('rate1').value;
	
//	var rate2  = document.getElementById('rate2').value;
//	var rate3  = document.getElementById('rate3').value;	
//	var fromdate  = document.getElementById('fromdate').value;	
//	var todate  = document.getElementById('todate').value;	
	
//	if((val_gh_id(gh)) && (val_cat_id(cat_id)) && (val_fooditem(item)) && (val_foodrate(rate1,"Official")) && (val_foodrate(rate2,"Private")) && (val_foodrate(rate3,"Outsider")) && (val_effect_date_from(fromdate)) && (val_effect_date_to(todate)) && (val_status()))
	if ((val_fooditem(item)) && (val_foodrate(rate1)))	
		return true;
	
}
function VAL_updatefooddetails(){
	var gh     = document.getElementById('sel_gh').value;
	var cat_id = document.getElementById('cat_id').value;
	var item   = document.getElementById('item').value;
	var rate1  = document.getElementById('rate1').value;
	var rate2  = document.getElementById('rate2').value;
	var rate3  = document.getElementById('rate3').value;	
	var fromdate  = document.getElementById('fromdate').value;	
	var todate  = document.getElementById('todate').value;	
	
	if((val_gh_id(gh)) && (val_cat_id(cat_id)) && (val_fooditem(item)) && (val_foodrate(rate1,"Official")) && (val_foodrate(rate2,"Private")) && (val_foodrate(rate3,"Outsider")) && (val_effect_date_from(fromdate)) && (val_effect_date_to(todate)) && (val_status()))
		return true;
	
	
}

//validate from_date
function val_effect_date_from(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			 
			nic_alert("Invalid Length for From Date.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			 
			nic_alert("Invalid Date.","V");
			return false;
		}
		
	}
	else
	{
		nic_alert("Please enter the From Date","V");
		return false;
	}
	return true;
}

function val_effect_date_to(p_val){
	var fromdate  = document.getElementById('fromdate').value;	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for To Date.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			nic_alert("Invalid Date.","V");
			return false;
		}
		else if(dateCompTwo(p_val,fromdate))
		{
		nic_alert("invalid from date,Only allowed from date less than to date","V");
			return false;
		}
	
	}
	else
	{
		nic_alert("Please enter the To Date","V");
		return false;
	}
	return true;
}

function val_status(){
	if (!$('input[name=status]:checked').val() ) { 
		 alert("Please Choose a Status");
		 return false;
	}
	
	return true;
}

