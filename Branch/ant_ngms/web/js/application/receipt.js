 

$(document).on('click', '.rad', function () {
	$('#radioselect input').on('change', function() {
		  // alert($('input[name=radioName]:checked', '#radioselect').val()); 
		
		var ccd= $('#radioselect input:checked').val();
		//alert(ccd);
		
		if (ccd=="POS"){
			$("#text_se").removeAttr('disabled','disabled');
			$('#text_se').attr('placeholder','Enter Transaction Number');
		//	$('#text_se').val("").prop("placeholder","Enter Check Number");
			
		}
		if (ccd=="ONLINE"){
			$("#text_se").attr('disabled','disabled');
		}
		
		if (ccd=="CASH"){
			$("#text_se").attr('disabled','disabled');
		}
		/*if ($(ccd.val("CHEQUE")))
			{
			alert();
			$('#se').val("CHEQUE");
			$('#se').removeAttr('disabled','disabled');
			$('#se').attr('enabled','');
			}*/
		/*
		if ($('input[id=radioselect]:checked').val("CHEQUE"))
				{
					alert("CHEQUE");
				}
		else if ($('input[id=radioselect]').val("DD"))
		{
			alert("DD");
		}
		
		else if ($('input[id=radioselect]').val("CASH"))
		{
			alert("CASH");
		}*/
				
		});
	
	
});


//multiple checkbox value stored in one variable
$(document).on('click', '#chck', function () {
	var cx=	$('#chck:checked').map(function() {return this.value;}).get().join("','");
	$('#hidvalue').val(cx);
});



//convert the rupees to word
var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];


function inWords(num) {
	 

    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only ' : '';
  
   return str;
	
}

function save_payment(){
	$('#frmPayment').submit();
}

function getBlocks(){
	ins_form= document.getElementById('form-general1');
	var gh_id = document.getElementById('sel_id').value;
	arr_fieldname =["task","process_id","gh_id"];
	arr_fieldvalue =["getblock",getprocessid(),gh_id];
	document.getElementById("header_div").innerHTML = "";
	ajax_post_form_field_divH(ins_form, "s_receipt", arr_fieldname, arr_fieldvalue, 'header_div');
}

function search_show_receipt(page_no){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var rec_no = document.getElementById('rec_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&rec_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_receipt(req_id,name,date)){
			if(!rec_no==""){
				if(!isLEN(rec_no,1,25)){
					 
					nic_alert("Invalid Length for Receipt No.","V");
					return false;
				}
				else if(!VAL_N(rec_no)){
					 
					nic_alert("Invalid Receipt No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","bill_no","page_no"];
		arr_fieldvalue=["searchShowRec",getprocessid(),gh_id,req_id,name,date,bill_no,page_no];
		ajax_post_field_divH("s_receipt",arr_fieldname,arr_fieldvalue,'BillShowList');	
	}}
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

function val_search_receipt(req_id,name,date){

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

function search_bill_toreceipt(page_no){
	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var bill_no = document.getElementById('bill_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&bill_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_bill_toreceipt(req_id,name,date)){
			if(!bill_no==""){
				if(!isLEN(bill_no,1,25)){
				 
					nic_alert("Invalid Length for Bill No.","V");
					return false;
				}
				else if(!VAL_N(bill_no)){
				 
					nic_alert("Invalid Bill No.","V");
					return false;
				}
			}
	arr_fieldname=["task","process_id","gh_id","req_id","name","date","bill_no","page_no","sel_block"];
	arr_fieldvalue=["searchShowBill",getprocessid(),gh_id,req_id,name,date,bill_no,page_no,sel_block];
	ajax_post_field_divH("s_receipt",arr_fieldname,arr_fieldvalue,'RecieptShowList');	
}}}

function val_search_bill_toreceipt(req_id,name,date){

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


function addreceipt()
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
		arr_fieldvalue =['addreceipt',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_receipt",handle_add_receipt,arr_fieldname,arr_fieldvalue,'receipt');
	}
}

function addreceiptPayment()
{
	var gh_id = document.getElementById('ghId').value;
	var sel_block = document.getElementById('blockId').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['abc',getprocessid(),gh_id,sel_block];	
		post_field("s_receipt",arr_fieldname,arr_fieldvalue);
	}
}

function handle_add_receipt()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		console.log(results);
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

//listreceipt
function listreceipt(page_no)
{
	var gh_id = document.getElementById('sel_id').value;
	var sel_block = document.getElementById('sel_block').value;
	if(gh_id==0){
		nic_alert("Please Select the  City","V");
	}
	else if(sel_block==0){
		nic_alert("Please Select the guest house","V");
	}else{ 
		
		arr_fieldname =["task","process_id","gh_id","page_no","sel_block"];
		arr_fieldvalue =['listreceipt',getprocessid(),gh_id,page_no,sel_block];	
		ajax_post_field_div("s_receipt",handle_list_receipt,arr_fieldname,arr_fieldvalue,'receipt');
	}
	
}
function handle_list_receipt()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			document.getElementById(resultDiv).innerHTML=results;
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}




