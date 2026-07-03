//search list data
function searchlist(page_no){
	var user_id=  document.getElementById('user_id').value;
	var name =  document.getElementById('name').value;
	if(user_id=="" && name==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		//if(val_search_data(user_id,name)){
		arr_fieldname=["task","process_id","user_id","name","page_no"];
		arr_fieldvalue=["search",getprocessid(),user_id,name,page_no];
		ajax_post_field_divH("s_userdetails",arr_fieldname,arr_fieldvalue,'search');	
	//}
	}
}

function listAll(){
	var user_id=  "1";
	var name =  document.getElementById('name').value;
	name = "list";
	arr_fieldname=["task","process_id","user_id","name","page_no"];
	arr_fieldvalue=["list",getprocessid(),user_id,name,user_id];
	ajax_post_field_divH("s_userdetails",arr_fieldname,arr_fieldvalue,'search');	
}


//chage password
function reset_userdetailsentry(user_id){
	arr_fieldname =["process_id","task","user_id"];
	arr_fieldvalue =[getprocessid(),"resetpassword",user_id];
	ajax_post_field_div("s_userdetails",handle_reset,arr_fieldname,arr_fieldvalue,'add');	
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

//save Reset password
function resetpassword(user_id)
{
	if(VAL_resetpassword())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","user_id"];
			arr_fieldvalue=["saveresetpassword",getprocessid(),user_id];
			ajax_post_form_field_div(ins_form,"s_userdetails",handle_update_reset,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_reset()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			edituserdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


//pagination data
function pagination_edit(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"edituserdetails",page_no];
	ajax_post_field_divH("s_userdetails", arr_fieldname,arr_fieldvalue,'add');
}
//pagination datalist
function pagination_delete(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"deleteuserdetails",page_no];
	ajax_post_field_divH("s_userdetails", arr_fieldname,arr_fieldvalue,'add');
}
//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_userdetails", arr_fieldname,arr_fieldvalue,'add');
}
//click Add userdetails submenu
function adduserdetails()
{
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["adduserdetails",getprocessid()];
	ajax_post_field_div("s_userdetails",handle_adduserdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_adduserdetails()
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

//click edit userdetails submenu
function edituserdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["edituserdetails",getprocessid(),"1"];
	ajax_post_field_div("s_userdetails",handle_edituserdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_edituserdetails()
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

//click delete userdetails submenu
function deleteuserdetails()
{
	arr_fieldname =["task","process_id","page_no"];
	arr_fieldvalue =["deleteuserdetails",getprocessid(),"1"];
	ajax_post_field_div("s_userdetails",handle_deleteuserdetails,arr_fieldname,arr_fieldvalue,'add');	
}
function handle_deleteuserdetails()
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



//click save userdetails

function add_userdetailsentry()
{
	if(VAL_saveuserdetails())
		{
			var ins_form= document.getElementById('form-general');
			var password  = document.getElementById('c').value;
			var retpasswd= SHA256(password);
			document.getElementById('c').value = retpasswd;
			document.getElementById('c1').value = retpasswd;
			arr_fieldname=["task","process_id"];
			arr_fieldvalue=["saveuserdetailsentry",getprocessid()];
			ajax_post_form_field_div(ins_form,"s_userdetails",handle_save_userdetailsentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_save_userdetailsentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			adduserdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit userdetails

function edit_userdetailsentry(user_id)
{
			var ins_form= document.getElementById('form-general');
			var hidvalue= document.getElementById('hidvalue');
			arr_fieldname=["task","process_id","user_id","hidvalue"];
			arr_fieldvalue=["edituserdetailsentry",getprocessid(),user_id,hidvalue];
			ajax_post_form_field_div(ins_form,"s_userdetails",handle_edituserdetails,arr_fieldname,arr_fieldvalue,'add');
}
function handle_edituserdetails()
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

//click edit userdetails

function update_userdetails(user_id,password)
{
	if(VAL_edituserdetails())
		{
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","user_id","password"];
			arr_fieldvalue=["updateuserdetailsentry",getprocessid(),user_id,password];
			ajax_post_form_field_div(ins_form,"s_userdetails",handle_update_userdetailsentry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_userdetailsentry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			edituserdetails();
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_userdetailsentry(user_id)
{
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","user_id"];
		arr_fieldvalue=["deleteuserdetailslist",getprocessid(),user_id];
		ajax_post_field("s_userdetails",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			deleteuserdetails();
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function cancel_creation(){
	arr_fieldname =["task","process_id"];
	arr_fieldvalue =["abc",getprocessid()];
	post_field("s_userdetails",arr_fieldname,arr_fieldvalue,false);
}

//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_saveuserdetails(){
	var userid  = document.getElementById('a').value;
	var user_name  = document.getElementById('b').value;
	var password  = document.getElementById('c').value;
	var password1=document.getElementById('c1').value;
	var email_id  = document.getElementById('d').value;
	var mob  = document.getElementById('e').value;	
	var status  = document.getElementById('f').value;	
	var chk=document.getElementById('rolecode').value;
	var chkk=document.getElementById('gh').value;
	if((val_userid(userid)) && (val_username(user_name)) && (val_password(password)) && (val_password1(password1)) && (val_emailid(email_id)) && (val_mobileno(mob)) && (val_status(status)) && (val_check(chk)))
		return true;
	}
function VAL_edituserdetails(){
	var userid  = document.getElementById('a').value;
	var user_name  = document.getElementById('b').value;
	//var password  = document.getElementById('c').value;
	//var password1=document.getElementById('c1').value;
	var email_id  = document.getElementById('e').value;
	var mob  = document.getElementById('f').value;	
	var chk=document.getElementById('rolecode').value;
	
	if((val_userid(userid)) && (val_username(user_name)) && (val_emailid(email_id)) && (val_mobileno(mob)) && (val_check(chk)))
		return true;
	}
function VAL_resetpassword(){
	var password  = document.getElementById('c').value;
	var password1=document.getElementById('c1').value;
	if((val_password(password)) && (val_password1(password1)))
return true;
	}
//--------------------------------password---------------------------------------------------------------------------------------------------------------------------
function val_password(p_val){
if(p_val!=""){
	if(!VAL_ispwdok(p_val)){
		nic_alert("Your Current password is not proper!\n Password should have minimum of 6 character in length, atleast one special character [!@#$%^&*()[]{}|<>?~_], Minimum of 2 alphabet, atleast one upper case letter, one lowercase letter, one digit","V");
		document.getElementById('txt_password').value="";
		return false;
	}
}
else
{
	nic_alert("Please Enter Password ","V");
	return false;
}
return true;
}
//-------------------------------------repassword----------------------

function val_password1(p_val){
var password=document.getElementById('c').value;
if(p_val!=""){
	if(p_val.match(' ')){
		nic_alert("Password should not contain space","V");
		return false;		
	}
	else if (password!=p_val){
		nic_alert("Password re-entered does not match the given password ","V");
		return false;
	}
}
 else
	 {
	 	nic_alert("Please Re-enter Password ","V");
		return false;
	
	 }
 return true;
} 
  



function val_username(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to user name.","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid user name.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the user name","V");
		return false;
	}
	return true;
}
function val_status(p_val){
	if(p_val!="")
	{
		if(p_val!="Y"&&p_val!="N"){
			nic_alert("Invalid status(Y or N)","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the status","V");
		return false;
	}
	return true;
}

function val_check(p_val){
	if(p_val==""){
		nic_alert("Please select an item","V");
		return false;
	}
	return true;
}

function val_check_gh(p_val){
	if(p_val==""){
		nic_alert("Please select the Guest House","V");
		return false;
	}
	return true;
}


function val_search_data(user_id,name){

	
	if(!user_id==""){
		if(!isLEN(req_id,1,25)){
			 
			nic_alert("Invalid Length for User ID.","V");
			return false;
		}
		else if(!VAL_ANS(user_id)){
			 
			nic_alert("Invalid User ID.","V");
			return false;
		}
	}
	if(name!=""){
	if(!isLEN(name,1,50)){
		 
		nic_alert("Invalid Length for Name.","V");
		return false;
	}
	else if(!VAL_NAME(name)){
		 
		nic_alert("Invalid Name.","V");
		return false;
	}
	}
	
	return true;
}
