
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_guestdetails", arr_fieldname, arr_fieldvalue, 'header_div');
}
function roomreport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["monthlyroomsearch",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_guestdetails",handle_reportList, arr_fieldname,arr_fieldvalue,'list');
	}
}
function roomconveancyreport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["roomconveancyreport",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_guestdetails",handle_reportList, arr_fieldname,arr_fieldvalue,'list');
	}
}
function conveancyreport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["conveancyreport",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_guestdetails",handle_reportList, arr_fieldname,arr_fieldvalue,'list');
	}
}

function cancelledreport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
	arr_fieldname=["task","process_id","gh_id","sel_block"];
	arr_fieldvalue=["cancelreport",getprocessid(),gh_id,sel_block];
	ajax_post_field_div("s_guestdetails",handle_reportList, arr_fieldname,arr_fieldvalue,'list');
	}
}
function handle_reportList()
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
function VAL_submit(){
	 
	var from  = document.getElementById('date').value;
	if((val_fromdate(from)))
		return true;
}
function goreportstatus(){
	var from  = document.getElementById('date').value;
	var purpose  = document.getElementById('txt_pur').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(VAL_submit())
	{
		arr_fieldname=["task","process_id","from","gh_id","sel_block","purpose"];
		arr_fieldvalue=["doprocessavailabilty",getprocessid(),from,gh_id,sel_block,purpose];
		ajax_post_field_div("s_finalreport",handlegetavailability, arr_fieldname,arr_fieldvalue,'reportlist');
	}
}
function gomonthstatus(page_no){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var month = document.getElementById('sel_month').value;
	if(VAL_submit())
		{
	arr_fieldname=["task","process_id","from","month","gh_id","page_no","sel_block"];
	arr_fieldvalue=["room",getprocessid(),from,month,gh_id,page_no,sel_block];//allotprocessid
	ajax_post_field_div("s_guestdetails",handlegetavailability, arr_fieldname,arr_fieldvalue,'listlist');
		}
}
function viewroom(req_id){
	gh_id  = document.getElementById('sel_id').value;
	if(req_id!=0 && typeof req_id!="undefined"){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showroomview",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_guestdetails",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'hhhhh');
	}
	else{
		nic_alert("Please select guest City","N");

		return false;
	}
}
function gomonthRCstatus(page_no){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var month = document.getElementById('sel_month').value;
	if(VAL_submit())
		{
	arr_fieldname=["task","process_id","from","month","gh_id","page_no","sel_block"];
	arr_fieldvalue=["roomconveancy",getprocessid(),from,month,gh_id,page_no,sel_block];//allotprocessid
	ajax_post_field_div("s_guestdetails",handlegetavailability, arr_fieldname,arr_fieldvalue,'listlist');
		}
}
function gomonthcancelstatus(page_no){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	var purpose  = document.getElementById('txt_pur').value;
	var sel_block  = document.getElementById('sel_block').value;
	var month = document.getElementById('sel_month').value;
	if(VAL_submit())
		{
	arr_fieldname=["task","process_id","from","month","gh_id","sel_block","page_no","purpose"];
	arr_fieldvalue=["cancelpro",getprocessid(),from,month,gh_id,sel_block,page_no,purpose];//allotprocessid
	ajax_post_field_div("s_guestdetails",handlegetavailability, arr_fieldname,arr_fieldvalue,'listlist');
		}
}

function gomonthCstatus(page_no){
	var from  = document.getElementById('date').value;
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block  = document.getElementById('sel_block').value;
	var month = document.getElementById('sel_month').value;
	if(VAL_submit())
		{
	arr_fieldname=["task","process_id","from","month","gh_id","page_no","sel_block"];
	arr_fieldvalue=["conveancy",getprocessid(),from,month,gh_id,page_no,sel_block];//allotprocessid
	ajax_post_field_div("s_guestdetails",handlegetavailability, arr_fieldname,arr_fieldvalue,'listlist');
		}
}
function view_pdf_roomReport(req_id){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["process_id","task","req_id","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"generateRoomreport",req_id,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}
function viewroomconveancy(req_id){
	gh_id  = document.getElementById('sel_id').value;
	if(req_id!=0 && typeof req_id!="undefined"){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showroomconveancyview",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_guestdetails",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'hhhhh');
	}
	else{
		nic_alert("Please select guest City","N");

		return false;
	}
}
function viewconveancy(req_id){
	gh_id  = document.getElementById('sel_id').value;
	if(req_id!=0 && typeof req_id!="undefined"){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showconveancyview",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_guestdetails",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'hhhhh');
	}
	else{
		nic_alert("Please select guest City","N");

		return false;
	}
}
function viewcancelledPro(req_id){
	gh_id  = document.getElementById('sel_id').value;
	if(req_id!=0 && typeof req_id!="undefined"){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showcancelledview",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_guestdetails",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'hhhhh');
	}
	else{
		nic_alert("Please select guest City","N");

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
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
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
