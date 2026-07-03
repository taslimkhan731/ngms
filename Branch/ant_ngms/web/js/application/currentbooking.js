function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_current_booking", arr_fieldname, arr_fieldvalue, 'header_div');
}

function getExtension(){
	if($("#ext").is(":checked")){
		$("#extdata").removeClass('hide');
	}
	else{
		$("#extdata").addClass('hide');
	}	

}
function getDesg(){
	ins_form= document.getElementById('formmod');
	var category = document.getElementById('category').value;
	
	arr_fieldname =["task","process_id","category"];
	arr_fieldvalue =["getdesignation",getprocessid(),category];
	ajax_post_form_field_divH(ins_form, "s_dataentry", arr_fieldname, arr_fieldvalue, 'desgtype');
}
function getExistingReq(){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =['getExistingReq',getprocessid(),gh_id];	
		ajax_post_field_div("s_dataentry",handle_getExistingReq,arr_fieldname,arr_fieldvalue,'dataEntry');
	}
}

function handle_getExistingReq()
{	
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function search_existing(){
	ins_form= document.getElementById('form-guest-selection');
	var gh_id = document.getElementById('gh_id').value;
	var req_id = document.getElementById('req_id').value;
	var name = document.getElementById('name').value;
	var date = document.getElementById('date').value;
	var desig = document.getElementById('desig').value;
	if(req_id==""&&name==""&&date==""&&desig==""){
		nic_alert("Please enter data to search","N");
	}else{
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =["selectedExistingReq",getprocessid(),gh_id];
		ajax_post_form_field_divH(ins_form, "s_dataentry", arr_fieldname, arr_fieldvalue, 'selectedReq');
	}
}

function selectExisting(p_req_id){
	close_nicpopup();
	$('#exist_req_id').val(p_req_id);
}

///de

$(document).ready(function() {
	$('#dynamicTable').on('click', '.autoc', function() {
		var $curRow = $(this).closest('tr'),
		$newRow = $curRow.clone(true);

		console.log($curRow);
		// $curRow.after($newRow);
		//  console.log('added');
	});






	$(document).on('change', '#roomtype', function() {
		var roomtype = $('#roomtype').find(":selected").val();
		if(roomtype=="D")
		{
			$('.roombox').hide();
			/*$('#rooms').attr('disabled','disabled');*/
		}
		else
		{
			$('.roombox').show();

			/*$('#rooms').removeAttr('disabled','disabled');*/
		}
	});



	$(document).on('change', '#chk', function() {

		if (this.checked) 
		{
			$('.data_box input[type="text"],.data_box select').prop("disabled", false);
			$('.data_box input[type="text"],.data_box select').prop('enabled',true);
		}
		else
		{
			$('.data_box input[type="text"],.data_box select').attr('disabled','');
		}
	});

	$(document).on('change', '#chk1', function() {

		if (this.checked) 
		{
			$('.data_box1 input[type="text"],.data_box1 select').prop("disabled", false);
			$('.data_box1 input[type="text"],.data_box1 select').prop('enabled',true);
		}
		else
		{
			$('.data_box1 input[type="text"],.data_box1 select').attr('disabled','');
		}
	});
});
function addcellcb(p_index,p_name,p_ptrRef,tableRow, arr_value, arr_text)
{
	var chkBoxCell = tableRow.insertCell(p_index);
	var chkBoxColElem = document.createElement('select');

	chkBoxColElem.name = p_name;
	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
	chkBoxCell.appendChild(chkBoxColElem);

	var i=0;
	for(i=0;i<arr_value.length;++i)
	{
		var option = document.createElement("option");
		option.text = arr_text[i];
		option.value = arr_value[i];
		try {

			chkBoxColElem.add(option, null);
		} 

		//Standard     
		catch(error) 
		{
			chkBoxColElem.add(option); 
			// IE only    
		}  

	}

}

function addcell_ta(p_index,p_name,p_ptrRef,tableRow)
{
	var chkBoxCell = tableRow.insertCell(p_index);
	var chkBoxColElem = document.createElement('textarea');
	chkBoxColElem.name = p_name;
	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
	chkBoxCell.appendChild(chkBoxColElem);
}


function addcell(p_index,p_type,p_name,p_size,p_ptrRef,tableRow)
{
	var chkBoxCell = tableRow.insertCell(p_index);
	var chkBoxColElem = document.createElement('input');
	chkBoxColElem.type = p_type;
	// chkBoxColElem.name = p_name;
	chkBoxColElem.size = p_size;
	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
	if(p_index == 2){
		chkBoxColElem.onkeypress = function(event) {  return restrictInput(this, event, digitsOnly); };
	}else if(p_index == 4){
		chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Designation'); };
	}else if(p_index == 5){
		chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Department'); };
	}
	chkBoxCell.appendChild(chkBoxColElem);
}



function addcell2(p_index,p_type,p_name,p_size,p_ptrRef,tableRow)
{

	var chkBoxCell = tableRow.insertCell(p_index);
	var chkBoxColElem = document.createElement('input');
	chkBoxColElem.type = p_type;
	chkBoxColElem.size = p_size;
	chkBoxColElem.onkeyup = function(event) {  getguestlist_db(event,this,p_ptrRef); };
	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
	chkBoxColElem.onkeypress = function(event) {  return restrictInputForNormal('Name'); };
	chkBoxCell.appendChild(chkBoxColElem);
}


function AddRow()
{

	$(".autocomplete-ul").remove();
	var tableId = document.getElementById('dynamicTable');
	var noOfRows = tableId.rows.length;
	var ptrRef = noOfRows-1;
	arr_value =["0","M","F"];
	arr_text =["--select--","Male","Female"];
	var tableRow = tableId.insertRow(noOfRows);	

	arr_fields=["name","age","gender","desg","dept","address"];

	addcell(0,'checkbox','chkbox',2,ptrRef,tableRow,0);
	addcell2(1,'text',arr_fields[0],30,ptrRef,tableRow);
	addcell(2,'text',arr_fields[1],4,ptrRef,tableRow,1);
	addcellcb(3,arr_fields[2],ptrRef,tableRow,arr_value,arr_text);
	addcell(4,'text',arr_fields[3],25,ptrRef,tableRow);
	addcell(5,'text',arr_fields[4],25,ptrRef,tableRow);
	addcell_ta(6,arr_fields[5],ptrRef,tableRow);
	var count = parseInt(document.getElementById('rowCnt').value);
	count++;			
	document.getElementById('rowCnt').value = count;	


}


function renameids()
{
	var i, cb, en, ed, len, tbl;

	var count=0;
	tbl = document.getElementById('dynamicTable');
	len = tbl.rows.length;
	for (i = 0; i < len-1; i++)
	{

		k=i;
		while(true )

		{
			cb = document.getElementById("chkbox" + k);
			if(cb !=null) 
			{
				txt_name = document.getElementById("name" + k);
				txt_age = document.getElementById("age" + k);
				cb_gender=document.getElementById("gender" + k);
				txt_desg = document.getElementById("desg" + k);
				txt_dept = document.getElementById("dept" + k);
				txt_address = document.getElementById("address" + k);


				cb.id = "chkbox" + i; //dont change
				cb.name = "chkbox" + i;

				txt_name.id = "name" + i;
				txt_name.name = "name" + i;

				txt_age.id = "age" + i;
				txt_age.name = "age" + i;

				cb_gender.id="gender" +i;
				cb_gender.name="gender" +i;


				txt_desg.id="desg"+i;
				txt_desg.name="desg"+i;

				txt_dept.id="dept"+i;
				txt_dept.name="dept"+i;

				txt_address.id="address"+i;
				txt_address.name="address"+i;


				break;
			}

			k=k+1;

		}
		count=count+1;

	}
	document.getElementById('rowCnt').value = count;	
}


function current_new_req()
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =['currentnewdataentry',getprocessid(),gh_id];	
		ajax_post_field_div("s_current_booking",handle_current_new_req,arr_fieldname,arr_fieldvalue,'current_booking_change');
	}

}

