$(document).on('click', '#c_can', function () {
	var cx=	$('#c_can:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});


function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_vehicleallotment", arr_fieldname, arr_fieldvalue, 'header_div');
}

//pagination list
function pagination_list(page_no){
	
	//val_pageno(page_no);
	arr_fieldname =["process_id","task","page_no"];
	arr_fieldvalue =[getprocessid(),"paginationlist",page_no];
	ajax_post_field_divH("s_vehicleallotment", arr_fieldname,arr_fieldvalue,'add');
}


function addvehicleallotment(page_no)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["addvehicleallotment",getprocessid(),page_no,gh_id,sel_block];
		ajax_post_field_div("s_vehicleallotment",handle_addvehicleallotment,arr_fieldname,arr_fieldvalue,'add');
	}
}
function handle_addvehicleallotment()
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


//add dataentry

function addvehicle(req_id)
{
		var gh_id = document.getElementById('sel_id').value;
		var sel_block = document.getElementById('sel_block').value;
		arr_fieldname =["task","process_id","req_id","gh_id","sel_block"];
		arr_fieldvalue =["addvehicle",getprocessid(),req_id,gh_id,sel_block];
		ajax_post_field_div("s_vehicleallotment",handle_addvehicle,arr_fieldname,arr_fieldvalue,'adds');
	
}
function handle_addvehicle()
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

//edit dataentry

function editvehicle(req_id, vreq_id)
{
	var gh_id = document.getElementById('sel_id').value;
		arr_fieldname =["task","process_id","req_id", "vehicle_reqid","gh_id"];
		arr_fieldvalue =["editvehicle",getprocessid(),req_id, vreq_id,gh_id];
		ajax_post_field_div("s_vehicleallotment",handle_addvehicle,arr_fieldname,arr_fieldvalue,'adds');
	
}
function handle_addvehicle()
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

//click edit vehicleallotment submenu
function editvehicleallotment(page_no)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no","sel_block","gh_id"];
		arr_fieldvalue =["editvehicleallotment",getprocessid(),page_no,sel_block,gh_id];
		ajax_post_field_div("s_vehicleallotment",handle_editvehicleallotment,arr_fieldname,arr_fieldvalue,'add');	
	}
}
function handle_editvehicleallotment()
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

//click Add vehicleallotment submenu
function deletevehicleallotment(page_no)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
		arr_fieldname =["task","process_id","page_no"];
		arr_fieldvalue =["deletevehicleallotment",getprocessid(),page_no];
		ajax_post_field_div("s_vehicleallotment",handle_deletevehicleallotment,arr_fieldname,arr_fieldvalue,'add');	
	}
}
function handle_deletevehicleallotment()
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



//click save vehicleallotment

