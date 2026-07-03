function getBlocks(){
	ins_form= document.getElementById('form-general');
	console.log(ins_form);
	var gh_id = document.getElementById('sel_gh').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	ajax_post_form_field_divH(ins_form, "s_vehiclemaster", arr_fieldname, arr_fieldvalue, 'addgh');
}

//pagination edit
function pagination_edit(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editvehiclemaster",page_no];
	ajax_post_field_divH("s_vehiclemaster", arr_fieldname,arr_fieldvalue,'add');
}
//pagination delete
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deletevehiclemaster",page_no];
	ajax_post_field_divH("s_vehiclemaster", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_vehiclemaster", arr_fieldname,arr_fieldvalue,'add');
}
//click Add vehiclemaster submenu
function addvehiclemaster()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addvehiclemaster",getprocessid()];
	ajax_post_field_div("s_vehiclemaster",handle_addvehiclemaster,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addvehiclemaster()
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

//click edit vehiclemaster submenu
function editvehiclemaster()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editvehiclemaster",getprocessid(),"1"];
	ajax_post_field_div("s_vehiclemaster",handle_editvehiclemaster,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editvehiclemaster()
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

//click Delete vehiclemaster submenu
function deletevehiclemaster()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletevehiclemaster",getprocessid(),"1"];
	ajax_post_field_div("s_vehiclemaster",handle_deletevehiclemaster,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletevehiclemaster()
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



//click save vehiclemaster

function add_vehiclemasterentry()
{
	if(VAL_savevehiclemaster())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["savevehiclemasterentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_vehiclemaster",handle_save_vehiclemasterentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_vehiclemasterentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addvehiclemaster();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit vehiclemaster

function edit_vehiclemasterentry(driver_id)
{
	var ins_form= document.getElementById('form-general');
//	if(VAL_savevehiclemaster())
//		{
		
			arr_fieldname=["task","process_id","driver_id"];
			arr_fieldvalue=["editvehiclemasterentry",getprocessid(),driver_id];
			ajax_post_form_field_div(ins_form,"s_vehiclemaster",handle_editvehiclemaster,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_editvehiclemaster()
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

//click edit vehiclemaster

function update_vehiclemaster(driver_id)
{
	if(VAL_updatevehiclemaster())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","driver_id"];
			arr_fieldvalue=["updatevehiclemasterentry",getprocessid(),driver_id];
			ajax_post_form_field_div(ins_form,"s_vehiclemaster",handle_update_vehiclemasterentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_vehiclemasterentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editvehiclemaster();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_vehiclemasterentry(driver_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","driver_id"];
		arr_fieldvalue=["deletevehiclemasterlist",getprocessid(),driver_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_vehiclemaster",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletevehiclemaster();
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
	post_field("s_vehiclemaster",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------
function VAL_savevehiclemaster(){
	 
	var driver_name  = document.getElementById('b').value;
	var driver_mobileno  = document.getElementById('c').value;
	var vehicle_no  = document.getElementById('d').value;
	var protocol_name  = document.getElementById('pr').value;
	var protocol_mobileno  = document.getElementById('pm').value;
	var sel_block  = document.getElementById('sel_block').value;
	var gh_id  = document.getElementById('sel_gh').value;
	if((val_driver_name(driver_name)) && (val_driver_mobileno(driver_mobileno)) && (val_protocol_name(protocol_name)) && (val_protocol_mobileno(protocol_mobileno)) && (val_vehicle_no(vehicle_no))&&(val_city(gh_id))&&(val_guest(sel_block)))
		return true;
}

function VAL_updatevehiclemaster(){
	 
	var driver_name  = document.getElementById('b').value;
	var driver_mobileno  = document.getElementById('c').value;
	var vehicle_no  = document.getElementById('d').value;
	var protocol_name  = document.getElementById('pr').value;
	var protocol_mobileno  = document.getElementById('pm').value;
	var sel_block  = document.getElementById('sel_block').value;
	var gh_id  = document.getElementById('sel_gh').value;
	if((val_driver_name(driver_name)) && (val_driver_mobileno(driver_mobileno)) && (val_protocol_name(protocol_name)) && (val_protocol_mobileno(protocol_mobileno)) && (val_vehicle_no(vehicle_no))&&(val_city(gh_id))&&(val_guest(sel_block)))
		return true;
}

function val_city(p_val){

		if(p_val!=0)
		{
			if(!isLEN(p_val,1,10)){
				nic_alert("Invalid Length for City Name ID.","V");
				return false;
			}
			else if(!VAL_N(p_val)){
				nic_alert("Invalid City Name ID.","V");
				return false;
			}
		}
		else
		{
			nic_alert("Please select the City Name","V");
			return false;
		}
		return true;
}

function val_guest(p_val){
	
	if(p_val!=0)
	{
		return true;
	}
	else
	{
		nic_alert("Please select the Guest House","V");
		return false;
	}
	
}


function val_vehicle_no(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to vehicle number.","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid vehicle number.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the vehicle number","V");
		return false;
	}
	return true;
}

function val_driver_name(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for driver name.","V");
			return false;
		}
		else if(!VAL_AS(p_val)){
			nic_alert("Invalid driver name only allowed alphabet and space.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the driver name","V");
		return false;
	}
	return true;
}

function val_protocol_name(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for protocol name.","V");
			return false;
		}
		else if(!VAL_AS(p_val)){
			nic_alert("Invalid protocol name only allowed alphabet and space.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the protocol name","V");
		return false;
	}
	return true;
}

function val_driver_mobileno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,15)){
			nic_alert("Invalid Length for driver mobile number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid driver mobileno, only allow numeric.","V");
			return false;
		}
		else if(!VAL_MOB(p_val)){
			nic_alert("Invalid driver mobileno.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the driver mobile no","V");
		return false;
	}
	return true;
}

function val_protocol_mobileno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,15)){
			nic_alert("Invalid Length for protocol mobile number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid protocol mobileno, only allow numeric.","V");
			return false;
		}
		else if(!VAL_MOB(p_val)){
			nic_alert("Invalid protocol mobileno.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the protocol mobile no","V");
		return false;
	}
	return true;
}
