//pagination edit
function pagination_edit(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editquota",page_no];
	ajax_post_field_divH("s_quota", arr_fieldname,arr_fieldvalue,'add');
}
//pagination delete
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deletequota",page_no];
	ajax_post_field_divH("s_quota", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_quota", arr_fieldname,arr_fieldvalue,'add');
}
//click Add quota submenu
function addquota()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addquota",getprocessid()];
	ajax_post_field_div("s_quota",handle_addquota,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addquota()
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

//click edit quota submenu
function editquota()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editquota",getprocessid(),"1"];
	ajax_post_field_div("s_quota",handle_editquota,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editquota()
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

//click Delete quota submenu
function deletequota()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletequota",getprocessid(),"1"];
	ajax_post_field_div("s_quota",handle_deletequota,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletequota()
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



//click save quota

function add_quotaentry()
{
	if(VAL_savequota())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["savequotaentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_quota",handle_save_quotaentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_quotaentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addquota();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit quota

function edit_quotaentry(quota_type)
{
	var ins_form= document.getElementById('form-general');
//	if(VAL_savequota())
//		{
		
			arr_fieldname=["task","process_id","quota_type"];
			arr_fieldvalue=["editquotaentry",getprocessid(),quota_type];
			ajax_post_form_field_div(ins_form,"s_quota",handle_editquota,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_editquota()
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

//click edit quota

function update_quota()
{
	if(VAL_updatequota())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updatequotaentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_quota",handle_update_quotaentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_quotaentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editquota();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_quotaentry(quota_type)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","quota_type"];
		arr_fieldvalue=["deletequotalist",getprocessid(),quota_type];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_quota",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletequota();
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
	post_field("s_quota",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_savequota(){
	 
	var quota_type  = document.getElementById('a').value;
	var quota_desc  = document.getElementById('b').value;
			
	if((val_quota_type(quota_type)) && (val_quota_desc(quota_desc)))
		return true;
	}

function VAL_updatequota(){
	 
	var quota_type  = document.getElementById('a').value;
	var quota_desc  = document.getElementById('b').value;
			
	if((val_quota_type(quota_type)) && (val_quota_desc(quota_desc)))
		return true;
	}

function val_quota_type(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
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
		nic_alert("Please Enter the quota type","V");
		return false;
	}
	return true;
}
function val_quota_desc(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to quota description.","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid quota name,only allowed description.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the quota description","V");
		return false;
	}
	return true;
}

