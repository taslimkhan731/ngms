$(document).on('click', '#chck', function () {
	var cx=	$('#chck:checked').map(function() {return this.value;}).get().join(",");
	$('#hidvaluecancel').val(cx);
});
function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_allotment", arr_fieldname, arr_fieldvalue, 'header_div');
}

function getStatustOnduty(from,to,tablename,type,res_id,req_id){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var sel_room = document.getElementById('sel_room').value;
	arr_fieldname=["task","process_id","gh_id","block_id","from","to","room_no","tablename","type","res_id","req_id"];
	arr_fieldvalue=["vacantroomstatus",getprocessid(),gh_id,sel_block,from,to,sel_room,tablename,type,res_id,req_id];
	ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'vacantroomstatus');
}
function getStatustOndutyAddbed(from,to,tablename,type,res_id,req_id){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var sel_room = document.getElementById('sel_room').value;
	arr_fieldname=["task","process_id","gh_id","block_id","from","to","room_no","tablename","type","res_id","req_id"];
	arr_fieldvalue=["vacantroomstatusaddbed",getprocessid(),gh_id,sel_block,from,to,sel_room,tablename,type,res_id,req_id];
	ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'vacantroomstatus');
}

function saveAllotmentOndutyBed(req_id,gh_id,from,to,tablename,type,res_id){
	var sel_block  = document.getElementById('sel_block').value;
	var sel_room  = document.getElementById('sel_room').value;
	var rent  = document.getElementById('rent').value;
	//var roomtype  = document.getElementByName('r').value;
	$("input[name=background][value='some value']").prop("checked",true);
	var roomtype  = $('input:radio[name=r]:checked').val();
	
	arr_fieldname=["task","process_id","req_id","gh_id","from","to","block","room","tablename","type","res_id","roomtype","rent"];
	arr_fieldvalue=["allotonduty",getprocessid(),req_id,gh_id,from,to,sel_block,sel_room,tablename,type,res_id,roomtype,rent];
	ajax_post_field_div("s_allotment",handlesaveAllotmentOndutyBed, arr_fieldname,arr_fieldvalue,'list');
}

function handlesaveAllotmentOndutyBed(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			nic_alert("Alloted Successfully","N");
			close_nicpopup(); 
			allotmentlistAdditionalBed('1');
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function saveAllotmentOnduty(req_id,gh_id,from,to,tablename,type,res_id){
	var sel_block  = document.getElementById('sel_block').value;
	var sel_room  = document.getElementById('sel_room').value;
	
	//var roomtype  = document.getElementByName('r').value;
	

	$("input[name=background][value='some value']").prop("checked",true);
	var roomtype  = $('input:radio[name=r]:checked').val();
	
	arr_fieldname=["task","process_id","req_id","gh_id","from","to","block","room","tablename","type","res_id","roomtype"];
	arr_fieldvalue=["allotonduty",getprocessid(),req_id,gh_id,from,to,sel_block,sel_room,tablename,type,res_id,roomtype];
	ajax_post_field_div("s_allotment",handlesaveAllotmentOnduty, arr_fieldname,arr_fieldvalue,'list');
}

function handlesaveAllotmentOnduty(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			nic_alert("Alloted Successfully","N");
			close_nicpopup(); 
			allotmentlistOnduty('1');
		}else{
			nic_alert(results.substr(8),"N");
			close_nicpopup();
		}
	}
}

function close_model(sel_block){
	document.getElementById('sel_block').value = sel_block;
	close_nicpopup(); 
}

function getVacantRooms(from,to,tablename,type,res_id,req_id){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname=["task","process_id","gh_id","block_id","from","to","tablename","type","res_id","req_id"];
	arr_fieldvalue=["vacantroomss",getprocessid(),gh_id,sel_block,from,to,tablename,type,res_id,req_id];
	ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'vacantrooms');
}

function getVacantRoomsAddbed(from,to,tablename,type,res_id,req_id){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname=["task","process_id","gh_id","block_id","from","to","tablename","type","res_id","req_id"];
	arr_fieldvalue=["vacantroomsaddbed",getprocessid(),gh_id,sel_block,from,to,tablename,type,res_id,req_id];
	ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'vacantrooms');
}

