$(document).ready(function (){
    $('table room_no:eq(1)').remove();
})

//occupancy click ocuupancy sub menu
function occupancy()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname =["task","process_id","gh_id","sel_block"];
			arr_fieldvalue =["occupancyreset",getprocessid(),gh_id,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_occupancy",handle_occupancy,arr_fieldname,arr_fieldvalue,'occupancy');	
	}
}
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_occupancy", arr_fieldname, arr_fieldvalue, 'header_div');
}


function handle_occupancy()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function occupancyold()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname =["task","process_id","gh_id"];
			arr_fieldvalue =["occupancylistold",getprocessid(),gh_id];
			$('#popup').show();
			ajax_post_field_div("s_occupancy",handle_occupancyold,arr_fieldname,arr_fieldvalue,'occupancy');	
	}
}
function handle_occupancyold()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//click GO Button
function viewroom()
{
	var block_id=document.getElementById('sel_block').value;
	var date=document.getElementById('in').value;
	var gh_id = document.getElementById('sel_id').value;
	if(VAL_viewroom()){
		arr_fieldname =["task","process_id","gh_id","block_id","date"];
		arr_fieldvalue =["occupancylistviewold",getprocessid(),gh_id,block_id,date];
		$('#popup').show();
		ajax_post_field_div("s_occupancy",handle_occupancyview,arr_fieldname,arr_fieldvalue,'viewdataOccupancy');	//occupancy
	}		
}
function handle_occupancyview()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function viewroomtoday()
{
	var block_id=document.getElementById('sel_block').value;
	
	//var date=document.getElementById('in').value;
	var gh_id = document.getElementById('sel_id').value;
	if(VAL_viewroomtoday()){
		arr_fieldname =["task","process_id","gh_id","block_id","date"];
		arr_fieldvalue =["occupancylistview",getprocessid(),gh_id,block_id,"12-12-2012 12:12"];
		$('#popup').show();
		ajax_post_field_div("s_occupancy",handle_occupancy,arr_fieldname,arr_fieldvalue,'occupancy');	//occupancy
	}		
}
function handle_occupancyviewtoday()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results);
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//Occupancy pdf
function occupancypdf(block_id,datee,gh_id)
{
	arr_fieldname =["task","process_id","block_id","datee","gh_id"];
	arr_fieldvalue =["occupancypdf",getprocessid(),block_id,datee,gh_id];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function occupancyoldpdf(block_id,datee,gh_id)
{
	arr_fieldname =["task","process_id","block_id","datee","gh_id"];
	arr_fieldvalue =["occupancyoldpdf",getprocessid(),block_id,datee,gh_id];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

//--------------------------------------------------------------------------------validation------------------------------------------------------------------------------

function VAL_viewroom(){
	var block_id=document.getElementById('sel_block').value;
	var date=document.getElementById('in').value;
	var gh_id = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select the City","N");
		return false;
	} 
	if(block_id==0){
		nic_alert("Please Select the Guest House","N");
		return false;
	} 
	if(date==""){
		nic_alert("Please Enter the date","N");
		return false;
	} 
	return true;
}

function VAL_viewroomtoday(){
	var block_id=document.getElementById('sel_block').value;
	//var date=document.getElementById('in').value;
	var gh_id = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select the City","N");
		return false;
	} 
	if(block_id==0){
		nic_alert("Please Select the Guest House","N");
		return false;
	} 
	/*if(date==""){
		nic_alert("Please Enter the date","N");
		return false;
	} */
	return true;
}