function add_vehicleallotmententry()
{
	if(VAL_savevehicleallotment())
		{
			var gh_id = document.getElementById('sel_id').value;
			var sel_block = document.getElementById('sel_block').value;
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","gh_id","sel_block"];
			arr_fieldvalue=["savevehicleallotmententry",getprocessid(),gh_id,sel_block];
			ajax_post_form_field_div(ins_form,"s_vehicleallotment",handle_save_vehicleallotmententry,arr_fieldname,arr_fieldvalue,'adds');
		}
}
function handle_save_vehicleallotmententry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Created Successfully.","N");
			addvehicleallotment('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//click edit vehicleallotment

function edit_vehicleallotmententry(req_id)
{
			var gh_id = document.getElementById('sel_id').value;
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","req_id","gh_id"];
			arr_fieldvalue=["editvehicleallotmententry",getprocessid(),req_id,gh_id];
			ajax_post_form_field_div(ins_form,"s_vehicleallotment",handle_editvehicleallot,arr_fieldname,arr_fieldvalue,'add');
		
}
function handle_editvehicleallot()
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

//click edit vehicleallotment

function update_vehicleallotment(req_id, pvech_id,gh_id)
{
	if(VAL_savevehicleallotment1())
	{
			//var gh_id = document.getElementById('sel_id').value;
			var ins_form= document.getElementById('form-general');
			arr_fieldname=["task","process_id","req_id","gh_id","vehicle_reqid"];
			arr_fieldvalue=["updatevehicleallotmententry",getprocessid(),req_id,gh_id,pvech_id];
			ajax_post_form_field_div(ins_form,"s_vehicleallotment",handle_update_vehicleallotmententry,arr_fieldname,arr_fieldvalue,'add');
		}
}
function handle_update_vehicleallotmententry()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Update Successfully.","N");
			editvehicleallotment('1');
			document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function delete_vehicleallotmententry(req_id,pve_reqid)
{
	var gh_id = document.getElementById('sel_id').value;
	var ins_form= document.getElementById('form-general');
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","req_id","gh_id","vehicle_reqid"];
		arr_fieldvalue=["deletevehicleallotmentlist",getprocessid(),req_id,gh_id,pve_reqid];
		ajax_post_form_field(ins_form,"s_vehicleallotment",handledeletelist,arr_fieldname,arr_fieldvalue);
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			editvehicleallotment('1');
			//deletevehicleallotment('1');
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
	post_field("s_vehicleallotment",arr_fieldname,arr_fieldvalue,false);
}




//click edit vehicleallotment submenu
function showview(page_no)
{
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
	arr_fieldvalue =["vehiclemasterlist",getprocessid(),page_no,gh_id,sel_block];
	ajax_post_field_div("s_vehicleallotment",handle_vehiclemaster,arr_fieldname,arr_fieldvalue,'list');	
}

function showview1(page_no)
{
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
	arr_fieldvalue =["vehiclemasterlists",getprocessid(),page_no,gh_id,sel_block];
	ajax_post_field_div("s_vehicleallotment",handle_vehiclemaster,arr_fieldname,arr_fieldvalue,'list');	
}
function handle_vehiclemaster()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//document.getElementById(resultDiv).innerHTML=results;
			show_nicpopup(results);
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
//end
function addmaster(driver_id,driver_name,driver_mobileno,vehicle_no,vehicle_description,protocol_name,protocol_mobileno)
{
	document.getElementById('b_vehicledetails').value=vehicle_description;
	document.getElementById('c_vehicleno').value=vehicle_no;
	document.getElementById('d_drivername').value=driver_name;
	document.getElementById('e_drivermobileno').value=driver_mobileno;
	document.getElementById('d_protocolname').value=protocol_name;
	document.getElementById('e_protocolmobileno').value=protocol_mobileno;
	close_nicpopup();
	
}
function addmaster1(driver_id,driver_name,driver_mobileno,vehicle_no,vehicle_description,protocol_name,protocol_mobileno)
{
	document.getElementById('vehicledetails').value=vehicle_description;
	document.getElementById('vehicleno').value=vehicle_no;
	document.getElementById('drivername').value=driver_name;
	document.getElementById('drivermobileno').value=driver_mobileno;
	document.getElementById('protocolname').value=protocol_name;
	document.getElementById('protocolmobileno').value=protocol_mobileno;
	close_nicpopup();
	
}
function handle_master()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//document.getElementById(resultDiv).innerHTML=results;
			close_nicpopup();
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
///REPORT Vehicle Allotment

function vehiclereport(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{
	arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
	arr_fieldvalue=["vehiclesearch",getprocessid(),gh_id,page_no,sel_block];
	ajax_post_field_div("s_vehicleallotment",handleallot, arr_fieldname,arr_fieldvalue,'add');
	}
}
function handleallot(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

///REPORT Vehicle Allotment

function vehiclereportreport(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the Guest House","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
	arr_fieldvalue=["vehiclesearch",getprocessid(),gh_id,page_no,sel_block];
	ajax_post_field_div("s_vehicleallotment",handleallotr, arr_fieldname,arr_fieldvalue,'list');
	}
}
function handleallotr(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			}else{
			nic_alert(results.substr(8),"N");
		}
	}
}
function govehiclestatus(page_no){
	var from  = document.getElementById('date').value;
	var status  = document.getElementById('hidvalue').value;
	var sel_block  = document.getElementById('sel_block').value;
	var gh_id  = document.getElementById('sel_id').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	}else if(from==""){
		nic_alert("Please Select the date","N");
		return false;
	} else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","from","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["doday",getprocessid(),from,gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handlegetavailability, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handlegetavailability(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}


function todays(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var status  = document.getElementById('hidvalue').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	} else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["dotoday",getprocessid(),gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handletoday, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handletoday(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}


function yesterdays(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var status  = document.getElementById('hidvalue').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	} else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["doyesterday",getprocessid(),gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handleyesterday, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handleyesterday(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}


function tomorrows(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var status  = document.getElementById('hidvalue').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
		return false;
	}else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
		return false;
	} else if(status==""){
		nic_alert("Please Select the radio button","N");
		return false;
	}else{
		arr_fieldname=["task","process_id","gh_id","page_no","status","sel_block"];
		arr_fieldvalue=["dotomorrow",getprocessid(),gh_id,page_no,status,sel_block];//allotprocessid
		ajax_post_field_div("s_vehicleallotment",handletomorrow, arr_fieldname,arr_fieldvalue,'listdiv');
	}
}
function handletomorrow(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}

function pdfdata(date,gh_id,sel_block){
	var status  = document.getElementById('hidvalue').value;
	arr_fieldname =["process_id","task","date","status","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"vehiclepdf",date,status,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}
//end
//----------------------------------------------------------------------------validation---------------------------------------------------------------------------

function VAL_savevehicleallotment(){
	var is_veh  = document.getElementById('is_veh').value;
	var is_veh1  = document.getElementById('is_veh1').value;
	var req_id  = document.getElementById('a_reqid').value;
	
	if(is_veh == "1"){
		
		var vehicle_details  = document.getElementById('b_vehicledetails').value;
		var vehicle_start_time  = document.getElementById('h_pick').value;
		var vehicle_end_time  = document.getElementById('i_releave').value;
		var vehicle_no  = document.getElementById('c_vehicleno').value;
		var driver_name  = document.getElementById('d_drivername').value;
		var driver_mobileno  = document.getElementById('e_drivermobileno').value;
		var time_of_report  = document.getElementById('f_timeofreport').value;
		var place_of_report  = document.getElementById('g_placeofreport').value;
		if((val_req_id(req_id)) && (val_vehicle_details(vehicle_details)) && (val_vehicle_no(vehicle_no)) && (val_driver_name(driver_name)) && (val_driver_mobileno(driver_mobileno)) && (val_time_of_report(time_of_report)) && (val_place_of_report(place_of_report)) && (val_vehicle_time_comp(time_of_report,vehicle_start_time,vehicle_end_time)))
			return true;
		}
}
	function VAL_savevehicleallotmentnext(){
		var is_veh1  = document.getElementById('is_veh1').value;
		var req_id  = document.getElementById('a_reqid').value;
	if(is_veh1 == "1"){
		
		var time1  = document.getElementById('arr1').value;
		var place1  = document.getElementById('arrp1').value;
		var driver_name  = document.getElementById('drivername').value;
		var driver_mobileno  = document.getElementById('drivermobileno').value;
		var vehicle_no  = document.getElementById('vehicleno').value;
		var vehicle_details  = document.getElementById('vehicledetails').value;
		var start_time  = document.getElementById('t_pick').value;
		var end_time  = document.getElementById('t_releave').value;
		if((val_req_id(req_id)) && (val_vehicle_details(vehicle_details)) && (val_vehicle_no(vehicle_no)) && (val_driver_name(driver_name)) && (val_driver_mobileno(driver_mobileno)) && (val_time_of_report(time1)) && (val_place_of_report(place1)) && (val_vehicle_time_comp1(time1,start_time,end_time)))
			return true;
	}
	
	}

function VAL_savevehicleallotment1(){
		var req_id  = document.getElementById('a_reqid').value;
		var vehicle_details  = document.getElementById('b_vehicledetails').value;
		var vehicle_start_time  = document.getElementById('h_pick').value;
		var vehicle_end_time  = document.getElementById('i_releave').value;
		var vehicle_no  = document.getElementById('c_vehicleno').value;
		var driver_name  = document.getElementById('d_drivername').value;
		var driver_mobileno  = document.getElementById('e_drivermobileno').value;
		var time_of_report  = document.getElementById('f_timeofreport').value;
		var place_of_report  = document.getElementById('g_placeofreport').value;
		if((val_req_id(req_id)) && (val_vehicle_details(vehicle_details)) && (val_vehicle_no(vehicle_no)) && (val_driver_name(driver_name)) && (val_driver_mobileno(driver_mobileno)) && (val_time_of_report(time_of_report)) && (val_place_of_report(place_of_report)) && (val_vehicle_time_comp(time_of_report,vehicle_start_time,vehicle_end_time)))
			return true;
}
function val_vehicle_details(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,200)){
			nic_alert("Invalid Length for to vehicle details.","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid vehicle details.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the vehicle details","V");
		return false;
	}
	return true;
}
function val_vehicle_no(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,50)){
			nic_alert("Invalid Length for to vehicle number.","V");
			return false;
		}
		else if(!VAL_ANS(p_val)){
			nic_alert("Invalid vehicle number.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the vehicle number","V");
		return false;
	}
	return true;
}