//allotmentpdf
function allotmentpdf(gh_id,from,to,block)
{
	arr_fieldname =["task","process_id","gh_id","from","to","block"];
	arr_fieldvalue =["allotmentpdf",getprocessid(),gh_id,from,to,block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

//allotmentlistOnduty
function allotmentlistOnduty(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["listonduty",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'list');
	}
}

function allotmentlist(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["list",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'list');
	}
}

//allotmentlistAdditionalBed
function allotmentlistAdditionalBed(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["listAdditionalBed",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_allotment",handle_show_allot,arr_fieldname,arr_fieldvalue,'list');
	}
}

function allot_cancel_individual(req_id,tablename,type,res_id,from,to,gh_id,page_no){
	 

	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","page_no"];
	arr_fieldvalue=["allotcancelpopupindiv",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,page_no]; //
	ajax_post_field_div("s_allotment",handleallotcancelindiv, arr_fieldname,arr_fieldvalue,'list');
}
function handleallotcancelindiv(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 
		show_nicpopup(results); 
		$('.disable_room').hide();
		$('.enable_room').show();
		
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

var digitsOnly = /[1234567890]/g;

function restrictInput(myfield, e, restrictionType, checkdot) {
	/*if (!e)
				var e = window.event
			if (e.keyCode)
				code = e.keyCode;
			else if (e.which)
				code = e.which;
			var character = String.fromCharCode(code);

			// if user pressed esc... remove focus from field...
			if (code == 27) {
				this.blur();
				return false;
			}
			if ((code == 46)) {
		        var strvalue = myfield.value;
		        if (strvalue.indexOf(".") > 0){
		            return false;}
		    }*/
	if (event.keyCode == 8) {
		// let it happen, don't do anything
	}
	else {
		// Ensure that it is a number and stop the keypress
		if (event.keyCode < 48 || event.keyCode > 57) {
			nic_alert("Character Not Permitted in this field. Enter Number.","V")
			return false;
		}
	}
	/*if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && 
					code != 38 &&(code != 39 || (code == 39 && character == "'"))
					&& code != 40 && code != 46) {
				if (character.match(restrictionType)) {
					if (checkdot == "checkdot") {
						return !isNaN(myfield.value.toString() + character);
					} else {
						return true;
					}
				} else {nic_alert("Character Not Permitted in this field. Enter Number.","V");
					return false;
				}
			}*/

}

function handle_show_allot()
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

function changeallot(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["changelist",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_allotment",handle_show_chage,arr_fieldname,arr_fieldvalue,'list');
	}
		
}
function handle_show_chage()
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

function cancelallotment(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["cancellist",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_allotment",handle_show_cancel, arr_fieldname,arr_fieldvalue,'list');
	}
}
function cancelreservationAllotment(page_no){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
			arr_fieldname=["task","process_id","gh_id","page_no","sel_block"];
			arr_fieldvalue=["cancelreservationlist",getprocessid(),gh_id,page_no,sel_block];
			$('#popup').show();
			ajax_post_field_div("s_allotment",handle_show_cancel, arr_fieldname,arr_fieldvalue,'list');
	}
}
function handle_show_cancel()
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

function search_process_allot(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var processid =  document.getElementById('sel_process').value;
			var req_id=  document.getElementById('requestid').value;
			var name =  document.getElementById('name').value;
			var date = document.getElementById('date').value;
			if(req_id==""&&processid==0&&name==""&&date==""){
				nic_alert("Please Enter data for searching","N");
			}else{
				if(val_search_process_allot(req_id,processid,name,date)){
					arr_fieldname=["task","process_id","req_id","processid","gh_id","name","date","page_no"];
					arr_fieldvalue=["getsearchrequest",getprocessid(),req_id,processid,gh_id,name,date,page_no];
					ajax_post_field_divH("s_allotment", arr_fieldname,arr_fieldvalue,'search');
				}
			}
}

function search_process_allotonduty(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var processid =  document.getElementById('sel_process').value;
			var req_id=  document.getElementById('requestid').value;
			var name =  document.getElementById('name').value;
			var date = document.getElementById('date').value;
			if(req_id==""&&processid==0&&name==""&&date==""){
				nic_alert("Please Enter data for searching","N");
			}else{
				if(val_search_process_allot(req_id,processid,name,date)){
					arr_fieldname=["task","process_id","req_id","processid","gh_id","name","date","page_no"];
					arr_fieldvalue=["getsearchrequestonduty",getprocessid(),req_id,processid,gh_id,name,date,page_no];
					ajax_post_field_divH("s_allotment", arr_fieldname,arr_fieldvalue,'search');
				}
			}
}

