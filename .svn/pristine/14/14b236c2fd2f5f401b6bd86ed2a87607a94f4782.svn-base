//pagination data
function pagination_data(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editquotablock",page_no];
	ajax_post_field_divH("s_quotablock", arr_fieldname,arr_fieldvalue,'add');
}
//pagination datalist
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deletequotablock",page_no];
	ajax_post_field_divH("s_quotablock", arr_fieldname,arr_fieldvalue,'add');
}

//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_quotablock", arr_fieldname,arr_fieldvalue,'add');
}
//click Add quotablock submenu
function addquotablock()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addquotablock",getprocessid()];
	ajax_post_field_div("s_quotablock",handle_addquotablock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addquotablock()
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

//click edit quotablock submenu
function editquotablock()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editquotablock",getprocessid(),"1"];
	ajax_post_field_div("s_quotablock",handle_editquotablock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editquotablock()
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

//click Delete quotablock submenu
function deletequotablock()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletequotablock",getprocessid(),"1"];
	ajax_post_field_div("s_quotablock",handle_deletequotablock,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletequotablock()
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



//click save quotablock

function add_quotablockentry()
{
	if(val_nullquotablk())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["savequotablockentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_quotablock",handle_save_quotablockentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_quotablockentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addquotablock();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit quotablock

function edit_quotablockentry(block_id,quota_type,gh_id)
{
	var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","block_id","quota_type","gh_id"];
			arr_fieldvalue=["editquotablockentry",getprocessid(),block_id,quota_type,gh_id];
			ajax_post_form_field_div(ins_form,"s_quotablock",handle_editquotablock,arr_fieldname,arr_fieldvalue,'add');
	
}
function handle_editquotablock()
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

//click edit quotablock

function update_quotablock()
{
	if(VAL_updatequotablock())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updatequotablockentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_quotablock",handle_update_quotablockentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_quotablockentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editquotablock();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_quotablockentry(block_id,quota_type)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","block_id","quota_type"];
		arr_fieldvalue=["deletequotablocklist",getprocessid(),block_id,quota_type];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_quotablock",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletequotablock();
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
	post_field("s_quotablock",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_savequotablock(){
	 
	var gh_id  = document.getElementById('sel_gh').value;
	var block_id  = document.getElementById('sel_block').value;
	var quota  = document.getElementById('sel_quota').value;
	var total=	document.getElementById('d').value;
	if((val_gh_id(gh_id)) && (val_block_id(block_id)) && (val_quota(quota)) && (val_total(total)))
		return true;
	}

function val_nullquotablk(){
	var gh_id  = document.getElementById('sel_gh').value;
	var block_id  = document.getElementById('sel_block').value;
	var quota  = document.getElementById('sel_quota').value;
	var total=	document.getElementById('d').value;
	if((val_NULL(gh_id)) && (val_NULL(block_id)) && (val_NULL(quota)))
		return true;
}
function VAL_updatequotablock(){
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
		else if(!VAL_AN(p_val)){
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
			nic_alert("Invalid Length for to number of rooms.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid number of rooms,only allowed number of rooms.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the number of rooms","V");
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
		nic_alert("Please select the block","V");
		return false;
	}
	return true;
}