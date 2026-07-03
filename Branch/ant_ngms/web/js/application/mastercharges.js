//pagination edit
function pagination_edit(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editmastercharges",page_no];
	ajax_post_field_divH("s_mastercharges", arr_fieldname,arr_fieldvalue,'add');
}
//pagination delete
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deletemastercharges",page_no];
	ajax_post_field_divH("s_mastercharges", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_mastercharges", arr_fieldname,arr_fieldvalue,'add');
}
//click Add mastercharges submenu
function addmastercharges()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addmastercharges",getprocessid()];
	ajax_post_field_div("s_mastercharges",handle_addmastercharges,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addmastercharges()
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

//click edit mastercharges submenu
function editmastercharges()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editmastercharges",getprocessid(),"1"];
	ajax_post_field_div("s_mastercharges",handle_editmastercharges,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editmastercharges()
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

//click Delete mastercharges submenu
function deletemastercharges()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletemastercharges",getprocessid(),"1"];
	ajax_post_field_div("s_mastercharges",handle_deletemastercharges,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletemastercharges()
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



//click save mastercharges

function add_masterchargesentry()
{
	if(VAL_savemastercharges())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["savemasterchargesentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_mastercharges",handle_save_masterchargesentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_masterchargesentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addmastercharges();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit mastercharges

function edit_masterchargesentry(charges_id)
{
	var ins_form= document.getElementById('form-general');
	//if(VAL_updatemastercharges())
		//{
		
			arr_fieldname=["task","process_id","charges_id"];
			arr_fieldvalue=["editmasterchargesentry",getprocessid(),charges_id];
			ajax_post_form_field_div(ins_form,"s_mastercharges",handle_editmastercharges,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_editmastercharges()
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

//click edit mastercharges

function update_mastercharges()
{
	var ins_form= document.getElementById('form-general');
	if(VAL_updatemastercharges())
		{
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updatemasterchargesentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_mastercharges",handle_update_masterchargesentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_masterchargesentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editmastercharges();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_masterchargesentry(charges_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","charges_id"];
		arr_fieldvalue=["deletemasterchargeslist",getprocessid(),charges_id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_mastercharges",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletemastercharges();
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
	post_field("s_mastercharges",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_savemastercharges(){
	 
	var charges_id  = document.getElementById('a').value;
	var charges_title  = document.getElementById('b').value;
			
	if((val_charges_id(charges_id)) && (val_charges_title(charges_title)))
		return true;
	}

function VAL_updatemastercharges(){
	 
	var charges_id  = document.getElementById('a').value;
	var charges_title  = document.getElementById('b').value;
	
	if((val_charges_id(charges_id)) && (val_charges_title(charges_title)))
		return true;
	}
function val_charges_id(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to mastercharges id.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid mastercharges id,only allowed numeric.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the mastercharges id","V");
		return false;
	}
	return true;
}
function val_charges_title(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,200)){
			nic_alert("Invalid Length for to mastercharges title.","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid mastercharges title,only allowed alphabet,numeric,special character.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the mastercharges title","V");
		return false;
	}
	return true;
}