function search_process_allotaddbed(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var processid =  document.getElementById('sel_process').value;
			var req_id=  document.getElementById('requestid').value;
			var name =  document.getElementById('name').value;
			var date = document.getElementById('date').value;
			if(req_id==""&&processid==0&&name==""&&date==""){
				nic_alert("Please Enter data for searching","N");
			}else{
				if(val_search_process_allot(req_id,processid,name,date)){
					arr_fieldname=["task","process_id","req_id","processid","gh_id","name","date","page_no"];
					arr_fieldvalue=["getsearchrequestaddbed",getprocessid(),req_id,processid,gh_id,name,date,page_no];
					ajax_post_field_divH("s_allotment", arr_fieldname,arr_fieldvalue,'search');
				}
			}
}

function search_change_allot(page_no){
	 
	var gh_id = document.getElementById('sel_id').value;
	var processid =  document.getElementById('sel_process').value;
			var req_id=  document.getElementById('requestid').value;
			var name =  document.getElementById('name').value;
			var date = document.getElementById('date').value;
			if(req_id==""&&processid==0&&name==""&&date==""){
				nic_alert("Please Enter data for searching","N");
			}else{
				if(val_search_process_allot(req_id,processid,name,date)){
				arr_fieldname=["task","process_id","req_id","processid","gh_id","name","date","page_no"];
				arr_fieldvalue=["searchrequestchange",getprocessid(),req_id,processid,gh_id,name,date,page_no];
				ajax_post_field_divH("s_allotment", arr_fieldname,arr_fieldvalue,'search');
				}
			}
	
	 
}

