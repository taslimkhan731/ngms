function getRepairBlocks(){
	ins_form= document.getElementById('form-general');
	var gh_id = document.getElementById('sel_gh').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	ajax_post_form_field_divH(ins_form, "s_repairblock", arr_fieldname, arr_fieldvalue, 'addgh');
}
//pagination data
function pagination_data(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editrepairblock",page_no];
	ajax_post_field_divH("s_repairblock", arr_fieldname,arr_fieldvalue,'add');
}
//pagination datalist
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleterepairblock",page_no];
	ajax_post_field_divH("s_repairblock", arr_fieldname,arr_fieldvalue,'add');
}

//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_repairblock", arr_fieldname,arr_fieldvalue,'add');
}
//click Add repairblock submenu
function addrepairblock()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addrepairblock",getprocessid()];
	ajax_post_field_div("s_repairblock",handle_addrepairblock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addrepairblock()
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

//click edit repairblock submenu
function editrepairblock()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editrepairblock",getprocessid(),"1"];
	ajax_post_field_div("s_repairblock",handle_editrepairblock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editrepairblock()
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

//click Delete repairblock submenu
function deleterepairblock()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleterepairblock",getprocessid(),"1"];
	ajax_post_field_div("s_repairblock",handle_deleterepairblock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleterepairblock()
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



//click save repairblock

function add_repairblockentry()
{
	if(VAL_saverepairblock())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["saverepairblockentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_repairblock",handle_save_repairblockentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_repairblockentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addrepairblock();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit repairblock

function edit_repairblockentry(block_id,gh_id)
{
	var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","block_id","gh_id"];
			arr_fieldvalue=["editrepairblockentry",getprocessid(),block_id,gh_id];
			ajax_post_form_field_div(ins_form,"s_repairblock",handle_editrepairblock,arr_fieldname,arr_fieldvalue,'add');
	
}
function handle_editrepairblock()
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

//click edit repairblock

function update_repairblock()
{
	if(VAL_updaterepairblock())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updaterepairblockentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_repairblock",handle_update_repairblockentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_repairblockentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editrepairblock();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_repairblockentry(block_id,gh_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","block_id","gh_id"];
		arr_fieldvalue=["deleterepairblocklist",getprocessid(),block_id,gh_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_repairblock",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleterepairblock();
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
	post_field("s_repairblock",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_saverepairblock(){
	 
	var gh_id  = document.getElementById('sel_gh').value;
	var block_id  = document.getElementById('sel_block').value;
	var total=	document.getElementById('d').value;
	if((val_gh_id(gh_id)) && (val_block_id(block_id)) && (val_total(total)))
		return true;
	}
function VAL_updaterepairblock(){
	var total=	document.getElementById('d').value;
	if(val_total(total))
		return true;
	}

function val_quota(p_val){
	
	if(p_val!=0)
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to quota type.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid quota type,only allowed alpha numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please select the quota","V");
		return false;
	}
	return true;
}
function val_total(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to number of repair rooms.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of rooms,only allowed number of repair rooms.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the number of rooms repair","V");
		return false;
	}
	return true;
}

function val_block_id(p_val){
	
	if(p_val!=0)
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
		
		nic_alert("Please select the Guest House Name","V");
		return false;
	}
	return true;
}