function handle_current_new_req()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

function save_data_current()
{
	var ins_form= document.getElementById('formmod');
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	count=document.getElementById('rowCnt').value;
	var duringstay= $("input[name='duringstay']:checked").val();
	var checkedval;
	var checkedval1;
	var extval;
	var familyval;
	//checked & unchecked
	if($("#chk").is(":checked")){
		if(VAL_savevehicle())return true;
		checkedval = 1;
	}
	else{
		checkedval = 0;
	}

	if($("#chk1").is(":checked")){
		if(VAL_savevehicle1())return true;
		checkedval1 = 1;
	}
	else{
		checkedval1 = 0;
	}

	if($("#ext").is(":checked")){
		extval = 1;
		exist_req_id  = document.getElementById('exist_req_id').value;
		if(exist_req_id==""){
			nic_alert("Please Select the existing request ID for extension","V");
			return false;
		}/*else if(exist_req_id!="G"&&exist_req_id!="H"&&exist_req_id!="P"){
				nic_alert("Invalid existing request ID for extension","V");
				return false;
			}*/
	}
	else{
		extval = 0;
	}	
	if($("#family").is(":checked")){
		familyval = 1;
	}
	else{
		familyval = 0;
	}
	if(VAL_savedataentry()){
		arr_fieldname=["task","process_id","gh_id","sel_block","checkedval","checkedval1","extval","familyval","duringstay"];
		arr_fieldvalue=["savedataformcurrent",getprocessid(),gh_id,sel_block,checkedval,checkedval1,extval,familyval,duringstay];
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_current_booking",handle_saveform_data,arr_fieldname,arr_fieldvalue);
	}
}
function handle_saveform_data(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){

			nic_alert("Created Successfully.","N");
			$('#popup').hide();
			$('#formmod')[0].reset();
			document.getElementById(results).innerHTML="";

		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}


