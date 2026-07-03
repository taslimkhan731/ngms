var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
var b = ['', '', 'Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety'];


function inWords(num) {

	if ((num = num.toString()).length > 9) return 'overflow';
	n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
	if (!n) return; var str = '';
	str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
	str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
	str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
	str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
	str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'Only ' : '';

	return str;

}

$(document).on('change', '#chck', function() {

	if($(this).is(":checked")){
		$(this).closest('tr').addClass("success"); 
	}else{
		$(this).closest('tr').removeClass("success");  
	}
});


function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_checkin", arr_fieldname, arr_fieldvalue, 'header_div');
}

function view_pdf_checkinslip(tr_id,gh_id){
	var sel_block = document.getElementById('sel_block').value;
	arr_fieldname =["process_id","task","tr_id","gh_id","sel_block"];
	arr_fieldvalue =[getprocessid(),"genPdfCheckinSlip",tr_id,gh_id,sel_block];
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
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



$(document).on('click', '#chck', function () {
	var cx=	$('#chck:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});

$(function () {
	$('.btn btn-success').click(function () { 
		if ($('.chck:checked').length) {
			var chkId ;
			$('.chck:checked').each(function () {
				chkId += $(this).val();
			});
			chkId = chkId.slice(0, -1);

		}
		else {
			// alert('Nothing Selected');
		}
	});
});




var checkboxes = { 
		red: getElementByID('chck'),
};

var resultMessage = "";
for (var name in checkboxes) {
	resultMessage += name + ": " + checkboxes[name].selected + "\n";
};

//alert(resultMessage);



function checkin(page_no)
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
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =["checkinlist",getprocessid(),gh_id,sel_block,page_no];
		ajax_post_field_div("s_checkin",handle_checkinlistc,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_checkinlistc()
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

//additionalBed
function additionalBed(page_no)
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
		arr_fieldname =["task","process_id","gh_id","page_no"];
		arr_fieldvalue =["checkinlistadditional",getprocessid(),gh_id,page_no];
		ajax_post_field_div("s_checkin",handle_additionalBed,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}

function handle_additionalBed()
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
//checkinSlip
function checkinSlip(page_no)
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
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =["checkinSlip",getprocessid(),gh_id,sel_block,page_no];
		ajax_post_field_div("s_checkin",handle_checkinSlip,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_checkinSlip()
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
//viewCheckinSlip(
function viewCheckinSlip(tr_id,page_no)
{
	//var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","gh_id","sel_block","tr_id","page_no"];
		arr_fieldvalue =["viewCheckinSlip",getprocessid(),gh_id,sel_block,tr_id,page_no];
		ajax_post_field_div("s_checkin",handle_viewdata,arr_fieldname,arr_fieldvalue,'viewdata');	
	}
}
function handle_viewdata()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


function checkout(page_no)
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
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =["checkoutlist",getprocessid(),gh_id,sel_block,page_no];
		ajax_post_field_div("s_checkin",handle_checkoutlistc,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_checkoutlistc()
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
//searchcheckinSlip
function searchcheckinSlip(page_no){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var date=document.getElementById('date').value;
	if(VAL_Slipcheckin()){
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","page_no"];
		arr_fieldvalue=["searchSlip",getprocessid(),gh_id,req_id,name,date,page_no];
		ajax_post_field_divH("s_checkin",arr_fieldname,arr_fieldvalue,'search');	
	}
}

function searchdata(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	//if(VAL_savecheckin()){
	if(req_id==""&&pro_id==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_data(req_id,pro_id,name,date)){
			arr_fieldname=["task","process_id","gh_id","req_id","name","pro_id","date","page_no"];
			arr_fieldvalue=["search",getprocessid(),gh_id,req_id,name,pro_id,date,page_no];
			ajax_post_field_divH("s_checkin",arr_fieldname,arr_fieldvalue,'search');	
		}}
}

function val_search_data(req_id,processid,name,date){

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


function searchdata_cancel(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	//if(VAL_savecheckin()){
	if(req_id==""&&pro_id==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_data(req_id,pro_id,name,date)){
			arr_fieldname=["task","process_id","gh_id","req_id","name","pro_id","date","page_no"];
			arr_fieldvalue=["searchcancel",getprocessid(),gh_id,req_id,name,pro_id,date,page_no];
			ajax_post_field_divH("s_checkin",arr_fieldname,arr_fieldvalue,'search');	
		}}
	//}
}

function searchdata_cancelcheckout(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	//if(VAL_savecheckin()){
	if(req_id==""&&pro_id==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_data(req_id,pro_id,name,date)){
			arr_fieldname=["task","process_id","gh_id","req_id","name","pro_id","date","page_no"];
			arr_fieldvalue=["searchcancelcheckout",getprocessid(),gh_id,req_id,name,pro_id,date,page_no];
			ajax_post_field_divH("s_checkin",arr_fieldname,arr_fieldvalue,'search');	
		}}
	//}
}

function searchdata_change(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	//if(VAL_savecheckin()){
	if(req_id==""&&pro_id==0&&name==""&&date==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_data(req_id,pro_id,name,date)){
			arr_fieldname=["task","process_id","gh_id","req_id","name","pro_id","date","page_no"];
			arr_fieldvalue=["searchchange",getprocessid(),gh_id,req_id,name,pro_id,date,page_no];
			ajax_post_field_divH("s_checkin",arr_fieldname,arr_fieldvalue,'search');	
		}}
}
function viewdata(req_id,page_no)
{
	var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(hidvalue==""){
		nic_alert("Select Items from the List","N");
	}else{
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id","hidvalue","page_no"];
		arr_fieldvalue =["viewlist",getprocessid(),gh_id,sel_block,req_id,hidvalue,page_no];
		ajax_post_field_div("s_checkin",handle_viewdata,arr_fieldname,arr_fieldvalue,'viewdata');	
	}
}
function handle_viewdata()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

function viewdataout(req_id,page_no)
{
	var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(hidvalue==""){
		nic_alert("Select Item(s) from the List","N");
	}else{
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id","hidvalue","page_no"];
		arr_fieldvalue =["viewlistout",getprocessid(),gh_id,sel_block,req_id,hidvalue,page_no];
		ajax_post_field("s_checkin",handle_viewdataout,arr_fieldname,arr_fieldvalue);	
	}
}
function handle_viewdataout()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML="";
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function save_checkin(req_id,page_no,from_date)
{

	if(VAL_savedata_withdate(from_date)){
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id","req_id","page_no"];
		arr_fieldvalue=["savecheckin",getprocessid(),req_id,page_no];
		ajax_post_form_field(ins_form,"s_checkin",handle_savecheckin,arr_fieldname,arr_fieldvalue);
	}
}
function handle_savecheckin(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
			close_nicpopup();
			nic_alert("Checked in Successfully.","N");
			checkin(n[3]);
			$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";

		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function save_checkout(req_id,page_no)
{
	if(VAL_checkout_withdate()){
		var hidvalue = document.getElementById('hidvalue').value;
		var ins_form= document.getElementById('form-general');
		arr_fieldname=["task","process_id","req_id","hidvalue","page_no"];
		arr_fieldvalue=["savecheckout",getprocessid(),req_id,hidvalue,page_no];
		ajax_post_form_field(ins_form,"s_checkin",handle_savecheckout,arr_fieldname,arr_fieldvalue);
	}//add
}
function handle_savecheckout(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
			close_nicpopup();
			nic_alert("Checked out Successfully.","N");


			checkout(n[3]);
			$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";



		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}


function handle_make_bill_fromcheckout()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
			close_nicpopup();
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

function cancel(page_no)
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
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =["checkincancel",getprocessid(),gh_id,sel_block,page_no];
		ajax_post_field_div("s_checkin",handle_checkincancel,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_checkincancel()
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

function checkoutcancel(page_no)
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
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no"];
		arr_fieldvalue =["checkoutcancel",getprocessid(),gh_id,sel_block,page_no];
		ajax_post_field_div("s_checkin",handle_checkincancel,arr_fieldname,arr_fieldvalue,'dataEntry');	
	}
}
function handle_checkincancel()
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

function canceldata(req_id,page_no)
{
	var hidvalue = document.getElementById('hidvalue').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(hidvalue==""){
		nic_alert("Select Item(s) from the List","N");
	}else{
		arr_fieldname =["task","process_id","gh_id","sel_block","req_id","hidvalue","page_no"];
		arr_fieldvalue =["cancellist",getprocessid(),gh_id,sel_block,req_id,hidvalue,page_no];
		ajax_post_field("s_checkin",handle_canceldata,arr_fieldname,arr_fieldvalue);	
	}
}
function handle_canceldata()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;

		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}
function canceloutdata_CHECKOUT(page_no)
{


	if ($('#chck').is(":checked"))
	{
		var sel_block = document.getElementById('sel_block').value;
		var gh_id = document.getElementById('gh_id').value;
		var hidvalue = document.getElementById('hidvalue').value;
		var ins_form= document.getElementById('form-general');
		arr_fieldname =["task","process_id","gh_id","sel_block","page_no","hidvalue"];
		arr_fieldvalue =["canceloutlist",getprocessid(),gh_id,sel_block,page_no,hidvalue];//canceloutlist
		ajax_post_form_field(ins_form,"s_checkin",handle_canceloutdata_CHECKOUT,arr_fieldname,arr_fieldvalue);	
	}
	else 
	{
		nic_alert("Please select item(s) from the list","V");
	}





}
function handle_canceloutdata_CHECKOUT()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			show_nicpopup(results);
			//document.getElementById(resultDiv).innerHTML=results;

		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}


function cancel_checkin(req_id,page_no)
{
	//if(VAL_savedata()){
	var hidvalue = document.getElementById('hidvalue').value;
	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","req_id","hidvalue","page_no"];
	arr_fieldvalue=["cancelcheckin",getprocessid(),req_id,hidvalue,page_no];
	ajax_post_form_field(ins_form,"s_checkin",handle_cancelcheckin,arr_fieldname,arr_fieldvalue);
	//}
}
function handle_cancelcheckin(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			close_nicpopup();
			nic_alert("Checkin Cancelled Successfully.","N");
			var n=results.split("-");
			cancel(n[3]);
			$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";

		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
//-------------------------------------------------------------------------------------------validation-----------------------------------------------------------------
function VAL_Slipcheckin(){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	//var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	if((req_id=="") && (name=="")  && (date==""))
	{
		nic_alert("Please Enter data for searching","V");
		return false;
	}
	else{
		if(!req_id==""){ 
			val_search(req_id);

		}
		else if(!name==""){ 
			val_search(name);

		}


		else if(!date==""){ 
			val_search(date);

		}
	}
	return true;
}
function VAL_savecheckin(){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var pro_id=document.getElementById('pro_id').value;
	var date=document.getElementById('date').value;
	if((req_id=="") && (name=="") && (pro_id=="") && (date==""))
	{
		nic_alert("Please enter the any one data(reuest id,guest name,process id,date","V");
		return false;
	}
	else{
		if(!req_id==""){ 
			val_search(req_id);

		}
		else if(!name==""){ 
			val_search(name);

		}

		else if(!pro_id==""){ 
			val_search(pro_id);

		}
		else if(!date==""){ 
			val_search(date);

		}
	}
	return true;
}
function VAL_checkout_withdate(){ 
	count= document.getElementById('noofrows').value;
	var i;
	for(i=1;i<=count;i++){
		var out='time'+i;
		var outtime 	  = document.getElementById(out).value;

		var ckin='ckeckintime'+i;
		var ckintime 	  = document.getElementById(ckin).value;

		if(outtime== ""){
			nic_alert("Please Enter the Checkout Time","V");
			return false;
		}else if(outtime != ""){
			if(!isdateandtime_GT(ckintime,outtime)){
				nic_alert("Checkout Date must be greater than Checkin Date","V");
				return false;
			}
		}

	}
	return true;

}
function VAL_savedata_withdate(from_date){
	//var vc_id= document.getElementById('sel_vcid').value;
	//var quota=document.getElementById('sel_q').value;
	//if(val_vcid(vc_id))
	//if(val_type(quota))

	count= document.getElementById('noofrows').value;
	var i;
	for(i=1;i<=count;i++){
		var b = 'roll'+i;
		var bb 	  = document.getElementById(b).value;
		var c='time'+i;
		var cc 	  = document.getElementById(c).value;
		var d='amount'+i;
		var dd 	  = document.getElementById(d).value;
		var e='sel_res_wl'+i;
		var ee 	  = document.getElementById(e).value;

		var sel_vcid='sel_vcid'+i;
		var vcid 	  = document.getElementById(sel_vcid).value;

		var t_purpose='purpose'+i;
		var purpose 	  = document.getElementById(t_purpose).value;


		var f='adults'+i;
		var ff 	  = document.getElementById(f).value;	
		var g='children'+i;
		var gg 	  = document.getElementById(g).value;	
		//if( bb =="" || cc =="" || dd =="" || gg==""|| ff==""|| ee ==0)
		//{

		if(bb != "")
		{
			if(!isLEN(bb,1,10))
			{
				nic_alert("Allowed Length of Roll No. is in between 1 and 100","N");
				return false;
			}
			else if(!VAL_N(bb))
			{
				nic_alert("Please Enter Valid Roll No. Allowed Numeric Only.","N");
				return false;
			}
		}
		else{
			nic_alert("Please Enter the Roll No.","V");
			return false;
		}
		if(cc== ""){
			nic_alert("Please Enter the Time","V");
			return false;
		}else if(cc != ""){
			/*if(!VAL_D(cc))
				{
					nic_alert("Please Enter Valid Date","N");
					return false;
				{
				}else*/ if(!dateCompTwo1less2(from_date,cc)){
					nic_alert("You Cannot Checkin Before Requested From Date","N");
					return false;
				}
		}
		if(dd != ""){
			if(!isLEN(dd,1,10))
			{
				nic_alert("Allowed Length of Advance Amount is in between 1 and 10","N");
				return false;
			}
			else if(!VAL_N(dd))
			{
				nic_alert("Please Enter Valid Advance Amount. Allowed Numeric only.","N");
				return false;
			}
		}
//		else{
//		nic_alert("Please Enter the advance amount","V");
//		return false;
//		}
		if(gg != ""){
			if(!VAL_N(gg))
			{
				nic_alert("Please Enter Valid No. of Children. Allowed Numeric Only.","N");
				return false;
			}
		}
		if(ff != ""){
			if(!VAL_N(ff))
			{
				nic_alert("Please Enter Valid No. of Adults. Allowed Numeric Only.","N");
				return false;
			}
		}
		/*else{
				if(dd==""){
					nic_alert("Please Enter the advance amount","V");
					return false;
				}
			}*/

		if(vcid=='z'){
			nic_alert("Please Select the Category","V");
			return false;
		}

		if(purpose==0){
			nic_alert("Please Select the Purpose","V");
			return false;
		}

		if(ee== 0){
			nic_alert("Please Select the Room","V");
			return false;
		}

		var room_bed = ee.split("-");
		if(room_bed[2]!="R"){

			if(!isvalidroomcount())
			{
				nic_alert('Duplication in Beds!!!',"V");
				return false;

			}
		}

		//}
		return true;
	}
}

function isvalidroomcount(){
	count= document.getElementById('noofrows').value;
	for(var i=1;i<=count;i++)
	{

		var e='sel_res_wl'+i;
		var ee 	  = document.getElementById(e).value;
		var tempcount= ismaxcount(ee,'sel_res_wl',count);
		if(tempcount>1)
		{				   
			return false;
		}
	}
	return true;
}

function ismaxcount(ee,room,p_count)
{		
	var count=0;
	for(var i=1;i<=p_count;i++)
	{
		var temproom=document.getElementById(room+i).value;
		if(temproom==ee)
			count=count+1;
	}
	return count;
}

function VAL_savedata(){
	//var vc_id= document.getElementById('sel_vcid').value;
	//var quota=document.getElementById('sel_q').value;
	//if(val_vcid(vc_id))
	//if(val_type(quota))

	count= document.getElementById('noofrows').value;
	var i;
	for(i=1;i<=count;i++){
		var b = 'roll'+i;
		var bb 	  = document.getElementById(b).value;
		var c='time'+i;
		var cc 	  = document.getElementById(c).value;
		var d='amount'+i;
		var dd 	  = document.getElementById(d).value;
		var e='sel_res_wl'+i;
		var ee 	  = document.getElementById(e).value;
		var f='adults'+i;
		var ff 	  = document.getElementById(f).value;	
		var g='children'+i;
		var gg 	  = document.getElementById(g).value;	
		if( bb =="" || cc =="" || dd =="" || gg==""|| ff==""|| ee ==0)
		{

			if(bb != "")
			{
				if(!isLEN(bb,1,10))
				{
					nic_alert("Allowed Length of Roll No. is in between 1 and 100","N");
					return false;
				}
				else if(!VAL_N(bb))
				{
					nic_alert("Please Enter Valid Roll No. Allowed Numeric Only.","N");
					return false;
				}
			}
			else{
				nic_alert("Please Enter the Roll No.","V");
				return false;
			}
			if(cc== ""){
				nic_alert("Please Enter the Time","V");
				return false;
			}
			if(dd != ""){
				if(!isLEN(dd,1,10))
				{
					nic_alert("Allowed Length of Advance Amount is in between 1 and 10","N");
					return false;
				}
				else if(!VAL_N(dd))
				{
					nic_alert("Please Enter Valid Advance Amount. Allowed Numeric only.","N");
					return false;
				}
			}
//			else{
//			nic_alert("Please Enter the advance amount","V");
//			return false;
//			}
			if(gg != ""){
				if(!VAL_N(gg))
				{
					nic_alert("Please Enter Valid No. of Children. Allowed Numeric Only.","N");
					return false;
				}
			}
			if(ff != ""){
				if(!VAL_N(ff))
				{
					nic_alert("Please Enter Valid No. of Adults. Allowed Numeric Only.","N");
					return false;
				}
			}
			/*else{
				if(dd==""){
					nic_alert("Please Enter the advance amount","V");
					return false;
				}
			}*/


			if(ee== 0){
				nic_alert("Please Select the Room","V");
				return false;
			}
		}
		return true;
	}
}

function cancel_checkout(req_id,guestlist,page_no)
{
	//if(VAL_savedata()){

	var ins_form= document.getElementById('form-general');
	arr_fieldname=["task","process_id","req_id","guestlist","page_no"];
	arr_fieldvalue=["cancelcheckout",getprocessid(),req_id,guestlist,page_no];
	ajax_post_form_field(ins_form,"s_checkin",handle_cancel_checkout,arr_fieldname,arr_fieldvalue);
	//}
}
function handle_cancel_checkout(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			var n=results.split("-");
			close_nicpopup();
			nic_alert("Checkout Cancelled Successfully.","N");
			checkoutcancel(n[3]);
			$('#form-general')[0].reset();
			document.getElementById(results).innerHTML="";

		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

