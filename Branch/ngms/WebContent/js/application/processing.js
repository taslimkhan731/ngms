function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_processing", arr_fieldname, arr_fieldvalue, 'header_div');
}

//pagination
function pagination_data(page_no,gh_id){
	alert(page_no);
	alert(gh_id);
	div='home_value';
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	VAL_pagination_data(page_no,gh_id);
	arr_fieldname =["process_id","task","page_no","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"createlist",page_no,gh_id,sel_block];
	ajax_post_field_divH("s_processing", arr_fieldname,arr_fieldvalue,div);
}

//click Moderator sub menu
function show_modlist(){

	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","page_no","sel_block"];
		arr_fieldvalue =["createlist",getprocessid(),gh_id,"1",sel_block];
		ajax_post_field_div( "s_processing",handle_show_form_moderation1,arr_fieldname,arr_fieldvalue,'home_value');	
	}
}
function handle_show_form_moderation1()
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

function do_process(gh_id)
{
	var sel_block = document.getElementById('sel_block').value;
	var sign = $('input[name=gosignature]:checked').val();
	$('#popup').show();
	arr_fieldname  =["task","gh_id","process_id","sign","sel_block"];
	arr_fieldvalue =['doprocess',gh_id,getprocessid(),sign,sel_block];	
	ajax_post_field_div("s_processing",handle_do_process,arr_fieldname,arr_fieldvalue,'listdiv');	
}