//add_guest_receipt(
function add_guest_receipt(gh_id,sel_block)
{	
		arr_fieldname =["task","process_id","gh_id","sel_block"];
		arr_fieldvalue =['addguestreceipt',getprocessid(),gh_id,sel_block];	
		ajax_post_field_div("s_receipt",handle_add_guest_receipt,arr_fieldname,arr_fieldvalue,'listd');
}
function handle_add_guest_receipt()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}


//list_guest_receipt(
function list_guest_receipt(gh_id)
{		
		arr_fieldname =["task","process_id","gh_id"];
		arr_fieldvalue =['listguestreceipt',getprocessid(),gh_id];	
		ajax_post_field_div("s_receipt",handle_list_guest_receipt,arr_field7ame,arr_fieldvalue,'receipt');
}
function handle_list_guest_receipt()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")
		{
			//alert(results);
			show_nicpopup(results);
		}
		else
		{
			nic_alert(results.substr(8),"N");
		}
	}
}

//click make receipt
function make_receipt_guestlist(gh_id,sel_block)
{
	
	var sThisVal = 0;
	$('input[type=checkbox]').each(function () {
		if(this.checked) { sThisVal = 1 ;}
	});
	
	if(sThisVal == 1)
	{
	var ins_form= document.getElementById('form-add-bill');
	arr_fieldname =["task","process_id","gh_id","sel_block"];
	arr_fieldvalue =['makereceiptbilling',getprocessid(),gh_id,sel_block];	
	ajax_post_form_field_div(ins_form,"s_receipt",handle_make_bill_guestlist,arr_fieldname,arr_fieldvalue,'receipt');
	}else
	{
		nic_alert("Please Select a Receipt","N");
	}
}

function handle_make_bill_guestlist()
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
function handle_viewdatareceipt()
{
	if(http.readyState == 4)
	{
		results = http.responseText;
		if(results.substr(0,7)!= "Message")	{
			//show_nicpopup(results); 
			close_nicpopup();
			document.getElementById(resultDiv).innerHTML=results;
		}
		else {
			nic_alert(results.substr(8),"V");
		}
	}
}

//printview the html list
function printview_receipt(bill_values)
{
	//alert("bill_values "+bill_values);
	//if(VAL_savedata()){
	    var ins_form= document.getElementById('form-general');
	    var gh_id=document.getElementById('gh_id').value;
	    var sel_block=document.getElementById('sel_block').value;
	    //var hidvalue = document.getElementById("hidvaluepop").value;
	    arr_fieldname=["task","process_id","bill_values","gh_id","sel_block","flag"];
		arr_fieldvalue=["printview",getprocessid(),bill_values,gh_id,sel_block,"REC"];
		ajax_post_field_div("s_receipt",handle_printview,arr_fieldname,arr_fieldvalue,'viewdata');
	//}
}
function handle_printview(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}

