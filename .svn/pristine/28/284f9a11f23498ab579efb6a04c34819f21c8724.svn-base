//pagination edit
function pagination_edit(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editroomblock",page_no];
	ajax_post_field_divH("s_roomblock", arr_fieldname,arr_fieldvalue,'add');
}
//pagination delete
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleteroomblock",page_no];
	ajax_post_field_divH("s_roomblock", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_roomblock", arr_fieldname,arr_fieldvalue,'add');
}
//click Add roomblock submenu
function addroomblock()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addroomblock",getprocessid()];
	ajax_post_field_div("s_roomblock",handle_addroomblock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addroomblock()
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

//click edit roomblock submenu
function editroomblock()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editroomblock",getprocessid(),"1"];
	ajax_post_field_div("s_roomblock",handle_editroomblock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editroomblock()
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

//click Delete roomblock submenu
function deleteroomblock()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteroomblock",getprocessid(),"1"];
	ajax_post_field_div("s_roomblock",handle_deleteroomblock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleteroomblock()
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



//click save roomblock

function add_roomblockentry()
{
	if(VAL_saveroomblock())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["saveroomblockentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_roomblock",handle_save_roomblockentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_roomblockentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addroomblock();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit roomblock

function edit_roomblockentry(block_id)
{
	var ins_form= document.getElementById('form-general');
//	if(VAL_saveroomblock())
//		{
		
			arr_fieldname=["task","process_id","block_id"];
			arr_fieldvalue=["editroomblockentry",getprocessid(),block_id];
			ajax_post_form_field_div(ins_form,"s_roomblock",handle_editroomblock,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_editroomblock()
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

//click edit roomblock

function update_roomblock()
{
	//if(VAL_saveroomblock())
		//{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateroomblockentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_roomblock",handle_update_roomblockentry,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_update_roomblockentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editroomblock();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_roomblockentry(block_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","block_id"];
		arr_fieldvalue=["deleteroomblocklist",getprocessid(),block_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_roomblock",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleteroomblock();
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
	post_field("s_roomblock",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_saveroomblock(){
	 
	var block_id  = document.getElementById('a').value;
	var block_name  = document.getElementById('b').value;
	var gh_id  = document.getElementById('sel_gh').value;
	if((val_block_id(block_id)) && (val_block_name(block_name)) && (val_gh_id(gh_id)))
		return true;
	}

function val_block_id(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to block id.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid block id,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the block id","V");
		return false;
	}
	return true;
}
function val_block_name(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to block name.","V");
			return false;
		}
		else if(!VAL_AS(p_val)){
			nic_alert("Invalid block name,only allowed name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the block name","V");
		return false;
	}
	return true;
}

