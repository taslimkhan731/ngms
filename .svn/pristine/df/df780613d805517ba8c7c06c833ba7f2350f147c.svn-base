//pagination list
function pagination_data(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_role", arr_fieldname,arr_fieldvalue,'add');
}
//click Add role submenu
function addrole()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addrole",getprocessid()];
	ajax_post_field_div("s_role",handle_addrole,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addrole()
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

//click edit role submenu
function editrole(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editrole",getprocessid(),page_no];
	ajax_post_field_div("s_role",handle_editrole,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editrole()
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

//click Delete role submenu
function deleterole(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleterole",getprocessid(),page_no];
	ajax_post_field_div("s_role",handle_deleterole,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleterole()
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



//click save role

function add_roleentry()
{
	if(VAL_saverole())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["saveroleentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_role",handle_save_roleentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_roleentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addrole();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit role

function edit_roleentry(role_code)
{
	var ins_form= document.getElementById('form-general');
//	if(VAL_saverole())
//		{
		
			arr_fieldname=["task","process_id","role_code"];
			arr_fieldvalue=["editroleentry",getprocessid(),role_code];
			ajax_post_form_field_div(ins_form,"s_role",handle_editrole,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_editrole()
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

function update_role()
{
	if(VAL_saverole())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateroleentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_role",handle_update_roleentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_roleentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editrole('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_roleentry(role_code)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","role_code"];
		arr_fieldvalue=["deleterolelist",getprocessid(),role_code];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_role",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleterole('1');
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
	post_field("s_role",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_saverole(){
	 
	var role_code  = document.getElementById('a').value;
	var role_name  = document.getElementById('b').value;
	var hierarchy  = document.getElementById('c').value;
		
	if((val_role_code(role_code)) && (val_role_name(role_name)) && (val_hierarchy(hierarchy)))
		return true;
	}

function val_role_code(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,2)){
			nic_alert("Invalid Length for to role code.","V");
			return false;
		}
		if(!VAL_N(p_val)){
			nic_alert("Invalid role code,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the role code","V");
		return false;
	}
	return true;
}
function val_role_name(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to role name.","V");
			return false;
		}
		else if(!VAL_NAME(p_val)){
			nic_alert("Invalid role name,only allowed name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the role name","V");
		return false;
	}
	return true;
}

function val_hierarchy(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,3)){
			nic_alert("Invalid Length for to hierarchy.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid hierarchy,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the hierarchy","V");
		return false;
	}
	return true;
}