function submit11(req_id,flag,due_id)
{
	
	if(VAL_submit()){
	    ins_form= document.getElementById('form-general');
	    var total = document.getElementById('total').value;
	    arr_fieldname=["task","process_id","req_id","total","flag","due_id"];
		arr_fieldvalue=["submitreceipt",getprocessid(),req_id,total,flag,due_id];
		$('#popup').show();
		ajax_post_form_field(ins_form,"s_receipt",handle_submit11,arr_fieldname,arr_fieldvalue);
	 }
}
function handle_submit11(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			
			//document.getElementById(resultDiv).innerHTML=results;
			/*nic_alert("Created Successfully.","N");*/
			close_nicpopup();
			//menu_url_call('s_receipt?task=abc',getprocessid());
			/*listreceipt();*/
			//$('#form-general')[0].reset();
			//document.getElementById(results).innerHTML="";
			
			var arr_result = results.split("-");
			var rec_id = arr_result[4];
			var gh_id = arr_result[3];
			var flag = arr_result[5];
			var mode = arr_result[6];
			var div = "";
			if(flag=="DUES"){
				div = 'dues';
			}else{
				div = 'receipt';
			}
			if(mode == 'CASH' || mode == 'POS'){
				arr_fieldname=["task","process_id","rec_id","gh_id","flag"];
				arr_fieldvalue=["receiptprint",getprocessid(),rec_id,gh_id,flag];
				ajax_post_field_divH("s_receipt",arr_fieldname,arr_fieldvalue,div);
			}else{
				arr_fieldname=["task","process_id","rec_id","gh_id","flag"];
				arr_fieldvalue=["paymentConformation",getprocessid(),rec_id,gh_id,flag];
				ajax_post_field_divH("s_receipt",arr_fieldname,arr_fieldvalue,div);
			}
			$('#popup').hide();
		}else{
			nic_alert(results.substr(8),"V");
			$('#popup').hide();
		}
	}
}

//pdf
function print_pdf(gh_id,rec_id,block)
{
	//var ins_form= document.getElementById('form-general');
	arr_fieldname =["task","process_id","rec_id","gh_id","block"];
	arr_fieldvalue =['printpdf',getprocessid(),rec_id,gh_id,block];	
	post_field("doc_handler",arr_fieldname,arr_fieldvalue,true);
}

//show_receiptforpdf
function show_receiptforpdf(gh_id,rec_id)
{
	//alert(gh_id);
	//alert(bill_id);
	//if(VAL_savedata()){
	    //var ins_form= document.getElementById('form-general');
	    //var hidvalue = document.getElementById('hidvalue').value;
	    arr_fieldname=["task","process_id","gh_id","rec_id"];
		arr_fieldvalue=["printviewlist",getprocessid(),gh_id,rec_id];
		ajax_post_field("s_receipt",handle_printview,arr_fieldname,arr_fieldvalue);
	//}
}
function handle_printview(){
	if(http.readyState == 4){
		results = http.responseText;
		if(results.substr(0,7)!= "Message"){
			show_nicpopup(results);
			
		}else{
			nic_alert(results.substr(8),"V");
		}
	}
}
function VAL_submit(){
	//var ss = $("#se:checked").val();
	var ss= $('.rad:checked').val();
	 
	var text_ruppees = document.getElementById("text_ruppees").value;
	if(text_ruppees==""){
		nic_alert("plese enter the amount to pay","N");
		return false;
	} 
	 
	if(!VAL_ND(text_ruppees))
	{
		nic_alert("Enter valid amount","N");
		return false;
	} 
	
	
	if(ss=='POS'){
	 	
	var tt = document.getElementById("text_se").value;
	if(tt==""){
		nic_alert("plese enter the  "+ss+"  number.","N");
		return false;
	} 
	 
	if(!VAL_AN(tt))
	{
		nic_alert("Only Numbers and characters are allowed","N");
		return false;
	} 
	 
}
	return true;
	
}


function search_show_receipt(page_no){

	var req_id=  document.getElementById('req_id').value;
	var name =  document.getElementById('name').value;
	var gh_id = document.getElementById('gh_id').value;
	var sel_block = document.getElementById('sel_block').value;
	var rec_no = document.getElementById('rec_no').value;
	var date=document.getElementById('date').value;
	if(req_id==""&&name==""&&date==""&&rec_no==""){
		nic_alert("Please Enter data for searching","N");
	}else{
		if(val_search_show_receipt(req_id,name,date)){
			if(!rec_no==""){
				if(!isLEN(rec_no,1,25)){
					 
					nic_alert("Invalid Length for Receipt No.","V");
					return false;
				}
				else if(!VAL_N(rec_no)){
					 
					nic_alert("Invalid Bill No.","V");
					return false;
				}
			}
		arr_fieldname=["task","process_id","gh_id","req_id","name","date","rec_no","page_no","sel_block"];
		arr_fieldvalue=["searchShowRec",getprocessid(),gh_id,req_id,name,date,rec_no,page_no,sel_block];
		ajax_post_field_divH("s_receipt",arr_fieldname,arr_fieldvalue,'ReceiptShowList');	
	}}
}
 
function val_search_show_receipt(req_id,name,date){

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