function val_driver_name(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for driver name.","V");
			return false;
		}
		else if(!VAL_AS(p_val)){
			nic_alert("Invalid driver name only allowed alphabet and space.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the driver name","V");
		return false;
	}
	return true;
}
function val_driver_mobileno(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,15)){
			nic_alert("Invalid Length for driver mobile number.","V");
			return false;
		}
		else if(!VAL_N(p_val)){
			nic_alert("Invalid driver mobileno, only allow numeric.","V");
			return false;
		}
		else if(!VAL_MOB(p_val)){
			nic_alert("Invalid driver mobileno.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the driver mobile no","V");
		return false;
	}
	return true;
}
function val_time_of_report(p_val){
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			 
			nic_alert("Invalid Length for time of report.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			 
			nic_alert("Invalid time of report.","V");
			return false;
		}
		
	}
	else
	{
		nic_alert("Please enter the time of report","V");
		return false;
	}
	return true;
}

function val_place_of_report(p_val){
	
	if(p_val!="")
	{
		if(!isLEN(p_val,1,100)){
			nic_alert("Invalid Length for place of report.","V");
			return false;
		}
		else if(!isANS(p_val)){
			nic_alert("Invalid  place of report.","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please Enter the  place of report","V");
		return false;
	}
	return true;
}



function val_vehicle_start_time(p_val){
	var time_of_report  = document.getElementById('f').value;
	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			 
			nic_alert("Invalid Length for time of report.","V");
			return false;
		}
		else if(time_of_report<=p_val)
			{
			nic_alert("vehicle start time lessthan or equalto time of report .","V");
			return false;
			}
		else if(!VAL_NC(p_val)){
			 
			nic_alert("Invalid time of report.","V");
			return false;
		}
		
	}
	else
	{
		nic_alert("Please enter the vehicle start time","V");
		return false;
	}
	return true;
}

function val_vehicle_time_comp(time_of_report,vehicle_start_time,vehicle_end_time){
	
		if(isdateandtime_between(time_of_report,vehicle_start_time,vehicle_end_time))
		{
			nic_alert("Please enter the time of report properly","V");
			return false;
		}
	
	return true;
}

function val_vehicle_time_comp1(time_of_report,vehicle_start_time,vehicle_end_time){
	
	if(isdateandtime_between(time_of_report,vehicle_start_time,vehicle_end_time))
	{
		nic_alert("Please enter the time of report properly","V");
		return false;
	}

return true;
}