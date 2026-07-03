//pagination data
function pagination_data(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editguesthouse",page_no];
	ajax_post_field_divH("s_guesthouse", arr_fieldname,arr_fieldvalue,'add');
}
//pagination data
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleteguesthouse",page_no];
	ajax_post_field_divH("s_guesthouse", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_guesthouse", arr_fieldname,arr_fieldvalue,'add');
}
//click Add guesthouse submenu
function addguesthouse()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addguesthouse",getprocessid()];
	ajax_post_field_div("s_guesthouse",handle_addguesthouse,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addguesthouse()
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

//click edit guesthouse submenu
function editguesthouse(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editguesthouse",getprocessid(),page_no];
	ajax_post_field_div("s_guesthouse",handle_editguesthouse,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editguesthouse()
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

//click Delete guesthouse submenu
function deleteguesthouse(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteguesthouse",getprocessid(),page_no];
	ajax_post_field_div("s_guesthouse",handle_deleteguesthouse,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleteguesthouse()
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
		ajax_post_form_field_div(ins_form,"s_guesthouse",handle_save_ghentry,arr_fieldname,arr_fieldvalue,'add');
	}
}
function handle_save_ghentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addguesthouse();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


//click edit guesthouse

function edit_guesthouse(gh_id)
{
	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","gh_id"];
	arr_fieldvalue=["editguesthouselist",getprocessid(),gh_id];
	ajax_post_form_field_div(ins_form,"s_guesthouse",handle_editguesthouse,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editguesthouse()
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

function update_guesthouse()
{
	if(VAL_updateuserdetails()){
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateghentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_guesthouse",handle_update_guesthouseentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_guesthouseentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editguesthouse('1');
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
		ajax_post_field("s_guesthouse",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleteguesthouse('1');
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
	post_field("s_guesthouse",arr_fieldname,arr_fieldvalue,false);
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
	if((val_gh_id(aa)) && (val_ghname(bb)) && (val_mode(cc)) && (val_heading1(dd)) && (val_heading2(ee)) && (val_heading3(ff)) && (val_heading4(gg)))
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
	//if((val_gh_id(aa)) && (val_ghname(bb)) && (val_street(cc)) && (val_city(dd)) && (val_district(ee)) && (val_state(ff)) && (val_pincode(gg)))
	if((val_gh_id(aa)) && (val_ghname(bb)) && (val_mode(cc)) && (val_heading1(dd)) && (val_heading2(ee)) && (val_heading3(ff)) && (val_heading4(gg)))
		//if((val_gh_id(aa)))
		return true;
	
}
function val_ghname(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for to guesthouse name","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid guesthouse name","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the guesthouse name","V");
		return false;
	}
	return true;
}
function val_mode(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to Mode","V");
			return false;
		}
		else if(!VAL_A(p_val)){
			nic_alert("Invalid Mode","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the Mode","V");
		return false;
	}
	return true;
}
function val_heading1(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,250)){
			nic_alert("Invalid Length for to heading1.","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid heading1.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the heading1","V");
		return false;
	}
	return true;
}
function val_heading2(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,250)){
			nic_alert("Invalid Length for to heading2.","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid heading2.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the heading2","V");
		return false;
	}
	return true;
}
function val_heading3(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,250)){
			nic_alert("Invalid Length for to heading3.","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid heading3.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the heading3","V");
		return false;
	}
	return true;
}
function val_heading4(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,250)){
			nic_alert("Invalid Length for to heading4 .","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid heading4.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the heading4","V");
		return false;
	}
	return true;
}