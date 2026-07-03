function getBlocks(){
	ins_form= document.getElementById('form-general');
	console.log(ins_form);
	var gh_id = document.getElementById('sel_gh').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	ajax_post_form_field_divH(ins_form, "s_notice", arr_fieldname, arr_fieldvalue, 'addgh');
}

//pagination list
function pagination_list(page_no){

	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_notice", arr_fieldname,arr_fieldvalue,'add');
}

function pagination_data(page_no){
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editroomdetails",page_no];
	ajax_post_field_divH("s_notice", arr_fieldname,arr_fieldvalue,'add');
}
//pagination data
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleteroomdetails",page_no];
	ajax_post_field_divH("s_notice", arr_fieldname,arr_fieldvalue,'add');
}

//click Add roomdetails submenu
function addroomdetails()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addroomdetails",getprocessid()];
	ajax_post_field_div("s_notice",handle_addroomdetails,arr_fieldname,arr_fieldvalue,'add');	
}

function handle_addroomdetails()
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

function add_roomdetailsentry()
{
	if(VAL_saveroomdetails()){
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["savenotice",getprocessid()];
		ajax_post_form_field_div(ins_form,"s_notice",handle_save_noticeentry,arr_fieldname,arr_fieldvalue,'add');
	}
}

function handle_save_noticeentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addroomdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

//click edit roomdetails submenu
function editroomdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editroomdetails",getprocessid(),"1"];
	ajax_post_field_div("s_notice",handle_editroomdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_editroomdetails()
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

function edit_roomdetails(id)
{
	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","id"];
	arr_fieldvalue=["editnotice",getprocessid(),id];
	ajax_post_form_field_div(ins_form,"s_notice",handle_editnotice,arr_fieldname,arr_fieldvalue,'add');

}
function handle_editnotice()
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

function update_notice()
{
	if(VAL_updateuserdetails()){
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["updatenotice",getprocessid()];
		ajax_post_form_field_div(ins_form,"s_notice",handle_update_notice,arr_fieldname,arr_fieldvalue,'add');
	}
}

function handle_update_notice()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editroomdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function delete_roomdetailsentry(id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","id"];
		arr_fieldvalue=["deleteroomdetailslist",getprocessid(),id];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_notice",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleteroomdetails();
			//menu_url_call('s_publicdataentry?task=abc',getprocessid());
			//document.getElementById(resultDiv).innerHTML=results;
			 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

//click Delete roomdetails submenu
function deleteroomdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteroomdetails",getprocessid(),"1"];
	ajax_post_field_div("s_notice",handle_deleteroomdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleteroomdetails()
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

function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_notice",arr_fieldname,arr_fieldvalue,false);
}


//-----------------------------------------------------------------Validation----------------------------------------------------------------------------------

function VAL_saveroomdetails(){
	var subject  = document.getElementById('subject').value;
	var description  = document.getElementById('desc').value;
	var from  = document.getElementById('from').value;	
	var tdate  = document.getElementById('tdate').value;
	if((val_notice_subject(subject)) && (val_notice_desc(description)) && (val_fromdate(from)) && (val_todate_notice(tdate,from)))
		return true;
}

function VAL_updateuserdetails(){
	var subject  = document.getElementById('subject').value;
	var description  = document.getElementById('desc').value;
	var from  = document.getElementById('from').value;	
	var tdate  = document.getElementById('tdate').value;
	if((val_notice_subject(subject)) && (val_notice_desc(description)) && (val_fromdate(from)) && (val_todate_notice(tdate,from)))
		return true;
}

function val_notice_subject(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid subject length","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid subject","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the Subject","V");
		return false;
	}
	return true;
}

function val_notice_desc(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,500)){
			nic_alert("Invalid Description length","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid Description","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the Description","V");
		return false;
	}
	return true;
}