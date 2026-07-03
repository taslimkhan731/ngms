//search list data
function searchlist(page_no){
	var menu_id=  document.getElementById('menu_id').value;
	var name =  document.getElementById('name').value;
	if(menu_id=="" && name==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		//if(val_search_data(menu_id,name)){
		arr_fieldname=["task","process_id","menu_id","name","page_no"];
		arr_fieldvalue=["search",getprocessid(),menu_id,name,page_no];
		ajax_post_field_divH("s_menudetails",arr_fieldname,arr_fieldvalue,'search');	
	//}
	}
}


//chage password
function reset_menudetailsentry(menu_id){
	arr_fieldname =["process_id","task","menu_id"];
	arr_fieldvalue =[getprocessid(),"resetpassword",menu_id];
	ajax_post_field_div("s_menudetails",handle_reset,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_reset()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			
			document.getElementById(resultDiv).innerHTML=results;
			//show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}



//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_menudetails", arr_fieldname,arr_fieldvalue,'add');
}
//click Add menudetails submenu
function addmenudetails()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addmenudetails",getprocessid()];
	ajax_post_field_div("s_menudetails",handle_addmenudetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_addmenudetails()
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

//click edit menudetails submenu
function editmenudetails(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editmenudetails",getprocessid(),page_no];
	ajax_post_field_div("s_menudetails",handle_editmenudetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editmenudetails()
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

//click delete menudetails submenu
function deletemenudetails(page_no)
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deletemenudetails",getprocessid(),page_no];
	ajax_post_field_div("s_menudetails",handle_deletemenudetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deletemenudetails()
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



//click save menudetails

function add_menudetailsentry()
{
		if(VAL_savemenudetails())
		{
					var ins_form= document.getElementById('form-general');
					arr_fieldname=["task","process_id"];
					arr_fieldvalue=["savemenudetailsentry",getprocessid()];
					ajax_post_form_field_div(ins_form,"s_menudetails",handle_save_menudetailsentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_menudetailsentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addmenudetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit menudetails

function edit_menudetailsentry(menu_id)
{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","menu_id"];
			arr_fieldvalue=["editmenudetailsentry",getprocessid(),menu_id];
			ajax_post_form_field_div(ins_form,"s_menudetails",handle_editmenudetails,arr_fieldname,arr_fieldvalue,'add');
}
function handle_editmenudetails()
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

//click edit menudetails

function update_menudetails(menu_id)
{
	if(VAL_updatemenudetails())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","menu_id"];
			arr_fieldvalue=["updatemenudetailsentry",getprocessid(),menu_id];
			ajax_post_form_field_div(ins_form,"s_menudetails",handle_update_menudetailsentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_menudetailsentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editmenudetails('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_menudetailsentry(menu_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","menu_id"];
		arr_fieldvalue=["deletemenudetailslist",getprocessid(),menu_id];
		ajax_post_field("s_menudetails",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deletemenudetails('1');
			//document.getElementById(resultDiv).innerHTML="";
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_menudetails",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_savemenudetails(){
	var menu_name  = document.getElementById('menu_name').value;
	var page_url  = document.getElementById('page_url').value;
	var parent_id=document.getElementById('parent_id').value;
	var image_url  = document.getElementById('image_url').value;	
	var fn_call  = document.getElementById('fn_call').value;	
	var priority=document.getElementById('priority').value;
	if((val_menuname(menu_name)) && (val_page_url(page_url)) && (val_parent_id(parent_id)) && (val_image_url(image_url)) && (val_fn_call(fn_call)) && (val_priority(priority)))
		return true;
	}
function VAL_updatemenudetails(){
	var menu_name  = document.getElementById('menu_name').value;
	var parent_id=document.getElementById('parent_id').value;
	var priority=document.getElementById('priority').value;
	if((val_menuname(menu_name)) && (val_parent_id(parent_id)) && (val_priority(priority)))
		return true;
	}
function val_menu_id(p_val){
	if(p_val!="")
	{
		if(!VAL_N(p_val)){
			nic_alert("Invalid menu id","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the menu id","V");
		return false;
	}
	return true;
}


function val_menuname(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for to menu name","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid menu name","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the menu name","V");
		return false;
	}
	return true;
}

//function val_page_url(p_val){
//	if(p_val!="")
//	{
//		if(!isLEN(p_val,1,150)){
//			nic_alert("Invalid Length for to Page URL","V");
//			return false;
//		}
////		else if(!VAL_ANS(p_val)){
////			nic_alert("Invalid Page URL","V");
////			return false;
////		}
//	}
//	else
//	{
//		nic_alert("Please enter the page url","V");
//		return false;
//	}
//	return true;
//}

function val_parent_id(p_val){
	if(p_val!="")
	{
		if(!VAL_N(p_val)){
			nic_alert("Invalid Parent Id","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the parent id","V");
		return false;
	}
	return true;
}

function val_page_url(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,150)){
			nic_alert("Invalid Length for to Page URL","V");
			return false;
		}
		else if(!VAL_ANSS(p_val)){
			nic_alert("Invalid Page URL","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the page url","V");
		return false;
	}
	return true;
}

function val_image_url(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,255)){
			nic_alert("Invalid Length for to Image URL","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid Image URL","V");
			return false;
		}
	}
	/*else
	{
		nic_alert("Please enter the image url","V");
		return false;
	}*/
	return true;
}

function val_fn_call(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,255)){
			nic_alert("Invalid Length for to Function Call","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid Function Call","V");
			return false;
		}
	}
	/*else
	{
		nic_alert("Please enter the function call","V");
		return false;
	}*/
	return true;
}

function val_priority(p_val){
	if(p_val!="")
	{
		if(!VAL_N(p_val)){
			nic_alert("Invalid priority","V");
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