function val_search_process_allot(req_id,processid,name,date){

	if(processid!=""){
	if(!isLEN(processid,1,25)){
		 
		nic_alert("Invalid Length for Process ID.","V");
		return false;
	}
	else if(!VAL_N(processid)){
		 
		nic_alert("Invalid Process ID.","V");
		return false;
	}
	}
	if(!req_id==""){
		if(!isLEN(req_id,1,25)){
			 
			nic_alert("Invalid Length for Request ID.","V");
			return false;
		}
		else if(!VAL_ANHYP(req_id)){
			 
			nic_alert("Invalid Request ID.","V");
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
	if(date!=""){
	if(!isLEN(date,1,25)){
		 
		nic_alert("Invalid Length for Date.","V");
		return false;
	}
	else if(!VAL_NC(date)){ 
		 
		nic_alert("Invalid Date.","V");
		return false;
	}
	}
	
	return true;
}

function allot(req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","sel_block","page_no"];
	arr_fieldvalue=["allotpopup",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no];//allotprocessid
	ajax_post_field_div("s_allotment",handleallot, arr_fieldname,arr_fieldvalue,'list');
}
function handleallot(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 
		show_nicpopup(results); 
		$('.disable_room').hide();
		$('.enable_room').show();
		$('.nav-tabs').tab();
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

//allotondutyadditionalbed(
function allotondutyadditionalbed(req_id,tablename,type,res_id,from,to,gh_id,page_no,sel_block){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","sel_block","page_no"];
	arr_fieldvalue=["allotondutyaddbedpopup",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no];//allotprocessid
	ajax_post_field_div("s_allotment",handleallotondutyaddbed, arr_fieldname,arr_fieldvalue,'list');
}

function handleallotondutyaddbed(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 
		show_nicpopup(results); 
		$('.disable_room').hide();
		$('.enable_room').show();
		$('.nav-tabs').tab();
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function allotonduty(req_id,tablename,type,res_id,from,to,gh_id,page_no){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block =  document.getElementById('sel_block').value;
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","page_no","sel_block"];
	arr_fieldvalue=["allotondutypopup",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,page_no,sel_block];//allotprocessid
	ajax_post_field_div("s_allotment",handleallotonduty, arr_fieldname,arr_fieldvalue,'list');
}

function handleallotonduty(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 
		show_nicpopup(results); 
		$('.disable_room').hide();
		$('.enable_room').show();
		$('.nav-tabs').tab();
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function show_functions()
{
	$(function () {
		$('#alot').scroll(sticky_relocate);
	    sticky_relocate();
	});	
}

function sticky_relocate() {
    var window_top = $(window).scrollTop();
    var div_top = $('#sticky-anchor').offset().top;
    if (window_top > div_top) {
        $('#final_data').addClass('stick');
    } else {
        $('#final_data').removeClass('stick');
    }
}

function getavailability(req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","sel_block","page_no"];
	arr_fieldvalue=["doprocessavailabilty",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no];//allotprocessid
	document.getElementById('popup').style.display = 'block';
	ajax_post_field_div("s_allotment",handlegetavailability, arr_fieldname,arr_fieldvalue,'availability-chart');
}

function handlegetavailability(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 document.getElementById('popup').style.display = 'none';
		    show_functions();
		/*show_nicpopup(results); 
		$('.disable_room').hide();
		 $('.enable_room').show();*/
		 $("ul.nav-tabs a").click(function (e) {
			  e.preventDefault();  
			    $(this).tab('show');
			
		 });	
		 $('.tab-pane:first').addClass('active in'); 
		 $('.nav-tabs li:first').addClass('active'); 
		 $('.nav-tabs').tab();
		 $("#alot").tab();			
		$('.showTooltip').tooltip();
		}else{
			nic_alert(results.substr(8),"N");
			 document.getElementById('popup').style.display = 'none';
		}
	}
}

function save_allotment_data(gh_id,req_id,from,to,page_no){
	var ins_form= document.getElementById('allotment-data');
	console.log(ins_form);
	 var start = $(from).datepicker("getDate");
	 end = $(to).datepicker("getDate");
	 currentDate = new Date(start);
	 between = [];
		while (currentDate <= end) {
		 between.push(new Date(currentDate));
		 currentDate.setDate(currentDate.getDate() + 1);
		}
		if(VAL_room()){
			arr_fieldname=["task","process_id","req_id","gh_id","from","to","page_no"];
			arr_fieldvalue=["saveallotmentdata",getprocessid(),req_id,gh_id,from,to,page_no];
			$("popup").show();
			ajax_post_form_field_div(ins_form,"s_allotment",handle_save_allotment_data, arr_fieldname,arr_fieldvalue,'list');
		}
}

function VAL_room(){
	var count = 0;
	var value= document.getElementById('count').value;
	for(var i=0;i<value;i++){
		var value1= document.getElementById('idd'+i).value;
		var block= document.getElementById('block_idO'+value1).value;
		if(block=="")
		{
			count = count + 1;
		}
	}
	if(value==count){
		alert("Please select room number");
		return false;
	}
	return true;
}

function handle_save_allotment_data(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
		 nic_alert("Alloted Successfully","N");
		//show_nicpopup(results); 
		 close_nicpopup();
		 allotmentlist(n[1]);
		 $("popup").hide();
		}else{
			nic_alert(results.substr(8),"N");
			 $("popup").hide();
		}
	}
}

function search_cancel_allot(page_no){ 
	 
	var gh_id = document.getElementById('sel_id').value;
	var processid =  document.getElementById('sel_process').value;
			var req_id=  document.getElementById('requestid').value;
			var name =  document.getElementById('name').value;
			var date = document.getElementById('date').value;
			if(req_id==""&&processid==0&&name==""&&date==""){
				nic_alert("Please Enter data for searching","N");
			}else{
				if(val_search_process_allot(req_id,processid,name,date)){
				arr_fieldname=["task","process_id","req_id","processid","gh_id","name","date","page_no"];
				arr_fieldvalue=["searchrequestcancel",getprocessid(),req_id,processid,gh_id,name,date,page_no];
				ajax_post_field_divH("s_allotment", arr_fieldname,arr_fieldvalue,'search');
				}
			}
}

function allot_change(req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no,make_change){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","sel_block","page_no","make_change"];
	arr_fieldvalue=["allotchagepopup",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no,make_change]; //

	ajax_post_field_div("s_allotment",handleallotchange, arr_fieldname,arr_fieldvalue,'list');
}
function handleallotchange(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 
		show_nicpopup(results); 

		$('.disable_room').hide();
		$('.enable_room').show();
		
		
		}else{
			nic_alert(results.substr(8),"N");
			
		}
	}
}

