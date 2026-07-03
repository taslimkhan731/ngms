function getBlocks(){
	ins_form= document.getElementById('form-general');
	console.log(ins_form);
	var gh_id = document.getElementById('sel_gh').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	ajax_post_form_field_divH(ins_form, "s_roomdetails", arr_fieldname, arr_fieldvalue, 'addgh');
}

//pagination data
function pagination_data(page_no){
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"editroomdetails",page_no];
	ajax_post_field_divH("s_roomdetails", arr_fieldname,arr_fieldvalue,'add');
}
//pagination data
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleteroomdetails",page_no];
	ajax_post_field_divH("s_roomdetails", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_roomdetails", arr_fieldname,arr_fieldvalue,'add');
}
//click Add roomdetails submenu
function addroomdetails()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["addroomdetails",getprocessid()];
	ajax_post_field_div("s_roomdetails",handle_addroomdetails,arr_fieldname,arr_fieldvalue,'add');	
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

//click edit roomdetails submenu
function editroomdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["editroomdetails",getprocessid(),"1"];
	ajax_post_field_div("s_roomdetails",handle_editroomdetails,arr_fieldname,arr_fieldvalue,'add');	
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

//click Delete roomdetails submenu
function deleteroomdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteroomdetails",getprocessid(),"1"];
	ajax_post_field_div("s_roomdetails",handle_deleteroomdetails,arr_fieldname,arr_fieldvalue,'add');	
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

//click save ghentry
function add_roomdetailsentry()
{
	if(VAL_saveroomdetails()){
	/*var aa  = document.getElementById('a').value;
	if(val_roomno(aa)){*/
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id"];
		arr_fieldvalue=["saveroomdetailsentry",getprocessid()];
		ajax_post_form_field_div(ins_form,"s_roomdetails",handle_save_roomdetailsentry,arr_fieldname,arr_fieldvalue,'add');
	}
}

function handle_save_roomdetailsentry()
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


//click edit roomdetails

function edit_roomdetails(room_no)
{
	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","room_no"];
	arr_fieldvalue=["editroomdetailslist",getprocessid(),room_no];
	ajax_post_form_field_div(ins_form,"s_roomdetails",handle_editroomdetails,arr_fieldname,arr_fieldvalue,'add');
		
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

//click edit role

function update_roomdetails()
{
	//if(VAL_updateuserdetails()){
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["updateroomdetailsentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_roomdetails",handle_update_roomdetailsentry,arr_fieldname,arr_fieldvalue,'add');
		//}
}
function handle_update_roomdetailsentry()
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

function delete_roomdetailsentry(room_no)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","room_no"];
		arr_fieldvalue=["deleteroomdetailslist",getprocessid(),room_no];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_roomdetails",handledeletelist,arr_fieldname,arr_fieldvalue);
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



function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_roomdetails",arr_fieldname,arr_fieldvalue,false);
}


//-----------------------------------------------------------------Validation----------------------------------------------------------------------------------

function VAL_saveroomdetails(){
	var aa  = document.getElementById('a').value;
	var bb  = document.getElementById('b').value;
	//var cc  = document.getElementById('c').value;
	var dd  = document.getElementById('sel_gh').value;
	var ee  = document.getElementById('sel_block').value;
	var ff  = document.getElementById('f').value;	
	var gg  = document.getElementById('g').value;
	//if((val_roomno(aa)) && (val_roomname(bb)) && (val_type(cc)) && (val_gh_id(dd)) && (val_blockid(ee)) && (val_floorno(ff)) && (val_maxnobed(gg)))
	if((val_roomno(aa)) && (val_roomname(bb)) && (val_gh_id(dd)) && (val_blockid(ee)) && (val_floorno(ff)) && (val_maxnobed(gg)))
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
	if((val_gh_id(aa)) && (val_ghname(bb)) && (val_street(cc)) && (val_city(dd)) && (val_district(ee)) && (val_state(ff)) && (val_pincode(gg)))
		//if((val_gh_id(aa)))
		return true;
	
}
function val_roomno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,20)){
			nic_alert("Invalid Length for to room number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid room number.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the room number","V");
		return false;
	}
	return true;
}
function val_roomname(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to room name.","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid room name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the room name","V");
		return false;
	}
	return true;
}
function val_type(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for room type.","V");
			return false;
		}
		else if(!VAL_AN(p_val)){
			nic_alert("Invalid room type.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the room type","V");
		return false;
	}
	return true;
}
function val_floorno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to floor number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid floor number.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the floor number","V");
		return false;
	}
	return true;
}
function val_maxnobed(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for maximum number of beds.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid Maximum number of beds.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the Maximum number of beds","V");
		return false;
	}
	return true;
}
function val_pincode(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,10)){
			nic_alert("Invalid Length for to pincode .","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid pincode.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the pincode","V");
		return false;
	}
	return true;
}