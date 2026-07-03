function pagination_edit(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editvisitorcategory",page_no];
	ajax_post_field_divH("s_visitorcategory", arr_fieldname,arr_fieldvalue,'add');
}
//pagination datalist
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deletevisitorcategory",page_no];
	ajax_post_field_divH("s_visitorcategory", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_visitorcategory", arr_fieldname,arr_fieldvalue,'add');
}
//click Add visitorcategory submenu
function addvisitorcategory()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addvisitorcategory",getprocessid()];
	ajax_post_field_div("s_visitorcategory",handle_addvisitorcategory,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addvisitorcategory()
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

//click edit visitorcategory submenu
function editvisitorcategory()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editvisitorcategory",getprocessid(),"1"];
	ajax_post_field_div("s_visitorcategory",handle_editvisitorcategory,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editvisitorcategory()
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

//click Add visitorcategory submenu
function deletevisitorcategory()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletevisitorcategory",getprocessid(),"1"];
	ajax_post_field_div("s_visitorcategory",handle_deletevisitorcategory,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletevisitorcategory()
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



//click save visitorcategory

function add_visitorcategoryentry()
{
	if(VAL_savevisitorcategory())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["savevisitorcategoryentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_visitorcategory",handle_save_visitorcategoryentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_visitorcategoryentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addvisitorcategory();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit visitorcategory

function edit_visitorcategoryentry(vc_id)
{
	var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","vc_id"];
			arr_fieldvalue=["editvisitorcategoryentry",getprocessid(),vc_id];
			ajax_post_form_field_div(ins_form,"s_visitorcategory",handle_editvisitorcategory,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editvisitorcategory()
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

//click edit visitorcategory

function update_visitorcategory()
{
	if(VAL_savevisitorcategory())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updatevisitorcategoryentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_visitorcategory",handle_update_visitorcategoryentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_visitorcategoryentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editvisitorcategory();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_visitorcategoryentry(vc_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","vc_id"];
		arr_fieldvalue=["deletevisitorcategorylist",getprocessid(),vc_id];
		ajax_post_field("s_visitorcategory",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletevisitorcategory();
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
	post_field("s_visitorcategory",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_savevisitorcategory(){
	 
	var vc_id  = document.getElementById('a').value;
	var desg_category  = document.getElementById('b').value;
	var priority  = document.getElementById('c').value;
	var category  = document.getElementById('d').value;
	var sub_cat  = document.getElementById('e').value;
			
	if((val_vc_id(vc_id)) && (val_desg_category(desg_category)) && (val_priority(priority)) && (val_category(category)) && (val_sub_cat(sub_cat)))
		return true;
	}

function val_vc_id(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to visitorcategory id.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid visitorcategory id,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the visitorcategory id","V");
		return false;
	}
	return true;
}
function val_desg_category(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for to designation category.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid designation category,only allowed alphanumeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the designation category","V");
		return false;
	}
	return true;
}

function val_priority(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,2)){
			nic_alert("Invalid Length for to priority.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid priority,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the priority","V");
		return false;
	}
	return true;
}
function val_category(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,0,1)){
			nic_alert("Invalid Length for to category.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid category,only allowed alpha numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the category","V");
		return false;
	}
	return true;
}
function val_sub_cat(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,5)){
			nic_alert("Invalid Length for to sub_cat.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid sub_cat,only allowed alpha numeric .","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the sub_category","V");
		return false;
	}
	return true;
}