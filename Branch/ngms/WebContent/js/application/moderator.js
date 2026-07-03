function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_moderator", arr_fieldname, arr_fieldvalue, 'header_div');
}

var digitsOnly = /[1234567890]/g;
function getExtension(){
	if($("#ext").is(":checked")){
		$("#extdata").removeClass('hide');
	}
	else{
		$("#extdata").addClass('hide');
	}	

}
function searcheditONE(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var date = document.getElementById('date').value;
	if(req_id==""&&desig==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","sel_block","req_id","name","desig","date","page_no"];
		arr_fieldvalue=["searchedit",getprocessid(),gh_id,sel_block,req_id,name,desig,date,page_no];
		ajax_post_field_divH("s_dataentry",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}
function getExistingReq(){
	var gh_id = document.getElementById('sel_id').value;
	$('#nic-popup').modal('show');
	$('#popup').show();
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =['getExistingReq',getprocessid(),gh_id];	
	ajax_post_field_div("s_dataentry",handle_getExistingReq,arr_fieldname,arr_fieldvalue,'dataEntry');
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
		ajax_post_field_div("s_dataentry",handle_showEditGuestDetails,arr_fieldname,arr_fieldvalue,'listdiv');
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
		ajax_post_field_div( "s_dataentry",handle_editGuestDetails,arr_fieldname,arr_fieldvalue,'listdiv');	
	}
	else{
		nic_alert("Please select the list","N");
		return false;
	}
}
function editGuestDetailsMod(req_id){
	gh_id  = document.getElementById('sel_id').value;
	val_ghid(gh_id);
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id","page_no"];
		arr_fieldvalue =["editguestdetailsMod",getprocessid(),gh_id,req_id,"1"];
		ajax_post_field_div( "s_dataentry",handle_editGuestDetailsMod,arr_fieldname,arr_fieldvalue,'home_value');	
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
function handle_editGuestDetailsMod()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{

			//document.getElementById(resultDiv).innerHTML=results;
			show_nicpopup(results);
			$('#popup').hide();
		}
		else
		{
			nic_alert(results.substr(8),"N");
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
//EDITED BY ATHIRA
//selectExisting
function selectExisting(p_req_id,name,age,gender,designation,department,address){
	close_nicpopup();

	$('#exist_req_id').val(p_req_id);
	if(name=='null'){
		$('#name0').val("");
	}else{
		$('#name0').val(name);}
	if(age=='null'){
		$('#age0').val("");
	}else{
		$('#age0').val(age);}
	if(gender== 'null'){
		$('#gender0').val("");
	}else{
		$('#gender0').val(gender);
	}
	if(designation=='null'){
		$('#desg0').val("");
	}else{
		$('#desg0').val(designation);
	}
	alert("dept :"+department);
	if(department=='null'){
		$('#dept0').val('');
	}else{
		$('#dept0').val(department);

	}
	if(address=='null'){
		$('#address0').val("");
	}else{
		$('#address0').val(address);
	}

//	END EDITING	
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

	addcell(0,'checkbox','chkbox',2,ptrRef,tableRow);
	addcell2(1,'text',arr_fields[0],20,ptrRef,tableRow);
	addcell(2,'text',arr_fields[1],2,ptrRef,tableRow);
	addcellcb(3,arr_fields[2],ptrRef,tableRow,arr_value,arr_text);
	addcell(4,'text',arr_fields[3],20,ptrRef,tableRow);
	addcell(5,'text',arr_fields[4],20,ptrRef,tableRow);
	addcell_ta(6,arr_fields[5],ptrRef,tableRow);
	var count = parseInt(document.getElementById('rowCnt').value);
	count++;			
	document.getElementById('rowCnt').value = count;	


}

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
//	chkBoxColElem.name = p_name;
	chkBoxColElem.size = p_size;

	chkBoxColElem.id = p_name + p_ptrRef;
	chkBoxColElem.name = p_name + p_ptrRef;
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
	chkBoxCell.appendChild(chkBoxColElem);
}
function getDesg(){
	ins_form= document.getElementById('formmod');
	var category = document.getElementById('category').value;
	
	arr_fieldname =["task","process_id","category"];
	arr_fieldvalue =["getdesignation",getprocessid(),category];
	ajax_post_form_field_divH(ins_form, "s_dataentry", arr_fieldname, arr_fieldvalue, 'desgtype');
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
		ajax_post_form_field(ins_form,"s_moderator",handle_doeditGuestDetails,arr_fieldname,arr_fieldvalue);
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
function doeditGuestDetailsMod()
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
	if(VAL_savedataentry1()){
		arr_fieldname=["task","process_id","checkedval","checkedval1","extval","req_id","familyval","duringstay"];
		arr_fieldvalue=["editformdata",getprocessid(),checkedval,checkedval1,extval,req_id1,familyval,duringstay];
		ajax_post_form_field(ins_form,"s_moderator",handle_doeditGuestDetailsMod,arr_fieldname,arr_fieldvalue);
	}
}

function handle_doeditGuestDetailsMod(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){

			nic_alert("Guest Details updated Successfully.","N");

			close_nicpopup();
			get_list_gh('1');

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
	if(purpose=='Onduty'||purpose=='civil service(PT)'){
		if((val_fromdate(from_date)) && (val_todateonduty(to_date)) && (val_purpose(purpose)) && (val_category(category)) && (val_desg(desg)) &&(val_mobileno(mobile_no)) /*&& (val_emailid(email_id))*/ && (VAL_roommf()) /*&& (val_address(address1))*/ && (VAL_guest()))
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
		if((val_ghid(gh_id)) && (val_purpose(purpose)) && (val_category(category)) && (val_desg(desg)) &&  (val_fromdate_public(from_date)) && (val_todate_purpose(to_date,purpose)) &&(val_pmobileno(mobile_no)) /*&& (val_pemailid(email_id))*/ && (VAL_roommf()) /*&& (val_address(address1))*/ && (VAL_guest()))
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
function VAL_savedataentry1(){
	var from_date  = document.getElementById('from').value;
	var to_date  = document.getElementById('to').value;
	var address1  = document.getElementById('addressoffice').value;
	var purpose  = document.getElementById('purpose').value;
	var mobile_no= document.getElementById('mobile').value;
	var category= document.getElementById('categoryy').value;
	var desg=document.getElementById('desg').value;
	if(purpose=='Onduty'||purpose=='civil service(PT)'){
		if((val_fromdate(from_date)) && (val_todateonduty(to_date)) && (val_purpose(purpose)) && (val_category(category)) && (val_desg(desg)) &&(val_mobileno(mobile_no)) /*&& (val_emailid(email_id))*/ && (VAL_roommf()) /*&& (val_address(address1))*/ && (VAL_guest()))
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
		if((val_ghid(gh_id)) && (val_purpose(purpose)) && (val_category(category))  && (val_desg(desg)) && (val_fromdate_public(from_date)) && (val_todate_purpose(to_date,purpose)) &&(val_pmobileno(mobile_no)) /*&& (val_pemailid(email_id))*/ && (VAL_roommf()) /*&& (val_address(address1))*/ && (VAL_guest()))
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
		if((val_name(name)) && (val_age(age)) && (val_gender(gender)) && (val_address1(address)))
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

//click Moderator main menu
function get_list_gh(page_no)
{
	gh_id  = document.getElementById('sel_id').value;
	sel_block  = document.getElementById('sel_block').value;
	if(validateChecking()){
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['createlist',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_divH("s_moderator",arr_fieldname,arr_fieldvalue,'listdiv');	
	}
}
function showCancelReservation(page_no)
{
	gh_id  = document.getElementById('sel_id').value;
	sel_block  = document.getElementById('sel_block').value;
	if(validateChecking()){
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['createcancelreservation',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_divH("s_moderator",arr_fieldname,arr_fieldvalue,'listdiv');	
	}	
}
//click editModerator sub menu
function show_form_editmoderation(page_no)
{
	gh_id  = document.getElementById('sel_id').value;
	VAL_getghlistt(gh_id);
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		//if (validateCheck()){
		//	return true;
		//}
		arr_fieldname =["task","gh_id","process_id","page_no","sel_block"];
		arr_fieldvalue =['createlistedit',gh_id,getprocessid(),page_no,sel_block];	
		ajax_post_field_divH("s_moderator",arr_fieldname,arr_fieldvalue,'listdiv');	
	}
}

//Search data list
function searchdata(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var category =  document.getElementById('category').value;
	var date = document.getElementById('date').value;
	var sel_block  = document.getElementById('sel_block').value;
	if(req_id==""&&desig==0&&name==""&&date==""&&category==0){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","req_id","name","desig","date","category","page_no","sel_block"];
		arr_fieldvalue=["search",getprocessid(),gh_id,req_id,name,desig,date,category,page_no,sel_block];
		ajax_post_field_divH("s_moderator",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}
function searchdata1(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var category =  document.getElementById('category').value;
	var date = document.getElementById('date').value;
	if(req_id==""&&desig==0&&name==""&&date==""&&category==0){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","req_id","name","desig","date","category","page_no"];
		arr_fieldvalue=["searchcancel",getprocessid(),gh_id,req_id,name,desig,date,category,page_no];
		ajax_post_field_divH("s_moderator",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}
function show_form_moderation(req_id){
	gh_id  = document.getElementById('sel_id').value;

	//if(gh_id!=0){
	//req_id= $("input[type='radio']:checked").val();
	//VAL_showformm(gh_id);
	if(req_id!=0 && typeof req_id!="undefined"){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformmod",getprocessid(),gh_id,req_id];
		ajax_post_field_div( "s_moderator",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'home_value');
	}
	//else{
	//nic_alert("Please select the list","N");

	//return false;
	//}
	//}
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

//click SAVE Button
function saveform_modd()
{
//	alert("inside saveform");
	var ins_form= document.getElementById('form_mod');
	var vc_id= document.getElementById('sel_vcid').value;
	var quota=document.getElementById('sel_q').value;
	gh_id  = document.getElementById('sel_id').value;
	var radioValue = $("input[name='conveyance']:checked").val();
	var radioValue1= $("input[name='duringstay']:checked").val();
	//VAL_getghlistt(gh_id);
	if(validate_creation("saveformmod")){
//		alert("inside validate_creation");
		$('#popup').show();
		arr_fieldname=["task","process_id","vc_id","gh_id","quota","vehicle","duringstay"];
		arr_fieldvalue=["saveformmod",getprocessid(),vc_id,gh_id,quota,radioValue,radioValue1];
		ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
	}
}

function handle_saveform_mod(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Moderated Successfully.","N");
			document.getElementById(resultDiv).innerHTML="";
			get_list_gh('1');
			close_nicpopup();
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function showcancelMod(){
	var req_id= document.getElementById('req_id1').value;
	alert(req_id);
	var remarksReason = document.getElementById('remarksReason').value;
	if(remarksReason == ""){
		nic_alert("Remarks should not be blank","V");
		return false
	}
	if(confirm("Do you want to cancel?")){
		$('#popup').show();
		arr_fieldname=["task","process_id","reqid","remarks"];
		arr_fieldvalue=["cancelformdata",getprocessid(),req_id,remarksReason];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_moderator",handle_cancel,arr_fieldname,arr_fieldvalue);	
	}
	//}	
}
function showcancel_reservation(reqid){
	if(confirm("Do you want to cancel?")){
		$('#popup').show();
		arr_fieldname=["task","process_id","reqid"];
		arr_fieldvalue=["cancelreservationformdata",getprocessid(),reqid];
		ajax_post_field("s_moderator",handle_cancel_reservation,arr_fieldname,arr_fieldvalue);	
	}
	//}	
}

function handle_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("cancelled","N");
			close_nicpopup();
			$('#myModal').modal('hide');
			$('#popup').hide();
			get_list_gh('1');
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}
function handle_cancel_reservation(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("cancelled","N");
			close_nicpopup();
			$('#myModal').modal('hide');
			showCancelReservation('1');
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
//			$('#popup').hide();
		}
	}
}
function showcancelmod1(){
	var req_id= document.getElementById('req_id1').value;
	var remarksReason = document.getElementById('remarksReason').value;
	if(remarksReason == ""){
		nic_alert("Remarks should not be blank","V");
		return false
	}
	if(confirm("Do you want to cancel?")){
		$('#popup').show();
		arr_fieldname=["task","process_id","req_id","remarks"];
		arr_fieldvalue=["cancelmodformdata",getprocessid(),req_id,remarksReason];
		//ajax_post_form_field(ins_form,"s_moderator",handle_saveform_mod,arr_fieldname,arr_fieldvalue);
		ajax_post_field("s_moderator",handle_edit_cancel,arr_fieldname,arr_fieldvalue);	
	}
	//}	
}

function handle_edit_cancel(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("cancelled","N");
			close_nicpopup();
			$('#myModal').modal('hide');
			$('#popup').hide();
			show_form_editmoderation('1');
			$('#popup').hide();
			//menu_url_call('s_dataentry?task=abc',getprocessid());
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

function setQuota(){
	ins_form= document.getElementById('form_mod');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["roomstatus",getprocessid(),gh_id];

	ajax_post_form_field_divH(ins_form, "s_moderator", arr_fieldname, arr_fieldvalue, 'roomdetails');
}

//Search edit list
function searchedit(page_no){
	var gh_id = document.getElementById('sel_id').value;
	var desig =  document.getElementById('desig').value;
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var category =  document.getElementById('category').value;
	var date = document.getElementById('date').value;
	if(req_id==""&&desig==0&&name==""&&date==""&&category == 0){
		nic_alert("Please Enter data for searching","N");
	}else{
		arr_fieldname=["task","process_id","gh_id","req_id","name","desig","date","category","page_no"];
		arr_fieldvalue=["searcheditlist",getprocessid(),gh_id,req_id,name,desig,date,category,page_no];
		ajax_post_field_divH("s_moderator",arr_fieldname,arr_fieldvalue,'home_value');	
	}
}

//click edit sub menu
function showedit(req_id){

	gh_id  = document.getElementById('sel_id').value;
	VAL_showformm(gh_id);
	if(req_id!=0){
		$('#popup').show();
		arr_fieldname =["task","process_id","gh_id","req_id"];
		arr_fieldvalue =["showformedit",getprocessid(),gh_id,req_id];
		ajax_post_field_div("s_moderator",handle_show_form_moderation,arr_fieldname,arr_fieldvalue,'home_value');	

	}
	else{
		nic_alert("Please select the City","N");
		return false;
	}
}

function deletelist(reqid){
	//if(validate_creation("deletecreation")){
	if(confirm("Do you want to delete?")){
		arr_fieldname=["task","process_id","reqid"];
		arr_fieldvalue=["deletelist",getprocessid(),reqid];
		ajax_post_field("s_moderator",handledeletelist,arr_fieldname,arr_fieldvalue);	
		//}
	}

}
function handledeletelist(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			nic_alert("Deleted","V");
			show_form_editmoderation('1');	 
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function setQuotaEdit(req_id){
	ins_form= document.getElementById('form_mod');
	var gh_id = document.getElementById('sel_id').value;

	arr_fieldname =["task","process_id","req_id","gh_id"];
	arr_fieldvalue =["roomstatusedit",getprocessid(),req_id,gh_id];

	ajax_post_form_field_divH(ins_form, "s_moderator", arr_fieldname, arr_fieldvalue, 'roomdetails');

	//ajax_post_form_field(ins_form,"s_processing",handle_accept_processofcorder,arr_fieldname,arr_fieldvalue,'home_value');
}

function check(i) {
	var chk = 'chk'+i;
	var req_id = document.getElementById(chk).value;
	document.getElementById('req_id').value= req_id;
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



//edit moderation
function editform_mod()
{
	var ins_form= document.getElementById('form_mod');
	gh_id  = document.getElementById('sel_id').value;
	req_id  = document.getElementById('req_id').value;	
	block_id  = document.getElementById('sel_block').value;
	var radioValue = $("input[name='conveyance']:checked").val();
	var radioValue1= $("input[name='duringstay']:checked").val();
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
		console.log(ins_form)
		arr_fieldname =["task","process_id","gh_id","block_id","req_id","vehicle","duringstay"];
		arr_fieldvalue =["editformmod",getprocessid(),gh_id,block_id,req_id,radioValue,radioValue1];
		$('#popup').show();
		ajax_post_form_field_div(ins_form, "s_moderator",handle_showedit_moderation,arr_fieldname,arr_fieldvalue,'home_value');	

	}
}

function handle_showedit_moderation()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			nic_alert("Updated Successfully","N");
			close_nicpopup();
			$('#popup').hide();
		}
		else {
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

window.onload = function() {
	$('input.abc_check').on('change', function() {
		$('input.abc_check').not(this).prop('checked', false);
	});

};


//----------------------------------------------validation--------------------------------------------------------------------

function validateChecking(){
	gh_id  = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{
		return true;
	}
}

function VAL_pagination_data(page_no,gh_id){
	val_pageno(page_no);
	val_ghid(gh_id);
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

function VAL_type(quota)
{
	val_type(quota);
	return true;
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
	//alert("countttt"+count);
	for(var i=1;i<=count;i++){

		var b = 'b'+i;
		var bb 	  = document.getElementById(b).value;
		var c='c'+i;
		var cc 	  = document.getElementById(c).value;
//		var d='d'+i;
//		var dd 	  = document.getElementById(d).value;
		var f='f'+i;
		var ff 	  = document.getElementById(f).value;
		/*var g='g'+i;
		var gg 	  = document.getElementById(g).value;*/

//		alert("aaaaaaaa"+ bb+"bbbbb" + cc +dd +ff+gg );

		if( bb !=0 || cc !=0 /*||dd !=0*/ ||ff!=0/*||gg!=0*/){
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
		/*if(dd != 0){
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
		}*/if(ff != 0){
			if(!isLEN(ff,1,10))
			{
				nic_alert("Allowed Length of dormatery(gents) is in between 1 and 100","N");
				return false;
			}
			/*else if(!VAL_N(dd))
			{
				nic_alert("Please Enter Valid dormatery(gents). Allowed numeric only.","N");
				return false;
			}*/
		}
		/*if(gg != 0){
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
		}*/
//		alert("111111");
	}
//	} alert("2222222");

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
	
	$("#myModal").on("hidden.bs.modal", function(){
		$('#remarksReason').val('');
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