////edit de



function current_edit_req(page_no) 
{


	val_ghid(gh_id);
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","gh_id","sel_block","process_id","page_no"];
		arr_fieldvalue =['createlistdatacurrent',gh_id,sel_block,getprocessid(),page_no];	
		ajax_post_field_divH("s_current_booking",arr_fieldname,arr_fieldvalue,'current_booking_change');
	}	
}

function VAL_savedataentry(){
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
//	var email_id= document.getElementById('email').value;
	var category= document.getElementById('category').value;
	if(purpose=='Onduty'||purpose=='civil service(PT)'){
		if((val_fromdate(from_date)) && (val_todateonduty(to_date)) && (val_purpose(purpose)) && val_category(category) &&(val_mobileno(mobile_no)) /*&& (val_emailid(email_id))*/ && (VAL_roommf())/* && (val_address(address1))*/ && (VAL_guest()))
		{
			if($("#chk").is(":checked")){

				var arrival_time  = document.getElementById('arrival').value;
				var temp_from = from_date.split(" ")[0]; 
				var temp_arrival = arrival_time.split(" ")[0];
				if(temp_arrival!=temp_from){
					if(confirm("From date and arrived date are different, Is it OK?")){
						return true;
					}
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}


		}
	}else{
		if((val_fromdate(from_date)) && (val_todateone(to_date)) && (val_purpose(purpose)) && val_category(category) &&(val_mobileno(mobile_no)) /*&& (val_emailid(email_id))*/ && (VAL_roommf())/* && (val_address(address1))*/ && (VAL_guest()))
		{
			if($("#chk").is(":checked")){

				var arrival_time  = document.getElementById('arrival').value;
				var temp_from = from_date.split(" ")[0]; 
				var temp_arrival = arrival_time.split(" ")[0];
				if(temp_arrival!=temp_from){
					if(confirm("From date and arrived date are different, Is it OK?")){
						return true;
					}
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
	}
}



function VAL_savevehicle(){
	var mode_of_arrival  = document.getElementById('mode').value;
	var arrival_time  = document.getElementById('arrival').value;
	var arrival_place  = document.getElementById('place').value;
	var no_seats_req  = document.getElementById('seat').value;
	var ftno = document.getElementById('ftno').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_no_seats_req(no_seats_req)) && (val_arrival_time(arrival_time)) && (val_arrival_place(arrival_place)) ) return false;
	return true;

}

function VAL_savevehicle1(){
	var mode_of_arrival  = document.getElementById('mode1').value;
	var arrival_time  = document.getElementById('arrival1').value;
	var arrival_place  = document.getElementById('place1').value;
	var no_seats_req  = document.getElementById('seat1').value;
	var ftno = document.getElementById('ftno1').value;
	if((val_mode_of_arrival(mode_of_arrival)) && (val_ftno(ftno)) && (val_no_seats_req(no_seats_req)) && (val_arrival_time1(arrival_time)) && (val_arrival_place(arrival_place)) ) return false;
	return true;

}

function VAL_roommf(){
	var nor_req  = document.getElementById('rooms').value;
	var nob_g_req  = document.getElementById('nbg').value;
	var nob_l_req  = document.getElementById('nbl').value;
	var nod_g_req  = document.getElementById('ndg').value;
	var nod_l_req  = document.getElementById('ndl').value;

	if((nor_req==0) && (nob_g_req==0) && (nob_l_req==0) && (nod_g_req==0) && (nod_l_req==0))
		//if(((val_rooms(nor_req)) || (val_nbg(nob_g_req)) || (val_nbl(nob_l_req)) || (val_ndg(nod_g_req)) || (val_ndl(nod_l_req))))
	{
		nic_alert("Please enter the any one data(room,male beds,female beds,male dormitory,female dormitory","V");
		return false;
	}
	else{
		if(nor_req>0){ 
			val_rooms(nor_req);

		}
		else if(nob_g_req>0){ 
			val_rooms(nor_req);

		}
		else if(nob_l_req>0){ 
			val_rooms(nor_req);

		}
		else if(nod_g_req>0){ 
			val_rooms(nor_req);

		}
		else if(nod_l_req>0){ 
			val_rooms(nor_req);

		}
	}
	return true;
}


function VAL_guest(){
	count=document.getElementById('rowCnt').value;
	var a=0;

	for(var i=0;i<count;i++)
	{
		var name  = document.getElementById('name'+i).value;
		var age	=document.getElementById('age'+i).value;
		var gender=document.getElementById('gender'+i).value;
//		var desg  = document.getElementById('desg'+i).value;
//		var dept	=document.getElementById('dept'+i).value;
		var address=document.getElementById('address'+i).value;
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) /*&& (val_designation(desg)) && (val_department(dept))*/ && (val_address1(address)))
		{
			a=1;
		}
		else
		{
			a=0;
		}

	}	
	if(a==1)
		return true;
}




