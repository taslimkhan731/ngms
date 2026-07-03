function show_form_add_document(){
	//gh_id  = document.getElementById('sel_id').value;
	//pid  = document.getElementById('pid').value;
	//VAL_showform(gh_id,pid);
	//if(pid!=0){
		arr_fieldname =["task","process_id"]; //,"gh_id","pid"
		arr_fieldvalue =["adddocument",getprocessid()]; //,gh_id,pid
		ajax_post_field_div("s_document",handle_show_form_add_document,arr_fieldname,arr_fieldvalue,'home_value');	
	//}
	//else{
		//nic_alert("Please select an item from the list","N");
		//return false;
	//}
}
function handle_show_form_add_document()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


$(document).ready(function() {
	    var inputs = 1; 
	    
	    $('#btnAdd').click(function() {
	        $('.btnDel:disabled').removeAttr('disabled');
	        var c = $('.clonedInput:first').clone(true);
	            c.children(':text').attr('name','input'+ (++inputs) );
	        $('.clonedInput:last').after(c);
	       
	       $(c).after(val(""));
	      
	    });
	    
	    $('.btnDel').click(function() {
	        if (confirm('continue delete?')) {
	            --inputs;
	            $(this).closest('.clonedInput').remove();
	            $('.btnDel').attr('disabled',($('.clonedInput').length  < 2));
	        }
	    });
	}); 

function view_pdf(process_id,attachment_id){
	arr_fieldname =["process_id","task","attachment_id"];
	arr_fieldvalue =[getprocessid(),"abc",attachment_id];
	post_field("s_ngm_fileread", arr_fieldname,arr_fieldvalue,true);
}