function getchangeavailability(req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no,make_change){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","sel_block","page_no","make_change"];
	arr_fieldvalue=["doprocesschangeavail",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,sel_block,page_no,make_change];//allotprocessid
	$('#popup').show();
	ajax_post_field_div("s_allotment",handlegetchangeavailability, arr_fieldname,arr_fieldvalue,'availability-chart-change');
}
function handlegetchangeavailability(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		 show_functions();
		 $('#popup').hide();
		 $("ul.nav-tabs a").click(function (e) {
			  e.preventDefault();  
			    $(this).tab('show');
		 });	
		 $('.tab-pane:first').addClass('active in'); 
		 $('.nav-tabs li:first').addClass('active'); 
		 $('.nav-tabs').tab();
		 $("#alot").tab();
		/*show_nicpopup(results); 
		$('.disable_room').hide();
		 $('.enable_room').show();*/
		
		}else{
			
			nic_alert(results.substr(8),"N");
			$('#popup').hide();
		}
	}
}

function save_change_allotment_data(gh_id,req_id,from,to,page_no,make_change){
	var ins_form= document.getElementById('allotment-data');
	
	if(VAL_room()){
		arr_fieldname=["task","process_id","req_id","gh_id","from","to","page_no","make_change"];
		arr_fieldvalue=["savechangeallotdata",getprocessid(),req_id,gh_id,from,to,page_no,make_change];
		ajax_post_form_field_div(ins_form,"s_allotment",handle_save_changeallo_data, arr_fieldname,arr_fieldvalue,'list');
	}
}

function handle_save_changeallo_data(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 var n=results.split("-");
		 nic_alert("Allotement Changed Successfully","N");
		//show_nicpopup(results); 
		 close_nicpopup();
		 if(n[2]=="M"){
			 allotmentlist(n[1]);
		 }else if(n[2]=="C"){
		 changeallot(n[1]);
		 }
			
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}
function allot_cancel(req_id,tablename,type,res_id,from,to,gh_id,page_no){
	 
	var hidvaluecancel = document.getElementById('hidvaluecancel').value;
if(hidvaluecancel==""){
	nic_alert("Select Item(s) from the list","N");
	return false;
}
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","hidvaluecancel","page_no"];
	arr_fieldvalue=["allotcancelpopup",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,hidvaluecancel,page_no]; //
	ajax_post_field_div("s_allotment",handleallotcancel, arr_fieldname,arr_fieldvalue,'list');
}
function allot_cancel1(req_id,tablename,type,res_id,from,to,gh_id,page_no){
	 
	var hidvaluecancel = document.getElementById('hidvaluecancel').value;
if(hidvaluecancel==""){
	nic_alert("Select Item(s) from the list","N");
	return false;
}
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","hidvaluecancel","page_no"];
	arr_fieldvalue=["allotcancelreservationpopup",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,hidvaluecancel,page_no]; //
	ajax_post_field_div("s_allotment",handleallotcancel, arr_fieldname,arr_fieldvalue,'list');
}
function handleallotcancel(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 
		show_nicpopup(results); 
		$('.disable_room').hide();
		$('.enable_room').show();
		
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
}

function save_cancel_allotment_data(req_id,tablename,type,res_id,from,to,gh_id,hidvaluecancel,page_no){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","hidvaluecancel","page_no"];
	arr_fieldvalue=["savecancelallotdata",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,hidvaluecancel,page_no];//allotprocessid
	ajax_post_field_div("s_allotment",handle_save_cancelallo_data, arr_fieldname,arr_fieldvalue,'list');
}
function save_cancel_allotment_data1(req_id,tablename,type,res_id,from,to,gh_id,hidvaluecancel,page_no){
	arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","hidvaluecancel","page_no"];
	arr_fieldvalue=["savereservationcancelallotdata",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,hidvaluecancel,page_no];//allotprocessid
	ajax_post_field_div("s_allotment",handle_save_reservation_cancelallo_data, arr_fieldname,arr_fieldvalue,'list');
}
function handle_save_cancelallo_data(){
if(http.readyState == 4){
	results = http.responseText;
   
	if(results.substr(0,7)!= "Message"){
		var n=results.split("-");
	 nic_alert("Selected Allotement Cancelled Successfully","N");
	//show_nicpopup(results); 
	 close_nicpopup();
	 cancelallotment(n[1]);
	}else{
		nic_alert(results.substr(8),"N");
	}
}
}
function handle_save_reservation_cancelallo_data(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
		 nic_alert("Selected Allotement Cancelled Successfully","N");
		//show_nicpopup(results); 
		 close_nicpopup();
		 cancelreservationAllotment(n[1]);
		}else{
			nic_alert(results.substr(8),"N");
		}
	}
	}