function validatepurpose(){
	var cc=document.getElementById('purpose').value;
	if(cc==0){
		nic_alert("Please select an item","V");
		//nic_alert("Please select an purpose","V");
		return false;
	}
	return true;
}



function RemoveRow()
{
	var i, cb, len=2, tbl;
	tbl = document.getElementById('dynamicTable');
	len = tbl.rows.length;


	for (i=0; i < len-2; i++) {
		cb = document.getElementById("chkbox" + i);
		if (cb.checked) {   	 
			tbl.deleteRow(i+1);    		
			renameids();    	   
			RemoveRow();
		} 
		else
		{
			len++;
		}

	}


}
//validate gh_id
function val_ghid(p_val){
	if(p_val==0) 
	{
		nic_alert("Please select the City","V");
		return false;
	}
	return true;
}
//validate pagination
function VAL_pagination_data(page_no,gh_id){
	val_pageno(page_no);
	val_ghid(gh_id);
}

function showview2_H(req_id,gh_id,sel_block){
	if(req_id!=0){
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id"];
		arr_fieldvalue =["showformview",getprocessid(),gh_id,sel_block,req_id];
		ajax_post_field_div( "s_current_booking",handle_show_form_dataentryH,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}
function handle_show_form_dataentryH()
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
function showedit_req_H(req_id,ggh_id,sel_block){
	val_ghid(ggh_id);
	if(req_id!=0){
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id"];
		arr_fieldvalue =["showformedit",getprocessid(),ggh_id,sel_block,req_id];
		ajax_post_field_div( "s_current_booking",handle_show_form_dataentryH,arr_fieldname,arr_fieldvalue,'home_val');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}
function handle_show_form_dataentryH()
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
function editform_data_H()
{
	var ins_form= document.getElementById('formmod');
	req_id1  = document.getElementById('reqid').value;
	count=document.getElementById('rowCnt').value;
	var checkedval;
	var checkedval1;
	var extval;
	var duringstay= $("input[name='duringstay']:checked").val();
	if($("#chk").is(":checked")){
		if(VAL_savevehicle())return true;
		checkedval = 1;
	}
	else{
		checkedval = 0;
	}

	if($("#chk1").is(":checked")){
		if(VAL_savevehicle1())return true;
		checkedval1 = 1;
	}
	else{
		checkedval1 = 0;
	}
	if($("#ext").is(":checked")){
		extval = 1;
	}
	else{
		extval = 0;
	}	
	if($("#family").is(":checked")){
		familyval = 1;
	}
	else{
		familyval = 0;
	}
	if(VAL_savedataentry()){
		arr_fieldname=["task","process_id","checkedval","checkedval1","extval","req_id","familyval","duringstay"];
		arr_fieldvalue=["editformdata",getprocessid(),checkedval,checkedval1,extval,req_id1,familyval,duringstay];
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_current_booking",handle_showeditdataentryH,arr_fieldname,arr_fieldvalue);
	}
}
function handle_showeditdataentryH(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){

			nic_alert("Updated Successfully.","N");
			$('#popup').hide();
			close_nicpopup();
			//current_new_req();
			currentbooking2_deedit();

		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function deletelist_req_H(reqid,gh_id,sel_block){

	//VAL_vcid(vc_id);
	//if(validate_creation("saveformmod")){
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","reqid","gh_id","sel_block"];
		arr_fieldvalue=["deletelistRequest",getprocessid(),reqid,gh_id,sel_block];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_current_booking",handledeletelist_req_h,arr_fieldname,arr_fieldvalue);	
	}

	//}

}
function handledeletelist_req_h(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Request Deleted","V");
			//get_list_gh2();
			current_edit_req('1');
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function deletelist_H(reqid){
	//if(validate_creation("deletecreation")){
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","reqid"];
		arr_fieldvalue=["deletelist",getprocessid(),reqid];
		ajax_post_field("s_current_booking",handledeletelistH,arr_fieldname,arr_fieldvalue);	
		//}
	}

}
function handledeletelistH(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Moderation Deleted","V");
			//close_and_reload();
			current_edit_mod('1'); 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function showcancel2_H(reqid,gh_id,sel_block){
	if(confirm("Do you want to cancel?")){
		arr_fieldname=["task","process_id","reqid","gh_id","sel_block"];
		arr_fieldvalue=["cancelformdata",getprocessid(),reqid,gh_id,sel_block];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_current_booking",handle_cancelH,arr_fieldname,arr_fieldvalue);	
	}
	//}	
}
function handle_cancelH(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Request Cancelled","V");
			//current_new_req();
			current_edit_req('1');
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function current_new_mod(page_no)
{
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","gh_id","sel_block","process_id","page_no"];
		arr_fieldvalue =['createlist',gh_id,sel_block,getprocessid(),page_no];	
		ajax_post_field_divH("s_current_booking",arr_fieldname,arr_fieldvalue,'current_booking_change');	
	}
}

function setQuota(){
	ins_form= document.getElementById('form_mod');
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["task","process_id","gh_id","sel_block"];
	arr_fieldvalue =["roomstatus",getprocessid(),gh_id,sel_block];

	ajax_post_form_field_divH(ins_form, "s_current_booking", arr_fieldname, arr_fieldvalue, 'roomdetails');

	//ajax_post_form_field(ins_form,"s_processing",handle_accept_processofcorder,arr_fieldname,arr_fieldvalue,'home_value');
}

function setQuotaEdit(req_id){
	ins_form= document.getElementById('form_mod');
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["task","process_id","req_id","gh_id","sel_block"];
	arr_fieldvalue =["roomstatusedit",getprocessid(),req_id,gh_id,sel_block];

	ajax_post_form_field_divH(ins_form, "s_current_booking", arr_fieldname, arr_fieldvalue, 'roomdetails');

	//ajax_post_form_field(ins_form,"s_processing",handle_accept_processofcorder,arr_fieldname,arr_fieldvalue,'home_value');
}

function show_form_moderationH(req_id){
	gh_id  = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	//if(gh_id!=0){
	//req_id= $("input[type='radio']:checked").val();
	//VAL_showformm(gh_id);
	if(req_id!=0 && typeof req_id!="undefined"){
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id"];
		arr_fieldvalue =["showformmod",getprocessid(),gh_id,sel_block,req_id];
		ajax_post_field_div( "s_current_booking",handle_show_form_moderationH,arr_fieldname,arr_fieldvalue,'home_value');
	}
	//else{
	//nic_alert("Please select the list","N");

	//return false;
	//}
	//}
	else{
		nic_alert("Please select City","N");

		return false;
	}
}
function handle_show_form_moderationH()
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

window.onload = function() {
	$('input.abc_check').on('change', function() {
		$('input.abc_check').not(this).prop('checked', false);
	});

};

function saveform_modd_H()
{
	var ins_form= document.getElementById('form_mod');
	var vc_id= document.getElementById('sel_vcid').value;
	var quota=document.getElementById('sel_q').value;
	gh_id  = document.getElementById('sel_id').value;
	sel_block = document.getElementById('sel_block').value;
	//VAL_getghlistt(gh_id);
	if(validate_creation("saveformmod")){
		arr_fieldname=["task","process_id","vc_id","gh_id","sel_block","quota"];
		arr_fieldvalue=["saveformmod",getprocessid(),vc_id,gh_id,sel_block,quota];
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_current_booking",handle_saveform_mod_H,arr_fieldname,arr_fieldvalue);
	}
}

function handle_saveform_mod_H(){ 
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Moderated Successfully.","N");
			$('#popup').hide();
			document.getElementById(resultDiv).innerHTML="";
			current_new_mod('1');
			close_nicpopup();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function validate_creation(p_method){
	count= document.getElementById('count2').value;

	var vc_id= document.getElementById('sel_vcid').value;
	var quota=document.getElementById('sel_q').value;
	// if( val_vcid(vc_id))
	// if(val_type(quota))
	if(vc_id=='z') 
	{	
		nic_alert("Please select the designation category","V");

		return false;
	}  
	if(quota==0) 
	{
		nic_alert("Please select the quota type","V");
		return false;
	} 
	var s= 0;
	for(var i=1;i<=count;i++){

		var b = 'b'+i;
		var bb 	  = document.getElementById(b).value;
		var c='c'+i;
		var cc 	  = document.getElementById(c).value;
		var d='d'+i;
		var dd 	  = document.getElementById(d).value;
		var f='f'+i;
		var ff 	  = document.getElementById(f).value;
		var g='g'+i;
		var gg 	  = document.getElementById(g).value;

		if( bb !=0 || cc !=0 ||dd !=0 ||ff!=0||gg!=0){
			s=1;
		}
		if(bb != 0)
		{
			if(!isLEN(bb,1,10))
			{
				nic_alert("Allowed Length of room number is in between 1 and 100","N");
				return false;
			}
			else if(!VAL_N(bb))
			{
				nic_alert("Please Enter Valid room number. Allowed numeric only.","N");
				return false;
			}
		}
		if(cc != 0){
			if(!isLEN(cc,1,10))
			{
				nic_alert("Allowed Length of beds(gends) is in between 1 and 100","N");
				return false;
			}
			else if(!VAL_N(cc))
			{
				nic_alert("Please Enter Valid beds(gents). Allowed numeric only.","N");
				return false;
			}
		}
		if(dd != 0){
			if(!isLEN(dd,1,10))
			{
				nic_alert("Allowed Length of beds(ladies) is in between 1 and 100","N");
				return false;
			}
			else if(!VAL_N(dd))
			{
				nic_alert("Please Enter Valid beds(ladies). Allowed numeric only.","N");
				return false;
			}
		}
		if(ff != 0){
			if(!isLEN(ff,1,10))
			{
				nic_alert("Allowed Length of dormatery(gents) is in between 1 and 100","N");
				return false;
			}
			else if(!VAL_N(dd))
			{
				nic_alert("Please Enter Valid dormatery(gents). Allowed numeric only.","N");
				return false;
			}
		}
		if(gg != 0){
			if(!isLEN(gg,1,10))
			{
				nic_alert("Allowed Length of dormatery(ladies) is in between 1 and 100","N");
				return false;
			}
			else if(!VAL_N(gg))
			{
				nic_alert("Please Enter Valid dormatery(ladies). Allowed numeric only.","N");
				return false;
			}
		}
	}
	if(s==0){
		nic_alert("Please enter the data","V");
		return false;
	}
	return true;
}
function replacezero(textboxid)
{

	if(document.getElementById(textboxid).value==0 || document.getElementById(textboxid).value==0.0 || document.getElementById(textboxid).value==0.00)
		document.getElementById(textboxid).value="";

}
function placezero(textboxid)
{

	if(document.getElementById(textboxid).value=="")
		document.getElementById(textboxid).value=0;

}

//process_current
function process_current()
{
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		if(confirm("Do you want to process all current requests?")){
			arr_fieldname =["task","gh_id","sel_block","process_id","page_no"];
			arr_fieldvalue =['processcurrent',gh_id,sel_block,getprocessid(),'1'];	 // do_process
			ajax_post_field_divH("s_current_booking",arr_fieldname,arr_fieldvalue,'current_booking_change');	
		}
	}
}

function current_edit_mod(page_no)
{
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		arr_fieldname =["task","gh_id","sel_block","process_id","page_no"];
		arr_fieldvalue =['createlisteditmod',gh_id,sel_block,getprocessid(),page_no];	
		ajax_post_field_divH("s_current_booking",arr_fieldname,arr_fieldvalue,'current_booking_change');
	}

}
function showedit_H(req_id){

	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	VAL_showformm(gh_id);
	if(req_id!=0){
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id"];
		arr_fieldvalue =["showformeditmod",getprocessid(),gh_id,sel_block,req_id];
		ajax_post_field_div("s_current_booking",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'home_value');	

	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}
function handle_show_form_moderation()
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
function editform_mod()
{
	var ins_form= document.getElementById('form_mod');
	gh_id  = document.getElementById('sel_id').value;
	req_id  = document.getElementById('req_id').value;	
	//VAL_getghlistt(gh_id);
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


	if(validate_creation("editformmod")){
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["editformmod",getprocessid(),gh_id,req_id];
		$('#popup').show();
		ajax_post_form_field_div(ins_form, "s_current_booking",handle_showedit_moderation,arr_fieldname,arr_fieldvalue,'home_value');	

	}
}
function handle_showedit_moderation()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Updated Successfully","N");
			$('#popup').hide();
			close_nicpopup();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function VAL_showformm(gh_id,pid)
{
	val_ghid(gh_id);
}
function VAL_getghlistt(gh_id)
{
	val_ghid(gh_id);
	return true;
}