function handle_do_process(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("All Requests Processed","N");
			show_modlist();
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//show_draftlist
function show_draftlist(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","page_no","sel_block"];
		arr_fieldvalue =["createdraftlist",getprocessid(),gh_id,page_no,sel_block];
		ajax_post_field_div( "s_processing",handle_show_draftlist,arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

function handle_show_draftlist()
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

//1.conformation report list sub menu
function show_conformation(page_no){

	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		$('#popup').show();
		arr_fieldname =["task","process_id","page_no","gh_id","sel_block"];
		arr_fieldvalue =["createlistconformation",getprocessid(),page_no,gh_id,sel_block];
		ajax_post_field_div("s_processing",handle_show_form_conformation,arr_fieldname,arr_fieldvalue,'home_value');	
	}
}
function handle_show_form_conformation()
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

function show_for_conf(pro_id){
	$('#popup').show();
	arr_fieldname=["task","process_id","pro_id"];
	arr_fieldvalue=["conformationforshow",getprocessid(),pro_id];
	ajax_post_field("s_processing",handle_show_fro_conf,arr_fieldname,arr_fieldvalue);	


}

function handle_show_fro_conf(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function do_accept(processid){
	$('#popup').show();
	arr_fieldname =["task","process_id","pro_id"];
	arr_fieldvalue =["acceptprocess",getprocessid(),processid];
	ajax_post_field_div( "s_processing",handle_do_accept,arr_fieldname,arr_fieldvalue,'home_value');	
}

function handle_do_accept()
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

function do_cancel(processid)
{
	if(confirm("Do you want to Reject?")){
		$('#popup').show();
		arr_fieldname =["task","process_id","pro_id"];
		arr_fieldvalue =['cancelprocess',getprocessid(),processid];	
		ajax_post_field_div("s_processing",handle_do_cancel,arr_fieldname,arr_fieldvalue,'home_value');
	}
}

function handle_do_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Cancelled","V");
			show_draftlist('1');
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function view_pdf_draft_process(pro_id){
	arr_fieldname =["process_id","task","pro_id"];
	arr_fieldvalue =[getprocessid(),"generatetestpdfdraft",pro_id];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

function accept_processofcorder(pro_id){
	office_order = null;
//	office_order= document.getElementById('office_order').value;
//	if(!VAL_GO(office_order))
//	{
//	return false;
//	}
	ins_form= document.getElementById('accept_process');
	arr_fieldname =["task","process_id","pro_id","office_order"];
	arr_fieldvalue =["acceptprocessorder",getprocessid(),pro_id,office_order];
	ajax_post_form_field(ins_form,"s_processing",handle_accept_processofcorder,arr_fieldname,arr_fieldvalue,'home_value');

}

function handle_accept_processofcorder()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Office Order Accepted Successfully","N");
			close_nicpopup();
			show_draftlist('1');
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
} 

//Search conform list
function searchconformlist(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var process =  document.getElementById('sel_process').value;
	var office =  document.getElementById('orderno').value;
	var date = document.getElementById('date').value;
	if(process==0 && office=="" && date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","process","office","date","page_no"];
		arr_fieldvalue=["searchconformlist",getprocessid(),gh_id,process,office,date,page_no];
		ajax_post_field_divH("s_processing",arr_fieldname,arr_fieldvalue,'home_valu');	
	}
}

function showconf(pro_id){
	$('#popup').show();
	arr_fieldname=["task","process_id","pro_id"];
	arr_fieldvalue=["conformationshow",getprocessid(),pro_id];
	ajax_post_field("s_processing",handle_conformation,arr_fieldname,arr_fieldvalue);	
}

function handle_conformation(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function view_pdf_process(pro_id){
	arr_fieldname =["process_id","task","pro_id"];
	arr_fieldvalue =[getprocessid(),"generatetestpdf",pro_id];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}


window.onload = function() {
	$('input.abc_check').on('change', function() {
		$('input.abc_check').not(this).prop('checked', false);
	});

};

//----------------------------------------------validation--------------------------------------------------------------------


function VAL_pagination_data(page_no,gh_id){
	val_pageno(page_no);
	val_ghid(gh_id);
}

function VAL_showform(gh_id,pid)
{
	val_ghid(gh_id);
	val_pid(pid);
}
function VAL_getghlist(gh_id)
{
	val_ghid(gh_id);
}
function VAL_vcid(vc_id)
{
	val_vcid(vc_id);
}
function validate_creation(p_method){
	count= document.getElementById('count2').value;
	for(i=1;i<=count;i++){
		var b = 'b'+i;
		var bb 	  = document.getElementById(b).value;
		var c='c'+i;
		var cc 	  = document.getElementById(c).value;
		var d='d'+i;
		var dd 	  = document.getElementById(d).value;

		if( bb !="" || cc !="" || dd !="")
		{
			if(bb != "")
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
			if(cc != ""){
				if(!isLEN(cc,1,10))
				{
					nic_alert("Allowed Length of beds(gends) is in between 1 and 100","N");
					return false;
				}
				else if(!VAL_N(cc))
				{
					nic_alert("Please Enter Valid beds(gens). Allowed numeric only.","N");
					return false;
				}
			}
			if(dd != ""){
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
		}
		else{
			nic_alert("Please enter data","N");
		}
		return true;
	}
}

function showEditGuestDetails(page_no)
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
		$('#popup').show();
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['listeditguestdetails',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_div("s_dataentry",handle_showEditGuestDetails,arr_fieldname,arr_fieldvalue,'home_value');
	}
}

function handle_showEditGuestDetails()
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

function editGuestDetails(req_id){
	gh_id  = document.getElementById('sel_id').value;
	val_ghid(gh_id);
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id","page_no"];
		arr_fieldvalue =["editguestdetails",getprocessid(),gh_id,req_id,"1"];
		ajax_post_field_div( "s_dataentry",handle_editGuestDetails,arr_fieldname,arr_fieldvalue,'home_value');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}

function handle_editGuestDetails()
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

function doeditGuestDetails()
{
	var ins_form= document.getElementById('formmod');
	req_id1  = document.getElementById('reqid').value;
	count=document.getElementById('rowCnt').value;
	var duringstay= $("input[name='duringstay']:checked").val();
	var checkedval;
	var checkedval1;
	var extval;
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
		ajax_post_form_field(ins_form,"s_processing",handle_doeditGuestDetails,arr_fieldname,arr_fieldvalue);
	}
}

function handle_doeditGuestDetails(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){

			nic_alert("Guest Details updated Successfully.","N");

			close_nicpopup();
			showEditGuestDetails('1');

		}else{
			nic_alert(results.substr(8),"V");
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
function val_mode_of_arrival(p_val){
	if(p_val=='s') 
	{
		nic_alert("Please select the mode of arrival","V");
		return false;
	} else{
		return true;
	}
}

//validate arrival_place
function val_arrival_place(p_val){
	if(p_val=="") 
	{
		nic_alert("Please enter the arrival place","V");
		return false;
	}
	else if(!isLEN(p_val,1,100))
	{
		nic_alert("Invalid Length for arrival place.","V");
		return false;
	}
	if(!isvalidaddress(p_val))
	{
		nic_alert("Invalid arrival place, only allow AlphaNumeric.","V");
		return false;
	}
	return true;
}


//validate arrival_time
function val_arrival_time(p_val){

	var from_date  = document.getElementById('from').value;

	if(p_val!="")
	{
		if(!isLEN(p_val,1,25)){
			nic_alert("Invalid Length for arraival time.","V");
			return false;
		}
		else if(!VAL_NC(p_val)){
			nic_alert("Invalid arraival time.","V");
			return false;
		}
		else if(dateCompTwo(from_date,p_val))
		{
			nic_alert("invalid Arrival Time,Only allowed Arrival date less than from date","V");
			return false;
		}
	}
	else
	{
		nic_alert("Please enter the arraival time","V");
		return false;
	}
	return true;
}

//validate no_seats_req
function val_no_seats_req(p_val){
	if(p_val=="") 
	{
		nic_alert("Please enter the no of seats req","V");
		return false;
	}
	else if(!isLEN(p_val,1,10))
	{
		nic_alert("Invalid Length for no of seats req.","V");
		return false;
	}
	if(!VAL_N(p_val))
	{
		nic_alert("Invalid no of seats req, only allow Numeric.","V");
		return false;
	}
	return true;
}


//validate flight number
function val_ftno(p_val){
	if(p_val=="") 
	{
		nic_alert("Please enter Flight/Train no","V");
		return false;
	}
	else if(!isLEN(p_val,1,50))
	{
		nic_alert("Invalid Length for Flight/Train no.","V");
		return false;
	}
	if(!isANS(p_val))
	{
		nic_alert("Invalid Flight/Train no, only allow Numeric.","V");
		return false;
	}
	return true;
}





//validate vc_id
function val_vcid(p_val){
	if(p_val=='z') 
	{	
		nic_alert("Please select the designation category","V");
		return false;
	}  
	return true; 
}

//validate quota
function val_type(p_val){

	if(p_val==0) 
	{
		nic_alert("Please select the quota type","V");
		return false;
	} else{
		return true;
	}
}



//validate block_id
function val_blockid(p_val){
	if(p_val==null) 
	{
		nic_alert("block_id is Null","N");
		return false;
	}
	else if(p_val.match(/^[\/ &a-zA-Z0-9]+$/))
	{ 
		if(!isLEN(p_val,1,10))
		{
			nic_alert("Invalid Length for block_id. Min(1) and Max(10).","N");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Invalid block_id. Allowed Characters are a-z A-Z 0-9 / space","N");
		return false;
	} 
}

function val_userid(p_val)
{
	if(p_val=="") 
	{
		nic_alert("Please enter the user id","V");
		return false;
	}
	else if(p_val.match(/^[-\/ &a-zA-Z0-9]+$/)) //(/^[\/ &a-zA-Z0-9]+$/)) 
	{ 
		if(!isLEN(p_val,1,25))
		{
			nic_alert("Invalid Length for userid. Min(1) and Max(25).","V");
			return false;
		}
		return true;
	}
	else
	{
		nic_alert("Invalid userid. Allowed Characters are a-z A-Z 0-9","V");
		return false;
	} 
}

function VAL_savedataentry(){
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var category= document.getElementById('category').value;
//	var email_id= document.getElementById('email').value;
	if(purpose=='Onduty'||purpose=='civil service(PT)'){
		if((val_fromdate(from_date)) && (val_todateonduty(to_date)) && (val_purpose(purpose)) &&(val_mobileno(mobile_no)) && /*(val_emailid(email_id)) && */(VAL_roommf()) /*&& (val_address(address1)) */&& (VAL_guest()))
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
		if((val_ghid(gh_id)) && (val_purpose(purpose)) && (val_category(category)) &&  (val_fromdate_public(from_date)) && (val_todate_purpose(to_date,purpose)) &&(val_pmobileno(mobile_no)) /*&& (val_pemailid(email_id))*/ && (VAL_roommf()) /*&& (val_address(address1)) */ && (VAL_guest()))
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
	/*if((val_fromdate(from_date)) && (val_todate(to_date)) && (val_purpose(purpose)) &&(val_mobileno(mobile_no)) && (val_emailid(email_id)) && (VAL_roommf()) && (val_address(address1)) && (VAL_guest()))
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
		}*/
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
		var desg  = document.getElementById('desg'+i).value;
		var dept	=document.getElementById('dept'+i).value;
		var address=document.getElementById('address'+i).value;
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) && (val_designation(desg)) && (val_department(dept)) && (val_address1(address)))
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
$(document).ready(function() {

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