//show_form_edit_document
function show_form_edit_document(){

	//ins_form= document.getElementById('form-doc');
	var objchb = document.getElementsByName('r_id');
	var ret_id="";
	for(var i=0; i < objchb.length; i++) 
	{ 
		if(objchb[i].checked == true){
			
			if(!isANSD(objchb[i].value))
				{
					nic_alert("Invalid Selection","N");
				}
			else{
			ret_id=objchb[i].value;
			
			}
		}
	}
	
	if(ret_id=="")
	{
		nic_alert("Please select an item from the list","N");
	}else{
	arr_fieldname =["task","ret_id","process_id"];
	arr_fieldvalue =["editdocument",ret_id,getprocessid()];
	//post_field("s_document",arr_fieldname,arr_fieldvalue,'');	
	ajax_post_field_div("s_document",handle_show_form_edit_document,arr_fieldname,arr_fieldvalue,'home_value');	
	 //ajax_post_div(ins_form, "s_document",handledocument,arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

function handle_show_form_edit_document()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function removeattachment(attachment_id){
	ins_form= document.getElementById('removeitem');
	if(confirm("Do you want to delete data?")){
		arr_fieldname =["task","process_id","attachment_id"];
		arr_fieldvalue =["deleteattachment",getprocessid(),attachment_id];
		ajax_post_form_field(ins_form,"s_document",handleremoveattachment,arr_fieldname,arr_fieldvalue);
	}
}

function handleremoveattachment()
{
	
if(http.readyState == 4)
{
	results = http.responseText;
	
	if(results.substr(0,7)!= "Message")
		{	
		
		$('.hiderow').hide(800);	
	//	if ($('tr td:hasClass(.temp)'))
		}
	else
	{
		nic_alert(results.substr(8),"N");
	}
}

}

function show_form_delete_document(){
	var objchb = document.getElementsByName('r_id');

	var ret_id="";
	for(var i=0; i < objchb.length; i++) 
	{ 
		if(objchb[i].checked == true){
			
			if(!isANSD(objchb[i].value))
				{
					nic_alert("Invalid Selection","V");
				}
			else{
			ret_id=objchb[i].value;
			
			}
		}
	}
	
	if(ret_id=="")
	{
		nic_alert("Please select a document from the list","V");	
	}else{
	arr_fieldname =["task","ret_id","process_id"];
	arr_fieldvalue =["deletedocuments",ret_id,getprocessid()];
	//ajax_post_field_divH("s_document",arr_fieldname,arr_fieldvalue,'home_value');
	ajax_post_field_div("s_document",handle_show_form_edit_document,arr_fieldname,arr_fieldvalue,'home_value');	

	}
}

function delete_documents(doc_id){
	ins_form= document.getElementById('form-doc-delete');
	if(VAL_delete_documents()){
		 if(confirm("Do you want to delete data?")){
		 arr_fieldname =["task","process_id","doc_id"];
		   arr_fieldvalue =["dodeletedocuments",getprocessid(),doc_id];
		   //ajax_post(ins_form,"s_document",handledeletedoc,arr_fieldname,arr_fieldvalue);
		   //post_field("s_document",arr_fieldname,arr_fieldvalue,'true')
		   ajax_post_form_field(ins_form,"s_document",handledeletedoc,arr_fieldname,arr_fieldvalue);

		 }
	}
}

function handledeletedoc()
{
if(http.readyState == 4)
{
	results = http.responseText;
	
	if(results.substr(0,7)!= "Message")
		{
		
		nic_alert("Deleted Successfully.","N");
		//var n=results.split("-");
		//arr_fieldname=['task','process_id'];
		//arr_fieldvalue=["abc",getprocessid()];
		//post_field(n[0],arr_fieldname,arr_fieldvalue,'true');
		
		close_and_reload();
		//location.reload(); 
		// document.getElementById('test').submit();
		//post_field("s_document",arr_fieldname,arr_fieldvalue,'true');
		//close_nicpopup();
		}
	else
	{
		nic_alert(results.substr(8),"N");
	}
}

}

/////////////////////////////////////////////////*Validation*/////////////////////////////////////////////////////////

function validateFormAdd(){
var txt_title = document.forms["form-doc-add"]["txt_title"].value;
if(txt_title = null || txt_title==""){
	nic_alert("Please Enter Title","V");
	return false;
}
var txt_title = document.forms["form-doc-add"]["txt_title"].value;
if(!isLEN(txt_title,3,50)){
	nic_alert("Allowed Length of Title is in between 2 and 50","V");
	return false;
}
var txt_title = document.forms["form-doc-add"]["txt_title"].value;
if(!isANS(txt_title)){
	nic_alert("Only alphanumeric and space allowed","V");
	return false;
}

var txt_valid_from = document.forms["form-doc-add"]["txt_valid_from"].value;
if(txt_valid_from==""){
	nic_alert("Please Enter From Date","V");
	return false;
}
var txt_valid_from = document.forms["form-doc-add"]["txt_valid_from"].value;
if(!isLEN(txt_valid_from,10,10)){
	nic_alert("Please check your From Date","V");
	return false;
}

var txt_valid_to = document.forms["form-doc-add"]["txt_valid_to"].value;
if(txt_valid_to==""){
	alert("Please Enter To Date");
	return false;
}	    
var txt_valid_to = document.forms["form-doc-add"]["txt_valid_to"].value;
if(!isLEN(txt_valid_to,10,10)){
	alert("Please check your To Date");
	return false;
}

var txt_valid_from = document.forms["form-doc-add"]["txt_valid_from"].value;
var txt_valid_to = document.forms["form-doc-add"]["txt_valid_to"].value;

if(!dateCompTwo(txt_valid_from, txt_valid_to)){
	alert("Please check yourFrom Date is greater than To Date");
	return false;
}

var sel_createdby = document.forms["form-doc-add"]["txt_createdby"].value;
//val_userid(sel_createdby);
if(sel_createdby==""){
	alert("No created by user");
	return false;
}

var sel_doc = document.forms["form-doc-add"]["sel_doc"].value;
if(sel_doc==""){
	alert("Please select document type");
	return false;
}else if(!isLEN(sel_doc,2,2)){
	alert("Please check your document type");
	return false;
}else if(!VAL_N(sel_doc)){
	alert("Please Enter  Valid document type");
	return false;
}

var x = document.forms["form-doc-add"]["input1"].value;
if (x == null || x == "") {
	alert("Please Select a File");
	return false;
}

return true;
}

function validateFormUpdate(){
	var txt_title = document.forms["form-doc-update"]["txt_title"].value;
	if(txt_title = null || txt_title==""){
		alert("Please Enter Title");
		return false;
	}
	var txt_title = document.forms["form-doc-update"]["txt_title"].value;
	if(!isLEN(txt_title,3,50)){
		alert("Allowed Length of User id is in between 2 and 50");
		return false;
	}
	var txt_title = document.forms["form-doc-update"]["txt_title"].value;
	if(!isANS(txt_title)){
		alert("Only alphanumeric and space allowed");
		return false;
	}

	var txt_valid_from = document.forms["form-doc-update"]["txt_valid_from"].value;
	if(txt_valid_from==""){
		alert("Please Enter From Date");
		return false;
	}
	var txt_valid_from = document.forms["form-doc-update"]["txt_valid_from"].value;
	if(!isLEN(txt_valid_from,10,10)){
		alert("Please check your From Date");
		return false;
	}

	var txt_valid_to = document.forms["form-doc-update"]["txt_valid_to"].value;
	if(txt_valid_to==""){
		alert("Please Enter To Date");
		return false;
	}	    
	var txt_valid_to = document.forms["form-doc-update"]["txt_valid_to"].value;
	if(!isLEN(txt_valid_to,10,10)){
		alert("Please check your To Date");
		return false;
	}

	var txt_valid_from = document.forms["form-doc-update"]["txt_valid_from"].value;
	var txt_valid_to = document.forms["form-doc-update"]["txt_valid_to"].value;
	if(!dateCompTwo(txt_valid_from, txt_valid_to)){
		alert("Please check yourFrom Date is greater than To Date");
		return false;
	}

	var sel_createdby = document.forms["form-doc-update"]["txt_createdby"].value;
	//val_userid(sel_createdby);
	if(sel_createdby==""){
		alert("No created by user");
		return false;
	}

	var sel_doc = document.forms["form-doc-update"]["sel_doc"].value;
	if(sel_doc==""){
		alert("Please select document type");
		return false;
	}else if(!isLEN(sel_doc,2,2)){
		alert("Please check your document type");
		return false;
	}else if(!VAL_N(sel_doc)){
		alert("Please Enter  Valid document type");
		return false;
	}

	var x = document.forms["form-doc-update"]["input1"].value;
	if (x == null || x == "") {
		alert("Please Select a File");
		return false;
	}

	return true;
	}

function VAL_delete_documents(){
	
	var doc_id = document.forms["form-doc-delete"]["doc_id"].value;
	var title = document.forms["form-doc-delete"]["txt_title"].value;
	var reason = document.forms["form-doc-delete"]["txt_reason"].value;

if(!VAL_N(doc_id)){
	alert("Invalid document id");
	return false;
}

if(!isANS(title)){
	alert("Invalid title");
	return false;
}
if(reason==""){
	alert("Please enter reason");
	return false;
}else if(!isLEN(reason,2,150)){
	alert("Allowed Length of reason is in between 2 and 150");
	return false;
}else if(!VAL_REMARK(reason)){
	alert("Please Enter  Valid reason");
	return false;
}
return true;

}



function close_and_reload()

{
	close_nicpopup();
	arr_fieldname =["process_id","task"];
	arr_fieldvalue =[getprocessid(),"abc"];
	post_field("s_document", arr_fieldname,arr_fieldvalue,false);
	
	}
