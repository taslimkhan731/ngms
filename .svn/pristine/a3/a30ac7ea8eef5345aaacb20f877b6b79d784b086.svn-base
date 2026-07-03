//pagination data
function pagination_data(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editghdetails",page_no];
	ajax_post_field_divH("s_ghdetails", arr_fieldname,arr_fieldvalue,'add');
}
//pagination data
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleteghdetails",page_no];
	ajax_post_field_divH("s_ghdetails", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_ghdetails", arr_fieldname,arr_fieldvalue,'add');
}
//click Add ghdetails submenu
function addghdetails()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addghdetails",getprocessid()];
	ajax_post_field_div("s_ghdetails",handle_addghdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addghdetails()
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

//click edit ghdetails submenu
function editghdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editghdetails",getprocessid(),"1"];
	ajax_post_field_div("s_ghdetails",handle_editghdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editghdetails()
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

//click Delete ghdetails submenu
function deleteghdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteghdetails",getprocessid(),"1"];
	ajax_post_field_div("s_ghdetails",handle_deleteghdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleteghdetails()
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

//click save ghentry
function add_ghentry()
{
	if(VAL_saveuserdetails()){
		
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["saveghentry",getprocessid()];
		ajax_post_form_field_div(ins_form,"s_ghdetails",handle_save_ghentry,arr_fieldname,arr_fieldvalue,'add');
	}
}
function handle_save_ghentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addghdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


//click edit ghdetails

function edit_ghdetails(gh_id)
{
	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","gh_id"];
	arr_fieldvalue=["editghdetailslist",getprocessid(),gh_id];
	ajax_post_form_field_div(ins_form,"s_ghdetails",handle_editghdetails,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editghdetails()
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

//click edit role

function update_ghdetails()
{
	if(VAL_updateuserdetails()){
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateghentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_ghdetails",handle_update_ghdetailsentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_ghdetailsentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editghdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function delete_ghentry(gh_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","gh_id"];
		arr_fieldvalue=["deleteghlist",getprocessid(),gh_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_ghdetails",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleteghdetails();
			//menu_url_call('s_publicdataentry?task=abc',getprocessid());
			//document.getElementById(resultDiv).innerHTML=results;
			 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}



function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_ghdetails",arr_fieldname,arr_fieldvalue,false);
}


//-----------------------------------------------------------------Validation----------------------------------------------------------------------------------

function VAL_saveuserdetails(){
	var aa  = document.getElementById('a').value;
	var bb  = document.getElementById('b').value;
	var cc  = document.getElementById('c').value;
	var dd  = document.getElementById('d').value;
	var ee  = document.getElementById('e').value;
	var ff  = document.getElementById('f').value;	
	var gg  = document.getElementById('g').value;	
	if((val_gh_id(aa)) && (val_ghname(bb)) && (val_street(cc)) && (val_city(dd)) && (val_district(ee)) && (val_state(ff)) && (val_pincode(gg)))
		//if((val_gh_id(aa)))
		return true;
	
}
function VAL_updateuserdetails(){
	var aa  = document.getElementById('a').value;
	var bb  = document.getElementById('b').value;
	var cc  = document.getElementById('c').value;
	var dd  = document.getElementById('d').value;
	var ee  = document.getElementById('e').value;
	var ff  = document.getElementById('f').value;	
	var gg  = document.getElementById('g').value;	
	if((val_gh_id(aa)) && (val_ghname(bb)) && (val_street(cc)) && (val_city(dd)) && (val_district(ee)) && (val_state(ff)) && (val_pincode(gg)))
		//if((val_gh_id(aa)))
		return true;
	
}
function val_ghname(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for to guesthouse name.","V");
			return false;
		}
		else if(!VAL_AS(p_val)){
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
		if(!isLEN(p_val,6,6)){
			nic_alert("Invalid Length for to pincode .","V");
			return false;
		}
		if(!VAL_N(p_val)){
			nic_alert("Invalid pincode. only allow numeric","V");
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