function save_cancel_allot_data_indiv(req_id,tablename,type,res_id,from,to,gh_id,page_no){
arr_fieldname=["task","process_id","req_id","tablename","type","res_id","from","to","gh_id","page_no"];
arr_fieldvalue=["savecancelallotdataindiv",getprocessid(),req_id,tablename,type,res_id,from,to,gh_id,page_no];//allotprocessid
ajax_post_field_div("s_allotment",handle_save_cancelallo_data_indiv, arr_fieldname,arr_fieldvalue,'list');
}

function handle_save_cancelallo_data_indiv(){
if(http.readyState == 4){
results = http.responseText;

if(results.substr(0,7)!= "Message"){
	var n=results.split("-");
 nic_alert("Allotement Cancelled Successfully","N");
//show_nicpopup(results); 
 close_nicpopup();
 allotmentlist(n[1]);
}else{
	nic_alert(results.substr(8),"N");
}
}
}

/////////////////////////////////////////////////////////////////////////////////
function allotmentreport(){
	var gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname=["task","process_id","gh_id","sel_block"];
		arr_fieldvalue=["allotmentreport",getprocessid(),gh_id,sel_block];
		//alert(arr_fieldvalue);
		ajax_post_field_div("s_allotment",handleallotmentreport, arr_fieldname,arr_fieldvalue,'list');
	}

}


function handleallotmentreport(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
			 document.getElementById(resultDiv).innerHTML = results;	
			// document.getElementById('popup').style.display = 'none';
		}else{
			nic_alert(results.substr(8),"N");
			 //document.getElementById('popup').style.display = 'none';
		}
	}
}


function allotmentReportView(){
	var from  = document.getElementById('fromdate').value;
	var to  = document.getElementById('todate').value;
	var sel_block  = document.getElementById('sel_block').value;
	
	var gh_id  = document.getElementById('sel_id').value;
	
	
	//if(VAL_fromto())
	//{
	if(from==""||to==""){
		nic_alert("Please Enter From Date & To Date","N");
		return false;
	}
	if(sel_block==0){
		nic_alert("Please Select the Guest House","N");
		return false;
	}
	arr_fieldname=["task","process_id","from","to","gh_id","sel_block"];
	arr_fieldvalue=["allotmentReportView",getprocessid(),from,to,gh_id,sel_block];//allotprocessid
	ajax_post_field_div("s_allotment",handleallotmentReportView, arr_fieldname,arr_fieldvalue,'listdiv');
	//}
}
function handleallotmentReportView(){
	if(http.readyState == 4){
		results = http.responseText;
	   
		if(results.substr(0,7)!= "Message"){
		 document.getElementById(resultDiv).innerHTML = results;
		//document.getElementById('popup').style.display = 'none';
		//show_nicpopup(results); 
		//$('.disable_room').hide();
		// $('.enable_room').show();
		
		}else{
			nic_alert(results.substr(8),"N");
			// document.getElementById('popup').style.display = 'none';
		}
	}
}





/*$(document).on('mousemove', '#alot', function () {
	 $('.tab-pane:first').addClass('active in'); 
	 $('.nav-tabs li:first').addClass('active'); 
	 $('.nav-tabs').tab();
	 $("#alot").tab();
	 $("ul.nav-tabs a").click(function (e) {
		  e.preventDefault();  
		    $(this).tab('show');
		   
		    
		   
		});
	 
});*/

$(document).on('ready', '#alot', function () {
	 $('#tab a:last').tab('show');
});

$(document).on('load', '#alot', function () {
//Change the selector if needed
var $table = $('table.scroll'),
   $bodyCells = $table.find('tbody tr:first').children(),
   colWidth;

//Adjust the width of thead cells when window resizes
$(window).resize(function() {
   // Get the tbody columns width array
   colWidth = $bodyCells.map(function() {
       return $(this).width();
   }).get();
   
   // Set the width of thead columns
   $table.find('thead tr').children().each(function(i, v) {
       $(v).width(colWidth[i]);
   });    
}).resize(); // Trigger resize handler

});

$(function () {
   $('#tab a:last').tab('show');
	
	
	   
 });

$('.disable_room').hide();


$(document).on('ready', '#alot', function () {   

	$('.enable_room').hide();
    $('.disable_room').show();	 
});


